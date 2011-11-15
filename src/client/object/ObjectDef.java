package client.object;

// Mostly Renamed - gamerx - 15.11.11

import client.Client;
import client.model.Model;
import client.util.MemCache;
import client.archive.VarBit;
import client.ondemand.OnDemandFetcher;
import client.animation.AnimationFrame;
import client.animation.Entity;
import client.data.JagexArchive;
import client.data.Stream;

/**
 * Object definition.
 * Loads Objects, and sets up variables.
 *
 * Class46
 * @author gamerx
 */
public final class ObjectDef {
    
    public boolean aBoolean736;
    private byte objBrightness;
    private int offsetX;
    public String objName;
    private int modelSizeY;
    private static Model[] modelParts = new Model[4];
    private byte objContrast;
    public int sizeX;
    private int offsetH;
    public int mapFunctionID;
    private int[] originalModelColors;
    private int modelSizeX;
    public int configID;
    private boolean aBoolean751;
    public static boolean lowMem;
    private static Stream locStream;
    public int type;
    private static int[] streamIndices;
    private static int totalObjects;
    public boolean aBoolean757;
    public int mapSceneID;
    public int configObjectIDs[];
    public int anInt760;
    public int objectHeight;
    public boolean adjustToTerrain;
    //private boolean aBoolean763;
    public boolean aBoolean764;
    public static Client clientInstance;
    public boolean isSolidObject;
    public boolean aBoolean767;
    public int anInt768;
    private boolean nonFlatShading;
    //private int anInt770;
    private static int cacheIndex;
    private int modelSizeH;
    private int objectModelIDs[];
    public int currentConfigID;
    public int anInt775;
    private int types[];
    public byte objDesc[];
    public boolean hasActions;
    public boolean aBoolean779;
    public static MemCache modelCache2 = new MemCache(30);
    public int animationID;
    private static ObjectDef objectCache[];
    private int offsetY;
    private int modifiedModelColors[];
    public static MemCache modelCache = new MemCache(500);
    public String objActions[];
    
    ObjectDef() {
        type = -1;
    }
    
    public static ObjectDef forID(int id) {
        for (int pos = 0; pos < 20; pos++) {
            if (objectCache[pos].type == id) {
                return objectCache[pos];
            }
        }
        cacheIndex = (cacheIndex + 1) % 20;
        ObjectDef objectDef = objectCache[cacheIndex];
        locStream.currentOffset = streamIndices[id];
        objectDef.type = id;
        objectDef.setDefaults();
        objectDef.readValues(locStream);

// START OF OBJECTS //

        if (id == 2476) {
            objectDef.objName = "Godwars entrance";
            objectDef.objDesc = "No way of coming back!".getBytes();
            objectDef.sizeX = 4;
            objectDef.objectHeight = 4;
            objectDef.objectModelIDs = new int[1];
            objectDef.objectModelIDs[0] = 8406;
            objectDef.hasActions = true;
            objectDef.aBoolean767 = true;
            objectDef.adjustToTerrain = false;
            objectDef.nonFlatShading = false;
        }
        if (id == 2477) {
            objectDef.objName = "Obelisk";
            objectDef.objDesc = "A summoning obelisk.".getBytes();
            objectDef.sizeX = 2;
            objectDef.objectHeight = 2;
            objectDef.objectModelIDs = new int[1];
            objectDef.objectModelIDs[0] = 8407;
            objectDef.hasActions = true;
            objectDef.aBoolean767 = true;
            objectDef.adjustToTerrain = false;
            objectDef.nonFlatShading = false;
        }
        if (id == 2478) {
            objectDef.objName = "Zamorak altar";
            objectDef.objDesc = "Pray for the power of the mighty god Zamorak".getBytes();
            objectDef.sizeX = 4;
            objectDef.objectHeight = 4;
            objectDef.objectModelIDs = new int[1];
            objectDef.objectModelIDs[0] = 8408;
            objectDef.hasActions = true;
            objectDef.aBoolean767 = true;
            objectDef.adjustToTerrain = false;
            objectDef.nonFlatShading = false;
        }
        if (id == 2479) {
            objectDef.objName = "Bandos altar";
            objectDef.objDesc = "Pray for the power of the mighty god Bandos".getBytes();
            objectDef.sizeX = 4;
            objectDef.objectHeight = 4;
            objectDef.objectModelIDs = new int[1];
            objectDef.objectModelIDs[0] = 8409;
            objectDef.hasActions = true;
            objectDef.aBoolean767 = true;
            objectDef.adjustToTerrain = false;
            objectDef.nonFlatShading = false;
        }
        if (id == 2480) {
            objectDef.objName = "Saradomin altar";
            objectDef.objDesc = "Pray for the power of the mighty god Saradomin".getBytes();
            objectDef.sizeX = 4;
            objectDef.objectHeight = 4;
            objectDef.objectModelIDs = new int[1];
            objectDef.objectModelIDs[0] = 8410;
            objectDef.hasActions = true;
            objectDef.aBoolean767 = true;
            objectDef.adjustToTerrain = false;
            objectDef.nonFlatShading = false;
        }
        if (id == 2481) {
            objectDef.objName = "Armadyl altar";
            objectDef.objDesc = "Pray for the power of the mighty god Armadyl".getBytes();
            objectDef.sizeX = 4;
            objectDef.objectHeight = 4;
            objectDef.objectModelIDs = new int[2];
            objectDef.objectModelIDs[0] = 8411;
            objectDef.objectModelIDs[1] = 8412;
            objectDef.hasActions = true;
            objectDef.aBoolean767 = true;
            objectDef.adjustToTerrain = false;
            objectDef.nonFlatShading = false;
        }

// END OF OBJECTS //

        return objectDef;
    }

