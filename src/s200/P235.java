package s200;

public class P235 {
	static double s(double n, double r) {
		double rn = Math.pow(r, n);
		double q = (rn - 1) / (r - 1);
		return (900.0 * q) - (3.0 / (r - 1)) * (n * rn - q);
	}

	public static void main(String[] args) {
		double x = -6.0e11;
		double a = 1.000000000;
		double b = 1.010000000;

		double r = 0;
		for (int i = 0; i < 100000; i++) {
			r = 0.5 * (a + b);
			double t = s(5000, r);
			if (t < x) {
				b = r;
			} else {
				a = r;
			}
		}

		System.out.printf("%.12f\n", r);
	}
}
