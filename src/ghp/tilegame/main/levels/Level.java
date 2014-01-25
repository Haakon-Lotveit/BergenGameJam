package ghp.tilegame.main.levels;

import ghp.tilegame.main.gfx.ImageManager;
import ghp.tilegame.main.tiles.FloorTile;
import ghp.tilegame.main.tiles.Tile;

import java.awt.Graphics;
import java.io.File;

public class Level implements TiledLevel
{
	private final File standardFile = new File("resources/text/Level1Map");

	private int tilesX = 10, tilesY = 6;

	private Tile[][] tiles;
	private final Tile FLOOR_TILE;

	/*public Tile[][] tiles;

	for (int i=0; i<tilesY; i++){
		for (int j=0; j<tilesX; j++){
			tiles[i][j] = new Tile();
		}
	}*/

	public Level (ImageManager im){
		FLOOR_TILE = new FloorTile(im);
		loadLevel();
	}

	private void loadLevel(){

		tiles = new Tile[tilesX][tilesY];
		for(int y=0; y<tilesY; y++){
			for(int x=0; x<tilesX; x++){
				if(true){ 	// if floor type tile
					//floorTile.render(g, tilesX*64, tilesY*64);
					tiles[x][y] = FLOOR_TILE;
				}
			}
		}
	}

	public void renderLevel(Graphics g, Tile floorTile){
		for(int y=0; y<tilesY; y++){
			for(int x=0; x<tilesX; x++){
				tiles[x][y].render(g,  x*64, y*64);
			}
		}
		
		
	}

	@Override
	public void setTile(int x, int y, Tile tile) {
		tiles[x][y] = tile;
	}

	@Override
	public Tile getTile(int x, int y) {
		return tiles[x][y];
	}

	@Override
	public int getXSize() {
		return this.tilesX;
	}

	@Override
	public int getYSize() {
		return this.tilesY;
	}
}

