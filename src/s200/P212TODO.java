package s200;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.TreeSet;

public class P212TODO {
	static class K {
		int x, y, z, xx, yy, zz;

		K(int a, int b, int c, int d, int e, int f) {
			x = a % 10000;
			y = b % 10000;
			z = c % 10000;
			xx = x + 1 + (d % 399);
			yy = y + 1 + (e % 399);
			zz = z + 1 + (f % 399);
		}
	}

	static class XComparator implements Comparator<K> {
		public int compare(K a, K b) {
			return a.x - b.x;
		}
	}

	static class YComparator implements Comparator<K> {
		public int compare(K a, K b) {
			return a.y - b.y;
		}
	}

	static class ZComparator implements Comparator<K> {
		public int compare(K a, K b) {
			return a.z - b.z;
		}
	}

	static final XComparator X = new XComparator();
	static final YComparator Y = new YComparator();
	static final ZComparator Z = new ZComparator();

	static long length(LinkedList<K> C) {
		if (C.isEmpty())
			return 0;

		Collections.sort(C, Y);
		long total = 0;
		int lastY = 0;

		for (K c : C) {
			int a = c.y, b = c.yy;
			if (a > lastY)
				total += b - a;
			else if (b > lastY)
				total += b - lastY;
			lastY = Math.max(lastY, b);
		}

		return total;
	}

	static long area(LinkedList<K> D) {
		if (D.isEmpty())
			return 0;

		LinkedList<K> C = new LinkedList<K>();
		C.addAll(D);
		Collections.sort(C, X);

		TreeSet<Integer> Q = new TreeSet<Integer>();
		for (K c : C) {
			Q.add(c.x);
			Q.add(c.xx);
		}

		LinkedList<K> I = new LinkedList<K>();
		long total = 0, partial = 0;
		int lastX = 0;

		for (int x : Q) {
			total += partial * (x - lastX);
			lastX = x;

			LinkedList<K> J = new LinkedList<K>();
			for (K c : I)
				if (c.xx > x)
					J.add(c);
			while (!C.isEmpty() && C.peek().x == x)
				J.add(C.pop());

			partial = length(J);
			I = J;
		}

		return total;
	}

	static long volume(LinkedList<K> C) {
		Collections.sort(C, Z);

		TreeSet<Integer> Q = new TreeSet<Integer>();
		for (K c : C) {
			Q.add(c.z);
			Q.add(c.zz);
		}

		LinkedList<K> I = new LinkedList<K>();
		long total = 0, partial = 0;
		int lastZ = 0;

		for (int z : Q) {
			total += partial * (z - lastZ);
			lastZ = z;

			LinkedList<K> J = new LinkedList<K>();
			for (K c : I)
				if (c.zz > z)
					J.add(c);
			while (!C.isEmpty() && C.peek().z == z)
				J.add(C.pop());

			partial = area(J);
			I = J;
		}

		return total;
	}

	public static void main(String[] args) {
		// TODO Slightly over 60s...
		LinkedList<Integer> S = new LinkedList<Integer>();
		for (int k = 1; k <= 55; k++) {
			long x = 300007 * k;
			x *= k * k;
			x -= 200003 * k;
			x += 100003;
			S.add((int) (x % 1000000));
		}
		for (int k = 56; k <= 300000; k++)
			S.add((S.get(k - 24 - 1) + S.get(k - 55 - 1)) % 1000000);

		LinkedList<K> C = new LinkedList<K>();
		for (int i = 0; i < 50000; i++)
			C.add(new K(S.pop(), S.pop(), S.pop(), S.pop(), S.pop(), S.pop()));

		System.out.println(volume(C));
	}
}
