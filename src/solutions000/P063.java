package s000;

public class P063 {
	public static void main(String[] args) {
		int ans = 9;
		double L10 = Math.log(10);
		double L9 = Math.log(9);
		int max = (int) (L10 / (L10 - L9));
		for (int p = 2; p <= max; p++) {
			double q = Math.pow(10, (double) (p - 1) / p);
			int r = 1 + (int) q;
			ans += (9 - r + 1);
		}
		System.out.println(ans);
	}
}
