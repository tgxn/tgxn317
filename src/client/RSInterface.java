package client;

import client.drawing.Sprite;
import client.animation.AnimationFrame;
import client.text.RSFont;
import client.text.TextClass;
import client.fileio.JagexArchive;

public class RSInterface {
    
    public Sprite disabledSprite;
    public int animationDelay;
    public Sprite sprites[];
    public static RSInterface interfaceCache[];
    public int requiredValues[];
    public int contentType;
    public int spritesX[];
    public int disabledHoverColor;
    public int atActionType;
    public String spellName;
    public int enabledColor;
    public int width;
    public String tooltip;
    public String selectedActionName;
    public boolean textCentered;
    public int scrollPosition;
    public String itemActions[];
    public int valueIndexArray[][];
    public boolean boxFilled;
    public String enabledMessage;
    public int hoverType;
    public int invSpritePadX;
    public int disabledColor;
    public int disabledMediaType;
    public int disabledMediaID;
    public boolean deletesTargetSlot;
    public int parentID;
    public int spellUsableOn;
    private static MemCache spriteNodes;
    public int enabledHoverColor;
    public int children[];
    public int childX[];
    public boolean usableItemInterface;
    public RSFont font;
    public int invSpritePadY;
    public int valueCompareType[];
    public int animationLength;
    public int spritesY[];
    public String disabledMessage;
    public boolean isInventoryInterface;
    public int id;
    public int inventoryValue[];
    public int inventory[];
    public byte opacity;
    public int enabledMediaType;
    public int enabledMediaID;
    public int disabledAnimation;
    public int enabledAnimation;
    public boolean allowSwapItems;
    public Sprite enabledSprite;
    public int scrollMax;
    public int interfaceType;
    public int xOffset;
    static MemCache modelNodes = new MemCache(30);
    public int yOffset;
    public boolean interfaceShown;
    public int height;
    public boolean textShadowed;
    public int modelZoom;
    public int modelRotationY;
    public int modelRotationX;
    public int childY[];
    static JagexArchive streamLoader;
    
    public RSInterface() {
    }
    
    public static RSInterface addTab(int i) {
        RSInterface class9 = interfaceCache[i] = new RSInterface();
        class9.id = i;
        class9.parentID = i;
        class9.interfaceType = 0;
        class9.atActionType = 0;
        class9.contentType = 0;
        class9.width = 512;
        class9.height = 334;
        class9.opacity = 0;
        class9.hoverType = 0;
        return class9;
    }

    public static void addText(int id, String text, RSFont wid[], int idx, int color) {
        RSInterface Tab = addTab(id);
        Tab.id = id;
        Tab.parentID = id;
        Tab.interfaceType = 4;
        Tab.atActionType = 0;
        Tab.width = 174;
        Tab.height = 11;
        Tab.contentType = 0;
        Tab.opacity = 0;
        Tab.hoverType = -1;
        Tab.textCentered = false;
        Tab.textShadowed = true;
        Tab.font = wid[idx];
        Tab.disabledMessage = text;
        Tab.enabledMessage = "";
        Tab.disabledColor = color;
        Tab.enabledColor = 0;
        Tab.disabledHoverColor = 0;
        Tab.enabledHoverColor = 0;
    }

    public static void removeSomething(int i) {
        RSInterface Tab = interfaceCache[i] = new RSInterface();
        Tab.id = i;
        Tab.parentID = i;
        Tab.interfaceType = 5;
        Tab.atActionType = 1;
        Tab.contentType = 0;
        Tab.opacity = 0;
        Tab.hoverType = 52;
    }

