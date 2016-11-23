package s200;

import java.util.TreeSet;

import core.Ratio;

public class P228 {
	static class Shape {
		TreeSet<Ratio> sides;
		long hidden;

		Shape(int n) {
			sides = new TreeSet<Ratio>();
			hidden = 0;

			int d = 4 * n;
			for (int i = 1; i < n; i++) {
				sides.add(new Ratio(n + 4 * i, d));
			}
		}

		Shape(TreeSet<Ratio> sides) {
			this.sides = sides;
		}

		Shape combine(Shape next) {
			TreeSet<Ratio> a = new TreeSet<Ratio>();
			a.addAll(sides);
			a.addAll(next.sides);
			return new Shape(a);
		}
	}

	public static void main(String[] args) {
		Shape s = new Shape(1864);
		for (int i = 1865; i <= 1909; i++) {
			s = s.combine(new Shape(i));
		}
		System.out.println(s.sides.size() + 1);
	}
}
