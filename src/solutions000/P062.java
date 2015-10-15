package s000;

import java.util.HashMap;

import core.MathLib;

public class P062 {
	public static void main(String[] args) {
		HashMap<Long, Integer> T = new HashMap<Long, Integer>();
		for (int i = 5; i < 100000; i++) {
			long c = MathLib.pow64(i, 3);
			long s = signature(c);
			if (T.containsKey(s)) {
				T.put(s, T.get(s) + 1);
			} else {
				T.put(s, 1);
			}
		}
		for (int i = 5; i < 100000; i++) {
			long c = MathLib.pow64(i, 3);
			long s = signature(c);
			if (T.get(s) == 5) {
				System.out.println(c);
				break;
			}
		}
	}

	static long signature(long n) {
		int[] v = new int[10];
		while (n > 0) {
			v[(int) (n % 10)]++;
			n /= 10;
		}
		long x = 0;
		for (int i = 9; i >= 0; i--) {
			x = 20 * x + v[i];
		}
		return x;
	}
}
