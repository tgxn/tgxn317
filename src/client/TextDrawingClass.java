package client;


import java.util.Random;

public final class TextDrawingClass extends DrawingArea {
    
    private boolean aBoolean1484;
    private int anInt1485;
    private int anInt1486;
    private int anInt1487;
    private boolean aBoolean1488;
    private int anInt1489;
    private boolean aBoolean1490;
    byte aByteArrayArray1491[][];
    int anIntArray1492[];
    int anIntArray1493[];
    int anIntArray1494[];
    int anIntArray1495[];
    public int anIntArray1496[];
    public int anInt1497;
    Random aRandom1498;
    boolean aBoolean1499;
    
    public TextDrawingClass(boolean flag, String s, Class44 class44) {
        aBoolean1484 = false;
        anInt1485 = 445;
        anInt1486 = -471;
        anInt1487 = -471;
        aBoolean1488 = false;
        anInt1489 = 3;
        aBoolean1490 = false;
        aByteArrayArray1491 = new byte[256][];
        anIntArray1492 = new int[256];
        anIntArray1493 = new int[256];
        anIntArray1494 = new int[256];
        anIntArray1495 = new int[256];
        anIntArray1496 = new int[256];
        aRandom1498 = new Random();
        aBoolean1499 = false;
        Stream class30_sub2_sub2 = new Stream(class44.method571(s + ".dat", null));
        Stream class30_sub2_sub2_1 = new Stream(class44.method571("index.dat", null));
        class30_sub2_sub2_1.currentOffset = class30_sub2_sub2.readUnsignedWord() + 4;
        int k = class30_sub2_sub2_1.readUnsignedByte();
        if (k > 0) {
            class30_sub2_sub2_1.currentOffset += 3 * (k - 1);
        }
        for (int l = 0; l < 256; l++) {
            int j = l;
            anIntArray1494[l] = class30_sub2_sub2_1.readUnsignedByte();
            anIntArray1495[l] = class30_sub2_sub2_1.readUnsignedByte();
            int i1 = anIntArray1492[l] = class30_sub2_sub2_1.readUnsignedWord();
            int j1 = anIntArray1493[l] = class30_sub2_sub2_1.readUnsignedWord();
            int k1 = class30_sub2_sub2_1.readUnsignedByte();
            int l1 = i1 * j1;
            aByteArrayArray1491[l] = new byte[l1];
            if (k1 == 0) {
                for (int i2 = 0; i2 < l1; i2++) {
                    aByteArrayArray1491[l][i2] = class30_sub2_sub2.readSignedByte();
                }
            } else if (k1 == 1) {
                for (int j2 = 0; j2 < i1; j2++) {
                    for (int l2 = 0; l2 < j1; l2++) {
                        aByteArrayArray1491[l][j2 + l2 * i1] = class30_sub2_sub2.readSignedByte();
                    }
                }
            }
            if (j1 > anInt1497 && l < 128) {
                anInt1497 = j1;
            }
            anIntArray1494[l] = 1;
            anIntArray1496[l] = i1 + 2;
            int k2 = 0;
            for (int i3 = j1 / 7; i3 < j1; i3++) {
                k2 += aByteArrayArray1491[l][i3 * i1];
            }
            if (k2 <= j1 / 7) {
                anIntArray1496[l]--;
                anIntArray1494[l] = 0;
            }
            k2 = 0;
            for (int j3 = j1 / 7; j3 < j1; j3++) {
                k2 += aByteArrayArray1491[l][(i1 - 1) + j3 * i1];
            }
            if (k2 <= j1 / 7) {
                anIntArray1496[l]--;
            }
        }
        if (flag) {
            anIntArray1496[32] = anIntArray1496[73];
            return;
        } else {
            anIntArray1496[32] = anIntArray1496[105];
            return;
        }
    }

    public void method380(String s, int i, int j, int k) {
        method385(j, s, k, 822, i - method384(s));
    }

    public void drawText(int i, String s, int k, int l) {
        method385(i, s, k, 822, l - method384(s) / 2);
    }

    public void drawCenteredText(int i, int j, String s, int l, boolean shouldshadow) {
        drawShadowedText(shouldshadow, j - getTextWidth(s) / 2, i, s, l);
    }

    public int getTextWidth(String s) {
        if (s == null) {
            return 0;
        }
        int j = 0;
        for (int k = 0; k < s.length(); k++) {
            if (s.charAt(k) == '@' && k + 4 < s.length() && s.charAt(k + 4) == '@') {
                k += 4;
            } else {
                j += anIntArray1496[s.charAt(k)];
            }
        }
        return j;
    }

