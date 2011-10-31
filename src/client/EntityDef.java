package client;

final class EntityDef {
    
    public int anInt55;
    private static int anInt56;
    public int anInt57;
    public int anInt58;
    public int anInt59;
    private static Stream stream;
    public int combatLevel;
    public static int anInt62;
    private int anInt63;
    private int anInt64;
    public String name;
    public String actions[];
    public int anInt67;
    public byte aByte68;
    private int anInt69;
    private int anIntArray70[];
    public int anInt71;
    private static int streamIndices[];
    private int anIntArray73[];
    private static int anInt74 = 748;
    public int anInt75;
    private int anIntArray76[];
    public int anInt77;
    public long type;
    public int anInt79;
    private static EntityDef cache[];
    private boolean aBoolean81;
    public static client clientInstance;
    public int anInt83;
    public boolean aBoolean84;
    private int anInt85;
    private int anInt86;
    public boolean aBoolean87;
    public int childrenIDs[];
    public byte description[];
    public int anInt90;
    private int anInt91;
    private int anInt92;
    public boolean aBoolean93;
    private int modelArray[];
    public static MRUNodes mruNodes = new MRUNodes(30);
    public int anInt96;
    
    EntityDef() {
        anInt55 = -1;
        anInt57 = -1;
        anInt58 = -1;
        anInt59 = -1;
        combatLevel = -1;
        anInt63 = 9;
        anInt64 = 1834;
        anInt67 = -1;
        aByte68 = 1;
        anInt69 = 9;
        anInt71 = -1;
        anInt75 = -1;
        anInt77 = -1;
        type = -1L;
        anInt79 = 32;
        aBoolean81 = false;
        anInt83 = -1;
        aBoolean84 = true;
        anInt86 = 128;
        aBoolean87 = true;
        anInt90 = -1;
        anInt91 = 128;
        aBoolean93 = false;
        anInt96 = -1;
    }
    
