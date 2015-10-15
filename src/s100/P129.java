package s100;

public class P129 {
	static int z(int n) {
		int x = 1;
		int j = 0;
		int ans = 0;
		do {
			j = (j + x) % n;
			x = (10 * x) % n;
			ans++;
		} while (j != 0);
		return ans;
	}

	public static void main(String[] args) {
		for (int i = 999999;;) {
			int z = z(i);
			if (z > 1000000) {
				System.out.printf("z(%d) = %d\n", i, z);
				break;
			}
			i += i % 10 == 3 ? 4 : 2;
		}
	}
}
