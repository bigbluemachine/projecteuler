package s000;

import java.util.TreeSet;

import core.MathLib;

public class P074 {
	static int sf(int n) {
		int ans = 0;
		while (n > 0) {
			int d = n % 10;
			n /= 10;
			ans += MathLib.fac32(d);
		}
		return ans;
	}

	static int count(int[] A, int n) {
		TreeSet<Integer> S = new TreeSet<Integer>();
		do {
			S.add(n);
			n = A[n];
		} while (!S.contains(n));
		return S.size();
	}

	public static void main(String[] args) {
		int m = 7 * MathLib.fac32(9) + 1;
		int[] A = new int[m];
		for (int i = 1; i < m; i++) {
			A[i] = sf(i);
		}

		int ans = 0;
		for (int i = 1; i < 1000000; i++) {
			if (count(A, i) == 60) {
				ans++;
			}
		}
		System.out.println(ans);
	}
}
