package s000;

public class P076 {
	public static void main(String[] args) {
		long[] P = partition(101);
		System.out.println(P[100] - 1);
	}

	public static long[] partition(int max) {
		int[] pent = new int[max];
		long[] part = new long[max];

		boolean a, b;
		int i, j;
		for (a = b = false, i = 1, j = 0; j < max; i = a ? 1 - i : -i, a = !a)
			pent[j++] = i * (3 * i - 1) / 2;

		long k;
		part[0] = part[1] = 1;
		for (i = 2; i < max; part[i++] = k)
			for (a = b = false, j = 0, k = 0; pent[j] <= i; j++, b ^= a, a = !a)
				if (b)
					k -= part[i - pent[j]];
				else
					k += part[i - pent[j]];

		return part;
	}
}
