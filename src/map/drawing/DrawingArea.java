package map.drawing;

public class DrawingArea {

    public static int pixels[];
    public static int width;
    public static int height;
    public static int topY = 0;
    public static int bottomY = 0;
    public static int topX = 0;
    public static int bottomX = 0;
    public static int centerX;
    public static int centerY;
    
    public DrawingArea() {
    }
    
    public static void method27(int i, int j, int k, int l) {
        if (j < topY || j >= bottomY) {
            return;
        }
        if (i < topX) {
            k -= topX - i;
            i = topX;
        }
        if (i + k > bottomX) {
            k = bottomX - i;
        }
        int i1 = i + j * width;
        for (int j1 = 0; j1 < k; j1++) {
            pixels[i1 + j1] = l;
        }
    }

    public static void method28(int i, int j, int k, int l) {
        if (i < topX || i >= bottomX) {
            return;
        }
        if (j < topY) {
            k -= topY - j;
            j = topY;
        }
        if (j + k > bottomY) {
            k = bottomY - j;
        }
        int i1 = i + j * width;
        for (int j1 = 0; j1 < k; j1++) {
            pixels[i1 + j1 * width] = l;
        }
    }

    public static void setDrawingArea(int i, int j, int k, int l) {
        if (i < 0) {
            i = 0;
        }
        if (j < 0) {
            j = 0;
        }
        if (k > width) {
            k = width;
        }
        if (l > height) {
            l = height;
        }
        topX = i;
        topY = j;
        bottomX = k;
        bottomY = l;
        centerX = bottomX - 1;
        centerY = bottomX / 2;
    }

    public static void method30(int i, int j, int k, int l, int i1, int j1) {
        if (i < topX) {
            k -= topX - i;
            i = topX;
        }
        if (j < topY) {
            l -= topY - j;
            j = topY;
        }
        if (i + k > bottomX) {
            k = bottomX - i;
        }
        if (j + l > bottomY) {
            l = bottomY - j;
        }
        int k1 = 256 - j1;
        int l1 = (i1 >> 16 & 0xff) * j1;
        int i2 = (i1 >> 8 & 0xff) * j1;
        int j2 = (i1 & 0xff) * j1;
        int j3 = width - k;
        int k3 = i + j * width;
        for (int l3 = 0; l3 < l; l3++) {
            for (int i4 = -k; i4 < 0; i4++) {
                int k2 = (pixels[k3] >> 16 & 0xff) * k1;
                int l2 = (pixels[k3] >> 8 & 0xff) * k1;
                int i3 = (pixels[k3] & 0xff) * k1;
                int j4 = ((l1 + k2 >> 8) << 16) + ((i2 + l2 >> 8) << 8) + (j2 + i3 >> 8);
                pixels[k3++] = j4;
            }
            k3 += j3;
        }
    }

    public static void fillPixels(int i, int j, int k, int l, int i1) {
        method27(i, j, k, i1);
        method27(i, (j + l) - 1, k, i1);
        method28(i, j, l, i1);
        method28((i + k) - 1, j, l, i1);
    }

    public static void initializeDrawingArea(int ai[], int w, int h) {
        pixels = ai;
        width = w;
        height = h;
        setDrawingArea(0, 0, w, h);
    }

    public static void method33(int i, int j, int k, int l, int i1) { //?
        if (i < topX) {
            k -= topX - i;
            i = topX;
        }
        if (j < topY) {
            l -= topY - j;
            j = topY;
        }
        if (i + k > bottomX) {
            k = bottomX - i;
        }
        if (j + l > bottomY) {
            l = bottomY - j;
        }
        int j1 = width - k;
        int k1 = i + j * width;
        for (int l1 = -l; l1 < 0; l1++) {
            for (int i2 = -k; i2 < 0; i2++) {
                pixels[k1++] = i1;
            }
            k1 += j1;
        }
    }

    public static void setAllPixelsToZero() {
        int i = width * height;
        for (int j = 0; j < i; j++) {
            pixels[j] = 0;
        }

    }

    public static void drawCircle(int i, int j, int k, int l, int i1) {
        int j1 = 256 - i1;
        int k1 = (l >> 16 & 0xff) * i1;
        int l1 = (l >> 8 & 0xff) * i1;
        int i2 = (l & 0xff) * i1;
        int i3 = j - k;
        if (i3 < 0) {
            i3 = 0;
        }
        int j3 = j + k;
        if (j3 >= height) {
            j3 = height - 1;
        }
        for (int k3 = i3; k3 <= j3; k3++) {
            int l3 = k3 - j;
            int i4 = (int) Math.sqrt(k * k - l3 * l3);
            int j4 = i - i4;
            if (j4 < 0) {
                j4 = 0;
            }
            int k4 = i + i4;
            if (k4 >= width) {
                k4 = width - 1;
            }
            int l4 = j4 + k3 * width;
            for (int i5 = j4; i5 <= k4; i5++) {
                int j2 = (pixels[l4] >> 16 & 0xff) * j1;
                int k2 = (pixels[l4] >> 8 & 0xff) * j1;
                int l2 = (pixels[l4] & 0xff) * j1;
                int j5 = ((k1 + j2 >> 8) << 16) + ((l1 + k2 >> 8) << 8) + (i2 + l2 >> 8);
                pixels[l4++] = j5;
            }
        }
    }
}