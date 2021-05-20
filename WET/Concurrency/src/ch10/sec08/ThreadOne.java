package ch10.sec08;

public class ThreadOne {

    public static void main(String[] args) throws InterruptedException {
    	Runnable interruptibleTask = () -> {
            int interuptionAt = (int) (Math.random()*20);
            try {
                for (int i = 1; i <= 10; i++) {
                    System.out.print(i + " ");
                    Thread.sleep(100);
                    if (i == interuptionAt) throw new IllegalStateException();
                    
                }
                System.out.println("Finished!");
            }
            catch (InterruptedException ex) {
                System.out.println("Interrupted!");
            }
        };
        
       Thread thread = new Thread(interruptibleTask);
       thread.setUncaughtExceptionHandler((t, ex) -> System.out.println("Yikes!"));
        thread.start();
        int interuptionTime = (int) (Math.random()*2000);
        Thread.sleep(interuptionTime);
        thread.interrupt();         

	}

}
