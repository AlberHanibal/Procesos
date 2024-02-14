package cliente;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Cliente {

	public static void main(String[] args) {
		try (Socket socket = new Socket("localhost", 13)) {
			InputStream iS = socket.getInputStream();
			StringBuilder cadena = new StringBuilder();
			InputStreamReader iSR = new InputStreamReader(iS, "ASCII");
			for (int c = iSR.read(); c != -1; c = iSR.read())
				cadena.append((char) c);
			System.out.println(cadena);
		} catch (IOException ex) {
			// No se pudo conectar a time.nist.gov
		}

	}

}
