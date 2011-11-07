package client;


public class SpotAnim {
    
    public static SpotAnim cache[];
    public int id;
    public int modelID;
    public int animationID;
    public Animation animation;
    public int originalModelColours[];
    public int modifiedModelColours[];
    public int resizeXY;
    public int resizeZ;
    public int rotation;
    public int modelBrightness;
    public int modelShadow;
    public static MemCache memCache = new MemCache(30);
    
    public SpotAnim() {
        animationID = -1;
        originalModelColours = new int[6];
        modifiedModelColours = new int[6];
        resizeXY = 128;
        resizeZ = 128;
    }
        
    public static void unpackConfig(JagexArchive jagexArchive) {
        Stream stream = new Stream(jagexArchive.getDataForName("spotanim.dat"));

        int newSpotAnimCount = 5;
        /* Yarnova: Raise this by one every time you add a new GFX */

        int length = stream.readUnsignedWord() + newSpotAnimCount;
        if (cache == null) {
            cache = new SpotAnim[length];
        }
        for (int j = 0; j < length; j++) {
            if (cache[j] == null) {
                cache[j] = new SpotAnim();
            }
            cache[j].id = j;
            int originalDataID = -1;
            SpotAnim spotAnim = cache[j];
            switch (j) {

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
                spotAnim.animationID = cache[originalDataID].animationID;
                if (Animation.animCache != null) {
                    spotAnim.animation = Animation.animCache[spotAnim.animationID];
                }
                spotAnim.resizeXY = cache[originalDataID].resizeXY;
                spotAnim.resizeZ = cache[originalDataID].resizeZ;
                spotAnim.rotation = cache[originalDataID].rotation;
                spotAnim.modelBrightness = cache[originalDataID].modelBrightness;
                spotAnim.modelShadow = cache[originalDataID].modelShadow;
                spotAnim.originalModelColours = cache[originalDataID].originalModelColours;
                spotAnim.modifiedModelColours = cache[originalDataID].modifiedModelColours;
            } else {
                spotAnim.readValues(stream);
            }
        }

    }

    public void readValues(Stream stream) {
        do {
            int i = stream.readUnsignedByte();
            if (i == 0) {
                return;
            }
            if (i == 1) {
                modelID = stream.readUnsignedWord();
            } else if (i == 2) {
                animationID = stream.readUnsignedWord();
                if (Animation.animCache != null) {
                    animation = Animation.animCache[animationID];
                }
            } else if (i == 4) {
                resizeXY = stream.readUnsignedWord();
            } else if (i == 5) {
                resizeZ = stream.readUnsignedWord();
            } else if (i == 6) {
                rotation = stream.readUnsignedWord();
            } else if (i == 7) {
                modelBrightness = stream.readUnsignedByte();
            } else if (i == 8) {
                modelShadow = stream.readUnsignedByte();
            } else if (i >= 40 && i < 50) {
                originalModelColours[i - 40] = stream.readUnsignedWord();
            } else if (i >= 50 && i < 60) {
                modifiedModelColours[i - 50] = stream.readUnsignedWord();
            } else {
                System.out.println("Error unrecognised spotanim config code: " + i);
            }
        } while (true);
    }

    public Model getModel() {
        Model model = (Model) memCache.get(id);
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
        memCache.put(model, id);
        return model;
    }
}
