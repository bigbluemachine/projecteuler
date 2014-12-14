package core;

import java.math.BigInteger;

public class MathLib {
	// Create BigInteger.
	public static BigInteger big(long n) {
		return BigInteger.valueOf(n);
	}

	// Tests BigInteger a < b.
	public static boolean lt(BigInteger a, BigInteger b) {
		return a.compareTo(b) < 0;
	}

	// Tests BigInteger a > b.
	public static boolean gt(BigInteger a, BigInteger b) {
		return a.compareTo(b) > 0;
	}

	// 32-bit exponent. b != 0, e >= 0.
	public static int pow32(long b, int e) {
		int ans = 1;
		while (e-- > 0) {
			ans *= b;
		}
		return ans;
	}

	// 64-bit exponent. b != 0, e >= 0.
	public static long pow64(long b, int e) {
		long ans = 1;
		while (e-- > 0) {
			ans *= b;
		}
		return ans;
	}

	// 32-bit factorial. n >= 0.
	public static int fac32(int n) {
		int ans = 1;
		for (; n >= 2; n--) {
			ans *= n;
		}
		return ans;
	}

	// 64-bit factorial. n >= 0.
	public static long fac64(int n) {
		long ans = 1;
		for (; n >= 2; n--) {
			ans *= n;
		}
		return ans;
	}

	// BigInteger factorial. n >= 0.
	public static BigInteger fac(int n) {
		BigInteger ans = BigInteger.ONE;
		for (; n >= 2; n--) {
			ans = ans.multiply(big(n));
		}
		return ans;
	}

	// 32-bit GCD. a >= 0, b >= 0, but not both 0.
	public static int gcd32(int a, int b) {
		while (a != 0) {
			a ^= b;
			b ^= a;
			a = (a ^ b) % b;
		}
		return b;
	}

	// 64-bit GCD. a >= 0, b >= 0, but not both 0.
	public static long gcd64(long a, long b) {
		while (a != 0) {
			a ^= b;
			b ^= a;
			a = (a ^ b) % b;
		}
		return b;
	}

	// b^e mod m. b >= 1, e >= 0, m > 0.
	// Possibly overflows for large b or m.
	public static long modExp(long b, long e, long m) {
		long ans = 1;
		for (long i = 1; i <= e; i <<= 1, b = (b * b) % m) {
			if ((e & i) > 0) {
				ans = (ans * b) % m;
			}
		}
		return ans;
	}

	// 32-bit triangular number. n >= 1.
	public static int tri32(int n) {
		return (n * (n + 1)) >> 1;
	}

	// 64-bit triangular number. n >= 1.
	public static long tri64(long n) {
		return (n * (n + 1)) >> 1;
	}

	// Digit sum. n >= 0.
	public static int digitSum(long n) {
		int ans = 0;
		while (n > 0) {
			ans += n % 10;
			n /= 10;
		}
		return ans;
	}

	// Digit sum. n >= 0.
	public static int digitSum(BigInteger n) {
		int ans = 0;
		for (char c : n.toString().toCharArray()) {
			ans += (c - '0');
		}
		return ans;
	}
}
