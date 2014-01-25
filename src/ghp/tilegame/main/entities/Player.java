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
	private final int SPEED = 3;
	
	public Player(int x, int y, ImageManager im){
		super(0, 0, 0, 0, 0, 0);
		this.x = x;
		this.y = y;
		this.im = im;
	}
	
	public void tick(){
		if(up){y -= SPEED;}
		if(dn){y += SPEED;}
		if(lt){x -= SPEED;}
		if(rt){x += SPEED;}
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