package s000;

import java.math.BigInteger;

public class P056 {
	public static void main(String[] args) {
		int ans = 0;
		for (int a = 2; a < 100; a++) {
			for (int b = 2; b < 100; b++) {
				BigInteger x = BigInteger.valueOf(a).pow(b);
				int sum = 0;
				for (char c : x.toString().toCharArray()) {
					sum += c - '0';
				}
				ans = Math.max(ans, sum);
			}
		}
		System.out.println(ans);
	}
}
