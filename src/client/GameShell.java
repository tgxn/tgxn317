package client;

import client.custom.cSettings;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.Toolkit;
import java.io.*;

public class GameShell extends Applet implements Runnable, MouseListener, MouseMotionListener, KeyListener, FocusListener, WindowListener, MouseWheelListener {
    
    //my vars
    public boolean pressingV;
    public boolean pressingCtrl;
    
    //class vars
    private int gameState;
    private int delayTime;
    public int minDelay;
    private long[] otim;
    public int fps;
    public boolean gameShellDumpRequested;
    public int myWidth;
    public int myHeight;
    public Graphics graphics;
    public GraphicsBuffer fullGameScreen;
    public static RSFrame gameFrame;
    public boolean shouldClearScreen;
    public boolean awtFocus;
    public int idleTime;
    public int mouseButtonDown;
    public int mouseEventX;
    public int mouseEventY;
    public int eventMouseButtonPressed;
    public int eventClickX;
    public int eventClickY;
    public long eventTime;
    public int mouseButtonPressed;
    public int clickX;
    public int clickY;
    public long clickTime;
    public int[] keyStatus;
    private int[] inputBuffer;
    private int readIndex;
    private int writeIndex;
    
    public GameShell() {
        delayTime = 20;
        minDelay = 1;
        otim = new long[10];
        gameShellDumpRequested = false;
        shouldClearScreen = true;
        awtFocus = true;
        keyStatus = new int[128];
        inputBuffer = new int[128];
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
        if (mouseEventX > 0 && mouseEventY > 346 && mouseEventX < 516 && mouseEventY < 505) {
            if (notches < 0) {
                if (Client.chatBoxScrollbar.scrollPosition < 1) {
                    //client.scrollableChatArea = client.scrollableChatArea;
                    Client.needChatAreaRedraw = true;
                    Client.inputTaken = true;
                } else {
                    Client.scrollableChatArea += 30;
                    Client.needChatAreaRedraw = true;
                    Client.inputTaken = true;
                }
            } else {
                if (Client.scrollableChatArea < 1) {
                    Client.scrollableChatArea = 0;
                    Client.needChatAreaRedraw = true;
                    Client.inputTaken = true;
                } else {
                    Client.scrollableChatArea -= 30;
                    Client.needChatAreaRedraw = true;
                    Client.inputTaken = true;
                }
            }
        }
        if (mouseEventY > 210 && mouseEventY < 473 && mouseEventX > 514 && mouseEventX < 762) { //Invertory
            if (Client.tabInterfaceIDs[Client.tabID] == 638) //quest tab
            {
                RSInterface.interfaceCache[639].scrollPosition += notches * 30; //scrollPosition
            }
        }

        if (mouseEventX > 0 && mouseEventX < 512 && mouseEventY > 0 && mouseEventY < 334) { //Ingame
            if (Client.openInterfaceID == 5292) {
                RSInterface.interfaceCache[5385].scrollPosition += notches * 30;
            }
        }
    }

    public final void createClientFrame(int height, int width) {
        myWidth = width;
        myHeight = height;
        gameFrame = new RSFrame(this, myWidth, myHeight);
        graphics = getGameComponent().getGraphics();
        fullGameScreen = new GraphicsBuffer(myWidth, myHeight, getGameComponent());
        startRunnable(this, 1);
        return;
    }

