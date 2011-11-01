package client;

public class RSInterface {
    
    public Sprite sprite1;
    public int anInt208;
    public Sprite sprites[];
    public static RSInterface interfaceCache[];
    public int anInt211;
    public int anIntArray212[];
    private int anInt213;
    public int anInt214;
    public int spritesX[];
    public int anInt216;
    public int atActionType;
    public String spellName;
    public int anInt219;
    public int width;
    public String tooltip;
    public String selectedActionName;
    public boolean aBoolean223;
    public int scrollPosition;
    public String actions[];
    public int valueIndexArray[][];
    public boolean aBoolean227;
    public String aString228;
    private int anInt229;
    public int anInt230;
    public int invSpritePadX;
    public int textColor;
    public int anInt233;
    public int mediaID;
    public boolean aBoolean235;
    public int parentID;
    public int spellUsableOn;
    private static MRUNodes aClass12_238;
    public int anInt239;
    public int children[];
    public int childX[];
    public boolean usableItemInterface;
    public RSFont textDrawingAreas;
    public int invSpritePadY;
    public int anIntArray245[];
    public int anInt246;
    public int spritesY[];
    public String message;
    public boolean isInventoryInterface;
    public int id;
    public boolean aBoolean251;
    public int invStackSized[];
    public int inv[];
    public byte aByte254;
    public int anInt255;
    public int anInt256;
    public int anInt257;
    public int anInt258;
    public boolean aBoolean259;
    public Sprite sprite2;
    public int scrollMax;
    public int type;
    public int anInt263;
    static MRUNodes aClass12_264 = new MRUNodes(30);
    public int anInt265;
    public boolean aBoolean266;
    public int height;
    public boolean aBoolean268;
    public int anInt269;
    public int anInt270;
    public int anInt271;
    public int childY[];
    static StreamLoader aClass44;
    
    public RSInterface() {
        anInt213 = 9;
        anInt229 = 891;
    }
    
    public static RSInterface addTab(int i) {
        RSInterface class9 = interfaceCache[i] = new RSInterface();
        class9.id = i;
        class9.parentID = i;
        class9.type = 0;
        class9.atActionType = 0;
        class9.anInt214 = 0;
        class9.width = 512;
        class9.height = 334;
        class9.aByte254 = 0;
        class9.anInt230 = 0;
        return class9;
    }

    public static void addText(int id, String text, RSFont wid[], int idx, int color) {
        RSInterface Tab = addTab(id);
        Tab.id = id;
        Tab.parentID = id;
        Tab.type = 4;
        Tab.atActionType = 0;
        Tab.width = 174;
        Tab.height = 11;
        Tab.anInt214 = 0;
        Tab.aByte254 = 0;
        Tab.anInt230 = -1;
        Tab.aBoolean223 = false;
        Tab.aBoolean268 = true;
        Tab.textDrawingAreas = wid[idx];
        Tab.message = text;
        Tab.aString228 = "";
        Tab.textColor = color;
        Tab.anInt219 = 0;
        Tab.anInt216 = 0;
        Tab.anInt239 = 0;
    }

    public static void removeSomething(int i) {
        RSInterface Tab = interfaceCache[i] = new RSInterface();
        Tab.id = i;
        Tab.parentID = i;
        Tab.type = 5;
        Tab.atActionType = 1;
        Tab.anInt214 = 0;
        Tab.aByte254 = 0;
        Tab.anInt230 = 52;
    }

    public static void addActionButton(int i, int j, int k, int width, int height, String s) {
        RSInterface Tab = interfaceCache[i] = new RSInterface();
        Tab.id = i;
        Tab.parentID = i;
        Tab.type = 5;
        Tab.atActionType = 1;
        Tab.anInt214 = 0;
        Tab.width = width;
        Tab.height = height;
        Tab.aByte254 = 0;
        Tab.anInt230 = 52;
        Tab.sprite1 = new Sprite(sign.signlink.findcachedir() + "sprites/interfaces/new/custom " + j + ".png");
        Tab.sprite2 = new Sprite(sign.signlink.findcachedir() + "sprites/interfaces/new/custom " + k + ".png");
        Tab.tooltip = s;
    }

