package client.drawing;

// Partially Refactored.

import client.data.JagexArchive;

public class Rasterizer extends DrawingArea {
    
    public static boolean lowMem = true;
    public static boolean restrictEdges;
    public static boolean opaque;
    public static boolean notTextured = true;
    public static int alpha;
    public static int intCenterX;
    public static int intCenterY;
    public static int[] shadowDecay;
    public static int[] anIntArray1469;
    public static int[] SINE;
    public static int[] COSINE;
    public static int[] lineOffsets;
    public static int loadedTextureCount;
    public static IndexedImage[] textureImages = new IndexedImage[50];
    public static boolean[] textureIsTransparent = new boolean[50];
    public static int[] averageTextureColour = new int[50];
    public static int textureTexelPoolPointer;
    public static int[][] texelArrayPool;
    public static int[][] texelCache = new int[50][];
    public static int[] textureLastUsed = new int[50];
    public static int textureGetCount;
    public static int[] hsl2rgb = new int[0x10000];
    public static int[][] texturePalettes = new int[50][];

    static {
        shadowDecay = new int[512];
        anIntArray1469 = new int[2048];
        SINE = new int[2048];
        COSINE = new int[2048];
        for (int i = 1; i < 512; i++) {
            shadowDecay[i] = 32768 / i; //decay rate for shadows or some shit - super_
        }
        for (int j = 1; j < 2048; j++) {
            anIntArray1469[j] = 0x10000 / j;
        }

        for (int k = 0; k < 2048; k++) {
            SINE[k] = (int) (65536D * Math.sin((double) k * 0.0030679614999999999D));
            COSINE[k] = (int) (65536D * Math.cos((double) k * 0.0030679614999999999D));
        }

    }

    public static void nullLoader() {
        shadowDecay = null;
        shadowDecay = null;
        SINE = null;
        COSINE = null;
        lineOffsets = null;
        textureImages = null;
        textureIsTransparent = null;
        averageTextureColour = null;
        texelArrayPool = null;
        texelCache = null;
        textureLastUsed = null;
        hsl2rgb = null;
        texturePalettes = null;
    }

    public static void setDefaultBounds() {
        lineOffsets = new int[Rasterizer.height];
        for (int j = 0; j < height; j++) {
            lineOffsets[j] = width * j;
        }
        intCenterX = width / 2;
        intCenterY = height / 2;
    }

    public static void setBounds(int width, int height) {
        lineOffsets = new int[height];
        for (int i = 0; i < height; i++) {
            lineOffsets[i] = width * i;
        }
        intCenterX = width / 2;
        intCenterY = height / 2;
    }

    public static void clearTextureCache() {
        texelArrayPool = null;
        for (int j = 0; j < 50; j++) {
            texelCache[j] = null;
        }
    }

    public static void resetTextures(int texturePoolSize) {
        if (texelArrayPool == null) {
            textureTexelPoolPointer = texturePoolSize;
            if (lowMem) {
                texelArrayPool = new int[textureTexelPoolPointer][16384];
            } else {
                texelArrayPool = new int[textureTexelPoolPointer][0x10000];
            }
            for (int k = 0; k < 50; k++) {
                texelCache[k] = null;
            }
        }
    }

    public static void unpackTextures(JagexArchive jagexArchive) {
        loadedTextureCount = 0;
        for (int i = 0; i < 50; i++) {
            try {
                textureImages[i] = new IndexedImage(jagexArchive, String.valueOf(i), 0);
                if (lowMem && textureImages[i].libWidth == 128) {
                    textureImages[i].loadSize128Image();
                } else {
                    textureImages[i].loadSize64Image();
                }
                loadedTextureCount++;
            } catch (Exception ignored) {
            }
        }
    }

    public static int getAverageTextureColour(int textureId) {
        if (averageTextureColour[textureId] != 0) {
            return averageTextureColour[textureId];
        }
        int red = 0;
        int green = 0;
        int blue = 0;
        int colourCount = texturePalettes[textureId].length;
        for (int ptr = 0; ptr < colourCount; ptr++) {
            red += texturePalettes[textureId][ptr] >> 16 & 0xff;
            green += texturePalettes[textureId][ptr] >> 8 & 0xff;
            blue += texturePalettes[textureId][ptr] & 0xff;
        }
        int rgb = (red / colourCount << 16) + (green / colourCount << 8) + blue / colourCount;
        rgb = adjustBrightness(rgb, 1.3999999999999999D);
        if (rgb == 0) {
            rgb = 1;
        }
        averageTextureColour[textureId] = rgb;
        return rgb;
    }

    public static void resetTexture(int resetTexture) {
        if (texelCache[resetTexture] == null) {
            return;
        }
        texelArrayPool[textureTexelPoolPointer++] = texelCache[resetTexture];
        texelCache[resetTexture] = null;
    }

    public static int[] getTexturePixels(int textureId) {
        
        /*	Textures
         *	50	suggests the end of the textures...
         *	49	=	Something to do with 48, I assume
         *	48	=	Hyroglyphs?
         *	47	=	Extremely light wall colour...
         *	46	=	Ground in varrock by the fountain
         *	45	=	Door
         *	44	=	Similar to 43; Darker
         *	43	=	Buildings' stone wall
         *	42	=	Sold blue, for those flowers in Varrock I believe
         *	41	=	Bushes of some sort
         *	40	=	lava	
         *	39	=	Painting of an elf
         *	38	=	One of the new Falador textures
         *	37	=	Same as 38	
         *	36	=	Door or floor texture?
         *	35	=	New Falador walls
         *	34	=	Magic tree stars	
         *	33	=	Maple tree leaves
         *	32	=	Sand maybe?
         *	31	=	Another lava overlay
         *	30	=	Yew tree overlays
         *	29	=	Sort of like fern leaves :\
         *	28	=	???????????????????????
         *	27	=	Snake skin
         *	26	=	Spider webs
         *	25	=	Water of some sort, I think	
         *	24	=	Flowing water	
         *	23	=	Dark buidling siding (something near Lumbridge I think?)
         *	22	=	Wooden floor
         *	21	=	Roofing maybe?
         *	20	=	Book case
         *	19	=	Something with.. holes..
         *	18	=	Sand..or..something?
         *	17	=	Water found water
         *	16	=	Wooden roof I think.
         *	15	=	Looks like an overlay for water?
         *	14	=	Portrait of a king
         *	13	=	Landscape of mountains	
         *	12	=	Bars on those jail doors, etc
         *	11	=	Runecrafting altar \ mysterious rune texture
         *	10	=	Solid blue...
         *	9	=	Tree stumps
         *	8	=	Tree leaves
         *	7	=	Um.. Logs or something?
         *	6	=	Red roofing
         *	5	=	Really dark wood texture
         *	4	=	Elven doors
         *	3	=	Wooden flooring
         *	2	=	Stone walls (i.e. on Varrock banks, etc)
         *	1	=	Water
         *	0	=	Door
         **/
        
        // Changes water into flowing water.
//        if (textureId == 1) {
//           textureId = 24;
//        }

        textureLastUsed[textureId] = textureGetCount++;
        if (texelCache[textureId] != null) {
            return texelCache[textureId];
        }
        //Start of mem management code
        int texels[];
        if (textureTexelPoolPointer > 0) { //Freed texture data arrays available
            texels = texelArrayPool[--textureTexelPoolPointer];
            texelArrayPool[textureTexelPoolPointer] = null;
        } else { //No freed texture data arrays available, recycle least used texture's array
            int lastUsed = 0;
            int target = -1;
            for (int l = 0; l < loadedTextureCount; l++) {
                if (texelCache[l] != null && (textureLastUsed[l] < lastUsed || target == -1)) {
                    lastUsed = textureLastUsed[l];
                    target = l;
                }
            }
            texels = texelCache[target];
            texelCache[target] = null;
        }
        texelCache[textureId] = texels;
        //End of mem management code
        IndexedImage indexedImage = textureImages[textureId];
        int texturePalette[] = texturePalettes[textureId];
        if (lowMem) {
            textureIsTransparent[textureId] = false;
            for (int texelPtr = 0; texelPtr < 4096; texelPtr++) {
                int texel = texels[texelPtr] = texturePalette[indexedImage.imgPixels[texelPtr]] & 0xf8f8ff;
                if (texel == 0) {
                    textureIsTransparent[textureId] = true;
                }
                texels[4096 + texelPtr] = texel - (texel >>> 3) & 0xf8f8ff;
                texels[8192 + texelPtr] = texel - (texel >>> 2) & 0xf8f8ff;
                texels[12288 + texelPtr] = texel - (texel >>> 2) - (texel >>> 3) & 0xf8f8ff;
            }
        } else {
            if (indexedImage.imgWidth == 64) {
                for (int y = 0; y < 128; y++) {
                    for (int x = 0; x < 128; x++) {
                        texels[x + (y << 7)] = texturePalette[indexedImage.imgPixels[(x >> 1) + ((y >> 1) << 6)]];
                    }

                }

            } else {
                for (int texelPtr = 0; texelPtr < 16384; texelPtr++) {
                    texels[texelPtr] = texturePalette[indexedImage.imgPixels[texelPtr]];
                }
            }
            textureIsTransparent[textureId] = false;
            for (int texelPtr = 0; texelPtr < 16384; texelPtr++) {
                texels[texelPtr] &= 0xf8f8ff;
                int texel = texels[texelPtr];
                if (texel == 0) {
                    textureIsTransparent[textureId] = true;
                }
                texels[16384 + texelPtr] = texel - (texel >>> 3) & 0xf8f8ff;
                texels[32768 + texelPtr] = texel - (texel >>> 2) & 0xf8f8ff;
                texels[49152 + texelPtr] = texel - (texel >>> 2) - (texel >>> 3) & 0xf8f8ff;
            }
        }
        return texels;
    }