    private void setDefaults() {
        objectModelIDs = null;
        types = null;
        objName = null;
        objDesc = null;
        modifiedModelColors = null;
        originalModelColors = null;
        sizeX = 1;
        objectHeight = 1;
        aBoolean767 = true;
        aBoolean757 = true;
        hasActions = false;
        adjustToTerrain = false;
        nonFlatShading = false;
        aBoolean764 = false;
        animationID = -1;
        anInt775 = 16;
        objBrightness = 0;
        objContrast = 0;
        objActions = null;
        mapFunctionID = -1;
        mapSceneID = -1;
        aBoolean751 = false;
        aBoolean779 = true;
        modelSizeX = 128;
        modelSizeH = 128;
        modelSizeY = 128;
        anInt768 = 0;
        offsetX = 0;
        offsetH = 0;
        offsetY = 0;
        aBoolean736 = false;
        isSolidObject = false;
        anInt760 = -1;
        currentConfigID = -1;
        configID = -1;
        configObjectIDs = null;
    }

    /**
     * Update ondemandfetcher.
     *
     * @param onDemandFetcher Instance to update.
     */
    public final void method574(OnDemandFetcher onDemandFetcher) {
        if (objectModelIDs == null) {
            return;
        }
        for (int pos = 0; pos < objectModelIDs.length; pos++) {
            onDemandFetcher.method560(objectModelIDs[pos] & 0xffff, 0, false);
        }
    }

    /**
     * Loads nulls into variables for shutdown.
     */
    public static void nullLoader() {
        modelCache = null;
        modelCache2 = null;
        streamIndices = null;
        objectCache = null;
    }

    /*
     * Unpacks configurations for each object.
     */
    public static void unpackConfig(JagexArchive jagexArchive) {
        locStream = new Stream(jagexArchive.getDataForName("loc.dat"));
        Stream index = new Stream(jagexArchive.getDataForName("loc.idx"));
        totalObjects = index.readUnsignedWord();
        streamIndices = new int[totalObjects + 30000];
        int i = 2;
        for (int pos = 0; pos < totalObjects; pos++) {
            streamIndices[pos] = i;
            i += index.readUnsignedWord();
        }
        objectCache = new ObjectDef[20];
        for (int pos = 0; pos < 20; pos++) {
            objectCache[pos] = new ObjectDef();
        }

    }