    public static void testing(RSFont[] wid) {
        RSInterface t = addTab(18000);
        addText(18001, "test", wid, 0, 0xffffff);
        t.children = new int[1];
        t.childX = new int[1];
        t.childY = new int[1];
        t.childX[0] = 0; //childX
        t.childY[0] = 0; //childY
        t.children[0] = 18001; //childID
    }

    public static void addHoveredButton(int i, String imageName, int j, int w, int h, int IMAGEID) {//hoverable button
        RSInterface tab = addTab(i);
        tab.parentID = i;
        tab.id = i;
        tab.type = 0;
        tab.atActionType = 0;
        tab.width = w;
        tab.height = h;
        tab.aBoolean266 = true;
        tab.aByte254 = 0;
        tab.anInt230 = -1;
        tab.scrollMax = 0;
        addHoverImage(IMAGEID, j, j, imageName);



        tab.children = new int[1];
        tab.childX = new int[1];
        tab.childY = new int[1];

        tab.childX[0] = 0; //childX
        tab.childY[0] = 0; //childY
        tab.children[0] = IMAGEID; //childID



    }

    public static void addHoverButton(int i, String imageName, int j, int anInt220, int anInt267, String text, int contentType, int hoverOver, int aT) {//hoverable button
        RSInterface tab = addTab(i);
        tab.id = i;
        tab.parentID = i;
        tab.type = 5;
        tab.atActionType = aT;
        tab.anInt214 = contentType;
        tab.aByte254 = 0;
        tab.anInt230 = hoverOver;
        tab.sprite1 = new Sprite(sign.signlink.findcachedir() + "sprites/interfaces/notes/NOTE " + j + ".png");
        tab.sprite2 = new Sprite(sign.signlink.findcachedir() + "sprites/interfaces/notes/NOTE " + j + ".png");
        tab.width = anInt220;
        tab.height = anInt267;
        tab.tooltip = text;
    }

    public static void addHoverImage(int i, int j, int k, String name) {
        RSInterface tab = addTab(i);
        tab.id = i;
        tab.parentID = i;
        tab.type = 5;
        tab.atActionType = 0;
        tab.anInt214 = 0;
        tab.width = 512;
        tab.height = 334;
        tab.aByte254 = 0;
        tab.anInt230 = 52;
        tab.sprite1 = new Sprite(sign.signlink.findcachedir() + "sprites/interfaces/notes/NOTE " + j + ".png");
        tab.sprite2 = new Sprite(sign.signlink.findcachedir() + "sprites/interfaces/notes/NOTE " + k + ".png");
    }

    public static void addSprite(int ID, int i, String name) { //static sprite
        RSInterface Tab = addTab(ID);
        Tab.id = ID;
        Tab.parentID = ID;
        Tab.type = 5;
        Tab.atActionType = 0;
        Tab.anInt214 = 0;
        Tab.width = 512;
        Tab.height = 334;
        Tab.aByte254 = 0;
        Tab.anInt230 = 0;
        Tab.sprite1 = new Sprite(sign.signlink.findcachedir() + "sprites/interfaces/" + name + " " + i + ".png");
        Tab.sprite2 = new Sprite(sign.signlink.findcachedir() + "sprites/interfaces/" + name + " " + i + ".png");
    }

