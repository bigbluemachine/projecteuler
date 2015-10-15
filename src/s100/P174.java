package s100;

public class P174 {
	public static void main(String[] args) {
		int M = 1000000;
		int[] v = new int[M + 1];
		for (int a = 3;; a++) {
			if (4 * (a - 1) > M) {
				break;
			}
			for (int x = 1; x + x < a; x++) {
				int n = 4 * x * (a - x);
				if (n > M) {
					break;
				}
				v[n]++;
			}
		}
		long ans = 0;
		for (int x : v) {
			if (1 <= x && x <= 10) {
				ans++;
			}
		}
		System.out.println(ans);
	}
}
