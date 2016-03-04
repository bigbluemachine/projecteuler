package s200;

public class P285 {
	static double s(double r, double t) {
		return r * r * t / 2.0;
	}

	static double g(double u, double v, double x, double y) {
		double a = Math.sqrt(v * v - x * x);
		double A = s(v, 0.5 * Math.PI - 2.0 * Math.asin(x / v)) + x * a;
		A = a * a - A;
		A = (a - x) * (a - x) - A;
		if (u > x) {
			double b = Math.sqrt(u * u - x * x);
			double B = s(u, 0.5 * Math.PI - 2.0 * Math.asin(x / u)) + x * b;
			B = b * b - B;
			B = (b - x) * (b - x) - B;
			A -= B;
		}
		return A;
	}

	static double f(double k) {
		double t = 1.0 + 0.25 / (k * k);
		double u = Math.sqrt(t - 1.0 / k);
		double v = Math.sqrt(t + 1.0 / k);
		double x = 1.0 / k;
		double y = 1.0 + 1.0 / k;
		return g(u, v, x, y);
	}

	public static void main(String[] args) {
		double ans = 0;
		for (int k = 1; k <= 100000; k++) {
			ans += f(k) * k;
		}
		System.out.printf("%.5f\n", ans);
	}
}
