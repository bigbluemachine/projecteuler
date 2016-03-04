package s200;

public class P226 {
	static class P {
		double x, y;

		P(double x, double y) {
			this.x = x;
			this.y = y;
		}

		P sub(P p) {
			return new P(x - p.x, y - p.y);
		}

		double norm() {
			return Math.sqrt(x * x + y * y);
		}

		double dot(P p) {
			return x * p.x + y * p.y;
		}
	}

	static double sample(double x) {
		double ans = 0;
		double m = 1;
		for (;; m += m) {
			double t = (m * x) % 1.0;
			t = Math.min(t, 1.0 - t) / m;
			ans += t;
			if (t < 1.0e-20) {
				break;
			}
		}
		return ans;
	}

	static double humpLeftArea(double u, double v, double a, double humpArea) {
		if (a - u < v - a) {
			double w = a - u;
			return 0.5 * w * w;
		} else {
			double w = v - a;
			return humpArea - 0.5 * w * w;
		}
	}

	static double layer(int e, double a, double b) {
		double humpWidth = Math.pow(2, -e);
		double factor = Math.pow(2, -(e + 2));
		double humpArea = humpWidth * factor;
		double u = a - a % humpWidth;
		double v = u + humpWidth;
		double w = humpLeftArea(u, v, v - a % humpWidth, humpArea);
		double x = b - b % humpWidth;
		double y = x + humpWidth;
		double z = humpLeftArea(x, y, b, humpArea);
		return w + (x - v) * factor + z;
	}

	public static void main(String[] args) {
		double cx = 0.25, cy = 0.5, r = 0.25;
		double xa = 0, xb = cx;
		double minDist = 1, bestX = 0;
		for (;;) {
			double delta = (xb - xa) / 1000;
			if (delta < 1.0e-10) {
				break;
			}
			for (double x = xa; x <= xb; x += delta) {
				double y = sample(x);
				double dx = x - cx, dy = y - cy;
				double e = Math.abs(dx * dx + dy * dy - r * r);
				if (e < minDist) {
					minDist = e;
					bestX = x;
				}
			}
			xa = bestX - 2 * delta;
			xb = bestX + 2 * delta;
		}

		double total = 0;
		for (int e = 0;; e++) {
			double a = layer(e, bestX, 0.5);
			total += a;
			if (a < 1.0e-20) {
				break;
			}
		}

		P a = new P(bestX, sample(bestX));
		P b = new P(0.5, sample(0.5));
		P c = new P(cx, cy);
		P ca = a.sub(c), cb = b.sub(c);
		double angle = Math.acos(ca.dot(cb) / ca.norm() / cb.norm());
		double sector = 0.5 * r * r * angle;
		double triangle = 0.5 * ca.norm() * cb.norm() * Math.sin(angle);
		double chord = sector - triangle;
		double trap = 0.5 * (a.y + b.y) * (b.x - a.x);
		double ans = total - trap + chord;

		System.out.printf("%.8f\n", ans);
	}
}
