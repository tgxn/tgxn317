package client;

import client.ondemand.OnDemandFetcherParent;
import client.drawing.DrawingArea;
import client.animation.AnimationFrame;
import client.animation.Animable;

public class Model extends Entity {
    
    public static Model aModel_1621 = new Model();
    private static int anIntArray1622[] = new int[2000];
    private static int anIntArray1623[] = new int[2000];
    private static int anIntArray1624[] = new int[2000];
    private static int anIntArray1625[] = new int[2000];
    public int vertexCount;
    public int vertexX[];
    public int vertexY[];
    public int vertexZ[];
    public int triangleCount;
    public int facePoint1[];
    public int facePoint2[];
    public int facePoint3[];
    public int triangleHslA[];
    public int triangleHslB[];
    public int triangleHslC[];
    public int triangleDrawType[];
    public int facePriority[];
    public int triangleAlpha[];
    public int triangleColourOrTexture[];
    
    public int anInt1641;
    public int textureTriangleCount;
    public int triPIndex[];
    public int triMIndex[];
    public int triNIndex[];
    public int minX;
    public int maxX;
    public int maxZ;
    public int minZ;
    public int diagonal2DAboveorigin;
    public int maxY;
    public int diagonal3D;
    public int diagonal3DAboveorigin;
    public int anInt1654;
    public int vertexVSkin[];
    public int triangleTSkin[];
    public int vertexSkin[][];
    public int triangleSkin[][];
    public boolean oneSquareModel;
    VertexNormal vertexNormalOffset[];
    static ModelHeader modelHeaderCache[];
    static OnDemandFetcherParent abstractODFetcher;
    static boolean aBooleanArray1663[] = new boolean[4096];
    static boolean aBooleanArray1664[] = new boolean[4096];
    static int vertexSX[] = new int[4096];
    static int vertexSY[] = new int[4096];
    static int depthBuffer[] = new int[4096];
    static int vertexMvX[] = new int[4096];
    static int vertexMvY[] = new int[4096];
    static int vertexMvZ[] = new int[4096];
    static int depthListIndices[] = new int[1500];
    static int faceLists[][] = new int[1500][512];
    static int anIntArray1673[] = new int[12];
    static int anIntArrayArray1674[][] = new int[12][2000];
    static int anIntArray1675[] = new int[2000];
    static int anIntArray1676[] = new int[2000];
    static int anIntArray1677[] = new int[12];
    static int anIntArray1678[] = new int[10];
    static int anIntArray1679[] = new int[10];
    static int anIntArray1680[] = new int[10];
    static int vertexXModifier;
    static int vertexYModifier;
    static int vertexZModifier;
    public static boolean aBoolean1684;
    public static int cursorXPos;
    public static int cursorYPos;
    public static int resourceCount;
    public static int resourceIDTag[] = new int[1000];
    public static int SINE[];
    public static int COSINE[];
    static int HSL2RGB[];
    static int modelIntArray4[];

    static {
        SINE = Rasterizer.SINE;
        COSINE = Rasterizer.COSINE;
        HSL2RGB = Rasterizer.hsl2rgb;
        modelIntArray4 = Rasterizer.anIntArray1469;
    }
    
    private Model() {
        oneSquareModel = false;
    }

