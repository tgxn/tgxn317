package client.bz2;

// Partially Renamed.

public final class BZ2InputStream {
    
    private static BZ2Block bz2Block = new BZ2Block();
    
    public static int decompressBuffer(byte outputBuff[], int decompressedSize, byte inputBuff[], int compressedSize, int offset) {
        synchronized (bz2Block) {
            bz2Block.input = inputBuff;
            bz2Block.nextIn = offset;
            bz2Block.output = outputBuff;
            bz2Block.availOut = 0;
            bz2Block.compressedSize = compressedSize;
            bz2Block.decompressedSize = decompressedSize;
            bz2Block.bsLive = 0;
            bz2Block.bsBuff = 0;
            bz2Block.totalInLo32 = 0;
            bz2Block.totalInHi32 = 0;
            bz2Block.totalOutLo32 = 0;
            bz2Block.totalOutHi32 = 0;
            bz2Block.blockNo = 0;
            decompress(bz2Block);
            decompressedSize -= bz2Block.decompressedSize;
            return decompressedSize;
        }
    }

    private static void getNextFileHeader(BZ2Block archive) {
        byte stateOutCH = archive.stateOutCH;
        int stateOutLen = archive.stateOutLen;
        int j = archive.anInt584;
        int k = archive.anInt582;
        int ai[] = BZ2Block.ll8;
        int l = archive.anInt581;
        byte abyte0[] = archive.output;
        int i1 = archive.availOut;
        int j1 = archive.decompressedSize;
        int k1 = j1;
        int l1 = archive.anInt601 + 1;
        label0:
        do {
            if (stateOutLen > 0) {
                do {
                    if (j1 == 0) {
                        break label0;
                    }
                    if (stateOutLen == 1) {
                        break;
                    }
                    abyte0[i1] = stateOutCH;
                    stateOutLen--;
                    i1++;
                    j1--;
                } while (true);
                if (j1 == 0) {
                    stateOutLen = 1;
                    break;
                }
                abyte0[i1] = stateOutCH;
                i1++;
                j1--;
            }
            boolean flag = true;
            while (flag) {
                flag = false;
                if (j == l1) {
                    stateOutLen = 0;
                    break label0;
                }
                stateOutCH = (byte) k;
                l = ai[l];
                byte byte0 = (byte) (l & 0xff);
                l >>= 8;
                j++;
                if (byte0 != k) {
                    k = byte0;
                    if (j1 == 0) {
                        stateOutLen = 1;
                    } else {
                        abyte0[i1] = stateOutCH;
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
                    stateOutLen = 1;
                    break label0;
                }
                abyte0[i1] = stateOutCH;
                i1++;
                j1--;
                flag = true;
            }
            stateOutLen = 2;
            l = ai[l];
            byte byte1 = (byte) (l & 0xff);
            l >>= 8;
            if (++j != l1) {
                if (byte1 != k) {
                    k = byte1;
                } else {
                    stateOutLen = 3;
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
                            stateOutLen = (byte3 & 0xff) + 4;
                            l = ai[l];
                            k = (byte) (l & 0xff);
                            l >>= 8;
                            j++;
                        }
                    }
                }
            }
        } while (true);
        int i2 = archive.totalOutLo32;
        archive.totalOutLo32 += k1 - j1;
        if (archive.totalOutLo32 < i2) {
            archive.totalOutHi32++;
        }
        archive.stateOutCH = stateOutCH;
        archive.stateOutLen = stateOutLen;
        archive.anInt584 = j;
        archive.anInt582 = k;
        BZ2Block.ll8 = ai;
        archive.anInt581 = l;
        archive.output = abyte0;
        archive.availOut = i1;
        archive.decompressedSize = j1;
    }

    private static void decompress(BZ2Block bz2Entry) {
        int tMinLen = 0;
        int tLimit[] = null;
        int tBase[] = null;
        int tPerm[] = null;
        bz2Entry.blockSize100k = 1;
        if (BZ2Block.ll8 == null) {
            BZ2Block.ll8 = new int[bz2Entry.blockSize100k * 0x186a0];
        }
        boolean reading = true;
        while (reading) {
            byte head = getByte(bz2Entry);
            if (head == 23) {
                return;
            }
            head = getByte(bz2Entry);
            head = getByte(bz2Entry);
            head = getByte(bz2Entry);
            head = getByte(bz2Entry);
            head = getByte(bz2Entry);
            bz2Entry.blockNo++;
            head = getByte(bz2Entry);
            head = getByte(bz2Entry);
            head = getByte(bz2Entry);
            head = getByte(bz2Entry);
            head = getBit(bz2Entry);
            bz2Entry.randomized = head != 0;
            if (bz2Entry.randomized) {
                System.out.println("PANIC! RANDOMISED BLOCK!");
            }
            bz2Entry.origPtr = 0;
            head = getByte(bz2Entry);
            bz2Entry.origPtr = bz2Entry.origPtr << 8 | head & 0xff;
            head = getByte(bz2Entry);
            bz2Entry.origPtr = bz2Entry.origPtr << 8 | head & 0xff;
            head = getByte(bz2Entry);
            bz2Entry.origPtr = bz2Entry.origPtr << 8 | head & 0xff;
            for (int i = 0; i < 16; i++) {
                byte used = getBit(bz2Entry);
                if (used == 1) {
                    bz2Entry.inUse16[i] = true;
                } else {
                    bz2Entry.inUse16[i] = false;
                }
            }

            for (int k = 0; k < 256; k++) {
                bz2Entry.inUse[k] = false;
            }

            for (int l = 0; l < 16; l++) {
                if (bz2Entry.inUse16[l]) {
                    for (int i3 = 0; i3 < 16; i3++) {
                        byte byte2 = getBit(bz2Entry);
                        if (byte2 == 1) {
                            bz2Entry.inUse[l * 16 + i3] = true;
                        }
                    }

                }
            }

            makeMaps(bz2Entry);
            int alphaSize = bz2Entry.nInUse + 2;
            int groups = getBits(3, bz2Entry);
            int selectors = getBits(15, bz2Entry);
            for (int i = 0; i < selectors; i++) {
                int selectorValue = 0;
                do {
                    byte v = getBit(bz2Entry);
                    if (v == 0) {
                        break;
                    }
                    selectorValue++;
                } while (true);
                bz2Entry.selectorMtf[i] = (byte) selectorValue;
            }

            byte pos[] = new byte[6];
            for (byte byte16 = 0; byte16 < groups; byte16++) {
                pos[byte16] = byte16;
            }

            for (int j1 = 0; j1 < selectors; j1++) {
                byte byte17 = bz2Entry.selectorMtf[j1];
                byte byte15 = pos[byte17];
                for (; byte17 > 0; byte17--) {
                    pos[byte17] = pos[byte17 - 1];
                }

                pos[0] = byte15;
                bz2Entry.selector[j1] = byte15;
            }

            for (int k3 = 0; k3 < groups; k3++) {
                int l6 = getBits(5, bz2Entry);
                for (int k1 = 0; k1 < alphaSize; k1++) {
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
                    bz2Entry.len[k3][k1] = (byte) l6;
                }

            }

            for (int l3 = 0; l3 < groups; l3++) {
                byte byte8 = 32;
                int i = 0;
                for (int l1 = 0; l1 < alphaSize; l1++) {
                    if (bz2Entry.len[l3][l1] > i) {
                        i = bz2Entry.len[l3][l1];
                    }
                    if (bz2Entry.len[l3][l1] < byte8) {
                        byte8 = bz2Entry.len[l3][l1];
                    }
                }

                method232(bz2Entry.limit[l3], bz2Entry.base[l3], bz2Entry.perm[l3], bz2Entry.len[l3], byte8, i, alphaSize);
                bz2Entry.minLens[l3] = byte8;
            }

            int l4 = bz2Entry.nInUse + 1;
            int l5 = 0x186a0 * bz2Entry.blockSize100k;
            int i5 = -1;
            int j5 = 0;
            for (int i2 = 0; i2 <= 255; i2++) {
                bz2Entry.unzftab[i2] = 0;
            }

            int j9 = 4095;
            for (int l8 = 15; l8 >= 0; l8--) {
                for (int i9 = 15; i9 >= 0; i9--) {
                    bz2Entry.yy[j9] = (byte) (l8 * 16 + i9);
                    j9--;
                }

                bz2Entry.mtf16[l8] = j9 + 1;
            }

            int i6 = 0;
            if (j5 == 0) {
                i5++;
                j5 = 50;
                byte byte12 = bz2Entry.selector[i5];
                tMinLen  = bz2Entry.minLens[byte12];
                tLimit = bz2Entry.limit[byte12];
                tPerm = bz2Entry.perm[byte12];
                tBase = bz2Entry.base[byte12];
            }
            j5--;
            int i7 = tMinLen ;
            int l7;
            byte byte9;
            for (l7 = getBits(i7, bz2Entry); l7 > tLimit[i7]; l7 = l7 << 1 | byte9) {
                i7++;
                byte9 = getBit(bz2Entry);
            }

            for (int k5 = tPerm[l7 - tBase[i7]]; k5 != l4;) {
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
                            byte byte13 = bz2Entry.selector[i5];
                            tMinLen  = bz2Entry.minLens[byte13];
                            tLimit = bz2Entry.limit[byte13];
                            tPerm = bz2Entry.perm[byte13];
                            tBase = bz2Entry.base[byte13];
                        }
                        j5--;
                        int j7 = tMinLen ;
                        int i8;
                        byte byte10;
                        for (i8 = getBits(j7, bz2Entry); i8 > tLimit[j7]; i8 = i8 << 1 | byte10) {
                            j7++;
                            byte10 = getBit(bz2Entry);
                        }

                        k5 = tPerm[i8 - tBase[j7]];
                    } while (k5 == 0 || k5 == 1);
                    j6++;
                    byte byte5 = bz2Entry.seqToUnseq[bz2Entry.yy[bz2Entry.mtf16[0]] & 0xff];
                    bz2Entry.unzftab[byte5 & 0xff] += j6;
                    for (; j6 > 0; j6--) {
                        BZ2Block.ll8[i6] = byte5 & 0xff;
                        i6++;
                    }

                } else {
                    int j11 = k5 - 1;
                    byte byte6;
                    if (j11 < 16) {
                        int j10 = bz2Entry.mtf16[0];
                        byte6 = bz2Entry.yy[j10 + j11];
                        for (; j11 > 3; j11 -= 4) {
                            int k11 = j10 + j11;
                            bz2Entry.yy[k11] = bz2Entry.yy[k11 - 1];
                            bz2Entry.yy[k11 - 1] = bz2Entry.yy[k11 - 2];
                            bz2Entry.yy[k11 - 2] = bz2Entry.yy[k11 - 3];
                            bz2Entry.yy[k11 - 3] = bz2Entry.yy[k11 - 4];
                        }

                        for (; j11 > 0; j11--) {
                            bz2Entry.yy[j10 + j11] = bz2Entry.yy[(j10 + j11) - 1];
                        }

                        bz2Entry.yy[j10] = byte6;
                    } else {
                        int l10 = j11 / 16;
                        int i11 = j11 % 16;
                        int k10 = bz2Entry.mtf16[l10] + i11;
                        byte6 = bz2Entry.yy[k10];
                        for (; k10 > bz2Entry.mtf16[l10]; k10--) {
                            bz2Entry.yy[k10] = bz2Entry.yy[k10 - 1];
                        }

                        bz2Entry.mtf16[l10]++;
                        for (; l10 > 0; l10--) {
                            bz2Entry.mtf16[l10]--;
                            bz2Entry.yy[bz2Entry.mtf16[l10]] = bz2Entry.yy[(bz2Entry.mtf16[l10 - 1] + 16) - 1];
                        }

                        bz2Entry.mtf16[0]--;
                        bz2Entry.yy[bz2Entry.mtf16[0]] = byte6;
                        if (bz2Entry.mtf16[0] == 0) {
                            int i10 = 4095;
                            for (int k9 = 15; k9 >= 0; k9--) {
                                for (int l9 = 15; l9 >= 0; l9--) {
                                    bz2Entry.yy[i10] = bz2Entry.yy[bz2Entry.mtf16[k9] + l9];
                                    i10--;
                                }

                                bz2Entry.mtf16[k9] = i10 + 1;
                            }

                        }
                    }
                    bz2Entry.unzftab[bz2Entry.seqToUnseq[byte6 & 0xff] & 0xff]++;
                    BZ2Block.ll8[i6] = bz2Entry.seqToUnseq[byte6 & 0xff] & 0xff;
                    i6++;
                    if (j5 == 0) {
                        i5++;
                        j5 = 50;
                        byte byte14 = bz2Entry.selector[i5];
                        tMinLen  = bz2Entry.minLens[byte14];
                        tLimit = bz2Entry.limit[byte14];
                        tPerm = bz2Entry.perm[byte14];
                        tBase = bz2Entry.base[byte14];
                    }
                    j5--;
                    int k7 = tMinLen ;
                    int j8;
                    byte byte11;
                    for (j8 = getBits(k7, bz2Entry); j8 > tLimit[k7]; j8 = j8 << 1 | byte11) {
                        k7++;
                        byte11 = getBit(bz2Entry);
                    }

                    k5 = tPerm[j8 - tBase[k7]];
                }
            }

            bz2Entry.stateOutLen = 0;
            bz2Entry.stateOutCH = 0;
            bz2Entry.cftab[0] = 0;
            for (int j2 = 1; j2 <= 256; j2++) {
                bz2Entry.cftab[j2] = bz2Entry.unzftab[j2 - 1];
            }

            for (int k2 = 1; k2 <= 256; k2++) {
                bz2Entry.cftab[k2] += bz2Entry.cftab[k2 - 1];
            }

            for (int l2 = 0; l2 < i6; l2++) {
                byte byte7 = (byte) (BZ2Block.ll8[l2] & 0xff);
                BZ2Block.ll8[bz2Entry.cftab[byte7 & 0xff]] |= l2 << 8;
                bz2Entry.cftab[byte7 & 0xff]++;
            }

            bz2Entry.anInt581 = BZ2Block.ll8[bz2Entry.origPtr] >> 8;
            bz2Entry.anInt584 = 0;
            bz2Entry.anInt581 = BZ2Block.ll8[bz2Entry.anInt581];
            bz2Entry.anInt582 = (byte) (bz2Entry.anInt581 & 0xff);
            bz2Entry.anInt581 >>= 8;
            bz2Entry.anInt584++;
            bz2Entry.anInt601 = i6;
            getNextFileHeader(bz2Entry);
            if (bz2Entry.anInt584 == bz2Entry.anInt601 + 1 && bz2Entry.stateOutLen == 0) {
                reading = true;
            } else {
                reading = false;
            }
        }
    }

    private static byte getByte(BZ2Block bz2Entry) {
        return (byte) getBits(8, bz2Entry);
    }

    private static byte getBit(BZ2Block bz2Entry) {
        return (byte) getBits(1, bz2Entry);
    }

    private static int getBits(int i, BZ2Block bz2Entry) {
        int j;
        do {
            if (bz2Entry.bsLive >= i) {
                int k = bz2Entry.bsBuff >> bz2Entry.bsLive - i & (1 << i) - 1;
                bz2Entry.bsLive -= i;
                j = k;
                break;
            }
            bz2Entry.bsBuff = bz2Entry.bsBuff << 8 | bz2Entry.input[bz2Entry.nextIn] & 0xff;
            bz2Entry.bsLive += 8;
            bz2Entry.nextIn++;
            bz2Entry.compressedSize--;
            bz2Entry.totalInLo32++;
            if (bz2Entry.totalInLo32 == 0) {
                bz2Entry.totalInHi32++;
            }
        } while (true);
        return j;
    }

    private static void makeMaps(BZ2Block bz2Entry) {
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
