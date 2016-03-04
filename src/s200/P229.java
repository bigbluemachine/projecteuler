package s200;

import java.util.Arrays;

public class P229 {
	public static void main(String[] args) {
		int MAX = 2000000000, S = (int) Math.sqrt(MAX) + 2;
		int[] Q = new int[S];
		int SIZE = 1000000;
		boolean[] A = new boolean[SIZE], B = new boolean[SIZE];
		int[] mul = { 2, 3, 7 };
		int k = 0, a, b, x, y, z, v;

		for (int i = 1; i < S; i++)
			Q[i] = i * i;

		for (z = 0; z < MAX; z = v) {
			v = z + SIZE;

			Arrays.fill(A, false);
			for (a = 1; (x = Q[a]) < v; a++)
				for (b = Math.max(0, (int) Math.sqrt(z - x - 1)); (y = x + Q[++b]) < v;)
					A[y - z] = true;

			for (int m : mul) {
				Arrays.fill(B, false);
				for (a = 1; (x = m * Q[a]) < v; a++)
					for (b = Math.max(0, (int) Math.sqrt(z - x - 1)); (y = x + Q[++b]) < v;)
						B[y - z] = true;

				for (int i = SIZE; i-- > 0;)
					A[i] &= B[i];
			}

			for (boolean q : A)
				if (q)
					k++;
		}

		System.out.println(k);
	}
}
