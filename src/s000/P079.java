package s000;

import java.util.HashSet;
import java.util.Scanner;

import core.ArrayLib;
import core.IOLib;
import core.Perm;

public class P079 {
	static int[] toArray(HashSet<Integer> S, int k) {
		int[] a = new int[k];
		int i = 0;
		for (int x : S) {
			a[i++] = x;
		}
		return a;
	}

	static boolean ordered(int[] v, int a, int b) {
		for (int i : v) {
			if (i == b) {
				return a == -1;
			}
			if (i == a) {
				a = -1;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		Scanner in = IOLib.scanner("data/079.txt");
		boolean[][] O = new boolean[10][10];
		HashSet<Integer> D = new HashSet<Integer>();
		while (in.hasNextLine()) {
			String s = in.nextLine();
			int a = s.charAt(0) - '0';
			int b = s.charAt(1) - '0';
			int c = s.charAt(2) - '0';
			D.add(a);
			D.add(b);
			D.add(c);
			O[a][b] = true;
			O[b][c] = true;
		}
		IOLib.close(in);

		int k = D.size();
		int[] d = toArray(D, k);

		Perm p = new Perm(k);
		while (p.hasNext()) {
			int[] v = ArrayLib.map(d, p.next());
			boolean bad = false;
			L: for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					if (O[i][j] && !ordered(v, i, j)) {
						bad = true;
						break L;
					}
				}
			}
			if (!bad) {
				String ans = "";
				for (int i : v) {
					ans += i;
				}
				System.out.println(ans);
			}
		}
	}
}
