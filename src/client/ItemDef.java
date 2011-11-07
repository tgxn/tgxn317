package client;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

final class ItemDef {
    
    private byte aByte154;
    public int value;
    private int modifiedModelColors[];
    public int id;
    static MemCache mruNodes1 = new MemCache(100);
    public static MemCache mruNodes2 = new MemCache(50);
    private int originalModelColors[];
    public boolean membersObject;
    public int anInt162;
    public int certTemplateID;
    private int anInt164;
    private int anInt165;
    public int anInt166;
    private int anInt167;
    public String groundActions[];
    private int modelOffset1;
    public String name;
    private int anInt171;
    private static ItemDef cache[];
    public int anInt173;
    private int modelID;
    private int anInt175;
    public boolean stackable;
    private int anInt177;
    public byte description[];
    public int certID;
    private static int cacheIndex;
    public int modelZoom;
    public static boolean isMembers = true;
    private static Stream stream;
    private int anInt184;
    public int anInt185;
    private boolean aBoolean186;
    private static boolean aBoolean187;
    private int anInt188;
    public String actions[];
    public int modelRotation1;
    private int anInt191;
    private int anInt192;
    public int stackIDs[];
    private int modelOffset2;
    private static int streamIndices[];
    private int anInt196;
    public int anInt197;
    public int modelRotation2;
    public int anInt199;
    private int anInt200;
    public int stackAmounts[];
    public int team;
    public static int totalItems;
    public static int frugooItems = 5;
    private int anInt204;
    private byte aByte205;
    private boolean aBoolean206;
    
    ItemDef() {
        id = -1;
        anInt171 = 9;
        anInt177 = 9;
        aBoolean186 = false;
        aBoolean206 = false;
    }
        
    public static void dumpItems() {
        for (int i = 0; i < 9250; i++) {
            ItemDef class8 = forID(i);
            BufferedWriter bw = null;
            try {
                bw = new BufferedWriter(new FileWriter("itemdump.cfg", true));
                bw.write("====================");
                bw.newLine();
                bw.write("Item Name: " + class8.name);
                bw.newLine();
                bw.write("Item ID: " + i);
                bw.newLine();
                if (class8.modifiedModelColors != null) {
                    for (int i2 = 0; i2 < class8.modifiedModelColors.length; i2++) {
                        if (i2 == 0) {
                            bw.write("Original model colors: ");
                        }
                        if (i2 != class8.modifiedModelColors.length - 1) {
                            bw.write("" + class8.modifiedModelColors[i2] + ", ");
                        } else {
                            bw.write("" + class8.modifiedModelColors[i2]);
                            bw.newLine();
                        }
                    }
                }
                if (class8.originalModelColors != null) {
                    for (int i2 = 0; i2 < class8.originalModelColors.length; i2++) {
                        if (i2 == 0) {
                            bw.write("Changed model colors: ");
                        }
                        if (i2 != class8.originalModelColors.length - 1) {
                            bw.write("" + class8.originalModelColors[i2] + ", ");
                        } else {
                            bw.write("" + class8.originalModelColors[i2]);
                            bw.newLine();
                        }
                    }
                }
                bw.write("class8.anInt174 = " + class8.modelID + ";");
                bw.newLine();
                bw.write("class8.anInt181 = " + class8.modelZoom + ";");
                bw.newLine();
                bw.write("class8.anInt190 = " + class8.modelRotation1 + ";");
                bw.newLine();
                bw.write("class8.anInt198 = " + class8.modelRotation2 + ";");
                bw.newLine();
                bw.write("class8.anInt204 = " + class8.anInt204 + ";");
                bw.newLine();
                bw.write("class8.anInt169 = " + class8.modelOffset1 + ";");
                bw.newLine();
                bw.write("class8.anInt194 = " + class8.modelOffset2 + ";");
                bw.newLine();
                bw.write("class8.anInt165 = " + class8.anInt165 + ";");
                bw.newLine();
                bw.write("class8.anInt200 = " + class8.anInt200 + ";");
                bw.newLine();
                bw.write("class8.anInt188 = " + class8.anInt188 + ";");
                bw.newLine();
                bw.write("class8.anInt164 = " + class8.anInt164 + ";");
                bw.newLine();
                bw.write("class8.anInt175 = " + class8.anInt175 + ";");
                bw.newLine();
                bw.write("class8.anInt197 = " + class8.anInt197 + ";");
                bw.newLine();
                bw.flush();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            } finally {
                if (bw != null) {
                    try {
                        bw.close();
                    } catch (IOException ioe2) {
                    }
                }
            }
        }
    }

    public static void dumpCfg() {
        boolean delete = (new File("item.cfg")).delete();
        for (int i = 0; i < 9200; i++) {
            ItemDef class8 = forID(i);
            BufferedWriter bw = null;
            String des = "";
            if (class8.description != null) {
                des = new String(class8.description);
            } else {
                des = "Its a " + class8.name;
            }
            try {
                BufferedWriter bufferedwriter;
                bufferedwriter = null;
                bufferedwriter = new BufferedWriter(new FileWriter("item.cfg", true));
                bufferedwriter.write((new StringBuilder()).append("item = ").append(i).append("	").append(class8.name).append("	").append(des).append("	").append(class8.value).append("	").append(class8.value).append("	").append(class8.value).append("	").append("0").append("	").append("0").append("	").append("0").append("	").append("0").append("	").append("0").append("	").append("0").append("	").append("0").append("	").append("0").append("	").append("0").append("	").append("0").append("	").append("0").append("	").append("0").toString());
                bufferedwriter.newLine();
                bufferedwriter.flush();
            } catch (Exception e) {
            }
        }
    }

    public static void dumpItemsList() {
        for (int i = 0; i < 9200; i++) {
            ItemDef class8 = forID(i);
            BufferedWriter bw = null;
            try {
                bw = new BufferedWriter(new FileWriter("ItemList.txt", true));
                if (class8.name != null) {
                    bw.write("ID: " + i + "\t\tName: " + class8.name);
                    bw.newLine();
                    bw.flush();
                    bw.close();
                }
            } catch (IOException ioe2) {
            }
        }
    }

    public static void dumpNewItems() {
        for (int i = 9000; i < 9200; i++) {
            ItemDef class8 = forID(i);
            BufferedWriter bw = null;
            try {
                bw = new BufferedWriter(new FileWriter("Item Dump.txt", true));
                if (class8.name != null) {
                    bw.write("<item members='true'  name='" + class8.name + "'  type='" + i + "'> </item>");
                    bw.newLine();
                    bw.flush();
                    bw.close();
                }
            } catch (IOException ioe2) {
            }
        }
    }

    public static final void nullLoader() { 
        mruNodes2 = null;
        mruNodes1 = null;
        streamIndices = null;
        cache = null;
        stream = null;
    }

    public final boolean method192(int i, int j) {
        if (i != -2836) {
            aBoolean186 = !aBoolean186;
        }
        int k = anInt175;
        int l = anInt166;
        if (j == 1) {
            k = anInt197;
            l = anInt173;
        }
        if (k == -1) {
            return true;
        }
        boolean flag = true;
        if (!Model.isCached(k)) {
            flag = false;
        }
        if (l != -1 && !Model.isCached(l)) {
            flag = false;
        }
        return flag;
    }

    public static final void unpackConfig(JagexArchive streamLoader) {
        stream = new Stream(streamLoader.getDataForName("obj.dat"));
        Stream stream1 = new Stream(streamLoader.getDataForName("obj.idx"));
        totalItems = stream1.readUnsignedWord();
        streamIndices = new int[totalItems + 400000];
        int i = 2;
        for (int j = 0; j < totalItems; j++) {
            streamIndices[j] = i;
            i += stream1.readUnsignedWord();
        }
        cache = new ItemDef[10];
        for (int k = 0; k < 10; k++) {
            cache[k] = new ItemDef();
        }
    }

    public final Model method194(int i, int j) {
        while (i >= 0) {
            aBoolean186 = !aBoolean186;
        }
        int k = anInt175;
        int l = anInt166;
        if (j == 1) {
            k = anInt197;
            l = anInt173;
        }
        if (k == -1) {
            return null;
        }
        Model class30_sub2_sub4_sub6 = Model.getModel(k);
        if (l != -1) {
            Model class30_sub2_sub4_sub6_1 = Model.getModel(l);
            Model aclass30_sub2_sub4_sub6[] = {
                class30_sub2_sub4_sub6, class30_sub2_sub4_sub6_1
            };
            class30_sub2_sub4_sub6 = new Model(2, aclass30_sub2_sub4_sub6);
        }
        if (modifiedModelColors != null) {
            for (int i1 = 0; i1 < modifiedModelColors.length; i1++) {
                class30_sub2_sub4_sub6.reColour(modifiedModelColors[i1], originalModelColors[i1]);
            }
        }
        return class30_sub2_sub4_sub6;
    }

    public final boolean method195(int i, int j) {
        if (i != 40903) {
            aBoolean206 = !aBoolean206;
        }
        int k = anInt165;
        int l = anInt188;
        int i1 = anInt185;
        if (j == 1) {
            k = anInt200;
            l = anInt164;
            i1 = anInt162;
        }
        if (k == -1) {
            return true;
        }
        boolean flag = true;
        if (!Model.isCached(k)) {
            flag = false;
        }
        if (l != -1 && !Model.isCached(l)) {
            flag = false;
        }
        if (i1 != -1 && !Model.isCached(i1)) {
            flag = false;
        }
        return flag;
    }

    public final Model method196(boolean flag, int i) {
        if (flag) {
            throw new NullPointerException();
        }
        int j = anInt165;
        int k = anInt188;
        int l = anInt185;
        if (i == 1) {
            j = anInt200;
            k = anInt164;
            l = anInt162;
        }
        if (j == -1) {
            return null;
        }
        Model class30_sub2_sub4_sub6 = Model.getModel(j);
        if (k != -1) {
            if (l != -1) {
                Model class30_sub2_sub4_sub6_1 = Model.getModel(k);
                Model class30_sub2_sub4_sub6_3 = Model.getModel(l);
                Model aclass30_sub2_sub4_sub6_1[] = {
                    class30_sub2_sub4_sub6, class30_sub2_sub4_sub6_1, class30_sub2_sub4_sub6_3
                };
                class30_sub2_sub4_sub6 = new Model(3, aclass30_sub2_sub4_sub6_1);
            } else {
                Model class30_sub2_sub4_sub6_2 = Model.getModel(k);
                Model aclass30_sub2_sub4_sub6[] = {
                    class30_sub2_sub4_sub6, class30_sub2_sub4_sub6_2
                };
                class30_sub2_sub4_sub6 = new Model(2, aclass30_sub2_sub4_sub6);
            }
        }
        if (i == 0 && aByte205 != 0) {
            class30_sub2_sub4_sub6.method475(0, aByte205, 0);
        }
        if (i == 1 && aByte154 != 0) {
            class30_sub2_sub4_sub6.method475(0, aByte154, 0);
        }
        if (modifiedModelColors != null) {
            for (int i1 = 0; i1 < modifiedModelColors.length; i1++) {
                class30_sub2_sub4_sub6.reColour(modifiedModelColors[i1], originalModelColors[i1]);
            }
        }
        return class30_sub2_sub4_sub6;
    }

    public final void setDefaults() {
        modelID = 0;
        name = null;
        description = null;
        modifiedModelColors = null;
        originalModelColors = null;
        modelZoom = 12000;
        modelRotation1 = 0;
        modelRotation2 = 0;
        anInt204 = 0;
        modelOffset1 = 0;
        modelOffset2 = 0;
        anInt199 = -1;
        stackable = false;
        value = 1;
        membersObject = false;
        groundActions = null;
        actions = null;
        anInt165 = -1;
        anInt188 = -1;
        aByte205 = 0;
        anInt200 = -1;
        anInt164 = -1;
        aByte154 = 0;
        anInt185 = -1;
        anInt162 = -1;
        anInt175 = -1;
        anInt166 = -1;
        anInt197 = -1;
        anInt173 = -1;
        stackIDs = null;
        stackAmounts = null;
        certID = -1;
        certTemplateID = -1;
        anInt167 = 128;
        anInt192 = 128;
        anInt191 = 128;
        anInt196 = 0;
        anInt184 = 0;
        team = 0;
    }

