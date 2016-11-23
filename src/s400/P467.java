package s400;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import core.NTLib;

public class P467 {
	static String A, B;
	static int la, lb;
	static int[][] L, T;
	static final int INCA = 1, INCB = 2, BOTH = 3;

	static int f(int a, int b) {
		if (L[a][b] >= 0) {
			return L[a][b];
		} else if (a == la || b == lb) {
			return L[a][b] = 0;
		}

		char ca = A.charAt(a);
		char cb = B.charAt(b);

		if (ca == cb) {
			T[a][b] = BOTH;
			return L[a][b] = 1 + f(a + 1, b + 1);
		}

		int ta = f(a + 1, b);
		int tb = f(a, b + 1);

		if (ta == tb) {
			// Lexicographic
			T[a][b] = ca > cb ? INCB : INCA;
			return L[a][b] = ta;
		} else if (ta > tb) {
			T[a][b] = INCA;
			return L[a][b] = ta;
		} else {
			T[a][b] = INCB;
			return L[a][b] = tb;
		}
	}

	static void lcs(String sa, String sb) {
		A = sa;
		B = sb;

		la = A.length();
		lb = B.length();
		L = new int[la + 1][lb + 1];
		T = new int[la + 1][lb + 1];

		for (int i = 0; i <= la; i++) {
			for (int j = 0; j <= lb; j++) {
				L[i][j] = -1;
			}
		}

		// Anti-stack overflow
		for (int i = la; i >= 0; i--) {
			for (int j = lb; j >= 0; j--) {
				f(i, j);
			}
		}

		String ab = "";
		int a = 0, b = 0;
		while (a < la && b < lb) {
			switch (T[a][b]) {
			case INCA:
				ab += A.charAt(a);
				a++;
				break;
			case INCB:
				ab += B.charAt(b);
				b++;
				break;
			case BOTH:
				ab += A.charAt(a);
				a++;
				b++;
				break;
			}
		}

		if (a < la) {
			for (int i = a; i < la; i++) {
				ab += A.charAt(i);
			}
		} else if (b < lb) {
			for (int i = b; i < lb; i++) {
				ab += B.charAt(i);
			}
		}

		BigInteger big = new BigInteger(ab);
		BigInteger m = big.mod(BigInteger.valueOf(1000000007));
		System.out.println(m);
	}

	static int dr(int n) {
		int x = 0;
		while (n > 0) {
			x += n % 10;
			n /= 10;
		}
		return x < 10 ? x : dr(x);
	}

	public static void main(String[] args) {
		int S = 10000;
		List<Integer> P = NTLib.primeList(104740);
		LinkedList<Integer> C = new LinkedList<Integer>();

		Iterator<Integer> ip = P.iterator();
		ip.next(); // consume 2
		ip.next(); // consume 3

		int next = 4;
		while (C.size() < S) {
			int p = ip.next();
			for (int i = next; i < p; i++) {
				C.add(i);
				if (C.size() >= S) {
					break;
				}
			}
			next = p + 1;
		}

		String PD = "";
		String CD = "";

		for (int i = 0; i < S; i++) {
			PD += dr(P.get(i));
			CD += dr(C.get(i));
		}

		lcs(PD, CD);
	}
}
