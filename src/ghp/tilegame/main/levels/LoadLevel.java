package ghp.tilegame.main.levels;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LoadLevel {
	public static Level loadLevel(File levelFile, BufferedImage bakgrunn) throws FileNotFoundException{
		TiledLevel loaded = null;
		Scanner readRow = new Scanner(levelFile);
		for(int y = 0; y < loaded.getYSize(); ++y){
			String[] row = readRow.nextLine().split(" ");
			for(int x = 0; x < loaded.getXSize(); ++x){
				loaded.setTile(x, y, parseTile(row[x]));
			}
		}
		
		return null;
		
	}
	private static Tile2D parseTile(String tileDef){
		return null; //TODO: Ta å faktisk parse noe.
	}
}
