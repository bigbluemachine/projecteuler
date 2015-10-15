package s100;

public class P120 {
	public static void main(String[] args) {
		long ans = 0;
		for (int a = 3; a <= 1000; a++) {
			long best = 0;
			int a2 = a * a;
			long p = a - 1, q = a + 1;
			for (int n = 1; n <= a2; n++) {
				long m = (p + q) % a2;
				best = Math.max(best, m);
				p = (p * (a - 1)) % a2;
				q = (q * (a + 1)) % a2;
			}
			ans += best;
		}
		System.out.println(ans);
	}
}
