package client;


public class Animable_Sub5 extends Animable {
    
    private byte aByte1598;
    private int anInt1599;
    int anIntArray1600[];
    int anInt1601;
    int anInt1602;
    private int anInt1603;
    private int anInt1604;
    private int anInt1605;
    private int anInt1606;
    private Animation aAnimation_1607;
    private int anInt1608;
    public static client clientInstance;
    private int anInt1610;
    private int anInt1611;
    private int anInt1612;
    private int anInt1613;
    
    public Animable_Sub5(int i, int j, int k, int l, byte byte0, int i1, int j1, int k1, int l1, boolean flag) {
        aByte1598 = 7;
        if (byte0 != aByte1598) {
            for (int i2 = 1; i2 > 0; i2++);
        }
        anInt1610 = i;
        anInt1611 = k;
        anInt1612 = j;
        anInt1603 = j1;
        anInt1604 = l;
        anInt1605 = i1;
        anInt1606 = k1;
        if (l1 != -1) {
            aAnimation_1607 = Animation.anims[l1];
            anInt1599 = 0;
            anInt1608 = client.loopCycle;
            if (flag && aAnimation_1607.anInt356 != -1) {
                anInt1599 = (int) (Math.random() * (double) aAnimation_1607.frameCount);
                anInt1608 -= (int) (Math.random() * (double) aAnimation_1607.getAnimationDelay(anInt1599));
            }
        }
        ObjectDef class46 = ObjectDef.forID(anInt1610);
        anInt1601 = class46.anInt774;
        anInt1602 = class46.anInt749;
        anIntArray1600 = class46.childrenIDs;
    }
    public final Model getRotatedModel() {
        int j = -1;
        if (aAnimation_1607 != null) {
            int k = client.loopCycle - anInt1608;
            if (k > 100 && aAnimation_1607.anInt356 > 0) {
                k = 100;
            }
            while (k > aAnimation_1607.getAnimationDelay(anInt1599)) {
                k -= aAnimation_1607.getAnimationDelay(anInt1599);
                anInt1599++;
                if (anInt1599 < aAnimation_1607.frameCount) {
                    continue;
                }
                anInt1599 -= aAnimation_1607.anInt356;
                if (anInt1599 >= 0 && anInt1599 < aAnimation_1607.frameCount) {
                    continue;
                }
                aAnimation_1607 = null;
                break;
            }
            anInt1608 = client.loopCycle - k;
            if (aAnimation_1607 != null) {
                j = aAnimation_1607.animationFrames[anInt1599];
            }
        }
        ObjectDef class46;
        if (anIntArray1600 != null) {
            class46 = method457();
        } else {
            class46 = ObjectDef.forID(anInt1610);
        }
        if (class46 == null) {
            return null;
        } else {
            Model class30_sub2_sub4_sub6 = class46.method578(anInt1611, anInt1612, anInt1603, anInt1604, anInt1605, anInt1606, j);
            return class30_sub2_sub4_sub6;
        }
    }

    public final ObjectDef method457() {
        int i = -1;
        if (anInt1601 != -1) {
            VarBit class37 = VarBit.cache[anInt1601];
            int k = class37.anInt648;
            int l = class37.anInt649;
            int i1 = class37.anInt650;
            int j1 = client.anIntArray1232[i1 - l];
            i = clientInstance.variousSettings[k] >> l & j1;
        } else if (anInt1602 != -1) {
            i = clientInstance.variousSettings[anInt1602];
        }
        if (i < 0 || i >= anIntArray1600.length || anIntArray1600[i] == -1) {
            return null;
        } else {
            return ObjectDef.forID(anIntArray1600[i]);
        }
    }
}
