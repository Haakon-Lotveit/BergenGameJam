package ghp.tilegame.main.tiles;

import ghp.tilegame.main.gfx.ImageManager;


public abstract class IMTile extends Tile {
	protected ImageManager im;
	
	public IMTile(ImageManager im) {
		super();
		this.im = im;	
	}
	
}
