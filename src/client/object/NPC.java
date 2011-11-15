package client.object;

import client.npc.NPCDef;
import client.animation.Mobile;
import client.model.Model;
import client.archive.Sequence;
import client.animation.AnimationFrame;
import client.animation.Entity;
import client.archive.SpotAnim;

/**
 * NPC Model?
 *
 * @author gamerx
 */
public final class NPC extends Mobile {
    
    public NPCDef npcDef;
    
    public NPC() {
    }
    
    private Model method450() {
        if (super.animation >= 0 && super.anInt1529 == 0) {
            int k = Sequence.animCache[super.animation].animationFrameID2[super.anInt1527];
            int i1 = -1;
            if (super.anInt1517 >= 0 && super.anInt1517 != super.anInt1511) {
                i1 = Sequence.animCache[super.anInt1517].animationFrameID2[super.anInt1518];
            }
            return npcDef.method164(i1, k, Sequence.animCache[super.animation].animationFlowControl);
        }
        int l = -1;
        if (super.anInt1517 >= 0) {
            l = Sequence.animCache[super.anInt1517].animationFrameID2[super.anInt1518];
        }
        return npcDef.method164(-1, l, null);
    }

    @Override
    public final Model getRotatedModel() {
        if (npcDef == null) {
            return null;
        }
        Model model = method450();
        if (model == null) {
            return null;
        }
        super.height = ((Entity) (model)).modelHeight;
        if (super.anInt1520 != -1 && super.anInt1521 != -1) {
            SpotAnim spotAnim = SpotAnim.spotAnimCache[super.anInt1520];
            Model class30_sub2_sub4_sub6_1 = spotAnim.getModel();
            if (class30_sub2_sub4_sub6_1 != null) {
                int j = spotAnim.animationSequence.animationFrameID2[super.anInt1521];
                Model class30_sub2_sub4_sub6_2 = new Model(9, true, AnimationFrame.isNullFrame(j), false, class30_sub2_sub4_sub6_1);
                class30_sub2_sub4_sub6_2.method475(0, -super.anInt1524, 0);
                class30_sub2_sub4_sub6_2.createBones((byte) -71);
                class30_sub2_sub4_sub6_2.applyTransform(j, 40542);
                class30_sub2_sub4_sub6_2.triangleSkin = null;
                class30_sub2_sub4_sub6_2.vertexSkin = null;
                if (spotAnim.resizeXY != 128 || spotAnim.resizeZ != 128) {
                    class30_sub2_sub4_sub6_2.scaleT(spotAnim.resizeXY, spotAnim.resizeXY, spotAnim.resizeZ);
                }
                class30_sub2_sub4_sub6_2.light(64 + spotAnim.modelBrightness, 850 + spotAnim.modelShadow, -30, -50, -30, true);
                Model aclass30_sub2_sub4_sub6[] = {
                    model, class30_sub2_sub4_sub6_2
                };
                model = new Model(2, -819, true, aclass30_sub2_sub4_sub6);
            }
        }
        if (npcDef.boundDim == 1) {
            model.oneSquareModel = true;
        }
        return model;
    }

    @Override
    public final boolean isVisible() {
        return npcDef != null;
    }
}
