package client;


public final class BZIP2Decompressor {
    
    private static BZ2Entry bz2Entry = new BZ2Entry();
    
    public static int decompress(byte abyte0[], int i, byte abyte1[], int j, int k) {
        synchronized (bz2Entry) {
            bz2Entry.aByteArray563 = abyte1;
            bz2Entry.anInt564 = k;
            bz2Entry.aByteArray568 = abyte0;
            bz2Entry.anInt569 = 0;
            bz2Entry.anInt565 = j;
            bz2Entry.anInt570 = i;
            bz2Entry.anInt577 = 0;
            bz2Entry.anInt576 = 0;
            bz2Entry.anInt566 = 0;
            bz2Entry.anInt567 = 0;
            bz2Entry.anInt571 = 0;
            bz2Entry.anInt572 = 0;
            bz2Entry.anInt579 = 0;
            method227(bz2Entry);
            i -= bz2Entry.anInt570;
            int l = i;
            return l;
        }
    }

    private static void method226(BZ2Entry bz2Entry) {
        byte byte4 = bz2Entry.aByte573;
        int i = bz2Entry.anInt574;
        int j = bz2Entry.anInt584;
        int k = bz2Entry.anInt582;
        int ai[] = BZ2Entry.anIntArray587;
        int l = bz2Entry.anInt581;
        byte abyte0[] = bz2Entry.aByteArray568;
        int i1 = bz2Entry.anInt569;
        int j1 = bz2Entry.anInt570;
        int k1 = j1;
        int l1 = bz2Entry.anInt601 + 1;
        label0:
        do {
            if (i > 0) {
                do {
                    if (j1 == 0) {
                        break label0;
                    }
                    if (i == 1) {
                        break;
                    }
                    abyte0[i1] = byte4;
                    i--;
                    i1++;
                    j1--;
                } while (true);
                if (j1 == 0) {
                    i = 1;
                    break;
                }
                abyte0[i1] = byte4;
                i1++;
                j1--;
            }
            boolean flag = true;
            while (flag) {
                flag = false;
                if (j == l1) {
                    i = 0;
                    break label0;
                }
                byte4 = (byte) k;
                l = ai[l];
                byte byte0 = (byte) (l & 0xff);
                l >>= 8;
                j++;
                if (byte0 != k) {
                    k = byte0;
                    if (j1 == 0) {
                        i = 1;
                    } else {
                        abyte0[i1] = byte4;
                        i1++;
                        j1--;
                        flag = true;
                        continue;
                    }
                    break label0;
                }
                if (j != l1) {
                    continue;
                }
                if (j1 == 0) {
                    i = 1;
                    break label0;
                }
                abyte0[i1] = byte4;
                i1++;
                j1--;
                flag = true;
            }
            i = 2;
            l = ai[l];
            byte byte1 = (byte) (l & 0xff);
            l >>= 8;
            if (++j != l1) {
                if (byte1 != k) {
                    k = byte1;
                } else {
                    i = 3;
                    l = ai[l];
                    byte byte2 = (byte) (l & 0xff);
                    l >>= 8;
                    if (++j != l1) {
                        if (byte2 != k) {
                            k = byte2;
                        } else {
                            l = ai[l];
                            byte byte3 = (byte) (l & 0xff);
                            l >>= 8;
                            j++;
                            i = (byte3 & 0xff) + 4;
                            l = ai[l];
                            k = (byte) (l & 0xff);
                            l >>= 8;
                            j++;
                        }
                    }
                }
            }
        } while (true);
        int i2 = bz2Entry.anInt571;
        bz2Entry.anInt571 += k1 - j1;
        if (bz2Entry.anInt571 < i2) {
            bz2Entry.anInt572++;
        }
        bz2Entry.aByte573 = byte4;
        bz2Entry.anInt574 = i;
        bz2Entry.anInt584 = j;
        bz2Entry.anInt582 = k;
        BZ2Entry.anIntArray587 = ai;
        bz2Entry.anInt581 = l;
        bz2Entry.aByteArray568 = abyte0;
        bz2Entry.anInt569 = i1;
        bz2Entry.anInt570 = j1;
    }

