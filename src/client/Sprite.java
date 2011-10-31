package client;


import java.awt.*;
import java.io.*;
import java.awt.image.PixelGrabber;
import javax.swing.ImageIcon;

public final class Sprite extends DrawingArea {

    public int myPixels[];
    public int myWidth;
    public int myHeight;
    public int drawOffsetX;
    public int drawOffsetY;
    public int maxWidth;
    public int maxHeight;

    public Sprite(int i, int j) {
        myPixels = new int[i * j];
        myWidth = maxWidth = i;
        myHeight = maxHeight = j;
        drawOffsetX = drawOffsetY = 0;
    }

    public Sprite(Component component) {
        try {
            String s = sign.signlink.findcachedir() + "sprites/titlescreen/background.png";
            File fcheck = new File(s);
            if (!fcheck.exists()) {
                s = sign.signlink.findcachedir() + "sprites/misc/error.png";
            }
            Image image = Toolkit.getDefaultToolkit().getImage((s));
            MediaTracker mediatracker = new MediaTracker(component);
            mediatracker.addImage(image, 0);
            mediatracker.waitForAll();
            myWidth = image.getWidth(component);
            myHeight = image.getHeight(component);
            maxWidth = myWidth;
            maxHeight = myHeight;
            drawOffsetX = 0;
            drawOffsetY = 0;
            myPixels = new int[myWidth * myHeight];
            PixelGrabber pixelgrabber = new PixelGrabber(image, 0, 0, myWidth, myHeight, myPixels, 0, myWidth);
            pixelgrabber.grabPixels();
            return;
        } catch (Exception exception) {
        }
    }

    public Sprite(String s, int width, int height) {
        try {
            Image image = Toolkit.getDefaultToolkit().createImage(FileOperations.ReadFile(s));
            myWidth = width;
            myHeight = height;
            maxWidth = myWidth;
            maxHeight = myHeight;
            drawOffsetX = 0;
            drawOffsetY = 0;
            myPixels = new int[myWidth * myHeight];
            PixelGrabber pixelgrabber = new PixelGrabber(image, 0, 0, myWidth, myHeight, myPixels, 0, myWidth);
            pixelgrabber.grabPixels();
        } catch (Exception exception) {
        }
    }

    public Sprite(String s) {
        try {
            File fcheck = new File(s);
            if (!fcheck.exists()) {
                s = sign.signlink.findcachedir() + "sprites/misc/error.png";
            }
            Image image = Toolkit.getDefaultToolkit().createImage(FileOperations.ReadFile(s));
            ImageIcon imageicon = new ImageIcon(s);
            myWidth = imageicon.getIconWidth();
            myHeight = imageicon.getIconHeight();
            maxWidth = myWidth;
            maxHeight = myHeight;
            drawOffsetX = 0;
            drawOffsetY = 0;
            myPixels = new int[myWidth * myHeight];
            PixelGrabber pixelgrabber = new PixelGrabber(image, 0, 0, myWidth, myHeight, myPixels, 0, myWidth);
            pixelgrabber.grabPixels();
        } catch (Exception exception) {
        }
    }

