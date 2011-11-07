package client;


final class Animable_Sub3 extends Animable {
    
    public int anInt1560;
    public int anInt1561;
    public int anInt1562;
    public int anInt1563;
    public int anInt1564;
    private boolean aBoolean1565;
    private int anInt1566;
    public boolean aBoolean1567;
    private SpotAnim aSpotAnim_1568;
    private int anInt1569;
    private int anInt1570;
    
    public Animable_Sub3(int i, int j, int k, int l, int i1, int j1, int k1,
            int l1) {
        aBoolean1565 = true;
        anInt1566 = 9;
        aBoolean1567 = false;
        aSpotAnim_1568 = SpotAnim.cache[i1];
        anInt1560 = i;
        anInt1561 = l1;
        anInt1562 = k1;
        anInt1563 = j1;
        anInt1564 = j + l;
        if (k != 6) {
            throw new NullPointerException();
        } else {
            aBoolean1567 = false;
            return;
        }
    }

    @Override
    public final Model getRotatedModel() {
        Model class30_sub2_sub4_sub6 = aSpotAnim_1568.getModel();
        if (class30_sub2_sub4_sub6 == null) {
            return null;
        }
        int j = aSpotAnim_1568.animation.animationFrameID2[anInt1569];
        Model class30_sub2_sub4_sub6_1 = new Model(9, true, AnimationFrame.isNullFrame(j), false, class30_sub2_sub4_sub6);
        if (!aBoolean1567) {
            class30_sub2_sub4_sub6_1.createBones((byte) -71);
            class30_sub2_sub4_sub6_1.applyTransform(j, 40542);
            class30_sub2_sub4_sub6_1.triangleSkin = null;
            class30_sub2_sub4_sub6_1.vertexSkin = null;
        }
        if (aSpotAnim_1568.resizeXY != 128 || aSpotAnim_1568.resizeZ != 128) {
            class30_sub2_sub4_sub6_1.scaleT(aSpotAnim_1568.resizeXY, aSpotAnim_1568.resizeXY, aSpotAnim_1568.resizeZ);
        }
        if (aSpotAnim_1568.rotation != 0) {
            if (aSpotAnim_1568.rotation == 90) {
                class30_sub2_sub4_sub6_1.method473(360);
            }
            if (aSpotAnim_1568.rotation == 180) {
                class30_sub2_sub4_sub6_1.method473(360);
                class30_sub2_sub4_sub6_1.method473(360);
            }
            if (aSpotAnim_1568.rotation == 270) {
                class30_sub2_sub4_sub6_1.method473(360);
                class30_sub2_sub4_sub6_1.method473(360);
                class30_sub2_sub4_sub6_1.method473(360);
            }
        }
        class30_sub2_sub4_sub6_1.light(64 + aSpotAnim_1568.modelBrightness, 850 + aSpotAnim_1568.modelShadow, -30, -50, -30, true);
        return class30_sub2_sub4_sub6_1;
    }

    public final void method454(int i) {
        for (anInt1570 += i; anInt1570 > aSpotAnim_1568.animation.getFrameLength(anInt1569);) {
            anInt1570 -= aSpotAnim_1568.animation.getFrameLength(anInt1569) + 1;
            anInt1569++;
            if (anInt1569 >= aSpotAnim_1568.animation.frameCount && (anInt1569 < 0 || anInt1569 >= aSpotAnim_1568.animation.frameCount)) {
                anInt1569 = 0;
                aBoolean1567 = true;
            }
        }
    }
}