    public static final EntityDef forID(int i) {
        for (int j = 0; j < 20; j++) {
            if (cache[j].type == (long) i) {
                return cache[j];
            }
        }
        anInt56 = (anInt56 + 1) % 20;
        EntityDef class5 = cache[anInt56] = new EntityDef();
        stream.currentOffset = streamIndices[i];
        class5.type = i;
        class5.readValues(stream);

        // START OF NPCS - START OF GODWARS BOSSES //

        if (i == 2169) {
            stream.currentOffset = streamIndices[630];
            class5.readValues(stream);
            class5.actions = new String[5];
            class5.actions[1] = "Attack";
            class5.modelArray = new int[2];
            class5.modelArray[0] = 5000;
            class5.modelArray[1] = 5001;
            class5.name = "General graardor";
            class5.combatLevel = 624;
            class5.description = "A huge war chief of bandos.".getBytes();
        }

        // END OF GOD WARS BOSSES - START OF TITANS //

        if (i == 2178) {
            stream.currentOffset = streamIndices[630];
            class5.readValues(stream);
            class5.actions = new String[5];
            class5.actions[1] = "Attack";
            class5.modelArray = new int[1];
            class5.modelArray[0] = 8405;
            class5.name = "Steel titan";
            class5.combatLevel = 230;
            class5.description = "A mighty titan made of steel.".getBytes();
        }
        if (i == 2179) {
            stream.currentOffset = streamIndices[630];
            class5.readValues(stream);
            class5.actions = new String[5];
            class5.actions[1] = "Attack";
            class5.modelArray = new int[1];
            class5.modelArray[0] = 8404;
            class5.name = "Moss titan";
            class5.combatLevel = 152;
            class5.description = "A mighty titan made of moss.".getBytes();
        }
        if (i == 2180) {
            stream.currentOffset = streamIndices[630];
            class5.readValues(stream);
            class5.actions = new String[5];
            class5.actions[1] = "Attack";
            class5.modelArray = new int[1];
            class5.modelArray[0] = 8403;
            class5.name = "Ice titan";
            class5.combatLevel = 139;
            class5.description = "A mighty titan made of ice.".getBytes();
        }

        // END OF TITANS - START OF REVENANTS //

        if (i == 2170) {
            stream.currentOffset = streamIndices[630];
            class5.readValues(stream);
            class5.actions = new String[5];
            class5.actions[1] = "Attack";
            class5.modelArray = new int[1];
            class5.modelArray[0] = 8395;
            class5.name = "Revenant knight";
            class5.combatLevel = 126;
            class5.description = "The ghost of a knight slain during the god wars.".getBytes();
        }
        if (i == 2171) {
            stream.currentOffset = streamIndices[630];
            class5.readValues(stream);
            class5.actions = new String[5];
            class5.actions[1] = "Attack";
            class5.modelArray = new int[1];
            class5.modelArray[0] = 8396;
            class5.name = "Revenant knight";
            class5.combatLevel = 126;
            class5.description = "The ghost of a knight slain during the god wars.".getBytes();
        }
        if (i == 2172) {
            stream.currentOffset = streamIndices[630];
            class5.readValues(stream);
            class5.actions = new String[5];
            class5.actions[1] = "Attack";
            class5.modelArray = new int[1];
            class5.modelArray[0] = 8397;
            class5.name = "Revenant werewolf";
            class5.combatLevel = 75;
            class5.description = "The ghost of a werewolf slain during the god wars.".getBytes();
        }
        if (i == 2173) {
            stream.currentOffset = streamIndices[630];
            class5.readValues(stream);
            class5.actions = new String[5];
            class5.actions[1] = "Attack";
            class5.modelArray = new int[1];
            class5.modelArray[0] = 8398;
            class5.name = "Revenant ork";
            class5.combatLevel = 105;
            class5.description = "The ghost of a ork slain during the god wars.".getBytes();
        }
        if (i == 2174) {
            stream.currentOffset = streamIndices[630];
            class5.readValues(stream);
            class5.actions = new String[5];
            class5.actions[1] = "Attack";
            class5.modelArray = new int[1];
            class5.modelArray[0] = 8399;
            class5.name = "Revenant hellhound";
            class5.combatLevel = 90;
            class5.description = "The ghost of a hellhound slain during the god wars.".getBytes();
        }
        if (i == 2175) {
            stream.currentOffset = streamIndices[630];
            class5.readValues(stream);
            class5.actions = new String[5];
            class5.actions[1] = "Attack";
            class5.modelArray = new int[1];
            class5.modelArray[0] = 8400;
            class5.name = "Revenant dark beast";
            class5.combatLevel = 120;
            class5.description = "The ghost of a dark beast slain during the god wars.".getBytes();
        }
        if (i == 2176) {
            stream.currentOffset = streamIndices[630];
            class5.readValues(stream);
            class5.actions = new String[5];
            class5.actions[1] = "Attack";
            class5.modelArray = new int[1];
            class5.modelArray[0] = 8401;
            class5.name = "Revenant imp";
            class5.combatLevel = 7;
            class5.description = "The ghost of a imp slain during the god wars.".getBytes();
        }
        if (i == 2177) {
            stream.currentOffset = streamIndices[630];
            class5.readValues(stream);
            class5.actions = new String[5];
            class5.actions[1] = "Attack";
            class5.modelArray = new int[1];
            class5.modelArray[0] = 8402;
            class5.name = "Revenant cyclops";
            class5.combatLevel = 82;
            class5.description = "The ghost of a cyclops slain during the god wars.".getBytes();
        }

        // END OF REVENANTS - START OF DRAGONS //

        if (i == 1596) {
            stream.currentOffset = streamIndices[630];
            class5.readValues(stream);
            class5.actions = new String[5];
            class5.actions[1] = "Attack";
            int changedColors = 6;
            class5.anIntArray76 = new int[changedColors];
            class5.anIntArray70 = new int[changedColors];
            class5.anIntArray76[0] = 43280;
            class5.anIntArray70[0] = 950;
            class5.anIntArray76[1] = 38172;
            class5.anIntArray70[1] = 920;
            class5.anIntArray76[2] = 38177;
            class5.anIntArray70[2] = 925;
            class5.anIntArray76[3] = 38057;
            class5.anIntArray70[3] = 930;
            class5.anIntArray76[4] = 38065;
            class5.anIntArray70[4] = 935;
            class5.anIntArray76[5] = 38090;
            class5.anIntArray70[5] = 945;
            class5.modelArray = new int[4];
            class5.modelArray[0] = 8414;
            class5.modelArray[1] = 8415;
            class5.modelArray[2] = 8416;
            class5.modelArray[3] = 8417;
            class5.name = "Dragonite dragon";
            class5.combatLevel = 801;
            class5.description = "Dragon dragon, is that possible?".getBytes();
        }
        if (i == 1595) {
            stream.currentOffset = streamIndices[630];
            class5.readValues(stream);
            class5.actions = new String[5];
            class5.actions[1] = "Attack";
            int changedColors = 6;
            class5.anIntArray76 = new int[changedColors];
            class5.anIntArray70 = new int[changedColors];
            class5.anIntArray76[0] = 43280;
            class5.anIntArray70[0] = 36160;
            class5.anIntArray76[1] = 38172;
            class5.anIntArray70[1] = 36130;
            class5.anIntArray76[2] = 38177;
            class5.anIntArray70[2] = 36135;
            class5.anIntArray76[3] = 38057;
            class5.anIntArray70[3] = 36140;
            class5.anIntArray76[4] = 38065;
            class5.anIntArray70[4] = 36145;
            class5.anIntArray76[5] = 38090;
            class5.anIntArray70[5] = 36155;
            class5.modelArray = new int[4];
            class5.modelArray[0] = 8418;
            class5.modelArray[1] = 8419;
            class5.modelArray[2] = 8416;
            class5.modelArray[3] = 8417;
            class5.name = "Runite dragon";
            class5.combatLevel = 503;
            class5.description = "Expirimenting with rune gone very wrong!".getBytes();
        }
        if (i == 1594) {
            stream.currentOffset = streamIndices[630];
            class5.readValues(stream);
            class5.actions = new String[5];
            class5.actions[1] = "Attack";
            int changedColors = 6;
            class5.anIntArray76 = new int[changedColors];
            class5.anIntArray70 = new int[changedColors];
            class5.anIntArray76[0] = 43280;
            class5.anIntArray70[0] = 21690;
            class5.anIntArray76[1] = 38172;
            class5.anIntArray70[1] = 21660;
            class5.anIntArray76[2] = 38177;
            class5.anIntArray70[2] = 21665;
            class5.anIntArray76[3] = 38057;
            class5.anIntArray70[3] = 21670;
            class5.anIntArray76[4] = 38065;
            class5.anIntArray70[4] = 21675;
            class5.anIntArray76[5] = 38090;
            class5.anIntArray70[5] = 21685;
            class5.modelArray = new int[4];
            class5.modelArray[0] = 8414;
            class5.modelArray[1] = 8419;
            class5.modelArray[2] = 8416;
            class5.modelArray[3] = 8417;
            class5.name = "Adamant dragon";
            class5.combatLevel = 409;
            class5.description = "Expirimenting with adamant gone wrong!".getBytes();
        }
        if (i == 1593) {
            stream.currentOffset = streamIndices[630];
            class5.readValues(stream);
            class5.actions = new String[5];
            class5.actions[1] = "Attack";
            class5.modelArray = new int[4];
            class5.modelArray[0] = 8420;
            class5.modelArray[1] = 8419;
            class5.modelArray[2] = 8416;
            class5.modelArray[3] = 8417;
            class5.name = "Mithril dragon";
            class5.combatLevel = 304;
            class5.description = "Expirimenting with mithril gone bad!".getBytes();
        }
        if (i == 1592) {
            stream.currentOffset = streamIndices[630];
            class5.readValues(stream);
            class5.actions = new String[5];
            class5.actions[1] = "Attack";
            int changedColors = 6;
            class5.anIntArray76 = new int[changedColors];
            class5.anIntArray70 = new int[changedColors];
            class5.anIntArray76[0] = 43280;
            class5.anIntArray70[0] = 30;
            class5.anIntArray76[1] = 38172;
            class5.anIntArray70[1] = 40;
            class5.anIntArray76[2] = 38177;
            class5.anIntArray70[2] = 45;
            class5.anIntArray76[3] = 38057;
            class5.anIntArray70[3] = 50;
            class5.anIntArray76[4] = 38065;
            class5.anIntArray70[4] = 55;
            class5.anIntArray76[5] = 38090;
            class5.anIntArray70[5] = 65;
            class5.modelArray = new int[4];
            class5.modelArray[0] = 8414;
            class5.modelArray[1] = 8419;
            class5.modelArray[2] = 8421;
            class5.modelArray[3] = 8417;
            class5.name = "Steel dragon";
            class5.combatLevel = 246;
            class5.description = "It's scales seem to be made of steel.".getBytes();
        }
        if (i == 1591) {
            stream.currentOffset = streamIndices[630];
            class5.readValues(stream);
            class5.actions = new String[5];
            class5.actions[1] = "Attack";
            int changedColors = 6;
            class5.anIntArray76 = new int[changedColors];
            class5.anIntArray70 = new int[changedColors];
            class5.anIntArray76[0] = 43280;
            class5.anIntArray70[0] = 10;
            class5.anIntArray76[1] = 38172;
            class5.anIntArray70[1] = 20;
            class5.anIntArray76[2] = 38177;
            class5.anIntArray70[2] = 25;
            class5.anIntArray76[3] = 38057;
            class5.anIntArray70[3] = 30;
            class5.anIntArray76[4] = 38065;
            class5.anIntArray70[4] = 35;
            class5.anIntArray76[5] = 38090;
            class5.anIntArray70[5] = 45;
            class5.modelArray = new int[4];
            class5.modelArray[0] = 8420;
            class5.modelArray[1] = 8415;
            class5.modelArray[2] = 8422;
            class5.modelArray[3] = 8417;
            class5.name = "Iron dragon";
            class5.combatLevel = 189;
            class5.description = "It's scales seem to be made of iron.".getBytes();
        }
        if (i == 1590) {
            stream.currentOffset = streamIndices[630];
            class5.readValues(stream);
            class5.actions = new String[5];
            class5.actions[1] = "Attack";
            int changedColors = 6;
            class5.anIntArray76 = new int[changedColors];
            class5.anIntArray70 = new int[changedColors];
            class5.anIntArray76[0] = 43280;
            class5.anIntArray70[0] = 5640;
            class5.anIntArray76[1] = 38172;
            class5.anIntArray70[1] = 5650;
            class5.anIntArray76[2] = 38177;
            class5.anIntArray70[2] = 5655;
            class5.anIntArray76[3] = 38057;
            class5.anIntArray70[3] = 5660;
            class5.anIntArray76[4] = 38065;
            class5.anIntArray70[4] = 5665;
            class5.anIntArray76[5] = 38090;
            class5.anIntArray70[5] = 5675;
            class5.modelArray = new int[4];
            class5.modelArray[0] = 8418;
            class5.modelArray[1] = 8423;
            class5.modelArray[2] = 8416;
            class5.modelArray[3] = 8417;
            class5.name = "Bronze dragon";
            class5.combatLevel = 131;
            class5.description = "It's scales seem to be made of bronze.".getBytes();
        }
        if (i == 51) {
            stream.currentOffset = streamIndices[630];
            class5.readValues(stream);
            class5.actions = new String[5];
            class5.actions[1] = "Attack";
            int changedColors = 6;
            class5.anIntArray76 = new int[changedColors];
            class5.anIntArray70 = new int[changedColors];
            class5.anIntArray76[0] = 2469;
            class5.anIntArray70[0] = 40;
            class5.anIntArray76[1] = 2588;
            class5.anIntArray70[1] = 35;
            class5.anIntArray76[2] = 910;
            class5.anIntArray70[2] = 15;
            class5.anIntArray76[3] = 912;
            class5.anIntArray70[3] = 20;
            class5.anIntArray76[4] = 1938;
            class5.anIntArray70[4] = 25;
            class5.anIntArray76[5] = 1814;
            class5.anIntArray70[5] = 30;
            class5.modelArray = new int[4];
            class5.modelArray[0] = 8424;
            class5.modelArray[1] = 8425;
            class5.modelArray[2] = 8426;
            class5.modelArray[3] = 8427;
            class5.name = "Baby black dragon";
            class5.combatLevel = 83;
            class5.description = "Cute and young, but still dangerous.".getBytes();
        }
        if (i == 52) {
            stream.currentOffset = streamIndices[630];
            class5.readValues(stream);
            class5.actions = new String[5];
            class5.actions[1] = "Attack";
            int changedColors = 6;
            class5.anIntArray76 = new int[changedColors];
            class5.anIntArray70 = new int[changedColors];
            class5.anIntArray76[0] = 2469;
            class5.anIntArray70[0] = 43940;
            class5.anIntArray76[1] = 2588;
            class5.anIntArray70[1] = 43935;
            class5.anIntArray76[2] = 910;
            class5.anIntArray70[2] = 43915;
            class5.anIntArray76[3] = 912;
            class5.anIntArray70[3] = 43920;
            class5.anIntArray76[4] = 1938;
            class5.anIntArray70[4] = 43925;
            class5.anIntArray76[5] = 1814;
            class5.anIntArray70[5] = 43930;
            class5.modelArray = new int[4];
            class5.modelArray[0] = 8424;
            class5.modelArray[1] = 8425;
            class5.modelArray[2] = 8426;
            class5.modelArray[3] = 8427;
            class5.name = "Baby blue dragon";
            class5.combatLevel = 48;
            class5.description = "Cute and young, but still dangerous.".getBytes();
        }
        if (i == 1589) {
            stream.currentOffset = streamIndices[630];
            class5.readValues(stream);
            class5.actions = new String[5];
            class5.actions[1] = "Attack";
            class5.modelArray = new int[4];
            class5.modelArray[0] = 8424;
            class5.modelArray[1] = 8425;
            class5.modelArray[2] = 8426;
            class5.modelArray[3] = 8427;
            class5.name = "Baby red dragon";
            class5.combatLevel = 48;
            class5.description = "Cute and young, but still dangerous.".getBytes();
        }
        if (i == 54) {
            stream.currentOffset = streamIndices[630];
            class5.readValues(stream);
            class5.actions = new String[5];
            class5.actions[1] = "Attack";
            int changedColors = 6;
            class5.anIntArray76 = new int[changedColors];
            class5.anIntArray70 = new int[changedColors];
            class5.anIntArray76[0] = 2469;
            class5.anIntArray70[0] = 20;
            class5.anIntArray76[1] = 2588;
            class5.anIntArray70[1] = 15;
            class5.anIntArray76[2] = 910;
            class5.anIntArray70[2] = 3;
            class5.anIntArray76[3] = 912;
            class5.anIntArray70[3] = 5;
            class5.anIntArray76[4] = 1938;
            class5.anIntArray70[4] = 7;
            class5.anIntArray76[5] = 1814;
            class5.anIntArray70[5] = 10;
            class5.modelArray = new int[4];
            class5.modelArray[0] = 8448;
            class5.modelArray[1] = 8449;
            class5.modelArray[2] = 8447;
            class5.modelArray[3] = 8450;
            class5.name = "Black dragon";
            class5.combatLevel = 227;
            class5.description = "A fierce dragon with black scales!".getBytes();
        }
        if (i == 55) {
            stream.currentOffset = streamIndices[630];
            class5.readValues(stream);
            class5.actions = new String[5];
            class5.actions[1] = "Attack";
            int changedColors = 6;
            class5.anIntArray76 = new int[changedColors];
            class5.anIntArray70 = new int[changedColors];
            class5.anIntArray76[0] = 2469;
            class5.anIntArray70[0] = 43940;
            class5.anIntArray76[1] = 2588;
            class5.anIntArray70[1] = 43935;
            class5.anIntArray76[2] = 910;
            class5.anIntArray70[2] = 43915;
            class5.anIntArray76[3] = 912;
            class5.anIntArray70[3] = 43920;
            class5.anIntArray76[4] = 1938;
            class5.anIntArray70[4] = 43925;
            class5.anIntArray76[5] = 1814;
            class5.anIntArray70[5] = 43930;
            class5.modelArray = new int[4];
            class5.modelArray[0] = 8448;
            class5.modelArray[1] = 8449;
            class5.modelArray[2] = 8447;
            class5.modelArray[3] = 8450;
            class5.name = "Blue dragon";
            class5.combatLevel = 111;
            class5.description = "A mother dragon.".getBytes();
        }
        if (i == 53) {
            stream.currentOffset = streamIndices[630];
            class5.readValues(stream);
            class5.actions = new String[5];
            class5.actions[1] = "Attack";
            class5.modelArray = new int[4];
            class5.modelArray[0] = 8448;
            class5.modelArray[1] = 8449;
            class5.modelArray[2] = 8447;
            class5.modelArray[3] = 8450;
            class5.name = "Red dragon";
            class5.combatLevel = 152;
            class5.description = "A huge powerful dragon.".getBytes();
        }
        if (i == 941) {
            stream.currentOffset = streamIndices[630];
            class5.readValues(stream);
            class5.actions = new String[5];
            class5.actions[1] = "Attack";
            class5.modelArray = new int[4];
            class5.modelArray[0] = 8451;
            class5.modelArray[1] = 8452;
            class5.modelArray[2] = 8453;
            class5.modelArray[3] = 8450;
            class5.name = "Green dragon";
            class5.combatLevel = 79;
            class5.description = "Must be relasted to Elvarg.".getBytes();
        }
        if (i == 50) {
            stream.currentOffset = streamIndices[630];
            class5.readValues(stream);
            class5.actions = new String[5];
            class5.actions[1] = "Attack";
            class5.modelArray = new int[6];
            class5.modelArray[0] = 8454;
            class5.modelArray[1] = 8455;
            class5.modelArray[2] = 8456;
            class5.modelArray[3] = 8457;
            class5.modelArray[4] = 8458;
            class5.modelArray[5] = 8459;
            class5.name = "King black dragon";
            class5.combatLevel = 279;
            class5.description = "A fierce dragon with black scales and three heads.".getBytes();
        }

        // END OF DRAGONS - STAR OF KALPHITES //

        // END OF KALPHITES - STAR OF OTHER NPCS //

        if (i == 1582) {
            stream.currentOffset = streamIndices[630];
            class5.readValues(stream);
            class5.actions = new String[5];
            class5.actions[1] = "Attack";
            class5.modelArray = new int[3];
            class5.modelArray[0] = 8433;
            class5.modelArray[1] = 8434;
            class5.modelArray[2] = 8430;
            class5.name = "Fire giant";
            class5.combatLevel = 86;
            class5.description = "A very large elemental adversary.".getBytes();
        }
        if (i == 1583) {
            stream.currentOffset = streamIndices[630];
            class5.readValues(stream);
            class5.actions = new String[5];
            class5.actions[1] = "Attack";
            class5.modelArray = new int[3];
            class5.modelArray[0] = 8433;
            class5.modelArray[1] = 8434;
            class5.modelArray[2] = 8430;
            class5.name = "Fire giant";
            class5.combatLevel = 85;
            class5.description = "A very large elemental adversary.".getBytes();
        }
        if (i == 1584) {
            stream.currentOffset = streamIndices[630];
            class5.readValues(stream);
            class5.actions = new String[5];
            class5.actions[1] = "Attack";
            class5.modelArray = new int[3];
            class5.modelArray[0] = 8433;
            class5.modelArray[1] = 8434;
            class5.modelArray[2] = 8430;
            class5.name = "Fire giant";
            class5.combatLevel = 86;
            class5.description = "A very large elemental adversary.".getBytes();
        }
        if (i == 1585) {
            stream.currentOffset = streamIndices[630];
            class5.readValues(stream);
            class5.actions = new String[5];
            class5.actions[1] = "Attack";
            class5.modelArray = new int[3];
            class5.modelArray[0] = 8433;
            class5.modelArray[1] = 8434;
            class5.modelArray[2] = 8430;
            class5.name = "Fire giant";
            class5.combatLevel = 89;
            class5.description = "A very large elemental adversary.".getBytes();
        }
        if (i == 1586) {
            stream.currentOffset = streamIndices[630];
            class5.readValues(stream);
            class5.actions = new String[5];
            class5.actions[1] = "Attack";
            class5.modelArray = new int[3];
            class5.modelArray[0] = 8433;
            class5.modelArray[1] = 8434;
            class5.modelArray[2] = 8430;
            class5.name = "Fire giant";
            class5.combatLevel = 86;
            class5.description = "A very large elemental adversary.".getBytes();
        }
        if (i == 110) {
            stream.currentOffset = streamIndices[630];
            class5.readValues(stream);
            class5.actions = new String[5];
            class5.actions[1] = "Attack";
            class5.modelArray = new int[3];
            class5.modelArray[0] = 8433;
            class5.modelArray[1] = 8434;
            class5.modelArray[2] = 8430;
            class5.name = "Fire giant";
            class5.combatLevel = 81;
            class5.description = "A very large elemental adversary.".getBytes();
        }
        if (i == 117) {
            stream.currentOffset = streamIndices[630];
            class5.readValues(stream);
            class5.actions = new String[5];
            class5.actions[1] = "Attack";
            class5.modelArray = new int[5];
            class5.modelArray[0] = 8435;
            class5.modelArray[1] = 8436;
            class5.modelArray[2] = 8430;
            class5.modelArray[3] = 8437;
            class5.modelArray[4] = 8438;
            class5.name = "Hill giant";
            class5.combatLevel = 28;
            class5.description = "A very large foe.".getBytes();
        }
        if (i == 111) {
            stream.currentOffset = streamIndices[630];
            class5.readValues(stream);
            class5.actions = new String[5];
            class5.actions[1] = "Attack";
            class5.modelArray = new int[3];
            class5.modelArray[0] = 8431;
            class5.modelArray[1] = 8432;
            class5.modelArray[2] = 8430;
            class5.name = "Ice giant";
            class5.combatLevel = 53;
            class5.description = "He's got icicles in his beard.".getBytes();
        }
        if (i == 112) {
            stream.currentOffset = streamIndices[630];
            class5.readValues(stream);
            class5.actions = new String[5];
            class5.actions[1] = "Attack";
            class5.modelArray = new int[3];
            class5.modelArray[0] = 8428;
            class5.modelArray[1] = 8429;
            class5.modelArray[2] = 8430;
            class5.name = "Moss giant";
            class5.combatLevel = 42;
            class5.description = "His beard seems to have a life of it's own.".getBytes();
        }
        if (i == 1587) {
            stream.currentOffset = streamIndices[630];
            class5.readValues(stream);
            class5.actions = new String[5];
            class5.actions[1] = "Attack";
            class5.modelArray = new int[3];
            class5.modelArray[0] = 8428;
            class5.modelArray[1] = 8429;
            class5.modelArray[2] = 8430;
            class5.name = "Moss giant";
            class5.combatLevel = 42;
            class5.description = "His beard seems to have a life of it's own.".getBytes();
        }
        if (i == 1588) {
            stream.currentOffset = streamIndices[630];
            class5.readValues(stream);
            class5.actions = new String[5];
            class5.actions[1] = "Attack";
            class5.modelArray = new int[3];
            class5.modelArray[0] = 8428;
            class5.modelArray[1] = 8429;
            class5.modelArray[2] = 8430;
            class5.name = "Moss giant";
            class5.combatLevel = 42;
            class5.description = "His beard seems to have a life of it's own.".getBytes();
        }
        if (i == 1681) {
            stream.currentOffset = streamIndices[630];
            class5.readValues(stream);
            class5.actions = new String[5];
            class5.actions[1] = "Attack";
            class5.modelArray = new int[3];
            class5.modelArray[0] = 8428;
            class5.modelArray[1] = 8429;
            class5.modelArray[2] = 8430;
            class5.name = "Moss giant";
            class5.combatLevel = 84;
            class5.description = "His beard seems to have a life of it's own.".getBytes();
        }
        if (i == 83) {
            stream.currentOffset = streamIndices[630];
            class5.readValues(stream);
            class5.actions = new String[5];
            class5.actions[1] = "Attack";
            class5.modelArray = new int[5];
            class5.modelArray[0] = 8442;
            class5.modelArray[1] = 8443;
            class5.modelArray[2] = 8444;
            class5.modelArray[3] = 8445;
            class5.modelArray[4] = 8446;
            class5.name = "Greater demon";
            class5.combatLevel = 92;
            class5.description = "Big. red, and incredibly evil.".getBytes();
        }
        if (i == 752) {
            stream.currentOffset = streamIndices[630];
            class5.readValues(stream);
            class5.actions = new String[5];
            class5.actions[1] = "Attack";
            class5.modelArray = new int[3];
            class5.modelArray[0] = 8439;
            class5.modelArray[1] = 8440;
            class5.modelArray[2] = 8441;
            class5.name = "Lesser demon";
            class5.combatLevel = 82;
            class5.description = "Lesser, but still pretty huge.".getBytes();
        }
        if (i == 82) {
            stream.currentOffset = streamIndices[630];
            class5.readValues(stream);
            class5.actions = new String[5];
            class5.actions[1] = "Attack";
            class5.modelArray = new int[3];
            class5.modelArray[0] = 8439;
            class5.modelArray[1] = 8440;
            class5.modelArray[2] = 8441;
            class5.name = "Lesser demon";
            class5.combatLevel = 82;
            class5.description = "Lesser, but still pretty huge.".getBytes();
        }
        if (i == 2783) {
            stream.currentOffset = streamIndices[630];
            class5.readValues(stream);
            class5.actions = new String[5];
            class5.actions[1] = "Attack";
            class5.modelArray = new int[1];
            class5.modelArray[0] = 8413;
            class5.name = "Dark beast";
            class5.combatLevel = 139;
            class5.description = "A beast from the dark dimension.".getBytes();
        }
        if (i == 2181) {
            stream.currentOffset = streamIndices[630];
            class5.readValues(stream);
            class5.actions = new String[5];
            class5.actions[1] = "Attack";
            class5.modelArray = new int[7];
            int changedColors = 1;
            class5.modelArray[0] = 4909;
            class5.modelArray[1] = 4911;
            class5.modelArray[2] = 4912;
            class5.modelArray[3] = 4913;
            class5.modelArray[4] = 4914;
            class5.modelArray[5] = 4915;
            class5.modelArray[6] = 4916;
            class5.name = "Pirate";
            class5.combatLevel = 76;
            class5.description = "Arrgh, examain info.".getBytes();
        }


        // END OF OTHER NPCS - END OF NPCS //

        return class5;
    }

