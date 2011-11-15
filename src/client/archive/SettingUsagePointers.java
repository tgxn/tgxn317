package client.archive;

// Fully Renamed - gamerx - 15.11.11

import client.data.JagexArchive;
import client.data.Stream;

// Documented - gamerx - 15.11.11

/**
 * Not 100% sure what this is used for, some kind of client settings.
 * Perhaps something to do with pointers or setting configuration.
 *
 * @knownas Class41, Varp
 * @author gamerx
 */
public class SettingUsagePointers {
    
    public static SettingUsagePointers usuagePointerCache[];
    public int usage;
    private static int cachePos;
    private static int cacheArray[];
    
    SettingUsagePointers() {
    }

    /**
     * Unpack configuration archive.
     *
     * @param jagexArchive The archive instance.
     */
    public static void unpackConfig(JagexArchive jagexArchive) {
        Stream varpData = new Stream(jagexArchive.getDataForName("varp.dat"));
        cachePos = 0;
        int cacheSize = varpData.readUnsignedWord();
        if (usuagePointerCache == null) {
            usuagePointerCache = new SettingUsagePointers[cacheSize];
        }
        if (cacheArray == null) {
            cacheArray = new int[cacheSize];
        }
        for (int pos = 0; pos < cacheSize; pos++) {
            if (usuagePointerCache[pos] == null) {
                usuagePointerCache[pos] = new SettingUsagePointers();
            }
            usuagePointerCache[pos].readValues(varpData, pos);
        }
        if (varpData.currentOffset != varpData.buffer.length) {
            System.out.println("varptype load mismatch");
        }
    }

    /**
     * Read values from the stream.
     *
     * @param stream The stram to read from.
     * @param i 
     */
    public void readValues(Stream stream, int i) {
        do {
            int packetType = stream.readUnsignedByte();
            if (packetType == 0) {
                return;
            }
            if (packetType == 1) {
                //anInt705 = stream.readUnsignedByte();
                stream.readUnsignedByte();
            } else if (packetType == 2) {
                //anInt706 = stream.readUnsignedByte();
                stream.readUnsignedByte();
            } else if (packetType == 3) {
                //aBoolean707 = true;
                cacheArray[cachePos++] = i;
            } else if (packetType == 4) {
                //aBoolean708 = false;
            } else if (packetType == 5) {
                usage = stream.readUnsignedWord();
            } else if (packetType == 6) {
                //aBoolean710 = true;
            } else if (packetType == 7) {
                //anInt711 = stream.readDWord();
                stream.readDWord();
            } else if (packetType == 8) {
                //anInt712 = 1;
                //aBoolean713 = true;
            } else if (packetType == 10) {
                //aString704 = stream.readString();
                stream.readString();
            } else if (packetType == 11) {
                //aBoolean713 = true;
            } else if (packetType == 12) {
                //anInt714 = stream.readDWord();
                stream.readDWord();
            } else if (packetType == 13) {
                //anInt712 = 2;
            } else {
                System.out.println("Error unrecognised config code: " + packetType);
            }
        } while (true);
    }
}
