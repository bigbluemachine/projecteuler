package s000;

import core.NTLib;
import core.Selector;

public class P051 {
	static boolean[] P;

	public static void main(String[] args) {
		P = NTLib.simpleSieve(10000000);

		for (int i = 1000; i < 10000000; i++) {
			if (!P[i]) {
				continue;
			}

			String s = Integer.toString(i);
			for (int k = 1; k < s.length(); k++) {
				int ans = Integer.MAX_VALUE;
				boolean found = false;

				Selector S = new Selector(s.length(), k);
				while (S.hasNext()) {
					int v = (int) S.next();
					int t = process(i, v);
					if (t != -1) {
						ans = Math.min(ans, t);
						found = true;
					}
				}

				if (found) {
					System.out.println(ans);
					return;
				}
			}
		}
	}

	static int process(int z, int v) {
		int d = readDigit(z, v);
		if (d == -1) {
			return -1;
		}
		int[] A = new int[10];
		A[0] = z;
		int count = 1;
		for (int i = 0; i <= 9; i++) {
			if (i != d) {
				int y = writeDigit(z, v, i);
				if (y == -1) {
					continue;
				}
				if (P[y]) {
					A[count++] = y;
				}
			}
		}
		if (count == 8) {
			int ans = z;
			for (int i = 1; i < count; i++) {
				ans = Math.min(ans, A[i]);
			}
			return ans;
		}
		return -1;
	}

	// Return -1 if not all are the same
	static int readDigit(int z, int v) {
		String s = Integer.toString(z);
		int d = -1;
		for (int i = 0; i < 32; i++) {
			if ((v & (1 << i)) > 0) {
				int t = s.charAt(i) - '0';
				if (d == -1) {
					d = t;
				} else if (d != t) {
					return -1;
				}
			}
		}
		return d;
	}

	// Return -1 if leading zero is written
	static int writeDigit(int z, int v, int d) {
		if ((v & 1) > 0 && d == 0) {
			return -1;
		}
		char[] c = Integer.toString(z).toCharArray();
		for (int i = 0; i < 32; i++) {
			if ((v & (1 << i)) > 0) {
				c[i] = (char) ('0' + d);
			}
		}
		return Integer.parseInt(new String(c));
	}
}
