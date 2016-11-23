package s500;

import java.util.PriorityQueue;

import core.MathLib;
import core.PList;

public class P500 {
	static PList P = new PList();

	static class B implements Comparable<B> {
		int i;
		long e;
		int p;

		B(int i, long e) {
			this.i = i;
			this.e = e;
			this.p = P.get(i);
		}

		@Override
		public int compareTo(B other) {
			return Double.compare(Math.log(p) * e, Math.log(other.p) * other.e);
		}
	}

	public static void main(String[] args) {
		PriorityQueue<B> Q = new PriorityQueue<B>();
		Q.add(new B(0, 2));
		Q.add(new B(1, 1));
		int maxIndex = 0;

		long ans = 2;
		for (int i = 1; i < 500500; i++) {
			B next = Q.remove();
			Q.add(new B(next.i, 2 * next.e));
			if (next.i > maxIndex) {
				maxIndex = next.i;
				Q.add(new B(maxIndex + 1, 1));
			}
			ans = (ans * MathLib.modExp(next.p, next.e, 500500507)) % 500500507;
		}
		System.out.println(ans);
	}
}
