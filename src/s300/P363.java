package s300;

public class P363 {
	static final double v = 2.0 - Math.sqrt(66.0 - 15.0 * Math.PI) / 3.0;
	static final double err = 1e-12;

	// Solve 3/20*v^2 - 3/5*v + pi/4 - 1/2 = 0
	static class P {
		double x, y;

		P(double X, double Y) {
			x = X;
			y = Y;
		}
	}

	static double dist(P p0, P p1) {
		double dx = p0.x - p1.x, dy = p0.y - p1.y;
		return Math.sqrt(dx * dx + dy * dy);
	}

	static P mid(P p0, P p1) {
		return new P(0.5 * (p0.x + p1.x), 0.5 * (p0.y + p1.y));
	}

	static double f(P p0, P p1, P p2, P p3) {
		double L0 = dist(p0, p3);
		double L1 = dist(p0, p1) + dist(p1, p2) + dist(p2, p3);
		double E = L1 - L0;
		if (E > err) {
			P p01 = mid(p0, p1);
			P p12 = mid(p1, p2);
			P p23 = mid(p2, p3);
			P p0112 = mid(p01, p12);
			P p1223 = mid(p12, p23);
			P m = mid(p0112, p1223);
			return f(p0, p01, p0112, m) + f(m, p1223, p23, p3);
		}
		return 0.5 * L0 + 0.5 * L1;
	}

	public static void main(String[] args) {
		double dist = f(new P(1, 0), new P(1, v), new P(v, 1), new P(0, 1));
		double test = 0.5 * Math.PI;
		System.out.printf("%.12f\n", 100.0 * (dist - test) / test);
	}
}
