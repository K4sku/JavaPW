package pl.edu.pw.ii.pte.patterns.account_with_state;

public class AccountOpen implements AccountState {

	@Override
	public void credit(Account acc, int amount) {
		acc.incBalance(amount);
	}

}
