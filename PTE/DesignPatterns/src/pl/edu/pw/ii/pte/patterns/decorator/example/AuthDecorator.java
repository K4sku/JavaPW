package pl.edu.pw.ii.pte.patterns.decorator.example;

public abstract class AuthDecorator implements AuthenticationService {

	protected AuthDecorator(AuthenticationService s) {
		this.decoratedService = s;
	}
	
	@Override
	public boolean loginUser(String name, String authString) {
		return decoratedService.loginUser(name, authString);
	}
	
	@Override
	public void registerUser(String name, String authString) {
		decoratedService.registerUser(name, authString);
	}
	
	private AuthenticationService decoratedService;
}
