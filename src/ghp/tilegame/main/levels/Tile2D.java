package ghp.tilegame.main.levels;

import java.awt.image.BufferedImage;

public interface Tile2D {
	public boolean getWalkable();
	public void setWalkable(boolean walkable);
	public BufferedImage getImage();
	public void setImage(BufferedImage image);
	/* Er det noe mer som trengs? */
}
