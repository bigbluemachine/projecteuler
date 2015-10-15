package s000;

import core.NTLib;

public class P058 {
	public static void main(String[] args) {
		int count = 0;
		int total = 1;
		for (int n = 2;; n++) {
			int s = 2 * n - 1;
			int s2 = s * s;

			total += 4;
			count += NTLib.MillerRabin(s2 - (s - 1), 5) ? 1 : 0;
			count += NTLib.MillerRabin(s2 - 2 * (s - 1), 5) ? 1 : 0;
			count += NTLib.MillerRabin(s2 - 3 * (s - 1), 5) ? 1 : 0;

			if (100 * count < 10 * total) {
				System.out.println(count + "/" + total);
				System.out.println(s);
				break;
			}
		}
	}
}
