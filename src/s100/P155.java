package s100;

import java.util.TreeSet;

import core.Ratio;

public class P155 {
	static class Q {
		TreeSet<Ratio> S = new TreeSet<Ratio>();
	}

	public static void main(String[] args) {
		int k = 18;

		Q[] qs = new Q[k];
		qs[0] = new Q();
		qs[0].S.add(new Ratio(1, 1));
		for (int i = 2; i <= k; i++) {
			qs[i - 1] = new Q();
			for (int a = 1, b = i - 1; a <= b; a++, b--) {
				for (Ratio x : qs[a - 1].S) {
					for (Ratio y : qs[b - 1].S) {
						qs[i - 1].S.add(x.add(y));
						qs[i - 1].S.add(x.inv().add(y.inv()).inv());
					}
				}
			}
		}

		TreeSet<Ratio> R = new TreeSet<Ratio>();
		for (int i = 1; i <= k; i++) {
			R.addAll(qs[i - 1].S);
		}
		System.out.println(R.size());
	}
}
