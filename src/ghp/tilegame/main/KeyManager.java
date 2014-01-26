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
			if(!Game.level1.isWalkable(xpos, ypos - 1)){ return; }
			Game.getPlayer().move('U');
		}
		if((e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) && Game.getPlayer().moving == false){
			if(!Game.level1.isWalkable(xpos, ypos + 1)){ return; }
			Game.getPlayer().move('D');
		}
		if((e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) && Game.getPlayer().moving == false){
			if(!Game.level1.isWalkable(xpos - 1, ypos)){ return; }
			Game.getPlayer().move('L');
		}
		if((e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) && Game.getPlayer().moving == false){
			if(!Game.level1.isWalkable(xpos + 1, ypos)){ return; }
			Game.getPlayer().move('R');
		}
		
		if(e.getKeyCode() == KeyEvent.VK_N && Game.getPlayer().moving == false){
			Game.fe.attack(p, p.getDir(), Game.level1);
			Game.getPlayer().playerAttack();
		}	
		if(e.getKeyCode() == KeyEvent.VK_M && Game.getPlayer().moving == false){
			Game.getPlayer().playerBlock();
		}	
	}

	public void keyReleased(KeyEvent e) {
//		MOVEMENT
		/*if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W){
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
		}*/
	}
	
	public void keyTyped(KeyEvent arg0) {
		
	}
}
