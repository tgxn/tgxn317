package map;

import map.drawing.RSImageProducer;
import map.drawing.MapFunctions;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class RSApplet extends Applet implements Runnable, MouseListener, MouseMotionListener, KeyListener, FocusListener, WindowListener {
    
    public int anInt1;
    public int delayTime;
    public int minDelay;
    public long aLongArray4[];
    public int framesPerSecond;
    public boolean shouldDebug;
    public int myWidth;
    public int myHeight;
    public Graphics graphics;
    public RSImageProducer fullScreen;
    public MapFunctions aClass1_Sub1_Sub1_Sub2Array11[];
    public RSFrame rsFrame;
    public boolean shouldClearScreen;
    public boolean awtFocus;
    public int idleTime;
    public int clickMode2;
    public int xDragged;
    public int yDragged;
    public int clickMode1;
    public int xPressed;
    public int yPressed;
    public long clickTime;
    public int anInt23;
    public int saveClickX;
    public int saveClickY;
    public long aLong26;
    public int keyArray[];
    public int charQueue[];
    public int readIndex;
    public int writeIndex;
    public static boolean aBoolean31;
    
    public RSApplet() {
        anInt1 = 0;
        delayTime = 20;
        minDelay = 1;
        aLongArray4 = new long[10]; //otim?, dont know
        framesPerSecond = 0;
        shouldDebug = false;
        //aClass1_Sub1_Sub1_Sub2Array11 = new MapFunctions[6]; //wtf
        shouldClearScreen = true;
        awtFocus = true;
        idleTime = 0;
        clickMode2 = 0;
        xDragged = 0;
        yDragged = 0;
        clickMode1 = 0;
        xPressed = 0;
        yPressed = 0;
        clickTime = 0L;
        anInt23 = 0; //clickMode3??? is used in click events, saveclickmode1
        saveClickX = 0;
        saveClickY = 0;
        aLong26 = 0L; //clicktime , saveclicktime??
        keyArray = new int[128];
        charQueue = new int[128];
        readIndex = 0;
        writeIndex = 0;
    }
        


    public void drawLoadingText(int i, String s) {
        while (graphics == null) {
            graphics = getGameComponent().getGraphics();
            try {
                getGameComponent().repaint();
            } catch (Exception exception) {
            }
            try {
                Thread.sleep(1000L);
            } catch (Exception exception1) {
            }
        }
        Font font = new Font("Helvetica", 0, 13);
        FontMetrics fontmetrics = getGameComponent().getFontMetrics(font);
        if (shouldClearScreen) {
            graphics.setColor(Color.black);
            graphics.fillRect(0, 0, myWidth, myHeight);
            shouldClearScreen = false;
        }
        int x = myWidth / 2 - 150;
        int y = myHeight / 2 - 18;
        graphics.setColor(Color.blue);
        graphics.drawRect(x - 2, y, 303, 33);
        graphics.fillRect(x, y + 2, i * 3, 30);
        graphics.setColor(Color.black);
        graphics.fillRect(x + i * 3, y + 2, 300 - i * 3, 30);
        graphics.setFont(font);
        graphics.setColor(Color.white);
        graphics.drawString(s, (myWidth - fontmetrics.stringWidth(s)) / 2, y + 22);
    }

    @Override
    public void mouseReleased(MouseEvent mouseevent) {
        idleTime = 0;
        clickMode2 = 0;
    }

    @Override
    public void keyPressed(KeyEvent keyevent) {
        idleTime = 0;
        int keyCode = keyevent.getKeyCode();
        int keyChar = keyevent.getKeyChar();
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
            keyArray[keyChar] = 1;
        }
        if (keyChar > 4) {
            charQueue[writeIndex] = keyChar;
            writeIndex = writeIndex + 1 & 0x7f;
        }
    }

    public void startRunnable(Runnable runnable, int priority) {
        Thread thread = new Thread(runnable);
        thread.start();
        thread.setPriority(priority);
    }

    @Override
    public void windowClosing(WindowEvent windowevent) {
    }

    public void exit() {
        anInt1 = -2;
        cleanUpForQuit();
        if (rsFrame != null) {
            try {
                Thread.sleep(1000L);
            } catch (Exception exception) {
            }
            try {
                System.exit(0);
            } catch (Throwable throwable) {
            }
        }
    }

    @Override
    public void update(Graphics g) {
        if (graphics == null) {
            graphics = g;
        }
        shouldClearScreen = true;
    }

    @Override
    public void mouseEntered(MouseEvent mouseevent) {
    }

    @Override
    public void mouseExited(MouseEvent mouseevent) {
        idleTime = 0;
        xDragged = -1;
        yDragged = -1;
    }

    @Override
    public void windowOpened(WindowEvent windowevent) {
    }

    @Override
    public void windowDeiconified(WindowEvent windowevent) {
    }

    @Override
    public void windowActivated(WindowEvent windowevent) {
    }

    public void startUp() {
    }

    @Override
    public void start() {
        if (anInt1 >= 0) {
            anInt1 = 0;
        }
    }

    public void createFrame(int width, int height) {
        myWidth = width;
        myHeight = height;
        rsFrame = new RSFrame(this, myWidth, myHeight);
        graphics = getGameComponent().getGraphics();
        fullScreen = new RSImageProducer(myWidth, myHeight, getGameComponent());
        startRunnable(this, 1);
    }

    public int method7() {
        int charID = -1;
        if (writeIndex != readIndex) {
            charID = charQueue[readIndex];
            readIndex = readIndex + 1 & 0x7f;
        }
        return charID;
    }

    public void processDrawing() {
    }

    public Component getGameComponent() {
        if (rsFrame != null) {
            return rsFrame;
        } else {
            return this;
        }
    }

    @Override
    public void mouseClicked(MouseEvent mouseevent) {
    }

    @Override
    public void mousePressed(MouseEvent mouseevent) {
        int x = mouseevent.getX();
        int y = mouseevent.getY();
        if (rsFrame != null) {
            x -= 4;
            y -= 22;
        }
        idleTime = 0;
        xPressed = x;
        yPressed = y;
        clickTime = System.currentTimeMillis();
        if (mouseevent.isMetaDown()) {
            clickMode1 = 2;
            clickMode2 = 2;
        } else {
            clickMode1 = 1;
            clickMode2 = 1;
        }
    }

    @Override
    public void mouseDragged(MouseEvent mouseevent) {
        int x = mouseevent.getX();
        int y = mouseevent.getY();
        if (rsFrame != null) {
            x -= 4;
            y -= 22;
        }
        idleTime = 0;
        xDragged = x;
        yDragged = y;
    }

    public void initializeFrame(int width, int height) {
        myWidth = width;
        myHeight = height;
        graphics = getGameComponent().getGraphics();
        fullScreen = new RSImageProducer(myWidth, myHeight, getGameComponent());
        startRunnable(this, 1);
    }

    @Override
    public void mouseMoved(MouseEvent mouseevent) {
        int i = mouseevent.getX();
        int j = mouseevent.getY();
        if (rsFrame != null) {
            i -= 4;
            j -= 22;
        }
        idleTime = 0;
        xDragged = i;
        yDragged = j;
    }

    @Override
    public void keyTyped(KeyEvent keyevent) {
    }

    @Override
    public void windowDeactivated(WindowEvent windowevent) {
    }

    @Override
    public void paint(Graphics g) {
        if (graphics == null) {
            graphics = g;
        }
        shouldClearScreen = true;
    }

    @Override
    public void destroy() {
        anInt1 = -1;
        try {
            Thread.sleep(5000L);
        } catch (Exception exception) {
        }
        if (anInt1 == -1) {
            exit();
        }
    }

    public void cleanUpForQuit() {
    }

    public void processLoop() {
    }

    @Override
    public void focusLost(FocusEvent focusevent) {
        awtFocus = false;
        for (int i = 0; i < 128; i++) {
            keyArray[i] = 0;
        }
    }

    @Override
    public void keyReleased(KeyEvent keyevent) {
        idleTime = 0;
        int i = keyevent.getKeyCode();
        char c = keyevent.getKeyChar();
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

    @Override
    public void windowClosed(WindowEvent windowevent) {
    }

    @Override
    public void run() {
        getGameComponent().addMouseListener(this);
        getGameComponent().addMouseMotionListener(this);
        getGameComponent().addKeyListener(this);
        getGameComponent().addFocusListener(this);
        if (rsFrame != null) {
            rsFrame.addWindowListener(this);
        }
        drawLoadingText(0, "Loading");
        startUp();
        int i = 0;
        int j = 256;
        int k = 1;
        int i1 = 0;
        int j1 = 0;
        for (int k1 = 0; k1 < 10; k1++) {
            aLongArray4[k1] = System.currentTimeMillis();
        }
        while (anInt1 >= 0) {
            if (anInt1 > 0) {
                anInt1--;
                if (anInt1 == 0) {
                    exit();
                    return;
                }
            }
            int i2 = j;
            int j2 = k;
            j = 300;
            k = 1;
            long l1 = System.currentTimeMillis();
            if (aLongArray4[i] == 0L) {
                j = i2;
                k = j2;
            } else if (l1 > aLongArray4[i]) {
                j = (int) ((long) (2560 * delayTime) / (l1 - aLongArray4[i]));
            }
            if (j < 25) {
                j = 25;
            }
            if (j > 256) {
                j = 256;
                k = (int) ((long) delayTime - (l1 - aLongArray4[i]) / 10L);
            }
            if (k > delayTime) {
                k = delayTime;
            }
            aLongArray4[i] = l1;
            i = (i + 1) % 10;
            if (k > 1) {
                for (int k2 = 0; k2 < 10; k2++) {
                    if (aLongArray4[k2] != 0L) {
                        aLongArray4[k2] += k;
                    }
                }
            }
            if (k < minDelay) {
                k = minDelay;
            }
            try {
                Thread.sleep(k);
            } catch (InterruptedException interruptedexception) {
                j1++;
            }
            for (; i1 < 256; i1 += j) {
                anInt23 = clickMode1;
                saveClickX = xPressed;
                saveClickY = yPressed;
                aLong26 = clickTime;
                clickMode1 = 0;
                processLoop();
                readIndex = writeIndex;
            }

            i1 &= 0xff;
            if (delayTime > 0) {
                framesPerSecond = (1000 * j) / (delayTime * 256);
            }
            processDrawing();
            if (shouldDebug) {
                System.out.println("ntime:" + l1);
                for (int l2 = 0; l2 < 10; l2++) {
                    int i3 = ((i - l2 - 1) + 20) % 10;
                    System.out.println("otim" + i3 + ":" + aLongArray4[i3]);
                }
                System.out.println("fps:" + framesPerSecond + " ratio:" + j + " count:" + i1);
                System.out.println("del:" + k + " deltime:" + delayTime + " mindel:" + minDelay);
                System.out.println("intex:" + j1 + " opos:" + i);
                shouldDebug = false;
                j1 = 0;
            }
        }
        if (anInt1 == -1) {
            exit();
        }
    }

    @Override
    public void focusGained(FocusEvent focusevent) {
        awtFocus = true;
        shouldClearScreen = true;
    }

    @Override
    public void windowIconified(WindowEvent windowevent) {
    }
}
