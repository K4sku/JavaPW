package pl.edu.pw.ii.pte.refactoring.move.method.pre;

public class TypKonta {
	
	private boolean superKonto;
	
	public TypKonta (boolean typ) {
		superKonto = typ;
	}
	
	public boolean czySuperKonto() {
		return superKonto;
	}

	public void enableSuperKonto() {
		superKonto = true;
	}
	
	public void disableSuperKonto() {
		superKonto = false;
	}

	}
