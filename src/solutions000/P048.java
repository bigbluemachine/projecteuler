package s000;

import java.math.BigInteger;

public class P048 {
	public static void main(String[] args) {
		long m = 10000000000L;
		long ans = 0;
		for (int i = 1; i <= 1000; i++) {
			ans += BigInteger.valueOf(i).modPow(BigInteger.valueOf(i), BigInteger.valueOf(m)).longValue();
			ans %= m;
		}
		System.out.println(ans);
	}
}
