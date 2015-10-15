package s100;

public class P112 {
	public static void main(String[] args) {
		int n = 0;
		for (int i = 1;; i++) {
			if (bouncy(i)) {
				n++;
			}
			if (100 * n == 99 * i) {
				System.out.println(i);
				break;
			}
		}
	}

	static boolean bouncy(int n) {
		boolean inc = false;
		boolean dec = false;
		int last = n % 10;
		n /= 10;
		while (n > 0) {
			int d = n % 10;
			if (d > last) {
				inc = true;
			} else if (d < last) {
				dec = true;
			}
			n /= 10;
			last = d;
		}
		return inc && dec;
	}
}
