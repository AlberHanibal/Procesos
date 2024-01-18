public class EjemploDeadlock {
	
	private static Object recurso1 = "impresora";
	private static Object recurso2 = "modem";
	
	private static class Hilo extends Thread {
		Object recursoA, recursoB;
		   
		Hilo( String nombreHilo, Object recursoA, Object recursoB) {
			super(nombreHilo);
			this.recursoA = recursoA;
			this.recursoB = recursoB;
		}
		   
		public void run() {
			System.out.println(getName() +
				": Solicitando bloqueo de " + recursoA);
			synchronized (recursoA) {
				System.out.println(getName() + ": Bloqueado " + recursoA);
				try { Thread.sleep(10); }
				catch (InterruptedException e) {}
				
				System.out.println(getName() +
					": Solicitando bloqueo de " + recursoB);      
				synchronized (recursoB) {
					System.out.println(getName() +
						": Bloqueados " + recursoA + " y " + recursoB);
				}
				System.out.println(getName() +
					": Liberado bloqueo sobre " + recursoB);
			}
			System.out.println(getName() +
				": Liberado bloqueo sobre " + recursoA);
		}
		
	}
	      
	public static void main(String[] args) {
		Thread t1 = new Hilo("Hilo 1-2", recurso1, recurso2);
		Thread t2 = new Hilo("Hilo 2-1", recurso2, recurso1);
		t1.start();
		t2.start();
	}
}