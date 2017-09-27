import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class InputHandler implements KeyListener {

	public static boolean UP = false;
	public static boolean DOWN = false;
	public static boolean RIGHT = false;
	public static boolean LEFT = false;

	public InputHandler() {
		System.out.println("Adding key listener");
		Window.frame.addKeyListener(this);
	}


	public void keyPressed(KeyEvent e) {

		int key = e.getKeyCode();

		if(key == KeyEvent.VK_SPACE) {
			MapHandler.createMap(200, 200, 2);
		}

		if(key == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		}

		if(key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
			UP = true;
		}
		if(key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
			DOWN = true;
		}
		if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
			RIGHT = true;
		} 
		if(key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
			LEFT = true;
		} 

	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if(key == KeyEvent.VK_UP || key == KeyEvent.VK_W) UP = false;
		if(key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) DOWN = false;
		if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) RIGHT = false;
		if(key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) LEFT = false;
	}

	public void keyTyped(KeyEvent e) {

	}
}