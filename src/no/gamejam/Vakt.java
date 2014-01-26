package no.gamejam;

import ghp.tilegame.main.Game;
import ghp.tilegame.main.entities.NilsAnims;
import ghp.tilegame.main.levels.Level;

import java.awt.Graphics;
import java.util.Random;

public class Vakt implements Actor {
	private int x,y;
	private int smisk;
	private int skrem;
	private int bløff;
	private int styrke;
	private int seighet;
	private int helse;
	protected FightState state;
	private int tileSize;
	private char direction;
	
	private final int SPEED = 2;
	public boolean moving = false, movingUp = false, movingDn = false, movingLt = false, movingRt = false;
	public int pixelsToMoveX = 0, pixelsToMoveY = 0, animFrame = 0;
	public char animType = 'W', charAngle = 'B';
	private long time = 0;
	private int limit = 166, limit2 = 333, limit3 = 500;
	public int pixelX = 256, pixelY = 192;

	private void init(){
		this.direction = 'F';
	}
	/**
	 * Lager et vaktobjekt med tilfeldig genererte verdier fra 0-14 inklusiv
	 */
	public Vakt(int smisk, int skrem, int bløff, int styrke, int seighet, int helse){
		init();
		x = y = 0;
		this.smisk = smisk;
		this.skrem = skrem;
		this.bløff = bløff;
		this.styrke = styrke;
		this.seighet = seighet;
		this.helse = helse;
		this.state = FightState.IDLE;
	}
	public Vakt(int smisk, int skrem, int bløff, int styrke, int seighet, int helse, int x, int y){
		init();
		this.x = x; this.y = y;
		this.smisk = smisk;
		this.skrem = skrem;
		this.bløff = bløff;
		this.styrke = styrke;
		this.seighet = seighet;
		this.helse = helse;
		this.state = FightState.IDLE;
	}
	public Vakt(){
		init();
		Random r = new Random();
		this.smisk = r.nextInt(15);
		this.skrem = r.nextInt(15);
		this.bløff = r.nextInt(15);
		this.seighet = r.nextInt(10);
		this.styrke = r.nextInt(10);
		this.helse = 4;
		this.state = FightState.IDLE;
	}

	public Vakt(int x, int y){
		init();
		this.x = x; this.y = y;
		Random r = new Random();
		this.smisk = r.nextInt(15);
		this.skrem = r.nextInt(15);
		this.bløff = r.nextInt(15);
		this.seighet = r.nextInt(10);
		this.styrke = r.nextInt(10);
		this.helse = 4;
		this.state = FightState.IDLE;
	}

	public boolean walk(char dir){
		/* TODO: Fix this blasphemy and/or madness! */
		Level lvl = Game.level1;
		int x = getX();
		int y = getY();
		switch(dir){
		case 'F': y++; break;
		case 'B': y--; break;
		case 'L': x--; break;
		case 'R': x++; break;
		default: throw new IllegalArgumentException("Use FBLR and only those!");
		}
		/* First, check if the player is where we want to go */
		if(Game.getPlayer().getX() == x && Game.getPlayer().getY() == y){
			return false;
		}
		
		/* Then, check if the level objects to us going there */
		if(lvl.isWalkable(x, y)){
			this.x = x;
			this.y = y;
			
			moving = true;
			time = System.currentTimeMillis();
			animFrame = 1;
			
			switch(dir){
			case 'B':  	movingUp = true;	
						pixelsToMoveY = 64;
						charAngle = 'B';
			break;
			case 'F':  	movingDn = true;
						pixelsToMoveY = -64;
						charAngle = 'F';
			break;
			case 'L': 	movingLt = true;
						pixelsToMoveX = 64;
						charAngle = 'L';
	        break;
			case 'R':  	movingRt = true;
						pixelsToMoveX = -64;
						charAngle = 'R';
			break;
			default: 	System.out.println("No direction to move");
						pixelsToMoveX = 0;
						moving = false;
	        break;
			}
			return true;
		}
		else{
			return false;
		}
	}
	
	public void checkMoving(){
		if(movingUp){
//			charAngle = 'B';
			pixelsToMoveY -= SPEED;
			if(pixelsToMoveY <= 0){movingUp = false; moving = false; animFrame = 0;}
		}
		else if(movingDn){
//			charAngle = 'F';
			pixelsToMoveY += SPEED;
			if(pixelsToMoveY >= 0){movingDn = false; moving = false; animFrame = 0;}
		}
		else if(movingLt){
//			charAngle = 'L';
			pixelsToMoveX -= SPEED;
			if(pixelsToMoveX <= 0){movingLt = false; moving = false; animFrame = 0;}
		}
		else if(movingRt){
//			charAngle = 'R';
			pixelsToMoveX += SPEED;
			if(pixelsToMoveX >= 0){movingRt = false; moving = false; animFrame = 0;}
		}
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
		if(state == FightState.DYING){
			return 0;
		}
		this.state = FightState.ATTACKING;
		return styrke;
	}

	@Override
	public int defend() {
		if(state == FightState.DYING){
			return 0;
		}
		this.state = FightState.ATTACKING;
		return seighet;
	}

	@Override
	public void takeDamage(int damage) {
		if(state == FightState.DYING){
			helse = 0;
			return;
		}
		this.state = FightState.ATTACKING;
		helse -= damage;
		if(helse < 0){
			state = FightState.DYING;
		}
	}

	@Override
	public int health(){
		return helse;
	}

	@Override
	public FightState status() {
		return state;
	}

	@Override // TICK____________
	public void tick(Object gameBoard) {
		if(health() < 0){
			this.state = FightState.DYING;
		}
		checkMoving();
		
		if (animType == 'W'){
			if (System.currentTimeMillis() - time > limit){
				if(moving){animFrame = 0;}
			}
			if (System.currentTimeMillis() - time > limit2){
				if(moving){animFrame = 2;}
			}
		}
		else if (animType == 'A'){
			if (System.currentTimeMillis() - time > limit2){
				if(moving){animFrame = 1;}
			}
			if (System.currentTimeMillis() - time > limit3){
				if(moving){animFrame = 0; moving = false; animType = 'W';}
			}
		}
		
		System.out.print(charAngle);
		return;
	}

	@Override
	public String statString() {
		return String.format("HP:%d A%d D%d", health(), attack(), defend());
	}
	@Override
	public int getX() {
		return x;
	}
	@Override
	public int getY() {
		return y;
	}
	public char getCharAngle(){
		return charAngle;
	}

	@Override
	public char facesDirection() {
		return direction;
	}
	
	public void setDirection(char dir){
		if("FBLR".indexOf(dir) == -1){ 
			return;
		}
		this.direction = dir;
	}
	@Override
	public void render(Graphics g) {
		System.out.println("The Vakt class has no concept of style, fashion, appearance or common decency, and is therefore barred from painting itself");
	}
	@Override
	public boolean blocksMovement() {
//		System.out.printf("I have %d so %s%n", health(), health() > 0 ? "YOU SHALL NOT PASS":"I'm in no position to argue");
		return health() > 0;
	}
	@Override
	public void act() {
		// TODO Auto-generated method stub
		
	}
	
}
