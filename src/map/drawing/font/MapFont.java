package map.drawing.font;

import java.util.Random;
import map.data.JagexArchive;
import map.data.Stream;
import map.drawing.DrawingArea;

public class MapFont extends DrawingArea {

    public byte aByteArrayArray217[][];
    public int anIntArray218[];
    public int anIntArray219[];
    public int anIntArray220[];
    public int anIntArray221[];
    public int anIntArray222[];
    public int anInt223;
    public Random aRandom224;
    public boolean aBoolean225;

    public MapFont(JagexArchive jagexArchive, String s, boolean flag) {
        aByteArrayArray217 = new byte[256][];
        anIntArray218 = new int[256];
        anIntArray219 = new int[256];
        anIntArray220 = new int[256];
        anIntArray221 = new int[256];
        anIntArray222 = new int[256];
        anInt223 = 0;
        aRandom224 = new Random();
        aBoolean225 = false;
        Stream class1_sub1_sub2 = new Stream(jagexArchive.getDataForName(s + ".dat"));
        Stream class1_sub1_sub2_1 = new Stream(jagexArchive.getDataForName("index.dat"));
        class1_sub1_sub2_1.currentOffset = class1_sub1_sub2.getShort() + 4;
        int j = class1_sub1_sub2_1.getUnsignedByte();
        if (j > 0) {
            class1_sub1_sub2_1.currentOffset += 3 * (j - 1);
        }
        for (int k = 0; k < 256; k++) {
            anIntArray220[k] = class1_sub1_sub2_1.getUnsignedByte();
            anIntArray221[k] = class1_sub1_sub2_1.getUnsignedByte();
            int l = anIntArray218[k] = class1_sub1_sub2_1.getShort();
            int i1 = anIntArray219[k] = class1_sub1_sub2_1.getShort();
            int j1 = class1_sub1_sub2_1.getUnsignedByte();
            int k1 = l * i1;
            aByteArrayArray217[k] = new byte[k1];
            if (j1 == 0) {
                for (int l1 = 0; l1 < k1; l1++) {
                    aByteArrayArray217[k][l1] = class1_sub1_sub2.getByte();
                }
            } else if (j1 == 1) {
                for (int i2 = 0; i2 < l; i2++) {
                    for (int k2 = 0; k2 < i1; k2++) {
                        aByteArrayArray217[k][i2 + k2 * l] = class1_sub1_sub2.getByte();
                    }
                }
            }
            if (i1 > anInt223 && k < 128) {
                anInt223 = i1;
            }
            anIntArray220[k] = 1;
            anIntArray222[k] = l + 2;
            int j2 = 0;
            for (int l2 = i1 / 7; l2 < i1; l2++) {
                j2 += aByteArrayArray217[k][l2 * l];
            }
            if (j2 <= i1 / 7) {
                anIntArray222[k]--;
                anIntArray220[k] = 0;
            }
            j2 = 0;
            for (int i3 = i1 / 7; i3 < i1; i3++) {
                j2 += aByteArrayArray217[k][(l - 1) + i3 * l];
            }
            if (j2 <= i1 / 7) {
                anIntArray222[k]--;
            }
        }
        if (flag) {
            anIntArray222[32] = anIntArray222[73];
        } else {
            anIntArray222[32] = anIntArray222[105];
        }
    }

    public int method52(String s) {
        if (s == null) {
            return 0;
        }
        int i = 0;
        for (int j = 0; j < s.length(); j++) {
            i += anIntArray222[s.charAt(j)];
        }
        return i;
    }

    public void method53(String s, int i, int j, int k) {
        if (s == null) {
            return;
        }
        j -= anInt223;
        for (int l = 0; l < s.length(); l++) {
            char c = s.charAt(l);
            if (c != ' ') {
                method56(aByteArrayArray217[c], i + anIntArray220[c], j + anIntArray221[c], anIntArray218[c], anIntArray219[c], k);
            }
            i += anIntArray222[c];
        }
    }

    public void method54(String s, int i, int j, int k) {
        method53(s, i - method52(s), j, k);
    }

    public void drawText(String s, int i, int j, int k) {
        method53(s, i - method52(s) / 2, j, k);
    }

    public void method56(byte abyte0[], int i, int j, int k, int l, int i1) {
        int j1 = i + j * DrawingArea.width;
        int k1 = DrawingArea.width - k;
        int l1 = 0;
        int i2 = 0;
        if (j < DrawingArea.topY) {
            int j2 = DrawingArea.topY - j;
            l -= j2;
            j = DrawingArea.topY;
            i2 += j2 * k;
            j1 += j2 * DrawingArea.width;
        }
        if (j + l >= DrawingArea.bottomY) {
            l -= ((j + l) - DrawingArea.bottomY) + 1;
        }
        if (i < DrawingArea.topX) {
            int k2 = DrawingArea.topX - i;
            k -= k2;
            i = DrawingArea.topX;
            i2 += k2;
            j1 += k2;
            l1 += k2;
            k1 += k2;
        }
        if (i + k >= DrawingArea.bottomX) {
            int l2 = ((i + k) - DrawingArea.bottomX) + 1;
            k -= l2;
            l1 += l2;
            k1 += l2;
        }
        if (k <= 0 || l <= 0) {
            return;
        } else {
            method57(DrawingArea.pixels, abyte0, i1, i2, j1, k, l, k1, l1);
            return;
        }
    }

    public void method57(int ai[], byte abyte0[], int i, int j, int k, int l, int i1, int j1, int k1) {
        int l1 = -(l >> 2);
        l = -(l & 3);
        for (int i2 = -i1; i2 < 0; i2++) {
            for (int j2 = l1; j2 < 0; j2++) {
                if (abyte0[j++] != 0) {
                    ai[k++] = i;
                } else {
                    k++;
                }
                if (abyte0[j++] != 0) {
                    ai[k++] = i;
                } else {
                    k++;
                }
                if (abyte0[j++] != 0) {
                    ai[k++] = i;
                } else {
                    k++;
                }
                if (abyte0[j++] != 0) {
                    ai[k++] = i;
                } else {
                    k++;
                }
            }
            for (int k2 = l; k2 < 0; k2++) {
                if (abyte0[j++] != 0) {
                    ai[k++] = i;
                } else {
                    k++;
                }
            }
            k += j1;
            j += k1;
        }
    }
}
