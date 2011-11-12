package client;

import client.ondemand.OnDemandFetcher;
import client.animation.AnimationFrame;
import client.animation.Animable;
import client.fileio.JagexArchive;

public final class ObjectDef {
    
    public boolean aBoolean736;
    private byte aByte737;
    private int anInt738;
    public String name;
    private int anInt740;
    private static Model model[] = new Model[4];
    private byte aByte742;
    private int anInt743;
    public int objectWidth;
    private int anInt745;
    public int anInt746;
    private int originalModelColors[];
    private int anInt748;
    public int anInt749;
    private boolean aBoolean751;
    public static boolean lowMem;
    private static Stream stream;
    public int type;
    private static int streamIndices[];
    private static int totalObjects;
    public boolean aBoolean757;
    public int anInt758;
    public int childrenIDs[];
    public int anInt760;
    public int objectHeight;
    public boolean aBoolean762;
    private boolean aBoolean763;
    public boolean aBoolean764;
    public static Client clientInstance;
    public boolean aBoolean766;
    public boolean aBoolean767;
    public int anInt768;
    private boolean aBoolean769;
    private int anInt770;
    private static int cacheIndex;
    private int anInt772;
    private int modelArray[];
    public int anInt774;
    public int anInt775;
    private int anIntArray776[];
    public byte description[];
    public boolean hasActions;
    public boolean aBoolean779;
    public static MemCache mruNodes2 = new MemCache(30);
    public int anInt781;
    private static ObjectDef cache[];
    private int anInt783;
    private int modifiedModelColors[];
    public static MemCache mruNodes1 = new MemCache(500);
    public String actions[];
    
    ObjectDef() {
        anInt743 = 9;
        type = -1;
        aBoolean763 = true;
        anInt770 = 9;
    }
    
    public static ObjectDef forID(int i) {
        for (int j = 0; j < 20; j++) {
            if (cache[j].type == i) {
                return cache[j];
            }
        }

        cacheIndex = (cacheIndex + 1) % 20;
        ObjectDef objectDef = cache[cacheIndex];
        stream.currentOffset = streamIndices[i];
        objectDef.type = i;
        objectDef.setDefaults();
        objectDef.readValues(stream);

// START OF OBJECTS //

        if (i == 2476) {
            objectDef.name = "Godwars entrance";
            objectDef.description = "No way of coming back!".getBytes();
            objectDef.objectWidth = 4;
            objectDef.objectHeight = 4;
            objectDef.modelArray = new int[1];
            objectDef.modelArray[0] = 8406;
            objectDef.hasActions = true;
            objectDef.aBoolean767 = true;
            objectDef.aBoolean762 = false;
            objectDef.aBoolean769 = false;
        }
        if (i == 2477) {
            objectDef.name = "Obelisk";
            objectDef.description = "A summoning obelisk.".getBytes();
            objectDef.objectWidth = 2;
            objectDef.objectHeight = 2;
            objectDef.modelArray = new int[1];
            objectDef.modelArray[0] = 8407;
            objectDef.hasActions = true;
            objectDef.aBoolean767 = true;
            objectDef.aBoolean762 = false;
            objectDef.aBoolean769 = false;
        }
        if (i == 2478) {
            objectDef.name = "Zamorak altar";
            objectDef.description = "Pray for the power of the mighty god Zamorak".getBytes();
            objectDef.objectWidth = 4;
            objectDef.objectHeight = 4;
            objectDef.modelArray = new int[1];
            objectDef.modelArray[0] = 8408;
            objectDef.hasActions = true;
            objectDef.aBoolean767 = true;
            objectDef.aBoolean762 = false;
            objectDef.aBoolean769 = false;
        }
        if (i == 2479) {
            objectDef.name = "Bandos altar";
            objectDef.description = "Pray for the power of the mighty god Bandos".getBytes();
            objectDef.objectWidth = 4;
            objectDef.objectHeight = 4;
            objectDef.modelArray = new int[1];
            objectDef.modelArray[0] = 8409;
            objectDef.hasActions = true;
            objectDef.aBoolean767 = true;
            objectDef.aBoolean762 = false;
            objectDef.aBoolean769 = false;
        }
        if (i == 2480) {
            objectDef.name = "Saradomin altar";
            objectDef.description = "Pray for the power of the mighty god Saradomin".getBytes();
            objectDef.objectWidth = 4;
            objectDef.objectHeight = 4;
            objectDef.modelArray = new int[1];
            objectDef.modelArray[0] = 8410;
            objectDef.hasActions = true;
            objectDef.aBoolean767 = true;
            objectDef.aBoolean762 = false;
            objectDef.aBoolean769 = false;
        }
        if (i == 2481) {
            objectDef.name = "Armadyl altar";
            objectDef.description = "Pray for the power of the mighty god Armadyl".getBytes();
            objectDef.objectWidth = 4;
            objectDef.objectHeight = 4;
            objectDef.modelArray = new int[2];
            objectDef.modelArray[0] = 8411;
            objectDef.modelArray[1] = 8412;
            objectDef.hasActions = true;
            objectDef.aBoolean767 = true;
            objectDef.aBoolean762 = false;
            objectDef.aBoolean769 = false;
        }

// END OF OBJECTS //

        return objectDef;
    }

