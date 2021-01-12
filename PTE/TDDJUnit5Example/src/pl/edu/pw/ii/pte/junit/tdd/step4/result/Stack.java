package pl.edu.pw.ii.pte.junit.tdd.step4.result;

import java.util.EmptyStackException;

public class Stack {

	private boolean empty = true;

	public boolean isEmpty() {
		return empty;
	}

	public void push(int i) {
		empty = false;
	}

	public void pop() {
		if (empty) {
			throw new EmptyStackException();
		}
		empty = true;
	}

}
