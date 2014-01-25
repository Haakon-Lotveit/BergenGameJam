package no.gamejam;

import java.util.Random;

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
	}
}
