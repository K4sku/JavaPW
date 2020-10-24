import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Tablica {
	
	public static void main(String[] args) {

		//read user input data
		Scanner input = new Scanner(System.in);
		System.out.print("Set table diemnsions [nXm] \nn:");
		int n = input.nextInt();
		System.out.print("m:");
		int m = input.nextInt();
		
		System.out.print("Set random generator range [-z..z] \nz:");
		int z = input.nextInt();
		int zLength = 0;
		input.close();
		
		// create n x m array with random numbers from -z..z range
		int[][] randTable = new int[n][m];
		
		System.out.println("Unformatted Table");
		for(int i = 0; i < randTable.length; i++) {
			int temLength = 0;
			for(int j = 0; j < randTable[i].length; j++) {
				randTable[i][j] = ThreadLocalRandom.current().nextInt(-z, z + 1);
				temLength = (int) (Math.log10(randTable[i][j]) + 1);
				if(zLength < temLength) zLength = temLength;
				
				System.out.print(randTable[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("\n\nFormatted Table");
		zLength = (int) (Math.log10(z) + 1);
		for (int[] row : randTable) {
			for (int value : row) {
				int printSpacing = zLength+2;
				System.out.printf("%"+printSpacing+"d", value);
			}
			System.out.println();
		}
	}
}