    public static void calculatePalette(double brightness) {
        brightness += Math.random() * 0.029999999999999999D - 0.014999999999999999D;
        int hsl = 0;
        for (int k = 0; k < 512; k++) {
            double d1 = (double) (k / 8) / 64D + 0.0078125D;
            double d2 = (double) (k & 7) / 8D + 0.0625D;
            for (int k1 = 0; k1 < 128; k1++) {
                double d3 = (double) k1 / 128D;
                double r = d3;
                double g = d3;
                double b = d3;
                if (d2 != 0.0D) {
                    double d7;
                    if (d3 < 0.5D) {
                        d7 = d3 * (1.0D + d2);
                    } else {
                        d7 = (d3 + d2) - d3 * d2;
                    }
                    double d8 = 2D * d3 - d7;
                    double d9 = d1 + 0.33333333333333331D;
                    if (d9 > 1.0D) {
                        d9--;
                    }
                    double d10 = d1;
                    double d11 = d1 - 0.33333333333333331D;
                    if (d11 < 0.0D) {
                        d11++;
                    }
                    if (6D * d9 < 1.0D) {
                        r = d8 + (d7 - d8) * 6D * d9;
                    } else if (2D * d9 < 1.0D) {
                        r = d7;
                    } else if (3D * d9 < 2D) {
                        r = d8 + (d7 - d8) * (0.66666666666666663D - d9) * 6D;
                    } else {
                        r = d8;
                    }
                    if (6D * d10 < 1.0D) {
                        g = d8 + (d7 - d8) * 6D * d10;
                    } else if (2D * d10 < 1.0D) {
                        g = d7;
                    } else if (3D * d10 < 2D) {
                        g = d8 + (d7 - d8) * (0.66666666666666663D - d10) * 6D;
                    } else {
                        g = d8;
                    }
                    if (6D * d11 < 1.0D) {
                        b = d8 + (d7 - d8) * 6D * d11;
                    } else if (2D * d11 < 1.0D) {
                        b = d7;
                    } else if (3D * d11 < 2D) {
                        b = d8 + (d7 - d8) * (0.66666666666666663D - d11) * 6D;
                    } else {
                        b = d8;
                    }
                }
                int byteR = (int) (r * 256D);
                int byteG = (int) (g * 256D);
                int byteB = (int) (b * 256D);
                int rgb = (byteR << 16) + (byteG << 8) + byteB;
                rgb = adjustBrightness(rgb, brightness);
                if (rgb == 0) {
                    rgb = 1;
                }
                hsl2rgb[hsl++] = rgb;
            }
        }
        for (int textureId = 0; textureId < 50; textureId++) {
            if (textureImages[textureId] != null) {
                int ai[] = textureImages[textureId].imgPalette;
                texturePalettes[textureId] = new int[ai.length];
                for (int j1 = 0; j1 < ai.length; j1++) {
                    texturePalettes[textureId][j1] = adjustBrightness(ai[j1], brightness);
                    if ((texturePalettes[textureId][j1] & 0xf8f8ff) == 0 && j1 != 0) {
                        texturePalettes[textureId][j1] = 1;
                    }
                }
            }
        }
        for (int textureId = 0; textureId < 50; textureId++) {
            resetTexture(textureId);
        }
    }

    public static int adjustBrightness(int rgb, double intensity) {
        double r = (double) (rgb >> 16) / 256D;
        double g = (double) (rgb >> 8 & 0xff) / 256D;
        double b = (double) (rgb & 0xff) / 256D;
        r = Math.pow(r, intensity);
        g = Math.pow(g, intensity);
        b = Math.pow(b, intensity);
        int byteR = (int) (r * 256D);
        int byteG = (int) (g * 256D);
        int byteB = (int) (b * 256D);
        return (byteR << 16) + (byteG << 8) + byteB;
    }

