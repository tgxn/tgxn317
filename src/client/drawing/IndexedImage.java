package client.drawing;

// Fully Renamed.

import client.data.Stream;
import client.data.JagexArchive;

public final class IndexedImage extends DrawingArea {
    
    public byte[] imgPixels;
    public int[] imgPalette;
    public int imgWidth;
    public int imgHeight;
    public int xDrawOffset;
    public int yDrawOffset;
    public int libWidth;
    private int libHeight;
    
    public IndexedImage(JagexArchive jagexArchive, String datToLoad, int i) {
        Stream dataStream = new Stream(jagexArchive.getDataForName(datToLoad + ".dat"));
        Stream indexStream = new Stream(jagexArchive.getDataForName("index.dat"));
        indexStream.currentOffset = dataStream.readUnsignedWord();
        libWidth = indexStream.readUnsignedWord();
        libHeight = indexStream.readUnsignedWord();
        int paletteLength = indexStream.readUnsignedByte();
        imgPalette = new int[paletteLength];
        for (int ptr = 0; ptr < paletteLength - 1; ptr++) {
            imgPalette[ptr + 1] = indexStream.read3Bytes();
        }
        for (int ptr = 0; ptr < i; ptr++) {
            indexStream.currentOffset += 2;
            dataStream.currentOffset += indexStream.readUnsignedWord() * indexStream.readUnsignedWord();
            indexStream.currentOffset++;
        }
        xDrawOffset = indexStream.readUnsignedByte();
        yDrawOffset = indexStream.readUnsignedByte();
        imgWidth = indexStream.readUnsignedWord();
        imgHeight = indexStream.readUnsignedWord();
        int imgType = indexStream.readUnsignedByte();
        int pixelsLength = imgWidth * imgHeight;
        imgPixels = new byte[pixelsLength];
        if (imgType == 0) {
            for (int ptr = 0; ptr < pixelsLength; ptr++) {
                imgPixels[ptr] = dataStream.readSignedByte();
            }
            return;
        }
        if (imgType == 1) {
            for (int l1 = 0; l1 < imgWidth; l1++) {
                for (int i2 = 0; i2 < imgHeight; i2++) {
                    imgPixels[l1 + i2 * imgWidth] = dataStream.readSignedByte();
                }
            }
        }
    }

    public void loadSize128Image() {
        libWidth /= 2;
        libHeight /= 2;
        byte pixelsBuffer[] = new byte[libWidth * libHeight];
        int i = 0;
        for (int j = 0; j < imgHeight; j++) {
            for (int k = 0; k < imgWidth; k++) {
                pixelsBuffer[(k + xDrawOffset >> 1) + (j + yDrawOffset >> 1) * libWidth] = imgPixels[i++];
            }
        }
        imgPixels = pixelsBuffer;
        imgWidth = libWidth;
        imgHeight = libHeight;
        xDrawOffset = 0;
        yDrawOffset = 0;
    }

    public void loadSize64Image() {
        if (imgWidth == libWidth && imgHeight == libHeight) {
            return;
        }
        byte pixelsBuffer[] = new byte[libWidth * libHeight];
        int i = 0;
        for (int j = 0; j < imgHeight; j++) {
            for (int k = 0; k < imgWidth; k++) {
                pixelsBuffer[k + xDrawOffset + (j + yDrawOffset) * libWidth] = imgPixels[i++];
            }
        }
        imgPixels = pixelsBuffer;
        imgWidth = libWidth;
        imgHeight = libHeight;
        xDrawOffset = 0;
        yDrawOffset = 0;
    }

    public void flipHorizontal() {
        byte pixelsBuffer[] = new byte[imgWidth * imgHeight];
        int j = 0;
        for (int k = 0; k < imgHeight; k++) {
            for (int l = imgWidth - 1; l >= 0; l--) {
                pixelsBuffer[j++] = imgPixels[l + k * imgWidth];
            }
        }
        imgPixels = pixelsBuffer;
        xDrawOffset = libWidth - imgWidth - xDrawOffset;
    }

