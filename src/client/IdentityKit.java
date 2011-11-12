package client;

// IDK

import client.fileio.JagexArchive;

// http://code.google.com/p/rename317/source/browse/trunk/src/rs2/IdentityKit.java?r=186

public class IdentityKit {

    public static int length;
    public static IdentityKit cache[];
    public int bodyPartID;
    public int bodyModelIDs[];
    public int recolourOriginal[];
    public int recolourTarget[];
    public int headModelIDs[] = {
        -1, -1, -1, -1, -1
    };
    public boolean notSelectable;

    public IdentityKit() {
        bodyPartID = -1;
        recolourOriginal = new int[6];
        recolourTarget = new int[6];
        notSelectable = false;
    }

    public static void unpackConfig(JagexArchive jagexArchive) {
        Stream idkData = new Stream(jagexArchive.getDataForName("idk.dat"));
        length = idkData.readUnsignedWord();
        if (cache == null) {
            cache = new IdentityKit[length];
        }
        for (int j = 0; j < length; j++) {
            if (cache[j] == null) {
                cache[j] = new IdentityKit();
            }
            cache[j].readValues(idkData);
        }
    }

    public void readValues(Stream stream) {
        do {
            int opCode = stream.readUnsignedByte();
            if (opCode == 0) {
                return;
            }
            if (opCode == 1) {
                bodyPartID = stream.readUnsignedByte();
            } else if (opCode == 2) {
                int j = stream.readUnsignedByte();
                bodyModelIDs = new int[j];
                for (int k = 0; k < j; k++) {
                    bodyModelIDs[k] = stream.readUnsignedWord();
                }

            } else if (opCode == 3) {
                notSelectable = true;
            } else if (opCode >= 40 && opCode < 50) {
                recolourOriginal[opCode - 40] = stream.readUnsignedWord();
            } else if (opCode >= 50 && opCode < 60) {
                recolourTarget[opCode - 50] = stream.readUnsignedWord();
            } else if (opCode >= 60 && opCode < 70) {
                headModelIDs[opCode - 60] = stream.readUnsignedWord();
            } else {
                System.out.println("Error unrecognised config code: " + opCode);
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
