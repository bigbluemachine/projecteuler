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

	public static int[] slice(int[] a, int from, int toExc) {
		int[] b = new int[toExc - from];
		System.arraycopy(a, from, b, 0, b.length);
		return b;
	}

	public static int[] map(int[] a, int[] indices) {
		int[] b = new int[indices.length];
		for (int i = 0; i < indices.length; i++) {
			b[i] = a[indices[i]];
		}
		return b;
	}
}
