package s100;

import java.util.TreeSet;

import core.MathLib;

public class P119 {
	public static void main(String[] args) {
		long M = 1000000000000000L;
		int maxSum = 9 * Long.toString(M).length();

		TreeSet<Long> S = new TreeSet<Long>();
		for (int i = 3; i <= maxSum; i++) {
			long P = i * i;
			do {
				if (MathLib.digitSum(P) == i) {
					S.add(P);
				}
				P *= i;
			} while (P <= M);
		}

		System.out.println(S.size());
		Long[] A = S.toArray(new Long[0]);
		System.out.println(A[29]);
	}
}
