package client;

// Fully refactored.

import client.fileio.JagexArchive;



public class VarBit {
    
    public static VarBit cache[];
    public int configId;
    public int leastSignificantBit;
    public int mostSignificantBit;
    
    public VarBit() {
    }
        
    public static void unpackConfig(JagexArchive jagexArchive) {
        Stream stream = new Stream(jagexArchive.getDataForName("varbit.dat"));
        int cacheSize = stream.readUnsignedWord();
        if (cache == null) {
            cache = new VarBit[cacheSize];
        }
        for (int j = 0; j < cacheSize; j++) {
            if (cache[j] == null) {
                cache[j] = new VarBit();
            }
            cache[j].readValues(stream);
        }
        if (stream.currentOffset != stream.buffer.length) {
            System.out.println("varbit load mismatch");
        }
    }

    public void readValues(Stream stream) {
        do {
            int opCode = stream.readUnsignedByte();
            if (opCode == 0) {
                return;
            }
            if (opCode == 1) {
                configId = stream.readUnsignedWord();
                leastSignificantBit = stream.readUnsignedByte();
                mostSignificantBit = stream.readUnsignedByte();
            } else if (opCode == 10) {
                stream.readString();
            //} else if (opCode == 2) {
            } else if (opCode == 3) {
                stream.readDWord();
            } else if (opCode == 4) {
                stream.readDWord();
            } else {
                System.out.println("Error unrecognised config code: " + opCode);
            }
        } while (true);
    }
}
