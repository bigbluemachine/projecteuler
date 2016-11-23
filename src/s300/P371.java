package s300;

public class P371 {
	static double f(int d, int s500) {
		double ans = 0;
		if (s500 == 0) {
			ans += 1 / 1000.0 * f(d, 1); // 500
			ans += d / 1000.0 * 0; // matching
			if (998 - 2 * d > 0) {
				ans += (998 - 2 * d) / 1000.0 * f(d + 1, s500);
			}
		} else {
			ans += 1 / 1000.0 * 0; // 500
			ans += d / 1000.0 * 0; // matching
			if (998 - 2 * d > 0) {
				ans += (998 - 2 * d) / 1000.0 * f(d + 1, s500);
			}
		}

		return 1000.0 * (1 + ans) / (999 - d);
	}

	public static void main(String[] args) {
		System.out.printf("%.8f\n", f(0, 0));
	}
}
