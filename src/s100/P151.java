package s100;

public class P151 {
	static double f(int[] v) {
		int total = 0;
		for (int i : v) {
			total += i;
		}

		if (total == 1 && v[5] == 1) {
			return 0;
		}
		double ans = total == 1 ? 1 : 0;

		if (v[5] > 0) {
			int[] w = v.clone();
			w[5]--;
			ans += ((double) v[5] / total) * f(w);
		}

		for (int i = 2; i <= 4; i++) {
			if (v[i] > 0) {
				int[] w = v.clone();
				w[i]--;
				for (int j = i + 1; j <= 5; j++) {
					w[j]++;
				}
				ans += ((double) v[i] / total) * f(w);
			}
		}

		return ans;
	}

	public static void main(String[] args) {
		int[] v = { 0, 0, 1, 1, 1, 1 };
		System.out.println(f(v));
	}
}