    public static final ItemDef forID(int i) {
        for (int j = 0; j < 10; j++) {
            if (cache[j].id == i) {
                return cache[j];
            }
        }
        cacheIndex = (cacheIndex + 1) % 10;
        ItemDef class8 = cache[cacheIndex];
        stream.currentOffset = streamIndices[i];
        class8.id = i;
        class8.setDefaults();
        class8.readValues(stream);





        if (i == 9001) {
            class8.actions = new String[5];
            class8.actions[1] = "Wear";
            class8.modifiedModelColors = new int[4];
            class8.originalModelColors = new int[4];
            class8.modifiedModelColors = new int[4];
            class8.originalModelColors = new int[4];
            class8.modifiedModelColors[0] = 57280;
            class8.originalModelColors[0] = 926;
            class8.modifiedModelColors[1] = 54503;
            class8.originalModelColors[1] = 926;
            class8.modifiedModelColors[2] = 54183;
            class8.originalModelColors[2] = 926;
            class8.modifiedModelColors[3] = 11200;
            class8.originalModelColors[3] = 926;
            class8.modelID = 4158; //Inv & Ground
            class8.modelZoom = 2000; //Zoom
            class8.modelRotation1 = 572;
            class8.modelRotation2 = 0;
            class8.anInt204 = 0;
            class8.modelOffset1 = 0;
            class8.modelOffset2 = 1;
            class8.anInt165 = 4181; //Male
            class8.anInt200 = 7178; //Female
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.anInt199 = -1;//noteable
            class8.name = "Attack Cape";
            class8.description = "Its A attack Cape".getBytes();
        }
        if (i == 9002) {
            class8.actions = new String[5];
            class8.actions[1] = "Wear";
            class8.modifiedModelColors = new int[4];
            class8.originalModelColors = new int[4];
            class8.modifiedModelColors[0] = 57280;
            class8.modifiedModelColors[1] = 54503;
            class8.modifiedModelColors[2] = 54183;
            class8.modifiedModelColors[3] = 11200;
            class8.originalModelColors[0] = 9152;
            class8.originalModelColors[1] = 7104;
            class8.originalModelColors[2] = 7104;
            class8.originalModelColors[3] = 796;
            class8.modelID = 4158; //Inv & Ground
            class8.modelZoom = 2000; //Zoom
            class8.modelRotation1 = 572;
            class8.modelRotation2 = 0;
            class8.anInt204 = 0;
            class8.modelOffset1 = 0;
            class8.modelOffset2 = 1;
            class8.anInt165 = 4181; //Male
            class8.anInt200 = 7178; //Female
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.anInt199 = -1;//noteable
            class8.name = "Attack Cape(t)";
            class8.description = "Its A attack Cape(t)".getBytes();
        }
        if (i == 9003) {
            class8.actions = new String[5];
            class8.actions[1] = "Wear";
            class8.modifiedModelColors = new int[3];
            class8.originalModelColors = new int[3];
            class8.modifiedModelColors[0] = 960;
            class8.originalModelColors[0] = 912;
            class8.modifiedModelColors[1] = 22464;
            class8.originalModelColors[1] = 912;
            class8.modifiedModelColors[2] = 43968;
            class8.originalModelColors[2] = 912;
            class8.modelID = 4203; //Model ID
            class8.modelZoom = 720; //zoom increase will make it smaller
            class8.modelRotation1 = 0; //model rotate up+down increase to move doen away from you
            class8.modelRotation2 = 0; //model rotate side ways increase to move right in circle
            class8.modelOffset1 = 0; // model offset increase to move to the right
            class8.modelOffset2 = 0; //model offset increase to move up
            class8.anInt165 = 4202;//male wearing
            class8.anInt200 = 4202;//female wearing
            class8.name = "Attack Hood";
            class8.description = "Its A attack Hood".getBytes();
        }
        if (i == 9092) {
            class8.actions = new String[5];
            class8.actions[1] = "Wear";
            class8.modifiedModelColors = new int[1];
            class8.originalModelColors = new int[1];
            class8.modifiedModelColors[0] = 933;
            class8.originalModelColors[0] = 36133;
            class8.modelID = 2537;
            class8.modelZoom = 540;
            class8.modelRotation1 = 72;
            class8.modelRotation2 = 136;
            class8.anInt204 = 0;
            class8.modelOffset1 = 0;
            class8.modelOffset2 = -3;
            class8.anInt165 = 189;
            class8.anInt200 = 366;
            class8.anInt175 = 69;
            class8.anInt197 = 127;
            class8.name = "Rune hat";
            class8.description = "A gift from santa".getBytes();
        }
        if (i == 6570) {
            class8.actions = new String[5];
            class8.actions[1] = "Wear";
            class8.modifiedModelColors = new int[1];
            class8.originalModelColors = new int[1];
            class8.modifiedModelColors[0] = 0;
            class8.originalModelColors[0] = 0;
            class8.modelID = 9631;//item look
            class8.modelZoom = 2000;
            class8.modelRotation1 = 400;
            class8.modelRotation2 = 0;
            class8.anInt204 = 2047;
            class8.modelOffset1 = -8;
            class8.modelOffset2 = 12;
            class8.anInt197 = -1;
            class8.name = "Fire Cape";
            class8.description = "A cape of fire.".getBytes();
        }
        if (i == 9093) //WH1P!!!!
        {
            class8.actions = new String[5];
            class8.actions[1] = "Wear"; //this is the new option to equipt, can change to w/e u want
            class8.modifiedModelColors = new int[1];
            class8.originalModelColors = new int[1];
            class8.modifiedModelColors[0] = 528;  //original color
            class8.originalModelColors[0] = 926; //changed color
            class8.modelID = 5412;
            class8.modelZoom = 840;
            class8.modelRotation1 = 280;
            class8.modelRotation2 = 0;
            class8.anInt204 = 0;
            class8.modelOffset1 = -2;
            class8.modelOffset2 = 56;
            class8.anInt165 = 5409;
            class8.anInt200 = 5409;
            class8.anInt188 = -1;
            class8.anInt164 = -1;
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.name = "Dragon Whip";
            class8.description = "A whip made of Dragon".getBytes();
        }
        if (i == 9095) {
            class8.actions = new String[5];
            class8.actions[1] = "Wear";
            class8.modifiedModelColors = new int[1];
            class8.originalModelColors = new int[1];
            class8.modifiedModelColors[0] = 0;
            class8.originalModelColors[0] = 0;
            class8.modelID = 7354;//item look
            class8.modelZoom = 1957;
            class8.modelRotation1 = 498;
            class8.modelRotation2 = 484;
            class8.anInt204 = 0;
            class8.modelOffset1 = -1;
            class8.modelOffset2 = -1;
            class8.anInt165 = 7355;
            class8.anInt200 = 7244;
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.name = "Bandos godsword ";
            class8.description = "A great weapon, used by the best warriors.".getBytes();
        }
        if (i == 9103) {
            class8.actions = new String[5];
            class8.actions[1] = "Wear";
            class8.modifiedModelColors = new int[1];
            class8.originalModelColors = new int[1];
            class8.modifiedModelColors[0] = 0;
            class8.originalModelColors[0] = 0;
            class8.modelID = 13422;//item look
            class8.modelZoom = 724;
            class8.modelRotation1 = 171;
            class8.modelRotation2 = 2039;
            class8.anInt204 = 144;
            class8.modelOffset1 = 3;
            class8.modelOffset2 = 3;
            class8.anInt165 = 13423;
            class8.anInt200 = 19951;
            class8.anInt175 = 28259;
            class8.anInt197 = 28260;
            class8.name = "Bandos boots";
            class8.description = "A nice set of Bandos boots, worn once by the god Bandos.".getBytes();
        }
        if (i == 9105) {
            class8.actions = new String[5];
            class8.actions[1] = "Wear";
            class8.modifiedModelColors = new int[1];
            class8.originalModelColors = new int[1];
            class8.modifiedModelColors[0] = 0;
            class8.originalModelColors[0] = 0;
            class8.modelID = 13426;
            class8.modelZoom = 1957;
            class8.modelRotation1 = 498;
            class8.modelRotation2 = 484;
            class8.anInt204 = 0;
            class8.modelOffset1 = -1;
            class8.modelOffset2 = -1;
            class8.anInt165 = 13427;
            class8.anInt200 = 7295;
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.name = "Saradomin Godsword";
            class8.description = "A saradomin godsword, once used by saradomin himself.".getBytes();
        }
        if (i == 9106) {
            class8.actions = new String[5];
            class8.actions[1] = "Wear";
            class8.modifiedModelColors = new int[1];
            class8.originalModelColors = new int[1];
            class8.modifiedModelColors[0] = 528;
            class8.originalModelColors[0] = 100;
            class8.modelID = 5412;
            class8.modelZoom = 840;
            class8.modelRotation1 = 280;
            class8.modelRotation2 = 0;
            class8.anInt204 = 0;
            class8.modelOffset1 = -2;
            class8.modelOffset2 = 56;
            class8.anInt165 = 5409;
            class8.anInt200 = 5409;
            class8.anInt188 = -1;
            class8.anInt164 = -1;
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.name = "White whip";
            class8.description = "Made By The White Knights.".getBytes();
        }
        if (i == 9107) // change this if you need to "item number"
        {
            class8.actions = new String[5];
            class8.actions[1] = "Wield";//New option
            class8.modifiedModelColors = new int[0];
            class8.originalModelColors = new int[0];
            class8.modelID = 7212; //Model ID
            class8.modelZoom = 1957;
            class8.modelRotation1 = 498;
            class8.modelRotation2 = 484;
            class8.anInt204 = 0;
            class8.modelOffset1 = -1;
            class8.modelOffset2 = -1;
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.anInt165 = 7209;//male wearing
            class8.anInt200 = 7209;//female wearing
            class8.stackable = false;//Stackable
            class8.name = "Armadyl Godsword";//Name of the new item
            class8.description = "A heavy sword".getBytes();//examin info
        }
        if (i == 9108) // change this if you need to "item number"
        {
            class8.actions = new String[5];
            class8.actions[1] = "Wield";//New option
            class8.modifiedModelColors = new int[0];
            class8.originalModelColors = new int[0];
            class8.modelID = 7213; //Model ID
            class8.modelZoom = 1957;
            class8.modelRotation1 = 498;
            class8.modelRotation2 = 484;
            class8.anInt204 = 0;
            class8.modelOffset1 = -1;
            class8.modelOffset2 = -1;
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.anInt165 = 7206;//male wearing
            class8.anInt200 = 7206;//female wearing
            class8.stackable = false;//Stackable
            class8.name = "Zamorak Godsword";//Name of the new item
            class8.description = "A heavy sword".getBytes();//examin info
        }
        if (i == 9109) {
            class8.actions = new String[5];
            class8.actions[1] = "Wear";
            class8.modifiedModelColors = new int[3];
            class8.originalModelColors = new int[3];
            class8.modifiedModelColors[0] = 43127;
            class8.originalModelColors[0] = 43968;
            class8.modifiedModelColors[1] = 38119;
            class8.originalModelColors[1] = 43968;
            class8.modifiedModelColors[2] = 36975;
            class8.originalModelColors[2] = 43968;
            class8.modelID = 5198;//Drop model & inv model
            class8.modelZoom = 1900;
            class8.modelRotation1 = 500;
            class8.modelRotation2 = 500;
            class8.anInt204 = 0;
            class8.modelOffset1 = 3;
            class8.modelOffset2 = 6;
            class8.anInt200 = 5196;//female wearing
            class8.anInt165 = 5196;//male wearing
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.name = "Blue Crystal shield";
            class8.description = "Blue Crystal shield by Ric914".getBytes();
        }
        if (i == 9110) { //D legs
            class8.actions = new String[5];
            class8.actions[1] = "Wear";
            class8.modifiedModelColors = new int[2];
            class8.originalModelColors = new int[2];
            class8.modifiedModelColors[0] = 926;
            class8.originalModelColors[0] = 43934;
            class8.modifiedModelColors[1] = 912;
            class8.originalModelColors[1] = 391;//43920
            class8.modelZoom = 1740;
            class8.modelRotation1 = 444;
            class8.modelRotation2 = 0;
            class8.anInt204 = 0;
            class8.modelOffset1 = 0;
            class8.modelOffset2 = -8;
            class8.anInt165 = 5024;
            class8.anInt200 = 5025;
            class8.modelID = 5026;
            class8.anInt188 = -1;
            class8.anInt164 = -1;
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.name = "Dragon Platelegs [b]";
            class8.description = "Blue dragon platelegs by jukk".getBytes();
        }
        if (i == 9111) { //D chain
            class8.actions = new String[5];
            class8.actions[1] = "Wear";
            class8.modifiedModelColors = new int[5];
            class8.originalModelColors = new int[5];
            class8.modifiedModelColors[0] = 914;
            class8.originalModelColors[0] = 43920;
            class8.modifiedModelColors[1] = 918;
            class8.originalModelColors[1] = 43920;
            class8.modifiedModelColors[2] = 922;
            class8.originalModelColors[2] = 43934;
            class8.modifiedModelColors[3] = 391;
            class8.originalModelColors[3] = 43934;
            class8.modifiedModelColors[4] = 917;
            class8.originalModelColors[4] = 43934;
            class8.modelZoom = 1100;
            class8.modelRotation1 = 568;
            class8.modelRotation2 = 0;
            class8.anInt204 = 0;
            class8.modelOffset1 = 0;
            class8.modelOffset2 = 2;
            class8.anInt165 = 3820;
            class8.anInt200 = 3821;
            class8.modelID = 3823;
            class8.anInt188 = 156;
            class8.anInt164 = 337;
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.name = "Dragon Chain [b]";
            class8.description = "Blue dragon Chain by jukk".getBytes();
        }
        if (i == 9112) {
            class8.actions = new String[5];
            class8.actions[1] = "Wear";
            class8.modifiedModelColors = new int[22];
            class8.originalModelColors = new int[22];
            class8.modifiedModelColors[0] = 78;
            class8.originalModelColors[0] = 910;
            class8.modifiedModelColors[1] = 20;
            class8.originalModelColors[1] = 910;
            class8.modifiedModelColors[2] = 24;
            class8.originalModelColors[2] = 920;
            class8.modifiedModelColors[3] = 82;
            class8.originalModelColors[3] = 920;
            class8.modifiedModelColors[4] = 86;
            class8.originalModelColors[4] = 930;
            class8.modifiedModelColors[5] = 28;
            class8.originalModelColors[5] = 930;
            class8.modifiedModelColors[6] = 53;
            class8.originalModelColors[6] = 905;
            class8.modifiedModelColors[7] = 49;
            class8.originalModelColors[7] = 920;
            class8.modifiedModelColors[8] = 57;
            class8.originalModelColors[8] = 920;
            class8.modifiedModelColors[9] = 45;
            class8.originalModelColors[9] = 920;
            class8.modifiedModelColors[10] = 33;
            class8.originalModelColors[10] = 920;
            class8.modifiedModelColors[11] = 37;
            class8.originalModelColors[11] = 905;
            class8.modifiedModelColors[12] = 41;
            class8.originalModelColors[12] = 920;
            class8.modifiedModelColors[13] = 305;
            class8.originalModelColors[13] = 10;
            class8.modifiedModelColors[14] = 22;
            class8.originalModelColors[14] = 1;
            class8.modifiedModelColors[15] = 3546;
            class8.originalModelColors[15] = 30;//903
            class8.modifiedModelColors[16] = 3776;
            class8.originalModelColors[16] = 30;//903
            class8.modifiedModelColors[17] = 3996;
            class8.originalModelColors[17] = 30;//901
            class8.modifiedModelColors[18] = 99;
            class8.originalModelColors[18] = 920;
            class8.modifiedModelColors[19] = 3895;
            class8.originalModelColors[19] = 30;//903
            class8.modifiedModelColors[20] = 947;
            class8.originalModelColors[20] = 100;
            class8.modifiedModelColors[21] = 51;
            class8.originalModelColors[21] = 920;
            class8.modelZoom = 1800;
            class8.modelRotation1 = 400;
            class8.modelRotation2 = 100;
            class8.anInt204 = 20;
            class8.modelOffset1 = 1;
            class8.modelOffset2 = 1;
            class8.modelID = 8;
            class8.anInt200 = 7;
            class8.anInt165 = 7;
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.name = "Red Dragonfire Shield";
            class8.description = "Red Dragonfire Shield".getBytes();
        }
        if (i == 9113) {
            class8.actions = new String[5];
            class8.actions[1] = "Wield";
            class8.modelID = 10910;
            class8.modelZoom = 1744;
            class8.modelRotation1 = 285;
            class8.modelRotation2 = 1508;
            class8.modelOffset1 = -1;
            class8.modelOffset2 = 46;
            class8.anInt165 = 10911;
            class8.anInt200 = 10911;
            class8.name = "Pharaoh's sceptre";
            class8.description = "This sceptre is fully charged.".getBytes();
        }

        if (i == 9114) {
            class8.actions = new String[5];
            class8.actions[1] = "Wear";
            class8.modifiedModelColors = new int[2];
            class8.originalModelColors = new int[2];
            class8.modifiedModelColors[0] = 29456;
            class8.originalModelColors[0] = 0;
            class8.modifiedModelColors[1] = 127;
            class8.originalModelColors[1] = 7097;
            class8.modelZoom = 1000;
            class8.modelRotation1 = 0;
            class8.modelRotation2 = 300;
            class8.anInt204 = 0;
            class8.modelOffset1 = -14;
            class8.modelOffset2 = 27;
            class8.modelID = 1;
            class8.anInt200 = 1;
            class8.anInt165 = 1;
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.name = "Briefcase";
            class8.description = "Briefcase by jukk".getBytes();
        }

        if (i == 9115) {
            class8.actions = new String[5];
            class8.actions[1] = "Wear";
            class8.modifiedModelColors = new int[3];
            class8.originalModelColors = new int[3];
            class8.modifiedModelColors[0] = 43127;
            class8.originalModelColors[0] = 128;
            class8.modifiedModelColors[1] = 38119;
            class8.originalModelColors[1] = 128;
            class8.modifiedModelColors[2] = 36975;
            class8.originalModelColors[2] = 128;
            class8.modelID = 5198;//Drop model & inv model
            class8.modelZoom = 1900;
            class8.modelRotation1 = 500;
            class8.modelRotation2 = 500;
            class8.anInt204 = 0;
            class8.modelOffset1 = 3;
            class8.modelOffset2 = 6;
            class8.anInt200 = 5196;//female wearing
            class8.anInt165 = 5196;//male wearing
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.name = "Black Crystal shield";
            class8.description = "Black Crystal shield by Ric914".getBytes();
        }

        if (i == 9116) {
            class8.actions = new String[5];
            class8.actions[1] = "Wear";
            class8.modifiedModelColors = new int[22];
            class8.originalModelColors = new int[22];
            class8.modifiedModelColors[0] = 78;
            class8.originalModelColors[0] = 43934;
            class8.modifiedModelColors[1] = 20;
            class8.originalModelColors[1] = 43934;
            class8.modifiedModelColors[2] = 24;
            class8.originalModelColors[2] = 43934;
            class8.modifiedModelColors[3] = 82;
            class8.originalModelColors[3] = 43934;
            class8.modifiedModelColors[4] = 86;
            class8.originalModelColors[4] = 43934;
            class8.modifiedModelColors[5] = 28;
            class8.originalModelColors[5] = 43934;
            class8.modifiedModelColors[6] = 53;
            class8.originalModelColors[6] = 905;
            class8.modifiedModelColors[7] = 49;
            class8.originalModelColors[7] = 43934;
            class8.modifiedModelColors[8] = 57;
            class8.originalModelColors[8] = 43934;
            class8.modifiedModelColors[9] = 45;
            class8.originalModelColors[9] = 43934;
            class8.modifiedModelColors[10] = 33;
            class8.originalModelColors[10] = 43934;
            class8.modifiedModelColors[11] = 37;
            class8.originalModelColors[11] = 905;
            class8.modifiedModelColors[12] = 41;
            class8.originalModelColors[12] = 43934;
            class8.modifiedModelColors[13] = 305;
            class8.originalModelColors[13] = 10;
            class8.modifiedModelColors[14] = 22;
            class8.originalModelColors[14] = 1;
            class8.modifiedModelColors[15] = 3546;
            class8.originalModelColors[15] = 30;//903
            class8.modifiedModelColors[16] = 3776;
            class8.originalModelColors[16] = 30;//903
            class8.modifiedModelColors[17] = 3996;
            class8.originalModelColors[17] = 30;//901
            class8.modifiedModelColors[18] = 99;
            class8.originalModelColors[18] = 43934;
            class8.modifiedModelColors[19] = 3895;
            class8.originalModelColors[19] = 30;//903
            class8.modifiedModelColors[20] = 947;
            class8.originalModelColors[20] = 100;
            class8.modifiedModelColors[21] = 51;
            class8.originalModelColors[21] = 43934;
            class8.modelZoom = 1800;
            class8.modelRotation1 = 400;
            class8.modelRotation2 = 100;
            class8.anInt204 = 20;
            class8.modelOffset1 = 1;
            class8.modelOffset2 = 1;
            class8.modelID = 8;
            class8.anInt200 = 7;
            class8.anInt165 = 7;
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.name = "Blue Dragonfire Shield";
            class8.description = "Blue Dragonfire Shield".getBytes();
        }

        if (i == 9117) {
            class8.actions = new String[5];
            class8.actions[1] = "Wear"; //this is the new option to equipt, can change to w/e u want
            class8.modifiedModelColors = new int[1];
            class8.originalModelColors = new int[1];
            class8.modifiedModelColors[0] = 926;  //original color
            class8.originalModelColors[0] = 0; //changed color
            class8.modelID = 2438;
            class8.modelZoom = 730;
            class8.modelRotation1 = 516;
            class8.modelRotation2 = 0;
            class8.anInt204 = 0;
            class8.modelOffset1 = 0;
            class8.modelOffset2 = -10;
            class8.anInt165 = 3188;
            class8.anInt200 = 3192;
            class8.anInt188 = -1;
            class8.anInt164 = -1;
            class8.anInt175 = 1755;
            class8.anInt197 = 3187;
            class8.name = "Black h'ween Mask";
            class8.description = "Aaaarrrghhh... I'm a monster.".getBytes();
        }
        if (i == 9118) {
            class8.actions = new String[5];
            class8.actions[1] = "Wear"; //this is the new option to equipt, can change to w/e u want
            class8.modifiedModelColors = new int[1];
            class8.originalModelColors = new int[1];
            class8.modifiedModelColors[0] = 926;  //original color
            class8.originalModelColors[0] = 11200; //changed color
            class8.modelID = 2438;
            class8.modelZoom = 730;
            class8.modelRotation1 = 516;
            class8.modelRotation2 = 0;
            class8.anInt204 = 0;
            class8.modelOffset1 = 0;
            class8.modelOffset2 = -10;
            class8.anInt165 = 3188;
            class8.anInt200 = 3192;
            class8.anInt188 = -1;
            class8.anInt164 = -1;
            class8.anInt175 = 1755;
            class8.anInt197 = 3187;
            class8.name = "Yellow h'ween Mask";
            class8.description = "Aaaarrrghhh... I'm a monster.".getBytes();
        }

        if (i == 9119) {
            class8.actions = new String[5];
            class8.actions[1] = "Wear";
            class8.modifiedModelColors = new int[3];
            class8.originalModelColors = new int[3];
            class8.modifiedModelColors[0] = 0xD4E7;
            class8.modifiedModelColors[1] = 0xDFC0;
            class8.modifiedModelColors[2] = 0xD3A7;
            class8.originalModelColors[0] = 8128;
            class8.originalModelColors[1] = 9126;
            class8.originalModelColors[2] = 8128;
            class8.modelID = 7022; //Inv & Ground
            class8.modelZoom = 2000; //Zoom
            class8.modelRotation1 = 572;
            class8.modelRotation2 = 0;
            class8.anInt204 = 0;
            class8.modelOffset1 = 0;
            class8.modelOffset2 = 1;
            class8.anInt165 = 7021; //Male
            class8.anInt200 = 7021; //Female
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.name = "Summoning Cape(t)";
            class8.description = "You've received a Summoning Cape(t)".getBytes();
        }
        if (i == 9120) {
            class8.actions = new String[5];
            class8.actions[1] = "Wear";
            class8.modifiedModelColors = new int[0];
            class8.originalModelColors = new int[0];
            class8.modelZoom = 1200;
            class8.modelRotation1 = 1;
            class8.modelRotation2 = 1;
            class8.anInt204 = 1;
            class8.modelOffset1 = 1;
            class8.modelOffset2 = 50;
            class8.modelID = 25;		// Drop model & inv model
            class8.anInt200 = 25;		// Female wearing
            class8.anInt165 = 25;		// Male wearing
            class8.anInt164 = 26;		// Female arms/sleeves
            class8.anInt188 = 26;		// male arms/sleeves
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.name = "Jacket";
            class8.description = "A Jacket made by Quint".getBytes();
        }
        if (i == 9121) { //White Mask
            class8.actions = new String[5];
            class8.actions[1] = "Wear";
            class8.modifiedModelColors = new int[1];
            class8.originalModelColors = new int[1];
            class8.modifiedModelColors[0] = 926;
            class8.originalModelColors[0] = 100;
            class8.modelID = 2438;
            class8.modelZoom = 730;
            class8.modelRotation1 = 516;
            class8.modelRotation2 = 0;
            class8.anInt204 = 0;
            class8.modelOffset1 = 0;
            class8.modelOffset2 = -10;
            class8.anInt165 = 3188;
            class8.anInt200 = 3192;
            class8.anInt175 = 1755;
            class8.anInt197 = 3187;
            class8.name = "White h'ween Mask";
            class8.description = "It's a white h'ween mask.".getBytes();
        }
        if (i == 9122) {
            class8.actions = new String[5];
            class8.actions[1] = "Teleport";
            class8.modifiedModelColors = new int[1];
            class8.originalModelColors = new int[1];
            class8.modifiedModelColors[0] = 0xFFFFFF;
            class8.originalModelColors[0] = 0;
            class8.modelID = 12325;
            class8.modelRotation1 = 600;
            class8.modelZoom = 440;
            class8.modelOffset1 = 0;
            class8.modelOffset2 = 1;
            class8.stackable = true;
            class8.name = "Varrock Teleport";
            class8.description = "A Teleportation Tablet ".getBytes();
        }
        if (i == 9123) {
            class8.actions = new String[5];
            class8.actions[1] = "Teleport";
            class8.modifiedModelColors = new int[1];
            class8.originalModelColors = new int[1];
            class8.modifiedModelColors[0] = 0xFFFFFF;
            class8.originalModelColors[0] = 0;
            class8.modelID = 12324;
            class8.modelRotation1 = 500;
            class8.modelZoom = 440;
            class8.modelOffset1 = 0;
            class8.modelOffset2 = 1;
            class8.stackable = true;
            class8.name = "Home Teleport";
            class8.description = "A Teleportation Tablet ".getBytes();
        }
        if (i == 9124) {
            class8.actions = new String[5];
            class8.actions[1] = "Teleport";
            class8.modifiedModelColors = new int[1];
            class8.originalModelColors = new int[1];
            class8.modifiedModelColors[0] = 0xFFFFFF;
            class8.originalModelColors[0] = 0;
            class8.modelID = 12322;
            class8.modelRotation1 = 500;
            class8.modelZoom = 440;
            class8.modelOffset1 = 0;
            class8.modelOffset2 = 1;
            class8.stackable = true;
            class8.name = "Lumbridge Teleport";
            class8.description = "A Teleportation Tablet ".getBytes();
        }
        if (i == 9125) {
            class8.actions = new String[5];
            class8.actions[1] = "Teleport";
            class8.modifiedModelColors = new int[1];
            class8.originalModelColors = new int[1];
            class8.modifiedModelColors[0] = 0xFFFFFF;
            class8.originalModelColors[0] = 0;
            class8.modelID = 12321;
            class8.modelRotation1 = 600;
            class8.modelZoom = 440;
            class8.modelOffset1 = 0;
            class8.modelOffset2 = 1;
            class8.stackable = true;
            class8.name = "Falador Teleport";
            class8.description = "A Teleportation Tablet ".getBytes();
        }
        if (i == 9126) {
            class8.actions = new String[5];
            class8.actions[1] = "Teleport";
            class8.modifiedModelColors = new int[1];
            class8.originalModelColors = new int[1];
            class8.modifiedModelColors[0] = 0xFFFFFF;
            class8.originalModelColors[0] = 0;
            class8.modelID = 12314;
            class8.modelRotation1 = 600;
            class8.modelZoom = 440;
            class8.modelOffset1 = 0;
            class8.modelOffset2 = 1;
            class8.stackable = true;
            class8.name = "Camelot Teleport";
            class8.description = "A Teleportation Tablet".getBytes();
        }
        if (i == 9127) {
            class8.actions = new String[5];
            class8.actions[1] = "Teleport";
            class8.modifiedModelColors = new int[1];
            class8.originalModelColors = new int[1];
            class8.modifiedModelColors[0] = 0xFFFFFF;
            class8.originalModelColors[0] = 0;
            class8.modelID = 12311;
            class8.modelRotation1 = 600;
            class8.modelZoom = 440;
            class8.modelOffset1 = 0;
            class8.modelOffset2 = 1;
            class8.stackable = true;
            class8.name = "Ardy Teleport";
            class8.description = "A Teleportation Tablet ".getBytes();
        }
        if (i == 9128) //Saradomin sword
        {
            class8.actions = new String[5];
            class8.actions[1] = "Wield";
            class8.modifiedModelColors = new int[1];
            class8.originalModelColors = new int[1];
            class8.modifiedModelColors[0] = 0;
            class8.originalModelColors[0] = 0;
            class8.modelID = 32;//Item Look
            class8.modelZoom = 2151;
            class8.modelRotation1 = 1521;
            class8.modelRotation2 = 484;
            class8.modelOffset1 = -1;
            class8.modelOffset2 = -1;
            class8.anInt165 = 8032;
            class8.anInt200 = 8032;
            class8.name = "Saradomin sword";
            class8.description = "The magnificent blade of Saradomin!".getBytes();
        }
        if (i == 9129) {
            class8.actions = new String[5];
            class8.actions[1] = "Wear";
            class8.modelID = 10076;//15615
            class8.modelZoom = 854;
            class8.modelRotation1 = 387;
            class8.modelRotation2 = 1892;
            class8.anInt204 = 138;
            class8.modelOffset1 = -1;
            class8.modelOffset2 = 3;
            class8.anInt165 = 10077;//15935
            class8.anInt200 = 10078;//15946
            class8.anInt188 = -1;
            class8.anInt164 = -1;
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.name = "Black Mask";
            class8.description = "A valuable slayer item.".getBytes();
        }
        if (i == 9130) {
            class8.actions = new String[5];
            class8.actions[1] = "Wield";
            class8.modelID = 10082;//16876
            class8.modelZoom = 926;
            class8.modelRotation1 = 432;
            class8.modelRotation2 = 258;
            class8.anInt204 = 83;
            class8.modelOffset1 = 3;
            class8.modelOffset2 = 3;
            class8.anInt165 = 10083;//16846
            class8.anInt200 = 10083;//16846
            class8.anInt188 = -1;
            class8.anInt164 = -1;
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.name = "Bronze c'bow";
            class8.description = "It's powerfull for bronze.".getBytes();
        }
        if (i == 9131) {
            class8.actions = new String[5];
            class8.actions[1] = "Wield";
            class8.modelID = 10082;//16876
            class8.modelZoom = 926;
            class8.modelRotation1 = 432;
            class8.modelRotation2 = 258;
            class8.anInt204 = 83;
            class8.modelOffset1 = 3;
            class8.modelOffset2 = 3;
            class8.anInt165 = 10083;//16846
            class8.anInt200 = 10083;//16846
            class8.anInt188 = -1;
            class8.anInt164 = -1;
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.name = "Iron c'bow";
            class8.description = "It's powerfull for iron.".getBytes();
            class8.modifiedModelColors = new int[1];//Start of old colors
            class8.modifiedModelColors[0] = 5656;
            class8.originalModelColors = new int[1];//Start of new colors
            class8.originalModelColors[0] = 33;
        }
        if (i == 9132) {
            class8.actions = new String[5];
            class8.actions[1] = "Wield";
            class8.modelID = 10082;//16876
            class8.modelZoom = 926;
            class8.modelRotation1 = 432;
            class8.modelRotation2 = 258;
            class8.anInt204 = 83;
            class8.modelOffset1 = 3;
            class8.modelOffset2 = 3;
            class8.anInt165 = 10083;//16846
            class8.anInt200 = 10083;//16846
            class8.anInt188 = -1;
            class8.anInt164 = -1;
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.name = "Steel c'bow";
            class8.description = "It's powerfull for steel.".getBytes();
            class8.modifiedModelColors = new int[1];//Start of old colors
            class8.modifiedModelColors[0] = 5656;
            class8.originalModelColors = new int[1];//Start of new colors
            class8.originalModelColors[0] = 43072;
        }
        if (i == 9133) {
            class8.actions = new String[5];
            class8.actions[1] = "Wield";
            class8.modelID = 10082;//16876
            class8.modelZoom = 926;
            class8.modelRotation1 = 432;
            class8.modelRotation2 = 258;
            class8.anInt204 = 83;
            class8.modelOffset1 = 3;
            class8.modelOffset2 = 3;
            class8.anInt165 = 10083;//16846
            class8.anInt200 = 10083;//16846
            class8.anInt188 = -1;
            class8.anInt164 = -1;
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.name = "Black c'bow";
            class8.description = "It's powerfull for black.".getBytes();
            class8.modifiedModelColors = new int[1];//Start of old colors
            class8.modifiedModelColors[0] = 5656;
            class8.originalModelColors = new int[1];//Start of new colors
            class8.originalModelColors[0] = 0;
        }
        if (i == 9134) {
            class8.actions = new String[5];
            class8.actions[1] = "Wield";
            class8.modelID = 10082;//16876
            class8.modelZoom = 926;
            class8.modelRotation1 = 432;
            class8.modelRotation2 = 258;
            class8.anInt204 = 83;
            class8.modelOffset1 = 3;
            class8.modelOffset2 = 3;
            class8.anInt165 = 10083;//16846
            class8.anInt200 = 10083;//16846
            class8.anInt188 = -1;
            class8.anInt164 = -1;
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.name = "Adamant c'bow";
            class8.description = "It's powerfull for steel.".getBytes();
            class8.modifiedModelColors = new int[1];//Start of old colors
            class8.modifiedModelColors[0] = 5656;
            class8.originalModelColors = new int[1];//Start of new colors
            class8.originalModelColors[0] = 21662;
        }
        if (i == 9135) {
            class8.actions = new String[5];
            class8.actions[1] = "Wield";
            class8.modelID = 10082;//16876
            class8.modelZoom = 926;
            class8.modelRotation1 = 432;
            class8.modelRotation2 = 258;
            class8.anInt204 = 83;
            class8.modelOffset1 = 3;
            class8.modelOffset2 = 3;
            class8.anInt165 = 10083;//16846
            class8.anInt200 = 10083;//16846
            class8.anInt188 = -1;
            class8.anInt164 = -1;
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.name = "Rune c'bow";
            class8.description = "It's powerfull for rune.".getBytes();
            class8.modifiedModelColors = new int[1];//Start of old colors
            class8.modifiedModelColors[0] = 5656;
            class8.originalModelColors = new int[1];//Start of new colors
            class8.originalModelColors[0] = 36133;
        }
        if (i == 9136) {
            class8.actions = new String[5];
            class8.actions[1] = "Wield";//New option
            class8.modifiedModelColors = new int[0];
            class8.originalModelColors = new int[0];
            class8.modelID = 1851; //Model ID
            class8.modelZoom = 1851;
            class8.modelRotation1 = 498;
            class8.modelRotation2 = 484;
            class8.anInt204 = 0;
            class8.modelOffset1 = -1;
            class8.modelOffset2 = -1;
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.anInt165 = 1851;//male wearing
            class8.anInt200 = 1851;//female wearing
            class8.stackable = false;//Stackable
            class8.name = "Narsil";
            class8.description = "The sword of a King...".getBytes();
        }
        if (i == 9137) {
            class8.actions = new String[5];
            class8.actions[1] = "Wear";
            class8.modifiedModelColors = new int[1];
            class8.originalModelColors = new int[1];
            class8.modifiedModelColors[0] = 0;
            class8.originalModelColors[0] = 0;
            class8.modelID = 14565;
            class8.modelZoom = 1178;
            class8.modelRotation1 = 498;
            class8.modelRotation2 = 1840;
            class8.anInt204 = 0;
            class8.modelOffset1 = 0;
            class8.modelOffset2 = 2;
            class8.anInt165 = 14561;
            class8.anInt200 = 14561;
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.stackable = false;
            class8.name = "Armadyl Helm";
            class8.description = "The Armadyl Helm".getBytes();
        }
        if (i == 9138) {
            class8.actions = new String[5];
            class8.actions[1] = "Wield";
            class8.modifiedModelColors = new int[1];
            class8.originalModelColors = new int[1];
            class8.modelID = 13047;
            class8.modelZoom = 1940;
            class8.modelRotation1 = 572;
            class8.modelRotation2 = 0;
            class8.anInt204 = 0;
            class8.modelOffset1 = 0;
            class8.modelOffset2 = 1;
            class8.anInt165 = 13046;
            class8.anInt200 = 13046;
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.name = "Yarnova's scythe";
            class8.description = "A rare and powerfull weapon.".getBytes();
        }
        if (i == 9139) // ItemID
        {
            class8.actions = new String[5];
            class8.actions[1] = "Wield";
            class8.modelID = 8;
            class8.modelZoom = 2022;
            class8.modelRotation1 = 540;
            class8.modelRotation2 = 123;
            class8.anInt204 = 0;
            class8.anInt199 = 0;
            class8.modelOffset1 = 3;
            class8.modelOffset2 = 0;
            class8.anInt165 = 7;
            class8.anInt200 = 7;
            class8.anInt175 = 56;
            class8.anInt197 = 116;
            class8.stackable = false;
            class8.name = "Dragonfire Shield";
            class8.description = "A dragonfire shield!!".getBytes();
        }
        if (i == 9140) { //Skele top;
            class8.actions = new String[5];
            class8.actions[1] = "Wear";
            class8.modifiedModelColors = new int[2];
            class8.originalModelColors = new int[2];
            class8.modelID = 13015;
            class8.modelZoom = 1000;
            class8.modelRotation1 = 543;
            class8.modelRotation2 = 0;
            class8.modelOffset1 = 0;
            class8.modelOffset2 = 0;
            class8.anInt165 = 13016;
            class8.anInt200 = 13017;
            class8.anInt188 = 13018;
            class8.anInt164 = 13019;
            class8.stackable = false;
            class8.name = "Skele top";
            class8.description = "Skele top from halloween".getBytes();
        }
        if (i == 9141) { //Skele legs;
            class8.actions = new String[5];
            class8.actions[1] = "Wear";
            class8.modelID = 13020;
            class8.modelZoom = 1750;
            class8.modelRotation1 = 572;
            class8.modelRotation2 = 0;
            class8.modelOffset1 = 4;
            class8.modelOffset2 = 11;
            class8.anInt165 = 13022;
            class8.anInt200 = 13021;
            class8.anInt164 = -1;
            class8.anInt188 = -1;
            class8.stackable = false;
            class8.name = "Skele legs";
            class8.description = "Skele legs from halloween".getBytes();
        }
        if (i == 9142) { //Skele helm;
            class8.actions = new String[5];
            class8.actions[1] = "Wear";
            class8.modelID = 13023;
            class8.modelZoom = 548;
            class8.modelRotation1 = 148;
            class8.modelRotation2 = 120;
            class8.modelOffset1 = 0;
            class8.modelOffset2 = 0;
            class8.anInt204 = 24;
            class8.anInt165 = 13024;
            class8.anInt200 = 13025;
            class8.anInt175 = -1;
            class8.stackable = false;
            class8.name = "Skele helm";
            class8.description = "Skele helm from halloween".getBytes();
        }
        if (i == 9143) { //Skele boots;
            class8.actions = new String[5];
            class8.actions[1] = "Wear";
            class8.modelID = 13026;
            class8.modelZoom = 585;
            class8.modelRotation1 = 0;
            class8.modelRotation2 = 0;
            class8.anInt204 = 0;
            class8.anInt199 = 0;
            class8.modelOffset1 = 1;
            class8.modelOffset2 = 1;
            class8.anInt204 = 24;
            class8.anInt165 = 13027;
            class8.anInt200 = 13028;
            class8.anInt175 = 56;
            class8.anInt197 = 116;
            class8.anInt164 = -1;
            class8.anInt188 = -1;
            class8.stackable = false;
            class8.name = "Skele boots";
            class8.description = "Skele boots from halloween".getBytes();
        }
        if (i == 9144) { //Skele gloves;
            class8.actions = new String[5];
            class8.actions[1] = "Wear";
            class8.modifiedModelColors = new int[2];
            class8.originalModelColors = new int[2];
            class8.modifiedModelColors[0] = 10004;
            class8.modifiedModelColors[1] = 5532;
            class8.originalModelColors[0] = 0;
            class8.originalModelColors[1] = 0;
            class8.modelRotation1 = 420;
            class8.modelRotation2 = 828;
            class8.modelOffset1 = 8;
            class8.modelOffset2 = -8;
            class8.modelZoom = 540;
            class8.anInt204 = 138;
            class8.anInt188 = -1;
            class8.anInt164 = -1;
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.modelID = 13029;
            class8.anInt165 = 13030;
            class8.anInt200 = 13031;
            class8.stackable = false;
            class8.name = "Skele gloves";
            class8.description = "Skele gloves from halloween".getBytes();
        }
        if (i == 9094) //WH1P!!!!
        {
            class8.actions = new String[5];
            class8.actions[1] = "Wear"; //this is the new option to equipt, can change to w/e u want
            class8.modifiedModelColors = new int[1];
            class8.originalModelColors = new int[1];
            class8.modifiedModelColors[0] = 528;  //original color
            class8.originalModelColors[0] = 36133; //changed color
            class8.modelID = 5412;
            class8.modelZoom = 840;
            class8.modelRotation1 = 280;
            class8.modelRotation2 = 0;
            class8.anInt204 = 0;
            class8.modelOffset1 = -2;
            class8.modelOffset2 = 56;
            class8.anInt165 = 5409;
            class8.anInt200 = 5409;
            class8.anInt188 = -1;
            class8.anInt164 = -1;
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.name = "Rune Whip";
            class8.description = "A whip made of Rune".getBytes();
        }
        if (i == 9096) //Replace with the id you want
        {
            class8.actions = new String[5];
            class8.actions[1] = "Wield";//New option
            class8.modelID = 6944; //Model ID
            class8.modelZoom = 1050; //1200 zoom increase will make it smaller
            class8.modelRotation1 = 572; //model rotate up+down increase to move doen away from you
            class8.modelRotation2 = 0; //model rotate side ways increase to move right in circle
            class8.modelOffset1 = 0; // model offset increase to move to the right
            class8.modelOffset2 = 1; //model offset increase to move up
            class8.anInt165 = 6940;//male wearing
            class8.anInt200 = 6943;//female wearing
            class8.anInt164 = 6942;//Female arms/sleeves
            class8.anInt188 = 6941;//male arms/sleeves
            class8.stackable = false;//Stackable
            class8.name = "Zamorak Robe top";//Name of the new item
            class8.description = "A Robe Top Worn By the Gods.".getBytes();//examin info
        }

        if (i == 9097) //Replace with the id you want
        {
            class8.actions = new String[5];
            class8.actions[1] = "Wield";//New option
            class8.modelID = 6947; //Model ID
            class8.modelZoom = 1800; //1690 zoom increase will make it smaller
            class8.modelRotation1 = 408; //model rotate up+down increase to move doen away from you
            class8.modelRotation2 = 0; //2024 model rotate side ways increase to move right in circle
            class8.modelOffset1 = 5; // model offset increase to move to the right
            class8.modelOffset2 = 0; //model offset increase to move up
            class8.anInt165 = 6945;//male wearing
            class8.anInt200 = 6946;//female wearing
            class8.stackable = false;//Stackable
            class8.name = "Zamorak Robe Bottom";//Name of the new item
            class8.description = "A Robe Bottom Worn By the Gods.".getBytes();//examin info
        }

        if (i == 9098) //Replace with the id you want
        {
            class8.actions = new String[5];
            class8.actions[1] = "Wield";//New option
            class8.modelID = 6948; //Model ID
            class8.modelZoom = 600; //980 zoom increase will make it smaller
            class8.modelRotation1 = 208; //model rotate up+down increase to move doen away from you
            class8.modelRotation2 = 220; //model rotate side ways increase to move right in circle
            class8.modelOffset1 = 0; // model offset increase to move to the right
            class8.modelOffset2 = -18; //model offset increase to move up
            class8.anInt165 = 6949;//male wearing
            class8.anInt200 = 6950;//female wearing
            class8.stackable = false;//Stackable
            class8.name = "Zamorak Mitre";//Name of the new item
            class8.description = "A Hat Worn By the Gods.".getBytes();//examin info
        }


        if (i == 9099) //Replace with the id you want
        {
            class8.actions = new String[5];
            class8.actions[1] = "Wield";//New option
            class8.modelID = 6953; //Model ID
            class8.modelZoom = 3000; //zoom increase will make it smaller
            class8.modelRotation1 = 600; //model rotate up+down increase to move doen away from you
            class8.modelRotation2 = 1000; //model rotate side ways increase to move right in circle
            class8.modelOffset1 = 0; // model offset increase to move to the right
            class8.modelOffset2 = 2; //model offset increase to move up
            class8.anInt165 = 6951;//male wearing
            class8.anInt200 = 6952;//female wearing
            class8.stackable = false;//Stackable
            class8.name = "Zamorak Cape";//Name of the new item
            class8.description = "A Cape Worn By the Gods.".getBytes();//examin info
        }


        if (i == 9100) //Replace with the id you want
        {
            class8.actions = new String[5];
            class8.actions[1] = "Wield";//New option
            class8.modelID = 6954; //Model ID
            class8.modelZoom = 2000; //1200 zoom increase will make it smaller
            class8.modelRotation1 = 572; //model rotate up+down increase to move doen away from you
            class8.modelRotation2 = 0; //model rotate side ways increase to move right in circle
            class8.modelOffset1 = 0; // model offset increase to move to the right
            class8.modelOffset2 = 1; //model offset increase to move up
            class8.anInt165 = 6955;//male wearing
            class8.anInt200 = 6955;//female wearing
            class8.stackable = false;//Stackable
            class8.name = "Zamorak Crozier";//Name of the new item
            class8.description = "A Staff Used By the Gods.".getBytes();//examin info
        }


        if (i == 9101) //Replace with the id you want
        {
            class8.actions = new String[5];
            class8.actions[1] = "Wield";//New option
            class8.modelID = 6956; //Model ID
            class8.modelZoom = 1700; //1200 zoom increase will make it smaller
            class8.modelRotation1 = 490; //572 model rotate up+down increase to move doen away from you
            class8.modelRotation2 = 60; //0 model rotate side ways increase to move right in circle
            class8.modelOffset1 = 0; // model offset increase to move to the right
            class8.modelOffset2 = -10; //model offset increase to move up
            class8.anInt165 = 6982;//male wearing
            class8.anInt200 = 6984;//female wearing
            class8.stackable = false;//Stackable
            class8.name = "Zamorak Stole";//Name of the new item
            class8.description = "A Stole Worn By the Gods.".getBytes();//examin info
        }


        if (i == 9102) //Replace with the id you want
        {
            class8.actions = new String[5];
            class8.actions[1] = "Wield";//New option
            class8.modelID = 6983; //Model ID
            class8.modelZoom = 620; //620 zoom increase will make it smaller
            class8.modelRotation1 = 424; //424 model rotate up+down increase to move doen away from you
            class8.modelRotation2 = 8; //model rotate side ways increase to move right in circle
            class8.modelOffset1 = 1; // model offset increase to move to the right
            class8.modelOffset2 = 16; //model offset increase to move up
            class8.anInt165 = 6986;//male wearing
            class8.anInt200 = 6987;//female wearing
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.stackable = false;//Stackable
            class8.name = "Amulet of Strength";//Name of the new item
            class8.description = "An amulet that gives of a strong feeling of strength.".getBytes();//examin info
        }
        if (i == 9004) {
            class8.actions = new String[5];
            class8.actions[1] = "Wear";
            class8.modifiedModelColors = new int[6];
            class8.originalModelColors = new int[6];
            class8.modifiedModelColors = new int[6];
            class8.originalModelColors = new int[6];
            class8.modifiedModelColors[0] = 57280;
            class8.modifiedModelColors[1] = 54503;
            class8.modifiedModelColors[2] = 54183;
            class8.originalModelColors[0] = 22424;
            class8.originalModelColors[1] = 22424;
            class8.originalModelColors[2] = 22424;
            class8.modelID = 4177; //Inv & Ground
            class8.modelZoom = 2000; //Zoom
            class8.modelRotation1 = 572;
            class8.modelRotation2 = 0;
            class8.anInt204 = 0;
            class8.modelOffset1 = 0;
            class8.modelOffset2 = 1;
            class8.anInt165 = 4199; //Male
            class8.anInt200 = 7197; //Female
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.name = "Strength Cape";
            class8.description = "Its A Strenght Cape".getBytes();
        }
        if (i == 9091) {
            class8.actions = new String[5];
            class8.actions[1] = "Wear";
            class8.modifiedModelColors = new int[1];
            class8.originalModelColors = new int[1];
            //Purple: 926-127
            class8.modifiedModelColors[0] = 926;
            class8.originalModelColors[0] = 128;
            class8.modelID = 2635;//Item Look
            class8.modelZoom = 440;
            class8.modelRotation1 = 76;
            class8.modelRotation2 = 1850;
            class8.anInt204 = 0;
            class8.modelOffset1 = 1;
            class8.modelOffset2 = 1;
            class8.anInt165 = 187;
            class8.anInt200 = 363;
            class8.anInt175 = 29;
            class8.anInt197 = 87;
            class8.name = "Black Party Hat";
            class8.description = "A Black Party Hat Say Thanks to frugoo <3.".getBytes();
        }
        if (i == 9005) {
            class8.actions = new String[5];
            class8.actions[1] = "Wear";
            class8.modifiedModelColors = new int[6];
            class8.originalModelColors = new int[6];
            class8.modifiedModelColors = new int[6];
            class8.originalModelColors = new int[6];
            class8.modifiedModelColors[0] = 8256;
            class8.originalModelColors[0] = 926;
            class8.modifiedModelColors[1] = 54183;
            class8.originalModelColors[1] = 926;
            class8.modifiedModelColors[2] = 57280;
            class8.originalModelColors[2] = 926;
            class8.modifiedModelColors[3] = 960;
            class8.originalModelColors[3] = 926;
            class8.modifiedModelColors[4] = 22464;
            class8.originalModelColors[4] = 926;
            class8.modifiedModelColors[5] = 21568;
            class8.originalModelColors[5] = 926;
            class8.modelID = 4177; //Inv & Ground
            class8.modelZoom = 2000; //Zoom
            class8.modelRotation1 = 572;
            class8.modelRotation2 = 0;
            class8.anInt204 = 0;
            class8.modelOffset1 = 0;
            class8.modelOffset2 = 1;
            class8.anInt165 = 4199; //Male
            class8.anInt200 = 7197; //Female
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.name = "strength Cape(t)";
            class8.description = "Its A Strenght Cape(t)".getBytes();
        }
        if (i == 9006) {
            class8.actions = new String[5];
            class8.actions[1] = "Wear";
            class8.modifiedModelColors = new int[3];
            class8.originalModelColors = new int[3];
            class8.modifiedModelColors[0] = 960;
            class8.modifiedModelColors[1] = 22464;
            class8.modifiedModelColors[2] = 43968;
            class8.originalModelColors[0] = 22424;
            class8.originalModelColors[1] = 22424;
            class8.originalModelColors[2] = 22424;
            class8.modelID = 4203; //Model ID
            class8.modelZoom = 720; //zoom increase will make it smaller
            class8.modelRotation1 = 0; //model rotate up+down increase to move doen away from you
            class8.modelRotation2 = 0; //model rotate side ways increase to move right in circle
            class8.modelOffset1 = 0; // model offset increase to move to the right
            class8.modelOffset2 = 0; //model offset increase to move up
            class8.anInt165 = 4202;//male wearing
            class8.anInt200 = 4202;//female wearing
            class8.name = "Strength Hood";
            class8.description = "Its A Strength Hood".getBytes();
        }
        if (i == 9007) {
            class8.actions = new String[5];
            class8.actions[1] = "Wear";
            class8.modifiedModelColors = new int[4];
            class8.originalModelColors = new int[4];
            class8.modifiedModelColors[0] = 57280;
            class8.originalModelColors[0] = 41416;
            class8.modifiedModelColors[1] = 54503;
            class8.originalModelColors[1] = 41416;
            class8.modifiedModelColors[2] = 54183;
            class8.originalModelColors[2] = 41416;
            class8.modifiedModelColors[3] = 127;
            class8.originalModelColors[3] = 41416;
            class8.modelID = 4162; //Inv & Ground
            class8.modelZoom = 2000; //Zoom
            class8.modelRotation1 = 572;
            class8.modelRotation2 = 0;
            class8.anInt204 = 0;
            class8.modelOffset1 = 0;
            class8.modelOffset2 = 1;
            class8.anInt165 = 4184; //Male
            class8.anInt200 = 7182; //Female
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.anInt199 = -1;//noteable
            class8.name = "Defence Cape";
            class8.description = "Its A Defence Cape".getBytes();
        }
        if (i == 9008) {
            class8.actions = new String[5];
            class8.actions[1] = "Wear";
            class8.modifiedModelColors = new int[3];
            class8.originalModelColors = new int[3];
            class8.modifiedModelColors[0] = 57280;
            class8.originalModelColors[0] = 107;
            class8.modifiedModelColors[1] = 54503;
            class8.originalModelColors[1] = 107;
            class8.modifiedModelColors[2] = 54183;
            class8.originalModelColors[2] = 107;
            class8.modelID = 4162; //Inv & Ground
            class8.modelZoom = 2000; //Zoom
            class8.modelRotation1 = 572;
            class8.modelRotation2 = 0;
            class8.anInt204 = 0;
            class8.modelOffset1 = 0;
            class8.modelOffset2 = 1;
            class8.anInt165 = 4184; //Male
            class8.anInt200 = 7182; //Female
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.anInt199 = -1;//noteable
            class8.name = "Defence Cape(t)";
            class8.description = "Its A Defence Cape(t)".getBytes();
        }
        if (i == 9009) {
            class8.actions = new String[5];
            class8.actions[1] = "Wear";
            class8.modifiedModelColors = new int[3];
            class8.originalModelColors = new int[3];
            class8.modifiedModelColors[0] = 960;
            class8.originalModelColors[0] = 41416;
            class8.modifiedModelColors[1] = 22464;
            class8.originalModelColors[1] = 41416;
            class8.modifiedModelColors[2] = 43968;
            class8.originalModelColors[2] = 41416;
            class8.modelID = 4203; //Model ID
            class8.modelZoom = 720; //zoom increase will make it smaller
            class8.modelRotation1 = 0; //model rotate up+down increase to move doen away from you
            class8.modelRotation2 = 0; //model rotate side ways increase to move right in circle
            class8.modelOffset1 = 0; // model offset increase to move to the right
            class8.modelOffset2 = 0; //model offset increase to move up
            class8.anInt165 = 4202;//male wearing
            class8.anInt200 = 4202;//female wearing
            class8.name = "Defence Hood";
            class8.description = "Its A Defence Hood".getBytes();
        }
        if (i == 9010) {
            class8.actions = new String[5];
            class8.actions[1] = "Wear";
            class8.modifiedModelColors = new int[6];
            class8.originalModelColors = new int[6];
            class8.modifiedModelColors = new int[3];
            class8.originalModelColors = new int[3];
            class8.modifiedModelColors[0] = 57280;
            class8.modifiedModelColors[1] = 54503;
            class8.modifiedModelColors[2] = 54183;
            class8.originalModelColors[0] = 13214;
            class8.originalModelColors[1] = 13214;
            class8.originalModelColors[2] = 13214;
            class8.modelID = 4173; //Inv & Ground
            class8.modelZoom = 2000; //Zoom
            class8.modelRotation1 = 572;
            class8.modelRotation2 = 0;
            class8.anInt204 = 0;
            class8.modelOffset1 = 0;
            class8.modelOffset2 = 1;
            class8.anInt165 = 4195; //Male
            class8.anInt200 = 7193; //Female
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.anInt199 = -1;//noteable
            class8.name = "Ranging Cape";
            class8.description = "Its A Ranging Cape".getBytes();
        }
        if (i == 9011) {
            class8.actions = new String[5];
            class8.actions[1] = "Wear";
            class8.modifiedModelColors = new int[3];
            class8.originalModelColors = new int[3];
            class8.modifiedModelColors[0] = 57280;
            class8.modifiedModelColors[1] = 54503;
            class8.modifiedModelColors[2] = 54183;
            class8.originalModelColors[0] = 8111;
            class8.originalModelColors[1] = 8111;
            class8.originalModelColors[2] = 8111;
            class8.modelID = 4173; //Inv & Ground
            class8.modelZoom = 2000; //Zoom
            class8.modelRotation1 = 572;
            class8.modelRotation2 = 0;
            class8.anInt204 = 0;
            class8.modelOffset1 = 0;
            class8.modelOffset2 = 1;
            class8.anInt165 = 4195; //Male
            class8.anInt200 = 7193; //Female
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.anInt199 = -1;//noteable
            class8.name = "Ranging Cape(t)";
            class8.description = "Its A Ranging Cape(t)".getBytes();
        }
        if (i == 9012) {
            class8.actions = new String[5];
            class8.actions[1] = "Wear";
            class8.modifiedModelColors = new int[3];
            class8.originalModelColors = new int[3];
            class8.modifiedModelColors[0] = 960;
            class8.modifiedModelColors[1] = 22464;
            class8.modifiedModelColors[2] = 43968;
            class8.originalModelColors[0] = 13214;
            class8.originalModelColors[1] = 13214;
            class8.originalModelColors[2] = 13214;
            class8.modelID = 4203; //Model ID
            class8.modelZoom = 720; //zoom increase will make it smaller
            class8.modelRotation1 = 0; //model rotate up+down increase to move doen away from you
            class8.modelRotation2 = 0; //model rotate side ways increase to move right in circle
            class8.modelOffset1 = 0; // model offset increase to move to the right
            class8.modelOffset2 = 0; //model offset increase to move up
            class8.anInt165 = 4202;//male wearing
            class8.anInt200 = 4202;//female wearing
            class8.name = "Range Hood";
            class8.description = "A Range Hood".getBytes();
        }
        if (i == 9013) {
            class8.actions = new String[5];
            class8.actions[1] = "Wear";
            class8.modifiedModelColors = new int[4];
            class8.originalModelColors = new int[4];
            class8.modifiedModelColors[0] = 57280;
            class8.modifiedModelColors[1] = 54503;
            class8.modifiedModelColors[2] = 54183;
            class8.modifiedModelColors[3] = 11200;
            class8.originalModelColors[0] = 107;
            class8.originalModelColors[1] = 107;
            class8.originalModelColors[2] = 107;
            class8.originalModelColors[3] = 107;
            class8.modelID = 4171; //Inv & Ground
            class8.modelZoom = 2000; //Zoom
            class8.modelRotation1 = 572;
            class8.modelRotation2 = 0;
            class8.anInt204 = 0;
            class8.modelOffset1 = 0;
            class8.modelOffset2 = 1;
            class8.anInt165 = 4193; //Male
            class8.anInt200 = 7191; //Female
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.name = "Prayer Cape";
            class8.description = "Its A Prayer Cape".getBytes();
        }
        if (i == 9014) {
            class8.actions = new String[5];
            class8.actions[1] = "Wear";
            class8.modifiedModelColors = new int[4];
            class8.originalModelColors = new int[4];
            class8.modifiedModelColors[0] = 57280;
            class8.modifiedModelColors[1] = 54503;
            class8.modifiedModelColors[2] = 54183;
            class8.modifiedModelColors[3] = 11200;
            class8.originalModelColors[0] = 9152;
            class8.originalModelColors[1] = 7104;
            class8.originalModelColors[2] = 7104;
            class8.originalModelColors[3] = 796;
            class8.modelID = 4171; //Inv & Ground
            class8.modelZoom = 2000; //Zoom
            class8.modelRotation1 = 572;
            class8.modelRotation2 = 0;
            class8.anInt204 = 0;
            class8.modelOffset1 = 0;
            class8.modelOffset2 = 1;
            class8.anInt165 = 4193; //Male
            class8.anInt200 = 7191; //Female
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.anInt199 = -1;//noteable
            class8.name = "Prayer Cape(t)";
            class8.description = "Its A Prayer Cape(t)".getBytes();
        }
        if (i == 9015) {
            class8.actions = new String[5];
            class8.actions[1] = "Wear";
            class8.modifiedModelColors = new int[3];
            class8.originalModelColors = new int[3];
            class8.modifiedModelColors[0] = 960;
            class8.modifiedModelColors[1] = 22464;
            class8.modifiedModelColors[2] = 43968;
            class8.originalModelColors[0] = 107;
            class8.originalModelColors[1] = 107;
            class8.originalModelColors[2] = 107;
            class8.modelID = 4203; //Model ID
            class8.modelZoom = 720; //zoom increase will make it smaller
            class8.modelRotation1 = 0; //model rotate up+down increase to move doen away from you
            class8.modelRotation2 = 0; //model rotate side ways increase to move right in circle
            class8.modelOffset1 = 0; // model offset increase to move to the right
            class8.modelOffset2 = 0; //model offset increase to move up
            class8.anInt165 = 4202;//male wearing
            class8.anInt200 = 4202;//female wearing
            class8.name = "Prayer Hood";
            class8.description = "A Prayer Hood".getBytes();
        }
        if (i == 9016) {
            class8.actions = new String[5];
            class8.actions[1] = "Wear";
            class8.modifiedModelColors = new int[4];
            class8.originalModelColors = new int[4];
            class8.modifiedModelColors[0] = 57280;
            class8.modifiedModelColors[1] = 54503;
            class8.modifiedModelColors[2] = 54183;
            class8.modifiedModelColors[3] = 11200;
            class8.originalModelColors[0] = 6348;
            class8.originalModelColors[1] = 6340;
            class8.originalModelColors[2] = 6331;
            class8.originalModelColors[3] = 6331;
            class8.modelID = 4169; //Inv & Ground
            class8.modelZoom = 2000; //Zoom
            class8.modelRotation1 = 572;
            class8.modelRotation2 = 0;
            class8.anInt204 = 0;
            class8.modelOffset1 = 0;
            class8.modelOffset2 = 1;
            class8.anInt165 = 4191; //Male
            class8.anInt200 = 7189; //Female
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.anInt199 = -1;//noteable
            class8.name = "Magic Cape";
            class8.description = "Its A magic Cape".getBytes();
        }
        if (i == 9017) {
            class8.actions = new String[5];
            class8.actions[1] = "Wear";
            class8.modifiedModelColors = new int[4];
            class8.originalModelColors = new int[4];
            class8.modifiedModelColors[0] = 57280;
            class8.modifiedModelColors[1] = 54503;
            class8.modifiedModelColors[2] = 54183;
            class8.modifiedModelColors[3] = 11200;
            class8.originalModelColors[0] = 43934;
            class8.originalModelColors[1] = 43934;
            class8.originalModelColors[2] = 43934;
            class8.originalModelColors[3] = 43934;
            class8.modelID = 4169; //Inv & Ground
            class8.modelZoom = 2000; //Zoom
            class8.modelRotation1 = 572;
            class8.modelRotation2 = 0;
            class8.anInt204 = 0;
            class8.modelOffset1 = 0;
            class8.modelOffset2 = 1;
            class8.anInt165 = 4191; //Male
            class8.anInt200 = 7189; //Female
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.anInt199 = -1;//noteable
            class8.name = "Magic Cape(t)";
            class8.description = "Its A magic Cape(t)".getBytes();
        }
        if (i == 9018) {
            class8.actions = new String[5];
            class8.actions[1] = "Wear";
            class8.modifiedModelColors = new int[3];
            class8.originalModelColors = new int[3];
            class8.modifiedModelColors[0] = 960;
            class8.modifiedModelColors[1] = 22464;
            class8.modifiedModelColors[2] = 43968;
            class8.originalModelColors[0] = 6348;
            class8.originalModelColors[1] = 6340;
            class8.originalModelColors[2] = 6331;
            class8.modelID = 4203; //Model ID
            class8.modelZoom = 720; //zoom increase will make it smaller
            class8.modelRotation1 = 0; //model rotate up+down increase to move doen away from you
            class8.modelRotation2 = 0; //model rotate side ways increase to move right in circle
            class8.modelOffset1 = 0; // model offset increase to move to the right
            class8.modelOffset2 = 0; //model offset increase to move up
            class8.anInt165 = 4202;//male wearing
            class8.anInt200 = 4202;//female wearing
            class8.name = "Magic Hood";
            class8.description = "A Magic Hood".getBytes();
        }
        if (i == 9019) {
            class8.actions = new String[5];
            class8.actions[1] = "Wear";
            class8.modifiedModelColors = new int[4];
            class8.originalModelColors = new int[4];
            class8.modifiedModelColors[0] = 57280;
            class8.modifiedModelColors[1] = 54503;
            class8.modifiedModelColors[2] = 54183;
            class8.modifiedModelColors[3] = 11200;
            class8.originalModelColors[0] = 6073;
            class8.originalModelColors[1] = 6073;
            class8.originalModelColors[2] = 6073;
            class8.originalModelColors[3] = 6073;
            class8.modelID = 4159; //Inv & Ground
            class8.modelZoom = 2000; //Zoom
            class8.modelRotation1 = 572;
            class8.modelRotation2 = 0;
            class8.anInt204 = 0;
            class8.modelOffset1 = 0;
            class8.modelOffset2 = 1;
            class8.anInt165 = 4186; //Male
            class8.anInt200 = 7184; //Female
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.anInt199 = -1;//noteable
            class8.name = "Firemaking Cape";
            class8.description = "Its A firemaking Cape(t)".getBytes();
        }
        if (i == 9020) {
            class8.actions = new String[5];
            class8.actions[1] = "Wear";
            class8.modifiedModelColors = new int[4];
            class8.originalModelColors = new int[4];
            class8.modifiedModelColors[0] = 57280;
            class8.modifiedModelColors[1] = 54503;
            class8.modifiedModelColors[2] = 54183;
            class8.modifiedModelColors[3] = 11200;
            class8.originalModelColors[0] = 9152;
            class8.originalModelColors[1] = 7104;
            class8.originalModelColors[2] = 7104;
            class8.originalModelColors[3] = 796;
            class8.modelID = 4159; //Inv & Ground
            class8.modelZoom = 2000; //Zoom
            class8.modelRotation1 = 572;
            class8.modelRotation2 = 0;
            class8.anInt204 = 0;
            class8.modelOffset1 = 0;
            class8.modelOffset2 = 1;
            class8.anInt165 = 4186; //Male
            class8.anInt200 = 7184; //Female
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.anInt199 = -1;//noteable
            class8.name = "Firemaking Cape(t)";
            class8.description = "Its A firemaking Cape(t)".getBytes();
        }
        if (i == 9021) {
            class8.actions = new String[5];
            class8.actions[1] = "Wear";
            class8.modifiedModelColors = new int[3];
            class8.originalModelColors = new int[3];
            class8.modifiedModelColors[0] = 960;
            class8.modifiedModelColors[1] = 22464;
            class8.modifiedModelColors[2] = 43968;
            class8.originalModelColors[0] = 6073;
            class8.originalModelColors[1] = 6073;
            class8.originalModelColors[2] = 6073;
            class8.modelID = 4203; //Model ID
            class8.modelZoom = 720; //zoom increase will make it smaller
            class8.modelRotation1 = 0; //model rotate up+down increase to move doen away from you
            class8.modelRotation2 = 0; //model rotate side ways increase to move right in circle
            class8.modelOffset1 = 0; // model offset increase to move to the right
            class8.modelOffset2 = 0; //model offset increase to move up
            class8.anInt165 = 4202;//male wearing
            class8.anInt200 = 4202;//female wearing
            class8.name = "FireMaking Hood";
            class8.description = "Its A FireMaking Hood".getBytes();
        }
        if (i == 9022) {
            class8.actions = new String[5];
            class8.actions[1] = "Wear";
            class8.modifiedModelColors = new int[4];
            class8.originalModelColors = new int[4];
            class8.modifiedModelColors[0] = 57280;
            class8.modifiedModelColors[1] = 54503;
            class8.modifiedModelColors[2] = 54183;
            class8.modifiedModelColors[3] = 11200;
            class8.originalModelColors[0] = 99;
            class8.originalModelColors[1] = 99;
            class8.originalModelColors[2] = 99;
            class8.originalModelColors[3] = 99;
            class8.modelID = 4164; //Inv & Ground
            class8.modelZoom = 2000; //Zoom
            class8.modelRotation1 = 572;
            class8.modelRotation2 = 0;
            class8.anInt204 = 0;
            class8.modelOffset1 = 0;
            class8.modelOffset2 = 1;
            class8.anInt165 = 4196; //Male
            class8.anInt200 = 7194; //Female
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.anInt199 = -1;//noteable
            class8.name = "RuneCrafting Cape";
            class8.description = "Its A firemaking Cape(t)".getBytes();
        }
        if (i == 9023) {
            class8.actions = new String[5];
            class8.actions[1] = "Wear";
            class8.modifiedModelColors = new int[4];
            class8.originalModelColors = new int[4];
            class8.modifiedModelColors[0] = 57280;
            class8.modifiedModelColors[1] = 54503;
            class8.modifiedModelColors[2] = 54183;
            class8.modifiedModelColors[3] = 11200;
            class8.originalModelColors[0] = 9152;
            class8.originalModelColors[1] = 7104;
            class8.originalModelColors[2] = 7104;
            class8.originalModelColors[3] = 796;
            class8.modelID = 4164; //Inv & Ground
            class8.modelZoom = 2000; //Zoom
            class8.modelRotation1 = 572;
            class8.modelRotation2 = 0;
            class8.anInt204 = 0;
            class8.modelOffset1 = 0;
            class8.modelOffset2 = 1;
            class8.anInt165 = 4196; //Male
            class8.anInt200 = 7194; //Female
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.anInt199 = -1;//noteable
            class8.name = "RuneCrafting Cape(t)";
            class8.description = "Its A firemaking Cape(t)".getBytes();
        }
        if (i == 9024) {
            class8.actions = new String[5];
            class8.actions[1] = "Wear";
            class8.modifiedModelColors = new int[3];
            class8.originalModelColors = new int[3];
            class8.modifiedModelColors[0] = 960;
            class8.modifiedModelColors[1] = 22464;
            class8.modifiedModelColors[2] = 43968;
            class8.originalModelColors[0] = 99;
            class8.originalModelColors[1] = 99;
            class8.originalModelColors[2] = 99;
            class8.modelID = 4203; //Model ID
            class8.modelZoom = 720; //zoom increase will make it smaller
            class8.modelRotation1 = 0; //model rotate up+down increase to move doen away from you
            class8.modelRotation2 = 0; //model rotate side ways increase to move right in circle
            class8.modelOffset1 = 0; // model offset increase to move to the right
            class8.modelOffset2 = 0; //model offset increase to move up
            class8.anInt165 = 4202;//male wearing
            class8.anInt200 = 4202;//female wearing
            class8.name = "RuneCrafting Hood";
            class8.description = "Its A RuneCrafting Hood".getBytes();
        }
        if (i == 9025) {
            class8.actions = new String[5];
            class8.actions[1] = "Wear";
            class8.modifiedModelColors = new int[4];
            class8.originalModelColors = new int[4];
            class8.modifiedModelColors[0] = 57280;
            class8.modifiedModelColors[1] = 54503;
            class8.modifiedModelColors[2] = 54183;
            class8.modifiedModelColors[3] = 11200;
            class8.originalModelColors[0] = 99;
            class8.originalModelColors[1] = 99;
            class8.originalModelColors[2] = 99;
            class8.originalModelColors[3] = 99;
            class8.modelID = 4168; //Inv & Ground
            class8.modelZoom = 2000; //Zoom
            class8.modelRotation1 = 572;
            class8.modelRotation2 = 0;
            class8.anInt204 = 0;
            class8.modelOffset1 = 0;
            class8.modelOffset2 = 1;
            class8.anInt165 = 4190; //Male
            class8.anInt200 = 7187; //Female
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.anInt199 = -1;//noteable
            class8.name = "Hitpoint Cape";
            class8.description = "Its A Hitpoint Cape".getBytes();
        }
        if (i == 9026) {
            class8.actions = new String[5];
            class8.actions[1] = "Wear";
            class8.modifiedModelColors = new int[4];
            class8.originalModelColors = new int[4];
            class8.modifiedModelColors[0] = 57280;
            class8.modifiedModelColors[1] = 54503;
            class8.modifiedModelColors[2] = 54183;
            class8.modifiedModelColors[3] = 11200;
            class8.originalModelColors[0] = 926;
            class8.originalModelColors[1] = 926;
            class8.originalModelColors[2] = 926;
            class8.originalModelColors[3] = 926;
            class8.modelID = 4168; //Inv & Ground
            class8.modelZoom = 2000; //Zoom
            class8.modelRotation1 = 572;
            class8.modelRotation2 = 0;
            class8.anInt204 = 0;
            class8.modelOffset1 = 0;
            class8.modelOffset2 = 1;
            class8.anInt165 = 4190; //Male
            class8.anInt200 = 7187; //Female
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.anInt199 = -1;//noteable
            class8.name = "Hitpoint Cape(t)";
            class8.description = "Its A Hitpoint Cape(t)".getBytes();
        }
        if (i == 9027) {
            class8.actions = new String[5];
            class8.actions[1] = "Wear";
            class8.modifiedModelColors = new int[3];
            class8.originalModelColors = new int[3];
            class8.modifiedModelColors[0] = 960;
            class8.modifiedModelColors[1] = 22464;
            class8.modifiedModelColors[2] = 43968;
            class8.originalModelColors[0] = 99;
            class8.originalModelColors[1] = 99;
            class8.originalModelColors[2] = 99;
            class8.modelID = 4203; //Model ID
            class8.modelZoom = 720; //zoom increase will make it smaller
            class8.modelRotation1 = 0; //model rotate up+down increase to move doen away from you
            class8.modelRotation2 = 0; //model rotate side ways increase to move right in circle
            class8.modelOffset1 = 0; // model offset increase to move to the right
            class8.modelOffset2 = 0; //model offset increase to move up
            class8.anInt165 = 4202;//male wearing
            class8.anInt200 = 4202;//female wearing
            class8.name = "Hitpoint Hood";
            class8.description = "Its A Hitpoint Hood".getBytes();
        }
        if (i == 9028) {
            class8.actions = new String[5];
            class8.actions[1] = "Wear";
            class8.modifiedModelColors = new int[4];
            class8.originalModelColors = new int[4];
            class8.modifiedModelColors[0] = 57280;
            class8.modifiedModelColors[1] = 54503;
            class8.modifiedModelColors[2] = 54183;
            class8.modifiedModelColors[3] = 11200;
            class8.originalModelColors[0] = 43932;
            class8.originalModelColors[1] = 43932;
            class8.originalModelColors[2] = 43932;
            class8.originalModelColors[3] = 43932;
            class8.modelID = 4157; //Inv & Ground
            class8.modelZoom = 2000; //Zoom
            class8.modelRotation1 = 572;
            class8.modelRotation2 = 0;
            class8.anInt204 = 0;
            class8.modelOffset1 = 0;
            class8.modelOffset2 = 1;
            class8.anInt165 = 4180; //Male
            class8.anInt200 = 7177; //Female
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.anInt199 = -1;//noteable
            class8.name = "Agility Cape";
            class8.description = "Its A Agility Cape".getBytes();
        }
        if (i == 9029) {
            class8.actions = new String[5];
            class8.actions[1] = "Wear";
            class8.modifiedModelColors = new int[4];
            class8.originalModelColors = new int[4];
            class8.modifiedModelColors[0] = 57280;
            class8.modifiedModelColors[1] = 54503;
            class8.modifiedModelColors[2] = 54183;
            class8.modifiedModelColors[3] = 11200;
            class8.originalModelColors[0] = 924;
            class8.originalModelColors[1] = 924;
            class8.originalModelColors[2] = 924;
            class8.originalModelColors[3] = 924;
            class8.modelID = 4157; //Inv & Ground
            class8.modelZoom = 2000; //Zoom
            class8.modelRotation1 = 572;
            class8.modelRotation2 = 0;
            class8.anInt204 = 0;
            class8.modelOffset1 = 0;
            class8.modelOffset2 = 1;
            class8.anInt165 = 4180; //Male
            class8.anInt200 = 7177; //Female
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.anInt199 = -1;//noteable
            class8.name = "Agility Cape(t)";
            class8.description = "Its A Agility Cape(t)".getBytes();
        }
        if (i == 9030) {
            class8.actions = new String[5];
            class8.actions[1] = "Wear";
            class8.modifiedModelColors = new int[3];
            class8.originalModelColors = new int[3];
            class8.modifiedModelColors[0] = 960;
            class8.modifiedModelColors[1] = 22464;
            class8.modifiedModelColors[2] = 43968;
            class8.originalModelColors[0] = 43934;
            class8.originalModelColors[1] = 43934;
            class8.originalModelColors[2] = 43934;
            class8.modelID = 4203; //Model ID
            class8.modelZoom = 720; //zoom increase will make it smaller
            class8.modelRotation1 = 0; //model rotate up+down increase to move doen away from you
            class8.modelRotation2 = 0; //model rotate side ways increase to move right in circle
            class8.modelOffset1 = 0; // model offset increase to move to the right
            class8.modelOffset2 = 0; //model offset increase to move up
            class8.anInt165 = 4202;//male wearing
            class8.anInt200 = 4202;//female wearing
            class8.name = "Agility Hood";
            class8.description = "Its A Agility Hood".getBytes();
        }
        if (i == 9031) {
            class8.actions = new String[5];
            class8.actions[1] = "Wear";
            class8.modifiedModelColors = new int[4];
            class8.originalModelColors = new int[4];
            class8.modifiedModelColors[0] = 57280;
            class8.modifiedModelColors[1] = 54503;
            class8.modifiedModelColors[2] = 54183;
            class8.modifiedModelColors[3] = 11200;
            class8.originalModelColors[0] = 22416;
            class8.originalModelColors[1] = 22416;
            class8.originalModelColors[2] = 22416;
            class8.originalModelColors[3] = 22416;
            class8.modelID = 4167; //Inv & Ground
            class8.modelZoom = 2000; //Zoom
            class8.modelRotation1 = 572;
            class8.modelRotation2 = 0;
            class8.anInt204 = 0;
            class8.modelOffset1 = 0;
            class8.modelOffset2 = 1;
            class8.anInt165 = 4189; //Male
            class8.anInt200 = 7187; //Female
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.anInt199 = -1;//noteable
            class8.name = "Herblore Cape";
            class8.description = "Its A Herblore Cape".getBytes();
        }
        if (i == 9032) {
            class8.actions = new String[5];
            class8.actions[1] = "Wear";
            class8.modifiedModelColors = new int[4];
            class8.originalModelColors = new int[4];
            class8.modifiedModelColors[0] = 57280;
            class8.modifiedModelColors[1] = 54503;
            class8.modifiedModelColors[2] = 54183;
            class8.modifiedModelColors[3] = 11200;
            class8.originalModelColors[0] = 9152;
            class8.originalModelColors[1] = 7104;
            class8.originalModelColors[2] = 7104;
            class8.originalModelColors[3] = 796;
            class8.modelID = 4167; //Inv & Ground
            class8.modelZoom = 2000; //Zoom
            class8.modelRotation1 = 572;
            class8.modelRotation2 = 0;
            class8.anInt204 = 0;
            class8.modelOffset1 = 0;
            class8.modelOffset2 = 1;
            class8.anInt165 = 4189; //Male
            class8.anInt200 = 7187; //Female
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.anInt199 = -1;//noteable
            class8.name = "Herblore Cape(t)";
            class8.description = "Its A Herblore Cape(t)".getBytes();
        }
        if (i == 9033) {
            class8.actions = new String[5];
            class8.actions[1] = "Wear";
            class8.modifiedModelColors = new int[3];
            class8.originalModelColors = new int[3];
            class8.modifiedModelColors[0] = 960;
            class8.modifiedModelColors[1] = 22464;
            class8.modifiedModelColors[2] = 43968;
            class8.originalModelColors[0] = 22416;
            class8.originalModelColors[1] = 22416;
            class8.originalModelColors[2] = 22416;
            class8.modelID = 4203; //Model ID
            class8.modelZoom = 720; //zoom increase will make it smaller
            class8.modelRotation1 = 0; //model rotate up+down increase to move doen away from you
            class8.modelRotation2 = 0; //model rotate side ways increase to move right in circle
            class8.modelOffset1 = 0; // model offset increase to move to the right
            class8.modelOffset2 = 0; //model offset increase to move up
            class8.anInt165 = 4202;//male wearing
            class8.anInt200 = 4202;//female wearing
            class8.name = "Herblore Hood";
            class8.description = "Its A Herblore Hood".getBytes();
        }
        if (i == 9034) {
            class8.actions = new String[5];
            class8.actions[1] = "Wear";
            class8.modifiedModelColors = new int[4];
            class8.originalModelColors = new int[4];
            class8.modifiedModelColors[0] = 57280;
            class8.modifiedModelColors[1] = 54503;
            class8.modifiedModelColors[2] = 54183;
            class8.modifiedModelColors[3] = 11200;
            class8.originalModelColors[0] = 51120;
            class8.originalModelColors[1] = 51120;
            class8.originalModelColors[2] = 51120;
            class8.originalModelColors[3] = 51120;
            class8.modelID = 4178; //Inv & Ground
            class8.modelZoom = 2000; //Zoom
            class8.modelRotation1 = 572;
            class8.modelRotation2 = 0;
            class8.anInt204 = 0;
            class8.modelOffset1 = 0;
            class8.modelOffset2 = 1;
            class8.anInt165 = 4201; //Male
            class8.anInt200 = 7198; //Female
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.anInt199 = -1;//noteable
            class8.name = "Thieving Cape";
            class8.description = "Its A thieving Cape".getBytes();
        }
        if (i == 9035) {
            class8.actions = new String[5];
            class8.actions[1] = "Wear";
            class8.modifiedModelColors = new int[4];
            class8.originalModelColors = new int[4];
            class8.modifiedModelColors[0] = 57280;
            class8.modifiedModelColors[1] = 54503;
            class8.modifiedModelColors[2] = 54183;
            class8.modifiedModelColors[3] = 11200;
            class8.originalModelColors[0] = 16000;
            class8.originalModelColors[1] = 16000;
            class8.originalModelColors[2] = 16000;
            class8.originalModelColors[3] = 16000;
            class8.modelID = 4178; //Inv & Ground
            class8.modelZoom = 2000; //Zoom
            class8.modelRotation1 = 572;
            class8.modelRotation2 = 0;
            class8.anInt204 = 0;
            class8.modelOffset1 = 0;
            class8.modelOffset2 = 1;
            class8.anInt165 = 4201; //Male
            class8.anInt200 = 7198; //Female
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.anInt199 = -1;//noteable
            class8.name = "Thieving Cape(t)";
            class8.description = "Its A Thieving Cape(t)".getBytes();
        }
        if (i == 9036) {
            class8.actions = new String[5];
            class8.actions[1] = "Wear";
            class8.modifiedModelColors = new int[3];
            class8.originalModelColors = new int[3];
            class8.modifiedModelColors[0] = 960;
            class8.modifiedModelColors[1] = 22464;
            class8.modifiedModelColors[2] = 43968;
            class8.originalModelColors[0] = 51120;
            class8.originalModelColors[1] = 51120;
            class8.originalModelColors[2] = 51120;
            class8.modelID = 4203; //Model ID
            class8.modelZoom = 720; //zoom increase will make it smaller
            class8.modelRotation1 = 0; //model rotate up+down increase to move doen away from you
            class8.modelRotation2 = 0; //model rotate side ways increase to move right in circle
            class8.modelOffset1 = 0; // model offset increase to move to the right
            class8.modelOffset2 = 0; //model offset increase to move up
            class8.anInt165 = 4202;//male wearing
            class8.anInt200 = 4202;//female wearing
            class8.name = "Thieving Hood";
            class8.description = "Its A Thieving Hood".getBytes();
        }
        if (i == 9037) {
            class8.actions = new String[5];
            class8.actions[1] = "Wear";
            class8.modifiedModelColors = new int[4];
            class8.originalModelColors = new int[4];
            class8.modifiedModelColors[0] = 57280;
            class8.modifiedModelColors[1] = 54503;
            class8.modifiedModelColors[2] = 54183;
            class8.modifiedModelColors[3] = 11200;
            class8.originalModelColors[0] = 4769;
            class8.originalModelColors[1] = 4769;
            class8.originalModelColors[2] = 4769;
            class8.originalModelColors[3] = 4769;
            class8.modelID = 4161; //Inv & Ground
            class8.modelZoom = 2000; //Zoom
            class8.modelRotation1 = 572;
            class8.modelRotation2 = 0;
            class8.anInt204 = 0;
            class8.modelOffset1 = 0;
            class8.modelOffset2 = 1;
            class8.anInt165 = 4183; //Male
            class8.anInt200 = 7181; //Female
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.anInt199 = -1;//noteable
            class8.name = "Crafting Cape";
            class8.description = "Its A Crafting Cape".getBytes();
        }
        if (i == 9038) {
            class8.actions = new String[5];
            class8.actions[1] = "Wear";
            class8.modifiedModelColors = new int[4];
            class8.originalModelColors = new int[4];
            class8.modifiedModelColors[0] = 57280;
            class8.modifiedModelColors[1] = 54503;
            class8.modifiedModelColors[2] = 54183;
            class8.modifiedModelColors[3] = 11200;
            class8.originalModelColors[0] = 9152;
            class8.originalModelColors[1] = 7104;
            class8.originalModelColors[2] = 7104;
            class8.originalModelColors[3] = 796;
            class8.modelID = 4161; //Inv & Ground
            class8.modelZoom = 2000; //Zoom
            class8.modelRotation1 = 572;
            class8.modelRotation2 = 0;
            class8.anInt204 = 0;
            class8.modelOffset1 = 0;
            class8.modelOffset2 = 1;
            class8.anInt165 = 4183; //Male
            class8.anInt200 = 7181; //Female
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.anInt199 = -1;//noteable
            class8.name = "crafting Cape(t)";
            class8.description = "Its A Crafting Cape(t)".getBytes();
        }
        if (i == 9039) {
            class8.actions = new String[5];
            class8.actions[1] = "Wear";
            class8.modifiedModelColors = new int[3];
            class8.originalModelColors = new int[3];
            class8.modifiedModelColors[0] = 960;
            class8.modifiedModelColors[1] = 22464;
            class8.modifiedModelColors[2] = 43968;
            class8.originalModelColors[0] = 4769;
            class8.originalModelColors[1] = 4769;
            class8.originalModelColors[2] = 4769;
            class8.modelID = 4203; //Model ID
            class8.stackable = false;//Stackable
            class8.modelZoom = 720; //zoom increase will make it smaller
            class8.modelRotation1 = 0; //model rotate up+down increase to move doen away from you
            class8.modelRotation2 = 0; //model rotate side ways increase to move right in circle
            class8.modelOffset1 = 0; // model offset increase to move to the right
            class8.modelOffset2 = 0; //model offset increase to move up
            class8.anInt165 = 4202;//male wearing
            class8.anInt200 = 4202;//female wearing
            class8.name = "Crafting Hood";
            class8.description = "Its A Crafting Hood".getBytes();
        }
        if (i == 9040) {
            class8.actions = new String[5];
            class8.actions[1] = "Wear";
            class8.modifiedModelColors = new int[4];
            class8.originalModelColors = new int[4];
            class8.modifiedModelColors[0] = 57280;
            class8.modifiedModelColors[1] = 54503;
            class8.modifiedModelColors[2] = 54183;
            class8.modifiedModelColors[3] = 11200;
            class8.originalModelColors[0] = 9152;
            class8.originalModelColors[1] = 7104;
            class8.originalModelColors[2] = 7104;
            class8.originalModelColors[3] = 796;
            class8.modelID = 4166; //Inv & Ground
            class8.modelZoom = 2000; //Zoom
            class8.modelRotation1 = 572;
            class8.modelRotation2 = 0;
            class8.anInt204 = 0;
            class8.modelOffset1 = 0;
            class8.modelOffset2 = 1;
            class8.anInt165 = 4188; //Male
            class8.anInt200 = 7186; //Female
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.anInt199 = -1;//noteable
            class8.name = "Fletching Cape(t)";
            class8.description = "Its A Fletching Cape(t)".getBytes();
        }
        if (i == 9041) {
            class8.actions = new String[5];
            class8.actions[1] = "Wear";
            class8.modifiedModelColors = new int[4];
            class8.originalModelColors = new int[4];
            class8.modifiedModelColors[0] = 57280;
            class8.modifiedModelColors[1] = 54503;
            class8.modifiedModelColors[2] = 54183;
            class8.modifiedModelColors[3] = 11200;
            class8.originalModelColors[0] = 22430;
            class8.originalModelColors[1] = 22430;
            class8.originalModelColors[2] = 22430;
            class8.originalModelColors[3] = 22430;
            class8.modelID = 4166; //Inv & Ground
            class8.modelZoom = 2000; //Zoom
            class8.modelRotation1 = 572;
            class8.modelRotation2 = 0;
            class8.anInt204 = 0;
            class8.modelOffset1 = 0;
            class8.modelOffset2 = 1;
            class8.anInt165 = 4188; //Male
            class8.anInt200 = 7186; //Female
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.anInt199 = -1;//noteable
            class8.name = "Fletching Cape";
            class8.description = "Its A Fletching Cape".getBytes();
        }
        if (i == 9042) {
            class8.actions = new String[5];
            class8.actions[1] = "Wear";
            class8.modifiedModelColors = new int[3];
            class8.originalModelColors = new int[3];
            class8.modifiedModelColors[0] = 960;
            class8.modifiedModelColors[1] = 22464;
            class8.modifiedModelColors[2] = 43968;
            class8.originalModelColors[0] = 22430;
            class8.originalModelColors[1] = 22430;
            class8.originalModelColors[2] = 22430;
            class8.modelID = 4203; //Model ID
            class8.stackable = false;//Stackable
            class8.modelZoom = 720; //zoom increase will make it smaller
            class8.modelRotation1 = 0; //model rotate up+down increase to move doen away from you
            class8.modelRotation2 = 0; //model rotate side ways increase to move right in circle
            class8.modelOffset1 = 0; // model offset increase to move to the right
            class8.modelOffset2 = 0; //model offset increase to move up
            class8.anInt165 = 4202;//male wearing
            class8.anInt200 = 4202;//female wearing
            class8.name = "Fletching Hood";
            class8.description = "Its A Fletching Hood".getBytes();
        }
        if (i == 9043) {
            class8.actions = new String[5];
            class8.actions[1] = "Wear";
            class8.modifiedModelColors = new int[4];
            class8.originalModelColors = new int[4];
            class8.modifiedModelColors[0] = 57280;
            class8.modifiedModelColors[1] = 54503;
            class8.modifiedModelColors[2] = 54183;
            class8.modifiedModelColors[3] = 11200;
            class8.originalModelColors[0] = 6;
            class8.originalModelColors[1] = 6;
            class8.originalModelColors[2] = 6;
            class8.originalModelColors[3] = 6;
            class8.modelID = 4175; //Inv & Ground
            class8.modelZoom = 2000; //Zoom
            class8.modelRotation1 = 572;
            class8.modelRotation2 = 0;
            class8.anInt204 = 0;
            class8.modelOffset1 = 0;
            class8.modelOffset2 = 1;
            class8.anInt165 = 4197; //Male
            class8.anInt200 = 7195; //Female
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.anInt199 = -1;//noteable
            class8.name = "Slayer Cape";
            class8.description = "Its A slayer Cape".getBytes();
        }
        if (i == 9044) {
            class8.actions = new String[5];
            class8.actions[1] = "Wear";
            class8.modifiedModelColors = new int[4];
            class8.originalModelColors = new int[4];
            class8.modifiedModelColors[0] = 57280;
            class8.modifiedModelColors[1] = 54503;
            class8.modifiedModelColors[2] = 54183;
            class8.modifiedModelColors[3] = 11200;
            class8.originalModelColors[0] = 912;
            class8.originalModelColors[1] = 912;
            class8.originalModelColors[2] = 912;
            class8.originalModelColors[3] = 912;
            class8.modelID = 4175; //Inv & Ground
            class8.modelZoom = 2000; //Zoom
            class8.modelRotation1 = 572;
            class8.modelRotation2 = 0;
            class8.anInt204 = 0;
            class8.modelOffset1 = 0;
            class8.modelOffset2 = 1;
            class8.anInt165 = 4197; //Male
            class8.anInt200 = 7195; //Female
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.anInt199 = -1;//noteable
            class8.name = "Slayer Cape";
            class8.description = "Its A slayer Cape".getBytes();
        }
        if (i == 9045) {
            class8.actions = new String[5];
            class8.actions[1] = "Wear";
            class8.modifiedModelColors = new int[3];
            class8.originalModelColors = new int[3];
            class8.modifiedModelColors[0] = 960;
            class8.modifiedModelColors[1] = 22464;
            class8.modifiedModelColors[2] = 43968;
            class8.originalModelColors[0] = 0;//Back
            class8.originalModelColors[1] = 0;//front Line
            class8.originalModelColors[2] = 6;//Inside
            class8.modelID = 4203; //Model ID
            class8.stackable = false;//Stackable
            class8.modelZoom = 720; //zoom increase will make it smaller
            class8.modelRotation1 = 0; //model rotate up+down increase to move doen away from you
            class8.modelRotation2 = 0; //model rotate side ways increase to move right in circle
            class8.modelOffset1 = 0; // model offset increase to move to the right
            class8.modelOffset2 = 0; //model offset increase to move up
            class8.anInt165 = 4202;//male wearing
            class8.anInt200 = 4202;//female wearing
            class8.name = "Slayer Hood";
            class8.description = "Its A Slayer Hood".getBytes();
        }
        if (i == 9046) {
            class8.actions = new String[5];
            class8.actions[1] = "Wear";
            class8.modifiedModelColors = new int[4];
            class8.originalModelColors = new int[4];
            class8.modifiedModelColors[0] = 57280;
            class8.modifiedModelColors[1] = 54503;
            class8.modifiedModelColors[2] = 54183;
            class8.modifiedModelColors[3] = 11200;
            class8.originalModelColors[0] = 10512;
            class8.originalModelColors[1] = 10512;
            class8.originalModelColors[2] = 10512;
            class8.originalModelColors[3] = 10512;
            class8.modelID = 4170; //Inv & Ground
            class8.modelZoom = 2000; //Zoom
            class8.modelRotation1 = 572;
            class8.modelRotation2 = 0;
            class8.anInt204 = 0;
            class8.modelOffset1 = 0;
            class8.modelOffset2 = 1;
            class8.anInt165 = 4192; //Male
            class8.anInt200 = 7190; //Female
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.anInt199 = -1;//noteable
            class8.name = "Mining Cape";
            class8.description = "Its A mining Cape".getBytes();
        }
        if (i == 9047) {
            class8.actions = new String[5];
            class8.actions[1] = "Wear";
            class8.modifiedModelColors = new int[4];
            class8.originalModelColors = new int[4];
            class8.modifiedModelColors[0] = 57280;
            class8.modifiedModelColors[1] = 54503;
            class8.modifiedModelColors[2] = 54183;
            class8.modifiedModelColors[3] = 11200;
            class8.originalModelColors[0] = 38226;
            class8.originalModelColors[1] = 38226;
            class8.originalModelColors[2] = 38226;
            class8.originalModelColors[3] = 38226;
            class8.modelID = 4170; //Inv & Ground
            class8.modelZoom = 2000; //Zoom
            class8.modelRotation1 = 572;
            class8.modelRotation2 = 0;
            class8.anInt204 = 0;
            class8.modelOffset1 = 0;
            class8.modelOffset2 = 1;
            class8.anInt165 = 4192; //Male
            class8.anInt200 = 7190; //Female
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.anInt199 = -1;//noteable
            class8.name = "Mining Cape(t)";
            class8.description = "Its A mining Cape(t)".getBytes();
        }

        if (i == 9048) {
            class8.actions = new String[5];
            class8.actions[1] = "Wear";
            class8.modifiedModelColors = new int[3];
            class8.originalModelColors = new int[3];
            class8.modifiedModelColors[0] = 960;
            class8.modifiedModelColors[1] = 22464;
            class8.modifiedModelColors[2] = 43968;
            class8.originalModelColors[0] = 10512;//Back
            class8.originalModelColors[1] = 10512;//front Line
            class8.originalModelColors[2] = 10512;//Inside
            class8.modelID = 4203; //Model ID
            class8.stackable = false;//Stackable
            class8.modelZoom = 720; //zoom increase will make it smaller
            class8.modelRotation1 = 0; //model rotate up+down increase to move doen away from you
            class8.modelRotation2 = 0; //model rotate side ways increase to move right in circle
            class8.modelOffset1 = 0; // model offset increase to move to the right
            class8.modelOffset2 = 0; //model offset increase to move up
            class8.anInt165 = 4202;//male wearing
            class8.anInt200 = 4202;//female wearing
            class8.name = "Mining Hood";
            class8.description = "Its A Mining Hood".getBytes();
        }


        if (i == 9049) {
            class8.actions = new String[5];
            class8.actions[1] = "Wear";
            class8.modifiedModelColors = new int[4];
            class8.originalModelColors = new int[4];
            class8.modifiedModelColors[0] = 57280;
            class8.modifiedModelColors[1] = 54503;
            class8.modifiedModelColors[2] = 54183;
            class8.modifiedModelColors[3] = 11200;
            class8.originalModelColors[0] = 10512;
            class8.originalModelColors[1] = 10512;
            class8.originalModelColors[2] = 10512;
            class8.originalModelColors[3] = 10512;
            class8.modelID = 4176; //Inv & Ground
            class8.modelZoom = 2000; //Zoom
            class8.modelRotation1 = 572;
            class8.modelRotation2 = 0;
            class8.anInt204 = 0;
            class8.modelOffset1 = 0;
            class8.modelOffset2 = 1;
            class8.anInt165 = 4198; //Male
            class8.anInt200 = 7196; //Female
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.anInt199 = -1;//noteable
            class8.name = "Smithing Cape";
            class8.description = "Its A smithing Cape".getBytes();
        }
        if (i == 9050) {
            class8.actions = new String[5];
            class8.actions[1] = "Wear";
            class8.modifiedModelColors = new int[4];
            class8.originalModelColors = new int[4];
            class8.modifiedModelColors[0] = 57280;
            class8.modifiedModelColors[1] = 54503;
            class8.modifiedModelColors[2] = 54183;
            class8.modifiedModelColors[3] = 11200;
            class8.originalModelColors[0] = 9152;
            class8.originalModelColors[1] = 7104;
            class8.originalModelColors[2] = 7104;
            class8.originalModelColors[3] = 796;
            class8.modelID = 4176; //Inv & Ground
            class8.modelZoom = 2000; //Zoom
            class8.modelRotation1 = 572;
            class8.modelRotation2 = 0;
            class8.anInt204 = 0;
            class8.modelOffset1 = 0;
            class8.modelOffset2 = 1;
            class8.anInt165 = 4198; //Male
            class8.anInt200 = 7196; //Female
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.anInt199 = -1;//noteable
            class8.name = "Smithing Cape(t)";
            class8.description = "Its A smithing Cape(t)".getBytes();
        }
        if (i == 9051) {
            class8.actions = new String[5];
            class8.actions[1] = "Wear";
            class8.modifiedModelColors = new int[3];
            class8.originalModelColors = new int[3];
            class8.modifiedModelColors[0] = 960;
            class8.modifiedModelColors[1] = 22464;
            class8.modifiedModelColors[2] = 43968;
            class8.originalModelColors[0] = 10512;//Back
            class8.originalModelColors[1] = 10512;//front Line
            class8.originalModelColors[2] = 10512;//Inside
            class8.modelID = 4203; //Model ID
            class8.stackable = false;//Stackable
            class8.modelZoom = 720; //zoom increase will make it smaller
            class8.modelRotation1 = 0; //model rotate up+down increase to move doen away from you
            class8.modelRotation2 = 0; //model rotate side ways increase to move right in circle
            class8.modelOffset1 = 0; // model offset increase to move to the right
            class8.modelOffset2 = 0; //model offset increase to move up
            class8.anInt165 = 4202;//male wearing
            class8.anInt200 = 4202;//female wearing
            class8.name = "Smithing Hood";
            class8.description = "Its A Smithing Hood".getBytes();
        }
        if (i == 9052) {
            class8.actions = new String[5];
            class8.actions[1] = "Wear";
            class8.modifiedModelColors = new int[4];
            class8.originalModelColors = new int[4];
            class8.modifiedModelColors[0] = 57280;
            class8.modifiedModelColors[1] = 54503;
            class8.modifiedModelColors[2] = 54183;
            class8.modifiedModelColors[3] = 11200;
            class8.originalModelColors[0] = 38226;
            class8.originalModelColors[1] = 38226;
            class8.originalModelColors[2] = 38226;
            class8.originalModelColors[3] = 38226;
            class8.modelID = 4165; //Inv & Ground
            class8.modelZoom = 2000; //Zoom
            class8.modelRotation1 = 572;
            class8.modelRotation2 = 0;
            class8.anInt204 = 0;
            class8.modelOffset1 = 0;
            class8.modelOffset2 = 1;
            class8.anInt165 = 4187; //Male
            class8.anInt200 = 7185; //Female
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.anInt199 = -1;//noteable
            class8.name = "Fishing Cape";
            class8.description = "Its A fishing Cape".getBytes();
        }
        if (i == 9053) {
            class8.actions = new String[5];
            class8.actions[1] = "Wear";
            class8.modifiedModelColors = new int[4];
            class8.originalModelColors = new int[4];
            class8.modifiedModelColors[0] = 57280;
            class8.modifiedModelColors[1] = 54503;
            class8.modifiedModelColors[2] = 54183;
            class8.modifiedModelColors[3] = 11200;
            class8.originalModelColors[0] = 9152;
            class8.originalModelColors[1] = 7104;
            class8.originalModelColors[2] = 7104;
            class8.originalModelColors[3] = 796;
            class8.modelID = 4165; //Inv & Ground
            class8.modelZoom = 2000; //Zoom
            class8.modelRotation1 = 572;
            class8.modelRotation2 = 0;
            class8.anInt204 = 0;
            class8.modelOffset1 = 0;
            class8.modelOffset2 = 1;
            class8.anInt165 = 4187; //Male
            class8.anInt200 = 7185; //Female
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.anInt199 = -1;//noteable
            class8.name = "Fishing Cape(t)";
            class8.description = "Its A Fishing Cape(t)".getBytes();
        }
        if (i == 9054) {
            class8.actions = new String[5];
            class8.actions[1] = "Wear";
            class8.modifiedModelColors = new int[3];
            class8.originalModelColors = new int[3];
            class8.modifiedModelColors[0] = 960;
            class8.modifiedModelColors[1] = 22464;
            class8.modifiedModelColors[2] = 43968;
            class8.originalModelColors[0] = 38226;//Back
            class8.originalModelColors[1] = 38226;//front Line
            class8.originalModelColors[2] = 38226;//Inside
            class8.modelID = 4203; //Model ID
            class8.stackable = false;//Stackable
            class8.modelZoom = 720; //zoom increase will make it smaller
            class8.modelRotation1 = 0; //model rotate up+down increase to move doen away from you
            class8.modelRotation2 = 0; //model rotate side ways increase to move right in circle
            class8.modelOffset1 = 0; // model offset increase to move to the right
            class8.modelOffset2 = 0; //model offset increase to move up
            class8.anInt165 = 4202;//male wearing
            class8.anInt200 = 4202;//female wearing
            class8.name = "Fishing Hood";
            class8.description = "Its A Fishing Hood".getBytes();
        }
        if (i == 9055) {
            class8.actions = new String[5];
            class8.actions[1] = "Wear";
            class8.modifiedModelColors = new int[4];
            class8.originalModelColors = new int[4];
            class8.modifiedModelColors[0] = 57280;
            class8.modifiedModelColors[1] = 54503;
            class8.modifiedModelColors[2] = 54183;
            class8.modifiedModelColors[3] = 11200;
            class8.originalModelColors[0] = 51000;
            class8.originalModelColors[1] = 51000;
            class8.originalModelColors[2] = 51000;
            class8.originalModelColors[3] = 51000;
            class8.modelID = 4160; //Inv & Ground
            class8.modelZoom = 2000; //Zoom
            class8.modelRotation1 = 572;
            class8.modelRotation2 = 0;
            class8.anInt204 = 0;
            class8.modelOffset1 = 0;
            class8.modelOffset2 = 1;
            class8.anInt165 = 4182; //Male
            class8.anInt200 = 7180; //Female
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.anInt199 = -1;//noteable
            class8.name = "cooking Cape";
            class8.description = "Its A Cooking Cape".getBytes();
        }
        if (i == 9056) {
            class8.actions = new String[5];
            class8.actions[1] = "Wear";
            class8.modifiedModelColors = new int[4];
            class8.originalModelColors = new int[4];
            class8.modifiedModelColors[0] = 57280;
            class8.modifiedModelColors[1] = 54503;
            class8.modifiedModelColors[2] = 54183;
            class8.modifiedModelColors[3] = 11200;
            class8.originalModelColors[0] = 926;
            class8.originalModelColors[1] = 926;
            class8.originalModelColors[2] = 926;
            class8.originalModelColors[3] = 926;
            class8.modelID = 4160; //Inv & Ground
            class8.modelZoom = 2000; //Zoom
            class8.modelRotation1 = 572;
            class8.modelRotation2 = 0;
            class8.anInt204 = 0;
            class8.modelOffset1 = 0;
            class8.modelOffset2 = 1;
            class8.anInt165 = 4182; //Male
            class8.anInt200 = 7180; //Female
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.anInt199 = -1;//noteable
            class8.name = "cooking Cape(t)";
            class8.description = "Its A Cooking Cape(t)".getBytes();
        }
        if (i == 9057) {
            class8.actions = new String[5];
            class8.actions[1] = "Wear";
            class8.modifiedModelColors = new int[3];
            class8.originalModelColors = new int[3];
            class8.modifiedModelColors[0] = 960;
            class8.modifiedModelColors[1] = 22464;
            class8.modifiedModelColors[2] = 43968;
            class8.originalModelColors[0] = 51136;//Back
            class8.originalModelColors[1] = 51136;//front Line
            class8.originalModelColors[2] = 51136;//Inside
            class8.modelID = 4203; //Model ID
            class8.stackable = false;//Stackable
            class8.modelZoom = 720; //zoom increase will make it smaller
            class8.modelRotation1 = 0; //model rotate up+down increase to move doen away from you
            class8.modelRotation2 = 0; //model rotate side ways increase to move right in circle
            class8.modelOffset1 = 0; // model offset increase to move to the right
            class8.modelOffset2 = 0; //model offset increase to move up
            class8.anInt165 = 4202;//male wearing
            class8.anInt200 = 4202;//female wearing
            class8.name = "Cooking Hood";
            class8.description = "Its A Cooking Hood".getBytes();
        }

        if (i == 9058) {
            class8.actions = new String[5];
            class8.actions[1] = "Wear";
            class8.modifiedModelColors = new int[4];
            class8.originalModelColors = new int[4];
            class8.modifiedModelColors[0] = 57280;
            class8.modifiedModelColors[1] = 54503;
            class8.modifiedModelColors[2] = 54183;
            class8.modifiedModelColors[3] = 11200;
            class8.originalModelColors[0] = 5560;
            class8.originalModelColors[1] = 5560;
            class8.originalModelColors[2] = 5560;
            class8.originalModelColors[3] = 5560;
            class8.modelID = 4174; //Inv & Ground
            class8.modelZoom = 2000; //Zoom
            class8.modelRotation1 = 572;
            class8.modelRotation2 = 0;
            class8.anInt204 = 0;
            class8.modelOffset1 = 0;
            class8.modelOffset2 = 1;
            class8.anInt165 = 4200; //Male
            class8.anInt200 = 7199; //Female
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.name = "woodcutting Cape";
            class8.description = "Its A woodcutting Cape".getBytes();
        }
        if (i == 9059) {
            class8.actions = new String[5];
            class8.actions[1] = "Wear";
            class8.modifiedModelColors = new int[4];
            class8.originalModelColors = new int[4];
            class8.modifiedModelColors[0] = 57280;
            class8.modifiedModelColors[1] = 54503;
            class8.modifiedModelColors[2] = 54183;
            class8.modifiedModelColors[3] = 11200;
            class8.originalModelColors[0] = 22430;
            class8.originalModelColors[1] = 22430;
            class8.originalModelColors[2] = 22430;
            class8.originalModelColors[3] = 22430;
            class8.modelID = 4174; //Inv & Ground
            class8.modelZoom = 2000; //Zoom
            class8.modelRotation1 = 572;
            class8.modelRotation2 = 0;
            class8.anInt204 = 0;
            class8.modelOffset1 = 0;
            class8.modelOffset2 = 1;
            class8.anInt165 = 4200; //Male
            class8.anInt200 = 7199; //Female
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.name = "woodcutting Cape";
            class8.description = "Its A woodcutting Cape".getBytes();
        }
        if (i == 9060) {
            class8.actions = new String[5];
            class8.actions[1] = "Wear";
            class8.modifiedModelColors = new int[3];
            class8.originalModelColors = new int[3];
            class8.modifiedModelColors[0] = 960;
            class8.modifiedModelColors[1] = 22464;
            class8.modifiedModelColors[2] = 43968;
            class8.originalModelColors[0] = 5560;//Back
            class8.originalModelColors[1] = 5560;//front Line
            class8.originalModelColors[2] = 5560;//Inside
            class8.modelID = 4203; //Model ID
            class8.stackable = false;//Stackable
            class8.modelZoom = 720; //zoom increase will make it smaller
            class8.modelRotation1 = 0; //model rotate up+down increase to move doen away from you
            class8.modelRotation2 = 0; //model rotate side ways increase to move right in circle
            class8.modelOffset1 = 0; // model offset increase to move to the right
            class8.modelOffset2 = 0; //model offset increase to move up
            class8.anInt165 = 4202;//male wearing
            class8.anInt200 = 4202;//female wearing
            class8.name = "Woodcutting Hood";
            class8.description = "Its A woodcutting Hood".getBytes();
        }
        if (i == 9061) {
            class8.actions = new String[5];
            class8.actions[1] = "Wear";
            class8.modifiedModelColors = new int[4];
            class8.originalModelColors = new int[4];
            class8.modifiedModelColors[0] = 57280;
            class8.modifiedModelColors[1] = 54503;
            class8.modifiedModelColors[2] = 54183;
            class8.modifiedModelColors[3] = 11200;
            class8.originalModelColors[0] = 22416;
            class8.originalModelColors[1] = 22416;
            class8.originalModelColors[2] = 22416;
            class8.originalModelColors[3] = 22416;
            class8.modelID = 4163; //Inv & Ground
            class8.modelZoom = 2000; //Zoom
            class8.modelRotation1 = 572;
            class8.modelRotation2 = 0;
            class8.anInt204 = 0;
            class8.modelOffset1 = 0;
            class8.modelOffset2 = 1;
            class8.anInt165 = 4185; //Male
            class8.anInt200 = 7183; //Female
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.anInt199 = -1;//noteable
            class8.name = "Farming Cape";
            class8.description = "Its A Farming Cape".getBytes();
        }

        if (i == 9062) {
            class8.actions = new String[5];
            class8.actions[1] = "Wear";
            class8.modifiedModelColors = new int[4];
            class8.originalModelColors = new int[4];
            class8.modifiedModelColors[0] = 57280;
            class8.modifiedModelColors[1] = 54503;
            class8.modifiedModelColors[2] = 54183;
            class8.modifiedModelColors[3] = 11200;
            class8.originalModelColors[0] = 14792;
            class8.originalModelColors[1] = 14784;
            class8.originalModelColors[2] = 14784;
            class8.originalModelColors[3] = 14792;
            class8.modelID = 4163; //Inv & Ground
            class8.modelZoom = 2000; //Zoom
            class8.modelRotation1 = 572;
            class8.modelRotation2 = 0;
            class8.anInt204 = 0;
            class8.modelOffset1 = 0;
            class8.modelOffset2 = 1;
            class8.anInt165 = 4185; //Male
            class8.anInt200 = 7183; //Female
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.anInt199 = -1;//noteable
            class8.name = "Farming Cape(t)";
            class8.description = "Its A Farming Cape(t)".getBytes();
        }
        if (i == 9063) {
            class8.actions = new String[5];
            class8.actions[1] = "Wear";
            class8.modifiedModelColors = new int[3];
            class8.originalModelColors = new int[3];
            class8.modifiedModelColors[0] = 960;
            class8.modifiedModelColors[1] = 22464;
            class8.modifiedModelColors[2] = 43968;
            class8.originalModelColors[0] = 22416;//Back
            class8.originalModelColors[1] = 22416;//front Line
            class8.originalModelColors[2] = 22416;//Inside
            class8.modelID = 4203; //Model ID
            class8.stackable = false;//Stackable
            class8.modelZoom = 720; //zoom increase will make it smaller
            class8.modelRotation1 = 0; //model rotate up+down increase to move doen away from you
            class8.modelRotation2 = 0; //model rotate side ways increase to move right in circle
            class8.modelOffset1 = 0; // model offset increase to move to the right
            class8.modelOffset2 = 0; //model offset increase to move up
            class8.anInt165 = 4202;//male wearing
            class8.anInt200 = 4202;//female wearing
            class8.name = "Farming Hood";
            class8.description = "Its A Farming Hood".getBytes();
        }
        if (i == 9064) {
            class8.actions = new String[5];
            class8.actions[1] = "Wear";
            class8.modifiedModelColors = new int[4];
            class8.originalModelColors = new int[4];
            class8.modifiedModelColors[0] = 57280;
            class8.modifiedModelColors[1] = 54503;
            class8.modifiedModelColors[2] = 54183;
            class8.modifiedModelColors[3] = 11200;
            class8.originalModelColors[0] = 38226;
            class8.originalModelColors[1] = 38226;
            class8.originalModelColors[2] = 38226;
            class8.originalModelColors[3] = 38226;
            class8.modelID = 4172; //Inv & Ground
            class8.modelZoom = 2000; //Zoom
            class8.modelRotation1 = 572;
            class8.modelRotation2 = 0;
            class8.anInt204 = 0;
            class8.modelOffset1 = 0;
            class8.modelOffset2 = 1;
            class8.anInt165 = 4194; //Male
            class8.anInt200 = 7192; //Female
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.anInt199 = -1;//noteable
            class8.name = "Quest Cape";
            class8.description = "Its A quest Cape".getBytes();
        }
        if (i == 9065) {
            class8.actions = new String[5];
            class8.actions[1] = "Wear";
            class8.modifiedModelColors = new int[3];
            class8.originalModelColors = new int[3];
            class8.modifiedModelColors[0] = 960;
            class8.modifiedModelColors[1] = 22464;
            class8.modifiedModelColors[2] = 43968;
            class8.originalModelColors[0] = 38226;//Back
            class8.originalModelColors[1] = 38226;//front Line
            class8.originalModelColors[2] = 38226;//Inside
            class8.modelID = 4203; //Model ID
            class8.stackable = false;//Stackable
            class8.modelZoom = 720; //zoom increase will make it smaller
            class8.modelRotation1 = 0; //model rotate up+down increase to move doen away from you
            class8.modelRotation2 = 0; //model rotate side ways increase to move right in circle
            class8.modelOffset1 = 0; // model offset increase to move to the right
            class8.modelOffset2 = 0; //model offset increase to move up
            class8.anInt165 = 4202;//male wearing
            class8.anInt200 = 4202;//female wearing
            class8.name = "Quest Hood";
            class8.description = "Its A Quest Hood".getBytes();
        }


        if (i == 9066) {
            class8.actions = new String[5];
            class8.actions[1] = "Wear";
            class8.modifiedModelColors = new int[4];
            class8.originalModelColors = new int[4];
            class8.modifiedModelColors[0] = 57280;
            class8.modifiedModelColors[1] = 54503;
            class8.modifiedModelColors[2] = 54183;
            class8.modifiedModelColors[3] = 11200;
            class8.originalModelColors[0] = 6340;
            class8.originalModelColors[1] = 6338;
            class8.originalModelColors[2] = 6338;
            class8.originalModelColors[3] = 6340;
            class8.modelRotation1 = 0;//ModelRotation1
            class8.modelRotation2 = 0;//modelrotate2 side ways increase to move right in circle
            class8.modelOffset1 = 0;// modeloffset1 increase to move to the right
            class8.modelOffset2 = 0;//modeloffset2 increase to move up
            class8.modelID = 4156; //Inv & Ground
            class8.modelZoom = 2000; //Zoom
            class8.modelRotation1 = 572;
            class8.modelRotation2 = 0;
            class8.anInt204 = 0;
            class8.modelOffset1 = 0;
            class8.modelOffset2 = 1;
            class8.anInt165 = 4179; //Male
            class8.anInt200 = 7179; //Female
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.anInt199 = -1;//noteable
            class8.name = "Const. Cape";
            class8.description = "Its A ConstructionCape".getBytes();
        }
        if (i == 9067) {
            class8.actions = new String[5];
            class8.actions[1] = "Wear";
            class8.modifiedModelColors = new int[4];
            class8.originalModelColors = new int[4];
            class8.modifiedModelColors[0] = 57280;
            class8.modifiedModelColors[1] = 54503;
            class8.modifiedModelColors[2] = 54183;
            class8.modifiedModelColors[3] = 11200;
            class8.originalModelColors[0] = 9152;
            class8.originalModelColors[1] = 7104;
            class8.originalModelColors[2] = 7104;
            class8.originalModelColors[3] = 796;
            class8.modelRotation1 = 0;//ModelRotation1
            class8.modelRotation2 = 0;//modelrotate2 side ways increase to move right in circle
            class8.modelOffset1 = 0;// modeloffset1 increase to move to the right
            class8.modelOffset2 = 0;//modeloffset2 increase to move up
            class8.modelID = 4156; //Inv & Ground
            class8.modelZoom = 2000; //Zoom
            class8.modelRotation1 = 572;
            class8.modelRotation2 = 0;
            class8.anInt204 = 0;
            class8.modelOffset1 = 0;
            class8.modelOffset2 = 1;
            class8.anInt165 = 4179; //Male
            class8.anInt200 = 7179; //Female
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.anInt199 = -1;//noteable
            class8.name = "Const. Cape(t)";
            class8.description = "Its A ConstructionCape".getBytes();
        }
        if (i == 9068) {
            class8.actions = new String[5];
            class8.actions[1] = "Wear";
            class8.modifiedModelColors = new int[3];
            class8.originalModelColors = new int[3];
            class8.modifiedModelColors[0] = 960;
            class8.modifiedModelColors[1] = 22464;
            class8.modifiedModelColors[2] = 43968;
            class8.originalModelColors[0] = 6340;//Back
            class8.originalModelColors[1] = 6340;//front Line
            class8.originalModelColors[2] = 6340;//Inside
            class8.modelID = 4203; //Model ID
            class8.stackable = false;//Stackable
            class8.modelZoom = 720; //zoom increase will make it smaller
            class8.modelRotation1 = 0; //model rotate up+down increase to move doen away from you
            class8.modelRotation2 = 0; //model rotate side ways increase to move right in circle
            class8.modelOffset1 = 0; // model offset increase to move to the right
            class8.modelOffset2 = 0; //model offset increase to move up
            class8.anInt165 = 4202;//male wearing
            class8.anInt200 = 4202;//female wearing
            class8.name = "Const. Hood";
            class8.description = "Its A Quest Hood".getBytes();
        }









        if (i == 6570) {
            class8.actions = new String[5];
            class8.actions[1] = "Wear";
            class8.modelID = 9631; //Model ID

            class8.stackable = false;//Stackable
        }
        if (i == 9080) // change this if you need to "item number"
        {
            class8.actions = new String[5];
            class8.actions[1] = "Wield";//New option
            class8.modifiedModelColors = new int[1];
            class8.originalModelColors = new int[1];
            class8.modifiedModelColors[0] = 28; // colors
            class8.modifiedModelColors[0] = 74; // colors
            class8.originalModelColors[0] = 38676; // colors
            class8.originalModelColors[0] = 38313; // colors
            class8.modelID = 4671; //Model ID
            class8.modelZoom = 490;//Model Zoom
            class8.modelRotation1 = 344;//Model Rotation
            class8.modelRotation2 = 192;//Unknown
            class8.anInt204 = 138;//Unknown
            class8.anInt199 = -1;//noteable
            class8.modelOffset1 = 1;//Unknown
            class8.modelOffset2 = 20;//Unknown
            class8.anInt165 = 4672;//male wearing
            class8.anInt200 = 4672;//female wearing
            class8.anInt175 = 56;//Unknown
            class8.anInt197 = 116;//Unknown
            class8.anInt164 = -1;//Female arms/sleeves
            class8.anInt188 = -1;//male arms/sleeves
            class8.stackable = false;//Stackable
            class8.name = "Rune defender";//Name of the new item
            class8.description = "A defensive weapon.".getBytes();//examin info
        }
        if (i == 9104) // change this if you need to "item number"
        {
            class8.actions = new String[5];
            class8.actions[1] = "Wield";
            class8.modifiedModelColors = new int[1];
            class8.originalModelColors = new int[1];
            class8.modifiedModelColors[0] = 28; // colors
            class8.modifiedModelColors[0] = 74; // colors
            class8.originalModelColors[0] = 38676; // colors
            class8.originalModelColors[0] = 924; // colors
            class8.modelID = 4671; //Model ID - 15335 -These are the models I over wrote for it
            class8.modelZoom = 490;
            class8.modelRotation1 = 344;
            class8.modelRotation2 = 192;
            class8.anInt204 = 138;
            class8.modelOffset1 = 1;
            class8.modelOffset2 = 20;
            class8.anInt165 = 4672;//male wearing 15413
            class8.anInt200 = 4672;//female wearing 15413
            class8.anInt175 = 56;
            class8.anInt197 = 116;
            class8.name = "Dragon defender";
            class8.description = "A defensive weapon.".getBytes();
        }
        if (i == 9081) // change this if you need to "item number"
        {
            class8.actions = new String[5];
            class8.actions[1] = "Wield";//New option
            class8.modelID = 4673; //Model ID
            class8.modelZoom = 1200; //zoom increase will make it smaller
            class8.modelRotation1 = 510; //model rotate up+down increase to move doen away from you
            class8.modelRotation2 = 1840; //model rotate side ways increase to move right in circle
            class8.modelOffset1 = 0; // model offset increase to move to the right
            class8.modelOffset2 = 5; //model offset increase to move up
            class8.anInt165 = 4674;//male wearing
            class8.anInt200 = 4675;//female wearing
            class8.anInt164 = 337;//Female arms/sleeves
            class8.anInt188 = 156;//male arms/sleeves
            class8.stackable = false;//Stackable
            class8.name = "Fighter Torso";//Name of the new item
            class8.description = "Its A fighter Torso".getBytes();//examin info
        }
        if (i == 9082) // change this if you need to "item number"
        {
            class8.actions = new String[5];
            class8.actions[1] = "Wield";//New option
            class8.modelID = 4676; //Model ID
            class8.modelZoom = 2128;//Model Zoom
            class8.modelRotation1 = 591;//Model Rotation
            class8.modelRotation2 = 1034;//model rotate side ways increase to move right in circle
            class8.anInt204 = 138;//Unknown
            class8.anInt199 = -1;//noteable
            class8.modelOffset1 = 8;// model offset increase to move to the right
            class8.modelOffset2 = 11;//model offset increase to move up
            class8.anInt165 = 4677;//male wearing
            class8.anInt200 = 4677;//female wearing
            class8.anInt175 = -1;//Unknown
            class8.stackable = false;//Stackable//Unknown
            class8.anInt164 = -1;//Female arms/sleeves
            class8.anInt188 = -1;//male arms/sleeves
            class8.stackable = false;//Stackable
            class8.name = "Dark bow";//Name of the new item
            class8.description = "A very PowerFull Bow".getBytes();//examin info
        }
        if (i == 9083) // change this if you need to "item number"
        {
            class8.actions = new String[5];
            class8.actions[1] = "Wield";//New option
            class8.modelID = 5000; //Model ID
            class8.modelZoom = 1500;//Model Zoom
            class8.modelRotation1 = 432;//Model Rotation
            class8.modelRotation2 = 180;//model rotate side ways increase to move right in circle
            class8.anInt204 = 2006;//Unknown
            class8.membersObject = true;
            class8.aByte205 = 1;
            class8.aByte154 = 1;
            class8.modelOffset1 = -1;// model offset increase to move to the right
            class8.modelOffset2 = -8;//model offset increase to move up
            class8.anInt165 = -1;//male wearing
            class8.anInt200 = -1;//female wearing
            class8.anInt175 = -1;//Unknown
            class8.stackable = false;//Stackable//Unknown
            class8.anInt164 = 337;//Female arms/sleeves
            class8.anInt188 = 156;//male arms/sleeves
            class8.stackable = true;//Stackable
            class8.name = "Dragon arrow";//Name of the new item
            class8.description = "Dragon arrow".getBytes();//examin info
        }
        if (i == 9084) // change this if you need to "item number"
        {
            class8.actions = new String[5];
            class8.actions[1] = "Wield";//New option
            class8.modelID = 4678; //Model ID
            class8.modelZoom = 595;//Model Zoom
            class8.modelRotation1 = 0;//Model Rotation
            class8.modelRotation2 = 0;//model rotate side ways increase to move right in circle
            class8.anInt204 = 0;//Unknown
            class8.anInt199 = 0;//noteable
            class8.modelOffset1 = 0;// model offset increase to move to the right
            class8.modelOffset2 = 0;//model offset increase to move up
            class8.anInt165 = 4679;//male wearing
            class8.anInt200 = 4680;//female wearing
            class8.anInt175 = 56;//Unknown
            class8.anInt197 = 116;//Unknown
            class8.anInt164 = -1;//Female arms/sleeves
            class8.anInt188 = -1;//male arms/sleeves
            class8.stackable = false;//Stackable
            class8.name = "Dragon Boots";//Name of the new item
            class8.description = "A nice pair Of boots".getBytes();//examin info
        }
        if (i == 9085) // change this if you need to "item number"
        {
            class8.actions = new String[5];
            class8.actions[1] = "Wield";//New option
            class8.modelID = 4681; //Model ID
            class8.modelZoom = 900;//Model Zoom
            class8.modelRotation1 = 540;//Model Rotation
            class8.modelRotation2 = 2039;//model rotate side ways increase to move right in circle
            class8.anInt199 = 0;//noteable
            class8.modelOffset1 = 0;// model offset increase to move to the right
            class8.modelOffset2 = 0;//model offset increase to move up
            class8.anInt165 = 4682;//male wearing
            class8.anInt200 = 4683;//female wearing
            class8.anInt164 = 4685;//Female arms/sleeves
            class8.anInt188 = 4684;//male arms/sleeves
            class8.anInt175 = -1;//Unknown
            class8.stackable = false;//Stackable
            class8.name = "Bandos Chestplate";//Name of the new item
            class8.description = "Its A Bandos Chestplate".getBytes();//examin info
        }
        if (i == 9086) // change this if you need to "item number"
        {
            class8.actions = new String[5];
            class8.actions[1] = "Wield";//New option
            class8.modelID = 4686; //Model ID
            class8.modelZoom = 854;//Model Zoom
            class8.modelRotation1 = 540;//Model Rotation
            class8.modelRotation2 = 2039;//model rotate side ways increase to move right in circle
            class8.anInt204 = 144;//Unknown
            class8.anInt199 = -1;//noteable
            class8.modelOffset1 = 9;// model offset increase to move to the right
            class8.modelOffset2 = 5;//model offset increase to move up
            class8.anInt165 = 4687;//male wearing
            class8.anInt200 = 4688;//female wearing
            class8.anInt175 = -1;//Unknown
            class8.stackable = false;//Stackable
            class8.anInt164 = -1;//Female arms/sleeves
            class8.anInt188 = -1;//male arms/sleeves
            class8.stackable = false;//Stackable
            class8.name = "Bandos tassets";//Name of the new item
            class8.description = "Its A Bandos Tassets".getBytes();//examin info
        }
        if (i == 9087) // change this if you need to "item number"
        {
            class8.actions = new String[5];
            class8.actions[1] = "Wield";//New option
            class8.modelID = 4689; //Model ID
            class8.modelZoom = 900;//Model Zoom
            class8.modelRotation1 = 540;//Model Rotation
            class8.modelRotation2 = 2039;//model rotate side ways increase to move right in circle
            class8.anInt199 = 0;//noteable
            class8.modelOffset1 = 0;// model offset increase to move to the right
            class8.modelOffset2 = 0;//model offset increase to move up
            class8.anInt165 = 4690;//male wearing
            class8.anInt200 = 4691;//female wearing
            class8.anInt175 = -1;//Unknown
            class8.stackable = false;//Stackable
            class8.anInt164 = 4693;//Female arms/sleeves
            class8.anInt188 = 4692;//male arms/sleeves
            class8.stackable = false;//Stackable
            class8.name = "Armadyl chestplate";//Name of the new item
            class8.description = "Its A Armadyl Chestplate".getBytes();//examin info
        }
        if (i == 9088) // change this if you need to "item number"
        {
            class8.actions = new String[5];
            class8.actions[1] = "Wield";//New option
            class8.modelID = 4694; //Model ID
            class8.modelZoom = 1600;//Model Zoom
            class8.modelRotation1 = 510;//Model Rotation
            class8.modelRotation2 = 0;//model rotate side ways increase to move right in circle
            class8.anInt204 = 138;//Unknown
            class8.anInt199 = -1;//noteable
            class8.modelOffset1 = 0;// model offset increase to move to the right
            class8.modelOffset2 = 0;//model offset increase to move up
            class8.anInt165 = 4695;//male wearing
            class8.anInt200 = 4696;//female wearing
            class8.anInt175 = -1;//Unknown
            class8.stackable = false;//Stackable
            class8.anInt164 = -1;//Female arms/sleeves
            class8.anInt188 = -1;//male arms/sleeves
            class8.stackable = false;//Stackable
            class8.name = "Armadyl plateskirt";//Name of the new item
            class8.description = "Its A Armadyl plateskirt".getBytes();//examin info
        }
        if (i == 9089) // change this if you need to "item number"
        {
            class8.actions = new String[5];
            class8.actions[1] = "Wield";//New option
            class8.modelID = 7202; //Model ID
            class8.stackable = false;//Stackable
            class8.modelZoom = 984; //zoom increase will make it smaller
            class8.modelRotation1 = 126; //model rotate up+down increase to move doen away from you
            class8.modelRotation2 = 129; //model rotate side ways increase to move right in circle
            class8.modelOffset1 = -1; // model offset increase to move to the right
            class8.modelOffset2 = 1; //model offset increase to move up
            class8.anInt204 = 28;
            class8.anInt165 = 7200;//male wearing
            class8.anInt200 = 7201;//female wearing
            class8.anInt204 = 28;
            class8.anInt175 = -1;//Unknown
            class8.anInt164 = -1;//Female arms/sleeves
            class8.anInt188 = -1;//male arms/sleeves
            class8.stackable = false;//Stackable
            class8.name = "Helm of neitiznot";//Name of the new item
            class8.description = "Its A Helm of neitiznot".getBytes();//examin info
        }
        if (i == 9090) // change this if you need to "item number"
        {
            class8.actions = new String[5];
            class8.actions[1] = "Wield";//New option
            class8.modelID = 7205; //Model ID
            class8.modelZoom = 844; //zoom increase will make it smaller
            class8.modelRotation1 = 126; //model rotate up+down increase to move doen away from you
            class8.modelRotation2 = 129; //model rotate side ways increase to move right in circle
            class8.modelOffset1 = -1; // model offset increase to move to the right
            class8.modelOffset2 = 1; //model offset increase to move up
            class8.anInt204 = 28;
            class8.anInt165 = 7204;//male wearing
            class8.anInt200 = 7203;//female wearing
            class8.anInt175 = -1;//Unknown
            class8.stackable = false;//Stackable
            class8.anInt164 = -1;//Female arms/sleeves
            class8.anInt188 = -1;//male arms/sleeves
            class8.stackable = false;//Stackable
            class8.name = "Dragon Full Helm";//Name of the new item
            class8.description = "Its A Dragon Full Helm".getBytes();//examin info
        }
        if (i == 9069) // change this if you need to "item number"
        {
            class8.actions = new String[5];
            class8.actions[1] = "Wield";//New option
            class8.modelID = 7169; //Model ID
            class8.modelZoom = 1200; //zoom increase will make it smaller
            class8.modelRotation1 = 572; //model rotate up+down increase to move doen away from you
            class8.modelRotation2 = 0; //model rotate side ways increase to move right in circle
            class8.modelOffset1 = 0; // model offset increase to move to the right
            class8.modelOffset2 = 1; //model offset increase to move up
            class8.anInt165 = 7148;//male wearing
            class8.anInt200 = 7163;//female wearing
            class8.anInt164 = 7152;//Female arms/sleeves
            class8.anInt188 = 7138;//male arms/sleeves
            class8.stackable = false;//Stackable
            class8.name = "3rd Age plateBody";//Name of the new item
            class8.description = "A ancient PlateBody".getBytes();//examin info
        }
        if (i == 9070) // change this if you need to "item number"
        {
            class8.actions = new String[5];
            class8.actions[1] = "Wield";//New option
            class8.modelID = 7167; //Model ID
            class8.modelZoom = 1740; //zoom increase will make it smaller
            class8.modelRotation1 = 572; //model rotate up+down increase to move doen away from you
            class8.modelRotation2 = 0; //model rotate side ways increase to move right in circle
            class8.modelOffset1 = 4; // model offset increase to move to the right
            class8.modelOffset2 = 11; //model offset increase to move up
            class8.anInt165 = 7145;//male wearing
            class8.anInt200 = 7159;//female wearing
            class8.anInt164 = -1;//Female arms/sleeves
            class8.anInt188 = -1;//male arms/sleeves
            class8.stackable = false;//Stackable
            class8.name = "3rd Age plateLegs";//Name of the new item
            class8.description = "A ancient Platelegs".getBytes();//examin info
        }
        if (i == 9071) // change this if you need to "item number"
        {
            class8.actions = new String[5];
            class8.actions[1] = "Wield";//New option
            class8.modelID = 7166; //Model ID
            class8.modelZoom = 720; //zoom increase will make it smaller
            class8.modelRotation1 = 28; //model rotate up+down increase to move doen away from you
            class8.modelRotation2 = 1892; //model rotate side ways increase to move right in circle
            class8.modelOffset1 = 0; // model offset increase to move to the right
            class8.modelOffset2 = 0; //model offset increase to move up
            class8.anInt165 = 7142;//male wearing
            class8.anInt200 = 7156;//female wearing
            class8.anInt164 = -1;//Female arms/sleeves
            class8.anInt188 = -1;//male arms/sleeves
            class8.stackable = false;//Stackable
            class8.name = "3rd Age Full helm";//Name of the new item
            class8.description = "A ancient Full helm".getBytes();//examin info
        }
        if (i == 9072) // change this if you need to "item number"
        {
            class8.actions = new String[5];
            class8.actions[1] = "Wield";//New option
            class8.modelID = 7168; //Model ID
            class8.modelZoom = 2030; //zoom increase will make it smaller
            class8.modelRotation1 = 364; //model rotate up+down increase to move doen away from you
            class8.modelRotation2 = 56; //model rotate side ways increase to move right in circle
            class8.modelOffset1 = 0; // model offset increase to move to the right
            class8.modelOffset2 = 0; //model offset increase to move up
            class8.anInt165 = 7151;//male wearing
            class8.anInt200 = 7151;//female wearing
            class8.anInt164 = -1;//Female arms/sleeves
            class8.anInt188 = -1;//male arms/sleeves
            class8.stackable = false;//Stackable
            class8.name = "3rd Age KiteShield";//Name of the new item
            class8.description = "A ancient Kiteshield".getBytes();//examin info
        }
        if (i == 9073) // change this if you need to "item number"
        {
            class8.actions = new String[5];
            class8.actions[1] = "Wield";//New option
            class8.modelID = 7172; //Model ID
            class8.modelZoom = 2083; //zoom increase will make it smaller
            class8.modelRotation1 = 572; //model rotate up+down increase to move doen away from you
            class8.modelRotation2 = 0; //model rotate side ways increase to move right in circle
            class8.modelOffset1 = 0; // model offset increase to move to the right
            class8.modelOffset2 = 0; //model offset increase to move up
            class8.anInt165 = 7149;//male wearing
            class8.anInt200 = 7164;//female wearing
            class8.anInt164 = 7153;//Female arms/sleeves
            class8.anInt188 = 7139;//male arms/sleeves
            class8.stackable = false;//Stackable
            class8.name = "3rd Age Robe Top";//Name of the new item
            class8.description = "A ancient Robe Top".getBytes();//examin info
        }
        if (i == 9074) // change this if you need to "item number"
        {
            class8.actions = new String[5];
            class8.actions[1] = "Wield";//New option
            class8.modelID = 7171; //Model ID
            class8.modelZoom = 2083; //zoom increase will make it smaller
            class8.modelRotation1 = 572; //model rotate up+down increase to move doen away from you
            class8.modelRotation2 = 0; //model rotate side ways increase to move right in circle
            class8.modelOffset1 = 0; // model offset increase to move to the right
            class8.modelOffset2 = 1; //model offset increase to move up
            class8.anInt165 = 7146;//male wearing
            class8.anInt200 = 7160;//female wearing
            class8.anInt164 = -1;//Female arms/sleeves
            class8.anInt188 = -1;//male arms/sleeves
            class8.stackable = false;//Stackable
            class8.name = "3rd Age Robe";//Name of the new item
            class8.description = "A ancient Robe".getBytes();//examin info
        }
        if (i == 9075) // change this if you need to "item number"
        {
            class8.actions = new String[5];
            class8.actions[1] = "Wield";//New option
            class8.modelID = 7170; //Model ID
            class8.modelZoom = 917; //zoom increase will make it smaller
            class8.modelRotation1 = 212; //model rotate up+down increase to move doen away from you
            class8.modelRotation2 = 1883; //model rotate side ways increase to move right in circle
            class8.modelOffset1 = 0; // model offset increase to move to the right
            class8.modelOffset2 = 0; //model offset increase to move up
            class8.anInt165 = 7143;//male wearing
            class8.anInt200 = 7157;//female wearing
            class8.anInt164 = -1;//Female arms/sleeves
            class8.anInt188 = -1;//male arms/sleeves
            class8.stackable = false;//Stackable
            class8.name = "3rd Age Mage Hat";//Name of the new item
            class8.description = "A ancient hat".getBytes();//examin info
        }
        if (i == 9076) // change this if you need to "item number"
        {
            class8.actions = new String[5];
            class8.actions[1] = "Wield";//New option
            class8.modelID = 7175; //Model ID
            class8.modelZoom = 1200; //zoom increase will make it smaller
            class8.modelRotation1 = 572; //model rotate up+down increase to move doen away from you
            class8.modelRotation2 = 0; //model rotate side ways increase to move right in circle
            class8.modelOffset1 = 0; // model offset increase to move to the right
            class8.modelOffset2 = 1; //model offset increase to move up
            class8.anInt165 = 7150;//male wearing
            class8.anInt200 = 7165;//female wearing
            class8.anInt164 = 7154;//Female arms/sleeves
            class8.anInt188 = 7140;//male arms/sleeves
            class8.stackable = false;//Stackable
            class8.name = "3rd Age Range Top";//Name of the new item
            class8.description = "A ancient Range Top".getBytes();//examin info
        }
        if (i == 9077) // change this if you need to "item number"
        {
            class8.actions = new String[5];
            class8.actions[1] = "Wield";//New option
            class8.modelID = 7174; //Model ID
            class8.modelZoom = 1827; //zoom increase will make it smaller
            class8.modelRotation1 = 572; //model rotate up+down increase to move doen away from you
            class8.modelRotation2 = 0; //model rotate side ways increase to move right in circle
            class8.modelOffset1 = 0; // model offset increase to move to the right
            class8.modelOffset2 = 1; //model offset increase to move up
            class8.anInt165 = 7147;//male wearing
            class8.anInt200 = 7161;//female wearing
            class8.anInt164 = -1;//Female arms/sleeves
            class8.anInt188 = -1;//male arms/sleeves
            class8.stackable = false;//Stackable
            class8.name = "3rd Age Range Legs";//Name of the new item
            class8.description = "A ancient Range Legs".getBytes();//examin info
        }
        if (i == 9078) // change this if you need to "item number"
        {
            class8.actions = new String[5];
            class8.actions[1] = "Wield";//New option
            class8.modelID = 7173; //Model ID
            class8.modelZoom = 917; //zoom increase will make it smaller
            class8.modelRotation1 = 212; //model rotate up+down increase to move doen away from you
            class8.modelRotation2 = 1883; //model rotate side ways increase to move right in circle
            class8.modelOffset1 = 0; // model offset increase to move to the right
            class8.modelOffset2 = 0; //model offset increase to move up
            class8.anInt165 = 7144;//male wearing
            class8.anInt200 = 7158;//female wearing
            class8.anInt164 = -1;//Female arms/sleeves
            class8.anInt188 = -1;//male arms/sleeves
            class8.stackable = false;//Stackable
            class8.name = "3rd Age Range Coif";//Name of the new item
            class8.description = "A ancient Range coif".getBytes();//examin info
        }
        if (i == 9079) // change this if you need to "item number"
        {
            class8.actions = new String[5];
            class8.actions[1] = "Wield";//New option
            class8.modelID = 7176; //Model ID
            class8.modelZoom = 740; //zoom increase will make it smaller
            class8.modelRotation1 = 196; //model rotate up+down increase to move doen away from you
            class8.modelRotation2 = 1784; //model rotate side ways increase to move right in circle
            class8.modelOffset1 = 0; // model offset increase to move to the right
            class8.modelOffset2 = 0; //model offset increase to move up
            class8.anInt165 = 7141;//male wearing
            class8.anInt200 = 7155;//female wearing
            class8.anInt164 = -1;//Female arms/sleeves
            class8.anInt188 = -1;//male arms/sleeves
            class8.stackable = false;//Stackable
            class8.name = "3rd Age vambraces";//Name of the new item
            class8.description = "A ancient vambraces".getBytes();//examin info
        }





        switch (i) {


            case 91:
                class8.name = "Guam potion (unf)";
                break;
            case 92:
                class8.name = "Guam potion (unf)";
                break;
            case 93:
                class8.name = "Marrentil potion (unf)";
                break;
            case 94:
                class8.name = "Marrentil potion (unf)";
                break;
            case 95:
                class8.name = "Tarromin potion (unf)";
                break;
            case 96:
                class8.name = "Tarromin potion (unf)";
                break;
            case 97:
                class8.name = "Harralander potion (unf)";
                break;
            case 98:
                class8.name = "Harralander potion (unf)";
                break;
            case 99:
                class8.name = "Ranarr potion (unf)";
                break;
            case 100:
                class8.name = "Ranarr potion (unf)";
                break;
            case 101:
                class8.name = "Irit potion (unf)";
                break;
            case 102:
                class8.name = "Irit potion (unf)";
                break;
            case 103:
                class8.name = "Avantoe potion (unf)";
                break;
            case 104:
                class8.name = "Avantoe potion (unf)";
                break;
            case 105:
                class8.name = "Kwuarm potion (unf)";
                break;
            case 106:
                class8.name = "Kwuarm potion (unf)";
                break;
            case 107:
                class8.name = "Cadantine potion (unf)";
                break;
            case 108:
                class8.name = "Cadantine potion (unf)";
                break;
            case 109:
                class8.name = "Dwarf weed potion (unf)";
                break;
            case 110:
                class8.name = "Dwarf weed potion (unf)";
                break;
            case 111:
                class8.name = "Torstol potion (unf)";
                break;
            case 2483:
                class8.name = "Lantadyme potion (unf)";
                break;
            case 2484:
                class8.name = "Lantadyme potion (unf)";
                break;
            case 3002:
                class8.name = "Toadflax potion (unf)";
                break;
            case 3003:
                class8.name = "Toadflax potion (unf)";
                break;
            case 3004:
                class8.name = "Snapdragon potion (unf)";
                break;
            case 3005:
                class8.name = "Snapdragon potion (unf)";
                break;







        }















        if (class8.certTemplateID != -1) {
            class8.toNode((byte) 61);
        }
        if (!isMembers && class8.membersObject) {
            class8.name = "Members Object";
            class8.description = "Login to a members' server to use this object.".getBytes();
            class8.groundActions = null;
            class8.actions = null;
            class8.team = 0;
        }
        return class8;
    }

