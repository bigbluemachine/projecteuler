package core;

import java.util.TreeMap;

public class Equation {
	public int id;
	public double mul, ct;
	public TreeMap<Integer, Double> coefs = new TreeMap<Integer, Double>();

	public Equation(int id, double multiplier, double constTerm) {
		this.mul = multiplier;
		this.id = id;
		this.ct = constTerm;
	}

	public boolean isResolved() {
		return coefs.isEmpty();
	}

	public Double getValue() {
		return isResolved() ? ct / mul : null;
	}

	public void substitute(Equation e) {
		if (!coefs.containsKey(e.id)) {
			return;
		}

		double m = coefs.get(e.id) / e.mul;
		ct += m * e.ct;
		coefs.remove(e.id);

		for (int id : e.coefs.keySet()) {
			double coef = e.coefs.get(id);

			if (id == this.id) {
				mul -= m * coef;
			} else {
				if (coefs.containsKey(id)) {
					coefs.put(id, coefs.get(id) + m * coef);
				} else {
					coefs.put(id, m * coef);
				}
			}
		}
	}

	public String toString() {
		String s = String.format("%.3f * x_%d = %.3f", mul, id, ct);

		for (int id : coefs.keySet()) {
			s += String.format(" + %.3fx_%d", coefs.get(id), id);
		}

		return s;
	}
}
