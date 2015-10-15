package s000;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

import core.IOLib;

public class P098 {
	static class Signature {
		int[] v;

		Signature(String s) {
			v = new int[26];
			for (char c : s.toCharArray()) {
				v[c - 'A']++;
			}
		}

		public int hashCode() {
			return Arrays.hashCode(v);
		}

		public boolean equals(Object other) {
			return Arrays.equals(v, ((Signature) other).v);
		}
	}

	static boolean isSquare(int n) {
		int s = (int) Math.sqrt(n);
		return s * s == n;
	}

	static int ANS = 0;

	static void search(String a, String b, int v) {
		boolean done = true;
		for (int i = 0; i < a.length(); i++) {
			char c = a.charAt(i);
			if (!Character.isDigit(c)) {
				for (int d = 0; d <= 9; d++) {
					if ((v & (1 << d)) == 0) {
						char r = (char) ('0' + d);
						search(a.replace(c, r), b.replace(c, r), v | 1 << d);
					}
				}
				done = false;
				break;
			}
		}

		if (done) {
			if (a.charAt(0) != '0' && b.charAt(0) != '0') {
				int x = Integer.parseInt(a);
				int y = Integer.parseInt(b);
				if (isSquare(x) && isSquare(y)) {
					ANS = Math.max(ANS, Math.max(x, y));
				}
			}
		}
	}

	public static void main(String[] args) {
		Scanner in = IOLib.scanner("data/098.txt");
		String str = in.nextLine();
		in.close();
		String[] a = str.split(",");
		HashMap<Signature, ArrayList<String>> T = new HashMap<Signature, ArrayList<String>>();
		for (String s : a) {
			s = s.substring(1, s.length() - 1);
			Signature x = new Signature(s);
			if (!T.containsKey(x)) {
				T.put(x, new ArrayList<String>());
			}
			T.get(x).add(s);
		}

		for (Signature x : T.keySet()) {
			ArrayList<String> S = T.get(x);
			if (S.size() > 1) {
				for (int i = 0; i < S.size(); i++) {
					for (int j = i + 1; j < S.size(); j++) {
						search(S.get(i), S.get(j), 0);
					}
				}
			}
		}

		System.out.println(ANS);
	}
}