    public final Model method160(boolean flag) {
        if (childrenIDs != null) {
            EntityDef class5 = method161(anInt64);
            if (class5 == null) {
                return null;
            } else {
                return class5.method160(true);
            }
        }
        if (anIntArray73 == null) {
            return null;
        }
        boolean flag1 = false;
        if (!flag) {
            anInt64 = 303;
        }
        for (int i = 0; i < anIntArray73.length; i++) {
            if (!Model.method463(anIntArray73[i])) {
                flag1 = true;
            }
        }
        if (flag1) {
            return null;
        }
        Model aclass30_sub2_sub4_sub6[] = new Model[anIntArray73.length];
        for (int j = 0; j < anIntArray73.length; j++) {
            aclass30_sub2_sub4_sub6[j] = Model.method462(anInt69, anIntArray73[j]);
        }
        Model class30_sub2_sub4_sub6;
        if (aclass30_sub2_sub4_sub6.length == 1) {
            class30_sub2_sub4_sub6 = aclass30_sub2_sub4_sub6[0];
        } else {
            class30_sub2_sub4_sub6 = new Model(aclass30_sub2_sub4_sub6.length, aclass30_sub2_sub4_sub6, -38);
        }
        if (anIntArray76 != null) {
            for (int k = 0; k < anIntArray76.length; k++) {
                class30_sub2_sub4_sub6.method476(anIntArray76[k], anIntArray70[k]);
            }
        }
        return class30_sub2_sub4_sub6;
    }

