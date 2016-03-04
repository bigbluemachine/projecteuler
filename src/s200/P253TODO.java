package s200;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.HashMap;

public class P253TODO {
	static BigInteger big(long n) {
		return BigInteger.valueOf(n);
	}

	static int W, H;
	static int[] L;
	static int count;

	static boolean isSet(long x, int i) {
		return (x & (1L << i)) > 0;
	}

	static long set(long x, int i) {
		return x | (1L << i);
	}

	static long flip(long x, int i, int n) {
		return x ^ (((1L << n) - 1) << i);
	}

	static class K {
		int[] M;
		int e1, e2;

		public K() {
			M = new int[W];
			e1 = 0;
			e2 = 0;
		}

		public void add(int n) {
			M[n]++;
		}

		public long encode() {
			long x = ~(0L);
			int ptr = 0;

			int e1 = Math.max(this.e1, this.e2);
			int e2 = Math.min(this.e1, this.e2);

			x = flip(x, W - e2, e2);
			x = flip(x, ptr, e1);
			ptr += e1 + 1;

			for (int i = 1; i < W; i++) {
				int m = M[i];
				while (m-- > 0) {
					x = flip(x, ptr, i);
					ptr += i + 1;
				}
			}

			return x;
		}
	}

	static void getRegions(long x) {
		count = 0;
		int last = -1;
		for (int i = 0; i < W; i++) {
			if (isSet(x, i)) {
				int n = i - last - 1;
				if (n > 0)
					L[count++] = n;
				last = i;
			}
		}
		int n = W - last - 1;
		if (n > 0)
			L[count++] = n;
	}

	static K decode(long x) {
		K s = new K();

		getRegions(x);
		int first = 0, last = count - 1;
		if (!isSet(x, 0)) {
			s.e1 = L[0];
			first++;
		}
		if (!isSet(x, W - 1)) {
			s.e2 = L[count - 1];
			last--;
		}
		for (int i = first; i <= last; i++)
			s.add(L[i]);

		return s;
	}

	static HashMap<Long, BigInteger[]> T = new HashMap<Long, BigInteger[]>();

	static void f(long x) {
		if (T.containsKey(x))
			return;

		getRegions(x);
		int k = count;

		BigInteger[] M = new BigInteger[W];
		for (int i = 0; i < W; i++)
			M[i] = big(0);

		for (int i = 0; i < W; i++)
			if (!isSet(x, i)) {
				long y = decode(set(x, i)).encode();
				f(y);

				BigInteger[] N = T.get(y);
				for (int j = 0; j < k; j++)
					M[k] = M[k].add(N[j]);
				for (int j = k; j < W; j++)
					M[j] = M[j].add(N[j]);
			}

		T.put(x, M);
	}

	public static void main(String[] args) {
		// TODO NOT ENOUGH SPACE for W = 40!
		W = 40;
		L = new int[W];

		BigInteger[] M = new BigInteger[W];
		for (int i = 0; i < W; i++)
			M[i] = big(0);
		M[1] = big(1);
		T.put(-1L, M);

		BigInteger[] A = new BigInteger[W];
		for (int i = 0; i < W; i++)
			A[i] = big(0);

		for (int i = 0; i < W; i++) {
			long x = decode(1L << i).encode();
			f(x);
			BigInteger[] N = T.get(x);
			for (int j = 0; j < W; j++)
				A[j] = A[j].add(N[j]);
		}

		BigInteger num = big(0);
		BigInteger denom = big(0);

		for (int i = 0; i < W; i++) {
			num = num.add(A[i].multiply(big(i)));
			denom = denom.add(A[i]);
		}

		System.out.printf("%s/%s\n", num, denom);

		BigDecimal n = new BigDecimal(num);
		BigDecimal d = new BigDecimal(denom);
		BigDecimal ans = n.divide(d, MathContext.DECIMAL128);

		System.out.println(ans);
		System.out.println(T.size());
	}
}
