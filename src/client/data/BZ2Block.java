package client.data;

// Fully Refactored.

class BZ2Block {
    
    byte input[];
    int nextIn;
    int compressedSize;
    int totalInLo32;
    int totalInHi32;
    byte output[];
    int availOut;
    int decompressedSize;
    int totalOutLo32;
    int totalOutHi32;
    byte stateOutCH;
    int stateOutLen;
    boolean randomized;
    int bsBuff;
    int bsLive;
    int blockSize100k;
    int blockNo;
    int origPtr;
    int anInt581;
    int anInt582;
    int unzftab[];
    int anInt584;
    int cftab[];
    //int anIntArray586[];
    public static int ll8[];
    int nInUse;
    boolean inUse[];
    boolean inUse16[];
    byte seqToUnseq[];
    byte yy[];
    int mtf16[];
    byte selector[];
    byte selectorMtf[];
    byte len[][];
    int limit[][];
    int base[][];
    int perm[][];
    int minLens[];
    int anInt601;
    
    BZ2Block() {
        unzftab = new int[256];
        cftab = new int[257];
        //anIntArray586 = new int[257];
        inUse = new boolean[256];
        inUse16 = new boolean[16];
        seqToUnseq = new byte[256];
        yy = new byte[4096];
        mtf16 = new int[16];
        selector = new byte[18002];
        selectorMtf = new byte[18002];
        len = new byte[6][258];
        limit = new int[6][258];
        base = new int[6][258];
        perm = new int[6][258];
        minLens = new int[6];
    }
}
