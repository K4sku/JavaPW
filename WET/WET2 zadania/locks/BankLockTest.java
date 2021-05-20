package locks;

/**
 * This program shows how multiple threads can safely access a data structure.
 * @version 1.32 2018-04-10
 * @author Cay Horstmann
 */
public class BankLockTest
{
   public static final int NACCOUNTS = 100;
   public static final double INITIAL_BALANCE = 1000;
   public static final double MAX_AMOUNT = 1000;
   public static final int DELAY = 10;
   
   public static void main(String[] args)
   {
      BankLock bank = new BankLock(NACCOUNTS, INITIAL_BALANCE);
      for (int i = 0; i < NACCOUNTS; i++)
      {
         int fromAccount = i;
         Runnable r = () -> {
            try
            {
               while (true)
               {
                  int toAccount = (int) (bank.size() * Math.random());
                  double amount = MAX_AMOUNT * Math.random();
                  bank.transfer(fromAccount, toAccount, amount);
                  Thread.sleep((int) (DELAY * Math.random()));
               }
            }
            catch (InterruptedException e)
            {
            }            
         };
         
         Runnable addIntrests = () -> {
        	 try {
        		 while (true) {
                     int account = (int) (bank.size() * Math.random());
        			 bank.intrests(account);
                     Thread.sleep((int) (DELAY * Math.random()));
        		 }
        	 }
        	 catch (InterruptedException e) {}
         };
         
         Runnable addIntrests2 = () -> {
        	 try {
        		 while (true) {
                     int account = (int) (bank.size() * Math.random());
        			 bank.intrests2(account, 1200);
                     Thread.sleep((int) (DELAY * Math.random()));
        		 }
        	 }
        	 catch (InterruptedException e) {}
         };
         
         Runnable addIntrests3 = () -> {
        	 try {
        		 while (true) {
                     int account = (int) (bank.size() * Math.random());
        			 bank.intrests3(account, 2000);
                     Thread.sleep((int) (DELAY * Math.random()));
        		 }
        	 }
        	 catch (InterruptedException e) {}
         };
         
         Thread t = new Thread(r);
         t.start();
         
         Thread x = new Thread(addIntrests2);
         x.start();
         
         Thread y = new Thread(addIntrests);
         y.start();
         
         Thread z = new Thread(addIntrests3);
         z.start();
      }
   }
}
