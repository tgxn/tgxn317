package client;


public class Varp {
    
    private static boolean aBoolean698 = true;
    public static int anInt700;
    public static Varp cache[];
    public static int anInt702;
    public static int anIntArray703[];
    public String aString704;
    public int anInt705;
    public int anInt706;
    public boolean aBoolean707;
    public boolean aBoolean708;
    public int anInt709;
    public boolean aBoolean710;
    public int anInt711;
    public int anInt712;
    public boolean aBoolean713;
    public int anInt714;
    
    public Varp() {
        aBoolean707 = false;
        aBoolean708 = true;
        aBoolean710 = false;
        aBoolean713 = false;
        anInt714 = -1;
    }
    
    public static void unpackConfig(int i, StreamLoader class44) {
        Stream class30_sub2_sub2 = new Stream(class44.getDataForName("varp.dat"));
        anInt702 = 0;
        anInt700 = class30_sub2_sub2.readUnsignedWord();
        if (cache == null) {
            cache = new Varp[anInt700];
        }
        if (anIntArray703 == null) {
            anIntArray703 = new int[anInt700];
        }
        for (int j = 0; j < anInt700; j++) {
            if (cache[j] == null) {
                cache[j] = new Varp();
            }
            cache[j].readValues(class30_sub2_sub2, j);
        }

        if (i != 0) {
            aBoolean698 = !aBoolean698;
        }
        if (class30_sub2_sub2.currentOffset != class30_sub2_sub2.buffer.length) {
            System.out.println("varptype load mismatch");
        }
    }

    public void readValues(Stream stream, int i) {
        do {
            int j = stream.readUnsignedByte();
            if (j == 0) {
                return;
            }
            if (j == 1) {
                anInt705 = stream.readUnsignedByte();
            } else if (j == 2) {
                anInt706 = stream.readUnsignedByte();
            } else if (j == 3) {
                aBoolean707 = true;
                anIntArray703[anInt702++] = i;
            } else if (j == 4) {
                aBoolean708 = false;
            } else if (j == 5) {
                anInt709 = stream.readUnsignedWord();
            } else if (j == 6) {
                aBoolean710 = true;
            } else if (j == 7) {
                anInt711 = stream.readDWord();
            } else if (j == 8) {
                anInt712 = 1;
                aBoolean713 = true;
            } else if (j == 10) {
                aString704 = stream.readString();
            } else if (j == 11) {
                aBoolean713 = true;
            } else if (j == 12) {
                anInt714 = stream.readDWord();
            } else if (j == 13) {
                anInt712 = 2;
            } else {
                System.out.println("Error unrecognised config code: " + j);
            }
        } while (true);
    }
}
