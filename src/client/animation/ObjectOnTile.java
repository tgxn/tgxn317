package client.animation;

// Partially Refactored - gamerx - 17.11.11

import client.archive.Sequence;
import client.Client;
import client.model.Model;
import client.object.ObjectDef;
import client.archive.VarBit;

/**
 * Seems to handle objects on a certain tile.
 *
 * @knownas Class30_Sub2_Sub4_Sub5, Animable_Sub5, ObjectOnTile
 * @author gamerx
 */
public class ObjectOnTile extends Entity {
    
    private int delay;
    private final int[] configObjectID;
    private final int currentConfigID;
    private final int configID;
    private final int anInt1603;
    private final int anInt1604;
    private final int anInt1605;
    private final int anInt1606;
    private Sequence sequence;
    private int loopCycle;
    public static Client clientInstance;
    private int objectID;
    private int anInt1611;
    private int anInt1612;
    
    public ObjectOnTile(int objectID, int j, int k, int l, byte byte0, int i1, int j1, int k1, int animationID, boolean flag) {
        this.objectID = objectID;
        anInt1611 = k;
        anInt1612 = j;
        anInt1603 = j1;
        anInt1604 = l;
        anInt1605 = i1;
        anInt1606 = k1;
        if (animationID != -1) {
            sequence = Sequence.animCache[animationID];
            delay = 0;
            loopCycle = Client.loopCycle;
            if (flag && sequence.frameStep != -1) {
                delay = (int) (Math.random() * (double) sequence.frameCount);
                loopCycle -= (int) (Math.random() * (double) sequence.getFrameLength(delay));
            }
        }
        ObjectDef objectDef = ObjectDef.forID(objectID);
        currentConfigID = objectDef.currentConfigID;
        configID = objectDef.configID;
        configObjectID = objectDef.configObjectIDs;
    }
    
    @Override
    public Model getRotatedModel() {
        int anim = -1;
        if (sequence != null) {
            int k = Client.loopCycle - loopCycle;
            if (k > 100 && sequence.frameStep > 0) {
                k = 100;
            }
            while (k > sequence.getFrameLength(delay)) {
                k -= sequence.getFrameLength(delay);
                delay++;
                if (delay < sequence.frameCount) {
                    continue;
                }
                delay -= sequence.frameStep;
                if (delay >= 0 && delay < sequence.frameCount) {
                    continue;
                }
                sequence = null;
                break;
            }
            loopCycle = Client.loopCycle - k;
            if (sequence != null) {
                anim = sequence.animationFrameID2[delay];
            }
        }
        ObjectDef objectDef;
        if (configObjectID != null) {
            objectDef = getChild();
        } else {
            objectDef = ObjectDef.forID(objectID);
        }
        if (objectDef == null) {
            return null;
        } else {
            return objectDef.renderObject(anInt1611, anInt1612, anInt1603, anInt1604, anInt1605, anInt1606, anim);
        }
    }

    private ObjectDef getChild() {
        int objID = -1;
        if (currentConfigID != -1) {
            VarBit varBit = VarBit.cache[currentConfigID];
            int k = varBit.configId;
            int l = varBit.leastSignificantBit;
            int i1 = varBit.mostSignificantBit;
            int j1 = Client.BITFIELD_MAX_VALUE[i1 - l];
            objID = clientInstance.sessionSettings[k] >> l & j1;
        } else if (configID != -1) {
            objID = clientInstance.sessionSettings[configID];
        }
        if (objID < 0 || objID >= configObjectID.length || configObjectID[objID] == -1) {
            return null;
        } else {
            return ObjectDef.forID(configObjectID[objID]);
        }
    }
}
