package s200;

public class P222 {
	static <T> T println(T t) {
		System.out.println(t);
		return t;
	}

	static int F(int last, int v) {
		if (F[last][v] > 0)
			return F[last][v];

		if ((v & (v - 1)) == 0) {
			int i = 0;
			while (v > 1 << i)
				i++;
			return F[last][v] = (int) ((30 + i + Math.sqrt(200 * (last + i + 10))) * 100000);
		}

		int best = Integer.MAX_VALUE;
		for (int i = 0; i < 21; i++)
			if ((v & (1 << i)) > 0)
				best = Math.min(best, (int) (Math.sqrt(2e12 * (last + i + 10))) + F(i, v & ~(1 << i)));

		return F[last][v] = best;
	}

	static int[][] F;

	public static void main(String[] args) {
		F = new int[21][1 << 21];

		int best = Integer.MAX_VALUE;
		int v = (1 << 21) - 1;
		for (int i = 0; i < 21; i++)
			best = Math.min(best, (30 + i) * 100000 + F(i, v & ~(1 << i)));

		println(best / 100.0);
	}
}
