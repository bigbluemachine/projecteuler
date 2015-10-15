package s100;

public class P138 {
	public static void main(String[] args) {
		long ans = 0;
		long m = 1;
		long n = 4;
		for (int i = 0; i < 12; i++) {
			long L = n * n + m * m;
			ans += L;
			long t = 4 * n + m;
			m = n;
			n = t;
		}
		System.out.println(ans);
	}
}
