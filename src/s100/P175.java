package s100;

import java.util.LinkedList;

import core.MathLib;

public class P175 {
	static String n(long fn, long fn1) {
		LinkedList<Long> S = new LinkedList<Long>();
		while (fn != 0 && fn1 != 0) {
			if (fn > fn1) {
				S.addFirst(fn / fn1);
				fn %= fn1;
			} else {
				S.addFirst(fn1 / fn);
				fn1 %= fn;
			}
		}
		if (fn1 == 1) {
			long a = S.removeFirst();
			S.addFirst(a - 1);
			S.addFirst(1L);
		}
		String ans = "";
		for (long s : S) {
			ans += "," + s;
		}
		return ans.substring(1);
	}

	public static void main(String[] args) {
		long fn = 123456789;
		long fn1 = 987654321;
		long g = MathLib.gcd64(fn, fn1);
		fn /= g;
		fn1 /= g;
		System.out.println(n(fn, fn1));
	}
}
