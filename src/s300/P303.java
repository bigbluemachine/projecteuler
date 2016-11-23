package s300;

import java.math.BigInteger;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class P303 {
	static class P {
		String s;
		int r;

		public P(String s, int r) {
			this.s = s;
			this.r = r;
		}
	}

	static boolean check(int x) {
		while (x > 0) {
			if (x % 10 > 2) {
				return false;
			}
			x /= 10;
		}
		return true;
	}

	static BigInteger search(int n) {
		PriorityQueue<P> Q = new PriorityQueue<P>(new Comparator<P>() {
			@Override
			public int compare(P a, P b) {
				String s = a.s;
				String t = b.s;
				if (s.length() != t.length()) {
					return s.length() - t.length();
				}
				return s.compareTo(t);
			}
		});
		HashMap<Integer, Integer> V = new HashMap<Integer, Integer>();

		for (int d = 1; d <= 9; d++) {
			int t = d * n;
			if (t % 10 <= 2) {
				Q.add(new P(Integer.toString(d), t / 10));
			}
		}

		while (!Q.isEmpty()) {
			P p = Q.poll();
			String s = p.s;
			int r = p.r;

			if (check(r)) {
				return new BigInteger(s);
			}
			if (V.containsKey(r) && V.get(r) < s.length()) {
				continue;
			}
			V.put(r, s.length());
			for (int d = 0; d <= 9; d++) {
				int t = d * n + r;
				if (t % 10 <= 2) {
					Q.add(new P(d + s, t / 10));
				}
			}
		}

		return null;
	}

	public static void main(String[] args) {
		BigInteger ans = BigInteger.ZERO;
		for (int n = 1; n <= 10000; n++) {
			ans = ans.add(search(n));
		}
		System.out.println(ans);
	}
}
