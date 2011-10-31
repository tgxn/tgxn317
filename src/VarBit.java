
public class VarBit {
    
    private static int anInt644;
    public static int anInt645;
    public static VarBit cache[];
    public String aString647;
    public int anInt648;
    public int anInt649;
    public int anInt650;
    public boolean aBoolean651;
    public int anInt652;
    public int anInt653;
    
    public VarBit() {
        aBoolean651 = false;
        anInt652 = -1;
    }
        
    public static void unpackConfig(Class44 class44) {
        Stream class30_sub2_sub2 = new Stream(class44.method571("varbit.dat", null));
        anInt645 = class30_sub2_sub2.readUnsignedWord();
        if (cache == null) {
            cache = new VarBit[anInt645];
        }
        for (int j = 0; j < anInt645; j++) {
            if (cache[j] == null) {
                cache[j] = new VarBit();
            }
            cache[j].readValues(class30_sub2_sub2, j);
            if (cache[j].aBoolean651) {
                Varp.cache[cache[j].anInt648].aBoolean713 = true;
            }
        }

        if (class30_sub2_sub2.currentOffset != class30_sub2_sub2.buffer.length) {
            System.out.println("varbit load mismatch");
        }
    }

    public void readValues(Stream stream, int i) {
        do {
            int j = stream.readUnsignedByte();
            if (j == 0) {
                return;
            }
            if (j == 1) {
                anInt648 = stream.readUnsignedWord();
                anInt649 = stream.readUnsignedByte();
                anInt650 = stream.readUnsignedByte();
            } else if (j == 10) {
                aString647 = stream.readString();
            } else if (j == 2) {
                aBoolean651 = true;
            } else if (j == 3) {
                anInt652 = stream.readDWord();
            } else if (j == 4) {
                anInt653 = stream.readDWord();
            } else {
                System.out.println("Error unrecognised config code: " + j);
            }
        } while (true);
    }
}
