package client;

import java.util.Random;

public final class TextDrawingArea extends DrawingArea {

    public int basicCharSetHeight;
    private final byte characterPixels[][];
    private final int characterWidth[];
    private final int characterHeight[];
    private final int charXDrawOffsets[];
    private final int charYDrawOffsets[];
    private final int characterScreenWidths[];
    private final Random myRandom;
    private boolean strikeThrough;

    public TextDrawingArea(boolean flag, String string, JagexArchive streamLoader) {
        characterPixels = new byte[256][];
        characterWidth = new int[256];
        characterHeight = new int[256];
        charXDrawOffsets = new int[256];
        charYDrawOffsets = new int[256];
        characterScreenWidths = new int[256];
        myRandom = new Random();
        strikeThrough = false;

        Packet fontStream = new Packet(streamLoader.getDataForName(string + ".dat"));
        Packet indexStream = new Packet(streamLoader.getDataForName("index.dat"));

        indexStream.currentOffset = fontStream.readUnsignedWord() + 4;

        int k = indexStream.readUnsignedByte();
        if (k > 0) {
            indexStream.currentOffset += 3 * (k - 1);
        }
        for (int characterID = 0; characterID < 256; characterID++) {
            int j = characterID;
            charXDrawOffsets[characterID] = indexStream.readUnsignedByte();
            charYDrawOffsets[characterID] = indexStream.readUnsignedByte();
            int charWidth = characterWidth[characterID] = indexStream.readUnsignedWord();
            int charHeight = characterHeight[characterID] = indexStream.readUnsignedWord();
            int mode = indexStream.readUnsignedByte();
            int size = charWidth * charHeight;
            characterPixels[characterID] = new byte[size];
            if (mode == 0) {
                for (int address = 0; address < size; address++) {
                    characterPixels[characterID][address] = fontStream.readSignedByte();
                }
            } else if (mode == 1) {
                for (int X = 0; X < charWidth; X++) {
                    for (int Y = 0; Y < charHeight; Y++) {
                        characterPixels[characterID][X + Y * charWidth] = fontStream.readSignedByte();
                    }
                }
            }
            if (charHeight > basicCharSetHeight && characterID < 128) {
                basicCharSetHeight = charHeight;
            }
            charXDrawOffsets[characterID] = 1;
            characterScreenWidths[characterID] = charWidth + 2;
            int k2 = 0;
            for (int i3 = charHeight / 7; i3 < charHeight; i3++) {
                k2 += characterPixels[characterID][i3 * charWidth];
            }
            if (k2 <= charHeight / 7) {
                characterScreenWidths[characterID]--;
                charXDrawOffsets[characterID] = 0;
            }
            k2 = 0;
            for (int j3 = charHeight / 7; j3 < charHeight; j3++) {
                k2 += characterPixels[characterID][(charWidth - 1) + j3 * charWidth];
            }
            if (k2 <= charHeight / 7) {
                characterScreenWidths[characterID]--;
            }
        }
        if (flag) {
            characterScreenWidths[32] = characterScreenWidths[73];
            return;
        } else {
            characterScreenWidths[32] = characterScreenWidths[105];
            return;
        }
    }

    public void drawTextRA(String drawString, int drawX, int drawY, int color) {
        drawString(drawString, drawX - getTextWidth(drawString), color, drawY);
    }

    public void drawCenterText(String drawString, int drawX, int drawY, int color) {
        drawString(drawString, drawX - getTextWidth(drawString) / 2, drawY, color);
    }

    public void drawCenterShadeEffectText(String string, int drawX, int drawY, int color, boolean shadow) {
        drawTextWithShadowAndEffects(string, drawX - getTextWidthWithEffects(string) / 2, drawY, color, shadow);
    }

    public int getTextWidthWithEffects(String string) {
        if (string == null) {
            return 0;
        }
        int textWidth = 0;
        for (int charID = 0; charID < string.length(); charID++) {
            if (string.charAt(charID) == '@' && charID + 4 < string.length() && string.charAt(charID + 4) == '@') {
                charID += 4;
            } else {
                textWidth += characterScreenWidths[string.charAt(charID)];
            }
        }
        return textWidth;
    }