    private Model(int i) {

        oneSquareModel = false;
        ModelHeader class21 = modelHeaderCache[i];
        vertexCount = class21.numVertices;
        triangleCount = class21.numTriangles;
        textureTriangleCount = class21.numTexTriangles;
        vertexX = new int[vertexCount];
        vertexY = new int[vertexCount];
        vertexZ = new int[vertexCount];
        facePoint1 = new int[triangleCount];
        facePoint2 = new int[triangleCount];
        facePoint3 = new int[triangleCount];
        triPIndex = new int[textureTriangleCount];
        triMIndex = new int[textureTriangleCount];
        triNIndex = new int[textureTriangleCount];
        if (class21.someOffset2 >= 0) {
            vertexVSkin = new int[vertexCount];
        }
        if (class21.faceTypeOffset >= 0) {
            triangleDrawType = new int[triangleCount];
        }
        if (class21.someOffset5 >= 0) {
            facePriority = new int[triangleCount];
        } else {
            anInt1641 = -class21.someOffset5 - 1;
        }
        if (class21.alphaOffset >= 0) {
            triangleAlpha = new int[triangleCount];
        }
        if (class21.someOffset7 >= 0) {
            triangleTSkin = new int[triangleCount];
        }
        triangleColourOrTexture = new int[triangleCount];
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
        for (int j1 = 0; j1 < vertexCount; j1++) {
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
            if (vertexVSkin != null) {
                vertexVSkin[j1] = class30_sub2_sub2_4.readUnsignedByte();
            }
        }

        class30_sub2_sub2.currentOffset = class21.colourOffset;
        class30_sub2_sub2_1.currentOffset = class21.faceTypeOffset;
        class30_sub2_sub2_2.currentOffset = class21.someOffset5;
        class30_sub2_sub2_3.currentOffset = class21.alphaOffset;
        class30_sub2_sub2_4.currentOffset = class21.someOffset7;
        for (int l1 = 0; l1 < triangleCount; l1++) {
            triangleColourOrTexture[l1] = class30_sub2_sub2.readUnsignedWord();
            if (triangleDrawType != null) {
                triangleDrawType[l1] = class30_sub2_sub2_1.readUnsignedByte();
            }
            if (facePriority != null) {
                facePriority[l1] = class30_sub2_sub2_2.readUnsignedByte();
            }
            if (triangleAlpha != null) {
                triangleAlpha[l1] = class30_sub2_sub2_3.readUnsignedByte();
            }
            if (triangleTSkin != null) {
                triangleTSkin[l1] = class30_sub2_sub2_4.readUnsignedByte();
            }
        }

        class30_sub2_sub2.currentOffset = class21.triangleDataOffset;
        class30_sub2_sub2_1.currentOffset = class21.triangleTypeOffset;
        int j2 = 0;
        int l2 = 0;
        int j3 = 0;
        int k3 = 0;
        for (int l3 = 0; l3 < triangleCount; l3++) {
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
        for (int j4 = 0; j4 < textureTriangleCount; j4++) {
            triPIndex[j4] = class30_sub2_sub2.readUnsignedWord();
            triMIndex[j4] = class30_sub2_sub2.readUnsignedWord();
            triNIndex[j4] = class30_sub2_sub2.readUnsignedWord();
        }

    }

    public Model(int i, Model aclass30_sub2_sub4_sub6[]) {
        oneSquareModel = false;
        boolean flag = false;
        boolean flag1 = false;
        boolean flag2 = false;
        boolean flag3 = false;
        vertexCount = 0;
        triangleCount = 0;
        textureTriangleCount = 0;
        anInt1641 = -1;
        for (int k = 0; k < i; k++) {
            Model class30_sub2_sub4_sub6 = aclass30_sub2_sub4_sub6[k];
            if (class30_sub2_sub4_sub6 != null) {
                vertexCount += class30_sub2_sub4_sub6.vertexCount;
                triangleCount += class30_sub2_sub4_sub6.triangleCount;
                textureTriangleCount += class30_sub2_sub4_sub6.textureTriangleCount;
                flag |= class30_sub2_sub4_sub6.triangleDrawType != null;
                if (class30_sub2_sub4_sub6.facePriority != null) {
                    flag1 = true;
                } else {
                    if (anInt1641 == -1) {
                        anInt1641 = class30_sub2_sub4_sub6.anInt1641;
                    }
                    if (anInt1641 != class30_sub2_sub4_sub6.anInt1641) {
                        flag1 = true;
                    }
                }
                flag2 |= class30_sub2_sub4_sub6.triangleAlpha != null;
                flag3 |= class30_sub2_sub4_sub6.triangleTSkin != null;
            }
        }

        vertexX = new int[vertexCount];
        vertexY = new int[vertexCount];
        vertexZ = new int[vertexCount];
        vertexVSkin = new int[vertexCount];
        facePoint1 = new int[triangleCount];
        facePoint2 = new int[triangleCount];
        facePoint3 = new int[triangleCount];
        triPIndex = new int[textureTriangleCount];
        triMIndex = new int[textureTriangleCount];
        triNIndex = new int[textureTriangleCount];
        if (flag) {
            triangleDrawType = new int[triangleCount];
        }
        if (flag1) {
            facePriority = new int[triangleCount];
        }
        if (flag2) {
            triangleAlpha = new int[triangleCount];
        }
        if (flag3) {
            triangleTSkin = new int[triangleCount];
        }
        triangleColourOrTexture = new int[triangleCount];
        vertexCount = 0;
        triangleCount = 0;
        textureTriangleCount = 0;
        int l = 0;
        for (int i1 = 0; i1 < i; i1++) {
            Model class30_sub2_sub4_sub6_1 = aclass30_sub2_sub4_sub6[i1];
            if (class30_sub2_sub4_sub6_1 != null) {
                for (int j1 = 0; j1 < class30_sub2_sub4_sub6_1.triangleCount; j1++) {
                    if (flag) {
                        if (class30_sub2_sub4_sub6_1.triangleDrawType == null) {
                            triangleDrawType[triangleCount] = 0;
                        } else {
                            int k1 = class30_sub2_sub4_sub6_1.triangleDrawType[j1];
                            if ((k1 & 2) == 2) {
                                k1 += l << 2;
                            }
                            triangleDrawType[triangleCount] = k1;
                        }
                    }
                    if (flag1) {
                        if (class30_sub2_sub4_sub6_1.facePriority == null) {
                            facePriority[triangleCount] = class30_sub2_sub4_sub6_1.anInt1641;
                        } else {
                            facePriority[triangleCount] = class30_sub2_sub4_sub6_1.facePriority[j1];
                        }
                    }
                    if (flag2) {
                        if (class30_sub2_sub4_sub6_1.triangleAlpha == null) {
                            triangleAlpha[triangleCount] = 0;
                        } else {
                            triangleAlpha[triangleCount] = class30_sub2_sub4_sub6_1.triangleAlpha[j1];
                        }
                    }
                    if (flag3 && class30_sub2_sub4_sub6_1.triangleTSkin != null) {
                        triangleTSkin[triangleCount] = class30_sub2_sub4_sub6_1.triangleTSkin[j1];
                    }
                    triangleColourOrTexture[triangleCount] = class30_sub2_sub4_sub6_1.triangleColourOrTexture[j1];
                    facePoint1[triangleCount] = method465(class30_sub2_sub4_sub6_1, class30_sub2_sub4_sub6_1.facePoint1[j1]);
                    facePoint2[triangleCount] = method465(class30_sub2_sub4_sub6_1, class30_sub2_sub4_sub6_1.facePoint2[j1]);
                    facePoint3[triangleCount] = method465(class30_sub2_sub4_sub6_1, class30_sub2_sub4_sub6_1.facePoint3[j1]);
                    triangleCount++;
                }

                for (int l1 = 0; l1 < class30_sub2_sub4_sub6_1.textureTriangleCount; l1++) {
                    triPIndex[textureTriangleCount] = method465(class30_sub2_sub4_sub6_1, class30_sub2_sub4_sub6_1.triPIndex[l1]);
                    triMIndex[textureTriangleCount] = method465(class30_sub2_sub4_sub6_1, class30_sub2_sub4_sub6_1.triMIndex[l1]);
                    triNIndex[textureTriangleCount] = method465(class30_sub2_sub4_sub6_1, class30_sub2_sub4_sub6_1.triNIndex[l1]);
                    textureTriangleCount++;
                }

                l += class30_sub2_sub4_sub6_1.textureTriangleCount;
            }
        }

    }

    public Model(int i, int j, boolean flag, Model aclass30_sub2_sub4_sub6[]) {
        oneSquareModel = false;
        boolean flag1 = false;
        boolean flag2 = false;
        boolean flag3 = false;
        boolean flag4 = false;
        vertexCount = 0;
        triangleCount = 0;
        textureTriangleCount = 0;
        anInt1641 = -1;
        for (int k = 0; k < i; k++) {
            Model class30_sub2_sub4_sub6 = aclass30_sub2_sub4_sub6[k];
            if (class30_sub2_sub4_sub6 != null) {
                vertexCount += class30_sub2_sub4_sub6.vertexCount;
                triangleCount += class30_sub2_sub4_sub6.triangleCount;
                textureTriangleCount += class30_sub2_sub4_sub6.textureTriangleCount;
                flag1 |= class30_sub2_sub4_sub6.triangleDrawType != null;
                if (class30_sub2_sub4_sub6.facePriority != null) {
                    flag2 = true;
                } else {
                    if (anInt1641 == -1) {
                        anInt1641 = class30_sub2_sub4_sub6.anInt1641;
                    }
                    if (anInt1641 != class30_sub2_sub4_sub6.anInt1641) {
                        flag2 = true;
                    }
                }
                flag3 |= class30_sub2_sub4_sub6.triangleAlpha != null;
                flag4 |= class30_sub2_sub4_sub6.triangleColourOrTexture != null;
            }
        }

        vertexX = new int[vertexCount];
        vertexY = new int[vertexCount];
        vertexZ = new int[vertexCount];
        facePoint1 = new int[triangleCount];
        facePoint2 = new int[triangleCount];
        facePoint3 = new int[triangleCount];
        triangleHslA = new int[triangleCount];
        triangleHslB = new int[triangleCount];
        triangleHslC = new int[triangleCount];
        triPIndex = new int[textureTriangleCount];
        triMIndex = new int[textureTriangleCount];
        triNIndex = new int[textureTriangleCount];
        if (j >= 0) {
            for (int l = 1; l > 0; l++);
        }
        if (flag1) {
            triangleDrawType = new int[triangleCount];
        }
        if (flag2) {
            facePriority = new int[triangleCount];
        }
        if (flag3) {
            triangleAlpha = new int[triangleCount];
        }
        if (flag4) {
            triangleColourOrTexture = new int[triangleCount];
        }
        vertexCount = 0;
        triangleCount = 0;
        textureTriangleCount = 0;
        int i1 = 0;
        for (int j1 = 0; j1 < i; j1++) {
            Model class30_sub2_sub4_sub6_1 = aclass30_sub2_sub4_sub6[j1];
            if (class30_sub2_sub4_sub6_1 != null) {
                int k1 = vertexCount;
                for (int l1 = 0; l1 < class30_sub2_sub4_sub6_1.vertexCount; l1++) {
                    vertexX[vertexCount] = class30_sub2_sub4_sub6_1.vertexX[l1];
                    vertexY[vertexCount] = class30_sub2_sub4_sub6_1.vertexY[l1];
                    vertexZ[vertexCount] = class30_sub2_sub4_sub6_1.vertexZ[l1];
                    vertexCount++;
                }

                for (int i2 = 0; i2 < class30_sub2_sub4_sub6_1.triangleCount; i2++) {
                    facePoint1[triangleCount] = class30_sub2_sub4_sub6_1.facePoint1[i2] + k1;
                    facePoint2[triangleCount] = class30_sub2_sub4_sub6_1.facePoint2[i2] + k1;
                    facePoint3[triangleCount] = class30_sub2_sub4_sub6_1.facePoint3[i2] + k1;
                    triangleHslA[triangleCount] = class30_sub2_sub4_sub6_1.triangleHslA[i2];
                    triangleHslB[triangleCount] = class30_sub2_sub4_sub6_1.triangleHslB[i2];
                    triangleHslC[triangleCount] = class30_sub2_sub4_sub6_1.triangleHslC[i2];
                    if (flag1) {
                        if (class30_sub2_sub4_sub6_1.triangleDrawType == null) {
                            triangleDrawType[triangleCount] = 0;
                        } else {
                            int j2 = class30_sub2_sub4_sub6_1.triangleDrawType[i2];
                            if ((j2 & 2) == 2) {
                                j2 += i1 << 2;
                            }
                            triangleDrawType[triangleCount] = j2;
                        }
                    }
                    if (flag2) {
                        if (class30_sub2_sub4_sub6_1.facePriority == null) {
                            facePriority[triangleCount] = class30_sub2_sub4_sub6_1.anInt1641;
                        } else {
                            facePriority[triangleCount] = class30_sub2_sub4_sub6_1.facePriority[i2];
                        }
                    }
                    if (flag3) {
                        if (class30_sub2_sub4_sub6_1.triangleAlpha == null) {
                            triangleAlpha[triangleCount] = 0;
                        } else {
                            triangleAlpha[triangleCount] = class30_sub2_sub4_sub6_1.triangleAlpha[i2];
                        }
                    }
                    if (flag4 && class30_sub2_sub4_sub6_1.triangleColourOrTexture != null) {
                        triangleColourOrTexture[triangleCount] = class30_sub2_sub4_sub6_1.triangleColourOrTexture[i2];
                    }
                    triangleCount++;
                }

                for (int k2 = 0; k2 < class30_sub2_sub4_sub6_1.textureTriangleCount; k2++) {
                    triPIndex[textureTriangleCount] = class30_sub2_sub4_sub6_1.triPIndex[k2] + k1;
                    triMIndex[textureTriangleCount] = class30_sub2_sub4_sub6_1.triMIndex[k2] + k1;
                    triNIndex[textureTriangleCount] = class30_sub2_sub4_sub6_1.triNIndex[k2] + k1;
                    textureTriangleCount++;
                }

                i1 += class30_sub2_sub4_sub6_1.textureTriangleCount;
            }
        }

        calculateDiagonals();
    }

    public Model(int i, boolean flag, boolean flag1, boolean flag2, Model class30_sub2_sub4_sub6) {
        oneSquareModel = false;
        vertexCount = class30_sub2_sub4_sub6.vertexCount;
        triangleCount = class30_sub2_sub4_sub6.triangleCount;
        textureTriangleCount = class30_sub2_sub4_sub6.textureTriangleCount;
        if (flag2) {
            vertexX = class30_sub2_sub4_sub6.vertexX;
            vertexY = class30_sub2_sub4_sub6.vertexY;
            vertexZ = class30_sub2_sub4_sub6.vertexZ;
        } else {
            vertexX = new int[vertexCount];
            vertexY = new int[vertexCount];
            vertexZ = new int[vertexCount];
            for (int j = 0; j < vertexCount; j++) {
                vertexX[j] = class30_sub2_sub4_sub6.vertexX[j];
                vertexY[j] = class30_sub2_sub4_sub6.vertexY[j];
                vertexZ[j] = class30_sub2_sub4_sub6.vertexZ[j];
            }

        }
        if (flag) {
            triangleColourOrTexture = class30_sub2_sub4_sub6.triangleColourOrTexture;
        } else {
            triangleColourOrTexture = new int[triangleCount];
            for (int k = 0; k < triangleCount; k++) {
                triangleColourOrTexture[k] = class30_sub2_sub4_sub6.triangleColourOrTexture[k];
            }

        }
        if (flag1) {
            triangleAlpha = class30_sub2_sub4_sub6.triangleAlpha;
        } else {
            triangleAlpha = new int[triangleCount];
            if (class30_sub2_sub4_sub6.triangleAlpha == null) {
                for (int l = 0; l < triangleCount; l++) {
                    triangleAlpha[l] = 0;
                }

            } else {
                for (int i1 = 0; i1 < triangleCount; i1++) {
                    triangleAlpha[i1] = class30_sub2_sub4_sub6.triangleAlpha[i1];
                }

            }
        }
        vertexVSkin = class30_sub2_sub4_sub6.vertexVSkin;
        triangleTSkin = class30_sub2_sub4_sub6.triangleTSkin;
        triangleDrawType = class30_sub2_sub4_sub6.triangleDrawType;
        facePoint1 = class30_sub2_sub4_sub6.facePoint1;
        facePoint2 = class30_sub2_sub4_sub6.facePoint2;
        facePoint3 = class30_sub2_sub4_sub6.facePoint3;
        facePriority = class30_sub2_sub4_sub6.facePriority;
        anInt1641 = class30_sub2_sub4_sub6.anInt1641;
        triPIndex = class30_sub2_sub4_sub6.triPIndex;
        if (i < 9 || i > 9) {
            for (int j1 = 1; j1 > 0; j1++);
        }
        triMIndex = class30_sub2_sub4_sub6.triMIndex;
        triNIndex = class30_sub2_sub4_sub6.triNIndex;
    }

    public Model(boolean flag, int i, boolean flag1, Model class30_sub2_sub4_sub6) {
        oneSquareModel = false;
        vertexCount = class30_sub2_sub4_sub6.vertexCount;
        triangleCount = class30_sub2_sub4_sub6.triangleCount;
        textureTriangleCount = class30_sub2_sub4_sub6.textureTriangleCount;
        if (flag) {
            vertexY = new int[vertexCount];
            for (int j = 0; j < vertexCount; j++) {
                vertexY[j] = class30_sub2_sub4_sub6.vertexY[j];
            }

        } else {
            vertexY = class30_sub2_sub4_sub6.vertexY;
        }
        if (flag1) {
            triangleHslA = new int[triangleCount];
            triangleHslB = new int[triangleCount];
            triangleHslC = new int[triangleCount];
            for (int k = 0; k < triangleCount; k++) {
                triangleHslA[k] = class30_sub2_sub4_sub6.triangleHslA[k];
                triangleHslB[k] = class30_sub2_sub4_sub6.triangleHslB[k];
                triangleHslC[k] = class30_sub2_sub4_sub6.triangleHslC[k];
            }

            triangleDrawType = new int[triangleCount];
            if (class30_sub2_sub4_sub6.triangleDrawType == null) {
                for (int l = 0; l < triangleCount; l++) {
                    triangleDrawType[l] = 0;
                }

            } else {
                for (int i1 = 0; i1 < triangleCount; i1++) {
                    triangleDrawType[i1] = class30_sub2_sub4_sub6.triangleDrawType[i1];
                }

            }
            super.aClass33Array1425 = new VertexNormal[vertexCount];
            for (int j1 = 0; j1 < vertexCount; j1++) {
                VertexNormal class33 = super.aClass33Array1425[j1] = new VertexNormal();
                VertexNormal class33_1 = ((Animable) (class30_sub2_sub4_sub6)).aClass33Array1425[j1];
                class33.anInt602 = class33_1.anInt602;
                class33.anInt603 = class33_1.anInt603;
                class33.anInt604 = class33_1.anInt604;
                class33.anInt605 = class33_1.anInt605;
            }

            vertexNormalOffset = class30_sub2_sub4_sub6.vertexNormalOffset;
        } else {
            triangleHslA = class30_sub2_sub4_sub6.triangleHslA;
            triangleHslB = class30_sub2_sub4_sub6.triangleHslB;
            triangleHslC = class30_sub2_sub4_sub6.triangleHslC;
            triangleDrawType = class30_sub2_sub4_sub6.triangleDrawType;
        }
        vertexX = class30_sub2_sub4_sub6.vertexX;
        vertexZ = class30_sub2_sub4_sub6.vertexZ;
        triangleColourOrTexture = class30_sub2_sub4_sub6.triangleColourOrTexture;
        triangleAlpha = class30_sub2_sub4_sub6.triangleAlpha;
        facePriority = class30_sub2_sub4_sub6.facePriority;
        anInt1641 = class30_sub2_sub4_sub6.anInt1641;
        facePoint1 = class30_sub2_sub4_sub6.facePoint1;
        facePoint2 = class30_sub2_sub4_sub6.facePoint2;
        facePoint3 = class30_sub2_sub4_sub6.facePoint3;
        triPIndex = class30_sub2_sub4_sub6.triPIndex;
        triMIndex = class30_sub2_sub4_sub6.triMIndex;
        triNIndex = class30_sub2_sub4_sub6.triNIndex;
        super.modelHeight = ((Animable) (class30_sub2_sub4_sub6)).modelHeight;
        for (maxY = class30_sub2_sub4_sub6.maxY; i >= 0;) {
            throw new NullPointerException();
        }

        diagonal2DAboveorigin = class30_sub2_sub4_sub6.diagonal2DAboveorigin;
        diagonal3DAboveorigin = class30_sub2_sub4_sub6.diagonal3DAboveorigin;
        diagonal3D = class30_sub2_sub4_sub6.diagonal3D;
        minX = class30_sub2_sub4_sub6.minX;
        maxZ = class30_sub2_sub4_sub6.maxZ;
        minZ = class30_sub2_sub4_sub6.minZ;
        maxX = class30_sub2_sub4_sub6.maxX;
    }
    
    public static void nullLoader() {
        modelHeaderCache = null;
        aBooleanArray1663 = null;
        aBooleanArray1664 = null;
        vertexSY = null;
        depthBuffer = null;
        vertexMvX = null;
        vertexMvY = null;
        vertexMvZ = null;
        depthListIndices = null;
        faceLists = null;
        anIntArray1673 = null;
        anIntArrayArray1674 = null;
        anIntArray1675 = null;
        anIntArray1676 = null;
        anIntArray1677 = null;
        SINE = null;
        COSINE = null;
        HSL2RGB = null;
        modelIntArray4 = null;
    }

    public static void initialize(int i, OnDemandFetcherParent onDemandFetcherParent) {
        modelHeaderCache = new ModelHeader[i];
        abstractODFetcher = onDemandFetcherParent;
    }

    public static void readRsModel(byte abyte0[], int i, int j) {
        if (abyte0 == null) {
            ModelHeader class21 = modelHeaderCache[j] = new ModelHeader();
            class21.numVertices = 0;
            class21.numTriangles = 0;
            class21.numTexTriangles = 0;
            return;
        }
        Stream class30_sub2_sub2 = new Stream(abyte0);
        class30_sub2_sub2.currentOffset = abyte0.length - 18;
        ModelHeader class21_1 = modelHeaderCache[j] = new ModelHeader();
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

    public static void method461(int j) {
        modelHeaderCache[j] = null;
    }

    public static Model getModel(int j) {
        if (modelHeaderCache == null) {
            return null;
        }
        ModelHeader class21 = modelHeaderCache[j];
        if (class21 == null) {
            abstractODFetcher.requestData(j);
            return null;
        } else {
            return new Model(j);
        }
    }

    public static boolean isCached(int i) {
        if (modelHeaderCache == null) {
            return false;
        }
        ModelHeader modelHeader = modelHeaderCache[i];
        if (modelHeader == null) {
            abstractODFetcher.requestData(i);
            return false;
        } else {
            return true;
        }
    }

    public void method464(Model model, boolean flag) {
        vertexCount = model.vertexCount;
        triangleCount = model.triangleCount;
        textureTriangleCount = model.textureTriangleCount;
        if (anIntArray1622.length < vertexCount) {
            anIntArray1622 = new int[vertexCount + 100];
            anIntArray1623 = new int[vertexCount + 100];
            anIntArray1624 = new int[vertexCount + 100];
        }
        vertexX = anIntArray1622;
        vertexY = anIntArray1623;
        vertexZ = anIntArray1624;
        for (int k = 0; k < vertexCount; k++) {
            vertexX[k] = model.vertexX[k];
            vertexY[k] = model.vertexY[k];
            vertexZ[k] = model.vertexZ[k];
        }

        if (flag) {
            triangleAlpha = model.triangleAlpha;
        } else {
            if (anIntArray1625.length < triangleCount) {
                anIntArray1625 = new int[triangleCount + 100];
            }
            triangleAlpha = anIntArray1625;
            if (model.triangleAlpha == null) {
                for (int l = 0; l < triangleCount; l++) {
                    triangleAlpha[l] = 0;
                }

            } else {
                System.arraycopy(model.triangleAlpha, 0, triangleAlpha, 0, triangleCount);

            }
        }
        triangleDrawType = model.triangleDrawType;
        triangleColourOrTexture = model.triangleColourOrTexture;
        facePriority = model.facePriority;
        anInt1641 = model.anInt1641;
        triangleSkin = model.triangleSkin;
        vertexSkin = model.vertexSkin;
        facePoint1 = model.facePoint1;
        facePoint2 = model.facePoint2;
        facePoint3 = model.facePoint3;
        triangleHslA = model.triangleHslA;
        triangleHslB = model.triangleHslB;
        triangleHslC = model.triangleHslC;
        triPIndex = model.triPIndex;
        triMIndex = model.triMIndex;
        triNIndex = model.triNIndex;
    }

    private int method465(Model model, int i) {
        int j = -1;
        int x = model.vertexX[i];
        int y = model.vertexY[i];
        int z = model.vertexZ[i];
        for (int j1 = 0; j1 < vertexCount; j1++) {
            if (x != vertexX[j1] || y != vertexY[j1] || z != vertexZ[j1]) {
                continue;
            }
            j = j1;
            break;
        }

        if (j == -1) {
            vertexX[vertexCount] = x;
            vertexY[vertexCount] = y;
            vertexZ[vertexCount] = z;
            if (model.vertexVSkin != null) {
                vertexVSkin[vertexCount] = model.vertexVSkin[i];
            }
            j = vertexCount++;
        }
        return j;
    }

    public void calculateDiagonals() {
        super.modelHeight = 0;
        diagonal2DAboveorigin = 0;
        maxY = 0;
        for (int i = 0; i < vertexCount; i++) {
            int j = vertexX[i];
            int k = vertexY[i];
            int l = vertexZ[i];
            if (-k > super.modelHeight) {
                super.modelHeight = -k;
            }
            if (k > maxY) {
                maxY = k;
            }
            int i1 = j * j + l * l;
            if (i1 > diagonal2DAboveorigin) {
                diagonal2DAboveorigin = i1;
            }
        }
        diagonal2DAboveorigin = (int) (Math.sqrt(diagonal2DAboveorigin) + 0.98999999999999999D);
        diagonal3DAboveorigin = (int) (Math.sqrt(diagonal2DAboveorigin * diagonal2DAboveorigin + super.modelHeight * super.modelHeight) + 0.98999999999999999D);
        diagonal3D = diagonal3DAboveorigin + (int) (Math.sqrt(diagonal2DAboveorigin * diagonal2DAboveorigin + maxY * maxY) + 0.98999999999999999D);
    }

    public void method467() {
        super.modelHeight = 0;
        maxY = 0;
        for (int i = 0; i < vertexCount; i++) {
            int j = vertexY[i];
            if (-j > super.modelHeight) {
                super.modelHeight = -j;
            }
            if (j > maxY) {
                maxY = j;
            }
        }

        diagonal3DAboveorigin = (int) (Math.sqrt(diagonal2DAboveorigin * diagonal2DAboveorigin + super.modelHeight * super.modelHeight) + 0.98999999999999999D);
        diagonal3D = diagonal3DAboveorigin + (int) (Math.sqrt(diagonal2DAboveorigin * diagonal2DAboveorigin + maxY * maxY) + 0.98999999999999999D);
    }

    public void calculateDiagonalsAndStats() {
        super.modelHeight = 0;
        diagonal2DAboveorigin = 0;
        maxY = 0;
        minX = 0xf423f;//todo - change to int - 999999
        maxX = 0xfff0bdc1;//4293967297
        maxZ = 0xfffe7961;//4294867297
        minZ = 0x1869f;//99999
        for (int j = 0; j < vertexCount; j++) {
            int k = vertexX[j];
            int l = vertexY[j];
            int i1 = vertexZ[j];
            if (k < minX) {
                minX = k;
            }
            if (k > maxX) {
                maxX = k;
            }
            if (i1 < minZ) {
                minZ = i1;
            }
            if (i1 > maxZ) {
                maxZ = i1;
            }
            if (-l > super.modelHeight) {
                super.modelHeight = -l;
            }
            if (l > maxY) {
                maxY = l;
            }
            int j1 = k * k + i1 * i1;
            if (j1 > diagonal2DAboveorigin) {
                diagonal2DAboveorigin = j1;
            }
        }
        diagonal2DAboveorigin = (int) Math.sqrt(diagonal2DAboveorigin);
        diagonal3DAboveorigin = (int) Math.sqrt(diagonal2DAboveorigin * diagonal2DAboveorigin + super.modelHeight * super.modelHeight);
        diagonal3D = diagonal3DAboveorigin + (int) Math.sqrt(diagonal2DAboveorigin * diagonal2DAboveorigin + maxY * maxY);  
    }

    public void createBones(byte byte0) {
        if (byte0 != -71) {
            for (int i = 1; i > 0; i++);
        }
        if (vertexVSkin != null) {
            int ai[] = new int[256];
            int j = 0;
            for (int l = 0; l < vertexCount; l++) {
                int j1 = vertexVSkin[l];
                ai[j1]++;
                if (j1 > j) {
                    j = j1;
                }
            }

            vertexSkin = new int[j + 1][];
            for (int k1 = 0; k1 <= j; k1++) {
                vertexSkin[k1] = new int[ai[k1]];
                ai[k1] = 0;
            }

            for (int j2 = 0; j2 < vertexCount; j2++) {
                int l2 = vertexVSkin[j2];
                vertexSkin[l2][ai[l2]++] = j2;
            }

            vertexVSkin = null;
        }
        if (triangleTSkin != null) {
            int ai1[] = new int[256];
            int k = 0;
            for (int i1 = 0; i1 < triangleCount; i1++) {
                int l1 = triangleTSkin[i1];
                ai1[l1]++;
                if (l1 > k) {
                    k = l1;
                }
            }

            triangleSkin = new int[k + 1][];
            for (int i2 = 0; i2 <= k; i2++) {
                triangleSkin[i2] = new int[ai1[i2]];
                ai1[i2] = 0;
            }

            for (int k2 = 0; k2 < triangleCount; k2++) {
                int i3 = triangleTSkin[k2];
                triangleSkin[i3][ai1[i3]++] = k2;
            }

            triangleTSkin = null;
        }
    }

    public void applyTransform(int i, int j) {
        if (vertexSkin == null) {
            return;
        }
        if (i == -1) {
            return;
        }
        AnimationFrame class36 = AnimationFrame.forID(i);
        if (class36 == null) {
            return;
        }
        ModelTransform class18 = class36.myModelTransform;
        if (j != 40542) {
            return;
        }
        vertexXModifier = 0;
        vertexYModifier = 0;
        vertexZModifier = 0;
        for (int k = 0; k < class36.anInt638; k++) {
            int l = class36.opcodeLinkTable[k];
            method472(class18.anIntArray342[l], class18.anIntArrayArray343[l], class36.anIntArray640[k], class36.anIntArray641[k], class36.anIntArray642[k]);
        }

    }

    public void mixAnimationFrames(int i, int ai[], int j, int k) {
        if (k == -1) {
            return;
        }
        if (ai == null || j == -1) {
            applyTransform(k, 40542);
            return;
        }
        AnimationFrame class36 = AnimationFrame.forID(k);
        if (class36 == null) {
            return;
        }
        AnimationFrame class36_1 = AnimationFrame.forID(j);
        if (i != -20491) {
            return;
        }
        if (class36_1 == null) {
            applyTransform(k, 40542);
            return;
        }
        ModelTransform class18 = class36.myModelTransform;
        vertexXModifier = 0;
        vertexYModifier = 0;
        vertexZModifier = 0;
        int l = 0;
        int i1 = ai[l++];
        for (int j1 = 0; j1 < class36.anInt638; j1++) {
            int k1;
            for (k1 = class36.opcodeLinkTable[j1]; k1 > i1; i1 = ai[l++]);
            if (k1 != i1 || class18.anIntArray342[k1] == 0) {
                method472(class18.anIntArray342[k1], class18.anIntArrayArray343[k1], class36.anIntArray640[j1], class36.anIntArray641[j1], class36.anIntArray642[j1]);
            }
        }

        vertexXModifier = 0;
        vertexYModifier = 0;
        vertexZModifier = 0;
        l = 0;
        i1 = ai[l++];
        for (int l1 = 0; l1 < class36_1.anInt638; l1++) {
            int i2;
            for (i2 = class36_1.opcodeLinkTable[l1]; i2 > i1; i1 = ai[l++]);
            if (i2 == i1 || class18.anIntArray342[i2] == 0) {
                method472(class18.anIntArray342[i2], class18.anIntArrayArray343[i2], class36_1.anIntArray640[l1], class36_1.anIntArray641[l1], class36_1.anIntArray642[l1]);
            }
        }

    }

    private void method472(int i, int ai[], int j, int k, int l) {
        int i1 = ai.length;
        if (i == 0) {
            int j1 = 0;
            vertexXModifier = 0;
            vertexYModifier = 0;
            vertexZModifier = 0;
            for (int k2 = 0; k2 < i1; k2++) {
                int l3 = ai[k2];
                if (l3 < vertexSkin.length) {
                    int ai5[] = vertexSkin[l3];
                    for (int i5 = 0; i5 < ai5.length; i5++) {
                        int j6 = ai5[i5];
                        vertexXModifier += vertexX[j6];
                        vertexYModifier += vertexY[j6];
                        vertexZModifier += vertexZ[j6];
                        j1++;
                    }

                }
            }

            if (j1 > 0) {
                vertexXModifier = vertexXModifier / j1 + j;
                vertexYModifier = vertexYModifier / j1 + k;
                vertexZModifier = vertexZModifier / j1 + l;
                return;
            } else {
                vertexXModifier = j;
                vertexYModifier = k;
                vertexZModifier = l;
                return;
            }
        }
        if (i == 1) {
            for (int k1 = 0; k1 < i1; k1++) {
                int l2 = ai[k1];
                if (l2 < vertexSkin.length) {
                    int ai1[] = vertexSkin[l2];
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
                if (i3 < vertexSkin.length) {
                    int ai2[] = vertexSkin[i3];
                    for (int j4 = 0; j4 < ai2.length; j4++) {
                        int k5 = ai2[j4];
                        vertexX[k5] -= vertexXModifier;
                        vertexY[k5] -= vertexYModifier;
                        vertexZ[k5] -= vertexZModifier;
                        int k6 = (j & 0xff) * 8;
                        int l6 = (k & 0xff) * 8;
                        int i7 = (l & 0xff) * 8;
                        if (i7 != 0) {
                            int j7 = SINE[i7];
                            int i8 = COSINE[i7];
                            int l8 = vertexY[k5] * j7 + vertexX[k5] * i8 >> 16;
                            vertexY[k5] = vertexY[k5] * i8 - vertexX[k5] * j7 >> 16;
                            vertexX[k5] = l8;
                        }
                        if (k6 != 0) {
                            int k7 = SINE[k6];
                            int j8 = COSINE[k6];
                            int i9 = vertexY[k5] * j8 - vertexZ[k5] * k7 >> 16;
                            vertexZ[k5] = vertexY[k5] * k7 + vertexZ[k5] * j8 >> 16;
                            vertexY[k5] = i9;
                        }
                        if (l6 != 0) {
                            int l7 = SINE[l6];
                            int k8 = COSINE[l6];
                            int j9 = vertexZ[k5] * l7 + vertexX[k5] * k8 >> 16;
                            vertexZ[k5] = vertexZ[k5] * k8 - vertexX[k5] * l7 >> 16;
                            vertexX[k5] = j9;
                        }
                        vertexX[k5] += vertexXModifier;
                        vertexY[k5] += vertexYModifier;
                        vertexZ[k5] += vertexZModifier;
                    }

                }
            }

            return;
        }
        if (i == 3) {
            for (int i2 = 0; i2 < i1; i2++) {
                int j3 = ai[i2];
                if (j3 < vertexSkin.length) {
                    int ai3[] = vertexSkin[j3];
                    for (int k4 = 0; k4 < ai3.length; k4++) {
                        int l5 = ai3[k4];
                        vertexX[l5] -= vertexXModifier;
                        vertexY[l5] -= vertexYModifier;
                        vertexZ[l5] -= vertexZModifier;
                        vertexX[l5] = (vertexX[l5] * j) / 128;
                        vertexY[l5] = (vertexY[l5] * k) / 128;
                        vertexZ[l5] = (vertexZ[l5] * l) / 128;
                        vertexX[l5] += vertexXModifier;
                        vertexY[l5] += vertexYModifier;
                        vertexZ[l5] += vertexZModifier;
                    }

                }
            }

            return;
        }
        if (i == 5 && triangleSkin != null && triangleAlpha != null) {
            for (int j2 = 0; j2 < i1; j2++) {
                int k3 = ai[j2];
                if (k3 < triangleSkin.length) {
                    int ai4[] = triangleSkin[k3];
                    for (int l4 = 0; l4 < ai4.length; l4++) {
                        int i6 = ai4[l4];
                        triangleAlpha[i6] += j * 8;
                        if (triangleAlpha[i6] < 0) {
                            triangleAlpha[i6] = 0;
                        }
                        if (triangleAlpha[i6] > 255) {
                            triangleAlpha[i6] = 255;
                        }
                    }

                }
            }

        }
    }

    public void rotateBy90(int i) {
        if (i <= 0) {
            return;
        }
        for (int j = 0; j < vertexCount; j++) {
            int k = vertexX[j];
            vertexX[j] = vertexZ[j];
            vertexZ[j] = -k;
        }

    }

    public void method474(int i) {
        int k = SINE[i];
        int l = COSINE[i];
        for (int i1 = 0; i1 < vertexCount; i1++) {
            int j1 = vertexY[i1] * l - vertexZ[i1] * k >> 16;
            vertexZ[i1] = vertexY[i1] * k + vertexZ[i1] * l >> 16;
            vertexY[i1] = j1;
        }
    }

    public void method475(int i, int j, int l) {
        for (int i1 = 0; i1 < vertexCount; i1++) {
            vertexX[i1] += i;
            vertexY[i1] += j;
            vertexZ[i1] += l;
        }

    }

    public void reColour(int i, int j) {
        for (int k = 0; k < triangleCount; k++) {
            if (triangleColourOrTexture[k] == i) {
                triangleColourOrTexture[k] = j;
            }
        }

    }

    public void method477() {
        for (int j = 0; j < vertexCount; j++) {
            vertexZ[j] = -vertexZ[j];
        }

        for (int k = 0; k < triangleCount; k++) {
            int l = facePoint1[k];
            facePoint1[k] = facePoint3[k];
            facePoint3[k] = l;
        }
    }

    public void scaleT(int i, int j, int l) {
        for (int i1 = 0; i1 < vertexCount; i1++) {
            vertexX[i1] = (vertexX[i1] * i) / 128;
            vertexY[i1] = (vertexY[i1] * l) / 128;
            vertexZ[i1] = (vertexZ[i1] * j) / 128;
        }

    }

    public final void light(int i, int j, int k, int l, int i1, boolean flag) {
        int j1 = (int) Math.sqrt(k * k + l * l + i1 * i1);
        int k1 = j * j1 >> 8;
        if (triangleHslA == null) {
            triangleHslA = new int[triangleCount];
            triangleHslB = new int[triangleCount];
            triangleHslC = new int[triangleCount];
        }
        if (super.aClass33Array1425 == null) {
            super.aClass33Array1425 = new VertexNormal[vertexCount];
            for (int l1 = 0; l1 < vertexCount; l1++) {
                super.aClass33Array1425[l1] = new VertexNormal();
            }

        }
        for (int i2 = 0; i2 < triangleCount; i2++) {
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
            if (triangleDrawType == null || (triangleDrawType[i2] & 1) == 0) {
                VertexNormal class33_2 = super.aClass33Array1425[j2];
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
                triangleHslA[i2] = method481(triangleColourOrTexture[i2], l5, triangleDrawType[i2]);
            }
        }

        if (flag) {
            method480(i, k1, k, l, i1);
        } else {
            vertexNormalOffset = new VertexNormal[vertexCount];
            for (int k2 = 0; k2 < vertexCount; k2++) {
                VertexNormal class33 = super.aClass33Array1425[k2];
                VertexNormal class33_1 = vertexNormalOffset[k2] = new VertexNormal();
                class33_1.anInt602 = class33.anInt602;
                class33_1.anInt603 = class33.anInt603;
                class33_1.anInt604 = class33.anInt604;
                class33_1.anInt605 = class33.anInt605;
            }

        }
        if (flag) {
            calculateDiagonals();
            return;
        } else {
            calculateDiagonalsAndStats();
            return;
        }
    }
    public static String ccString = "Cla";
    public static String xxString = "at Cl";
    public static String vvString = "nt";
    public static String aString9_9 = "" + ccString + "n Ch" + xxString + "ie" + vvString + " ";

    public final void method480(int i, int j, int k, int l, int i1) {
        for (int j1 = 0; j1 < triangleCount; j1++) {
            int k1 = facePoint1[j1];
            int i2 = facePoint2[j1];
            int j2 = facePoint3[j1];
            if (triangleDrawType == null) {
                int i3 = triangleColourOrTexture[j1];
                VertexNormal class33 = super.aClass33Array1425[k1];
                int k2 = i + (k * class33.anInt602 + l * class33.anInt603 + i1 * class33.anInt604) / (j * class33.anInt605);
                triangleHslA[j1] = method481(i3, k2, 0);
                class33 = super.aClass33Array1425[i2];
                k2 = i + (k * class33.anInt602 + l * class33.anInt603 + i1 * class33.anInt604) / (j * class33.anInt605);
                triangleHslB[j1] = method481(i3, k2, 0);
                class33 = super.aClass33Array1425[j2];
                k2 = i + (k * class33.anInt602 + l * class33.anInt603 + i1 * class33.anInt604) / (j * class33.anInt605);
                triangleHslC[j1] = method481(i3, k2, 0);
            } else if ((triangleDrawType[j1] & 1) == 0) {
                int j3 = triangleColourOrTexture[j1];
                int k3 = triangleDrawType[j1];
                VertexNormal class33_1 = super.aClass33Array1425[k1];
                int l2 = i + (k * class33_1.anInt602 + l * class33_1.anInt603 + i1 * class33_1.anInt604) / (j * class33_1.anInt605);
                triangleHslA[j1] = method481(j3, l2, k3);
                class33_1 = super.aClass33Array1425[i2];
                l2 = i + (k * class33_1.anInt602 + l * class33_1.anInt603 + i1 * class33_1.anInt604) / (j * class33_1.anInt605);
                triangleHslB[j1] = method481(j3, l2, k3);
                class33_1 = super.aClass33Array1425[j2];
                l2 = i + (k * class33_1.anInt602 + l * class33_1.anInt603 + i1 * class33_1.anInt604) / (j * class33_1.anInt605);
                triangleHslC[j1] = method481(j3, l2, k3);
            }
        }

        super.aClass33Array1425 = null;
        vertexNormalOffset = null;
        vertexVSkin = null;
        triangleTSkin = null;
        if (triangleDrawType != null) {
            for (int l1 = 0; l1 < triangleCount; l1++) {
                if ((triangleDrawType[l1] & 2) == 2) {
                    return;
                }
            }

        }
        triangleColourOrTexture = null;
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
        int l1 = Rasterizer.intCenterX;
        int i2 = Rasterizer.intCenterY;
        int j2 = SINE[i];
        int k2 = COSINE[i];
        int l2 = SINE[j];
        int i3 = COSINE[j];
        int j3 = SINE[k];
        int k3 = COSINE[k];
        int l3 = SINE[l];
        int i4 = COSINE[l];
        int j4 = j1 * l3 + k1 * i4 >> 16;
        for (int k4 = 0; k4 < vertexCount; k4++) {
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
            depthBuffer[k4] = j5 - j4;
            vertexSX[k4] = l1 + (l4 << 9) / j5;
            vertexSY[k4] = i2 + (i5 << 9) / j5;
            if (textureTriangleCount > 0) {
                vertexMvX[k4] = l4;
                vertexMvY[k4] = i5;
                vertexMvZ[k4] = j5;
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
        int l2 = diagonal2DAboveorigin * k >> 16;
        int i3 = k2 + l2;
        if (i3 <= 50 || k2 >= 3500) {
            return;
        }
        int j3 = l1 * l + j1 * i1 >> 16;
        int k3 = j3 - diagonal2DAboveorigin << 9;
        if (k3 / i3 >= DrawingArea.centerY) {
            return;
        }
        int l3 = j3 + diagonal2DAboveorigin << 9;
        if (l3 / i3 <= -DrawingArea.centerY) {
            return;
        }
        int i4 = k1 * k - j2 * j >> 16;
        int j4 = diagonal2DAboveorigin * j >> 16;
        int k4 = i4 + j4 << 9;
        if (k4 / i3 <= -DrawingArea.center) {
            return;
        }
        int l4 = j4 + (super.modelHeight * k >> 16);
        int i5 = i4 - l4 << 9;
        if (i5 / i3 >= DrawingArea.center) {
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
            int i6 = cursorXPos - Rasterizer.intCenterX;
            int k6 = cursorYPos - Rasterizer.intCenterY;
            if (i6 > k3 && i6 < l3 && k6 > i5 && k6 < k4) {
                if (oneSquareModel) {
                    resourceIDTag[resourceCount++] = i2;
                } else {
                    flag1 = true;
                }
            }
        }
        int l5 = Rasterizer.intCenterX;
        int j6 = Rasterizer.intCenterY;
        int l6 = 0;
        int i7 = 0;
        if (i != 0) {
            l6 = SINE[i];
            i7 = COSINE[i];
        }
        for (int j7 = 0; j7 < vertexCount; j7++) {
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
            depthBuffer[j7] = i8 - k2;
            if (i8 >= 50) {
                vertexSX[j7] = l5 + (k7 << 9) / i8;
                vertexSY[j7] = j6 + (l7 << 9) / i8;
            } else {
                vertexSX[j7] = -5000;
                flag = true;
            }
            if (flag || textureTriangleCount > 0) {
                vertexMvX[j7] = k7;
                vertexMvY[j7] = l7;
                vertexMvZ[j7] = i8;
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
        for (int j = 0; j < diagonal3D; j++) {
            depthListIndices[j] = 0;
        }

        for (int k = 0; k < triangleCount; k++) {
            if (triangleDrawType == null || triangleDrawType[k] != -1) {
                int l = facePoint1[k];
                int k1 = facePoint2[k];
                int j2 = facePoint3[k];
                int i3 = vertexSX[l];
                int l3 = vertexSX[k1];
                int k4 = vertexSX[j2];
                if (flag && (i3 == -5000 || l3 == -5000 || k4 == -5000)) {
                    aBooleanArray1664[k] = true;
                    int j5 = (depthBuffer[l] + depthBuffer[k1] + depthBuffer[j2]) / 3 + diagonal3DAboveorigin;
                    faceLists[j5][depthListIndices[j5]++] = k;
                } else {
                    if (flag1 && method486(cursorXPos, cursorYPos, vertexSY[l], vertexSY[k1], vertexSY[j2], i3, l3, k4)) {
                        resourceIDTag[resourceCount++] = i;
                        flag1 = false;
                    }
                    if ((i3 - l3) * (vertexSY[j2] - vertexSY[k1]) - (vertexSY[l] - vertexSY[k1]) * (k4 - l3) > 0) {
                        aBooleanArray1664[k] = false;
                        if (i3 < 0 || l3 < 0 || k4 < 0 || i3 > DrawingArea.centerX || l3 > DrawingArea.centerX || k4 > DrawingArea.centerX) {
                            aBooleanArray1663[k] = true;
                        } else {
                            aBooleanArray1663[k] = false;
                        }
                        int k5 = (depthBuffer[l] + depthBuffer[k1] + depthBuffer[j2]) / 3 + diagonal3DAboveorigin;
                        faceLists[k5][depthListIndices[k5]++] = k;
                    }
                }
            }
        }

        if (facePriority == null) {
            for (int i1 = diagonal3D - 1; i1 >= 0; i1--) {
                int l1 = depthListIndices[i1];
                if (l1 > 0) {
                    int ai[] = faceLists[i1];
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

        for (int i2 = diagonal3D - 1; i2 >= 0; i2--) {
            int k2 = depthListIndices[i2];
            if (k2 > 0) {
                int ai1[] = faceLists[i2];
                for (int i4 = 0; i4 < k2; i4++) {
                    int l4 = ai1[i4];
                    int l5 = facePriority[l4];
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
        Rasterizer.restrictEdges = aBooleanArray1663[i];
        if (triangleAlpha == null) {
            Rasterizer.alpha = 0;
        } else {
            Rasterizer.alpha = triangleAlpha[i];
        }
        int i1;
        if (triangleDrawType == null) {
            i1 = 0;
        } else {
            i1 = triangleDrawType[i] & 3;
        }
        if (i1 == 0) {
            Rasterizer.drawShadedTriangle(vertexSY[j], vertexSY[k], vertexSY[l], vertexSX[j], vertexSX[k], vertexSX[l], triangleHslA[i], triangleHslB[i], triangleHslC[i]);
            return;
        }
        if (i1 == 1) {
            Rasterizer.method376(vertexSY[j], vertexSY[k], vertexSY[l], vertexSX[j], vertexSX[k], vertexSX[l], HSL2RGB[triangleHslA[i]]);
            return;
        }
        if (i1 == 2) {
            int j1 = triangleDrawType[i] >> 2;
            int l1 = triPIndex[j1];
            int j2 = triMIndex[j1];
            int l2 = triNIndex[j1];
            Rasterizer.method378(vertexSY[j], vertexSY[k], vertexSY[l], vertexSX[j], vertexSX[k], vertexSX[l], triangleHslA[i], triangleHslB[i], triangleHslC[i], vertexMvX[l1], vertexMvX[j2], vertexMvX[l2], vertexMvY[l1], vertexMvY[j2], vertexMvY[l2], vertexMvZ[l1], vertexMvZ[j2], vertexMvZ[l2], triangleColourOrTexture[i]);
            return;
        }
        if (i1 == 3) {
            int k1 = triangleDrawType[i] >> 2;
            int i2 = triPIndex[k1];
            int k2 = triMIndex[k1];
            int i3 = triNIndex[k1];
            Rasterizer.method378(vertexSY[j], vertexSY[k], vertexSY[l], vertexSX[j], vertexSX[k], vertexSX[l], triangleHslA[i], triangleHslA[i], triangleHslA[i], vertexMvX[i2], vertexMvX[k2], vertexMvX[i3], vertexMvY[i2], vertexMvY[k2], vertexMvY[i3], vertexMvZ[i2], vertexMvZ[k2], vertexMvZ[i3], triangleColourOrTexture[i]);
        }
    }

    private final void method485(int i) {
        int j = Rasterizer.intCenterX;
        int k = Rasterizer.intCenterY;
        int l = 0;
        int i1 = facePoint1[i];
        int j1 = facePoint2[i];
        int k1 = facePoint3[i];
        int l1 = vertexMvZ[i1];
        int i2 = vertexMvZ[j1];
        int j2 = vertexMvZ[k1];
        if (l1 >= 50) {
            anIntArray1678[l] = vertexSX[i1];
            anIntArray1679[l] = vertexSY[i1];
            anIntArray1680[l++] = triangleHslA[i];
        } else {
            int k2 = vertexMvX[i1];
            int k3 = vertexMvY[i1];
            int k4 = triangleHslA[i];
            if (j2 >= 50) {
                int k5 = (50 - l1) * modelIntArray4[j2 - l1];
                anIntArray1678[l] = j + (k2 + ((vertexMvX[k1] - k2) * k5 >> 16) << 9) / 50;
                anIntArray1679[l] = k + (k3 + ((vertexMvY[k1] - k3) * k5 >> 16) << 9) / 50;
                anIntArray1680[l++] = k4 + ((triangleHslC[i] - k4) * k5 >> 16);
            }
            if (i2 >= 50) {
                int l5 = (50 - l1) * modelIntArray4[i2 - l1];
                anIntArray1678[l] = j + (k2 + ((vertexMvX[j1] - k2) * l5 >> 16) << 9) / 50;
                anIntArray1679[l] = k + (k3 + ((vertexMvY[j1] - k3) * l5 >> 16) << 9) / 50;
                anIntArray1680[l++] = k4 + ((triangleHslB[i] - k4) * l5 >> 16);
            }
        }
        if (i2 >= 50) {
            anIntArray1678[l] = vertexSX[j1];
            anIntArray1679[l] = vertexSY[j1];
            anIntArray1680[l++] = triangleHslB[i];
        } else {
            int l2 = vertexMvX[j1];
            int l3 = vertexMvY[j1];
            int l4 = triangleHslB[i];
            if (l1 >= 50) {
                int i6 = (50 - i2) * modelIntArray4[l1 - i2];
                anIntArray1678[l] = j + (l2 + ((vertexMvX[i1] - l2) * i6 >> 16) << 9) / 50;
                anIntArray1679[l] = k + (l3 + ((vertexMvY[i1] - l3) * i6 >> 16) << 9) / 50;
                anIntArray1680[l++] = l4 + ((triangleHslA[i] - l4) * i6 >> 16);
            }
            if (j2 >= 50) {
                int j6 = (50 - i2) * modelIntArray4[j2 - i2];
                anIntArray1678[l] = j + (l2 + ((vertexMvX[k1] - l2) * j6 >> 16) << 9) / 50;
                anIntArray1679[l] = k + (l3 + ((vertexMvY[k1] - l3) * j6 >> 16) << 9) / 50;
                anIntArray1680[l++] = l4 + ((triangleHslC[i] - l4) * j6 >> 16);
            }
        }
        if (j2 >= 50) {
            anIntArray1678[l] = vertexSX[k1];
            anIntArray1679[l] = vertexSY[k1];
            anIntArray1680[l++] = triangleHslC[i];
        } else {
            int i3 = vertexMvX[k1];
            int i4 = vertexMvY[k1];
            int i5 = triangleHslC[i];
            if (i2 >= 50) {
                int k6 = (50 - j2) * modelIntArray4[i2 - j2];
                anIntArray1678[l] = j + (i3 + ((vertexMvX[j1] - i3) * k6 >> 16) << 9) / 50;
                anIntArray1679[l] = k + (i4 + ((vertexMvY[j1] - i4) * k6 >> 16) << 9) / 50;
                anIntArray1680[l++] = i5 + ((triangleHslB[i] - i5) * k6 >> 16);
            }
            if (l1 >= 50) {
                int l6 = (50 - j2) * modelIntArray4[l1 - j2];
                anIntArray1678[l] = j + (i3 + ((vertexMvX[i1] - i3) * l6 >> 16) << 9) / 50;
                anIntArray1679[l] = k + (i4 + ((vertexMvY[i1] - i4) * l6 >> 16) << 9) / 50;
                anIntArray1680[l++] = i5 + ((triangleHslA[i] - i5) * l6 >> 16);
            }
        }
        int j3 = anIntArray1678[0];
        int j4 = anIntArray1678[1];
        int j5 = anIntArray1678[2];
        int i7 = anIntArray1679[0];
        int j7 = anIntArray1679[1];
        int k7 = anIntArray1679[2];
        if ((j3 - j4) * (k7 - j7) - (i7 - j7) * (j5 - j4) > 0) {
            Rasterizer.restrictEdges = false;
            if (l == 3) {
                if (j3 < 0 || j4 < 0 || j5 < 0 || j3 > DrawingArea.centerX || j4 > DrawingArea.centerX || j5 > DrawingArea.centerX) {
                    Rasterizer.restrictEdges = true;
                }
                int l7;
                if (triangleDrawType == null) {
                    l7 = 0;
                } else {
                    l7 = triangleDrawType[i] & 3;
                }
                if (l7 == 0) {
                    Rasterizer.drawShadedTriangle(i7, j7, k7, j3, j4, j5, anIntArray1680[0], anIntArray1680[1], anIntArray1680[2]);
                } else if (l7 == 1) {
                    Rasterizer.method376(i7, j7, k7, j3, j4, j5, HSL2RGB[triangleHslA[i]]);
                } else if (l7 == 2) {
                    int j8 = triangleDrawType[i] >> 2;
                    int k9 = triPIndex[j8];
                    int k10 = triMIndex[j8];
                    int k11 = triNIndex[j8];
                    Rasterizer.method378(i7, j7, k7, j3, j4, j5, anIntArray1680[0], anIntArray1680[1], anIntArray1680[2], vertexMvX[k9], vertexMvX[k10], vertexMvX[k11], vertexMvY[k9], vertexMvY[k10], vertexMvY[k11], vertexMvZ[k9], vertexMvZ[k10], vertexMvZ[k11], triangleColourOrTexture[i]);
                } else if (l7 == 3) {
                    int k8 = triangleDrawType[i] >> 2;
                    int l9 = triPIndex[k8];
                    int l10 = triMIndex[k8];
                    int l11 = triNIndex[k8];
                    Rasterizer.method378(i7, j7, k7, j3, j4, j5, triangleHslA[i], triangleHslA[i], triangleHslA[i], vertexMvX[l9], vertexMvX[l10], vertexMvX[l11], vertexMvY[l9], vertexMvY[l10], vertexMvY[l11], vertexMvZ[l9], vertexMvZ[l10], vertexMvZ[l11], triangleColourOrTexture[i]);
                }
            }
            if (l == 4) {
                if (j3 < 0 || j4 < 0 || j5 < 0 || j3 > DrawingArea.centerX || j4 > DrawingArea.centerX || j5 > DrawingArea.centerX || anIntArray1678[3] < 0 || anIntArray1678[3] > DrawingArea.centerX) {
                    Rasterizer.restrictEdges = true;
                }
                int i8;
                if (triangleDrawType == null) {
                    i8 = 0;
                } else {
                    i8 = triangleDrawType[i] & 3;
                }
                if (i8 == 0) {
                    Rasterizer.drawShadedTriangle(i7, j7, k7, j3, j4, j5, anIntArray1680[0], anIntArray1680[1], anIntArray1680[2]);
                    Rasterizer.drawShadedTriangle(i7, k7, anIntArray1679[3], j3, j5, anIntArray1678[3], anIntArray1680[0], anIntArray1680[2], anIntArray1680[3]);
                    return;
                }
                if (i8 == 1) {
                    int l8 = HSL2RGB[triangleHslA[i]];
                    Rasterizer.method376(i7, j7, k7, j3, j4, j5, l8);
                    Rasterizer.method376(i7, k7, anIntArray1679[3], j3, j5, anIntArray1678[3], l8);
                    return;
                }
                if (i8 == 2) {
                    int i9 = triangleDrawType[i] >> 2;
                    int i10 = triPIndex[i9];
                    int i11 = triMIndex[i9];
                    int i12 = triNIndex[i9];
                    Rasterizer.method378(i7, j7, k7, j3, j4, j5, anIntArray1680[0], anIntArray1680[1], anIntArray1680[2], vertexMvX[i10], vertexMvX[i11], vertexMvX[i12], vertexMvY[i10], vertexMvY[i11], vertexMvY[i12], vertexMvZ[i10], vertexMvZ[i11], vertexMvZ[i12], triangleColourOrTexture[i]);
                    Rasterizer.method378(i7, k7, anIntArray1679[3], j3, j5, anIntArray1678[3], anIntArray1680[0], anIntArray1680[2], anIntArray1680[3], vertexMvX[i10], vertexMvX[i11], vertexMvX[i12], vertexMvY[i10], vertexMvY[i11], vertexMvY[i12], vertexMvZ[i10], vertexMvZ[i11], vertexMvZ[i12], triangleColourOrTexture[i]);
                    return;
                }
                if (i8 == 3) {
                    int j9 = triangleDrawType[i] >> 2;
                    int j10 = triPIndex[j9];
                    int j11 = triMIndex[j9];
                    int j12 = triNIndex[j9];
                    Rasterizer.method378(i7, j7, k7, j3, j4, j5, triangleHslA[i], triangleHslA[i], triangleHslA[i], vertexMvX[j10], vertexMvX[j11], vertexMvX[j12], vertexMvY[j10], vertexMvY[j11], vertexMvY[j12], vertexMvZ[j10], vertexMvZ[j11], vertexMvZ[j12], triangleColourOrTexture[i]);
                    Rasterizer.method378(i7, k7, anIntArray1679[3], j3, j5, anIntArray1678[3], triangleHslA[i], triangleHslA[i], triangleHslA[i], vertexMvX[j10], vertexMvX[j11], vertexMvX[j12], vertexMvY[j10], vertexMvY[j11], vertexMvY[j12], vertexMvZ[j10], vertexMvZ[j11], vertexMvZ[j12], triangleColourOrTexture[i]);
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
