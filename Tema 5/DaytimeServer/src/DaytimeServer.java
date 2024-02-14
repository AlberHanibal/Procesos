//An Introduction to Network Programming with Java
//Jan Graba (2013). Springer London

import java.net.*;
import java.io.*;
import java.util.Date;

public class DaytimeServer {
	public static void main(String[] args) {
		final int DAYTIME_PORT = 13;
		ServerSocket server;
		Socket socket;

		try {
			server = new ServerSocket(DAYTIME_PORT);

			do {
				socket = server.accept();
				PrintWriter output = new PrintWriter(socket.getOutputStream(),
						true);
				Date date = new Date();
				output.println(date); 
				socket.close();
			} while (true);
		} catch (IOException ioEx) {
			System.out.println(ioEx);
		}
	}
}