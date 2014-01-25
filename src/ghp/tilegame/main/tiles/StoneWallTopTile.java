package ghp.tilegame.main.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class StoneWallTopTile extends PNGTile {
	private static BufferedImage image = null;
	private static final String imageLocation = "resources/sprites/tiles/wall_stone_top.png"; 

	@Override
	protected BufferedImage getImage() {
		if(null == image){
			try{
				image = ImageIO.read(new File(imageLocation));
			} catch(IOException ioe){
				ioe.printStackTrace();
				System.exit(1);
			}
		}
		return image;
	}

	@Override
	public boolean getWalkable() {
		return false;
	}

	@Override
	public void tick() {
		// Do nothing on update
	}

}
