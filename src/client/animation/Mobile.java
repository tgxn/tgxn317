package client.animation;

import client.archive.Sequence;
import client.animation.Entity;

// Renamed from entity3
public class Mobile extends Entity {
    
    public int[] pathX;
    public int[] pathY;
    public int interactingEntity;
    public int anInt1503;
    public int anInt1504;
    public int anInt1505;
    public String textSpoken;
    public int height;
    public int turnDirection;
    public int anInt1511;//idle?
    public int anInt1512;//idleTurn?
    public int anInt1513;//
    public int[] hitArray;
    public int[] hitMarkTypes;
    public int[] hitsLoopCycle;
    public int anInt1517;
    public int anInt1518;
    public int anInt1519;
    public int anInt1520;
    public int anInt1521;
    public int anInt1522;
    public int anInt1523;
    public int anInt1524;
    public int pathLength;
    public int animation;
    public int anInt1527;
    public int anInt1528;
    public int anInt1529;
    public int anInt1530;
    public int anInt1531;
    public int loopCycleStatus;
    public int currentHealth;
    public int maxHealth;
    public int textCycle;
    public int anInt1537;
    public int anInt1538;
    public int anInt1539;
    public int boundDim;
    public boolean aBoolean1541;
    public int anInt1542;
    public int anInt1543;
    public int anInt1544;
    public int anInt1545;
    public int anInt1546;
    public int anInt1547;
    public int anInt1548;
    public int anInt1549;
    public int boundExtentX;
    public int boundExtentY;
    public int anInt1552;
    public boolean[] pathRun;
    public int anInt1554;//walk?
    public int anInt1555;//turn180?
    public int anInt1556;//turn90clockwise?
    public int anInt1557;//turn90counterclockwise?
    
    public Mobile() {
        pathX = new int[10];
        pathY = new int[10];
        interactingEntity = -1;
        anInt1504 = 32;
        anInt1505 = -1;
        height = 200;
        anInt1511 = -1;
        anInt1512 = -1;
        hitArray = new int[4];
        hitMarkTypes = new int[4];
        hitsLoopCycle = new int[4];
        anInt1517 = -1;
        anInt1520 = -1;
        animation = -1;
        loopCycleStatus = -1000;
        textCycle = 100;
        boundDim = 1;
        aBoolean1541 = false;
        pathRun = new boolean[10];
        anInt1554 = -1;
        anInt1555 = -1;
        anInt1556 = -1;
        anInt1557 = -1;
    }
    
    public final void setPos(int x, int y, boolean flag) {
        if (animation != -1 && Sequence.animCache[animation].priority == 1) {
            animation = -1;
        }
        if (!flag) {
            int dx = x - pathX[0];
            int dy = y - pathY[0];
            if (dx >= -8 && dx <= 8 && dy >= -8 && dy <= 8) {
                if (pathLength < 9) {
                    pathLength++;
                }
                for (int i1 = pathLength; i1 > 0; i1--) {
                    pathX[i1] = pathX[i1 - 1];
                    pathY[i1] = pathY[i1 - 1];
                    pathRun[i1] = pathRun[i1 - 1];
                }

                pathX[0] = x;
                pathY[0] = y;
                pathRun[0] = false;
                return;
            }
        }
        pathLength = 0;
        anInt1542 = 0;
        anInt1503 = 0;
        pathX[0] = x;
        pathY[0] = y;
        this.boundExtentX = pathX[0] * 128 + boundDim * 64;
        this.boundExtentY = pathY[0] * 128 + boundDim * 64;
    }

    public final void method446() {
        pathLength = 0;
        anInt1542 = 0;
    }

    public final void updateHitData(int j, int k, int l) {
        for (int i1 = 0; i1 < 4; i1++) {
            if (hitsLoopCycle[i1] <= l) {
                hitArray[i1] = k;
                hitMarkTypes[i1] = j;
                hitsLoopCycle[i1] = l + 70;
                return;
            }
        }
    }

    public final void moveInDir(boolean run, int dir) {
        int x = pathX[0];
        int y = pathY[0];
        if (dir == 0) {
            x--;
            y++;
        }
        if (dir == 1) {
            y++;
        }
        if (dir == 2) {
            x++;
            y++;
        }
        if (dir == 3) {
            x--;
        }
        if (dir == 4) {
            x++;
        }
        if (dir == 5) {
            x--;
            y--;
        }
        if (dir == 6) {
            y--;
        }
        if (dir == 7) {
            x++;
            y--;
        }
        if (animation != -1 && Sequence.animCache[animation].priority == 1) {
            animation = -1;
        }
        if (pathLength < 9) {
            pathLength++;
        }
        for (int l = pathLength; l > 0; l--) {
            pathX[l] = pathX[l - 1];
            pathY[l] = pathY[l - 1];
            pathRun[l] = pathRun[l - 1];
        }
        pathX[0] = x;
        pathY[0] = y;
        pathRun[0] = run;
    }

    public boolean isVisible() {
        return false;
    }
}
