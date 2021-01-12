package pl.edu.pw.ii.pte.junit.tdd.step8.result;

import java.nio.BufferOverflowException;
import java.util.EmptyStackException;

public class Stack {

	public static final int MAXIMUM_CAPACITY = 10;
	private int size = 0;
	private int[] contents = new int[MAXIMUM_CAPACITY];

	public boolean isEmpty() {
		return size == 0;
	}

	public void push(int i) {
		if (size == MAXIMUM_CAPACITY) {
			throw new BufferOverflowException();
		}
		contents[size++] = i;
	}

	public int pop() {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		return contents[--size];
	}

}
