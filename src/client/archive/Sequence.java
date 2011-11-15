package client.archive;

// Mostly Renamed - gamerx - 15.11.11

import client.animation.AnimationFrame;
import client.data.JagexArchive;
import client.data.Stream;

/**
 * Unpack sequencing data.
 *
 * @knownas Animation, Class20
 * @author gamerx
 */
public class Sequence {
    
    public static Sequence[] animCache;
    public int frameCount;
    public int[] animationFrameID2;
    public int[] animationFrameID1;
    private int[] frameLengths;
    public int frameStep;
    public int[] animationFlowControl;
    public boolean aBoolean358;
    public int anInt359;
    public int anInt360;
    public int anInt361;
    public int anInt362;
    public int anInt363;
    public int priority;
    public int anInt365;
    
    public Sequence() {
        frameStep = -1;
        aBoolean358 = false;
        anInt359 = 5;
        anInt360 = -1;
        anInt361 = -1;
        anInt362 = 99;
        anInt363 = -1;
        priority = -1;
        anInt365 = 2;
    }

    /**
     * Unpack animation sequencing archive.
     *
     * @param jagexArchive The archive instance.
     */
    public static void unpackConfig(JagexArchive jagexArchive) {
        Stream sequenceData = new Stream(jagexArchive.getDataForName("seq.dat"));
        int length = sequenceData.readUnsignedWord();
        if (animCache == null) {
            animCache = new Sequence[length];
        }
        for (int j = 0; j < length; j++) {
            if (animCache[j] == null) {
                animCache[j] = new Sequence();
            }
            animCache[j].readValues(sequenceData);
        }
    }

    /**
     * Get length of a defined frame.
     *
     * @param frame The frame to get the length for.
     * @return The frames length.
     */
    public int getFrameLength(int frame) {
        int length = frameLengths[frame];
        if (length == 0) {
            AnimationFrame animationFrame = AnimationFrame.forID(animationFrameID2[frame]);
            if (animationFrame != null) {
                length = frameLengths[frame] = animationFrame.displayLength;
            }
        }
        if (length == 0) {
            length = 1;
        }
        return length;
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
                break;
            }
            if (packetType == 1) {
                frameCount = stream.readUnsignedByte();
                animationFrameID2 = new int[frameCount];
                animationFrameID1 = new int[frameCount];
                frameLengths = new int[frameCount];
                for (int pos = 0; pos < frameCount; pos++) {
                    animationFrameID2[pos] = stream.readUnsignedWord();
                    animationFrameID1[pos] = stream.readUnsignedWord();
                    if (animationFrameID1[pos] == 65535) {
                        animationFrameID1[pos] = -1;
                    }
                    frameLengths[pos] = stream.readUnsignedWord();
                }
            } else if (packetType == 2) {
                frameStep = stream.readUnsignedWord();
            } else if (packetType == 3) {
                int flowLength = stream.readUnsignedByte();
                animationFlowControl = new int[flowLength + 1];
                for (int pos = 0; pos < flowLength; pos++) {
                    animationFlowControl[pos] = stream.readUnsignedByte();
                }
                animationFlowControl[flowLength] = 0x98967f;
            } else if (packetType == 4) {
                aBoolean358 = true;
            } else if (packetType == 5) {
                anInt359 = stream.readUnsignedByte();
            } else if (packetType == 6) {
                anInt360 = stream.readUnsignedWord();
            } else if (packetType == 7) {
                anInt361 = stream.readUnsignedWord();
            } else if (packetType == 8) {
                anInt362 = stream.readUnsignedByte();
            } else if (packetType == 9) {
                anInt363 = stream.readUnsignedByte();
            } else if (packetType == 10) {
                priority = stream.readUnsignedByte();
            } else if (packetType == 11) {
                anInt365 = stream.readUnsignedByte();
            } else if (packetType == 12) {
                //anInt366 = stream.readDWord();
                stream.readDWord();
            } else {
                //@TODO Error handling.
                System.out.println("Error unrecognised seq config code: " + packetType);
            }
        } while (true);
        if (frameCount == 0) {
            frameCount = 1;
            animationFrameID2 = new int[1];
            animationFrameID2[0] = -1;
            animationFrameID1 = new int[1];
            animationFrameID1[0] = -1;
            frameLengths = new int[1];
            frameLengths[0] = -1;
        }
        if (anInt363 == -1) {
            if (animationFlowControl != null) {
                anInt363 = 2;
            } else {
                anInt363 = 0;
            }
        }
        if (priority == -1) {
            if (animationFlowControl != null) {
                priority = 2;
                return;
            }
            priority = 0;
        }
    }
}
