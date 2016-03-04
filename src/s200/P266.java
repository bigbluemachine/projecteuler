package s200;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;

import core.PList;

public class P266 {
	static void append(ArrayList<BigInteger> A, int p) {
		BigInteger P = BigInteger.valueOf(p);
		ArrayList<BigInteger> temp = new ArrayList<BigInteger>();
		for (BigInteger a : A) {
			temp.add(P.multiply(a));
		}
		A.add(P);
		A.addAll(temp);
	}

	public static void main(String[] args) {
		PList ps = new PList();
		while (ps.getLast() < 190) {
			ps.expand();
		}

		ArrayList<BigInteger> A = new ArrayList<BigInteger>();
		ArrayList<BigInteger> B = new ArrayList<BigInteger>();
		A.add(BigInteger.ONE);
		B.add(BigInteger.ONE);

		BigInteger P = BigInteger.ONE;
		int i = 0;
		for (int p : ps) {
			if (p >= 190) {
				break;
			}
			P = P.multiply(BigInteger.valueOf(p));
			if (i == 0) {
				append(A, p);
			} else {
				append(B, p);
			}
			i = 1 - i;
		}
		Collections.sort(A);
		Collections.sort(B);

		BigInteger best = BigInteger.ONE;
		int p = 0;
		int q = B.size() - 1;
		while (p < A.size() && q >= 0) {
			BigInteger test = A.get(p).multiply(B.get(q));
			BigInteger t2 = test.multiply(test);
			if (t2.compareTo(P) < 0) {
				if (test.compareTo(best) > 0) {
					best = test;
				}
				p++;
			} else {
				q--;
			}
		}
		System.out.println(best.mod(BigInteger.valueOf(10000000000000000L)));
	}
}
