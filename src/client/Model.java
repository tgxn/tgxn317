package client;


public class Model extends Animable {
    
    private int anInt1614;
    private boolean aBoolean1615;
    private int anInt1616;
    private int anInt1617;
    private boolean aBoolean1618;
    private static int anInt1619 = -192;
    public static int anInt1620;
    public static Model aModel_1621 = new Model(true);
    private static int anIntArray1622[] = new int[2000];
    private static int anIntArray1623[] = new int[2000];
    private static int anIntArray1624[] = new int[2000];
    private static int anIntArray1625[] = new int[2000];
    public int numVertices;
    public int vertexX[];
    public int vertexY[];
    public int vertexZ[];
    public int numTriangles;
    public int facePoint1[];
    public int facePoint2[];
    public int facePoint3[];
    public int anIntArray1634[];
    public int anIntArray1635[];
    public int anIntArray1636[];
    public int anIntArray1637[];
    public int anIntArray1638[];
    public int anIntArray1639[];
    public int trianglecolours[];
    public int anInt1641;
    public int numTexTriangles;
    public int texTrianglesPoint1[];
    public int texTrianglesPoint2[];
    public int texTrianglesPoint3[];
    public int anInt1646;
    public int anInt1647;
    public int anInt1648;
    public int anInt1649;
    public int anInt1650;
    public int anInt1651;
    public int anInt1652;
    public int anInt1653;
    public int anInt1654;
    public int anIntArray1655[];
    public int anIntArray1656[];
    public int anIntArrayArray1657[][];
    public int anIntArrayArray1658[][];
    public boolean aBoolean1659;
    Class33 aClass33Array1660[];
    static ModelDef aClass21Array1661[];
    static OnDemandFetcherParent aClass42_1662;
    static boolean aBooleanArray1663[] = new boolean[4096];
    static boolean aBooleanArray1664[] = new boolean[4096];
    static int anIntArray1665[] = new int[4096];
    static int anIntArray1666[] = new int[4096];
    static int anIntArray1667[] = new int[4096];
    static int anIntArray1668[] = new int[4096];
    static int anIntArray1669[] = new int[4096];
    static int anIntArray1670[] = new int[4096];
    static int anIntArray1671[] = new int[1500];
    static int anIntArrayArray1672[][] = new int[1500][512];
    static int anIntArray1673[] = new int[12];
    static int anIntArrayArray1674[][] = new int[12][2000];
    static int anIntArray1675[] = new int[2000];
    static int anIntArray1676[] = new int[2000];
    static int anIntArray1677[] = new int[12];
    static int anIntArray1678[] = new int[10];
    static int anIntArray1679[] = new int[10];
    static int anIntArray1680[] = new int[10];
    static int anInt1681;
    static int anInt1682;
    static int anInt1683;
    public static boolean aBoolean1684;
    public static int anInt1685;
    public static int anInt1686;
    public static int anInt1687;
    public static int anIntArray1688[] = new int[1000];
    public static int modelIntArray1[];
    public static int modelIntArray2[];
    static int modelIntArray3[];
    static int modelIntArray4[];

    static {
        modelIntArray1 = Texture.anIntArray1470;
        modelIntArray2 = Texture.anIntArray1471;
        modelIntArray3 = Texture.anIntArray1482;
        modelIntArray4 = Texture.anIntArray1469;
    }
    
    private Model(boolean flag) {
        anInt1614 = 9;
        aBoolean1615 = false;
        anInt1616 = 360;
        anInt1617 = 1;
        aBoolean1618 = true;
        aBoolean1659 = false;
        if (!flag) {
            aBoolean1618 = !aBoolean1618;
        }
    }

    private Model(int i, int j) {
        anInt1614 = 9;
        aBoolean1615 = false;
        anInt1616 = 360;
        anInt1617 = 1;
        aBoolean1618 = true;
        aBoolean1659 = false;
        anInt1620++;
        ModelDef class21 = aClass21Array1661[i];
        numVertices = class21.numVertices;
        numTriangles = class21.numTriangles;
        numTexTriangles = class21.numTexTriangles;
        vertexX = new int[numVertices];
        vertexY = new int[numVertices];
        vertexZ = new int[numVertices];
        facePoint1 = new int[numTriangles];
        facePoint2 = new int[numTriangles];
        while (j >= 0) {
            aBoolean1618 = !aBoolean1618;
        }
        facePoint3 = new int[numTriangles];
        texTrianglesPoint1 = new int[numTexTriangles];
        texTrianglesPoint2 = new int[numTexTriangles];
        texTrianglesPoint3 = new int[numTexTriangles];
        if (class21.someOffset2 >= 0) {
            anIntArray1655 = new int[numVertices];
        }
        if (class21.faceTypeOffset >= 0) {
            anIntArray1637 = new int[numTriangles];
        }
        if (class21.someOffset5 >= 0) {
            anIntArray1638 = new int[numTriangles];
        } else {
            anInt1641 = -class21.someOffset5 - 1;
        }
        if (class21.alphaOffset >= 0) {
            anIntArray1639 = new int[numTriangles];
        }
        if (class21.someOffset7 >= 0) {
            anIntArray1656 = new int[numTriangles];
        }
        trianglecolours = new int[numTriangles];
        Stream class30_sub2_sub2 = new Stream(class21.data);
        class30_sub2_sub2.currentOffset = class21.dirOffset;
        Stream class30_sub2_sub2_1 = new Stream(class21.data);
        class30_sub2_sub2_1.currentOffset = class21.xsOffset;
        Stream class30_sub2_sub2_2 = new Stream(class21.data);
        class30_sub2_sub2_2.currentOffset = class21.ysOffset;
        Stream class30_sub2_sub2_3 = new Stream(class21.data);
        class30_sub2_sub2_3.currentOffset = class21.zsOffset;
        Stream class30_sub2_sub2_4 = new Stream(class21.data);
        class30_sub2_sub2_4.currentOffset = class21.someOffset2;
        int k = 0;
        int l = 0;
        int i1 = 0;
        for (int j1 = 0; j1 < numVertices; j1++) {
            int k1 = class30_sub2_sub2.readUnsignedByte();
            int i2 = 0;
            if ((k1 & 1) != 0) {
                i2 = class30_sub2_sub2_1.method421();
            }
            int k2 = 0;
            if ((k1 & 2) != 0) {
                k2 = class30_sub2_sub2_2.method421();
            }
            int i3 = 0;
            if ((k1 & 4) != 0) {
                i3 = class30_sub2_sub2_3.method421();
            }
            vertexX[j1] = k + i2;
            vertexY[j1] = l + k2;
            vertexZ[j1] = i1 + i3;
            k = vertexX[j1];
            l = vertexY[j1];
            i1 = vertexZ[j1];
            if (anIntArray1655 != null) {
                anIntArray1655[j1] = class30_sub2_sub2_4.readUnsignedByte();
            }
        }

        class30_sub2_sub2.currentOffset = class21.colourOffset;
        class30_sub2_sub2_1.currentOffset = class21.faceTypeOffset;
        class30_sub2_sub2_2.currentOffset = class21.someOffset5;
        class30_sub2_sub2_3.currentOffset = class21.alphaOffset;
        class30_sub2_sub2_4.currentOffset = class21.someOffset7;
        for (int l1 = 0; l1 < numTriangles; l1++) {
            trianglecolours[l1] = class30_sub2_sub2.readUnsignedWord();
            if (anIntArray1637 != null) {
                anIntArray1637[l1] = class30_sub2_sub2_1.readUnsignedByte();
            }
            if (anIntArray1638 != null) {
                anIntArray1638[l1] = class30_sub2_sub2_2.readUnsignedByte();
            }
            if (anIntArray1639 != null) {
                anIntArray1639[l1] = class30_sub2_sub2_3.readUnsignedByte();
            }
            if (anIntArray1656 != null) {
                anIntArray1656[l1] = class30_sub2_sub2_4.readUnsignedByte();
            }
        }

        class30_sub2_sub2.currentOffset = class21.triangleDataOffset;
        class30_sub2_sub2_1.currentOffset = class21.triangleTypeOffset;
        int j2 = 0;
        int l2 = 0;
        int j3 = 0;
        int k3 = 0;
        for (int l3 = 0; l3 < numTriangles; l3++) {
            int i4 = class30_sub2_sub2_1.readUnsignedByte();
            if (i4 == 1) {
                j2 = class30_sub2_sub2.method421() + k3;
                k3 = j2;
                l2 = class30_sub2_sub2.method421() + k3;
                k3 = l2;
                j3 = class30_sub2_sub2.method421() + k3;
                k3 = j3;
                facePoint1[l3] = j2;
                facePoint2[l3] = l2;
                facePoint3[l3] = j3;
            }
            if (i4 == 2) {
                j2 = j2;
                l2 = j3;
                j3 = class30_sub2_sub2.method421() + k3;
                k3 = j3;
                facePoint1[l3] = j2;
                facePoint2[l3] = l2;
                facePoint3[l3] = j3;
            }
            if (i4 == 3) {
                j2 = j3;
                l2 = l2;
                j3 = class30_sub2_sub2.method421() + k3;
                k3 = j3;
                facePoint1[l3] = j2;
                facePoint2[l3] = l2;
                facePoint3[l3] = j3;
            }
            if (i4 == 4) {
                int k4 = j2;
                j2 = l2;
                l2 = k4;
                j3 = class30_sub2_sub2.method421() + k3;
                k3 = j3;
                facePoint1[l3] = j2;
                facePoint2[l3] = l2;
                facePoint3[l3] = j3;
            }
        }

        class30_sub2_sub2.currentOffset = class21.texCoordsOffset;
        for (int j4 = 0; j4 < numTexTriangles; j4++) {
            texTrianglesPoint1[j4] = class30_sub2_sub2.readUnsignedWord();
            texTrianglesPoint2[j4] = class30_sub2_sub2.readUnsignedWord();
            texTrianglesPoint3[j4] = class30_sub2_sub2.readUnsignedWord();
        }

    }

