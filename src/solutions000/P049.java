package s000;

import java.util.TreeSet;

import core.MathLib;
import core.NTLib;

public class P049 {
	public static void main(String[] args) {
		TreeSet<String> S = new TreeSet<String>();
		boolean[] p = NTLib.simpleSieve(10000);
		for (int i = 1001; i < 10000; i += 2) {
			if (!p[i]) {
				continue;
			}
			int[] a = toArray(i);
			for (int j = 1; j < 24; j++) {
				int[] b = perm(a, j, 4);
				int k = b[0] + 10 * b[1] + 100 * b[2] + 1000 * b[3];
				if (k > i && p[k]) {
					int l = k + k - i;
					if (l < 10000 && p[l] && isPerm(toArray(l), a)) {
						S.add(i + "" + k + "" + l);
					}
				}
			}
		}
		System.out.println(S);
	}

	static int[] toArray(int n) {
		return new int[] { n % 10, n / 10 % 10, n / 100 % 10, n / 1000 % 10 };
	}

	static int[] perm(int[] a, int i, int length) {
		if (i == 0) {
			return a;
		}
		int k = MathLib.fac32(length - 1);
		int index = i / k;
		int[] b = new int[length - 1];
		System.arraycopy(a, 0, b, 0, index);
		System.arraycopy(a, index + 1, b, index, length - index - 1);
		a[0] = a[index];
		b = perm(b, i - index * k, length - 1);
		System.arraycopy(b, 0, a, 1, length - 1);
		return a;
	}

	static boolean isPerm(int[] a, int[] b) {
		int[] v = new int[10];
		for (int i : a) {
			v[i]++;
		}
		for (int i : b) {
			v[i]--;
		}
		for (int i : v) {
			if (i > 0) {
				return false;
			}
		}
		return true;
	}
}
