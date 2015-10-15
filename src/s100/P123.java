package s100;

import java.math.BigInteger;
import java.util.List;

import core.NTLib;

// Yet another hack...
public class P123 {
	public static void main(String[] args) {
		List<Integer> ps = NTLib.primeList(1000000);
		int n = 1;
		for (int p : ps) {
			long p2 = (long) p * p;
			long m = 0;
			BigInteger n_ = BigInteger.valueOf(n);
			BigInteger p2_ = BigInteger.valueOf(p2);
			m += BigInteger.valueOf(p - 1).modPow(n_, p2_).longValue();
			m += BigInteger.valueOf(p + 1).modPow(n_, p2_).longValue();
			m %= p2;
			if (m > 10000000000L) {
				System.out.println(n);
				break;
			}
			n++;
		}
	}
}
