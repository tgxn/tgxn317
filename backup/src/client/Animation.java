package client;


public class Animation {
    
    private int anInt348;
    private boolean aBoolean349;
    public static int anInt350;
    public static Animation anims[];
    public int frameCount;
    public int animationFrames[];
    public int anIntArray354[];
    private int anIntArray355[];
    public int anInt356;
    public int anIntArray357[];
    public boolean aBoolean358;
    public int anInt359;
    public int anInt360;
    public int anInt361;
    public int anInt362;
    public int anInt363;
    public int anInt364;
    public int anInt365;
    public int anInt366;
    public static int anInt367;
    
    public Animation() {
        anInt348 = 9;
        aBoolean349 = false;
        anInt356 = -1;
        aBoolean358 = false;
        anInt359 = 5;
        anInt360 = -1;
        anInt361 = -1;
        anInt362 = 99;
        anInt363 = -1;
        anInt364 = -1;
        anInt365 = 2;
    }
    
    public static void unpackConfig(JagexArchive class44) {
        Packet stream = new Packet(class44.getDataForName("seq.dat"));
        anInt350 = stream.readUnsignedWord();
        if (anims == null) {
            anims = new Animation[anInt350];
        }
        for (int j = 0; j < anInt350; j++) {
            if (anims[j] == null) {
                anims[j] = new Animation();
            }
            anims[j].readValues(stream);
        }
    }

    public int getAnimationDelay(int i) {
        int j = anIntArray355[i];

        if (j == 0) {
            AnimationHeader class36 = AnimationHeader.method531(animationFrames[i]);
            if (class36 != null) {
                j = anIntArray355[i] = class36.anInt636;
            }
        }
        if (j == 0) {
            j = 1;
        }
        return j;
    }

    public void readValues(Packet class30_sub2_sub2) {
        do {
            int i = class30_sub2_sub2.readUnsignedByte();
            if (i == 0) {
                break;
            }
            if (i == 1) {
                frameCount = class30_sub2_sub2.readUnsignedByte();
                animationFrames = new int[frameCount];
                anIntArray354 = new int[frameCount];
                anIntArray355 = new int[frameCount];
                for (int j = 0; j < frameCount; j++) {
                    animationFrames[j] = class30_sub2_sub2.readUnsignedWord();
                    anIntArray354[j] = class30_sub2_sub2.readUnsignedWord();
                    if (anIntArray354[j] == 65535) {
                        anIntArray354[j] = -1;
                    }
                    anIntArray355[j] = class30_sub2_sub2.readUnsignedWord();
                }

            } else if (i == 2) {
                anInt356 = class30_sub2_sub2.readUnsignedWord();
            } else if (i == 3) {
                int k = class30_sub2_sub2.readUnsignedByte();
                anIntArray357 = new int[k + 1];
                for (int l = 0; l < k; l++) {
                    anIntArray357[l] = class30_sub2_sub2.readUnsignedByte();
                }

                anIntArray357[k] = 0x98967f;
            } else if (i == 4) {
                aBoolean358 = true;
            } else if (i == 5) {
                anInt359 = class30_sub2_sub2.readUnsignedByte();
            } else if (i == 6) {
                anInt360 = class30_sub2_sub2.readUnsignedWord();
            } else if (i == 7) {
                anInt361 = class30_sub2_sub2.readUnsignedWord();
            } else if (i == 8) {
                anInt362 = class30_sub2_sub2.readUnsignedByte();
            } else if (i == 9) {
                anInt363 = class30_sub2_sub2.readUnsignedByte();
            } else if (i == 10) {
                anInt364 = class30_sub2_sub2.readUnsignedByte();
            } else if (i == 11) {
                anInt365 = class30_sub2_sub2.readUnsignedByte();
            } else if (i == 12) {
                anInt366 = class30_sub2_sub2.readDWord();
            } else {
                System.out.println("Error unrecognised seq config code: " + i);
            }
        } while (true);
        if (frameCount == 0) {
            frameCount = 1;
            animationFrames = new int[1];
            animationFrames[0] = -1;
            anIntArray354 = new int[1];
            anIntArray354[0] = -1;
            anIntArray355 = new int[1];
            anIntArray355[0] = -1;
        }
        if (anInt363 == -1) {
            if (anIntArray357 != null) {
                anInt363 = 2;
            } else {
                anInt363 = 0;
            }
        }
        if (anInt364 == -1) {
            if (anIntArray357 != null) {
                anInt364 = 2;
                return;
            }
            anInt364 = 0;
        }
    }
}