    public final void initClientFrame(int height, int width) {
        myWidth = width;
        myHeight = height;
        graphics = getGameComponent().getGraphics();
        fullGameScreen = new GraphicsBuffer(myWidth, myHeight, getGameComponent());
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
        drawLoadingText(0, "Initalizing...");
        
        startUp();
        int opos = 0;
        int ratio = 256;
        int del = 1;
        int count = 0;
        int intex = 0;
        
        for (int otimPtr = 0; otimPtr < 10; otimPtr++) {
            otim[otimPtr] = System.currentTimeMillis();
        }
        while (gameState >= 0) {
            if (gameState > 0) {
                gameState--;
                if (gameState == 0) {
                    exit();
                    return;
                }
            }
            int i2 = ratio;
            int j2 = del;
            ratio = 300;
            del = 1;
            long ntime = System.currentTimeMillis();
            if (otim[opos] == 0L) {
                ratio = i2;
                del = j2;
            } else {
                if (ntime > otim[opos]) {
                    ratio = (int) ((long) (2560 * delayTime) / (ntime - otim[opos]));
                }
            }
            if (ratio < 25) {
                ratio = 25;
            }
            if (ratio > 256) {
                ratio = 256;
                del = (int) ((long) delayTime - (ntime - otim[opos]) / 10L);
            }
            if (del > delayTime) {
                del = delayTime;
            }
            otim[opos] = ntime;
            opos = (opos + 1) % 10;
            if (del > 1) {
                for (int k2 = 0; k2 < 10; k2++) {
                    if (otim[k2] != 0L) {
                        otim[k2] += del;
                    }
                }
            }
            if (del < minDelay) {
                del = minDelay;
            }
            try {
                Thread.sleep(del);
            } catch (InterruptedException ie) {
                intex++;
            }
            for (; count < 256; count += ratio) {
                mouseButtonPressed = eventMouseButtonPressed;
                clickX = eventClickX;
                clickY = eventClickY;
                clickTime = eventTime;
                eventMouseButtonPressed = 0;
                processGameLoop();
                readIndex = writeIndex;
            }
            count &= 0xff;
            if (delayTime > 0) {
                fps = (1000 * ratio) / (delayTime * 256);
            }
            drawGame();
            if (gameShellDumpRequested) {
                System.out.println("ntime:" + ntime);
                for (int l2 = 0; l2 < 10; l2++) {
                    int otimPtr = ((opos - l2 - 1) + 20) % 10;
                    System.out.println("otim" + otimPtr + ":" + otim[otimPtr]);
                }
                System.out.println("fps:" + fps + " ratio:" + ratio + " count:" + count);
                System.out.println("del:" + del + " deltime:" + delayTime + " mindel:" + minDelay);
                System.out.println("intex:" + intex + " opos:" + opos);
                gameShellDumpRequested = false;
                intex = 0;
            }
        }
        if (gameState == -1) {
            exit();
        }
    }

    public final void exit() {
        gameState = -2;
        shutdown();
        if (gameFrame != null) {
            try {
                Thread.sleep(1000L);
            } catch (Exception ignored) {
            }
            try {
                System.exit(0);
                return;
            } catch (Throwable ignored) {
            }
        }
    }

    public final void setTargetFramerate(int i) {
            delayTime = 1000 / i;
    }

    @Override
    public final void start() {
        if (gameState >= 0) {
            gameState = 0;
        }
    }

    @Override
    public final void stop() {
        if (gameState >= 0) {
            gameState = 4000 / delayTime;
        }
    }

