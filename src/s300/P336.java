package s300;

import java.util.ArrayList;
import java.util.Collections;

import core.MathLib;
import core.Perm;

public class P336 {
	static void reverse(int[] v, int i) {
		for (int a = i, z = v.length - 1; a < z; a++, z--) {
			int t = v[a];
			v[a] = v[z];
			v[z] = t;
		}
	}

	static int indexOf(int[] v, int n) {
		for (int i = 0; i < v.length; i++) {
			if (v[i] == n) {
				return i;
			}
		}
		return -1;
	}

	static int solve(int[] v) {
		int ans = 0;
		for (int n = 0; n < v.length; n++) {
			int i = indexOf(v, n);
			if (i != n) {
				if (i != v.length - 1) {
					reverse(v, i);
					ans++;
				}
				reverse(v, n);
				ans++;
			}
		}
		return ans;
	}

	public static void main(String[] args) {
		int x = 11;
		int y = 2011;
		Perm p = new Perm(x);

		int f = MathLib.fac32(x);
		int[] s = new int[f];
		int max = 0;
		for (int i = 0; p.hasNext(); i++) {
			s[i] = solve(p.next());
			max = Math.max(max, s[i]);
		}

		ArrayList<String> S = new ArrayList<String>();
		p.reset();
		for (int i = 0; p.hasNext(); i++) {
			int[] v = p.next();
			if (s[i] == max) {
				char[] c = new char[x];
				for (int j = 0; j < x; j++) {
					c[j] = (char) ('A' + v[j]);
				}
				S.add(new String(c));
			}
		}
		Collections.sort(S);

		System.out.println(S.get(y - 1));
	}
}
