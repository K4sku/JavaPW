package pl.edu.pw.ii.pte.junit.moneyAssigment;

import java.util.HashMap;

class Money {
	private long fAmount;
	private String fCurrency;
	private static HashMap<String, Integer> exchangeRate = new HashMap<>(); 

	public Money(long amount, String currency) {
		fAmount = amount; //money is stored in notation: 12345 = 1.2345
		fCurrency = currency;
		
		exchangeRate.put("PLN", 1_0000); 
		exchangeRate.put("USD", 3_7142);
		exchangeRate.put("EUR", 4_5272);
		exchangeRate.put("CHF", 4_1855);
	}

	public long amount() {
		return fAmount;
	}

	public String currency() {
		return fCurrency;
	}
	
	public Money addAnyCurrency(Money m) {
		var val = (fAmount * Money.exchangeRate.get(fCurrency) + m.fAmount * Money.exchangeRate.get(m.fCurrency)) / Money.exchangeRate.get(fCurrency);
		return new Money(val, fCurrency);
	}

	public Money add(Money m) {
		if (currency() == m.currency())	return new Money(amount() + m.amount(), currency());
		else return addAnyCurrency(m);
	}

	public boolean equals(Object anObject) {
		if (anObject instanceof Money) {
			Money a = (Money) anObject;
			if (a.currency().equals(currency()) && amount() == a.amount()) return true;
			if (!a.currency().equals(currency())) {
				return (a.amount() * Money.exchangeRate.get(a.fCurrency) == amount() * Money.exchangeRate.get(fCurrency));
			}
		}
		return false;

	}
	
	public int compareTo(Object anObject) {
		Money a = (Money) anObject;
		if (equals(a)) return 0;
		else if ((a.amount() * Money.exchangeRate.get(a.fCurrency) > amount() * Money.exchangeRate.get(fCurrency))) return 1;
		else return -1;

	}
	
	public Money multiplyCurrency(int k) {
		return new Money(amount() * k, currency());
	}
}