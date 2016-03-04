package s200;

import java.util.TreeMap;

public class P219 {
	static void add(TreeMap<Integer, Integer> M, int k, int v) {
		if (M.containsKey(k)) {
			M.put(k, M.get(k) + v);
		} else {
			M.put(k, v);
		}
	}

	public static void main(String[] args) {
		int K = 1000000000;

		TreeMap<Integer, Integer> C = new TreeMap<Integer, Integer>();
		C.put(1, 1);
		C.put(4, 1);

		int left = K - 2;
		while (true) {
			int n = C.firstKey();
			if (left < C.get(n)) {
				add(C, n + 1, left);
				add(C, n + 4, left);
				add(C, n, -left);
				break;
			} else {
				int t = C.get(n);
				add(C, n + 1, t);
				add(C, n + 4, t);
				C.remove(n);
				left -= t;
			}
		}

		long ans = 0;
		for (int k : C.keySet()) {
			ans += (long) k * C.get(k);
		}
		System.out.println(ans);
	}
}
