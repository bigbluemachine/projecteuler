package s000;

import java.util.Scanner;

import core.IOLib;

public class P081 {
	public static void main(String[] args) {
		Scanner in = IOLib.scanner("data/081.txt");
		int[][] M = new int[80][80];
		for (int i = 0; i < 80; i++) {
			String[] s = in.nextLine().split(",");
			for (int j = 0; j < 80; j++) {
				M[i][j] = Integer.parseInt(s[j]);
			}
		}
		IOLib.close(in);
		int[][] F = new int[80][80];
		F[79][79] = M[79][79];
		for (int i = 78; i >= 0; i--) {
			F[i][79] = M[i][79] + F[i + 1][79];
			F[79][i] = M[79][i] + F[79][i + 1];
		}
		for (int i = 78; i >= 0; i--) {
			for (int j = 78; j >= 0; j--) {
				F[i][j] = M[i][j] + Math.min(F[i][j + 1], F[i + 1][j]);
			}
		}
		System.out.println(F[0][0]);
	}
}
