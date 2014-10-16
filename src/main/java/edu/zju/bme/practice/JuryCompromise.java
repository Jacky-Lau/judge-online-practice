package edu.zju.bme.practice;

import java.util.Arrays;
import java.util.Scanner;

public class JuryCompromise {
	
	static short[] p, d;
	static short[][][] arr, path;
	static int add, a, b, st, ed;

	public static void others(String[] args) {
		Scanner in = new Scanner(System.in);
		int cnt = 0;
		while (true) {
			cnt++;
			a = in.nextInt();
			b = in.nextInt();
			if (a == 0 && b == 0)
				break;
			System.out.println("Jury #" + cnt);
			p = new short[a + 1];
			d = new short[a + 1];
			for (int i = 1; i <= a; i++) {
				p[i] = in.nextShort();
				d[i] = in.nextShort();
			}
			dp();
			for (int k = 0; k <= add; k++) {
				if (arr[a][b][add + k] > arr[a][b][add - k]) {
					System.out.println("Best jury has value "
							+ ((arr[a][b][add + k] + k) / 2)
							+ " for prosecution and value "
							+ ((arr[a][b][add + k] - k) / 2) + " for defence:");
					print(a, b, k);
					break;
				} else if (arr[a][b][add - k] != -1) {
					System.out.println("Best jury has value "
							+ ((arr[a][b][add - k] - k) / 2)
							+ " for prosecution and value "
							+ ((arr[a][b][add - k] + k) / 2) + " for defence:");
					print(a, b, -k);
					break;
				}
			}
			System.out.println();
			System.out.println();
		}
	}

	static void dp() {
		arr = new short[a + 1][b + 1][40 * b + 30];
		path = new short[a + 1][b + 1][40 * b + 30];
		add = 20 * b;
		st = -b * 20;
		ed = -st;
		for (int i = 0; i <= a; i++)
			for (int j = 0; j <= b; j++)
				for (int k = st; k <= ed; k++)
					arr[i][j][k + add] = -1;
		arr[0][0][add] = 0;
		for (int i = 1; i <= a; i++)
			for (int j = 0; j <= b; j++)
				for (int k = st; k <= ed; k++) {
					arr[i][j][k + add] = arr[i - 1][j][k + add];
					path[i][j][k + add] = 1;
					if (j > 0
							&& k - p[i] + d[i] >= -add
							&& k - p[i] + d[i] <= add
							&& arr[i - 1][j - 1][k - p[i] + d[i] + add] != -1
							&& arr[i - 1][j - 1][k - p[i] + d[i] + add] + p[i]
									+ d[i] > arr[i][j][k + add]) {
						arr[i][j][k + add] = (short) (arr[i - 1][j - 1][k
								- p[i] + d[i] + add]
								+ p[i] + d[i]);
						path[i][j][k + add] = 2;
					}
				}
	}

	static void print(int i, int j, int k) {
		if (i == 0 && j == 0 && k == 0)
			return;
		if (path[i][j][k + add] == 1)
			print(i - 1, j, k);
		else {
			print(i - 1, j - 1, k - p[i] + d[i]);
			System.out.print(" " + i);
		}
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int round = 0;
		int candidateQuantity = in.nextInt();
		int juryQuantity = in.nextInt();
		while (candidateQuantity != 0 && juryQuantity != 0) {
			round++;
			int[][] f = new int[juryQuantity + 1][juryQuantity * 40 + 1];
			int[] variances = new int[candidateQuantity];
			int[] defences = new int[candidateQuantity];
			int[] prosecutions = new int[candidateQuantity];
			int[] sums = new int[candidateQuantity];
			int[][] path = new int[juryQuantity + 1][juryQuantity * 40 + 1];
			for (int i = 0; i < candidateQuantity; i++) {
				defences[i] = in.nextInt();
				prosecutions[i] = in.nextInt();
				variances[i] = 20 + prosecutions[i] - defences[i];
				sums[i] = defences[i] + prosecutions[i];
			}
			for (int i = 0; i < f[0].length; i++) {
				f[0][i] = -1;
			}
			f[0][0] = 0;
			for (int i = 1; i <= juryQuantity; i++) {
				for (int j = 0; j <= juryQuantity * 40; j++) {
					boolean possible = false;
					for (int k = 0; k < candidateQuantity; k++) {
						boolean alreadySelected = false;
						int varianceSum = j;
						int selectedJury = k + 1;
						for (int l = i; l > 0; l--) {
							varianceSum -= variances[selectedJury - 1];
							if (varianceSum < 0 || f[l - 1][varianceSum] < 0) {
								alreadySelected = true;
								break;
							}
							if (path[l - 1][varianceSum] == k + 1) {
								alreadySelected = true;
								break;
							} else {
								selectedJury = path[l - 1][varianceSum];
							}
						}
						if (!alreadySelected) {
							if (f[i - 1][j - variances[k]] + sums[k] > f[i][j]) {
								f[i][j] = f[i - 1][j - variances[k]] + sums[k];
								path[i][j] = k + 1;
								possible = true;
							}
						}

					}
					if (!possible) {
						f[i][j] = -1;
					}
				}
			}
			System.out.println("Jury #" + round);
			int[] juries = new int[juryQuantity];
			for (int i = 0; i <= juryQuantity * 20; i++) {
				int left = f[juryQuantity][juryQuantity * 20 - i];
				int right = f[juryQuantity][juryQuantity * 20 + i];
				if (left >= 0 || right >= 0) {
					int factor = 0;
					if (left >= 0 && right < 0) {
						factor = -1;
					} else if (left < 0 && right >= 0) {
						factor = 1;
					} else if (left >= 0 && right >= 0) {
						if (left >= right) {
							factor = -1;
						} else {
							factor = 1;
						}
					}
					int varianceSum = juryQuantity * 20 + factor * i;
					juries[juryQuantity - 1] = path[juryQuantity][varianceSum];
					for (int l = juryQuantity - 1; l > 0; l--) {
						varianceSum -= variances[juries[l] - 1];
						juries[l - 1] = path[l][varianceSum];
					}
					int defenceSum = 0;
					int prosecutionSum = 0;
					for (int j = 0; j < juries.length; j++) {
						defenceSum += defences[juries[j] - 1];
						prosecutionSum += prosecutions[juries[j] - 1];
					}
					System.out.println("Best jury has value " + prosecutionSum
							+ " for prosecution and value " + defenceSum
							+ " for defence:");
					Arrays.sort(juries);
					for (int j = 0; j < juries.length; j++) {
						System.out.print(" " + juries[j]);
					}
					System.out.println();
					System.out.println();
					break;
				}
			}
			candidateQuantity = in.nextInt();
			juryQuantity = in.nextInt();
		}
	}
}
