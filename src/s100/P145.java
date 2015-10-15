package s100;

public class P145 {
	static boolean f(int n) {
		int r = 0;
		int s = n;
		while (n > 0) {
			int d = n % 10;
			n /= 10;
			r = 10 * r + d;
		}
		s += r;
		while (s > 0) {
			if ((s % 10) % 2 == 0) {
				return false;
			}
			s /= 10;
		}
		return true;
	}

	public static void main(String[] args) {
		int ans = 0;
		for (int i = 0; i < 1000000000; i++) {
			if (i % 10 > 0 && f(i)) {
				ans++;
			}
		}
		System.out.println(ans);
	}
}