    public int method384(String s) {
        if (s == null) {
            return 0;
        }
        int j = 0;
        for (int k = 0; k < s.length(); k++) {
            j += anIntArray1496[s.charAt(k)];
        }

        return j;
    }

    public void method385(int i, String s, int j, int k, int l) {
        if (s == null) {
            return;
        }
        j -= anInt1497;
        for (int i1 = 0; i1 < s.length(); i1++) {
            char c = s.charAt(i1);
            if (c != ' ') {
                method392(aByteArrayArray1491[c], l + anIntArray1494[c], j + anIntArray1495[c], anIntArray1492[c], anIntArray1493[c], i);
            }
            l += anIntArray1496[c];
        }
        k = 50 / k;
    }

    public void method386(int i, String s, int j, int k, int l) {
        if (s == null) {
            return;
        }
        j -= method384(s) / 2;
        l -= anInt1497;
        for (int i1 = 0; i1 < s.length(); i1++) {
            char c = s.charAt(i1);
            if (c != ' ') {
                method392(aByteArrayArray1491[c], j + anIntArray1494[c], l + anIntArray1495[c] + (int) (Math.sin((double) i1 / 2D + (double) k / 5D) * 5D), anIntArray1492[c], anIntArray1493[c], i);
            }
            j += anIntArray1496[c];
        }
    }
    
    public void method387(int i, String s, int j, int k, int l) {
        if (s == null) {
            return;
        }
        i -= method384(s) / 2;
        k -= anInt1497;
        for (int i1 = 0; i1 < s.length(); i1++) {
            char c = s.charAt(i1);
            if (c != ' ') {
                method392(aByteArrayArray1491[c], i + anIntArray1494[c] + (int) (Math.sin((double) i1 / 5D + (double) j / 5D) * 5D), k + anIntArray1495[c] + (int) (Math.sin((double) i1 / 3D + (double) j / 5D) * 5D), anIntArray1492[c], anIntArray1493[c], l);
            }
            i += anIntArray1496[c];
        }
    }

    public void method388(int i, String s, int j, int k, int l, int i1) {
        if (s == null) {
            return;
        }
        double d = 7D - (double) i / 8D;
        if (d < 0.0D) {
            d = 0.0D;
        }
        l -= method384(s) / 2;
        k -= anInt1497;
        for (int k1 = 0; k1 < s.length(); k1++) {
            char c = s.charAt(k1);
            if (c != ' ') {
                method392(aByteArrayArray1491[c], l + anIntArray1494[c], k + anIntArray1495[c] + (int) (Math.sin((double) k1 / 1.5D + (double) j) * d), anIntArray1492[c], anIntArray1493[c], i1);
            }
            l += anIntArray1496[c];
        }
    }

    public void drawShadowedText(boolean shouldshadow, int i, int j, String s, int k) {
        aBoolean1499 = false;
        int l = i;
        if (s == null) {
            return;
        }
        k -= anInt1497;
        for (int i1 = 0; i1 < s.length(); i1++) {
            if (s.charAt(i1) == '@' && i1 + 4 < s.length() && s.charAt(i1 + 4) == '@') {
                int j1 = getColorByName(s.substring(i1 + 1, i1 + 4));
                if (j1 != -1) {
                    j = j1;
                }
                i1 += 4;
            } else {
                char c = s.charAt(i1);
                if (c != ' ') {
                    if (shouldshadow) {
                        method392(aByteArrayArray1491[c], i + anIntArray1494[c] + 1, k + anIntArray1495[c] + 1, anIntArray1492[c], anIntArray1493[c], 0);
                    }
                    method392(aByteArrayArray1491[c], i + anIntArray1494[c], k + anIntArray1495[c], anIntArray1492[c], anIntArray1493[c], j);
                }
                i += anIntArray1496[c];
            }
        }
        if (aBoolean1499) {
            DrawingArea.method339(k + (int) ((double) anInt1497 * 0.69999999999999996D), 0x800000, i - l, l);
        }
    }

    public void method390(boolean flag, int i, int j, String s, int k, int l, int i1) {
        if (s == null) {
            return;
        }
        aRandom1498.setSeed(k);
        int j1 = 192 + (aRandom1498.nextInt() & 0x1f);
        i1 -= anInt1497;
        l = 10 / l;
        for (int k1 = 0; k1 < s.length(); k1++) {
            if (s.charAt(k1) == '@' && k1 + 4 < s.length() && s.charAt(k1 + 4) == '@') {
                int l1 = getColorByName(s.substring(k1 + 1, k1 + 4));
                if (l1 != -1) {
                    j = l1;
                }
                k1 += 4;
            } else {
                char c = s.charAt(k1);
                if (c != ' ') {
                    if (flag) {
                        method394(192, i + anIntArray1494[c] + 1, aByteArrayArray1491[c], anIntArray1492[c], i1 + anIntArray1495[c] + 1, anIntArray1493[c], 0);
                    }
                    method394(j1, i + anIntArray1494[c], aByteArrayArray1491[c], anIntArray1492[c], i1 + anIntArray1495[c], anIntArray1493[c], j);
                }
                i += anIntArray1496[c];
                if ((aRandom1498.nextInt() & 3) == 0) {
                    i++;
                }
            }
        }
    }

