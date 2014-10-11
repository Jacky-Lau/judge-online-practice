package edu.zju.bme.practice;

import java.util.Scanner;

public class CounterfeitDollar {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int cases = Integer.valueOf(in.nextLine());
		while (cases-- > 0) {
			String[][] input = new String[3][3];
			input[0] = in.nextLine().split(" ");
			input[1] = in.nextLine().split(" ");
			input[2] = in.nextLine().split(" ");
			Integer[] coins = new Integer[12];
			int[] times = new int[12];
			int notEvenTimes = 0;
			for (int i = 0; i < 3; i++) {
				String balance = input[i][2];
				if (balance.equals("even")) {
					for (char coin : input[i][0].toCharArray()) {
						coins[coin - 'A'] = 0;
					}
					for (char coin : input[i][1].toCharArray()) {
						coins[coin - 'A'] = 0;
					}
				} else if (balance.equals("up")) {
					notEvenTimes++;
					for (char coin : input[i][0].toCharArray()) {
						if (coins[coin - 'A'] == null) {
							coins[coin - 'A'] = 1;
							times[coin - 'A'] ++;
						} else if (coins[coin - 'A'] < 0) {
							coins[coin - 'A'] = 0;
						}else if (coins[coin - 'A'] > 0) {
							times[coin - 'A'] ++;
						}
					}
					for (char coin : input[i][1].toCharArray()) {
						if (coins[coin - 'A'] == null) {
							coins[coin - 'A'] = -1;
							times[coin - 'A'] ++;
						} else if (coins[coin - 'A'] > 0) {
							coins[coin - 'A'] = 0;
						}else if (coins[coin - 'A'] < 0) {
							times[coin - 'A'] ++;
						}
					}
				} else if (balance.equals("down")) {
					notEvenTimes++;
					for (char coin : input[i][0].toCharArray()) {
						if (coins[coin - 'A'] == null) {
							coins[coin - 'A'] = -1;
							times[coin - 'A'] ++;
						} else if (coins[coin - 'A'] > 0) {
							coins[coin - 'A'] = 0;
						}else if (coins[coin - 'A'] < 0) {
							times[coin - 'A'] ++;
						}
					}
					for (char coin : input[i][1].toCharArray()) {
						if (coins[coin - 'A'] == null) {
							coins[coin - 'A'] = 1;
							times[coin - 'A'] ++;
						} else if (coins[coin - 'A'] < 0) {
							coins[coin - 'A'] = 0;
						}else if (coins[coin - 'A'] > 0) {
							times[coin - 'A'] ++;
						}
					}
				}
			}
			for (int i = 0; i < 12; i++) {
				if (coins[i] != null && coins[i] != 0) {
					if (coins[i] > 0 && times[i] == notEvenTimes) {
						char counterfeit = (char) ('A' + i);
						System.out.println(counterfeit
								+ " is the counterfeit coin and it is heavy.");
					} else if(coins[i] < 0 && times[i] == notEvenTimes){
						char counterfeit = (char) ('A' + i);
						System.out.println(counterfeit
								+ " is the counterfeit coin and it is light.");
					}
				}
			}
		}
	}
}
