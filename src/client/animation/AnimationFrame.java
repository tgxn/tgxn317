package client.animation;

import client.ModelTransform;
import client.Stream;

public class AnimationFrame {
    
    private static AnimationFrame cache[];
    public int displayLength;
    public ModelTransform myModelTransform;
    public int anInt638;
    public int opcodeLinkTable[];
    public int anIntArray640[];
    public int anIntArray641[];
    public int anIntArray642[];
    
    public AnimationFrame() {
    }

    public static void initAnimFrame(int i) {
        cache = new AnimationFrame[i + 1];
    }

    public static void load(byte abyte0[]) {
        Stream stream = new Stream(abyte0);
        stream.currentOffset = abyte0.length - 8;
        int i = stream.readUnsignedWord();
        int j = stream.readUnsignedWord();
        int k = stream.readUnsignedWord();
        int l = stream.readUnsignedWord();
        int i1 = 0;
        Stream class30_sub2_sub2_1 = new Stream(abyte0);
        class30_sub2_sub2_1.currentOffset = i1;
        i1 += i + 2;
        Stream class30_sub2_sub2_2 = new Stream(abyte0);
        class30_sub2_sub2_2.currentOffset = i1;
        i1 += j;
        Stream class30_sub2_sub2_3 = new Stream(abyte0);
        class30_sub2_sub2_3.currentOffset = i1;
        i1 += k;
        Stream class30_sub2_sub2_4 = new Stream(abyte0);
        class30_sub2_sub2_4.currentOffset = i1;
        i1 += l;
        Stream class30_sub2_sub2_5 = new Stream(abyte0);
        class30_sub2_sub2_5.currentOffset = i1;
        ModelTransform modelTransform = new ModelTransform(class30_sub2_sub2_5);
        int k1 = class30_sub2_sub2_1.readUnsignedWord();
        int ai[] = new int[500];
        int ai1[] = new int[500];
        int ai2[] = new int[500];
        int ai3[] = new int[500];
        for (int l1 = 0; l1 < k1; l1++) {
            int i2 = class30_sub2_sub2_1.readUnsignedWord();
            AnimationFrame annimationFrame = cache[i2] = new AnimationFrame();
            annimationFrame.displayLength = class30_sub2_sub2_4.readUnsignedByte();
            annimationFrame.myModelTransform = modelTransform;
            int j2 = class30_sub2_sub2_1.readUnsignedByte();
            int k2 = -1;
            int l2 = 0;
            for (int i3 = 0; i3 < j2; i3++) {
                int j3 = class30_sub2_sub2_2.readUnsignedByte();
                if (j3 > 0) {
                    if (modelTransform.anIntArray342[i3] != 0) {
                        for (int l3 = i3 - 1; l3 > k2; l3--) {
                            if (modelTransform.anIntArray342[l3] != 0) {
                                continue;
                            }
                            ai[l2] = l3;
                            ai1[l2] = 0;
                            ai2[l2] = 0;
                            ai3[l2] = 0;
                            l2++;
                            break;
                        }
                    }
                    ai[l2] = i3;
                    char c = '\0';
                    if (modelTransform.anIntArray342[i3] == 3) {
                        c = '\200';
                    }
                    if ((j3 & 1) != 0) {
                        ai1[l2] = class30_sub2_sub2_3.method421();
                    } else {
                        ai1[l2] = c;
                    }
                    if ((j3 & 2) != 0) {
                        ai2[l2] = class30_sub2_sub2_3.method421();
                    } else {
                        ai2[l2] = c;
                    }
                    if ((j3 & 4) != 0) {
                        ai3[l2] = class30_sub2_sub2_3.method421();
                    } else {
                        ai3[l2] = c;
                    }
                    k2 = i3;
                    l2++;

                }
            }

            annimationFrame.anInt638 = l2;
            annimationFrame.opcodeLinkTable = new int[l2];
            annimationFrame.anIntArray640 = new int[l2];
            annimationFrame.anIntArray641 = new int[l2];
            annimationFrame.anIntArray642 = new int[l2];
            for (int k3 = 0; k3 < l2; k3++) {
                annimationFrame.opcodeLinkTable[k3] = ai[k3];
                annimationFrame.anIntArray640[k3] = ai1[k3];
                annimationFrame.anIntArray641[k3] = ai2[k3];
                annimationFrame.anIntArray642[k3] = ai3[k3];
            }
        }
    }

    public static void nullLoader() {
        cache = null;
    }

    public static AnimationFrame forID(int j) {
        if (cache == null) {
            return null;
        } else {
            return cache[j];
        }
    }

    public static boolean isNullFrame(int i) {
        return i == -1;
    }
}
