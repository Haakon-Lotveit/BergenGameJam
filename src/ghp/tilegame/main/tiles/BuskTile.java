package ghp.tilegame.main.tiles;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BuskTile extends PNGTile {
	private static BufferedImage busk = null;
	private static final String imageLocation = "resources/sprites/tiles/busk.png";
	@Override
	protected BufferedImage getImage() {
		if(null == busk){
			try{
				busk = ImageIO.read(new File(imageLocation));
			}
			catch(IOException e){
				e.printStackTrace();
				System.exit(1);
			}
		}
		return busk;
	}

	@Override
	public boolean isWalkable() {
		return false;
	}

	@Override
	public void tick() {
		/* Vi har ingen animasjoner så denne bare returnerer */
		return;
	}

}
