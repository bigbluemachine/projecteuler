package s100;

import java.util.TreeSet;

import core.MathLib;
import core.Selector;

public class P106 {
	static boolean test(TreeSet<Integer> A, TreeSet<Integer> B) {
		boolean ab = A.last() > B.last();
		while (A.size() > 0) {
			int a = A.last();
			int b = B.last();
			if (ab ^ (a > b)) {
				return false;
			}
			A.remove(a);
			B.remove(b);
		}
		return true;
	}

	static int test(int n) {
		int ans = 0;
		Selector s = new Selector(n, n / 2);
		while (s.hasNext()) {
			long v = s.next();
			TreeSet<Integer> A = new TreeSet<Integer>();
			TreeSet<Integer> B = new TreeSet<Integer>();
			for (int i = 0; i < n; i++) {
				if ((v & (1 << i)) > 0) {
					A.add(i);
				} else {
					B.add(i);
				}
			}
			ans += test(A, B) ? 0 : 1;
		}
		return ans / 2;
	}

	static long C(int n, int k) {
		return MathLib.fac64(n) / (MathLib.fac64(k) * MathLib.fac64(n - k));
	}

	static long f(int n) {
		long ans = 0;
		for (int i = 4; i <= n; i += 2) {
			ans += C(n, i) * test(i);
		}
		return ans;
	}

	public static void main(String[] args) {
		System.out.println(f(12));
	}
}
