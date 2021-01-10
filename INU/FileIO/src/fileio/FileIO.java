package fileio;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class FileIO {

	public static void main(String[] args) {
		Scanner in = null;
		int id;
		String firstName;
		String lastName;
		ArrayList<Person> personList = new ArrayList();
		PrintWriter out=null;
		
		try {
			in = new Scanner(Paths.get("C:\\JavaPW\\JavaPW\\INU\\FileIO\\src\\fileio\\infile.txt"));
			while (in.hasNext()) {
				id=in.nextInt();
				firstName=in.next();
				lastName=in.next();
				personList.add(new Person(id, firstName, lastName));
				
				System.out.printf(
						"Wczytane dane -> id:%03d, imie:%-12s, nazwisko:%-12s%n", 
						id, firstName, lastName
						);
			}
			out = new PrintWriter("C:\\JavaPW\\JavaPW\\INU\\FileIO\\src\\fileio\\outfile.txt");
			for (int i =0; i<personList.size(); i++) {
				out.printf("%s:%s:%d%n", 
						personList.get(i).getFirstName(), 
						personList.get(i).getLastName(), 
						personList.get(i).getId()
						);
			}
			out.flush();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
