package client.npc;

import client.Client;
import client.model.Model;
import client.util.MemCache;
import client.data.Stream;
import client.animation.AnimationFrame;
import client.data.JagexArchive;
import client.archive.VarBit;

public final class NPCDef {
    
    public int turn90CCWAnimIndex;
    private static int cacheIndex;
    public int varBitID;
    public int turn180AnimIndex;
    public int sessionSettingID;
    private static Stream npcData;
    public int combatLevel;
    public String name;
    public String[] actions;
    public int walkAnimIndex;
    public byte boundDim;
    private int[] recolourTarget;
    private static int[] streamIndices;
    private int[] aditionalModels;
    public int headIcon;
    private int[] recolourOriginal;
    public int idleAnimation;
    public long type;
    public int degreesToTurn;
    private static NPCDef[] cache;
    public static Client clientInstance;
    public int turn90CWAnimIndex;
    public boolean clickable;
    private int lightModifier;
    private int scaleY;
    public boolean drawMinimapDot;
    public int[] childrenIDs;
    public byte[] description;
    private int scaleXZ;
    private int shadowModifier;
    public boolean aBoolean93;
    private int[] npcModels;
    public static MemCache modelCache = new MemCache(30);
    
    NPCDef() {
        turn90CCWAnimIndex = -1;
        varBitID = -1;
        turn180AnimIndex = -1;
        sessionSettingID = -1;
        combatLevel = -1;
        walkAnimIndex = -1;
        boundDim = 1;
        headIcon = -1;
        idleAnimation = -1;
        type = -1L;
        degreesToTurn = 32;
        turn90CWAnimIndex = -1;
        clickable = true;
        scaleY = 128;
        drawMinimapDot = true;
        scaleXZ = 128;
        aBoolean93 = false;
    }
    
