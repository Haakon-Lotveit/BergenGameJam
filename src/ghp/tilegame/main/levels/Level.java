package ghp.tilegame.main.levels;

import java.awt.image.BufferedImage;

public class Level 
{
	private int[][] tiles;
	
	public Level (BufferedImage levelImage){
		LoadLevel(levelImage);
	}
	
	public void LoadLevel(BufferedImage levelImage){	// Reads the level design from an image
		tiles = new int[levelImage.getWidth()][levelImage.getHeight()];
		for(int y=0; y<levelImage.getHeight(); y++){
			for(int x=0; x<levelImage.getWidth(); x++){
				
			}
		}
	}

}
