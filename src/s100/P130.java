package s100;

import core.NTLib;

public class P130 {
	static int z(int n) {
		int x = 1;
		int j = 0;
		int ans = 0;
		do {
			j = (j + x) % n;
			x = (10 * x) % n;
			ans++;
		} while (j != 0);
		return ans;
	}

	public static void main(String[] args) {
		int ans = 0;
		int count = 0;
		boolean[] P = NTLib.simpleSieve(1000000);
		for (int i = 3; i < 1000000; i += i % 10 == 3 ? 4 : 2) {
			if (P[i]) {
				continue;
			}
			int z = z(i);
			if ((i - 1) % z == 0) {
				ans += i;
				if (++count == 25) {
					break;
				}
			}
		}
		System.out.println(ans);
	}
}
