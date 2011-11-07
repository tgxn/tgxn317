package client;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.Toolkit;
import java.io.*;

public class RSApplet extends Applet implements Runnable, MouseListener, MouseMotionListener, KeyListener, FocusListener, WindowListener, MouseWheelListener {
    
    //my vars
    public boolean pressingV;
    public boolean pressingCtrl;
    public long oldClick;
    
    //class vars
    private int anInt4;
    private int delayTime;
    public int minDelay;
    private long aLongArray7[];
    public int fps;
    public boolean shouldDebug;
    public int myWidth;
    public int myHeight;
    public Graphics graphics;
    public RSImageProducer fullGameScreen;
    public static RSFrame gameFrame;
    public boolean shouldClearScreen;
    public boolean awtFocus;
    public int idleTime;
    public int clickMode2;
    public int mouseX;
    public int mouseY;
    public int clickMode1;
    public int clickX;
    public int clickY;
    public long clickTime;
    public int clickMode3;
    public int saveClickX;
    public int saveClickY;
    public long aLong29;
    public int keyArray[];
    private int charQueue[];
    private int readIndex;
    private int writeIndex;
    public static int anInt34;
    
    public RSApplet() {
        delayTime = 20;
        minDelay = 1;
        aLongArray7 = new long[10];
        shouldDebug = false;
        shouldClearScreen = true;
        awtFocus = true;
        keyArray = new int[128];
        charQueue = new int[128];
    }
    
    public String getClipboardContents() {
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
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }
        return result;
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        int notches = e.getWheelRotation();
        if (mouseX > 0 && mouseY > 346 && mouseX < 516 && mouseY < 505) {
            if (notches < 0) {
                if (client.chatBoxScrollbar.scrollPosition < 1) {
                    //client.scrollableChatArea = client.scrollableChatArea;
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
        if (mouseY > 210 && mouseY < 473 && mouseX > 514 && mouseX < 762) { //Invertory
            if (client.tabInterfaceIDs[client.tabID] == 638) //quest tab
            {
                RSInterface.interfaceCache[639].scrollPosition += notches * 30; //scrollPosition
            }
        }

        if (mouseX > 0 && mouseX < 512 && mouseY > 0 && mouseY < 334) { //Ingame
            if (client.openInterfaceID == 5292) {
                RSInterface.interfaceCache[5385].scrollPosition += notches * 30;
            }
        }
    }

    public final void createClientFrame(int myHeight, int myWidth) {
        gameFrame = new RSFrame(this, myWidth, myHeight);
        graphics = getGameComponent().getGraphics();
        fullGameScreen = new RSImageProducer(myWidth, myHeight, getGameComponent());
        startRunnable(this, 1);
        return;
    }

    public final void initClientFrame(int myHeight, int myWidth) {
        graphics = getGameComponent().getGraphics();
        fullGameScreen = new RSImageProducer(myWidth, myHeight, getGameComponent());
        startRunnable(this, 1);
    }

    @Override
    public void run() {
        getGameComponent().addMouseWheelListener(this);
        getGameComponent().addMouseListener(this);
        getGameComponent().addMouseMotionListener(this);
        getGameComponent().addKeyListener(this);
        getGameComponent().addFocusListener(this);
        if (gameFrame != null) {
            gameFrame.addWindowListener(this);
        }
        oldClick = System.currentTimeMillis();
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
                    exit();
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
                clickMode3 = clickMode1;
                saveClickX = clickX;
                saveClickY = clickY;
                aLong29 = clickTime;
                clickMode1 = 0;
                processGameLoop();
                readIndex = writeIndex;
            }
            i1 &= 0xff;
            if (delayTime > 0) {
                fps = (1000 * j) / (delayTime * 256);
            }
            processDrawing();
            
            if (shouldDebug) {
                System.out.println("ntime:" + l1);
                for (int l2 = 0; l2 < 10; l2++) {
                    int i3 = ((i - l2 - 1) + 20) % 10;
                    System.out.println("otim" + i3 + ":" + aLongArray7[i3]);
                }
                System.out.println("fps:" + fps + " ratio:" + j + " count:" + i1);
                System.out.println("del:" + k + " deltime:" + delayTime + " mindel:" + minDelay);
                System.out.println("intex:" + j1 + " opos:" + i);
                shouldDebug = false;
                j1 = 0;
            }
            
        }
        if (anInt4 == -1) {
            exit();
        }
    }

