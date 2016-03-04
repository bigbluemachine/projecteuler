package s200;

import java.util.HashMap;
import java.util.HashSet;

public class P237 {
	static enum Kind {
		NONE, AA__, __AA, A__A, _AA_, AABB, ABBA
	}

	static long N;
	static long M;

	static HashSet<Long> visited = new HashSet<Long>();
	static HashMap<Long, Long> T = new HashMap<Long, Long>();

	static long encode(long n, Kind l, Kind r) {
		return (n << 6) | (l.ordinal() << 3) | r.ordinal();
	}

	static long t(long n, Kind l, Kind r) {
		long key = encode(n, l, r);
		if (T.containsKey(key))
			return T.get(key);

		long ans = 0;
		for (Kind k : Kind.values())
			ans += (t(n / 2, l, k) * t((n + 1) / 2, k, r)) % M;
		ans %= M;

		T.put(key, ans);
		return ans;
	}

	static void t(Kind l, Kind r) {
		T.put(encode(1, l, r), 1L);
	}

	public static void main(String[] args) {
		for (Kind l : Kind.values())
			for (Kind r : Kind.values())
				T.put(encode(1, l, r), 0L);

		t(Kind.AA__, Kind.A__A); // shift
		t(Kind.AA__, Kind.AABB); // create

		t(Kind.__AA, Kind.A__A); // shift
		t(Kind.__AA, Kind.AABB); // create

		t(Kind.A__A, Kind.AA__); // shift
		t(Kind.A__A, Kind._AA_); // shift
		t(Kind.A__A, Kind.__AA); // shift
		t(Kind.A__A, Kind.ABBA); // create
		t(Kind.A__A, Kind.NONE); // end

		t(Kind._AA_, Kind.A__A); // shift

		t(Kind.AABB, Kind.A__A); // merge
		t(Kind.AABB, Kind.AABB); // stay

		t(Kind.ABBA, Kind.__AA); // merge
		t(Kind.ABBA, Kind.AA__); // merge
		t(Kind.ABBA, Kind.ABBA); // stay
		t(Kind.ABBA, Kind.NONE); // end

		// ----------------

		N = 1000000000000L;
		M = 100000000L;

		long ans = t(N, Kind.A__A, Kind.NONE);
		System.out.println(ans);
		System.out.println(T.size());
	}
}
