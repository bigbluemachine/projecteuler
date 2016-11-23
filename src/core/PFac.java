package core;

import java.math.BigInteger;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

public class PFac {
	public int twos;
	public TreeMap<Long, Integer> M;

	public PFac() {
		twos = 0;
		M = new TreeMap<Long, Integer>();
	}

	public PFac(int... a) {
		twos = 0;
		M = new TreeMap<Long, Integer>();
		multiply(a);
	}

	private PFac(int t, TreeMap<Long, Integer> m) {
		twos = t;
		M = m;
	}

	public static PFac make(long n, List<Integer> ps) {
		PFac pf = new PFac();

		while ((n & 1) == 0) {
			pf.twos++;
			n >>= 1;
		}

		for (int p : ps) {
			if (p > n) {
				break;
			}
			int e = 0;
			while (n % p == 0) {
				n /= p;
				e++;
			}
			if (e > 0) {
				pf.multiply(p, e);
			}
		}

		if (n > 1) {
			pf.multiply((int) n, 1);
		}

		return pf;
	}

	public void multiply(int... a) {
		for (int i = 0; i < a.length; i += 2) {
			if (a[i] == 2) {
				twos += a[i + 1];
			} else {
				long k = a[i];
				M.put(k, M.containsKey(k) ? M.get(k) + a[i + 1] : a[i + 1]);
			}
		}
	}

	public PFac multiply(PFac o) {
		TreeMap<Long, Integer> N = new TreeMap<Long, Integer>();
		N.putAll(M);
		for (long q : o.M.keySet()) {
			N.put(q, N.containsKey(q) ? N.get(q) + o.M.get(q) : o.M.get(q));
		}
		return new PFac(twos + o.twos, N);
	}

	public long divisorCount() {
		long ans = twos + 1;
		for (long p : M.keySet()) {
			ans *= (M.get(p) + 1);
		}
		return ans;
	}

	public int intValue() {
		int ans = 1;
		for (long p : M.keySet()) {
			ans *= MathLib.pow32(p, M.get(p));
		}
		return ans << twos;
	}

	public long longValue() {
		long ans = 1;
		for (long p : M.keySet()) {
			ans *= MathLib.pow64(p, M.get(p));
		}
		return ans << twos;
	}

	public BigInteger bigValue() {
		BigInteger ans = BigInteger.ONE;
		for (long p : M.keySet()) {
			ans = ans.multiply(BigInteger.valueOf(p).pow(M.get(p)));
		}
		return ans.shiftLeft(twos);
	}

	public BigInteger totient() {
		BigInteger ans = bigValue();
		if (twos > 0) {
			ans = ans.divide(BigInteger.valueOf(2));
		}
		for (long p : M.keySet()) {
			ans = ans.multiply(BigInteger.valueOf(p - 1));
			ans = ans.divide(BigInteger.valueOf(p));
		}
		return ans;
	}

	public TreeSet<Long> divisors() {
		TreeSet<Long> S = new TreeSet<Long>();
		S.add(1L);
		for (int i = 1; i <= twos; i++) {
			S.add(1L << i);
		}
		for (long p : M.keySet()) {
			for (int i = 0; i < M.get(p); i++) {
				TreeSet<Long> T = new TreeSet<Long>();
				for (long s : S) {
					T.add(s * p);
				}
				S.addAll(T);
			}
		}
		return S;
	}

	public String toString() {
		String ans = "2^" + twos;
		for (long p : M.keySet()) {
			ans += "*" + p + "^" + M.get(p);
		}
		return ans;
	}
}
