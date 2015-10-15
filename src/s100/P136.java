package s100;

public class P136 {
	public static void main(String[] args) {
		int max = 50000000;
		int[] v = new int[max];
		for (int a = 2; a < max; a++) {
			for (int b = 1 + a / 4; b < a; b++) {
				int t = a * (4 * b - a);
				if (t >= max) {
					break;
				}
				v[t]++;
			}
		}

		int ans = 0;
		for (int i = 1; i < max; i++) {
			if (v[i] == 1) {
				ans++;
			}
		}
		System.out.println(ans);
	}
}
