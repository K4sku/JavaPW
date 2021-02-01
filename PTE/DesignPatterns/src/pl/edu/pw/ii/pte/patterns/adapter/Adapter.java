package pl.edu.pw.ii.pte.patterns.adapter;

public class Adapter implements TargetInterface {

	private Adaptee adaptee;

	public Adapter(Adaptee adaptee) {
		this.adaptee = adaptee;
	}

	@Override
	public void clientRequest() {
		adaptee.specialCall();
	}

}
