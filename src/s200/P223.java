package s200;

import java.util.HashSet;
import java.util.LinkedList;

import core.NTLib;

public class P223 {
	static int M;

	static int process(int a, LinkedList<Integer> l, LinkedList<Integer> r) {
		long z = (long) (a - 1) * (a + 1);
		HashSet<Integer> V = new HashSet<Integer>();

		for (int i : l) {
			for (int j : r) {
				long p = (long) i * j;
				long q = z / p;

				if (p > q || q - p < a + a) {
					break;
				}

				if (a + q > M || (p + q) % 2 == 1) {
					continue;
				}

				V.add((int) ((q - p) / 2));
			}
		}

		return V.size();
	}

	public static void main(String[] args) {
		M = 25000000;

		long ans = 0;
		for (int b = 1; 1 + b + b <= M; b++) {
			ans++;
		}

		int max = (int) ((double) M / (2.0 + Math.sqrt(2)));

		int interval = 30000;
		int offset = 1;
		LinkedList<Integer>[] cur = NTLib.batchFactor(offset, interval);

		for (int a = 2; a <= max; a++) {
			if (a + 1 - offset >= interval) {
				offset = a - 1;
				cur = NTLib.batchFactor(offset, Math.min(interval, max - a + 3));
			}

			ans += process(a, cur[a - 1 - offset], cur[a + 1 - offset]);
		}

		System.out.println(ans);
	}
}
