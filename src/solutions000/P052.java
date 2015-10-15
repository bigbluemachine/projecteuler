package s000;

public class P052 {
	public static void main(String[] args) {
		for (int i = 10; i < 10000000; i *= 10) {
			int ii = 10 * i;
			for (int j = i; 6 * j < ii; j++) {
				if (eq(j, 2 * j) && eq(j, 3 * j) && eq(j, 4 * j) && eq(j, 5 * j) && eq(j, 6 * j)) {
					System.out.println(j);
					break;
				}
			}
		}
	}

	static boolean eq(int a, int b) {
		int[] v = new int[10];
		while (a > 0) {
			v[a % 10]++;
			v[b % 10]--;
			a /= 10;
			b /= 10;
		}
		for (int i : v) {
			if (i != 0) {
				return false;
			}
		}
		return true;
	}
}
