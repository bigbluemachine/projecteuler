package s100;

import java.util.LinkedList;

public class P199 {
	static double[] v(double... v) {
		return v;
	}

	static double area(double r) {
		return Math.PI * r * r;
	}

	static double outer(double a, double b, double c) {
		double u = a * b * c;
		double v = a * b + a * c + b * c;
		double w = 2.0 * Math.sqrt(u * (a + b + c));
		return u / (v - w);
	}

	static double inner(double a, double b, double c) {
		double u = a * b * c;
		double v = a * b + a * c + b * c;
		double w = 2.0 * Math.sqrt(u * (a + b + c));
		return u / (v + w);
	}

	public static void main(String[] args) {
		double r = Math.sqrt(3);
		r = r / (r + 2.0);

		LinkedList<double[]> todo = new LinkedList<double[]>();
		todo.add(v(r, r, r));
		todo.add(v(-1, r, r));
		todo.add(v(-1, r, r));
		todo.add(v(-1, r, r));

		double ans = 3.0 * area(r);
		for (int i = 0; i < 10; i++) {
			int count = todo.size();
			for (int j = 0; j < count; j++) {
				double[] v = todo.removeFirst();
				if (v[0] == -1) {
					double t = outer(-1, v[1], v[2]);
					ans += area(t);
					if (i < 9) {
						todo.add(v(-1, t, v[1]));
						todo.add(v(-1, t, v[2]));
						todo.add(v(t, v[1], v[2]));
					}
				} else {
					double t = inner(v[0], v[1], v[2]);
					ans += area(t);
					if (i < 9) {
						todo.add(v(t, v[0], v[1]));
						todo.add(v(t, v[0], v[2]));
						todo.add(v(t, v[1], v[2]));
					}
				}
			}
		}

		ans = area(1) - ans;
		ans = ans / area(1);
		System.out.printf("%.8f\n", ans);
	}
}
