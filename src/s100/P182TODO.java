package s100;

import java.util.ArrayList;

import core.MathLib;

public class P182TODO {
	public static void main(String[] args) {
		// TODO ~2min
		int p = 1009;
		int q = 3643;
		int n = p * q;
		int t = (p - 1) * (q - 1);

		ArrayList<Integer> div = new ArrayList<Integer>();
		for (int e = 1; e <= t; e++) {
			if (t % e == 0) {
				div.add(e);
			}
		}

		int[] f = new int[t];
		for (int m = 2; m < n; m++) {
			for (int e : div) {
				if (MathLib.modExp(m, e + 1, n) == m) {
					for (int ee = e + 1; ee < t; ee += e) {
						f[ee]++;
					}
					break;
				}
			}
		}

		int min = n;
		for (int e = 2; e < t; e++) {
			if (MathLib.gcd32(e, t) == 1) {
				min = Math.min(min, f[e]);
			}
		}

		long ans = 0;
		for (int e = 2; e < t; e++) {
			if (f[e] == min && MathLib.gcd32(e, t) == 1) {
				ans += e;
			}
		}
		System.out.println(ans);
	}
}
