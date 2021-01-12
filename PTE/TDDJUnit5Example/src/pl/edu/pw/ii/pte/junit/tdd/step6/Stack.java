package pl.edu.pw.ii.pte.junit.tdd.step6;

import java.util.EmptyStackException;

public class Stack {
	
	private boolean empty = true;
	private int value;

	public boolean isEmpty() {
		return empty;
	}

	public void push(int i) {
		empty = false;
		value = i;
	}

	public int pop() {
		if (empty) {
			throw new EmptyStackException();			
		}
		empty = true;		
		return value;
	}

}
