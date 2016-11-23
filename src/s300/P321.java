package s300;

public class P321 {
	public static void main(String[] args) {
		int N = 40;
		long[] T = new long[N];
		T[0] = 2;
		T[1] = 4;
		T[2] = 11;
		T[3] = 23;
		for (int i = 4; i < N; i++) {
			T[i] = 6 * T[i - 2] - T[i - 4];
		}

		long ans = 0;
		for (int i = 0; i < N; i++) {
			ans += T[i] - 1;
		}
		System.out.println(ans);
	}
}
