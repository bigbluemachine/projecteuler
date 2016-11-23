package s300;

import core.MathLib;
import core.NTLib;

public class P315 {
	static int[] D = new int[10];
	static {
		D[0] = m(0, 1, 2, 4, 5, 6);
		D[1] = m(2, 5);
		D[2] = m(0, 2, 3, 4, 6);
		D[3] = m(0, 2, 3, 5, 6);
		D[4] = m(1, 2, 3, 5);
		D[5] = m(0, 1, 3, 5, 6);
		D[6] = m(0, 1, 3, 4, 5, 6);
		D[7] = m(0, 1, 2, 5);
		D[8] = m(0, 1, 2, 3, 4, 5, 6);
		D[9] = m(0, 1, 2, 3, 5, 6);
	}

	static int m(int... s) {
		int v = 0;
		for (int t : s) {
			v |= 1 << t;
		}
		return v;
	}

	static int g(int a, int b) {
		int ans = 0;
		while (a > 0 && b > 0) {
			int v = D[a % 10] & D[b % 10];
			for (int i = 0; i <= 30; i++) {
				if ((v & (1 << i)) > 0) {
					ans++;
				}
			}
			a /= 10;
			b /= 10;
		}
		return ans;
	}

	static int f(int d) {
		int ans = 0;
		while (d > 9) {
			int e = MathLib.digitSum(d);
			ans += g(d, e);
			d = e;
		}
		return 2 * ans;
	}

	public static void main(String[] args) {
		boolean[] P = NTLib.simpleSieve(20000000);
		int ans = 0;
		for (int i = 10000000; i < 20000000; i++) {
			if (P[i]) {
				ans += f(i);
			}
		}
		System.out.println(ans);
	}
}