    public Sprite(StreamLoader class44, String s, int i) {
        Stream class30_sub2_sub2 = new Stream(class44.getDataForName((new StringBuilder()).append(s).append(".dat").toString()));
        Stream class30_sub2_sub2_1 = new Stream(class44.getDataForName("index.dat"));
        class30_sub2_sub2_1.currentOffset = class30_sub2_sub2.readUnsignedWord();
        maxWidth = class30_sub2_sub2_1.readUnsignedWord();
        maxHeight = class30_sub2_sub2_1.readUnsignedWord();
        int j = class30_sub2_sub2_1.readUnsignedByte();
        int ai[] = new int[j];
        for (int k = 0; k < j - 1; k++) {
            ai[k + 1] = class30_sub2_sub2_1.read3Bytes();
            if (ai[k + 1] == 0) {
                ai[k + 1] = 1;
            }
        }
        for (int l = 0; l < i; l++) {
            class30_sub2_sub2_1.currentOffset += 2;
            class30_sub2_sub2.currentOffset += class30_sub2_sub2_1.readUnsignedWord() * class30_sub2_sub2_1.readUnsignedWord();
            class30_sub2_sub2_1.currentOffset++;
        }
        drawOffsetX = class30_sub2_sub2_1.readUnsignedByte();
        drawOffsetY = class30_sub2_sub2_1.readUnsignedByte();
        myWidth = class30_sub2_sub2_1.readUnsignedWord();
        myHeight = class30_sub2_sub2_1.readUnsignedWord();
        int i1 = class30_sub2_sub2_1.readUnsignedByte();
        int j1 = myWidth * myHeight;
        myPixels = new int[j1];
        //writeTemp(""+s+" "+i+"", "@txt sprite dump.txt");

        if (s.startsWith("backleft2")) {
            try {
                Image image = Toolkit.getDefaultToolkit().getImage(sign.signlink.findcachedir() + "sprites/gameframe/borderchatleft.png");
                myWidth = 8;
                myHeight = 134;
                myPixels = new int[myWidth * myHeight];
                PixelGrabber pixelgrabber = new PixelGrabber(image, 0, 0, myWidth, myHeight, myPixels, 0, myWidth);
                pixelgrabber.grabPixels();
            } catch (Exception exception) {
                System.out.println(((Object) (exception)));
            }
        } else if (s.startsWith("backbase1")) {
            try {
                Image image = Toolkit.getDefaultToolkit().getImage(sign.signlink.findcachedir() + "sprites/gameframe/bottomopt.png");
                myPixels = new int[myWidth * myHeight];
                PixelGrabber pixelgrabber = new PixelGrabber(image, 0, 0, myWidth, myHeight, myPixels, 0, myWidth);
                pixelgrabber.grabPixels();
            } catch (Exception exception) {
                System.out.println(((Object) (exception)));
            }
        } else if (s.startsWith("backbase2")) {
            try {
                Image image = Toolkit.getDefaultToolkit().getImage(sign.signlink.findcachedir() + "sprites/gameframe/bottomtabs.png");
                myPixels = new int[myWidth * myHeight];
                PixelGrabber pixelgrabber = new PixelGrabber(image, 0, 0, myWidth, myHeight, myPixels, 0, myWidth);
                pixelgrabber.grabPixels();
            } catch (Exception exception) {
                System.out.println(((Object) (exception)));
            }
        } else if (s.startsWith("backhmid1")) {
            try {
                Image image = Toolkit.getDefaultToolkit().getImage(sign.signlink.findcachedir() + "sprites/gameframe/toptabs.png");
                myPixels = new int[myWidth * myHeight];
                PixelGrabber pixelgrabber = new PixelGrabber(image, 0, 0, myWidth, myHeight, myPixels, 0, myWidth);
                pixelgrabber.grabPixels();
            } catch (Exception exception) {
                System.out.println(((Object) (exception)));
            }
        } else if (s.startsWith("backhmid2")) {
            try {
                Image image = Toolkit.getDefaultToolkit().getImage(sign.signlink.findcachedir() + "sprites/gameframe/chattopborder.png");
                myWidth = 555;
                myHeight = 6;
                myPixels = new int[myWidth * myHeight];
                PixelGrabber pixelgrabber = new PixelGrabber(image, 0, 0, myWidth, myHeight, myPixels, 0, myWidth);
                pixelgrabber.grabPixels();
            } catch (Exception exception) {
                System.out.println(((Object) (exception)));
            }
        } else if (s.startsWith("backleft1")) {
            try {
                Image image = Toolkit.getDefaultToolkit().getImage(sign.signlink.findcachedir() + "sprites/gameframe/borderleft.png");
                myPixels = new int[myWidth * myHeight];
                PixelGrabber pixelgrabber = new PixelGrabber(image, 0, 0, myWidth, myHeight, myPixels, 0, myWidth);
                pixelgrabber.grabPixels();
            } catch (Exception exception) {
                System.out.println(((Object) (exception)));
            }
        } else if (s.startsWith("backright2")) {
            try {
                Image image = Toolkit.getDefaultToolkit().getImage(sign.signlink.findcachedir() + "sprites/gameframe/pillarright.png");
                myPixels = new int[myWidth * myHeight];
                PixelGrabber pixelgrabber = new PixelGrabber(image, 0, 0, myWidth, myHeight, myPixels, 0, myWidth);
                pixelgrabber.grabPixels();
            } catch (Exception exception) {
                System.out.println(((Object) (exception)));
            }
        } else if (s.startsWith("backtop1")) {
            try {
                Image image = Toolkit.getDefaultToolkit().getImage(sign.signlink.findcachedir() + "sprites/gameframe/topborder.png");
                myPixels = new int[myWidth * myHeight];
                PixelGrabber pixelgrabber = new PixelGrabber(image, 0, 0, myWidth, myHeight, myPixels, 0, myWidth);
                pixelgrabber.grabPixels();
            } catch (Exception exception) {
                System.out.println(((Object) (exception)));
            }
        } else if (s.startsWith("backvmid2")) {
            try {
                Image image = Toolkit.getDefaultToolkit().getImage(sign.signlink.findcachedir() + "sprites/gameframe/pillarlefttop.png");
                myPixels = new int[myWidth * myHeight];
                PixelGrabber pixelgrabber = new PixelGrabber(image, 0, 0, myWidth, myHeight, myPixels, 0, myWidth);
                pixelgrabber.grabPixels();
            } catch (Exception exception) {
                System.out.println(((Object) (exception)));
            }
        } else if (s.startsWith("backvmid3")) {
            try {
                Image image = Toolkit.getDefaultToolkit().getImage(sign.signlink.findcachedir() + "sprites/gameframe/pillarleftbottom.png");
                myWidth = 39;
                myHeight = 128;
                myPixels = new int[myWidth * myHeight];
                PixelGrabber pixelgrabber = new PixelGrabber(image, 0, 0, myWidth, myHeight, myPixels, 0, myWidth);
                pixelgrabber.grabPixels();
            } catch (Exception exception) {
                System.out.println(((Object) (exception)));
            }
        } else if (s.startsWith("mapback")) {
            try {
                Image image = Toolkit.getDefaultToolkit().getImage(sign.signlink.findcachedir() + "sprites/gameframe/map1.png");
                myPixels = new int[myWidth * myHeight];
                PixelGrabber pixelgrabber = new PixelGrabber(image, 0, 0, myWidth, myHeight, myPixels, 0, myWidth);
                pixelgrabber.grabPixels();
            } catch (Exception exception) {
                System.out.println(((Object) (exception)));
            }
        } else if (s.startsWith("sideicon")) {
            try {
                Image image = Toolkit.getDefaultToolkit().getImage(sign.signlink.findcachedir() + "sprites/tabicons/icon " + i + ".png");
                myWidth = 100;
                myHeight = 100;
                myPixels = new int[myWidth * myHeight];
                PixelGrabber pixelgrabber = new PixelGrabber(image, 0, 0, myWidth, myHeight, myPixels, 0, myWidth);
                pixelgrabber.grabPixels();
            } catch (Exception exception) {
                System.out.println(((Object) (exception)));
            }
        } else if (s.startsWith("staticons")) {
            try {
                Image image = Toolkit.getDefaultToolkit().getImage(sign.signlink.findcachedir() + "sprites/" + s + " " + i + ".png");
                myWidth = 28;
                myHeight = 28;
                myPixels = new int[myWidth * myHeight];
                PixelGrabber pixelgrabber = new PixelGrabber(image, 0, 0, myWidth, myHeight, myPixels, 0, myWidth);
                pixelgrabber.grabPixels();
            } catch (Exception exception) {
                System.out.println(((Object) (exception)));
            }
        } else if (s.startsWith("chat")) {
            try {
                Image image = Toolkit.getDefaultToolkit().getImage(sign.signlink.findcachedir() + "sprites/gameframe/chatback.png");
                myWidth = 600;
                myHeight = 400;
                myPixels = new int[myWidth * myHeight];
                PixelGrabber pixelgrabber = new PixelGrabber(image, 0, 0, myWidth, myHeight, myPixels, 0, myWidth);
                pixelgrabber.grabPixels();
            } catch (Exception exception) {
                System.out.println(((Object) (exception)));
            }
        } else if (s.startsWith("redstone2")) {
            try {
                Image image = Toolkit.getDefaultToolkit().getImage(sign.signlink.findcachedir() + "sprites/gameframe/stones/n.png");
                myWidth = 100;
                myHeight = 100;
                myPixels = new int[myWidth * myHeight];
                PixelGrabber pixelgrabber = new PixelGrabber(image, 0, 0, myWidth, myHeight, myPixels, 0, myWidth);
                pixelgrabber.grabPixels();
            } catch (Exception exception) {
                System.out.println(((Object) (exception)));
            }
        } else if (s.startsWith("redstone1")) {
            try {
                myWidth = 100;
                myHeight = 100;
                myPixels = new int[myWidth * myHeight];
                Image image = Toolkit.getDefaultToolkit().getImage(sign.signlink.findcachedir() + "sprites/gameframe/stones/REDSTONE1 0.png");
                if (client.flip) {
                    image = Toolkit.getDefaultToolkit().getImage(sign.signlink.findcachedir() + "sprites/gameframe/stones/REDSTONE1 1.png");
                }
                if (client.flip_s) {
                    image = Toolkit.getDefaultToolkit().getImage(sign.signlink.findcachedir() + "sprites/gameframe/stones/REDSTONE1 2.png");
                }
                if (client.flip_r) {
                    image = Toolkit.getDefaultToolkit().getImage(sign.signlink.findcachedir() + "sprites/gameframe/stones/REDSTONE1 3.png");
                }
                PixelGrabber pixelgrabber = new PixelGrabber(image, 0, 0, myWidth, myHeight, myPixels, 0, myWidth);
                pixelgrabber.grabPixels();
            } catch (Exception exception) {
                System.out.println(((Object) (exception)));
            }
        } else if (s.startsWith("scroll")) {
            try {
                myWidth = 28;
                myHeight = 16;
                Image image = Toolkit.getDefaultToolkit().getImage(sign.signlink.findcachedir() + "sprites/" + s + " " + i + ".png");
                myPixels = new int[myWidth * myHeight];
                PixelGrabber pixelgrabber = new PixelGrabber(image, 0, 0, myWidth, myHeight, myPixels, 0, myWidth);
                pixelgrabber.grabPixels();
            } catch (Exception exception) {
                System.out.println(((Object) (exception)));
            }
        } else if (s.equalsIgnoreCase("invback")) {
            try {
                Image image = Toolkit.getDefaultToolkit().getImage(sign.signlink.findcachedir() + "sprites/gameframe/invback.png");
                myWidth = 192;
                myHeight = 261;
                myPixels = new int[myWidth * myHeight];
                PixelGrabber pixelgrabber = new PixelGrabber(image, 0, 0, myWidth, myHeight, myPixels, 0, myWidth);
                pixelgrabber.grabPixels();
            } catch (Exception exception) {
                System.out.println(((Object) (exception)));
            }
        } else if (s.equalsIgnoreCase("compass")) {
            try {
                Image image = Toolkit.getDefaultToolkit().getImage(sign.signlink.findcachedir() + "sprites/gameframe/compass.png");
                myPixels = new int[myWidth * myHeight];
                PixelGrabber pixelgrabber = new PixelGrabber(image, 0, 0, myWidth, myHeight, myPixels, 0, myWidth);
                pixelgrabber.grabPixels();
            } catch (Exception exception) {
                System.out.println(((Object) (exception)));
            }
        } else if (s.equalsIgnoreCase("mod_icons")) {
            try {
                Image image = Toolkit.getDefaultToolkit().getImage(sign.signlink.findcachedir() + "sprites/misc/mod " + i + ".png");
                myPixels = new int[myWidth * myHeight];
                PixelGrabber pixelgrabber = new PixelGrabber(image, 0, 0, myWidth, myHeight, myPixels, 0, myWidth);
                pixelgrabber.grabPixels();
            } catch (Exception exception) {
                System.out.println(((Object) (exception)));
            }
        } else if (s.equalsIgnoreCase("STEELBORDER")) { //corners
            try {
                Image image = Toolkit.getDefaultToolkit().getImage(sign.signlink.findcachedir() + "sprites/borders/corners " + i + ".png");
                myPixels = new int[myWidth * myHeight];
                PixelGrabber pixelgrabber = new PixelGrabber(image, 0, 0, myWidth, myHeight, myPixels, 0, myWidth);
                pixelgrabber.grabPixels();
            } catch (Exception exception) {
                System.out.println(((Object) (exception)));
            }
        } else if (s.equalsIgnoreCase("STEELBORDER2") && i == 0 || s.equalsIgnoreCase("STEELBORDER2") && i == 1) { //vert and horizontal top and left
            try {
                Image image = Toolkit.getDefaultToolkit().getImage(sign.signlink.findcachedir() + "sprites/borders/topright " + i + ".png");
                myPixels = new int[myWidth * myHeight];
                PixelGrabber pixelgrabber = new PixelGrabber(image, 0, 0, myWidth, myHeight, myPixels, 0, myWidth);
                pixelgrabber.grabPixels();
            } catch (Exception exception) {
                System.out.println(((Object) (exception)));
            }
        } else if (s.equalsIgnoreCase("MISCGRAPHICS") && i == 2 || s.equalsIgnoreCase("MISCGRAPHICS") && i == 3) { //vert and horizontal bottom and right
            try {
                Image image = Toolkit.getDefaultToolkit().getImage(sign.signlink.findcachedir() + "sprites/borders/botleft " + i + ".png");
                myPixels = new int[myWidth * myHeight];
                PixelGrabber pixelgrabber = new PixelGrabber(image, 0, 0, myWidth, myHeight, myPixels, 0, myWidth);
                pixelgrabber.grabPixels();
            } catch (Exception exception) {
                System.out.println(((Object) (exception)));
            }
        } else {
            if (i1 == 0) {
                for (int k1 = 0; k1 < j1; k1++) {
                    myPixels[k1] = ai[class30_sub2_sub2.readUnsignedByte()];
                }
                return;
            }
            if (i1 == 1) {
                for (int l1 = 0; l1 < myWidth; l1++) {
                    for (int i2 = 0; i2 < myHeight; i2++) {
                        myPixels[l1 + i2 * myWidth] = ai[class30_sub2_sub2.readUnsignedByte()];
                    }
                }
            }
        }
    }

