package map;

import map.drawing.font.MapFont;
import map.instance.RSApplet;
import map.data.*;
import map.drawing.*;

import java.io.*;

public class Map extends RSApplet {
    
    public static boolean showMapAreas = false;
    public int brownBoxColour;
    public int redBoxColour;
    public boolean needToDraw;
    public static int mapStartX;
    public static int mapStartY;
    public static int mapWidth;
    public static int mapHeight;
    public int anIntArray115[];
    public int anIntArray116[];
    public int anIntArrayArray117[][];
    public int anIntArrayArray118[][];
    public byte aByteArrayArray119[][];
    public byte aByteArrayArray120[][];
    public byte aByteArrayArray121[][];
    public byte aByteArrayArray122[][];
    public MapFunctions mapFunctionsArray[];
    public MapScenes mapScenesArray[];
    public MapFont normalFont;
    public Sprite font11PT;
    public Sprite font12PT;
    public Sprite font14PT;
    public Sprite font17PT;
    public Sprite font19PT;
    public Sprite font22PT;
    public Sprite font26PT;
    public Sprite font30PT;
    public int anIntArray134[];
    public int anIntArray135[];
    public int anIntArray136[];
    public int anInt137;
    public int anIntArray138[];
    public int anIntArray139[];
    public int anIntArray140[];
    public int keyLocX;
    public int keyLocY;
    public int keyWidth;
    public int keyHeight;
    public int scrollPos;
    public int scrollTo;
    public boolean showKey;
    public int anInt148;
    public int anInt149;
    public int anInt150;
    public int flashCycle;
    public int overviewHeight;
    public int overviewWidth;
    public int overviewLocX;
    public int overviewLocY;
    public boolean showOverview;
    public MapFunctions worldOverviewSprite;
    public int anInt158;
    public int anInt159;
    public int anInt160;
    public int anInt161;
    public static boolean drawLabels = true;
    public int labelAmount;
    public int maxLabels;
    public String labelsArray[];
    public int labelPosXArray[];
    public int labelPosYArray[];
    public static int hashArray[] = {
        118, -62, 7, 24, 121, -123, -10, -8, 116, 6,
        -106, 28, -36, 86, 109, 112, -115, -116, -37, -35
    };
    public int labelSizeArray[];
    public double zoomTo;
    public double zoomLevel;
    public static int mapViewX;
    public static int mapViewY;
    public String iconExplainers[] = {
        "General Store", "Sword Shop", "Magic Shop", "Axe Shop",
        "Helmet Shop", "Bank", "Quest Start", "Amulet Shop",
        "Mining Site", "Furnace", "Anvil", "Combat Training",
        "Dungeon", "Staff Shop", "Platebody Shop", "Platelegs Shop",
        "Scimitar Shop", "Archery Shop", "Shield Shop", "Altar",
        "Herbalist", "Jewelery", "Gem Shop", "Crafting Shop",
        "Candle Shop", "Fishing Shop", "Fishing Spot", "Clothes Shop",
        "Apothecary", "Silk Trader", "Kebab Seller", "Pub/Bar",
        "Mace Shop", "Tannery", "Rare Trees", "Spinning Wheel",
        "Food Shop", "Cookery Shop", "Mini-Game", "Water Source",
        "Cooking Range", "Skirt Shop", "Potters Wheel", "Windmill",
        "Mining Shop", "Chainmail Shop", "Silver Shop", "Fur Trader",
        "Spice Shop", "Agility Training", "Vegetable Store", "Slayer Master",
        "Hair Dressers", "Farming patch", "Makeover Mage", "Guide",
        "Transportation", "???", "Farming shop", "Loom", "Brewery"
    };
    
    public Map() {
        brownBoxColour = 0x776644; //colour for brown unselected boxes
        redBoxColour = 0xaa0000; //red for selected top and left of boxes
        needToDraw = true;
        mapScenesArray = new MapScenes[100]; //
        mapFunctionsArray = new MapFunctions[100];

        anIntArray134 = new int[2000];
        anIntArray135 = new int[2000];
        anIntArray136 = new int[2000];
        anIntArray138 = new int[2000];
        anIntArray139 = new int[2000];
        anIntArray140 = new int[2000];

        showKey = false;
        keyLocX = 5; //key X
        keyLocY = 13; //key Y
        keyWidth = 140; //key width
        keyHeight = 470; //key heightt

        showOverview = false;
        anInt148 = -1;
        anInt149 = -1;
        anInt150 = -1;

        maxLabels = 1000;
        labelsArray = new String[maxLabels];
        labelPosXArray = new int[maxLabels];
        labelPosYArray = new int[maxLabels];
        labelSizeArray = new int[maxLabels];
        zoomTo = 4D;
        zoomLevel = 4D;
    }
    