    public int getColorByName(String s) {
        if (s.equals("red")) {
            return 0xff0000;
        }
        if (s.equals("gre")) {
            return 65280;
        }
        if (s.equals("blu")) {
            return 255;
        }
        if (s.equals("yel")) {
            return 0xffff00;
        }
        if (s.equals("cya")) {
            return 65535;
        }
        if (s.equals("mag")) {
            return 0xff00ff;
        }
        if (s.equals("whi")) {
            return 0xffffff;
        }
        if (s.equals("bla")) {
            return 0;
        }
        if (s.equals("lre")) {
            return 0xff9040;
        }
        if (s.equals("dre")) {
            return 0x800000;
        }
        if (s.equals("dbl")) {
            return 128;
        }
        if (s.equals("or1")) {
            return 0xffb000;
        }
        if (s.equals("or2")) {
            return 0xff7000;
        }
        if (s.equals("or3")) {
            return 0xff3000;
        }
        if (s.equals("gr1")) {
            return 0xc0ff00;
        }
        if (s.equals("gr2")) {
            return 0x80ff00;
        }
        if (s.equals("gr3")) {
            return 0x40ff00;
        }
        if (s.equals("xxx")) {
            return 0x33CCFF;
        }
        if (s.equals("str")) {
            aBoolean1499 = true;
        }
        if (s.equals("end")) {
            aBoolean1499 = false;
        }
        return -1;
    }

    private void method392(byte abyte0[], int i, int j, int k, int l, int i1) {
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
            l -= ((j + l) - DrawingArea.bottomY);
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
            int l2 = ((i + k) - DrawingArea.bottomX);
            k -= l2;
            l1 += l2;
            k1 += l2;
        }
        if (k <= 0 || l <= 0) {
            return;
        } else {
            method393(DrawingArea.pixels, abyte0, i1, i2, j1, k, l, k1, l1);
            return;
        }
    }

    private void method393(int ai[], byte abyte0[], int i, int j, int k, int l, int i1,
            int j1, int k1) {
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

    private void method394(int i, int j, byte abyte0[], int k, int l, int i1, int j1) {
        int k1 = j + l * DrawingArea.width;
        int l1 = DrawingArea.width - k;
        int i2 = 0;
        int j2 = 0;
        if (l < DrawingArea.topY) {
            int k2 = DrawingArea.topY - l;
            i1 -= k2;
            l = DrawingArea.topY;
            j2 += k2 * k;
            k1 += k2 * DrawingArea.width;
        }
        if (l + i1 >= DrawingArea.bottomY) {
            i1 -= ((l + i1) - DrawingArea.bottomY);
        }
        if (j < DrawingArea.topX) {
            int l2 = DrawingArea.topX - j;
            k -= l2;
            j = DrawingArea.topX;
            j2 += l2;
            k1 += l2;
            i2 += l2;
            l1 += l2;
        }
        if (j + k >= DrawingArea.bottomX) {
            int i3 = ((j + k) - DrawingArea.bottomX);
            k -= i3;
            i2 += i3;
            l1 += i3;
        }
        if (k <= 0 || i1 <= 0) {
            return;
        }
        method395(abyte0, i1, k1, DrawingArea.pixels, 520, j2, k, i2, l1, j1, i);
    }

    private void method395(byte abyte0[], int i, int j, int ai[], int k, int l, int i1, int j1, int k1, int l1, int i2) {
        k = 98 / k;
        l1 = ((l1 & 0xff00ff) * i2 & 0xff00ff00) + ((l1 & 0xff00) * i2 & 0xff0000) >> 8;
        i2 = 256 - i2;
        for (int j2 = -i; j2 < 0; j2++) {
            for (int k2 = -i1; k2 < 0; k2++) {
                if (abyte0[l++] != 0) {
                    int l2 = ai[j];
                    ai[j++] = (((l2 & 0xff00ff) * i2 & 0xff00ff00) + ((l2 & 0xff00) * i2 & 0xff0000) >> 8) + l1;
                } else {
                    j++;
                }
            }
            j += k1;
            l += j1;
        }

    }
}
