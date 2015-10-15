package s000;

public class P053 {
	public static void main(String[] args) {
		int count = 0;
		for (int n = 1; n <= 100; n++) {
			long C = 1;
			for (int r = 0; r <= n; r++) {
				if (C > 1000000) {
					count += n - r - r + 1;
					break;
				}
				C *= n - r;
				C /= r + 1;
			}
		}
		System.out.println(count);
	}
}
