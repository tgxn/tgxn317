package client.archive;

// Fully refactored - gamerx - 14.11.11

import client.data.JagexArchive;
import client.data.Stream;

/**
 * Unpacks Var Bit archive.
 *
 * @knownas Class37
 * @author gamerx
 */
public class VarBit {
    
    public static VarBit cache[];
    public int configId;
    public int leastSignificantBit;
    public int mostSignificantBit;
    
    public VarBit() {
    }

    /**
     * Unpack varbit archive.
     *
     * @param jagexArchive The archive instance.
     */
    public static void unpackConfig(JagexArchive jagexArchive) {
        Stream varBitData = new Stream(jagexArchive.getDataForName("varbit.dat"));
        int cacheSize = varBitData.readUnsignedWord();
        if (cache == null) {
            cache = new VarBit[cacheSize];
        }
        for (int pos = 0; pos < cacheSize; pos++) {
            if (cache[pos] == null) {
                cache[pos] = new VarBit();
            }
            cache[pos].readValues(varBitData);
        }
        if (varBitData.currentOffset != varBitData.buffer.length) {
            System.out.println("varbit load mismatch");
        }
    }

    /**
     * Read values from the stream.
     *
     * @param stream The stram to read from.
     */
    public void readValues(Stream stream) {
        do {
            int packetType = stream.readUnsignedByte();
            if (packetType == 0) {
                return;
            }
            if (packetType == 1) {
                configId = stream.readUnsignedWord();
                leastSignificantBit = stream.readUnsignedByte();
                mostSignificantBit = stream.readUnsignedByte();
            } else if (packetType == 10) {
                stream.readString();
            } else if (packetType == 3) {
                stream.readDWord();
            } else if (packetType == 4) {
                stream.readDWord();
            } else {
                System.out.println("Error unrecognised config code: " + packetType);
            }
        } while (true);
    }
}
