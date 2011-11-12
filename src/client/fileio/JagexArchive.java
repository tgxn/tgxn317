package client.fileio;

//class44
// Fully Refactored.

import client.bz2.BZ2InputStream;
import client.Stream;

public final class JagexArchive {
    
    public byte[] outputData;
    public int dataSize;
    public int[] myNameIndexes;
    public int[] myFileSizes;
    public int[] myOnDiskFileSizes;
    public int[] myFileOffsets;
    private boolean isCompressed;
    
    public JagexArchive(byte in[]) {
        Stream stream = new Stream(in);
        int i = stream.read3Bytes();
        int j = stream.read3Bytes();
        if (j != i) {
            byte out[] = new byte[i];
            BZ2InputStream.decompressBuffer(out, i, in, j, 6);
            outputData = out;
            stream = new Stream(outputData);
            isCompressed = true;
        } else {
            outputData = in;
            isCompressed = false;
        }
        dataSize = stream.readUnsignedWord();
        myNameIndexes = new int[dataSize];
        myFileSizes = new int[dataSize];
        myOnDiskFileSizes = new int[dataSize];
        myFileOffsets = new int[dataSize];
        int k = stream.currentOffset + dataSize * 10;
        for (int l = 0; l < dataSize; l++) {
            myNameIndexes[l] = stream.readDWord();
            myFileSizes[l] = stream.read3Bytes();
            myOnDiskFileSizes[l] = stream.read3Bytes();
            myFileOffsets[l] = k;
            k += myOnDiskFileSizes[l];
        }
    }
    
    public byte[] getDataForName(String s) {
        byte byteData[];
        int i = 0;
        s = s.toUpperCase();
        for (int j = 0; j < s.length(); j++) {
            i = (i * 61 + s.charAt(j)) - 32;
        }
        for (int position = 0; position < dataSize; position++) {
            if (myNameIndexes[position] == i) {
                byteData = new byte[myFileSizes[position]];
                if (!isCompressed) {
                    BZ2InputStream.decompressBuffer(byteData, myFileSizes[position], outputData, myOnDiskFileSizes[position], myFileOffsets[position]);
                } else {
                    for (int l = 0; l < myFileSizes[position]; l++) {
                        byteData[l] = outputData[myFileOffsets[position] + l];
                    }
                }
                return byteData;
            }
        }
        return null;
    }
}
