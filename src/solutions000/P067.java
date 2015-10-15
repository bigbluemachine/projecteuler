package s000;

import java.util.Arrays;
import java.util.Scanner;

import core.IOLib;

public class P067 {
	public static void main(String[] args) {
		Scanner in = IOLib.scanner("data/067.txt");
		int[][] a = new int[100][];
		int[][] f = new int[100][];
		for (int i = 0; i < 100; i++) {
			String[] ns = in.nextLine().split(" ");
			int l = ns.length;
			a[i] = new int[l];
			f[i] = new int[l];
			Arrays.fill(f[i], -1);
			for (int j = 0; j < l; j++) {
				a[i][j] = Integer.parseInt(ns[j]);
			}
		}
		IOLib.close(in);
		System.out.println(best(0, 0, a, f));
	}

	static int best(int i, int j, int[][] a, int[][] f) {
		if (f[i][j] >= 0) {
			return f[i][j];
		}
		if (i + 1 == a.length) {
			return f[i][j] = a[i][j];
		}
		return f[i][j] = a[i][j] + Math.max(best(i + 1, j, a, f), best(i + 1, j + 1, a, f));
	}
}
