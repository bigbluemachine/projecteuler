package s200;

import java.math.BigInteger;
import java.util.List;
import java.util.TreeSet;

import core.NTLib;

public class P200 {
	static BigInteger big(long n) {
		return BigInteger.valueOf(n);
	}

	static boolean primeProof(long n) {
		if (n % 2 == 0 || n % 5 == 0) {
			int d = (int) (n % 10);
			for (int x : new int[] { 1, 3, 7, 9 }) {
				if (NTLib.MillerRabin(n - d + x, 10)) {
					return false;
				}
			}
		} else {
			char[] c = Long.toString(n).toCharArray();
			for (int i = c.length - 1; i >= 0; i--) {
				int d = (int) (c[i] - '0');
				for (int x = 0; x <= 9; x++) {
					if (x == 0 && i == 0) {
						continue;
					}
					if (x != d) {
						char[] cc = c.clone();
						cc[i] = (char) ('0' + x);
						long m = Long.parseLong(new String(cc));
						if (m % 2 == 0 || m % 3 == 0 || m % 5 == 0 || m % 7 == 0) {
							continue;
						}
						if (NTLib.MillerRabin(m, 10)) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}

	public static void main(String[] args) {
		long limit = 1000000000000000000L;
		List<Integer> ps = NTLib.primeList(1000000);
		TreeSet<Long> S = new TreeSet<Long>();
		for (int i = 1; i < ps.size(); i++) {
			for (int j = 0; j < i; j++) {
				int p = ps.get(i);
				int q = ps.get(j);
				long pq = (long) p * q;
				if (pq > limit / pq) {
					break;
				}
				long p2q2 = pq * pq;
				if (p2q2 > limit / Math.max(p, q)) {
					break;
				}
				long x = p2q2 * p;
				long y = p2q2 * q;
				if (Long.toString(x).contains("200")) {
					S.add(x);
				}
				if (Long.toString(y).contains("200")) {
					S.add(y);
				}
			}
		}

		int k = 0;
		for (long s : S) {
			if (primeProof(s)) {
				k++;
				if (k == 200) {
					System.out.println(s);
					break;
				}
			}
		}
	}
}