    public Model(int i, Model aclass30_sub2_sub4_sub6[], int j) {
        anInt1614 = 9;
        aBoolean1615 = false;
        anInt1616 = 360;
        anInt1617 = 1;
        aBoolean1618 = true;
        aBoolean1659 = false;
        anInt1620++;
        boolean flag = false;
        boolean flag1 = false;
        boolean flag2 = false;
        boolean flag3 = false;
        numVertices = 0;
        numTriangles = 0;
        numTexTriangles = 0;
        anInt1641 = -1;
        for (int k = 0; k < i; k++) {
            Model class30_sub2_sub4_sub6 = aclass30_sub2_sub4_sub6[k];
            if (class30_sub2_sub4_sub6 != null) {
                numVertices += class30_sub2_sub4_sub6.numVertices;
                numTriangles += class30_sub2_sub4_sub6.numTriangles;
                numTexTriangles += class30_sub2_sub4_sub6.numTexTriangles;
                flag |= class30_sub2_sub4_sub6.anIntArray1637 != null;
                if (class30_sub2_sub4_sub6.anIntArray1638 != null) {
                    flag1 = true;
                } else {
                    if (anInt1641 == -1) {
                        anInt1641 = class30_sub2_sub4_sub6.anInt1641;
                    }
                    if (anInt1641 != class30_sub2_sub4_sub6.anInt1641) {
                        flag1 = true;
                    }
                }
                flag2 |= class30_sub2_sub4_sub6.anIntArray1639 != null;
                flag3 |= class30_sub2_sub4_sub6.anIntArray1656 != null;
            }
        }

        vertexX = new int[numVertices];
        vertexY = new int[numVertices];
        vertexZ = new int[numVertices];
        anIntArray1655 = new int[numVertices];
        facePoint1 = new int[numTriangles];
        facePoint2 = new int[numTriangles];
        facePoint3 = new int[numTriangles];
        texTrianglesPoint1 = new int[numTexTriangles];
        texTrianglesPoint2 = new int[numTexTriangles];
        texTrianglesPoint3 = new int[numTexTriangles];
        if (flag) {
            anIntArray1637 = new int[numTriangles];
        }
        if (flag1) {
            anIntArray1638 = new int[numTriangles];
        }
        if (flag2) {
            anIntArray1639 = new int[numTriangles];
        }
        if (flag3) {
            anIntArray1656 = new int[numTriangles];
        }
        trianglecolours = new int[numTriangles];
        numVertices = 0;
        numTriangles = 0;
        numTexTriangles = 0;
        if (j >= 0) {
            anInt1619 = 23;
        }
        int l = 0;
        for (int i1 = 0; i1 < i; i1++) {
            Model class30_sub2_sub4_sub6_1 = aclass30_sub2_sub4_sub6[i1];
            if (class30_sub2_sub4_sub6_1 != null) {
                for (int j1 = 0; j1 < class30_sub2_sub4_sub6_1.numTriangles; j1++) {
                    if (flag) {
                        if (class30_sub2_sub4_sub6_1.anIntArray1637 == null) {
                            anIntArray1637[numTriangles] = 0;
                        } else {
                            int k1 = class30_sub2_sub4_sub6_1.anIntArray1637[j1];
                            if ((k1 & 2) == 2) {
                                k1 += l << 2;
                            }
                            anIntArray1637[numTriangles] = k1;
                        }
                    }
                    if (flag1) {
                        if (class30_sub2_sub4_sub6_1.anIntArray1638 == null) {
                            anIntArray1638[numTriangles] = class30_sub2_sub4_sub6_1.anInt1641;
                        } else {
                            anIntArray1638[numTriangles] = class30_sub2_sub4_sub6_1.anIntArray1638[j1];
                        }
                    }
                    if (flag2) {
                        if (class30_sub2_sub4_sub6_1.anIntArray1639 == null) {
                            anIntArray1639[numTriangles] = 0;
                        } else {
                            anIntArray1639[numTriangles] = class30_sub2_sub4_sub6_1.anIntArray1639[j1];
                        }
                    }
                    if (flag3 && class30_sub2_sub4_sub6_1.anIntArray1656 != null) {
                        anIntArray1656[numTriangles] = class30_sub2_sub4_sub6_1.anIntArray1656[j1];
                    }
                    trianglecolours[numTriangles] = class30_sub2_sub4_sub6_1.trianglecolours[j1];
                    facePoint1[numTriangles] = method465(class30_sub2_sub4_sub6_1, class30_sub2_sub4_sub6_1.facePoint1[j1]);
                    facePoint2[numTriangles] = method465(class30_sub2_sub4_sub6_1, class30_sub2_sub4_sub6_1.facePoint2[j1]);
                    facePoint3[numTriangles] = method465(class30_sub2_sub4_sub6_1, class30_sub2_sub4_sub6_1.facePoint3[j1]);
                    numTriangles++;
                }

                for (int l1 = 0; l1 < class30_sub2_sub4_sub6_1.numTexTriangles; l1++) {
                    texTrianglesPoint1[numTexTriangles] = method465(class30_sub2_sub4_sub6_1, class30_sub2_sub4_sub6_1.texTrianglesPoint1[l1]);
                    texTrianglesPoint2[numTexTriangles] = method465(class30_sub2_sub4_sub6_1, class30_sub2_sub4_sub6_1.texTrianglesPoint2[l1]);
                    texTrianglesPoint3[numTexTriangles] = method465(class30_sub2_sub4_sub6_1, class30_sub2_sub4_sub6_1.texTrianglesPoint3[l1]);
                    numTexTriangles++;
                }

                l += class30_sub2_sub4_sub6_1.numTexTriangles;
            }
        }

    }

    public Model(int i, int j, boolean flag, Model aclass30_sub2_sub4_sub6[]) {
        anInt1614 = 9;
        aBoolean1615 = false;
        anInt1616 = 360;
        anInt1617 = 1;
        aBoolean1618 = true;
        aBoolean1659 = false;
        anInt1620++;
        boolean flag1 = false;
        boolean flag2 = false;
        boolean flag3 = false;
        boolean flag4 = false;
        numVertices = 0;
        numTriangles = 0;
        numTexTriangles = 0;
        anInt1641 = -1;
        for (int k = 0; k < i; k++) {
            Model class30_sub2_sub4_sub6 = aclass30_sub2_sub4_sub6[k];
            if (class30_sub2_sub4_sub6 != null) {
                numVertices += class30_sub2_sub4_sub6.numVertices;
                numTriangles += class30_sub2_sub4_sub6.numTriangles;
                numTexTriangles += class30_sub2_sub4_sub6.numTexTriangles;
                flag1 |= class30_sub2_sub4_sub6.anIntArray1637 != null;
                if (class30_sub2_sub4_sub6.anIntArray1638 != null) {
                    flag2 = true;
                } else {
                    if (anInt1641 == -1) {
                        anInt1641 = class30_sub2_sub4_sub6.anInt1641;
                    }
                    if (anInt1641 != class30_sub2_sub4_sub6.anInt1641) {
                        flag2 = true;
                    }
                }
                flag3 |= class30_sub2_sub4_sub6.anIntArray1639 != null;
                flag4 |= class30_sub2_sub4_sub6.trianglecolours != null;
            }
        }

        vertexX = new int[numVertices];
        vertexY = new int[numVertices];
        vertexZ = new int[numVertices];
        facePoint1 = new int[numTriangles];
        facePoint2 = new int[numTriangles];
        facePoint3 = new int[numTriangles];
        anIntArray1634 = new int[numTriangles];
        anIntArray1635 = new int[numTriangles];
        anIntArray1636 = new int[numTriangles];
        texTrianglesPoint1 = new int[numTexTriangles];
        texTrianglesPoint2 = new int[numTexTriangles];
        texTrianglesPoint3 = new int[numTexTriangles];
        if (j >= 0) {
            for (int l = 1; l > 0; l++);
        }
        if (flag1) {
            anIntArray1637 = new int[numTriangles];
        }
        if (flag2) {
            anIntArray1638 = new int[numTriangles];
        }
        if (flag3) {
            anIntArray1639 = new int[numTriangles];
        }
        if (flag4) {
            trianglecolours = new int[numTriangles];
        }
        numVertices = 0;
        numTriangles = 0;
        numTexTriangles = 0;
        int i1 = 0;
        for (int j1 = 0; j1 < i; j1++) {
            Model class30_sub2_sub4_sub6_1 = aclass30_sub2_sub4_sub6[j1];
            if (class30_sub2_sub4_sub6_1 != null) {
                int k1 = numVertices;
                for (int l1 = 0; l1 < class30_sub2_sub4_sub6_1.numVertices; l1++) {
                    vertexX[numVertices] = class30_sub2_sub4_sub6_1.vertexX[l1];
                    vertexY[numVertices] = class30_sub2_sub4_sub6_1.vertexY[l1];
                    vertexZ[numVertices] = class30_sub2_sub4_sub6_1.vertexZ[l1];
                    numVertices++;
                }

                for (int i2 = 0; i2 < class30_sub2_sub4_sub6_1.numTriangles; i2++) {
                    facePoint1[numTriangles] = class30_sub2_sub4_sub6_1.facePoint1[i2] + k1;
                    facePoint2[numTriangles] = class30_sub2_sub4_sub6_1.facePoint2[i2] + k1;
                    facePoint3[numTriangles] = class30_sub2_sub4_sub6_1.facePoint3[i2] + k1;
                    anIntArray1634[numTriangles] = class30_sub2_sub4_sub6_1.anIntArray1634[i2];
                    anIntArray1635[numTriangles] = class30_sub2_sub4_sub6_1.anIntArray1635[i2];
                    anIntArray1636[numTriangles] = class30_sub2_sub4_sub6_1.anIntArray1636[i2];
                    if (flag1) {
                        if (class30_sub2_sub4_sub6_1.anIntArray1637 == null) {
                            anIntArray1637[numTriangles] = 0;
                        } else {
                            int j2 = class30_sub2_sub4_sub6_1.anIntArray1637[i2];
                            if ((j2 & 2) == 2) {
                                j2 += i1 << 2;
                            }
                            anIntArray1637[numTriangles] = j2;
                        }
                    }
                    if (flag2) {
                        if (class30_sub2_sub4_sub6_1.anIntArray1638 == null) {
                            anIntArray1638[numTriangles] = class30_sub2_sub4_sub6_1.anInt1641;
                        } else {
                            anIntArray1638[numTriangles] = class30_sub2_sub4_sub6_1.anIntArray1638[i2];
                        }
                    }
                    if (flag3) {
                        if (class30_sub2_sub4_sub6_1.anIntArray1639 == null) {
                            anIntArray1639[numTriangles] = 0;
                        } else {
                            anIntArray1639[numTriangles] = class30_sub2_sub4_sub6_1.anIntArray1639[i2];
                        }
                    }
                    if (flag4 && class30_sub2_sub4_sub6_1.trianglecolours != null) {
                        trianglecolours[numTriangles] = class30_sub2_sub4_sub6_1.trianglecolours[i2];
                    }
                    numTriangles++;
                }

                for (int k2 = 0; k2 < class30_sub2_sub4_sub6_1.numTexTriangles; k2++) {
                    texTrianglesPoint1[numTexTriangles] = class30_sub2_sub4_sub6_1.texTrianglesPoint1[k2] + k1;
                    texTrianglesPoint2[numTexTriangles] = class30_sub2_sub4_sub6_1.texTrianglesPoint2[k2] + k1;
                    texTrianglesPoint3[numTexTriangles] = class30_sub2_sub4_sub6_1.texTrianglesPoint3[k2] + k1;
                    numTexTriangles++;
                }

                i1 += class30_sub2_sub4_sub6_1.numTexTriangles;
            }
        }

        method466(false);
    }

