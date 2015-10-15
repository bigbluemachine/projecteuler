package s000;

public class P086 {
	static int count;

	static boolean test(int x, int y, int z) {
		int xy = x + y;
		int xz = x + z;
		int yz = y + z;
		int a2 = xy * xy + z * z;
		int b2 = xz * xz + y * y;
		int c2 = yz * yz + x * x;
		int m2 = Math.min(Math.min(a2, b2), c2);
		int m = (int) Math.sqrt(m2);
		return m * m == m2;
	}

	public static void main(String[] args) {
		// 1818
		count = 0;
		for (int z = 1;; z++) {
			for (int y = 1; y <= z; y++) {
				for (int x = 1; x <= y; x++) {
					if (test(x, y, z)) {
						count++;
					}
				}
			}

			if (count > 1000000) {
				System.out.println(z);
				break;
			}
		}
	}
}
