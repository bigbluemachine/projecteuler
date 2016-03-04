package s200;

import core.Equation;

public class P227 {
	static final double frac(int a, int b) {
		return (double) a / b;
	}

	public static void main(String[] args) {
		int H = 50; // 2H players

		Equation[] e = new Equation[H + 1];

		e[1] = new Equation(1, frac(17, 36), 1);
		e[1].coefs.put(3, frac(1, 36));
		e[1].coefs.put(2, frac(8, 36));

		e[2] = new Equation(2, frac(18, 36), 1);
		e[2].coefs.put(4, frac(1, 36));
		e[2].coefs.put(3, frac(8, 36));
		e[2].coefs.put(1, frac(8, 36));

		e[H] = new Equation(H, frac(18, 36), 1);
		e[H].coefs.put(H - 1, frac(16, 36));
		e[H].coefs.put(H - 2, frac(2, 36));

		e[H - 1] = new Equation(H - 1, frac(17, 36), 1);
		e[H - 1].coefs.put(H, frac(8, 36));
		e[H - 1].coefs.put(H - 2, frac(8, 36));
		e[H - 1].coefs.put(H - 3, frac(1, 36));

		for (int x = 3; x <= H - 2; x++) {
			e[x] = new Equation(x, frac(18, 36), 1);
			e[x].coefs.put(x + 2, frac(1, 36));
			e[x].coefs.put(x + 1, frac(8, 36));
			e[x].coefs.put(x - 1, frac(8, 36));
			e[x].coefs.put(x - 2, frac(1, 36));
		}

		for (int i = 1; i <= H - 2; i++) {
			e[i + 1].substitute(e[i]);
			e[i + 2].substitute(e[i]);
		}

		e[H].substitute(e[H - 1]);
		System.out.println(e[H].ct / e[H].mul);
	}
}
