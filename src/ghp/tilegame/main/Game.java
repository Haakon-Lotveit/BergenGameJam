package ghp.tilegame.main;

//import ghp.tilegame.main.entities.Nils;
import ghp.tilegame.main.entities.Nils;
import ghp.tilegame.main.entities.Player;
import ghp.tilegame.main.gfx.ImageLoader;
import ghp.tilegame.main.gfx.ImageManager;
import ghp.tilegame.main.gfx.SpriteSheet;
import ghp.tilegame.main.levels.Level;
import ghp.tilegame.main.levels.LoadLevel;
import ghp.tilegame.main.levels.UndefinedGlyphException;
import ghp.tilegame.main.tiles.FloorTile;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JFrame;

import no.gamejam.FightEngine;
import no.gamejam.TalkEngine;

public class Game extends Canvas implements Runnable{
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 320, HEIGHT = 192, SCALE = 2, TILESIZE =34;
	public static boolean running = false;
	/* DETTE MÅ FIKSES ;_; */
	public static FightEngine fe;
	public static TalkEngine te;
	public Thread gameThread;
	
	private BufferedImage tileSheet;
	private ImageManager im;
	
	private static Player player;
	private static Nils nils;
//	temp
	private static FloorTile floorTile;
	public static Level level1;
	public static Game game;
	
	public void init(){
		ImageLoader loader = new ImageLoader();
		
		nils = new Nils(7,2);
<<<<<<< HEAD

=======
>>>>>>> 76689378d4558aa60d93e443dda535cc516e79a2
		
		tileSheet = loader.load("resources/sprites/tilesheet.png");
		
		SpriteSheet ss = new SpriteSheet(tileSheet);
		im = new ImageManager(ss);
		player = new Player(0, 0, im);

		//		temp
		floorTile = new FloorTile(im);
//		level1 = new Level(im);
		
		
		try {
			level1 = new LoadLevel().loadLevel(new File("test.level"), im);
			fe = new FightEngine();
			te = new TalkEngine();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		
		level1.registerActor(nils);
		this.addKeyListener(new KeyManager());
	}
	
	public synchronized void start(){
		if(running)return;
		running = true;
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	public synchronized void stop(){
		if(!running)return;
		running = false;
		try {
			gameThread.join();
		} catch (InterruptedException e) {e.printStackTrace();}
	}
	
	public void run(){
		init();
		long lastTime = System.nanoTime();
		final double amountOfTicks = 60D;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		
		while(running){		// 60 fps
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if(delta >= 1){
				tick();
				delta--;
			}
			render();
		}
		stop();
	}
	
	public void tick(){
		player.tick();
//		nils.tick(level1);
	}
	
	public void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		
//		RENDER HERE
		g.fillRect(0, 0, WIDTH*SCALE, HEIGHT*SCALE);
		//level1.renderLevel();
		level1.renderLevel(g, floorTile);
		//floorTile.render(g, 0, 0);
		player.render(g);
//		nils.render(g);
//		END RENDER
		g.dispose();
		bs.show();
	}
	
	public static void main(String[] args)
	{
		game = new Game();
		game.setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		game.setMaximumSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		game.setMinimumSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		
		JFrame frame = new JFrame("Tile RPG");
		frame.setSize(WIDTH*SCALE, HEIGHT*SCALE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.add(game);
		frame.setVisible(true);
		
		game.start();
	}
	
	public static Player getPlayer(){
		return player;
	}

	public static void startDiplomancy(String greeting) {
		System.out.println(greeting);
	}

}
