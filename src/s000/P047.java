package s000;

import java.util.LinkedList;

import core.PFac;
import core.PList;

// Ugly.
public class P047 {
	public static void main(String[] args) {
		int target = 4;
		PList pl = new PList();
		LinkedList<PFac> Q = new LinkedList<PFac>();
		int i = 1;
		for (; i <= target; i++) {
			Q.addLast(PFac.make(i, pl.ps));
		}
		L: for (;; i++) {
			while (true) {
				int last = pl.getLast();
				if (last * last < i) {
					break;
				}
				pl.expand();
			}
			Q.addLast(PFac.make(i, pl.ps));
			Q.removeFirst();
			for (PFac pf : Q) {
				if (dpf(pf) != target) {
					continue L;
				}
			}
			break;
		}
		System.out.println(Q.getFirst().longValue());
	}

	static int dpf(PFac pf) {
		return pf.M.size() + (pf.twos > 0 ? 1 : 0);
	}
}
