package s300;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.TreeSet;

import core.MathLib;
import core.NTLib;

public class P362TODO {
	static HashMap<Long, Long> M = new HashMap<>();
	static Integer[] P;
	static TreeSet<Integer> S;
	static HashMap<Integer, Integer> R;
	static {
		P = NTLib.primeList(10000000).toArray(new Integer[0]);
		S = new TreeSet<>();
		R = new HashMap<>();
		int i = 0;
		for (int p : P) {
			S.add(p);
			R.put(p, i);
			i++;
		}
	}

	static long encode(int[] a, int v) {
		long x = v;
		for (int i = 0; i < a.length; i++) {
			x = 34 * x + a[i];
		}
		return x;
	}

	static int[] trim(int[] a) {
		int toTrim = 0;
		for (int i = a.length - 1; i >= 0 && a[i] == 0; i--) {
			toTrim++;
		}
		if (toTrim == 0) {
			return a;
		}
		int[] b = new int[a.length - toTrim];
		System.arraycopy(a, 0, b, 0, b.length);
		return b;
	}

	static int leadingMask(int[] a) {
		return a.length == 0 ? 0 : 1 << (a.length - 1);
	}

	static int bitCount(int v) {
		int ans = 0;
		while (v > 0) {
			ans++;
			v >>= 1;
		}
		return ans;
	}

	static long f(int[] a, int v) {
		long key = encode(a, v);
		if (M.containsKey(key)) {
			return M.get(key);
		}

		long ans = 0;
		if (a.length == 0) {
			ans = 1;
		} else if (v < leadingMask(a)) {
			ans = 0;
		} else if (v == 1) {
			ans = 1;
		} else {
			// Max times to subtract
			int t = Integer.MAX_VALUE;
			for (int i = 0; i < a.length; i++) {
				if ((v & (1 << i)) > 0) {
					t = Math.min(t, a[i]);
				}
			}

			// Next state
			int w = (v & (v + 1)) == 0 ? (v + 1) >> 2 : v + 1;
			int l = bitCount(w);

			ans += f(a, w);
			for (int i = 1; i <= t; i++) {
				// Subtract mask
				int[] b = a.clone();
				for (int j = 0, temp = v; temp > 0; temp >>= 1, j++) {
					if ((temp & 1) > 0) {
						b[j] -= i;
					}
				}

				int[] c = trim(b);
				ans += f(c, l > c.length ? leadingMask(c) : w);
			}
		}

		M.put(key, ans);
		return ans;
	}

	static long f(int[] a) {
		return f(a, leadingMask(a));
	}

	static void gen(long max, int[] v, int iv, LinkedList<int[]> S) {
		long test = 1;
		for (int i = 0; i < v.length; i++) {
			test *= MathLib.pow64(P[i], v[i]);
			if (test > max) {
				return;
			}
		}

		S.add(v);
		for (int i = iv; i < v.length; i++) {
			int[] w = v.clone();
			w[i]++;
			gen(max, w, i, S);
		}
	}

	// Generate factorization signatures for an upper bound
	static LinkedList<int[]> gen(long max) {
		LinkedList<int[]> S = new LinkedList<>();
		long test = 2;
		for (int len = 1; test <= max; len++) {
			int[] v = new int[len];
			Arrays.fill(v, 1);
			gen(max, v, 0, S);
			test *= P[len];
		}
		return S;
	}

	static long root(long n, int t) {
		long x = ((long) Math.pow(n, 1.0 / t) + 1);
		while (MathLib.pow64(x, t) > n) {
			x--;
		}
		return x;
	}

	// Count primes p, a <= p <= b
	static long countPrimes(int ip, long b) {
		if (P[ip] > b) {
			return 0;
		}

		if (b <= P[P.length - 1]) {
			int u = S.floor((int) b);
			return R.get(u) - ip + 1;
		} else {
			long ans = 0;
			int interval = 5000000;
			long start = P[ip];
			for (; start <= b;) {
				boolean[] qq = primeRange(start, Math.min(start + interval, b));
				for (int i = 0; i < qq.length; i++) {
					if (qq[i]) {
						ans++;
					}
				}
				start += interval + 1;
			}
			return ans;
		}
	}

	static long count(long max, long acc, int[] v, int iv, int ip) {
		if (iv == v.length - 1) {
			long r = root(max / acc, v[iv]);
			return countPrimes(ip, r);
		}

		long ans = 0;
		for (int ip_ = ip;; ip_++) {
			long test = acc;
			boolean pass = true;
			for (int i = 0; i < v[iv]; i++) {
				test *= P[ip_];
				if (test * P[ip_] > max) {
					pass = false;
					break;
				}
			}

			if (pass) {
				ans += count(max, test, v, iv + 1, ip_ + 1);
			} else {
				break;
			}
		}

		return ans;
	}

	// Count numbers with factorization signature
	static long count(long max, int[] v) {
		return count(max, 1, v, 0, 0);
	}

	// Hack
	static boolean[] primeRange(long lo, long hi) {
		int count = (int) (hi - lo + 1);
		int s = (int) Math.sqrt(hi);

		boolean[] v = new boolean[count];
		boolean[] p = NTLib.simpleSieve(s + 1);

		Arrays.fill(v, true);
		for (int j = lo == 2 ? 2 : (int) (lo % 2); j < count; j += 2) {
			v[j] = false;
		}

		for (int i = 3; i <= s; i += 2) {
			if (p[i]) {
				int j;
				if (i >= lo) {
					j = (int) (i + i - lo);
				} else {
					int m = (int) (lo % i);
					j = m == 0 ? 0 : i - m;
				}
				for (; j < count; j += i) {
					v[j] = false;
				}
			}
		}

		return v;
	}

	public static void main(String[] args) {
		// TODO 20 minutes!!
		long max = 10000000000L;

		BigInteger ans = BigInteger.valueOf(0);
		LinkedList<int[]> S = gen(max);
		for (int[] s : S) {
			long p = count(max, s);
			long q = f(s);
			ans = ans.add(BigInteger.valueOf(p).multiply(BigInteger.valueOf(q)));
		}
		System.out.println(ans);
	}
}
