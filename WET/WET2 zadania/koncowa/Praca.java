package koncowa;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.security.Key;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;


import javax.crypto.Cipher;




public class Praca {

	public static void main(String[] args) {

		 TransferQueue<byte[]> loadFileQueue = new LinkedTransferQueue<>();
		TransferQueue<byte[]> decryptQueue = new LinkedTransferQueue<>();
//				LinkedBlockingDeque<>(1);
		final String inputFile = "G:\\JavaPW\\WET\\WET2 zadania\\koncowa\\longlorem.txt";
		final String outputFile = "G:\\JavaPW\\WET\\WET2 zadania\\koncowa\\output.txt";
		final String keyFile = "G:\\JavaPW\\WET\\Security\\key.txt";
		
		Runnable queueFile = () ->{
			try(FileInputStream fstream = new FileInputStream(inputFile)) {
	            byte[] inBytes = new byte[600];
	            while ((fstream.read(inBytes)) != -1) {
	            	loadFileQueue.transfer(inBytes);
	            }
			} catch (Exception ex) {
				System.out.println(ex);
			}
		};
		
		Runnable encode = () ->{
		     try (ObjectInputStream keyIn = new ObjectInputStream(new FileInputStream(keyFile)))
		     {

		    	ByteArrayOutputStream out = new ByteArrayOutputStream();
		        Key key = (Key) keyIn.readObject();
		        Cipher cipher = Cipher.getInstance("AES");
		        cipher.init(Cipher.ENCRYPT_MODE, key);
		        while(true) {
		        	ByteArrayInputStream in = new ByteArrayInputStream(loadFileQueue.take());
		        	AesUtil.crypt(in, out, cipher);
		        	decryptQueue.transfer(out.toByteArray());
		        }
		        
		     } catch (Exception ex) {
		    	 System.out.println(ex);
		     } 
		};
		
		Runnable decode = () ->{
	         try (ObjectInputStream keyIn = new ObjectInputStream(new FileInputStream(keyFile)))
	         {

	            OutputStream out = new FileOutputStream(outputFile);
	            Key key = (Key) keyIn.readObject();
	            Cipher cipher = Cipher.getInstance("AES");
	            cipher.init(Cipher.DECRYPT_MODE, key);
		        while(true) {
		        	ByteArrayInputStream in = new ByteArrayInputStream(decryptQueue.take());
		            AesUtil.crypt(in, out, cipher);
		        }

	         } catch (Exception ex) {
		    	 System.out.println(ex);
		     } 
		};
		
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(queueFile);
        
        executor.execute(encode);
        executor.execute(decode);
        executor.shutdown();


	}

}
