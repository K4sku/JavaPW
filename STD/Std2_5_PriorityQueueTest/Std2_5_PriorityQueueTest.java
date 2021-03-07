import java.util.*;

public class Std2_5_PriorityQueueTest{
   public static void main(String [] args){
     AbstractQueue<Integer> ipq = new PriorityQueue<>();
     //PriorityQueue<Integer> ipq = new PriorityQueue<>();
     Scanner scn = new Scanner(System.in);
     Random rnum = new Random();
     
     System.out.println("Operacje na kolejce PriorityQueue<Integer> ");
     while(true ){  // Pętla konwersacji
       int n, z;
 
       System.out.print("\nIle liczb generować: n = ");
       n = scn.nextInt();
       if(n <= 0) break;

       ipq.clear();
       System.out.print("Jaki zakres 0..z: z = ");
       z = scn.nextInt();

       System.out.println("Dane z generatora: ");
       for(int i=0; i<n; ++i) {
         int r = rnum.nextInt(z+1);
         System.out.print(r+" ");
         ipq.add(r);
       }
       System.out.println();

       System.out.println("Podgląd kolejki poprzez Arrays.toString()");
       System.out.println(Arrays.toString(ipq.toArray()));
       
       System.out.println("Podgląd zawartości kolejki poprzez iterator");
       for(Integer e : ipq)
         System.out.print(e + " ");
       System.out.println();

       System.out.println("\nPobieranie z kolejki przez poll()");
       while(!ipq.isEmpty())
         System.out.print(ipq.poll() + " ");
       System.out.println();
     }
     scn.close();
     System.out.println("\nKONIEC PROGRAMU");     
   }
}