    /**
     *
     *
     * @param i
     * @return
     */
    public final boolean method577(int i) {
        if (types == null) {
            if (objectModelIDs == null) {
                return true;
            }
            if (i != 10) {
                return true;
            }
            boolean flag1 = true;
            for (int k = 0; k < objectModelIDs.length; k++) {
                flag1 &= Model.isCached(objectModelIDs[k] & 0xffff);
            }
            return flag1;
        }
        for (int j = 0; j < types.length; j++) {
            if (types[j] == i) {
                return Model.isCached(objectModelIDs[j] & 0xffff);
            }
        }
        return true;
    }

    public final Model renderObject(int i, int j, int k, int l, int i1, int j1, int k1) {
        Model model = getAnimatedModel(i, k1, j);
        if (model == null) {
            return null;
        }
        if (adjustToTerrain || nonFlatShading) {
            model = new Model(adjustToTerrain, -819, nonFlatShading, model);
        }
        if (adjustToTerrain) {
            int l1 = (k + l + i1 + j1) / 4;
            for (int i2 = 0; i2 < model.vertexCount; i2++) {
                int vertexX = model.vertexX[i2];
                int vertexY = model.vertexZ[i2];
                int l2 = k + ((l - k) * (vertexX + 64)) / 128;
                int i3 = j1 + ((i1 - j1) * (vertexX + 64)) / 128;
                int j3 = l2 + ((i3 - l2) * (vertexY + 64)) / 128;
                model.vertexY[i2] += j3 - l1;
            }

            model.normalise();
        }
        return model;
    }

    public final boolean isCached() {
        if (objectModelIDs == null) {
            return true;
        }
        boolean cached = true;
        for (int i = 0; i < objectModelIDs.length; i++) {
            cached &= Model.isCached(objectModelIDs[i] & 0xffff);
        }
        return cached;
    }

    public final ObjectDef getConfig() {
        int theConfID = -1;
        if (currentConfigID != -1) {
            VarBit varBit = VarBit.cache[currentConfigID];
            int confID = varBit.configId;
            int leastSigBit = varBit.leastSignificantBit;
            int mostSigBit = varBit.mostSignificantBit;
            int maxValue = Client.BITFIELD_MAX_VALUE[mostSigBit - leastSigBit];
            theConfID = clientInstance.sessionSettings[confID] >> leastSigBit & maxValue;
        } else if (theConfID != -1) {
            theConfID = clientInstance.sessionSettings[theConfID];
        }
        if (theConfID < 0 || theConfID >= configObjectIDs.length || configObjectIDs[theConfID] == -1) {
            return null;
        } else {
            return forID(configObjectIDs[theConfID]);
        }
    }

