package ghp.tilegame.main.gfx;

import java.awt.image.BufferedImage;

//Stores all the game images
public class ImageManager
{
	public BufferedImage player, grassTile, stoneWall;
	
	public ImageManager(SpriteSheet ss){
		player = ss.crop(0, 0, 64, 64);
		grassTile = ss.crop(2, 0, 64, 64);
		stoneWall = ss.crop(2, 0, 64, 64);
	}

}
