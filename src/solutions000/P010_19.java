package solutions000;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

import core.Euler;
import core.IOLib;
import core.MathLib;
import core.NTLib;
import core.PFac;
import core.PList;

public class P010_19 {
	static void p010() {
		LinkedList<Integer> ps = NTLib.simpleList(2000000);
		long ans = 0;
		for (int p : ps) {
			ans += p;
		}
		Euler.printAnswer(10, ans);
	}

	static void p011() {
		Scanner in = IOLib.scanner("011.txt");
		int[][] a = new int[20][20];
		for (int i = 0; i < 20; i++) {
			String[] ns = in.nextLine().split(" ");
			for (int j = 0; j < 20; j++) {
				a[i][j] = Integer.parseInt(ns[j]);
			}
		}
		IOLib.close(in);
		int ans = 0;
		for (int i = 3; i < 20; i++) {
			for (int j = 3; j < 20; j++) {
				ans = Math.max(ans, a[i - 3][j - 3] * a[i - 2][j - 2] * a[i - 1][j - 1] * a[i][j]);
				ans = Math.max(ans, a[i - 3][j] * a[i - 2][j - 1] * a[i - 1][j - 2] * a[i][j - 3]);
			}
		}
		for (int i = 0; i < 20; i++) {
			for (int j = 3; j < 20; j++) {
				ans = Math.max(ans, a[i][j - 3] * a[i][j - 2] * a[i][j - 1] * a[i][j]);
			}
		}
		for (int i = 3; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				ans = Math.max(ans, a[i - 3][j] * a[i - 2][j] * a[i - 1][j] * a[i][j]);
			}
		}
		Euler.printAnswer(11, ans);
	}

	static void p012() {
		PList p = new PList();
		long ans = 0;
		for (int i = 2;; i++) {
			if (p.ps.getLast() < i) {
				p.expand();
			}
			PFac a = PFac.make(i - 1, p.ps);
			PFac b = PFac.make(i, p.ps);
			PFac c = a.multiply(b);
			c.twos--;
			if (c.divisorCount() > 500) {
				ans = c.longValue();
				break;
			}
		}
		Euler.printAnswer(12, ans);
	}

	static void p013() {
		Scanner in = IOLib.scanner("013.txt");
		BigInteger ans = BigInteger.ZERO;
		for (int i = 0; i < 100; i++) {
			ans = ans.add(new BigInteger(in.nextLine()));
		}
		IOLib.close(in);
		Euler.printAnswer(13, ans.toString().substring(0, 10));
	}

	static void p014() {
		int max = 0;
		int ans = -1;
		for (int i = 2; i < 1000000; i++) {
			long j = i;
			int k = 0;
			while (j != 1) {
				k++;
				if (j % 2 == 0) {
					j /= 2;
				} else {
					j = 3 * j + 1;
				}
			}
			if (k > max) {
				max = k;
				ans = i;
			}
		}
		Euler.printAnswer(14, ans);
	}

	static void p015() {
		int n = 21;
		long[][] M = new long[n][n];
		for (int i = 0; i < n; i++) {
			M[i][0] = M[0][i] = 1;
		}
		for (int i = 1; i < n; i++) {
			for (int j = 1; j < n; j++) {
				M[i][j] = M[i - 1][j] + M[i][j - 1];
			}
		}
		Euler.printAnswer(15, M[n - 1][n - 1]);
	}

	static void p016() {
		BigInteger n = MathLib.big(2).pow(1000);
		Euler.printAnswer(16, MathLib.digitSum(n));
	}

	static void p017() {
		int ans = 0;
		for (int i = 1; i <= 1000; i++) {
			ans += letters(i);
		}
		Euler.printAnswer(17, ans);
	}

	static void p018() {
		Scanner in = IOLib.scanner("018.txt");
		int[][] a = new int[15][];
		int[][] f = new int[15][];
		for (int i = 0; i < 15; i++) {
			String[] ns = in.nextLine().split(" ");
			int l = ns.length;
			a[i] = new int[l];
			f[i] = new int[l];
			Arrays.fill(f[i], -1);
			for (int j = 0; j < l; j++) {
				a[i][j] = Integer.parseInt(ns[j]);
			}
		}
		IOLib.close(in);
		Euler.printAnswer(18, best(0, 0, a, f));
	}

	static void p019() {
		int y = 1900;
		int m = 1;
		int d = 1;
		int z = 1;
		int ans = 0;
		while (y != 2001) {
			if (y >= 1901 && z == 0 && d == 1) {
				ans++;
			}
			z = (z + 1) % 7;
			int q = N_DAYS[m];
			if (m == 2 && leap(y)) {
				q++;
			}
			if (++d > q) {
				d = 1;
				if (++m > 12) {
					m = 1;
					y++;
				}
			}
		}
		Euler.printAnswer(19, ans);
	}

	public static void main(String[] args) {
		p010();
		p011();
		p012();
		p013();
		p014();
		p015();
		p016();
		p017();
		p018();
		p019();
	}

	static int[] L_ONES = { 3, 3, 5, 4, 4, 3, 5, 5, 4 };
	static int[] L_TEENS = { 6, 6, 8, 8, 7, 7, 9, 8, 8 };
	static int[] L_TENS = { 3, 6, 6, 5, 5, 5, 7, 6, 6 };

	static int letters(int n) {
		if (n == 0) {
			return 0;
		}
		if (n < 10) {
			return L_ONES[n - 1];
		}
		if (n == 10) {
			return L_TENS[0];
		}
		if (n < 20) {
			return L_TEENS[n - 11];
		}
		if (n < 100) {
			return L_TENS[n / 10 - 1] + letters(n % 10);
		}
		if (n < 1000) {
			if (n % 100 == 0) {
				return letters(n / 100) + 7;
			}
			return letters(n / 100) + 10 + letters(n % 100);
		}
		return 11;
	}

	static int best(int i, int j, int[][] a, int[][] f) {
		if (f[i][j] >= 0) {
			return f[i][j];
		}
		if (i + 1 == a.length) {
			return f[i][j] = a[i][j];
		}
		return f[i][j] = a[i][j] + Math.max(best(i + 1, j, a, f), best(i + 1, j + 1, a, f));
	}

	static int[] N_DAYS = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

	static boolean leap(int n) {
		return n % 400 == 0 || (n % 100 > 0 && n % 4 == 0);
	}
}
