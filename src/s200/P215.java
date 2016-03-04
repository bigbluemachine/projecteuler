package s200;

import java.util.ArrayList;
import java.util.HashMap;

public class P215 {
	static int W, H;

	static void gen(int mask, int curr, int i, ArrayList<Integer> L) {
		if (W - i < 3) {
			return;
		}
		if (W - i < 5) {
			L.add(curr);
			return;
		}
		int next;
		next = curr | 1 << (i + 2);
		if ((mask & next) == 0) {
			gen(mask, next, i + 2, L);
		}
		next = curr | 1 << (i + 3);
		if ((mask & next) == 0) {
			gen(mask, next, i + 3, L);
		}
	}

	static long f(int mask, int left, HashMap<Long, Long> T) {
		long key = mask;
		key = key << 4 | left;
		if (!T.containsKey(key)) {
			long ans;
			if (left == 0) {
				ans = 1;
			} else {
				ArrayList<Integer> L = new ArrayList<Integer>();
				gen(mask, 0, -1, L);
				ans = 0;
				for (int i : L) {
					ans += f(i, left - 1, T);
				}
			}
			T.put(key, ans);
		}
		return T.get(key);
	}

	public static void main(String[] args) {
		W = 32;
		H = 10;
		System.out.println(f(0, H, new HashMap<Long, Long>()));
	}
}
