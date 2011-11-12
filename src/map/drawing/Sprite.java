package map.drawing;






import map.drawing.DrawingArea;
import java.awt.*;
import java.awt.image.PixelGrabber;
import map.RSApplet;

public class Sprite extends DrawingArea {
    
    public boolean aBoolean198;
    public int anInt199;
    public byte byteArrayofPixels[];
    public static int anIntArray201[];

    static {
        anIntArray201 = new int[256];
        for (int i = 0; i < 256; i++) {
            int j = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!\"\243$%^&*()-_=+[{]};:'@#~,<.>/?\\| ".indexOf(i);
            if (j == -1) {
                j = 74;
            }
            anIntArray201[i] = j * 9;
        }

    }
    
    public Sprite(int fontPT, RSApplet rsApplet) {
        aBoolean198 = false;
        anInt199 = 0;
        byteArrayofPixels = new byte[0x186a0];
        anInt199 = 855;
        aBoolean198 = false;
        Font font = new Font("Helvetica", 1, fontPT);
        FontMetrics fontmetrics = rsApplet.getFontMetrics(font);

        for (int j = 0; j < 95; j++) {
            method36(font, fontmetrics, "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!\"\243$%^&*()-_=+[{]};:'@#~,<.>/?\\| ".charAt(j), j, false, rsApplet);
        }

        if (aBoolean198) {
            anInt199 = 855;
            aBoolean198 = false;
            Font font1 = new Font("Helvetica", 0, fontPT);
            FontMetrics fontmetrics1 = rsApplet.getFontMetrics(font1);
            for (int k = 0; k < 95; k++) {
                method36(font1, fontmetrics1, "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!\"\243$%^&*()-_=+[{]};:'@#~,<.>/?\\| ".charAt(k), k, false, rsApplet);
            }

            if (!aBoolean198) {
                anInt199 = 855;
                aBoolean198 = false;
                for (int l = 0; l < 95; l++) {
                    method36(font1, fontmetrics1, "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!\"\243$%^&*()-_=+[{]};:'@#~,<.>/?\\| ".charAt(l), l, true, rsApplet);
                }

            }
        }
        byte abyte0[] = new byte[anInt199];
        System.arraycopy(byteArrayofPixels, 0, abyte0, 0, anInt199);
        byteArrayofPixels = abyte0;
    }

