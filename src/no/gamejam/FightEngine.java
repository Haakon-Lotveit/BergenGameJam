package no.gamejam;

import java.util.Random;
import ghp.tilegame.main.levels.Level;

public class FightEngine {
	private int diesize;
	public FightEngine(){
		diesize = 6;
	}
	public FightEngine(int diesize){
		this.diesize = diesize;
	}
	
	Random r = new Random();
	public void fight(Fighter agressor, Fighter defender){
		int attack = (agressor.attack() + r.nextInt(diesize)) - (defender.defend() - r.nextInt(diesize));
		int damage = 1;
		if(attack > 0){ ++damage;}
		if(attack < 0){ --damage;}
		defender.takeDamage(damage);
		System.out.printf("Did %d damage to enemy!%n", damage);

	}
	
	public void attack(Actor aggressor, char dir, Level lvl){
		int x = aggressor.getX();
		int y = aggressor.getY();
		switch(dir){
		case 'u':
			--y;
			break;
		case 'd':
			++y;
			break;
		case 'l':
			--x;
			break;
		case 'r':
			++x;
			break;
		}
		System.out.printf("Looking for someone at %d×%d%n", x, y);
		for(Actor a :  lvl.getActors()){
			System.out.printf("This guy is at %d×%d%n", a.getX(), a.getY());
			if(a.getX() == x && a.getY() == y){
				System.out.printf("Fant %s!", a.toString());
				fight(aggressor, a);
			}
		}
	}
}
