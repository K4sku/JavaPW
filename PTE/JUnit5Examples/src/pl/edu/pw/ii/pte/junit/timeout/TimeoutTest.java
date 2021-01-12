package pl.edu.pw.ii.pte.junit.timeout;

import static java.time.Duration.ofMillis;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

import org.junit.jupiter.api.Test;

public class TimeoutTest {

	@Test
	public void infinity() {
		assertTimeoutPreemptively(
			ofMillis(10), 
			() -> {
				while (true) {
					;
					}
				});	
	}
	
    @Test
    void timeoutExceeded() {
        assertTimeout(ofMillis(10), () -> {
            // Simulate task that takes more than 10 ms.
            Thread.sleep(100);
        });
    }
        
}
