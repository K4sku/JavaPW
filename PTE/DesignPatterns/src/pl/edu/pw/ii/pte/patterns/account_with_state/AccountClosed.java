package pl.edu.pw.ii.pte.patterns.account_with_state;

public class AccountClosed implements AccountState {
	
	@Override
	public void credit(Account acc, int amount) {
		throw new IllegalStateException("Konto nieaktywne!");
	}

}