    public void flipVertical() {
        byte pixelsBuffer[] = new byte[imgWidth * imgHeight];
        int i = 0;
        for (int j = imgHeight - 1; j >= 0; j--) {
            for (int k = 0; k < imgWidth; k++) {
                pixelsBuffer[i++] = imgPixels[k + j * imgWidth];
            }
        }
        imgPixels = pixelsBuffer;
        yDrawOffset = libHeight - imgHeight - yDrawOffset;
    }

    public void shiftColours(int i, int j, int k) {
        for (int i1 = 0; i1 < imgPalette.length; i1++) {
            int j1 = imgPalette[i1] >> 16 & 0xff;
            j1 += i;
            if (j1 < 0) {
                j1 = 0;
            } else if (j1 > 255) {
                j1 = 255;
            }
            int k1 = imgPalette[i1] >> 8 & 0xff;
            k1 += j;
            if (k1 < 0) {
                k1 = 0;
            } else if (k1 > 255) {
                k1 = 255;
            }
            int l1 = imgPalette[i1] & 0xff;
            l1 += k;
            if (l1 < 0) {
                l1 = 0;
            } else if (l1 > 255) {
                l1 = 255;
            }
            imgPalette[i1] = (j1 << 16) + (k1 << 8) + l1;
        }
    }

    public void drawImage(int x, int y) {
        x += xDrawOffset;
        y += yDrawOffset;
        int l = x + y * DrawingArea.width;
        int i1 = 0;
        int j1 = imgHeight;
        int k1 = imgWidth;
        int l1 = DrawingArea.width - k1;
        int i2 = 0;
        if (y < DrawingArea.topY) {
            int j2 = DrawingArea.topY - y;
            j1 -= j2;
            y = DrawingArea.topY;
            i1 += j2 * k1;
            l += j2 * DrawingArea.width;
        }
        if (y + j1 > DrawingArea.bottomY) {
            j1 -= (y + j1) - DrawingArea.bottomY;
        }
        if (x < DrawingArea.topX) {
            int k2 = DrawingArea.topX - x;
            k1 -= k2;
            x = DrawingArea.topX;
            i1 += k2;
            l += k2;
            i2 += k2;
            l1 += k2;
        }
        if (x + k1 > DrawingArea.bottomX) {
            int l2 = (x + k1) - DrawingArea.bottomX;
            k1 -= l2;
            i2 += l2;
            l1 += l2;
        }
        if (k1 <= 0 || j1 <= 0) {
            return;
        } else {
            arraycopy(j1, DrawingArea.pixels, imgPixels, l1, l, k1, i1, imgPalette, i2);
            return;
        }
    }

    private void arraycopy(int i, int ai[], byte abyte0[], int j, int k, int l, int i1, int ai1[], int j1) {
        int k1 = -(l >> 2);
        l = -(l & 3);
        for (int l1 = -i; l1 < 0; l1++) {
            for (int i2 = k1; i2 < 0; i2++) {
                byte byte1 = abyte0[i1++];
                if (byte1 != 0) {
                    ai[k++] = ai1[byte1 & 0xff];
                } else {
                    k++;
                }
                byte1 = abyte0[i1++];
                if (byte1 != 0) {
                    ai[k++] = ai1[byte1 & 0xff];
                } else {
                    k++;
                }
                byte1 = abyte0[i1++];
                if (byte1 != 0) {
                    ai[k++] = ai1[byte1 & 0xff];
                } else {
                    k++;
                }
                byte1 = abyte0[i1++];
                if (byte1 != 0) {
                    ai[k++] = ai1[byte1 & 0xff];
                } else {
                    k++;
                }
            }

            for (int j2 = l; j2 < 0; j2++) {
                byte byte2 = abyte0[i1++];
                if (byte2 != 0) {
                    ai[k++] = ai1[byte2 & 0xff];
                } else {
                    k++;
                }
            }

            k += j;
            i1 += j1;
        }

    }
}
