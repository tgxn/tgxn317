package client.tile;

import client.node.Node;

public final class Tile extends Node {

    public int plane;
    public int x;
    public int y;
    public int anInt1310;
    public PlainTile plainTile;
    public ShapedTile shapedTile;
    public WallObject wallObject;
    public WallDecoration wallDecoration;
    public GroundDecoration groundDecoration;
    public GroundItemTile groundItemTile;
    public int entityCount;
    public InteractableObject[] interactableObjects;
    public int anIntArray1319[];
    public int anInt1320;
    public int logicHeight;
    public boolean aBoolean1322;
    public boolean aBoolean1323;
    public boolean aBoolean1324;
    public int anInt1325;
    public int anInt1326;
    public int anInt1327;
    public int anInt1328;
    public Tile tileBelow;

    public Tile(int i, int j, int k) {
        interactableObjects = new InteractableObject[5];
        anIntArray1319 = new int[5];
        anInt1310 = plane = i;
        x = j;
        y = k;
    }
}
