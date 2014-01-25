package ghp.tilegame.main.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import ghp.tilegame.main.Game;
import ghp.tilegame.main.gfx.ImageManager;

public class FloorTile extends IMTile{
	
	public FloorTile(ImageManager im) {
		super(im);
	}

	public void tick() {
		
	}

	public void render(Graphics g, int x, int y) {
		g.drawImage(im.grassTile, x, y, Game.TILESIZE*Game.SCALE, Game.TILESIZE*Game.SCALE, null);
	}

	@Override
	public boolean isWalkable() {
		return true;
	}


}
