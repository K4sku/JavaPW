package abstractFactory;

public class GUIApplication {
	private Button button;
	private Checkbox checkbox;
	
	public GUIApplication(GUIFactory factory) {
		button=factory.createButton();
		checkbox=factory.createCheckbox();
	}
	
	public void paint() {
		button.paint();
		checkbox.paint();
	}
}