    public void drawSprite(int i, int k, int color) {
        int tempWidth = myWidth + 2;
        int tempHeight = myHeight + 2;
        int[] tempArray = new int[tempWidth * tempHeight];
        for (int x = 0; x < myWidth; x++) {
            for (int y = 0; y < myHeight; y++) {
                if (myPixels[x + y * myWidth] != 0) {
                    tempArray[(x + 1) + (y + 1) * tempWidth] = myPixels[x + y * myWidth];
                }
            }
        }
        for (int x = 0; x < tempWidth; x++) {
            for (int y = 0; y < tempHeight; y++) {
                if (tempArray[(x) + (y) * tempWidth] == 0) {
                    if (x < tempWidth - 1 && tempArray[(x + 1) + ((y) * tempWidth)] > 0 && tempArray[(x + 1) + ((y) * tempWidth)] != 0xffffff) {
                        tempArray[(x) + (y) * tempWidth] = color;
                    }
                    if (x > 0 && tempArray[(x - 1) + ((y) * tempWidth)] > 0 && tempArray[(x - 1) + ((y) * tempWidth)] != 0xffffff) {
                        tempArray[(x) + (y) * tempWidth] = color;
                    }
                    if (y < tempHeight - 1 && tempArray[(x) + ((y + 1) * tempWidth)] > 0 && tempArray[(x) + ((y + 1) * tempWidth)] != 0xffffff) {
                        tempArray[(x) + (y) * tempWidth] = color;
                    }
                    if (y > 0 && tempArray[(x) + ((y - 1) * tempWidth)] > 0 && tempArray[(x) + ((y - 1) * tempWidth)] != 0xffffff) {
                        tempArray[(x) + (y) * tempWidth] = color;
                    }
                }
            }
        }
        i--;
        k--;
        i += drawOffsetX;
        k += drawOffsetY;
        int l = i + k * DrawingArea.width;
        int i1 = 0;
        int j1 = tempHeight;
        int k1 = tempWidth;
        int l1 = DrawingArea.width - k1;
        int i2 = 0;
        if (k < DrawingArea.topY) {
            int j2 = DrawingArea.topY - k;
            j1 -= j2;
            k = DrawingArea.topY;
            i1 += j2 * k1;
            l += j2 * DrawingArea.width;
        }
        if (k + j1 > DrawingArea.bottomY) {
            j1 -= (k + j1) - DrawingArea.bottomY;
        }
        if (i < DrawingArea.topX) {
            int k2 = DrawingArea.topX - i;
            k1 -= k2;
            i = DrawingArea.topX;
            i1 += k2;
            l += k2;
            i2 += k2;
            l1 += k2;
        }
        if (i + k1 > DrawingArea.bottomX) {
            int l2 = (i + k1) - DrawingArea.bottomX;
            k1 -= l2;
            i2 += l2;
            l1 += l2;
        }
        if (!(k1 <= 0 || j1 <= 0)) {
            method349(DrawingArea.pixels, tempArray, 0, i1, l, k1, j1, l1, i2);
        }
    }

