package pl.edu.pw.ii.pte.junit.money;

class Money {
	private int fAmount;
	private String fCurrency;

	public Money(int amount, String currency) {
		fAmount = amount;
		fCurrency = currency;
	}

	public int amount() {
		return fAmount;
	}

	public String currency() {
		return fCurrency;
	}

	public Money add(Money m) {
		return new Money(amount() + m.amount(), currency());
	}

	public boolean equals(Object anObject) {
		if (anObject instanceof Money) {
			Money a = (Money) anObject;

			return a.currency().equals(currency()) && amount() == a.amount();
		}
		return false;

	}
	
	public Money multiplyCurrency(int k) {
		return new Money(amount() * k, currency());
	}
}