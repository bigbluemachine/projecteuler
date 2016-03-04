package core;

import java.math.BigInteger;

public class Matrix {
	private int size;
	private long[][] data;

	public Matrix(long[][] x) {
		int s = x.length;
		long[][] m = new long[s][s];
		for (int i = 0; i < s; i++) {
			for (int j = 0; j < s; j++) {
				m[i][j] = x[i][j];
			}
		}
		this.size = s;
		this.data = m;
	}

	public static Matrix identity(int s) {
		long[][] m = new long[s][s];
		for (int i = 0; i < s; i++) {
			m[i][i] = 1;
		}
		return new Matrix(m);
	}

	public int getSize() {
		return size;
	}

	public long[][] getData() {
		return data;
	}

	public long[] multiply(long[] v) {
		long[] w = new long[size];
		for (int i = 0; i < size; i++) {
			long t = 0;
			for (int j = 0; j < size; j++) {
				t += v[j] * data[i][j];
			}
			w[i] = t;
		}
		return w;
	}

	public Matrix multiply(Matrix b) {
		long[][] x = data, y = b.getData();
		long[][] z = new long[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				long t = 0;
				for (int k = 0; k < size; k++) {
					t += x[i][k] * y[k][j];
				}
				z[i][j] = t;
			}
		}
		return new Matrix(z);
	}

	public Matrix multiply(Matrix b, long m) {
		long[][] x = data, y = b.getData();
		long[][] c = new long[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				long t = 0;
				for (int k = 0; k < size; k++) {
					t = term(x[i][k], y[k][j], t, m);
				}
				c[i][j] = t;
			}
		}
		return new Matrix(c);
	}

	public Matrix modexp(long e, long m) {
		Matrix a = Matrix.identity(size);
		Matrix b = new Matrix(data);
		for (long i = 1; i <= e; i <<= 1, b = b.multiply(b, m)) {
			if ((e & i) > 0) {
				a = a.multiply(b, m);
			}
		}
		return a;
	}

	public void dump(int places) {
		String fmt = String.format("%c%d%c ", '%', places, 'd');
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				System.out.printf(fmt, data[i][j]);
			}
			System.out.println();
		}
	}

	// (ab + c) % m
	public static long term(long a, long b, long c, long mod) {
		return big(c).add(big(a).multiply(big(b))).mod(big(mod)).longValue();
	}

	private static BigInteger big(long n) {
		return BigInteger.valueOf(n);
	}
}
