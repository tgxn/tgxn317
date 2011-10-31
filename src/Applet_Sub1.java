
import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.Toolkit;
import java.io.*;

public class Applet_Sub1 extends Applet implements Runnable, MouseListener, MouseMotionListener, KeyListener, FocusListener, WindowListener, MouseWheelListener {

    public String getClipboardContents() { //for paste
        String result = "";
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        //odd: the Object param of getContents is not currently used
        Transferable contents = clipboard.getContents(null);
        boolean hasTransferableText = (contents != null) && contents.isDataFlavorSupported(DataFlavor.stringFlavor);
        if (hasTransferableText) {
            try {
                result = (String) contents.getTransferData(DataFlavor.stringFlavor);
            } catch (UnsupportedFlavorException ex) {
                //highly unlikely since we are using a standard DataFlavor
                System.out.println(ex);
                ex.printStackTrace();
            } catch (IOException ex) {
                System.out.println(ex);
                ex.printStackTrace();
            }
        }
        return result;
    }

    public void mouseWheelMoved(MouseWheelEvent e) {
        int notches = e.getWheelRotation();
        if (anInt20 > 0 && anInt21 > 346 && anInt20 < 516 && anInt21 < 505) {
            if (notches < 0) {
                if (client.chatBoxScrollbar.anInt224 < 1) {
                    client.scrollableChatArea = client.scrollableChatArea;
                    client.needChatAreaRedraw = true;
                    client.inputTaken = true;
                } else {
                    client.scrollableChatArea += 30;
                    client.needChatAreaRedraw = true;
                    client.inputTaken = true;
                }
            } else {
                if (client.scrollableChatArea < 1) {
                    client.scrollableChatArea = 0;
                    client.needChatAreaRedraw = true;
                    client.inputTaken = true;
                } else {
                    client.scrollableChatArea -= 30;
                    client.needChatAreaRedraw = true;
                    client.inputTaken = true;
                }
            }
        }
        if (anInt21 > 210 && anInt21 < 473 && anInt20 > 514 && anInt20 < 762) { //Invertory
            if (client.tabInterfaceIDs[client.tabID] == 638) //quest tab
            {
                Class9.aClass9Array210[639].anInt224 += notches * 30; //scrollPosition
            }
        }

        if (anInt20 > 0 && anInt20 < 512 && anInt21 > 0 && anInt21 < 334) { //Ingame
            if (client.openInterfaceID == 5292) {
                Class9.aClass9Array210[5385].anInt224 += notches * 30;
            }
        }
    }

    public final void createClientFrame(int myHeight, int myWidth) { //createClientFrame
        aFrame_Sub1_15 = new Frame_Sub1(this, myWidth, (byte) 5, myHeight);
        aGraphics12 = getGameComponent().getGraphics();
        aClass15_13 = new RSImageProducer(myWidth, myHeight, getGameComponent(), 0);
        startRunnable(this, 1);
        return;
    }

    public final void initClientFrame(int myHeight, int myWidth) { //initClientFrame
        aGraphics12 = getGameComponent().getGraphics();
        aClass15_13 = new RSImageProducer(myWidth, myHeight, getGameComponent(), 0);
        startRunnable(this, 1);
    }

