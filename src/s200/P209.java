package s200;

public class P209 {
	static int f(int i) {
		boolean a = (i & 32) > 0;
		boolean b = (i & 16) > 0;
		boolean c = (i & 8) > 0;
		int j = (i << 1) & 63;
		if (a ^ (b & c)) {
			j |= 1;
		}
		return j;
	}

	static void merge(int[] v, int a, int b) {
		if (v[a] == v[b]) {
			return;
		}
		int aa = v[a];
		int bb = v[b];
		for (int i = 0; i < 64; i++) {
			if (v[i] == bb) {
				v[i] = aa;
			}
		}
	}

	static long lucas(int n, long a, long b) {
		if (n == 1) {
			return a;
		}
		return lucas(n - 1, b, a + b);
	}

	static long lucas(int n) {
		return lucas(n, 1, 3);
	}

	public static void main(String[] args) {
		int[] v = new int[64];
		for (int i = 0; i < 64; i++) {
			v[i] = i;
		}
		for (int i = 0; i < 64; i++) {
			merge(v, i, f(i));
		}
		long ans = 1;
		for (int i = 0; i < 64; i++) {
			if (v[i] == -1) {
				continue;
			}
			int n = 0;
			int k = v[i];
			for (int j = 0; j < 64; j++) {
				if (v[j] == k) {
					n++;
					v[j] = -1;
				}
			}
			ans *= lucas(n);
		}
		System.out.println(ans);
	}
}
