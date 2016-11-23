package s300;

import java.util.HashMap;
import java.util.HashSet;

public class P332 {
	static int encode(int x, int y, int z) {
		x += 64;
		y += 64;
		z += 64;
		return x << 14 | y << 7 | z;
	}

	static double[] decode(int v) {
		int z = v & 127;
		int y = (v >> 7) & 127;
		int x = (v >> 14) & 127;
		return new double[] { x - 64, y - 64, z - 64 };
	}

	static double norm(double[] v) {
		return Math.sqrt(v[0] * v[0] + v[1] * v[1] + v[2] * v[2]);
	}

	static double dot(double[] v, double[] w) {
		return v[0] * w[0] + v[1] * w[1] + v[2] * w[2];
	}

	static double gcDist(double[] v, double[] w) {
		return Math.acos(dot(v, w) / (norm(v) * norm(w)));
	}

	static double gcDist(int v, int w, HashMap<Long, Double> M) {
		long key = Math.min(v, w);
		key = key << 32 | Math.max(v, w);
		if (M.containsKey(key)) {
			return M.get(key);
		}
		double ans = gcDist(decode(v), decode(w));
		M.put(key, ans);
		return ans;
	}

	static double area(double pq, double pr, double qr, double R) {
		double s = 0.5 * (pq + pr + qr);
		double a = Math.tan(0.5 * s);
		double b = Math.tan(0.5 * (s - pq));
		double c = Math.tan(0.5 * (s - pr));
		double d = Math.tan(0.5 * (s - qr));
		double e = Math.sqrt(a * b * c * d);
		double E = 4.0 * Math.atan(e);
		return R * R * E;
	}

	static double f(int R) {
		HashSet<Integer> P = new HashSet<Integer>();
		for (int x = 0; x <= R; x++) {
			for (int y = 0; y <= R; y++) {
				for (int z = 0; z <= R; z++) {
					if (x * x + y * y + z * z == R * R) {
						P.add(encode(x, y, z));
						P.add(encode(x, -y, z));
						P.add(encode(x, y, -z));
						P.add(encode(x, -y, -z));
					}
				}
			}
		}

		double best = 1.0e10;
		Integer[] ps = P.toArray(new Integer[0]);
		HashMap<Long, Double> M = new HashMap<Long, Double>();
		for (int i = 0; i < ps.length; i++) {
			for (int j = i + 1; j < ps.length; j++) {
				for (int k = j + 1; k < ps.length; k++) {
					double pq = gcDist(ps[i], ps[j], M);
					double pr = gcDist(ps[i], ps[k], M);
					double qr = gcDist(ps[j], ps[k], M);
					double area = area(pq, pr, qr, R);
					if (!Double.isNaN(area) && area > 1.0e-3) {
						best = Math.min(best, area);
					}
				}
			}
		}
		return best;
	}

	public static void main(String[] args) {
		double ans = 0;
		for (int r = 1; r <= 50; r++) {
			ans += f(r);
		}
		System.out.printf("%.6f\n", ans);
	}
}
