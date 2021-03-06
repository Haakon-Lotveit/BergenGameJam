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
	public boolean moving = false, movingUp = false, movingDn = false, movingLt = false, movingRt = false;
	public int pixelsToMove = 0;

	private int tileSize = 64;
	private char animType = 'W', charAngle = 'F';
	private long time;
	private int limit, limit2, limit3, animFrame = 0;
	
	private static BufferedImage image = null;            
	PlayerAnims anims = new PlayerAnims();
	
	protected BufferedImage getImage() {
		//if(null == image){
			try {
				image = ImageIO.read(new File(anims.getAnims(animType, charAngle, animFrame)));
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
		limit = 166;
		limit2 = 333;
		limit3 = 500;
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
	
	public void tick(Game g){
		checkMoving();
		if (animType == 'W'){
			if (System.currentTimeMillis() - time > limit){
				if(moving){animFrame = 0;}
			}
			if (System.currentTimeMillis() - time > limit2){
				if(moving){animFrame = 2;}
			}
		}
		else if (animType == 'A'){
			if (System.currentTimeMillis() - time > limit2){
				if(moving){animFrame = 1;}
			}
			if (System.currentTimeMillis() - time > limit3){
				if(moving){animFrame = 0; moving = false; animType = 'W';}
			}
		}
		
	}

	public void checkMoving(){
		if(movingUp){
			charAngle = 'B';
			y -= SPEED;
			pixelsToMove -= SPEED;
			if(pixelsToMove <= 0){movingUp = false; moving = false; animFrame = 0;}
		}
		else if(movingDn){
			charAngle = 'F';
			y += SPEED;
			pixelsToMove -= SPEED;
			if(pixelsToMove <= 0){movingDn = false; moving = false; animFrame = 0;}
		}
		else if(movingLt){
			charAngle = 'L';
			x -= SPEED;
			pixelsToMove -= SPEED;
			if(pixelsToMove <= 0){movingLt = false; moving = false; animFrame = 0;}
		}
		else if(movingRt){
			charAngle = 'R';
			x += SPEED;
			pixelsToMove -= SPEED;
			if(pixelsToMove <= 0){movingRt = false; moving = false; animFrame = 0;}
		}
	}
	
	public void move(char direction){
		pixelsToMove = 64;
		moving = true;
		time = System.currentTimeMillis();
		animFrame = 1;
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
		if(moving){
			Game.sounds.playRandom("STEP");
		}
	}

	public void render(Graphics g){
		if (animType == 'A')	// Hack, to make the attack animation look better
			g.drawImage(getImage(), x-40, y-70, Game.TILESIZE*Game.SCALE*2, Game.TILESIZE*Game.SCALE*2, null);
		else
			g.drawImage(getImage(), x, y, Game.TILESIZE*Game.SCALE, Game.TILESIZE*Game.SCALE, null);

	}

	/**
	 * Meant to be called from a FightEngine or something like that
	 * @return the attack strength of this player;
	 */
	public int playerAttack(){
		System.out.println("ATTACKING");
		moving = true;
		animType = 'A';
		time = System.currentTimeMillis();
		
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
		System.out.println("WRONG TICK CALLED");
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
	public char facesDirection() {
		return charAngle;
	}

	@Override
	public boolean blocksMovement() {
		return true; /* Hank Isen blokkerer alltid røyrsle */
	}

	@Override
	public void act() {
		System.out.println("Player has been called upon to act, which should not happen.");
	}

}