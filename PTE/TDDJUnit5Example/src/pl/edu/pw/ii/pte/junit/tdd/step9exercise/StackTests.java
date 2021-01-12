package pl.edu.pw.ii.pte.junit.tdd.step9exercise;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.nio.BufferOverflowException;
import java.util.EmptyStackException;

import pl.edu.pw.ii.pte.junit.tdd.step9exercise.Stack;

public class StackTests {

	@Test
	public void newlyCreatedStackIsEmpty() {
		Stack s = new Stack();

		assertTrue(s.isEmpty());
	}

	@Test
	public void afterPushStackIsNoLongerEmpty() {
		Stack s = new Stack();

		s.push(1);

		assertFalse(s.isEmpty());
	}

	@Test
	public void afterPushAndPopStackIsEmptyAgain() {
		Stack s = new Stack();

		s.push(1);
		s.pop();

		assertTrue(s.isEmpty());
	}

	@Test
	public void emptyStackThrowsOnPop() {
		assertThrows(
		EmptyStackException.class,
		()->{
			Stack s = new Stack();
			s.pop();
			}
		);
	}

	@Test
	public void popReturnsWhatWasPushed() {
		Stack s = new Stack();

		s.push(1234);

		assertEquals(1234, s.pop());
	}

	@Test
	public void stackDoesNotBecomeEmptyWhenThereWasLessPopThanPush() {
		Stack s = new Stack();

		s.push(1);
		s.push(2);
		s.pop();

		assertFalse(s.isEmpty());
	}

	@Test
	public void lastPopReturnsFirstPushedValue() {
		Stack s = new Stack();

		s.push(1);
		s.push(2);
		s.pop();

		assertEquals(1, s.pop());
	}

	@Test
	public void stackThrowsWhenTryingToPushMoreThanMaximumCapacity() {
		assertThrows(
			BufferOverflowException.class,
		()->{
			Stack s = new Stack();

			for (int i = 0; i < Stack.MAXIMUM_CAPACITY + 1; ++i)
				s.push(i);
			}
		);
	}
	
	// TODO configurable Stack capacity
	// TODO Stack.clear()
	// TODO Stack.getSize()
}
