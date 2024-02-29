// Java Network Programming. Fourth Edition
// Rusty Harold, Elliotte (2014). O´Reilly Media

import java.io.*;
import java.net.*;

public class SourceViewer {
	public static void main(String[] args) {
		if (args.length > 0) {
			try {
				URL u = new URL(args[0]);
				URLConnection uc = u.openConnection();
				try (InputStream raw = uc.getInputStream()) {
					InputStream buffer = new BufferedInputStream(raw);
					Reader reader = new InputStreamReader(buffer);
					int c;
					while ((c = reader.read()) != -1) {
						System.out.print((char) c);
					}
				}
			} catch (MalformedURLException ex) {
				System.err.println(args[0] + " no es una URL bien construida");
			} catch (IOException ex) {
				System.err.println(ex);
			}
		}
	}
}