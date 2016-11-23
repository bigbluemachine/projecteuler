package s200;

import java.util.HashMap;

public class P276 {
	static void seed(long[] S, long... ns) {
		System.arraycopy(ns, 0, S, 0, ns.length);
	}

	static long f(int p, HashMap<Integer, Long> M, long[] T) {
		if (M.containsKey(p)) {
			return M.get(p);
		}
		long ans = T[p];
		for (int g = 2; 3 * g <= p; g++) {
			ans -= f(p / g, M, T);
		}
		M.put(p, ans);
		return ans;
	}

	public static void main(String[] args) {
		int max = 10000000;

		// Produce Alcuin's sequence; See OEIS.
		long[] A059169 = new long[max + 1];
		seed(A059169, 0, 0, 0, 1, 0, 1);
		for (int i = 6; i <= max; i++) {
			A059169[i] = A059169[i - 1] + A059169[i - 4] - A059169[i - 5];
		}

		long[] A005044 = new long[max + 1];
		seed(A005044, 0, 0, 0, 1, 0, 1);
		for (int i = 6; i <= max; i++) {
			A005044[i] = A005044[i - 6] + A059169[i];
		}

		long[] T = new long[max + 1];
		long sum = 0;
		for (int i = 3; i <= max; i++) {
			sum += A005044[i];
			T[i] = sum;
		}

		System.out.println(f(max, new HashMap<Integer, Long>(), T));
	}
}
