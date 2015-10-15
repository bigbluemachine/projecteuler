package s100;

import java.util.Scanner;

import core.IOLib;

public class P102 {
	public static void main(String[] args) {
		Scanner in = IOLib.scanner("data/102.txt");
		int ans = 0;
		for (int i = 0; i < 1000; i++) {
			String[] line = in.nextLine().split(",");
			int x1 = Integer.parseInt(line[0]);
			int y1 = Integer.parseInt(line[1]);
			int x2 = Integer.parseInt(line[2]);
			int y2 = Integer.parseInt(line[3]);
			int x3 = Integer.parseInt(line[4]);
			int y3 = Integer.parseInt(line[5]);
			if (inter(x1, y1, x2, y2, x3, y3)) {
				ans++;
			}
		}
		IOLib.close(in);
		System.out.println(ans);
	}

	static int area(int x1, int y1, int x2, int y2) {
		return x1 * y2 - y1 * x2;
	}

	static boolean inter(int x1, int y1, int x2, int y2, int x3, int y3) {
		int s1 = area(x1, y1, x2, y2);
		int s2 = area(x2, y2, x3, y3);
		int s3 = area(x3, y3, x1, y1);
		return (s1 > 0 && s2 > 0 && s3 > 0) || (s1 < 0 && s2 < 0 && s3 < 0);
	}
}
