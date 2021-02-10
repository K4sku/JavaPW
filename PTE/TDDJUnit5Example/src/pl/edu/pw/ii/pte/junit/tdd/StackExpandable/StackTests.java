package pl.edu.pw.ii.pte.junit.tdd.StackExpandable;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.EmptyStackException;


public class StackTests {

	@Test
	public void newlyCreatedStackIsEmpty() {
		Stack s = new Stack();

		assertTrue(s.isEmpty());
	}
	
	@Test
	public void newlyCreatedStackHasDeclaredCapacity() {
		Stack s = new Stack(5);
		
		assertEquals(5, s.getCapacity());
		assertNotEquals(4, s.getCapacity());
		assertTrue(s.isEmpty());
		
		Stack t = new Stack(0);
		
		assertEquals(0, t.getCapacity());
		assertNotEquals(1, t.getCapacity());
		assertTrue(t.isEmpty());
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

//	@Test
//	public void stackThrowsWhenTryingToPushMoreThanMaximumCapacity() {
//		assertThrows(
//			BufferOverflowException.class,
//		()->{
//			Stack s = new Stack();
//
//			for (int i = 0; i < Stack.MAXIMUM_CAPACITY + 1; ++i)
//				s.push(i);
//			}
//		);
//	}
	
	@Test
	public void stackReturnsSize() {
		Stack s = new Stack();
		
		assertEquals(0, s.getSize());
		s.push(1);
		assertEquals(1, s.getSize());
		s.push(2);
		assertEquals(2, s.getSize());
		s.pop();
		assertEquals(1, s.getSize());
	}
	
	@Test
	public void stackIsEmptyAfterClearing() {
		Stack s = new Stack();
		assertTrue(s.isEmpty());
		s.clear();
		assertTrue(s.isEmpty());
		s.push(1);
		s.push(2);
		s.clear();
		assertTrue(s.isEmpty());
	}
	
	@Test
	public void stackCapacityIncreasesByOneWithPush() {
		Stack s = new Stack();
		assertTrue(s.isEmpty());
		assertEquals(0, s.getCapacity());
		s.push(1);
		assertEquals(1, s.getCapacity());		
	}
	
	@Test
	public void stackCapacityIncreasesByOneWithNoValue() {
		Stack s = new Stack();
		assertEquals(0, s.getCapacity());
		s.increaseCapacity();
		assertEquals(1, s.getCapacity());	
	}
	
	@Test
	public void stackCapacityIncreasesByDeclaredValue() {
		Stack s = new Stack();
		assertEquals(0, s.getCapacity());
		s.increaseCapacity(4);
		assertEquals(4, s.getCapacity());	
	}
	
	@Test
	public void stackCapacityDoesNotDecreaseWithPop() {
		Stack s = new Stack(0);
		assertEquals(0, s.getCapacity());
		s.push(1);
		assertEquals(1, s.getCapacity());
		assertEquals(1, s.pop());
		assertEquals(1, s.getCapacity());
	}
	
	@Test
	public void stackReteinsValuesAfterExpanding() {
		Stack s = new Stack(5);
		
		for(int i=0; i<s.getCapacity(); i++) {
			s.push(i);
		}
		
		s.increaseCapacity(5);
		
		for(int i=5; i<10; i++) {
			s.push(i);
		}
		
		for(int i=9; i>=0; i=i-1) {
			assertEquals(i, s.pop());
		}
	}
	
	@Test
	public void stackThrowsExceptionWhenIncreaseCapacityArgumentIsNegative() {
		Stack s = new Stack(5);
		
		assertThrows(
				IllegalArgumentException.class,()->{
					s.increaseCapacity(-2);
			}
		);
	}
	

}
