package core;

import java.util.ArrayList;
import java.util.Iterator;

public class PList implements Iterable<Integer> {
	private static final int MAX_INTERVAL = 1000000;
	public final ArrayList<Integer> ps;

	public PList() {
		ps = new ArrayList<Integer>();
		ps.add(2);
		ps.add(3);
	}

	public void expand() {
		int last = getLast();
		long max = (long) last * (last + 2);
		int m = last + 2;
		int n = (int) Math.min(MAX_INTERVAL, max - m);

		boolean[] p = NTLib.offsetSieve(m, n, ps);
		for (int i = 0; i < n; i++) {
			if (p[i]) {
				ps.add(m + i);
			}
		}
	}

	public int size() {
		return ps.size();
	}

	public int getLast() {
		return ps.get(size() - 1);
	}

	public int get(int n) {
		while (size() <= n) {
			expand();
		}
		return ps.get(n);
	}

	public int count(int n) {
		while (getLast() < n) {
			expand();
		}
		int i = 0;
		for (int p : ps) {
			if (p >= n) {
				break;
			}
			i++;
		}
		return i;
	}

	@Override
	public Iterator<Integer> iterator() {
		return ps.iterator();
	}
}