    public int getTextWidth(String string) {
        if (string == null) {
            return 0;
        }
        int textWidth = 0;
        for (int charID = 0; charID < string.length(); charID++) {
            textWidth += characterScreenWidths[string.charAt(charID)];
        }
        return textWidth;
    }

    public void drawString(String drawString, int drawX, int drawY, int color) {
        if (drawString == null) {
            return;
        }
        drawY -= basicCharSetHeight;
        for (int charID = 0; charID < drawString.length(); charID++) {
            char c = drawString.charAt(charID);
            if (c != ' ') {
                drawCharacter(characterPixels[c], drawX + charXDrawOffsets[c], drawY + charYDrawOffsets[c], characterWidth[c], characterHeight[c], color);
            }
            drawX += characterScreenWidths[c];
        }
    }

    public void drawCenterTextMoveY(String string, int drawX, int drawY, int color, int waveAmount) {
        if (string == null) {
            return;
        }
        drawX -= getTextWidth(string) / 2;
        drawY -= basicCharSetHeight;
        for (int charID = 0; charID < string.length(); charID++) {
            char character = string.charAt(charID);
            if (character != ' ') {
                drawCharacter(characterPixels[character], drawX + charXDrawOffsets[character], drawY + charYDrawOffsets[character] + (int) (Math.sin((double) charID / 2D + (double) color / 5D) * 5D), characterWidth[character], characterHeight[character], waveAmount);
            }
            drawX += characterScreenWidths[character];
        }
    }

    public void drawCenterTextMoveXY(String string, int drawX, int drawY, int color, int waveAmount) {
        if (string == null) {
            return;
        }
        drawX -= getTextWidth(string) / 2;
        drawY -= basicCharSetHeight;
        for (int i1 = 0; i1 < string.length(); i1++) {
            char c = string.charAt(i1);
            if (c != ' ') {
                drawCharacter(characterPixels[c], drawX + charXDrawOffsets[c] + (int) (Math.sin((double) i1 / 5D + (double) waveAmount / 5D) * 5D), drawY + charYDrawOffsets[c] + (int) (Math.sin((double) i1 / 3D + (double) waveAmount / 5D) * 5D), characterWidth[c], characterHeight[c], color);
            }
            drawX += characterScreenWidths[c];
        }
    }

    public void drawTextMoveY2(String string, int drawX, int drawY, int color, int waveSpeed, int waveAmount) {
        if (string == null) {
            return;
        }
        double myDouble = 7D - (double) waveSpeed / 8D;
        if (myDouble < 0.0D) {
            myDouble = 0.0D;
        }
        drawX -= getTextWidth(string) / 2;
        drawY -= basicCharSetHeight;
        for (int charID = 0; charID < string.length(); charID++) {
            char character = string.charAt(charID);
            if (character != ' ') {
                drawCharacter(characterPixels[character], drawX + charXDrawOffsets[character], drawY + charYDrawOffsets[character] + (int) (Math.sin((double) charID / 1.5D + (double) waveAmount) * myDouble), characterWidth[character], characterHeight[character], color);
            }
            drawX += characterScreenWidths[character];
        }
    }

    public void drawTextWithShadowAndEffects(String string, int drawX, int drawY, int color, boolean shaded) {
        strikeThrough = false;
        int startX = drawX;
        if (string == null) {
            return;
        }
        drawY -= basicCharSetHeight;
        for (int charID = 0; charID < string.length(); charID++) {
            if (string.charAt(charID) == '@' && charID + 4 < string.length() && string.charAt(charID + 4) == '@') {
                int newColor = getColorByName(string.substring(charID + 1, charID + 4));
                if (newColor != -1) {
                    color = newColor;
                }
                charID += 4;
            } else {
                char c = string.charAt(charID);
                if (c != ' ') {
                    if (shaded) {
                        drawCharacter(characterPixels[c], drawX + charXDrawOffsets[c] + 1, drawY + charYDrawOffsets[c] + 1, characterWidth[c], characterHeight[c], 0);
                    }
                    drawCharacter(characterPixels[c], drawX + charXDrawOffsets[c], drawY + charYDrawOffsets[c], characterWidth[c], characterHeight[c], color);
                }
                drawX += characterScreenWidths[c];
            }
        }
        if (strikeThrough) {
            DrawingArea.drawHorizontalLine(startX, drawY + (int) ((double) basicCharSetHeight * 0.69999999999999996D), drawX - startX, 0x800000);
        }
    }

