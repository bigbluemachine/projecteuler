package core;

public class Ratio implements Comparable<Ratio> {
	public static boolean AUTO_REDUCE = true;
	public long n, d;

	public Ratio(long n, long d) {
		if (d < 0) {
			n = -n;
			d = -d;
		}
		if (AUTO_REDUCE) {
			long g = MathLib.gcd64(Math.abs(n), d);
			n /= g;
			d /= g;
		}
		this.n = n;
		this.d = d;
	}

	public Ratio add(Ratio r) {
		return new Ratio(n * r.d + d * r.n, d * r.d);
	}

	public Ratio sub(Ratio r) {
		return new Ratio(n * r.d - d * r.n, d * r.d);
	}

	public Ratio mul(Ratio r) {
		return new Ratio(n * r.n, d * r.d);
	}

	public Ratio div(Ratio r) {
		return new Ratio(n * r.d, d * r.n);
	}

	public Ratio inv() {
		return new Ratio(d, n);
	}

	public int compareTo(Ratio r) {
		return Long.signum(n * r.d - d * r.n);
	}

	public String toString() {
		return String.format("%d/%d", n, d);
	}
}
