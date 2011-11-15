package client.data;

// Fully Refactored - gamerx - 14.11.11
// Updated Naming - gamerx - 15.11.11

/**
 * Custom Jagex archive file loader.
 *
 * @knownas Class44,
 * @author gamerx
 */
public final class JagexArchive {
    
    private byte[] outputBuffer;
    private int archiveSize;
    private int[] dataIndexes;
    private int[] dataSizes;
    private int[] dataOnDiskSizes;
    private int[] dtaOffsets;
    private boolean isCacheCompressed;
    
    public JagexArchive(byte dataToLoad[]) {
        Stream stream = new Stream(dataToLoad);
        int outSize = stream.read3Bytes();
        int actualSize = stream.read3Bytes();
        if (actualSize != outSize) {
            byte out[] = new byte[outSize];
            BZ2InputStream.decompressBuffer(out, outSize, dataToLoad, actualSize, 6);
            outputBuffer = out;
            stream = new Stream(outputBuffer);
            isCacheCompressed = true;
        } else {
            outputBuffer = dataToLoad;
            isCacheCompressed = false;
        }
        archiveSize = stream.readUnsignedWord();
        dataIndexes = new int[archiveSize];
        dataSizes = new int[archiveSize];
        dataOnDiskSizes = new int[archiveSize];
        dtaOffsets = new int[archiveSize];
        int offset = stream.currentOffset + archiveSize * 10;
        for (int pos = 0; pos < archiveSize; pos++) {
            dataIndexes[pos] = stream.readDWord();
            dataSizes[pos] = stream.read3Bytes();
            dataOnDiskSizes[pos] = stream.read3Bytes();
            dtaOffsets[pos] = offset;
            offset += dataOnDiskSizes[pos];
        }
    }

    /**
     * Retrieve data from instance of @JagexArchive.
     *
     * @param name Name of data to load.
     * @return Data that was loaded.
     */
    public byte[] getDataForName(String name) {
        byte byteData[];
        int nameID = 0;
        name = name.toUpperCase();
        for (int atChar = 0; atChar < name.length(); atChar++) {
            nameID = (nameID * 61 + name.charAt(atChar)) - 32;
        }
        for (int position = 0; position < archiveSize; position++) {
            if (dataIndexes[position] == nameID) {
                byteData = new byte[dataSizes[position]];
                if (!isCacheCompressed) {
                    BZ2InputStream.decompressBuffer(byteData, dataSizes[position], outputBuffer, dataOnDiskSizes[position], dtaOffsets[position]);
                } else {
                    for (int pos = 0; pos < dataSizes[position]; pos++) {
                        byteData[pos] = outputBuffer[dtaOffsets[position] + pos];
                    }
                }
                return byteData;
            }
        }
        return null;
    }
}