    public final EntityDef method161(int i) {
        int j = -1;
        if (i != 1834) {
            aBoolean81 = !aBoolean81;
        }
        if (anInt57 != -1) {
            VarBit class37 = VarBit.cache[anInt57];
            int k = class37.anInt648;
            int l = class37.anInt649;
            int i1 = class37.anInt650;
            int j1 = client.anIntArray1232[i1 - l];
            j = clientInstance.variousSettings[k] >> l & j1;
        } else {
            if (anInt59 != -1) {
                j = clientInstance.variousSettings[anInt59];
            }
        }
        if (j < 0 || j >= childrenIDs.length || childrenIDs[j] == -1) {
            return null;
        } else {
            return forID(childrenIDs[j]);
        }
    }

    public static void unpackConfig(StreamLoader streamLoader) {
        stream = new Stream(streamLoader.getDataForName("npc.dat"));
        Stream class30_sub2_sub2 = new Stream(streamLoader.getDataForName("npc.idx"));
        anInt62 = class30_sub2_sub2.readUnsignedWord();
        streamIndices = new int[anInt62];
        int i = 2;
        for (int j = 0; j < anInt62; j++) {
            streamIndices[j] = i;
            i += class30_sub2_sub2.readUnsignedWord();
        }
        cache = new EntityDef[20];
        for (int k = 0; k < 20; k++) {
            cache[k] = new EntityDef();
        }
    }

