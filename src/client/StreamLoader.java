package client;

public final class StreamLoader {
    
    public byte aByteArray726[];
    public int dataSize;
    public int anIntArray728[];
    public int anIntArray729[];
    public int anIntArray730[];
    public int anIntArray731[];
    private boolean aBoolean732;
    
    public StreamLoader(byte abyte0[]) {
        method570(abyte0);
        return;
    }

    private void method570(byte abyte0[]) {
        Stream stream = new Stream(abyte0);
        int i = stream.read3Bytes();
        int j = stream.read3Bytes();
        if (j != i) {
            byte abyte1[] = new byte[i];
            BZIP2Decompressor.decompress(abyte1, i, abyte0, j, 6);
            aByteArray726 = abyte1;
            stream = new Stream(aByteArray726);
            aBoolean732 = true;
        } else {
            aByteArray726 = abyte0;
            aBoolean732 = false;
        }
        dataSize = stream.readUnsignedWord();
        anIntArray728 = new int[dataSize];
        anIntArray729 = new int[dataSize];
        anIntArray730 = new int[dataSize];
        anIntArray731 = new int[dataSize];
        int k = stream.currentOffset + dataSize * 10;
        for (int l = 0; l < dataSize; l++) {
            anIntArray728[l] = stream.readDWord();
            anIntArray729[l] = stream.read3Bytes();
            anIntArray730[l] = stream.read3Bytes();
            anIntArray731[l] = k;
            k += anIntArray730[l];
        }
    }

    public byte[] getDataForName(String s) {
        byte abyte0[];
        int i = 0;
        s = s.toUpperCase();
        for (int j = 0; j < s.length(); j++) {
            i = (i * 61 + s.charAt(j)) - 32;
        }
        for (int k = 0; k < dataSize; k++) {
            if (anIntArray728[k] == i) {
                abyte0 = new byte[anIntArray729[k]];
                if (!aBoolean732) {
                    BZIP2Decompressor.decompress(abyte0, anIntArray729[k], aByteArray726, anIntArray730[k], anIntArray731[k]);
                } else {
                    for (int l = 0; l < anIntArray729[k]; l++) {
                        abyte0[l] = aByteArray726[anIntArray731[k] + l];
                    }
                }
                return abyte0;
            }
        }
        return null;
    }
}