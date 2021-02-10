package pl.edu.pw.ii.pte.patterns.account_with_state;

public class Account {

	private int balance = 0; // suma kredyt�w
	private String owner = null; // w�a�ciciel
	private AccountState state = null; // rachunek nieaktywny

	public Account(String owner, int balance) {

		this.owner = owner;
		this.balance = balance;
		this.state = new AccountOpen(); // rachunek aktywny
	}

	public void credit(int amount) { // udzielenie kredytu zale�y od stanu
		this.state.credit(this, amount);
	}

	public void close() { // zablokowanie konta
		this.state = new AccountClosed();
	}
	
	public void suspend() { // zawieszenie konta
		this.state = new AccountSupended();
	}

	public void incBalance(int amount) {
		this.balance += amount;
		System.out.println(balance);
	}

	public String getOwner() {
		return owner;
	}
	
	public int getBalance() {
		return balance;
	}
}