    public void method36(Font font, FontMetrics fontmetrics, char c, int i, boolean bool, RSApplet rsApplet) {
        int i_0_ = fontmetrics.charWidth(c);
        int i_1_ = i_0_;
        if (bool) {
            try {
                if (c == '/') {
                    bool = false;
                }
                if (c == 'f' || c == 't' || c == 'w' || c == 'v' || c == 'k' || c == 'x' || c == 'y' || c == 'A' || c == 'V' || c == 'W') {
                    i_0_++;
                }
            } catch (Exception e) {
            }
        }
        int i_2_ = fontmetrics.getMaxAscent();
        int i_3_ = fontmetrics.getMaxAscent() + fontmetrics.getMaxDescent();
        int i_4_ = fontmetrics.getHeight();
        Image image = rsApplet.getGameComponent().createImage(i_0_, i_3_);
        Graphics graphics = image.getGraphics();
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, i_0_, i_3_);
        graphics.setColor(Color.white);
        graphics.setFont(font);
        graphics.drawString(c + "", 0, i_2_);
        if (bool) {
            graphics.drawString(c + "", 1, i_2_);
        }
        int[] is = new int[i_0_ * i_3_];
        PixelGrabber pixelgrabber = new PixelGrabber(image, 0, 0, i_0_, i_3_, is, 0, i_0_);
        try {
            pixelgrabber.grabPixels();
        } catch (Exception e) {
        }
        image.flush();
        int i_5_ = 0;
        int i_6_ = 0;
        int i_7_ = i_0_;
        int i_8_ = i_3_;
        while_0_:
        for (int i_9_ = 0; i_9_ < i_3_; i_9_++) {
            for (int i_10_ = 0; i_10_ < i_0_; i_10_++) {
                int i_11_ = is[i_10_ + i_9_ * i_0_];
                if ((i_11_ & 0xffffff) != 0) {
                    i_6_ = i_9_;
                    break while_0_;
                }
            }
        }
        while_1_:
        for (int i_12_ = 0; i_12_ < i_0_; i_12_++) {
            for (int i_13_ = 0; i_13_ < i_3_; i_13_++) {
                int i_14_ = is[i_12_ + i_13_ * i_0_];
                if ((i_14_ & 0xffffff) != 0) {
                    i_5_ = i_12_;
                    break while_1_;
                }
            }
        }
        while_2_:
        for (int i_15_ = i_3_ - 1; i_15_ >= 0; i_15_--) {
            for (int i_16_ = 0; i_16_ < i_0_; i_16_++) {
                int i_17_ = is[i_16_ + i_15_ * i_0_];
                if ((i_17_ & 0xffffff) != 0) {
                    i_8_ = i_15_ + 1;
                    break while_2_;
                }
            }
        }
        while_3_:
        for (int i_18_ = i_0_ - 1; i_18_ >= 0; i_18_--) {
            for (int i_19_ = 0; i_19_ < i_3_; i_19_++) {
                int i_20_ = is[i_18_ + i_19_ * i_0_];
                if ((i_20_ & 0xffffff) != 0) {
                    i_7_ = i_18_ + 1;
                    break while_3_;
                }
            }
        }
        byteArrayofPixels[i * 9] = (byte) (anInt199 / 16384);
        byteArrayofPixels[i * 9 + 1] = (byte) (anInt199 / 128 & 0x7f);
        byteArrayofPixels[i * 9 + 2] = (byte) (anInt199 & 0x7f);
        byteArrayofPixels[i * 9 + 3] = (byte) (i_7_ - i_5_);
        byteArrayofPixels[i * 9 + 4] = (byte) (i_8_ - i_6_);
        byteArrayofPixels[i * 9 + 5] = (byte) i_5_;
        byteArrayofPixels[i * 9 + 6] = (byte) (i_2_ - i_6_);
        byteArrayofPixels[i * 9 + 7] = (byte) i_1_;
        byteArrayofPixels[i * 9 + 8] = (byte) i_4_;
        for (int i_21_ = i_6_; i_21_ < i_8_; i_21_++) {
            for (int i_22_ = i_5_; i_22_ < i_7_; i_22_++) {
                int i_23_ = is[i_22_ + i_21_ * i_0_] & 0xff;
                if (i_23_ > 30 && i_23_ < 230) {
                    aBoolean198 = true;
                }
                byteArrayofPixels[anInt199++] = (byte) i_23_;
            }
        }
    }

    public void method37(String s, int i, int j, int k, boolean flag) {
        try {
            if (aBoolean198 || k == 0) {
                flag = false;
            }
            for (int l = 0; l < s.length(); l++) {
                int i1 = anIntArray201[s.charAt(l)];
                if (flag) {
                    drawSprite(i1, i + 1, j, 0, byteArrayofPixels, aBoolean198);
                    drawSprite(i1, i, j + 1, 0, byteArrayofPixels, aBoolean198);
                }
                drawSprite(i1, i, j, k, byteArrayofPixels, aBoolean198);
                i += byteArrayofPixels[i1 + 7];
            }
        } catch (Exception exception) {
            System.out.println("drawstring: " + exception);
            exception.printStackTrace(System.out);
        }
    }

    public void method38(String s, int i, int j, int k, boolean flag) {
        int l = method41(s) / 2;
        int i1 = method44();
        if (i - l > DrawingArea.bottomX) {
            return;
        }
        if (i + l < DrawingArea.topX) {
            return;
        }
        if (j - i1 > DrawingArea.bottomY) {
            return;
        }
        if (j < 0) {
            return;
        } else {
            method37(s, i - l, j, k, flag);
            return;
        }
    }

    public void method39(int ai[], byte abyte0[], int i, int j, int k, int l, int i1,
            int j1, int k1) {
        for (int l1 = -i1; l1 < 0; l1++) {
            for (int i2 = -l; i2 < 0; i2++) {
                int j2 = abyte0[j++] & 0xff;
                if (j2 > 30) {
                    if (j2 >= 230) {
                        ai[k++] = i;
                    } else {
                        int k2 = ai[k];
                        ai[k++] = ((i & 0xff00ff) * j2 + (k2 & 0xff00ff) * (256 - j2) & 0xff00ff00) + ((i & 0xff00) * j2 + (k2 & 0xff00) * (256 - j2) & 0xff0000) >> 8;
                    }
                } else {
                    k++;
                }
            }

            k += j1;
            j += k1;
        }

    }

    public int method40() {
        return byteArrayofPixels[8] - 1;
    }

    public int method41(String s) {
        int i = 0;
        for (int j = 0; j < s.length(); j++) {
            if (s.charAt(j) == '@' && j + 4 < s.length() && s.charAt(j + 4) == '@') {
                j += 4;
            } else if (s.charAt(j) == '~' && j + 4 < s.length() && s.charAt(j + 4) == '~') {
                j += 4;
            } else {
                i += byteArrayofPixels[anIntArray201[s.charAt(j)] + 7];
            }
        }

        return i;
    }

    public void drawSprite(int i, int j, int k, int l, byte pixelsAsAByte[], boolean dummy) {
        int i1 = j + pixelsAsAByte[i + 5];
        int j1 = k - pixelsAsAByte[i + 6];
        int k1 = pixelsAsAByte[i + 3];
        int l1 = pixelsAsAByte[i + 4];
        int i2 = pixelsAsAByte[i] * 16384 + pixelsAsAByte[i + 1] * 128 + pixelsAsAByte[i + 2];
        int j2 = i1 + j1 * DrawingArea.width;
        int k2 = DrawingArea.width - k1;
        int l2 = 0;
        if (j1 < DrawingArea.topY) {
            int i3 = DrawingArea.topY - j1;
            l1 -= i3;
            j1 = DrawingArea.topY;
            i2 += i3 * k1;
            j2 += i3 * DrawingArea.width;
        }
        if (j1 + l1 >= DrawingArea.bottomY) {
            l1 -= ((j1 + l1) - DrawingArea.bottomY) + 1;
        }
        if (i1 < DrawingArea.topX) {
            int j3 = DrawingArea.topX - i1;
            k1 -= j3;
            i1 = DrawingArea.topX;
            i2 += j3;
            j2 += j3;
            l2 += j3;
            k2 += j3;
        }
        if (i1 + k1 >= DrawingArea.bottomX) {
            int k3 = ((i1 + k1) - DrawingArea.bottomX) + 1;
            k1 -= k3;
            l2 += k3;
            k2 += k3;
        }
        if (k1 > 0 && l1 > 0) {
            if (dummy) {
                method39(DrawingArea.pixels, pixelsAsAByte, l, i2, j2, k1, l1, k2, l2);
            } else {
                method43(DrawingArea.pixels, pixelsAsAByte, l, i2, j2, k1, l1, k2, l2);
            }
        }
    }

    public void method43(int ai[], byte abyte0[], int i, int j, int k, int l, int i1,
            int j1, int k1) {
        try {
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

        } catch (Exception exception) {
            System.out.println("plotletter: " + exception);
            exception.printStackTrace(System.out);
        }
    }

    public int method44() {
        return byteArrayofPixels[6];
    }
}