    public Model(int i, boolean flag, boolean flag1, boolean flag2, Model class30_sub2_sub4_sub6) {
        anInt1614 = 9;
        aBoolean1615 = false;
        anInt1616 = 360;
        anInt1617 = 1;
        aBoolean1618 = true;
        aBoolean1659 = false;
        anInt1620++;
        numVertices = class30_sub2_sub4_sub6.numVertices;
        numTriangles = class30_sub2_sub4_sub6.numTriangles;
        numTexTriangles = class30_sub2_sub4_sub6.numTexTriangles;
        if (flag2) {
            vertexX = class30_sub2_sub4_sub6.vertexX;
            vertexY = class30_sub2_sub4_sub6.vertexY;
            vertexZ = class30_sub2_sub4_sub6.vertexZ;
        } else {
            vertexX = new int[numVertices];
            vertexY = new int[numVertices];
            vertexZ = new int[numVertices];
            for (int j = 0; j < numVertices; j++) {
                vertexX[j] = class30_sub2_sub4_sub6.vertexX[j];
                vertexY[j] = class30_sub2_sub4_sub6.vertexY[j];
                vertexZ[j] = class30_sub2_sub4_sub6.vertexZ[j];
            }

        }
        if (flag) {
            trianglecolours = class30_sub2_sub4_sub6.trianglecolours;
        } else {
            trianglecolours = new int[numTriangles];
            for (int k = 0; k < numTriangles; k++) {
                trianglecolours[k] = class30_sub2_sub4_sub6.trianglecolours[k];
            }

        }
        if (flag1) {
            anIntArray1639 = class30_sub2_sub4_sub6.anIntArray1639;
        } else {
            anIntArray1639 = new int[numTriangles];
            if (class30_sub2_sub4_sub6.anIntArray1639 == null) {
                for (int l = 0; l < numTriangles; l++) {
                    anIntArray1639[l] = 0;
                }

            } else {
                for (int i1 = 0; i1 < numTriangles; i1++) {
                    anIntArray1639[i1] = class30_sub2_sub4_sub6.anIntArray1639[i1];
                }

            }
        }
        anIntArray1655 = class30_sub2_sub4_sub6.anIntArray1655;
        anIntArray1656 = class30_sub2_sub4_sub6.anIntArray1656;
        anIntArray1637 = class30_sub2_sub4_sub6.anIntArray1637;
        facePoint1 = class30_sub2_sub4_sub6.facePoint1;
        facePoint2 = class30_sub2_sub4_sub6.facePoint2;
        facePoint3 = class30_sub2_sub4_sub6.facePoint3;
        anIntArray1638 = class30_sub2_sub4_sub6.anIntArray1638;
        anInt1641 = class30_sub2_sub4_sub6.anInt1641;
        texTrianglesPoint1 = class30_sub2_sub4_sub6.texTrianglesPoint1;
        if (i < 9 || i > 9) {
            for (int j1 = 1; j1 > 0; j1++);
        }
        texTrianglesPoint2 = class30_sub2_sub4_sub6.texTrianglesPoint2;
        texTrianglesPoint3 = class30_sub2_sub4_sub6.texTrianglesPoint3;
    }

    public Model(boolean flag, int i, boolean flag1, Model class30_sub2_sub4_sub6) {
        anInt1614 = 9;
        aBoolean1615 = false;
        anInt1616 = 360;
        anInt1617 = 1;
        aBoolean1618 = true;
        aBoolean1659 = false;
        anInt1620++;
        numVertices = class30_sub2_sub4_sub6.numVertices;
        numTriangles = class30_sub2_sub4_sub6.numTriangles;
        numTexTriangles = class30_sub2_sub4_sub6.numTexTriangles;
        if (flag) {
            vertexY = new int[numVertices];
            for (int j = 0; j < numVertices; j++) {
                vertexY[j] = class30_sub2_sub4_sub6.vertexY[j];
            }

        } else {
            vertexY = class30_sub2_sub4_sub6.vertexY;
        }
        if (flag1) {
            anIntArray1634 = new int[numTriangles];
            anIntArray1635 = new int[numTriangles];
            anIntArray1636 = new int[numTriangles];
            for (int k = 0; k < numTriangles; k++) {
                anIntArray1634[k] = class30_sub2_sub4_sub6.anIntArray1634[k];
                anIntArray1635[k] = class30_sub2_sub4_sub6.anIntArray1635[k];
                anIntArray1636[k] = class30_sub2_sub4_sub6.anIntArray1636[k];
            }

            anIntArray1637 = new int[numTriangles];
            if (class30_sub2_sub4_sub6.anIntArray1637 == null) {
                for (int l = 0; l < numTriangles; l++) {
                    anIntArray1637[l] = 0;
                }

            } else {
                for (int i1 = 0; i1 < numTriangles; i1++) {
                    anIntArray1637[i1] = class30_sub2_sub4_sub6.anIntArray1637[i1];
                }

            }
            super.aClass33Array1425 = new Class33[numVertices];
            for (int j1 = 0; j1 < numVertices; j1++) {
                Class33 class33 = super.aClass33Array1425[j1] = new Class33();
                Class33 class33_1 = ((Animable) (class30_sub2_sub4_sub6)).aClass33Array1425[j1];
                class33.anInt602 = class33_1.anInt602;
                class33.anInt603 = class33_1.anInt603;
                class33.anInt604 = class33_1.anInt604;
                class33.anInt605 = class33_1.anInt605;
            }

            aClass33Array1660 = class30_sub2_sub4_sub6.aClass33Array1660;
        } else {
            anIntArray1634 = class30_sub2_sub4_sub6.anIntArray1634;
            anIntArray1635 = class30_sub2_sub4_sub6.anIntArray1635;
            anIntArray1636 = class30_sub2_sub4_sub6.anIntArray1636;
            anIntArray1637 = class30_sub2_sub4_sub6.anIntArray1637;
        }
        vertexX = class30_sub2_sub4_sub6.vertexX;
        vertexZ = class30_sub2_sub4_sub6.vertexZ;
        trianglecolours = class30_sub2_sub4_sub6.trianglecolours;
        anIntArray1639 = class30_sub2_sub4_sub6.anIntArray1639;
        anIntArray1638 = class30_sub2_sub4_sub6.anIntArray1638;
        anInt1641 = class30_sub2_sub4_sub6.anInt1641;
        facePoint1 = class30_sub2_sub4_sub6.facePoint1;
        facePoint2 = class30_sub2_sub4_sub6.facePoint2;
        facePoint3 = class30_sub2_sub4_sub6.facePoint3;
        texTrianglesPoint1 = class30_sub2_sub4_sub6.texTrianglesPoint1;
        texTrianglesPoint2 = class30_sub2_sub4_sub6.texTrianglesPoint2;
        texTrianglesPoint3 = class30_sub2_sub4_sub6.texTrianglesPoint3;
        super.modelHeight = ((Animable) (class30_sub2_sub4_sub6)).modelHeight;
        for (anInt1651 = class30_sub2_sub4_sub6.anInt1651; i >= 0;) {
            throw new NullPointerException();
        }

        anInt1650 = class30_sub2_sub4_sub6.anInt1650;
        anInt1653 = class30_sub2_sub4_sub6.anInt1653;
        anInt1652 = class30_sub2_sub4_sub6.anInt1652;
        anInt1646 = class30_sub2_sub4_sub6.anInt1646;
        anInt1648 = class30_sub2_sub4_sub6.anInt1648;
        anInt1649 = class30_sub2_sub4_sub6.anInt1649;
        anInt1647 = class30_sub2_sub4_sub6.anInt1647;
    }
    
    public static void nullLoader() {
        aClass21Array1661 = null;
        aBooleanArray1663 = null;
        aBooleanArray1664 = null;
        anIntArray1666 = null;
        anIntArray1667 = null;
        anIntArray1668 = null;
        anIntArray1669 = null;
        anIntArray1670 = null;
        anIntArray1671 = null;
        anIntArrayArray1672 = null;
        anIntArray1673 = null;
        anIntArrayArray1674 = null;
        anIntArray1675 = null;
        anIntArray1676 = null;
        anIntArray1677 = null;
        modelIntArray1 = null;
        modelIntArray2 = null;
        modelIntArray3 = null;
        modelIntArray4 = null;
    }

    public static void method459(int i, OnDemandFetcherParent class42) {
        aClass21Array1661 = new ModelDef[i];
        aClass42_1662 = class42;
    }

