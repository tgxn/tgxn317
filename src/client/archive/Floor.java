package client.archive;

// Mostly Renamed - gamerx - 15.11.11

import client.data.Stream;
import client.data.JagexArchive;

/**
 * Floor configuration loader.
 *
 * @knownas Flo, Class22
 * @author gamerx
 */
public class Floor {

    //public static int anInt387;
    public static Floor floorCache[];
    public String floorInfoString;
    public int groundColor;
    public int groundTexture;
    public boolean aBoolean392;
    public boolean occlude;
    public int groundSaturation;
    public int groundHue;
    public int groundLuminosity;
    public int hue2;
    public int anInt398;
    public int mapColor;
    
    private Floor() {
        groundTexture = -1;
        occlude = true;
    }

    /**
     * Unpack floor data archive.
     *
     * @param jagexArchive The archive instance.
     */
    public static void unpackConfig(JagexArchive jagexArchive) {
        Stream floorData = new Stream(jagexArchive.getDataForName("flo.dat"));
        int cacheSize = floorData.readUnsignedWord();
        if (floorCache == null) {
            floorCache = new Floor[cacheSize];
        }
        for (int j = 0; j < cacheSize; j++) {
            if (floorCache[j] == null) {
                floorCache[j] = new Floor();
            }
            floorCache[j].readValues(floorData);
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
                groundColor = stream.read3Bytes();
                rgb2hls(groundColor);
            } else if (packetType == 2) {
                groundTexture = stream.readUnsignedByte();
            } else if (packetType == 3) {
                //aBoolean392 = true;
            } else if (packetType == 5) {
                occlude = false;
            } else if (packetType == 6) {
                floorInfoString = stream.readString();
            } else if (packetType == 7) {
                int j = groundSaturation;
                int k = groundHue;
                int l = groundLuminosity;
                int i1 = hue2;
                int j1 = stream.read3Bytes();
                rgb2hls(j1);
                groundSaturation = j;
                groundHue = k;
                groundLuminosity = l;
                hue2 = i1;
                anInt398 = i1;
            } else {
                System.out.println("Error unrecognised config code: " + packetType);
            }
        } while (true);
    }

    private void rgb2hls(int i) {
        double d = (double) (i >> 16 & 0xff) / 256D;
        double d1 = (double) (i >> 8 & 0xff) / 256D;
        double d2 = (double) (i & 0xff) / 256D;
        double d3 = d;
        if (d1 < d3) {
            d3 = d1;
        }
        if (d2 < d3) {
            d3 = d2;
        }
        double d4 = d;
        if (d1 > d4) {
            d4 = d1;
        }
        if (d2 > d4) {
            d4 = d2;
        }
        double d5 = 0.0D;
        double d6 = 0.0D;
        double d7 = (d3 + d4) / 2D;
        if (d3 != d4) {
            if (d7 < 0.5D) {
                d6 = (d4 - d3) / (d4 + d3);
            }
            if (d7 >= 0.5D) {
                d6 = (d4 - d3) / (2D - d4 - d3);
            }
            if (d == d4) {
                d5 = (d1 - d2) / (d4 - d3);
            } else if (d1 == d4) {
                d5 = 2D + (d2 - d) / (d4 - d3);
            } else if (d2 == d4) {
                d5 = 4D + (d - d1) / (d4 - d3);
            }
        }
        d5 /= 6D;
        groundSaturation = (int) (d5 * 256D);
        groundHue = (int) (d6 * 256D);
        groundLuminosity = (int) (d7 * 256D);
        if (groundHue < 0) {
            groundHue = 0;
        } else if (groundHue > 255) {
            groundHue = 255;
        }
        if (groundLuminosity < 0) {
            groundLuminosity = 0;
        } else if (groundLuminosity > 255) {
            groundLuminosity = 255;
        }
        if (d7 > 0.5D) {
            anInt398 = (int) ((1.0D - d7) * d6 * 512D);
        } else {
            anInt398 = (int) (d7 * d6 * 512D);
        }
        if (anInt398 < 1) {
            anInt398 = 1;
        }
        hue2 = (int) (d5 * (double) anInt398);
        int k = (groundSaturation + (int) (Math.random() * 16D)) - 8;
        if (k < 0) {
            k = 0;
        } else if (k > 255) {
            k = 255;
        }
        int l = (groundHue + (int) (Math.random() * 48D)) - 24;
        if (l < 0) {
            l = 0;
        } else if (l > 255) {
            l = 255;
        }
        int i1 = (groundLuminosity + (int) (Math.random() * 48D)) - 24;
        if (i1 < 0) {
            i1 = 0;
        } else if (i1 > 255) {
            i1 = 255;
        }
        mapColor = method263(k, l, i1);
    }

    private int method263(int i, int j, int k) {
        if (k > 179) {
            j /= 2;
        }
        if (k > 192) {
            j /= 2;
        }
        if (k > 217) {
            j /= 2;
        }
        if (k > 243) {
            j /= 2;
        }
        int l = (i / 4 << 10) + (j / 32 << 7) + k / 2;
        return l;
    }
}
