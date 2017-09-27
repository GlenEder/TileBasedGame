import java.util.Random;
import java.awt.Graphics2D;
import java.awt.Color;

public class MapHandler {
	
	private static Random rand = new Random();

	public static int[][] map;
	public static int tileSize = 64;
	public static int maxGrass = 550;
	public static int maxWater = 400;
	

	public static void createMap(int width, int height, int numBuffers) {
		map = new int[height][width];
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				map[i][j] = rand.nextInt(1000);
			}
		}

		//buffer map
		for(int k = 0; k < numBuffers; k++) {
			for(int i = 0; i < height; i++) {
				for(int j = 0; j < width; j++) {
					int sum = map[i][j];
					int numElements = 1;
					if(i - 1 >= 0) {
						sum += map[i - 1][j];
						numElements++;
						if(j - 1 >= 0) {
							sum += map[i - 1][j - 1];
							numElements++;
						}

						if(j + 1 < width) {
							sum += map[i - 1][j + 1];
							numElements++;
						}
					}

					if(i + 1 < height) {
						sum += map[i + 1][j];
						numElements++;
						if(j - 1 >= 0) {
							sum += map[i + 1][j - 1];
							numElements++;
						}

						if(j + 1 < width) {
							sum += map[i + 1][j + 1];
							numElements++;
						}
					}

					if(j - 1 >= 0) {
							sum += map[i][j - 1];
							numElements++;
					}

					if(j + 1 < width) {
							sum += map[i][j + 1];
							numElements++;
					}

					map[i][j] = sum / numElements;
				}
			}
		}

		map[0][0] = 0;
		
	}

	public static int getTileNumber(int x, int y) {
		int i, j;
		i = y / tileSize;
		j = (x * -1) / tileSize;

		//System.out.println("I: " + i + " || J: " + j);
		return map[i][j];

	}

	public static void printNumMap() {
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map[i].length; j++) {
				System.out.print(" " + map[i][j]);
			}
			System.out.println("");
		}
	}

	public static int getStartI(int y) {
		int i = (y - (Window.height / 2) - tileSize) / tileSize;
		if(i < 0) {
			i = 0;
		}

		return i;
	}

	public static int getEndI(int y) {
		int i = (y + (Window.height / 2) + tileSize) / tileSize;
		if(i > map.length) {
			i = map.length;
		}

		return i;
	}

	public static int getStartJ(int x) {
		int j = ((x * -1) - (Window.width / 2) - tileSize) / tileSize;
		if(j < 0) {
			j = 0;
		} 

		return j;
	}

	public static int getEndJ(int x) {
		int j = ((x * -1) + (Window.width / 2) + tileSize) / tileSize;
		if(j > map[0].length) {
			j = map[0].length;
		}

		return j;
	}


	public static void render(Graphics2D g) {
		int tilesRendered = 0;
		for(int i = getStartI((int)Player.yPos); i < getEndI((int)Player.yPos); i++) {
			for(int j = getStartJ((int)Player.xPos); j < getEndJ((int)Player.xPos); j++) {
				if(map[i][j] < maxWater) {
					g.setColor(Color.blue);
				}else if (map[i][j] < maxGrass) {
					g.setColor(Color.green);
				}else {
					g.setColor(Color.gray);
				}

				int tileLocationI = i * tileSize;
				int tileLocationJ = j * tileSize;
				int windowHalfHeight = Window.height / 2;
				int windowHalfWidth = Window.width / 2;

			
				g.fillRect(tileLocationJ + (int)Player.xPos + windowHalfWidth - Player.playerRadius, tileLocationI - (int)Player.yPos + windowHalfHeight - Player.playerRadius, tileSize, tileSize);
				tilesRendered++;
				
			}
		}

		//System.out.println("Tiles rendered: " + tilesRendered);
	}
}