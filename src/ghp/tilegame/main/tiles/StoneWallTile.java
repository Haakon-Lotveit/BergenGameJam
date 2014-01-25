package ghp.tilegame.main.tiles;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class StoneWallTile extends PNGTile {
	private static BufferedImage image = null;
	private static final String imageLocation = "resources/sprites/tiles/floor_brick.png"; 
	
	public StoneWallTile(){
		super();
		// Nothing to do in the standard constructor;
	}
	
	@Override
	public boolean getWalkable() {
		return false; // Cannot walk on a wall
	}

	@Override
	public void tick() {
		// Do nothing on a tick
	}

	protected BufferedImage getImage() {
		if(null == image){
			try {
				image = ImageIO.read(new File(imageLocation));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.exit(1);
			}
		}
		return image;
	}

}
