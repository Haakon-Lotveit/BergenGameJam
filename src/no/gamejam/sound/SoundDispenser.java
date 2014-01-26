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
		samling.add(new SoundController(new SoundFile(new File("resources/sound/swoosh_1.wav"))));
		samling.add(new SoundController(new SoundFile(new File("resources/sound/swoosh_2.wav"))));
		småLyder.put("SWOOSH", samling);
		
		samling = new ArrayList<>();
		samling.add(new SoundController(new SoundFile(new File("resources/sound/step_1.wav"))));
		samling.add(new SoundController(new SoundFile(new File("resources/sound/step_2.wav"))));
		samling.add(new SoundController(new SoundFile(new File("resources/sound/step_3.wav"))));
		småLyder.put("STEP", samling);
		
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
		int which = new Random().nextInt(valg.size());
//		System.out.printf("%d/%d%n", which, valg.size());
		return valg.get(which);
	}
	
	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		SoundDispenser disp = new SoundDispenser();
		int ganger = 10;
		long venting = 2000L;
		for(int i = 0; i < ganger; ++i){
			
			disp.playRandom("HIT");
			
			Long time = System.currentTimeMillis();
			while(System.currentTimeMillis() - time < venting){
				continue;
			}
		}
	}
	public void playRandom(String type){
		SoundController sc = getRandomSound(type);
		new SoundThread(sc).start();
		sc.sendMessage(SoundController.MSG_PLAY);
	}
}
