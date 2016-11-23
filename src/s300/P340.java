package s300;

import java.math.BigInteger;

import core.MathLib;

public class P340 {
	static BigInteger T(long n) {
		BigInteger a = BigInteger.valueOf(n);
		BigInteger b = BigInteger.valueOf(n + 1);
		return a.multiply(b).divide(BigInteger.valueOf(2));
	}

	public static void main(String[] args) {
		long a = MathLib.pow64(21, 7);
		long b = MathLib.pow64(7, 21);
		long c = MathLib.pow64(12, 7);

		long y = (b + 1) % a;
		long t = (b + 1) / a;

		BigInteger F = BigInteger.ZERO;
		F = F.add(BigInteger.valueOf(y).multiply(T(t)));
		F = F.add(BigInteger.valueOf(a - y).multiply(T(t - 1)));
		F = F.multiply(BigInteger.valueOf(3 * (a - c)));

		BigInteger Z = BigInteger.ZERO;
		Z = Z.add(BigInteger.valueOf(b + 4 * (a - c)).multiply(BigInteger.valueOf(b + 1)));
		Z = Z.subtract(BigInteger.valueOf(b / a).multiply(T(a - 1)));
		Z = Z.subtract(T(b % a));

		F = F.add(Z);
		System.out.println(F.mod(BigInteger.valueOf(1000000000)));
	}
}
