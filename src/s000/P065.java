package s000;

import java.math.BigInteger;

public class P065 {
	public static void main(String[] args) {
		int k = 100;
		int[] a = new int[k];
		a[0] = 2;
		for (int i = 1; i < k; i++) {
			if (i % 3 == 2) {
				a[i] = 2 * (i / 3 + 1);
			} else {
				a[i] = 1;
			}
		}
		BigInteger n = BigInteger.valueOf(a[k - 1]);
		BigInteger d = BigInteger.ONE;
		for (int i = k - 2; i >= 0; i--) {
			BigInteger t = n;
			n = d;
			d = t;
			n = n.add(BigInteger.valueOf(a[i]).multiply(d));
			BigInteger g = n.gcd(d);
			n = n.divide(g);
			d = d.divide(g);
		}
		int ans = 0;
		while (n.signum() > 0) {
			ans += n.mod(BigInteger.TEN).intValue();
			n = n.divide(BigInteger.TEN);
		}
		System.out.println(ans);
	}
}
