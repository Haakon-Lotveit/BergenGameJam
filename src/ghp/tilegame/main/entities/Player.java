package ghp.tilegame.main.entities;

import java.awt.Graphics;

import no.gamejam.Spiller;
import ghp.tilegame.main.Game;
import ghp.tilegame.main.gfx.ImageManager;
import ghp.tilegame.main.levels.Paintable;

public class Player extends Spiller implements Paintable
{
	
	private int x, y;
	private ImageManager im;
	public boolean up = false, dn = false, lt = false, rt = false;
	private final int SPEED = 4;
	public boolean moving = false;
	public boolean movingUp = false, movingDn = false, movingLt = false, movingRt = false;
	public int pixelsToMove = 0;
	
	public Player(int x, int y, ImageManager im){
		super(0, 0, 0, 0, 0, 0);
		this.x = x;
		this.y = y;
		this.im = im;
	}
	
	public void tick(){
		checkMoving();
	}
	
	public void checkMoving(){
		if(movingUp){
			y -= SPEED;
			pixelsToMove -= SPEED;
			if(pixelsToMove <= 0){movingUp = false; moving = false;}
		}
		else if(movingDn){
			y += SPEED;
			pixelsToMove -= SPEED;
			if(pixelsToMove <= 0){movingDn = false; moving = false;}
		}
		else if(movingLt){
			x -= SPEED;
			pixelsToMove -= SPEED;
			if(pixelsToMove <= 0){movingLt = false; moving = false;}
		}
		else if(movingRt){
			x += SPEED;
			pixelsToMove -= SPEED;
			if(pixelsToMove <= 0){movingRt = false; moving = false;}
		}
			
	}
	
	public void render(Graphics g){
		g.drawImage(im.player, x, y, Game.TILESIZE*Game.SCALE, Game.TILESIZE*Game.SCALE, null);
		
	}
	
	public int playerAttack(){
		return attack();
		// Check if there is an enemy on the tile in front of player
//		If so, deal damage to enemy
	}
	
	public int playerBlock(){
		return defend();
	}
}