    public static void drawShadedTriangle(int i, int j, int k, int l, int i1, int j1, int k1, int l1, int i2) {
        int j2 = 0;
        int k2 = 0;
        if (j != i) {
            j2 = (i1 - l << 16) / (j - i);
            k2 = (l1 - k1 << 15) / (j - i);
        }
        int l2 = 0;
        int i3 = 0;
        if (k != j) {
            l2 = (j1 - i1 << 16) / (k - j);
            i3 = (i2 - l1 << 15) / (k - j);
        }
        int j3 = 0;
        int k3 = 0;
        if (k != i) {
            j3 = (l - j1 << 16) / (i - k);
            k3 = (k1 - i2 << 15) / (i - k);
        }
        if (i <= j && i <= k) {
            if (i >= DrawingArea.bottomY) {
                return;
            }
            if (j > DrawingArea.bottomY) {
                j = DrawingArea.bottomY;
            }
            if (k > DrawingArea.bottomY) {
                k = DrawingArea.bottomY;
            }
            if (j < k) {
                j1 = l <<= 16;
                i2 = k1 <<= 15;
                if (i < 0) {
                    j1 -= j3 * i;
                    l -= j2 * i;
                    i2 -= k3 * i;
                    k1 -= k2 * i;
                    i = 0;
                }
                i1 <<= 16;
                l1 <<= 15;
                if (j < 0) {
                    i1 -= l2 * j;
                    l1 -= i3 * j;
                    j = 0;
                }
                if (i != j && j3 < j2 || i == j && j3 > l2) {
                    k -= j;
                    j -= i;
                    for (i = lineOffsets[i]; --j >= 0; i += DrawingArea.width) {
                        method375(DrawingArea.pixels, i, 0, 0, j1 >> 16, l >> 16, i2 >> 7, k1 >> 7);
                        j1 += j3;
                        l += j2;
                        i2 += k3;
                        k1 += k2;
                    }

                    while (--k >= 0) {
                        method375(DrawingArea.pixels, i, 0, 0, j1 >> 16, i1 >> 16, i2 >> 7, l1 >> 7);
                        j1 += j3;
                        i1 += l2;
                        i2 += k3;
                        l1 += i3;
                        i += DrawingArea.width;
                    }
                    return;
                }
                k -= j;
                j -= i;
                for (i = lineOffsets[i]; --j >= 0; i += DrawingArea.width) {
                    method375(DrawingArea.pixels, i, 0, 0, l >> 16, j1 >> 16, k1 >> 7, i2 >> 7);
                    j1 += j3;
                    l += j2;
                    i2 += k3;
                    k1 += k2;
                }

                while (--k >= 0) {
                    method375(DrawingArea.pixels, i, 0, 0, i1 >> 16, j1 >> 16, l1 >> 7, i2 >> 7);
                    j1 += j3;
                    i1 += l2;
                    i2 += k3;
                    l1 += i3;
                    i += DrawingArea.width;
                }
                return;
            }
            i1 = l <<= 16;
            l1 = k1 <<= 15;
            if (i < 0) {
                i1 -= j3 * i;
                l -= j2 * i;
                l1 -= k3 * i;
                k1 -= k2 * i;
                i = 0;
            }
            j1 <<= 16;
            i2 <<= 15;
            if (k < 0) {
                j1 -= l2 * k;
                i2 -= i3 * k;
                k = 0;
            }
            if (i != k && j3 < j2 || i == k && l2 > j2) {
                j -= k;
                k -= i;
                for (i = lineOffsets[i]; --k >= 0; i += DrawingArea.width) {
                    method375(DrawingArea.pixels, i, 0, 0, i1 >> 16, l >> 16, l1 >> 7, k1 >> 7);
                    i1 += j3;
                    l += j2;
                    l1 += k3;
                    k1 += k2;
                }

                while (--j >= 0) {
                    method375(DrawingArea.pixels, i, 0, 0, j1 >> 16, l >> 16, i2 >> 7, k1 >> 7);
                    j1 += l2;
                    l += j2;
                    i2 += i3;
                    k1 += k2;
                    i += DrawingArea.width;
                }
                return;
            }
            j -= k;
            k -= i;
            for (i = lineOffsets[i]; --k >= 0; i += DrawingArea.width) {
                method375(DrawingArea.pixels, i, 0, 0, l >> 16, i1 >> 16, k1 >> 7, l1 >> 7);
                i1 += j3;
                l += j2;
                l1 += k3;
                k1 += k2;
            }

            while (--j >= 0) {
                method375(DrawingArea.pixels, i, 0, 0, l >> 16, j1 >> 16, k1 >> 7, i2 >> 7);
                j1 += l2;
                l += j2;
                i2 += i3;
                k1 += k2;
                i += DrawingArea.width;
            }
            return;
        }
        if (j <= k) {
            if (j >= DrawingArea.bottomY) {
                return;
            }
            if (k > DrawingArea.bottomY) {
                k = DrawingArea.bottomY;
            }
            if (i > DrawingArea.bottomY) {
                i = DrawingArea.bottomY;
            }
            if (k < i) {
                l = i1 <<= 16;
                k1 = l1 <<= 15;
                if (j < 0) {
                    l -= j2 * j;
                    i1 -= l2 * j;
                    k1 -= k2 * j;
                    l1 -= i3 * j;
                    j = 0;
                }
                j1 <<= 16;
                i2 <<= 15;
                if (k < 0) {
                    j1 -= j3 * k;
                    i2 -= k3 * k;
                    k = 0;
                }
                if (j != k && j2 < l2 || j == k && j2 > j3) {
                    i -= k;
                    k -= j;
                    for (j = lineOffsets[j]; --k >= 0; j += DrawingArea.width) {
                        method375(DrawingArea.pixels, j, 0, 0, l >> 16, i1 >> 16, k1 >> 7, l1 >> 7);
                        l += j2;
                        i1 += l2;
                        k1 += k2;
                        l1 += i3;
                    }

                    while (--i >= 0) {
                        method375(DrawingArea.pixels, j, 0, 0, l >> 16, j1 >> 16, k1 >> 7, i2 >> 7);
                        l += j2;
                        j1 += j3;
                        k1 += k2;
                        i2 += k3;
                        j += DrawingArea.width;
                    }
                    return;
                }
                i -= k;
                k -= j;
                for (j = lineOffsets[j]; --k >= 0; j += DrawingArea.width) {
                    method375(DrawingArea.pixels, j, 0, 0, i1 >> 16, l >> 16, l1 >> 7, k1 >> 7);
                    l += j2;
                    i1 += l2;
                    k1 += k2;
                    l1 += i3;
                }

                while (--i >= 0) {
                    method375(DrawingArea.pixels, j, 0, 0, j1 >> 16, l >> 16, i2 >> 7, k1 >> 7);
                    l += j2;
                    j1 += j3;
                    k1 += k2;
                    i2 += k3;
                    j += DrawingArea.width;
                }
                return;
            }
            j1 = i1 <<= 16;
            i2 = l1 <<= 15;
            if (j < 0) {
                j1 -= j2 * j;
                i1 -= l2 * j;
                i2 -= k2 * j;
                l1 -= i3 * j;
                j = 0;
            }
            l <<= 16;
            k1 <<= 15;
            if (i < 0) {
                l -= j3 * i;
                k1 -= k3 * i;
                i = 0;
            }
            if (j2 < l2) {
                k -= i;
                i -= j;
                for (j = lineOffsets[j]; --i >= 0; j += DrawingArea.width) {
                    method375(DrawingArea.pixels, j, 0, 0, j1 >> 16, i1 >> 16, i2 >> 7, l1 >> 7);
                    j1 += j2;
                    i1 += l2;
                    i2 += k2;
                    l1 += i3;
                }

                while (--k >= 0) {
                    method375(DrawingArea.pixels, j, 0, 0, l >> 16, i1 >> 16, k1 >> 7, l1 >> 7);
                    l += j3;
                    i1 += l2;
                    k1 += k3;
                    l1 += i3;
                    j += DrawingArea.width;
                }
                return;
            }
            k -= i;
            i -= j;
            for (j = lineOffsets[j]; --i >= 0; j += DrawingArea.width) {
                method375(DrawingArea.pixels, j, 0, 0, i1 >> 16, j1 >> 16, l1 >> 7, i2 >> 7);
                j1 += j2;
                i1 += l2;
                i2 += k2;
                l1 += i3;
            }

            while (--k >= 0) {
                method375(DrawingArea.pixels, j, 0, 0, i1 >> 16, l >> 16, l1 >> 7, k1 >> 7);
                l += j3;
                i1 += l2;
                k1 += k3;
                l1 += i3;
                j += DrawingArea.width;
            }
            return;
        }
        if (k >= DrawingArea.bottomY) {
            return;
        }
        if (i > DrawingArea.bottomY) {
            i = DrawingArea.bottomY;
        }
        if (j > DrawingArea.bottomY) {
            j = DrawingArea.bottomY;
        }
        if (i < j) {
            i1 = j1 <<= 16;
            l1 = i2 <<= 15;
            if (k < 0) {
                i1 -= l2 * k;
                j1 -= j3 * k;
                l1 -= i3 * k;
                i2 -= k3 * k;
                k = 0;
            }
            l <<= 16;
            k1 <<= 15;
            if (i < 0) {
                l -= j2 * i;
                k1 -= k2 * i;
                i = 0;
            }
            if (l2 < j3) {
                j -= i;
                i -= k;
                for (k = lineOffsets[k]; --i >= 0; k += DrawingArea.width) {
                    method375(DrawingArea.pixels, k, 0, 0, i1 >> 16, j1 >> 16, l1 >> 7, i2 >> 7);
                    i1 += l2;
                    j1 += j3;
                    l1 += i3;
                    i2 += k3;
                }

                while (--j >= 0) {
                    method375(DrawingArea.pixels, k, 0, 0, i1 >> 16, l >> 16, l1 >> 7, k1 >> 7);
                    i1 += l2;
                    l += j2;
                    l1 += i3;
                    k1 += k2;
                    k += DrawingArea.width;
                }
                return;
            }
            j -= i;
            i -= k;
            for (k = lineOffsets[k]; --i >= 0; k += DrawingArea.width) {
                method375(DrawingArea.pixels, k, 0, 0, j1 >> 16, i1 >> 16, i2 >> 7, l1 >> 7);
                i1 += l2;
                j1 += j3;
                l1 += i3;
                i2 += k3;
            }

            while (--j >= 0) {
                method375(DrawingArea.pixels, k, 0, 0, l >> 16, i1 >> 16, k1 >> 7, l1 >> 7);
                i1 += l2;
                l += j2;
                l1 += i3;
                k1 += k2;
                k += DrawingArea.width;
            }
            return;
        }
        l = j1 <<= 16;
        k1 = i2 <<= 15;
        if (k < 0) {
            l -= l2 * k;
            j1 -= j3 * k;
            k1 -= i3 * k;
            i2 -= k3 * k;
            k = 0;
        }
        i1 <<= 16;
        l1 <<= 15;
        if (j < 0) {
            i1 -= j2 * j;
            l1 -= k2 * j;
            j = 0;
        }
        if (l2 < j3) {
            i -= j;
            j -= k;
            for (k = lineOffsets[k]; --j >= 0; k += DrawingArea.width) {
                method375(DrawingArea.pixels, k, 0, 0, l >> 16, j1 >> 16, k1 >> 7, i2 >> 7);
                l += l2;
                j1 += j3;
                k1 += i3;
                i2 += k3;
            }

            while (--i >= 0) {
                method375(DrawingArea.pixels, k, 0, 0, i1 >> 16, j1 >> 16, l1 >> 7, i2 >> 7);
                i1 += j2;
                j1 += j3;
                l1 += k2;
                i2 += k3;
                k += DrawingArea.width;
            }
            return;
        }
        i -= j;
        j -= k;
        for (k = lineOffsets[k]; --j >= 0; k += DrawingArea.width) {
            method375(DrawingArea.pixels, k, 0, 0, j1 >> 16, l >> 16, i2 >> 7, k1 >> 7);
            l += l2;
            j1 += j3;
            k1 += i3;
            i2 += k3;
        }

        while (--i >= 0) {
            method375(DrawingArea.pixels, k, 0, 0, j1 >> 16, i1 >> 16, i2 >> 7, l1 >> 7);
            i1 += j2;
            j1 += j3;
            l1 += k2;
            i2 += k3;
            k += DrawingArea.width;
        }
    }

