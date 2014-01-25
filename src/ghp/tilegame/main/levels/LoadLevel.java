package ghp.tilegame.main.levels;

import ghp.tilegame.main.gfx.ImageManager;
import ghp.tilegame.main.tiles.FloorTile;
import ghp.tilegame.main.tiles.Tile;
import ghp.tilegame.main.tiles.TileFactory;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LoadLevel {
	private Map<String, String> glyphDictionary;
	
	public LoadLevel(){
		glyphDictionary = new HashMap<>();
		glyphDictionary.put("G", "FloorTile");
		glyphDictionary.put("V", "StoneWallTile");
		glyphDictionary.put("T", "StoneWallTopTile");
	}
	public Level loadLevel(File levelFile, ImageManager im) throws FileNotFoundException, UndefinedGlyphException{
		Level loaded = new Level(im);
		Scanner readRow = new Scanner(levelFile);
		for(int y = 0; y < loaded.getYSize(); ++y){
			String[] row = readRow.nextLine().split(" ");
			for(int x = 0; x < loaded.getXSize(); ++x){
				loaded.setTile(x, y, parseTile(row[x], im));
			}
		}
		readRow.close();
		return loaded;
		
	}
	
	private Tile parseTile(String tileDef, ImageManager im) throws UndefinedGlyphException{
		if(glyphDictionary.containsKey(tileDef)){
			TileFactory.setImageManager(im);
			return TileFactory.create(glyphDictionary.get(tileDef));
		}
		else throw new UndefinedGlyphException(String.format("%s is not a valid glyph.", tileDef));
	}
}