    public static void readRsModel(byte abyte0[], int i, int j) {
        if (abyte0 == null) {
            ModelDef class21 = aClass21Array1661[j] = new ModelDef();
            class21.numVertices = 0;
            class21.numTriangles = 0;
            class21.numTexTriangles = 0;
            return;
        }
        Stream class30_sub2_sub2 = new Stream(abyte0);
        class30_sub2_sub2.currentOffset = abyte0.length - 18;
        ModelDef class21_1 = aClass21Array1661[j] = new ModelDef();
        class21_1.data = abyte0;
        class21_1.numVertices = class30_sub2_sub2.readUnsignedWord();
        class21_1.numTriangles = class30_sub2_sub2.readUnsignedWord();
        class21_1.numTexTriangles = class30_sub2_sub2.readUnsignedByte();
        int k = class30_sub2_sub2.readUnsignedByte();
        int l = class30_sub2_sub2.readUnsignedByte();
        if (i != -4036) {
            return;
        }
        int i1 = class30_sub2_sub2.readUnsignedByte();
        int j1 = class30_sub2_sub2.readUnsignedByte();
        int k1 = class30_sub2_sub2.readUnsignedByte();
        int l1 = class30_sub2_sub2.readUnsignedWord();
        int i2 = class30_sub2_sub2.readUnsignedWord();
        int j2 = class30_sub2_sub2.readUnsignedWord();
        int k2 = class30_sub2_sub2.readUnsignedWord();
        int l2 = 0;
        class21_1.dirOffset = l2;
        l2 += class21_1.numVertices;
        class21_1.triangleTypeOffset = l2;
        l2 += class21_1.numTriangles;
        class21_1.someOffset5 = l2;
        if (l == 255) {
            l2 += class21_1.numTriangles;
        } else {
            class21_1.someOffset5 = -l - 1;
        }
        class21_1.someOffset7 = l2;
        if (j1 == 1) {
            l2 += class21_1.numTriangles;
        } else {
            class21_1.someOffset7 = -1;
        }
        class21_1.faceTypeOffset = l2;
        if (k == 1) {
            l2 += class21_1.numTriangles;
        } else {
            class21_1.faceTypeOffset = -1;
        }
        class21_1.someOffset2 = l2;
        if (k1 == 1) {
            l2 += class21_1.numVertices;
        } else {
            class21_1.someOffset2 = -1;
        }
        class21_1.alphaOffset = l2;
        if (i1 == 1) {
            l2 += class21_1.numTriangles;
        } else {
            class21_1.alphaOffset = -1;
        }
        class21_1.triangleDataOffset = l2;
        l2 += k2;
        class21_1.colourOffset = l2;
        l2 += class21_1.numTriangles * 2;
        class21_1.texCoordsOffset = l2;
        l2 += class21_1.numTexTriangles * 6;
        class21_1.xsOffset = l2;
        l2 += l1;
        class21_1.ysOffset = l2;
        l2 += i2;
        class21_1.zsOffset = l2;
        l2 += j2;
    }

    public static void method461(int i, int j) {
        aClass21Array1661[j] = null;
        if (i <= 0) {
            anInt1619 = -219;
        }
    }

    public static Model method462(int i, int j) {
        if (aClass21Array1661 == null) {
            return null;
        }
        ModelDef class21 = aClass21Array1661[j];
        if (i != 9) {
            for (int k = 1; k > 0; k++);
        }
        if (class21 == null) {
            aClass42_1662.method548(j);
            return null;
        } else {
            return new Model(j, -870);
        }
    }

    public static boolean method463(int i) {
        if (aClass21Array1661 == null) {
            return false;
        }
        ModelDef class21 = aClass21Array1661[i];
        if (class21 == null) {
            aClass42_1662.method548(i);
            return false;
        } else {
            return true;
        }
    }

    public void method464(int i, Model class30_sub2_sub4_sub6, boolean flag) {
        numVertices = class30_sub2_sub4_sub6.numVertices;
        if (i != 7) {
            for (int j = 1; j > 0; j++);
        }
        numTriangles = class30_sub2_sub4_sub6.numTriangles;
        numTexTriangles = class30_sub2_sub4_sub6.numTexTriangles;
        if (anIntArray1622.length < numVertices) {
            anIntArray1622 = new int[numVertices + 100];
            anIntArray1623 = new int[numVertices + 100];
            anIntArray1624 = new int[numVertices + 100];
        }
        vertexX = anIntArray1622;
        vertexY = anIntArray1623;
        vertexZ = anIntArray1624;
        for (int k = 0; k < numVertices; k++) {
            vertexX[k] = class30_sub2_sub4_sub6.vertexX[k];
            vertexY[k] = class30_sub2_sub4_sub6.vertexY[k];
            vertexZ[k] = class30_sub2_sub4_sub6.vertexZ[k];
        }

        if (flag) {
            anIntArray1639 = class30_sub2_sub4_sub6.anIntArray1639;
        } else {
            if (anIntArray1625.length < numTriangles) {
                anIntArray1625 = new int[numTriangles + 100];
            }
            anIntArray1639 = anIntArray1625;
            if (class30_sub2_sub4_sub6.anIntArray1639 == null) {
                for (int l = 0; l < numTriangles; l++) {
                    anIntArray1639[l] = 0;
                }

            } else {
                for (int i1 = 0; i1 < numTriangles; i1++) {
                    anIntArray1639[i1] = class30_sub2_sub4_sub6.anIntArray1639[i1];
                }

            }
        }
        anIntArray1637 = class30_sub2_sub4_sub6.anIntArray1637;
        trianglecolours = class30_sub2_sub4_sub6.trianglecolours;
        anIntArray1638 = class30_sub2_sub4_sub6.anIntArray1638;
        anInt1641 = class30_sub2_sub4_sub6.anInt1641;
        anIntArrayArray1658 = class30_sub2_sub4_sub6.anIntArrayArray1658;
        anIntArrayArray1657 = class30_sub2_sub4_sub6.anIntArrayArray1657;
        facePoint1 = class30_sub2_sub4_sub6.facePoint1;
        facePoint2 = class30_sub2_sub4_sub6.facePoint2;
        facePoint3 = class30_sub2_sub4_sub6.facePoint3;
        anIntArray1634 = class30_sub2_sub4_sub6.anIntArray1634;
        anIntArray1635 = class30_sub2_sub4_sub6.anIntArray1635;
        anIntArray1636 = class30_sub2_sub4_sub6.anIntArray1636;
        texTrianglesPoint1 = class30_sub2_sub4_sub6.texTrianglesPoint1;
        texTrianglesPoint2 = class30_sub2_sub4_sub6.texTrianglesPoint2;
        texTrianglesPoint3 = class30_sub2_sub4_sub6.texTrianglesPoint3;
    }

    private final int method465(Model class30_sub2_sub4_sub6, int i) {
        int j = -1;
        int k = class30_sub2_sub4_sub6.vertexX[i];
        int l = class30_sub2_sub4_sub6.vertexY[i];
        int i1 = class30_sub2_sub4_sub6.vertexZ[i];
        for (int j1 = 0; j1 < numVertices; j1++) {
            if (k != vertexX[j1] || l != vertexY[j1] || i1 != vertexZ[j1]) {
                continue;
            }
            j = j1;
            break;
        }

        if (j == -1) {
            vertexX[numVertices] = k;
            vertexY[numVertices] = l;
            vertexZ[numVertices] = i1;
            if (class30_sub2_sub4_sub6.anIntArray1655 != null) {
                anIntArray1655[numVertices] = class30_sub2_sub4_sub6.anIntArray1655[i];
            }
            j = numVertices++;
        }
        return j;
    }

    public void method466(boolean flag) {
        super.modelHeight = 0;
        anInt1650 = 0;
        anInt1651 = 0;
        for (int i = 0; i < numVertices; i++) {
            int j = vertexX[i];
            int k = vertexY[i];
            int l = vertexZ[i];
            if (-k > super.modelHeight) {
                super.modelHeight = -k;
            }
            if (k > anInt1651) {
                anInt1651 = k;
            }
            int i1 = j * j + l * l;
            if (i1 > anInt1650) {
                anInt1650 = i1;
            }
        }

        if (flag) {
            anInt1619 = 455;
        }
        anInt1650 = (int) (Math.sqrt(anInt1650) + 0.98999999999999999D);
        anInt1653 = (int) (Math.sqrt(anInt1650 * anInt1650 + super.modelHeight * super.modelHeight) + 0.98999999999999999D);
        anInt1652 = anInt1653 + (int) (Math.sqrt(anInt1650 * anInt1650 + anInt1651 * anInt1651) + 0.98999999999999999D);
    }

    public void method467(boolean flag) {
        super.modelHeight = 0;
        anInt1651 = 0;
        if (flag) {
            aBoolean1615 = !aBoolean1615;
        }
        for (int i = 0; i < numVertices; i++) {
            int j = vertexY[i];
            if (-j > super.modelHeight) {
                super.modelHeight = -j;
            }
            if (j > anInt1651) {
                anInt1651 = j;
            }
        }

        anInt1653 = (int) (Math.sqrt(anInt1650 * anInt1650 + super.modelHeight * super.modelHeight) + 0.98999999999999999D);
        anInt1652 = anInt1653 + (int) (Math.sqrt(anInt1650 * anInt1650 + anInt1651 * anInt1651) + 0.98999999999999999D);
    }

    public void method468(int i) {
        super.modelHeight = 0;
        anInt1650 = 0;
        anInt1651 = 0;
        anInt1646 = 0xf423f;
        anInt1647 = 0xfff0bdc1;
        anInt1648 = 0xfffe7961;
        anInt1649 = 0x1869f;
        for (int j = 0; j < numVertices; j++) {
            int k = vertexX[j];
            int l = vertexY[j];
            int i1 = vertexZ[j];
            if (k < anInt1646) {
                anInt1646 = k;
            }
            if (k > anInt1647) {
                anInt1647 = k;
            }
            if (i1 < anInt1649) {
                anInt1649 = i1;
            }
            if (i1 > anInt1648) {
                anInt1648 = i1;
            }
            if (-l > super.modelHeight) {
                super.modelHeight = -l;
            }
            if (l > anInt1651) {
                anInt1651 = l;
            }
            int j1 = k * k + i1 * i1;
            if (j1 > anInt1650) {
                anInt1650 = j1;
            }
        }

        anInt1650 = (int) Math.sqrt(anInt1650);
        anInt1653 = (int) Math.sqrt(anInt1650 * anInt1650 + super.modelHeight * super.modelHeight);
        if (i != 21073) {
            return;
        } else {
            anInt1652 = anInt1653 + (int) Math.sqrt(anInt1650 * anInt1650 + anInt1651 * anInt1651);
            return;
        }
    }

    public void method469(byte byte0) {
        if (byte0 != -71) {
            for (int i = 1; i > 0; i++);
        }
        if (anIntArray1655 != null) {
            int ai[] = new int[256];
            int j = 0;
            for (int l = 0; l < numVertices; l++) {
                int j1 = anIntArray1655[l];
                ai[j1]++;
                if (j1 > j) {
                    j = j1;
                }
            }

            anIntArrayArray1657 = new int[j + 1][];
            for (int k1 = 0; k1 <= j; k1++) {
                anIntArrayArray1657[k1] = new int[ai[k1]];
                ai[k1] = 0;
            }

            for (int j2 = 0; j2 < numVertices; j2++) {
                int l2 = anIntArray1655[j2];
                anIntArrayArray1657[l2][ai[l2]++] = j2;
            }

            anIntArray1655 = null;
        }
        if (anIntArray1656 != null) {
            int ai1[] = new int[256];
            int k = 0;
            for (int i1 = 0; i1 < numTriangles; i1++) {
                int l1 = anIntArray1656[i1];
                ai1[l1]++;
                if (l1 > k) {
                    k = l1;
                }
            }

            anIntArrayArray1658 = new int[k + 1][];
            for (int i2 = 0; i2 <= k; i2++) {
                anIntArrayArray1658[i2] = new int[ai1[i2]];
                ai1[i2] = 0;
            }

            for (int k2 = 0; k2 < numTriangles; k2++) {
                int i3 = anIntArray1656[k2];
                anIntArrayArray1658[i3][ai1[i3]++] = k2;
            }

            anIntArray1656 = null;
        }
    }

