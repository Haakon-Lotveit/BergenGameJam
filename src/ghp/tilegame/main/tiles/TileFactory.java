package ghp.tilegame.main.tiles;

import ghp.tilegame.main.gfx.ImageManager;

public class TileFactory {
	private static ImageManager im = null;
	
	public static void setImageManager(ImageManager im){
		TileFactory.im = im;
	}
	public static Tile create(String tiletype) {
		switch(tiletype){
		case "StoneWallTile":
			return new StoneWallTile();
		case "StoneWallTopTile":
			return new StoneWallTopTile();
		case "FloorTile":
			if(null != TileFactory.im) { return new FloorTile(im); }
			break;
		default:
			throw new IllegalArgumentException(String.format("No tile called %s registered", tiletype));
		}
			
		System.err.println("ERROR: Couldn't load new tile");
		return null;
	}
}
