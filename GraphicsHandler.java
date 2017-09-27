import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class GraphicsHandler extends JPanel implements Runnable{

	private Thread thread;
	private boolean running = false;
	private InputHandler input;
	private int FPS;
	private int fpsCap = 60;

	private int width = 1080;
	private int height = 720;
	private String title = "Game Title that Glen will come up with later!";

	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);


	public void run() {
		init();

		long now = System.nanoTime();
		long lastTime = now;
		long second = 1000000000;
		long timer = second / fpsCap;
		long sleepTime;
		int ups = 0;

		while(running) {
			lastTime = now;
			render();
			now = System.nanoTime();
			sleepTime = (timer - (now - lastTime));
			try {
				if(sleepTime > 0) {
					thread.sleep(sleepTime / 1000000);
				}
			}catch (Exception e) {
				e.printStackTrace();
			}

			if(now - lastTime != 0) {
				FPS = (int)(second / (now - lastTime));
			}
			
		}
	}

	private void init() {
		Window.createWindow(width, height, title);
		width = Window.width;
		height = Window.height;
		Window.frame.add(this);
		input = new InputHandler();
		Window.frame.setVisible(true);
	}

	public synchronized void start() {
		if(thread == null) {
			System.out.println("Starting graphics handler thread");
			running = true;
			thread = new Thread(this);
			thread.start();
		}
	}

	public int printNum = 0;
	public void render() {
		BufferStrategy bs = Window.canvas.getBufferStrategy();
		if(bs == null) {
			Window.canvas.createBufferStrategy(3);
			return;
		}

		if(printNum < 1) {
			System.out.println("Pageping: " + bs.getCapabilities().isPageFlipping());
			System.out.println("Multipleers: " + bs.getCapabilities().isMultiBufferAvailable());
			System.out.println("FSEMired: " + bs.getCapabilities().isFullScreenRequired());
			System.out.println("Backbufferlerated: " + bs.getCapabilities().getBackBufferCapabilities().isAccelerated());
			System.out.println("Frontbufferlerated: " + bs.getCapabilities().getFrontBufferCapabilities().isAccelerated());
			printNum++;
		}


		Graphics2D g = (Graphics2D)bs.getDrawGraphics();
		g.drawImage(image, 0, 0, width, height, null);

		//draw map
		MapHandler.render(g);

		//draw Player
		Player.render(g);

		//debugging
		g.setColor(Color.black);
		g.drawString("FPS: " + FPS, 15, 15);



		g.dispose();
		bs.show();
	}
}