import javax.swing.JFrame;
import java.awt.Canvas;
import java.awt.Dimension;

public class Window {

	public static int width;
	public static int height;
	public static JFrame frame;
	public static Canvas canvas;

	private static String title;

	public static void createWindow(int WIDTH, int HEIGHT, String gameTitle) {

		width = WIDTH;
		height = HEIGHT;
		title = gameTitle;

		frame = new JFrame(title);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setUndecorated(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);

		width = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;
		height = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;


		canvas = new Canvas();
		canvas.setSize(width, height);
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setVisible(true);


		frame.add(canvas);

	}
}