package s000;

import core.MathLib;
import core.NTLib;

public class P041 {
	public static void main(String[] args) {
		int ans = 0;
		for (int n = 2; n <= 7; n++) {
			int[] a = new int[n];
			for (int i = 0; i < n; i++) {
				a[i] = i + 1;
			}
			int max = MathLib.fac32(n);
			for (int i = 0; i < max; i++) {
				int[] p = perm(a, i, n);
				int x = 0;
				for (int j = 0; j < n; j++) {
					x = 10 * x + p[j];
				}
				if (isPrime(x)) {
					ans = Math.max(ans, x);
				}
			}
		}
		System.out.println(ans);
	}

	static int P_MAX = 1000000;
	static boolean[] P = NTLib.simpleSieve(P_MAX);

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

	static boolean isPrime(long n) {
		return n < P_MAX ? P[(int) n] : NTLib.MillerRabin(n, 5);
	}
}
