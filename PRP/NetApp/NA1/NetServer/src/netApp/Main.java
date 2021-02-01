package netApp;

public class Main {

	// args[]: server|client port_number [server_address]
	public static void main(String[] args) throws Exception {
		if (args.length < 2) return;
			int portNumber = Integer.parseInt(args[1]);
		if (args[0].equalsIgnoreCase("server")) {
			NetServer server = new NetServer(portNumber);
			System.out.println("Server is running");
			server.run();
			server.close();
		} else if (args[0].equalsIgnoreCase("client")) {
			String serverAddress = args[2];
			NetClient client = new NetClient(serverAddress, portNumber);
			System.out.print(client.getResponse());
			client.close();
		}
	}
}