    public static NPCDef forID(int itemId) {
        for (int index = 0; index < 20; index++) {
            if (cache[index].type == (long) itemId) {
                return cache[index];
            }
        }
        cacheIndex = (cacheIndex + 1) % 20;
        NPCDef npcDef = cache[cacheIndex] = new NPCDef();
        npcData.currentOffset = streamIndices[itemId];
        npcDef.type = itemId;
        npcDef.readValues(npcData);

        // START OF NPCS - START OF GODWARS BOSSES //

        if (itemId == 2169) {
            npcData.currentOffset = streamIndices[630];
            npcDef.readValues(npcData);
            npcDef.actions = new String[5];
            npcDef.actions[1] = "Attack";
            npcDef.npcModels = new int[2];
            npcDef.npcModels[0] = 5000;
            npcDef.npcModels[1] = 5001;
            npcDef.name = "General graardor";
            npcDef.combatLevel = 624;
            npcDef.description = "A huge war chief of bandos.".getBytes();
        }

        // END OF GOD WARS BOSSES - START OF TITANS //

        if (itemId == 2178) {
            npcData.currentOffset = streamIndices[630];
            npcDef.readValues(npcData);
            npcDef.actions = new String[5];
            npcDef.actions[1] = "Attack";
            npcDef.npcModels = new int[1];
            npcDef.npcModels[0] = 8405;
            npcDef.name = "Steel titan";
            npcDef.combatLevel = 230;
            npcDef.description = "A mighty titan made of steel.".getBytes();
        }
        if (itemId == 2179) {
            npcData.currentOffset = streamIndices[630];
            npcDef.readValues(npcData);
            npcDef.actions = new String[5];
            npcDef.actions[1] = "Attack";
            npcDef.npcModels = new int[1];
            npcDef.npcModels[0] = 8404;
            npcDef.name = "Moss titan";
            npcDef.combatLevel = 152;
            npcDef.description = "A mighty titan made of moss.".getBytes();
        }
        if (itemId == 2180) {
            npcData.currentOffset = streamIndices[630];
            npcDef.readValues(npcData);
            npcDef.actions = new String[5];
            npcDef.actions[1] = "Attack";
            npcDef.npcModels = new int[1];
            npcDef.npcModels[0] = 8403;
            npcDef.name = "Ice titan";
            npcDef.combatLevel = 139;
            npcDef.description = "A mighty titan made of ice.".getBytes();
        }

        // END OF TITANS - START OF REVENANTS //

        if (itemId == 2170) {
            npcData.currentOffset = streamIndices[630];
            npcDef.readValues(npcData);
            npcDef.actions = new String[5];
            npcDef.actions[1] = "Attack";
            npcDef.npcModels = new int[1];
            npcDef.npcModels[0] = 8395;
            npcDef.name = "Revenant knight";
            npcDef.combatLevel = 126;
            npcDef.description = "The ghost of a knight slain during the god wars.".getBytes();
        }
        if (itemId == 2171) {
            npcData.currentOffset = streamIndices[630];
            npcDef.readValues(npcData);
            npcDef.actions = new String[5];
            npcDef.actions[1] = "Attack";
            npcDef.npcModels = new int[1];
            npcDef.npcModels[0] = 8396;
            npcDef.name = "Revenant knight";
            npcDef.combatLevel = 126;
            npcDef.description = "The ghost of a knight slain during the god wars.".getBytes();
        }
        if (itemId == 2172) {
            npcData.currentOffset = streamIndices[630];
            npcDef.readValues(npcData);
            npcDef.actions = new String[5];
            npcDef.actions[1] = "Attack";
            npcDef.npcModels = new int[1];
            npcDef.npcModels[0] = 8397;
            npcDef.name = "Revenant werewolf";
            npcDef.combatLevel = 75;
            npcDef.description = "The ghost of a werewolf slain during the god wars.".getBytes();
        }
        if (itemId == 2173) {
            npcData.currentOffset = streamIndices[630];
            npcDef.readValues(npcData);
            npcDef.actions = new String[5];
            npcDef.actions[1] = "Attack";
            npcDef.npcModels = new int[1];
            npcDef.npcModels[0] = 8398;
            npcDef.name = "Revenant ork";
            npcDef.combatLevel = 105;
            npcDef.description = "The ghost of a ork slain during the god wars.".getBytes();
        }
        if (itemId == 2174) {
            npcData.currentOffset = streamIndices[630];
            npcDef.readValues(npcData);
            npcDef.actions = new String[5];
            npcDef.actions[1] = "Attack";
            npcDef.npcModels = new int[1];
            npcDef.npcModels[0] = 8399;
            npcDef.name = "Revenant hellhound";
            npcDef.combatLevel = 90;
            npcDef.description = "The ghost of a hellhound slain during the god wars.".getBytes();
        }
        if (itemId == 2175) {
            npcData.currentOffset = streamIndices[630];
            npcDef.readValues(npcData);
            npcDef.actions = new String[5];
            npcDef.actions[1] = "Attack";
            npcDef.npcModels = new int[1];
            npcDef.npcModels[0] = 8400;
            npcDef.name = "Revenant dark beast";
            npcDef.combatLevel = 120;
            npcDef.description = "The ghost of a dark beast slain during the god wars.".getBytes();
        }
        if (itemId == 2176) {
            npcData.currentOffset = streamIndices[630];
            npcDef.readValues(npcData);
            npcDef.actions = new String[5];
            npcDef.actions[1] = "Attack";
            npcDef.npcModels = new int[1];
            npcDef.npcModels[0] = 8401;
            npcDef.name = "Revenant imp";
            npcDef.combatLevel = 7;
            npcDef.description = "The ghost of a imp slain during the god wars.".getBytes();
        }
        if (itemId == 2177) {
            npcData.currentOffset = streamIndices[630];
            npcDef.readValues(npcData);
            npcDef.actions = new String[5];
            npcDef.actions[1] = "Attack";
            npcDef.npcModels = new int[1];
            npcDef.npcModels[0] = 8402;
            npcDef.name = "Revenant cyclops";
            npcDef.combatLevel = 82;
            npcDef.description = "The ghost of a cyclops slain during the god wars.".getBytes();
        }

        // END OF REVENANTS - START OF DRAGONS //

        if (itemId == 1596) {
            npcData.currentOffset = streamIndices[630];
            npcDef.readValues(npcData);
            npcDef.actions = new String[5];
            npcDef.actions[1] = "Attack";
            int changedColors = 6;
            npcDef.recolourOriginal = new int[changedColors];
            npcDef.recolourTarget = new int[changedColors];
            npcDef.recolourOriginal[0] = 43280;
            npcDef.recolourTarget[0] = 950;
            npcDef.recolourOriginal[1] = 38172;
            npcDef.recolourTarget[1] = 920;
            npcDef.recolourOriginal[2] = 38177;
            npcDef.recolourTarget[2] = 925;
            npcDef.recolourOriginal[3] = 38057;
            npcDef.recolourTarget[3] = 930;
            npcDef.recolourOriginal[4] = 38065;
            npcDef.recolourTarget[4] = 935;
            npcDef.recolourOriginal[5] = 38090;
            npcDef.recolourTarget[5] = 945;
            npcDef.npcModels = new int[4];
            npcDef.npcModels[0] = 8414;
            npcDef.npcModels[1] = 8415;
            npcDef.npcModels[2] = 8416;
            npcDef.npcModels[3] = 8417;
            npcDef.name = "Dragonite dragon";
            npcDef.combatLevel = 801;
            npcDef.description = "Dragon dragon, is that possible?".getBytes();
        }
        if (itemId == 1595) {
            npcData.currentOffset = streamIndices[630];
            npcDef.readValues(npcData);
            npcDef.actions = new String[5];
            npcDef.actions[1] = "Attack";
            int changedColors = 6;
            npcDef.recolourOriginal = new int[changedColors];
            npcDef.recolourTarget = new int[changedColors];
            npcDef.recolourOriginal[0] = 43280;
            npcDef.recolourTarget[0] = 36160;
            npcDef.recolourOriginal[1] = 38172;
            npcDef.recolourTarget[1] = 36130;
            npcDef.recolourOriginal[2] = 38177;
            npcDef.recolourTarget[2] = 36135;
            npcDef.recolourOriginal[3] = 38057;
            npcDef.recolourTarget[3] = 36140;
            npcDef.recolourOriginal[4] = 38065;
            npcDef.recolourTarget[4] = 36145;
            npcDef.recolourOriginal[5] = 38090;
            npcDef.recolourTarget[5] = 36155;
            npcDef.npcModels = new int[4];
            npcDef.npcModels[0] = 8418;
            npcDef.npcModels[1] = 8419;
            npcDef.npcModels[2] = 8416;
            npcDef.npcModels[3] = 8417;
            npcDef.name = "Runite dragon";
            npcDef.combatLevel = 503;
            npcDef.description = "Expirimenting with rune gone very wrong!".getBytes();
        }
        if (itemId == 1594) {
            npcData.currentOffset = streamIndices[630];
            npcDef.readValues(npcData);
            npcDef.actions = new String[5];
            npcDef.actions[1] = "Attack";
            int changedColors = 6;
            npcDef.recolourOriginal = new int[changedColors];
            npcDef.recolourTarget = new int[changedColors];
            npcDef.recolourOriginal[0] = 43280;
            npcDef.recolourTarget[0] = 21690;
            npcDef.recolourOriginal[1] = 38172;
            npcDef.recolourTarget[1] = 21660;
            npcDef.recolourOriginal[2] = 38177;
            npcDef.recolourTarget[2] = 21665;
            npcDef.recolourOriginal[3] = 38057;
            npcDef.recolourTarget[3] = 21670;
            npcDef.recolourOriginal[4] = 38065;
            npcDef.recolourTarget[4] = 21675;
            npcDef.recolourOriginal[5] = 38090;
            npcDef.recolourTarget[5] = 21685;
            npcDef.npcModels = new int[4];
            npcDef.npcModels[0] = 8414;
            npcDef.npcModels[1] = 8419;
            npcDef.npcModels[2] = 8416;
            npcDef.npcModels[3] = 8417;
            npcDef.name = "Adamant dragon";
            npcDef.combatLevel = 409;
            npcDef.description = "Expirimenting with adamant gone wrong!".getBytes();
        }
        if (itemId == 1593) {
            npcData.currentOffset = streamIndices[630];
            npcDef.readValues(npcData);
            npcDef.actions = new String[5];
            npcDef.actions[1] = "Attack";
            npcDef.npcModels = new int[4];
            npcDef.npcModels[0] = 8420;
            npcDef.npcModels[1] = 8419;
            npcDef.npcModels[2] = 8416;
            npcDef.npcModels[3] = 8417;
            npcDef.name = "Mithril dragon";
            npcDef.combatLevel = 304;
            npcDef.description = "Expirimenting with mithril gone bad!".getBytes();
        }
        if (itemId == 1592) {
            npcData.currentOffset = streamIndices[630];
            npcDef.readValues(npcData);
            npcDef.actions = new String[5];
            npcDef.actions[1] = "Attack";
            int changedColors = 6;
            npcDef.recolourOriginal = new int[changedColors];
            npcDef.recolourTarget = new int[changedColors];
            npcDef.recolourOriginal[0] = 43280;
            npcDef.recolourTarget[0] = 30;
            npcDef.recolourOriginal[1] = 38172;
            npcDef.recolourTarget[1] = 40;
            npcDef.recolourOriginal[2] = 38177;
            npcDef.recolourTarget[2] = 45;
            npcDef.recolourOriginal[3] = 38057;
            npcDef.recolourTarget[3] = 50;
            npcDef.recolourOriginal[4] = 38065;
            npcDef.recolourTarget[4] = 55;
            npcDef.recolourOriginal[5] = 38090;
            npcDef.recolourTarget[5] = 65;
            npcDef.npcModels = new int[4];
            npcDef.npcModels[0] = 8414;
            npcDef.npcModels[1] = 8419;
            npcDef.npcModels[2] = 8421;
            npcDef.npcModels[3] = 8417;
            npcDef.name = "Steel dragon";
            npcDef.combatLevel = 246;
            npcDef.description = "It's scales seem to be made of steel.".getBytes();
        }
        if (itemId == 1591) {
            npcData.currentOffset = streamIndices[630];
            npcDef.readValues(npcData);
            npcDef.actions = new String[5];
            npcDef.actions[1] = "Attack";
            int changedColors = 6;
            npcDef.recolourOriginal = new int[changedColors];
            npcDef.recolourTarget = new int[changedColors];
            npcDef.recolourOriginal[0] = 43280;
            npcDef.recolourTarget[0] = 10;
            npcDef.recolourOriginal[1] = 38172;
            npcDef.recolourTarget[1] = 20;
            npcDef.recolourOriginal[2] = 38177;
            npcDef.recolourTarget[2] = 25;
            npcDef.recolourOriginal[3] = 38057;
            npcDef.recolourTarget[3] = 30;
            npcDef.recolourOriginal[4] = 38065;
            npcDef.recolourTarget[4] = 35;
            npcDef.recolourOriginal[5] = 38090;
            npcDef.recolourTarget[5] = 45;
            npcDef.npcModels = new int[4];
            npcDef.npcModels[0] = 8420;
            npcDef.npcModels[1] = 8415;
            npcDef.npcModels[2] = 8422;
            npcDef.npcModels[3] = 8417;
            npcDef.name = "Iron dragon";
            npcDef.combatLevel = 189;
            npcDef.description = "It's scales seem to be made of iron.".getBytes();
        }
        if (itemId == 1590) {
            npcData.currentOffset = streamIndices[630];
            npcDef.readValues(npcData);
            npcDef.actions = new String[5];
            npcDef.actions[1] = "Attack";
            int changedColors = 6;
            npcDef.recolourOriginal = new int[changedColors];
            npcDef.recolourTarget = new int[changedColors];
            npcDef.recolourOriginal[0] = 43280;
            npcDef.recolourTarget[0] = 5640;
            npcDef.recolourOriginal[1] = 38172;
            npcDef.recolourTarget[1] = 5650;
            npcDef.recolourOriginal[2] = 38177;
            npcDef.recolourTarget[2] = 5655;
            npcDef.recolourOriginal[3] = 38057;
            npcDef.recolourTarget[3] = 5660;
            npcDef.recolourOriginal[4] = 38065;
            npcDef.recolourTarget[4] = 5665;
            npcDef.recolourOriginal[5] = 38090;
            npcDef.recolourTarget[5] = 5675;
            npcDef.npcModels = new int[4];
            npcDef.npcModels[0] = 8418;
            npcDef.npcModels[1] = 8423;
            npcDef.npcModels[2] = 8416;
            npcDef.npcModels[3] = 8417;
            npcDef.name = "Bronze dragon";
            npcDef.combatLevel = 131;
            npcDef.description = "It's scales seem to be made of bronze.".getBytes();
        }
        if (itemId == 51) {
            npcData.currentOffset = streamIndices[630];
            npcDef.readValues(npcData);
            npcDef.actions = new String[5];
            npcDef.actions[1] = "Attack";
            int changedColors = 6;
            npcDef.recolourOriginal = new int[changedColors];
            npcDef.recolourTarget = new int[changedColors];
            npcDef.recolourOriginal[0] = 2469;
            npcDef.recolourTarget[0] = 40;
            npcDef.recolourOriginal[1] = 2588;
            npcDef.recolourTarget[1] = 35;
            npcDef.recolourOriginal[2] = 910;
            npcDef.recolourTarget[2] = 15;
            npcDef.recolourOriginal[3] = 912;
            npcDef.recolourTarget[3] = 20;
            npcDef.recolourOriginal[4] = 1938;
            npcDef.recolourTarget[4] = 25;
            npcDef.recolourOriginal[5] = 1814;
            npcDef.recolourTarget[5] = 30;
            npcDef.npcModels = new int[4];
            npcDef.npcModels[0] = 8424;
            npcDef.npcModels[1] = 8425;
            npcDef.npcModels[2] = 8426;
            npcDef.npcModels[3] = 8427;
            npcDef.name = "Baby black dragon";
            npcDef.combatLevel = 83;
            npcDef.description = "Cute and young, but still dangerous.".getBytes();
        }
        if (itemId == 52) {
            npcData.currentOffset = streamIndices[630];
            npcDef.readValues(npcData);
            npcDef.actions = new String[5];
            npcDef.actions[1] = "Attack";
            int changedColors = 6;
            npcDef.recolourOriginal = new int[changedColors];
            npcDef.recolourTarget = new int[changedColors];
            npcDef.recolourOriginal[0] = 2469;
            npcDef.recolourTarget[0] = 43940;
            npcDef.recolourOriginal[1] = 2588;
            npcDef.recolourTarget[1] = 43935;
            npcDef.recolourOriginal[2] = 910;
            npcDef.recolourTarget[2] = 43915;
            npcDef.recolourOriginal[3] = 912;
            npcDef.recolourTarget[3] = 43920;
            npcDef.recolourOriginal[4] = 1938;
            npcDef.recolourTarget[4] = 43925;
            npcDef.recolourOriginal[5] = 1814;
            npcDef.recolourTarget[5] = 43930;
            npcDef.npcModels = new int[4];
            npcDef.npcModels[0] = 8424;
            npcDef.npcModels[1] = 8425;
            npcDef.npcModels[2] = 8426;
            npcDef.npcModels[3] = 8427;
            npcDef.name = "Baby blue dragon";
            npcDef.combatLevel = 48;
            npcDef.description = "Cute and young, but still dangerous.".getBytes();
        }
        if (itemId == 1589) {
            npcData.currentOffset = streamIndices[630];
            npcDef.readValues(npcData);
            npcDef.actions = new String[5];
            npcDef.actions[1] = "Attack";
            npcDef.npcModels = new int[4];
            npcDef.npcModels[0] = 8424;
            npcDef.npcModels[1] = 8425;
            npcDef.npcModels[2] = 8426;
            npcDef.npcModels[3] = 8427;
            npcDef.name = "Baby red dragon";
            npcDef.combatLevel = 48;
            npcDef.description = "Cute and young, but still dangerous.".getBytes();
        }
        if (itemId == 54) {
            npcData.currentOffset = streamIndices[630];
            npcDef.readValues(npcData);
            npcDef.actions = new String[5];
            npcDef.actions[1] = "Attack";
            int changedColors = 6;
            npcDef.recolourOriginal = new int[changedColors];
            npcDef.recolourTarget = new int[changedColors];
            npcDef.recolourOriginal[0] = 2469;
            npcDef.recolourTarget[0] = 20;
            npcDef.recolourOriginal[1] = 2588;
            npcDef.recolourTarget[1] = 15;
            npcDef.recolourOriginal[2] = 910;
            npcDef.recolourTarget[2] = 3;
            npcDef.recolourOriginal[3] = 912;
            npcDef.recolourTarget[3] = 5;
            npcDef.recolourOriginal[4] = 1938;
            npcDef.recolourTarget[4] = 7;
            npcDef.recolourOriginal[5] = 1814;
            npcDef.recolourTarget[5] = 10;
            npcDef.npcModels = new int[4];
            npcDef.npcModels[0] = 8448;
            npcDef.npcModels[1] = 8449;
            npcDef.npcModels[2] = 8447;
            npcDef.npcModels[3] = 8450;
            npcDef.name = "Black dragon";
            npcDef.combatLevel = 227;
            npcDef.description = "A fierce dragon with black scales!".getBytes();
        }
        if (itemId == 55) {
            npcData.currentOffset = streamIndices[630];
            npcDef.readValues(npcData);
            npcDef.actions = new String[5];
            npcDef.actions[1] = "Attack";
            int changedColors = 6;
            npcDef.recolourOriginal = new int[changedColors];
            npcDef.recolourTarget = new int[changedColors];
            npcDef.recolourOriginal[0] = 2469;
            npcDef.recolourTarget[0] = 43940;
            npcDef.recolourOriginal[1] = 2588;
            npcDef.recolourTarget[1] = 43935;
            npcDef.recolourOriginal[2] = 910;
            npcDef.recolourTarget[2] = 43915;
            npcDef.recolourOriginal[3] = 912;
            npcDef.recolourTarget[3] = 43920;
            npcDef.recolourOriginal[4] = 1938;
            npcDef.recolourTarget[4] = 43925;
            npcDef.recolourOriginal[5] = 1814;
            npcDef.recolourTarget[5] = 43930;
            npcDef.npcModels = new int[4];
            npcDef.npcModels[0] = 8448;
            npcDef.npcModels[1] = 8449;
            npcDef.npcModels[2] = 8447;
            npcDef.npcModels[3] = 8450;
            npcDef.name = "Blue dragon";
            npcDef.combatLevel = 111;
            npcDef.description = "A mother dragon.".getBytes();
        }
        if (itemId == 53) {
            npcData.currentOffset = streamIndices[630];
            npcDef.readValues(npcData);
            npcDef.actions = new String[5];
            npcDef.actions[1] = "Attack";
            npcDef.npcModels = new int[4];
            npcDef.npcModels[0] = 8448;
            npcDef.npcModels[1] = 8449;
            npcDef.npcModels[2] = 8447;
            npcDef.npcModels[3] = 8450;
            npcDef.name = "Red dragon";
            npcDef.combatLevel = 152;
            npcDef.description = "A huge powerful dragon.".getBytes();
        }
        if (itemId == 941) {
            npcData.currentOffset = streamIndices[630];
            npcDef.readValues(npcData);
            npcDef.actions = new String[5];
            npcDef.actions[1] = "Attack";
            npcDef.npcModels = new int[4];
            npcDef.npcModels[0] = 8451;
            npcDef.npcModels[1] = 8452;
            npcDef.npcModels[2] = 8453;
            npcDef.npcModels[3] = 8450;
            npcDef.name = "Green dragon";
            npcDef.combatLevel = 79;
            npcDef.description = "Must be relasted to Elvarg.".getBytes();
        }
        if (itemId == 50) {
            npcData.currentOffset = streamIndices[630];
            npcDef.readValues(npcData);
            npcDef.actions = new String[5];
            npcDef.actions[1] = "Attack";
            npcDef.npcModels = new int[6];
            npcDef.npcModels[0] = 8454;
            npcDef.npcModels[1] = 8455;
            npcDef.npcModels[2] = 8456;
            npcDef.npcModels[3] = 8457;
            npcDef.npcModels[4] = 8458;
            npcDef.npcModels[5] = 8459;
            npcDef.name = "King black dragon";
            npcDef.combatLevel = 279;
            npcDef.description = "A fierce dragon with black scales and three heads.".getBytes();
        }

        // END OF DRAGONS - STAR OF KALPHITES //

        // END OF KALPHITES - STAR OF OTHER NPCS //

        if (itemId == 1582) {
            npcData.currentOffset = streamIndices[630];
            npcDef.readValues(npcData);
            npcDef.actions = new String[5];
            npcDef.actions[1] = "Attack";
            npcDef.npcModels = new int[3];
            npcDef.npcModels[0] = 8433;
            npcDef.npcModels[1] = 8434;
            npcDef.npcModels[2] = 8430;
            npcDef.name = "Fire giant";
            npcDef.combatLevel = 86;
            npcDef.description = "A very large elemental adversary.".getBytes();
        }
        if (itemId == 1583) {
            npcData.currentOffset = streamIndices[630];
            npcDef.readValues(npcData);
            npcDef.actions = new String[5];
            npcDef.actions[1] = "Attack";
            npcDef.npcModels = new int[3];
            npcDef.npcModels[0] = 8433;
            npcDef.npcModels[1] = 8434;
            npcDef.npcModels[2] = 8430;
            npcDef.name = "Fire giant";
            npcDef.combatLevel = 85;
            npcDef.description = "A very large elemental adversary.".getBytes();
        }
        if (itemId == 1584) {
            npcData.currentOffset = streamIndices[630];
            npcDef.readValues(npcData);
            npcDef.actions = new String[5];
            npcDef.actions[1] = "Attack";
            npcDef.npcModels = new int[3];
            npcDef.npcModels[0] = 8433;
            npcDef.npcModels[1] = 8434;
            npcDef.npcModels[2] = 8430;
            npcDef.name = "Fire giant";
            npcDef.combatLevel = 86;
            npcDef.description = "A very large elemental adversary.".getBytes();
        }
        if (itemId == 1585) {
            npcData.currentOffset = streamIndices[630];
            npcDef.readValues(npcData);
            npcDef.actions = new String[5];
            npcDef.actions[1] = "Attack";
            npcDef.npcModels = new int[3];
            npcDef.npcModels[0] = 8433;
            npcDef.npcModels[1] = 8434;
            npcDef.npcModels[2] = 8430;
            npcDef.name = "Fire giant";
            npcDef.combatLevel = 89;
            npcDef.description = "A very large elemental adversary.".getBytes();
        }
        if (itemId == 1586) {
            npcData.currentOffset = streamIndices[630];
            npcDef.readValues(npcData);
            npcDef.actions = new String[5];
            npcDef.actions[1] = "Attack";
            npcDef.npcModels = new int[3];
            npcDef.npcModels[0] = 8433;
            npcDef.npcModels[1] = 8434;
            npcDef.npcModels[2] = 8430;
            npcDef.name = "Fire giant";
            npcDef.combatLevel = 86;
            npcDef.description = "A very large elemental adversary.".getBytes();
        }
        if (itemId == 110) {
            npcData.currentOffset = streamIndices[630];
            npcDef.readValues(npcData);
            npcDef.actions = new String[5];
            npcDef.actions[1] = "Attack";
            npcDef.npcModels = new int[3];
            npcDef.npcModels[0] = 8433;
            npcDef.npcModels[1] = 8434;
            npcDef.npcModels[2] = 8430;
            npcDef.name = "Fire giant";
            npcDef.combatLevel = 81;
            npcDef.description = "A very large elemental adversary.".getBytes();
        }
        if (itemId == 117) {
            npcData.currentOffset = streamIndices[630];
            npcDef.readValues(npcData);
            npcDef.actions = new String[5];
            npcDef.actions[1] = "Attack";
            npcDef.npcModels = new int[5];
            npcDef.npcModels[0] = 8435;
            npcDef.npcModels[1] = 8436;
            npcDef.npcModels[2] = 8430;
            npcDef.npcModels[3] = 8437;
            npcDef.npcModels[4] = 8438;
            npcDef.name = "Hill giant";
            npcDef.combatLevel = 28;
            npcDef.description = "A very large foe.".getBytes();
        }
        if (itemId == 111) {
            npcData.currentOffset = streamIndices[630];
            npcDef.readValues(npcData);
            npcDef.actions = new String[5];
            npcDef.actions[1] = "Attack";
            npcDef.npcModels = new int[3];
            npcDef.npcModels[0] = 8431;
            npcDef.npcModels[1] = 8432;
            npcDef.npcModels[2] = 8430;
            npcDef.name = "Ice giant";
            npcDef.combatLevel = 53;
            npcDef.description = "He's got icicles in his beard.".getBytes();
        }
        if (itemId == 112) {
            npcData.currentOffset = streamIndices[630];
            npcDef.readValues(npcData);
            npcDef.actions = new String[5];
            npcDef.actions[1] = "Attack";
            npcDef.npcModels = new int[3];
            npcDef.npcModels[0] = 8428;
            npcDef.npcModels[1] = 8429;
            npcDef.npcModels[2] = 8430;
            npcDef.name = "Moss giant";
            npcDef.combatLevel = 42;
            npcDef.description = "His beard seems to have a life of it's own.".getBytes();
        }
        if (itemId == 1587) {
            npcData.currentOffset = streamIndices[630];
            npcDef.readValues(npcData);
            npcDef.actions = new String[5];
            npcDef.actions[1] = "Attack";
            npcDef.npcModels = new int[3];
            npcDef.npcModels[0] = 8428;
            npcDef.npcModels[1] = 8429;
            npcDef.npcModels[2] = 8430;
            npcDef.name = "Moss giant";
            npcDef.combatLevel = 42;
            npcDef.description = "His beard seems to have a life of it's own.".getBytes();
        }
        if (itemId == 1588) {
            npcData.currentOffset = streamIndices[630];
            npcDef.readValues(npcData);
            npcDef.actions = new String[5];
            npcDef.actions[1] = "Attack";
            npcDef.npcModels = new int[3];
            npcDef.npcModels[0] = 8428;
            npcDef.npcModels[1] = 8429;
            npcDef.npcModels[2] = 8430;
            npcDef.name = "Moss giant";
            npcDef.combatLevel = 42;
            npcDef.description = "His beard seems to have a life of it's own.".getBytes();
        }
        if (itemId == 1681) {
            npcData.currentOffset = streamIndices[630];
            npcDef.readValues(npcData);
            npcDef.actions = new String[5];
            npcDef.actions[1] = "Attack";
            npcDef.npcModels = new int[3];
            npcDef.npcModels[0] = 8428;
            npcDef.npcModels[1] = 8429;
            npcDef.npcModels[2] = 8430;
            npcDef.name = "Moss giant";
            npcDef.combatLevel = 84;
            npcDef.description = "His beard seems to have a life of it's own.".getBytes();
        }
        if (itemId == 83) {
            npcData.currentOffset = streamIndices[630];
            npcDef.readValues(npcData);
            npcDef.actions = new String[5];
            npcDef.actions[1] = "Attack";
            npcDef.npcModels = new int[5];
            npcDef.npcModels[0] = 8442;
            npcDef.npcModels[1] = 8443;
            npcDef.npcModels[2] = 8444;
            npcDef.npcModels[3] = 8445;
            npcDef.npcModels[4] = 8446;
            npcDef.name = "Greater demon";
            npcDef.combatLevel = 92;
            npcDef.description = "Big. red, and incredibly evil.".getBytes();
        }
        if (itemId == 752) {
            npcData.currentOffset = streamIndices[630];
            npcDef.readValues(npcData);
            npcDef.actions = new String[5];
            npcDef.actions[1] = "Attack";
            npcDef.npcModels = new int[3];
            npcDef.npcModels[0] = 8439;
            npcDef.npcModels[1] = 8440;
            npcDef.npcModels[2] = 8441;
            npcDef.name = "Lesser demon";
            npcDef.combatLevel = 82;
            npcDef.description = "Lesser, but still pretty huge.".getBytes();
        }
        if (itemId == 82) {
            npcData.currentOffset = streamIndices[630];
            npcDef.readValues(npcData);
            npcDef.actions = new String[5];
            npcDef.actions[1] = "Attack";
            npcDef.npcModels = new int[3];
            npcDef.npcModels[0] = 8439;
            npcDef.npcModels[1] = 8440;
            npcDef.npcModels[2] = 8441;
            npcDef.name = "Lesser demon";
            npcDef.combatLevel = 82;
            npcDef.description = "Lesser, but still pretty huge.".getBytes();
        }
        if (itemId == 2783) {
            npcData.currentOffset = streamIndices[630];
            npcDef.readValues(npcData);
            npcDef.actions = new String[5];
            npcDef.actions[1] = "Attack";
            npcDef.npcModels = new int[1];
            npcDef.npcModels[0] = 8413;
            npcDef.name = "Dark beast";
            npcDef.combatLevel = 139;
            npcDef.description = "A beast from the dark dimension.".getBytes();
        }
        if (itemId == 2181) {
            npcData.currentOffset = streamIndices[630];
            npcDef.readValues(npcData);
            npcDef.actions = new String[5];
            npcDef.actions[1] = "Attack";
            npcDef.npcModels = new int[7];
            int changedColors = 1;
            npcDef.npcModels[0] = 4909;
            npcDef.npcModels[1] = 4911;
            npcDef.npcModels[2] = 4912;
            npcDef.npcModels[3] = 4913;
            npcDef.npcModels[4] = 4914;
            npcDef.npcModels[5] = 4915;
            npcDef.npcModels[6] = 4916;
            npcDef.name = "Pirate";
            npcDef.combatLevel = 76;
            npcDef.description = "Arrgh, examain info.".getBytes();
        }


        // END OF OTHER NPCS - END OF NPCS //

        return npcDef;
    }

