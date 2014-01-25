package ghp.tilegame.main.gfx;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader
{
	public BufferedImage load(String path){
		File imageFile = new File(path);
		System.out.print("Finnes filen? ");
		System.out.println(imageFile.exists());
		System.out.println(imageFile.getAbsolutePath());
		try {
			return ImageIO.read(imageFile);
		} catch (IOException e) {e.printStackTrace();}
		return null;
	}
}
