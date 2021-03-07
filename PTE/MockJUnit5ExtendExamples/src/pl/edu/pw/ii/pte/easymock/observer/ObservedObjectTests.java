package pl.edu.pw.ii.pte.easymock.observer;

import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(EasyMockExtension.class)
public class ObservedObjectTests extends EasyMockSupport {

	public static class StubObservedObject extends ObservedObject {
		public void simulateNotification() {
			super.notifyAllObservers();
		}
	}

	public static class ObserverStub implements Observer {
		public boolean wasDataChangedCalled = false;

		@Override
		public void dataChanged() {
			wasDataChangedCalled = true;
		}
		
	}

	@TestSubject
	public StubObservedObject object = new StubObservedObject();
	
	@Mock
	public Observer observer1;

	@Test
	public void singleObserverIsNotified() {
		object.add(observer1);
		
		observer1.dataChanged();
		replayAll();
		
		object.simulateNotification();
		
		verifyAll();
	}
	
	@Test
	public void singleObserverIsNotified_NO_MOCK() {
		ObserverStub o = new ObserverStub();
		object.add(o);
		
		object.simulateNotification();
		
		assertTrue(o.wasDataChangedCalled);
	}

}
