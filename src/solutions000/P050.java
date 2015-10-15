package s000;

import java.util.HashSet;
import java.util.List;

import core.NTLib;

public class P050 {
	public static void main(String[] args) {
		List<Integer> L = NTLib.primeList(1000000);
		HashSet<Integer> S = new HashSet<Integer>();
		S.addAll(L);

		int best = 0;
		int ans = -1;
		M: for (int i = 0; i < L.size(); i++) {
			int sum = L.get(i);
			int count = 1;
			for (int j = i + 1; j < L.size(); j++) {
				sum += L.get(j);
				if (sum >= 1000000) {
					continue M;
				}
				count++;
				if (S.contains(sum) && count > best) {
					best = count;
					ans = sum;
				}
			}
		}
		System.out.println(ans);
	}
}