    public static void main(String args[]) {
        Map mapMain = new Map();
        mapMain.createFrame(765, 503);
    }

    
    @Override
    public void startUp() {
        JagexArchive jagexArchive = readDAT();
        
        drawLoadingText("Loading size data", 9);
        Stream sizeData = new Stream(jagexArchive.getDataForName("size.dat"));
        mapStartX = sizeData.getShort();
        mapStartY = sizeData.getShort();
        mapWidth = sizeData.getShort();
        mapHeight = sizeData.getShort();
        mapViewX = 3200 - mapStartX;
        mapViewY = (mapStartY + mapHeight) - 3200;
        overviewHeight = 190;
        overviewWidth = (mapWidth * overviewHeight) / mapHeight;
        overviewLocX = 765 - overviewWidth - 5;
        overviewLocY = 503 - overviewHeight - 20;
        
        drawLoadingText("Loading label data", 18);
        Stream labelData = new Stream(jagexArchive.getDataForName("labels.dat"));
        labelAmount = labelData.getShort();
        for (int pos = 0; pos < labelAmount; pos++) {
            labelsArray[pos] = labelData.getString();
            labelPosXArray[pos] = labelData.getShort();
            labelPosYArray[pos] = labelData.getShort();
            labelSizeArray[pos] = labelData.getUnsignedByte();
        }
        
        drawLoadingText("Loading floor color data", 27);
        Stream floorColorData = new Stream(jagexArchive.getDataForName("floorcol.dat"));

        int colorAmount = floorColorData.getShort();
        anIntArray115 = new int[colorAmount + 1];
        anIntArray116 = new int[colorAmount + 1];
        for (int pos = 0; pos < colorAmount; pos++) {
            anIntArray115[pos + 1] = floorColorData.getInt();
            anIntArray116[pos + 1] = floorColorData.getInt();
        }
        
        drawLoadingText("Loading map underlay", 36);
        byte underlayData[] = jagexArchive.getDataForName("underlay.dat");
        byte mapSizeArray[][] = new byte[mapWidth][mapHeight];
        loadUnderlay(underlayData, mapSizeArray);
        anIntArrayArray117 = new int[mapWidth][mapHeight];
        method16(mapSizeArray, anIntArrayArray117);
        
        drawLoadingText("Loading map overlay", 45);
        byte overlayData[] = jagexArchive.getDataForName("overlay.dat");
        anIntArrayArray118 = new int[mapWidth][mapHeight];
        aByteArrayArray119 = new byte[mapWidth][mapHeight];
        loadOverlay(overlayData, anIntArrayArray118, aByteArrayArray119);
        
        drawLoadingText("Loading map loc", 54);
        byte locData[] = jagexArchive.getDataForName("loc.dat");
        aByteArrayArray120 = new byte[mapWidth][mapHeight];
        aByteArrayArray122 = new byte[mapWidth][mapHeight];
        aByteArrayArray121 = new byte[mapWidth][mapHeight];
        loadLoc(locData, aByteArrayArray120, aByteArrayArray122, aByteArrayArray121);
        
        drawLoadingText("Loading map scenes", 63);
        try {
            for (int sceneIndex = 0; sceneIndex < 100; sceneIndex++) {
                mapScenesArray[sceneIndex] = new MapScenes(jagexArchive, "mapscene", sceneIndex);
            }
        } catch (Exception ignored) {
        }
        
        drawLoadingText("Loading map functions", 72);
        try {
            for (int functionIndex = 0; functionIndex < 100; functionIndex++) {
                mapFunctionsArray[functionIndex] = new MapFunctions(jagexArchive, "mapfunction", functionIndex);
            }
        } catch (Exception ignored) {
        }
        
        drawLoadingText("Loading fonts", 81);
        normalFont = new MapFont(jagexArchive, "b12_full", false);
        font11PT = new Sprite(11, this);
        font12PT = new Sprite(12, this);
        font14PT = new Sprite(14, this);
        font17PT = new Sprite(17, this);
        font19PT = new Sprite(19, this);
        font22PT = new Sprite(22, this);
        font26PT = new Sprite(26, this);
        font30PT = new Sprite(30, this);
        
        drawLoadingText("Loading map functions", 90);
        worldOverviewSprite = new MapFunctions(overviewWidth, overviewHeight);
        worldOverviewSprite.method45();
        
        drawLoadingText("Rendering map", 100);
        drawMap(0, 0, mapWidth, mapHeight, 0, 0, overviewWidth, overviewHeight);
        DrawingArea.fillPixels(0, 0, overviewWidth, overviewHeight, 0);
        DrawingArea.fillPixels(1, 1, overviewWidth - 2, overviewHeight - 2, brownBoxColour);
        super.fullScreen.initDrawingArea();
    }
    
    public void loadLoc(byte abyte0[], byte abyte1[][], byte abyte2[][], byte abyte3[][]) {
        for (int i = 0; i < abyte0.length;) {
            int k = (abyte0[i++] & 0xff) * 64 - mapStartX;
            int l = (abyte0[i++] & 0xff) * 64 - mapStartY;
            if (k > 0 && l > 0 && k + 64 < mapWidth && l + 64 < mapHeight) {
                for (int i1 = 0; i1 < 64; i1++) {
                    byte abyte4[] = abyte1[i1 + k];
                    byte abyte5[] = abyte2[i1 + k];
                    byte abyte6[] = abyte3[i1 + k];
                    int l1 = mapHeight - l - 1;
                    for (int i2 = -64; i2 < 0; i2++) {
                        do {
                            int j = abyte0[i++] & 0xff;
                            if (j == 0) {
                                break;
                            }
                            if (j < 29) {
                                abyte4[l1] = (byte) j;
                            } else if (j < 160) {
                                abyte5[l1] = (byte) (j - 28);
                            } else {
                                abyte6[l1] = (byte) (j - 159);
                                anIntArray138[anInt137] = i1 + k;
                                anIntArray139[anInt137] = l1;
                                anIntArray140[anInt137] = j - 160;
                                anInt137++;
                            }
                        } while (true);
                        l1--;
                    }
                }
            } else {
                for (int j1 = 0; j1 < 64; j1++) {
                    for (int k1 = -64; k1 < 0; k1++) {
                        byte byte0;
                        do {
                            byte0 = abyte0[i++];
                        } while (byte0 != 0);
                    }
                }
            }
        }
    }

    public void loadUnderlay(byte abyte0[], byte abyte1[][]) {
        for (int i = 0; i < abyte0.length;) {
            int j = (abyte0[i++] & 0xff) * 64 - mapStartX;
            int k = (abyte0[i++] & 0xff) * 64 - mapStartY;
            if (j > 0 && k > 0 && j + 64 < mapWidth && k + 64 < mapHeight) {
                for (int l = 0; l < 64; l++) {
                    byte abyte2[] = abyte1[l + j];
                    int i1 = mapHeight - k - 1;
                    for (int j1 = -64; j1 < 0; j1++) {
                        abyte2[i1--] = abyte0[i++];
                    }
                }
            } else {
                i += 4096;
            }
        }
    }

    public void loadOverlay(byte abyte0[], int ai[][], byte abyte1[][]) {
        for (int i = 0; i < abyte0.length;) {
            int j = (abyte0[i++] & 0xff) * 64 - mapStartX;
            int k = (abyte0[i++] & 0xff) * 64 - mapStartY;
            if (j > 0 && k > 0 && j + 64 < mapWidth && k + 64 < mapHeight) {
                for (int l = 0; l < 64; l++) {
                    int ai1[] = ai[l + j];
                    byte abyte2[] = abyte1[l + j];
                    int j1 = mapHeight - k - 1;
                    for (int k1 = -64; k1 < 0; k1++) {
                        byte byte0 = abyte0[i++];
                        if (byte0 != 0) {
                            abyte2[j1] = abyte0[i++];
                            int l1 = 0;
                            if (byte0 > 0) {
                                l1 = anIntArray116[byte0];
                            }
                            ai1[j1--] = l1;
                        } else {
                            ai1[j1--] = 0;
                        }
                    }
                }
            } else {
                for (int i1 = -4096; i1 < 0; i1++) {
                    byte byte1 = abyte0[i++];
                    if (byte1 != 0) {
                        i++;
                    }
                }
            }
        }
    }

    public void method16(byte abyte0[][], int ai[][]) {
        int i = mapWidth;
        int j = mapHeight;
        int ai1[] = new int[j];
        for (int k = 0; k < j; k++) {
            ai1[k] = 0;
        }

        for (int l = 5; l < i - 5; l++) {
            byte abyte1[] = abyte0[l + 5];
            byte abyte2[] = abyte0[l - 5];
            for (int i1 = 0; i1 < j; i1++) {
                ai1[i1] += anIntArray115[abyte1[i1] & 0xff] - anIntArray115[abyte2[i1] & 0xff];
            }
            if (l > 10 && l < i - 10) {
                int j1 = 0;
                int k1 = 0;
                int l1 = 0;
                int ai2[] = ai[l];
                for (int i2 = 5; i2 < j - 5; i2++) {
                    int j2 = ai1[i2 - 5];
                    int k2 = ai1[i2 + 5];
                    j1 += (k2 >> 20) - (j2 >> 20);
                    k1 += (k2 >> 10 & 0x3ff) - (j2 >> 10 & 0x3ff);
                    l1 += (k2 & 0x3ff) - (j2 & 0x3ff);
                    if (l1 > 0) {
                        ai2[i2] = method17((double) j1 / 8533D, (double) k1 / 8533D, (double) l1 / 8533D);
                    }
                }
            }
        }
    }

