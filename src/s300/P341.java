package s300;

import java.util.ArrayList;

import core.MathLib;

public class P341 {
	static long T(long n) {
		return (n * (n + 1)) / 2;
	}

	static long G(long n, ArrayList<Integer> G, long[] A, long[] B) {
		if (n < G.size()) {
			return G.get((int) n);
		}

		int lo = 3;
		int hi = A.length - 1;
		while (hi - lo > 1) {
			int mid = (lo + hi) / 2;
			if (A[mid] > n) {
				hi = mid;
			} else {
				lo = mid;
			}
		}
		if (n == A[lo]) {
			return B[lo];
		}

		return B[lo] + (n - A[lo]) / lo;
	}

	public static void main(String[] args) {
		ArrayList<Integer> G = new ArrayList<Integer>();
		G.add(0);
		G.add(1);
		G.add(2);
		G.add(2);
		long s = 11;
		for (int i = 3; s < 1000000000000000000L; i++) { // 1000000000000000000L
			int t = G.get(i);
			long temp = T(G.size() - 1 + t) - T(G.size() - 1);
			s += temp * i;
			for (int j = 0; j < t; j++) {
				G.add(i);
			}
		}

		long[] A = new long[G.size()];
		long[] B = new long[G.size()];
		long a = 6;
		long b = 4;
		for (int c = 3; c < G.size(); c++) {
			A[c] = a;
			B[c] = b;
			long g = G.get(c);
			a += c * g;
			b += g;
		}

		long ans = 0;
		for (int i = 1; i < 1000000; i++) {
			long n = MathLib.pow64(i, 3);
			ans += G(n, G, A, B);
		}
		System.out.println(ans);
	}
}