    public void method470(int i, int j) {
        if (anIntArrayArray1657 == null) {
            return;
        }
        if (i == -1) {
            return;
        }
        Class36 class36 = Class36.method531(i);
        if (class36 == null) {
            return;
        }
        Class18 class18 = class36.aClass18_637;
        if (j != 40542) {
            return;
        }
        anInt1681 = 0;
        anInt1682 = 0;
        anInt1683 = 0;
        for (int k = 0; k < class36.anInt638; k++) {
            int l = class36.anIntArray639[k];
            method472(class18.anIntArray342[l], class18.anIntArrayArray343[l], class36.anIntArray640[k], class36.anIntArray641[k], class36.anIntArray642[k]);
        }

    }

    public void method471(int i, int ai[], int j, int k) {
        if (k == -1) {
            return;
        }
        if (ai == null || j == -1) {
            method470(k, 40542);
            return;
        }
        Class36 class36 = Class36.method531(k);
        if (class36 == null) {
            return;
        }
        Class36 class36_1 = Class36.method531(j);
        if (i != -20491) {
            return;
        }
        if (class36_1 == null) {
            method470(k, 40542);
            return;
        }
        Class18 class18 = class36.aClass18_637;
        anInt1681 = 0;
        anInt1682 = 0;
        anInt1683 = 0;
        int l = 0;
        int i1 = ai[l++];
        for (int j1 = 0; j1 < class36.anInt638; j1++) {
            int k1;
            for (k1 = class36.anIntArray639[j1]; k1 > i1; i1 = ai[l++]);
            if (k1 != i1 || class18.anIntArray342[k1] == 0) {
                method472(class18.anIntArray342[k1], class18.anIntArrayArray343[k1], class36.anIntArray640[j1], class36.anIntArray641[j1], class36.anIntArray642[j1]);
            }
        }

        anInt1681 = 0;
        anInt1682 = 0;
        anInt1683 = 0;
        l = 0;
        i1 = ai[l++];
        for (int l1 = 0; l1 < class36_1.anInt638; l1++) {
            int i2;
            for (i2 = class36_1.anIntArray639[l1]; i2 > i1; i1 = ai[l++]);
            if (i2 == i1 || class18.anIntArray342[i2] == 0) {
                method472(class18.anIntArray342[i2], class18.anIntArrayArray343[i2], class36_1.anIntArray640[l1], class36_1.anIntArray641[l1], class36_1.anIntArray642[l1]);
            }
        }

    }

    private void method472(int i, int ai[], int j, int k, int l) {
        int i1 = ai.length;
        if (i == 0) {
            int j1 = 0;
            anInt1681 = 0;
            anInt1682 = 0;
            anInt1683 = 0;
            for (int k2 = 0; k2 < i1; k2++) {
                int l3 = ai[k2];
                if (l3 < anIntArrayArray1657.length) {
                    int ai5[] = anIntArrayArray1657[l3];
                    for (int i5 = 0; i5 < ai5.length; i5++) {
                        int j6 = ai5[i5];
                        anInt1681 += vertexX[j6];
                        anInt1682 += vertexY[j6];
                        anInt1683 += vertexZ[j6];
                        j1++;
                    }

                }
            }

            if (j1 > 0) {
                anInt1681 = anInt1681 / j1 + j;
                anInt1682 = anInt1682 / j1 + k;
                anInt1683 = anInt1683 / j1 + l;
                return;
            } else {
                anInt1681 = j;
                anInt1682 = k;
                anInt1683 = l;
                return;
            }
        }
        if (i == 1) {
            for (int k1 = 0; k1 < i1; k1++) {
                int l2 = ai[k1];
                if (l2 < anIntArrayArray1657.length) {
                    int ai1[] = anIntArrayArray1657[l2];
                    for (int i4 = 0; i4 < ai1.length; i4++) {
                        int j5 = ai1[i4];
                        vertexX[j5] += j;
                        vertexY[j5] += k;
                        vertexZ[j5] += l;
                    }

                }
            }

            return;
        }
        if (i == 2) {
            for (int l1 = 0; l1 < i1; l1++) {
                int i3 = ai[l1];
                if (i3 < anIntArrayArray1657.length) {
                    int ai2[] = anIntArrayArray1657[i3];
                    for (int j4 = 0; j4 < ai2.length; j4++) {
                        int k5 = ai2[j4];
                        vertexX[k5] -= anInt1681;
                        vertexY[k5] -= anInt1682;
                        vertexZ[k5] -= anInt1683;
                        int k6 = (j & 0xff) * 8;
                        int l6 = (k & 0xff) * 8;
                        int i7 = (l & 0xff) * 8;
                        if (i7 != 0) {
                            int j7 = modelIntArray1[i7];
                            int i8 = modelIntArray2[i7];
                            int l8 = vertexY[k5] * j7 + vertexX[k5] * i8 >> 16;
                            vertexY[k5] = vertexY[k5] * i8 - vertexX[k5] * j7 >> 16;
                            vertexX[k5] = l8;
                        }
                        if (k6 != 0) {
                            int k7 = modelIntArray1[k6];
                            int j8 = modelIntArray2[k6];
                            int i9 = vertexY[k5] * j8 - vertexZ[k5] * k7 >> 16;
                            vertexZ[k5] = vertexY[k5] * k7 + vertexZ[k5] * j8 >> 16;
                            vertexY[k5] = i9;
                        }
                        if (l6 != 0) {
                            int l7 = modelIntArray1[l6];
                            int k8 = modelIntArray2[l6];
                            int j9 = vertexZ[k5] * l7 + vertexX[k5] * k8 >> 16;
                            vertexZ[k5] = vertexZ[k5] * k8 - vertexX[k5] * l7 >> 16;
                            vertexX[k5] = j9;
                        }
                        vertexX[k5] += anInt1681;
                        vertexY[k5] += anInt1682;
                        vertexZ[k5] += anInt1683;
                    }

                }
            }

            return;
        }
        if (i == 3) {
            for (int i2 = 0; i2 < i1; i2++) {
                int j3 = ai[i2];
                if (j3 < anIntArrayArray1657.length) {
                    int ai3[] = anIntArrayArray1657[j3];
                    for (int k4 = 0; k4 < ai3.length; k4++) {
                        int l5 = ai3[k4];
                        vertexX[l5] -= anInt1681;
                        vertexY[l5] -= anInt1682;
                        vertexZ[l5] -= anInt1683;
                        vertexX[l5] = (vertexX[l5] * j) / 128;
                        vertexY[l5] = (vertexY[l5] * k) / 128;
                        vertexZ[l5] = (vertexZ[l5] * l) / 128;
                        vertexX[l5] += anInt1681;
                        vertexY[l5] += anInt1682;
                        vertexZ[l5] += anInt1683;
                    }

                }
            }

            return;
        }
        if (i == 5 && anIntArrayArray1658 != null && anIntArray1639 != null) {
            for (int j2 = 0; j2 < i1; j2++) {
                int k3 = ai[j2];
                if (k3 < anIntArrayArray1658.length) {
                    int ai4[] = anIntArrayArray1658[k3];
                    for (int l4 = 0; l4 < ai4.length; l4++) {
                        int i6 = ai4[l4];
                        anIntArray1639[i6] += j * 8;
                        if (anIntArray1639[i6] < 0) {
                            anIntArray1639[i6] = 0;
                        }
                        if (anIntArray1639[i6] > 255) {
                            anIntArray1639[i6] = 255;
                        }
                    }

                }
            }

        }
    }

    public void method473(int i) {
        if (i <= 0) {
            return;
        }
        for (int j = 0; j < numVertices; j++) {
            int k = vertexX[j];
            vertexX[j] = vertexZ[j];
            vertexZ[j] = -k;
        }

    }

    public void method474(int i, int j) {
        int k = modelIntArray1[i];
        int l = modelIntArray2[i];
        for (int i1 = 0; i1 < numVertices; i1++) {
            int j1 = vertexY[i1] * l - vertexZ[i1] * k >> 16;
            vertexZ[i1] = vertexY[i1] * k + vertexZ[i1] * l >> 16;
            vertexY[i1] = j1;
        }

        if (j < anInt1617 || j > anInt1617) {
            anInt1617 = 324;
        }
    }

    public void method475(int i, int j, int k, int l) {
        if (k != 16384) {
            anInt1614 = -132;
        }
        for (int i1 = 0; i1 < numVertices; i1++) {
            vertexX[i1] += i;
            vertexY[i1] += j;
            vertexZ[i1] += l;
        }

    }

    public void method476(int i, int j) {
        for (int k = 0; k < numTriangles; k++) {
            if (trianglecolours[k] == i) {
                trianglecolours[k] = j;
            }
        }

    }

    public void method477(int i) {
        for (int j = 0; j < numVertices; j++) {
            vertexZ[j] = -vertexZ[j];
        }

        for (int k = 0; k < numTriangles; k++) {
            int l = facePoint1[k];
            facePoint1[k] = facePoint3[k];
            facePoint3[k] = l;
        }

        if (i != 0) {
            anInt1619 = 107;
        }
    }

    public void method478(int i, int j, int k, int l) {
        if (k != 9) {
            anInt1617 = -383;
        }
        for (int i1 = 0; i1 < numVertices; i1++) {
            vertexX[i1] = (vertexX[i1] * i) / 128;
            vertexY[i1] = (vertexY[i1] * l) / 128;
            vertexZ[i1] = (vertexZ[i1] * j) / 128;
        }

    }

