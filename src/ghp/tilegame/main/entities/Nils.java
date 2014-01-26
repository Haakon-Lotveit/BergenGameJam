package ghp.tilegame.main.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import ghp.tilegame.main.Game;
import ghp.tilegame.main.levels.Paintable;
import no.gamejam.Actor;
import no.gamejam.TalkEngine;
import no.gamejam.TalkType;
import no.gamejam.Vakt;

public class Nils extends Vakt implements Paintable {
	private final static String IMAGE_LOCATION= "nils_placeholder.png";
	private static BufferedImage image;
	
	private int tileSize = 64;
	private boolean busy = false;
	
	@Override
	public void tick(Object gameboard){
		super.tick(gameboard);
		TalkEngine te = Game.te;
		int manhattan = manhattanAirDistance(Game.getPlayer());
//		System.out.println(manhattan);
		if(manhattan <= 2){
//				Game.startDiplomancy(te.getResponse("VAKT", "HILS"));
		}
	}
	
	/**
	 * Computes the Manhattan distance (AFAICBA googling it at 0244 to ensure its correctness) between this Nils and an actor
	 * @param a The actor that's being compared to
	 * @return The distance as an integer.
	 */
	public int manhattanAirDistance(Actor a){
		return Math.abs(a.getX() - getX()) + Math.abs(a.getY() - getY());
	}
	
	/**
	 * TODO: Skal søke etter annet innen en avstand
	 * @param searchDepth
	 * @return
	 */
	public int search(int searchDepth){
		return 0;
	}
	
	public Nils(int smisk, int skrem, int bløff, int styrke, int seighet, int helse, int x, int y, int tileSize){
		super(smisk, skrem, bløff, styrke, seighet, helse);
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
		g.drawImage(getImage(), super.getX()*tileSize, super.getY()*tileSize, Game.TILESIZE*Game.SCALE, Game.TILESIZE*Game.SCALE, null);
	}

	protected BufferedImage getImage(){
		if(null == image){
			System.out.println("fetching image for nils");
			try {
				image = ImageIO.read(new File(IMAGE_LOCATION));
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(1);
			}
		}
		return image;
	}
}
