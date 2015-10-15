package s100;

public class P190 {
	static double f(int n) {
		double[] x = new double[n];
		double s = 0;
		for (int i = 1; i <= n; i++) {
			x[i - 1] = i;
			s += i;
		}
		double p = 1;
		for (int i = 1; i <= n; i++) {
			p *= Math.pow(x[i - 1] * n / s, i);
		}
		return p;
	}

	public static void main(String[] args) {
		long ans = 0;
		for (int i = 2; i <= 15; i++) {
			ans += (long) f(i);
		}
		System.out.println(ans);
	}
}
