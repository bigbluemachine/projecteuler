package s400;

import java.util.Arrays;

import core.ArrayLib;

public class P493 {
	public static void main(String[] args) {
		int[] v = ArrayLib.repeat(EACH, 7);
		T = new double[encode(v) + 1];
		System.out.println(f(encode(v)));
	}

	static double[] T;
	static int EACH = 10;
	static int TARGET = 20;

	static double f(int x) {
		if (T[x] > 0) {
			return T[x];
		}
		int[] v = decode(x);
		int sum = 0;
		int distinct = 0;
		for (int u : v) {
			sum += u;
			if (u != EACH) {
				distinct++;
			}
		}
		if (sum == 7 * EACH - TARGET) {
			return T[x] = distinct;
		}
		double ans = 0;
		for (int i = 0; i < 7; i++) {
			if (v[i] == 0) {
				continue;
			}
			int[] w = v.clone();
			ans += w[i]-- * f(encode(w));
		}
		ans /= sum;
		return T[x] = ans;
	}

	static int encode(int[] v) {
		int ans = 0;
		Arrays.sort(v);
		for (int i : v) {
			ans = (EACH + 1) * ans + i;
		}
		return ans;
	}

	static int[] decode(int x) {
		int[] v = new int[7];
		for (int i = 0; i < 7; i++) {
			v[i] = x % (EACH + 1);
			x /= (EACH + 1);
		}
		return v;
	}
}
