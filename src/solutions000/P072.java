package s000;

import core.NTLib;

public class P072 {
	public static void main(String[] args) {
		int[] t = NTLib.totient(1000001);
		long ans = 0;
		for (int d = 2; d <= 1000000; d++) {
			ans += t[d];
		}
		System.out.println(ans);
	}
}
