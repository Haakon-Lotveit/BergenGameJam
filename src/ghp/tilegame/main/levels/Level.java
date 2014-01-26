package ghp.tilegame.main.levels;

import ghp.tilegame.main.gfx.ImageManager;
import ghp.tilegame.main.tiles.FloorTile;
import ghp.tilegame.main.tiles.Tile;
import no.gamejam.Actor;

import java.awt.Graphics;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class Level implements TiledLevel
{
	private final File standardFile = new File("resources/text/Level1Map");

	private int tilesX = 10, tilesY = 6;

	private List<Actor> actors;
	private Tile[][] tiles;
	private final Tile FLOOR_TILE;

	/*public Tile[][] tiles;

	for (int i=0; i<tilesY; i++){
		for (int j=0; j<tilesX; j++){
			tiles[i][j] = new Tile();
		}
	}*/

	public boolean isWalkable(int x, int y){
		if(x < 0 || x >= tiles.length || y < 0 || y > tiles[0].length){
			return false;
		}
		if(!tiles[x][y].isWalkable()){
			return false;
		}
		for(Actor a : getActors()){
			if(a.getX() == x && a.getY() == y){
				return false;
			}
		}
		return true;
	}
	
	public Level (ImageManager im){
		actors = new LinkedList<>();
		FLOOR_TILE = new FloorTile(im);
		loadLevel();
	}

	public void registerActor(Actor a){
		actors.add(a);
	}
	
	public List<Actor> getActors(){
		return actors;
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

