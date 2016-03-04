package s100;

import java.math.BigInteger;
import java.util.HashMap;

public class P169 {
	static HashMap<BigInteger, BigInteger> T = new HashMap<BigInteger, BigInteger>();

	static BigInteger f(BigInteger n) {
		if (T.containsKey(n)) {
			return T.get(n);
		}
		if (n.testBit(0)) {
			return f(n.shiftRight(1));
		}
		BigInteger ans = f(n.shiftRight(1)).add(f(n.shiftRight(1).subtract(BigInteger.ONE)));
		T.put(n, ans);
		return ans;
	}

	public static void main(String[] args) {
		T.put(BigInteger.ZERO, BigInteger.ONE);
		T.put(BigInteger.ONE, BigInteger.ONE);
		System.out.println(f(new BigInteger("10000000000000000000000000")));
		System.out.println(T.size());
	}
}
