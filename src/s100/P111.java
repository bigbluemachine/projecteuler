package s100;

import java.util.Arrays;
import java.util.TreeSet;

import core.MathLib;
import core.NTLib;
import core.Selector;

public class P111 {
	// Searches for n-digit numbers with d repeated k times
	static TreeSet<Long> gen(int n, int d, int k) {
		TreeSet<Long> ans = new TreeSet<Long>();
		Selector s = new Selector(n, n - k);
		while (s.hasNext()) {
			int[] z = new int[n];
			Arrays.fill(z, d);
			long v = s.next();
			for (int i = 0; i < n; i++) {
				if ((v & (1L << i)) > 0) {
					z[i] = -1;
				}
			}
			ans.addAll(gen2(z, d, n - k));
		}
		return ans;
	}

	static TreeSet<Long> gen2(int[] z, int d, int t) {
		TreeSet<Long> ans = new TreeSet<Long>();
		int p = MathLib.pow32(10, t);
		Z: for (int i = 0; i < p; i++) {
			int[] y = z.clone();
			int j = i;
			for (int k = j; k > 0; k /= 10) {
				if (k % 10 == d) {
					continue Z;
				}
			}
			for (int k = 0; k < y.length; k++) {
				if (y[k] == -1) {
					y[k] = j % 10;
					j /= 10;
				}
			}
			if (y[0] != 0) {
				long n = 0;
				for (int k = 0; k < y.length; k++) {
					n = 10 * n + y[k];
				}
				if (n % 2 == 1 && NTLib.MillerRabin(n, 10)) {
					ans.add(n);
				}
			}
		}
		return ans;
	}

	public static void main(String[] args) {
		int D = 10;
		long ans = 0;
		Z: for (int d = 0; d <= 9; d++) {
			for (int m = D - 1; m >= 1; m--) {
				TreeSet<Long> S = gen(D, d, m);
				if (!S.isEmpty()) {
					long sum = 0;
					for (long s : S) {
						sum += s;
					}
					ans += sum;
					continue Z;
				}
			}
		}
		System.out.println(ans);
	}
}
