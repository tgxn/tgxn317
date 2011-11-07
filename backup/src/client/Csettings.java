package client;

import java.io.*;
import java.util.*;
import sign.signlink;
import java.awt.*;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public final class Csettings {


    /*/ Server Settings \*\
    
    @String mainIconLocation
    -> Set the path for the frames logo.
    
    \*\                 /*/
    public static String mainIconLocation = "icon.png";

    /*/ Server Settings \*\
    
    @String server
    -> Set the IP or Hostname for server.
    
    @String port
    -> Sets port for the above server.
    
    @int intPort
    -> Sets port for the above server. (Interger)
    
    @String cacheURL
    -> Sets the URL to get the cache file from.
    
    @String cacheNAME
    -> Sets the name of the cache file.
    
    @clientDownloadURL
    -> Sets the URL to download the client, if that has been updated.
    
    \*\                 /*/
    public static String server = "localhost";
    public static String port = "43594";
    
    public static String versionCheck = "http://www.tgxn.net/projects/rsps/checkversion.php";
    public static String cacheURL = "http://www.tgxn.net/projects/rsps/cache.zip";
    public static String cacheNAME = "cache.zip";
    public static String clientDownloadURL = "http://www.tgxn.net/projects/rsps/download.php?client";
    /*/ Loading Settings \*\
    
    @boolean LoadingBarOn
    -> Enables or Disables the loading bar
    
    @boolean ShowLoadingText
    -> Enables or Disables text on the loading bar.
    
    @int LoadingBarStyle
    -> Sets the loading bars style. 0: Themed 1: New RS 2: Old RS
    
    @int barWidth
    -> Width of the classic loading bar.
    
    @int barHeight
    -> Height of the classic loading bar.
    
    
    \*\              /*/
    public static boolean LoadingBarOn = true;
    public static boolean ShowLoadingText = true;
    public static int LoadingBarStyle = 0;
    public static int barWidth = 300;
    public static int barHeight = 15;
    /*/ Sky & In-Game Settings \*\
    
    @boolean skySmog
    -> Enables or Disables coloured sky. 
    
    @int SkyColour ::(SkySmog must be ON)::
    -> Sets the colour for the sky in a hex value.
    
    @boolean ShowNearby
    -> If true, amount of nearby players will be shown.
    
    \*\              /*/
    public static boolean skySmog = false;
    public static int SkyColour = 0xC8C0A8; //brown
    public static boolean ShowNearby = true;
    public static boolean CustomCursor = true;
    public static String CustomCursorPath = "sprites/misc/cursor.png";
    /*/ Chat Settings \*\
    
    @int chatColour
    -> Sets the colour for defualt chat.
    
    @int chatEffect
    -> Sets the default for the chat effect.
    
    @oolean censor
    -> Turns the word Censor on or off.
    
    @String badWords[]
    -> Array for storing bad words, loads from a file.
    
    
    @boolean RWQuote
    -> If "true" messages with bad words will not be sent and the player will get a random quote
    
    @String badQuotes[] ::(RWQuote must be TRUE)::
    -> Array storing the Quotes for the above option
    
    \*\              /*/
    public static int chatColour = 0;
    public static int chatEffect = 0;
    public static boolean censor = true;
    //this should get loaded after the cache...
    public static String badWords[] = loadFile(sign.signlink.findCacheDIR() + "bad/words.cfg");
    public static boolean RWQuote = true;
    public static String badQuotes[] = {
        "Please do not swear!",
        "Please do not insult other members!",
        "Please Dont say that!",
        "If you say that agian you might get kicked!"
    };
    public static String hilightCueWord = "hl:";
    /*/ Player Settings \*\
    
    @int HPBarID
    -> Sets the defualt HP Bar style. (0-6)
    
    @boolean HeadHP
    -> Turn HP over heads default on or off.
    
    \*\             /*/
    public static int HPBarID = 1;
    public static boolean HeadHP = true;
    public static boolean headNames = true;
    public static int headNamesColour = 0x0000ff;
    /*/ DO NOT EDIT BELOW THIS LINE \*\
    \*\(unless your a smart cunt :D)/*/
    public static String serverList[][] = {
        //fave, name, website, ip, port, location/flag 0-4
        {"1", "World 1", "http://www.tgxn.net/", "AwesomeVille", "localhost", "43594", "0"},
        {"1", "World 2", "http://www.tgxn.net/", "WootLand", "localhost", "43595", "0"},
        {"1", "World 3", "http://www.tgxn.net/", "Random House", "localhost", "43596", "0"},
        {"1", "World 4", "http://www.tgxn.net/", "Cake Place", "localhost", "43597", "0"},
        {"1", "World 5", "http://www.tgxn.net/", "Pizza Cave", "localhost", "43598", "0"},
        {"0", "World x", "http://www.example.com/", "Worlds End", "localhost", "43594", "1"},
        {"0", "World x", "http://www.example.com/", "Worlds End", "localhost", "43594", "2"},
        {"0", "World x", "http://www.example.com/", "Worlds End", "localhost", "43594", "3"},
        {"0", "World x", "http://www.example.com/", "Worlds End", "localhost", "43594", "4"},
        {"0", "World x", "http://www.example.com/", "Worlds End", "localhost", "43594", "1"},
        {"0", "World x", "http://www.example.com/", "Worlds End", "localhost", "43594", "2"},
        {"0", "World x", "http://www.example.com/", "Worlds End", "localhost", "43594", "3"},
        {"0", "World x", "http://www.example.com/", "Worlds End", "localhost", "43594", "4"},
        {"0", "World x", "http://www.example.com/", "Worlds End", "localhost", "43594", "1"},
        {"0", "World x", "http://www.example.com/", "Worlds End", "localhost", "43594", "2"},
        {"0", "World x", "http://www.example.com/", "Worlds End", "localhost", "43594", "3"},
        {"0", "World x", "http://www.example.com/", "Worlds End", "localhost", "43594", "4"},
        {"0", "World x", "http://www.example.com/", "Worlds End", "localhost", "43594", "1"},
        {"0", "World x", "http://www.example.com/", "Worlds End", "localhost", "43594", "2"},
        {"0", "World x", "http://www.example.com/", "Worlds End", "localhost", "43594", "3"},
        {"0", "World x", "http://www.example.com/", "Worlds End", "localhost", "43594", "4"}
    };
    public static String clientVersion = "cli-b12";

    public static String cacheVersion() {
        String revision = "none";
        try {
            Properties p = new Properties();
            p.load(new FileInputStream(sign.signlink.findCacheDIR() + "version.ini"));
            revision = p.getProperty("rev");
        } catch (Exception e) {
            revision = "rev";
        }
        return revision;
    }

    public static String[] loadFile(final String f) {
        int i = 0;
        String line;
        int count = 0;
        try {
            File file = new File(f);
            if (file.exists()) {
                FileReader fr = new FileReader(file);
                LineNumberReader ln = new LineNumberReader(fr);
                while (ln.readLine() != null) {
                    count++;
                }
            }
        } catch (IOException e) {
            System.out.println("There was a problem loading the badwords file!");
            //e.printStackTrace();
        }
        String data[] = new String[count];
        try {
            BufferedReader in = new BufferedReader(new FileReader(f));
            while ((line = in.readLine()) != null) {
                data[i] = line;
                i++;
            }
        } catch (IOException e) {
            System.out.println("There was a problem loading the badwords file!");
            //e.printStackTrace();
        }
        return data;
    }

    public static int exitWarningDialog() {
        JOptionPane pane = new JOptionPane("Are you sure you want to quit?", JOptionPane.QUESTION_MESSAGE);
        Object[] options = new String[]{"Yes", "No"};
        pane.setOptions(options);
        JDialog dialog = pane.createDialog(new JFrame(), "Really Exit?");
        Image image1 = Toolkit.getDefaultToolkit().getImage(signlink.findCacheDIR() + mainIconLocation); //icon
        dialog.setIconImage(image1);
        dialog.setVisible(true); //show it
        Object obj = pane.getValue();
        int result = -1;
        for (int k = 0; k < options.length; k++) {
            if (options[k].equals(obj)) {
                result = k;
            }
        }
        if (result == 0) {
            System.exit(0);
            return 1;
        } else {
            return 0;
        }

    }
}