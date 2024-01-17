import java.util.concurrent.*;

public class BeeperControl {
	private final ScheduledExecutorService scheduler =	Executors.newScheduledThreadPool(1);
	
	public void beepForAMinute() {
		
		final Runnable beeper = new Runnable() {
			public void run() {
				System.out.println("¡BEEP!");
			}
		};
		
		final ScheduledFuture<?> future = scheduler.scheduleAtFixedRate(beeper, 250, 250, TimeUnit.MILLISECONDS);
		
		scheduler.schedule(
			new Runnable() {
				public void run(){
					future.cancel(true);
					System.out.println("Beeper parado");
				}
			}, 3, TimeUnit.SECONDS);
		
		while (!future.isDone()) {
			try {
				Thread.sleep(10);
			} catch(InterruptedException e) {}
		}
		
		scheduler.shutdown();
		
		System.out.println("Adiós");
	}
	
	public static void main(String[] args) {
		BeeperControl bc = new BeeperControl();
		bc.beepForAMinute();
	}
}