    public int method17(double d, double d1, double d2) {
        double d3 = d2;
        double d4 = d2;
        double d5 = d2;
        if (d1 != 0.0D) {
            double d6;
            if (d2 < 0.5D) {
                d6 = d2 * (1.0D + d1);
            } else {
                d6 = (d2 + d1) - d2 * d1;
            }
            double d7 = 2D * d2 - d6;
            double d8 = d + 0.33333333333333331D;
            if (d8 > 1.0D) {
                d8--;
            }
            double d9 = d;
            double d10 = d - 0.33333333333333331D;
            if (d10 < 0.0D) {
                d10++;
            }
            if (6D * d8 < 1.0D) {
                d3 = d7 + (d6 - d7) * 6D * d8;
            } else if (2D * d8 < 1.0D) {
                d3 = d6;
            } else if (3D * d8 < 2D) {
                d3 = d7 + (d6 - d7) * (0.66666666666666663D - d8) * 6D;
            } else {
                d3 = d7;
            }
            if (6D * d9 < 1.0D) {
                d4 = d7 + (d6 - d7) * 6D * d9;
            } else if (2D * d9 < 1.0D) {
                d4 = d6;
            } else if (3D * d9 < 2D) {
                d4 = d7 + (d6 - d7) * (0.66666666666666663D - d9) * 6D;
            } else {
                d4 = d7;
            }
            if (6D * d10 < 1.0D) {
                d5 = d7 + (d6 - d7) * 6D * d10;
            } else if (2D * d10 < 1.0D) {
                d5 = d6;
            } else if (3D * d10 < 2D) {
                d5 = d7 + (d6 - d7) * (0.66666666666666663D - d10) * 6D;
            } else {
                d5 = d7;
            }
        }
        int i = (int) (d3 * 256D);
        int j = (int) (d4 * 256D);
        int k = (int) (d5 * 256D);
        int l = (i << 16) + (j << 8) + k;
        return l;
    }

    @Override
    public void cleanUpForQuit() {
        try {
            anIntArray115 = null;
            anIntArray116 = null;
            anIntArrayArray117 = null;
            anIntArrayArray118 = null;
            aByteArrayArray119 = null;
            aByteArrayArray120 = null;
            aByteArrayArray121 = null;
            aByteArrayArray122 = null;
            mapScenesArray = null;
            mapFunctionsArray = null;
            normalFont = null;
            anIntArray134 = null;
            anIntArray135 = null;
            anIntArray136 = null;
            anIntArray138 = null;
            anIntArray139 = null;
            anIntArray140 = null;
            worldOverviewSprite = null;
            labelsArray = null;
            labelPosXArray = null;
            labelPosYArray = null;
            labelSizeArray = null;
            iconExplainers = null;
            System.gc();
            return;
        } catch (Throwable _ex) {
            return;
        }
    }

