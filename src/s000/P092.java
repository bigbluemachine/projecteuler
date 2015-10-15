package s000;

public class P092 {
	public static void main(String[] args) {
		int[] a = new int[10000000];
		a[1] = 1;
		a[89] = 89;
		int ans = 0;
		for (int i = 1; i < 10000000; i++) {
			if (typeof(i, a) == 89) {
				ans++;
			}
		}
		System.out.println(ans);
	}

	static int typeof(int n, int[] a) {
		if (a[n] == 0) {
			int next = 0;
			int m = n;
			while (m > 0) {
				int d = m % 10;
				next += d * d;
				m /= 10;
			}
			a[n] = typeof(next, a);
		}
		return a[n];
	}
}
