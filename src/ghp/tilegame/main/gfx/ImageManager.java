package ghp.tilegame.main.gfx;

import java.awt.image.BufferedImage;

//Stores all the game images
public class ImageManager
{
	public BufferedImage player, grassTile;
	
	public ImageManager(SpriteSheet ss){
		player = ss.crop(0, 0, 16, 16);
		grassTile = ss.crop(1, 0, 16, 16);
	}

}
