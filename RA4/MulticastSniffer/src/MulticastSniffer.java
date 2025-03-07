// Java Network Programming. Fourth Edition
// Rusty Harold, Elliotte (2014). O�Reilly Media

import java.io.*;
import java.net.*;

public class MulticastSniffer {
	public static void main(String[] args) {
		InetAddress group = null;
		int port = 0;
		
		// lee la direcci�n desde la l�nea de �rdenes
		try {
			group = InetAddress.getByName(args[0]);
			port = Integer.parseInt(args[1]);
		} catch (ArrayIndexOutOfBoundsException | NumberFormatException | UnknownHostException ex) {
			System.err.println("Uso: java MulticastSniffer direccion_multicast puerto");
			System.exit(1);
		}
		
		MulticastSocket ms = null;
		try {
			ms = new MulticastSocket(port);
			ms.joinGroup(group);
			byte[] buffer = new byte[8192];
			while (true) {
				DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
				ms.receive(dp);
				String s = new String(dp.getData(), "8859_1");
				System.out.println(s);
			}
		} catch (IOException ex) {
			System.err.println(ex);
		} finally {
			if (ms != null) {
				try {
					ms.leaveGroup(group);
					ms.close();
				} catch (IOException ex) {
				}
			}
		}
	}
}