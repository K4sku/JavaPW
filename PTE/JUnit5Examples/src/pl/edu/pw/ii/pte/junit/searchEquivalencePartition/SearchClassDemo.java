package pl.edu.pw.ii.pte.junit.searchEquivalencePartition;

public class SearchClassDemo {
	static final int NMAX = 4;
	static int [] inputdata = new int [NMAX+1];
	
	public static void main(String[] args) {
		SearchClass s= new SearchClass();
		int key;
		
		s.fill_in(inputdata);
		s.print(inputdata);
		
		key = 120;
		System.out.printf("Key = ");
		System.out.println( key);

		System.out.printf("Index = ");
		System.out.println(s.search(inputdata,  key));
		
		key = inputdata[NMAX];
		System.out.printf("Key = ");
		System.out.println( key);

		System.out.printf("Index = ");
		System.out.println(s.search(inputdata,  key));
		//result can differ from NMAX, if exists earlier the same element
		s.print(inputdata);
	}

}

