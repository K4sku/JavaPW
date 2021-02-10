package pl.edu.pw.ii.pte.junit.tdd.StackExpandable;

import java.util.Arrays;
import java.util.EmptyStackException;

public class Stack {

	private int capacity;
	private int size = 0;
	private int[] contents;
	
	public Stack() {
		capacity = 0;
		contents = new int[capacity];
	}
	
	public Stack(int capacity) {
		this.capacity = capacity;
		contents = new int[this.capacity];
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void push(int i) {
		if (size == capacity) {
			increaseCapacity();
		}
		contents[size++] = i;
	}

	public int pop() {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		return contents[--size];
	}

	public int getSize() {
		return size;
	}
	
	public void clear() {
		Arrays.fill(contents, 0);
		size = 0;
		
	}
	
	public int getCapacity() {
		return capacity;
	}
	
	public void increaseCapacity() {
		capacity++;
		int[] newContents = new int[capacity];
		System.arraycopy(contents, 0, newContents, 0, contents.length);
		contents = newContents;
	}
	
	public void increaseCapacity(int i) {
		if (i >= 0) {
			capacity = capacity + i;
			int[] newContents = new int[capacity];
			System.arraycopy(contents, 0, newContents, 0, contents.length);
			contents = newContents;
		} else {
			throw new IllegalArgumentException("Can not increase stack by negative value.");
		}
	}
	
}
