package s100;

public class P159 {
	public static void main(String[] args) {
		int[] F = new int[1000000];
		for (int i = 2; i < 1000000; i++) {
			F[i] = i - 9 * ((i - 1) / 9);
		}

		for (int i = 2; i < 1000000; i++) {
			for (int j = i; j < 1000000; j += i) {
				F[j] = Math.max(F[j], F[i] + F[j / i]);
			}
		}

		long ans = 0;
		for (int i = 2; i < 1000000; i++) {
			ans += F[i];
		}
		System.out.println(ans);
	}
}
