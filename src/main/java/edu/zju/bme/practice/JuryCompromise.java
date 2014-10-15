package edu.zju.bme.practice;

import java.util.Scanner;

public class JuryCompromise {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int round = 0;
		String[] requirement = in.nextLine().split(" ");
		int candidateQuantity = Integer.valueOf(requirement[0]);
		int juryQuantity = Integer.valueOf(requirement[1]);
		while (candidateQuantity != 0 && juryQuantity != 0) {
			int[][] f = new int[candidateQuantity][801];
			int[] variances = new int[candidateQuantity];
			int[] sums = new int[candidateQuantity];
			int[][] path = new int[candidateQuantity][801];
			for (int i = 0; i < candidateQuantity; i++) {
				String[] input = in.nextLine().split(" ");
				variances[i] = Integer.valueOf(input[0])
						- Integer.valueOf(input[1]);
				sums[i] = Integer.valueOf(input[0]) - Integer.valueOf(input[1]);
			}
			for (int i = 0; i < juryQuantity; i++) {
				for (int j = 0; j < 801; j++) {
					for (int k = 0; k < candidateQuantity; k++) {
						boolean alreadySelected = false;
						if (!alreadySelected) {
							if (f[i][j - variances[k]] + sums[k] > f[i][j]) {
								f[i][j] = f[i][j - variances[k]] + sums[k];
								path[i][j] = k;
							}
						}
					}
				}
			}

		}
	}
}
