package no.gamejam;

public abstract class Spiller implements Fighter{
	public static final int BLOCK_BONUS = 10;
	
    private int skrem;
    private int smisk;
    private int bløff;
	private int styrke;
	private int seighet;
	private int helse;
	private FightState state;
    
    public Spiller(int skrem, int smisk, int bløff, int styrke, int seighet, int helse){
    	this.skrem = skrem;
    	this.smisk = smisk;
    	this.bløff = bløff;
    	this.styrke = styrke;
    	this.seighet = seighet;
    	this.helse = helse;
    	this.state = FightState.IDLE;
    	
    }

	public int getSkrem() {
		return skrem;
	}

	public void setSkrem(int skrem) {
		this.skrem = skrem;
	}

	public int getSmisk() {
		return smisk;
	}

	public void setSmisk(int smisk) {
		this.smisk = smisk;
	}

	public int getBløff() {
		return bløff;
	}

	public void setBløff(int bløff) {
		this.bløff = bløff;
	}

	@Override
	public int attack() {
		return styrke;
	}

	@Override
	public int defend() {
		if(state.equals(FightState.BLOCKING)){
			return seighet + BLOCK_BONUS;
		}
		return seighet;
	}

	@Override
	public void takeDamage(int damage) {
		helse -= damage;
	}

	@Override
	public int health() {
		return helse;
	}

	@Override
	public FightState status() {
		return state;
	}
	
	@Override
	public String statString() {
		return String.format("HP:%d A%d D%d", health(), attack(), defend());
	}	
}
