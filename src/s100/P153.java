package s100;

import java.util.HashMap;

import core.MathLib;

public class P153 {
	static int max = 100000000, s = (int) Math.sqrt(max);
	static HashMap<Integer, Long> DIV = new HashMap<Integer, Long>();
	static long ans = 0;

	static long div(int x) {
		if (DIV.containsKey(x))
			return DIV.get(x);

		long r = (long) x * x;
		for (int i = 2; i < x; i++)
			r -= x % i;

		DIV.put(x, r);
		return r;
	}

	public static void main(String[] args) {
		for (int a = 2; a <= s; a++) {
			int a2 = a * a, upper = Math.min(a - 1, (int) Math.sqrt(max - a2));
			for (int b = 1; b <= upper; b++)
				if (MathLib.gcd32(a, b) == 1)
					ans += div(max / (a2 + b * b)) * (a + b);
		}

		ans += div(max / 2);
		ans <<= 1;
		ans += div(max);

		System.out.println(ans);
	}
}
