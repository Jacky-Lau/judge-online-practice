package edu.zju.bme.practice;

import java.util.Scanner;

public class Cipher {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int k = in.nextInt();
		while (k != 0) {
			int count = k;
			int[] keys = new int[count];
			for (int i = 0; i < count; i++) {
				keys[i] = in.nextInt() - 1;
			}
			int[] loopLength = new int[count];
			for (int i = 0; i < count; i++) {
				loopLength[i] = 0;
				int position = i;
				do {
					position = keys[position];
					loopLength[i]++;
				} while (position != i);
			}
			k = in.nextInt();
			while (k != 0) {
				in.skip(" ");
				String msg = in.nextLine();
				char[] encrypted = new char[count];
				for (int i = 0; i < count; i++) {
					int loopCount = k % loopLength[i];
					int position = i;
					while (loopCount-- > 0) {
						position = keys[position];
					}
					if (i < msg.length()) {
						encrypted[position] = msg.charAt(i);
					} else {
						encrypted[position] = ' ';
					}
				}
				for (int i = 0; i < encrypted.length; i++) {
					if (encrypted[i] == 0) {
						encrypted[i] = ' ';
					}
				}
				System.out.println(encrypted);
				k = in.nextInt();
			}
			System.out.println();
			k = in.nextInt();
		}
	}
}