    public void toNode(byte byte0) {
        ItemDef class8 = forID(certTemplateID);
        modelID = class8.modelID;
        modelZoom = class8.modelZoom;
        modelRotation1 = class8.modelRotation1;
        modelRotation2 = class8.modelRotation2;
        anInt204 = class8.anInt204;
        modelOffset1 = class8.modelOffset1;
        modelOffset2 = class8.modelOffset2;
        if (byte0 != 61) {
            aBoolean186 = !aBoolean186;
        }
        modifiedModelColors = class8.modifiedModelColors;
        originalModelColors = class8.originalModelColors;
        ItemDef class8_1 = forID(certID);
        name = class8_1.name;
        membersObject = class8_1.membersObject;
        value = class8_1.value;
        String s = "a";
        char c = class8_1.name.charAt(0);
        if (c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U') {
            s = "an";
        }
        description = ("Swap this note at any bank for " + s + " " + class8_1.name + ".").getBytes();
        stackable = true;
    }

    public static final Sprite getSprite(int i, int j, int k, int l) {
        if (k == 0) {
            Sprite class30_sub2_sub1_sub1 = (Sprite) mruNodes1.get(i);
            if (class30_sub2_sub1_sub1 != null && class30_sub2_sub1_sub1.maxHeight != j && class30_sub2_sub1_sub1.maxHeight != -1) {
                class30_sub2_sub1_sub1.unlink();
                class30_sub2_sub1_sub1 = null;
            }
            if (class30_sub2_sub1_sub1 != null) {
                return class30_sub2_sub1_sub1;
            }
        }
        ItemDef class8 = forID(i);
        if (class8.stackIDs == null) {
            j = -1;
        }
        if (j > 1) {
            int i1 = -1;
            for (int j1 = 0; j1 < 10; j1++) {
                if (j >= class8.stackAmounts[j1] && class8.stackAmounts[j1] != 0) {
                    i1 = class8.stackIDs[j1];
                }
            }
            if (i1 != -1) {
                class8 = forID(i1);
            }
        }
        Model class30_sub2_sub4_sub6 = class8.method201(1);
        if (class30_sub2_sub4_sub6 == null) {
            return null;
        }
        Sprite class30_sub2_sub1_sub1_2 = null;
        if (class8.certTemplateID != -1) {
            class30_sub2_sub1_sub1_2 = getSprite(class8.certID, 10, -1, 9);
            if (class30_sub2_sub1_sub1_2 == null) {
                return null;
            }
        }
        Sprite class30_sub2_sub1_sub1_1 = new Sprite(32, 32);
        int k1 = Rasterizer.textureInt1;
        int l1 = Rasterizer.textureInt2;
        int ai[] = Rasterizer.anIntArray1472;
        int ai1[] = DrawingArea.pixels;
        int i2 = DrawingArea.width;
        int j2 = DrawingArea.height;
        int k2 = DrawingArea.topX;
        int l2 = DrawingArea.bottomX;
        int i3 = DrawingArea.topY;
        int j3 = DrawingArea.bottomY;
        Rasterizer.aBoolean1464 = false;
        DrawingArea.initDrawingArea(32, 32, class30_sub2_sub1_sub1_1.myPixels);
        DrawingArea.fillBox(0, 0, 32, 32, 0);
        Rasterizer.method364();
        int k3 = class8.modelZoom;
        if (k == -1) {
            k3 = (int) ((double) k3 * 1.5D);
        }
        if (k > 0) {
            k3 = (int) ((double) k3 * 1.04D);
        }
        int l3 = Rasterizer.SINE[class8.modelRotation1] * k3 >> 16;
        int i4 = Rasterizer.COSINE[class8.modelRotation1] * k3 >> 16;
        class30_sub2_sub4_sub6.method482(0, class8.modelRotation2, class8.anInt204, class8.modelRotation1, class8.modelOffset1, l3 + ((Animable) (class30_sub2_sub4_sub6)).modelHeight / 2 + class8.modelOffset2, i4 + class8.modelOffset2);
        for (int i5 = 31; i5 >= 0; i5--) {
            for (int j4 = 31; j4 >= 0; j4--) {
                if (class30_sub2_sub1_sub1_1.myPixels[i5 + j4 * 32] == 0) {
                    if (i5 > 0 && class30_sub2_sub1_sub1_1.myPixels[(i5 - 1) + j4 * 32] > 1) {
                        class30_sub2_sub1_sub1_1.myPixels[i5 + j4 * 32] = 1;
                    } else if (j4 > 0 && class30_sub2_sub1_sub1_1.myPixels[i5 + (j4 - 1) * 32] > 1) {
                        class30_sub2_sub1_sub1_1.myPixels[i5 + j4 * 32] = 1;
                    } else if (i5 < 31 && class30_sub2_sub1_sub1_1.myPixels[i5 + 1 + j4 * 32] > 1) {
                        class30_sub2_sub1_sub1_1.myPixels[i5 + j4 * 32] = 1;
                    } else if (j4 < 31 && class30_sub2_sub1_sub1_1.myPixels[i5 + (j4 + 1) * 32] > 1) {
                        class30_sub2_sub1_sub1_1.myPixels[i5 + j4 * 32] = 1;
                    }
                }
            }
        }
        if (k > 0) {
            for (int j5 = 31; j5 >= 0; j5--) {
                for (int k4 = 31; k4 >= 0; k4--) {
                    if (class30_sub2_sub1_sub1_1.myPixels[j5 + k4 * 32] == 0) {
                        if (j5 > 0 && class30_sub2_sub1_sub1_1.myPixels[(j5 - 1) + k4 * 32] == 1) {
                            class30_sub2_sub1_sub1_1.myPixels[j5 + k4 * 32] = k;
                        } else if (k4 > 0 && class30_sub2_sub1_sub1_1.myPixels[j5 + (k4 - 1) * 32] == 1) {
                            class30_sub2_sub1_sub1_1.myPixels[j5 + k4 * 32] = k;
                        } else if (j5 < 31 && class30_sub2_sub1_sub1_1.myPixels[j5 + 1 + k4 * 32] == 1) {
                            class30_sub2_sub1_sub1_1.myPixels[j5 + k4 * 32] = k;
                        } else if (k4 < 31 && class30_sub2_sub1_sub1_1.myPixels[j5 + (k4 + 1) * 32] == 1) {
                            class30_sub2_sub1_sub1_1.myPixels[j5 + k4 * 32] = k;
                        }
                    }
                }
            }
        } else if (k == 0) {
            for (int k5 = 31; k5 >= 0; k5--) {
                for (int l4 = 31; l4 >= 0; l4--) {
                    if (class30_sub2_sub1_sub1_1.myPixels[k5 + l4 * 32] == 0 && k5 > 0 && l4 > 0 && class30_sub2_sub1_sub1_1.myPixels[(k5 - 1) + (l4 - 1) * 32] > 0) {
                        class30_sub2_sub1_sub1_1.myPixels[k5 + l4 * 32] = 0x302020;
                    }
                }
            }
        }
        if (class8.certTemplateID != -1) {
            int l5 = class30_sub2_sub1_sub1_2.maxWidth;
            int j6 = class30_sub2_sub1_sub1_2.maxHeight;
            class30_sub2_sub1_sub1_2.maxWidth = 32;
            class30_sub2_sub1_sub1_2.maxHeight = 32;
            class30_sub2_sub1_sub1_2.method348(0, 0);
            class30_sub2_sub1_sub1_2.maxWidth = l5;
            class30_sub2_sub1_sub1_2.maxHeight = j6;
        }
        if (k == 0) {
            mruNodes1.put(class30_sub2_sub1_sub1_1, i);
        }
        DrawingArea.initDrawingArea(j2, i2, ai1);
        DrawingArea.setDrawingArea(k2, i3, l2, j3);
        Rasterizer.textureInt1 = k1;
        Rasterizer.textureInt2 = l1;
        Rasterizer.anIntArray1472 = ai;
        Rasterizer.aBoolean1464 = true;
        if (l < 9 || l > 9) {
            for (int i6 = 1; i6 > 0; i6++);
        }
        if (class8.stackable) {
            class30_sub2_sub1_sub1_1.maxWidth = 33;
        } else {
            class30_sub2_sub1_sub1_1.maxWidth = 32;
        }
        class30_sub2_sub1_sub1_1.maxHeight = j;
        return class30_sub2_sub1_sub1_1;
    }

