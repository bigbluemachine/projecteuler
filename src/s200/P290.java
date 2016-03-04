package s200;

import java.util.HashMap;

public class P290 {
	static int sd(int n) {
		int ans = 0;
		while (n > 0) {
			ans += n % 10;
			n /= 10;
		}
		return ans;
	}

	static int digits;
	static HashMap<Integer, Long> M = new HashMap<Integer, Long>();

	static int encode(int left, int last, int highest, int diff) {
		int ans = diff + 9 * digits;
		ans = 10 * ans + last;
		ans = digits * ans + left;
		ans = (ans << 8) + highest;
		return ans;
	}

	// left digits left to consider
	// last digit considered
	// highest = highest digits of 137n
	// diff gets + from n, - from 137n
	static long f(int left, int last, int highest, int diff) {
		int key = encode(left, last, highest, diff);
		if (M.containsKey(key))
			return M.get(key);

		long ans = 0;
		if (last != 0 && sd(highest) == diff)
			ans++;

		if (left > 0)
			for (int i = 0; i < 10; i++) {
				int newHighest = highest + 137 * i;
				ans += f(left - 1, i, newHighest / 10, diff + i - (newHighest % 10));
			}

		M.put(key, ans);
		return ans;
	}

	public static void main(String[] args) {
		digits = 18;
		System.out.println(f(digits, 0, 0, 0) + 1);
		System.out.println(M.size());
	}
}