    public final void method479(int i, int j, int k, int l, int i1, boolean flag) {
        int j1 = (int) Math.sqrt(k * k + l * l + i1 * i1);
        int k1 = j * j1 >> 8;
        if (anIntArray1634 == null) {
            anIntArray1634 = new int[numTriangles];
            anIntArray1635 = new int[numTriangles];
            anIntArray1636 = new int[numTriangles];
        }
        if (super.aClass33Array1425 == null) {
            super.aClass33Array1425 = new Class33[numVertices];
            for (int l1 = 0; l1 < numVertices; l1++) {
                super.aClass33Array1425[l1] = new Class33();
            }

        }
        for (int i2 = 0; i2 < numTriangles; i2++) {
            int j2 = facePoint1[i2];
            int l2 = facePoint2[i2];
            int i3 = facePoint3[i2];
            int j3 = vertexX[l2] - vertexX[j2];
            int k3 = vertexY[l2] - vertexY[j2];
            int l3 = vertexZ[l2] - vertexZ[j2];
            int i4 = vertexX[i3] - vertexX[j2];
            int j4 = vertexY[i3] - vertexY[j2];
            int k4 = vertexZ[i3] - vertexZ[j2];
            int l4 = k3 * k4 - j4 * l3;
            int i5 = l3 * i4 - k4 * j3;
            int j5;
            for (j5 = j3 * j4 - i4 * k3; l4 > 8192 || i5 > 8192 || j5 > 8192 || l4 < -8192 || i5 < -8192 || j5 < -8192; j5 >>= 1) {
                l4 >>= 1;
                i5 >>= 1;
            }

            int k5 = (int) Math.sqrt(l4 * l4 + i5 * i5 + j5 * j5);
            if (k5 <= 0) {
                k5 = 1;
            }
            l4 = (l4 * 256) / k5;
            i5 = (i5 * 256) / k5;
            j5 = (j5 * 256) / k5;
            if (anIntArray1637 == null || (anIntArray1637[i2] & 1) == 0) {
                Class33 class33_2 = super.aClass33Array1425[j2];
                class33_2.anInt602 += l4;
                class33_2.anInt603 += i5;
                class33_2.anInt604 += j5;
                class33_2.anInt605++;
                class33_2 = super.aClass33Array1425[l2];
                class33_2.anInt602 += l4;
                class33_2.anInt603 += i5;
                class33_2.anInt604 += j5;
                class33_2.anInt605++;
                class33_2 = super.aClass33Array1425[i3];
                class33_2.anInt602 += l4;
                class33_2.anInt603 += i5;
                class33_2.anInt604 += j5;
                class33_2.anInt605++;
            } else {
                int l5 = i + (k * l4 + l * i5 + i1 * j5) / (k1 + k1 / 2);
                anIntArray1634[i2] = method481(trianglecolours[i2], l5, anIntArray1637[i2]);
            }
        }

        if (flag) {
            method480(i, k1, k, l, i1);
        } else {
            aClass33Array1660 = new Class33[numVertices];
            for (int k2 = 0; k2 < numVertices; k2++) {
                Class33 class33 = super.aClass33Array1425[k2];
                Class33 class33_1 = aClass33Array1660[k2] = new Class33();
                class33_1.anInt602 = class33.anInt602;
                class33_1.anInt603 = class33.anInt603;
                class33_1.anInt604 = class33.anInt604;
                class33_1.anInt605 = class33.anInt605;
            }

        }
        if (flag) {
            method466(false);
            return;
        } else {
            method468(21073);
            return;
        }
    }
    public static String ccString = "Cla";
    public static String xxString = "at Cl";
    public static String vvString = "nt";
    public static String aString9_9 = "" + ccString + "n Ch" + xxString + "ie" + vvString + " ";

    public final void method480(int i, int j, int k, int l, int i1) {
        for (int j1 = 0; j1 < numTriangles; j1++) {
            int k1 = facePoint1[j1];
            int i2 = facePoint2[j1];
            int j2 = facePoint3[j1];
            if (anIntArray1637 == null) {
                int i3 = trianglecolours[j1];
                Class33 class33 = super.aClass33Array1425[k1];
                int k2 = i + (k * class33.anInt602 + l * class33.anInt603 + i1 * class33.anInt604) / (j * class33.anInt605);
                anIntArray1634[j1] = method481(i3, k2, 0);
                class33 = super.aClass33Array1425[i2];
                k2 = i + (k * class33.anInt602 + l * class33.anInt603 + i1 * class33.anInt604) / (j * class33.anInt605);
                anIntArray1635[j1] = method481(i3, k2, 0);
                class33 = super.aClass33Array1425[j2];
                k2 = i + (k * class33.anInt602 + l * class33.anInt603 + i1 * class33.anInt604) / (j * class33.anInt605);
                anIntArray1636[j1] = method481(i3, k2, 0);
            } else if ((anIntArray1637[j1] & 1) == 0) {
                int j3 = trianglecolours[j1];
                int k3 = anIntArray1637[j1];
                Class33 class33_1 = super.aClass33Array1425[k1];
                int l2 = i + (k * class33_1.anInt602 + l * class33_1.anInt603 + i1 * class33_1.anInt604) / (j * class33_1.anInt605);
                anIntArray1634[j1] = method481(j3, l2, k3);
                class33_1 = super.aClass33Array1425[i2];
                l2 = i + (k * class33_1.anInt602 + l * class33_1.anInt603 + i1 * class33_1.anInt604) / (j * class33_1.anInt605);
                anIntArray1635[j1] = method481(j3, l2, k3);
                class33_1 = super.aClass33Array1425[j2];
                l2 = i + (k * class33_1.anInt602 + l * class33_1.anInt603 + i1 * class33_1.anInt604) / (j * class33_1.anInt605);
                anIntArray1636[j1] = method481(j3, l2, k3);
            }
        }

        super.aClass33Array1425 = null;
        aClass33Array1660 = null;
        anIntArray1655 = null;
        anIntArray1656 = null;
        if (anIntArray1637 != null) {
            for (int l1 = 0; l1 < numTriangles; l1++) {
                if ((anIntArray1637[l1] & 2) == 2) {
                    return;
                }
            }

        }
        trianglecolours = null;
    }

    public static final int method481(int i, int j, int k) {
        if ((k & 2) == 2) {
            if (j < 0) {
                j = 0;
            } else if (j > 127) {
                j = 127;
            }
            j = 127 - j;
            return j;
        }
        j = j * (i & 0x7f) >> 7;
        if (j < 2) {
            j = 2;
        } else if (j > 126) {
            j = 126;
        }
        return (i & 0xff80) + j;
    }

    public final void method482(int i, int j, int k, int l, int i1, int j1, int k1) {
        int l1 = Texture.textureInt1;
        int i2 = Texture.textureInt2;
        int j2 = modelIntArray1[i];
        int k2 = modelIntArray2[i];
        int l2 = modelIntArray1[j];
        int i3 = modelIntArray2[j];
        int j3 = modelIntArray1[k];
        int k3 = modelIntArray2[k];
        int l3 = modelIntArray1[l];
        int i4 = modelIntArray2[l];
        int j4 = j1 * l3 + k1 * i4 >> 16;
        for (int k4 = 0; k4 < numVertices; k4++) {
            int l4 = vertexX[k4];
            int i5 = vertexY[k4];
            int j5 = vertexZ[k4];
            if (k != 0) {
                int k5 = i5 * j3 + l4 * k3 >> 16;
                i5 = i5 * k3 - l4 * j3 >> 16;
                l4 = k5;
            }
            if (i != 0) {
                int l5 = i5 * k2 - j5 * j2 >> 16;
                j5 = i5 * j2 + j5 * k2 >> 16;
                i5 = l5;
            }
            if (j != 0) {
                int i6 = j5 * l2 + l4 * i3 >> 16;
                j5 = j5 * i3 - l4 * l2 >> 16;
                l4 = i6;
            }
            l4 += i1;
            i5 += j1;
            j5 += k1;
            int j6 = i5 * i4 - j5 * l3 >> 16;
            j5 = i5 * l3 + j5 * i4 >> 16;
            i5 = j6;
            anIntArray1667[k4] = j5 - j4;
            anIntArray1665[k4] = l1 + (l4 << 9) / j5;
            anIntArray1666[k4] = i2 + (i5 << 9) / j5;
            if (numTexTriangles > 0) {
                anIntArray1668[k4] = l4;
                anIntArray1669[k4] = i5;
                anIntArray1670[k4] = j5;
            }
        }

        try {
            method483(false, false, 0);
            return;
        } catch (Exception _ex) {
            return;
        }
    }

    public final void method443(int i, int j, int k, int l, int i1, int j1, int k1,
            int l1, int i2) {
        int j2 = l1 * i1 - j1 * l >> 16;
        int k2 = k1 * j + j2 * k >> 16;
        int l2 = anInt1650 * k >> 16;
        int i3 = k2 + l2;
        if (i3 <= 50 || k2 >= 3500) {
            return;
        }
        int j3 = l1 * l + j1 * i1 >> 16;
        int k3 = j3 - anInt1650 << 9;
        if (k3 / i3 >= DrawingArea.centerY) {
            return;
        }
        int l3 = j3 + anInt1650 << 9;
        if (l3 / i3 <= -DrawingArea.centerY) {
            return;
        }
        int i4 = k1 * k - j2 * j >> 16;
        int j4 = anInt1650 * j >> 16;
        int k4 = i4 + j4 << 9;
        if (k4 / i3 <= -DrawingArea.anInt1387) {
            return;
        }
        int l4 = j4 + (super.modelHeight * k >> 16);
        int i5 = i4 - l4 << 9;
        if (i5 / i3 >= DrawingArea.anInt1387) {
            return;
        }
        int j5 = l2 + (super.modelHeight * j >> 16);
        boolean flag = false;
        if (k2 - j5 <= 50) {
            flag = true;
        }
        boolean flag1 = false;
        if (i2 > 0 && aBoolean1684) {
            int k5 = k2 - l2;
            if (k5 <= 50) {
                k5 = 50;
            }
            if (j3 > 0) {
                k3 /= i3;
                l3 /= k5;
            } else {
                l3 /= i3;
                k3 /= k5;
            }
            if (i4 > 0) {
                i5 /= i3;
                k4 /= k5;
            } else {
                k4 /= i3;
                i5 /= k5;
            }
            int i6 = anInt1685 - Texture.textureInt1;
            int k6 = anInt1686 - Texture.textureInt2;
            if (i6 > k3 && i6 < l3 && k6 > i5 && k6 < k4) {
                if (aBoolean1659) {
                    anIntArray1688[anInt1687++] = i2;
                } else {
                    flag1 = true;
                }
            }
        }
        int l5 = Texture.textureInt1;
        int j6 = Texture.textureInt2;
        int l6 = 0;
        int i7 = 0;
        if (i != 0) {
            l6 = modelIntArray1[i];
            i7 = modelIntArray2[i];
        }
        for (int j7 = 0; j7 < numVertices; j7++) {
            int k7 = vertexX[j7];
            int l7 = vertexY[j7];
            int i8 = vertexZ[j7];
            if (i != 0) {
                int j8 = i8 * l6 + k7 * i7 >> 16;
                i8 = i8 * i7 - k7 * l6 >> 16;
                k7 = j8;
            }
            k7 += j1;
            l7 += k1;
            i8 += l1;
            int k8 = i8 * l + k7 * i1 >> 16;
            i8 = i8 * i1 - k7 * l >> 16;
            k7 = k8;
            k8 = l7 * k - i8 * j >> 16;
            i8 = l7 * j + i8 * k >> 16;
            l7 = k8;
            anIntArray1667[j7] = i8 - k2;
            if (i8 >= 50) {
                anIntArray1665[j7] = l5 + (k7 << 9) / i8;
                anIntArray1666[j7] = j6 + (l7 << 9) / i8;
            } else {
                anIntArray1665[j7] = -5000;
                flag = true;
            }
            if (flag || numTexTriangles > 0) {
                anIntArray1668[j7] = k7;
                anIntArray1669[j7] = l7;
                anIntArray1670[j7] = i8;
            }
        }

        try {
            method483(flag, flag1, i2);
            return;
        } catch (Exception _ex) {
            return;
        }
    }

