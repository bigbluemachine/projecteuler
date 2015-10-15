package s000;

import java.util.Scanner;

import core.IOLib;

public class P059 {
	public static void main(String[] args) {
		Scanner in = IOLib.scanner("data/059.txt");
		String[] s = in.nextLine().split(",");
		IOLib.close(in);

		char[] v = { 'g', 'o', 'd' };
		int i = 0;
		String t = "";
		int ans = 0;
		for (int j = 0; j < s.length; j++) {
			int x = Integer.parseInt(s[j]);
			char y = (char) (x ^ v[i]);
			t += y;
			ans += (int) y;
			i = (i + 1) % 3;
		}
		System.out.println(t);
		System.out.println(ans);
	}
}
