package s000;

import java.util.TreeSet;

// Incomplete solution.
public class P044 {
	public static void main(String[] args) {
		int[] P = new int[10000];
		TreeSet<Integer> S = new TreeSet<Integer>();
		for (int i = 1; i < 10000; i++) {
			P[i] = (i * (3 * i - 1)) / 2;
			S.add(P[i]);
		}
		int best = Integer.MAX_VALUE;
		for (int i = 1; i < 10000; i++) {
			for (int j = i + 1; j < 10000; j++) {
				int d = P[j] - P[i];
				if (d > best) {
					break;
				}
				if (S.contains(d) && S.contains(P[j] + P[i])) {
					best = d;
				}
			}
		}
		System.out.println(best);
	}
}
