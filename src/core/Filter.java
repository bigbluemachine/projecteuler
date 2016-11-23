package core;

import java.util.Collection;
import java.util.Iterator;

public abstract class Filter<T> {
	public abstract boolean cond(T t);

	public void apply(Collection<T> S) {
		for (Iterator<T> it = S.iterator(); it.hasNext();) {
			if (!cond(it.next())) {
				it.remove();
			}
		}
	}
}
