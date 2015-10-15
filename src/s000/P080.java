package s000;

import java.math.BigInteger;

public class P080 {
	public static void main(String[] args) {
		int ans = 0;
		for (int i = 1; i <= 100; i++) {
			int s = (int) Math.sqrt(i);
			if (s * s == i) {
				continue;
			}
			ans += f(i);
		}
		System.out.println(ans);
	}

	static int f(int n) {
		BigInteger z = BigInteger.valueOf(n).multiply(BigInteger.TEN.pow(200));
		BigInteger s = search(z);
		char[] c = s.toString().toCharArray();
		int ans = 0;
		for (int i = 0; i < 100; i++) {
			ans += (c[i] - '0');
		}
		return ans;
	}

	static BigInteger search(BigInteger n) {
		BigInteger lo = BigInteger.valueOf(2);
		BigInteger hi = n.shiftRight(1);
		do {
			BigInteger mid = lo.add(hi).shiftRight(1);
			if (mid.pow(2).compareTo(n) > 0) {
				hi = mid;
			} else {
				lo = mid;
			}
		} while (hi.subtract(lo).compareTo(BigInteger.ONE) > 0);
		return lo;
	}
}
