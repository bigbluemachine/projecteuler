package s000;

import java.math.BigInteger;

// Beware -- lazy.
public class P055 {
	public static void main(String[] args) {
		int ans = 0;
		for (int i = 1; i < 10000; i++) {
			if (lychrel(i)) {
				ans++;
			}
		}
		System.out.println(ans);
	}

	static boolean lychrel(int n) {
		BigInteger x = BigInteger.valueOf(n);
		for (int i = 0; i < 49; i++) {
			BigInteger y = new BigInteger(reverse(x.toString()));
			x = x.add(y);
			if (x.toString().equals(reverse(x.toString()))) {
				return false;
			}
		}
		return true;
	}

	static String reverse(String s) {
		if (s.length() < 2) {
			return s;
		}
		int m = s.length() / 2;
		return reverse(s.substring(m)) + reverse(s.substring(0, m));
	}
}
