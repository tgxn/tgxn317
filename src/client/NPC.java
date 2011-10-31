package client;


final class NPC extends Entity {
    
    private int anInt1693;
    private boolean aBoolean1694;
    private int anInt1695;
    EntityDef desc;
    
    NPC() {
        aBoolean1694 = false;
        anInt1695 = 9;
    }
    
    private Model method450() {
        if (super.anim >= 0 && super.anInt1529 == 0) {
            int k = Animation.anims[super.anim].anIntArray353[super.anInt1527];
            int i1 = -1;
            if (super.anInt1517 >= 0 && super.anInt1517 != super.anInt1511) {
                i1 = Animation.anims[super.anInt1517].anIntArray353[super.anInt1518];
            }
            return desc.method164(0, i1, k, Animation.anims[super.anim].anIntArray357);
        }
        int l = -1;
        if (super.anInt1517 >= 0) {
            l = Animation.anims[super.anInt1517].anIntArray353[super.anInt1518];
        }
        return desc.method164(0, -1, l, null);
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
                int j = class23.aClass20_407.anIntArray353[super.anInt1521];
                Model class30_sub2_sub4_sub6_2 = new Model(9, true, Class36.method532(j), false, class30_sub2_sub4_sub6_1);
                class30_sub2_sub4_sub6_2.method475(0, -super.anInt1524, 16384, 0);
                class30_sub2_sub4_sub6_2.method469((byte) -71);
                class30_sub2_sub4_sub6_2.method470(j, 40542);
                class30_sub2_sub4_sub6_2.anIntArrayArray1658 = null;
                class30_sub2_sub4_sub6_2.anIntArrayArray1657 = null;
                if (class23.anInt410 != 128 || class23.anInt411 != 128) {
                    class30_sub2_sub4_sub6_2.method478(class23.anInt410, class23.anInt410, anInt1695, class23.anInt411);
                }
                class30_sub2_sub4_sub6_2.method479(64 + class23.anInt413, 850 + class23.anInt414, -30, -50, -30, true);
                Model aclass30_sub2_sub4_sub6[] = {
                    class30_sub2_sub4_sub6, class30_sub2_sub4_sub6_2
                };
                class30_sub2_sub4_sub6 = new Model(2, -819, true, aclass30_sub2_sub4_sub6);
            }
        }
        if (desc.aByte68 == 1) {
            class30_sub2_sub4_sub6.aBoolean1659 = true;
        }
        return class30_sub2_sub4_sub6;
    }

    @Override
    public final boolean isVisible() {
        return desc != null;
    }
}
