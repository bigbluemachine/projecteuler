package s100;

public class P154 {
	static int[] C2 = new int[200001];
	static int[] C5 = new int[200001];

	static void f(int r, int[] a2, int[] a5, int n) {
		for (int i = 1; i < n; i++) {
			a2[i] = a2[i - 1] + C2[r - i + 1] - C2[i];
			a5[i] = a5[i - 1] + C5[r - i + 1] - C5[i];
		}
	}

	public static void main(String[] args) {
		for (int i = 2; i <= 200000; i *= 2) {
			for (int j = i; j <= 200000; j += i) {
				C2[j]++;
			}
		}
		for (int i = 5; i <= 200000; i *= 5) {
			for (int j = i; j <= 200000; j += i) {
				C5[j]++;
			}
		}

		// Special case: k is a multiple of 3
		int k = 200000;
		int[] c2 = new int[k + 1];
		int[] c5 = new int[k + 1];
		f(k, c2, c5, k + 1);

		long ans = 0;
		for (int r = k, s = k; k >= 1; r--, s -= 2, k -= 3) {
			int[] t2 = new int[s];
			int[] t5 = new int[s];
			t2[0] = c2[r];
			t5[0] = c5[r];
			f(r, t2, t5, s);
			for (int i = s - k; i < s; i++) {
				if (t2[i] >= 12 && t5[i] >= 12) {
					ans += 3;
				}
			}
		}
		System.out.println(ans);
	}
}