    public static void method375(int ai[], int i, int j, int k, int l, int i1, int j1, int k1) {
        if (notTextured) {
            int l1;
            if (restrictEdges) {
                if (i1 - l > 3) {
                    l1 = (k1 - j1) / (i1 - l);
                } else {
                    l1 = 0;
                }
                if (i1 > DrawingArea.centerX) {
                    i1 = DrawingArea.centerX;
                }
                if (l < 0) {
                    j1 -= l * l1;
                    l = 0;
                }
                if (l >= i1) {
                    return;
                }
                i += l;
                k = i1 - l >> 2;
                l1 <<= 2;
            } else {
                if (l >= i1) {
                    return;
                }
                i += l;
                k = i1 - l >> 2;
                if (k > 0) {
                    l1 = (k1 - j1) * shadowDecay[k] >> 15;
                } else {
                    l1 = 0;
                }
            }
            if (alpha == 0) {
                while (--k >= 0) {
                    j = hsl2rgb[j1 >> 8];
                    j1 += l1;
                    ai[i++] = j;
                    ai[i++] = j;
                    ai[i++] = j;
                    ai[i++] = j;
                }
                k = i1 - l & 3;
                if (k > 0) {
                    j = hsl2rgb[j1 >> 8];
                    do {
                        ai[i++] = j;
                    } while (--k > 0);
                    return;
                }
            } else {
                int j2 = alpha;
                int l2 = 256 - alpha;
                while (--k >= 0) {
                    j = hsl2rgb[j1 >> 8];
                    j1 += l1;
                    j = ((j & 0xff00ff) * l2 >> 8 & 0xff00ff) + ((j & 0xff00) * l2 >> 8 & 0xff00);
                    ai[i++] = j + ((ai[i] & 0xff00ff) * j2 >> 8 & 0xff00ff) + ((ai[i] & 0xff00) * j2 >> 8 & 0xff00);
                    ai[i++] = j + ((ai[i] & 0xff00ff) * j2 >> 8 & 0xff00ff) + ((ai[i] & 0xff00) * j2 >> 8 & 0xff00);
                    ai[i++] = j + ((ai[i] & 0xff00ff) * j2 >> 8 & 0xff00ff) + ((ai[i] & 0xff00) * j2 >> 8 & 0xff00);
                    ai[i++] = j + ((ai[i] & 0xff00ff) * j2 >> 8 & 0xff00ff) + ((ai[i] & 0xff00) * j2 >> 8 & 0xff00);
                }
                k = i1 - l & 3;
                if (k > 0) {
                    j = hsl2rgb[j1 >> 8];
                    j = ((j & 0xff00ff) * l2 >> 8 & 0xff00ff) + ((j & 0xff00) * l2 >> 8 & 0xff00);
                    do {
                        ai[i++] = j + ((ai[i] & 0xff00ff) * j2 >> 8 & 0xff00ff) + ((ai[i] & 0xff00) * j2 >> 8 & 0xff00);
                    } while (--k > 0);
                }
            }
            return;
        }
        if (l >= i1) {
            return;
        }
        int i2 = (k1 - j1) / (i1 - l);
        if (restrictEdges) {
            if (i1 > DrawingArea.centerX) {
                i1 = DrawingArea.centerX;
            }
            if (l < 0) {
                j1 -= l * i2;
                l = 0;
            }
            if (l >= i1) {
                return;
            }
        }
        i += l;
        k = i1 - l;
        if (alpha == 0) {
            do {
                ai[i++] = hsl2rgb[j1 >> 8];
                j1 += i2;
            } while (--k > 0);
            return;
        }
        int k2 = alpha;
        int i3 = 256 - alpha;
        do {
            j = hsl2rgb[j1 >> 8];
            j1 += i2;
            j = ((j & 0xff00ff) * i3 >> 8 & 0xff00ff) + ((j & 0xff00) * i3 >> 8 & 0xff00);
            ai[i++] = j + ((ai[i] & 0xff00ff) * k2 >> 8 & 0xff00ff) + ((ai[i] & 0xff00) * k2 >> 8 & 0xff00);
        } while (--k > 0);
    }

    public static void method376(int i, int j, int k, int l, int i1, int j1, int k1) {
        int l1 = 0;
        if (j != i) {
            l1 = (i1 - l << 16) / (j - i);
        }
        int i2 = 0;
        if (k != j) {
            i2 = (j1 - i1 << 16) / (k - j);
        }
        int j2 = 0;
        if (k != i) {
            j2 = (l - j1 << 16) / (i - k);
        }
        if (i <= j && i <= k) {
            if (i >= DrawingArea.bottomY) {
                return;
            }
            if (j > DrawingArea.bottomY) {
                j = DrawingArea.bottomY;
            }
            if (k > DrawingArea.bottomY) {
                k = DrawingArea.bottomY;
            }
            if (j < k) {
                j1 = l <<= 16;
                if (i < 0) {
                    j1 -= j2 * i;
                    l -= l1 * i;
                    i = 0;
                }
                i1 <<= 16;
                if (j < 0) {
                    i1 -= i2 * j;
                    j = 0;
                }
                if (i != j && j2 < l1 || i == j && j2 > i2) {
                    k -= j;
                    j -= i;
                    for (i = lineOffsets[i]; --j >= 0; i += DrawingArea.width) {
                        method377(DrawingArea.pixels, i, k1, 0, j1 >> 16, l >> 16);
                        j1 += j2;
                        l += l1;
                    }

                    while (--k >= 0) {
                        method377(DrawingArea.pixels, i, k1, 0, j1 >> 16, i1 >> 16);
                        j1 += j2;
                        i1 += i2;
                        i += DrawingArea.width;
                    }
                    return;
                }
                k -= j;
                j -= i;
                for (i = lineOffsets[i]; --j >= 0; i += DrawingArea.width) {
                    method377(DrawingArea.pixels, i, k1, 0, l >> 16, j1 >> 16);
                    j1 += j2;
                    l += l1;
                }

                while (--k >= 0) {
                    method377(DrawingArea.pixels, i, k1, 0, i1 >> 16, j1 >> 16);
                    j1 += j2;
                    i1 += i2;
                    i += DrawingArea.width;
                }
                return;
            }
            i1 = l <<= 16;
            if (i < 0) {
                i1 -= j2 * i;
                l -= l1 * i;
                i = 0;
            }
            j1 <<= 16;
            if (k < 0) {
                j1 -= i2 * k;
                k = 0;
            }
            if (i != k && j2 < l1 || i == k && i2 > l1) {
                j -= k;
                k -= i;
                for (i = lineOffsets[i]; --k >= 0; i += DrawingArea.width) {
                    method377(DrawingArea.pixels, i, k1, 0, i1 >> 16, l >> 16);
                    i1 += j2;
                    l += l1;
                }

                while (--j >= 0) {
                    method377(DrawingArea.pixels, i, k1, 0, j1 >> 16, l >> 16);
                    j1 += i2;
                    l += l1;
                    i += DrawingArea.width;
                }
                return;
            }
            j -= k;
            k -= i;
            for (i = lineOffsets[i]; --k >= 0; i += DrawingArea.width) {
                method377(DrawingArea.pixels, i, k1, 0, l >> 16, i1 >> 16);
                i1 += j2;
                l += l1;
            }

            while (--j >= 0) {
                method377(DrawingArea.pixels, i, k1, 0, l >> 16, j1 >> 16);
                j1 += i2;
                l += l1;
                i += DrawingArea.width;
            }
            return;
        }
        if (j <= k) {
            if (j >= DrawingArea.bottomY) {
                return;
            }
            if (k > DrawingArea.bottomY) {
                k = DrawingArea.bottomY;
            }
            if (i > DrawingArea.bottomY) {
                i = DrawingArea.bottomY;
            }
            if (k < i) {
                l = i1 <<= 16;
                if (j < 0) {
                    l -= l1 * j;
                    i1 -= i2 * j;
                    j = 0;
                }
                j1 <<= 16;
                if (k < 0) {
                    j1 -= j2 * k;
                    k = 0;
                }
                if (j != k && l1 < i2 || j == k && l1 > j2) {
                    i -= k;
                    k -= j;
                    for (j = lineOffsets[j]; --k >= 0; j += DrawingArea.width) {
                        method377(DrawingArea.pixels, j, k1, 0, l >> 16, i1 >> 16);
                        l += l1;
                        i1 += i2;
                    }

                    while (--i >= 0) {
                        method377(DrawingArea.pixels, j, k1, 0, l >> 16, j1 >> 16);
                        l += l1;
                        j1 += j2;
                        j += DrawingArea.width;
                    }
                    return;
                }
                i -= k;
                k -= j;
                for (j = lineOffsets[j]; --k >= 0; j += DrawingArea.width) {
                    method377(DrawingArea.pixels, j, k1, 0, i1 >> 16, l >> 16);
                    l += l1;
                    i1 += i2;
                }

                while (--i >= 0) {
                    method377(DrawingArea.pixels, j, k1, 0, j1 >> 16, l >> 16);
                    l += l1;
                    j1 += j2;
                    j += DrawingArea.width;
                }
                return;
            }
            j1 = i1 <<= 16;
            if (j < 0) {
                j1 -= l1 * j;
                i1 -= i2 * j;
                j = 0;
            }
            l <<= 16;
            if (i < 0) {
                l -= j2 * i;
                i = 0;
            }
            if (l1 < i2) {
                k -= i;
                i -= j;
                for (j = lineOffsets[j]; --i >= 0; j += DrawingArea.width) {
                    method377(DrawingArea.pixels, j, k1, 0, j1 >> 16, i1 >> 16);
                    j1 += l1;
                    i1 += i2;
                }

                while (--k >= 0) {
                    method377(DrawingArea.pixels, j, k1, 0, l >> 16, i1 >> 16);
                    l += j2;
                    i1 += i2;
                    j += DrawingArea.width;
                }
                return;
            }
            k -= i;
            i -= j;
            for (j = lineOffsets[j]; --i >= 0; j += DrawingArea.width) {
                method377(DrawingArea.pixels, j, k1, 0, i1 >> 16, j1 >> 16);
                j1 += l1;
                i1 += i2;
            }

            while (--k >= 0) {
                method377(DrawingArea.pixels, j, k1, 0, i1 >> 16, l >> 16);
                l += j2;
                i1 += i2;
                j += DrawingArea.width;
            }
            return;
        }
        if (k >= DrawingArea.bottomY) {
            return;
        }
        if (i > DrawingArea.bottomY) {
            i = DrawingArea.bottomY;
        }
        if (j > DrawingArea.bottomY) {
            j = DrawingArea.bottomY;
        }
        if (i < j) {
            i1 = j1 <<= 16;
            if (k < 0) {
                i1 -= i2 * k;
                j1 -= j2 * k;
                k = 0;
            }
            l <<= 16;
            if (i < 0) {
                l -= l1 * i;
                i = 0;
            }
            if (i2 < j2) {
                j -= i;
                i -= k;
                for (k = lineOffsets[k]; --i >= 0; k += DrawingArea.width) {
                    method377(DrawingArea.pixels, k, k1, 0, i1 >> 16, j1 >> 16);
                    i1 += i2;
                    j1 += j2;
                }

                while (--j >= 0) {
                    method377(DrawingArea.pixels, k, k1, 0, i1 >> 16, l >> 16);
                    i1 += i2;
                    l += l1;
                    k += DrawingArea.width;
                }
                return;
            }
            j -= i;
            i -= k;
            for (k = lineOffsets[k]; --i >= 0; k += DrawingArea.width) {
                method377(DrawingArea.pixels, k, k1, 0, j1 >> 16, i1 >> 16);
                i1 += i2;
                j1 += j2;
            }

            while (--j >= 0) {
                method377(DrawingArea.pixels, k, k1, 0, l >> 16, i1 >> 16);
                i1 += i2;
                l += l1;
                k += DrawingArea.width;
            }
            return;
        }
        l = j1 <<= 16;
        if (k < 0) {
            l -= i2 * k;
            j1 -= j2 * k;
            k = 0;
        }
        i1 <<= 16;
        if (j < 0) {
            i1 -= l1 * j;
            j = 0;
        }
        if (i2 < j2) {
            i -= j;
            j -= k;
            for (k = lineOffsets[k]; --j >= 0; k += DrawingArea.width) {
                method377(DrawingArea.pixels, k, k1, 0, l >> 16, j1 >> 16);
                l += i2;
                j1 += j2;
            }

            while (--i >= 0) {
                method377(DrawingArea.pixels, k, k1, 0, i1 >> 16, j1 >> 16);
                i1 += l1;
                j1 += j2;
                k += DrawingArea.width;
            }
            return;
        }
        i -= j;
        j -= k;
        for (k = lineOffsets[k]; --j >= 0; k += DrawingArea.width) {
            method377(DrawingArea.pixels, k, k1, 0, j1 >> 16, l >> 16);
            l += i2;
            j1 += j2;
        }

        while (--i >= 0) {
            method377(DrawingArea.pixels, k, k1, 0, j1 >> 16, i1 >> 16);
            i1 += l1;
            j1 += j2;
            k += DrawingArea.width;
        }
    }

