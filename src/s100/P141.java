package s100;

public class P141 {
	public static void main(String[] args) {
		int M = 1000000;
		boolean[] SP = new boolean[M];
		int x, y, r, yx, d;

		for (x = 2; x < 1000; x++)
			for (y = 1, r = x * x; r < M; y++, r += x * x)
				for (yx = y * x, d = r + yx; d < M; d += yx) {
					long n = (1L * d * d * d) / r + r;
					int s = (int) Math.sqrt(n);
					if (s >= M)
						break;
					if (1L * s * s == n)
						SP[s] = true;
				}

		long sum = 9;
		for (int i = 4; i < M; i++)
			if (SP[i])
				sum += 1L * i * i;

		System.out.println(sum);
	}
}
