package s300;

import java.util.TreeSet;

public class P305 {
	private static TreeSet<Long> TENS;
	private TreeSet<Long> S;
	private long bound;
	private int maxCount;

	public P305() {
		TENS = new TreeSet<Long>();
		for (long p = 1; p > 0; p *= 10) {
			TENS.add(p);
		}
	}

	public void run() {
		long ans = 0;
		for (int k = 1, n = 3; k <= 13; k++, n *= 3) {
			long f = f(n);
			ans += f;

			System.out.printf("f(%d) = %d\n", n, f);
		}
		System.out.println(ans);
	}

	public long f(int n) {
		search(n);
		String t = Integer.toString(n);
		int count = 0;
		for (long l : S) {
			String s = Long.toString(l);
			int index = 0;
			boolean found = false;
			for (;;) {
				index = s.indexOf(t, index);
				if (index == -1) {
					break;
				}
				found = true;
				if (++count == n) {
					return indexOf(l) + index;
				}
				index++;
			}
			if (!found && ++count == n) {
				String u = Long.toString(l - 1) + s;
				return indexOf(l - 1) + u.indexOf(t);
			}
		}
		throw new Error();
	}

	private void add(long n) {
		S.add(n);
		if (S.size() > maxCount) {
			S.remove(S.last());
			bound = S.last();
		}
	}

	private static long indexOf(long n) {
		if (n <= 10) {
			return n;
		}
		long g = 10;
		int d = 2;
		while (10 * g <= n) {
			g *= 10;
			d++;
		}
		long ans = d * (n - g);
		long p = 1;
		for (int i = 1; i < d; i++) {
			ans += 9 * p * i;
			p *= 10;
		}
		return ans + 1;
	}

	private static long concat(int a, int b) {
		return 10L * a * TENS.floor((long) b) + b;
	}

	private void overlapSearch(String s, String t) {
		int maxLen = s.length() + t.length() - 1;
		int minLen = Math.max(s.length(), t.length());

		L: for (int l = minLen; l <= maxLen; l++) {
			char[] c = new char[l];
			char[] d = new char[l];

			for (int i = 0; i < s.length(); i++) {
				c[i] = s.charAt(i);
			}
			for (int i = 0; i < t.length(); i++) {
				d[l - 1 - i] = t.charAt(t.length() - 1 - i);
			}

			for (int i = 0; i < l; i++) {
				if (c[i] != 0 && d[i] != 0 && c[i] != d[i]) {
					continue L;
				}
				if (c[i] == 0) {
					c[i] = d[i];
				}
			}

			add(Long.parseLong(new String(c)));
		}
	}

	private void splitSearch(String head, String tail) {
		add(Long.parseLong(head + tail));

		L: for (long m = 10;; m *= 10) {
			for (long a = 0; a < m; a++) {
				String u = Long.toString(m + a).substring(1);
				long x = Long.parseLong(head + u + tail);
				if (x > bound) {
					break L;
				}
				add(x);
			}
		}

		overlapSearch(head, tail);
	}

	private void search(int n) {
		S = new TreeSet<Long>();
		bound = concat(n, n);
		maxCount = n;

		// append head
		for (int i = 0;; i++) {
			long x = concat(i, n);
			if (x > bound) {
				break;
			}
			add(x);

			// and tail
			for (long m = 1;; m *= 10) {
				long y = x * m;
				if (y > bound) {
					break;
				}
				for (long a = 0; a < m; a++) {
					long z = y + a;
					if (z > bound) {
						break;
					}
					add(z);
				}
			}
		}

		// split
		String s = Long.toString(n);
		for (int i = 1; i < s.length(); i++) {
			String b = s.substring(i);
			if (b.charAt(0) == '0') {
				continue;
			}
			String a = s.substring(0, i);
			int inc = Integer.parseInt(a) + 1;
			if (TENS.contains((long) inc)) {
				a = Integer.toString(inc).substring(1);
			} else {
				a = Integer.toString(inc);
			}
			splitSearch(b, a);
		}
	}

	public static void main(String[] args) {
		new P305().run();
	}
}
