package client;

public class AnimableObject extends Animable {
    
    private int delay;
    private final int children[];
    private final int anInt1601;
    private final int anInt1602;
    private final int anInt1603;
    private final int anInt1604;
    private final int anInt1605;
    private final int anInt1606;
    private Animation objAnim;
    private int loopCycle;
    public static client clientInstance;
    private int id;
    private int anInt1611;
    private int anInt1612;
    
    public AnimableObject(int i, int j, int k, int l, byte byte0, int i1, int j1, int k1, int animationID, boolean flag) {
        id = i;
        anInt1611 = k;
        anInt1612 = j;
        anInt1603 = j1;
        anInt1604 = l;
        anInt1605 = i1;
        anInt1606 = k1;
        if (animationID != -1) {
            objAnim = Animation.anims[animationID];
            delay = 0;
            loopCycle = client.loopCycle;
            if (flag && objAnim.anInt356 != -1) {
                delay = (int) (Math.random() * (double) objAnim.frameCount);
                loopCycle -= (int) (Math.random() * (double) objAnim.getAnimationDelay(delay));
            }
        }
        ObjectDef object = ObjectDef.forID(id);
        anInt1601 = object.anInt774;
        anInt1602 = object.anInt749;
        children = object.childrenIDs;
    }
    
    @Override
    public Model getRotatedModel() {
        int j = -1;
        if (objAnim != null) {
            int k = client.loopCycle - loopCycle;
            if (k > 100 && objAnim.anInt356 > 0) {
                k = 100;
            }
            while (k > objAnim.getAnimationDelay(delay)) {
                k -= objAnim.getAnimationDelay(delay);
                delay++;
                if (delay < objAnim.frameCount) {
                    continue;
                }
                delay -= objAnim.anInt356;
                if (delay >= 0 && delay < objAnim.frameCount) {
                    continue;
                }
                objAnim = null;
                break;
            }
            loopCycle = client.loopCycle - k;
            if (objAnim != null) {
                j = objAnim.animationFrames[delay];
            }
        }
        ObjectDef object;
        if (children != null) {
            object = getChild();
        } else {
            object = ObjectDef.forID(id);
        }
        if (object == null) {
            return null;
        } else {
            return object.method578(anInt1611, anInt1612, anInt1603, anInt1604, anInt1605, anInt1606, j);
        }
    }

    private ObjectDef getChild() {
        int objid = -1;
        if (anInt1601 != -1) {
            VarBit varBit = VarBit.cache[anInt1601];
            int k = varBit.anInt648;
            int l = varBit.anInt649;
            int i1 = varBit.anInt650;
            int j1 = client.anIntArray1232[i1 - l];
            objid = clientInstance.variousSettings[k] >> l & j1;
        } else if (anInt1602 != -1) {
            objid = clientInstance.variousSettings[anInt1602];
        }
        if (objid < 0 || objid >= children.length || children[objid] == -1) {
            return null;
        } else {
            return ObjectDef.forID(children[objid]);
        }
    }
}
