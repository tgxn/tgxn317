
public class SpotAnim {
    
    private int anInt400;
    private static boolean aBoolean401 = true;
    public static int anInt402;
    public static SpotAnim cache[];
    public int anInt404;
    public int anInt405;
    public int anInt406;
    public Animation aClass20_407;
    public int anIntArray408[];
    public int anIntArray409[];
    public int anInt410;
    public int anInt411;
    public int anInt412;
    public int anInt413;
    public int anInt414;
    public static MRUNodes aMRUNodes_415 = new MRUNodes(30);
    
    public SpotAnim() {
        anInt400 = 9;
        anInt406 = -1;
        anIntArray408 = new int[6];
        anIntArray409 = new int[6];
        anInt410 = 128;
        anInt411 = 128;
    }
        
    public static void unpackConfig(Class44 class44) {
        Stream class30_sub2_sub2 = new Stream(class44.method571("spotanim.dat", null));

        int newSpotAnimCount = 5;
        /* Yarnova: Raise this by one every time you add a new GFX */

        anInt402 = class30_sub2_sub2.readUnsignedWord() + newSpotAnimCount;
        if (cache == null) {
            cache = new SpotAnim[anInt402];
        }
        for (int j = 0; j < anInt402; j++) {
            if (cache[j] == null) {
                cache[j] = new SpotAnim();
            }
            cache[j].anInt404 = j;
            int originalDataID = -1;
            SpotAnim gfx = cache[j];
            switch (j) {

                case 642: /* gfx ID veng */
                    originalDataID = 601; // you can change this , this id makes it spin ;)
                    gfx.anInt405 = 1828; /* Model ID 16828*/
                    break;
                case 643: // This is the GFX ID
                    originalDataID = 602; // makes it spin =]
                    gfx.anInt405 = 9950; // This is the Model ID! (Original was 19086)
                    break;
                case 590: //Bandos
                    originalDataID = 290;
                    gfx.anInt405 = 10555;
                    break;
                case 592: //Zamorak
                    originalDataID = 291;
                    gfx.anInt405 = 10556;
                    break;
                case 594: // Armadyl
                    originalDataID = 292;
                    gfx.anInt405 = 10557;
                    break;


                default:
                    originalDataID = -1;
                    break;
            }
            if (originalDataID != -1) {
                gfx.anInt406 = cache[originalDataID].anInt406;
                if (Animation.anims != null) {
                    gfx.aClass20_407 = Animation.anims[gfx.anInt406];
                }
                gfx.anInt410 = cache[originalDataID].anInt410;
                gfx.anInt411 = cache[originalDataID].anInt411;
                gfx.anInt412 = cache[originalDataID].anInt412;
                gfx.anInt413 = cache[originalDataID].anInt413;
                gfx.anInt414 = cache[originalDataID].anInt414;
                gfx.anIntArray408 = cache[originalDataID].anIntArray408;
                gfx.anIntArray409 = cache[originalDataID].anIntArray409;
            } else {
                gfx.readValues(class30_sub2_sub2);
            }
        }

    }

    public void readValues(Stream class30_sub2_sub2) {
        do {
            int i = class30_sub2_sub2.readUnsignedByte();
            if (i == 0) {
                return;
            }
            if (i == 1) {
                anInt405 = class30_sub2_sub2.readUnsignedWord();
            } else if (i == 2) {
                anInt406 = class30_sub2_sub2.readUnsignedWord();
                if (Animation.anims != null) {
                    aClass20_407 = Animation.anims[anInt406];
                }
            } else if (i == 4) {
                anInt410 = class30_sub2_sub2.readUnsignedWord();
            } else if (i == 5) {
                anInt411 = class30_sub2_sub2.readUnsignedWord();
            } else if (i == 6) {
                anInt412 = class30_sub2_sub2.readUnsignedWord();
            } else if (i == 7) {
                anInt413 = class30_sub2_sub2.readUnsignedByte();
            } else if (i == 8) {
                anInt414 = class30_sub2_sub2.readUnsignedByte();
            } else if (i >= 40 && i < 50) {
                anIntArray408[i - 40] = class30_sub2_sub2.readUnsignedWord();
            } else if (i >= 50 && i < 60) {
                anIntArray409[i - 50] = class30_sub2_sub2.readUnsignedWord();
            } else {
                System.out.println("Error unrecognised spotanim config code: " + i);
            }
        } while (true);
    }

    public Model getModel() {
        Model class30_sub2_sub4_sub6 = (Model) aMRUNodes_415.insertFromCache(anInt404);
        if (class30_sub2_sub4_sub6 != null) {
            return class30_sub2_sub4_sub6;
        }
        class30_sub2_sub4_sub6 = Model.method462(anInt400, anInt405);
        if (class30_sub2_sub4_sub6 == null) {
            return null;
        }
        for (int i = 0; i < 6; i++) {
            if (anIntArray408[0] != 0) {
                class30_sub2_sub4_sub6.method476(anIntArray408[i], anIntArray409[i]);
            }
        }

        aMRUNodes_415.removeFromCache(class30_sub2_sub4_sub6, anInt404, (byte) 2);
        return class30_sub2_sub4_sub6;
    }
}
