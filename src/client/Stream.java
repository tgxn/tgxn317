package client;


import java.math.BigInteger;
import client.sign.Signlink;

public final class Stream extends NodeSub {
    
    private int anInt1389;
    private int anInt1390;
    private byte aByte1391;
    private int anInt1392;
    private int anInt1393;
    private byte aByte1394;
    private int anInt1395;
    private boolean aBoolean1396;
    private int anInt1397;
    private byte aByte1398;
    private byte aByte1399;
    private byte aByte1400;
    private boolean aBoolean1401;
    private int anInt1402;
    private boolean aBoolean1403;
    private boolean aBoolean1404;
    public byte buffer[];
    public int currentOffset;
    public int bitPosition;
    private static int anIntArray1408[];
    private static final int anIntArray1409[] = {
        0, 1, 3, 7, 15, 31, 63, 127, 255, 511,
        1023, 2047, 4095, 8191, 16383, 32767, 65535, 0x1ffff, 0x3ffff, 0x7ffff,
        0xfffff, 0x1fffff, 0x3fffff, 0x7fffff, 0xffffff, 0x1ffffff, 0x3ffffff, 0x7ffffff, 0xfffffff, 0x1fffffff,
        0x3fffffff, 0x7fffffff, -1
    };
    public IsaacCipher encryption;
    private static int anInt1411;
    private static int anInt1412;
    private static int anInt1413;
    private static NodeList aClass19_1414 = new NodeList();
    private static NodeList aClass19_1415 = new NodeList();
    private static NodeList aClass19_1416 = new NodeList();
    private static char aCharArray1417[] = {
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
        'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
        'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd',
        'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
        'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
        'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7',
        '8', '9', '+', '/', '~'
    };
    
    public static boolean aBoolean1418;

    static {
        anIntArray1408 = new int[256];
        for (int j = 0; j < 256; j++) {
            int i = j;
            for (int k = 0; k < 8; k++) {
                if ((i & 1) == 1) {
                    i = i >>> 1 ^ 0xedb88320;
                } else {
                    i >>>= 1;
                }
            }
            anIntArray1408[j] = i;
        }
    }
    
    private Stream() {
        anInt1389 = 891;
        anInt1390 = 9;
        aByte1391 = 14;
        anInt1392 = -29508;
        anInt1393 = 881;
        aByte1394 = 8;
        anInt1395 = 657;
        aBoolean1396 = false;
        anInt1397 = -715;
        aByte1398 = -57;
        aByte1399 = 108;
        aByte1400 = 3;
        aBoolean1401 = false;
        anInt1402 = -373;
        aBoolean1403 = false;
        aBoolean1404 = true;
        return;
    }

    public Stream(byte abyte0[]) {
        anInt1389 = 891;
        anInt1390 = 9;
        aByte1391 = 14;
        anInt1392 = -29508;
        anInt1393 = 881;
        aByte1394 = 8;
        anInt1395 = 657;
        aBoolean1396 = false;
        anInt1397 = -715;
        aByte1398 = -57;
        aByte1399 = 108;
        aByte1400 = 3;
        aBoolean1401 = false;
        anInt1402 = -373;
        aBoolean1403 = false;
        aBoolean1404 = true;
        buffer = abyte0;
        currentOffset = 0;
        return;
    }
    
    public static Stream create(int i, int j) {
        synchronized (aClass19_1415) {
            Stream class30_sub2_sub2_2 = null;
            if (i == 0 && anInt1411 > 0) {
                anInt1411--;
                class30_sub2_sub2_2 = (Stream) aClass19_1414.popHead();
            } else if (i == 1 && anInt1412 > 0) {
                anInt1412--;
                class30_sub2_sub2_2 = (Stream) aClass19_1415.popHead();
            } else if (i == 2 && anInt1413 > 0) {
                anInt1413--;
                class30_sub2_sub2_2 = (Stream) aClass19_1416.popHead();
            }
            if (class30_sub2_sub2_2 != null) {
                class30_sub2_sub2_2.currentOffset = 0;
                Stream class30_sub2_sub2 = class30_sub2_sub2_2;
                return class30_sub2_sub2;
            }
        }
        if (j < 9 || j > 9) {
            throw new NullPointerException();
        }
        Stream class30_sub2_sub2_1 = new Stream();
        class30_sub2_sub2_1.currentOffset = 0;
        if (i == 0) {
            class30_sub2_sub2_1.buffer = new byte[100];
        } else if (i == 1) {
            class30_sub2_sub2_1.buffer = new byte[5000];
        } else {
            class30_sub2_sub2_1.buffer = new byte[30000];
        }
        return class30_sub2_sub2_1;
    }
        
    public void createFrame(int i) {
        buffer[currentOffset++] = (byte) (i + encryption.getNextKey());
    }

