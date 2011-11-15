package map.instance;

// Fully Renamed - gamerx - 14.11.11

import map.drawing.*;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class RSApplet extends Applet implements Runnable, MouseListener, MouseMotionListener, KeyListener, FocusListener, WindowListener {
    
    public int gameState;
    public int delayTime;
    public int minDelay;
    public long[] otim;
    public int framesPerSecond;
    public boolean shouldDebug;
    public int appWidth;
    public int appHeight;
    public Graphics graphics;
    public GraphicsBuffer fullScreen;
    public MapFunctions[] mapFunctions;
    public Frame rsFrame;
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
    public int[] inputBuffer;
    public int readIndex;
    public int writeIndex;
    
    public RSApplet() {
        gameState = 0;
        delayTime = 20;
        minDelay = 1;
        otim = new long[10];
        framesPerSecond = 0;
        shouldDebug = false;
        shouldClearScreen = true;
        awtFocus = true;
        idleTime = 0;
        mouseButtonDown = 0;
        mouseEventX = 0;
        mouseEventY = 0;
        eventMouseButtonPressed = 0;
        eventClickX = 0;
        eventClickY = 0;
        eventTime = 0L;
        mouseButtonPressed = 0;
        clickX = 0;
        clickY = 0;
        clickTime = 0L;
        keyStatus = new int[128];
        inputBuffer = new int[128];
        readIndex = 0;
        writeIndex = 0;
    }
    
    /*
     * Overridden Method's.
     */
    
    public void startUp() {
    }
    
    public void processLoop() {
    }
    
    public void processDrawing() {
    }
    
    public void cleanUpForQuit() {
    }
    
    /*
     * Runnable component of RSApplet
     */
    
    @Override
    public void run() {
        getGameComponent().addMouseListener(this);
        getGameComponent().addMouseMotionListener(this);
        getGameComponent().addKeyListener(this);
        getGameComponent().addFocusListener(this);
        if (rsFrame != null) {
            rsFrame.addWindowListener(this);
        }
        drawLoadingText("Starting up...", 0);
        startUp();
        int i = 0;
        int j = 256;
        int k = 1;
        int i1 = 0;
        int j1 = 0;
        for (int k1 = 0; k1 < 10; k1++) {
            otim[k1] = System.currentTimeMillis();
        }
        while (gameState >= 0) {
            if (gameState > 0) {
                gameState--;
                if (gameState == 0) {
                    exit();
                    return;
                }
            }
            int i2 = j;
            int j2 = k;
            j = 300;
            k = 1;
            long l1 = System.currentTimeMillis();
            if (otim[i] == 0L) {
                j = i2;
                k = j2;
            } else if (l1 > otim[i]) {
                j = (int) ((long) (2560 * delayTime) / (l1 - otim[i]));
            }
            if (j < 25) {
                j = 25;
            }
            if (j > 256) {
                j = 256;
                k = (int) ((long) delayTime - (l1 - otim[i]) / 10L);
            }
            if (k > delayTime) {
                k = delayTime;
            }
            otim[i] = l1;
            i = (i + 1) % 10;
            if (k > 1) {
                for (int k2 = 0; k2 < 10; k2++) {
                    if (otim[k2] != 0L) {
                        otim[k2] += k;
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
                mouseButtonPressed = eventMouseButtonPressed;
                clickX = eventClickX;
                clickY = eventClickY;
                clickTime = eventTime;
                eventMouseButtonPressed = 0;
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
                    System.out.println("otim" + i3 + ":" + otim[i3]);
                }
                System.out.println("fps:" + framesPerSecond + " ratio:" + j + " count:" + i1);
                System.out.println("del:" + k + " deltime:" + delayTime + " mindel:" + minDelay);
                System.out.println("intex:" + j1 + " opos:" + i);
                shouldDebug = false;
                j1 = 0;
            }
        }
        if (gameState == -1) {
            exit();
        }
    }
    
    /**
     * Initialize the frame, with specified height and width.
     * 
     * @param frameWidth The width or the frame.
     * @param frameHeight The height of the frame.
     */
    public void initializeFrame(int frameWidth, int frameHeight) {
        appWidth = frameWidth;
        appHeight = frameHeight;
        graphics = getGameComponent().getGraphics();
        fullScreen = new GraphicsBuffer(appWidth, appHeight, getGameComponent());
        startRunnable(this, 1);
    }
    
    /**
     * Starts the runnable thread for this applet.
     * 
     * @param runnable The object that implements runnable.
     * @param priority Priority for the thread.
     */
    public void startRunnable(Runnable runnable, int priority) {
        Thread thread = new Thread(runnable);
        thread.start();
        thread.setPriority(priority);
    }
    
    /**
     * Create the applet's frame.
     * 
     * @param frameWidth The width or the frame.
     * @param frameHeight The height of the frame.
     */
    public void createFrame(int frameWidth, int frameHeight) {
        appWidth = frameWidth;
        appHeight = frameHeight;
        rsFrame = new RSFrame(this, appWidth, appHeight);
        graphics = getGameComponent().getGraphics();
        fullScreen = new GraphicsBuffer(appWidth, appHeight, getGameComponent());
        startRunnable(this, 1);
    }
    
    /**
     * Draws loading screen, which includes:
     * - Loading text.
     * - Loading bar.
     * - Black background.
     * 
     * @param loadingBarPercentage Percentage loaded.
     * @param loadingBarText String to accompany Percentage.
     */
    public void drawLoadingText(String loadingBarText, int loadingBarPercentage) {
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
        Font helveticaFont = new Font("Helvetica", 0, 13);
        FontMetrics fotnMetrics = getGameComponent().getFontMetrics(helveticaFont);
        if (shouldClearScreen) {
            graphics.setColor(Color.black);
            graphics.fillRect(0, 0, appWidth, appHeight);
            shouldClearScreen = false;
        }
        int x = appWidth / 2 - 150;
        int y = appHeight / 2 - 18;
        graphics.setColor(Color.blue);
        graphics.drawRect(x - 2, y, 303, 33);
        graphics.fillRect(x, y + 2, loadingBarPercentage * 3, 30);
        graphics.setColor(Color.black);
        graphics.fillRect(x + loadingBarPercentage * 3, y + 2, 300 - loadingBarPercentage * 3, 30);
        graphics.setFont(helveticaFont);
        graphics.setColor(Color.white);
        graphics.drawString(loadingBarText, (appWidth - fotnMetrics.stringWidth(loadingBarText)) / 2, y + 22);
    }
    
    /**
     * Called when we want to exit
     * - Sets gameState to -2.
     * - Cleans up.
     * - Waits for frame to become null before closing.
     * 
     */
    public void exit() {
        gameState = -2;
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
    
    /**
     * Get the gameframe component.
     * 
     * @return The rsFrame instance, or this.
     */
    public Component getGameComponent() {
        if (rsFrame != null) {
            return rsFrame;
        } else {
            return this;
        }
    }
    
    /**
     * ?????
     * 
     * @return Some iD
     */
    public int method7() {
        int charID = -1;
        if (writeIndex != readIndex) {
            charID = inputBuffer[readIndex];
            readIndex = readIndex + 1 & 0x7f;
        }
        return charID;
    }
    
    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        idleTime = 0;
        mouseButtonDown = 0;
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        idleTime = 0;
        int keyCode = keyEvent.getKeyCode();
        int keyChar = keyEvent.getKeyChar();
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
    
    @Override
    public void windowClosing(WindowEvent windowevent) {
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
        mouseEventX = -1;
        mouseEventY = -1;
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

    @Override
    public void start() {
        if (gameState >= 0) {
            gameState = 0;
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
        eventClickX = x;
        eventClickY = y;
        eventTime = System.currentTimeMillis();
        if (mouseevent.isMetaDown()) {
            eventMouseButtonPressed = 2;
            mouseButtonDown = 2;
        } else {
            eventMouseButtonPressed = 1;
            mouseButtonDown = 1;
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
        mouseEventX = x;
        mouseEventY = y;
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
        mouseEventX = i;
        mouseEventY = j;
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
        gameState = -1;
        try {
            Thread.sleep(5000L);
        } catch (Exception exception) {
        }
        if (gameState == -1) {
            exit();
        }
    }

    @Override
    public void focusLost(FocusEvent focusevent) {
        awtFocus = false;
        for (int i = 0; i < 128; i++) {
            keyStatus[i] = 0;
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
            keyStatus[c] = 0;
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

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }
}
