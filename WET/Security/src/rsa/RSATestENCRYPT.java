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
public class RSATestENCRYPT
{
   private static final int KEYSIZE = 512;

   public static void main(String[] args) 
      throws IOException, GeneralSecurityException, ClassNotFoundException
   {
	   if (args[0].equals("-encrypt"))
      {
         KeyGenerator keygen = KeyGenerator.getInstance("AES");
         SecureRandom random = new SecureRandom();
         keygen.init(random);
         SecretKey key = keygen.generateKey();

         // wrap with RSA public key
         try (ObjectInputStream keyIn = new ObjectInputStream(new FileInputStream(args[3]));
            DataOutputStream out = new DataOutputStream(new FileOutputStream(args[2]));
            InputStream in = new FileInputStream(args[1]) )
         {
            Key publicKey = (Key) keyIn.readObject();
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.WRAP_MODE, publicKey);
            byte[] wrappedKey = cipher.wrap(key);
            out.writeInt(wrappedKey.length);
            out.write(wrappedKey);
         
            cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            Util.crypt(in, out, cipher);            
         }         
      }
   }
}
