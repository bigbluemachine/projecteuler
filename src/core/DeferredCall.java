package core;

import java.util.LinkedList;

public class DeferredCall {
	private boolean fifo;
	private LinkedList<Call> calls;

	public DeferredCall() {
		this(true);
	}

	public DeferredCall(boolean fifo) {
		this.fifo = fifo;
		calls = new LinkedList<Call>();
	}

	public void add(Call call) {
		if (fifo) {
			calls.addLast(call);
		} else {
			calls.addFirst(call);
		}
	}

	public boolean getFifo() {
		return fifo;
	}

	public int getSize() {
		return calls.size();
	}

	public boolean isEmpty() {
		return calls.isEmpty();
	}

	public void call() {
		calls.removeFirst().call();
	}

	public static interface Call {
		void call();
	}
}