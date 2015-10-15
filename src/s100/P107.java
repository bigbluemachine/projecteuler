package s100;

import java.util.Scanner;
import java.util.TreeSet;

import core.IOLib;

public class P107 {
	static class E implements Comparable<E> {
		int u, v, w;

		E(int u, int v, int w) {
			this.u = u;
			this.v = v;
			this.w = w;
		}

		@Override
		public int compareTo(E e) {
			if (w != e.w) {
				return w - e.w;
			}
			if (v != e.v) {
				return v - e.v;
			}
			return u - e.u;
		}
	}

	static class Q {
		TreeSet<Integer> E = new TreeSet<Integer>();
	}

	static void union(Q[] qs, int i, int j) {
		if (i > j) {
			int t = i;
			i = j;
			j = t;
		}
		qs[i].E.addAll(qs[j].E);
		qs[j].E.clear();
	}

	public static void main(String[] args) {
		int X = 40;
		Integer[][] M = new Integer[X][X];

		Scanner in = IOLib.scanner("data/107.txt");
		{
			int i = 0;
			int j = 0;
			while (in.hasNextLine()) {
				String line = in.nextLine();
				String[] tokens = line.split(",");
				j = 0;
				for (String t : tokens) {
					if (!t.equals("-")) {
						M[i][j] = Integer.parseInt(t);
					}
					j++;
				}
				i++;
			}
		}
		in.close();

		int total = 0;
		TreeSet<E> es = new TreeSet<E>();
		for (int i = 0; i < X; i++) {
			for (int j = 0; j < X; j++) {
				if (M[i][j] != null) {
					es.add(new E(i, j, M[i][j]));
					total += M[i][j];
				}
			}
		}
		total /= 2;

		Q[] qs = new Q[X];
		for (int i = 0; i < X; i++) {
			qs[i] = new Q();
			qs[i].E.add(i);
		}

		TreeSet<E> A = new TreeSet<E>();
		for (E e : es) {
			int u = e.u, v = e.v;
			int ui = -1, vi = -1;
			for (int i = 0; i < qs.length; i++) {
				if (qs[i].E.contains(u)) {
					ui = i;
				}
				if (qs[i].E.contains(v)) {
					vi = i;
				}
			}
			if (ui != vi) {
				A.add(e);
				union(qs, ui, vi);
			}
		}

		int ans = 0;
		for (E e : A) {
			ans += e.w;
		}
		System.out.println(total - ans);
	}
}
