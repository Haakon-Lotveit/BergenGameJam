package ghp.tilegame.main.tiles;

import java.awt.Graphics;

import ghp.tilegame.main.gfx.ImageManager;

public abstract class Tile {
	
	protected ImageManager im;
	
	public Tile(ImageManager im){
		this.im = im;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g, int x, int y);
}
