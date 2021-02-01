package pl.edu.pw.ii.pte.patterns.singleton;

public class LazySingleton {
	private static LazySingleton instance = null;

	public LazySingleton getInstance() {
		if (instance == null) {
			instance = new LazySingleton();
		}
		return instance;
	}

	private LazySingleton() {
	}
}
