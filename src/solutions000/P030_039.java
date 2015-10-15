package s000;

import java.util.HashSet;
import java.util.TreeSet;

import core.Euler;
import core.MathLib;
import core.NTLib;

public class P030_039 {
	static void p030() {
		int ans = 0;
		for (int i = 11; i < 1000000; i++) {
			int j = i;
			int s = 0;
			while (j > 0) {
				s += MathLib.pow32(j % 10, 5);
				j /= 10;
			}
			if (s == i) {
				ans += s;
			}
		}
		Euler.printAnswer(30, ans);
	}

	static void p031() {
		Euler.printAnswer(31, ways(200, 200));
	}

	static void p032() {
		HashSet<Integer> S = new HashSet<Integer>();
		for (int i = 1; i <= 9; i++) {
			for (int j = 1000; j < 9999; j++) {
				if (pandigital(i, j, i * j)) {
					S.add(i * j);
				}
			}
		}
		for (int i = 10; i <= 99; i++) {
			for (int j = 100; j <= 999; j++) {
				if (pandigital(i, j, i * j)) {
					S.add(i * j);
				}
			}
		}
		int ans = 0;
		for (int s : S) {
			ans += s;
		}
		Euler.printAnswer(32, ans);
	}

	static void p033() {
		int n = 1;
		int d = 1;
		for (int a = 10; a <= 99; a++) {
			for (int b = a + 1; b <= 99; b++) {
				if ((a % 10 == 0 && b % 10 == 0) || (a % 11 == 0 && b % 11 == 0)) {
					continue;
				}
				int p = a / 10, q = a % 10;
				int r = b / 10, s = b % 10;
				if ((a * s == b * p && q == r) || (a * r == b * q && p == s)) {
					n *= a;
					d *= b;
				}
			}
		}
		int g = MathLib.gcd32(n, d);
		Euler.printAnswer(32, d / g);
	}

	static void p034() {
		int[] f = new int[10];
		f[0] = 1;
		for (int i = 1; i < 10; i++) {
			f[i] = f[i - 1] * i;
		}
		int ans = 0;
		for (int i = 10; i < 3000000; i++) {
			int s = 0;
			int j = i;
			while (j > 0) {
				s += f[j % 10];
				j /= 10;
			}
			if (s == i) {
				ans += i;
			}
		}
		Euler.printAnswer(34, ans);
	}

	static void p035() {
		boolean[] p = NTLib.simpleSieve(1000000);
		int ans = 1;
		L: for (int i = 3; i < 1000000; i += 2) {
			if (!p[i]) {
				continue;
			}
			String s = "";
			s += i;
			int n = s.length();
			s += i;
			for (int j = 0; j < n; j++) {
				int r = Integer.parseInt(s.substring(j, j + n));
				if (!p[r]) {
					continue L;
				}
			}
			ans++;
		}
		Euler.printAnswer(35, ans);
	}

	static void p036() {
		long ans = 0;
		for (int i = 0; i < 1000000; i++) {
			if (palindrome(Integer.toString(i)) && palindrome(Integer.toBinaryString(i))) {
				ans += i;
			}
		}
		Euler.printAnswer(36, ans);
	}

	static void p037() {
		TreeSet<Long> l = new TreeSet<Long>();
		TreeSet<Long> r = new TreeSet<Long>();
		leftPrime(3, l);
		leftPrime(7, l);
		rightPrime(2, r);
		rightPrime(3, r);
		rightPrime(5, r);
		rightPrime(7, r);
		long ans = 0;
		for (long x : l) {
			if (x >= 10 && r.contains(x)) {
				ans += x;
			}
		}
		Euler.printAnswer(37, ans);
	}

	static void p038() {
		int ans = 0;
		for (int n = 2; n <= 9; n++) {
			int d = 9 / n;
			for (int k = MathLib.pow32(10, d); k > 0; k--) {
				String s = "";
				for (int i = 1; i <= n; i++) {
					s += k * i;
				}
				if (s.length() == 9) {
					int z = Integer.parseInt(s);
					if (pandigital(z)) {
						ans = Math.max(ans, z);
					}
				}
			}
		}
		Euler.printAnswer(38, ans);
	}

	static void p039() {
		int[] k = new int[1001];
		for (int u = 1; u < 1000; u++) {
			for (int v = u + 1; v < 1000; v += 2) {
				if (MathLib.gcd32(u, v) > 1) {
					continue;
				}
				int s = 2 * v * (v + u);
				for (int p = s; p <= 1000; p += s) {
					k[p]++;
				}
			}
		}
		int max = 0;
		int ans = -1;
		for (int i = 0; i <= 1000; i++) {
			if (k[i] > max) {
				max = k[i];
				ans = i;
			}

		}
		Euler.printAnswer(39, ans);
	}

	public static void main(String[] args) {
		p030();
		p031();
		p032();
		p033();
		p034();
		p035();
		p036();
		p037();
		p038();
		p039();
	}

	static int P_MAX = 1000000;
	static boolean[] P = NTLib.simpleSieve(P_MAX);
	static int[] D = { 1, 2, 3, 5, 7, 9 };

	static int ways(int n, int c) {
		if (n == 0 || c == 1) {
			return 1;
		}
		int next = c == 50 ? 20 : c / 2;
		int ans = ways(n, next);
		if (n >= c) {
			ans += ways(n - c, c);
		}
		return ans;
	}

	static boolean pandigital(int... a) {
		int v = 0;
		for (int i : a) {
			while (i > 0) {
				v ^= 1 << (i % 10);
				i /= 10;
			}
		}
		return v == 1022;
	}

	static boolean palindrome(String s) {
		for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
			if (s.charAt(i) != s.charAt(j)) {
				return false;
			}
		}
		return true;
	}

	static boolean isPrime(long n) {
		return n < P_MAX ? P[(int) n] : NTLib.MillerRabin(n, 5);
	}

	static void leftPrime(long n, TreeSet<Long> S) {
		if (isPrime(n)) {
			S.add(n);
			for (int d : D) {
				leftPrime(Long.parseLong(d + Long.toString(n)), S);
			}
		}
	}

	static void rightPrime(long n, TreeSet<Long> S) {
		if (isPrime(n)) {
			S.add(n);
			for (int d : D) {
				rightPrime(10 * n + d, S);
			}
		}
	}
}
