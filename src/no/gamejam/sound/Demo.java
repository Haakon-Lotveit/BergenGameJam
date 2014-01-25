package no.gamejam.sound;

import java.io.File;
import java.util.Scanner;
import static no.gamejam.sound.SoundController.*;
public class Demo {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		SoundFile sf = new SoundFile(new File("resources/music/Sitron_Krig_v0.1.wav"));
		SoundController sc = new SoundController(sf);
		Thread sound = new Thread(sc);
		sound.start();
		Scanner kb = new Scanner(System.in);
		System.out.println("Kommandoer:");
		System.out.println("[P]lay/pause");
		System.out.println("[S]top");
		System.out.println("[Q]uit");
		
		boolean done = false;
		
		while(!done){
			char c = kb.nextLine().trim().toLowerCase().charAt(0);
			System.out.printf("Button: %c%n", c);
			switch(c){
			case 'p': sc.sendMessage(MSG_PLAY_PAUSE); break;
			case 's': sc.sendMessage(MSG_STOP); break;
			case 'q': done = true; break;
			default: System.out.println("Undefined key pressed");
			}
		}
		
		kb.close();
		System.exit(0);
		
		
	}

}