    public static void method377(int ai[], int i, int j, int k, int l, int i1) {
        if (restrictEdges) {
            if (i1 > DrawingArea.centerX) {
                i1 = DrawingArea.centerX;
            }
            if (l < 0) {
                l = 0;
            }
        }
        if (l >= i1) {
            return;
        }
        i += l;
        k = i1 - l >> 2;
        if (alpha == 0) {
            while (--k >= 0) {
                ai[i++] = j;
                ai[i++] = j;
                ai[i++] = j;
                ai[i++] = j;
            }
            for (k = i1 - l & 3; --k >= 0;) {
                ai[i++] = j;
            }

            return;
        }
        int j1 = alpha;
        int k1 = 256 - alpha;
        j = ((j & 0xff00ff) * k1 >> 8 & 0xff00ff) + ((j & 0xff00) * k1 >> 8 & 0xff00);
        while (--k >= 0) {
            ai[i++] = j + ((ai[i] & 0xff00ff) * j1 >> 8 & 0xff00ff) + ((ai[i] & 0xff00) * j1 >> 8 & 0xff00);
            ai[i++] = j + ((ai[i] & 0xff00ff) * j1 >> 8 & 0xff00ff) + ((ai[i] & 0xff00) * j1 >> 8 & 0xff00);
            ai[i++] = j + ((ai[i] & 0xff00ff) * j1 >> 8 & 0xff00ff) + ((ai[i] & 0xff00) * j1 >> 8 & 0xff00);
            ai[i++] = j + ((ai[i] & 0xff00ff) * j1 >> 8 & 0xff00ff) + ((ai[i] & 0xff00) * j1 >> 8 & 0xff00);
        }
        for (k = i1 - l & 3; --k >= 0;) {
            ai[i++] = j + ((ai[i] & 0xff00ff) * j1 >> 8 & 0xff00ff) + ((ai[i] & 0xff00) * j1 >> 8 & 0xff00);
        }

    }

