package s000;

import java.util.Scanner;

import core.IOLib;

public class P042 {
	public static void main(String[] args) {
		Scanner in = IOLib.scanner("data/042.txt");
		String[] words = in.nextLine().split(",");
		IOLib.close(in);
		int ans = 0;
		for (String word : words) {
			word = word.substring(1, word.length() - 1);
			if (isTriangle(score(word))) {
				ans++;
			}
		}
		System.out.println(ans);
	}

	static int score(String s) {
		int ans = 0;
		for (char c : s.toCharArray()) {
			ans += c - 'A' + 1;
		}
		return ans;
	}

	static boolean isTriangle(int n) {
		n *= 2;
		int s = (int) Math.sqrt(n);
		return n == s * (s + 1);
	}
}
