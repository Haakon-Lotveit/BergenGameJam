package ghp.tilegame.main.tiles;

import ghp.tilegame.main.Game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public abstract class PNGTile extends Tile {

	protected abstract BufferedImage getImage();

	@Override
	public void render(Graphics g, int x, int y) {
		g.drawImage(getImage(), x, y, Game.TILESIZE*Game.SCALE, Game.TILESIZE*Game.SCALE, null);
	}

}
