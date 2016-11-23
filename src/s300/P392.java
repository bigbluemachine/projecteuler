package s300;

public class P392 {
	static double R;
	static int BINS;
	static double EPS;
	static double R2;

	static double f(double x) {
		x -= R;
		return R - Math.sqrt(R2 - x * x);
	}

	static double refine(double left, double right, int bins) {
		double best = 0;
		double lo = left;
		double hi = right;
		double yr = f(right);

		for (;;) {
			double delta = (hi - lo) / (bins - 1);
			double[] v = new double[bins];
			for (int i = 0; i < bins; i++) {
				double x = lo + delta * i;
				v[i] = f(x) * (x - left) + yr * (right - x);
			}

			int i = 0;
			while (i + 1 < bins && v[i] < v[i + 1]) {
				i++;
			}

			int j = bins - 1;
			while (j > 0 && v[j - 1] > v[j]) {
				j--;
			}

			if (i >= j) {
				i = Math.max(i - 1, 0);
				j = Math.min(j + 1, bins - 1);
			}

			hi = lo + delta * j;
			lo = lo + delta * i;

			double temp = 0;
			for (double w : v) {
				temp = Math.max(temp, w);
			}
			if (temp - best < EPS) {
				break;
			}
			best = temp;
		}

		return 0.5 * (lo + hi);
	}

	static double area(double[] xs) {
		double ans = 0;
		for (int i = 1; i < xs.length - 1; i++) {
			ans += (xs[i] - xs[i - 1]) * f(xs[i]);
		}
		return ans;
	}

	public static void main(String[] args) {
		R = 10.0;
		BINS = 50;
		EPS = 1.0e-14;
		R2 = R * R;

		int n = 400;
		int h = n / 2;

		double[] xs = new double[h + 2];
		double d = R / (h + 1);
		for (int i = 1; i <= h; i++) {
			xs[i] = d * i;
		}
		xs[h + 1] = R;

		for (;;) {
			double gain = 0;
			for (int j = h; j >= 1; j--) {
				double x = refine(xs[j - 1], xs[j + 1], BINS);
				double yr = f(xs[j + 1]);
				double prev = f(xs[j]) * (xs[j] - xs[j - 1]) - yr * xs[j];
				double next = f(x) * (x - xs[j - 1]) - yr * x;
				if (next < prev) {
					continue;
				}
				gain += next - prev;
				xs[j] = x;
			}
			if (gain < EPS) {
				break;
			}
		}

		double area = 0;
		for (int i = 1; i < xs.length - 1; i++) {
			area += (xs[i] - xs[i - 1]) * f(xs[i]);
		}
		System.out.printf("%.10f\n", (4.0 * (R2 - area)) / R2);
	}
}