    public final void exit() {
        anInt4 = -2;
        cleanUpForQuit(493);
        if (gameFrame != null) {
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

    public final void method4(boolean flag, int i) {
        if (flag) {
            return;
        } else {
            delayTime = 1000 / i;
            return;
        }
    }

    @Override
    public final void start() {
        if (anInt4 >= 0) {
            anInt4 = 0;
        }
    }

    @Override
    public final void stop() {
        if (anInt4 >= 0) {
            anInt4 = 4000 / delayTime;
        }
    }

    @Override
    public final void destroy() {
        anInt4 = -1;
        try {
            Thread.sleep(5000L);
        } catch (Exception _ex) {
        }
        if (anInt4 == -1) {
            exit();
        }
    }

    @Override
    public final void update(Graphics g) {
        if (graphics == null) {
            graphics = g;
        }
        shouldClearScreen = true;
        raiseWelcomeScreen();
    }

    @Override
    public final void paint(Graphics g) {
        if (graphics == null) {
            graphics = g;
        }
        shouldClearScreen = true;
        raiseWelcomeScreen();
    }

    @Override
    public final void mousePressed(MouseEvent mouseevent) {
        int i = mouseevent.getX();
        int j = mouseevent.getY();
        if (gameFrame != null) {
            i -= 4;
            j -= 22;
        }
        idleTime = 0;
        clickX = i;
        clickY = j;
        clickTime = System.currentTimeMillis();
        if (mouseevent.isMetaDown()) {
            clickMode1 = 2;
            clickMode2 = 2;
            return;
        } else {
            clickMode1 = 1;
            clickMode2 = 1;
            return;
        }
    }

    @Override
    public final void mouseReleased(MouseEvent mouseevent) {
        idleTime = 0;
        clickMode2 = 0;
    }

    @Override
    public final void mouseClicked(MouseEvent mouseevent) {
        int x = mouseevent.getX();
        int y = mouseevent.getY();
    }

    @Override
    public final void mouseEntered(MouseEvent mouseevent) {
        boolean exists = (new File(sign.signlink.findCacheDIR() + Csettings.CustomCursorPath)).exists();
        if (Csettings.CustomCursor && exists) {
            //Set cursor for the frame component
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Image image = toolkit.getImage(sign.signlink.findCacheDIR() + Csettings.CustomCursorPath);
            Point point = new Point(0, 0);
            Cursor c = toolkit.createCustomCursor(image, point, "img");
            gameFrame.setCursor(c);
        }
    }

    @Override
    public final void mouseExited(MouseEvent mouseevent) {
        idleTime = 0;
        mouseX = -1;
        mouseY = -1;
    }

    @Override
    public final void mouseDragged(MouseEvent mouseevent) {
        int i = mouseevent.getX();
        int j = mouseevent.getY();
        if (gameFrame != null) {
            i -= 4;
            j -= 22;
        }
        idleTime = 0;
        mouseX = i;
        mouseY = j;
    }

    @Override
    public final void mouseMoved(MouseEvent mouseevent) {
        int i = mouseevent.getX();
        int j = mouseevent.getY();
        if (gameFrame != null) {
            i -= 4;
            j -= 22;
        }
        idleTime = 0;
        mouseX = i;
        mouseY = j;
    }

    @Override
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
            charQueue[writeIndex] = j; //writeIndex for the key code
            writeIndex = writeIndex + 1 & 0x7f; //writeIndex
        }
    }

    public final void keyReleased(KeyEvent keyevent) {
        idleTime = 0; //idleTime
        int i = keyevent.getKeyCode(); //get the keycode
        char c = keyevent.getKeyChar(); //get the keycharacter

        if (pressingV && pressingCtrl && client.loggedIn && client.playerRights >= 1) { //when we release any keys :D & being a mod
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
        if (writeIndex != readIndex) {
            k = charQueue[readIndex];
            readIndex = readIndex + 1 & 0x7f;
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

    public void cleanUpForQuit(int i) {
    }

    public void processDrawing() {
    }

    public void raiseWelcomeScreen() {
    }

    public Component getGameComponent() {
        if (gameFrame != null) {
            return gameFrame;
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
}
