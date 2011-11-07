package client;


final class NPC extends Entity {
    
    private int anInt1693;
    private boolean aBoolean1694;
    private int anInt1695;
    NPCDef desc;
    
    NPC() {
        aBoolean1694 = false;
        anInt1695 = 9;
    }
    
    private Model method450() {
        if (super.animation >= 0 && super.anInt1529 == 0) {
            int k = Animation.animCache[super.animation].animationFrameID2[super.anInt1527];
            int i1 = -1;
            if (super.anInt1517 >= 0 && super.anInt1517 != super.anInt1511) {
                i1 = Animation.animCache[super.anInt1517].animationFrameID2[super.anInt1518];
            }
            return desc.method164(i1, k, Animation.animCache[super.animation].animationFlowControl);
        }
        int l = -1;
        if (super.anInt1517 >= 0) {
            l = Animation.animCache[super.anInt1517].animationFrameID2[super.anInt1518];
        }
        return desc.method164(-1, l, null);
    }

    @Override
    public final Model getRotatedModel() {
        if (desc == null) {
            return null;
        }
        Model class30_sub2_sub4_sub6 = method450();
        if (class30_sub2_sub4_sub6 == null) {
            return null;
        }
        super.height = ((Animable) (class30_sub2_sub4_sub6)).modelHeight;
        if (super.anInt1520 != -1 && super.anInt1521 != -1) {
            SpotAnim class23 = SpotAnim.cache[super.anInt1520];
            Model class30_sub2_sub4_sub6_1 = class23.getModel();
            if (class30_sub2_sub4_sub6_1 != null) {
                int j = class23.animation.animationFrameID2[super.anInt1521];
                Model class30_sub2_sub4_sub6_2 = new Model(9, true, AnimationFrame.isNullFrame(j), false, class30_sub2_sub4_sub6_1);
                class30_sub2_sub4_sub6_2.method475(0, -super.anInt1524, 0);
                class30_sub2_sub4_sub6_2.createBones((byte) -71);
                class30_sub2_sub4_sub6_2.applyTransform(j, 40542);
                class30_sub2_sub4_sub6_2.triangleSkin = null;
                class30_sub2_sub4_sub6_2.vertexSkin = null;
                if (class23.resizeXY != 128 || class23.resizeZ != 128) {
                    class30_sub2_sub4_sub6_2.scaleT(class23.resizeXY, class23.resizeXY, class23.resizeZ);
                }
                class30_sub2_sub4_sub6_2.light(64 + class23.modelBrightness, 850 + class23.modelShadow, -30, -50, -30, true);
                Model aclass30_sub2_sub4_sub6[] = {
                    class30_sub2_sub4_sub6, class30_sub2_sub4_sub6_2
                };
                class30_sub2_sub4_sub6 = new Model(2, -819, true, aclass30_sub2_sub4_sub6);
            }
        }
        if (desc.boundDim == 1) {
            class30_sub2_sub4_sub6.oneSquareModel = true;
        }
        return class30_sub2_sub4_sub6;
    }

    @Override
    public final boolean isVisible() {
        return desc != null;
    }
}
