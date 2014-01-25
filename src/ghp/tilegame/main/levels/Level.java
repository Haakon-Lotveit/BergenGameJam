package ghp.tilegame.main.levels;

import ghp.tilegame.main.gfx.ImageManager;
import ghp.tilegame.main.tiles.FloorTile;
import ghp.tilegame.main.tiles.Tile;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import no.gamejam.Actor;
import no.gamejam.TileMechanics;

public class Level 
{
	private final File standardFile = new File("resources/text/Level1Map");
	
	private int tilesX = 10, tilesY = 6;
	
	private int[][] tiles;
	//private static FloorTile floorTile;
	
	/*public Tile[][] tiles;
	
	for (int i=0; i<tilesY; i++){
		for (int j=0; j<tilesX; j++){
			tiles[i][j] = new Tile();
		}
	}*/
	
	public void setTile(int x, int y, Tile tileType){
		//tileType.render(g, 0, 0);
	}
	
	public Level (ImageManager im){
		LoadLevel();
	}
	
	public void LoadLevel(){
		
		tiles = new int[tilesX][tilesY];
		for(int y=0; y<tilesY; y++){
			for(int x=0; x<tilesX; x++){
				if(true){ 	// if floor type tile
					//floorTile.render(g, tilesX*64, tilesY*64);
					tiles[x][y] = 0;
				}
			}
		}
	}
	
	public void renderLevel(Graphics g, Tile floorTile){
		for(int y=0; y<tilesY; y++){
			for(int x=0; x<tilesX; x++){
				if(tiles[x][y] == 0){ 	// if floor type tile
					floorTile.render(g, x*64, y*64);
					
				}
			}
		}
	}

}
