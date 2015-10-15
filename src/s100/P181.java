package s100;

import java.util.Arrays;
import java.util.TreeSet;

public class P181 {
	static class Slope implements Comparable<Slope> {
		int dy, dx;

		Slope(int y, int x) {
			dy = y;
			dx = x;
		}

		public int compareTo(Slope other) {
			return dy * other.dx - dx * other.dy;
		}
	}

	static int X, Y, N;
	static Slope[] S;
	static long[] P;
	static long[][][] T;

	static int gcd(int a, int b) {
		while (a != 0) {
			a ^= b;
			b ^= a;
			a = (a ^ b) % b;
		}
		return b;
	}

	static long[] partition(int max) {
		int[] pent = new int[max];
		long[] part = new long[max];

		boolean a, b;
		int i, j;
		long k;

		for (a = false, i = 1, j = 0; j < max; i = a ? 1 - i : -i, a = !a) {
			pent[j++] = i * (3 * i - 1) / 2;
		}

		part[0] = part[1] = 1;
		for (i = 2, k = 0; i < max; i++) {
			for (a = b = false, j = 0, k = 0; pent[j] <= i; j++, b ^= a, a = !a) {
				k += b ? -part[i - pent[j]] : part[i - pent[j]];
			}
			part[i] = k;
		}

		return part;
	}

	static long F(int x, int y, int index) {
		if (T[x][y][index] >= 0) {
			return T[x][y][index];
		}

		if (x == X) {
			return T[x][y][index] = P[Y - y];
		}

		Slope test = new Slope(Y - y, X - x);
		long ans = 0;
		for (int i = index; i < N && S[i].compareTo(test) <= 0; i++) {
			int xx = x + S[i].dx, yy = y + S[i].dy, k = 1;
			while (xx <= X && yy <= Y) {
				ans += P[k++] * F(xx, yy, i + 1);
				xx += S[i].dx;
				yy += S[i].dy;
			}
		}

		return T[x][y][index] = ans;
	}

	static long run(int a, int b) {
		X = a;
		Y = b;

		TreeSet<Slope> s = new TreeSet<Slope>();
		s.add(new Slope(0, 1));
		for (int x = 1; x <= X; x++) {
			for (int y = 1; y <= Y; y++) {
				if (gcd(x, y) == 1) {
					s.add(new Slope(y, x));
				}
			}
		}

		N = s.size();
		S = s.toArray(new Slope[N]);
		P = partition(100);
		T = new long[X + 1][Y + 1][N + 1];

		for (long[][] U : T) {
			for (long[] V : U) {
				Arrays.fill(V, -1);
			}
		}

		return F(0, 0, 0);
	}

	public static void main(String[] args) {
		long t = System.currentTimeMillis();
		long ans = run(60, 40);
		System.out.printf("Answer: %d\n", ans);
		System.out.printf("Time: %d ms\n", System.currentTimeMillis() - t);
	}
}
