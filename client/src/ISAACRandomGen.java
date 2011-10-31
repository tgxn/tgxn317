
public final class ISAACRandomGen {
    
    private int count;
    private int results[];
    private int anIntArray336[];
    private int memory;
    private int lastResult;
    private int counter;
    
    public ISAACRandomGen(int ai[]) {
        anIntArray336 = new int[256];
        results = new int[256];
        System.arraycopy(ai, 0, results, 0, ai.length);
        initializeKeySet();
    }

    public final int getNextKey() {
        if (count-- == 0) {
            isaac();
            count = 255;
        }
        return results[count];
    }

    private void isaac() {
        lastResult += ++counter;
        for (int i = 0; i < 256; i++) {
            int j = anIntArray336[i];
            if ((i & 3) == 0) {
                memory ^= memory << 13;
            } else if ((i & 3) == 1) {
                memory ^= memory >>> 6;
            } else if ((i & 3) == 2) {
                memory ^= memory << 2;
            } else if ((i & 3) == 3) {
                memory ^= memory >>> 16;
            }
            memory += anIntArray336[i + 128 & 0xff];
            int k;
            anIntArray336[i] = k = anIntArray336[(j & 0x3fc) >> 2] + memory + lastResult;
            results[i] = lastResult = anIntArray336[(k >> 8 & 0x3fc) >> 2] + j;
        }

    }

    private void initializeKeySet() {
        int i1;
        int j1;
        int k1;
        int l1;
        int i2;
        int j2;
        int k2;
        int l = i1 = j1 = k1 = l1 = i2 = j2 = k2 = 0x9e3779b9;
        for (int i = 0; i < 4; i++) {
            l ^= i1 << 11;
            k1 += l;
            i1 += j1;
            i1 ^= j1 >>> 2;
            l1 += i1;
            j1 += k1;
            j1 ^= k1 << 8;
            i2 += j1;
            k1 += l1;
            k1 ^= l1 >>> 16;
            j2 += k1;
            l1 += i2;
            l1 ^= i2 << 10;
            k2 += l1;
            i2 += j2;
            i2 ^= j2 >>> 4;
            l += i2;
            j2 += k2;
            j2 ^= k2 << 8;
            i1 += j2;
            k2 += l;
            k2 ^= l >>> 9;
            j1 += k2;
            l += i1;
        }

        for (int j = 0; j < 256; j += 8) {
            l += results[j];
            i1 += results[j + 1];
            j1 += results[j + 2];
            k1 += results[j + 3];
            l1 += results[j + 4];
            i2 += results[j + 5];
            j2 += results[j + 6];
            k2 += results[j + 7];
            l ^= i1 << 11;
            k1 += l;
            i1 += j1;
            i1 ^= j1 >>> 2;
            l1 += i1;
            j1 += k1;
            j1 ^= k1 << 8;
            i2 += j1;
            k1 += l1;
            k1 ^= l1 >>> 16;
            j2 += k1;
            l1 += i2;
            l1 ^= i2 << 10;
            k2 += l1;
            i2 += j2;
            i2 ^= j2 >>> 4;
            l += i2;
            j2 += k2;
            j2 ^= k2 << 8;
            i1 += j2;
            k2 += l;
            k2 ^= l >>> 9;
            j1 += k2;
            l += i1;
            anIntArray336[j] = l;
            anIntArray336[j + 1] = i1;
            anIntArray336[j + 2] = j1;
            anIntArray336[j + 3] = k1;
            anIntArray336[j + 4] = l1;
            anIntArray336[j + 5] = i2;
            anIntArray336[j + 6] = j2;
            anIntArray336[j + 7] = k2;
        }

        for (int k = 0; k < 256; k += 8) {
            l += anIntArray336[k];
            i1 += anIntArray336[k + 1];
            j1 += anIntArray336[k + 2];
            k1 += anIntArray336[k + 3];
            l1 += anIntArray336[k + 4];
            i2 += anIntArray336[k + 5];
            j2 += anIntArray336[k + 6];
            k2 += anIntArray336[k + 7];
            l ^= i1 << 11;
            k1 += l;
            i1 += j1;
            i1 ^= j1 >>> 2;
            l1 += i1;
            j1 += k1;
            j1 ^= k1 << 8;
            i2 += j1;
            k1 += l1;
            k1 ^= l1 >>> 16;
            j2 += k1;
            l1 += i2;
            l1 ^= i2 << 10;
            k2 += l1;
            i2 += j2;
            i2 ^= j2 >>> 4;
            l += i2;
            j2 += k2;
            j2 ^= k2 << 8;
            i1 += j2;
            k2 += l;
            k2 ^= l >>> 9;
            j1 += k2;
            l += i1;
            anIntArray336[k] = l;
            anIntArray336[k + 1] = i1;
            anIntArray336[k + 2] = j1;
            anIntArray336[k + 3] = k1;
            anIntArray336[k + 4] = l1;
            anIntArray336[k + 5] = i2;
            anIntArray336[k + 6] = j2;
            anIntArray336[k + 7] = k2;
        }

        isaac();
        count = 256;
    }
}
