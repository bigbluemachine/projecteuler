package s100;

public class P100 {
	public static void main(String[] args) {
		long n = 1, d = 1;
		long x = 1, y = 2;
		long px = 0, py = 0;
		int m = 0;
		for (;;) {
			long t = d + n;
			n = t + d;
			d = t;
			long blue = x * n + m;
			long total = y * d + m;
			long u = 2 * x + px;
			px = x;
			x = u;
			long v = 2 * y + py;
			py = y;
			y = v;
			m = 1 - m;
			System.out.printf("%d/%d\n", blue, total);
			if (blue + total > 1000000000000L) {
				break;
			}
		}
	}
}
