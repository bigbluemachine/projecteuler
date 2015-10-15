package s100;

public class P144 {
	static double norm(double[] v) {
		return Math.sqrt(v[0] * v[0] + v[1] * v[1]);
	}

	static double[] reflect(double[] v, double[] normal) {
		double norm = norm(normal);
		double[] b = new double[] { normal[0] / norm, normal[1] / norm };
		double ab = v[0] * b[0] + v[1] * b[1];
		return new double[] { 2 * (v[0] - b[0] * ab) - v[0], 2 * (v[1] - b[1] * ab) - v[1] };
	}

	public static void main(String[] args) {
		int ans = 0;
		double[] v = { 1.4, -19.7 };
		double x = 1.4, y = -9.6;
		while (Math.abs(x) > 0.01 || Math.abs(y - 10) >= 0.01) {
			ans++;
			v = reflect(v, new double[] { 4.0 * x, y });
			double dx = v[0], dy = v[1];
			double n = -(8 * x * dx + 2 * y * dy) / (4 * dx * dx + dy * dy);
			x += n * dx;
			y += n * dy;
		}
		System.out.println(ans);
	}
}
