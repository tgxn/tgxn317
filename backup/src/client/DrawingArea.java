package client;

public class DrawingArea extends NodeSub {
    
    public static int pixels[];
    public static int width;
    public static int height;
    public static int topY;
    public static int bottomY;
    public static int topX;
    public static int bottomX;
    public static int centerX;
    public static int centerY;
    public static int center;
    
    public DrawingArea() {
    }
    
    public static void initDrawingArea(int areaHeight, int areaWidth, int areaPixels[]) {
        pixels = areaPixels;
        width = areaWidth;
        height = areaHeight;
        createDrawingArea(0, 0, areaWidth, areaHeight);
    }

    public static void defaultDrawingAreaSize() {
        topX = 0;
        topY = 0;
        bottomX = width;
        bottomY = height;
        centerX = bottomX;
        centerY = bottomX / 2;
    }

    public static void createDrawingArea(int drawX, int drawY, int areaWidth, int areaHeight) {
        if (drawX < 0)
            drawX = 0;
        if (drawY < 0)
            drawY = 0;
        if (areaWidth > width)
            areaWidth = width;
        if (areaHeight > height)
            areaHeight = height;
        topX = drawX;
        topY = drawY;
        bottomX = areaWidth;
        bottomY = areaHeight;
        centerX = bottomX;
        centerY = bottomX / 2;
        center = bottomY / 2;
    }

    public static void clearDrawingArea() {
        int i = width * height;
        for (int j = 0; j < i; j++) {
            pixels[j] = 0;
        }
    }

    public static void fillBoxWithOpacity(int drawX, int drawY, int boxWidth, int boxHeight, int color, int opacity) {
        if (drawX < topX) {
            boxWidth -= topX - drawX;
            drawX = topX;
        }
        if (drawY < topY) {
            boxHeight -= topY - drawY;
            drawY = topY;
        }
        if (drawX + boxWidth > bottomX) {
            boxWidth = bottomX - drawX;
        }
        if (drawY + boxHeight > bottomY) {
            boxHeight = bottomY - drawY;
        }
        int l1 = 256 - opacity;
        int i2 = (color >> 16 & 0xff) * opacity;
        int j2 = (color >> 8 & 0xff) * opacity;
        int k2 = (color & 0xff) * opacity;
        int k3 = width - boxWidth;
        int l3 = drawX + drawY * width;
        for (int i4 = 0; i4 < boxHeight; i4++) {
            for (int j4 = -boxWidth; j4 < 0; j4++) {
                int l2 = (pixels[l3] >> 16 & 0xff) * l1;
                int i3 = (pixels[l3] >> 8 & 0xff) * l1;
                int j3 = (pixels[l3] & 0xff) * l1;
                int k4 = ((i2 + l2 >> 8) << 16) + ((j2 + i3 >> 8) << 8) + (k2 + j3 >> 8);
                pixels[l3++] = k4;
            }

            l3 += k3;
        }
    }
    
    public static void fillBox(int drawX, int drawY, int boxWidth, int boxHeight, int color) {
        if (drawX < topX) {
            boxWidth -= topX - drawX;
            drawX = topX;
        }
        if (drawY < topY) {
            boxHeight -= topY - drawY;
            drawY = topY;
        }
        if (drawX + boxWidth > bottomX) {
            boxWidth = bottomX - drawX;
        }
        if (drawY + boxHeight > bottomY) {
            boxHeight = bottomY - drawY;
        }
        int k1 = width - boxWidth;
        int l1 = drawX + drawY * width;
        for (int i2 = -boxHeight; i2 < 0; i2++) {
            for (int j2 = -boxWidth; j2 < 0; j2++) {
                pixels[l1++] = color;
            }
            l1 += k1;
        }
    }

    public static void drawBox(int drawX, int drawY, int boxWidth, int boxHeight, int boxColor) {
        drawHorizontalLine(drawX, drawY, boxWidth, boxColor);
        drawHorizontalLine(drawX, (drawY + boxHeight) - 1, boxWidth, boxColor);
        drawVerticalLine(drawX, drawY, boxHeight, boxColor);
        drawVerticalLine((drawX + boxWidth) - 1, drawY, boxHeight, boxColor);
    }

