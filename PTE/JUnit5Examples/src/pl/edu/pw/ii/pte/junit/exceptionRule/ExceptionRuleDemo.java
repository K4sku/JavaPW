package pl.edu.pw.ii.pte.junit.exceptionRule;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.*;
import org.junit.jupiter.api.Test;

public class ExceptionRuleDemo {
	@Test
	public void shouldTestExceptionMessage() {

		assertThrows(
				IndexOutOfBoundsException.class,
				()->{
					List<Object> list = new ArrayList<Object>();
					list.get(0); // wykonanie nie przekroczy tej linii
				}
				);
		
			}
}
