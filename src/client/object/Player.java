package client.object;

import client.npc.NPCDef;
import client.item.ItemDef;
import client.Client;
import client.animation.Mobile;
import client.archive.IdentityKit;
import client.model.Model;
import client.util.MemCache;
import client.data.Stream;
import client.animation.Entity;
import client.archive.Sequence;
import client.animation.AnimationFrame;
import client.text.TextClass;
import client.archive.SpotAnim;


public final class Player extends Mobile {
    
    public long aLong1697;
    public NPCDef desc;
    public boolean aBoolean1699;
    public int anIntArray1700[];
    public int team;
    public int anInt1702;
    public static String name;
    public static MemCache mruNodes = new MemCache(260);
    public int combatLevel;
    public int headIcon;
    public int anInt1707;
    public int anInt1708;
    public int anInt1709;
    public boolean visible;
    public int anInt1711;
    public int anInt1712;
    public int anInt1713;
    public Model aModel_1714;
    private int anInt1715;
    private boolean aBoolean1716;
    public int equipment[];
    public long aLong1718;
    public int anInt1719;
    public int anInt1720;
    public int anInt1721;
    public int anInt1722;
    public int skill;
        
    public Player() {
        aLong1697 = -1L;
        aBoolean1699 = false;
        anIntArray1700 = new int[5];
        visible = false;
        anInt1715 = 9;
        aBoolean1716 = true;
        equipment = new int[12];
    }
    
    @Override
    public final Model getRotatedModel() {
        if (!visible) {
            return null;
        }
        Model class30_sub2_sub4_sub6 = method452(0);
        if (class30_sub2_sub4_sub6 == null) {
            return null;
        }
        super.height = ((Entity) (class30_sub2_sub4_sub6)).modelHeight;
        class30_sub2_sub4_sub6.oneSquareModel = true;
        if (aBoolean1699) {
            return class30_sub2_sub4_sub6;
        }
        if (super.anInt1520 != -1 && super.anInt1521 != -1) {
            SpotAnim class23 = SpotAnim.spotAnimCache[super.anInt1520];
            Model class30_sub2_sub4_sub6_2 = class23.getModel();
            if (class30_sub2_sub4_sub6_2 != null) {
                Model class30_sub2_sub4_sub6_3 = new Model(9, true, AnimationFrame.isNullFrame(super.anInt1521), false, class30_sub2_sub4_sub6_2);
                class30_sub2_sub4_sub6_3.method475(0, -super.anInt1524, 0);
                class30_sub2_sub4_sub6_3.createBones((byte) -71);
                class30_sub2_sub4_sub6_3.applyTransform(class23.animationSequence.animationFrameID2[super.anInt1521], 40542);
                class30_sub2_sub4_sub6_3.triangleSkin = null;
                class30_sub2_sub4_sub6_3.vertexSkin = null;
                if (class23.resizeXY != 128 || class23.resizeZ != 128) {
                    class30_sub2_sub4_sub6_3.scaleT(class23.resizeXY, class23.resizeXY, class23.resizeZ);
                    //class30_sub2_sub4_sub6_3.method479(frontLight, backLight, rightLight, middleLight, leftLight, true);
                }
                class30_sub2_sub4_sub6_3.light(64 + class23.modelBrightness, 850 + class23.modelShadow, -30, -50, -30, true);
                Model aclass30_sub2_sub4_sub6_1[] = {
                    class30_sub2_sub4_sub6, class30_sub2_sub4_sub6_3
                };
                class30_sub2_sub4_sub6 = new Model(2, -819, true, aclass30_sub2_sub4_sub6_1);
            }
        }
        if (aModel_1714 != null) {
            if (Client.loopCycle >= anInt1708) {
                aModel_1714 = null;
            }
            if (Client.loopCycle >= anInt1707 && Client.loopCycle < anInt1708) {
                Model class30_sub2_sub4_sub6_1 = aModel_1714;
                class30_sub2_sub4_sub6_1.method475(anInt1711 - super.boundExtentX, anInt1712 - anInt1709, anInt1713 - super.boundExtentY);
                if (super.turnDirection == 512) {
                    class30_sub2_sub4_sub6_1.rotateBy90(360);
                    class30_sub2_sub4_sub6_1.rotateBy90(360);
                    class30_sub2_sub4_sub6_1.rotateBy90(360);
                } else if (super.turnDirection == 1024) {
                    class30_sub2_sub4_sub6_1.rotateBy90(360);
                    class30_sub2_sub4_sub6_1.rotateBy90(360);
                } else if (super.turnDirection == 1536) {
                    class30_sub2_sub4_sub6_1.rotateBy90(360);
                }
                Model aclass30_sub2_sub4_sub6[] = {
                    class30_sub2_sub4_sub6, class30_sub2_sub4_sub6_1
                };
                class30_sub2_sub4_sub6 = new Model(2, -819, true, aclass30_sub2_sub4_sub6);
                if (super.turnDirection == 512) {
                    class30_sub2_sub4_sub6_1.rotateBy90(360);
                } else if (super.turnDirection == 1024) {
                    class30_sub2_sub4_sub6_1.rotateBy90(360);
                    class30_sub2_sub4_sub6_1.rotateBy90(360);
                } else if (super.turnDirection == 1536) {
                    class30_sub2_sub4_sub6_1.rotateBy90(360);
                    class30_sub2_sub4_sub6_1.rotateBy90(360);
                    class30_sub2_sub4_sub6_1.rotateBy90(360);
                }
                class30_sub2_sub4_sub6_1.method475(super.boundExtentX - anInt1711, anInt1709 - anInt1712, super.boundExtentY - anInt1713);
            }
        }
        class30_sub2_sub4_sub6.oneSquareModel = true;
        return class30_sub2_sub4_sub6;
    }