    private Model getAnimatedModel(int objectType, int currentAnimID, int face) {
        Model model = null;
        long hash;
        if (types == null) {
            if (objectType != 10) {
                return null;
            }
            hash = (long) ((type << 6) + face) + ((long) (currentAnimID + 1) << 32);
            Model modelReturn = (Model) modelCache2.get(hash);
            if (modelReturn != null) {
                return modelReturn;
            }
            if (objectModelIDs == null) {
                return null;
            }
            boolean flag1 = aBoolean751 ^ (face > 3);
            int k1 = objectModelIDs.length;
            for (int i2 = 0; i2 < k1; i2++) {
                int l2 = objectModelIDs[i2];
                if (flag1) {
                    l2 += 0x10000;
                }
                model = (Model) modelCache.get(l2);
                if (model == null) {
                    model = Model.getModel(l2 & 0xffff);
                    if (model == null) {
                        return null;
                    }
                    if (flag1) {
                        model.method477();
                    }
                    modelCache.put(model, l2);
                }
                if (k1 > 1) {
                    modelParts [i2] = model;
                }
            }

            if (k1 > 1) {
                model = new Model(k1, modelParts );
            }
        } else {
            int i1 = -1;
            for (int j1 = 0; j1 < types.length; j1++) {
                if (types[j1] != objectType) {
                    continue;
                }
                i1 = j1;
                break;
            }

            if (i1 == -1) {
                return null;
            }
            hash = (long) ((type << 6) + (i1 << 3) + face) + ((long) (currentAnimID + 1) << 32);
            Model class30_sub2_sub4_sub6_2 = (Model) modelCache2.get(hash);
            if (class30_sub2_sub4_sub6_2 != null) {
                return class30_sub2_sub4_sub6_2;
            }
            int j2 = objectModelIDs[i1];
            boolean flag3 = aBoolean751 ^ (face > 3);
            if (flag3) {
                j2 += 0x10000;
            }
            model = (Model) modelCache.get(j2);
            if (model == null) {
                model = Model.getModel(j2 & 0xffff);
                if (model == null) {
                    return null;
                }
                if (flag3) {
                    model.method477();
                }
                modelCache.put(model, j2);
            }
        }
        boolean flag;
        if (modelSizeX != 128 || modelSizeH != 128 || modelSizeY != 128) {
            flag = true;
        } else {
            flag = false;
        }
        boolean flag2;
        if (offsetX != 0 || offsetH != 0 || offsetY != 0) {
            flag2 = true;
        } else {
            flag2 = false;
        }
        Model class30_sub2_sub4_sub6_3 = new Model(9, modifiedModelColors == null, AnimationFrame.isNullFrame(currentAnimID), face == 0 && currentAnimID == -1 && !flag && !flag2, model);
        if (currentAnimID != -1) {
            class30_sub2_sub4_sub6_3.createBones((byte) -71);
            class30_sub2_sub4_sub6_3.applyTransform(currentAnimID, 40542);
            class30_sub2_sub4_sub6_3.triangleSkin = null;
            class30_sub2_sub4_sub6_3.vertexSkin = null;
        }
        while (face-- > 0) {
            class30_sub2_sub4_sub6_3.rotateBy90(360);
        }
        if (modifiedModelColors != null) {
            for (int k2 = 0; k2 < modifiedModelColors.length; k2++) {
                class30_sub2_sub4_sub6_3.reColour(modifiedModelColors[k2], originalModelColors[k2]);
            }

        }
        if (flag) {
            class30_sub2_sub4_sub6_3.scaleT(modelSizeX, modelSizeY, modelSizeH);
        }
        if (flag2) {
            class30_sub2_sub4_sub6_3.method475(offsetX, offsetH, offsetY);
        }
        class30_sub2_sub4_sub6_3.light(64 + objBrightness, 768 + objContrast * 5, -50, -10, -50, !nonFlatShading);
        if (anInt760 == 1) {
            class30_sub2_sub4_sub6_3.anInt1654 = ((Entity) (class30_sub2_sub4_sub6_3)).modelHeight;
        }
        modelCache2.put(class30_sub2_sub4_sub6_3, hash);
        return class30_sub2_sub4_sub6_3;
    }