    public void run() {
        getGameComponent().addMouseWheelListener(this);
        oldClick = System.currentTimeMillis();
        getGameComponent().addMouseListener(this);
        getGameComponent().addMouseMotionListener(this);
        getGameComponent().addKeyListener(this);
        getGameComponent().addFocusListener(this);
        if (aFrame_Sub1_15 != null) {
            aFrame_Sub1_15.addWindowListener(this);
        }
        startUp();
        int i = 0;
        int j = 256;
        int k = 1;
        int i1 = 0;
        int j1 = 0;
        for (int k1 = 0; k1 < 10; k1++) {
            aLongArray7[k1] = System.currentTimeMillis();
        }
        long l = System.currentTimeMillis();
        while (anInt4 >= 0) {
            if (anInt4 > 0) {
                anInt4--;
                if (anInt4 == 0) {
                    method3(4747);
                    return;
                }
            }
            int i2 = j;
            int j2 = k;
            j = 300;
            k = 1;
            long l1 = System.currentTimeMillis();
            if (aLongArray7[i] == 0L) {
                j = i2;
                k = j2;
            } else {
                if (l1 > aLongArray7[i]) {
                    j = (int) ((long) (2560 * delayTime) / (l1 - aLongArray7[i]));
                }
            }
            if (j < 25) {
                j = 25;
            }
            if (j > 256) {
                j = 256;
                k = (int) ((long) delayTime - (l1 - aLongArray7[i]) / 10L);
            }
            if (k > delayTime) {
                k = delayTime;
            }
            aLongArray7[i] = l1;
            i = (i + 1) % 10;
            if (k > 1) {
                for (int k2 = 0; k2 < 10; k2++) {
                    if (aLongArray7[k2] != 0L) {
                        aLongArray7[k2] += k;
                    }
                }
            }
            if (k < minDelay) {
                k = minDelay;
            }
            try {
                Thread.sleep(k);
            } catch (InterruptedException _ex) {
                j1++;
            }
            for (; i1 < 256; i1 += j) {
                anInt26 = anInt22;
                saveClickX = anInt23;
                saveClickY = anInt24;
                aLong29 = aLong25;
                anInt22 = 0;
                processGameLoop();
                anInt32 = anInt33;
            }
            i1 &= 0xff;
            if (delayTime > 0) {
                anInt8 = (1000 * j) / (delayTime * 256);
            }
            processDrawing();
            if (shouldDebug) { //showDebug
                System.out.println("ntime:" + l1);
                for (int l2 = 0; l2 < 10; l2++) {
                    int i3 = ((i - l2 - 1) + 20) % 10;
                    System.out.println("otim" + i3 + ":" + aLongArray7[i3]);
                }
                System.out.println("fps:" + anInt8 + " ratio:" + j + " count:" + i1);
                System.out.println("del:" + k + " deltime:" + delayTime + " mindel:" + minDelay);
                System.out.println("intex:" + j1 + " opos:" + i);
                shouldDebug = false;
                j1 = 0;
            }
        }
        if (anInt4 == -1) {
            method3(4747);
        }
    }

    public final void method3(int i) { //exit
        anInt4 = -2;
        method8(493);
        if (i != 4747) {
            return;
        }
        if (aFrame_Sub1_15 != null) {
            try {
                Thread.sleep(1000L);
            } catch (Exception _ex) {
            }
            try {
                System.exit(0);
                return;
            } catch (Throwable _ex) {
            }
        }
    }

    public final void method4(boolean flag, int i) { //method4
        if (flag) {
            return;
        } else {
            delayTime = 1000 / i;
            return;
        }
    }

    public final void start() {
        if (anInt4 >= 0) {
            anInt4 = 0;
        }
    }

    public final void stop() {
        if (anInt4 >= 0) {
            anInt4 = 4000 / delayTime;
        }
    }

    public final void destroy() {
        anInt4 = -1;
        try {
            Thread.sleep(5000L);
        } catch (Exception _ex) {
        }
        if (anInt4 == -1) {
            method3(4747);
        }
    }

    public final void update(Graphics g) {
        if (aGraphics12 == null) {
            aGraphics12 = g;
        }
        shouldClearScreen = true;
        raiseWelcomeScreen();
    }

    public final void paint(Graphics g) {
        if (aGraphics12 == null) {
            aGraphics12 = g;
        }
        shouldClearScreen = true;
        raiseWelcomeScreen();
    }

    public final void mousePressed(MouseEvent mouseevent) {
        int i = mouseevent.getX();
        int j = mouseevent.getY();
        if (aFrame_Sub1_15 != null) {
            i -= 4;
            j -= 22;
        }
        idleTime = 0;
        anInt23 = i;
        anInt24 = j;
        aLong25 = System.currentTimeMillis();
        if (mouseevent.isMetaDown()) {
            anInt22 = 2;
            clickMode2 = 2;
            return;
        } else {
            anInt22 = 1;
            clickMode2 = 1;
            return;
        }
    }

    public final void mouseReleased(MouseEvent mouseevent) {
        idleTime = 0;
        clickMode2 = 0;
    }

    public final void mouseClicked(MouseEvent mouseevent) {
        int x = mouseevent.getX();
        int y = mouseevent.getY();
    }