    private final void setDefaults() {
        modelArray = null;
        anIntArray776 = null;
        name = null;
        description = null;
        modifiedModelColors = null;
        originalModelColors = null;
        objectWidth = 1;
        objectHeight = 1;
        aBoolean767 = true;
        aBoolean757 = true;
        hasActions = false;
        aBoolean762 = false;
        aBoolean769 = false;
        aBoolean764 = false;
        anInt781 = -1;
        anInt775 = 16;
        aByte737 = 0;
        aByte742 = 0;
        actions = null;
        anInt746 = -1;
        anInt758 = -1;
        aBoolean751 = false;
        aBoolean779 = true;
        anInt748 = 128;
        anInt772 = 128;
        anInt740 = 128;
        anInt768 = 0;
        anInt738 = 0;
        anInt745 = 0;
        anInt783 = 0;
        aBoolean736 = false;
        aBoolean766 = false;
        anInt760 = -1;
        anInt774 = -1;
        anInt749 = -1;
        childrenIDs = null;
    }

    public final void method574(OnDemandFetcher class42_sub1, int i) {
        if (modelArray == null) {
            return;
        }
        for (int j = 0; j < modelArray.length; j++) {
            class42_sub1.method560(modelArray[j] & 0xffff, 0, false);
        }

        while (i >= 0) {
            aBoolean763 = !aBoolean763;
        }
    }

    public static void nullLoader() {
        mruNodes1 = null;
        mruNodes2 = null;
        streamIndices = null;
        cache = null;
    }

    public static void unpackConfig(JagexArchive class44) {
        stream = new Stream(class44.getDataForName("loc.dat"));
        Stream class30_sub2_sub2 = new Stream(class44.getDataForName("loc.idx"));
        totalObjects = class30_sub2_sub2.readUnsignedWord();
        streamIndices = new int[totalObjects + 30000];
        int i = 2;
        for (int j = 0; j < totalObjects; j++) {
            streamIndices[j] = i;
            i += class30_sub2_sub2.readUnsignedWord();
        }

        cache = new ObjectDef[20];
        for (int k = 0; k < 20; k++) {
            cache[k] = new ObjectDef();
        }

    }

    public final boolean method577(int i, boolean flag) {
        if (!flag) {
            throw new NullPointerException();
        }
        if (anIntArray776 == null) {
            if (modelArray == null) {
                return true;
            }
            if (i != 10) {
                return true;
            }
            boolean flag1 = true;
            for (int k = 0; k < modelArray.length; k++) {
                flag1 &= Model.isCached(modelArray[k] & 0xffff);
            }

            return flag1;
        }
        for (int j = 0; j < anIntArray776.length; j++) {
            if (anIntArray776[j] == i) {
                return Model.isCached(modelArray[j] & 0xffff);
            }
        }

        return true;
    }