    public static void addActionButton(int i, int j, int k, int width, int height, String s) {
        RSInterface Tab = interfaceCache[i] = new RSInterface();
        Tab.id = i;
        Tab.parentID = i;
        Tab.interfaceType = 5;
        Tab.atActionType = 1;
        Tab.contentType = 0;
        Tab.width = width;
        Tab.height = height;
        Tab.opacity = 0;
        Tab.hoverType = 52;
        Tab.disabledSprite = new Sprite(client.sign.Signlink.findCacheDIR() + "sprites/interfaces/new/custom " + j + ".png");
        Tab.enabledSprite = new Sprite(client.sign.Signlink.findCacheDIR() + "sprites/interfaces/new/custom " + k + ".png");
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
        tab.interfaceType = 0;
        tab.atActionType = 0;
        tab.width = w;
        tab.height = h;
        tab.interfaceShown = true;
        tab.opacity = 0;
        tab.hoverType = -1;
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
        tab.interfaceType = 5;
        tab.atActionType = aT;
        tab.contentType = contentType;
        tab.opacity = 0;
        tab.hoverType = hoverOver;
        tab.disabledSprite = new Sprite(client.sign.Signlink.findCacheDIR() + "sprites/interfaces/notes/NOTE " + j + ".png");
        tab.enabledSprite = new Sprite(client.sign.Signlink.findCacheDIR() + "sprites/interfaces/notes/NOTE " + j + ".png");
        tab.width = anInt220;
        tab.height = anInt267;
        tab.tooltip = text;
    }

    public static void addHoverImage(int i, int j, int k, String name) {
        RSInterface tab = addTab(i);
        tab.id = i;
        tab.parentID = i;
        tab.interfaceType = 5;
        tab.atActionType = 0;
        tab.contentType = 0;
        tab.width = 512;
        tab.height = 334;
        tab.opacity = 0;
        tab.hoverType = 52;
        tab.disabledSprite = new Sprite(client.sign.Signlink.findCacheDIR() + "sprites/interfaces/notes/NOTE " + j + ".png");
        tab.enabledSprite = new Sprite(client.sign.Signlink.findCacheDIR() + "sprites/interfaces/notes/NOTE " + k + ".png");
    }

    public static void addSprite(int ID, int i, String name) { //static sprite
        RSInterface Tab = addTab(ID);
        Tab.id = ID;
        Tab.parentID = ID;
        Tab.interfaceType = 5;
        Tab.atActionType = 0;
        Tab.contentType = 0;
        Tab.width = 512;
        Tab.height = 334;
        Tab.opacity = 0;
        Tab.hoverType = 0;
        Tab.disabledSprite = new Sprite(client.sign.Signlink.findCacheDIR() + "sprites/interfaces/" + name + " " + i + ".png");
        Tab.enabledSprite = new Sprite(client.sign.Signlink.findCacheDIR() + "sprites/interfaces/" + name + " " + i + ".png");
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
        class9.disabledColor = 0xff981f;
        class9.disabledMessage = "When you have finished playing,";
        class9 = interfaceCache[2452];
        class9.disabledColor = 0xff981f;
        class9.disabledMessage = "on -GaMeR X-'s Client,";
        class9 = interfaceCache[2451];
        class9.disabledColor = 0xff981f;
        class9.disabledMessage = "click below, to logout safely.";
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
        class9.disabledMessage = "Friends List - World 1";
        class9.textCentered = true;
        class9.disabledColor = 0xff9933;
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
        class9.disabledMessage = "Ignore List";
        class9.textCentered = true; //offsets to old location of the header..
        class9.disabledColor = 0xff9933; //sets the colour
    }

    public void swapInventoryItems(int startSlot, int endSlot) {
        int next = inventory[startSlot];
        inventory[startSlot] = inventory[endSlot];
        inventory[endSlot] = next;
        next = inventoryValue[startSlot];
        inventoryValue[startSlot] = inventoryValue[endSlot];
        inventoryValue[endSlot] = next;
    }