    public final Model getHeadModel() {
        if (childrenIDs != null) {
            NPCDef npcDef = method161();
            if (npcDef == null) {
                return null;
            } else {
                return npcDef.getHeadModel();
            }
        }
        if (aditionalModels == null) {
            return null;
        }
        boolean modelMissing = false;
        for (int i = 0; i < aditionalModels.length; i++) {
            if (!Model.isCached(aditionalModels[i])) {
                modelMissing = true;
            }
        }
        if (modelMissing) {
            return null;
        }
        Model additionalModels[] = new Model[aditionalModels.length];
        for (int j = 0; j < aditionalModels.length; j++) {
            additionalModels[j] = Model.getModel(aditionalModels[j]);
        }
        Model model;
        if (additionalModels.length == 1) {
            model = additionalModels[0];
        } else {
            model = new Model(additionalModels.length, additionalModels);
        }
        if (recolourOriginal != null) {
            for (int k = 0; k < recolourOriginal.length; k++) {
                model.reColour(recolourOriginal[k], recolourTarget[k]);
            }
        }
        return model;
    }

    public final NPCDef method161() {
        int j = -1;
        if (varBitID != -1) {
            VarBit varBit = VarBit.cache[varBitID];
            int k = varBit.configId;
            int l = varBit.leastSignificantBit;
            int i1 = varBit.mostSignificantBit;
            int j1 = Client.BITFIELD_MAX_VALUE[i1 - l];
            j = clientInstance.sessionSettings[k] >> l & j1;
        } else {
            if (sessionSettingID != -1) {
                j = clientInstance.sessionSettings[sessionSettingID];
            }
        }
        if (j < 0 || j >= childrenIDs.length || childrenIDs[j] == -1) {
            return null;
        } else {
            return forID(childrenIDs[j]);
        }
    }