    public final Model method578(int i, int j, int k, int l, int i1, int j1, int k1) {
        Model class30_sub2_sub4_sub6 = method581(0, i, k1, j);
        if (class30_sub2_sub4_sub6 == null) {
            return null;
        }
        if (aBoolean762 || aBoolean769) {
            class30_sub2_sub4_sub6 = new Model(aBoolean762, -819, aBoolean769, class30_sub2_sub4_sub6);
        }
        if (aBoolean762) {
            int l1 = (k + l + i1 + j1) / 4;
            for (int i2 = 0; i2 < class30_sub2_sub4_sub6.vertexCount; i2++) {
                int j2 = class30_sub2_sub4_sub6.vertexX[i2];
                int k2 = class30_sub2_sub4_sub6.vertexZ[i2];
                int l2 = k + ((l - k) * (j2 + 64)) / 128;
                int i3 = j1 + ((i1 - j1) * (j2 + 64)) / 128;
                int j3 = l2 + ((i3 - l2) * (k2 + 64)) / 128;
                class30_sub2_sub4_sub6.vertexY[i2] += j3 - l1;
            }

            class30_sub2_sub4_sub6.method467();
        }
        return class30_sub2_sub4_sub6;
    }

    public final boolean method579(boolean flag) {
        if (modelArray == null) {
            return true;
        }
        boolean flag1 = true;
        for (int i = 0; i < modelArray.length; i++) {
            flag1 &= Model.isCached(modelArray[i] & 0xffff);
        }

        if (!flag) {
            throw new NullPointerException();
        } else {
            return flag1;
        }
    }

    public final ObjectDef method580(boolean flag) {
        if (!flag) {
            throw new NullPointerException();
        }
        int i = -1;
        if (anInt774 != -1) {
            VarBit class37 = VarBit.cache[anInt774];
            int j = class37.configId;
            int k = class37.leastSignificantBit;
            int l = class37.mostSignificantBit;
            int i1 = Client.anIntArray1232[l - k];
            i = clientInstance.sessionSettings[j] >> k & i1;
        } else if (anInt749 != -1) {
            i = clientInstance.sessionSettings[anInt749];
        }
        if (i < 0 || i >= childrenIDs.length || childrenIDs[i] == -1) {
            return null;
        } else {
            return forID(childrenIDs[i]);
        }
    }