    public static void unpack(JagexArchive interfaceIndex, RSFont fonts[], JagexArchive spriteArchive) {
        spriteNodes = new MemCache(50000);
        Stream data = new Stream(interfaceIndex.getDataForName("data"));
        int child = -1;
        data.readUnsignedWord();
        interfaceCache = new RSInterface[50000]; //20001
        while(data.currentOffset < data.buffer.length) {
            int parent = data.readUnsignedWord();
            if (parent == 65535) {
                child = data.readUnsignedWord();
                parent = data.readUnsignedWord();
            }
            RSInterface rsInterface = interfaceCache[parent] = new RSInterface();
            rsInterface.id = parent;
            rsInterface.parentID = child;
            rsInterface.interfaceType = data.readUnsignedByte();
            rsInterface.atActionType = data.readUnsignedByte();
            rsInterface.contentType = data.readUnsignedWord();
            rsInterface.width = data.readUnsignedWord();
            rsInterface.height = data.readUnsignedWord();
            rsInterface.opacity = (byte) data.readUnsignedByte();
            rsInterface.hoverType = data.readUnsignedByte();
            if (rsInterface.hoverType != 0) {
                rsInterface.hoverType = (rsInterface.hoverType - 1 << 8) + data.readUnsignedByte();
            } else {
                rsInterface.hoverType = -1;
            }
            int requirementIndex = data.readUnsignedByte();
            if (requirementIndex > 0) {
                rsInterface.valueCompareType = new int[requirementIndex];
                rsInterface.requiredValues = new int[requirementIndex];
                for (int index = 0; index < requirementIndex; index++) {
                    rsInterface.valueCompareType[index] = data.readUnsignedByte();
                    rsInterface.requiredValues[index] = data.readUnsignedWord();
                }
            }
            int valueType = data.readUnsignedByte();
            if (valueType > 0) {
                rsInterface.valueIndexArray = new int[valueType][];
                for (int valueIndex = 0; valueIndex < valueType; valueIndex++) {
                    int size = data.readUnsignedWord();
                    rsInterface.valueIndexArray[valueIndex] = new int[size];
                    for (int nextIndex = 0; nextIndex < size; nextIndex++) {
                        rsInterface.valueIndexArray[valueIndex][nextIndex] = data.readUnsignedWord();
                    }
                }
            }
            if (rsInterface.interfaceType == 0) {
                rsInterface.scrollMax = data.readUnsignedWord();
                rsInterface.interfaceShown = data.readUnsignedByte() == 1;
                int childCount = data.readUnsignedWord();
                rsInterface.children = new int[childCount];
                rsInterface.childX = new int[childCount];
                rsInterface.childY = new int[childCount];
                for (int childIndex = 0; childIndex < childCount; childIndex++) {
                    rsInterface.children[childIndex] = data.readUnsignedWord();
                    rsInterface.childX[childIndex] = data.readSignedWord();
                    rsInterface.childY[childIndex] = data.readSignedWord();
                }
            }
            if (rsInterface.interfaceType == 1) {
                data.readUnsignedWord();
                data.readUnsignedByte();
            }
            if (rsInterface.interfaceType == 2) {
                rsInterface.inventory = new int[rsInterface.width * rsInterface.height];
                rsInterface.inventoryValue = new int[rsInterface.width * rsInterface.height];
                rsInterface.allowSwapItems = data.readUnsignedByte() == 1;
                rsInterface.isInventoryInterface = data.readUnsignedByte() == 1;
                rsInterface.usableItemInterface = data.readUnsignedByte() == 1;
                rsInterface.deletesTargetSlot = data.readUnsignedByte() == 1;
                rsInterface.invSpritePadX = data.readUnsignedByte();
                rsInterface.invSpritePadY = data.readUnsignedByte();
                rsInterface.spritesX = new int[20];
                rsInterface.spritesY = new int[20];
                rsInterface.sprites = new Sprite[20];
                for (int index = 0; index < 20; index++) {
                    int dummy = data.readUnsignedByte();
                    if (dummy == 1) {
                        rsInterface.spritesX[index] = data.readSignedWord();
                        rsInterface.spritesY[index] = data.readSignedWord();
                        String spriteName = data.readString();
                        if (spriteArchive != null && spriteName.length() > 0) {
                            int spriteID = spriteName.lastIndexOf(",");
                            rsInterface.sprites[index] = getSprite(Integer.parseInt(spriteName.substring(spriteID + 1)), spriteArchive, spriteName.substring(0, spriteID));
                        }
                    }
                }
                rsInterface.itemActions = new String[5];
                for (int actionIndex = 0; actionIndex < 5; actionIndex++) {
                    rsInterface.itemActions[actionIndex] = data.readString();
                    if (rsInterface.itemActions[actionIndex].length() == 0) {
                        rsInterface.itemActions[actionIndex] = null;
                    }
                }
            }
            if (rsInterface.interfaceType == 3) {
                rsInterface.boxFilled = data.readUnsignedByte() == 1;
            }
            if (rsInterface.interfaceType == 4 || rsInterface.interfaceType == 1) {
                rsInterface.textCentered = data.readUnsignedByte() == 1;
                int fontID = data.readUnsignedByte();
                if (fonts != null) {
                    rsInterface.font = fonts[fontID];
                }
                rsInterface.textShadowed = data.readUnsignedByte() == 1;
            }
            if (rsInterface.interfaceType == 4) {
                rsInterface.disabledMessage = data.readString(); //readString
                //rsInterface.message = rsInterface.message.replaceAll("RuneScape", "-GaMeR X's Client"); //message
                rsInterface.enabledMessage = data.readString();
            }
            if (rsInterface.interfaceType == 1 || rsInterface.interfaceType == 3 || rsInterface.interfaceType == 4) {
                rsInterface.disabledColor = data.readDWord();
            }
            if (rsInterface.interfaceType == 3 || rsInterface.interfaceType == 4) {
                rsInterface.enabledColor = data.readDWord();
                rsInterface.disabledHoverColor = data.readDWord();
                rsInterface.enabledHoverColor = data.readDWord();
            }
            if (rsInterface.interfaceType == 5) {
                String spriteName = data.readString();
                if (spriteArchive != null && spriteName.length() > 0) {
                    int disabledSpriteId = spriteName.lastIndexOf(",");
                    rsInterface.disabledSprite = getSprite(Integer.parseInt(spriteName.substring(disabledSpriteId + 1)), spriteArchive, spriteName.substring(0, disabledSpriteId));
                }
                spriteName = data.readString();
                if (spriteArchive != null && spriteName.length() > 0) {
                    int enabledSpriteId = spriteName.lastIndexOf(",");
                    rsInterface.enabledSprite = getSprite(Integer.parseInt(spriteName.substring(enabledSpriteId + 1)), spriteArchive, spriteName.substring(0, enabledSpriteId));
                }
            }
            if (rsInterface.interfaceType == 6) {
                int readValue = data.readUnsignedByte();
                if (readValue != 0) {
                    rsInterface.disabledMediaType = 1;
                    rsInterface.disabledMediaID = (readValue - 1 << 8) + data.readUnsignedByte();
                }
                readValue = data.readUnsignedByte();
                if (readValue != 0) {
                    rsInterface.enabledMediaType = 1;
                    rsInterface.enabledMediaID = (readValue - 1 << 8) + data.readUnsignedByte();
                }
                readValue = data.readUnsignedByte();
                if (readValue != 0) {
                    rsInterface.disabledAnimation = (readValue - 1 << 8) + data.readUnsignedByte();
                } else {
                    rsInterface.disabledAnimation = -1;
                }
                readValue = data.readUnsignedByte();
                if (readValue != 0) {
                    rsInterface.enabledAnimation = (readValue - 1 << 8) + data.readUnsignedByte();
                } else {
                    rsInterface.enabledAnimation = -1;
                }
                rsInterface.modelZoom = data.readUnsignedWord();
                rsInterface.modelRotationY = data.readUnsignedWord();
                rsInterface.modelRotationX = data.readUnsignedWord();
            }
            if (rsInterface.interfaceType == 7) {
                rsInterface.inventory = new int[rsInterface.width * rsInterface.height];
                rsInterface.inventoryValue = new int[rsInterface.width * rsInterface.height];
                rsInterface.textCentered = data.readUnsignedByte() == 1;
                int fontID = data.readUnsignedByte();
                if (fonts != null) {
                    rsInterface.font = fonts[fontID];
                }
                rsInterface.textShadowed = data.readUnsignedByte() == 1;
                rsInterface.disabledColor = data.readDWord();
                rsInterface.invSpritePadX = data.readSignedWord();
                rsInterface.invSpritePadY = data.readSignedWord();
                rsInterface.isInventoryInterface = data.readUnsignedByte() == 1;
                rsInterface.itemActions = new String[5];
                for (int actionIndex = 0; actionIndex < 5; actionIndex++) {
                    rsInterface.itemActions[actionIndex] = data.readString();
                    if (rsInterface.itemActions[actionIndex].length() == 0) {
                        rsInterface.itemActions[actionIndex] = null;
                    }
                }
            }
            if (rsInterface.atActionType == 2 || rsInterface.interfaceType == 2) {
                rsInterface.selectedActionName = data.readString();
                rsInterface.spellName = data.readString();
                rsInterface.spellUsableOn = data.readUnsignedWord();
            }
            if (rsInterface.interfaceType == 8) {
                rsInterface.disabledMessage = data.readString();
            }
            if (rsInterface.atActionType == 1 || rsInterface.atActionType == 4 || rsInterface.atActionType == 5 || rsInterface.atActionType == 6) {
                rsInterface.tooltip = data.readString();
                if (rsInterface.tooltip.length() == 0) {
                    if (rsInterface.atActionType == 1) {
                        rsInterface.tooltip = "Ok";
                    }
                    if (rsInterface.atActionType == 4) {
                        rsInterface.tooltip = "Select";
                    }
                    if (rsInterface.atActionType == 5) {
                        rsInterface.tooltip = "Select";
                    }
                    if (rsInterface.atActionType == 6) {
                        rsInterface.tooltip = "Continue";
                    }
                }
            }
        }
        spriteNodes = null;
        streamLoader = interfaceIndex;
        ////////////////////////// HEREEEEEEEEEEEEE /////////////////////////////
        /////////////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////////

        Sidebar4(fonts); //equip

        Sidebar7(fonts); //notes
        Sidebar8(fonts); //friends
        Sidebar9(fonts);//ignores


        LogoutSB(fonts); //logout

        testing(fonts);



    }

