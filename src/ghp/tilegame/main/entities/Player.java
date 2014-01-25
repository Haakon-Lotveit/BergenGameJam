package ghp.tilegame.main.entities;

import java.awt.Graphics;

import ghp.tilegame.main.Game;
import ghp.tilegame.main.gfx.ImageManager;

public class Player
{
	private int x, y;
	private ImageManager im;
	public boolean up = false, dn = false, lt = false, rt = false;
	private final int SPEED = 3;
	
	public Player(int x, int y, ImageManager im){
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
}