    @Override
    public void processLoop() {
        
        if (super.keyStatus[1] == 1) {
            mapViewX = (int) ((double) mapViewX - 16D / zoomTo); //move left
            needToDraw = true;
        }
        if (super.keyStatus[2] == 1) {
            mapViewX = (int) ((double) mapViewX + 16D / zoomTo); //move right
            needToDraw = true;
        }
        if (super.keyStatus[3] == 1) {
            mapViewY = (int) ((double) mapViewY - 16D / zoomTo); //move up
            needToDraw = true;
        }
        if (super.keyStatus[4] == 1) {
            mapViewY = (int) ((double) mapViewY + 16D / zoomTo); //move down
            needToDraw = true;
        }


        for (int i = 1; i > 0;) {
            i = method7();
            if (i == 49) { //37%
                zoomLevel = 3D;
                needToDraw = true;
            }
            if (i == 50) { //50%
                zoomLevel = 4D;
                needToDraw = true;
            }
            if (i == 51) { //75%
                zoomLevel = 6D;
                needToDraw = true;
            }
            if (i == 52) { //100%
                zoomLevel = 8D;
                needToDraw = true;
            }

            if (i == 107 || i == 75) { //the buttons for show key
                showKey = !showKey;
                needToDraw = true;
            }
            if (i == 111 || i == 79) { //the buttons for showing the overview
                showOverview = !showOverview;
                needToDraw = true;
            }
        }

        if (super.mouseButtonPressed == 1) {
            anInt158 = super.clickX;
            anInt159 = super.clickY;
            anInt160 = mapViewX;
            anInt161 = mapViewY;


            if (super.clickX > 660 && super.clickX < 760 && super.clickY > 5 && super.clickY < 30) {
                this.rsFrame.setVisible(false);
            }


            if (super.clickX > 170 && super.clickX < 220 && super.clickY > 471 && super.clickY < 503) {
                zoomLevel = 3D;
                anInt158 = -1;
            }
            if (super.clickX > 230 && super.clickX < 280 && super.clickY > 471 && super.clickY < 503) {
                zoomLevel = 4D;
                anInt158 = -1;
            }
            if (super.clickX > 290 && super.clickX < 340 && super.clickY > 471 && super.clickY < 503) {
                zoomLevel = 6D;
                anInt158 = -1;
            }
            if (super.clickX > 350 && super.clickX < 400 && super.clickY > 471 && super.clickY < 503) {
                zoomLevel = 8D;
                anInt158 = -1;
            }

            if (super.clickX > 410 && super.clickX < 460 && super.clickY > 471 && super.clickY < 503) {
                zoomLevel = 16D;
                anInt158 = -1;
            }
            if (super.clickX > keyLocX && super.clickY > keyLocY + keyHeight && super.clickX < keyLocX + keyWidth && super.clickY < 503) {
                showKey = !showKey;
                anInt158 = -1;
            }
            if (super.clickX > overviewLocX && super.clickY > overviewLocY + overviewHeight && super.clickX < overviewLocX + overviewWidth && super.clickY < 503) {
                showOverview = !showOverview;
                anInt158 = -1;
            }

            if (showKey) {
                if (super.clickX > keyLocX && super.clickY > keyLocY && super.clickX < keyLocX + keyWidth && super.clickY < keyLocY + keyHeight) {
                    anInt158 = -1;
                }
                if (super.clickX > keyLocX && super.clickY > keyLocY && super.clickX < keyLocX + keyWidth && super.clickY < keyLocY + 18 && scrollTo > 0) {
                    scrollTo -= 25;
                }
                if (super.clickX > keyLocX && super.clickY > (keyLocY + keyHeight) - 18 && super.clickX < keyLocX + keyWidth && super.clickY < keyLocY + keyHeight && scrollTo < 50) {
                    scrollTo += 25;
                }
            }

            needToDraw = true;
        }

        if (showKey) {
            anInt148 = -1;
            if (super.mouseEventX > keyLocX && super.mouseEventX < keyLocX + keyWidth) {
                int j = keyLocY + 21 + 5;
                for (int j1 = 0; j1 < 25; j1++) {
                    if (j1 + scrollPos >= iconExplainers.length || !iconExplainers[j1 + scrollPos].equals("???")) {

                        if (super.mouseEventY >= j && super.mouseEventY < j + 17) { //clicked link
                            anInt148 = j1 + scrollPos;
                            if (super.mouseButtonPressed == 1) {
                                anInt150 = j1 + scrollPos;
                                flashCycle = 50;
                            }
                        }

                        j += 17;
                    }
                }

            }
            if (anInt148 != anInt149) {
                anInt149 = anInt148;
                needToDraw = true;
            }
        }

        if ((super.mouseButtonDown == 1 || super.mouseButtonPressed == 1) && showOverview) {
            int k = super.clickX;
            int k1 = super.clickY;
            if (super.mouseButtonDown == 1) {
                k = super.mouseEventX;
                k1 = super.mouseEventY;
            }
            if (k > overviewLocX && k1 > overviewLocY && k < overviewLocX + overviewWidth && k1 < overviewLocY + overviewHeight) {
                mapViewX = ((k - overviewLocX) * mapWidth) / overviewWidth;
                mapViewY = ((k1 - overviewLocY) * mapHeight) / overviewHeight;
                anInt158 = -1;
                needToDraw = true;
            }
        }
        if (super.mouseButtonDown == 1 && anInt158 != -1) {
            mapViewX = anInt160 + (int) (((double) (anInt158 - super.mouseEventX) * 2D) / zoomLevel);
            mapViewY = anInt161 + (int) (((double) (anInt159 - super.mouseEventY) * 2D) / zoomLevel);



            ///mapViewX = 3200 - mapStartX; //starting position? X
            ///mapViewY = (mapStartY + mapHeight) - 3200; //starting pos Y

            ///System.out.println("ClickX " + (mapViewX+2112));
            ///System.out.println("ClickY " + (mapViewY+2368));


            needToDraw = true;
        }


        if (zoomTo < zoomLevel) { //zoom in
            needToDraw = true;
            zoomTo += zoomTo / 30D; //speed
            if (zoomTo > zoomLevel) {
                zoomTo = zoomLevel;
            }
        }

        if (zoomTo > zoomLevel) { //zoom out
            needToDraw = true;
            zoomTo -= zoomTo / 30D; //speed
            if (zoomTo < zoomLevel) {
                zoomTo = zoomLevel;
            }
        }


        if (scrollPos < scrollTo) {
            needToDraw = true;
            scrollPos++;
        }
        if (scrollPos > scrollTo) {
            needToDraw = true;
            scrollPos--;
        }
        if (flashCycle > 0) {
            needToDraw = true;
            flashCycle--;
        }

        int l = mapViewX - (int) (765D / zoomTo);
        int l1 = mapViewY - (int) (503D / zoomTo);
        int i2 = mapViewX + (int) (765D / zoomTo);
        int k2 = mapViewY + (int) (503D / zoomTo);

        if (l < 48) {
            mapViewX = 48 + (int) (765D / zoomTo);
        }
        if (l1 < 48) {
            mapViewY = 48 + (int) (503D / zoomTo);
        }
        if (i2 > mapWidth - 48) {
            mapViewX = mapWidth - 48 - (int) (765D / zoomTo);
        }
        if (k2 > mapHeight - 48) {
            mapViewY = mapHeight - 48 - (int) (503D / zoomTo);
        }
    }

    @Override
    public void processDrawing() {
        if (needToDraw) {
            needToDraw = false;
            DrawingArea.setAllPixelsToZero();

            int i = mapViewX - (int) (765D / zoomTo);
            int j = mapViewY - (int) (503D / zoomTo);
            int k = mapViewX + (int) (765D / zoomTo);
            int l = mapViewY + (int) (503D / zoomTo);

            drawMap(i, j, k, l, 0, 0, 765, 503);

            if (showOverview) {
                //draw bg
                worldOverviewSprite.method48(overviewLocX, overviewLocY);
                //draw box
                DrawingArea.method30(overviewLocX + (overviewWidth * i) / mapWidth, overviewLocY + (overviewHeight * j) / mapHeight, ((k - i) * overviewWidth) / mapWidth, ((l - j) * overviewHeight) / mapHeight, 0xff0000, 128);
                //drawfill
                DrawingArea.fillPixels(overviewLocX + (overviewWidth * i) / mapWidth, overviewLocY + (overviewHeight * j) / mapHeight, ((k - i) * overviewWidth) / mapWidth, ((l - j) * overviewHeight) / mapHeight, 0xff0000);
                if (flashCycle > 0 && flashCycle % 10 < 5) { //flashing things on world map
                    for (int i1 = 0; i1 < anInt137; i1++) {
                        if (anIntArray140[i1] == anInt150) {
                            int k1 = overviewLocX + (overviewWidth * anIntArray138[i1]) / mapWidth;
                            int i2 = overviewLocY + (overviewHeight * anIntArray139[i1]) / mapHeight;
                            DrawingArea.drawCircle(k1, i2, 2, 0xffff00, 256);
                        }
                    }
                }
            }

            if (showKey) {
                drawBoxWithText(keyLocX, keyLocY, keyWidth, 18, 0x999999, 0x777777, 0x555555, "Page Up");
                drawBoxWithText(keyLocX, keyLocY + 18, keyWidth, keyHeight - 36, 0x999999, 0x777777, 0x555555, "");
                drawBoxWithText(keyLocX, (keyLocY + keyHeight) - 18, keyWidth, 18, 0x999999, 0x777777, 0x555555, "Page Down");
                int j1 = keyLocY + 3 + 18;
                for (int l1 = 0; l1 < 25; l1++) { //per page
                    if (l1 + scrollPos < mapFunctionsArray.length && l1 + scrollPos < iconExplainers.length) {
                        if (iconExplainers[l1 + scrollPos].equals("???")) {
                            continue;
                        }
                        mapFunctionsArray[l1 + scrollPos].method46(keyLocX + 3, j1);
                        normalFont.method53(iconExplainers[l1 + scrollPos], keyLocX + 21, j1 + 14, 0);
                        int j2 = 0xffffff;
                        if (anInt148 == l1 + scrollPos) {
                            j2 = 0xbbaaaa;
                        }
                        if (flashCycle > 0 && flashCycle % 10 < 5 && anInt150 == l1 + scrollPos) {
                            j2 = 0xffff00;
                        }
                        normalFont.method53(iconExplainers[l1 + scrollPos], keyLocX + 20, j1 + 13, j2);
                    }
                    j1 += 17;
                }

            }

            drawBoxWithText(660, 5, 100, 25, brownBoxColour, brownBoxColour, brownBoxColour, "Hide Map");


            drawBoxWithText(overviewLocX, overviewLocY + overviewHeight, overviewWidth, 18, brownBoxColour, brownBoxColour, brownBoxColour, "Overview");
            drawBoxWithText(keyLocX, keyLocY + keyHeight, keyWidth, 18, brownBoxColour, brownBoxColour, brownBoxColour, "Key");

            if (zoomLevel == 3D) {
                drawBoxWithText(170, 471, 50, 30, redBoxColour, redBoxColour, redBoxColour, "37%");
            } else {
                drawBoxWithText(170, 471, 50, 30, brownBoxColour, brownBoxColour, brownBoxColour, "37%");
            }
            if (zoomLevel == 4D) {
                drawBoxWithText(230, 471, 50, 30, redBoxColour, redBoxColour, redBoxColour, "50%");
            } else {
                drawBoxWithText(230, 471, 50, 30, brownBoxColour, brownBoxColour, brownBoxColour, "50%");
            }
            if (zoomLevel == 6D) {
                drawBoxWithText(290, 471, 50, 30, redBoxColour, redBoxColour, redBoxColour, "75%");
            } else {
                drawBoxWithText(290, 471, 50, 30, brownBoxColour, brownBoxColour, brownBoxColour, "75%");
            }

            if (zoomLevel == 8D) {
                drawBoxWithText(350, 471, 50, 30, redBoxColour, redBoxColour, redBoxColour, "100%");
            } else {
                drawBoxWithText(350, 471, 50, 30, brownBoxColour, brownBoxColour, brownBoxColour, "100%");
            }

            if (zoomLevel == 16D) {
                drawBoxWithText(410, 471, 50, 30, redBoxColour, redBoxColour, redBoxColour, "200%");
            } else {
                drawBoxWithText(410, 471, 50, 30, brownBoxColour, brownBoxColour, brownBoxColour, "200%");
            }

        }
        super.fullScreen.drawGraphics(super.graphics, 0, 0);
    }

