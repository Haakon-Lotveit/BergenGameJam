package ghp.tilegame.main.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import ghp.tilegame.main.Game;
import ghp.tilegame.main.levels.Paintable;
import no.gamejam.Actor;
import no.gamejam.FightState;
import no.gamejam.Vakt;

public class Nils extends Vakt implements Paintable {
	private final static String IMAGE_LOCATION= "nils_placeholder.png";
	private static BufferedImage image;
	private static BufferedImage death;
	private int tileSize = 64;
	private int walkstep = 0;
	private char[] steps = new char[0];
	NilsAnims anims = new NilsAnims();
	
	@Override
	public void tick(Object gameboard){
		if(super.health() <= 0){
			die();
		}
		super.tick(gameboard);
		int manhattan = manhattanAirDistance(Game.getPlayer());
//		System.out.println(manhattan);
		if(manhattan <= 2){
//				Game.startDiplomancy(te.getResponse("VAKT", "HILS"));
		}	
	}
	
	public Nils setPatrolRoute(String route){
		this.steps = route.toCharArray();
		return this;
	}
	@Override
	public void act(){
		int manhattan = manhattanAirDistance(Game.getPlayer());
		if(state == FightState.ATTACKING && manhattan <= 1){
			Game.fe.fight(this, Game.getPlayer());
		}
		if(state == FightState.DYING){
			return;
		}
		else{
			if(steps.length == 0){
				System.out.println("En Nils stirrer sløvt ut i horisonten: Han har ingensteder å gå :( ");
				return;
			}
			char dir = steps[walkstep];
			if(walk(dir)){
				++walkstep;
			}
			/* Husker noen den kule måten å gjøre dette på? */
			if(walkstep >= steps.length){
				walkstep = 0;
			}
			
		}

	}
	
	private void die(){
		super.state = FightState.DYING;
	}
	/**
	 * Computes the Manhattan distance (AFAICBA googling it at 0244 to ensure its correctness) between this Nils and an actor
	 * @param a The actor that's being compared to
	 * @return The distance as an integer.
	 */
	public int manhattanAirDistance(Actor a){
		return Math.abs(a.getX() - getX()) + Math.abs(a.getY() - getY());
	}
	
	public Nils(int smisk, int skrem, int bløff, int styrke, int seighet, int helse, int x, int y, int tileSize){
		super(smisk, skrem, bløff, styrke, seighet, helse, x, y);	// Her multipliserer jeg x og y for å få pixel verdier
		this.tileSize = tileSize;
	}
	
	/**
	 * Denne anbefales ikke å bruke, men kan være grei for kjapp Nilsing.
	 */
	public Nils(){
		super();
	}

	public Nils(int x, int y){
		super(x,y);
	}
	public Nils(int x, int y, int tileSize){
		super(x,y);
		this.tileSize = tileSize;
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(getImage(), super.getX()*tileSize + pixelsToMoveX, super.getY()*tileSize + pixelsToMoveY, Game.TILESIZE*Game.SCALE, Game.TILESIZE*Game.SCALE, null);
		// Pixel bevegelse
//		g.drawImage(getImage(), super.getX()*tileSize, super.getY()*tileSize, Game.TILESIZE*Game.SCALE, Game.TILESIZE*Game.SCALE, null);
	}

	protected BufferedImage getImage(){
		if(null == death){
				try{
					death = ImageIO.read(new File("skull_dead.png"));
				}
				catch(IOException ioe){
					ioe.printStackTrace();
					System.exit(2);
				}
			}
//		if(null == image){
			System.out.println("fetching image for nils");
			try {
//				image = ImageIO.read(new File(IMAGE_LOCATION));
				System.out.println(super.getCharAngle());
				image = ImageIO.read(new File(anims.getAnims(animType, super.getCharAngle(), animFrame)));
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(1);
			}
//		}
		return health() > 0? image : death;
	}
	
	
}
