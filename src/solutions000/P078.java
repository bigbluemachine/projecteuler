package s000;

public class P078 {
	public static void main(String[] args) {
		int[] P = partition(1000000);
		for (int i = 1; i < 1000000; i++) {
			if (P[i] == 0) {
				System.out.println(i);
				break;
			}
		}
	}

	public static int[] partition(int max) {
		int[] pent = new int[max];
		int[] part = new int[max];

		boolean a, b;
		int i, j;
		for (a = false, i = 1, j = 0; j < max; i = a ? 1 - i : -i, a = !a) {
			pent[j++] = i * (3 * i - 1) / 2;
		}

		int k;
		part[0] = part[1] = 1;
		for (i = 2; i < max; part[i++] = k) {
			for (a = b = false, j = 0, k = 0; pent[j] <= i; j++, b ^= a, a = !a) {
				if (b) {
					k = (k - part[i - pent[j]]) % 1000000;
				} else {
					k = (k + part[i - pent[j]]) % 1000000;
				}
			}
		}

		return part;
	}
}
