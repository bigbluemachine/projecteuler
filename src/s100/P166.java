package s100;

import java.util.Arrays;

public class P166 {
	// Board configuration:
	// 0 1 2 3
	// 4 5 6 7
	// 8 9 a b
	// c d e f

	// Order to fill out cells
	static int[] O = { 0x4, 0x8, 0xc, 0x6, 0x9, 0x5, 0x7, 0xd, 0xa, 0xf, 0xb, 0xe };

	// Relations to compute
	static int[][] Q = { {}, {}, { 0x048c }, {}, { 0x369c }, {}, { 0x4567 }, { 0x159d }, {}, { 0x05af },
			{ 0x89ab, 0x37bf }, { 0xcdef, 0x26ae } };

	static int S;
	static int[] B = new int[16];
	static long ANS;

	static boolean check(int q) {
		int s = 0;
		for (int i = 0; i < 4; i++) {
			s += B[q & 0xf];
			q >>= 4;
		}
		return s == S;
	}

	static void gen(int i) {
		if (i >= O.length) {
			ANS++;
			return;
		}

		for (int d = 0; d <= 9; d++) { // lazy-ass coding; optimize this
			B[O[i]] = d;
			boolean good = true;
			for (int q : Q[i]) {
				if (!check(q)) {
					good = false;
					break;
				}
			}
			if (good) {
				gen(i + 1);
			}
		}
	}

	public static void main(String[] args) {
		ANS = 0;
		for (int n = 0; n <= 36; n++) {
			S = n;
			for (int v = 0; v <= 9999; v++) {
				int[] r = new int[4];
				int s = 0;
				int t = v;
				for (int i = 3; i >= 0; i--) {
					r[i] = t % 10;
					s += r[i];
					t /= 10;
				}
				if (s == S) {
					Arrays.fill(B, 0);
					B[0] = r[0];
					B[1] = r[1];
					B[2] = r[2];
					B[3] = r[3];
					gen(0);
				}
			}
		}
		System.out.println(ANS);
	}
}
