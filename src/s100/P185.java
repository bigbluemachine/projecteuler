package s100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

import core.IOLib;
import core.Selector;

public class P185 {
	static int N;
	static ArrayList<Constraint> C = new ArrayList<Constraint>();

	static class Constraint implements Comparable<Constraint> {
		int[] c;
		int k;

		Constraint(int[] c, int k) {
			this.c = c;
			this.k = k;
		}

		@Override
		public int compareTo(Constraint q) {
			return k - q.k;
		}
	}

	static int[] restrict(int[] c, int[] d, long v) {
		for (int j = 0; j < N; j++) {
			if ((v & (1L << j)) > 0) {
				if ((d[j] & (1 << c[j])) == 0) {
					return null;
				}
				d[j] = 1 << c[j];
			} else {
				d[j] &= ~(1 << c[j]);
				if (d[j] == 0) {
					return null;
				}
			}
		}
		return d;
	}

	static ArrayList<int[]> gen(int i, int[] d) {
		ArrayList<int[]> ans = new ArrayList<int[]>();
		int k = C.get(i).k;
		if (k == 0) {
			int[] dd = restrict(C.get(i).c, d.clone(), 0);
			if (dd != null) {
				ans.add(dd);
			}
			return ans;
		}

		for (Selector s = new Selector(N, k); s.hasNext();) {
			int[] dd = restrict(C.get(i).c, d.clone(), s.next());
			if (dd != null) {
				ans.add(dd);
			}
		}
		return ans;
	}

	static boolean search(int i, int[] d) {
		if (i == C.size()) {
			String s = "";
			L: for (int j = 0; j < d.length; j++) {
				for (int x = 0; x <= 9; x++) {
					if (d[j] == 1 << x) {
						s += x;
						continue L;
					}
				}
				return false;
			}
			System.out.println(s);
			return true;
		}

		for (int[] child : gen(i, d)) {
			if (search(i + 1, child)) {
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		Scanner in = IOLib.scanner("data/185.txt");
		while (in.hasNextLine()) {
			String[] t = in.nextLine().split(" ");
			int[] c = new int[t[0].length()];
			N = c.length;
			for (int i = 0; i < N; i++) {
				c[i] = t[0].charAt(i) - '0';
			}
			C.add(new Constraint(c, Integer.parseInt(t[1].substring(1))));
		}
		in.close();

		Collections.sort(C);
		int[] d = new int[N];
		Arrays.fill(d, 1023);
		search(0, d);
	}
}
