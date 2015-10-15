package s100;

import java.util.ArrayList;
import java.util.TreeSet;

import core.Filter;

public class P167 {
	static int cycleLen, cycleBgn, cycleSum;
	static ArrayList<Integer> L = new ArrayList<Integer>();

	static void process(int a, int n) {
		TreeSet<Integer> Q = new TreeSet<Integer>();
		TreeSet<Integer> T = new TreeSet<Integer>();

		int fst = 2 * n + 1, snd = 2 * fst + 2, t;

		L.clear();
		L.add(a);
		L.add(fst);
		Q.add(a + fst);

		for (;;) {
			Q.remove(t = Q.first());
			if (T.remove(t))
				continue;
			for (int j : L)
				if (!Q.add(t + j))
					T.add(t + j);
			L.add(t);
			if (t > snd + 1)
				break;
		}

		new Filter<Integer>() {
			public boolean cond(Integer t) {
				return (t & 1) > 0;
			}
		}.apply(Q);

		int twos = 0, fours = 0, state = 0;

		for (;;) {
			Q.remove(t = Q.first());
			if (T.remove(t))
				continue;
			if (!Q.add(t + 2))
				T.add(t + 2);
			if (!Q.add(t + snd))
				T.add(t + snd);

			int diff = t - L.get(L.size() - 1);
			L.add(t);

			if (state > 0) {
				if (diff == 2 && fours == 0)
					twos++;
				else if (twos >= n - 1 && diff == 4) {
					if (fours++ == n)
						break;
				} else
					state = twos = fours = 0;
			} else if (diff == 2)
				state = twos = 1;
		}

		cycleBgn = n + 4;
		cycleLen = L.size() - 3 * n - 5;
		cycleSum = L.get(cycleBgn + cycleLen) - L.get(cycleBgn);
	}

	static long get(long index) {
		index -= cycleBgn + 1;
		long result = (index / cycleLen) * cycleSum + L.get(cycleBgn);
		result += L.get(cycleBgn + (int) (index % cycleLen)) - L.get(cycleBgn);
		return result;
	}

	public static void main(String[] args) {
		// 3916160068885
		long sum = 0;
		for (int n = 2; n <= 10; n++) {
			process(2, n);
			sum += get(100000000000L);
		}
		System.out.println(sum);
	}
}
