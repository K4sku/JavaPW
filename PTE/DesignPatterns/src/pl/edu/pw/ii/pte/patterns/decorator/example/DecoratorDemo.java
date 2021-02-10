package pl.edu.pw.ii.pte.patterns.decorator.example;

public class DecoratorDemo {

	public static void main(String[] args) {
		AuthenticationService authService;

		if (args.length > 0 && args[0].equals("refuse")) {
			authService = new RefuseAllAuthService();
		} else if(args[0].equals("random")) {
			authService = new RandomAuthService();
		} else {
			authService = new PasswdAuthService();
		}
		
		authService = new LoggingDecorator(authService);
		
		authService.registerUser("admin", "qwer1234");
		System.out.println(authService.loginUser("admin", "qwer1234") ? "Logged" : "Not logged");
		
		authService.registerUser("user", "123");
		System.out.println(authService.loginUser("user", "123") ? "Logged" : "Not logged");
		
		authService.registerUser("admin", "qwer123443");
		System.out.println(authService.loginUser("admin", "qwer1234") ? "Logged" : "Not logged");
	}
}