    public void drawBoxWithText(int posX, int posY, int widthX, int widthY, int i1, int j1, int k1, String text) {
        DrawingArea.fillPixels(posX, posY, widthX, widthY, 0);
        posX++;
        posY++;
        widthX -= 2;
        widthY -= 2;
        DrawingArea.method33(posX, posY, widthX, widthY, j1);
        DrawingArea.method27(posX, posY, widthX, i1);
        DrawingArea.method28(posX, posY, widthY, i1);
        DrawingArea.method27(posX, (posY + widthY) - 1, widthX, k1);
        DrawingArea.method28((posX + widthX) - 1, posY, widthY, k1);
        normalFont.drawText(text, posX + widthX / 2 + 1, posY + widthY / 2 + 1 + 4, 0);
        normalFont.drawText(text, posX + widthX / 2, posY + widthY / 2 + 4, 0xffffff);
    }

    public void drawMap(int i, int j, int k, int l, int i1, int j1, int k1, int l1) {
        int i2 = k - i;
        int j2 = l - j;
        int k2 = (k1 - i1 << 16) / i2;
        int l2 = (l1 - j1 << 16) / j2;
        for (int i3 = 0; i3 < i2; i3++) {
            int j3 = k2 * i3 >> 16;
            int l3 = k2 * (i3 + 1) >> 16;
            int j4 = l3 - j3;
            if (j4 > 0) {
                j3 += i1;
                l3 += i1;
                int ai[] = anIntArrayArray117[i3 + i];
                int ai1[] = anIntArrayArray118[i3 + i];
                byte abyte0[] = aByteArrayArray119[i3 + i];
                for (int j7 = 0; j7 < j2; j7++) {
                    int i8 = l2 * j7 >> 16;
                    int l8 = l2 * (j7 + 1) >> 16;
                    int l9 = l8 - i8;
                    if (l9 > 0) {
                        i8 += j1;
                        l8 += j1;
                        int l10 = ai1[j7 + j];
                        if (l10 == 0) {
                            DrawingArea.method33(j3, i8, l3 - j3, l8 - i8, ai[j7 + j]);
                        } else {
                            byte byte0 = abyte0[j7 + j];
                            int l11 = byte0 & 0xfc;
                            if (l11 == 0 || j4 <= 1 || l9 <= 1) {
                                DrawingArea.method33(j3, i8, j4, l9, l10);
                            } else {
                                method20(DrawingArea.pixels, i8 * DrawingArea.width + j3, ai[j7 + j], l10, j4, l9, l11 >> 2, byte0 & 3);
                            }
                        }
                    }
                }
            }
        }

        if (k - i > k1 - i1) {
            return;
        }
        int k3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            int k4 = k2 * i4 >> 16;
            int i5 = k2 * (i4 + 1) >> 16;
            int i6 = i5 - k4;
            if (i6 > 0) {
                byte abyte1[] = aByteArrayArray120[i4 + i];
                byte abyte2[] = aByteArrayArray122[i4 + i];
                byte abyte3[] = aByteArrayArray121[i4 + i];
                for (int i9 = 0; i9 < j2; i9++) {
                    int i10 = l2 * i9 >> 16;
                    int i11 = l2 * (i9 + 1) >> 16;
                    int k11 = i11 - i10;
                    if (k11 > 0) {
                        int i12 = abyte1[i9 + j] & 0xff;
                        if (i12 != 0) {
                            int k12;
                            if (i6 == 1) {
                                k12 = k4;
                            } else {
                                k12 = i5 - 1;
                            }
                            int j13;
                            if (k11 == 1) {
                                j13 = i10;
                            } else {
                                j13 = i11 - 1;
                            }
                            int i14 = 0xcccccc;
                            if (i12 >= 5 && i12 <= 8 || i12 >= 13 && i12 <= 16 || i12 >= 21 && i12 <= 24 || i12 == 27 || i12 == 28) {
                                i14 = 0xcc0000;
                                i12 -= 4;
                            }
                            if (i12 == 1) {
                                DrawingArea.method28(k4, i10, k11, i14);
                            } else if (i12 == 2) {
                                DrawingArea.method27(k4, i10, i6, i14);
                            } else if (i12 == 3) {
                                DrawingArea.method28(k12, i10, k11, i14);
                            } else if (i12 == 4) {
                                DrawingArea.method27(k4, j13, i6, i14);
                            } else if (i12 == 9) {
                                DrawingArea.method28(k4, i10, k11, 0xffffff);
                                DrawingArea.method27(k4, i10, i6, i14);
                            } else if (i12 == 10) {
                                DrawingArea.method28(k12, i10, k11, 0xffffff);
                                DrawingArea.method27(k4, i10, i6, i14);
                            } else if (i12 == 11) {
                                DrawingArea.method28(k12, i10, k11, 0xffffff);
                                DrawingArea.method27(k4, j13, i6, i14);
                            } else if (i12 == 12) {
                                DrawingArea.method28(k4, i10, k11, 0xffffff);
                                DrawingArea.method27(k4, j13, i6, i14);
                            } else if (i12 == 17) {
                                DrawingArea.method27(k4, i10, 1, i14);
                            } else if (i12 == 18) {
                                DrawingArea.method27(k12, i10, 1, i14);
                            } else if (i12 == 19) {
                                DrawingArea.method27(k12, j13, 1, i14);
                            } else if (i12 == 20) {
                                DrawingArea.method27(k4, j13, 1, i14);
                            } else if (i12 == 25) {
                                for (int j14 = 0; j14 < k11; j14++) {
                                    DrawingArea.method27(k4 + j14, j13 - j14, 1, i14);
                                }

                            } else if (i12 == 26) {
                                for (int k14 = 0; k14 < k11; k14++) {
                                    DrawingArea.method27(k4 + k14, i10 + k14, 1, i14);
                                }

                            }
                        }
                        int l12 = abyte2[i9 + j] & 0xff;
                        if (l12 != 0) {
                            mapScenesArray[l12 - 1].method50(k4 - i6 / 2, i10 - k11 / 2, i6 * 2, k11 * 2);
                        }
                        int k13 = abyte3[i9 + j] & 0xff;
                        if (k13 != 0) {
                            anIntArray136[k3] = k13 - 1;
                            anIntArray134[k3] = k4 + i6 / 2;
                            anIntArray135[k3] = i10 + k11 / 2;
                            k3++;
                        }
                    }
                }

            }
        }

