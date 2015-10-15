package s000;

import java.math.BigInteger;

public class P057 {
	public static void main(String[] args) {
		BigInteger n = BigInteger.ONE;
		BigInteger d = BigInteger.ONE;
		int ans = 0;
		for (int i = 0; i < 1000; i++) {
			BigInteger t = n.add(d);
			n = d.add(t);
			d = t;
			if (n.toString().length() > d.toString().length()) {
				ans++;
			}
		}
		System.out.println(ans);
	}
}
