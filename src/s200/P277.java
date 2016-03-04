package s200;

import java.math.BigInteger;

public class P277 {
	static BigInteger big(long l) {
		return BigInteger.valueOf(l);
	}

	public static void main(String[] args) {
		char[] c = "UDDDUdddDDUDDddDdDddDDUDDdUUDd".toCharArray();
		long a = 1, b = 0, d = 1;

		for (int i = c.length; i-- > 0;)
			switch (c[i]) {
			case 'D':
				a += a + a;
				b += b + b;
				break;
			case 'd':
				a += a + a;
				b += b + b + d;
				d += d;
				break;
			case 'U':
				a += a + a;
				b += b + b - d - d;
				d += d;
				d += d;
				break;
			}

		BigInteger M = big(1000000000000000L);
		BigInteger A = big(a), D = big(d), N = big(a + b);
		while (true)
			if (N.mod(D).signum() == 0 && N.divide(D).compareTo(M) > 0)
				break;
			else
				N = N.add(A);

		System.out.println(N.divide(D));
	}
}