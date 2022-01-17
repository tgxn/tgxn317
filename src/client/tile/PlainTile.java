package client.tile;

// Fully Renamed - gamerx - 16.11.11

/**
 * Tile handler for plain tiles.
 *
 * @knownas Class43
 * @author gamerx
 */
public final class PlainTile {

    public int colourA;
    public int colourB;
    public int colourD;
    public int colourC;
    public int texture;
    public boolean flat;
    public int rgbColor;

    public PlainTile(int cA, int cB, int cD, int cC, int tex, int cRGB, boolean isFlat) {
        flat = true;
        colourA = cA;
        colourB = cB;
        colourD = cD;
        colourC = cC;
        texture = tex;
        rgbColor = cRGB;
        flat = isFlat;
    }
}
