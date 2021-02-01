package abstractFactory;

public class LinuxCheckbox implements Checkbox {

	@Override
	public void paint() {
		System.out.println("(x) Linux checkbox");
	}

}
