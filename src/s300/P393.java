package s300;

import java.util.ArrayList;
import java.util.Arrays;

public class P393 {
	static class Pair {
		int a, b;

		Pair(int A, int B) {
			a = A;
			b = B;
		}
	}

	static int W, E;
	static long[][][] F;
	static ArrayList<Integer> empties = new ArrayList<Integer>();
	static ArrayList<Integer> carries = new ArrayList<Integer>();
	static int lastCarry;

	static final boolean isSet(int v, int i) {
		return (v & (1 << i)) > 0;
	}

	static final int set(int v, int i) {
		return v | (1 << i);
	}

	static final int unset(int v, int i) {
		return v & ~(1 << i);
	}

	static void generate(int cur, int index, boolean right, int nextCarry) {
		if (index == W) {
			if (!right) {
				empties.add(~(cur | lastCarry) & (E - 1));
				carries.add(nextCarry);
			}
			return;
		}

		if (isSet(cur, index)) {
			if (right) {
				generate(cur, index + 1, false, set(nextCarry, index));

				if (!isSet(lastCarry, index + 1))
					generate(cur, index + 1, true, nextCarry);
			} else {
				if (index > 0 && !isSet(cur, index - 1) && !isSet(lastCarry, index - 1))
					generate(unset(set(cur, index - 1), index), index + 1, false, nextCarry);

				generate(unset(cur, index), index + 1, false, set(nextCarry, index));

				if (!isSet(lastCarry, index + 1))
					generate(unset(cur, index), index + 1, true, nextCarry);
			}
		} else
			generate(right ? set(cur, index) : cur, index + 1, false, nextCarry);
	}

	static ArrayList<Pair> generate(int cur) {
		empties.clear();
		carries.clear();

		generate(cur, 0, false, 0);

		ArrayList<Pair> pairs = new ArrayList<Pair>();
		for (int i = 0; i < empties.size(); i++)
			pairs.add(new Pair(empties.get(i), carries.get(i)));

		return pairs;
	}

	static long F(int empty, int carry, int left) {
		if (F[empty][carry][left] >= 0)
			return F[empty][carry][left];

		long ans = 0;

		int cur = (1 << W) - 1;
		for (int i = 0; i < W; i++)
			if (isSet(empty, i)) {
				if (isSet(carry, i))
					return F[empty][carry][left] = 0;
				cur = unset(cur, i);
			}

		lastCarry = carry;
		ArrayList<Pair> gen = generate(cur);

		for (Pair p : gen)
			ans += F(p.a, p.b, left - 1);

		return F[empty][carry][left] = ans;
	}

	static long F(int n) {
		W = n;
		E = 1 << W;

		F = new long[E][E][W + 1];
		for (long[][] N : F)
			for (long[] O : N)
				Arrays.fill(O, -1);

		for (int i = 0; i < E; i++)
			for (int j = 0; j < E; j++)
				F[i][j][0] = 0;
		F[0][0][0] = 1;

		return F(0, 0, W);

	}

	public static void main(String[] args) {
		System.out.println(F(10));
	}
}
