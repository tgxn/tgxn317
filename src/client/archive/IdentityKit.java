package client.archive;

// Mostly Renamed - gamerx - 15.11.11

import client.model.Model;
import client.data.Stream;
import client.data.JagexArchive;

/**
 * Identity kit configuration loader.
 *
 * @knownas IDK, Class38
 * @author gamerx
 */
public class IdentityKit {

    public static int length;
    public static IdentityKit identityCache[];
    public int bodyPartID;
    public int bodyModelIDs[];
    public int recolourOriginal[];
    public int recolourTarget[];
    public int headModelIDs[] = {
        -1, -1, -1, -1, -1
    };
    public boolean notSelectable;

    private IdentityKit() {
        bodyPartID = -1;
        recolourOriginal = new int[6];
        recolourTarget = new int[6];
        notSelectable = false;
    }

    /**
     * Unpack identity archive.
     *
     * @param jagexArchive The archive instance.
     */
    public static void unpackConfig(JagexArchive jagexArchive) {
        Stream idkData = new Stream(jagexArchive.getDataForName("idk.dat"));
        length = idkData.readUnsignedWord();
        if (identityCache == null) {
            identityCache = new IdentityKit[length];
        }
        for (int j = 0; j < length; j++) {
            if (identityCache[j] == null) {
                identityCache[j] = new IdentityKit();
            }
            identityCache[j].readValues(idkData);
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
                bodyPartID = stream.readUnsignedByte();
            } else if (packetType == 2) {
                int bodyModelLength = stream.readUnsignedByte();
                bodyModelIDs = new int[bodyModelLength];
                for (int pos = 0; pos < bodyModelLength; pos++) {
                    bodyModelIDs[pos] = stream.readUnsignedWord();
                }

            } else if (packetType == 3) {
                notSelectable = true;
            } else if (packetType >= 40 && packetType < 50) {
                recolourOriginal[packetType - 40] = stream.readUnsignedWord();
            } else if (packetType >= 50 && packetType < 60) {
                recolourTarget[packetType - 50] = stream.readUnsignedWord();
            } else if (packetType >= 60 && packetType < 70) {
                headModelIDs[packetType - 60] = stream.readUnsignedWord();
            } else {
                //@TODO Error handling.
                System.out.println("Error unrecognised config code: " + packetType);
            }
        } while (true);
    }

    public boolean isBodyDownloaded() {
        if (bodyModelIDs == null) {
            return true;
        }
        boolean isDownloaded = true;
        for (int ptr = 0; ptr < bodyModelIDs.length; ptr++) {
            if (!Model.isCached(bodyModelIDs[ptr])) {
                isDownloaded = false;
            }
        }
        return isDownloaded;
    }

    public Model getBodyModel() {
        if (bodyModelIDs == null) {
            return null;
        }
        Model subModels[] = new Model[bodyModelIDs.length];
        for (int modelPointer = 0; modelPointer < bodyModelIDs.length; modelPointer++) {
            subModels[modelPointer] = Model.getModel(bodyModelIDs[modelPointer]);
        }
        Model model;
        if (subModels.length == 1) {
            model = subModels[0];
        } else {
            model = new Model(subModels.length, subModels);
        }
        for (int colourPointer = 0; colourPointer < 6; colourPointer++) {
            if (recolourOriginal[colourPointer] == 0) {
                break;
            }
            model.reColour(recolourOriginal[colourPointer], recolourTarget[colourPointer]);
        }
        return model;
    }

    public boolean isHeadDownloaded() {
        boolean isDownloaded = true;
        for (int ptr = 0; ptr < 5; ptr++) {
            if (headModelIDs[ptr] != -1 && !Model.isCached(headModelIDs[ptr])) {
                isDownloaded = false;
            }
        }
        return isDownloaded;
    }

    public Model getHeadModel() {
        Model subModels[] = new Model[5];
        int modelPtr = 0;
        for (int idPtr = 0; idPtr < 5; idPtr++) {
            if (headModelIDs[idPtr] != -1) {
                subModels[modelPtr++] = Model.getModel(headModelIDs[idPtr]);
            }
        }
        Model model = new Model(modelPtr, subModels);
        for (int colourPointer = 0; colourPointer < 6; colourPointer++) {
            if (recolourOriginal[colourPointer] == 0) {
                break;
            }
            model.reColour(recolourOriginal[colourPointer], recolourTarget[colourPointer]);
        }
        return model;
    }
}
