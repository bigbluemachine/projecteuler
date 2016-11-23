package s300;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

import core.EEA;
import core.NTLib;

public class P365 {
	static BigInteger big(long n) {
		return BigInteger.valueOf(n);
	}

	static ArrayList<Integer> expansion(long n, int b) {
		ArrayList<Integer> ans = new ArrayList<Integer>();
		while (n > 0) {
			ans.add((int) (n % b));
			n /= b;
		}
		return ans;
	}

	static int c(int n, int k, int p) {
		BigInteger num = big(1);
		BigInteger den = big(1);
		for (int i = 0; i < k; i++) {
			num = num.multiply(big(n - i));
			den = den.multiply(big(i + 1));
		}
		return num.divide(den).mod(big(p)).intValue();
	}

	static int lucas(int p) {
		ArrayList<Integer> x = expansion(1000000000000000000L, p);
		ArrayList<Integer> y = expansion(1000000000L, p);
		int l = Math.max(x.size(), y.size());
		long ans = 1;
		for (int i = 0; i < l; i++) {
			int m = i < x.size() ? x.get(i) : 0;
			int n = i < y.size() ? y.get(i) : 0;
			if (m < n) {
				return 0;
			}
			if (n == 0) {
				continue;
			}
			ans = (ans * c(m, Math.min(n, m - n), p)) % p;
		}
		return (int) ans;
	}

	public static void main(String[] args) {
		TreeMap<Integer, Integer> M = new TreeMap<Integer, Integer>();
		boolean[] p = NTLib.simpleSieve(5000);
		for (int i = 1001; i < 5000; i += 2) {
			if (p[i]) {
				M.put(i, lucas(i));
			}
		}

		Integer[] ps = M.keySet().toArray(new Integer[0]);
		HashMap<Integer, Long> R = new HashMap<Integer, Long>();
		for (int i = 0; i < ps.length; i++) {
			int a = ps[i];
			for (int j = i + 1; j < ps.length; j++) {
				int b = ps[j];
				R.put(a * b, EEA.crt(a, M.get(a), b, M.get(b)).longValue());
			}
		}

		long ans = 0;
		for (int i = 0; i < ps.length; i++) {
			int a = ps[i];
			for (int j = i + 1; j < ps.length; j++) {
				int b = ps[j];
				int x = a * b;
				for (int k = j + 1; k < ps.length; k++) {
					int c = ps[k];
					long r = R.get(x);
					ans += EEA.crt(x, r, c, M.get(c)).longValue();
				}
			}
		}
		System.out.println(ans);
	}
}