    private final Model method581(int i, int j, int k, int l) {
        Model class30_sub2_sub4_sub6 = null;
        long l1;
        if (anIntArray776 == null) {
            if (j != 10) {
                return null;
            }
            l1 = (long) ((type << 6) + l) + ((long) (k + 1) << 32);
            Model class30_sub2_sub4_sub6_1 = (Model) mruNodes2.get(l1);
            if (class30_sub2_sub4_sub6_1 != null) {
                return class30_sub2_sub4_sub6_1;
            }
            if (modelArray == null) {
                return null;
            }
            boolean flag1 = aBoolean751 ^ (l > 3);
            int k1 = modelArray.length;
            for (int i2 = 0; i2 < k1; i2++) {
                int l2 = modelArray[i2];
                if (flag1) {
                    l2 += 0x10000;
                }
                class30_sub2_sub4_sub6 = (Model) mruNodes1.get(l2);
                if (class30_sub2_sub4_sub6 == null) {
                    class30_sub2_sub4_sub6 = Model.getModel(l2 & 0xffff);
                    if (class30_sub2_sub4_sub6 == null) {
                        return null;
                    }
                    if (flag1) {
                        class30_sub2_sub4_sub6.method477();
                    }
                    mruNodes1.put(class30_sub2_sub4_sub6, l2);
                }
                if (k1 > 1) {
                    model[i2] = class30_sub2_sub4_sub6;
                }
            }

            if (k1 > 1) {
                class30_sub2_sub4_sub6 = new Model(k1, model);
            }
        } else {
            int i1 = -1;
            for (int j1 = 0; j1 < anIntArray776.length; j1++) {
                if (anIntArray776[j1] != j) {
                    continue;
                }
                i1 = j1;
                break;
            }

            if (i1 == -1) {
                return null;
            }
            l1 = (long) ((type << 6) + (i1 << 3) + l) + ((long) (k + 1) << 32);
            Model class30_sub2_sub4_sub6_2 = (Model) mruNodes2.get(l1);
            if (class30_sub2_sub4_sub6_2 != null) {
                return class30_sub2_sub4_sub6_2;
            }
            int j2 = modelArray[i1];
            boolean flag3 = aBoolean751 ^ (l > 3);
            if (flag3) {
                j2 += 0x10000;
            }
            class30_sub2_sub4_sub6 = (Model) mruNodes1.get(j2);
            if (class30_sub2_sub4_sub6 == null) {
                class30_sub2_sub4_sub6 = Model.getModel(j2 & 0xffff);
                if (class30_sub2_sub4_sub6 == null) {
                    return null;
                }
                if (flag3) {
                    class30_sub2_sub4_sub6.method477();
                }
                mruNodes1.put(class30_sub2_sub4_sub6, j2);
            }
        }
        boolean flag;
        if (anInt748 != 128 || anInt772 != 128 || anInt740 != 128) {
            flag = true;
        } else {
            flag = false;
        }
        boolean flag2;
        if (anInt738 != 0 || anInt745 != 0 || anInt783 != 0) {
            flag2 = true;
        } else {
            flag2 = false;
        }
        Model class30_sub2_sub4_sub6_3 = new Model(9, modifiedModelColors == null, AnimationFrame.isNullFrame(k), l == 0 && k == -1 && !flag && !flag2, class30_sub2_sub4_sub6);
        if (k != -1) {
            class30_sub2_sub4_sub6_3.createBones((byte) -71);
            class30_sub2_sub4_sub6_3.applyTransform(k, 40542);
            class30_sub2_sub4_sub6_3.triangleSkin = null;
            class30_sub2_sub4_sub6_3.vertexSkin = null;
        }
        while (l-- > 0) {
            class30_sub2_sub4_sub6_3.rotateBy90(360);
        }
        if (modifiedModelColors != null) {
            for (int k2 = 0; k2 < modifiedModelColors.length; k2++) {
                class30_sub2_sub4_sub6_3.reColour(modifiedModelColors[k2], originalModelColors[k2]);
            }

        }
        if (flag) {
            class30_sub2_sub4_sub6_3.scaleT(anInt748, anInt740, anInt772);
        }
        if (flag2) {
            class30_sub2_sub4_sub6_3.method475(anInt738, anInt745, anInt783);
        }
        class30_sub2_sub4_sub6_3.light(64 + aByte737, 768 + aByte742 * 5, -50, -10, -50, !aBoolean769);
        if (anInt760 == 1) {
            class30_sub2_sub4_sub6_3.anInt1654 = ((Animable) (class30_sub2_sub4_sub6_3)).modelHeight;
        }
        mruNodes2.put(class30_sub2_sub4_sub6_3, l1);
        if (i != 0) {
            anInt743 = 422;
        }
        return class30_sub2_sub4_sub6_3;
    }

