package client.custom;

import java.io.BufferedInputStream;
import java.io.File;

import java.io.FileInputStream;
import java.io.FilenameFilter;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;

/*
 * INIT:
 * MP3 mp3Instance = new MP3();
 * mp3Instance.setFolder();
 * 
 * PLAY:
 * mp3Instance.playMP3();
 * 
 * PAUSE:
 * mp3Instance.pauseMP3();
 * 
 * RESUME:
 * mp3Instance.playMP3();
 * 
 * STOP:
 * mp3Instance.stopMP3();
 * 
 * SKIP FORWARD:
 * mp3Instance.skipForward();
 * 
 * SKIP BACKWARD:
 * mp3Instance.skipBackward();
 * 
 * 
 * 
 * 
 */

public class MP3 extends Thread {
    
    public File mp3Folder; // Folder to play from.
    public boolean playing; // Is currently playing.
    public boolean paused; // If is paused.
    public int currentSong; // Current position in the array to play.
    public File[] mp3Files; // The array of files.
    private int currentFrame; // Frame is paused.
    
    //private String filename;
    public PlaybackEvent playbackEvent;
    public AdvancedPlayer player;  // The player.

    
    
    // Constructor.
    public MP3() {
        mp3Folder = null;
        playing = false;
        currentSong = 0;
    }
    
    @Override
    public void run() {
    }
    
    // PUBLIC FUNCTIONS //
    
    /**
     * Set a folder path to play from.
     * Does not start any MP3 playing.
     * 
     * @param folderPath The folder path to play from.
     */
    public void setFolder(String folderPath) {
        mp3Folder = new File(folderPath);
        scanMP3Folder();
    }
    
    /**
     * Starts the current song playing.
     */
    public void playMP3FromStart() {
        playFile(mp3Files[currentSong], 0);
    }
    
    public void pauseOrResumeMP3() {
        if(paused && playing) {
            resumeMP3();
        } else {
            pauseMP3();
        }
    }
    
    public void pauseMP3() {
        paused = true;
        if (player != null)
            currentFrame = playbackEvent.getFrame();
        else
            currentFrame = 0;
        player.stop();
    }
    
    public void resumeMP3() {
        if(paused && player != null) {
            playFile(mp3Files[currentSong], currentFrame);
            paused = false;
        }
    }
    
    
    /**
     * Stop playing, change to next song, then play that song.
     */
    public void skipForward() {
        stopMP3();
        if(currentSong == mp3Files.length-1)
            currentSong = 0;
        else
            currentSong++;
        playFile(mp3Files[currentSong], 0);
    }
    
    /**
     * Stop playing, change to previous song, then play that song.
     */
    public void skipBackward() {
        stopMP3();
        if(currentSong == 0)
            currentSong = mp3Files.length-1;
        else
            currentSong--;
        playFile(mp3Files[currentSong], 0);
    }
    
    /**
     * Checks something is playing, then close it.
     */
    public void stopMP3()  {
        if (player != null)
            player.stop();
        playing = false;
        paused = false;
    }
    
    
    
    
    
    
    
    
    // PRIVATE METHODS //
    
    private void scanMP3Folder() {
        FilenameFilter filter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.contains(".mp3");
            }
        };
        File[] listOfFiles = mp3Folder.listFiles(filter);
        if (listOfFiles == null) {
            mp3Files = null;
        } else {
            mp3Files = new File[listOfFiles.length];
            for(int i = 0; i <listOfFiles.length; i++) {
                mp3Files[i] = listOfFiles[i];
                System.out.println(listOfFiles[i].toString() + " : " + i);
            }
        }
    }
    
    private void playFile(File mp3File, int startingFrame) {
        try {
            FileInputStream fis     = new FileInputStream(mp3File);
            BufferedInputStream bis = new BufferedInputStream(fis);
            player = new AdvancedPlayer(bis);
            player.play(startingFrame, ); //startingFrame
            playbackEvent = new PlaybackEvent(player, 0, startingFrame);
            System.out.println("Playing " + mp3File.toString() + " : " + startingFrame);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        playing = true;
        paused = false;
    }
}
