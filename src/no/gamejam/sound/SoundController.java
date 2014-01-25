/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.gamejam.sound;

import java.io.File;
import java.util.LinkedList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

import no.gamejam.structure.Pair;


/**
 *
 * @author haakon
 */
public class SoundController implements Runnable {
	public static Pair<Thread, SoundController> create(File soundFile) throws Exception {
		SoundFile sf = new SoundFile(soundFile);
		SoundController sc = new SoundController(sf);
		Thread t = new Thread(sc);
		
		return new Pair<Thread, SoundController>(t, sc); 
	}
	String progress = "0%";

	/**
	 * This message will start, pause or resume playback, depending on what it
	 * is currently doing. Made for SoundControlPane.java, in this same package.
	 */
	public static final int MSG_PLAY_PAUSE = 1;

	/**
	 * Send this message to start playback. Once playback has successfully
	 * ended, the clip will be rewinded, so to speak.
	 */
	public static final int MSG_PLAY = 2;

	/**
	 * Send this message to pause playback. Sending the message to play after
	 * this will resume from where you left off.
	 */
	public static final int MSG_PAUSE = 3;

	/**
	 * This not only stops the playback from continuing, but rewinds it to the
	 * beginning.
	 */
	public static final int MSG_STOP = 4;


	private int status;
	private final int PLAYING = 1;
	private final int PAUSED  = 2;
	private final int STOPPED = 3;

	SoundFile sound;

	private Lock lock = new ReentrantLock();
	private LinkedList<Integer> messagebox = new LinkedList<Integer>();

	public SoundController(SoundFile sound) {
		this.sound = sound;
		this.status = STOPPED;

		// Due to implementation differences in the different JVMs, not all controls are necessarily supported on all platforms.
		// OpenJDK doesn't seem to like the libraries we're using for this assignment for instance.
		/* 
		 * Opprinnelig var det noe feilsjekking og slikt her, som skrev til skjermen. Dette er tatt vekk nå, siden det dreide seg om knapper og slikt i en GUI.
		 * Kommentaren over står grunnet historisk interesse.
		 */
	}


	@SuppressWarnings("unused")
	private SoundController() {
		throw new UnsupportedOperationException("Standard constructor is strictly forbidden and makes no sense");
	}

	public void sendMessage(Integer message) {
		lock.lock();
		try {
			this.messagebox.add(message);
		} finally {
			lock.unlock();
		}
	}

	@Override
	public void run() {
		// Let's get ambitious and do an event loop.
		while (true) {
			Integer command = -1;
			/*
			 *  This should give us a classic race condition.
			 *  There are two threads that can request a write to the messagebox: 
			 *  This thread can attempt to take a message out of the mailbox, and the controller thread (main game loop) can attempt to put a message into it.
			 *  Since both cannot do their thing at the same time, we use a reentrant lock, meaning that the other thread has to wait until the first one is done.
			 *  
			 *  This is not considered "the best way" to do it, but it works for our use-case.
			 */
			lock.lock();
			try {
				if(messagebox.size() > 0){
					command = messagebox.remove();
				}
			} finally {
				lock.unlock();
			}
			if (command != -1) {
				switch ((int) command) {
				/*
				 * In the java 7 edition of this code, the final static variables were used instead.
				 * Since Java 6, what the systems at the labs ostensibly run doesn't seem to support that,
				 * This shit happens instead. We COULD have thrown the code through the C preprocessor,
				 * but since a C compiler is not necessarily present at every stage, I thought that to be a bit harsh.
				 * 
				 * I could go back at a later day to create enums or something, but final ints will have to do for now.
				 */

				case MSG_PLAY_PAUSE:
					//					System.out.println("The play/pause button has been pushed! GLORIOUS!");
					if (status == PLAYING) {
						pause();
					}
					else{
						play();
					}
					break;

				case MSG_PLAY:
					play();
					break;

				case MSG_PAUSE:
					pause();
					break;

				case MSG_STOP:
					stop();
					break;

				default:
					System.err.printf("Unrecognized message “%d”,%n", command);
					break;
				}

			}

			// general updating, etc.

			switch (status) {
			case PLAYING:
				if (!sound.isPlaying()) {
					//					System.out.println("We're not currently playing. Stopping");                        
					sound.stop();
					status = STOPPED;
				}
				break;
				// If we're paused or stopped, we have no updating tasks, so we just break off.
			case PAUSED:
			case STOPPED:                
				break;
			default:
				System.err.printf("ERROR: I have no idea what I'm supposed to be doing. Stopping whatever I WAS doing and resetting myself to STOPPED%n");
				this.stop();
				break;
			}

			updateProgress();
			command = -1;
		}
	}

	private void play() {
		try {
			sound.play();
			status = PLAYING;
		} catch (Exception ex) {
			Logger.getLogger(SoundController.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	private void stop() {
		sound.stop();
		status = STOPPED;
	}    

	private void pause() {
		sound.pause();
		status = PAUSED;
	}

	public String progress(){
		return progress;
	}
	private void updateProgress() {
		int percentage = (int) Math.ceil(((double) sound.getClip().getFramePosition() / (double) sound.getClip().getFrameLength()) * 100);
		progress = String.format("%d%%", percentage);

	}

}
