package pl.edu.pw.ii.pte.patterns.singleton;

public final class Singleton {
	private final static Singleton instance = new Singleton();

	public static Singleton getInstance() {
		return instance;
	}

	private Singleton() {
	}
}