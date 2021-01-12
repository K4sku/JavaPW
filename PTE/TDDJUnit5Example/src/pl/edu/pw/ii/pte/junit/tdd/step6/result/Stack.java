package pl.edu.pw.ii.pte.junit.tdd.step6.result;

import java.util.EmptyStackException;

public class Stack {
	
	private int size = 0;
	private int value;

	public boolean isEmpty() {
		return size == 0;
	}

	public void push(int i) {
		size++;
		value = i;
	}

	public int pop() {
		if (isEmpty()) {
			throw new EmptyStackException();			
		}
		size--;
		return value;
	}

}
