package rsa;

import java.io.*;
import java.security.*;
import javax.crypto.*;

/**
 * This program tests the RSA cipher. Usage:<br>
 * java rsa.RSATest -genkey public private<br>
 * java rsa.RSATest -encrypt plaintext encrypted public<br>
 * java rsa.RSATest -decrypt encrypted decrypted private<br>
 * @author Cay Horstmann
 * @version 1.01 2012-06-10 
 */
public class RSATestDECRYPT
{
   private static final int KEYSIZE = 512;

   public static void main(String[] args) 
      throws IOException, GeneralSecurityException, ClassNotFoundException
   {
	   if (args[0].equals("-decrypt"))
       {
         try (DataInputStream in = new DataInputStream(new FileInputStream(args[1]));
            ObjectInputStream keyIn = new ObjectInputStream(new FileInputStream(args[3]));
            OutputStream out = new FileOutputStream(args[2]))
         {
            int length = in.readInt();
            byte[] wrappedKey = new byte[length];
            in.read(wrappedKey, 0, length);

            // unwrap with RSA private key
            Key privateKey = (Key) keyIn.readObject();
   
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.UNWRAP_MODE, privateKey);
            Key key = cipher.unwrap(wrappedKey, "AES", Cipher.SECRET_KEY);
   
            cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, key);
   
            Util.crypt(in, out, cipher);
         }
      }
   }
}
