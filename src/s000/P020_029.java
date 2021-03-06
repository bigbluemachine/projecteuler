package s000;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;

import core.IOLib;
import core.MathLib;
import core.NTLib;
import core.Perm;

public class P020_029 {
	static void p020() {
		BigInteger n = MathLib.fac(100);
		System.out.println(MathLib.digitSum(n));
	}

	static void p021() {
		int[] s = NTLib.divisor(40000, true);
		int ans = 0;
		for (int i = 2; i < 10000; i++) {
			int j = s[i];
			if (i != j && s[j] == i) {
				ans += i;
			}
		}
		System.out.println(ans);
	}

	static void p022() {
		Scanner in = IOLib.scanner("data/022.txt");
		String line = in.nextLine();
		IOLib.close(in);
		ArrayList<String> ss = new ArrayList<String>();
		for (String s : line.split(",")) {
			ss.add(s.substring(1, s.length() - 1));
		}
		Collections.sort(ss);
		int i = 1;
		long ans = 0;
		for (String s : ss) {
			int k = 0;
			for (char c : s.toCharArray()) {
				k += (c - 'A' + 1);
			}
			ans += i * k;
			i++;
		}
		System.out.println(ans);
	}

	static void p023() {
		int[] s = NTLib.divisor(28124, true);
		ArrayList<Integer> p = new ArrayList<Integer>();
		for (int i = 1; i < 28124; i++) {
			if (s[i] > i) {
				p.add(i);
			}
		}
		boolean[] q = new boolean[28124];
		for (int i = 0; i < p.size(); i++) {
			for (int j = i; j < p.size(); j++) {
				int sum = p.get(i) + p.get(j);
				if (sum >= 28124) {
					break;
				}
				q[sum] = true;
			}
		}
		long ans = 0;
		for (int i = 0; i < 28124; i++) {
			if (!q[i]) {
				ans += i;
			}
		}
		System.out.println(ans);
	}

	static void p024() {
		Perm p = new Perm(10);
		int[] v = null;
		for (int i = 0; i < 1000000; i++) {
			v = p.next();
		}
		String ans = "";
		for (int i : v) {
			ans += i;
		}
		System.out.println(ans);
	}

	static void p025() {
		BigInteger a = BigInteger.ZERO;
		BigInteger b = BigInteger.ONE;
		int ans = 1;
		while (b.toString().length() < 1000) {
			ans++;
			BigInteger c = a.add(b);
			a = b;
			b = c;
		}
		System.out.println(ans);
	}

	static void p026() {
		int max = 0;
		int ans = -1;
		for (int i = 2; i < 1000; i++) {
			int j = i;
			while (j % 2 == 0) {
				j /= 2;
			}
			while (j % 5 == 0) {
				j /= 5;
			}
			if (j == 1) {
				continue;
			}
			j = 1;
			int[] v = new int[i];
			for (int d = 10;;) {
				int m = d % i;
				if (v[m] > 0) {
					break;
				}
				v[m] = j++;
				d = m * 10;
			}
			j--;
			if (j > max) {
				max = j;
				ans = i;
			}
		}
		System.out.println(ans);
	}

	static void p027() {
		boolean[] p = NTLib.simpleSieve(1000000);
		int max = 0;
		int ans = -1;
		for (int a = -999; a < 1000; a++) {
			for (int b = -999; b < 1000; b++) {
				int k = 0;
				for (int n = 0;; n++) {
					int f = n * n + a * n + b;
					if (f < 0 || !p[f]) {
						break;
					}
					k++;
				}
				if (k > max) {
					max = k;
					ans = a * b;
				}
			}
		}
		System.out.println(ans);
	}

	static void p028() {
		long ans = 1;
		for (int k = 3; k <= 1001; k += 2) {
			ans += 4 * k * k - 6 * (k - 1);
		}
		System.out.println(ans);
	}

	static void p029() {
		HashSet<BigInteger> S = new HashSet<BigInteger>();
		for (int a = 2; a <= 100; a++) {
			for (int b = 2; b <= 100; b++) {
				S.add(BigInteger.valueOf(a).pow(b));
			}
		}
		System.out.println(S.size());
	}

	public static void main(String[] args) {
		p020();
		p021();
		p022();
		p023();
		p024();
		p025();
		p026();
		p027();
		p028();
		p029();
	}
}
