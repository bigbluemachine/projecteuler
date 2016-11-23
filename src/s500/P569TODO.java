package s500;

import java.math.BigInteger;
import java.util.LinkedList;

import core.PList;

public class P569TODO {
	static class Pt {
		long x, y;

		Pt(long a, long b) {
			x = a;
			y = b;
		}
	}

	static BigInteger big(long n) {
		return BigInteger.valueOf(n);
	}

	static boolean ccw(Pt p1, Pt p2, Pt p3) {
		long a = p2.x - p1.x;
		long b = p3.y - p1.y;
		long c = p2.y - p1.y;
		long d = p3.x - p1.x;
		return big(a).multiply(big(b)).compareTo(big(c).multiply(big(d))) > 0;
	}

	static LinkedList<Integer> hull(Pt[] P) {
		LinkedList<Integer> H = new LinkedList<Integer>();
		H.add(0);
		H.add(1);
		for (int i = 2; i < P.length; i++) {
			for (;;) {
				int b = H.removeLast();
				int a = H.getLast();
				H.add(b);
				Pt p = P[a];
				Pt q = P[b];
				if (!ccw(p, q, P[i])) {
					break;
				}
				H.removeLast();
			}
			H.add(i);
		}
		return H;
	}

	static long count(Pt[] P, int from, int to) {
		long ans = 0;
		for (int i = from; i < to; i++) {
			Pt q = P[i + 1];
			for (int j = i + 1; j <= to; j++) {
				if (ccw(P[i], q, P[j])) {
					ans++;
					q = P[j];
				}
			}
		}
		return ans;
	}

	public static void main(String[] args) {
		// TODO: SLOW! Try using the "merge" method I had before...
		int n = 100;

		// 100 : 227
		// 1000 : 4084
		// 5000 : 24229
		// 10000 : 52462
		// 2500000 : 21025060

		PList primes = new PList();
		while (primes.size() < 2 * n - 1) {
			primes.expand();
		}

		Pt[] P = new Pt[n];
		P[0] = new Pt(0, 0);
		for (int i = 2; i <= n; i++) {
			int j = 2 * (i - 1);
			int a = primes.get(j - 1);
			int b = primes.get(j);
			P[i - 1] = new Pt(P[i - 2].x + (b + a) / 2, P[i - 2].y + (b - a) / 2);
		}

		long ans = 0;
		LinkedList<Integer> H = hull(P);
		Integer[] I = H.toArray(new Integer[0]);
		for (int i = 1; i < I.length; i++) {
			if (I[i - 1] + 1 == I[i]) {
				ans++;
			} else {
				ans += I[i] - I[i - 1];
				ans += count(P, I[i - 1], I[i]);
			}
		}
		System.out.println(ans);
	}
}
