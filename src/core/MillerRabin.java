package core;

public class MillerRabin {
	// ----------------------------------------
	// Miller-Rabin test for 32-bit integers.
	// Assume input is >= 3.
	// ----------------------------------------

	public static boolean MR32Pass(long n, long k) {
		int t = Long.numberOfTrailingZeros(k - 1);
		if ((n = MathLib.modExp(n, (k - 1) >> t, k)) == 1) {
			return true;
		}
		for (int i = 1; i < t; i++, n = (n * n) % k) {
			if (n == k - 1) {
				return true;
			}
		}
		return n == k - 1;
	}

	public static boolean MR32(long n) {
		return MR32Pass(2, n) && (n <= 7 || MR32Pass(7, n)) && (n <= 61 || MR32Pass(61, n));
	}
}
