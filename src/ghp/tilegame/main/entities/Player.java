package ghp.tilegame.main.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import no.gamejam.Spiller;
import ghp.tilegame.main.Game;
import ghp.tilegame.main.gfx.ImageManager;
import ghp.tilegame.main.levels.Level;
import ghp.tilegame.main.levels.Paintable;

public class Player extends Spiller implements Paintable
{
	private int x, y;
	private ImageManager im;
	private final int SPEED = 4;
	public boolean moving = false;
	public boolean movingUp = false, movingDn = false, movingLt = false, movingRt = false;
	public int pixelsToMove = 0;

	private int tileSize = 64;
	private int animFrame = 0;
	private char charAngle = 'F';
	
	private static BufferedImage image = null;            
	private static final String imageLocation = "resources/sprites/HanKisen_Anims/HanKisen_Walk_F01.png";
	PlayerAnims anims = new PlayerAnims();
	
	protected BufferedImage getImage() {
		//if(null == image){
			try {
				image = ImageIO.read(new File(anims.getAnims(charAngle, animFrame, 0)));
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(1);
			}
		//}
		return image;
	}
	
	public Player(int x, int y, ImageManager im){
		super(0, 0, 0, 0, 0, 0);
		this.x = x;
		this.y = y;
		this.im = im;
	}

	public int getXCoord(){
		return x/tileSize;
	}
	public int getYCoord(){
		return y/tileSize;
	}
	public void tick(){
		checkMoving();
	}

	public void checkMoving(){
		if(movingUp){
			charAngle = 'B';
			y -= SPEED;
			pixelsToMove -= SPEED;
			if(pixelsToMove <= 0){movingUp = false; moving = false;}
		}
		else if(movingDn){
			charAngle = 'F';
			y += SPEED;
			pixelsToMove -= SPEED;
			if(pixelsToMove <= 0){movingDn = false; moving = false;}
		}
		else if(movingLt){
			charAngle = 'L';
			x -= SPEED;
			pixelsToMove -= SPEED;
			if(pixelsToMove <= 0){movingLt = false; moving = false;}
		}
		else if(movingRt){
			charAngle = 'R';
			x += SPEED;
			pixelsToMove -= SPEED;
			if(pixelsToMove <= 0){movingRt = false; moving = false;}
		}
	}
	
	public void move(char direction){
		pixelsToMove = 64;
		moving = true;
		switch(direction){
		case 'U':  	movingUp = true;
		break;
		case 'D':  	movingDn = true;
		break;
		case 'L': 	movingLt = true;
        break;
		case 'R':  	movingRt = true;
		break;
		default: 	System.out.println("No direction to move");
					pixelsToMove = 0;
					moving = false;
        break;
		}
	}

	public void render(Graphics g){
		//g.drawImage(im.player, x, y, Game.TILESIZE*Game.SCALE, Game.TILESIZE*Game.SCALE, null);
		g.drawImage(getImage(), x, y, Game.TILESIZE*Game.SCALE, Game.TILESIZE*Game.SCALE, null);

	}

	public int playerAttack(){
		return attack();

		//level.getTile(x, y)
	}

	public int playerBlock(){
		return defend();
	}

}