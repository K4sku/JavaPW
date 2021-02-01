package abstractFactory;

public class LinuxButton implements Button {

	@Override
	public void paint() {
		System.out.println("[Linux button]");
	}

}
