package client.animation;

import client.archive.Sequence;
import client.Client;
import client.model.Model;
import client.object.ObjectDef;
import client.archive.VarBit;

public class AnimableObject extends Entity {
    
    private int delay;
    private final int children[];
    private final int anInt1601;
    private final int anInt1602;
    private final int anInt1603;
    private final int anInt1604;
    private final int anInt1605;
    private final int anInt1606;
    private Sequence objAnim;
    private int loopCycle;
    public static Client clientInstance;
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
            objAnim = Sequence.animCache[animationID];
            delay = 0;
            loopCycle = Client.loopCycle;
            if (flag && objAnim.frameStep != -1) {
                delay = (int) (Math.random() * (double) objAnim.frameCount);
                loopCycle -= (int) (Math.random() * (double) objAnim.getFrameLength(delay));
            }
        }
        ObjectDef object = ObjectDef.forID(id);
        anInt1601 = object.currentConfigID;
        anInt1602 = object.configID;
        children = object.configObjectIDs;
    }
    
    @Override
    public Model getRotatedModel() {
        int j = -1;
        if (objAnim != null) {
            int k = Client.loopCycle - loopCycle;
            if (k > 100 && objAnim.frameStep > 0) {
                k = 100;
            }
            while (k > objAnim.getFrameLength(delay)) {
                k -= objAnim.getFrameLength(delay);
                delay++;
                if (delay < objAnim.frameCount) {
                    continue;
                }
                delay -= objAnim.frameStep;
                if (delay >= 0 && delay < objAnim.frameCount) {
                    continue;
                }
                objAnim = null;
                break;
            }
            loopCycle = Client.loopCycle - k;
            if (objAnim != null) {
                j = objAnim.animationFrameID2[delay];
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
            return object.renderObject(anInt1611, anInt1612, anInt1603, anInt1604, anInt1605, anInt1606, j);
        }
    }

    private ObjectDef getChild() {
        int objid = -1;
        if (anInt1601 != -1) {
            VarBit varBit = VarBit.cache[anInt1601];
            int k = varBit.configId;
            int l = varBit.leastSignificantBit;
            int i1 = varBit.mostSignificantBit;
            int j1 = Client.BITFIELD_MAX_VALUE[i1 - l];
            objid = clientInstance.sessionSettings[k] >> l & j1;
        } else if (anInt1602 != -1) {
            objid = clientInstance.sessionSettings[anInt1602];
        }
        if (objid < 0 || objid >= children.length || children[objid] == -1) {
            return null;
        } else {
            return ObjectDef.forID(children[objid]);
        }
    }
}