    public static void drawBoxWithOpacity(int drawY, int boxHeight, int boxOpacity, int boxColor, int boxWidth, int drawX) {
        drawHorizontalLineWithOpacity(drawX, drawY, boxWidth, boxColor, boxOpacity);
        drawHorizontalLineWithOpacity(drawX, (drawY + boxHeight) - 1, boxWidth, boxColor, boxOpacity);
        if (boxHeight >= 3) {
            drawVerticalLineWithOpacity(drawX, drawY + 1, boxHeight - 2, boxColor, boxOpacity);
            drawVerticalLineWithOpacity((drawX + boxWidth) - 1, drawY + 1, boxHeight - 2, boxColor, boxOpacity);
        }
    }

    public static void drawHorizontalLine(int drawX, int drawY, int lineWidth, int color) {
        if (drawY < topY || drawY >= bottomY) {
            return;
        }
        if (drawX < topX) {
            lineWidth -= topX - drawX;
            drawX = topX;
        }
        if (drawX + lineWidth > bottomX) {
            lineWidth = bottomX - drawX;
        }
        int i1 = drawX + drawY * width;
        for (int j1 = 0; j1 < lineWidth; j1++) {
            pixels[i1 + j1] = color;
        }
    }

    public static void drawHorizontalLineWithOpacity(int drawX, int drawY, int lineWidth, int color, int opacity) {
        if (drawY < topY || drawY >= bottomY) {
            return;
        }
        if (drawX < topX) {
            lineWidth -= topX - drawX;
            drawX = topX;
        }
        if (drawX + lineWidth > bottomX) {
            lineWidth = bottomX - drawX;
        }
        int j1 = 256 - opacity;
        int k1 = (color >> 16 & 0xff) * opacity;
        int l1 = (color >> 8 & 0xff) * opacity;
        int i2 = (color & 0xff) * opacity;
        int i3 = drawX + drawY * width;
        for (int j3 = 0; j3 < lineWidth; j3++) {
            int j2 = (pixels[i3] >> 16 & 0xff) * j1;
            int k2 = (pixels[i3] >> 8 & 0xff) * j1;
            int l2 = (pixels[i3] & 0xff) * j1;
            int k3 = ((k1 + j2 >> 8) << 16) + ((l1 + k2 >> 8) << 8) + (i2 + l2 >> 8);
            pixels[i3++] = k3;
        }
    }

    public static void drawVerticalLine(int drawX, int drawY, int lineHeight, int color) {
        if (drawX < topX || drawX >= bottomX) {
            return;
        }
        if (drawY < topY) {
            lineHeight -= topY - drawY;
            drawY = topY;
        }
        if (drawY + lineHeight > bottomY) {
            lineHeight = bottomY - drawY;
        }
        int j1 = drawX + drawY * width;
        for (int k1 = 0; k1 < lineHeight; k1++) {
            pixels[j1 + k1 * width] = color;
        }
    }

    public static void drawVerticalLineWithOpacity(int drawX, int drawY, int lineHeight, int color, int opacity) {
        if (drawX < topX || drawX >= bottomX) {
            return;
        }
        if (drawY < topY) {
            lineHeight -= topY - drawY;
            drawY = topY;
        }
        if (drawY + lineHeight > bottomY) {
            lineHeight = bottomY - drawY;
        }
        int j1 = 256 - opacity;
        int k1 = (color >> 16 & 0xff) * opacity;
        int l1 = (color >> 8 & 0xff) * opacity;
        int i2 = (color & 0xff) * opacity;
        int i3 = drawX + drawY * width;
        for (int j3 = 0; j3 < lineHeight; j3++) {
            int j2 = (pixels[i3] >> 16 & 0xff) * j1;
            int k2 = (pixels[i3] >> 8 & 0xff) * j1;
            int l2 = (pixels[i3] & 0xff) * j1;
            int k3 = ((k1 + j2 >> 8) << 16) + ((l1 + k2 >> 8) << 8) + (i2 + l2 >> 8);
            pixels[i3] = k3;
            i3 += width;
        }

    }
}
