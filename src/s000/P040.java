package s000;

// Lazy.
public class P040 {
	public static void main(String[] args) {
		char[] v = new char[1000007];
		int i = 0;
		for (int n = 1;; n++) {
			char[] c = Integer.toString(n).toCharArray();
			for (int k = 0; k < c.length; k++) {
				v[i++] = c[k];
			}
			if (i > 1000000) {
				break;
			}
		}
		int ans = 1;
		for (int k = 1; k <= 1000000; k *= 10) {
			int x = v[k - 1] - '0';
			ans *= x;
		}
		System.out.println(ans);
	}
}
