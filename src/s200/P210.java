package s200;

public class P210 {
	public static void main(String[] args) {
		long r = 1000000000L;
		long q = r / 4;
		long outer = 2 * r * (r - q);
		long inner = 2 * ((q * (q + 1)) / 2 - 1);
		long half = q / 2;
		long r2 = 2 * half * half;
		long fr = (long) (Math.sqrt(2) * half);

		for (long x = half + 1; x <= fr; x++) {
			long y = (long) Math.ceil(Math.sqrt(r2 - x * x)) - 1;
			inner += 4 * (2 * y + 1);
		}

		System.out.println(outer + inner);
	}
}
