package s000;

import java.util.Scanner;

import core.IOLib;

public class P099 {
	public static void main(String[] args) {
		int ans = -1;
		double best = 0;
		Scanner in = IOLib.scanner("data/099.txt");
		int i = 1;
		while (in.hasNext()) {
			String line = in.nextLine();
			String[] s = line.split(",");
			int a = Integer.parseInt(s[0]);
			int b = Integer.parseInt(s[1]);
			double la = Math.log(a);
			double l = la * b;
			if (l > best) {
				best = l;
				ans = i;
			}
			i++;
		}
		in.close();
		System.out.println(ans);
	}
}
