package s100;

public class P137 {
	public static void main(String[] args) {
		// Let 0 < a < b, a < p * b where p = (sqrt(5) - 1) / 2.
		// Find a, b, s. t. n = ab / (b^2 - ab - a^2) is an integer.
		// Prove that this works :)
		long a = 1;
		long b = 2;
		for (int i = 1; i <= 15; i++) {
			long x = a * b;
			long y = b * b - x - a * a;
			System.out.printf("%d/%d; %d\n", a, b, x / y);
			a = a + b;
			b = a + b;
		}
	}
}