    private final void readValues(Stream stream) {
        int i = -1;
        label0:
        do {
            int j;
            do {
                j = stream.readUnsignedByte();
                if (j == 0) {
                    break label0;
                }
                if (j == 1) {
                    int k = stream.readUnsignedByte();
                    if (k > 0) {
                        if (modelArray == null || lowMem) {
                            anIntArray776 = new int[k];
                            modelArray = new int[k];
                            for (int k1 = 0; k1 < k; k1++) {
                                modelArray[k1] = stream.readUnsignedWord();
                                anIntArray776[k1] = stream.readUnsignedByte();
                            }

                        } else {
                            stream.currentOffset += k * 3;
                        }
                    }
                } else if (j == 2) {
                    name = stream.readString();
                } else if (j == 3) {
                    description = stream.readBytes();
                } else if (j == 5) {
                    int l = stream.readUnsignedByte();
                    if (l > 0) {
                        if (modelArray == null || lowMem) {
                            anIntArray776 = null;
                            modelArray = new int[l];
                            for (int l1 = 0; l1 < l; l1++) {
                                modelArray[l1] = stream.readUnsignedWord();
                            }

                        } else {
                            stream.currentOffset += l * 2;
                        }
                    }
                } else if (j == 14) {
                    objectWidth = stream.readUnsignedByte();
                } else if (j == 15) {
                    objectHeight = stream.readUnsignedByte();
                } else if (j == 17) {
                    aBoolean767 = false;
                } else if (j == 18) {
                    aBoolean757 = false;
                } else if (j == 19) {
                    i = stream.readUnsignedByte();
                    if (i == 1) {
                        hasActions = true;
                    }
                } else if (j == 21) {
                    aBoolean762 = true;
                } else if (j == 22) {
                    aBoolean769 = true;
                } else if (j == 23) {
                    aBoolean764 = true;
                } else if (j == 24) {
                    anInt781 = stream.readUnsignedWord();
                    if (anInt781 == 65535) {
                        anInt781 = -1;
                    }
                } else if (j == 28) {
                    anInt775 = stream.readUnsignedByte();
                } else if (j == 29) {
                    aByte737 = stream.readSignedByte();
                } else if (j == 39) {
                    aByte742 = stream.readSignedByte();
                } else if (j >= 30 && j < 39) {
                    if (actions == null) {
                        actions = new String[5];
                    }
                    actions[j - 30] = stream.readString();
                    if (actions[j - 30].equalsIgnoreCase("hidden")) {
                        actions[j - 30] = null;
                    }
                } else if (j == 40) {
                    int i1 = stream.readUnsignedByte();
                    modifiedModelColors = new int[i1];
                    originalModelColors = new int[i1];
                    for (int i2 = 0; i2 < i1; i2++) {
                        modifiedModelColors[i2] = stream.readUnsignedWord();
                        originalModelColors[i2] = stream.readUnsignedWord();
                    }

                } else if (j == 60) {
                    anInt746 = stream.readUnsignedWord();
                } else if (j == 62) {
                    aBoolean751 = true;
                } else if (j == 64) {
                    aBoolean779 = false;
                } else if (j == 65) {
                    anInt748 = stream.readUnsignedWord();
                } else if (j == 66) {
                    anInt772 = stream.readUnsignedWord();
                } else if (j == 67) {
                    anInt740 = stream.readUnsignedWord();
                } else if (j == 68) {
                    anInt758 = stream.readUnsignedWord();
                } else if (j == 69) {
                    anInt768 = stream.readUnsignedByte();
                } else if (j == 70) {
                    anInt738 = stream.readSignedWord();
                } else if (j == 71) {
                    anInt745 = stream.readSignedWord();
                } else if (j == 72) {
                    anInt783 = stream.readSignedWord();
                } else if (j == 73) {
                    aBoolean736 = true;
                } else if (j == 74) {
                    aBoolean766 = true;
                } else {
                    if (j != 75) {
                        continue;
                    }
                    anInt760 = stream.readUnsignedByte();
                }
                continue label0;
            } while (j != 77);
            anInt774 = stream.readUnsignedWord();
            if (anInt774 == 65535) {
                anInt774 = -1;
            }
            anInt749 = stream.readUnsignedWord();
            if (anInt749 == 65535) {
                anInt749 = -1;
            }
            int j1 = stream.readUnsignedByte();
            childrenIDs = new int[j1 + 1];
            for (int j2 = 0; j2 <= j1; j2++) {
                childrenIDs[j2] = stream.readUnsignedWord();
                if (childrenIDs[j2] == 65535) {
                    childrenIDs[j2] = -1;
                }
            }

        } while (true);
        if (i == -1) {
            hasActions = false;
            if (modelArray != null && (anIntArray776 == null || anIntArray776[0] == 10)) {
                hasActions = true;
            }
            if (actions != null) {
                hasActions = true;
            }
        }
        if (aBoolean766) {
            aBoolean767 = false;
            aBoolean757 = false;
        }
        if (anInt760 == -1) {
            anInt760 = aBoolean767 ? 1 : 0;
        }
    }
}
