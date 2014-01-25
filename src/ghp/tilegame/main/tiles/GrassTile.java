package ghp.tilegame.main.tiles;

import java.awt.Graphics;

import ghp.tilegame.main.Game;
import ghp.tilegame.main.gfx.ImageManager;

public class GrassTile extends Tile{

	public GrassTile(ImageManager im) {
		super(im);
	}

	public void tick() {
		
	}

	public void render(Graphics g, int x, int y) {
		g.drawImage(im.grassTile, x, y, Game.TILESIZE*Game.SCALE, Game.TILESIZE*Game.SCALE, null);		
	}

}
