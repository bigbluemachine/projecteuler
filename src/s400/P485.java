package s400;

import java.util.Arrays;
import java.util.List;

import core.NTLib;

public class P485 {
	static short[] divisorCount(int n, List<Integer> ps) {
		short[] d = new short[n + 1];
		Arrays.fill(d, (short) 1);

		int[] v = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			v[i] = i;
		}

		for (int p : ps) {
			for (int i = p; i <= n; i += p) {
				int e = 0;
				while (v[i] % p == 0) {
					v[i] /= p;
					e++;
				}
				d[i] *= e + 1;
			}
		}

		for (int i = 1; i <= n; i++) {
			if (v[i] != 1) {
				d[i] *= 2;
			}
		}

		return d;
	}

	static short max(short[] d) {
		short ans = 0;
		for (short s : d) {
			if (s > ans) {
				ans = s;
			}
		}
		return ans;
	}

	static long maxSum(short[] d, int a, int b) {
		short[] Q = new short[b];
		for (int i = 0; i < b; i++) {
			Q[i] = d[i];
		}
		long ans = 0;
		int q = 0;
		short m = max(Q);
		for (int i = b; i <= a; i++) {
			short v = Q[q];
			Q[q] = d[i];
			q = (q + 1) % b;
			if (d[i] > m) {
				m = d[i];
			} else if (v == m) {
				m = max(Q);
			}
			ans += m;
		}
		return ans;
	}

	public static void main(String[] args) {
		int a = 100000000;
		int b = 100000;

		List<Integer> P = NTLib.primeList((int) Math.sqrt(a) + 1);
		short[] d = divisorCount(a, P);
		System.out.println(maxSum(d, a, b));
	}
}