    public final void updatePlayer(int i, Stream class30_sub2_sub2) {
        class30_sub2_sub2.currentOffset = 0;
        anInt1702 = class30_sub2_sub2.readUnsignedByte();
        headIcon = class30_sub2_sub2.readUnsignedByte();
        if (i != 0) {
            return;
        }
        desc = null;
        team = 0;
        for (int j = 0; j < 12; j++) {
            int k = class30_sub2_sub2.readUnsignedByte();
            if (k == 0) {
                equipment[j] = 0;
                continue;
            }
            int i1 = class30_sub2_sub2.readUnsignedByte();
            equipment[j] = (k << 8) + i1;
            if (j == 0 && equipment[0] == 65535) {
                desc = NPCDef.forID(class30_sub2_sub2.readUnsignedWord());
                break;
            }
            if (equipment[j] >= 512 && equipment[j] - 512 < ItemDef.totalItems) {
                int l1 = ItemDef.forID(equipment[j] - 512).team;
                if (l1 != 0) {
                    team = l1;
                }
            }
        }

        for (int l = 0; l < 5; l++) {
            int j1 = class30_sub2_sub2.readUnsignedByte();
            if (j1 < 0 || j1 >= Client.anIntArrayArray1003[l].length) {
                j1 = 0;
            }
            anIntArray1700[l] = j1;
        }

        super.anInt1511 = class30_sub2_sub2.readUnsignedWord();
        if (super.anInt1511 == 65535) {
            super.anInt1511 = -1;
        }
        super.anInt1512 = class30_sub2_sub2.readUnsignedWord();
        if (super.anInt1512 == 65535) {
            super.anInt1512 = -1;
        }
        super.anInt1554 = class30_sub2_sub2.readUnsignedWord();
        if (super.anInt1554 == 65535) {
            super.anInt1554 = -1;
        }
        super.anInt1555 = class30_sub2_sub2.readUnsignedWord();
        if (super.anInt1555 == 65535) {
            super.anInt1555 = -1;
        }
        super.anInt1556 = class30_sub2_sub2.readUnsignedWord();
        if (super.anInt1556 == 65535) {
            super.anInt1556 = -1;
        }
        super.anInt1557 = class30_sub2_sub2.readUnsignedWord();
        if (super.anInt1557 == 65535) {
            super.anInt1557 = -1;
        }
        super.anInt1505 = class30_sub2_sub2.readUnsignedWord();
        if (super.anInt1505 == 65535) {
            super.anInt1505 = -1;
        }
        name = TextClass.fixName(TextClass.longForName(class30_sub2_sub2.readQWord()));
        combatLevel = class30_sub2_sub2.readUnsignedByte();
        skill = class30_sub2_sub2.readUnsignedWord();
        visible = true;
        aLong1718 = 0L;
        for (int k1 = 0; k1 < 12; k1++) {
            aLong1718 <<= 4;
            if (equipment[k1] >= 256) {
                aLong1718 += equipment[k1] - 256;
            }
        }

        if (equipment[0] >= 256) {
            aLong1718 += equipment[0] - 256 >> 4;
        }
        if (equipment[1] >= 256) {
            aLong1718 += equipment[1] - 256 >> 8;
        }
        for (int i2 = 0; i2 < 5; i2++) {
            aLong1718 <<= 3;
            aLong1718 += anIntArray1700[i2];
        }

        aLong1718 <<= 1;
        aLong1718 += anInt1702;
    }

