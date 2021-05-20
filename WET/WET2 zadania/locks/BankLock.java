package locks;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.*;

/**
 * A bank with a number of bank accounts that uses locks for serializing access.
 */
public class BankLock
{
   private final double[] accounts;
   private Lock bankLock;
   private Condition sufficientFunds;

   /**
    * Constructs the bank.
    * @param n the number of accounts
    * @param initialBalance the initial balance for each account
    */
   public BankLock(int n, double initialBalance)
   {
      accounts = new double[n];
      Arrays.fill(accounts, initialBalance);
      bankLock = new ReentrantLock();
      sufficientFunds = bankLock.newCondition();
   }

   /**
    * Transfers money from one account to another.
    * @param from the account to transfer from
    * @param to the account to transfer to
    * @param amount the amount to transfer
    */
   public void transfer(int from, int to, double amount) throws InterruptedException
   {
      bankLock.lock();
      try
      {
         while (accounts[from] < amount)
            sufficientFunds.await();
         System.out.print("transfer  - " + Thread.currentThread());
         accounts[from] -= amount;
         System.out.printf(" %10.2f from %d to %d", amount, from, to);
         accounts[to] += amount;
         System.out.printf(" Total Balance: %10.2f%n", getTotalBalance());
         sufficientFunds.signalAll();
      }
      finally
      {
         bankLock.unlock();
      }
   }

   /**
    * Gets the sum of all account balances.
    * @return the total balance
    */
   public double getTotalBalance()
   {
      bankLock.lock();
      try
      {
         double sum = 0;

         for (double a : accounts)
            sum += a;

         return sum;
      }
      finally
      {
         bankLock.unlock();
      }
   }

   /**
    * Gets the number of accounts in the bank.
    * @return the number of accounts
    */
   public int size()
   {
      return accounts.length;
   }
   
   public void intrests(int accNumber) throws InterruptedException {
	   bankLock.lock();
	   try {
		   accounts[accNumber] = accounts[accNumber]*1.01;
		   System.out.print("intrests  - " + Thread.currentThread());
		   System.out.printf(" Account %d new balance %7.2f%n", accNumber, accounts[accNumber]);
		   
		   sufficientFunds.signalAll();
	   }
	   finally {
		   bankLock.unlock();
	   }
   }
   
   public void intrests2(int accNumber, int minimalBalance) throws InterruptedException {
	   bankLock.lock();
	   try {
		   while (accounts[accNumber] < minimalBalance) 
	            sufficientFunds.await();
		   accounts[accNumber] = accounts[accNumber]*1.01;
		   System.out.print("intrests2 - " + Thread.currentThread());
		   System.out.printf(" Account %d new balance %7.2f%n", accNumber, accounts[accNumber]);
		   
		   sufficientFunds.signalAll();
		   
	   }
	   finally {
		   bankLock.unlock();
	   }
   }
   
   public void intrests3(int accNumber, int minimalBalance) throws InterruptedException {
	   bankLock.lock();
	   try {
		   while (accounts[accNumber] < minimalBalance) {
			   	sufficientFunds.await(1,TimeUnit.SECONDS);
		   }
			   accounts[accNumber] = accounts[accNumber]*1.01;
			   System.out.print("intrests2 - " + Thread.currentThread());
			   System.out.printf(" Account %d new balance %7.2f%n", accNumber, accounts[accNumber]);
		   
		   sufficientFunds.signalAll();
		   
	   }
	   finally {
		   bankLock.unlock();
	   }
   }
}
