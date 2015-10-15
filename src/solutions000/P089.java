package s000;

import java.util.Scanner;

import core.IOLib;

public class P089 {
	public static void main(String[] args) {
		Scanner in = IOLib.scanner("data/089.txt");
		int ans = 0;
		while (in.hasNextLine()) {
			String s = in.nextLine();
			ans += minimize(s);
		}
		IOLib.close(in);
		System.out.println(ans);
	}

	static int minimize(String s) {
		int l = s.length();
		s = s.replace("IIII", "IV");
		s = s.replace("VIV", "IX");
		s = s.replace("XXXX", "XL");
		s = s.replace("LXL", "XC");
		s = s.replace("CCCC", "CD");
		s = s.replace("DCD", "CM");
		return l - s.length();
	}
}