    public static void method378(int i, int j, int k, int l, int i1, int j1, int k1, int l1,
            int i2, int j2, int k2, int l2, int i3, int j3, int k3,
            int l3, int i4, int j4, int k4) {
        int ai[] = getTexturePixels(k4);
        opaque = !textureIsTransparent[k4];
        k2 = j2 - k2;
        j3 = i3 - j3;
        i4 = l3 - i4;
        l2 -= j2;
        k3 -= i3;
        j4 -= l3;
        int l4 = l2 * i3 - k3 * j2 << 14;
        int i5 = k3 * l3 - j4 * i3 << 8;
        int j5 = j4 * j2 - l2 * l3 << 5;
        int k5 = k2 * i3 - j3 * j2 << 14;
        int l5 = j3 * l3 - i4 * i3 << 8;
        int i6 = i4 * j2 - k2 * l3 << 5;
        int j6 = j3 * l2 - k2 * k3 << 14;
        int k6 = i4 * k3 - j3 * j4 << 8;
        int l6 = k2 * j4 - i4 * l2 << 5;
        int i7 = 0;
        int j7 = 0;
        if (j != i) {
            i7 = (i1 - l << 16) / (j - i);
            j7 = (l1 - k1 << 16) / (j - i);
        }
        int k7 = 0;
        int l7 = 0;
        if (k != j) {
            k7 = (j1 - i1 << 16) / (k - j);
            l7 = (i2 - l1 << 16) / (k - j);
        }
        int i8 = 0;
        int j8 = 0;
        if (k != i) {
            i8 = (l - j1 << 16) / (i - k);
            j8 = (k1 - i2 << 16) / (i - k);
        }
        if (i <= j && i <= k) {
            if (i >= DrawingArea.bottomY) {
                return;
            }
            if (j > DrawingArea.bottomY) {
                j = DrawingArea.bottomY;
            }
            if (k > DrawingArea.bottomY) {
                k = DrawingArea.bottomY;
            }
            if (j < k) {
                j1 = l <<= 16;
                i2 = k1 <<= 16;
                if (i < 0) {
                    j1 -= i8 * i;
                    l -= i7 * i;
                    i2 -= j8 * i;
                    k1 -= j7 * i;
                    i = 0;
                }
                i1 <<= 16;
                l1 <<= 16;
                if (j < 0) {
                    i1 -= k7 * j;
                    l1 -= l7 * j;
                    j = 0;
                }
                int k8 = i - intCenterY;
                l4 += j5 * k8;
                k5 += i6 * k8;
                j6 += l6 * k8;
                if (i != j && i8 < i7 || i == j && i8 > k7) {
                    k -= j;
                    j -= i;
                    i = lineOffsets[i];
                    while (--j >= 0) {
                        method379(DrawingArea.pixels, ai, 0, 0, i, j1 >> 16, l >> 16, i2 >> 8, k1 >> 8, l4, k5, j6, i5, l5, k6);
                        j1 += i8;
                        l += i7;
                        i2 += j8;
                        k1 += j7;
                        i += DrawingArea.width;
                        l4 += j5;
                        k5 += i6;
                        j6 += l6;
                    }
                    while (--k >= 0) {
                        method379(DrawingArea.pixels, ai, 0, 0, i, j1 >> 16, i1 >> 16, i2 >> 8, l1 >> 8, l4, k5, j6, i5, l5, k6);
                        j1 += i8;
                        i1 += k7;
                        i2 += j8;
                        l1 += l7;
                        i += DrawingArea.width;
                        l4 += j5;
                        k5 += i6;
                        j6 += l6;
                    }
                    return;
                }
                k -= j;
                j -= i;
                i = lineOffsets[i];
                while (--j >= 0) {
                    method379(DrawingArea.pixels, ai, 0, 0, i, l >> 16, j1 >> 16, k1 >> 8, i2 >> 8, l4, k5, j6, i5, l5, k6);
                    j1 += i8;
                    l += i7;
                    i2 += j8;
                    k1 += j7;
                    i += DrawingArea.width;
                    l4 += j5;
                    k5 += i6;
                    j6 += l6;
                }
                while (--k >= 0) {
                    method379(DrawingArea.pixels, ai, 0, 0, i, i1 >> 16, j1 >> 16, l1 >> 8, i2 >> 8, l4, k5, j6, i5, l5, k6);
                    j1 += i8;
                    i1 += k7;
                    i2 += j8;
                    l1 += l7;
                    i += DrawingArea.width;
                    l4 += j5;
                    k5 += i6;
                    j6 += l6;
                }
                return;
            }
            i1 = l <<= 16;
            l1 = k1 <<= 16;
            if (i < 0) {
                i1 -= i8 * i;
                l -= i7 * i;
                l1 -= j8 * i;
                k1 -= j7 * i;
                i = 0;
            }
            j1 <<= 16;
            i2 <<= 16;
            if (k < 0) {
                j1 -= k7 * k;
                i2 -= l7 * k;
                k = 0;
            }
            int l8 = i - intCenterY;
            l4 += j5 * l8;
            k5 += i6 * l8;
            j6 += l6 * l8;
            if (i != k && i8 < i7 || i == k && k7 > i7) {
                j -= k;
                k -= i;
                i = lineOffsets[i];
                while (--k >= 0) {
                    method379(DrawingArea.pixels, ai, 0, 0, i, i1 >> 16, l >> 16, l1 >> 8, k1 >> 8, l4, k5, j6, i5, l5, k6);
                    i1 += i8;
                    l += i7;
                    l1 += j8;
                    k1 += j7;
                    i += DrawingArea.width;
                    l4 += j5;
                    k5 += i6;
                    j6 += l6;
                }
                while (--j >= 0) {
                    method379(DrawingArea.pixels, ai, 0, 0, i, j1 >> 16, l >> 16, i2 >> 8, k1 >> 8, l4, k5, j6, i5, l5, k6);
                    j1 += k7;
                    l += i7;
                    i2 += l7;
                    k1 += j7;
                    i += DrawingArea.width;
                    l4 += j5;
                    k5 += i6;
                    j6 += l6;
                }
                return;
            }
            j -= k;
            k -= i;
            i = lineOffsets[i];
            while (--k >= 0) {
                method379(DrawingArea.pixels, ai, 0, 0, i, l >> 16, i1 >> 16, k1 >> 8, l1 >> 8, l4, k5, j6, i5, l5, k6);
                i1 += i8;
                l += i7;
                l1 += j8;
                k1 += j7;
                i += DrawingArea.width;
                l4 += j5;
                k5 += i6;
                j6 += l6;
            }
            while (--j >= 0) {
                method379(DrawingArea.pixels, ai, 0, 0, i, l >> 16, j1 >> 16, k1 >> 8, i2 >> 8, l4, k5, j6, i5, l5, k6);
                j1 += k7;
                l += i7;
                i2 += l7;
                k1 += j7;
                i += DrawingArea.width;
                l4 += j5;
                k5 += i6;
                j6 += l6;
            }
            return;
        }
        if (j <= k) {
            if (j >= DrawingArea.bottomY) {
                return;
            }
            if (k > DrawingArea.bottomY) {
                k = DrawingArea.bottomY;
            }
            if (i > DrawingArea.bottomY) {
                i = DrawingArea.bottomY;
            }
            if (k < i) {
                l = i1 <<= 16;
                k1 = l1 <<= 16;
                if (j < 0) {
                    l -= i7 * j;
                    i1 -= k7 * j;
                    k1 -= j7 * j;
                    l1 -= l7 * j;
                    j = 0;
                }
                j1 <<= 16;
                i2 <<= 16;
                if (k < 0) {
                    j1 -= i8 * k;
                    i2 -= j8 * k;
                    k = 0;
                }
                int i9 = j - intCenterY;
                l4 += j5 * i9;
                k5 += i6 * i9;
                j6 += l6 * i9;
                if (j != k && i7 < k7 || j == k && i7 > i8) {
                    i -= k;
                    k -= j;
                    j = lineOffsets[j];
                    while (--k >= 0) {
                        method379(DrawingArea.pixels, ai, 0, 0, j, l >> 16, i1 >> 16, k1 >> 8, l1 >> 8, l4, k5, j6, i5, l5, k6);
                        l += i7;
                        i1 += k7;
                        k1 += j7;
                        l1 += l7;
                        j += DrawingArea.width;
                        l4 += j5;
                        k5 += i6;
                        j6 += l6;
                    }
                    while (--i >= 0) {
                        method379(DrawingArea.pixels, ai, 0, 0, j, l >> 16, j1 >> 16, k1 >> 8, i2 >> 8, l4, k5, j6, i5, l5, k6);
                        l += i7;
                        j1 += i8;
                        k1 += j7;
                        i2 += j8;
                        j += DrawingArea.width;
                        l4 += j5;
                        k5 += i6;
                        j6 += l6;
                    }
                    return;
                }
                i -= k;
                k -= j;
                j = lineOffsets[j];
                while (--k >= 0) {
                    method379(DrawingArea.pixels, ai, 0, 0, j, i1 >> 16, l >> 16, l1 >> 8, k1 >> 8, l4, k5, j6, i5, l5, k6);
                    l += i7;
                    i1 += k7;
                    k1 += j7;
                    l1 += l7;
                    j += DrawingArea.width;
                    l4 += j5;
                    k5 += i6;
                    j6 += l6;
                }
                while (--i >= 0) {
                    method379(DrawingArea.pixels, ai, 0, 0, j, j1 >> 16, l >> 16, i2 >> 8, k1 >> 8, l4, k5, j6, i5, l5, k6);
                    l += i7;
                    j1 += i8;
                    k1 += j7;
                    i2 += j8;
                    j += DrawingArea.width;
                    l4 += j5;
                    k5 += i6;
                    j6 += l6;
                }
                return;
            }
            j1 = i1 <<= 16;
            i2 = l1 <<= 16;
            if (j < 0) {
                j1 -= i7 * j;
                i1 -= k7 * j;
                i2 -= j7 * j;
                l1 -= l7 * j;
                j = 0;
            }
            l <<= 16;
            k1 <<= 16;
            if (i < 0) {
                l -= i8 * i;
                k1 -= j8 * i;
                i = 0;
            }
            int j9 = j - intCenterY;
            l4 += j5 * j9;
            k5 += i6 * j9;
            j6 += l6 * j9;
            if (i7 < k7) {
                k -= i;
                i -= j;
                j = lineOffsets[j];
                while (--i >= 0) {
                    method379(DrawingArea.pixels, ai, 0, 0, j, j1 >> 16, i1 >> 16, i2 >> 8, l1 >> 8, l4, k5, j6, i5, l5, k6);
                    j1 += i7;
                    i1 += k7;
                    i2 += j7;
                    l1 += l7;
                    j += DrawingArea.width;
                    l4 += j5;
                    k5 += i6;
                    j6 += l6;
                }
                while (--k >= 0) {
                    method379(DrawingArea.pixels, ai, 0, 0, j, l >> 16, i1 >> 16, k1 >> 8, l1 >> 8, l4, k5, j6, i5, l5, k6);
                    l += i8;
                    i1 += k7;
                    k1 += j8;
                    l1 += l7;
                    j += DrawingArea.width;
                    l4 += j5;
                    k5 += i6;
                    j6 += l6;
                }
                return;
            }
            k -= i;
            i -= j;
            j = lineOffsets[j];
            while (--i >= 0) {
                method379(DrawingArea.pixels, ai, 0, 0, j, i1 >> 16, j1 >> 16, l1 >> 8, i2 >> 8, l4, k5, j6, i5, l5, k6);
                j1 += i7;
                i1 += k7;
                i2 += j7;
                l1 += l7;
                j += DrawingArea.width;
                l4 += j5;
                k5 += i6;
                j6 += l6;
            }
            while (--k >= 0) {
                method379(DrawingArea.pixels, ai, 0, 0, j, i1 >> 16, l >> 16, l1 >> 8, k1 >> 8, l4, k5, j6, i5, l5, k6);
                l += i8;
                i1 += k7;
                k1 += j8;
                l1 += l7;
                j += DrawingArea.width;
                l4 += j5;
                k5 += i6;
                j6 += l6;
            }
            return;
        }
        if (k >= DrawingArea.bottomY) {
            return;
        }
        if (i > DrawingArea.bottomY) {
            i = DrawingArea.bottomY;
        }
        if (j > DrawingArea.bottomY) {
            j = DrawingArea.bottomY;
        }
        if (i < j) {
            i1 = j1 <<= 16;
            l1 = i2 <<= 16;
            if (k < 0) {
                i1 -= k7 * k;
                j1 -= i8 * k;
                l1 -= l7 * k;
                i2 -= j8 * k;
                k = 0;
            }
            l <<= 16;
            k1 <<= 16;
            if (i < 0) {
                l -= i7 * i;
                k1 -= j7 * i;
                i = 0;
            }
            int k9 = k - intCenterY;
            l4 += j5 * k9;
            k5 += i6 * k9;
            j6 += l6 * k9;
            if (k7 < i8) {
                j -= i;
                i -= k;
                k = lineOffsets[k];
                while (--i >= 0) {
                    method379(DrawingArea.pixels, ai, 0, 0, k, i1 >> 16, j1 >> 16, l1 >> 8, i2 >> 8, l4, k5, j6, i5, l5, k6);
                    i1 += k7;
                    j1 += i8;
                    l1 += l7;
                    i2 += j8;
                    k += DrawingArea.width;
                    l4 += j5;
                    k5 += i6;
                    j6 += l6;
                }
                while (--j >= 0) {
                    method379(DrawingArea.pixels, ai, 0, 0, k, i1 >> 16, l >> 16, l1 >> 8, k1 >> 8, l4, k5, j6, i5, l5, k6);
                    i1 += k7;
                    l += i7;
                    l1 += l7;
                    k1 += j7;
                    k += DrawingArea.width;
                    l4 += j5;
                    k5 += i6;
                    j6 += l6;
                }
                return;
            }
            j -= i;
            i -= k;
            k = lineOffsets[k];
            while (--i >= 0) {
                method379(DrawingArea.pixels, ai, 0, 0, k, j1 >> 16, i1 >> 16, i2 >> 8, l1 >> 8, l4, k5, j6, i5, l5, k6);
                i1 += k7;
                j1 += i8;
                l1 += l7;
                i2 += j8;
                k += DrawingArea.width;
                l4 += j5;
                k5 += i6;
                j6 += l6;
            }
            while (--j >= 0) {
                method379(DrawingArea.pixels, ai, 0, 0, k, l >> 16, i1 >> 16, k1 >> 8, l1 >> 8, l4, k5, j6, i5, l5, k6);
                i1 += k7;
                l += i7;
                l1 += l7;
                k1 += j7;
                k += DrawingArea.width;
                l4 += j5;
                k5 += i6;
                j6 += l6;
            }
            return;
        }
        l = j1 <<= 16;
        k1 = i2 <<= 16;
        if (k < 0) {
            l -= k7 * k;
            j1 -= i8 * k;
            k1 -= l7 * k;
            i2 -= j8 * k;
            k = 0;
        }
        i1 <<= 16;
        l1 <<= 16;
        if (j < 0) {
            i1 -= i7 * j;
            l1 -= j7 * j;
            j = 0;
        }
        int l9 = k - intCenterY;
        l4 += j5 * l9;
        k5 += i6 * l9;
        j6 += l6 * l9;
        if (k7 < i8) {
            i -= j;
            j -= k;
            k = lineOffsets[k];
            while (--j >= 0) {
                method379(DrawingArea.pixels, ai, 0, 0, k, l >> 16, j1 >> 16, k1 >> 8, i2 >> 8, l4, k5, j6, i5, l5, k6);
                l += k7;
                j1 += i8;
                k1 += l7;
                i2 += j8;
                k += DrawingArea.width;
                l4 += j5;
                k5 += i6;
                j6 += l6;
            }
            while (--i >= 0) {
                method379(DrawingArea.pixels, ai, 0, 0, k, i1 >> 16, j1 >> 16, l1 >> 8, i2 >> 8, l4, k5, j6, i5, l5, k6);
                i1 += i7;
                j1 += i8;
                l1 += j7;
                i2 += j8;
                k += DrawingArea.width;
                l4 += j5;
                k5 += i6;
                j6 += l6;
            }
            return;
        }
        i -= j;
        j -= k;
        k = lineOffsets[k];
        while (--j >= 0) {
            method379(DrawingArea.pixels, ai, 0, 0, k, j1 >> 16, l >> 16, i2 >> 8, k1 >> 8, l4, k5, j6, i5, l5, k6);
            l += k7;
            j1 += i8;
            k1 += l7;
            i2 += j8;
            k += DrawingArea.width;
            l4 += j5;
            k5 += i6;
            j6 += l6;
        }
        while (--i >= 0) {
            method379(DrawingArea.pixels, ai, 0, 0, k, j1 >> 16, i1 >> 16, i2 >> 8, l1 >> 8, l4, k5, j6, i5, l5, k6);
            i1 += i7;
            j1 += i8;
            l1 += j7;
            i2 += j8;
            k += DrawingArea.width;
            l4 += j5;
            k5 += i6;
            j6 += l6;
        }
    }

