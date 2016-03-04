package s200;

import java.util.TreeSet;

import core.MathLib;
import core.Ratio;

public class P236 {
	static int[] cA = { 5248, 5760, 1312, 2624, 3936 }; // sum = 18880
	static int[] cB = { 640, 3776, 1888, 3776, 5664 }; // sum = 15744
	static int tA = 18880, tB = 15744;
	static int[] redCA = new int[5], redCB = new int[5];
	static int redTA, redTB;
	static int[] G = new int[5], P = new int[5], Q = new int[5];

	static Ratio ratio(Ratio m, int p, int q) {
		int pn = (int) (p * m.n);
		int qd = (int) (q * m.d);
		int g = MathLib.gcd32(pn, qd);
		return new Ratio(pn / g, qd / g);
	}

	static boolean search(int[][] k) {
		for (int a = 1; a <= k[0][0]; a++) {
			for (int b = 1; b <= k[1][0]; b++) {
				for (int c = 1; c <= k[2][0]; c++) {
					int m = k[3][1];
					int rhs = -(a * k[0][1] + b * k[1][1] + c * k[2][1]);
					if (m < 0) {
						m = -m;
						rhs = -rhs;
					}
					if (rhs > 0 && rhs % m == 0 && k[3][1] + k[4][1] <= rhs / m)
						return true;
				}
			}
		}
		return false;
	}

	static boolean f(Ratio m) {
		Ratio t = ratio(m, redTA, redTB);
		for (int i = 0; i < 5; i++)
			if (t.n > cA[i] || t.d > cB[i])
				return false;

		Ratio[] v = new Ratio[5];
		int[][] k = new int[5][2];
		for (int i = 0; i < 5; i++) {
			v[i] = ratio(m.inv(), redCA[i], redCB[i]);
			k[i][0] = (int) Math.min(cA[i] / v[i].n, cB[i] / v[i].d);
		}
		{
			int g = 0;
			for (int i = 0; i < 5; i++) {
				k[i][1] = (int) (v[i].n * t.d - v[i].d * t.n);
				g = MathLib.gcd32(g, Math.abs(k[i][1]));
			}
			for (int i = 0; i < 5; i++)
				k[i][1] /= g;
		}

		return search(k);
	}

	public static void main(String[] args) {
		{
			int g = MathLib.gcd32(tA, tB);
			redTA = tA / g;
			redTB = tB / g;
		}

		for (int i = 0; i < 5; i++) {
			int g = MathLib.gcd32(cA[i], cB[i]);
			redCA[i] = cA[i] / g;
			redCB[i] = cB[i] / g;
			int p = redTA * redCA[i], q = redTB * redCB[i];
			G[i] = MathLib.gcd32(p, q);
			P[i] = p / G[i];
			Q[i] = q / G[i];
		}

		TreeSet<Ratio> Y = new TreeSet<Ratio>();
		TreeSet<Ratio> N = new TreeSet<Ratio>();

		for (int nA = 2; nA <= tA; nA++) {
			L: for (int nB = 1; nB <= tB && tA * nB < tB * nA; nB++) {
				int tBnA = tB * nA, tAnB = tA * nB;
				if (tBnA >= 2.45 * tAnB)
					continue;

				for (int i = 0; i < 5; i++) {
					int pnB = P[i] * nB, qnA = Q[i] * nA;
					int g = MathLib.gcd32(pnB, qnA);
					if (pnB > g * cA[i] || qnA > g * cB[i])
						continue L;
				}

				int g = MathLib.gcd32(tBnA, tAnB);
				Ratio m = new Ratio(tBnA / g, tAnB / g);
				if (!Y.contains(m) && !N.contains(m))
					(f(m) ? Y : N).add(m);
			}
		}

		System.out.println(Y);
		System.out.println(Y.size());
	}
}
