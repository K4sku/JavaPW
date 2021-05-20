package aes;

import java.io.*;
import java.security.*;
import javax.crypto.*;

/**
 * This program tests the AES cipher. Usage:<br>
 * java aes.AESTest -genkey keyfile<br>
 * java aes.AESTest -encrypt plaintext encrypted keyfile<br>
 * java aes.AESTest -decrypt encrypted decrypted keyfile<br>
 * @author Cay Horstmann
 * @version 1.01 2012-06-10
 */
public class AESTestENCRYPT
{
   public static void main(String[] args) 
      throws IOException, GeneralSecurityException, ClassNotFoundException
   {
     int mode;
     if (args[0].equals("-encrypt")) {
    	 mode = Cipher.ENCRYPT_MODE;
	     try (ObjectInputStream keyIn = new ObjectInputStream(new FileInputStream(args[3]));
	        InputStream in = new FileInputStream(args[1]);
	        OutputStream out = new FileOutputStream(args[2]))
	     {
	        Key key = (Key) keyIn.readObject();
	        Cipher cipher = Cipher.getInstance("AES");
	        cipher.init(mode, key);
	        Util.crypt(in, out, cipher);
	     } 
     }
   }
}

