package pl.edu.pw.ii.pte.junit.tdd.step2.result;

public class Stack {
	
	private boolean empty = true;

	public boolean isEmpty() {
		return empty;
	}

	public void push(int i) {
		empty = false;
	}

}