    public static void unpackConfig(JagexArchive jagexArchive) {
        npcData = new Stream(jagexArchive.getDataForName("npc.dat"));
        Stream indexData = new Stream(jagexArchive.getDataForName("npc.idx"));
        int totalNPCs = indexData.readUnsignedWord();
        streamIndices = new int[totalNPCs];
        int i = 2;
        for (int npcPtr = 0; npcPtr < totalNPCs; npcPtr++) {
            streamIndices[npcPtr] = i;
            i += indexData.readUnsignedWord();
        }
        cache = new NPCDef[20];
        for (int k = 0; k < 20; k++) {
            cache[k] = new NPCDef();
        }
    }

    public static void nullLoader() {
        modelCache = null;
        streamIndices = null;
        cache = null;
        npcData = null;
    }

    public final Model method164(int frameId2, int frameId, int framesFrom2[]) {
        if (childrenIDs != null) {
            NPCDef npcDef = method161();
            if (npcDef == null) {
                return null;
            } else {
                return npcDef.method164(frameId2, frameId, framesFrom2);
            }
        }
        Model model = (Model) modelCache.get(type);
        if (model == null) {
            boolean modelMissing = false;
            for (int i1 = 0; i1 < npcModels.length; i1++) {
                if (!Model.isCached(npcModels[i1])) {
                    modelMissing = true;
                }
            }
            if (modelMissing) {
                return null;
            }
            Model models[] = new Model[npcModels.length];
            for (int j1 = 0; j1 < npcModels.length; j1++) {
                models[j1] = Model.getModel(npcModels[j1]);
            }
            if (models.length == 1) {
                model = models[0];
            } else {
                model = new Model(models.length, models);
            }
            if (recolourOriginal != null) {
                for (int k1 = 0; k1 < recolourOriginal.length; k1++) {
                    model.reColour(recolourOriginal[k1], recolourTarget[k1]);
                }
            }
            model.createBones((byte) -71);
            model.light(64 + lightModifier, 850 + shadowModifier , -30, -50, -30, true);
            modelCache.put(model, type);
        }
        Model model_1 = Model.aModel_1621;
        model_1.method464(model, AnimationFrame.isNullFrame(frameId) & AnimationFrame.isNullFrame(frameId2));
        if (frameId != -1 && frameId2 != -1) {
            model_1.mixAnimationFrames(-20491, framesFrom2, frameId2, frameId);
        } else if (frameId != -1) {
            model_1.applyTransform(frameId, 40542);
        }
        if (scaleXZ != 128 || scaleY != 128) {
            model_1.scaleT(scaleXZ, scaleXZ,  scaleY);
        }
        model_1.calculateDiagonals();
        model_1.triangleSkin = null;
        model_1.vertexSkin = null;
        if (boundDim == 1) {
            model_1.oneSquareModel = true;
        }
        return model_1;
    }

