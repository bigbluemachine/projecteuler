package s200;

public class P207 {
	public static void main(String[] args) {
		// Derivation: for z = 2, 3, 4, ... let k = z(z - 1).
		// A partition is perfect if z is a power of 2.

		int n = 0;
		int d = 0;
		for (int z = 2;; z++) {
			if ((z & (z - 1)) == 0) {
				n++;
			}
			d++;
			if (12345 * n < d) {
				System.out.println((long) z * (z - 1));
				break;
			}
		}
	}
}