    public final void mouseEntered(MouseEvent mouseevent) {
        boolean exists = (new File(sign.signlink.findcachedir() + Csettings.CustomCursorPath)).exists();
        if (Csettings.CustomCursor && exists) {
            //Set cursor for the frame component
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Image image = toolkit.getImage(sign.signlink.findcachedir() + Csettings.CustomCursorPath);
            Point point = new Point(0, 0);
            Cursor c = toolkit.createCustomCursor(image, point, "img");
            aFrame_Sub1_15.setCursor(c);
        }
    }

    public final void mouseExited(MouseEvent mouseevent) {
        idleTime = 0;
        anInt20 = -1;
        anInt21 = -1;
    }

    public final void mouseDragged(MouseEvent mouseevent) {
        int i = mouseevent.getX();
        int j = mouseevent.getY();
        if (aFrame_Sub1_15 != null) {
            i -= 4;
            j -= 22;
        }
        idleTime = 0;
        anInt20 = i;
        anInt21 = j;
    }

    public final void mouseMoved(MouseEvent mouseevent) {
        int i = mouseevent.getX();
        int j = mouseevent.getY();
        if (aFrame_Sub1_15 != null) {
            i -= 4;
            j -= 22;
        }
        idleTime = 0;
        anInt20 = i;
        anInt21 = j;
    }

    public final void keyPressed(KeyEvent keyevent) { //whenever we press a key :p
        idleTime = 0; //idleTime
        int i = keyevent.getKeyCode(); //get the code
        int j = keyevent.getKeyChar(); //get the char

        //start detection algorithms xD


        /*
        if(i == 38) { //up
        client.inputString = "fail";
        client.inputTaken = true;
        }
        
        if(i == 40) { //down
        client.inputString = "fail";
        client.inputTaken = true;
        }
         */


        if (i == 86) { //V
            pressingV = true;
        }
        if (i == 17) { //CTRL
            pressingCtrl = true;
        }

        if (i == KeyEvent.VK_F1) {
            client.setTab(0);
        }
        if (i == KeyEvent.VK_F2) {
            client.setTab(1);
        }
        if (i == KeyEvent.VK_F3) {
            client.setTab(2);
        }
        if (i == KeyEvent.VK_F4) {
            client.setTab(3);
        }
        if (i == KeyEvent.VK_F5) {
            client.setTab(4);
        }
        if (i == KeyEvent.VK_F6) {
            client.setTab(5);
        }
        if (i == KeyEvent.VK_F7) {
            client.setTab(6);
        }
        if (i == KeyEvent.VK_F8) {
            client.setTab(8);
        }
        if (i == KeyEvent.VK_F9) {
            client.setTab(9);
        }
        if (i == KeyEvent.VK_ESCAPE) {
            client.setTab(10); //logout tab
        }
        if (i == KeyEvent.VK_F10) {
            client.setTab(11);
        }
        if (i == KeyEvent.VK_F11) {
            client.setTab(12);
        }
        if (i == KeyEvent.VK_F12) {
            client.setTab(13);
        }

        //WTF do these DO!!!
        if (j < 30) {
            j = 0;
        }


        if (i == 37) //left arrow
        {
            j = 1;
        }
        if (i == 39) //right arrow
        {
            j = 2;
        }
        if (i == 38) //up arrow
        {
            j = 3;
        }
        if (i == 40) //down arrow
        {
            j = 4;
        }


        if (i == 17) {
            j = 5;
        }
        if (i == 8) {
            j = 8;
        }
        if (i == 127) {
            j = 8;
        }
        if (i == 9) {
            j = 9;
        }
        if (i == 10) {
            j = 10;
        }
        if (i >= 112 && i <= 123) {
            j = (1008 + i) - 112;
        }
        if (i == 36) {
            j = 1000;
        }
        if (i == 35) {
            j = 1001;
        }
        if (i == 33) {
            j = 1002;
        }
        if (i == 34) {
            j = 1003;
        }

        if (j > 0 && j < 128) {
            keyArray[j] = 1;
        }
        if (j > 4) { //not arrow keys
            charQueue[anInt33] = j; //writeIndex for the key code
            anInt33 = anInt33 + 1 & 0x7f; //writeIndex
        }
    }

