package s100;

import java.util.HashMap;

public class P158 {
	static HashMap<Long, Long> T = new HashMap<Long, Long>();
	static int N;

	static long f(int l, int r, int found, int count) {
		long k = l;
		k = k << 5 | r;
		k = k << 1 | found;
		if (T.containsKey(k)) {
			return T.get(k);
		}

		if (count == N) {
			T.put(k, (long) found);
			return found;
		}

		long ans = 0;
		for (int i = 0; i < l; i++) {
			ans += f(i, r + l - i - 1, found, count + 1);
		}
		if (found == 0) {
			for (int i = 0; i < r; i++) {
				ans += f(l + i, r - i - 1, 1, count + 1);
			}
		}
		T.put(k, ans);
		return ans;
	}

	public static void main(String[] args) {
		long ans = 0;
		for (int i = 2; i <= 26; i++) {
			N = i;
			T.clear();
			long t = 0;
			for (int w = 0; w < 26; w++) {
				t += f(w, 25 - w, 0, 1);
			}
			ans = Math.max(ans, t);
		}
		System.out.println(ans);
	}
}
