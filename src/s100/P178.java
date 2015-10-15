package s100;

import java.math.BigInteger;

public class P178 {
	static BigInteger big(long l) {
		return BigInteger.valueOf(l);
	}

	static BigInteger STEP[][] = new BigInteger[41][10];

	static BigInteger step(int n, int d) {
		if (STEP[n][d].compareTo(big(0)) > 0)
			return STEP[n][d];

		BigInteger result;
		if (n == 1)
			result = big(1);
		else {
			result = big(0);
			if (d < 9)
				result = result.add(step(n - 1, d + 1));
			if (d > 0)
				result = result.add(step(n - 1, d - 1));
		}

		return STEP[n][d] = result;
	}

	static BigInteger PSTEP[][][] = new BigInteger[41][10][1024];

	static BigInteger pstep(int n, int last, int v) {
		if (PSTEP[n][last][v].compareTo(big(0)) >= 0)
			return PSTEP[n][last][v];

		BigInteger result = big(0);
		if (v == 1023) {
			if (n == 0)
				result = big(1);
			else {
				if (last < 9)
					result = result.add(step(n, last + 1));
				if (last > 0)
					result = result.add(step(n, last - 1));
			}
		} else {
			if (n > 0) {
				if (last < 9)
					result = result.add(pstep(n - 1, last + 1, v | (1 << (last + 1))));
				if (last > 0)
					result = result.add(pstep(n - 1, last - 1, v | (1 << (last - 1))));
			}
		}

		return PSTEP[n][last][v] = result;
	}

	public static void main(String[] args) {
		for (int i = 0; i < 41; i++)
			for (int j = 0; j < 10; j++)
				STEP[i][j] = big(0);

		for (int i = 0; i < 41; i++)
			for (int j = 0; j < 10; j++)
				for (int k = 0; k < 1024; k++)
					PSTEP[i][j][k] = big(-1);

		BigInteger result = big(0);
		for (int n = 9; n <= 39; n++)
			for (int d = 1; d <= 9; d++)
				result = result.add(pstep(n, d, (1 << d)));
		System.out.println(result);
	}
}
