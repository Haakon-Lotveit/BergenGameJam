package ghp.tilegame.main.levels;

import ghp.tilegame.main.tiles.Tile;

public interface TiledLevel {
	public void setTile(int x, int y, Tile tile);
	public Tile getTile(int x, int y);
	public int getXSize();
	public int getYSize();
	/* Er det noe mer som trengs? */
}