    public static void writeTemp(String tempData, String tempFile) {
        try {
            BufferedWriter LV = new BufferedWriter(new FileWriter(tempFile, true));
            LV.write(tempData);
            LV.newLine();
            LV.flush();
        } catch (IOException IOE) {
        }
    }

    public void createSpriteDrawingArea() {
        DrawingArea.initDrawingArea(myHeight, myWidth, myPixels);
    }

    public void method344(int i, int j, int k, int l) {
        for (int i1 = 0; i1 < myPixels.length; i1++) {
            int j1 = myPixels[i1];
            if (j1 != 0) {
                int k1 = j1 >> 16 & 0xff;
                k1 += i;
                if (k1 < 1) {
                    k1 = 1;
                } else {
                    if (k1 > 255) {
                        k1 = 255;
                    }
                }
                int l1 = j1 >> 8 & 0xff;
                l1 += j;
                if (l1 < 1) {
                    l1 = 1;
                } else {
                    if (l1 > 255) {
                        l1 = 255;
                    }
                }
                int i2 = j1 & 0xff;
                i2 += k;
                if (i2 < 1) {
                    i2 = 1;
                } else {
                    if (i2 > 255) {
                        i2 = 255;
                    }
                }
                myPixels[i1] = (k1 << 16) + (l1 << 8) + i2;
            }
        }
    }

