package s100;

public class P197 {
	public static void main(String[] args) {
		double u = -1;
		for (int i = 1; i <= 10000; i++) {
			u = f(u);
		}
		System.out.println(u + f(u));
	}

	static double f(double x) {
		return Math.floor(Math.pow(2.0, 30.403243784 - x * x)) * 1.0e-9;
	}
}
