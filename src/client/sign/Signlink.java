package client.sign;

import java.applet.Applet;
import java.io.*;
import java.net.*;
import javax.sound.midi.*;
import javax.sound.sampled.*;

public final class Signlink implements Runnable {
    
    public int waveTimer1, waveTimer2 = 0;
    public int WaveNumber = 1;
    public static int clientUID;
    public static RandomAccessFile cacheDatFile = null;
    public static RandomAccessFile cacheIndexFiles[] = new RandomAccessFile[5];
    public static boolean sunjava;
    public static Applet mainApplet = null;
    private static boolean active;
    private static int threadliveid;
    private static InetAddress socketip;
    private static int socketreq;
    private static Socket socket = null;
    private static int threadreqpri = 1;
    private static Runnable threadreq = null;
    private static String addressToDNSLookup = null;
    public static String hostnameFromDNS = null;
    private static String urlreq = null;
    public static DataInputStream urlstream = null;
    public static int savelenmidi;
    public static String[] savereq = new String[11];
    public static String savereqmidi = null;
    public static byte savebufmidi[] = null;
    public static boolean midiplay;
    public static int midipos;
    public static String midi = null;
    public static int midivol;
    public static int midifade;
    public static boolean[] waveplay = new boolean[11];
    public static int wavevol;
    public static boolean music = true;
    public static boolean reporterror = true;
    public static String errorname = "";
    enum Position { LEFT, RIGHT, NORMAL };
    public static Sequencer musicSr = null;
    public static Sequence musicS = null;
    
    public Signlink() {
    }
    
