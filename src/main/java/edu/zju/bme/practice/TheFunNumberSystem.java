package edu.zju.bme.practice;

import java.util.Scanner;

class Parameters {

	private int length = 0;
	private String pattern = "";
	private int number = 0;

	public Parameters() {

	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

}

public class TheFunNumberSystem {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int count = scanner.nextInt();
		Parameters[] params = new Parameters[count];
		for (int i = 0; i < count; i++) {
			params[i] = new Parameters();
			params[i].setLength(scanner.nextInt());
			params[i].setPattern(scanner.next());
			params[i].setNumber(scanner.nextInt());
		}

		for (Parameters param : params) {
			int length = param.getLength();
			String pattern = param.getPattern();
			int number = param.getNumber();
			double[] factors = new double[length];
			for (int i = 0; i < length; i++) {
				char bit = pattern.charAt(i);
				int index = length - i - 1;
				if (bit == 'n') {
					factors[index] = -Math.pow(2, index);
				} else if (bit == 'p') {
					factors[index] = Math.pow(2, index);
				}
			}
			boolean isPossible = false;
			for (int j = 0; j < Math.pow(2, length); j++) {
				double total = 0;
				for (int k = 0; k < length; k++) {
					if (((j >> (length - k)) & 1) == 1) {
						total += factors[length - k];
					}
				}
				if (total == number) {
					isPossible = true;
					System.out.println(Integer.toBinaryString(j));
					break;
				}
			}
			if (!isPossible) {
				System.out.println("Impossible");
			}
		}
	}
}
