package s200;

import java.util.LinkedList;

public class P265 {
	static long search(LinkedList<String> S, String last, String seq) {
		if (S.isEmpty()) {
			if (seq.startsWith(last.substring(1))) {
				return Long.parseLong(seq, 2);
			}
			return 0;
		}
		long ans = 0;
		for (int i = 0; i < S.size(); i++) {
			String s = S.get(i);
			if (s.startsWith(last.substring(1))) {
				LinkedList<String> T = new LinkedList<String>();
				T.addAll(S);
				T.remove(i);
				ans += search(T, s, seq + s.charAt(0));
			}
		}
		return ans;
	}

	public static void main(String[] args) {
		int N = 5;
		int X = 1 << N;
		LinkedList<String> S = new LinkedList<String>();
		for (int i = 0; i < X; i++) {
			String s = Integer.toBinaryString(1024 + i);
			S.add(s.substring(s.length() - N));
		}
		String first = S.removeFirst();
		System.out.println(search(S, first, "0"));
	}
}
