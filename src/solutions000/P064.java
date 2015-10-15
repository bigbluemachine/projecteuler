package s000;

import static java.lang.Math.sqrt;

import java.util.LinkedList;

public class P064 {
	public static void main(String[] args) {
		int ans = 0;
		for (int i = 2; i <= 10000; i++) {
			int s = (int) Math.sqrt(i);
			if (s * s != i) {
				if (sqrtCF(i).size() % 2 == 0) {
					ans++;
				}
			}
		}
		System.out.println(ans);
	}

	static LinkedList<Integer> sqrtCF(int n) {
		LinkedList<Integer> seq = new LinkedList<Integer>();
		double s = sqrt(n);
		int k = 0, d = 1, x;
		do {
			x = (int) ((k + s) / d);
			k = x * d - k;
			d = (n - k * k) / d;
			seq.add(x);
		} while (d != 1);
		seq.add(((int) s) << 1);
		return seq;
	}
}
