package s000;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import core.NTLib;

public class P060TODO {
	static int size;
	static HashMap<Long, Boolean> concat = new HashMap<Long, Boolean>();
	static Integer[] primes;
	static int[] p10;
	static int[] stack;
	static int count = 0;
	static int ans = Integer.MAX_VALUE;

	static long concat(int a, int b) {
		return (long) a * p10[b] + b;
	}

	static boolean concatPrime(int a, int b) {
		if ((a + b) % 3 == 0)
			return false;

		long key = (long) (a << 16) | b;
		if (concat.containsKey(key))
			return concat.get(key);

		boolean ans = NTLib.MillerRabin(concat(a, b), 10) && NTLib.MillerRabin(concat(b, a), 10);
		concat.put(key, ans);
		return ans;
	}

	static boolean addPrime(int x) {
		for (int j = 0; j < count; j++)
			if (!concatPrime(x, stack[j]))
				return false;
		return true;
	}

	static void search(int index) {
		if (count == size) {
			int s = 0;
			for (int n : stack)
				s += n;
			if (s < ans)
				ans = s;
			System.out.printf("%d: %s\n", s, Arrays.toString(stack));
			return;
		}

		for (int i = index; i < primes.length && primes[i] < ans; i++) {
			if (addPrime(primes[i])) {
				stack[count++] = primes[i];
				search(i + 1);
				count--;
			}
		}
	}

	public static void main(String[] args) {
		// Set size
		size = 5;
		stack = new int[size];

		// Get primes less than M, except 2 and 5
		int M = 50000;
		List<Integer> ps = NTLib.primeList(M);
		ps.remove(2); // 5
		ps.remove(0); // 2
		primes = ps.toArray(new Integer[ps.size()]);

		// B[n] = largest power of 10 less than n
		p10 = new int[M];
		int base = 10;
		for (int i = 0; i < primes.length; p10[primes[i++]] = base)
			while (base < primes[i])
				base *= 10;

		// Search
		for (int i = 0; i < primes.length - 1; i++) {
			stack[count++] = primes[i];
			search(i + 1);
			count--;
		}

		System.out.println(ans);
	}
}
