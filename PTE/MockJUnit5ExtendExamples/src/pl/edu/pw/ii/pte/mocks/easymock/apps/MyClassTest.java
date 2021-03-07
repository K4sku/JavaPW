package pl.edu.pw.ii.pte.mocks.easymock.apps;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import  org.junit.jupiter.api.BeforeEach;

//import static org.easymock.EasyMock.createNiceMock;
import org.easymock.EasyMockSupport;

public class MyClassTest extends EasyMockSupport{
	private SimpleLogger logger = createNiceMock(SimpleLogger.class);
	private MyClass myClass;

	@BeforeEach
	public void createMyClass() throws Exception {
		myClass = new MyClass();
		myClass.setLogger(logger);
	}

	@Test
	public void testMessage() throws Exception {
		//throw new RuntimeException("not yet implemented");
		 // given
        myClass.setMsg("World");
        // when
        String res = myClass.message();
        // then
        assertEquals("Hello World", res);
	}

	@Test
    public void testMessage2() throws Exception {
        // given
        myClass.setMsg("World");                // ustawiamy obiekt
        logger.messageGenerated("Hello World");    // ma byæ 1 wywo³anie z takim parametrem        
        // when
        replayAll();                            // zaczynamy odtwarzanie
        String res = myClass.message();            // tu kod sprawdzany
        // then
        assertEquals("Hello World", res);// tradycyjne sprawdzenie wyniku
        verifyAll();                            // sprawdzamy, czy zgadza siê z nagraniem
    }
}
