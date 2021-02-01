package pl.edu.pw.ii.pte.patterns.decorator.example;

public interface AuthenticationService {
	boolean loginUser(String name, String authString);
	void registerUser(String name, String authString);
}