    public static void method379(int ai[], int ai1[], int i, int j, int k, int l, int i1, int j1,
            int k1, int l1, int i2, int j2, int k2, int l2, int i3) {
        if (l >= i1) {
            return;
        }
        int j3;
        int k3;
        if (restrictEdges) {
            j3 = (k1 - j1) / (i1 - l);
            if (i1 > DrawingArea.centerX) {
                i1 = DrawingArea.centerX;
            }
            if (l < 0) {
                j1 -= l * j3;
                l = 0;
            }
            if (l >= i1) {
                return;
            }
            k3 = i1 - l >> 3;
            j3 <<= 12;
            j1 <<= 9;
        } else {
            if (i1 - l > 7) {
                k3 = i1 - l >> 3;
                j3 = (k1 - j1) * shadowDecay[k3] >> 6;
            } else {
                k3 = 0;
                j3 = 0;
            }
            j1 <<= 9;
        }
        k += l;
        if (lowMem) {
            int i4 = 0;
            int k4 = 0;
            int k6 = l - intCenterX;
            l1 += (k2 >> 3) * k6;
            i2 += (l2 >> 3) * k6;
            j2 += (i3 >> 3) * k6;
            int i5 = j2 >> 12;
            if (i5 != 0) {
                i = l1 / i5;
                j = i2 / i5;
                if (i < 0) {
                    i = 0;
                } else if (i > 4032) {
                    i = 4032;
                }
            }
            l1 += k2;
            i2 += l2;
            j2 += i3;
            i5 = j2 >> 12;
            if (i5 != 0) {
                i4 = l1 / i5;
                k4 = i2 / i5;
                if (i4 < 7) {
                    i4 = 7;
                } else if (i4 > 4032) {
                    i4 = 4032;
                }
            }
            int i7 = i4 - i >> 3;
            int k7 = k4 - j >> 3;
            i += (j1 & 0x600000) >> 3;
            int i8 = j1 >> 23;
            if (opaque) {
                while (k3-- > 0) {
                    ai[k++] = ai1[(j & 0xfc0) + (i >> 6)] >>> i8;
                    i += i7;
                    j += k7;
                    ai[k++] = ai1[(j & 0xfc0) + (i >> 6)] >>> i8;
                    i += i7;
                    j += k7;
                    ai[k++] = ai1[(j & 0xfc0) + (i >> 6)] >>> i8;
                    i += i7;
                    j += k7;
                    ai[k++] = ai1[(j & 0xfc0) + (i >> 6)] >>> i8;
                    i += i7;
                    j += k7;
                    ai[k++] = ai1[(j & 0xfc0) + (i >> 6)] >>> i8;
                    i += i7;
                    j += k7;
                    ai[k++] = ai1[(j & 0xfc0) + (i >> 6)] >>> i8;
                    i += i7;
                    j += k7;
                    ai[k++] = ai1[(j & 0xfc0) + (i >> 6)] >>> i8;
                    i += i7;
                    j += k7;
                    ai[k++] = ai1[(j & 0xfc0) + (i >> 6)] >>> i8;
                    i = i4;
                    j = k4;
                    l1 += k2;
                    i2 += l2;
                    j2 += i3;
                    int j5 = j2 >> 12;
                    if (j5 != 0) {
                        i4 = l1 / j5;
                        k4 = i2 / j5;
                        if (i4 < 7) {
                            i4 = 7;
                        } else if (i4 > 4032) {
                            i4 = 4032;
                        }
                    }
                    i7 = i4 - i >> 3;
                    k7 = k4 - j >> 3;
                    j1 += j3;
                    i += (j1 & 0x600000) >> 3;
                    i8 = j1 >> 23;
                }
                for (k3 = i1 - l & 7; k3-- > 0;) {
                    ai[k++] = ai1[(j & 0xfc0) + (i >> 6)] >>> i8;
                    i += i7;
                    j += k7;
                }

                return;
            }
            while (k3-- > 0) {
                int k8;
                if ((k8 = ai1[(j & 0xfc0) + (i >> 6)] >>> i8) != 0) {
                    ai[k] = k8;
                }
                k++;
                i += i7;
                j += k7;
                if ((k8 = ai1[(j & 0xfc0) + (i >> 6)] >>> i8) != 0) {
                    ai[k] = k8;
                }
                k++;
                i += i7;
                j += k7;
                if ((k8 = ai1[(j & 0xfc0) + (i >> 6)] >>> i8) != 0) {
                    ai[k] = k8;
                }
                k++;
                i += i7;
                j += k7;
                if ((k8 = ai1[(j & 0xfc0) + (i >> 6)] >>> i8) != 0) {
                    ai[k] = k8;
                }
                k++;
                i += i7;
                j += k7;
                if ((k8 = ai1[(j & 0xfc0) + (i >> 6)] >>> i8) != 0) {
                    ai[k] = k8;
                }
                k++;
                i += i7;
                j += k7;
                if ((k8 = ai1[(j & 0xfc0) + (i >> 6)] >>> i8) != 0) {
                    ai[k] = k8;
                }
                k++;
                i += i7;
                j += k7;
                if ((k8 = ai1[(j & 0xfc0) + (i >> 6)] >>> i8) != 0) {
                    ai[k] = k8;
                }
                k++;
                i += i7;
                j += k7;
                if ((k8 = ai1[(j & 0xfc0) + (i >> 6)] >>> i8) != 0) {
                    ai[k] = k8;
                }
                k++;
                i = i4;
                j = k4;
                l1 += k2;
                i2 += l2;
                j2 += i3;
                int k5 = j2 >> 12;
                if (k5 != 0) {
                    i4 = l1 / k5;
                    k4 = i2 / k5;
                    if (i4 < 7) {
                        i4 = 7;
                    } else if (i4 > 4032) {
                        i4 = 4032;
                    }
                }
                i7 = i4 - i >> 3;
                k7 = k4 - j >> 3;
                j1 += j3;
                i += (j1 & 0x600000) >> 3;
                i8 = j1 >> 23;
            }
            for (k3 = i1 - l & 7; k3-- > 0;) {
                int l8;
                if ((l8 = ai1[(j & 0xfc0) + (i >> 6)] >>> i8) != 0) {
                    ai[k] = l8;
                }
                k++;
                i += i7;
                j += k7;
            }

            return;
        }
        int j4 = 0;
        int l4 = 0;
        int l6 = l - intCenterX;
        l1 += (k2 >> 3) * l6;
        i2 += (l2 >> 3) * l6;
        j2 += (i3 >> 3) * l6;
        int l5 = j2 >> 14;
        if (l5 != 0) {
            i = l1 / l5;
            j = i2 / l5;
            if (i < 0) {
                i = 0;
            } else if (i > 16256) {
                i = 16256;
            }
        }
        l1 += k2;
        i2 += l2;
        j2 += i3;
        l5 = j2 >> 14;
        if (l5 != 0) {
            j4 = l1 / l5;
            l4 = i2 / l5;
            if (j4 < 7) {
                j4 = 7;
            } else if (j4 > 16256) {
                j4 = 16256;
            }
        }
        int j7 = j4 - i >> 3;
        int l7 = l4 - j >> 3;
        i += j1 & 0x600000;
        int j8 = j1 >> 23;
        if (opaque) {
            while (k3-- > 0) {
                ai[k++] = ai1[(j & 0x3f80) + (i >> 7)] >>> j8;
                i += j7;
                j += l7;
                ai[k++] = ai1[(j & 0x3f80) + (i >> 7)] >>> j8;
                i += j7;
                j += l7;
                ai[k++] = ai1[(j & 0x3f80) + (i >> 7)] >>> j8;
                i += j7;
                j += l7;
                ai[k++] = ai1[(j & 0x3f80) + (i >> 7)] >>> j8;
                i += j7;
                j += l7;
                ai[k++] = ai1[(j & 0x3f80) + (i >> 7)] >>> j8;
                i += j7;
                j += l7;
                ai[k++] = ai1[(j & 0x3f80) + (i >> 7)] >>> j8;
                i += j7;
                j += l7;
                ai[k++] = ai1[(j & 0x3f80) + (i >> 7)] >>> j8;
                i += j7;
                j += l7;
                ai[k++] = ai1[(j & 0x3f80) + (i >> 7)] >>> j8;
                i = j4;
                j = l4;
                l1 += k2;
                i2 += l2;
                j2 += i3;
                int i6 = j2 >> 14;
                if (i6 != 0) {
                    j4 = l1 / i6;
                    l4 = i2 / i6;
                    if (j4 < 7) {
                        j4 = 7;
                    } else if (j4 > 16256) {
                        j4 = 16256;
                    }
                }
                j7 = j4 - i >> 3;
                l7 = l4 - j >> 3;
                j1 += j3;
                i += j1 & 0x600000;
                j8 = j1 >> 23;
            }
            for (k3 = i1 - l & 7; k3-- > 0;) {
                ai[k++] = ai1[(j & 0x3f80) + (i >> 7)] >>> j8;
                i += j7;
                j += l7;
            }

            return;
        }
        while (k3-- > 0) {
            int i9;
            if ((i9 = ai1[(j & 0x3f80) + (i >> 7)] >>> j8) != 0) {
                ai[k] = i9;
            }
            k++;
            i += j7;
            j += l7;
            if ((i9 = ai1[(j & 0x3f80) + (i >> 7)] >>> j8) != 0) {
                ai[k] = i9;
            }
            k++;
            i += j7;
            j += l7;
            if ((i9 = ai1[(j & 0x3f80) + (i >> 7)] >>> j8) != 0) {
                ai[k] = i9;
            }
            k++;
            i += j7;
            j += l7;
            if ((i9 = ai1[(j & 0x3f80) + (i >> 7)] >>> j8) != 0) {
                ai[k] = i9;
            }
            k++;
            i += j7;
            j += l7;
            if ((i9 = ai1[(j & 0x3f80) + (i >> 7)] >>> j8) != 0) {
                ai[k] = i9;
            }
            k++;
            i += j7;
            j += l7;
            if ((i9 = ai1[(j & 0x3f80) + (i >> 7)] >>> j8) != 0) {
                ai[k] = i9;
            }
            k++;
            i += j7;
            j += l7;
            if ((i9 = ai1[(j & 0x3f80) + (i >> 7)] >>> j8) != 0) {
                ai[k] = i9;
            }
            k++;
            i += j7;
            j += l7;
            if ((i9 = ai1[(j & 0x3f80) + (i >> 7)] >>> j8) != 0) {
                ai[k] = i9;
            }
            k++;
            i = j4;
            j = l4;
            l1 += k2;
            i2 += l2;
            j2 += i3;
            int j6 = j2 >> 14;
            if (j6 != 0) {
                j4 = l1 / j6;
                l4 = i2 / j6;
                if (j4 < 7) {
                    j4 = 7;
                } else if (j4 > 16256) {
                    j4 = 16256;
                }
            }
            j7 = j4 - i >> 3;
            l7 = l4 - j >> 3;
            j1 += j3;
            i += j1 & 0x600000;
            j8 = j1 >> 23;
        }
        for (int l3 = i1 - l & 7; l3-- > 0;) {
            int j9;
            if ((j9 = ai1[(j & 0x3f80) + (i >> 7)] >>> j8) != 0) {
                ai[k] = j9;
            }
            k++;
            i += j7;
            j += l7;
        }

    }
}
