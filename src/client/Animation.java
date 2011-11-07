package client;


public class Animation {
    
    public static Animation animCache[];
    public int frameCount;
    public int animationFrameID2[];
    public int animationFrameID1[];
    private int frameLengths[];
    public int frameStep;
    public int animationFlowControl[];
    public boolean aBoolean358;
    public int anInt359;
    public int anInt360;
    public int anInt361;
    public int anInt362;
    public int anInt363;
    public int priority;
    public int anInt365;
    public int anInt366;
    public static int anInt367;
    
    public Animation() {
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
    
    public static void unpackConfig(JagexArchive jagexArchive) {
        Stream stream = new Stream(jagexArchive.getDataForName("seq.dat"));
        int length = stream.readUnsignedWord();
        if (animCache == null) {
            animCache = new Animation[length];
        }
        for (int j = 0; j < length; j++) {
            if (animCache[j] == null) {
                animCache[j] = new Animation();
            }
            animCache[j].readValues(stream);
        }
    }

    public int getFrameLength(int i) {
        int j = frameLengths[i];
        if (j == 0) {
            AnimationFrame animationFrame = AnimationFrame.forID(animationFrameID2[i]);
            if (animationFrame != null) {
                j = frameLengths[i] = animationFrame.displayLength;
            }
        }
        if (j == 0) {
            j = 1;
        }
        return j;
    }

    public void readValues(Stream stream) {
        do {
            int i = stream.readUnsignedByte();
            if (i == 0) {
                break;
            }
            if (i == 1) {
                frameCount = stream.readUnsignedByte();
                animationFrameID2 = new int[frameCount];
                animationFrameID1 = new int[frameCount];
                frameLengths = new int[frameCount];
                for (int j = 0; j < frameCount; j++) {
                    animationFrameID2[j] = stream.readUnsignedWord();
                    animationFrameID1[j] = stream.readUnsignedWord();
                    if (animationFrameID1[j] == 65535) {
                        animationFrameID1[j] = -1;
                    }
                    frameLengths[j] = stream.readUnsignedWord();
                }

            } else if (i == 2) {
                frameStep = stream.readUnsignedWord();
            } else if (i == 3) {
                int k = stream.readUnsignedByte();
                animationFlowControl = new int[k + 1];
                for (int l = 0; l < k; l++) {
                    animationFlowControl[l] = stream.readUnsignedByte();
                }

                animationFlowControl[k] = 0x98967f;
            } else if (i == 4) {
                aBoolean358 = true;
            } else if (i == 5) {
                anInt359 = stream.readUnsignedByte();
            } else if (i == 6) {
                anInt360 = stream.readUnsignedWord();
            } else if (i == 7) {
                anInt361 = stream.readUnsignedWord();
            } else if (i == 8) {
                anInt362 = stream.readUnsignedByte();
            } else if (i == 9) {
                anInt363 = stream.readUnsignedByte();
            } else if (i == 10) {
                priority = stream.readUnsignedByte();
            } else if (i == 11) {
                anInt365 = stream.readUnsignedByte();
            } else if (i == 12) {
                anInt366 = stream.readDWord();
            } else {
                System.out.println("Error unrecognised seq config code: " + i);
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
