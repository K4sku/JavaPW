package pl.edu.pw.ii.pte.junit.moneyAssigment;

import java.util.HashMap;

class Money {
	private int fAmount;
	private String fCurrency;
	private static HashMap<String, Integer> exchangeRate = new HashMap<>(); 

	public Money(int amount, String currency) {
		fAmount = amount;
		fCurrency = currency;
		
		exchangeRate.put("PLN", 10000);
		exchangeRate.put("USD", 37142);
		exchangeRate.put("EUR", 45272);
		exchangeRate.put("CHF", 41855);
	}

	public int amount() {
		return fAmount;
	}

	public String currency() {
		return fCurrency;
	}
	
	public Money addAnyCurrency(Money m) {
		var val = (fAmount * exchangeRate.get(fCurrency) + m.fAmount * m.exchangeRate.get(m.fCurrency)) / exchangeRate.get(fCurrency);
		return new Money(val, fCurrency);
	}

	public Money add(Money m) {
		if (currency() == m.currency())	return new Money(amount() + m.amount(), currency());
		else return addAnyCurrency(m);
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