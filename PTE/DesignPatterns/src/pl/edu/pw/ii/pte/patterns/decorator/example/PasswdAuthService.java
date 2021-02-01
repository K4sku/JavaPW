package pl.edu.pw.ii.pte.patterns.decorator.example;

import java.util.HashMap;
import java.util.Map;

public class PasswdAuthService implements AuthenticationService {

	private Map<String, String> userPasswordsMap = new HashMap<String, String>();

	@Override
	public void registerUser(String name, String authString) {
		userPasswordsMap.put(name, authString);
	}

	@Override
	public boolean loginUser(String name, String authString) {
		return authString.equals(userPasswordsMap.get(name));
	}
}