    public static void Sidebar7(RSFont[] tda) {
        RSInterface t = addTab(19000);
        addSprite(17351, 0, "notes/NOTE");
        addHoverButton(17352, "NOTE", 1, 200, 30, "Add note", -1, 17353, 1);
        addHoveredButton(17353, "NOTE", 2, 200, 30, 17354);
        addHoverButton(17355, "NOTE", 3, 200, 30, "Delete all", -1, 17356, 1);
        addHoveredButton(17356, "NOTE", 4, 200, 30, 17357);

        addText(13800, "Under Construction", tda, 0, 0xffffff);

        addText(17801, "", tda, 0, 0xff981f);
        addText(17812, "", tda, 0, 0xff981f);
        addText(17813, "", tda, 0, 0xff981f);
        addText(17814, "", tda, 0, 0xff981f);

        t.children = new int[6];
        t.childX = new int[6];
        t.childY = new int[6];

        t.childX[0] = 0; //childX
        t.childY[0] = 0; //childY
        t.children[0] = 17351; //childID

        t.childX[1] = 8; //childX
        t.childY[1] = 2; //childY
        t.children[1] = 17352; //childID

        t.childX[2] = 8; //childX
        t.childY[2] = 2; //childY
        t.children[2] = 17353; //childID

        t.childX[3] = 165; //childX
        t.childY[3] = 237; //childY
        t.children[3] = 17355; //childID

        t.childX[4] = 165; //childX
        t.childY[4] = 237; //childY
        t.children[4] = 17356; //childID

        t.childX[5] = 40; //childX
        t.childY[5] = 78; //childY
        t.children[5] = 13800; //childID


        t = addTab(14000);
        t.width = 474;
        t.height = 213;
        t.scrollMax = 305;


        for (int i = 14001; i <= 14030; i++) {
            addText(i, "", tda, 0, 0xffffff);
        }

        t.children = new int[30];
        t.childX = new int[30];
        t.childY = new int[30];

        int Y = 5;
        int Child = 0;
        for (int i = 14001; i <= 14030; i++) {

            t.childX[Child] = 248; //childX
            t.childY[Child] = Y; //childY
            t.children[Child] = i; //childID

            Y += 13;
            Child++;
        }
    }

    public static void LogoutSB(RSFont[] wid) {
        RSInterface class9 = interfaceCache[2449];

        //top line
        class9.childY[0] = 10;
        class9.childX[0] = 4; //X pos
        class9.children[0] = 2450; //Interface id

        //middle line
        class9.childY[1] = 26;
        class9.childX[1] = 31; //X pos
        class9.children[1] = 2452; //Interface id

        //bottom line
        class9.childY[2] = 41;
        class9.childX[2] = 16;
        class9.children[2] = 2451; //Interface id



        addSprite(17351, 0, "notes/NOTE");




        //red box
        class9.childY[3] = 215; //down
        class9.childY[4] = 215; //down
        class9.childY[5] = 215; //down
        class9.childY[6] = 215; //down
        class9.childY[7] = 215; //down

        //red box TEXT
        class9.childX[8] = 31; //acroos
        class9.childY[8] = 225; //down

        //top text
        class9 = interfaceCache[2450];
        class9.textColor = 0xff981f;
        class9.message = "When you have finished playing,";
        class9 = interfaceCache[2452];
        class9.textColor = 0xff981f;
        class9.message = "on -GaMeR X-'s Client,";
        class9 = interfaceCache[2451];
        class9.textColor = 0xff981f;
        class9.message = "click below, to logout safely.";
    }

    public static void Sidebar4(RSFont[] wid) {
        RSInterface t = interfaceCache[1644];
        /*addActionButton(ID, SpriteON, SpriteOFF, SpriteX, SpriteY, "SpriteText");*/
        addActionButton(15101, 15101, 15101, 40, 39, "Show Equipment Screen");
        //addActionButton(15102, 15102, 15102, 40, 39, "Items Kept on Death");
		/*removeSomething(ID)*/
        removeSomething(15103);
        removeSomething(15104);
        t.children[23] = 15101;
        t.childX[23] = 78;
        t.childY[23] = 212;
        t.children[24] = 15102;
        t.childX[24] = 41 + 39 + 30;
        t.childY[24] = 212;
        t.children[25] = 15103;
        t.childX[25] = 41;
        t.childY[25] = 212;
        t.children[26] = 15104;
        t.childX[26] = 41 + 39 + 30;
        t.childY[26] = 212;
    }

