package client;

final class Tile extends Node {

    int plane;
    int x;
    int y;
    int anInt1310;
    public PlainTile plain;
    public ShapedTile shaped;
    Object1 obj1;
    Object2 obj2;
    Object3 obj3;
    FloorItem heldItem;
    int anInt1317;
    NPCFloorTile NPCs[];
    int anIntArray1319[];
    int anInt1320;
    int anInt1321;
    boolean aBoolean1322;
    boolean aBoolean1323;
    boolean aBoolean1324;
    int anInt1325;
    int anInt1326;
    int anInt1327;
    int anInt1328;
    Tile instance;

    public Tile(int i, int j, int k) {
        NPCs = new NPCFloorTile[5];
        anIntArray1319 = new int[5];
        anInt1310 = plane = i;
        x = j;
        y = k;
    }
}
