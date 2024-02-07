import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

class Controlador {
	int babuinosEW = 0;
	int babuinosWE = 0;
	Semaphore mutexEW = new Semaphore(1);
	Semaphore mutexWE = new Semaphore(1);
	Semaphore liana = new Semaphore(1);
	int segundosCruzando = 5;

	public void cruzaEW(Babuino babuino) {
		try {
			System.out.println(babuino.getId() + ": Solicitud de cruzar al oeste");
			mutexEW.acquire();
			babuinosEW += 1;
			if (babuinosEW == 1) {
				liana.acquire();
				System.out.println(babuino.getId() + ": reservada liana (sentido al oeste)");
			}
			mutexEW.release();

			System.out.println(babuino.getId() + ": Cruzando al oeste");
			TimeUnit.SECONDS.sleep(segundosCruzando);
			System.out.println(babuino.getId() + ": Ya en el oeste");

			mutexEW.acquire();
			babuinosEW -= 1;
			if (babuinosEW == 0) {
				System.out.println(babuino.getId() + ": liberada liana (sentido al oeste)");
				liana.release();
			}
			mutexEW.release();
		} catch (InterruptedException iE) {
		}
	}

	public void cruzaWE(Babuino babuino) {
		try {
			System.out.println(babuino.getId() + ": Solicitud de cruzar al este");
			mutexWE.acquire();
			babuinosWE += 1;
			if (babuinosWE == 1) {
				liana.acquire();
				System.out.println(babuino.getId() + ": reservada liana (sentido al este)");
			}
			mutexWE.release();

			System.out.println(babuino.getId() + ": Cruzando al este");
			TimeUnit.SECONDS.sleep(segundosCruzando);
			System.out.println(babuino.getId() + ": Ya en el este");

			mutexWE.acquire();
			babuinosWE -= 1;
			if (babuinosWE == 0) {
				System.out.println(babuino.getId() + ": liberada liana (sentido al este)");
				liana.release();
			}
			mutexWE.release();
		} catch (InterruptedException iE) {
		}
	}
}

public class Babuino implements Runnable {
	static Controlador controlador = new Controlador();
	final static int SENTIDO_EW = 0;
	final static int SENTIDO_WE = 1;

	int id;
	int sentidoDeAvance;

	public Babuino(int id, int sentidoDeAvance) {
		this.id = id;
		this.sentidoDeAvance = sentidoDeAvance;
	}

	public int getId() {
		return this.id;
	}

	public void run() {
		try {
			while (true) {
				int segundosADormir = (int) Math.rint(Math.random() * 10);
				TimeUnit.SECONDS.sleep(segundosADormir);

				if (sentidoDeAvance == SENTIDO_EW)
					controlador.cruzaEW(this);
				else
					controlador.cruzaWE(this);

				sentidoDeAvance = (sentidoDeAvance + 1) % 2;
			}
		} catch (InterruptedException iE) {
		}
	}

	public static void main(String[] args) {
		int num_babuinos = (int) Math.rint(Math.random() * 20);
		System.out.println("Habrá " + num_babuinos + " babuinos");

		for (int contador = 0; contador < num_babuinos; contador++) {
			int sentidoDeAvance = (int) Math.rint(Math.random());
			Thread thread = new Thread(new Babuino(contador, sentidoDeAvance));
			thread.start();
		}
	}

}