    public static void Sidebar8(RSFont[] wid) {
        RSInterface class9 = interfaceCache[5065];
        addText(5070, "Add Friend", wid, 0, 0xff9933);
        addText(5071, "Del Friend", wid, 0, 0xff9933);
        class9.childX[0] = 4;
        class9.childY[0] = 20;
        class9.childX[1] = 53;//friends list - world xxx X
        class9.childX[4] = 25;//add friend X
        class9.childX[5] = 106;//del friend X
        class9.childY[4] = 236;//add friend Y
        class9.childY[5] = 236;//del friend Y
        class9 = interfaceCache[5066];
        class9.height = 194;
        for (int i = 0; i < 400; i++) {
            class9.childY[i] -= 1;
        }
        class9 = interfaceCache[5067];
        class9.message = "Friends List - World 1";
        class9.aBoolean223 = true;
        class9.textColor = 0xff9933;
    }

    public static void Sidebar9(RSFont[] wid) {
        RSInterface class9 = interfaceCache[5715];
        addText(5720, " Add Name", wid, 0, 0xff9933);
        addText(5721, " Del Name", wid, 0, 0xff9933);
        class9.childX[0] = 4; //offset for the players names..
        class9.childY[0] = 20; //players from top..
        class9.childX[1] = 53; //friends list - world
        class9.childX[4] = 25; //add friend X
        class9.childX[5] = 106; //del friend X
        class9.childY[4] = 236; //add friend Y
        class9.childY[5] = 236; //del friend Y
        class9 = interfaceCache[5717]; //this makes to we can ser a new text..
        class9.message = "Ignore List";
        class9.aBoolean223 = true; //offsets to old location of the header..
        class9.textColor = 0xff9933; //sets the colour
    }

    public void swapInventoryItems(int i, byte byte0, int j) {
        int k = inv[i];
        inv[i] = inv[j];
        if (byte0 == 9) {
            byte0 = 0;
        } else {
            anInt229 = -76;
        }
        inv[j] = k;
        k = invStackSized[i];
        invStackSized[i] = invStackSized[j];
        invStackSized[j] = k;
    }