    private final Model method452(int i) {
        if (desc != null) {
            int j = -1;
            if (super.animation >= 0 && super.anInt1529 == 0) {
                j = Sequence.animCache[super.animation].animationFrameID2[super.anInt1527];
            } else if (super.anInt1517 >= 0) {
                j = Sequence.animCache[super.anInt1517].animationFrameID2[super.anInt1518];
            }
            Model class30_sub2_sub4_sub6 = desc.method164(-1, j, null);
            return class30_sub2_sub4_sub6;
        }
        long l = aLong1718;
        int k = -1;
        int i1 = -1;
        int j1 = -1;
        int k1 = -1;
        if (super.animation >= 0 && super.anInt1529 == 0) {
            Sequence class20 = Sequence.animCache[super.animation];
            k = class20.animationFrameID2[super.anInt1527];
            if (super.anInt1517 >= 0 && super.anInt1517 != super.anInt1511) {
                i1 = Sequence.animCache[super.anInt1517].animationFrameID2[super.anInt1518];
            }
            if (class20.anInt360 >= 0) {
                j1 = class20.anInt360;
                l += j1 - equipment[5] << 40;
            }
            if (class20.anInt361 >= 0) {
                k1 = class20.anInt361;
                l += k1 - equipment[3] << 48;
            }
        } else if (super.anInt1517 >= 0) {
            k = Sequence.animCache[super.anInt1517].animationFrameID2[super.anInt1518];
        }
        Model class30_sub2_sub4_sub6_1 = (Model) mruNodes.get(l);
        if (i != 0) {
            for (int l1 = 1; l1 > 0; l1++);
        }
        if (class30_sub2_sub4_sub6_1 == null) {
            boolean flag = false;
            for (int i2 = 0; i2 < 12; i2++) {
                int k2 = equipment[i2];
                if (k1 >= 0 && i2 == 3) {
                    k2 = k1;
                }
                if (j1 >= 0 && i2 == 5) {
                    k2 = j1;
                }
                if (k2 >= 256 && k2 < 512 && !IdentityKit.identityCache[k2 - 256].isBodyDownloaded()) {
                    flag = true;
                }
                if (k2 >= 512 && !ItemDef.forID(k2 - 512).method195(40903, anInt1702)) {
                    flag = true;
                }
            }

            if (flag) {
                if (aLong1697 != -1L) {
                    class30_sub2_sub4_sub6_1 = (Model) mruNodes.get(aLong1697);
                }
                if (class30_sub2_sub4_sub6_1 == null) {
                    return null;
                }
            }
        }
        if (class30_sub2_sub4_sub6_1 == null) {
            Model aclass30_sub2_sub4_sub6[] = new Model[12];
            int j2 = 0;
            for (int l2 = 0; l2 < 12; l2++) {
                int i3 = equipment[l2];
                if (k1 >= 0 && l2 == 3) {
                    i3 = k1;
                }
                if (j1 >= 0 && l2 == 5) {
                    i3 = j1;
                }
                if (i3 >= 256 && i3 < 512) {
                    Model class30_sub2_sub4_sub6_3 = IdentityKit.identityCache[i3 - 256].getBodyModel();
                    if (class30_sub2_sub4_sub6_3 != null) {
                        aclass30_sub2_sub4_sub6[j2++] = class30_sub2_sub4_sub6_3;
                    }
                }
                if (i3 >= 512) {
                    Model class30_sub2_sub4_sub6_4 = ItemDef.forID(i3 - 512).method196(false, anInt1702);
                    if (class30_sub2_sub4_sub6_4 != null) {
                        aclass30_sub2_sub4_sub6[j2++] = class30_sub2_sub4_sub6_4;
                    }
                }
            }

            class30_sub2_sub4_sub6_1 = new Model(j2, aclass30_sub2_sub4_sub6);
            for (int j3 = 0; j3 < 5; j3++) {
                if (anIntArray1700[j3] != 0) {
                    class30_sub2_sub4_sub6_1.reColour(Client.anIntArrayArray1003[j3][0], Client.anIntArrayArray1003[j3][anIntArray1700[j3]]);
                    if (j3 == 1) {
                        class30_sub2_sub4_sub6_1.reColour(Client.anIntArray1204[0], Client.anIntArray1204[anIntArray1700[j3]]);
                    }
                }
            }

            class30_sub2_sub4_sub6_1.createBones((byte) -71);
            class30_sub2_sub4_sub6_1.light(64, 850, -30, -50, -30, true);
            mruNodes.put(class30_sub2_sub4_sub6_1, l);
            aLong1697 = l;
        }
        if (aBoolean1699) {
            return class30_sub2_sub4_sub6_1;
        }
        Model class30_sub2_sub4_sub6_2 = Model.aModel_1621;
        class30_sub2_sub4_sub6_2.method464(class30_sub2_sub4_sub6_1, AnimationFrame.isNullFrame(k) & AnimationFrame.isNullFrame(i1));
        if (k != -1 && i1 != -1) {
            class30_sub2_sub4_sub6_2.mixAnimationFrames(-20491, Sequence.animCache[super.animation].animationFlowControl, i1, k);
        } else if (k != -1) {
            class30_sub2_sub4_sub6_2.applyTransform(k, 40542);
        }
        class30_sub2_sub4_sub6_2.calculateDiagonals();
        class30_sub2_sub4_sub6_2.triangleSkin = null;
        class30_sub2_sub4_sub6_2.vertexSkin = null;
        return class30_sub2_sub4_sub6_2;
    }

