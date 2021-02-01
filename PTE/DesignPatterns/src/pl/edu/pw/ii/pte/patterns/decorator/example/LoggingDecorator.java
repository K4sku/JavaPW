package pl.edu.pw.ii.pte.patterns.decorator.example;

public class LoggingDecorator extends AuthDecorator {

	public LoggingDecorator(AuthenticationService s) {
		super(s);
	}

	@Override
	public boolean loginUser(String name, String authString) {
		System.out.println("Trying to log user: " + name);
		return super.loginUser(name, authString);
	}
}
