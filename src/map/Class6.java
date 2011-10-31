package map;






public class Class6 {
    
    public byte aByteArray93[];
    public int anInt94;
    public int anIntArray95[];
    public int anIntArray96[];
    public int anIntArray97[];
    public int anIntArray98[];
    public boolean aBoolean99;

    public Class6(byte abyte0[]) {
        method75(abyte0);
    }

    public void method75(byte abyte0[]) {
        ByteVector class1_sub1_sub2 = new ByteVector(abyte0);
        int i = class1_sub1_sub2.getShortInt();
        int j = class1_sub1_sub2.getShortInt();
        if (j != i) {
            byte abyte1[] = new byte[i];
            Class4.method73(abyte1, i, abyte0, j, 6);
            aByteArray93 = abyte1;
            class1_sub1_sub2 = new ByteVector(aByteArray93);
            aBoolean99 = true;
        } else {
            aByteArray93 = abyte0;
            aBoolean99 = false;
        }
        anInt94 = class1_sub1_sub2.getShort();
        anIntArray95 = new int[anInt94];
        anIntArray96 = new int[anInt94];
        anIntArray97 = new int[anInt94];
        anIntArray98 = new int[anInt94];
        int k = class1_sub1_sub2.currentOffset + anInt94 * 10;
        for (int l = 0; l < anInt94; l++) {
            anIntArray95[l] = class1_sub1_sub2.getInt();
            anIntArray96[l] = class1_sub1_sub2.getShortInt();
            anIntArray97[l] = class1_sub1_sub2.getShortInt();
            anIntArray98[l] = k;
            k += anIntArray97[l];
        }
    }

    public byte[] method76(String s, byte abyte0[]) {
        int i = 0;
        s = s.toUpperCase();
        for (int j = 0; j < s.length(); j++) {
            i = (i * 61 + s.charAt(j)) - 32;
        }

        for (int k = 0; k < anInt94; k++) {
            if (anIntArray95[k] == i) {
                if (abyte0 == null) {
                    abyte0 = new byte[anIntArray96[k]];
                }
                if (!aBoolean99) {
                    Class4.method73(abyte0, anIntArray96[k], aByteArray93, anIntArray97[k], anIntArray98[k]);
                } else {
                    for (int l = 0; l < anIntArray96[k]; l++) {
                        abyte0[l] = aByteArray93[anIntArray98[k] + l];
                    }
                }
                return abyte0;
            }
        }
        return null;
    }
}