    private void readValues(Stream stream) {
        int objHasActions = -1;
        readingStream:
        do {
            int packetType;
            do {
                packetType = stream.readUnsignedByte();
                if (packetType == 0) {
                    break readingStream;
                }
                if (packetType == 1) {
                    int dataSize = stream.readUnsignedByte();
                    if (dataSize > 0) {
                        if (objectModelIDs == null || lowMem) {
                            types = new int[dataSize];
                            objectModelIDs = new int[dataSize];
                            for (int pos = 0; pos < dataSize; pos++) {
                                objectModelIDs[pos] = stream.readUnsignedWord();
                                types[pos] = stream.readUnsignedByte();
                            }

                        } else {
                            stream.currentOffset += dataSize * 3;
                        }
                    }
                } else if (packetType == 2) {
                    objName = stream.readString();
                } else if (packetType == 3) {
                    objDesc = stream.readBytes();
                } else if (packetType == 5) {
                    int objModels = stream.readUnsignedByte();
                    if (objModels > 0) {
                        if (objectModelIDs == null || lowMem) {
                            types = null;
                            objectModelIDs = new int[objModels];
                            for (int pos = 0; pos < objModels; pos++) {
                                objectModelIDs[pos] = stream.readUnsignedWord();
                            }

                        } else {
                            stream.currentOffset += objModels * 2;
                        }
                    }
                } else if (packetType == 14) {
                    sizeX = stream.readUnsignedByte();
                } else if (packetType == 15) {
                    objectHeight = stream.readUnsignedByte();
                } else if (packetType == 17) {
                    aBoolean767 = false;
                } else if (packetType == 18) {
                    aBoolean757 = false;
                } else if (packetType == 19) {
                    objHasActions = stream.readUnsignedByte();
                    if (objHasActions == 1) {
                        hasActions = true;
                    }
                } else if (packetType == 21) {
                    adjustToTerrain = true;
                } else if (packetType == 22) {
                    nonFlatShading = true;
                } else if (packetType == 23) {
                    aBoolean764 = true;
                } else if (packetType == 24) {
                    animationID = stream.readUnsignedWord();
                    if (animationID == 65535) {
                        animationID = -1;
                    }
                } else if (packetType == 28) {
                    anInt775 = stream.readUnsignedByte();
                } else if (packetType == 29) {
                    objBrightness = stream.readSignedByte();
                } else if (packetType == 39) {
                    objContrast = stream.readSignedByte();
                } else if (packetType >= 30 && packetType < 39) {
                    if (objActions == null) {
                        objActions = new String[5];
                    }
                    objActions[packetType - 30] = stream.readString();
                    if (objActions[packetType - 30].equalsIgnoreCase("hidden")) {
                        objActions[packetType - 30] = null;
                    }
                } else if (packetType == 40) {
                    int objColorsSize = stream.readUnsignedByte();
                    modifiedModelColors = new int[objColorsSize];
                    originalModelColors = new int[objColorsSize];
                    for (int pos = 0; pos < objColorsSize; pos++) {
                        modifiedModelColors[pos] = stream.readUnsignedWord();
                        originalModelColors[pos] = stream.readUnsignedWord();
                    }

                } else if (packetType == 60) {
                    mapFunctionID = stream.readUnsignedWord();
                } else if (packetType == 62) {
                    aBoolean751 = true;
                } else if (packetType == 64) {
                    aBoolean779 = false;
                } else if (packetType == 65) {
                    modelSizeX = stream.readUnsignedWord();
                } else if (packetType == 66) {
                    modelSizeH = stream.readUnsignedWord();
                } else if (packetType == 67) {
                    modelSizeY = stream.readUnsignedWord();
                } else if (packetType == 68) {
                    mapSceneID = stream.readUnsignedWord();
                } else if (packetType == 69) {
                    anInt768 = stream.readUnsignedByte();
                } else if (packetType == 70) {
                    offsetX = stream.readSignedWord();
                } else if (packetType == 71) {
                    offsetH = stream.readSignedWord();
                } else if (packetType == 72) {
                    offsetY = stream.readSignedWord();
                } else if (packetType == 73) {
                    aBoolean736 = true;
                } else if (packetType == 74) {
                    isSolidObject = true;
                } else {
                    if (packetType != 75) {
                        continue;
                    }
                    anInt760 = stream.readUnsignedByte();
                }
                continue readingStream;
            } while (packetType != 77);
            currentConfigID = stream.readUnsignedWord();
            if (currentConfigID == 65535) {
                currentConfigID = -1;
            }
            configID = stream.readUnsignedWord();
            if (configID == 65535) {
                configID = -1;
            }
            int objConfigsSize = stream.readUnsignedByte();
            configObjectIDs = new int[objConfigsSize + 1];
            for (int pos = 0; pos <= objConfigsSize; pos++) {
                configObjectIDs[pos] = stream.readUnsignedWord();
                if (configObjectIDs[pos] == 65535) {
                    configObjectIDs[pos] = -1;
                }
            }

        } while (true);
        if (objHasActions == -1) {
            hasActions = false;
            if (objectModelIDs != null && (types == null || types[0] == 10)) {
                hasActions = true;
            }
            if (objActions != null) {
                hasActions = true;
            }
        }
        if (isSolidObject) {
            aBoolean767 = false;
            aBoolean757 = false;
        }
        if (anInt760 == -1) {
            anInt760 = aBoolean767 ? 1 : 0;
        }
    }
}