    public void method345() {
        int ai[] = new int[maxWidth * maxHeight];
        for (int j = 0; j < myHeight; j++) {
            for (int k = 0; k < myWidth; k++) {
                ai[(j + drawOffsetY) * maxWidth + (k + drawOffsetX)] = myPixels[j * myWidth + k];
            }
        }
        myPixels = ai;
        myWidth = maxWidth;
        myHeight = maxHeight;
        drawOffsetX = 0;
        drawOffsetY = 0;
    }

    public void method346(int i, int j) {
        i += drawOffsetX;
        j += drawOffsetY;
        int l = i + j * DrawingArea.width;
        int i1 = 0;
        int j1 = myHeight;
        int k1 = myWidth;
        int l1 = DrawingArea.width - k1;
        int i2 = 0;
        if (j < DrawingArea.topY) {
            int j2 = DrawingArea.topY - j;
            j1 -= j2;
            j = DrawingArea.topY;
            i1 += j2 * k1;
            l += j2 * DrawingArea.width;
        }
        if (j + j1 > DrawingArea.bottomY) {
            j1 -= (j + j1) - DrawingArea.bottomY;
        }
        if (i < DrawingArea.topX) {
            int k2 = DrawingArea.topX - i;
            k1 -= k2;
            i = DrawingArea.topX;
            i1 += k2;
            l += k2;
            i2 += k2;
            l1 += k2;
        }
        if (i + k1 > DrawingArea.bottomX) {
            int l2 = (i + k1) - DrawingArea.bottomX;
            k1 -= l2;
            i2 += l2;
            l1 += l2;
        }
        if (k1 <= 0 || j1 <= 0) {
            return;
        } else {
            method347(l, k1, j1, i2, i1, l1, myPixels, DrawingArea.pixels);
            return;
        }
    }

