package s200;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;

import core.NTLib;

public class P251 {
	static int M;

	static int process(int y, LinkedList<Integer> l, LinkedList<Integer> r) {
		int p = 8 * y - 3;
		int a = 3 * y - 1;

		HashSet<Integer> V = new HashSet<Integer>();

		for (int i : l) {
			int yi = y / i;

			for (int j : r) {
				int pj = p / (j * j);
				long c = (long) pj * yi * yi;

				// OVERFLOW TEST
				if (c / yi != (long) pj * yi) {
					continue;
				}

				long b = (long) i * j;

				if (a + b + c <= M) {
					V.add((int) b);
				}
			}
		}

		return V.size();
	}

	public static void main(String[] args) throws Exception {
		M = 110000000;

		int max = (int) Math.ceil((double) M / 6.6); // A reasonable upper bound

		// Q[n] = max n s.t. n^2 is a divisor of 8n - 3
		// e.g. Q[6] = 3
		int[] Q = new int[max + 1];
		Arrays.fill(Q, 1);
		int maxQ = 1;
		for (int i = 3; i * i + 3 <= 8 * max; i += 2) {
			for (int j = i * i; (j + 3) <= 8 * max; j += i * i) {
				if (j % 8 == 5) {
					Q[((j + 3) / 8)] = i;
					maxQ = Math.max(maxQ, i);
				}
			}
		}
		LinkedList<Integer>[] divQ = NTLib.batchFactor(0, maxQ + 1);

		long ans = 0;

		int interval = 50000;
		int offset = 1;
		LinkedList<Integer>[] divY = NTLib.batchFactor(offset, interval);

		for (int y = 1; y <= max; y++) {
			if (y - offset >= interval) {
				offset = y;
				divY = NTLib.batchFactor(offset, Math.min(interval, max - y + 1));
			}

			ans += process(y, divY[y - offset], divQ[Q[y]]);
		}

		System.out.println(ans);
	}
}
