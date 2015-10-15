package s100;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import core.MathLib;
import core.NTLib;

public class P176 {
	static int NP;
	static Integer[] P;
	static double[] LOG;

	static double bestExp = Double.MAX_VALUE;
	static int bestR;
	static LinkedList<Integer> bestL;

	static void g(int r, LinkedList<Integer> L) {
		Collections.sort(L, Collections.reverseOrder());

		double d = LOG[0] * r;
		int index = 1;
		for (int i : L)
			d += LOG[index++] * ((i - 1) / 2);

		if (d < bestExp) {
			bestExp = d;
			bestR = r;
			bestL = L;
		}
	}

	static void f(int r, int n, LinkedList<Integer> L) {
		LinkedList<Integer> L_ = new LinkedList<Integer>(L);
		L_.add(n);
		g(r, L_);
		for (int i = n / 2; i > 1; i--)
			if (n % i == 0) {
				L.add(i);
				f(r, n / i, L);
				L.removeLast();
			}
	}

	public static void main(String[] args) {
		List<Integer> p = NTLib.primeList(100000);
		NP = p.size();
		P = new Integer[NP];
		LOG = new double[NP];
		for (int i = 0; i < NP; i++)
			LOG[i] = Math.log(P[i] = p.get(i));

		int n = 47547;
		for (int c = n, a = c / 2 + 1; c >= 1; c -= 2, a--)
			if ((n + a) % c == 0)
				f((n + a) / c, c, new LinkedList<Integer>());

		long ans = 1 << bestR;
		int index = 1;
		for (int e : bestL)
			ans *= MathLib.pow64(P[index++], (e - 1) / 2);
		System.out.println(ans);
	}
}