    public static void nullLoader() {
        mruNodes = null;
        streamIndices = null;
        cache = null;
        stream = null;
    }

    public final Model method164(int i, int j, int k, int ai[]) {
        if (childrenIDs != null) {
            EntityDef class5 = method161(anInt64);
            if (class5 == null) {
                return null;
            } else {
                return class5.method164(0, j, k, ai);
            }
        }
        Model class30_sub2_sub4_sub6 = (Model) mruNodes.insertFromCache(type);
        if (i != 0) {
            for (int l = 1; l > 0; l++);
        }
        if (class30_sub2_sub4_sub6 == null) {
            boolean flag = false;
            for (int i1 = 0; i1 < modelArray.length; i1++) {
                if (!Model.method463(modelArray[i1])) {
                    flag = true;
                }
            }
            if (flag) {
                return null;
            }
            Model aclass30_sub2_sub4_sub6[] = new Model[modelArray.length];
            for (int j1 = 0; j1 < modelArray.length; j1++) {
                aclass30_sub2_sub4_sub6[j1] = Model.method462(anInt69, modelArray[j1]);
            }
            if (aclass30_sub2_sub4_sub6.length == 1) {
                class30_sub2_sub4_sub6 = aclass30_sub2_sub4_sub6[0];
            } else {
                class30_sub2_sub4_sub6 = new Model(aclass30_sub2_sub4_sub6.length, aclass30_sub2_sub4_sub6, -38);
            }
            if (anIntArray76 != null) {
                for (int k1 = 0; k1 < anIntArray76.length; k1++) {
                    class30_sub2_sub4_sub6.method476(anIntArray76[k1], anIntArray70[k1]);
                }
            }
            class30_sub2_sub4_sub6.method469((byte) -71);
            class30_sub2_sub4_sub6.method479(64 + anInt85, 850 + anInt92, -30, -50, -30, true);
            mruNodes.removeFromCache(class30_sub2_sub4_sub6, type, (byte) 2);
        }
        Model class30_sub2_sub4_sub6_1 = Model.aModel_1621;
        class30_sub2_sub4_sub6_1.method464(7, class30_sub2_sub4_sub6, Class36.method532(k) & Class36.method532(j));
        if (k != -1 && j != -1) {
            class30_sub2_sub4_sub6_1.method471(-20491, ai, j, k);
        } else if (k != -1) {
            class30_sub2_sub4_sub6_1.method470(k, 40542);
        }
        if (anInt91 != 128 || anInt86 != 128) {
            class30_sub2_sub4_sub6_1.method478(anInt91, anInt91, anInt63, anInt86);
        }
        class30_sub2_sub4_sub6_1.method466(false);
        class30_sub2_sub4_sub6_1.anIntArrayArray1658 = null;
        class30_sub2_sub4_sub6_1.anIntArrayArray1657 = null;
        if (aByte68 == 1) {
            class30_sub2_sub4_sub6_1.aBoolean1659 = true;
        }
        return class30_sub2_sub4_sub6_1;
    }

