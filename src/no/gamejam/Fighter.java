package no.gamejam;

public interface Fighter {
	public int attack();
	public int defend();
	public void takeDamage(int damage);
	public int health();
	public FightState status();
	public String statString();
}
