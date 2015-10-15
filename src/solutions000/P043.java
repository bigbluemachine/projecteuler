package s000;

// Real ugly!
public class P043 {
	public static void main(String[] args) {
		for (int i = 6;; i++) {
			int z = 17 * i;
			if (z > 999) {
				break;
			}
			gen(Integer.toString(z), 0);
		}
		System.out.println(SUM);
	}

	static int[] P = { 13, 11, 7, 5, 3, 2 };
	static long SUM = 0;

	static void gen(String s, int i) {
		if (i >= P.length) {
			for (int d = 0; d <= 9; d++) {
				String t = d + s;
				if (allDiff(t)) {
					SUM += Long.parseLong(t);
				}
			}
			return;
		}
		for (int d = 0; d <= 9; d++) {
			String t = d + s;
			if (allDiff(t) && Integer.parseInt(t.substring(0, 3)) % P[i] == 0) {
				gen(t, i + 1);
			}
		}
	}

	static boolean allDiff(String s) {
		boolean[] v = new boolean[58];
		for (char c : s.toCharArray()) {
			if (v[c]) {
				return false;
			}
			v[c] = true;
		}
		return true;
	}
}
