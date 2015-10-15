package s000;

import java.util.Arrays;
import java.util.Scanner;

import core.ArrayLib;
import core.IOLib;

public class P054 {
	static boolean ACE_HIGH_STRAIGHT = true;
	static final int HIGH_CARD = 0;
	static final int ONE_PAIR = 1;
	static final int TWO_PAIR = 2;
	static final int THREE = 3;
	static final int STRAIGHT = 4;
	static final int FULL_HOUSE = 5;
	static final int FOUR = 6;

	static boolean isFlush(String[] hand) {
		char suit = hand[0].charAt(1);
		for (int i = 1; i < 5; i++) {
			if (hand[i].charAt(1) != suit) {
				return false;
			}
		}
		return true;
	}

	static int[] values(String[] hand) {
		int[] v = new int[5];
		for (int i = 0; i < 5; i++) {
			char c = hand[i].charAt(0);
			int k = c == 'T' ? 10 : c == 'J' ? 11 : c == 'Q' ? 12 : c == 'K' ? 13 : c == 'A' ? 14 : c - '0';
			v[i] = k;
		}
		Arrays.sort(v);
		return v;
	}

	static int[] straight(int[] v) {
		if (v[0] == 2 && v[3] == 5 && v[4] == 14) {
			return new int[] { 5 };
		}
		if (v[4] == 14) {
			if (ACE_HIGH_STRAIGHT && v[0] == 10) {
				return new int[] { 14 };
			}
		} else {
			if (v[0] + 4 == v[4]) {
				return new int[] { v[4] };
			}
		}
		return null;
	}

	// (a, b) : (4, 1)
	static int[] four(int[] v) {
		if (v[0] == v[3]) {
			return new int[] { v[0], v[4] };
		}
		if (v[1] == v[4]) {
			return new int[] { v[4], v[0] };
		}
		return null;
	}

	// (a, b) : (3, 2)
	static int[] fullHouse(int[] v) {
		if (v[0] == v[2] && v[3] == v[4]) {
			return new int[] { v[0], v[4] };
		}
		if (v[0] == v[1] && v[2] == v[4]) {
			return new int[] { v[4], v[0] };
		}
		return null;
	}

	// (a, b, c) : (3, 1, 1)
	static int[] three(int[] v) {
		if (v[0] == v[2]) {
			return new int[] { v[0], v[4], v[3] };
		}
		if (v[1] == v[3]) {
			return new int[] { v[2], v[4], v[0] };
		}
		if (v[2] == v[4]) {
			return new int[] { v[4], v[1], v[0] };
		}
		return null;
	}

	// (a, b, c) : (2, 2, 1)
	static int[] twoPair(int[] v) {
		if (v[0] == v[1] && v[2] == v[3]) {
			return new int[] { v[2], v[0], v[4] };
		}
		if (v[0] == v[1] && v[3] == v[4]) {
			return new int[] { v[4], v[0], v[2] };
		}
		if (v[1] == v[2] && v[3] == v[4]) {
			return new int[] { v[4], v[2], v[0] };
		}
		return null;
	}

	// (a, b, c, d) : (2, 1, 1, 1)
	static int[] pair(int[] v) {
		if (v[0] == v[1]) {
			return new int[] { v[0], v[4], v[3], v[2] };
		}
		if (v[1] == v[2]) {
			return new int[] { v[1], v[4], v[3], v[0] };
		}
		if (v[2] == v[3]) {
			return new int[] { v[3], v[4], v[1], v[0] };
		}
		if (v[3] == v[4]) {
			return new int[] { v[4], v[2], v[1], v[0] };
		}
		return null;
	}

	static boolean compareHigh(int[] va, int[] vb) {
		for (int i = 4; i >= 0; i--) {
			if (va[i] == vb[i]) {
				continue;
			}
			return va[i] > vb[i];
		}
		return true;
	}

	static int[] getHand(int[] v) {
		int[] t;
		if ((t = four(v)) != null) {
			return ArrayLib.concat(new int[] { FOUR }, t);
		}
		if ((t = fullHouse(v)) != null) {
			return ArrayLib.concat(new int[] { FULL_HOUSE }, t);
		}
		if ((t = three(v)) != null) {
			return ArrayLib.concat(new int[] { THREE }, t);
		}
		if ((t = twoPair(v)) != null) {
			return ArrayLib.concat(new int[] { TWO_PAIR }, t);
		}
		if ((t = pair(v)) != null) {
			return ArrayLib.concat(new int[] { ONE_PAIR }, t);
		}
		if ((t = straight(v)) != null) {
			return ArrayLib.concat(new int[] { STRAIGHT }, t);
		}
		t = new int[6];
		t[0] = HIGH_CARD;
		for (int i = 1; i < 6; i++) {
			t[i] = v[5 - i];
		}
		return t;
	}

	static boolean compare(int[] va, int[] vb, boolean fa, boolean fb) {
		if (!fa && fb) {
			return !compare(vb, va, fb, fa);
		}
		int[] ha = getHand(va);
		int[] hb = getHand(vb);
		if (fa && !fb) {
			if (ha[0] == STRAIGHT) {
				return true;
			}
			if (hb[0] == FOUR || hb[0] == FULL_HOUSE) {
				return false;
			}
			return true;
		}
		if (ha[0] == hb[0]) {
			for (int i = 1; i < ha.length; i++) {
				if (ha[i] == hb[i]) {
					continue;
				}
				return ha[i] > hb[i];
			}
			throw new Error("Unreachable");
		}
		return ha[0] > hb[0];
	}

	public static void main(String[] args) {
		Scanner in = IOLib.scanner("data/054.txt");
		int ans = 0;
		while (in.hasNext()) {
			String[] a = { in.next(), in.next(), in.next(), in.next(), in.next() };
			String[] b = { in.next(), in.next(), in.next(), in.next(), in.next() };
			boolean fa = isFlush(a);
			boolean fb = isFlush(b);
			int[] va = values(a);
			int[] vb = values(b);
			ans += compare(va, vb, fa, fb) ? 1 : 0;
		}
		in.close();
		System.out.println(ans);
	}
}
