package s200;

public class P220 {
	static <T> T println(T t) {
		System.out.println(t);
		return t;
	}

	static class Triple {
		int X, Y, D;

		Triple(int x, int y, int d) {
			X = x;
			Y = y;
			D = d;
		}

		public String toString() {
			return String.format("(%d, %d, %d)", X, Y, D);
		}
	}

	static long roundDown(long v) {
		v--;
		v |= v >> 1;
		v |= v >> 2;
		v |= v >> 4;
		v |= v >> 8;
		v |= v >> 16;
		v |= v >> 32;
		return (v + 1) >> 1;
	}

	static void g(long a, long b) {
		f(a);
		d = (d + 2) & 3;
		f(a + 1, b);
	}

	static void f(long a, long b) {
		if (a == b) {
			apply(F[0]);
			return;
		}

		long n = b - a + 1, p = roundDown(a), q = p >> 1;
		if (n >= q)
			g(q, n);
		else
			f(n);
	}

	static void f(long n) {
		if ((n & (n - 1)) == 0) {
			int x = 0;
			while ((n >>= 1) > 0)
				x++;
			apply(F[x]);
			return;
		}

		long h = roundDown(n), r = n - h, t = roundDown(r);
		f(h);
		if (r >= (h >> 1) && t > 0)
			g(t, r);
		else
			f(r);
	}

	static void apply(Triple q) {
		switch (d) {
		case 0:
			x += q.X;
			y += q.Y;
			break;
		case 1:
			x += q.Y;
			y -= q.X;
			break;
		case 2:
			x -= q.X;
			y -= q.Y;
			break;
		case 3:
			x -= q.Y;
			y += q.X;
			break;
		}
		d = (d + q.D) & 3;
	}

	static int x = 0, y = 0, d = 0;
	static Triple[] F = new Triple[61];

	public static void main(String[] args) {
		F[0] = new Triple(0, 1, 1);
		int cur = 1, sign = 1;
		for (int i = 0, j = 1; i < 15; i++, sign = -sign) {
			F[j++] = new Triple(sign * cur, sign * cur, 2);
			F[j++] = new Triple(sign * (cur <<= 1), 0, 2);
			F[j++] = new Triple(sign * cur, -sign * cur, 2);
			F[j++] = new Triple(0, -sign * (cur <<= 1), 2);
		}

		f(1000000000000L);
		System.out.println(new Triple(x, y, d));
	}
}