    private void method347(int i, int j, int k, int l, int i1, int k1, int ai[], int ai1[]) {
        int l1 = -(j >> 2);
        j = -(j & 3);
        for (int i2 = -k; i2 < 0; i2++) {
            for (int j2 = l1; j2 < 0; j2++) {
                ai1[i++] = ai[i1++];
                ai1[i++] = ai[i1++];
                ai1[i++] = ai[i1++];
                ai1[i++] = ai[i1++];
            }
            for (int k2 = j; k2 < 0; k2++) {
                ai1[i++] = ai[i1++];
            }
            i += k1;
            i1 += l;
        }
    }

    public void method348(int i, int k) {
        i += drawOffsetX;
        k += drawOffsetY;
        int l = i + k * DrawingArea.width;
        int i1 = 0;
        int j1 = myHeight;
        int k1 = myWidth;
        int l1 = DrawingArea.width - k1;
        int i2 = 0;
        if (k < DrawingArea.topY) {
            int j2 = DrawingArea.topY - k;
            j1 -= j2;
            k = DrawingArea.topY;
            i1 += j2 * k1;
            l += j2 * DrawingArea.width;
        }
        if (k + j1 > DrawingArea.bottomY) {
            j1 -= (k + j1) - DrawingArea.bottomY;
        }
        if (i < DrawingArea.topX) {
            int k2 = DrawingArea.topX - i;
            k1 -= k2;
            i = DrawingArea.topX;
            i1 += k2;
            l += k2;
            i2 += k2;
            l1 += k2;
        }
        if (i + k1 > DrawingArea.bottomX) {
            int l2 = (i + k1) - DrawingArea.bottomX;
            k1 -= l2;
            i2 += l2;
            l1 += l2;
        }
        if (k1 <= 0 || j1 <= 0) {
            return;
        } else {
            method349(DrawingArea.pixels, myPixels, 0, i1, l, k1, j1, l1, i2);
            return;
        }
    }

