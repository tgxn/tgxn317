package client.sounds;

import client.Stream;
import client.sounds.SoundTrack;


public class Sounds {
    
    boolean aBoolean321;
    private static boolean aBoolean322;
    private static boolean aBoolean323 = true;
    private static Sounds aClass16Array325[] = new Sounds[5000];
    public static int anIntArray326[] = new int[5000];
    private static byte aByteArray327[];
    private static Stream aClass30_Sub2_Sub2_328;
    private SoundTrack aClass6Array329[];
    private int anInt330;
    private int anInt331;
    
    private Sounds(int i) {
        aBoolean321 = true;
        aClass6Array329 = new SoundTrack[10];
    }

    public static void unpack(int i, Stream class30_sub2_sub2) {
        aByteArray327 = new byte[0x6baa8];
        aClass30_Sub2_Sub2_328 = new Stream(aByteArray327);
        if (i != 0) {
            aBoolean322 = !aBoolean322;
        }
        SoundTrack.initialise();
        do {
            int j = class30_sub2_sub2.readUnsignedWord();
            if (j == 65535) {
                return;
            }
            aClass16Array325[j] = new Sounds(8);
            aClass16Array325[j].method242(class30_sub2_sub2);
            anIntArray326[j] = aClass16Array325[j].method243();
        } while (true);
    }

    public static Stream method241(int i, int j) {
        if (aClass16Array325[j] != null) {
            Sounds class16 = aClass16Array325[j];
            return class16.method244(i, 6);
        } else {
            return null;
        }
    }

    private void method242(Stream class30_sub2_sub2) {
        for (int i = 0; i < 10; i++) {
            int j = class30_sub2_sub2.readUnsignedByte();
            if (j != 0) {
                class30_sub2_sub2.currentOffset--;
                aClass6Array329[i] = new SoundTrack();
                aClass6Array329[i].unpack(class30_sub2_sub2);
            }
        }

        anInt330 = class30_sub2_sub2.readUnsignedWord();
        anInt331 = class30_sub2_sub2.readUnsignedWord();
    }

    private int method243() {
        int j = 0x98967f;
        for (int k = 0; k < 10; k++) {
            if (aClass6Array329[k] != null && aClass6Array329[k].anInt114 / 20 < j) {
                j = aClass6Array329[k].anInt114 / 20;
            }
        }

        if (anInt330 < anInt331 && anInt330 / 20 < j) {
            j = anInt330 / 20;
        }
        if (j == 0x98967f || j == 0) {
            return 0;
        }
        for (int l = 0; l < 10; l++) {
            if (aClass6Array329[l] != null) {
                aClass6Array329[l].anInt114 -= j * 20;
            }
        }

        if (anInt330 < anInt331) {
            anInt330 -= j * 20;
            anInt331 -= j * 20;
        }
        return j;
    }

    private Stream method244(int i, int j) {
        int k = method245(i);
        aClass30_Sub2_Sub2_328.currentOffset = 0;
        aClass30_Sub2_Sub2_328.writeDWord(0x52494646);
        aClass30_Sub2_Sub2_328.method403(0, 36 + k);
        aClass30_Sub2_Sub2_328.writeDWord(0x57415645);
        aClass30_Sub2_Sub2_328.writeDWord(0x666d7420);
        aClass30_Sub2_Sub2_328.method403(0, 16);
        aClass30_Sub2_Sub2_328.method400(1);
        aClass30_Sub2_Sub2_328.method400(1);
        aClass30_Sub2_Sub2_328.method403(0, 22050);
        aClass30_Sub2_Sub2_328.method403(0, 22050);
        aClass30_Sub2_Sub2_328.method400(1);
        aClass30_Sub2_Sub2_328.method400(8);
        aClass30_Sub2_Sub2_328.writeDWord(0x64617461);
        aClass30_Sub2_Sub2_328.method403(0, k);
        aClass30_Sub2_Sub2_328.currentOffset += k;
        return aClass30_Sub2_Sub2_328;
    }

    private int method245(int i) {
        int j = 0;
        for (int k = 0; k < 10; k++) {
            if (aClass6Array329[k] != null && aClass6Array329[k].msLength + aClass6Array329[k].anInt114 > j) {
                j = aClass6Array329[k].msLength + aClass6Array329[k].anInt114;
            }
        }

        if (j == 0) {
            return 0;
        }
        int l = (22050 * j) / 1000;
        int i1 = (22050 * anInt330) / 1000;
        int j1 = (22050 * anInt331) / 1000;
        if (i1 < 0 || i1 > l || j1 < 0 || j1 > l || i1 >= j1) {
            i = 0;
        }
        int k1 = l + (j1 - i1) * (i - 1);
        for (int l1 = 44; l1 < k1 + 44; l1++) {
            aByteArray327[l1] = -128;
        }

        for (int i2 = 0; i2 < 10; i2++) {
            if (aClass6Array329[i2] != null) {
                int j2 = (aClass6Array329[i2].msLength * 22050) / 1000;
                int i3 = (aClass6Array329[i2].anInt114 * 22050) / 1000;
                int ai[] = aClass6Array329[i2].buildSoundData(j2, aClass6Array329[i2].msLength);
                for (int l3 = 0; l3 < j2; l3++) {
                    aByteArray327[l3 + i3 + 44] += (byte) (ai[l3] >> 8);
                }

            }
        }

        if (i > 1) {
            i1 += 44;
            j1 += 44;
            l += 44;
            int k2 = (k1 += 44) - l;
            for (int j3 = l - 1; j3 >= j1; j3--) {
                aByteArray327[j3 + k2] = aByteArray327[j3];
            }

            for (int k3 = 1; k3 < i; k3++) {
                int l2 = (j1 - i1) * k3;
                for (int i4 = i1; i4 < j1; i4++) {
                    aByteArray327[i4 + l2] = aByteArray327[i4];
                }

            }

            k1 -= 44;
        }
        return k1;
    }
}
