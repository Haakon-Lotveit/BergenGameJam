package no.gamejam.sound;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.sound.sampled.Clip;

public class LargeSound implements Runnable {
	public static final int MSG_PLAY = 1;
	public static final int MSG_STOP = 2;

	private Lock lock = new ReentrantLock();
	private AudioClip clip;
	
	private List<Integer> messagebox = new LinkedList<>();

	public void sendMessage(Integer message) {
		System.out.println("YOU GOT MAIL");
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
					command = messagebox.remove(0);
					System.out.println("MAIL!");
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

				case MSG_PLAY:
					System.out.println("Playing!");
					play();
					break;

				case MSG_STOP:
					stop();
					break;

				default:
					System.err.printf("Unrecognized message “%d”,%n", command);
					break;
				}

			}

		}
	}
	private void play(){
		clip.loop();
	}
	private void stop(){
		clip.stop();
	}
	
	public LargeSound(URL soundLocation){
		clip = Applet.newAudioClip(soundLocation);
	}
	
	public static void main(String[] args) throws Exception{
		AudioClip clip = Applet.newAudioClip(new File("test.wav").toURI().toURL());
		clip.loop();
	}
}
