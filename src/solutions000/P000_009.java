package s000;

import java.util.Scanner;

import core.Euler;
import core.IOLib;
import core.MathLib;
import core.PList;

public class P000_009 {
	static void p000() {
		Euler.printAnswer(0, "Welcome to Project Euler!");
	}

	static void p001() {
		Euler.printAnswer(1, 3 * MathLib.tri32(999 / 3) + 5 * MathLib.tri32(999 / 5) - 15 * MathLib.tri32(999 / 15));
	}

	static void p002() {
		int a = 1, b = 2, c;
		int ans = 0;
		while (b < 4000000) {
			if (b % 2 == 0) {
				ans += b;
			}
			c = a;
			a = b;
			b += c;
		}
		Euler.printAnswer(2, ans);
	}

	static void p003() {
		long n = 600851475143L;
		long ans = 1;
		for (int p = 3; p * p <= n; p += 2) {
			while (n % p == 0) {
				ans = p;
				n /= p;
			}
		}
		Euler.printAnswer(3, Math.max(ans, n));
	}

	static void p004() {
		int ans = 0;
		for (int i = 100; i <= 999; i++) {
			Z: for (int j = 100; j <= 999; j++) {
				char[] cs = Integer.toString(i * j).toCharArray();
				for (int a = 0, b = cs.length - 1; a < b; a++, b--) {
					if (cs[a] != cs[b]) {
						continue Z;
					}
				}
				ans = Math.max(ans, i * j);
			}
		}
		Euler.printAnswer(4, ans);
	}

	static void p005() {
		long ans = 1;
		for (int i = 1; i <= 20; i++) {
			ans = (ans * i) / MathLib.gcd64(ans, i);
		}
		Euler.printAnswer(5, ans);
	}

	static void p006() {
		int a = 0, b = 0;
		for (int i = 1; i <= 100; i++) {
			a += i;
			b += i * i;
		}
		Euler.printAnswer(6, a * a - b);
	}

	static void p007() {
		PList p = new PList();
		while (p.ps.size() < 10001) {
			p.expand();
		}
		Euler.printAnswer(7, p.ps.get(10000));
	}

	static void p008() {
		Scanner in = IOLib.scanner("data/008.txt");
		int[] a = new int[1000];
		int i = 0;
		while (in.hasNextLine()) {
			String line = in.nextLine();
			for (int j = 0; j < line.length(); j++) {
				a[i++] = line.charAt(j) - '0';
			}
		}
		IOLib.close(in);
		long ans = 0;
		for (int j = 12; j < 1000; j++) {
			long test = 1;
			for (int k = 0; k < 13; k++) {
				test *= a[j - k];
			}
			ans = Math.max(ans, test);
		}
		Euler.printAnswer(8, ans);
	}

	static void p009() {
		int ans = 0;
		for (int i = 3; i <= 1000; i++) {
			for (int j = i + 1; i + j <= 1000; j++) {
				int k = 1000 - i - j;
				if (i * i + j * j == k * k) {
					ans = i * j * k;
					break;
				}
			}
		}
		Euler.printAnswer(9, ans);
	}

	public static void main(String[] args) {
		p000();
		p001();
		p002();
		p003();
		p004();
		p005();
		p006();
		p007();
		p008();
		p009();
	}
}
