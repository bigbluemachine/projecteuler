package s100;

import java.util.HashSet;

public class P109 {
	static class D {
		String a, b, c;

		public D(String a, String b, String c) {
			this.c = c;
			if (a != null && b != null) {
				if (a.compareTo(b) > 0) {
					this.a = a;
					this.b = b;
				} else {
					this.a = b;
					this.b = a;
				}
			} else {
				this.a = a;
				this.b = b;
			}
		}

		public int score() {
			int ans = 0;
			if (a != null) {
				ans += sc(a);
			}
			if (b != null) {
				ans += sc(b);
			}
			if (c != null) {
				ans += sc(c);
			}
			return ans;
		}

		int sc(String x) {
			if (x.charAt(0) == 'T') {
				return 3 * Integer.parseInt(x.substring(1));
			}
			if (x.charAt(0) == 'D') {
				return 2 * Integer.parseInt(x.substring(1));
			}
			return Integer.parseInt(x.substring(1));
		}

		public boolean equals(Object o) {
			D d = (D) o;
			return eq(a, d.a) && eq(b, d.b) && eq(c, d.c);
		}

		public int hashCode() {
			return hc(a) ^ hc(b) ^ hc(c);
		}

		static boolean eq(String x, String y) {
			if (x == null) {
				return y == null;
			}
			return x.equals(y);
		}

		static int hc(String x) {
			if (x == null) {
				return 0;
			}
			return x.hashCode();
		}
	}

	static void gen(String[] s, int n, HashSet<D> S) {
		if (n == 3) {
			if (s[2].charAt(0) == 'D') {
				S.add(new D(s[0], s[1], s[2]));
			}
			return;
		}
		if (n == 1) {
			if (s[0].charAt(0) == 'D') {
				S.add(new D(null, null, s[0]));
			}
		}
		if (n == 2) {
			if (s[1].charAt(0) == 'D') {
				S.add(new D(s[0], null, s[1]));
			}
		}

		for (int i = 1; i <= 20; i++) {
			s[n] = "S" + i;
			gen(s, n + 1, S);
			s[n] = "D" + i;
			gen(s, n + 1, S);
			s[n] = "T" + i;
			gen(s, n + 1, S);
		}
		s[n] = "S25";
		gen(s, n + 1, S);
		s[n] = "D25";
		gen(s, n + 1, S);
	}

	public static void main(String[] args) {
		HashSet<D> S = new HashSet<D>();
		gen(new String[3], 0, S);

		int ans = 0;
		for (D d : S) {
			if (d.score() < 100) {
				ans++;
			}
		}
		System.out.println(ans);
	}
}
