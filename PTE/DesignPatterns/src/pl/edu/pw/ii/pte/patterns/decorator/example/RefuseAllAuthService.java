package pl.edu.pw.ii.pte.patterns.decorator.example;

public class RefuseAllAuthService implements AuthenticationService {

	@Override
	public boolean loginUser(String name, String authString) {
		return false;
	}

	@Override
	public void registerUser(String name, String authString) {
	}

}