    public void drawShadowedText(String string, int drawX, int drawY, int drawColor, int seed) {
        if (string == null) {
            return;
        }
        myRandom.setSeed(seed);
        int j1 = 192 + (myRandom.nextInt() & 0x1f);
        drawY -= basicCharSetHeight;
        for (int charID = 0; charID < string.length(); charID++) {
            if (string.charAt(charID) == '@' && charID + 4 < string.length() && string.charAt(charID + 4) == '@') {
                int color = getColorByName(string.substring(charID + 1, charID + 4));
                if (color != -1) {
                    drawColor = color;
                }
                charID += 4;
            } else {
                char c = string.charAt(charID);
                if (c != ' ') {
                    drawCharacterWithOpacity(192, drawX + charXDrawOffsets[c] + 1, characterPixels[c], characterWidth[c], drawY + charYDrawOffsets[c] + 1, characterHeight[c], 0);
                    drawCharacterWithOpacity(j1, drawX + charXDrawOffsets[c], characterPixels[c], characterWidth[c], drawY + charYDrawOffsets[c], characterHeight[c], drawColor);
                }
                drawX += characterScreenWidths[c];
                if ((myRandom.nextInt() & 3) == 0) {
                    drawX++;
                }
            }
        }
    }

    public int getColorByName(String s) {
        if (s.equals("red")) {
            return 0xff0000;
        }
        if (s.equals("gre")) {
            return 65280;
        }
        if (s.equals("blu")) {
            return 255;
        }
        if (s.equals("yel")) {
            return 0xffff00;
        }
        if (s.equals("cya")) {
            return 65535;
        }
        if (s.equals("mag")) {
            return 0xff00ff;
        }
        if (s.equals("whi")) {
            return 0xffffff;
        }
        if (s.equals("bla")) {
            return 0;
        }
        if (s.equals("lre")) {
            return 0xff9040;
        }
        if (s.equals("dre")) {
            return 0x800000;
        }
        if (s.equals("dbl")) {
            return 128;
        }
        if (s.equals("or1")) {
            return 0xffb000;
        }
        if (s.equals("or2")) {
            return 0xff7000;
        }
        if (s.equals("or3")) {
            return 0xff3000;
        }
        if (s.equals("gr1")) {
            return 0xc0ff00;
        }
        if (s.equals("gr2")) {
            return 0x80ff00;
        }
        if (s.equals("gr3")) {
            return 0x40ff00;
        }
        if (s.equals("xxx")) {
            return 0x33CCFF;
        }
        if (s.equals("str")) {
            strikeThrough = true;
        }
        if (s.equals("end")) {
            strikeThrough = false;
        }
        return -1;
    }

    private void drawCharacter(byte charPixels[], int drawX, int drawY, int Width, int Height, int color) {
        int j1 = drawX + drawY * DrawingArea.width;
        int k1 = DrawingArea.width - Width;
        int l1 = 0;
        int i2 = 0;
        if (drawY < DrawingArea.topY) {
            int j2 = DrawingArea.topY - drawY;
            Height -= j2;
            drawY = DrawingArea.topY;
            i2 += j2 * Width;
            j1 += j2 * DrawingArea.width;
        }
        if (drawY + Height >= DrawingArea.bottomY) {
            Height -= ((drawY + Height) - DrawingArea.bottomY);
        }
        if (drawX < DrawingArea.topX) {
            int k2 = DrawingArea.topX - drawX;
            Width -= k2;
            drawX = DrawingArea.topX;
            i2 += k2;
            j1 += k2;
            l1 += k2;
            k1 += k2;
        }
        if (drawX + Width >= DrawingArea.bottomX) {
            int l2 = ((drawX + Width) - DrawingArea.bottomX);
            Width -= l2;
            l1 += l2;
            k1 += l2;
        }
        if (Width <= 0 || Height <= 0) {
            return;
        } else {
            createCharacterPixels(DrawingArea.pixels, charPixels, color, i2, j1, Width, Height, k1, l1);
            return;
        }
    }

