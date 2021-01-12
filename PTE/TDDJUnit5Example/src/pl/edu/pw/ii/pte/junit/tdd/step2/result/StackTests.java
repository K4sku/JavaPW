package pl.edu.pw.ii.pte.junit.tdd.step2.result;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

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

}
