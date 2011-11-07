package client;


public class IDK {

    private int anInt654;
    public static int length;
    public static IDK cache[];
    public int anInt657;
    public int anIntArray658[];
    public int anIntArray659[];
    public int anIntArray660[];
    public int anIntArray661[] = {
        -1, -1, -1, -1, -1
    };
    public boolean aBoolean662;

    public IDK() {
        anInt654 = 9;
        anInt657 = -1;
        anIntArray659 = new int[6];
        anIntArray660 = new int[6];
        aBoolean662 = false;
    }

    public static void unpackConfig(JagexArchive class44) {
        Stream class30_sub2_sub2 = new Stream(class44.getDataForName("idk.dat"));
        length = class30_sub2_sub2.readUnsignedWord();
        if (cache == null) {
            cache = new IDK[length];
        }
        for (int j = 0; j < length; j++) {
            if (cache[j] == null) {
                cache[j] = new IDK();
            }
            cache[j].readValues(class30_sub2_sub2);
        }
    }

    public void readValues(Stream stream) {
        do {
            int i = stream.readUnsignedByte();
            if (i == 0) {
                return;
            }
            if (i == 1) {
                anInt657 = stream.readUnsignedByte();
            } else if (i == 2) {
                int j = stream.readUnsignedByte();
                anIntArray658 = new int[j];
                for (int k = 0; k < j; k++) {
                    anIntArray658[k] = stream.readUnsignedWord();
                }

            } else if (i == 3) {
                aBoolean662 = true;
            } else if (i >= 40 && i < 50) {
                anIntArray659[i - 40] = stream.readUnsignedWord();
            } else if (i >= 50 && i < 60) {
                anIntArray660[i - 50] = stream.readUnsignedWord();
            } else if (i >= 60 && i < 70) {
                anIntArray661[i - 60] = stream.readUnsignedWord();
            } else {
                System.out.println("Error unrecognised config code: " + i);
            }
        } while (true);
    }

    public boolean method537() {
        if (anIntArray658 == null) {
            return true;
        }
        boolean flag = true;
        for (int j = 0; j < anIntArray658.length; j++) {
            if (!Model.isCached(anIntArray658[j])) {
                flag = false;
            }
        }

        return flag;
    }

    public Model method538() {
        if (anIntArray658 == null) {
            return null;
        }
        Model aclass30_sub2_sub4_sub6[] = new Model[anIntArray658.length];
        for (int i = 0; i < anIntArray658.length; i++) {
            aclass30_sub2_sub4_sub6[i] = Model.getModel(anIntArray658[i]);
        }

        Model class30_sub2_sub4_sub6;
        if (aclass30_sub2_sub4_sub6.length == 1) {
            class30_sub2_sub4_sub6 = aclass30_sub2_sub4_sub6[0];
        } else {
            class30_sub2_sub4_sub6 = new Model(aclass30_sub2_sub4_sub6.length, aclass30_sub2_sub4_sub6);
        }
        for (int j = 0; j < 6; j++) {
            if (anIntArray659[j] == 0) {
                break;
            }
            class30_sub2_sub4_sub6.reColour(anIntArray659[j], anIntArray660[j]);
        }

        return class30_sub2_sub4_sub6;
    }

    public boolean method539() {
        boolean flag1 = true;
        for (int i = 0; i < 5; i++) {
            if (anIntArray661[i] != -1 && !Model.isCached(anIntArray661[i])) {
                flag1 = false;
            }
        }
        return flag1;
    }

    public Model method540() {
        Model aclass30_sub2_sub4_sub6[] = new Model[5];
        int j = 0;
        for (int k = 0; k < 5; k++) {
            if (anIntArray661[k] != -1) {
                aclass30_sub2_sub4_sub6[j++] = Model.getModel(anIntArray661[k]);
            }
        }
        Model class30_sub2_sub4_sub6 = new Model(j, aclass30_sub2_sub4_sub6);
        for (int l = 0; l < 6; l++) {
            if (anIntArray659[l] == 0) {
                break;
            }
            class30_sub2_sub4_sub6.reColour(anIntArray659[l], anIntArray660[l]);
        }
        return class30_sub2_sub4_sub6;
    }
}
