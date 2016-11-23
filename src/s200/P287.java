package s200;

public class P287 {
	static int E;
	static long S;
	static long H;

	static boolean test(long x, long y) {
		x -= H;
		y -= H;
		return x * x + y * y <= H * H;
	}

	static long f(long xl, long yl, long xr, long yr) {
		if (xl == xr) {
			return 2;
		}

		int count = 0;
		count += test(xl, yl) ? 1 : 0;
		count += test(xl, yr) ? 1 : 0;
		count += test(xr, yl) ? 1 : 0;
		count += test(xr, yr) ? 1 : 0;
		if (count == 0 || count == 4) {
			return 2;
		}

		long h = (xr - xl + 1) >> 1;
		long x0 = xl + h;
		long y0 = yl + h;

		long ans = 1;
		ans += f(x0, y0, xr, yr);
		ans += f(xl, y0, x0 - 1, yr);
		ans += f(xl, yl, x0 - 1, y0 - 1);
		ans += f(x0, yl, xr, y0 - 1);
		return ans;
	}

	public static void main(String[] args) {
		E = 24;
		S = 1 << E;
		H = 1 << (E - 1);

		long ans = 1;
		ans += f(H, H, S - 1, S - 1);
		ans += f(0, H, H - 1, S - 1);
		ans += f(0, 0, H - 1, H - 1);
		ans += f(H, 0, S - 1, H - 1);
		System.out.println(ans);
	}
}
