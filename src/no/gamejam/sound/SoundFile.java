package no.gamejam.sound;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.BooleanControl;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundFile {
	public static final boolean debug = Boolean.TRUE;
	
    private File soundfile;
    private AudioInputStream ais;
    private Clip clip;
    private DataLine.Info line;
    private FloatControl gain;
    private BooleanControl mute;
    private boolean playing;

    /**
     * Constructs a new soundfile object, which is a simple wrapper over the
     * java sound libraries. Does NOT throw an error if certain controls are not
     * present, but asking for them will return nulls. You have to check for
     * them.
     *
     * @param f The file to be played
     * @throws FileNotFoundException If the file does not exist
     * @throws UnsupportedAudioFileException If the system cannot play this file
     * @throws IOException If IO goes berserk for watever reason.
     * @throws LineUnavailableException If there is no line to the mixer
     * @see {@link AudioSystem} {@link DataLine} {@link Clip}
     */
    public SoundFile(File f) throws FileNotFoundException, UnsupportedAudioFileException, IOException, LineUnavailableException {
        if (!f.exists()) {
            throw new FileNotFoundException(String.format("File at \"%s\" could not be found%n", f.toURI()));
        }
        this.soundfile = f;
        ais = AudioSystem.getAudioInputStream(soundfile);
        line = new DataLine.Info(Clip.class, ais.getFormat());
        clip = (Clip) AudioSystem.getLine(line);
        clip.open(ais);

        if (clip.isControlSupported(BooleanControl.Type.MUTE)) {
            this.mute = (BooleanControl) clip.getControl(BooleanControl.Type.MUTE);
        } else {
            if(debug) {System.err.printf("Boolean control for muting audio is unsupported.%nAudiofile concerned: %s%n", f.toURI().toString());}
            this.mute = null;
        }
        if (clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
            this.gain = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        } else {
            if(debug) {System.err.printf("Mixer control for gain is unsupported.%nAudiofile concerned: %s%n", f.toURI().toString());}
            this.gain = null;
        }

        this.playing = false;


        clip.addLineListener(new LineListener() {
            @Override
            public void update(LineEvent evt) {
                if (evt.getType() == LineEvent.Type.STOP) {
                    playing = false;
                } else if (evt.getType() == LineEvent.Type.START) {
                    playing = true;
                }
            }
        });
    }

    public FloatControl getGainControl() {
        return this.gain;
    }

    public BooleanControl getMuteControl() {
        return this.mute;
    }

    public void play(int times) throws LineUnavailableException, IOException {
        playing = true;
//		System.out.printf("%s.play(times=%d) called%n",this.getClass().getCanonicalName(), times);

        if (clip.isRunning()) {
            System.out.println("Sound is still running, exiting");
            return;
        } else if (clip.getFramePosition() == clip.getFrameLength()) {
            clip.setFramePosition(0);
        }
//        System.out.printf("Clips at position %d/%d%n", clip.getFramePosition(), clip.getFrameLength());
        clip.loop(times - 1);


    }

    public void play() throws LineUnavailableException, IOException {
        playing = true;
        this.play(1);
    }

    public void pause() {
        playing = false;
        clip.stop();
    }

    @Override
    public SoundFile clone() {
        try {
            return new SoundFile(soundfile);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Clip getClip() {
        return this.clip;
    }

    
    public boolean isPlaying() {
//        System.out.println(playing? "Yes we are playing":"No we are not playing");
        return playing;
    }

    public void stop() {
        playing = false;
        clip.stop();
        clip.setFramePosition(0);
    }
    
    public static void main(String[] args) throws Exception {
        if (System.getProperties().getProperty("java.vm.name").contains("Java HotSpot(TM)")) {
            System.out.println("Oracle java detected");
        } else {
            System.out.println("Nonoracle java detected");
        }

        System.out.println();
        System.out.println("Starting actual playback:");
        File soundfile = new File("resources/music/Sitron_Krig_v0.1.wav");
        SoundFile sf = new SoundFile(soundfile);
        sf.play(1);
        Thread.sleep(5000L);
        sf.pause();
        Thread.sleep(1000L);
        sf.play(2);
        Thread.sleep(6000L);

    }

}