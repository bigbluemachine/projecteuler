package s200;

import java.util.HashMap;
import java.util.TreeSet;

import core.Equation;

public class P280 {
	static Equation[] E = new Equation[25600];
	static TreeSet<Integer> S = new TreeSet<>();
	static HashMap<Integer, TreeSet<Integer>> T = new HashMap<>();

	static void process(int s, int t, double p) {
		E[s].coefs.put(t, p);

		if (!T.containsKey(t)) {
			T.put(t, new TreeSet<Integer>());
		}
		T.get(t).add(s);

		if (E[t] == null) {
			S.add(t);
		}
	}

	static int bitCount(int v) {
		int ans = 0;
		for (int i = 0; i < 30; i++) {
			if ((v & (1 << i)) > 0) {
				ans++;
			}
		}
		return ans;
	}

	static int encode(int top, int bottom, int r, int c) {
		int x = (top << 5) | bottom;
		return 25 * x + 5 * r + c;
	}

	public static void main(String[] args) {
		int start = encode(0, 31, 2, 2);
		TreeSet<Integer> curr = new TreeSet<>();

		S.add(start);
		while (!S.isEmpty()) {
			int s = S.first();
			S.remove(s);

			// Decode
			int temp = s;
			int c = temp % 5;
			int r = (temp /= 5) % 5;
			int bottom = (temp /= 5) & 31;
			int top = temp >> 5;

			// Terminal
			if (top == 31) {
				curr.add(s);
				E[s] = new Equation(s, 1, 0);
				continue;
			}

			// Init
			E[s] = new Equation(s, 1, 1);
			int n = 0;
			if (r != 0) {
				n++;
			}
			if (r != 4) {
				n++;
			}
			if (c != 0) {
				n++;
			}
			if (c != 4) {
				n++;
			}
			double p = 1.0 / n;

			if (bitCount(top << 5 | bottom) == 5) { // No seed
				if (c != 0) { // L
					if (r == 4) { // Bottom
						process(s, encode(top, bottom & ~(1 << (c - 1)), r, c - 1), p);
					} else {
						process(s, encode(top, bottom, r, c - 1), p);
					}
				}
				if (c != 4) { // R
					if (r == 4) { // Bottom
						process(s, encode(top, bottom & ~(1 << (c + 1)), r, c + 1), p);
					} else {
						process(s, encode(top, bottom, r, c + 1), p);
					}
				}
				if (r != 0) { // U
					process(s, encode(top, bottom, r - 1, c), p);
				}
				if (r != 4) { // D
					if (r == 3) { // Second from bottom
						process(s, encode(top, bottom & ~(1 << c), r + 1, c), p);
					} else {
						process(s, encode(top, bottom, r + 1, c), p);
					}
				}
			} else { // Seed
				if (c != 0) { // L
					if (r == 0) { // Top
						process(s, encode(top | (1 << (c - 1)), bottom, r, c - 1), p);
					} else {
						process(s, encode(top, bottom, r, c - 1), p);
					}
				}
				if (c != 4) { // R
					if (r == 0) { // Top
						process(s, encode(top | (1 << (c + 1)), bottom, r, c + 1), p);
					} else {
						process(s, encode(top, bottom, r, c + 1), p);
					}
				}
				if (r != 0) { // U
					if (r == 1) { // Second from top
						process(s, encode(top | (1 << c), bottom, r - 1, c), p);
					} else {
						process(s, encode(top, bottom, r - 1, c), p);
					}
				}
				if (r != 4) { // D
					process(s, encode(top, bottom, r + 1, c), p);
				}
			}
		}

		TreeSet<Integer> prev = new TreeSet<>();
		while (!curr.isEmpty()) {
			for (int s = 0; s < 25600; s++) {
				if (E[s] == null) {
					continue;
				}

				for (int t : prev) {
					E[s].substitute(E[t]);
				}
			}

			for (int s : curr) {
				for (int t : curr) {
					E[t].substitute(E[s]);
				}
			}

			prev.clear();
			prev.addAll(curr);
			curr.clear();
			for (int s : prev) {
				if (T.containsKey(s)) {
					for (int t : T.get(s)) {
						if (!E[t].isResolved()) {
							curr.add(t);
						}
					}
				}
			}
		}

		System.out.println(E[start].getValue());
	}
}