    public void method349(int ai[], int ai1[], int i, int j, int k, int l, int i1, int j1, int k1) {
        int l1 = -(l >> 2);
        l = -(l & 3);
        for (int i2 = -i1; i2 < 0; i2++) {
            for (int j2 = l1; j2 < 0; j2++) {
                i = ai1[j++];
                if (i != 0 && i != -1) {
                    ai[k++] = i;
                } else {
                    k++;
                }
                i = ai1[j++];
                if (i != 0 && i != -1) {
                    ai[k++] = i;
                } else {
                    k++;
                }
                i = ai1[j++];
                if (i != 0 && i != -1) {
                    ai[k++] = i;
                } else {
                    k++;
                }
                i = ai1[j++];
                if (i != 0 && i != -1) {
                    ai[k++] = i;
                } else {
                    k++;
                }
            }
            for (int k2 = l; k2 < 0; k2++) {
                i = ai1[j++];
                if (i != 0 && i != -1) {
                    ai[k++] = i;
                } else {
                    k++;
                }
            }
            k += j1;
            j += k1;
        }
    }

    public void method350(int i, int j, int k) {
        i += drawOffsetX;
        j += drawOffsetY;
        int i1 = i + j * DrawingArea.width;
        int j1 = 0;
        int k1 = myHeight;
        int l1 = myWidth;
        int i2 = DrawingArea.width - l1;
        int j2 = 0;
        if (j < DrawingArea.topY) {
            int k2 = DrawingArea.topY - j;
            k1 -= k2;
            j = DrawingArea.topY;
            j1 += k2 * l1;
            i1 += k2 * DrawingArea.width;
        }
        if (j + k1 > DrawingArea.bottomY) {
            k1 -= (j + k1) - DrawingArea.bottomY;
        }
        if (i < DrawingArea.topX) {
            int l2 = DrawingArea.topX - i;
            l1 -= l2;
            i = DrawingArea.topX;
            j1 += l2;
            i1 += l2;
            j2 += l2;
            i2 += l2;
        }
        if (i + l1 > DrawingArea.bottomX) {
            int i3 = (i + l1) - DrawingArea.bottomX;
            l1 -= i3;
            j2 += i3;
            i2 += i3;
        }
        if (l1 <= 0 || k1 <= 0) {
            return;
        } else {
            method351(j1, l1, DrawingArea.pixels, 0, myPixels, j2, k1, i2, k, i1);
            return;
        }
    }

    private void method351(int i, int j, int ai[], int k, int ai1[], int l, int i1, int j1, int k1, int l1) {
        int j2 = 256 - k1;
        for (int k2 = -i1; k2 < 0; k2++) {
            for (int l2 = -j; l2 < 0; l2++) {
                k = ai1[i++];
                if (k != 0) {
                    int i3 = ai[l1];
                    ai[l1++] = ((k & 0xff00ff) * k1 + (i3 & 0xff00ff) * j2 & 0xff00ff00) + ((k & 0xff00) * k1 + (i3 & 0xff00) * j2 & 0xff0000) >> 8;
                } else {
                    l1++;
                }
            }
            l1 += j1;
            i += l;
        }
    }

    public void method352(int i, int j, int ai[], int k, int ai1[], int i1, int j1, int k1, int l1, int i2) {
        try {
            int j2 = -l1 / 2;
            int k2 = -i / 2;
            int l2 = (int) (Math.sin((double) j / 326.11000000000001D) * 65536D);
            int i3 = (int) (Math.cos((double) j / 326.11000000000001D) * 65536D);
            l2 = l2 * k >> 8;
            i3 = i3 * k >> 8;
            int j3 = (i2 << 16) + (k2 * l2 + j2 * i3);
            int k3 = (i1 << 16) + (k2 * i3 - j2 * l2);
            int l3 = k1 + j1 * DrawingArea.width;
            for (j1 = 0; j1 < i; j1++) {
                int i4 = ai1[j1];
                int j4 = l3 + i4;
                int k4 = j3 + i3 * i4;
                int l4 = k3 - l2 * i4;
                for (k1 = -ai[j1]; k1 < 0; k1++) {
                    DrawingArea.pixels[j4++] = myPixels[(k4 >> 16) + (l4 >> 16) * myWidth];
                    k4 += i3;
                    l4 -= l2;
                }
                j3 += l2;
                k3 += i3;
                l3 += DrawingArea.width;
            }
            return;
        } catch (Exception _ex) {
            return;
        }
    }

