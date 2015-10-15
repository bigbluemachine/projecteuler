package core;

import java.util.Arrays;

public class ArrayLib {
	public static int[] repeat(int n, int t) {
		int[] a = new int[t];
		Arrays.fill(a, n);
		return a;
	}

	public static int[] concat(int[] a, int[] b) {
		int[] c = new int[a.length + b.length];
		System.arraycopy(a, 0, c, 0, a.length);
		System.arraycopy(b, 0, c, a.length, b.length);
		return c;
	}

	// Does not include to-index!
	public static int[] slice(int[] a, int from, int to) {
		int[] b = new int[to - from];
		System.arraycopy(a, from, b, 0, b.length);
		return b;
	}

	// Maps index array to an array.
	public static int[] map(int[] a, int[] is) {
		int[] b = new int[is.length];
		for (int i = 0; i < is.length; i++) {
			b[i] = a[is[i]];
		}
		return b;
	}
}
