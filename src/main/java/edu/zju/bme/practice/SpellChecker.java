package edu.zju.bme.practice;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SpellChecker {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		List<String> dictionary = new ArrayList<String>();
		List<String> words = new ArrayList<String>();
		String input = in.next();
		while (!input.equals("#")) {
			dictionary.add(input);
			input = in.next();
		}
		input = in.next();
		while (!input.equals("#")) {
			words.add(input);
			input = in.next();
		}
		for (String word : words) {
			if (dictionary.contains(word)) {
				System.out.println(word + " is correct");
			} else {
				System.out.print(word + ": ");
				for (String dict : dictionary) {
					int offset = 0;
					String longWord = "";
					String shortWord = "";
					if (word.length() == dict.length()) {
						offset = 0;
						longWord = dict;
						shortWord = word;
					} else if (word.length() == dict.length() - 1) {
						longWord = dict;
						shortWord = word;
						offset = 1;
					} else if (word.length() == dict.length() + 1) {
						longWord = word;
						shortWord = dict;
						offset = 1;
					} else {
						continue;
					}
					boolean isMatch = true;
					boolean hasPassDifferentLetter = false;
					char[] letters = longWord.toCharArray();
					for (int i = 0; i < letters.length; i++) {
						if (!hasPassDifferentLetter) {
							if (i <shortWord.length() && letters[i] != shortWord.charAt(i)) {
								hasPassDifferentLetter = true;
							}
						} else {
							if (letters[i] != shortWord.charAt(i - offset)) {
								isMatch = false;
								break;
							}
						}
					}
					if (isMatch) {
						System.out.print(dict + " ");
					}
				}
				System.out.println();
			}
		}
	}
}
