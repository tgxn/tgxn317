package client.tile;


public final class ShapedTile {
    
    public int origVertexX[];
    public int origVertexY[];
    public int origVertexZ[];
    public int anIntArray676[];
    public int anIntArray677[];
    public int anIntArray678[];
    public int anIntArray679[];
    public int anIntArray680[];
    public int anIntArray681[];
   public  int anIntArray682[];
   public  boolean flat;
    public int shape;
    public int rotation;
    public int colourRGB;
    public int colourRGBA;
    public static int anIntArray688[] = new int[6];
    public static int anIntArray689[] = new int[6];
    public static int anIntArray690[] = new int[6];
    public static int anIntArray691[] = new int[6];
    public static int anIntArray692[] = new int[6];
    public static int anIntArray693[] = {
        1, 0
    };
    static int anIntArray694[] = {
        2, 1
    };
    static int anIntArray695[] = {
        3, 3
    };
    static final int shapedTilePointData[][] = {
        {
            1, 3, 5, 7
        }, {
            1, 3, 5, 7
        }, {
            1, 3, 5, 7
        }, {
            1, 3, 5, 7, 6
        }, {
            1, 3, 5, 7, 6
        }, {
            1, 3, 5, 7, 6
        }, {
            1, 3, 5, 7, 6
        }, {
            1, 3, 5, 7, 2, 6
        }, {
            1, 3, 5, 7, 2, 8
        }, {
            1, 3, 5, 7, 2, 8
        }, {
            1, 3, 5, 7, 11, 12
        }, {
            1, 3, 5, 7, 11, 12
        }, {
            1, 3, 5, 7, 13, 14
        }
    };
    static final int shapedTileElementData[][] = {
        {
            0, 1, 2, 3, 0, 0, 1, 3
        }, {
            1, 1, 2, 3, 1, 0, 1, 3
        }, {
            0, 1, 2, 3, 1, 0, 1, 3
        }, {
            0, 0, 1, 2, 0, 0, 2, 4, 1, 0,
            4, 3
        }, {
            0, 0, 1, 4, 0, 0, 4, 3, 1, 1,
            2, 4
        }, {
            0, 0, 4, 3, 1, 0, 1, 2, 1, 0,
            2, 4
        }, {
            0, 1, 2, 4, 1, 0, 1, 4, 1, 0,
            4, 3
        }, {
            0, 4, 1, 2, 0, 4, 2, 5, 1, 0,
            4, 5, 1, 0, 5, 3
        }, {
            0, 4, 1, 2, 0, 4, 2, 3, 0, 4,
            3, 5, 1, 0, 4, 5
        }, {
            0, 0, 4, 5, 1, 4, 1, 2, 1, 4,
            2, 3, 1, 4, 3, 5
        }, {
            0, 0, 1, 5, 0, 1, 4, 5, 0, 1,
            2, 4, 1, 0, 5, 3, 1, 5, 4, 3,
            1, 4, 2, 3
        }, {
            1, 0, 1, 5, 1, 1, 4, 5, 1, 1,
            2, 4, 0, 0, 5, 3, 0, 5, 4, 3,
            0, 4, 2, 3
        }, {
            1, 0, 5, 4, 1, 0, 1, 5, 0, 0,
            4, 3, 0, 4, 5, 3, 0, 5, 2, 3,
            0, 1, 2, 5
        }
    };
    
