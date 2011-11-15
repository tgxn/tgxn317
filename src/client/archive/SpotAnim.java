package client.archive;

// Mostly Renamed - gamerx - 15.11.11

import client.data.JagexArchive;
import client.util.MemCache;
import client.model.Model;
import client.data.Stream;

/**
 * Unpack spot animation cache and models.
 *
 * @knownas Class23
 * @author gamerx
 */
public class SpotAnim {
    
    public static SpotAnim spotAnimCache[];
    public int dataID;
    public int modelID;
    public int animationID;
    public Sequence animationSequence;
    public int originalModelColours[];
    public int modifiedModelColours[];
    public int resizeXY;
    public int resizeZ;
    public int rotation;
    public int modelBrightness;
    public int modelShadow;
    public static MemCache modelCache = new MemCache(30);
    
    private SpotAnim() {
        animationID = -1;
        originalModelColours = new int[6];
        modifiedModelColours = new int[6];
        resizeXY = 128;
        resizeZ = 128;
    }

    /**
     * Unpack spot animation archive.
     *
     * @param jagexArchive The archive instance.
     */
    public static void unpackConfig(JagexArchive jagexArchive) {
        Stream spotAnimData = new Stream(jagexArchive.getDataForName("spotanim.dat"));

        int newSpotAnimCount = 5;
        /* Yarnova: Raise this by one every time you add a new GFX */

        int length = spotAnimData.readUnsignedWord() + newSpotAnimCount;
        if (spotAnimCache == null) {
            spotAnimCache = new SpotAnim[length];
        }
        for (int pos = 0; pos < length; pos++) {
            if (spotAnimCache[pos] == null) {
                spotAnimCache[pos] = new SpotAnim();
            }
            spotAnimCache[pos].dataID = pos;
            int originalDataID = -1;
            SpotAnim spotAnim = spotAnimCache[pos];
            switch (pos) {

                case 642: /* gfx ID veng */
                    originalDataID = 601; // you can change this , this id makes it spin ;)
                    spotAnim.modelID = 1828; /* Model ID 16828*/
                    break;
                case 643: // This is the GFX ID
                    originalDataID = 602; // makes it spin =]
                    spotAnim.modelID = 9950; // This is the Model ID! (Original was 19086)
                    break;
                case 590: //Bandos
                    originalDataID = 290;
                    spotAnim.modelID = 10555;
                    break;
                case 592: //Zamorak
                    originalDataID = 291;
                    spotAnim.modelID = 10556;
                    break;
                case 594: // Armadyl
                    originalDataID = 292;
                    spotAnim.modelID = 10557;
                    break;


                default:
                    originalDataID = -1;
                    break;
            }
            if (originalDataID != -1) {
                spotAnim.animationID = spotAnimCache[originalDataID].animationID;
                if (Sequence.animCache != null) {
                    spotAnim.animationSequence = Sequence.animCache[spotAnim.animationID];
                }
                spotAnim.resizeXY = spotAnimCache[originalDataID].resizeXY;
                spotAnim.resizeZ = spotAnimCache[originalDataID].resizeZ;
                spotAnim.rotation = spotAnimCache[originalDataID].rotation;
                spotAnim.modelBrightness = spotAnimCache[originalDataID].modelBrightness;
                spotAnim.modelShadow = spotAnimCache[originalDataID].modelShadow;
                spotAnim.originalModelColours = spotAnimCache[originalDataID].originalModelColours;
                spotAnim.modifiedModelColours = spotAnimCache[originalDataID].modifiedModelColours;
            } else {
                spotAnim.readValues(spotAnimData);
            }
        }
    }

    /**
     * Read values from the stream.
     *
     * @param stream The stram to read from.
     */
    public void readValues(Stream stream) {
        do {
            int packetType = stream.readUnsignedByte();
            if (packetType == 0) {
                return;
            }
            if (packetType == 1) {
                modelID = stream.readUnsignedWord();
            } else if (packetType == 2) {
                animationID = stream.readUnsignedWord();
                if (Sequence.animCache != null) {
                    animationSequence = Sequence.animCache[animationID];
                }
            } else if (packetType == 4) {
                resizeXY = stream.readUnsignedWord();
            } else if (packetType == 5) {
                resizeZ = stream.readUnsignedWord();
            } else if (packetType == 6) {
                rotation = stream.readUnsignedWord();
            } else if (packetType == 7) {
                modelBrightness = stream.readUnsignedByte();
            } else if (packetType == 8) {
                modelShadow = stream.readUnsignedByte();
            } else if (packetType >= 40 && packetType < 50) {
                originalModelColours[packetType - 40] = stream.readUnsignedWord();
            } else if (packetType >= 50 && packetType < 60) {
                modifiedModelColours[packetType - 50] = stream.readUnsignedWord();
            } else {
                System.out.println("Error unrecognised spotanim config code: " + packetType);
            }
        } while (true);
    }

    public Model getModel() {
        Model model = (Model) modelCache.get(dataID);
        if (model != null) {
            return model;
        }
        model = Model.getModel(modelID);
        if (model == null) {
            return null;
        }
        for (int i = 0; i < 6; i++) {
            if (originalModelColours[0] != 0) {
                model.reColour(originalModelColours[i], modifiedModelColours[i]);
            }
        }
        modelCache.put(model, dataID);
        return model;
    }
}
