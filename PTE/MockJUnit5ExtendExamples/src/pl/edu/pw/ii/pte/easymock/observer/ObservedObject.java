package pl.edu.pw.ii.pte.easymock.observer;

public class ObservedObject {
	private Observer observer = null;
	public void add(Observer o) {
		observer = o;
	}
	
	protected void notifyAllObservers() {
		observer.dataChanged();
	}
}
