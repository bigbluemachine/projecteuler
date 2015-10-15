package s100;

import java.util.Scanner;
import java.util.TreeSet;

import core.ArrayLib;
import core.IOLib;
import core.Selector;

public class P105 {
	static int count = 0;

	static boolean test(int[] a, long v, long w) {
		int sa = 0, sb = 0, ca = 0, cb = 0;
		for (int i = 0; i < a.length; i++) {
			if ((v & (1 << i)) > 0) {
				sa += a[i];
				ca++;
			} else {
				sb += a[i];
				cb++;
			}
		}
		return ca > cb ? sa > sb : ca < cb ? sa < sb : sa != sb;
	}

	static boolean test2(int[] a) {
		TreeSet<Long> V = new TreeSet<Long>();

		for (int i = 1; i + i <= a.length; i++) {
			Selector s = new Selector(a.length, i);
			while (s.hasNext()) {
				long v = s.next();
				long w = s.inverse(v);
				if (V.contains(v) || V.contains(w)) {
					continue;
				}
				V.add(v);

				if (!test(a, v, w)) {
					return false;
				}
			}
		}

		return true;
	}

	static boolean test(int[] a) {
		for (int i = 2; i <= a.length; i++) {
			Selector s = new Selector(a.length, i);
			while (s.hasNext()) {
				long v = s.next();

				int[] b = new int[i];
				for (int j = 0, k = 0; j < a.length; j++) {
					if ((v & (1 << j)) > 0) {
						b[k++] = j;
					}
				}

				if (!test2(ArrayLib.map(a, b))) {
					return false;
				}
			}
		}

		return true;
	}

	public static void main(String[] args) {
		Scanner in = IOLib.scanner("data/105.txt");
		int ans = 0;
		while (in.hasNextLine()) {
			String line = in.nextLine();
			String[] s = line.split(",");
			int[] a = new int[s.length];
			for (int i = 0; i < s.length; i++) {
				a[i] = Integer.parseInt(s[i]);
			}
			if (test(a)) {
				for (int i : a) {
					ans += i;
				}
			}
		}
		in.close();
		System.out.println(ans);
	}
}
