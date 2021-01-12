package pl.edu.pw.ii.pte.junit.tdd.step8;

import java.util.EmptyStackException;

public class Stack {
	
	private int size = 0;
	private int[] contents = new int[10];

	public boolean isEmpty() {
		return size == 0;
	}

	public void push(int i) {
		contents[size++] = i;
	}

	public int pop() {
		if (isEmpty()) {
			throw new EmptyStackException();			
		}
		return contents[--size];
	}

}