    @Override
    public final void destroy() {
        gameState = -1;
        try {
            Thread.sleep(5000L);
        } catch (Exception ignored) {
        }
        if (gameState == -1) {
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
        int x = mouseevent.getX();
        int y = mouseevent.getY();
        if (gameFrame != null) {
            x -= 4;
            y -= 22;
        }
        idleTime = 0;
        eventClickX = x;
        eventClickY = y;
        eventTime = System.currentTimeMillis();
        if (mouseevent.isMetaDown()) {
            eventMouseButtonPressed = 2;
            mouseButtonDown = 2;
            return;
        } else {
            eventMouseButtonPressed = 1;
            mouseButtonDown = 1;
            return;
        }
    }

    @Override
    public final void mouseReleased(MouseEvent mouseevent) {
        idleTime = 0;
        mouseButtonDown = 0;
    }

    @Override
    public final void mouseClicked(MouseEvent mouseevent) {
    }

    @Override
    public final void mouseEntered(MouseEvent mouseevent) {
        if(cSettings.customCursor) {
            boolean exists = (new File(client.sign.Signlink.findCacheDIR() + cSettings.CustomCursorPath)).exists();
            if (cSettings.CustomCursor && exists) {
                //Set cursor for the frame component
                Toolkit toolkit = Toolkit.getDefaultToolkit();
                Image image = toolkit.getImage(client.sign.Signlink.findCacheDIR() + cSettings.CustomCursorPath);
                Point point = new Point(0, 0);
                Cursor c = toolkit.createCustomCursor(image, point, "img");
                gameFrame.setCursor(c);
            }
        }
    }

    @Override
    public final void mouseExited(MouseEvent mouseevent) {
        idleTime = 0;
        mouseEventX = -1;
        mouseEventY = -1;
    }

    @Override
    public final void mouseDragged(MouseEvent mouseevent) {
        int x = mouseevent.getX();
        int y = mouseevent.getY();
        if (gameFrame != null) {
            x -= 4;
            y -= 22;
        }
        idleTime = 0;
        mouseEventX = x;
        mouseEventY = y;
    }

    @Override
    public final void mouseMoved(MouseEvent mouseevent) {
        int x = mouseevent.getX();
        int y = mouseevent.getY();
        if (gameFrame != null) {
            x -= 4;
            y -= 22;
        }
        idleTime = 0;
        mouseEventX = x;
        mouseEventY = y;
    }

    @Override
    public final void keyPressed(KeyEvent keyevent) {
        idleTime = 0;
        int keyCode = keyevent.getKeyCode();
        int keyChar = keyevent.getKeyChar();
        
        // Custom keys.
        doKeyCodeDownAction(keyCode);

        if (keyChar < 30) {
            keyChar = 0;
        }
        if (keyCode == 37) {
            keyChar = 1;
        }
        if (keyCode == 39) {
            keyChar = 2;
        }
        if (keyCode == 38) {
            keyChar = 3;
        }
        if (keyCode == 40) {
            keyChar = 4;
        }
        if (keyCode == 17) {
            keyChar = 5;
        }
        if (keyCode == 8) {
            keyChar = 8;
        }
        if (keyCode == 127) {
            keyChar = 8;
        }
        if (keyCode == 9) {
            keyChar = 9;
        }
        if (keyCode == 10) {
            keyChar = 10;
        }
        if (keyCode >= 112 && keyCode <= 123) {
            keyChar = (1008 + keyCode) - 112;
        }
        if (keyCode == 36) {
            keyChar = 1000;
        }
        if (keyCode == 35) {
            keyChar = 1001;
        }
        if (keyCode == 33) {
            keyChar = 1002;
        }
        if (keyCode == 34) {
            keyChar = 1003;
        }
        if (keyChar > 0 && keyChar < 128) {
            keyStatus[keyChar] = 1;
        }
        if (keyChar > 4) {
            inputBuffer[writeIndex] = keyChar;
            writeIndex = writeIndex + 1 & 0x7f;
        }
    }
    
    private void doKeyCodeDownAction(int keyCode) {
        if (keyCode == 17) {
            pressingCtrl = true;
        }
        if (keyCode == 86) {
            pressingV = true;
        }
        if (keyCode == KeyEvent.VK_F1) {
            Client.setTab(0);
        }
        if (keyCode == KeyEvent.VK_F2) {
            Client.setTab(1);
        }
        if (keyCode == KeyEvent.VK_F3) {
            Client.setTab(2);
        }
        if (keyCode == KeyEvent.VK_F4) {
            Client.setTab(3);
        }
        if (keyCode == KeyEvent.VK_F5) {
            Client.setTab(4);
        }
        if (keyCode == KeyEvent.VK_F6) {
            Client.setTab(5);
        }
        if (keyCode == KeyEvent.VK_F7) {
            Client.setTab(6);
        }
        if (keyCode == KeyEvent.VK_F8) {
            Client.setTab(8);
        }
        if (keyCode == KeyEvent.VK_F9) {
            Client.setTab(9);
        }
        if (keyCode == KeyEvent.VK_ESCAPE) {
            Client.setTab(10);
        }
        if (keyCode == KeyEvent.VK_F10) {
            Client.setTab(11);
        }
        if (keyCode == KeyEvent.VK_F11) {
            Client.setTab(12);
        }
        if (keyCode == KeyEvent.VK_F12) {
            Client.setTab(13);
        }
    }
    
    @Override
    public final void keyReleased(KeyEvent keyevent) {
        idleTime = 0;
        int keyCode = keyevent.getKeyCode();
        char keyChar = keyevent.getKeyChar();
        doKeyCodeUpAction(keyCode);
        if (keyChar < '\036') {
            keyChar = '\0';
        }
        if (keyCode == 37) {
            keyChar = '\001';
        }
        if (keyCode == 39) {
            keyChar = '\002';
        }
        if (keyCode == 38) {
            keyChar = '\003';
        }
        if (keyCode == 40) {
            keyChar = '\004';
        }
        if (keyCode == 17) {
            keyChar = '\005';
        }
        if (keyCode == 8) {
            keyChar = '\b';
        }
        if (keyCode == 127) {
            keyChar = '\b';
        }
        if (keyCode == 9) {
            keyChar = '\t';
        }
        if (keyCode == 10) {
            keyChar = '\n';
        }
        if (keyChar > 0 && keyChar < '\200') {
            keyStatus[keyChar] = 0;
        }
    }
    
    private void doKeyCodeUpAction(int keyCode) {
        if (pressingV && pressingCtrl && Client.loggedIn && Client.playerRights >= 1) { //when we release any keys :D & being a mod
            Client.inputString += getClipboardContents();
            Client.inputTaken = true;
        }
        if (keyCode == 86) {
            pressingV = false;
        }
        if (keyCode == 17) {
            pressingCtrl = false;
        }
    }

    @Override
    public final void keyTyped(KeyEvent keyevent) {
    }

    public final int readChar() {
        int k = -1;
        if (writeIndex != readIndex) {
            k = inputBuffer[readIndex];
            readIndex = readIndex + 1 & 0x7f;
        }
        return k;
    }

    @Override
    public final void focusGained(FocusEvent focusevent) {
        awtFocus = true;
        shouldClearScreen = true;
        raiseWelcomeScreen();
    }

    @Override
    public final void focusLost(FocusEvent focusevent) {
        awtFocus = false;
        for (int i = 0; i < 128; i++) {
            keyStatus[i] = 0;
        }
    }

    @Override
    public final void windowActivated(WindowEvent windowevent) {
    }

    @Override
    public final void windowClosed(WindowEvent windowevent) {
    }

    @Override
    public final void windowClosing(WindowEvent windowevent) {
        cSettings.showExitWarningDialog();
    }

    @Override
    public final void windowDeactivated(WindowEvent windowevent) {
    }

    @Override
    public final void windowDeiconified(WindowEvent windowevent) {
    }

    @Override
    public final void windowIconified(WindowEvent windowevent) {
    }

    @Override
    public final void windowOpened(WindowEvent windowevent) {
    }

    public void startUp() {
    }

    public void processGameLoop() {
    }

    public void shutdown() {
    }

    public void drawGame() {
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

  protected void drawLoadingText(int i, String s) {
        while (graphics == null) {
            graphics = getGameComponent().getGraphics();
            try {
                getGameComponent().repaint();
            } catch (Exception ignored) {
            }
            try {
                Thread.sleep(1000L);
            } catch (Exception ignored) {
            }
        }
        Font font = new Font("Helvetica", 1, 13);
        FontMetrics fontmetrics = getGameComponent().getFontMetrics(font);
        Font font1 = new Font("Helvetica", 0, 13);
        getGameComponent().getFontMetrics(font1);
        if (shouldClearScreen) {
            graphics.setColor(Color.black);
            graphics.fillRect(0, 0, myWidth, myHeight);
            shouldClearScreen = false;
        }
        Color color = new Color(140, 17, 17);
        int j = myHeight / 2 - 18;
        graphics.setColor(color);
        graphics.drawRect(myWidth / 2 - 152, j, 304, 34);
        graphics.fillRect(myWidth / 2 - 150, j + 2, i * 3, 30);
        graphics.setColor(Color.black);
        graphics.fillRect((myWidth / 2 - 150) + i * 3, j + 2, 300 - i * 3, 30);
        graphics.setFont(font);
        graphics.setColor(Color.white);
        graphics.drawString(s, (myWidth - fontmetrics.stringWidth(s)) / 2, j + 22);
    }
}
