package ghp.tilegame.main.levels;

import ghp.tilegame.main.tiles.Tile;

public interface TiledLevel {
	/*
	 * Enten må konstruktøren la oss spesifisere størrelsen i antall tiles, eller så må den være et fast tall
	 * Det letteste er fast tall
	 */
	public void setTile(int x, int y, Tile2D tile);
	public Tile getTile(int x, int y);
	public int getXSize();
	public int getYSize();
	/* Er det noe mer som trengs? */
}
