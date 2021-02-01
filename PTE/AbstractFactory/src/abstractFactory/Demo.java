package abstractFactory;

public class Demo {
	
	private static GUIApplication configureApplication() {
		GUIApplication app;
		GUIFactory factory;
		
		String osName=System.getProperty("os.name").toLowerCase();
		System.out.println(osName);
		
		if (osName.contains("linux")){
		  factory = new LinuxFactory();
		}
		else {
			factory = new WindowsFactory();	
		}
			
		app=new GUIApplication(factory);
		
		return app;
	}
	
	public static void main(String[] args) {
		GUIApplication app=configureApplication();
		app.paint();
	}

}
