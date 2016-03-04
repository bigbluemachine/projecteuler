package s200;

import java.util.HashMap;

import core.MathLib;

public class P205 {
	static void gen(int d, int n, int s, HashMap<Integer, Long> D) {
		if (n == 0) {
			if (D.containsKey(s)) {
				D.put(s, D.get(s) + 1);
			} else {
				D.put(s, 1L);
			}
			return;
		}
		for (int i = 0; i < d; i++) {
			gen(d, n - 1, s + i + 1, D);
		}
	}

	public static void main(String[] args) {
		HashMap<Integer, Long> P = new HashMap<Integer, Long>();
		HashMap<Integer, Long> C = new HashMap<Integer, Long>();
		gen(4, 9, 0, P);
		gen(6, 6, 0, C);

		long ans = 0;
		for (int p : P.keySet()) {
			long count = 0;
			for (int i = 6; i < p; i++) {
				count += C.get(i);
			}
			count *= P.get(p);
			ans += count;
		}
		long total = MathLib.pow64(4, 9) * MathLib.pow64(6, 6);
		double r = (double) ans / total;
		System.out.printf("%.7f\n", r);
	}
}
