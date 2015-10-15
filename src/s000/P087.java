package s000;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import core.NTLib;

public class P087 {
	public static void main(String[] args) {
		int m = 50000000;
		List<Integer> ps = NTLib.primeList((int) Math.sqrt(m) + 1);
		ArrayList<Integer> a = new ArrayList<Integer>();
		ArrayList<Integer> b = new ArrayList<Integer>();
		ArrayList<Integer> c = new ArrayList<Integer>();
		for (int p : ps) {
			long s = (long) p * p;
			if (s < m) {
				a.add((int) s);
				s *= p;
				if (s < m) {
					b.add((int) s);
					s *= p;
					if (s < m) {
						c.add((int) s);
					}
				}
			}
		}
		TreeSet<Integer> S = new TreeSet<Integer>();
		for (int i : a) {
			for (int j : b) {
				int ij = i + j;
				if (ij >= m) {
					break;
				}
				for (int k : c) {
					int ijk = ij + k;
					if (ijk >= m) {
						break;
					}
					S.add(ijk);
				}
			}
		}
		System.out.println(S.size());
	}
}
