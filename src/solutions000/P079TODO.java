package s000;

import java.util.Scanner;
import java.util.TreeSet;

import core.IOLib;

// Pencil & paper
public class P079TODO {
	public static void main(String[] args) {
		Scanner in = IOLib.scanner("data/079.txt");
		TreeSet<String> S = new TreeSet<String>();
		while (in.hasNextLine()) {
			S.add(in.nextLine());
		}
		IOLib.close(in);
		System.out.println(S);
		boolean[][] seq = new boolean[10][10];
		for (String s : S) {
			char[] c = s.toCharArray();
			seq[c[0] - '0'][c[1] - '0'] = true;
			seq[c[0] - '0'][c[2] - '0'] = true;
			seq[c[1] - '0'][c[2] - '0'] = true;
		}
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (seq[i][j] && !seq[j][i]) {
					System.out.println(i + " before " + j);
				}
			}
		}
	}
}
