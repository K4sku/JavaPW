package pl.edu.pw.ii.pte.patterns.account_with_state;

public class AccountDemo {

	public static void main(String[] args) {
		Account a = new Account("Ann", 0);
		a.credit(100);
		a.close();
		a.credit(50);
	}

}
