package edu.zju.bme.practice;

import java.math.*;
import java.util.Scanner;

public class TheFunNumberSystem {

	public static String solve(String s, long n) {
		String result = "";
		boolean flag = n > 0 ? true : false;
		n = n > 0 ? n : -n;
		for (int i = s.length() - 1; i >= 0; --i) {
			char ch = s.charAt(i);
			switch (ch) {
			case 'p':
				if (flag) {
					result += n % 2;
					n /= 2;
					break;
				} else {
					result += n % 2;
					n = (n + n % 2 ) / 2;
					break;
				}
			case 'n':
				if (flag) {
					result += n % 2;
					n = (n + n % 2 ) / 2;
					break;
				} else {
					result += n % 2;
					n = n / 2;
					break;
				}
			}
		}
		StringBuffer one = new StringBuffer(result);
		if (n == 0)
			result = one.reverse().toString();
		if (n != 0)
			result = "Impossible";
		return result;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String s;
		long len;
		long n;
		int test;
		test = in.nextInt();
		for (int i = 0; i < test; i++) {
			len = in.nextLong();
			s = in.next();
			n = in.nextLong();
			System.out.println(TheFunNumberSystem.solve(s, n));
		}
	}
}