package s300;

public class P349 {
	static final long X = 1000000000000000000L;

	public static void main(String[] args) {
		boolean[][] Z = new boolean[256][256];
		int x = 127;
		int y = 127;
		int d = 0;

		int[] K = new int[12000];
		int count = 0;
		for (int i = 0; i < 12000; i++) {
			K[i] = count;
			if (Z[x][y]) {
				Z[x][y] = false;
				d = (d + 3) % 4;
				count--;
			} else {
				Z[x][y] = true;
				d = (d + 1) % 4;
				count++;
			}
			if (d == 0) {
				x++;
			} else if (d == 1) {
				y--;
			} else if (d == 2) {
				x--;
			} else {
				y++;
			}
		}

		// Number of black cells is periodic (104) after ~10000 steps.
		int m = (int) (X % 104);
		while (m + 104 < K.length) {
			m += 104;
		}

		long t = (X - m) / 104;
		long ans = K[m] + t * (K[m] - K[m - 104]);
		System.out.println(ans);
	}
}