    private static void method227(BZ2Entry bz2Entry) {
        int k8 = 0;
        int ai[] = null;
        int ai1[] = null;
        int ai2[] = null;
        bz2Entry.anInt578 = 1;
        if (BZ2Entry.anIntArray587 == null) {
            BZ2Entry.anIntArray587 = new int[bz2Entry.anInt578 * 0x186a0];
        }
        boolean flag19 = true;
        while (flag19) {
            byte byte0 = getByte(bz2Entry);
            if (byte0 == 23) {
                return;
            }
            byte0 = getByte(bz2Entry);
            byte0 = getByte(bz2Entry);
            byte0 = getByte(bz2Entry);
            byte0 = getByte(bz2Entry);
            byte0 = getByte(bz2Entry);
            bz2Entry.anInt579++;
            byte0 = getByte(bz2Entry);
            byte0 = getByte(bz2Entry);
            byte0 = getByte(bz2Entry);
            byte0 = getByte(bz2Entry);
            byte0 = getBit(bz2Entry);
            if (byte0 != 0) {
                bz2Entry.aBoolean575 = true;
            } else {
                bz2Entry.aBoolean575 = false;
            }
            if (bz2Entry.aBoolean575) {
                System.out.println("PANIC! RANDOMISED BLOCK!");
            }
            bz2Entry.anInt580 = 0;
            byte0 = getByte(bz2Entry);
            bz2Entry.anInt580 = bz2Entry.anInt580 << 8 | byte0 & 0xff;
            byte0 = getByte(bz2Entry);
            bz2Entry.anInt580 = bz2Entry.anInt580 << 8 | byte0 & 0xff;
            byte0 = getByte(bz2Entry);
            bz2Entry.anInt580 = bz2Entry.anInt580 << 8 | byte0 & 0xff;
            for (int j = 0; j < 16; j++) {
                byte byte1 = getBit(bz2Entry);
                if (byte1 == 1) {
                    bz2Entry.aBooleanArray590[j] = true;
                } else {
                    bz2Entry.aBooleanArray590[j] = false;
                }
            }

            for (int k = 0; k < 256; k++) {
                bz2Entry.inUse[k] = false;
            }

            for (int l = 0; l < 16; l++) {
                if (bz2Entry.aBooleanArray590[l]) {
                    for (int i3 = 0; i3 < 16; i3++) {
                        byte byte2 = getBit(bz2Entry);
                        if (byte2 == 1) {
                            bz2Entry.inUse[l * 16 + i3] = true;
                        }
                    }

                }
            }

            makeMaps(bz2Entry);
            int i4 = bz2Entry.nInUse + 2;
            int j4 = getBits(3, bz2Entry);
            int k4 = getBits(15, bz2Entry);
            for (int i1 = 0; i1 < k4; i1++) {
                int j3 = 0;
                do {
                    byte byte3 = getBit(bz2Entry);
                    if (byte3 == 0) {
                        break;
                    }
                    j3++;
                } while (true);
                bz2Entry.aByteArray595[i1] = (byte) j3;
            }

            byte abyte0[] = new byte[6];
            for (byte byte16 = 0; byte16 < j4; byte16++) {
                abyte0[byte16] = byte16;
            }

            for (int j1 = 0; j1 < k4; j1++) {
                byte byte17 = bz2Entry.aByteArray595[j1];
                byte byte15 = abyte0[byte17];
                for (; byte17 > 0; byte17--) {
                    abyte0[byte17] = abyte0[byte17 - 1];
                }

                abyte0[0] = byte15;
                bz2Entry.aByteArray594[j1] = byte15;
            }

            for (int k3 = 0; k3 < j4; k3++) {
                int l6 = getBits(5, bz2Entry);
                for (int k1 = 0; k1 < i4; k1++) {
                    do {
                        byte byte4 = getBit(bz2Entry);
                        if (byte4 == 0) {
                            break;
                        }
                        byte4 = getBit(bz2Entry);
                        if (byte4 == 0) {
                            l6++;
                        } else {
                            l6--;
                        }
                    } while (true);
                    bz2Entry.aByteArrayArray596[k3][k1] = (byte) l6;
                }

            }

            for (int l3 = 0; l3 < j4; l3++) {
                byte byte8 = 32;
                int i = 0;
                for (int l1 = 0; l1 < i4; l1++) {
                    if (bz2Entry.aByteArrayArray596[l3][l1] > i) {
                        i = bz2Entry.aByteArrayArray596[l3][l1];
                    }
                    if (bz2Entry.aByteArrayArray596[l3][l1] < byte8) {
                        byte8 = bz2Entry.aByteArrayArray596[l3][l1];
                    }
                }

                method232(bz2Entry.anIntArrayArray597[l3], bz2Entry.anIntArrayArray598[l3], bz2Entry.anIntArrayArray599[l3], bz2Entry.aByteArrayArray596[l3], byte8, i, i4);
                bz2Entry.anIntArray600[l3] = byte8;
            }

            int l4 = bz2Entry.nInUse + 1;
            int l5 = 0x186a0 * bz2Entry.anInt578;
            int i5 = -1;
            int j5 = 0;
            for (int i2 = 0; i2 <= 255; i2++) {
                bz2Entry.anIntArray583[i2] = 0;
            }

            int j9 = 4095;
            for (int l8 = 15; l8 >= 0; l8--) {
                for (int i9 = 15; i9 >= 0; i9--) {
                    bz2Entry.aByteArray592[j9] = (byte) (l8 * 16 + i9);
                    j9--;
                }

                bz2Entry.anIntArray593[l8] = j9 + 1;
            }

            int i6 = 0;
            if (j5 == 0) {
                i5++;
                j5 = 50;
                byte byte12 = bz2Entry.aByteArray594[i5];
                k8 = bz2Entry.anIntArray600[byte12];
                ai = bz2Entry.anIntArrayArray597[byte12];
                ai2 = bz2Entry.anIntArrayArray599[byte12];
                ai1 = bz2Entry.anIntArrayArray598[byte12];
            }
            j5--;
            int i7 = k8;
            int l7;
            byte byte9;
            for (l7 = getBits(i7, bz2Entry); l7 > ai[i7]; l7 = l7 << 1 | byte9) {
                i7++;
                byte9 = getBit(bz2Entry);
            }

            for (int k5 = ai2[l7 - ai1[i7]]; k5 != l4;) {
                if (k5 == 0 || k5 == 1) {
                    int j6 = -1;
                    int k6 = 1;
                    do {
                        if (k5 == 0) {
                            j6 += k6;
                        } else if (k5 == 1) {
                            j6 += 2 * k6;
                        }
                        k6 *= 2;
                        if (j5 == 0) {
                            i5++;
                            j5 = 50;
                            byte byte13 = bz2Entry.aByteArray594[i5];
                            k8 = bz2Entry.anIntArray600[byte13];
                            ai = bz2Entry.anIntArrayArray597[byte13];
                            ai2 = bz2Entry.anIntArrayArray599[byte13];
                            ai1 = bz2Entry.anIntArrayArray598[byte13];
                        }
                        j5--;
                        int j7 = k8;
                        int i8;
                        byte byte10;
                        for (i8 = getBits(j7, bz2Entry); i8 > ai[j7]; i8 = i8 << 1 | byte10) {
                            j7++;
                            byte10 = getBit(bz2Entry);
                        }

                        k5 = ai2[i8 - ai1[j7]];
                    } while (k5 == 0 || k5 == 1);
                    j6++;
                    byte byte5 = bz2Entry.seqToUnseq[bz2Entry.aByteArray592[bz2Entry.anIntArray593[0]] & 0xff];
                    bz2Entry.anIntArray583[byte5 & 0xff] += j6;
                    for (; j6 > 0; j6--) {
                        BZ2Entry.anIntArray587[i6] = byte5 & 0xff;
                        i6++;
                    }

                } else {
                    int j11 = k5 - 1;
                    byte byte6;
                    if (j11 < 16) {
                        int j10 = bz2Entry.anIntArray593[0];
                        byte6 = bz2Entry.aByteArray592[j10 + j11];
                        for (; j11 > 3; j11 -= 4) {
                            int k11 = j10 + j11;
                            bz2Entry.aByteArray592[k11] = bz2Entry.aByteArray592[k11 - 1];
                            bz2Entry.aByteArray592[k11 - 1] = bz2Entry.aByteArray592[k11 - 2];
                            bz2Entry.aByteArray592[k11 - 2] = bz2Entry.aByteArray592[k11 - 3];
                            bz2Entry.aByteArray592[k11 - 3] = bz2Entry.aByteArray592[k11 - 4];
                        }

                        for (; j11 > 0; j11--) {
                            bz2Entry.aByteArray592[j10 + j11] = bz2Entry.aByteArray592[(j10 + j11) - 1];
                        }

                        bz2Entry.aByteArray592[j10] = byte6;
                    } else {
                        int l10 = j11 / 16;
                        int i11 = j11 % 16;
                        int k10 = bz2Entry.anIntArray593[l10] + i11;
                        byte6 = bz2Entry.aByteArray592[k10];
                        for (; k10 > bz2Entry.anIntArray593[l10]; k10--) {
                            bz2Entry.aByteArray592[k10] = bz2Entry.aByteArray592[k10 - 1];
                        }

                        bz2Entry.anIntArray593[l10]++;
                        for (; l10 > 0; l10--) {
                            bz2Entry.anIntArray593[l10]--;
                            bz2Entry.aByteArray592[bz2Entry.anIntArray593[l10]] = bz2Entry.aByteArray592[(bz2Entry.anIntArray593[l10 - 1] + 16) - 1];
                        }

                        bz2Entry.anIntArray593[0]--;
                        bz2Entry.aByteArray592[bz2Entry.anIntArray593[0]] = byte6;
                        if (bz2Entry.anIntArray593[0] == 0) {
                            int i10 = 4095;
                            for (int k9 = 15; k9 >= 0; k9--) {
                                for (int l9 = 15; l9 >= 0; l9--) {
                                    bz2Entry.aByteArray592[i10] = bz2Entry.aByteArray592[bz2Entry.anIntArray593[k9] + l9];
                                    i10--;
                                }

                                bz2Entry.anIntArray593[k9] = i10 + 1;
                            }

                        }
                    }
                    bz2Entry.anIntArray583[bz2Entry.seqToUnseq[byte6 & 0xff] & 0xff]++;
                    BZ2Entry.anIntArray587[i6] = bz2Entry.seqToUnseq[byte6 & 0xff] & 0xff;
                    i6++;
                    if (j5 == 0) {
                        i5++;
                        j5 = 50;
                        byte byte14 = bz2Entry.aByteArray594[i5];
                        k8 = bz2Entry.anIntArray600[byte14];
                        ai = bz2Entry.anIntArrayArray597[byte14];
                        ai2 = bz2Entry.anIntArrayArray599[byte14];
                        ai1 = bz2Entry.anIntArrayArray598[byte14];
                    }
                    j5--;
                    int k7 = k8;
                    int j8;
                    byte byte11;
                    for (j8 = getBits(k7, bz2Entry); j8 > ai[k7]; j8 = j8 << 1 | byte11) {
                        k7++;
                        byte11 = getBit(bz2Entry);
                    }

                    k5 = ai2[j8 - ai1[k7]];
                }
            }

            bz2Entry.anInt574 = 0;
            bz2Entry.aByte573 = 0;
            bz2Entry.anIntArray585[0] = 0;
            for (int j2 = 1; j2 <= 256; j2++) {
                bz2Entry.anIntArray585[j2] = bz2Entry.anIntArray583[j2 - 1];
            }

            for (int k2 = 1; k2 <= 256; k2++) {
                bz2Entry.anIntArray585[k2] += bz2Entry.anIntArray585[k2 - 1];
            }

            for (int l2 = 0; l2 < i6; l2++) {
                byte byte7 = (byte) (BZ2Entry.anIntArray587[l2] & 0xff);
                BZ2Entry.anIntArray587[bz2Entry.anIntArray585[byte7 & 0xff]] |= l2 << 8;
                bz2Entry.anIntArray585[byte7 & 0xff]++;
            }

            bz2Entry.anInt581 = BZ2Entry.anIntArray587[bz2Entry.anInt580] >> 8;
            bz2Entry.anInt584 = 0;
            bz2Entry.anInt581 = BZ2Entry.anIntArray587[bz2Entry.anInt581];
            bz2Entry.anInt582 = (byte) (bz2Entry.anInt581 & 0xff);
            bz2Entry.anInt581 >>= 8;
            bz2Entry.anInt584++;
            bz2Entry.anInt601 = i6;
            method226(bz2Entry);
            if (bz2Entry.anInt584 == bz2Entry.anInt601 + 1 && bz2Entry.anInt574 == 0) {
                flag19 = true;
            } else {
                flag19 = false;
            }
        }
    }

