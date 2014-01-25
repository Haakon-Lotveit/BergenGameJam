package ghp.tilegame.main.tiles;

import ghp.tilegame.main.gfx.ImageManager;

import java.awt.Graphics;

public abstract class Tile {
	public abstract boolean getWalkable();
	public abstract void tick();
	public abstract void render(Graphics g, int x, int y);
}
