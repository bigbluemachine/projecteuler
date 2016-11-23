package s500;

public class P571 {
	static void r(int[] v, int a, int b) {
		while (a < b) {
			int t = v[a];
			v[a] = v[b];
			v[b] = t;
			a++;
			b--;
		}
	}

	static boolean n(int[] v) {
		int l = v.length;
		for (int i = l - 2; i >= 0; i--) {
			if (v[i] < v[i + 1]) {
				r(v, i + 1, l - 1);
				int j = i + 1;
				while (v[i] >= v[j]) {
					j++;
				}
				int t = v[i];
				v[i] = v[j];
				v[j] = t;
				return true;
			}
		}
		return false;
	}

	static long i(int[] v, int b) {
		long ans = 0;
		for (int i : v) {
			ans = b * ans + i;
		}
		return ans;
	}

	static boolean p(long n, int b) {
		boolean[] v = new boolean[b];
		int count = 0;
		while (n > 0) {
			int m = (int) (n % b);
			if (!v[m]) {
				count++;
				if (count == b) {
					return true;
				}
				v[m] = true;
			}
			n /= b;
		}
		return false;
	}

	public static void main(String[] args) {
		int x = 12;

		int[] v = new int[x];
		for (int i = 0; i < x; i++) {
			v[i] = i;
		}
		v[0] = 1;
		v[1] = 0;

		long ans = 0;
		int count = 0;
		for (;;) {
			long n = i(v, x);
			boolean t = true;
			for (int b = x - 1; b >= 3; b--) {
				if (!p(n, b)) {
					t = false;
					break;
				}
			}
			if (t) {
				ans += n;
				count++;
				System.out.println(n);
				if (count == 10) {
					break;
				}
			}
			if (!n(v)) {
				break;
			}
		}
		System.out.println(ans);
	}
}