        for (int l4 = 0; l4 < k3; l4++) {
            if (mapFunctionsArray[anIntArray136[l4]] != null) {
                mapFunctionsArray[anIntArray136[l4]].method46(anIntArray134[l4] - 7, anIntArray135[l4] - 7);
            }
        }

        if (flashCycle > 0) {
            for (int j5 = 0; j5 < k3; j5++) {
                if (anIntArray136[j5] == anInt150) {
                    mapFunctionsArray[anIntArray136[j5]].method46(anIntArray134[j5] - 7, anIntArray135[j5] - 7);
                    if (flashCycle % 10 < 5) {
                        DrawingArea.drawCircle(anIntArray134[j5], anIntArray135[j5], 15, 0xffff00, 128); //yellow flash
                        DrawingArea.drawCircle(anIntArray134[j5], anIntArray135[j5], 7, 0xffffff, 256); //white flash
                    }
                }
            }

        }
        if (zoomTo == zoomLevel && drawLabels) {
            for (int pos = 0; pos < labelAmount; pos++) {
                int labelPosX = labelPosXArray[pos];
                int labelPosY = labelPosYArray[pos];
                labelPosX -= mapStartX;
                labelPosY = (mapStartY + mapHeight) - labelPosY;
                int k7 = i1 + ((k1 - i1) * (labelPosX - i)) / (k - i);
                int j8 = j1 + ((l1 - j1) * (labelPosY - j)) / (l - j);
                int labelSize = labelSizeArray[pos];
                int labelColor = 0xffffff;
                Sprite fontToDraw = null;

                if (labelSize == 0) {
                    if (zoomTo == 3D) {
                        fontToDraw = font11PT;
                    }
                    if (zoomTo == 4D) {
                        fontToDraw = font12PT;
                    }
                    if (zoomTo == 6D) {
                        fontToDraw = font14PT;
                    }
                    if (zoomTo == 8D) {
                        fontToDraw = font17PT;
                    }
                    if (zoomTo == 16D) {
                        fontToDraw = font17PT;
                    }
                }
                if (labelSize == 1) {
                    if (zoomTo == 3D) {
                        fontToDraw = font14PT;
                    }
                    if (zoomTo == 4D) {
                        fontToDraw = font17PT;
                    }
                    if (zoomTo == 6D) {
                        fontToDraw = font19PT;
                    }
                    if (zoomTo == 8D) {
                        fontToDraw = font22PT;
                    }
                    if (zoomTo == 16D) {
                        fontToDraw = font22PT;
                    }
                }
                if (labelSize == 2) {
                    labelColor = 0xffaa00;
                    if (zoomTo == 3D) {
                        fontToDraw = font19PT;
                    }
                    if (zoomTo == 4D) {
                        fontToDraw = font22PT;
                    }
                    if (zoomTo == 6D) {
                        fontToDraw = font26PT;
                    }
                    if (zoomTo == 8D) {
                        fontToDraw = font30PT;
                    }
                    if (zoomTo == 16D) {
                        fontToDraw = font30PT;
                    }
                }


                if (fontToDraw != null) {
                    String s = labelsArray[pos];
                    int j12 = 1;
                    for (int i13 = 0; i13 < s.length(); i13++) {
                        if (s.charAt(i13) == '/') {
                            j12++;
                        }
                    }

                    j8 -= (fontToDraw.method40() * (j12 - 1)) / 2;
                    j8 += fontToDraw.method44() / 2;
                    do {
                        int l13 = s.indexOf("/");
                        if (l13 == -1) {
                            fontToDraw.method38(s, k7, j8, labelColor, true);
                            break;
                        }
                        String s1 = s.substring(0, l13);
                        fontToDraw.method38(s1, k7, j8, labelColor, true);
                        j8 += fontToDraw.method40();
                        s = s.substring(l13 + 1);
                    } while (true);
                }
            }

        }

