// Java Network Programming. Fourth Edition
// Rusty Harold, Elliotte (2014). O´Reilly Media

import java.io.*;
import java.net.*;

public class MulticastSender {
	public static void main(String[] args) {
		InetAddress ia = null;
		int port = 0;
		byte ttl = (byte) 1;
		
		// lee la dirección desde la línea de órdenes
		try {
			ia = InetAddress.getByName(args[0]);
			port = Integer.parseInt(args[1]);
			if (args.length > 2)
				ttl = (byte) Integer.parseInt(args[2]);
		} catch (NumberFormatException | IndexOutOfBoundsException | UnknownHostException ex) {
			System.err.println(ex);
			System.err.println("Uso: java MulticastSender direccion_multicast puerto ttl");
			System.exit(1);
		}
		
		byte[] data = "Esto son unos datos multicast\r\n".getBytes();
		DatagramPacket dp = new DatagramPacket(data, data.length, ia, port);
		try (MulticastSocket ms = new MulticastSocket()) {
			ms.setTimeToLive(ttl);
			for (int i = 1; i < 10; i++) {
				ms.send(dp);
			}
		} catch (SocketException ex) {
			System.err.println(ex);
		} catch (IOException ex) {
			System.err.println(ex);
		}
	}
}