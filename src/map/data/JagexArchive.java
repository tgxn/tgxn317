package map.data;

// Fully Renamed - gamerx - 14.11.11

public final class JagexArchive {
    
    public byte[] outputData;
    public int readSize;
    public int[] myNameIndexes;
    public int[] myFileSizes;
    public int[] myOnDiskFileSizes;
    public int[] myFileOffsets;
    private boolean isCompressed;
    
    public JagexArchive(byte datFile[]) {
        Stream stream = new Stream(datFile);
        int archiveSize = stream.getShortInt();
        int actualFileSize = stream.getShortInt();
        if (actualFileSize != archiveSize) {
            byte out[] = new byte[archiveSize];
            client.data.BZ2InputStream.decompressBuffer(out, archiveSize, datFile, actualFileSize, 6);
            outputData = out;
            stream = new Stream(outputData);
            isCompressed = true;
        } else {
            outputData = datFile;
            isCompressed = false;
        }
        readSize = stream.getShort();
        myNameIndexes = new int[readSize];
        myFileSizes = new int[readSize];
        myOnDiskFileSizes = new int[readSize];
        myFileOffsets = new int[readSize];
        int k = stream.currentOffset + readSize * 10;
        for (int pos = 0; pos < readSize; pos++) {
            myNameIndexes[pos] = stream.getInt();
            myFileSizes[pos] = stream.getShortInt();
            myOnDiskFileSizes[pos] = stream.getShortInt();
            myFileOffsets[pos] = k;
            k += myOnDiskFileSizes[pos];
        }
    }
    
    public byte[] getDataForName(String archiveToLoad) {
        int intDatToLoad = 0;
        archiveToLoad = archiveToLoad.toUpperCase();
        for (int pos = 0; pos < archiveToLoad.length(); pos++) {
            intDatToLoad = (intDatToLoad * 61 + archiveToLoad.charAt(pos)) - 32;
        }
        byte loadedData[] = null;
        for (int readLoop = 0; readLoop < readSize; readLoop++) {
            if (myNameIndexes[readLoop] == intDatToLoad) {
                if (loadedData == null) {
                    loadedData = new byte[myFileSizes[readLoop]];
                }
                if (!isCompressed) {
                    client.data.BZ2InputStream.decompressBuffer(loadedData, myFileSizes[readLoop], outputData, myOnDiskFileSizes[readLoop], myFileOffsets[readLoop]);
                } else {
                    for (int pos = 0; pos < myFileSizes[readLoop]; pos++) {
                        loadedData[pos] = outputData[myFileOffsets[readLoop] + pos];
                    }
                }
                return loadedData;
            }
        }
        return null;
    }
}
