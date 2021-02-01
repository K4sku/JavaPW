package netApp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class NetClient implements AutoCloseable {
	private Socket socket;
	private InputStream inputStream;
	
	public NetClient(String serverAddress, int portNumber) throws UnknownHostException,	IOException {
		socket = new Socket(serverAddress, portNumber);
		inputStream = socket.getInputStream();
		}
		
	public String getResponse() {
	String response = "";
		try (InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
			response = bufferedReader.readLine();
		} catch (IOException e) { e.printStackTrace(); }
			return response;
		}
		
	@Override
	public void close() throws Exception {
	if (socket != null) socket.close();
	}
}