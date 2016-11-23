package s500;

import java.math.BigInteger;

import core.EEA;
import core.NTLib;

public class P531 {
	public static void main(String[] args) {
		int[] t = NTLib.totient(1005000);
		BigInteger ans = BigInteger.ZERO;
		for (int n = 1000000; n < 1005000; n++) {
			for (int m = n + 1; m < 1005000; m++) {
				ans = ans.add(EEA.crt(n, t[n], m, t[m]));
			}
		}
		System.out.println(ans);
	}
}