    public final Model method201(int i) {
        if (stackIDs != null && i > 1) {
            int j = -1;
            for (int k = 0; k < 10; k++) {
                if (i >= stackAmounts[k] && stackAmounts[k] != 0) {
                    j = stackIDs[k];
                }
            }
            if (j != -1) {
                return forID(j).method201(1);
            }
        }
        Model class30_sub2_sub4_sub6 = (Model) mruNodes2.get(id);
        if (class30_sub2_sub4_sub6 != null) {
            return class30_sub2_sub4_sub6;
        }
        class30_sub2_sub4_sub6 = Model.getModel(modelID);
        if (class30_sub2_sub4_sub6 == null) {
            return null;
        }
        if (anInt167 != 128 || anInt192 != 128 || anInt191 != 128) {
            class30_sub2_sub4_sub6.scaleT(anInt167, anInt191, anInt192);
        }
        if (modifiedModelColors != null) {
            for (int l = 0; l < modifiedModelColors.length; l++) {
                class30_sub2_sub4_sub6.reColour(modifiedModelColors[l], originalModelColors[l]);
            }
        }
        class30_sub2_sub4_sub6.light(64 + anInt196, 768 + anInt184, -50, -10, -50, true);
        class30_sub2_sub4_sub6.oneSquareModel = true;
        mruNodes2.put(class30_sub2_sub4_sub6, id);
        return class30_sub2_sub4_sub6;
    }

