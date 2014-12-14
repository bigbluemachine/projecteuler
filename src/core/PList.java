package core;

import java.util.Iterator;
import java.util.LinkedList;

public class PList implements Iterable<Integer> {
	private static final int MAX_INTERVAL = 1000000;
	public final LinkedList<Integer> ps;

	public PList() {
		ps = new LinkedList<Integer>();
		ps.add(2);
		ps.add(3);
	}

	public void expand() {
		int last = ps.getLast();
		long max = last * (last + 2);
		int m = last + 2;
		int n = (int) Math.min(MAX_INTERVAL, max - m);

		boolean[] p = NTLib.offsetSieve(m, n, ps);
		for (int i = 0; i < n; i++) {
			if (p[i]) {
				ps.add(m + i);
			}
		}
	}

	@Override
	public Iterator<Integer> iterator() {
		return ps.iterator();
	}
}
