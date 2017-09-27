import java.awt.Graphics;
import java.awt.Color;

public class Player {

	public static double xPos = 0.0;
	public static double yPos = 0.0;
	public static int playerRadius = 12;
	
	private static double speed = 3.5;
	private static int rightBorder;
	private static int bottomBorder;

	public static void tick() {
		rightBorder = (MapHandler.map[0].length * MapHandler.tileSize * -1) + playerRadius;
		bottomBorder = (MapHandler.map.length * MapHandler.tileSize) - playerRadius;

		double tempx = xPos;
		double tempy = yPos;

		if(InputHandler.UP) {
			tempy -= speed;
		}
		if(InputHandler.DOWN) {
			tempy += speed;
		}
		if(InputHandler.RIGHT) {
			tempx -= speed;
		}
		if(InputHandler.LEFT) {
			tempx += speed;
		}

		//System.out.println("xPos: " + xPos + " || yPos: " + yPos);

		if(tempx > 0) {
			tempx = 0;
		}

		if(tempx < rightBorder) {
			tempx = rightBorder;
		}

		if(tempy < 0) {
			tempy = 0;
		}

		if(tempy > bottomBorder) {
			tempy = bottomBorder;
		}

		int currentTile = MapHandler.getTileNumber((int)tempx, (int)tempy);
		//System.out.println("currentTile: " + currentTile);

		if(currentTile < MapHandler.maxGrass) {
			xPos = tempx;
			yPos = tempy;
		}
		
	}

	public static void render(Graphics g) {
		g.setColor(Color.red);
		g.fillOval((Window.width / 2) - playerRadius, (Window.height / 2) - playerRadius, playerRadius, playerRadius); 
	}
}