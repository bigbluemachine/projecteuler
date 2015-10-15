package s100;

import java.util.List;

import core.MathLib;
import core.NTLib;

// HACK!
public class P132 {
	public static void main(String[] args) {
		List<Integer> ps = NTLib.primeList(1000000);
		int e = 1000000000;
		int n = 40;
		int k = 0;
		int ans = 0;
		for (int p : ps) {
			if (p == 3) {
				continue;
			}
			if (MathLib.modExp(10, e, p) == 1) {
				ans += p;
				if (++k == n) {
					break;
				}
			}
		}
		System.out.println(ans);
	}
}