    @Override
    public final boolean isVisible() {
        return visible;
    }

    public final Model getPlayerModel() {
        if (!visible) {
            return null;
        }
        if (desc != null) {
            return desc.getHeadModel();
        }
        boolean flag = false;
        for (int i = 0; i < 12; i++) {
            int j = equipment[i];
            if (j >= 256 && j < 512 && !IdentityKit.identityCache[j - 256].isHeadDownloaded()) {
                flag = true;
            }
            if (j >= 512 && !ItemDef.forID(j - 512).method192(-2836, anInt1702)) {
                flag = true;
            }
        }

        if (flag) {
            return null;
        }
        Model aclass30_sub2_sub4_sub6[] = new Model[12];
        int k = 0;
        for (int l = 0; l < 12; l++) {
            int i1 = equipment[l];
            if (i1 >= 256 && i1 < 512) {
                Model class30_sub2_sub4_sub6_1 = IdentityKit.identityCache[i1 - 256].getHeadModel();
                if (class30_sub2_sub4_sub6_1 != null) {
                    aclass30_sub2_sub4_sub6[k++] = class30_sub2_sub4_sub6_1;
                }
            }
            if (i1 >= 512) {
                Model class30_sub2_sub4_sub6_2 = ItemDef.forID(i1 - 512).method194(-705, anInt1702);
                if (class30_sub2_sub4_sub6_2 != null) {
                    aclass30_sub2_sub4_sub6[k++] = class30_sub2_sub4_sub6_2;
                }
            }
        }

        Model class30_sub2_sub4_sub6 = new Model(k, aclass30_sub2_sub4_sub6);
        for (int j1 = 0; j1 < 5; j1++) {
            if (anIntArray1700[j1] != 0) {
                class30_sub2_sub4_sub6.reColour(Client.anIntArrayArray1003[j1][0], Client.anIntArrayArray1003[j1][anIntArray1700[j1]]);
                if (j1 == 1) {
                    class30_sub2_sub4_sub6.reColour(Client.anIntArray1204[0], Client.anIntArray1204[anIntArray1700[j1]]);
                }
            }
        }

        return class30_sub2_sub4_sub6;
    }
}
