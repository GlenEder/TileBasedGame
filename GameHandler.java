

public class GameHandler implements Runnable{
	
	private Thread thread;
	private boolean running = false;
	private GraphicsHandler graphics;
	private int tpsCap = 60;

	public static int TPS = 0;

	public GameHandler() {

	}

	private void tick() {
		Player.tick();
	}

	public void run() {
		init();

		long now = System.nanoTime();
		long lastTime = now;
		long second = 1000000000;
		long timer = second / tpsCap;
		long sleepTime;

		while(running) {
			lastTime = now;
			tick();
			now = System.nanoTime();
			sleepTime = (timer - (now - lastTime));
			try {
				if(sleepTime > 0) {
					thread.sleep(sleepTime / 1000000);		//converts ns to millsec
				}
			}catch (Exception e) {
				e.printStackTrace();
			}

			if(now - lastTime != 0) {
				TPS = (int)(second / (now - lastTime));
			}
			
		}
	}

	private void init() {
		graphics = new GraphicsHandler();
		graphics.start();

		MapHandler.createMap(20, 30, 3);
		//MapHandler.printNumMap();

	}

	public synchronized void start() {
		if(thread == null) {
			System.out.println("Starting game handler thread");
			running = true;
			thread = new Thread(this);
			thread.start();
		}
	}


}