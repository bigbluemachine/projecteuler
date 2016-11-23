package s200;

import java.util.ArrayList;

import core.MathLib;
import core.NTLib;

public class P278 {
	static long lcm(long a, long b) {
		return (a * b) / MathLib.gcd64(a, b);
	}

	static long f(long p, long q, long r) {
		long lpq = lcm(p, q);
		long lpqr = lcm(lpq, r);
		long fpq = (p - 1) * (q - 1) - 1;
		return lpqr + fpq * r - p * q;
	}

	public static void main(String[] args) {
		ArrayList<Integer> ps = NTLib.primeList(5000);
		long ans = 0;
		for (int i = 0; i < ps.size(); i++) {
			int p = ps.get(i);
			for (int j = i + 1; j < ps.size(); j++) {
				int q = ps.get(j);
				for (int k = j + 1; k < ps.size(); k++) {
					int r = ps.get(k);
					ans += f(p, q, r);
				}
			}
		}
		System.out.println(ans);
	}
}
