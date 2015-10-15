package s000;

import java.util.TreeSet;

import old.Lib;
import core.Ratio;

public class P093 {
	static TreeSet<Ratio> S = new TreeSet<Ratio>();

	static Ratio[] apply(Ratio[] r, int i, char op) {
		Ratio x = null;
		if (op == '+') {
			x = r[i].add(r[i + 1]);
		} else if (op == '-') {
			x = r[i].sub(r[i + 1]);
		} else if (op == '*') {
			x = r[i].mul(r[i + 1]);
		} else {
			if (r[i + 1].n == 0) {
				return null;
			}
			x = r[i].div(r[i + 1]);
		}

		Ratio[] s = new Ratio[r.length - 1];
		for (int j = 0, k = 0; j < r.length; j++) {
			if (j == i) {
				s[k++] = x;
				j++;
			} else {
				s[k++] = r[j];
			}
		}
		return s;
	}

	static void gen(Ratio[] r) {
		if (r.length == 1) {
			if (r[0].d == 1 && r[0].n > 0) {
				S.add(r[0]);
			}
			return;
		}
		for (char c : new char[] { '+', '-', '*', '/' }) {
			for (int i = 0; i < r.length - 1; i++) {
				Ratio[] s = apply(r, i, c);
				if (s != null) {
					gen(s);
				}
			}
		}
	}

	static void genAll(Ratio[] r) {
		int[] p = { 0, 1, 2, 3 };
		for (int i = 0; i < 24; i++) {
			int[] is = Lib.perm(p, i, 4);
			Ratio[] s = { r[is[0]], r[is[1]], r[is[2]], r[is[3]] };
			gen(s);
		}
	}

	static int count() {
		long last = 0;
		for (Ratio r : S) {
			if (r.n == last + 1) {
				last++;
			} else {
				break;
			}
		}
		return (int) last;
	}

	public static void main(String[] args) {
		int best = 0;
		int bestA = 0;
		int bestB = 0;
		int bestC = 0;
		int bestD = 0;

		for (int a = 1; a <= 9; a++) {
			for (int b = a + 1; b <= 9; b++) {
				for (int c = b + 1; c <= 9; c++) {
					for (int d = c + 1; d <= 9; d++) {
						S.clear();
						genAll(new Ratio[] { new Ratio(a, 1), new Ratio(b, 1), new Ratio(c, 1), new Ratio(d, 1) });
						int t = count();
						if (t > best) {
							best = t;
							bestA = a;
							bestB = b;
							bestC = c;
							bestD = d;
						}
					}
				}
			}
		}

		System.out.printf("%d%d%d%d\n", bestA, bestB, bestC, bestD);
	}
}