    public final Model getInventoryModel(int i) {
        if (stackIDs != null && i > 1) {
            int j = -1;
            for (int k = 0; k < 10; k++) {
                if (i >= stackAmounts[k] && stackAmounts[k] != 0) {
                    j = stackIDs[k];
                }
            }
            if (j != -1) {
                return forID(j).getInventoryModel(1);
            }
        }
        Model class30_sub2_sub4_sub6 = Model.getModel(modelID);
        if (class30_sub2_sub4_sub6 == null) {
            return null;
        }
        if (modifiedModelColors != null) {
            for (int l = 0; l < modifiedModelColors.length; l++) {
                class30_sub2_sub4_sub6.reColour(modifiedModelColors[l], originalModelColors[l]);
            }
        }
        return class30_sub2_sub4_sub6;
    }

    public final void readValues(Stream stream) {
        do {
            int i = stream.readUnsignedByte();
            if (i == 0) {
                return;
            }
            if (i == 1) {
                modelID = stream.readUnsignedWord();
            } else if (i == 2) {
                name = stream.readString();
            } else if (i == 3) {
                description = stream.readBytes();
            } else if (i == 4) {
                modelZoom = stream.readUnsignedWord();
            } else if (i == 5) {
                modelRotation1 = stream.readUnsignedWord();
            } else if (i == 6) {
                modelRotation2 = stream.readUnsignedWord();
            } else if (i == 7) {
                modelOffset1 = stream.readUnsignedWord();
                if (modelOffset1 > 32767) {
                    modelOffset1 -= 0x10000;
                }
            } else if (i == 8) {
                modelOffset2 = stream.readUnsignedWord();
                if (modelOffset2 > 32767) {
                    modelOffset2 -= 0x10000;
                }
            } else if (i == 10) {
                anInt199 = stream.readUnsignedWord();
            } else if (i == 11) {
                stackable = true;
            } else if (i == 12) {
                value = stream.readDWord();
            } else if (i == 16) {
                membersObject = true;
            } else if (i == 23) {
                anInt165 = stream.readUnsignedWord();
                aByte205 = stream.readSignedByte();
            } else if (i == 24) {
                anInt188 = stream.readUnsignedWord();
            } else if (i == 25) {
                anInt200 = stream.readUnsignedWord();
                aByte154 = stream.readSignedByte();
            } else if (i == 26) {
                anInt164 = stream.readUnsignedWord();
            } else if (i >= 30 && i < 35) {
                if (groundActions == null) {
                    groundActions = new String[5];
                }
                groundActions[i - 30] = stream.readString();
                if (groundActions[i - 30].equalsIgnoreCase("hidden")) {
                    groundActions[i - 30] = null;
                }
            } else if (i >= 35 && i < 40) {
                if (actions == null) {
                    actions = new String[5];
                }
                actions[i - 35] = stream.readString();
            } else if (i == 40) {
                int j = stream.readUnsignedByte();
                modifiedModelColors = new int[j];
                originalModelColors = new int[j];
                for (int k = 0; k < j; k++) {
                    modifiedModelColors[k] = stream.readUnsignedWord();
                    originalModelColors[k] = stream.readUnsignedWord();
                }
            } else if (i == 78) {
                anInt185 = stream.readUnsignedWord();
            } else if (i == 79) {
                anInt162 = stream.readUnsignedWord();
            } else if (i == 90) {
                anInt175 = stream.readUnsignedWord();
            } else if (i == 91) {
                anInt197 = stream.readUnsignedWord();
            } else if (i == 92) {
                anInt166 = stream.readUnsignedWord();
            } else if (i == 93) {
                anInt173 = stream.readUnsignedWord();
            } else if (i == 95) {
                anInt204 = stream.readUnsignedWord();
            } else if (i == 97) {
                certID = stream.readUnsignedWord();
            } else if (i == 98) {
                certTemplateID = stream.readUnsignedWord();
            } else if (i >= 100 && i < 110) {
                if (stackIDs == null) {
                    stackIDs = new int[10];
                    stackAmounts = new int[10];
                }
                stackIDs[i - 100] = stream.readUnsignedWord();
                stackAmounts[i - 100] = stream.readUnsignedWord();
            } else if (i == 110) {
                anInt167 = stream.readUnsignedWord();
            } else if (i == 111) {
                anInt192 = stream.readUnsignedWord();
            } else if (i == 112) {
                anInt191 = stream.readUnsignedWord();
            } else if (i == 113) {
                anInt196 = stream.readSignedByte();
            } else if (i == 114) {
                anInt184 = stream.readSignedByte() * 5;
            } else if (i == 115) {
                team = stream.readUnsignedByte();
            }
        } while (true);
    }
}