    private void readValues(Stream stream) {
        do {
            int i = stream.readUnsignedByte();
            if (i == 0) {
                return;
            }
            if (i == 1) {
                int j = stream.readUnsignedByte();
                modelArray = new int[j];
                for (int j1 = 0; j1 < j; j1++) {
                    modelArray[j1] = stream.readUnsignedWord();
                }
            } else if (i == 2) {
                name = stream.readString();
            } else if (i == 3) {
                description = stream.readBytes();
            } else if (i == 12) {
                aByte68 = stream.readSignedByte();
            } else if (i == 13) {
                anInt77 = stream.readUnsignedWord();
            } else if (i == 14) {
                anInt67 = stream.readUnsignedWord();
            } else if (i == 17) {
                anInt67 = stream.readUnsignedWord();
                anInt58 = stream.readUnsignedWord();
                anInt83 = stream.readUnsignedWord();
                anInt55 = stream.readUnsignedWord();
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
                anIntArray76 = new int[k];
                anIntArray70 = new int[k];
                for (int k1 = 0; k1 < k; k1++) {
                    anIntArray76[k1] = stream.readUnsignedWord();
                    anIntArray70[k1] = stream.readUnsignedWord();
                }
            } else if (i == 60) {
                int l = stream.readUnsignedByte();
                anIntArray73 = new int[l];
                for (int l1 = 0; l1 < l; l1++) {
                    anIntArray73[l1] = stream.readUnsignedWord();
                }
            } else if (i == 90) {
                anInt96 = stream.readUnsignedWord();
            } else if (i == 91) {
                anInt71 = stream.readUnsignedWord();
            } else if (i == 92) {
                anInt90 = stream.readUnsignedWord();
            } else if (i == 93) {
                aBoolean87 = false;
            } else if (i == 95) {
                combatLevel = stream.readUnsignedWord();
            } else if (i == 97) {
                anInt91 = stream.readUnsignedWord();
            } else if (i == 98) {
                anInt86 = stream.readUnsignedWord();
            } else if (i == 99) {
                aBoolean93 = true;
            } else if (i == 100) {
                anInt85 = stream.readSignedByte();
            } else if (i == 101) {
                anInt92 = stream.readSignedByte() * 5;
            } else if (i == 102) {
                anInt75 = stream.readUnsignedWord();
            } else if (i == 103) {
                anInt79 = stream.readUnsignedWord();
            } else if (i == 106) {
                anInt57 = stream.readUnsignedWord();
                if (anInt57 == 65535) {
                    anInt57 = -1;
                }
                anInt59 = stream.readUnsignedWord();
                if (anInt59 == 65535) {
                    anInt59 = -1;
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
                aBoolean84 = false;
            }
        } while (true);
    }
}
