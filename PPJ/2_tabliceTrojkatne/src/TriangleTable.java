import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class TriangleTable {

	public static void main(String[] args) {

		//read user input data
		Scanner input = new Scanner(System.in);
		System.out.print("Set table diemnsion [nXn]: \nn:");
		int n = input.nextInt();
		
		System.out.print("Set random generator range [-z..z] \nz::");
		int z = input.nextInt();
		input.close();
		
		// create n x m array with random numbers from -z..z range
		int[][] randTable = new int[n][n];
		
		for(int i = 0; i < randTable.length; i++) {
			for(int j = 0; j < randTable[i].length; j++) {
				randTable[i][j] = ThreadLocalRandom.current().nextInt(-z, z + 1);
			}
		}
		int zLength = (int) (Math.log10(z) + 1);
		for(int i = 0; i < randTable.length; i++) {
			for(int j = 0; j <= i; j++) {
				int printSpacing = zLength+2;
				System.out.printf("%"+printSpacing+"d", randTable[i][j]);
			}
			System.out.println();
		}
	}
}