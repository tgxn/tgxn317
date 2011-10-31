package map;






public class MapFunctions extends DrawingArea {
    
    public int anIntArray202[];
    public int anInt203;
    public int anInt204;
    public int anInt205;
    public int anInt206;
    public int anInt207;
    public int anInt208;
    
    public MapFunctions(int i, int j) {
        anIntArray202 = new int[i * j];
        anInt203 = anInt207 = i;
        anInt204 = anInt208 = j;
        anInt205 = anInt206 = 0;
    }

    public MapFunctions(Class6 class6, String s, int i) {
        ByteVector class1_sub1_sub2 = new ByteVector(class6.method76(s + ".dat", null));
        ByteVector class1_sub1_sub2_1 = new ByteVector(class6.method76("index.dat", null));
        class1_sub1_sub2_1.currentOffset = class1_sub1_sub2.getShort();
        anInt207 = class1_sub1_sub2_1.getShort();
        anInt208 = class1_sub1_sub2_1.getShort();
        int j = class1_sub1_sub2_1.getUnsignedByte();
        int ai[] = new int[j];
        for (int k = 0; k < j - 1; k++) {
            ai[k + 1] = class1_sub1_sub2_1.getShortInt();
            if (ai[k + 1] == 0) {
                ai[k + 1] = 1;
            }
        }
        for (int l = 0; l < i; l++) {
            class1_sub1_sub2_1.currentOffset += 2;
            class1_sub1_sub2.currentOffset += class1_sub1_sub2_1.getShort() * class1_sub1_sub2_1.getShort();
            class1_sub1_sub2_1.currentOffset++;
        }
        anInt205 = class1_sub1_sub2_1.getUnsignedByte();
        anInt206 = class1_sub1_sub2_1.getUnsignedByte();
        anInt203 = class1_sub1_sub2_1.getShort();
        anInt204 = class1_sub1_sub2_1.getShort();
        int i1 = class1_sub1_sub2_1.getUnsignedByte();
        int j1 = anInt203 * anInt204;
        anIntArray202 = new int[j1];
        if (i1 == 0) {
            for (int k1 = 0; k1 < j1; k1++) {
                anIntArray202[k1] = ai[class1_sub1_sub2.getUnsignedByte()];
            }
        } else if (i1 == 1) {
            for (int l1 = 0; l1 < anInt203; l1++) {
                for (int i2 = 0; i2 < anInt204; i2++) {
                    anIntArray202[l1 + i2 * anInt203] = ai[class1_sub1_sub2.getUnsignedByte()];
                }
            }
        }
    }

    public void method45() {
        DrawingArea.initializeDrawingArea(anIntArray202, anInt203, anInt204);
    }

    public void method46(int i, int j) {
        i += anInt205; //width
        j += anInt206; //height
        int k = i + j * DrawingArea.width;
        int l = 0;
        int i1 = anInt204;
        int j1 = anInt203;
        int k1 = DrawingArea.width - j1;
        int l1 = 0;
        if (j < DrawingArea.topY) {
            int i2 = DrawingArea.topY - j;
            i1 -= i2;
            j = DrawingArea.topY;
            l += i2 * j1;
            k += i2 * DrawingArea.width;
        }
        if (j + i1 > DrawingArea.bottomY) {
            i1 -= (j + i1) - DrawingArea.bottomY;
        }
        if (i < DrawingArea.topX) {
            int j2 = DrawingArea.topX - i;
            j1 -= j2;
            i = DrawingArea.topX;
            l += j2;
            k += j2;
            l1 += j2;
            k1 += j2;
        }
        if (i + j1 > DrawingArea.bottomX) {
            int k2 = (i + j1) - DrawingArea.bottomX;
            j1 -= k2;
            l1 += k2;
            k1 += k2;
        }
        if (j1 <= 0 || i1 <= 0) {
            return;
        } else {
            method47(DrawingArea.pixels, anIntArray202, 0, l, k, j1, i1, k1, l1);
            return;
        }
    }

    public void method47(int ai[], int ai1[], int i, int j, int k, int l, int i1, int j1, int k1) {
        int l1 = -(l >> 2);
        l = -(l & 3);
        for (int i2 = -i1; i2 < 0; i2++) {
            for (int j2 = l1; j2 < 0; j2++) {
                i = ai1[j++];
                if (i != 0) {
                    ai[k++] = i;
                } else {
                    k++;
                }
                i = ai1[j++];
                if (i != 0) {
                    ai[k++] = i;
                } else {
                    k++;
                }
                i = ai1[j++];
                if (i != 0) {
                    ai[k++] = i;
                } else {
                    k++;
                }
                i = ai1[j++];
                if (i != 0) {
                    ai[k++] = i;
                } else {
                    k++;
                }
            }
            for (int k2 = l; k2 < 0; k2++) {
                i = ai1[j++];
                if (i != 0) {
                    ai[k++] = i;
                } else {
                    k++;
                }
            }
            k += j1;
            j += k1;
        }
    }

    public void method48(int i, int j) {
        i += anInt205;
        j += anInt206;
        int k = i + j * DrawingArea.width;
        int l = 0;
        int i1 = anInt204;
        int j1 = anInt203;
        int k1 = DrawingArea.width - j1;
        int l1 = 0;
        if (j < DrawingArea.topY) {
            int i2 = DrawingArea.topY - j;
            i1 -= i2;
            j = DrawingArea.topY;
            l += i2 * j1;
            k += i2 * DrawingArea.width;
        }
        if (j + i1 > DrawingArea.bottomY) {
            i1 -= (j + i1) - DrawingArea.bottomY;
        }
        if (i < DrawingArea.topX) {
            int j2 = DrawingArea.topX - i;
            j1 -= j2;
            i = DrawingArea.topX;
            l += j2;
            k += j2;
            l1 += j2;
            k1 += j2;
        }
        if (i + j1 > DrawingArea.bottomX) {
            int k2 = (i + j1) - DrawingArea.bottomX;
            j1 -= k2;
            l1 += k2;
            k1 += k2;
        }
        if (j1 <= 0 || i1 <= 0) {
            return;
        } else {
            method49(DrawingArea.pixels, anIntArray202, l, k, j1, i1, k1, l1);
            return;
        }
    }

    public void method49(int ai[], int ai1[], int i, int j, int k, int l, int i1, int j1) {
        int k1 = -(k >> 2);
        k = -(k & 3);
        for (int l1 = -l; l1 < 0; l1++) {
            for (int i2 = k1; i2 < 0; i2++) {
                ai[j++] = ai1[i++];
                ai[j++] = ai1[i++];
                ai[j++] = ai1[i++];
                ai[j++] = ai1[i++];
            }
            for (int j2 = k; j2 < 0; j2++) {
                ai[j++] = ai1[i++];
            }
            j += i1;
            i += j1;
        }
    }
}