        if (showMapAreas) {
            for (int blockX = mapStartX / 64; blockX < (mapStartX + mapWidth) / 64; blockX++) {
                for (int blockY = mapStartY / 64; blockY < (mapStartY + mapHeight) / 64; blockY++) {
                    int i7 = blockX * 64;
                    int l7 = blockY * 64;
                    i7 -= mapStartX;
                    l7 = (mapStartY + mapHeight) - l7;
                    int k8 = i1 + ((k1 - i1) * (i7 - i)) / (k - i);
                    int k9 = j1 + ((l1 - j1) * (l7 - 64 - j)) / (l - j);
                    int k10 = i1 + ((k1 - i1) * ((i7 + 64) - i)) / (k - i);
                    int j11 = j1 + ((l1 - j1) * (l7 - j)) / (l - j);
                    DrawingArea.fillPixels(k8, k9, k10 - k8, j11 - k9, 0xffffff);
                    normalFont.method54(blockX + "_" + blockY, k10 - 5, j11 - 5, 0xffffff);
                    if (blockX == 33 && blockY >= 71 && blockY <= 73) //suggests we can load more map??
                    {
                        normalFont.drawText("u_pass", (k10 + k8) / 2, (j11 + k9) / 2, 0xff0000);
                    }
                }
            }
        }


    }

    public void method20(int ai[], int i, int j, int k, int l, int i1, int j1,
            int k1) {
        int l1 = DrawingArea.width - l;
        if (j1 == 9) {
            j1 = 1;
            k1 = k1 + 1 & 3;
        }
        if (j1 == 10) {
            j1 = 1;
            k1 = k1 + 3 & 3;
        }
        if (j1 == 11) {
            j1 = 8;
            k1 = k1 + 3 & 3;
        }
        if (j1 == 1) {
            if (k1 == 0) {
                for (int i2 = 0; i2 < i1; i2++) {
                    for (int i10 = 0; i10 < l; i10++) {
                        if (i10 <= i2) {
                            ai[i++] = k;
                        } else {
                            ai[i++] = j;
                        }
                    }

                    i += l1;
                }

                return;
            }
            if (k1 == 1) {
                for (int j2 = i1 - 1; j2 >= 0; j2--) {
                    for (int j10 = 0; j10 < l; j10++) {
                        if (j10 <= j2) {
                            ai[i++] = k;
                        } else {
                            ai[i++] = j;
                        }
                    }

                    i += l1;
                }

                return;
            }
            if (k1 == 2) {
                for (int k2 = 0; k2 < i1; k2++) {
                    for (int k10 = 0; k10 < l; k10++) {
                        if (k10 >= k2) {
                            ai[i++] = k;
                        } else {
                            ai[i++] = j;
                        }
                    }

                    i += l1;
                }

                return;
            }
            if (k1 == 3) {
                for (int l2 = i1 - 1; l2 >= 0; l2--) {
                    for (int l10 = 0; l10 < l; l10++) {
                        if (l10 >= l2) {
                            ai[i++] = k;
                        } else {
                            ai[i++] = j;
                        }
                    }

                    i += l1;
                }

                return;
            } else {
                return;
            }
        }
        if (j1 == 2) {
            if (k1 == 0) {
                for (int i3 = i1 - 1; i3 >= 0; i3--) {
                    for (int i11 = 0; i11 < l; i11++) {
                        if (i11 <= i3 >> 1) {
                            ai[i++] = k;
                        } else {
                            ai[i++] = j;
                        }
                    }

                    i += l1;
                }

                return;
            }
            if (k1 == 1) {
                for (int j3 = 0; j3 < i1; j3++) {
                    for (int j11 = 0; j11 < l; j11++) {
                        if (j11 >= j3 << 1) {
                            ai[i++] = k;
                        } else {
                            ai[i++] = j;
                        }
                    }

                    i += l1;
                }

                return;
            }
            if (k1 == 2) {
                for (int k3 = 0; k3 < i1; k3++) {
                    for (int k11 = l - 1; k11 >= 0; k11--) {
                        if (k11 <= k3 >> 1) {
                            ai[i++] = k;
                        } else {
                            ai[i++] = j;
                        }
                    }

                    i += l1;
                }

                return;
            }
            if (k1 == 3) {
                for (int l3 = i1 - 1; l3 >= 0; l3--) {
                    for (int l11 = l - 1; l11 >= 0; l11--) {
                        if (l11 >= l3 << 1) {
                            ai[i++] = k;
                        } else {
                            ai[i++] = j;
                        }
                    }

                    i += l1;
                }

                return;
            } else {
                return;
            }
        }
        if (j1 == 3) {
            if (k1 == 0) {
                for (int i4 = i1 - 1; i4 >= 0; i4--) {
                    for (int i12 = l - 1; i12 >= 0; i12--) {
                        if (i12 <= i4 >> 1) {
                            ai[i++] = k;
                        } else {
                            ai[i++] = j;
                        }
                    }

                    i += l1;
                }

                return;
            }
            if (k1 == 1) {
                for (int j4 = i1 - 1; j4 >= 0; j4--) {
                    for (int j12 = 0; j12 < l; j12++) {
                        if (j12 >= j4 << 1) {
                            ai[i++] = k;
                        } else {
                            ai[i++] = j;
                        }
                    }

                    i += l1;
                }

                return;
            }
            if (k1 == 2) {
                for (int k4 = 0; k4 < i1; k4++) {
                    for (int k12 = 0; k12 < l; k12++) {
                        if (k12 <= k4 >> 1) {
                            ai[i++] = k;
                        } else {
                            ai[i++] = j;
                        }
                    }

                    i += l1;
                }

                return;
            }
            if (k1 == 3) {
                for (int l4 = 0; l4 < i1; l4++) {
                    for (int l12 = l - 1; l12 >= 0; l12--) {
                        if (l12 >= l4 << 1) {
                            ai[i++] = k;
                        } else {
                            ai[i++] = j;
                        }
                    }

                    i += l1;
                }

                return;
            } else {
                return;
            }
        }
        if (j1 == 4) {
            if (k1 == 0) {
                for (int i5 = i1 - 1; i5 >= 0; i5--) {
                    for (int i13 = 0; i13 < l; i13++) {
                        if (i13 >= i5 >> 1) {
                            ai[i++] = k;
                        } else {
                            ai[i++] = j;
                        }
                    }

                    i += l1;
                }

                return;
            }
            if (k1 == 1) {
                for (int j5 = 0; j5 < i1; j5++) {
                    for (int j13 = 0; j13 < l; j13++) {
                        if (j13 <= j5 << 1) {
                            ai[i++] = k;
                        } else {
                            ai[i++] = j;
                        }
                    }

                    i += l1;
                }

                return;
            }
            if (k1 == 2) {
                for (int k5 = 0; k5 < i1; k5++) {
                    for (int k13 = l - 1; k13 >= 0; k13--) {
                        if (k13 >= k5 >> 1) {
                            ai[i++] = k;
                        } else {
                            ai[i++] = j;
                        }
                    }

                    i += l1;
                }

                return;
            }
            if (k1 == 3) {
                for (int l5 = i1 - 1; l5 >= 0; l5--) {
                    for (int l13 = l - 1; l13 >= 0; l13--) {
                        if (l13 <= l5 << 1) {
                            ai[i++] = k;
                        } else {
                            ai[i++] = j;
                        }
                    }

                    i += l1;
                }

                return;
            } else {
                return;
            }
        }
        if (j1 == 5) {
            if (k1 == 0) {
                for (int i6 = i1 - 1; i6 >= 0; i6--) {
                    for (int i14 = l - 1; i14 >= 0; i14--) {
                        if (i14 >= i6 >> 1) {
                            ai[i++] = k;
                        } else {
                            ai[i++] = j;
                        }
                    }

                    i += l1;
                }

                return;
            }
            if (k1 == 1) {
                for (int j6 = i1 - 1; j6 >= 0; j6--) {
                    for (int j14 = 0; j14 < l; j14++) {
                        if (j14 <= j6 << 1) {
                            ai[i++] = k;
                        } else {
                            ai[i++] = j;
                        }
                    }

                    i += l1;
                }

                return;
            }
            if (k1 == 2) {
                for (int k6 = 0; k6 < i1; k6++) {
                    for (int k14 = 0; k14 < l; k14++) {
                        if (k14 >= k6 >> 1) {
                            ai[i++] = k;
                        } else {
                            ai[i++] = j;
                        }
                    }

                    i += l1;
                }

                return;
            }
            if (k1 == 3) {
                for (int l6 = 0; l6 < i1; l6++) {
                    for (int l14 = l - 1; l14 >= 0; l14--) {
                        if (l14 <= l6 << 1) {
                            ai[i++] = k;
                        } else {
                            ai[i++] = j;
                        }
                    }

                    i += l1;
                }

                return;
            } else {
                return;
            }
        }
        if (j1 == 6) {
            if (k1 == 0) {
                for (int i7 = 0; i7 < i1; i7++) {
                    for (int i15 = 0; i15 < l; i15++) {
                        if (i15 <= l / 2) {
                            ai[i++] = k;
                        } else {
                            ai[i++] = j;
                        }
                    }

                    i += l1;
                }

                return;
            }
            if (k1 == 1) {
                for (int j7 = 0; j7 < i1; j7++) {
                    for (int j15 = 0; j15 < l; j15++) {
                        if (j7 <= i1 / 2) {
                            ai[i++] = k;
                        } else {
                            ai[i++] = j;
                        }
                    }

                    i += l1;
                }

                return;
            }
            if (k1 == 2) {
                for (int k7 = 0; k7 < i1; k7++) {
                    for (int k15 = 0; k15 < l; k15++) {
                        if (k15 >= l / 2) {
                            ai[i++] = k;
                        } else {
                            ai[i++] = j;
                        }
                    }

                    i += l1;
                }

                return;
            }
            if (k1 == 3) {
                for (int l7 = 0; l7 < i1; l7++) {
                    for (int l15 = 0; l15 < l; l15++) {
                        if (l7 >= i1 / 2) {
                            ai[i++] = k;
                        } else {
                            ai[i++] = j;
                        }
                    }

                    i += l1;
                }

                return;
            }
        }
        if (j1 == 7) {
            if (k1 == 0) {
                for (int i8 = 0; i8 < i1; i8++) {
                    for (int i16 = 0; i16 < l; i16++) {
                        if (i16 <= i8 - i1 / 2) {
                            ai[i++] = k;
                        } else {
                            ai[i++] = j;
                        }
                    }

                    i += l1;
                }

                return;
            }
            if (k1 == 1) {
                for (int j8 = i1 - 1; j8 >= 0; j8--) {
                    for (int j16 = 0; j16 < l; j16++) {
                        if (j16 <= j8 - i1 / 2) {
                            ai[i++] = k;
                        } else {
                            ai[i++] = j;
                        }
                    }

                    i += l1;
                }

                return;
            }
            if (k1 == 2) {
                for (int k8 = i1 - 1; k8 >= 0; k8--) {
                    for (int k16 = l - 1; k16 >= 0; k16--) {
                        if (k16 <= k8 - i1 / 2) {
                            ai[i++] = k;
                        } else {
                            ai[i++] = j;
                        }
                    }

                    i += l1;
                }

                return;
            }
            if (k1 == 3) {
                for (int l8 = 0; l8 < i1; l8++) {
                    for (int l16 = l - 1; l16 >= 0; l16--) {
                        if (l16 <= l8 - i1 / 2) {
                            ai[i++] = k;
                        } else {
                            ai[i++] = j;
                        }
                    }

                    i += l1;
                }

                return;
            }
        }
        if (j1 == 8) {
            if (k1 == 0) {
                for (int i9 = 0; i9 < i1; i9++) {
                    for (int i17 = 0; i17 < l; i17++) {
                        if (i17 >= i9 - i1 / 2) {
                            ai[i++] = k;
                        } else {
                            ai[i++] = j;
                        }
                    }

                    i += l1;
                }

                return;
            }
            if (k1 == 1) {
                for (int j9 = i1 - 1; j9 >= 0; j9--) {
                    for (int j17 = 0; j17 < l; j17++) {
                        if (j17 >= j9 - i1 / 2) {
                            ai[i++] = k;
                        } else {
                            ai[i++] = j;
                        }
                    }

                    i += l1;
                }

                return;
            }
            if (k1 == 2) {
                for (int k9 = i1 - 1; k9 >= 0; k9--) {
                    for (int k17 = l - 1; k17 >= 0; k17--) {
                        if (k17 >= k9 - i1 / 2) {
                            ai[i++] = k;
                        } else {
                            ai[i++] = j;
                        }
                    }

                    i += l1;
                }

                return;
            }
            if (k1 == 3) {
                for (int l9 = 0; l9 < i1; l9++) {
                    for (int l17 = l - 1; l17 >= 0; l17--) {
                        if (l17 >= l9 - i1 / 2) {
                            ai[i++] = k;
                        } else {
                            ai[i++] = j;
                        }
                    }

                    i += l1;
                }

            }
        }
    }

    public JagexArchive readDAT() {
        byte datFile[] = null;
        try {
            datFile = client.custom.FileOperations.ReadFile(client.sign.Signlink.findCacheDIR() + "worldmap.dat");
            return new JagexArchive(datFile);
        } catch (Throwable _ex) {
        }
        datFile = getMap();
        return new JagexArchive(datFile);
    }

    public byte[] getMap() {
        drawLoadingText("Requesting map", 0);
        try {
            DataInputStream mapDatFile;
            mapDatFile = new DataInputStream(new FileInputStream(client.sign.Signlink.findCacheDIR() + "worldmap.dat"));
            int amountLoaded = 0;
            int mapSize = 0x53901;
            byte loadedMap[] = new byte[mapSize];
            while (amountLoaded < mapSize) {
                int amountLeft = mapSize - amountLoaded;
                if (amountLeft > 1000) {
                    amountLeft = 1000;
                }
                int read = mapDatFile.read(loadedMap, amountLoaded, amountLeft);
                if (read < 0) {
                    throw new IOException("End of file");
                }
                int percentLoaded = (amountLoaded * 100) / mapSize;
                if (percentLoaded != 0) {
                    drawLoadingText("Loading map data - " + percentLoaded + "%", percentLoaded);
                }
                amountLoaded += read;
            }
            mapDatFile.close();
            return loadedMap;
        } catch (IOException ignored) {
            System.out.println("Error loading map");
            return null;
        }
    }

//    public byte[] method24(String s) throws IOException {//Decompress something?
//        File file = new File(s);
//        if (!file.exists()) {
//            return null;
//        } else {
//            int i = (int) file.length();
//            byte buffer[] = new byte[i];
//            DataInputStream datainputstream = new DataInputStream(new BufferedInputStream(new FileInputStream(s)));
//            datainputstream.readFully(buffer, 0, i);
//            datainputstream.close();
//            return buffer;
//        }
//    }
//
//    public boolean checkHash(byte toHash[]) throws Exception {
//        if (toHash == null) {
//            return false;
//        }
//        MessageDigest messageDigest = MessageDigest.getInstance("SHA");
//        messageDigest.reset();
//        messageDigest.update(toHash);
//        byte hashed[] = messageDigest.digest();
//        for (int pos = 0; pos < 20; pos++) {
//            if (hashed[pos] != hashArray[pos]) {
//                return false;
//            }
//        }
//        return true;
//    }
}