    @Override
    public final void run() {
        active = true;
        String cacheDIR = findCacheDIR();
        clientUID = getUID();
        try {
            
            File cacheFile = new File(cacheDIR + "main_file_cache.dat");
            
            // If file is too large?
            if (cacheFile.exists() && cacheFile.length() > 0x3200000L) {
                cacheFile.delete();
            }
            
            cacheDatFile = new RandomAccessFile(cacheDIR + "main_file_cache.dat", "rw");
            
            for (int idxfile = 0; idxfile < 5; idxfile++) {
                cacheIndexFiles[idxfile] = new RandomAccessFile(cacheDIR + "main_file_cache.idx" + idxfile, "rw");
            }
            
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        
        for (int i = threadliveid; threadliveid == i;) {
            if (socketreq != 0) {
                try {
                    socket = new Socket(socketip, socketreq);
                } catch (Exception e) {
                    socket = null;
                }
                socketreq = 0;
            } else if (threadreq != null) {
                Thread thread = new Thread(threadreq);
                thread.setDaemon(true);
                thread.start();
                thread.setPriority(threadreqpri);
                threadreq = null;
            } else if (addressToDNSLookup != null) {
                try {
                    hostnameFromDNS = InetAddress.getByName(addressToDNSLookup).getHostName();
                } catch (Exception e) {
                    hostnameFromDNS = "unknown";
                }
                addressToDNSLookup = null;
            } else if (midiplay) {
                playMidi(cacheDIR);
                midiplay = false;
                savereqmidi = null;
            } else if (urlreq != null) {
                try {
                    urlstream = new DataInputStream((new URL(mainApplet.getCodeBase(), urlreq)).openStream());
                } catch (Exception _ex) {
                    urlstream = null;
                }
                urlreq = null;
            }
            try {
                Thread.sleep(50L);
            } catch (Exception _ex) {
            }
        }
    }
    
        public static void startpriv(InetAddress inetaddress) {
        threadliveid = (int) (Math.random() * 99999999D);
        if (active) {
            try {
                Thread.sleep(500L);
            } catch (Exception _ex) {
            }
            active = false;
        }
        socketreq = 0;
        threadreq = null;
        addressToDNSLookup = null;
        savereqmidi = null;
        urlreq = null;
        socketip = inetaddress;
        Thread thread = new Thread(new Signlink());
        thread.setDaemon(true);
        thread.start();
        while (!active) {
            try {
                Thread.sleep(50L);
            } catch (Exception _ex) {
            }
        }
    }

    public static String findCacheDIR() {
        // System.getProperty("user.home")+System.getProperty("file.separator"
        try {
            String s = "cache317";
            File file = new File(s);
            if (file.exists() || file.mkdir()) {
                return s + "/";
            }
        } catch (Exception e) {
        }
        return null;
    }

    private static int getUID() {
        return (int) (Math.random() * 99999999D);
    }

//    public static synchronized Socket opensocket(int i) throws IOException {
//        for (socketreq = i; socketreq != 0;) {
//            try {
//                Thread.sleep(50L);
//            } catch (Exception _ex) {
//            }
//        }
//        if (socket == null) {
//            throw new IOException("could not open socket");
//        } else {
//            return socket;
//        }
//    }

//    public static synchronized DataInputStream openurl(String s) throws IOException {
//        for (urlreq = s; urlreq != null;) {
//            try {
//                Thread.sleep(50L);
//            } catch (Exception _ex) {
//            }
//        }
//        if (urlstream == null) {
//            throw new IOException("could not open: " + s);
//        } else {
//            return urlstream;
//        }
//    }

    public static synchronized void dnslookup(String s) {
        hostnameFromDNS = s;
        addressToDNSLookup = s;
    }

    public static synchronized void startthread(Runnable runnable, int i) {
        threadreqpri = i;
        threadreq = runnable;
    }

    public static synchronized boolean wavesave(byte abyte0[], int i, int j) {
        String s = findCacheDIR();
        String savereqwave;
        byte savebufwave[];
        int savelenwave;
        int waveposwave = 1;
        if (i > 0x1e8480) {
            return false;
        }
        waveposwave = (waveposwave) % 15;
        savelenwave = i;
        savebufwave = abyte0;
        waveplay[j] = true;
        savereqwave = "sound" + waveposwave + ".wav";
        savereq[j] = savereqwave;
        saveSound(s, savereqwave, savebufwave, savelenwave);
        return true;
    }

    public static synchronized boolean wavereplay(int j) {
        int waveposwave = 1;
        waveposwave = (waveposwave) % 15;
        waveplay[j] = true;
        savereq[j] = "sound" + waveposwave + ".wav";
        return true;
    }

    public static synchronized void midisave(byte abyte0[], int i) {
        if (i > 0x1e8480) {
            return;
        }
        if (savereqmidi != null) {
            return;
        } else {
            midipos = (midipos + 1) % 5;
            savelenmidi = i;
            savebufmidi = abyte0;
            midiplay = true;
            savereqmidi = "jingle" + midipos + ".mid";
            return;
        }
    }

    public static void saveSound(String swave, String savereqwave, byte savebufwave[], int savelenwave) {
        try {
            FileOutputStream fileoutputstream = new FileOutputStream(swave + savereqwave);
            fileoutputstream.write(savebufwave, 0, savelenwave);
            fileoutputstream.close();
        } catch (Exception _ex) {
        }
    }

    public static boolean playSound(String swave, String savereqwave, int j) {
        String[] wave = new String[10];
        String[] savereqwave2 = new String[10];
        savereqwave2[j] = savereqwave;
        if (savereqwave2[j] != null) {
            wave[j] = swave + savereqwave2[j];
            AudioInputStream[] audioInputStream = new AudioInputStream[10];
            try {
                audioInputStream[j] = AudioSystem.getAudioInputStream(new File(wave[j]));
            } catch (UnsupportedAudioFileException e1) {
                e1.printStackTrace(System.out);
                return false;
            } catch (IOException e1) {
                e1.printStackTrace(System.out);
                return false;
            }
            AudioFormat[] format = new AudioFormat[10];
            format[j] = audioInputStream[j].getFormat();
            SourceDataLine[] auline = new SourceDataLine[10];
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format[j]);
            try {
                auline[j] = (SourceDataLine) AudioSystem.getLine(info);
                auline[j].open(format[j]);
            } catch (LineUnavailableException e) {
                e.printStackTrace(System.out);
                return false;
            } catch (Exception e) {
                e.printStackTrace(System.out);
                return false;
            }
            auline[j].start();
            int nBytesRead[] = new int[10];
            byte[] abData[] = new byte[5200][10];
            try {
                while (nBytesRead[j] != -1) {
                    nBytesRead[j] = audioInputStream[j].read(abData[j], 0, abData[j].length);
                    if (nBytesRead[j] >= 0) {
                        auline[j].write(abData[j], 0, nBytesRead[j]);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace(System.out);
                return false;
            } finally {
                auline[j].drain();
                auline[j].close();
            }
        }
        try {
            Thread.sleep(500L);
        } catch (Exception _ex) {
        }
        waveplay[j] = false;
        return true;
    }

    public static void reportError(String s) {
        if (!reporterror) {
            return;
        }
        if (!active) {
            return;
        }
        System.out.println("Error: " + s);
        //try {
            s = s.replace(':', '_');
            s = s.replace('@', '_');
            s = s.replace('&', '_');
            s = s.replace('#', '_');
            
            // This reports the error to a page that would log it.
            //DataInputStream datainputstream = openurl("reporterror" + 317 + ".cgi?error=" + errorname + " " + s);
            //datainputstream.readLine();
            //datainputstream.close();
            
            return;
        // catch (IOException ioe) {
            //return;
        //}
    }

    public static boolean playMidi(String s) {
        midi = s + savereqmidi;
        try {
            if (musicSr != null) {
                musicSr.stop();
                musicSr.close();
            }
            musicSr = null;
            musicS = null;

            File music_1 = new File(midi);
            if (music_1.exists()) {
                musicS = MidiSystem.getSequence(music_1);
            }

            // Create a sequencer for the sequence
            musicSr = MidiSystem.getSequencer();
            musicSr.open();
            musicSr.setSequence(musicS);
            musicSr.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
            musicSr.start();

        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
        return true;
    }
}
