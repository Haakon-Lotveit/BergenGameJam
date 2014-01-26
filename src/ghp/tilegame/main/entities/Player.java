package ghp.tilegame.main.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import no.gamejam.Actor;
import no.gamejam.Spiller;
import ghp.tilegame.main.Game;
import ghp.tilegame.main.gfx.ImageManager;
import ghp.tilegame.main.levels.Level;
import ghp.tilegame.main.levels.Paintable;
import java.util.Timer;

public class Player extends Spiller implements Paintable, Actor
{
	private int x, y;
	private ImageManager im;
	private final int SPEED = 2;
	public boolean moving = false;
	public boolean movingUp = false, movingDn = false, movingLt = false, movingRt = false;
	public int pixelsToMove = 0;

	private int tileSize = 64;
	private int animFrame = 0;
	private char charAngle = 'F';
	private long time, limit;
	
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
		limit = 250;
		time = 0;
	}

	public int getXCoord(){
		return x/tileSize;
	}
	public int getYCoord(){
		return y/tileSize;
	}
	
	public char getDir(){
		return charAngle;
	}
	public void tick(){
		checkMoving();
		
		if (System.currentTimeMillis() - time > limit){
			time = System.currentTimeMillis();
			if(moving){
				System.out.println("HHH");
				animFrame = 0;
			}
			
		}
		
		
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
					animFrame = 1;
		break;
		case 'D':  	movingDn = true;
					animFrame = 1;
		break;
		case 'L': 	movingLt = true;
					animFrame = 1;
        break;
		case 'R':  	movingRt = true;
					animFrame = 1;
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

	/**
	 * Meant to be called from a FightEngine or something like that
	 * @return the attack strength of this player;
	 */
	public int playerAttack(){
		System.out.println("ATTACKING");
		return attack();

		//level.getTile(x, y)
	}

	/**
	 * Meant to be called from a FightEngine or something like that
	 * @return the blocking strength of this player;
	 */
	public int playerBlock(){
		System.out.println("BLOCKING");
		return defend();
	}

	@Override
	public void tick(Object gameBoard) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getX() {
		return getXCoord();
	}

	@Override
	public int getY() {
		return getYCoord();
	}

	@Override
	public boolean collidesWith(Actor a) {
		System.out.printf("%d×%d vs %d×%d%n (dir: %c)%n", getX(), getY(), a.getX(), a.getY(), charAngle);
		switch(charAngle){
		case 'B':
			return a.getX() == this.getX() && a.getY() == this.getY() - 1;
		case 'D':
			return a.getX() == this.getX() && a.getY() == this.getY() + 1;
		case 'L':
			return a.getX() == this.getX() - 1 && a.getY() == this.getY();
		case 'R':
			return a.getX() == this.getX() + 1 && a.getY() == this.getY();
		default:
			return false;
		}
	}

	@Override
	public char facesDirection() {
		return charAngle;
	}

}