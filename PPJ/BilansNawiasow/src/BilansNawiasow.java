import java.io.File;
import java.util.Scanner;

public class BilansNawiasow {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 
		try {
			System.out.print("Enter the file name with extension : ");

            Scanner input = new Scanner(System.in);

//            File file = new File(input.nextLine());
            File file = new File("HelloWorld.txt");

            input = new Scanner(file);
       
            String sameNawiasy = "";
            
            sprawdzanie_programu:
            while (input.hasNextLine()) {
            	
                String line = input.nextLine();
                line = line.trim();
                for(int i = 0; i < line.length(); i++) {
                	if(czyOtwarcie(line.charAt(i))) {
                		sameNawiasy = sameNawiasy + line.charAt(i); 
                	}
                	else if (czyZamkniecie(line.charAt(i))) {
                		if (sameNawiasy.length() > 0) {
	                		switch (line.charAt(i)) {
	                		case ')':
	                			if (sameNawiasy.charAt(sameNawiasy.length()-1) == (line.charAt(i) -1)) {
	                				sameNawiasy = usunOstatniZnak(sameNawiasy);
	                			} else {
	                				break sprawdzanie_programu;
	                			}
	                			break;
	                		case '}':
	                		case ']':
	                			if (sameNawiasy.charAt(sameNawiasy.length()-1) == (line.charAt(i) -2)) {
	                				sameNawiasy = usunOstatniZnak(sameNawiasy);
	                			} else {
	                				break sprawdzanie_programu;
	                			}
	                			break;
	                		}
                		} else {
                			sameNawiasy = "Something went wrong";
                			break sprawdzanie_programu;
                		}
                	}
                }
            }
            input.close();
            if (sameNawiasy.length() != 0) {
            	System.out.println("O fuj, zły kod!");
            } else {
            	System.out.println("Wygląda OK, ale ja się nie znam");
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
	}
	
	static private boolean czyOtwarcie(char c) {
		char[] klamry = {'(', '{', '['};
			for (char klamra : klamry) {
				if(klamra == c) {
					return true;
				}
			}
		return false;
	}
	
	static private boolean czyZamkniecie(char c) {
		char[] klamry = {')', '}', ']'};
			for (char klamra : klamry) {
				if(klamra == c) {
					return true;
				}
			}
		return false;
	}
	
	static private String usunOstatniZnak(String s) {
		String result = null;
	   if ((s != null) && (s.length() > 0)) {
	      result = s.substring(0, s.length() - 1);
	   }
	   return result;
	}
}
