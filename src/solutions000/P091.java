package s000;

public class P091 {
	public static void main(String[] args) {
		int m = 50;
		m++;
		int n = m * m;
		int ans = 0;
		for (int i = 1; i < n; i++) {
			int x1 = i % m;
			int y1 = i / m;
			for (int j = i + 1; j < n; j++) {
				int x2 = j % m;
				int y2 = j / m;
				if (isRight(x1, y1, x2, y2)) {
					ans++;
				}
			}
		}
		System.out.println(ans);
	}

	static boolean isRight(int x1, int y1, int x2, int y2) {
		if (dot(x1, y1, x2, y2) == 0) {
			return true;
		}
		if (dot(-x1, -y1, x2 - x1, y2 - y1) == 0) {
			return true;
		}
		if (dot(x1 - x2, y1 - y2, -x2, -y2) == 0) {
			return true;
		}
		return false;
	}

	static int dot(int x1, int y1, int x2, int y2) {
		return x1 * x2 + y1 * y2;
	}
}
