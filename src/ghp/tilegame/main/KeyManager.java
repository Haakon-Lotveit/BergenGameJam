package ghp.tilegame.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener
{
	public void keyPressed(KeyEvent e) {
//		MOVEMENT
		if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W){
			Game.getPlayer().up = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S){
			Game.getPlayer().dn = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A){
			Game.getPlayer().lt = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D){
			Game.getPlayer().rt = true;
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
