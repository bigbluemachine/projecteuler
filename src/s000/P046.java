package s000;

import core.NTLib;

// Unproven bounds.
public class P046 {
	public static void main(String[] args) {
		boolean[] v = new boolean[1000000];
		boolean[] p = NTLib.simpleSieve(1000000);
		for (int i = 3; i < 1000000; i++) {
			if (p[i]) {
				for (int j = 1; i + 2 * j * j < 1000000; j++) {
					v[i + 2 * j * j] = true;
				}
			}
		}
		for (int i = 3; i < 1000000; i += 2) {
			if (!p[i] && !v[i]) {
				System.out.println(i);
				break;
			}
		}
	}
}