    public static void unpack(StreamLoader class44, RSFont aclass30_sub2_sub1_sub4[], byte byte0, StreamLoader class44_1) {
        aClass12_238 = new MRUNodes(50000);
        Stream class30_sub2_sub2 = new Stream(class44.getDataForName("data"));
        int i = -1;
        int j = class30_sub2_sub2.readUnsignedWord();
        interfaceCache = new RSInterface[20001];
        do {
            if (class30_sub2_sub2.currentOffset >= class30_sub2_sub2.buffer.length) {
                break;
            }
            int k = class30_sub2_sub2.readUnsignedWord();
            if (k == 65535) {
                i = class30_sub2_sub2.readUnsignedWord();
                k = class30_sub2_sub2.readUnsignedWord();
            }
            RSInterface class9 = interfaceCache[k] = new RSInterface();
            class9.id = k;
            class9.parentID = i;
            class9.type = class30_sub2_sub2.readUnsignedByte();
            class9.atActionType = class30_sub2_sub2.readUnsignedByte();
            class9.anInt214 = class30_sub2_sub2.readUnsignedWord();
            class9.width = class30_sub2_sub2.readUnsignedWord();
            class9.height = class30_sub2_sub2.readUnsignedWord();
            class9.aByte254 = (byte) class30_sub2_sub2.readUnsignedByte();
            class9.anInt230 = class30_sub2_sub2.readUnsignedByte();
            if (class9.anInt230 != 0) {
                class9.anInt230 = (class9.anInt230 - 1 << 8) + class30_sub2_sub2.readUnsignedByte();
            } else {
                class9.anInt230 = -1;
            }
            int l = class30_sub2_sub2.readUnsignedByte();
            if (l > 0) {
                class9.anIntArray245 = new int[l];
                class9.anIntArray212 = new int[l];
                for (int i1 = 0; i1 < l; i1++) {
                    class9.anIntArray245[i1] = class30_sub2_sub2.readUnsignedByte();
                    class9.anIntArray212[i1] = class30_sub2_sub2.readUnsignedWord();
                }
            }
            int j1 = class30_sub2_sub2.readUnsignedByte();
            if (j1 > 0) {
                class9.valueIndexArray = new int[j1][];
                for (int k1 = 0; k1 < j1; k1++) {
                    int j3 = class30_sub2_sub2.readUnsignedWord();
                    class9.valueIndexArray[k1] = new int[j3];
                    for (int l4 = 0; l4 < j3; l4++) {
                        class9.valueIndexArray[k1][l4] = class30_sub2_sub2.readUnsignedWord();
                    }
                }
            }
            if (class9.type == 0) { //type
                class9.scrollMax = class30_sub2_sub2.readUnsignedWord();
                class9.aBoolean266 = class30_sub2_sub2.readUnsignedByte() == 1;
                int l1 = class30_sub2_sub2.readUnsignedWord();
                class9.children = new int[l1];
                class9.childX = new int[l1];
                class9.childY = new int[l1];
                for (int k3 = 0; k3 < l1; k3++) {
                    class9.children[k3] = class30_sub2_sub2.readUnsignedWord();
                    class9.childX[k3] = class30_sub2_sub2.readSignedWord();
                    class9.childY[k3] = class30_sub2_sub2.readSignedWord();
                }
            }
            if (class9.type == 1) {
                class9.anInt211 = class30_sub2_sub2.readUnsignedWord();
                class9.aBoolean251 = class30_sub2_sub2.readUnsignedByte() == 1;
            }
            if (class9.type == 2) {
                class9.inv = new int[class9.width * class9.height];
                class9.invStackSized = new int[class9.width * class9.height];
                class9.aBoolean259 = class30_sub2_sub2.readUnsignedByte() == 1;
                class9.isInventoryInterface = class30_sub2_sub2.readUnsignedByte() == 1;
                class9.usableItemInterface = class30_sub2_sub2.readUnsignedByte() == 1;
                class9.aBoolean235 = class30_sub2_sub2.readUnsignedByte() == 1;
                class9.invSpritePadX = class30_sub2_sub2.readUnsignedByte();
                class9.invSpritePadY = class30_sub2_sub2.readUnsignedByte();
                class9.spritesX = new int[20];
                class9.spritesY = new int[20];
                class9.sprites = new Sprite[20];
                for (int i2 = 0; i2 < 20; i2++) {
                    int l3 = class30_sub2_sub2.readUnsignedByte();
                    if (l3 != 1) {
                        continue;
                    }
                    class9.spritesX[i2] = class30_sub2_sub2.readSignedWord();
                    class9.spritesY[i2] = class30_sub2_sub2.readSignedWord();
                    String s1 = class30_sub2_sub2.readString();
                    if (class44_1 != null && s1.length() > 0) {
                        int i5 = s1.lastIndexOf(",");
                        class9.sprites[i2] = method207(Integer.parseInt(s1.substring(i5 + 1)), false, class44_1, s1.substring(0, i5));
                    }
                }
                class9.actions = new String[5];
                for (int j2 = 0; j2 < 5; j2++) {
                    class9.actions[j2] = class30_sub2_sub2.readString();
                    if (class9.actions[j2].length() == 0) {
                        class9.actions[j2] = null;
                    }
                }
            }
            if (class9.type == 3) {
                class9.aBoolean227 = class30_sub2_sub2.readUnsignedByte() == 1;
            }
            if (class9.type == 4 || class9.type == 1) {
                class9.aBoolean223 = class30_sub2_sub2.readUnsignedByte() == 1;
                int k2 = class30_sub2_sub2.readUnsignedByte();
                if (aclass30_sub2_sub1_sub4 != null) {
                    class9.textDrawingAreas = aclass30_sub2_sub1_sub4[k2];
                }
                class9.aBoolean268 = class30_sub2_sub2.readUnsignedByte() == 1;
            }
            if (class9.type == 4) { //type = 4??
                class9.message = class30_sub2_sub2.readString(); //readString
                class9.message = class9.message.replaceAll("RuneScape", "-GaMeR X's Client"); //message
                class9.aString228 = class30_sub2_sub2.readString(); //readString
            }
            if (class9.type == 1 || class9.type == 3 || class9.type == 4) {
                class9.textColor = class30_sub2_sub2.readDWord();
            }
            if (class9.type == 3 || class9.type == 4) {
                class9.anInt219 = class30_sub2_sub2.readDWord();
                class9.anInt216 = class30_sub2_sub2.readDWord();
                class9.anInt239 = class30_sub2_sub2.readDWord();
            }
            if (class9.type == 5) {
                String s = class30_sub2_sub2.readString();
                if (class44_1 != null && s.length() > 0) {
                    int i4 = s.lastIndexOf(",");
                    class9.sprite1 = method207(Integer.parseInt(s.substring(i4 + 1)), false, class44_1, s.substring(0, i4));
                }
                s = class30_sub2_sub2.readString();
                if (class44_1 != null && s.length() > 0) {
                    int j4 = s.lastIndexOf(",");
                    class9.sprite2 = method207(Integer.parseInt(s.substring(j4 + 1)), false, class44_1, s.substring(0, j4));
                }
            }
            if (class9.type == 6) {
                int l2 = class30_sub2_sub2.readUnsignedByte();
                if (l2 != 0) {
                    class9.anInt233 = 1;
                    class9.mediaID = (l2 - 1 << 8) + class30_sub2_sub2.readUnsignedByte();
                }
                l2 = class30_sub2_sub2.readUnsignedByte();
                if (l2 != 0) {
                    class9.anInt255 = 1;
                    class9.anInt256 = (l2 - 1 << 8) + class30_sub2_sub2.readUnsignedByte();
                }
                l2 = class30_sub2_sub2.readUnsignedByte();
                if (l2 != 0) {
                    class9.anInt257 = (l2 - 1 << 8) + class30_sub2_sub2.readUnsignedByte();
                } else {
                    class9.anInt257 = -1;
                }
                l2 = class30_sub2_sub2.readUnsignedByte();
                if (l2 != 0) {
                    class9.anInt258 = (l2 - 1 << 8) + class30_sub2_sub2.readUnsignedByte();
                } else {
                    class9.anInt258 = -1;
                }
                class9.anInt269 = class30_sub2_sub2.readUnsignedWord();
                class9.anInt270 = class30_sub2_sub2.readUnsignedWord();
                class9.anInt271 = class30_sub2_sub2.readUnsignedWord();
            }
            if (class9.type == 7) {
                class9.inv = new int[class9.width * class9.height];
                class9.invStackSized = new int[class9.width * class9.height];
                class9.aBoolean223 = class30_sub2_sub2.readUnsignedByte() == 1;
                int i3 = class30_sub2_sub2.readUnsignedByte();
                if (aclass30_sub2_sub1_sub4 != null) {
                    class9.textDrawingAreas = aclass30_sub2_sub1_sub4[i3];
                }
                class9.aBoolean268 = class30_sub2_sub2.readUnsignedByte() == 1;
                class9.textColor = class30_sub2_sub2.readDWord();
                class9.invSpritePadX = class30_sub2_sub2.readSignedWord();
                class9.invSpritePadY = class30_sub2_sub2.readSignedWord();
                class9.isInventoryInterface = class30_sub2_sub2.readUnsignedByte() == 1;
                class9.actions = new String[5];
                for (int k4 = 0; k4 < 5; k4++) {
                    class9.actions[k4] = class30_sub2_sub2.readString();
                    if (class9.actions[k4].length() == 0) {
                        class9.actions[k4] = null;
                    }
                }
            }
            if (class9.atActionType == 2 || class9.type == 2) {
                class9.selectedActionName = class30_sub2_sub2.readString();
                class9.spellName = class30_sub2_sub2.readString();
                class9.spellUsableOn = class30_sub2_sub2.readUnsignedWord();
            }
            if (class9.type == 8) {	//	Loads new .dat files
                class9.message = class30_sub2_sub2.readString();
            }
            if (class9.atActionType == 1 || class9.atActionType == 4 || class9.atActionType == 5 || class9.atActionType == 6) {
                class9.tooltip = class30_sub2_sub2.readString();
                if (class9.tooltip.length() == 0) {
                    if (class9.atActionType == 1) {
                        class9.tooltip = "Ok";
                    }
                    if (class9.atActionType == 4) {
                        class9.tooltip = "Select";
                    }
                    if (class9.atActionType == 5) {
                        class9.tooltip = "Select";
                    }
                    if (class9.atActionType == 6) {
                        class9.tooltip = "Continue";
                    }
                }
            }
        } while (true);
        aClass12_238 = null;
        aClass44 = class44;
        ////////////////////////// HEREEEEEEEEEEEEE /////////////////////////////
        /////////////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////////

        Sidebar4(aclass30_sub2_sub1_sub4); //equip

        Sidebar7(aclass30_sub2_sub1_sub4); //notes
        Sidebar8(aclass30_sub2_sub1_sub4); //friends
        Sidebar9(aclass30_sub2_sub1_sub4);//ignores


        LogoutSB(aclass30_sub2_sub1_sub4); //logout

        testing(aclass30_sub2_sub1_sub4);



        if (byte0 != -84);
    }