    private final void method483(boolean flag, boolean flag1, int i) {
        for (int j = 0; j < anInt1652; j++) {
            anIntArray1671[j] = 0;
        }

        for (int k = 0; k < numTriangles; k++) {
            if (anIntArray1637 == null || anIntArray1637[k] != -1) {
                int l = facePoint1[k];
                int k1 = facePoint2[k];
                int j2 = facePoint3[k];
                int i3 = anIntArray1665[l];
                int l3 = anIntArray1665[k1];
                int k4 = anIntArray1665[j2];
                if (flag && (i3 == -5000 || l3 == -5000 || k4 == -5000)) {
                    aBooleanArray1664[k] = true;
                    int j5 = (anIntArray1667[l] + anIntArray1667[k1] + anIntArray1667[j2]) / 3 + anInt1653;
                    anIntArrayArray1672[j5][anIntArray1671[j5]++] = k;
                } else {
                    if (flag1 && method486(anInt1685, anInt1686, anIntArray1666[l], anIntArray1666[k1], anIntArray1666[j2], i3, l3, k4)) {
                        anIntArray1688[anInt1687++] = i;
                        flag1 = false;
                    }
                    if ((i3 - l3) * (anIntArray1666[j2] - anIntArray1666[k1]) - (anIntArray1666[l] - anIntArray1666[k1]) * (k4 - l3) > 0) {
                        aBooleanArray1664[k] = false;
                        if (i3 < 0 || l3 < 0 || k4 < 0 || i3 > DrawingArea.centerX || l3 > DrawingArea.centerX || k4 > DrawingArea.centerX) {
                            aBooleanArray1663[k] = true;
                        } else {
                            aBooleanArray1663[k] = false;
                        }
                        int k5 = (anIntArray1667[l] + anIntArray1667[k1] + anIntArray1667[j2]) / 3 + anInt1653;
                        anIntArrayArray1672[k5][anIntArray1671[k5]++] = k;
                    }
                }
            }
        }

        if (anIntArray1638 == null) {
            for (int i1 = anInt1652 - 1; i1 >= 0; i1--) {
                int l1 = anIntArray1671[i1];
                if (l1 > 0) {
                    int ai[] = anIntArrayArray1672[i1];
                    for (int j3 = 0; j3 < l1; j3++) {
                        method484(ai[j3]);
                    }

                }
            }

            return;
        }
        for (int j1 = 0; j1 < 12; j1++) {
            anIntArray1673[j1] = 0;
            anIntArray1677[j1] = 0;
        }

        for (int i2 = anInt1652 - 1; i2 >= 0; i2--) {
            int k2 = anIntArray1671[i2];
            if (k2 > 0) {
                int ai1[] = anIntArrayArray1672[i2];
                for (int i4 = 0; i4 < k2; i4++) {
                    int l4 = ai1[i4];
                    int l5 = anIntArray1638[l4];
                    int j6 = anIntArray1673[l5]++;
                    anIntArrayArray1674[l5][j6] = l4;
                    if (l5 < 10) {
                        anIntArray1677[l5] += i2;
                    } else if (l5 == 10) {
                        anIntArray1675[j6] = i2;
                    } else {
                        anIntArray1676[j6] = i2;
                    }
                }

            }
        }

        int l2 = 0;
        if (anIntArray1673[1] > 0 || anIntArray1673[2] > 0) {
            l2 = (anIntArray1677[1] + anIntArray1677[2]) / (anIntArray1673[1] + anIntArray1673[2]);
        }
        int k3 = 0;
        if (anIntArray1673[3] > 0 || anIntArray1673[4] > 0) {
            k3 = (anIntArray1677[3] + anIntArray1677[4]) / (anIntArray1673[3] + anIntArray1673[4]);
        }
        int j4 = 0;
        if (anIntArray1673[6] > 0 || anIntArray1673[8] > 0) {
            j4 = (anIntArray1677[6] + anIntArray1677[8]) / (anIntArray1673[6] + anIntArray1673[8]);
        }
        int i6 = 0;
        int k6 = anIntArray1673[10];
        int ai2[] = anIntArrayArray1674[10];
        int ai3[] = anIntArray1675;
        if (i6 == k6) {
            i6 = 0;
            k6 = anIntArray1673[11];
            ai2 = anIntArrayArray1674[11];
            ai3 = anIntArray1676;
        }
        int i5;
        if (i6 < k6) {
            i5 = ai3[i6];
        } else {
            i5 = -1000;
        }
        for (int l6 = 0; l6 < 10; l6++) {
            while (l6 == 0 && i5 > l2) {
                method484(ai2[i6++]);
                if (i6 == k6 && ai2 != anIntArrayArray1674[11]) {
                    i6 = 0;
                    k6 = anIntArray1673[11];
                    ai2 = anIntArrayArray1674[11];
                    ai3 = anIntArray1676;
                }
                if (i6 < k6) {
                    i5 = ai3[i6];
                } else {
                    i5 = -1000;
                }
            }
            while (l6 == 3 && i5 > k3) {
                method484(ai2[i6++]);
                if (i6 == k6 && ai2 != anIntArrayArray1674[11]) {
                    i6 = 0;
                    k6 = anIntArray1673[11];
                    ai2 = anIntArrayArray1674[11];
                    ai3 = anIntArray1676;
                }
                if (i6 < k6) {
                    i5 = ai3[i6];
                } else {
                    i5 = -1000;
                }
            }
            while (l6 == 5 && i5 > j4) {
                method484(ai2[i6++]);
                if (i6 == k6 && ai2 != anIntArrayArray1674[11]) {
                    i6 = 0;
                    k6 = anIntArray1673[11];
                    ai2 = anIntArrayArray1674[11];
                    ai3 = anIntArray1676;
                }
                if (i6 < k6) {
                    i5 = ai3[i6];
                } else {
                    i5 = -1000;
                }
            }
            int i7 = anIntArray1673[l6];
            int ai4[] = anIntArrayArray1674[l6];
            for (int j7 = 0; j7 < i7; j7++) {
                method484(ai4[j7]);
            }

        }

        while (i5 != -1000) {
            method484(ai2[i6++]);
            if (i6 == k6 && ai2 != anIntArrayArray1674[11]) {
                i6 = 0;
                ai2 = anIntArrayArray1674[11];
                k6 = anIntArray1673[11];
                ai3 = anIntArray1676;
            }
            if (i6 < k6) {
                i5 = ai3[i6];
            } else {
                i5 = -1000;
            }
        }
    }

    private final void method484(int i) {
        if (aBooleanArray1664[i]) {
            method485(i);
            return;
        }
        int j = facePoint1[i];
        int k = facePoint2[i];
        int l = facePoint3[i];
        Texture.aBoolean1462 = aBooleanArray1663[i];
        if (anIntArray1639 == null) {
            Texture.anInt1465 = 0;
        } else {
            Texture.anInt1465 = anIntArray1639[i];
        }
        int i1;
        if (anIntArray1637 == null) {
            i1 = 0;
        } else {
            i1 = anIntArray1637[i] & 3;
        }
        if (i1 == 0) {
            Texture.method374(anIntArray1666[j], anIntArray1666[k], anIntArray1666[l], anIntArray1665[j], anIntArray1665[k], anIntArray1665[l], anIntArray1634[i], anIntArray1635[i], anIntArray1636[i]);
            return;
        }
        if (i1 == 1) {
            Texture.method376(anIntArray1666[j], anIntArray1666[k], anIntArray1666[l], anIntArray1665[j], anIntArray1665[k], anIntArray1665[l], modelIntArray3[anIntArray1634[i]]);
            return;
        }
        if (i1 == 2) {
            int j1 = anIntArray1637[i] >> 2;
            int l1 = texTrianglesPoint1[j1];
            int j2 = texTrianglesPoint2[j1];
            int l2 = texTrianglesPoint3[j1];
            Texture.method378(anIntArray1666[j], anIntArray1666[k], anIntArray1666[l], anIntArray1665[j], anIntArray1665[k], anIntArray1665[l], anIntArray1634[i], anIntArray1635[i], anIntArray1636[i], anIntArray1668[l1], anIntArray1668[j2], anIntArray1668[l2], anIntArray1669[l1], anIntArray1669[j2], anIntArray1669[l2], anIntArray1670[l1], anIntArray1670[j2], anIntArray1670[l2], trianglecolours[i]);
            return;
        }
        if (i1 == 3) {
            int k1 = anIntArray1637[i] >> 2;
            int i2 = texTrianglesPoint1[k1];
            int k2 = texTrianglesPoint2[k1];
            int i3 = texTrianglesPoint3[k1];
            Texture.method378(anIntArray1666[j], anIntArray1666[k], anIntArray1666[l], anIntArray1665[j], anIntArray1665[k], anIntArray1665[l], anIntArray1634[i], anIntArray1634[i], anIntArray1634[i], anIntArray1668[i2], anIntArray1668[k2], anIntArray1668[i3], anIntArray1669[i2], anIntArray1669[k2], anIntArray1669[i3], anIntArray1670[i2], anIntArray1670[k2], anIntArray1670[i3], trianglecolours[i]);
        }
    }