    private void readValues(Stream stream) {
        do {
            int i = stream.readUnsignedByte();
            if (i == 0) {
                return;
            }
            if (i == 1) {
                int j = stream.readUnsignedByte();
                npcModels = new int[j];
                for (int j1 = 0; j1 < j; j1++) {
                    npcModels[j1] = stream.readUnsignedWord();
                }
            } else if (i == 2) {
                name = stream.readString();
            } else if (i == 3) {
                description = stream.readBytes();
            } else if (i == 12) {
                boundDim = stream.readSignedByte();
            } else if (i == 13) {
                idleAnimation = stream.readUnsignedWord();
            } else if (i == 14) {
                walkAnimIndex = stream.readUnsignedWord();
            } else if (i == 17) {
                walkAnimIndex = stream.readUnsignedWord();
                turn180AnimIndex = stream.readUnsignedWord();
                turn90CWAnimIndex = stream.readUnsignedWord();
                turn90CCWAnimIndex = stream.readUnsignedWord();
            } else if (i >= 30 && i < 40) {
                if (actions == null) {
                    actions = new String[5];
                }
                actions[i - 30] = stream.readString();
                if (actions[i - 30].equalsIgnoreCase("hidden")) {
                    actions[i - 30] = null;
                }
            } else if (i == 40) {
                int k = stream.readUnsignedByte();
                recolourOriginal = new int[k];
                recolourTarget = new int[k];
                for (int k1 = 0; k1 < k; k1++) {
                    recolourOriginal[k1] = stream.readUnsignedWord();
                    recolourTarget[k1] = stream.readUnsignedWord();
                }
            } else if (i == 60) {
                int l = stream.readUnsignedByte();
                aditionalModels = new int[l];
                for (int l1 = 0; l1 < l; l1++) {
                    aditionalModels[l1] = stream.readUnsignedWord();
                }
            } else if (i == 90) {
                stream.readUnsignedWord();
            } else if (i == 91) {
                stream.readUnsignedWord();
            } else if (i == 92) {
                stream.readUnsignedWord();
            } else if (i == 93) {
                drawMinimapDot = false;
            } else if (i == 95) {
                combatLevel = stream.readUnsignedWord();
            } else if (i == 97) {
                scaleXZ = stream.readUnsignedWord();
            } else if (i == 98) {
                scaleY = stream.readUnsignedWord();
            } else if (i == 99) { //should be isVisible but method146 is wrong?
                aBoolean93 = true;
            } else if (i == 100) {
                lightModifier = stream.readSignedByte();
            } else if (i == 101) {
                shadowModifier  = stream.readSignedByte() * 5;
            } else if (i == 102) {
                headIcon = stream.readUnsignedWord();
            } else if (i == 103) {
                degreesToTurn = stream.readUnsignedWord();
            } else if (i == 106) {
                varBitID = stream.readUnsignedWord();
                if (varBitID == 65535) {
                    varBitID = -1;
                }
                sessionSettingID = stream.readUnsignedWord();
                if (sessionSettingID == 65535) {
                    sessionSettingID = -1;
                }
                int i1 = stream.readUnsignedByte();
                childrenIDs = new int[i1 + 1];
                for (int i2 = 0; i2 <= i1; i2++) {
                    childrenIDs[i2] = stream.readUnsignedWord();
                    if (childrenIDs[i2] == 65535) {
                        childrenIDs[i2] = -1;
                    }
                }
            } else if (i == 107) {
                clickable = false;
            }
        } while (true);
    }
}
