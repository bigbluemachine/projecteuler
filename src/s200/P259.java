package s200;

import java.util.TreeSet;

public class P259 {
	static class F implements Comparable<F> {
		int n, d;

		F(int N) {
			n = N;
			d = 1;
		}

		F(int N, int D) {
			if (D < 0) {
				n = -N;
				d = -D;
			} else {
				n = N;
				d = D;
			}
		}

		F add(F other) {
			return new F(n * other.d + d * other.n, d * other.d);
		}

		F sub(F other) {
			return new F(n * other.d - d * other.n, d * other.d);
		}

		F mul(F other) {
			return new F(n * other.n, d * other.d);
		}

		F div(F other) {
			return new F(n * other.d, d * other.n);
		}

		public String toString() {
			return n + "/" + d;
		}

		public int compareTo(F other) {
			return (int) (n * other.d - d * other.n);
		}
	}

	static class C {
		TreeSet<F> a = new TreeSet<F>();

		C(F initial) {
			a.add(initial);
		}

		void add(F f) {
			a.add(f);
		}
	}

	static C[] cs = new C[90];

	static int index(int a, int b) {
		return a == b ? a : 10 * a + b;
	}

	static int concat(int from, int to) {
		int result = to, base = 10;
		while (to != from) {
			to--;
			result += base * to;
			base *= 10;
		}
		return result;
	}

	static void eval(F f) {
		int n = f.n, d = f.d;
		if (n <= 0)
			return;
		if (n % d == 0)
			reachable.add(n / d);
	}

	static TreeSet<Integer> reachable = new TreeSet<Integer>();

	public static void main(String[] args) {
		for (int a = 1; a <= 9; a++)
			cs[a] = new C(new F(a));

		for (int d = 1; d <= 7; d++)
			for (int a = 1, b = a + d; b <= 9; a++, b++) {
				C c = cs[index(a, b)] = new C(new F(concat(a, b)));
				for (int mid = a; mid < b; mid++)
					for (F l : cs[index(a, mid)].a)
						for (F r : cs[index(mid + 1, b)].a) {
							c.add(l.add(r));
							c.add(l.sub(r));
							c.add(l.mul(r));
							if (r.n != 0)
								c.add(l.div(r));
						}
			}

		for (int mid = 1; mid < 9; mid++)
			for (F l : cs[index(1, mid)].a)
				for (F r : cs[index(mid + 1, 9)].a) {
					eval(l.add(r));
					eval(l.sub(r));
					eval(l.mul(r));
					if (r.n != 0)
						eval(l.div(r));
				}

		long sum = 123456789;
		for (int i : reachable)
			sum += i;
		System.out.println(sum);
	}
}