    public void writeWordBigEndian(int i) {
        buffer[currentOffset++] = (byte) i;
    }

    public void writeWord(int i) {
        buffer[currentOffset++] = (byte) (i >> 8);
        buffer[currentOffset++] = (byte) i;
    }

    public void method400(int i) {
        buffer[currentOffset++] = (byte) i;
        buffer[currentOffset++] = (byte) (i >> 8);

    }

    public void writeDWordBigEndian(int i) {
        buffer[currentOffset++] = (byte) (i >> 16);
        buffer[currentOffset++] = (byte) (i >> 8);
        buffer[currentOffset++] = (byte) i;
    }

    public void writeDWord(int i) {
        buffer[currentOffset++] = (byte) (i >> 24);
        buffer[currentOffset++] = (byte) (i >> 16);
        buffer[currentOffset++] = (byte) (i >> 8);
        buffer[currentOffset++] = (byte) i;
    }

    public void method403(int i, int j) {
        buffer[currentOffset++] = (byte) j;
        buffer[currentOffset++] = (byte) (j >> 8);
        if (i != 0) {
            return;
        } else {
            buffer[currentOffset++] = (byte) (j >> 16);
            buffer[currentOffset++] = (byte) (j >> 24);
            return;
        }
    }

    public void writeQWord(long l) {
        try {
            buffer[currentOffset++] = (byte) (int) (l >> 56);
            buffer[currentOffset++] = (byte) (int) (l >> 48);
            buffer[currentOffset++] = (byte) (int) (l >> 40);
            buffer[currentOffset++] = (byte) (int) (l >> 32);
            buffer[currentOffset++] = (byte) (int) (l >> 24);
            buffer[currentOffset++] = (byte) (int) (l >> 16);
            buffer[currentOffset++] = (byte) (int) (l >> 8);
            buffer[currentOffset++] = (byte) (int) l;
        } catch (RuntimeException runtimeexception) {
            Signlink.reportError("14395, " + l + ", " + runtimeexception.toString());
            throw new RuntimeException();
        }
    }

    public void writeString(String s) {
        byte[] tmpBuffer = s.getBytes();
        System.arraycopy(tmpBuffer, 0, buffer, currentOffset, s.length());
        currentOffset += s.length();
        buffer[currentOffset++] = 10;
    }

    public void writeBytes(byte abyte0[], int i, int j) {
        for (int k = j; k < j + i; k++) {
            buffer[currentOffset++] = abyte0[k];
        }
    }

    public void writeBytes(int i) {
        buffer[currentOffset - i - 1] = (byte) i;
    }

    public int readUnsignedByte() {
        return buffer[currentOffset++] & 0xff;
    }

    public byte readSignedByte() {
        return buffer[currentOffset++];
    }

    public int readUnsignedWord() {
        currentOffset += 2;
        return ((buffer[currentOffset - 2] & 0xff) << 8) + (buffer[currentOffset - 1] & 0xff);
    }

    public int readSignedWord() {
        currentOffset += 2;
        int i = ((buffer[currentOffset - 2] & 0xff) << 8) + (buffer[currentOffset - 1] & 0xff);
        if (i > 32767) {
            i -= 0x10000;
        }
        return i;
    }

    public int read3Bytes() {
        currentOffset += 3;
        return ((buffer[currentOffset - 3] & 0xff) << 16) + ((buffer[currentOffset - 2] & 0xff) << 8) + (buffer[currentOffset - 1] & 0xff);
    }

    public int readDWord() {
        currentOffset += 4;
        return ((buffer[currentOffset - 4] & 0xff) << 24) + ((buffer[currentOffset - 3] & 0xff) << 16) + ((buffer[currentOffset - 2] & 0xff) << 8) + (buffer[currentOffset - 1] & 0xff);
    }

    public long readQWord() {
        long l = (long) readDWord() & 0xffffffffL;
        long l1 = (long) readDWord() & 0xffffffffL;
        return (l << 32) + l1;
    }

    public String readString() {
        int i = currentOffset;
        while (buffer[currentOffset++] != 10) ;
        return new String(buffer, i, currentOffset - i - 1);
    }

    public byte[] readBytes() {
        int i = currentOffset;
        while (buffer[currentOffset++] != 10) ;
        byte abyte0[] = new byte[currentOffset - i - 1];
        for (int j = i; j < currentOffset - 1; j++) {
            abyte0[j - i] = buffer[j];
        }
        return abyte0;
    }

    public void readBytes(int i, int j, byte abyte0[]) {
        for (int l = j; l < j + i; l++) {
            abyte0[l] = buffer[currentOffset++];
        }
    }

    public void initBitAccess() {
        bitPosition = currentOffset * 8;
    }

