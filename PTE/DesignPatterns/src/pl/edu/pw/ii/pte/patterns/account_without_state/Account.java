package pl.edu.pw.ii.pte.patterns.account_without_state;

public class Account {

	private int balance = 0; // suma kredytów
	private String owner = null; // w³aœciciel
	private boolean isOpen = false; // rachunek nieaktywny

	public Account(String owner, int balance) {

		this.owner = owner;
		this.balance = balance;
		this.isOpen = true; // rachunek aktywny
	}

	public void credit(int amount) { // udzielenie kredytu zale¿y od stanu
		if (isOpen) {
			this.balance += amount;
			System.out.println(balance);
		} else {
			throw new IllegalStateException("Konto nieaktywne!");
		}
	}

	public void close() { // zablokowanie konta
		this.isOpen = false;
	}

	
	public String getOwner() {
		return owner;
	}
}
