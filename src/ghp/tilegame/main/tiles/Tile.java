package ghp.tilegame.main.tiles;

import java.awt.Graphics;

public abstract class Tile {
	public abstract boolean isWalkable();
	public abstract void tick();
	public abstract void render(Graphics g, int x, int y);
}
