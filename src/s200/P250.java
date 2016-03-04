package s200;

import core.MathLib;

public class P250 {
	public static void main(String[] args) {
		int[] Q = new int[250];
		for (int i = 1; i <= 250250; i++) {
			int k = (int) MathLib.modExp(i, i, 250);
			Q[k]++;
		}

		long M = 10000000000000000L;
		long[] A = new long[250];
		A[0] = 1;
		for (int i = 0; i < Q[0]; i++) {
			A[0] = (A[0] << 1) % M;
		}

		for (int i = 1; i < 250; i++) {
			for (int j = 0; j < Q[i]; j++) {
				long[] B = new long[250];
				for (int k = 0; k < 250; k++) {
					B[k] = A[(k - i + 250) % 250];
				}
				for (int k = 0; k < 250; k++) {
					A[k] = (A[k] + B[k]) % M;
				}
			}
		}

		System.out.println(A[0] - 1);
	}
}
