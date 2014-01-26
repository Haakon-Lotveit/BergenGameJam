package no.gamejam.sound;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import java.util.Scanner;

public class SoundDispenser {
	Map<String, ArrayList<SoundController>> småLyder;
	Map<String, LargeSound> storeLyder;
	public SoundDispenser(){
		try{
		småLyder = new HashMap<>();
		storeLyder = new HashMap<>();
		ArrayList<SoundController> samling = new ArrayList<>();
		samling.add(new SoundController(new SoundFile(new File("resources/sound/hit_1.wav"))));
		samling.add(new SoundController(new SoundFile(new File("resources/sound/hit_2.wav"))));
		småLyder.put("HIT", samling);
		
		samling = new ArrayList<>();
		}catch(Exception e){
			e.printStackTrace();
			System.exit(1);
		}
	}
	public SoundController getRandomSound(String type){
		if(!småLyder.containsKey(type)){
			return null;
		}		
		ArrayList<SoundController> valg = småLyder.get(type);
		return valg.get(new Random().nextInt(valg.size()));
	}
	
	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		SoundDispenser disp = new SoundDispenser();
		System.out.print("Hvor mange ganger skal vi spille?");
		int ganger = 5;
//		ganger = Integer.parseInt(kb.nextLine());
		for(int i = 0; i < ganger; ++i){
			System.out.println(i);
			SoundController lyd = disp.getRandomSound("HIT");
			new Thread(lyd).start();
			lyd.sendMessage(SoundController.MSG_PLAY);
		}
		System.out.println("DONE");
	}
}