    public final void keyReleased(KeyEvent keyevent) {
        idleTime = 0; //idleTime
        int i = keyevent.getKeyCode(); //get the keycode
        char c = keyevent.getKeyChar(); //get the keycharacter

        if (pressingV && pressingCtrl && client.loggedIn && client.anInt863 >= 1) { //when we release any keys :D & being a mod
            client.inputString += getClipboardContents();
            client.inputTaken = true;
        }
        if (i == 86) {
            pressingV = false;
        }
        if (i == 17) {
            pressingCtrl = false;
        }

        if (c < '\036') {
            c = '\0';
        }
        if (i == 37) {
            c = '\001';
        }
        if (i == 39) {
            c = '\002';
        }
        if (i == 38) {
            c = '\003';
        }
        if (i == 40) {
            c = '\004';
        }
        if (i == 17) {
            c = '\005';
        }
        if (i == 8) {
            c = '\b';
        }
        if (i == 127) {
            c = '\b';
        }
        if (i == 9) {
            c = '\t';
        }
        if (i == 10) {
            c = '\n';
        }
        if (c > 0 && c < '\200') {
            keyArray[c] = 0;
        }
    }

    public final void keyTyped(KeyEvent keyevent) {
    }

    public final int readChar() {
        int k = -1;
        if (anInt33 != anInt32) {
            k = charQueue[anInt32];
            anInt32 = anInt32 + 1 & 0x7f;
        }
        return k;
    }

    public final void focusGained(FocusEvent focusevent) {
        awtFocus = true;
        shouldClearScreen = true;
        raiseWelcomeScreen();
    }

    public final void focusLost(FocusEvent focusevent) {
        awtFocus = false;
        for (int i = 0; i < 128; i++) {
            keyArray[i] = 0;
        }
    }

    public final void windowActivated(WindowEvent windowevent) {
    }

    public final void windowClosed(WindowEvent windowevent) {
    }

    public final void windowClosing(WindowEvent windowevent) {
        Csettings.exitWarningDialog();
    }

    public final void windowDeactivated(WindowEvent windowevent) {
    }

    public final void windowDeiconified(WindowEvent windowevent) {
    }

    public final void windowIconified(WindowEvent windowevent) {
    }

    public final void windowOpened(WindowEvent windowevent) {
    }

    public void startUp() {
    }

    public void processGameLoop() {
    }

    public void method8(int i) { //cleanUpForQuit
        //i = 91 / i;
    }

    public void processDrawing() {
    }

    public void raiseWelcomeScreen() {
    }

    public Component getGameComponent() {
        if (aFrame_Sub1_15 != null) {
            return aFrame_Sub1_15;
        } else {
            return this;
        }
    }

    public void startRunnable(Runnable runnable, int priority) {
        Thread thread = new Thread(runnable);
        thread.start();
        thread.setPriority(priority);
    }

    public void drawLoadingText(int i, String s) {
    }

    public Applet_Sub1() {
        delayTime = 20;
        minDelay = 1;
        aLongArray7 = new long[10];
        shouldDebug = false;
        shouldClearScreen = true;
        awtFocus = true;
        keyArray = new int[128];
        charQueue = new int[128];
    }
    //my vars
    public boolean pressingV;
    public boolean pressingCtrl;
    public long oldClick;
    //class vars
    private int anInt4;
    private int delayTime;
    public int minDelay;
    private long aLongArray7[];
    public int anInt8;
    public boolean shouldDebug;
    public int myWidth;
    public int myHeight;
    public Graphics aGraphics12;
    public RSImageProducer aClass15_13;
    public static Frame_Sub1 aFrame_Sub1_15;
    public boolean shouldClearScreen;
    public boolean awtFocus;
    public int idleTime;
    public int clickMode2;
    public int anInt20;
    public int anInt21;
    public int anInt22;
    public int anInt23;
    public int anInt24;
    public long aLong25;
    public int anInt26;
    public int saveClickX;
    public int saveClickY;
    public long aLong29;
    public int keyArray[];
    private int charQueue[];
    private int anInt32;
    private int anInt33;
    public static int anInt34;
}