    public ShapedTile(int tileZ, int j, int k, int l, int i1, int j1, int rotation,
            int l1, int rgb, int j2, int k2, int yB, int i3, int shape,
            int k3, int cBA, int cB, int tileX, int rgba) {
        flat = true;
        if (i3 != yB || i3 != l || i3 != k2) {
            flat = false;
        }
        this.shape = shape;
        this.rotation = rotation;
        colourRGB = rgb;
        colourRGBA = rgba;
        char const512 = '\200';
        int cont256 = const512 / 2;
        int const128 = const512 / 4;
        int const384 = (const512 * 3) / 4;
        int shapedTileMesh[] = shapedTilePointData[shape];
        int meshLength = shapedTileMesh.length;
        origVertexX = new int[meshLength];
        origVertexY = new int[meshLength];
        origVertexZ = new int[meshLength];
        int vertexColourOverlay[] = new int[meshLength];
        int vertexColourUnderlay[] = new int[meshLength];
        int x512 = tileX * const512;
        int z512 = tileZ * const512;
        for (int vertexPtr = 0; vertexPtr < meshLength; vertexPtr++) {
            int vertexType = shapedTileMesh[vertexPtr];
            if ((vertexType & 1) == 0 && vertexType <= 8) {
                vertexType = (vertexType - rotation - rotation - 1 & 7) + 1;
            }
            if (vertexType > 8 && vertexType <= 12) {
                vertexType = (vertexType - 9 - rotation & 3) + 9;
            }
            if (vertexType > 12 && vertexType <= 16) {
                vertexType = (vertexType - 13 - rotation & 3) + 13;
            }
            int vertexX;
            int vertexZ;
            int vertexY;
            int vertexCOverlay;
            int vertexCUnderlay;
            if (vertexType == 1) {
                vertexX = x512;
                vertexZ = z512;
                vertexY = i3;
                vertexCOverlay = l1;
                vertexCUnderlay = j;
            } else if (vertexType == 2) {
                vertexX = x512 + cont256;
                vertexZ = z512;
                vertexY = i3 + yB >> 1;
                vertexCOverlay = l1 + cB >> 1;
                vertexCUnderlay = j + cBA >> 1;
            } else if (vertexType == 3) {
                vertexX = x512 + const512;
                vertexZ = z512;
                vertexY = yB;
                vertexCOverlay = cB;
                vertexCUnderlay = cBA;
            } else if (vertexType == 4) {
                vertexX = x512 + const512;
                vertexZ = z512 + cont256;
                vertexY = yB + l >> 1;
                vertexCOverlay = cB + j2 >> 1;
                vertexCUnderlay = cBA + j1 >> 1;
            } else if (vertexType == 5) {
                vertexX = x512 + const512;
                vertexZ = z512 + const512;
                vertexY = l;
                vertexCOverlay = j2;
                vertexCUnderlay = j1;
            } else if (vertexType == 6) {
                vertexX = x512 + cont256;
                vertexZ = z512 + const512;
                vertexY = l + k2 >> 1;
                vertexCOverlay = j2 + k >> 1;
                vertexCUnderlay = j1 + k3 >> 1;
            } else if (vertexType == 7) {
                vertexX = x512;
                vertexZ = z512 + const512;
                vertexY = k2;
                vertexCOverlay = k;
                vertexCUnderlay = k3;
            } else if (vertexType == 8) {
                vertexX = x512;
                vertexZ = z512 + cont256;
                vertexY = k2 + i3 >> 1;
                vertexCOverlay = k + l1 >> 1;
                vertexCUnderlay = k3 + j >> 1;
            } else if (vertexType == 9) {
                vertexX = x512 + cont256;
                vertexZ = z512 + const128;
                vertexY = i3 + yB >> 1;
                vertexCOverlay = l1 + cB >> 1;
                vertexCUnderlay = j + cBA >> 1;
            } else if (vertexType == 10) {
                vertexX = x512 + const384;
                vertexZ = z512 + cont256;
                vertexY = yB + l >> 1;
                vertexCOverlay = cB + j2 >> 1;
                vertexCUnderlay = cBA + j1 >> 1;
            } else if (vertexType == 11) {
                vertexX = x512 + cont256;
                vertexZ = z512 + const384;
                vertexY = l + k2 >> 1;
                vertexCOverlay = j2 + k >> 1;
                vertexCUnderlay = j1 + k3 >> 1;
            } else if (vertexType == 12) {
                vertexX = x512 + const128;
                vertexZ = z512 + cont256;
                vertexY = k2 + i3 >> 1;
                vertexCOverlay = k + l1 >> 1;
                vertexCUnderlay = k3 + j >> 1;
            } else if (vertexType == 13) {
                vertexX = x512 + const128;
                vertexZ = z512 + const128;
                vertexY = i3;
                vertexCOverlay = l1;
                vertexCUnderlay = j;
            } else if (vertexType == 14) {
                vertexX = x512 + const384;
                vertexZ = z512 + const128;
                vertexY = yB;
                vertexCOverlay = cB;
                vertexCUnderlay = cBA;
            } else if (vertexType == 15) {
                vertexX = x512 + const384;
                vertexZ = z512 + const384;
                vertexY = l;
                vertexCOverlay = j2;
                vertexCUnderlay = j1;
            } else {
                vertexX = x512 + const128;
                vertexZ = z512 + const384;
                vertexY = k2;
                vertexCOverlay = k;
                vertexCUnderlay = k3;
            }
            origVertexX[vertexPtr] = vertexX;
            origVertexY[vertexPtr] = vertexY;
            origVertexZ[vertexPtr] = vertexZ;
            vertexColourOverlay[vertexPtr] = vertexCOverlay;
            vertexColourUnderlay[vertexPtr] = vertexCUnderlay;
        }

        int shapedTileElements[] = shapedTileElementData[shape];
        int vertexCount = shapedTileElements.length / 4;
        anIntArray679 = new int[vertexCount];
        anIntArray680 = new int[vertexCount];
        anIntArray681 = new int[vertexCount];
        anIntArray676 = new int[vertexCount];
        anIntArray677 = new int[vertexCount];
        anIntArray678 = new int[vertexCount];
        if (i1 != -1) {
            anIntArray682 = new int[vertexCount];
        }
        int l7 = 0;
        for (int j8 = 0; j8 < vertexCount ; j8++) {
            int l8 = shapedTileElements[l7];
            int k9 = shapedTileElements[l7 + 1];
            int i10 = shapedTileElements[l7 + 2];
            int k10 = shapedTileElements[l7 + 3];
            l7 += 4;
            if (k9 < 4) {
                k9 = k9 - rotation & 3;
            }
            if (i10 < 4) {
                i10 = i10 - rotation & 3;
            }
            if (k10 < 4) {
                k10 = k10 - rotation & 3;
            }
            anIntArray679[j8] = k9;
            anIntArray680[j8] = i10;
            anIntArray681[j8] = k10;
            if (l8 == 0) {
                anIntArray676[j8] = vertexColourOverlay[k9];
                anIntArray677[j8] = vertexColourOverlay[i10];
                anIntArray678[j8] = vertexColourOverlay[k10];
                if (anIntArray682 != null) {
                    anIntArray682[j8] = -1;
                }
            } else {
                anIntArray676[j8] = vertexColourUnderlay[k9];
                anIntArray677[j8] = vertexColourUnderlay[i10];
                anIntArray678[j8] = vertexColourUnderlay[k10];
                if (anIntArray682 != null) {
                    anIntArray682[j8] = i1;
                }
            }
        }

        int i9 = i3;
        int l9 = yB;
        if (yB < i9) {
            i9 = yB;
        }
        if (yB > l9) {
            l9 = yB;
        }
        if (l < i9) {
            i9 = l;
        }
        if (l > l9) {
            l9 = l;
        }
        if (k2 < i9) {
            i9 = k2;
        }
        if (k2 > l9) {
            l9 = k2;
        }
        i9 /= 14;
        l9 /= 14;
    }
}
