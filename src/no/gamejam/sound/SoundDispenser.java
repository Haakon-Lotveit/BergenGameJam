


package no.gamejam.sound;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import java.util.Scanner;

import kuusisto.tinysound.Sound;
import kuusisto.tinysound.TinySound;

public class SoundDispenser {
	protected Map<String, ArrayList<Sound>> sounds;
	
	public SoundDispenser(){
		sounds = new HashMap<>();
		String prefix = "resources/sound/";
		ArrayList<Sound> group = new ArrayList<>();
		group.add(TinySound.loadSound(new File(String.format("%s%s", prefix, "fx_atmos.wav"))));
		sounds.put("ATMOS", group);
		group = new ArrayList<>();
		group.add(TinySound.loadSound(new File(String.format("%s%s", prefix, "hit_1.wav"))));
		group.add(TinySound.loadSound(new File(String.format("%s%s", prefix, "hit_2.wav"))));
		sounds.put("HIT", group);
		group = new ArrayList<>();
		group.add(TinySound.loadSound(new File(String.format("%s%s", prefix, "step_1.wav"))));
		group.add(TinySound.loadSound(new File(String.format("%s%s", prefix, "step_2.wav"))));
		group.add(TinySound.loadSound(new File(String.format("%s%s", prefix, "step_3.wav"))));
		sounds.put("STEP", group);
		group = new ArrayList<>();
		group.add(TinySound.loadSound(new File(String.format("%s%s", prefix, "swoosh_1.wav"))));
		group.add(TinySound.loadSound(new File(String.format("%s%s", prefix, "swoosh_2.wav"))));
		sounds.put("SWOOSH", group);
	}
	
	public void playRandom(String type){
		if(!sounds.containsKey(type)){
			System.err.printf("No sounds has been registered as %s%n", type);
		}
		ArrayList<Sound> group = sounds.get(type);
		Random r = new Random();
		
		group.get(r.nextInt(group.size())).play();
	}
}