    private void createCharacterPixels(int drawingAreaPixels[], byte charPixels[], int color, int j, int k, int l, int Width, int Height, int k1) {
        int l1 = -(l >> 2);
        l = -(l & 3);
        for (int i2 = -Width; i2 < 0; i2++) {
            for (int j2 = l1; j2 < 0; j2++) {
                if (charPixels[j++] != 0) {
                    drawingAreaPixels[k++] = color;
                } else {
                    k++;
                }
                if (charPixels[j++] != 0) {
                    drawingAreaPixels[k++] = color;
                } else {
                    k++;
                }
                if (charPixels[j++] != 0) {
                    drawingAreaPixels[k++] = color;
                } else {
                    k++;
                }
                if (charPixels[j++] != 0) {
                    drawingAreaPixels[k++] = color;
                } else {
                    k++;
                }
            }

            for (int k2 = l; k2 < 0; k2++) {
                if (charPixels[j++] != 0) {
                    drawingAreaPixels[k++] = color;
                } else {
                    k++;
                }
            }

            k += Height;
            j += k1;
        }
    }

    private void drawCharacterWithOpacity(int opacity, int drawX, byte chatPixels[], int Width, int drawY, int Height, int color) {
        int k1 = drawX + drawY * DrawingArea.width;
        int l1 = DrawingArea.width - Width;
        int i2 = 0;
        int j2 = 0;
        if (drawY < DrawingArea.topY) {
            int k2 = DrawingArea.topY - drawY;
            Height -= k2;
            drawY = DrawingArea.topY;
            j2 += k2 * Width;
            k1 += k2 * DrawingArea.width;
        }
        if (drawY + Height >= DrawingArea.bottomY) {
            Height -= ((drawY + Height) - DrawingArea.bottomY);
        }
        if (drawX < DrawingArea.topX) {
            int l2 = DrawingArea.topX - drawX;
            Width -= l2;
            drawX = DrawingArea.topX;
            j2 += l2;
            k1 += l2;
            i2 += l2;
            l1 += l2;
        }
        if (drawX + Width >= DrawingArea.bottomX) {
            int i3 = ((drawX + Width) - DrawingArea.bottomX);
            Width -= i3;
            i2 += i3;
            l1 += i3;
        }
        if (Width <= 0 || Height <= 0) {
            return;
        }
        createCharacterPixelsWithOpacity(chatPixels, Height, k1, DrawingArea.pixels, j2, Width, i2, l1, color, opacity);
    }

    private void createCharacterPixelsWithOpacity(byte charPixels[], int Height, int j, int drawingAreaPixels[], int l, int Width, int j1, int k1, int color, int opacity) {
        color = ((color & 0xff00ff) * opacity & 0xff00ff00) + ((color & 0xff00) * opacity & 0xff0000) >> 8;
        opacity = 256 - opacity;
        for (int j2 = -Height; j2 < 0; j2++) {
            for (int k2 = -Width; k2 < 0; k2++) {
                if (charPixels[l++] != 0) {
                    int l2 = drawingAreaPixels[j];
                    drawingAreaPixels[j++] = (((l2 & 0xff00ff) * opacity & 0xff00ff00) + ((l2 & 0xff00) * opacity & 0xff0000) >> 8) + color;
                } else {
                    j++;
                }
            }
            j += k1;
            l += j1;
        }
    }
}