    private static byte getByte(BZ2Entry bz2Entry) {
        return (byte) getBits(8, bz2Entry);
    }

    private static byte getBit(BZ2Entry bz2Entry) {
        return (byte) getBits(1, bz2Entry);
    }

    private static int getBits(int i, BZ2Entry bz2Entry) {
        int j;
        do {
            if (bz2Entry.anInt577 >= i) {
                int k = bz2Entry.anInt576 >> bz2Entry.anInt577 - i & (1 << i) - 1;
                bz2Entry.anInt577 -= i;
                j = k;
                break;
            }
            bz2Entry.anInt576 = bz2Entry.anInt576 << 8 | bz2Entry.aByteArray563[bz2Entry.anInt564] & 0xff;
            bz2Entry.anInt577 += 8;
            bz2Entry.anInt564++;
            bz2Entry.anInt565--;
            bz2Entry.anInt566++;
            if (bz2Entry.anInt566 == 0) {
                bz2Entry.anInt567++;
            }
        } while (true);
        return j;
    }

    private static void makeMaps(BZ2Entry bz2Entry) {
        bz2Entry.nInUse = 0;
        for (int i = 0; i < 256; i++) {
            if (bz2Entry.inUse[i]) {
                bz2Entry.seqToUnseq[bz2Entry.nInUse] = (byte) i;
                bz2Entry.nInUse++;
            }
        }

    }

    private static void method232(int ai[], int ai1[], int ai2[], byte abyte0[], int i, int j, int k) {
        int l = 0;
        for (int i1 = i; i1 <= j; i1++) {
            for (int l2 = 0; l2 < k; l2++) {
                if (abyte0[l2] == i1) {
                    ai2[l] = l2;
                    l++;
                }
            }

        }

        for (int j1 = 0; j1 < 23; j1++) {
            ai1[j1] = 0;
        }

        for (int k1 = 0; k1 < k; k1++) {
            ai1[abyte0[k1] + 1]++;
        }

        for (int l1 = 1; l1 < 23; l1++) {
            ai1[l1] += ai1[l1 - 1];
        }

        for (int i2 = 0; i2 < 23; i2++) {
            ai[i2] = 0;
        }

        int i3 = 0;
        for (int j2 = i; j2 <= j; j2++) {
            i3 += ai1[j2 + 1] - ai1[j2];
            ai[j2] = i3 - 1;
            i3 <<= 1;
        }

        for (int k2 = i + 1; k2 <= j; k2++) {
            ai1[k2] = (ai[k2 - 1] + 1 << 1) - ai1[k2];
        }

    }
}