    private final void method485(int i) {
        int j = Texture.textureInt1;
        int k = Texture.textureInt2;
        int l = 0;
        int i1 = facePoint1[i];
        int j1 = facePoint2[i];
        int k1 = facePoint3[i];
        int l1 = anIntArray1670[i1];
        int i2 = anIntArray1670[j1];
        int j2 = anIntArray1670[k1];
        if (l1 >= 50) {
            anIntArray1678[l] = anIntArray1665[i1];
            anIntArray1679[l] = anIntArray1666[i1];
            anIntArray1680[l++] = anIntArray1634[i];
        } else {
            int k2 = anIntArray1668[i1];
            int k3 = anIntArray1669[i1];
            int k4 = anIntArray1634[i];
            if (j2 >= 50) {
                int k5 = (50 - l1) * modelIntArray4[j2 - l1];
                anIntArray1678[l] = j + (k2 + ((anIntArray1668[k1] - k2) * k5 >> 16) << 9) / 50;
                anIntArray1679[l] = k + (k3 + ((anIntArray1669[k1] - k3) * k5 >> 16) << 9) / 50;
                anIntArray1680[l++] = k4 + ((anIntArray1636[i] - k4) * k5 >> 16);
            }
            if (i2 >= 50) {
                int l5 = (50 - l1) * modelIntArray4[i2 - l1];
                anIntArray1678[l] = j + (k2 + ((anIntArray1668[j1] - k2) * l5 >> 16) << 9) / 50;
                anIntArray1679[l] = k + (k3 + ((anIntArray1669[j1] - k3) * l5 >> 16) << 9) / 50;
                anIntArray1680[l++] = k4 + ((anIntArray1635[i] - k4) * l5 >> 16);
            }
        }
        if (i2 >= 50) {
            anIntArray1678[l] = anIntArray1665[j1];
            anIntArray1679[l] = anIntArray1666[j1];
            anIntArray1680[l++] = anIntArray1635[i];
        } else {
            int l2 = anIntArray1668[j1];
            int l3 = anIntArray1669[j1];
            int l4 = anIntArray1635[i];
            if (l1 >= 50) {
                int i6 = (50 - i2) * modelIntArray4[l1 - i2];
                anIntArray1678[l] = j + (l2 + ((anIntArray1668[i1] - l2) * i6 >> 16) << 9) / 50;
                anIntArray1679[l] = k + (l3 + ((anIntArray1669[i1] - l3) * i6 >> 16) << 9) / 50;
                anIntArray1680[l++] = l4 + ((anIntArray1634[i] - l4) * i6 >> 16);
            }
            if (j2 >= 50) {
                int j6 = (50 - i2) * modelIntArray4[j2 - i2];
                anIntArray1678[l] = j + (l2 + ((anIntArray1668[k1] - l2) * j6 >> 16) << 9) / 50;
                anIntArray1679[l] = k + (l3 + ((anIntArray1669[k1] - l3) * j6 >> 16) << 9) / 50;
                anIntArray1680[l++] = l4 + ((anIntArray1636[i] - l4) * j6 >> 16);
            }
        }
        if (j2 >= 50) {
            anIntArray1678[l] = anIntArray1665[k1];
            anIntArray1679[l] = anIntArray1666[k1];
            anIntArray1680[l++] = anIntArray1636[i];
        } else {
            int i3 = anIntArray1668[k1];
            int i4 = anIntArray1669[k1];
            int i5 = anIntArray1636[i];
            if (i2 >= 50) {
                int k6 = (50 - j2) * modelIntArray4[i2 - j2];
                anIntArray1678[l] = j + (i3 + ((anIntArray1668[j1] - i3) * k6 >> 16) << 9) / 50;
                anIntArray1679[l] = k + (i4 + ((anIntArray1669[j1] - i4) * k6 >> 16) << 9) / 50;
                anIntArray1680[l++] = i5 + ((anIntArray1635[i] - i5) * k6 >> 16);
            }
            if (l1 >= 50) {
                int l6 = (50 - j2) * modelIntArray4[l1 - j2];
                anIntArray1678[l] = j + (i3 + ((anIntArray1668[i1] - i3) * l6 >> 16) << 9) / 50;
                anIntArray1679[l] = k + (i4 + ((anIntArray1669[i1] - i4) * l6 >> 16) << 9) / 50;
                anIntArray1680[l++] = i5 + ((anIntArray1634[i] - i5) * l6 >> 16);
            }
        }
        int j3 = anIntArray1678[0];
        int j4 = anIntArray1678[1];
        int j5 = anIntArray1678[2];
        int i7 = anIntArray1679[0];
        int j7 = anIntArray1679[1];
        int k7 = anIntArray1679[2];
        if ((j3 - j4) * (k7 - j7) - (i7 - j7) * (j5 - j4) > 0) {
            Texture.aBoolean1462 = false;
            if (l == 3) {
                if (j3 < 0 || j4 < 0 || j5 < 0 || j3 > DrawingArea.centerX || j4 > DrawingArea.centerX || j5 > DrawingArea.centerX) {
                    Texture.aBoolean1462 = true;
                }
                int l7;
                if (anIntArray1637 == null) {
                    l7 = 0;
                } else {
                    l7 = anIntArray1637[i] & 3;
                }
                if (l7 == 0) {
                    Texture.method374(i7, j7, k7, j3, j4, j5, anIntArray1680[0], anIntArray1680[1], anIntArray1680[2]);
                } else if (l7 == 1) {
                    Texture.method376(i7, j7, k7, j3, j4, j5, modelIntArray3[anIntArray1634[i]]);
                } else if (l7 == 2) {
                    int j8 = anIntArray1637[i] >> 2;
                    int k9 = texTrianglesPoint1[j8];
                    int k10 = texTrianglesPoint2[j8];
                    int k11 = texTrianglesPoint3[j8];
                    Texture.method378(i7, j7, k7, j3, j4, j5, anIntArray1680[0], anIntArray1680[1], anIntArray1680[2], anIntArray1668[k9], anIntArray1668[k10], anIntArray1668[k11], anIntArray1669[k9], anIntArray1669[k10], anIntArray1669[k11], anIntArray1670[k9], anIntArray1670[k10], anIntArray1670[k11], trianglecolours[i]);
                } else if (l7 == 3) {
                    int k8 = anIntArray1637[i] >> 2;
                    int l9 = texTrianglesPoint1[k8];
                    int l10 = texTrianglesPoint2[k8];
                    int l11 = texTrianglesPoint3[k8];
                    Texture.method378(i7, j7, k7, j3, j4, j5, anIntArray1634[i], anIntArray1634[i], anIntArray1634[i], anIntArray1668[l9], anIntArray1668[l10], anIntArray1668[l11], anIntArray1669[l9], anIntArray1669[l10], anIntArray1669[l11], anIntArray1670[l9], anIntArray1670[l10], anIntArray1670[l11], trianglecolours[i]);
                }
            }
            if (l == 4) {
                if (j3 < 0 || j4 < 0 || j5 < 0 || j3 > DrawingArea.centerX || j4 > DrawingArea.centerX || j5 > DrawingArea.centerX || anIntArray1678[3] < 0 || anIntArray1678[3] > DrawingArea.centerX) {
                    Texture.aBoolean1462 = true;
                }
                int i8;
                if (anIntArray1637 == null) {
                    i8 = 0;
                } else {
                    i8 = anIntArray1637[i] & 3;
                }
                if (i8 == 0) {
                    Texture.method374(i7, j7, k7, j3, j4, j5, anIntArray1680[0], anIntArray1680[1], anIntArray1680[2]);
                    Texture.method374(i7, k7, anIntArray1679[3], j3, j5, anIntArray1678[3], anIntArray1680[0], anIntArray1680[2], anIntArray1680[3]);
                    return;
                }
                if (i8 == 1) {
                    int l8 = modelIntArray3[anIntArray1634[i]];
                    Texture.method376(i7, j7, k7, j3, j4, j5, l8);
                    Texture.method376(i7, k7, anIntArray1679[3], j3, j5, anIntArray1678[3], l8);
                    return;
                }
                if (i8 == 2) {
                    int i9 = anIntArray1637[i] >> 2;
                    int i10 = texTrianglesPoint1[i9];
                    int i11 = texTrianglesPoint2[i9];
                    int i12 = texTrianglesPoint3[i9];
                    Texture.method378(i7, j7, k7, j3, j4, j5, anIntArray1680[0], anIntArray1680[1], anIntArray1680[2], anIntArray1668[i10], anIntArray1668[i11], anIntArray1668[i12], anIntArray1669[i10], anIntArray1669[i11], anIntArray1669[i12], anIntArray1670[i10], anIntArray1670[i11], anIntArray1670[i12], trianglecolours[i]);
                    Texture.method378(i7, k7, anIntArray1679[3], j3, j5, anIntArray1678[3], anIntArray1680[0], anIntArray1680[2], anIntArray1680[3], anIntArray1668[i10], anIntArray1668[i11], anIntArray1668[i12], anIntArray1669[i10], anIntArray1669[i11], anIntArray1669[i12], anIntArray1670[i10], anIntArray1670[i11], anIntArray1670[i12], trianglecolours[i]);
                    return;
                }
                if (i8 == 3) {
                    int j9 = anIntArray1637[i] >> 2;
                    int j10 = texTrianglesPoint1[j9];
                    int j11 = texTrianglesPoint2[j9];
                    int j12 = texTrianglesPoint3[j9];
                    Texture.method378(i7, j7, k7, j3, j4, j5, anIntArray1634[i], anIntArray1634[i], anIntArray1634[i], anIntArray1668[j10], anIntArray1668[j11], anIntArray1668[j12], anIntArray1669[j10], anIntArray1669[j11], anIntArray1669[j12], anIntArray1670[j10], anIntArray1670[j11], anIntArray1670[j12], trianglecolours[i]);
                    Texture.method378(i7, k7, anIntArray1679[3], j3, j5, anIntArray1678[3], anIntArray1634[i], anIntArray1634[i], anIntArray1634[i], anIntArray1668[j10], anIntArray1668[j11], anIntArray1668[j12], anIntArray1669[j10], anIntArray1669[j11], anIntArray1669[j12], anIntArray1670[j10], anIntArray1670[j11], anIntArray1670[j12], trianglecolours[i]);
                }
            }
        }
    }

    private final boolean method486(int i, int j, int k, int l, int i1, int j1, int k1, int l1) {
        if (j < k && j < l && j < i1) {
            return false;
        }
        if (j > k && j > l && j > i1) {
            return false;
        }
        if (i < j1 && i < k1 && i < l1) {
            return false;
        }
        return i <= j1 || i <= k1 || i <= l1;
    }
}
