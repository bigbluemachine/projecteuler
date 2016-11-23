package s300;

public class P301 {
	public static void main(String[] args) {
		int M = 1 << 30;
		int ans = 0;
		for (int n = 1; n <= M; n++) {
			if ((n ^ (2 * n) ^ (3 * n)) == 0) {
				ans++;
			}
		}
		System.out.println(ans);
	}
}
