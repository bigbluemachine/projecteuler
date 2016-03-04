package s200;

import java.util.TreeSet;

public class P225 {
	public static void main(String[] args) {
		int n = 124;
		int count = 0;

		for (int m = 7;; m += 2) {
			int a = 1, b = 1, c = 1;

			TreeSet<Integer> V = new TreeSet<Integer>();
			while (true) {
				int k = a * m * m + b * m + c;
				if (V.contains(k)) {
					if (++count == n) {
						System.out.println(m);
						return;
					}
					break;
				}
				V.add(k);

				int t = (a + b + c) % m;
				if (t == 0) {
					break;
				}

				a = b;
				b = c;
				c = t;
			}
		}
	}
}
