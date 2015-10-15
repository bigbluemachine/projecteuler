package s100;

import core.NTLib;

public class P196 {
	static int R, C;

	static long f(int r, int c) {
		return ((long) r * (r - 1)) / 2 + c;
	}

	static int isPrime(int r, int c) {
		long n = f(r, c);
		if (n % 2 > 0 && NTLib.MillerRabin(n, 5)) {
			R = r;
			C = c;
			return 1;
		}
		return 0;
	}

	static int primeCount(int r, int c) {
		int ans = 0;
		if (c > 1) {
			ans += isPrime(r - 1, c - 1);
			ans += isPrime(r, c - 1);
			ans += isPrime(r + 1, c - 1);
		}
		ans += isPrime(r - 1, c);
		ans += isPrime(r + 1, c);
		if (c < r - 1) {
			ans += isPrime(r - 1, c + 1);
		}
		if (c < r) {
			ans += isPrime(r, c + 1);
		}
		ans += isPrime(r + 1, c + 1);
		return ans;
	}

	static long scan(int r) {
		int c = 1;
		if (f(r, c) % 2 == 0) {
			c++;
		}
		long ans = 0;
		for (; c <= r; c += 2) {
			if (isPrime(r, c) == 0) {
				continue;
			}
			int k = primeCount(r, c);
			if (k == 0) {
				continue;
			} else if (k >= 2) {
				ans += f(r, c);
			} else {
				if (primeCount(R, C) > 1) {
					ans += f(r, c);
				}
			}
		}
		return ans;
	}

	public static void main(String[] args) {
		System.out.println(scan(5678027) + scan(7208785));
	}
}