    private Model method206(int i, int j) {
        Model class30_sub2_sub4_sub6 = (Model) aClass12_264.insertFromCache((i << 16) + j);
        if (class30_sub2_sub4_sub6 != null) {
            return class30_sub2_sub4_sub6;
        }
        if (i == 1) {
            class30_sub2_sub4_sub6 = Model.method462(anInt213, j);
        }
        if (i == 2) {
            class30_sub2_sub4_sub6 = EntityDef.forID(j).method160(true);
        }
        if (i == 3) {
            class30_sub2_sub4_sub6 = client.aClass30_Sub2_Sub4_Sub1_Sub2_1126.method453((byte) -41);
        }
        if (i == 4) {
            class30_sub2_sub4_sub6 = ItemDef.forID(j).method202(50, true);
        }
        if (i == 5) {
            class30_sub2_sub4_sub6 = null;
        }
        if (class30_sub2_sub4_sub6 != null) {
            aClass12_264.removeFromCache(class30_sub2_sub4_sub6, (i << 16) + j, (byte) 2);
        }
        return class30_sub2_sub4_sub6;
    }

    private static Sprite method207(int i, boolean flag, StreamLoader class44, String s) {
        long l = (TextClass.method585(s) << 8) + (long) i;
        if (flag) {
            throw new NullPointerException();
        }
        Sprite class30_sub2_sub1_sub1 = (Sprite) aClass12_238.insertFromCache(l);
        if (class30_sub2_sub1_sub1 != null) {
            return class30_sub2_sub1_sub1;
        }
        try {
            class30_sub2_sub1_sub1 = new Sprite(class44, s, i);
            aClass12_238.removeFromCache(class30_sub2_sub1_sub1, l, (byte) 2);
        } catch (Exception exception) {
            return null;
        }
        return class30_sub2_sub1_sub1;
    }

    public static void method208(int i, boolean flag, int j, Model class30_sub2_sub4_sub6) {
        if (flag) {
            return;
        }
        aClass12_264.unlinkAll();
        if (class30_sub2_sub4_sub6 != null && j != 4) {
            aClass12_264.removeFromCache(class30_sub2_sub4_sub6, (j << 16) + i, (byte) 2);
        }
    }

    public Model method209(int j, int k, boolean flag) {
        Model model;
        if (flag) {
            model = method206(anInt255, anInt256);
        } else {
            model = method206(anInt233, mediaID);
        }
        if (model == null) {
            return null;
        }
        if (k == -1 && j == -1 && model.trianglecolours == null) {
            return model;
        }
        Model model_1 = new Model(9, true, Class36.method532(k) & Class36.method532(j), false, model);
        if (k != -1 || j != -1) {
            model_1.method469((byte) -71);
        }
        if (k != -1) {
            model_1.method470(k, 40542);
        }
        if (j != -1) {
            model_1.method470(j, 40542);
        }
        model_1.method479(64, 768, -50, -10, -50, true);
        return model_1;
    }
}