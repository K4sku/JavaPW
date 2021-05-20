package synces;

import java.util.*;

/**
 * A bank with a number of bank accounts that uses synchronization primitives.
 */
public class BankSynch
{
   private final double[] accounts;

   /**
    * Constructs the bank.
    * @param n the number of accounts
    * @param initialBalance the initial balance for each account
    */
   public BankSynch(int n, double initialBalance)
   {
      accounts = new double[n];
      Arrays.fill(accounts, initialBalance);
   }

   /**
    * Transfers money from one account to another.
    * @param from the account to transfer from
    * @param to the account to transfer to
    * @param amount the amount to transfer
    */
   public synchronized void transfer(int from, int to, double amount) 
         throws InterruptedException
   {
      while (accounts[from] < amount)
         wait();
	  System.out.print("transfer  - " + Thread.currentThread());
      accounts[from] -= amount;
      System.out.printf(" %10.2f from %d to %d", amount, from, to);
      accounts[to] += amount;
      System.out.printf(" Total Balance: %10.2f%n", getTotalBalance());
      notifyAll();
   }

   /**
    * Gets the sum of all account balances.
    * @return the total balance
    */
   public synchronized double getTotalBalance()
   {
      double sum = 0;

      for (double a : accounts)
         sum += a;

      return sum;
   }

   /**
    * Gets the number of accounts in the bank.
    * @return the number of accounts
    */
   public int size()
   {
      return accounts.length;
   }
   
   public synchronized void intrests(int accNumber) throws InterruptedException {
	   accounts[accNumber] = accounts[accNumber]*1.01;
	   System.out.print("intrests  - " + Thread.currentThread());
	   System.out.printf(" Account %d new balance %7.2f%n", accNumber, accounts[accNumber]);
	   notifyAll();
   }
   
   public synchronized void intrests2(int accNumber, int minimalBalance) throws InterruptedException {
	   while (accounts[accNumber] < minimalBalance) 
		   wait();
	   accounts[accNumber] = accounts[accNumber]*1.01;
	   System.out.print("intrests2 - " + Thread.currentThread());
	   System.out.printf(" Account %d new balance %7.2f%n", accNumber, accounts[accNumber]);
	   notifyAll();
   }
   
   public synchronized void intrests3(int accNumber, int minimalBalance) throws InterruptedException {
	   while (accounts[accNumber] < minimalBalance) 
		   wait(1);
	   accounts[accNumber] = accounts[accNumber]*1.01;
	   System.out.print("intrests3 - " + Thread.currentThread());
	   System.out.printf(" Account %d new balance %7.2f%n", accNumber, accounts[accNumber]);
	   notifyAll();
   }
}