    public int readBits(int i) {
        int k = bitPosition >> 3;
        int l = 8 - (bitPosition & 7);
        int i1 = 0;
        bitPosition += i;
        for (; i > l; l = 8) {
            i1 += (buffer[k++] & anIntArray1409[l]) << i - l;
            i -= l;
        }

        if (i == l) {
            i1 += buffer[k] & anIntArray1409[l];
        } else {
            i1 += buffer[k] >> l - i & anIntArray1409[i];
        }
        return i1;
    }

    public void finishBitAccess() {
        currentOffset = (bitPosition + 7) / 8;
    }

    public int method421() {
        int i = buffer[currentOffset] & 0xff;
        if (i < 128) {
            return readUnsignedByte() - 64;
        } else {
            return readUnsignedWord() - 49152;
        }
    }

    public int method422() {
        int i = buffer[currentOffset] & 0xff;
        if (i < 128) {
            return readUnsignedByte();
        } else {
            return readUnsignedWord() - 32768;
        }
    }

    public void doKeys() {
        int i = currentOffset;
        currentOffset = 0;
        byte abyte0[] = new byte[i];
        readBytes(i, 0, abyte0);
        BigInteger biginteger2 = new BigInteger(abyte0);
        BigInteger biginteger3 = biginteger2;
        byte abyte1[] = biginteger3.toByteArray();
        currentOffset = 0;
        writeWordBigEndian(abyte1.length);
        writeBytes(abyte1, abyte1.length, 0);
    }

    public void method424(int i) {
        buffer[currentOffset++] = (byte) (-i);

    }

    public void method425(int i) {
        buffer[currentOffset++] = (byte) (128 - i);
    }

    public int method426() {
        return buffer[currentOffset++] - 128 & 0xff;
    }

    public int method427() {
        return -buffer[currentOffset++] & 0xff;
    }

    public int method428() {
        return 128 - buffer[currentOffset++] & 0xff;
    }

    public byte method429() {
        return (byte) (-buffer[currentOffset++]);
    }

    public byte method430() {
        return (byte) (128 - buffer[currentOffset++]);
    }

    public void method431(int i) {
        buffer[currentOffset++] = (byte) i;
        buffer[currentOffset++] = (byte) (i >> 8);
    }

    public void method432(int j) {
        buffer[currentOffset++] = (byte) (j >> 8);
        buffer[currentOffset++] = (byte) (j + 128);
    }

    public void method433(int i) {
        buffer[currentOffset++] = (byte) (i + 128);
        buffer[currentOffset++] = (byte) (i >> 8);
    }
    
    public int method434(byte byte0) {
        currentOffset += 2;
        if (byte0 != aByte1399) {
            return 3;
        } else {
            return ((buffer[currentOffset - 1] & 0xff) << 8) + (buffer[currentOffset - 2] & 0xff);
        }
    }

    public int method435() {
        currentOffset += 2;
        return ((buffer[currentOffset - 2] & 0xff) << 8) + (buffer[currentOffset - 1] - 128 & 0xff);
    }

    public int method436() {
        currentOffset += 2;
        return ((buffer[currentOffset - 1] & 0xff) << 8) + (buffer[currentOffset - 2] - 128 & 0xff);
    }

    public int method437() {
        currentOffset += 2;
        int j = ((buffer[currentOffset - 1] & 0xff) << 8) + (buffer[currentOffset - 2] & 0xff);
        if (j > 32767) {
            j -= 0x10000;
        }
        return j;
    }

    public int method438() {
        currentOffset += 2;
        int j = ((buffer[currentOffset - 1] & 0xff) << 8) + (buffer[currentOffset - 2] - 128 & 0xff);
        if (j > 32767) {
            j -= 0x10000;
        }
        return j;
    }

    public int method439(byte byte0) {
        if (byte0 != 41) {
            return 3;
        } else {
            currentOffset += 4;
            return ((buffer[currentOffset - 2] & 0xff) << 24) + ((buffer[currentOffset - 1] & 0xff) << 16) + ((buffer[currentOffset - 4] & 0xff) << 8) + (buffer[currentOffset - 3] & 0xff);
        }
    }

    public int method440() {
        currentOffset += 4;
        return ((buffer[currentOffset - 3] & 0xff) << 24) + ((buffer[currentOffset - 4] & 0xff) << 16) + ((buffer[currentOffset - 1] & 0xff) << 8) + (buffer[currentOffset - 2] & 0xff);
    }

    public void method441(int i, byte abyte0[], int j) {
        for (int k = (i + j) - 1; k >= i; k--) {
            buffer[currentOffset++] = (byte) (abyte0[k] + 128);
        }

    }

    public void method442(int i, int j, byte abyte0[]) {
        for (int k = (j + i) - 1; k >= j; k--) {
            abyte0[k] = buffer[currentOffset++];
        }
    }
}
