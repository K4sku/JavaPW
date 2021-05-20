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
public class RSATestGEN
{
   private static final int KEYSIZE = 512;

   public static void main(String[] args) 
      throws IOException, GeneralSecurityException, ClassNotFoundException
   {
      if (args[0].equals("-genkey"))
      {
         KeyPairGenerator pairgen = KeyPairGenerator.getInstance("RSA");
         SecureRandom random = new SecureRandom();
         pairgen.initialize(KEYSIZE, random);
         KeyPair keyPair = pairgen.generateKeyPair();
         try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(args[1])))
         {
            out.writeObject(keyPair.getPublic());
         }
         try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(args[2])))
         {
            out.writeObject(keyPair.getPrivate());
         }
      }
      
   }
}
