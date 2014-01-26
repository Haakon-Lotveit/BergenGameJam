package no.gamejam.sound;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundThread extends Thread {
	private final SoundController SC;

	
	public static SoundThread create(SoundController controller) {
		return new SoundThread(controller);		
	}
	public static SoundThread create(SoundFile soundFile){
		return SoundThread.create(new SoundController(soundFile));
	}
	/**
	 * 
	 * @param soundfile A string representing the path to an audio file.
	 * @return a new SoundThread that you can use for audial purposes
	 * @throws FileNotFoundException 
	 * @throws UnsupportedAudioFileException
	 * @throws IOException - For instance, if we don't have read-access or our read-lock gets revoked or whatever else can go wrong.
	 * @throws LineUnavailableException
	 */
	public static SoundThread create(File soundFile) throws FileNotFoundException, UnsupportedAudioFileException, IOException, LineUnavailableException{
		return create(new SoundFile(soundFile));
	}
	/**
	 * 
	 * @param soundfile A string representing the path to an audio file.
	 * @return a new SoundThread that you can use for audial purposes
	 * @throws FileNotFoundException 
	 * @throws UnsupportedAudioFileException
	 * @throws IOException - For instance, if we don't have read-access or our read-lock gets revoked or whatever else can go wrong.
	 * @throws LineUnavailableException
	 */
	public static SoundThread create(String soundfile) throws FileNotFoundException, UnsupportedAudioFileException, IOException, LineUnavailableException{
		return create(new File(soundfile));
	}
	
	public SoundThread(SoundController sc){
		this.SC = sc;
	}
	
	/**
	 * Since you might want to send message to the controller, you can fetch it with this method.
	 * @return the SoundController associated with this thread. The standard implementation uses reentrant locks, so buyer beware.
	 */
	public SoundController getController(){
		return this.SC;
	}
	
	@Override
	public void run(){
			SC.run();
	}
}
