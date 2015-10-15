package s000;

public class P085 {
	static long f(int w, int h) {
		long ans = 0;
		for (int i = 1; i <= w; i++) {
			for (int j = 1; j <= h; j++) {
				ans += (w - i + 1) * (h - j + 1);
			}
		}
		return ans;
	}

	static int search(int w, long m) {
		long p = 0, q = 0;
		int ans = 0;
		for (int h = 1; h <= w; h++) {
			p = q;
			q = f(w, h);
			if (q > m) {
				ans = h;
				break;
			}
		}
		if (ans == 0) {
			return -1;
		}
		p = Math.abs(m - p);
		q = Math.abs(m - p);
		return p <= q ? ans - 1 : ans;
	}

	public static void main(String[] args) {
		int bestW = 0, bestH = 0;
		long bestDiff = Long.MAX_VALUE;
		for (int w = 1; w <= 2000; w++) {
			int h = search(w, 2000000);
			if (h == -1) {
				continue;
			}
			long r = f(w, h);
			long diff = Math.abs(2000000 - r);
			if (diff < bestDiff) {
				bestW = w;
				bestH = h;
				bestDiff = diff;
			}
		}
		System.out.println(bestW * bestH);
	}
}
