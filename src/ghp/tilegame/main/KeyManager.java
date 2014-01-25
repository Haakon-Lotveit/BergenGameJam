package ghp.tilegame.main;

import ghp.tilegame.main.entities.Player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener
{
	public void keyPressed(KeyEvent e) {
//		MOVEMENT
		Player p = Game.getPlayer();
		int xpos = p.getXCoord();
		int ypos = p.getYCoord();
		
		
		if((e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) && Game.getPlayer().moving == false){
			if(ypos == 0 ){
				return;
			}
			if(!Game.level1.getTile(xpos, ypos - 1).isWalkable()){
				return;
			}
//			p.moveUp()
//			Game.getPlayer().moveUp;
			Game.getPlayer().up = true;
			Game.getPlayer().moving = true;
			Game.getPlayer().movingUp = true;
			Game.getPlayer().pixelsToMove = 64;
		}
		if((e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) && Game.getPlayer().moving == false){
			if(Game.level1.getYSize() - 1 == ypos){
				return;
			}
			if(!Game.level1.getTile(xpos, ypos + 1).isWalkable()){
				return;
			}
			Game.getPlayer().dn = true;
			Game.getPlayer().moving = true;
			Game.getPlayer().movingDn = true;
			Game.getPlayer().pixelsToMove = 64;
		}
		if((e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) && Game.getPlayer().moving == false){
			if(xpos == 0){
				return;
			}
			if(!Game.level1.getTile(xpos - 1, ypos).isWalkable()){
				return;
			}
			Game.getPlayer().lt = true;
			Game.getPlayer().moving = true;
			Game.getPlayer().movingLt = true;
			Game.getPlayer().pixelsToMove = 64;
		}
		if((e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) && Game.getPlayer().moving == false){
			if(xpos == Game.level1.getXSize() - 1){
				return;
			}
			if(!Game.level1.getTile(xpos + 1, ypos).isWalkable()){
				return;
			}
			Game.getPlayer().rt = true;
			Game.getPlayer().moving = true;
			Game.getPlayer().movingRt = true;
			Game.getPlayer().pixelsToMove = 64;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_N){
			Game.getPlayer().playerAttack();
		}	
		if(e.getKeyCode() == KeyEvent.VK_M){
			Game.getPlayer().playerBlock();
		}	
	}

	public void keyReleased(KeyEvent e) {
//		MOVEMENT
		if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W){
			Game.getPlayer().up = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S){
			Game.getPlayer().dn = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A){
			Game.getPlayer().lt = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D){
			Game.getPlayer().rt = false;
		}
	}
	
	public void keyTyped(KeyEvent arg0) {
		
	}
}
