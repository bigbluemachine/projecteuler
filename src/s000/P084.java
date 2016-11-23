package s000;

import java.util.HashMap;

public class P084 {
	static final int GJ = 101;
	static final int CC = 102;
	static final int CH = 103;
	static final int GO = 104;
	static final int NEXT_R = 105;
	static final int NEXT_U = 106;
	static final int BACK_3 = 107;
	static int[] CC_CARDS = new int[16];
	static int[] CH_CARDS = new int[16];
	static int[] B = new int[40];
	static {
		B[30] = GJ;
		B[2] = B[17] = B[33] = CC;
		B[7] = B[22] = B[36] = CH;
		CC_CARDS[0] = GO;
		CC_CARDS[1] = 10;
		CH_CARDS[0] = GO;
		CH_CARDS[1] = 10;
		CH_CARDS[2] = 11;
		CH_CARDS[3] = 24;
		CH_CARDS[4] = 39;
		CH_CARDS[5] = 5;
		CH_CARDS[6] = NEXT_R;
		CH_CARDS[7] = NEXT_R;
		CH_CARDS[8] = NEXT_U;
		CH_CARDS[9] = BACK_3;
	}

	static int doubles;
	static int ccIndex;
	static int chIndex;

	static void shuffle(int[] a) {
		for (int i = 1; i < a.length; i++) {
			int j = (int) ((i + 1) * Math.random());
			int t = a[i];
			a[i] = a[j];
			a[j] = t;
		}
	}

	static int roll(int d) {
		int a = 1 + (int) (d * Math.random());
		int b = 1 + (int) (d * Math.random());
		if (a == b) {
			doubles++;
		} else {
			doubles = 0;
		}
		return a + b;
	}

	static String sim(int it) {
		shuffle(CC_CARDS);
		shuffle(CH_CARDS);
		doubles = 0;
		ccIndex = 0;
		chIndex = 0;

		int[] P = new int[40];
		int cur = 0;
		for (int i = 0; i < it; i++) {
			cur = (cur + roll(4)) % 40;
			if (B[cur] == GJ) {
				cur = 10;
			} else if (doubles >= 3) {
				doubles = 0;
				cur = 10;
			} else if (B[cur] == CC) {
				int k = CC_CARDS[ccIndex];
				if (k == GO) {
					cur = 0;
				} else if (k != 0) {
					cur = k;
				}
				ccIndex = (ccIndex + 1) % 16;
			} else if (B[cur] == CH) {
				int k = CH_CARDS[chIndex];
				if (k == GO) {
					cur = 0;
				} else if (k == NEXT_R) {
					cur -= cur % 5;
					if (cur % 5 == 0) {
						cur += 10;
					} else {
						cur += 5;
					}
					cur %= 40;
				} else if (k == NEXT_U) {
					if (12 < cur && cur < 28) {
						cur = 28;
					} else {
						cur = 12;
					}
				} else if (k == BACK_3) {
					cur -= 3;
				} else if (k != 0) {
					cur = k;
				}
				chIndex = (chIndex + 1) % 16;
			}
			P[cur]++;
		}

		String ans = "";
		for (int j = 0; j < 3; j++) {
			int max = 0;
			int str = 0;
			for (int i = 0; i < 40; i++) {
				if (P[i] > max) {
					max = P[i];
					str = i;
				}
			}
			P[str] = 0;
			ans += String.format("%02d", str);
		}
		return ans;
	}

	public static void main(String[] args) {
		HashMap<String, Integer> F = new HashMap<String, Integer>();
		for (int i = 0; i < 100; i++) {
			String s = sim(100000);
			if (!F.containsKey(s)) {
				F.put(s, 1);
			} else {
				F.put(s, F.get(s) + 1);
			}
		}

		String ans = "";
		int best = 0;
		for (String s : F.keySet()) {
			if (F.get(s) > best) {
				ans = s;
				best = F.get(s);
			}
		}
		System.out.println(ans);
		System.out.println(F);
	}
}
