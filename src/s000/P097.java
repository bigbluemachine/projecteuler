package s000;

import java.math.BigInteger;

public class P097 {
	public static void main(String[] args) {
		long ans = BigInteger.valueOf(2).modPow(BigInteger.valueOf(7830457), BigInteger.valueOf(10000000000L))
				.longValue();
		ans = (ans * 28433 + 1) % 10000000000L;
		System.out.println(ans);
	}
}
