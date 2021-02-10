package pl.edu.pw.ii.pte.patterns.decorator.example;

import java.util.Random;

public class RandomAuthService implements AuthenticationService {
	
	@Override
	public boolean loginUser(String name, String authString) {
		Random random = new Random();
		return random.nextBoolean();
	}

	@Override
	public void registerUser(String name, String authString) {
	}
}