    public void method353(int i, int j, int k, int l, int j1, int k1, double d, int l1) {
        try {
            int i2 = -k / 2;
            int j2 = -k1 / 2;
            int k2 = (int) (Math.sin(d) * 65536D);
            int l2 = (int) (Math.cos(d) * 65536D);
            k2 = k2 * j1 >> 8;
            l2 = l2 * j1 >> 8;
            int i3 = (l << 16) + (j2 * k2 + i2 * l2);
            int j3 = (j << 16) + (j2 * l2 - i2 * k2);
            int k3 = l1 + i * DrawingArea.width;
            for (i = 0; i < k1; i++) {
                int l3 = k3;
                int i4 = i3;
                int j4 = j3;
                for (l1 = -k; l1 < 0; l1++) {
                    int k4 = myPixels[(i4 >> 16) + (j4 >> 16) * myWidth];
                    if (k4 != 0) {
                        DrawingArea.pixels[l3++] = k4;
                    } else {
                        l3++;
                    }
                    i4 += l2;
                    j4 -= k2;
                }
                i3 += k2;
                j3 += l2;
                k3 += DrawingArea.width;
            }
            return;
        } catch (Exception _ex) {
            return;
        }
    }

    public void method354(Background class30_sub2_sub1_sub2, int i, int j) {
        j += drawOffsetX;
        i += drawOffsetY;
        int k = j + i * DrawingArea.width;
        int l = 0;
        int i1 = myHeight;
        int j1 = myWidth;
        int k1 = DrawingArea.width - j1;
        int l1 = 0;
        if (i < DrawingArea.topY) {
            int i2 = DrawingArea.topY - i;
            i1 -= i2;
            i = DrawingArea.topY;
            l += i2 * j1;
            k += i2 * DrawingArea.width;
        }
        if (i + i1 > DrawingArea.bottomY) {
            i1 -= (i + i1) - DrawingArea.bottomY;
        }
        if (j < DrawingArea.topX) {
            int j2 = DrawingArea.topX - j;
            j1 -= j2;
            j = DrawingArea.topX;
            l += j2;
            k += j2;
            l1 += j2;
            k1 += j2;
        }
        if (j + j1 > DrawingArea.bottomX) {
            int k2 = (j + j1) - DrawingArea.bottomX;
            j1 -= k2;
            l1 += k2;
            k1 += k2;
        }
        if (j1 <= 0 || i1 <= 0) {
            return;
        } else {
            method355(myPixels, j1, class30_sub2_sub1_sub2.pixelInfo, i1, DrawingArea.pixels, 0, k1, k, l1, l);
            return;
        }
    }

    private void method355(int ai[], int i, byte abyte0[], int j, int ai1[], int k, int l, int i1, int j1, int k1) {
        int l1 = -(i >> 2);
        i = -(i & 3);
        for (int j2 = -j; j2 < 0; j2++) {
            for (int k2 = l1; k2 < 0; k2++) {
                k = ai[k1++];
                if (k != 0 && abyte0[i1] == 0) {
                    ai1[i1++] = k;
                } else {
                    i1++;
                }
                k = ai[k1++];
                if (k != 0 && abyte0[i1] == 0) {
                    ai1[i1++] = k;
                } else {
                    i1++;
                }

                k = ai[k1++];
                if (k != 0 && abyte0[i1] == 0) {
                    ai1[i1++] = k;
                } else {
                    i1++;
                }

                k = ai[k1++];
                if (k != 0 && abyte0[i1] == 0) {
                    ai1[i1++] = k;
                } else {
                    i1++;
                }
            }
            for (int l2 = i; l2 < 0; l2++) {
                k = ai[k1++];
                if (k != 0 && abyte0[i1] == 0) {
                    ai1[i1++] = k;
                } else {
                    i1++;
                }
            }
            i1 += l;
            k1 += j1;
        }
    }
}