    private Model getModelByType(int modelType, int modelID) {
        Model model = (Model) modelNodes.get((modelType << 16) + modelID);
        if (model != null) {
            return model;
        }
        if (modelType == 1) {
            model = Model.getModel(modelID);
        }
        if (modelType == 2) {
            model = NPCDef.forID(modelID).getHeadModel();
        }
        if (modelType == 3) {
            model = Client.myPlayer.getPlayerModel();
        }
        if (modelType == 4) {
            model = ItemDef.forID(modelID).getInventoryModel(50);
        }
        if (modelType == 5) {
            model = null;
        }
        if (model != null) {
            modelNodes.put(model, (modelType << 16) + modelID);
        }
        return model;
    }

    private static Sprite getSprite(int i, JagexArchive spriteArchive, String s) {
        long l = (TextClass.method585(s) << 8) + (long) i;
        Sprite cacheSprite = (Sprite) spriteNodes.get(l);
        if (cacheSprite != null) {
            return cacheSprite;
        }
        try {
            cacheSprite = new Sprite(spriteArchive, s, i);
            spriteNodes.put(cacheSprite, l);
        } catch (Exception exception) {
            return null;
        }
        return cacheSprite;
    }

    public static void getRegularModel(boolean flag, Model model) {
        if (flag) {
            return;
        }
        modelNodes.unlinkAll();
        if (model != null) {
            modelNodes.put(model, (5 << 16));
        }
    }

    public Model getAnimatedModel(int id, int k, boolean enabled) {
        Model animatedModel;
        if (enabled) {
            animatedModel = getModelByType(enabledMediaType, enabledMediaID);
        } else {
            animatedModel = getModelByType(disabledMediaType, disabledMediaID);
        }
        if (animatedModel == null) {
            return null;
        }
        if (k == -1 && id == -1 && animatedModel.triangleColourOrTexture == null) {
            return animatedModel;
        }
        Model model_1 = new Model(9, true, AnimationFrame.isNullFrame(k) & AnimationFrame.isNullFrame(id), false, animatedModel);
        if (k != -1 || id != -1) {
            model_1.createBones((byte) -71);
        }
        if (k != -1) {
            model_1.applyTransform(k, 40542);
        }
        if (id != -1) {
            model_1.applyTransform(id, 40542);
        }
        model_1.light(64, 768, -50, -10, -50, true);
        return model_1;
    }
}