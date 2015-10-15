package s100;

public class P193 {
	// 684465067343069
	public static void main(String[] args) {
		int m = 1 << 25;
		int[] a = new int[m];
		long x = (long) m * m - 1;
		long ans = x;

		for (int i = 2; i < m; i++) {
			ans += (a[i] - 1) * (x / ((long) i * i));

			if (a[i] == 0) {
				for (int j = i + i; j < m; j += i) {
					a[j]++;
				}
			} else if (a[i] > 1) {
				for (int j = i + i; j < m; j += i) {
					a[j]--;
				}
			}
		}

		System.out.println(ans);
	}
}
