package no.gamejam;

import java.util.Random;

public class Vakt implements Fighter, Actor {
	private int smisk;
	private int skrem;
	private int bløff;
	private int styrke;
	private int seighet;
	private int helse;
	private FightState state;
	
	/**
	 * Lager et vaktobjekt med tilfeldig genererte verdier fra 0-14 inklusiv
	 */
	public Vakt(int smisk, int skrem, int bløff, int styrke, int seighet, int helse){
		this.smisk = smisk;
		this.skrem = skrem;
		this.bløff = bløff;
		this.styrke = styrke;
		this.seighet = seighet;
		this.helse = helse;
		this.state = FightState.IDLE;
	}
	public Vakt(){
		Random r = new Random();
		this.smisk = r.nextInt(15);
		this.skrem = r.nextInt(15);
		this.bløff = r.nextInt(15);
		this.seighet = r.nextInt(10);
		this.styrke = r.nextInt(10);
		this.helse = 4;
		this.state = FightState.IDLE;
	}
	
	public int smisk(){
		return smisk;
	}
	public int skrem(){
		return skrem;
	}
	public int bløff(){
		return bløff;
	}
	
	public String toString(){
		return String.format("Smisk: %d, skrem: %d, bløff: %d",
				smisk(), skrem(), bløff());
	}

	@Override
	public int attack() {
		this.state = FightState.ATTACKING;
		return styrke;
	}

	@Override
	public int defend() {
		this.state = FightState.ATTACKING;
		return seighet;
	}

	@Override
	public void takeDamage(int damage) {
		this.state = FightState.ATTACKING;
		helse -= damage;
	}
	
	@Override
	public int health(){
		return helse;
	}

	@Override
	public FightState status() {
		return state;
	}

	@Override
	public void tick(Object gameBoard) {
		if(health() < 0){
			this.state = FightState.DYING;
		}
		return;
	}

	@Override
	public String statString() {
		return String.format("HP:%d A%d D%d", health(), attack(), defend());
	}
	
	
	
}
