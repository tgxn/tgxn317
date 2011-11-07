package client;

// Mostly Renamed. Class11
// I believed this handles clipping data for the area in view.
// Thanks Stewie! http://goo.gl/8awVA

public final class MapRegion {

    public int mapRegionX;
    public int mapRegionY;
    public int mapRegionWidth;
    public int mapRegionHeight;
    public int mapRegionArea[][];

    public MapRegion() {
        mapRegionX = 0;
        mapRegionY = 0;
        mapRegionWidth = 104;
        mapRegionHeight = 104;
        mapRegionArea = new int[mapRegionWidth][mapRegionHeight];
        setRegion();
    }

    public void setRegion() {
        for (int x = 0; x < mapRegionWidth; x++) {
            for (int y = 0; y < mapRegionHeight; y++) {
                if (x == 0 || y == 0 || x == mapRegionWidth - 1 || y == mapRegionHeight - 1) {
                    mapRegionArea[x][y] = 0xffffff;
                } else {
                    mapRegionArea[x][y] = 0x1000000;
                }
            }
        }
    }

    public void method211(int i, int j, int k, int l, boolean flag) {
        k -= mapRegionX;
        i -= mapRegionY;
        if (l == 0) {
            if (j == 0) {
                method214(k, i, 128);
                method214(k - 1, i, 8);
            }
            if (j == 1) {
                method214(k, i, 2);
                method214(k, i + 1, 32);
            }
            if (j == 2) {
                method214(k, i, 8);
                method214(k + 1, i, 128);
            }
            if (j == 3) {
                method214(k, i, 32);
                method214(k, i - 1, 2);
            }
        }
        if (l == 1 || l == 3) {
            if (j == 0) {
                method214(k, i, 1);
                method214(k - 1, i + 1, 16);
            }
            if (j == 1) {
                method214(k, i, 4);
                method214(k + 1, i + 1, 64);
            }
            if (j == 2) {
                method214(k, i, 16);
                method214(k + 1, i - 1, 1);
            }
            if (j == 3) {
                method214(k, i, 64);
                method214(k - 1, i - 1, 4);
            }
        }
        if (l == 2) {
            if (j == 0) {
                method214(k, i, 130);
                method214(k - 1, i, 8);
                method214(k, i + 1, 32);
            }
            if (j == 1) {
                method214(k, i, 10);
                method214(k, i + 1, 32);
                method214(k + 1, i, 128);
            }
            if (j == 2) {
                method214(k, i, 40);
                method214(k + 1, i, 128);
                method214(k, i - 1, 2);
            }
            if (j == 3) {
                method214(k, i, 160);
                method214(k, i - 1, 2);
                method214(k - 1, i, 8);
            }
        }
        if (flag) {
            if (l == 0) {
                if (j == 0) {
                    method214(k, i, 0x10000);
                    method214(k - 1, i, 4096);
                }
                if (j == 1) {
                    method214(k, i, 1024);
                    method214(k, i + 1, 16384);
                }
                if (j == 2) {
                    method214(k, i, 4096);
                    method214(k + 1, i, 0x10000);
                }
                if (j == 3) {
                    method214(k, i, 16384);
                    method214(k, i - 1, 1024);
                }
            }
            if (l == 1 || l == 3) {
                if (j == 0) {
                    method214(k, i, 512);
                    method214(k - 1, i + 1, 8192);
                }
                if (j == 1) {
                    method214(k, i, 2048);
                    method214(k + 1, i + 1, 32768);
                }
                if (j == 2) {
                    method214(k, i, 8192);
                    method214(k + 1, i - 1, 512);
                }
                if (j == 3) {
                    method214(k, i, 32768);
                    method214(k - 1, i - 1, 2048);
                }
            }
            if (l == 2) {
                if (j == 0) {
                    method214(k, i, 0x10400);
                    method214(k - 1, i, 4096);
                    method214(k, i + 1, 16384);
                }
                if (j == 1) {
                    method214(k, i, 5120);
                    method214(k, i + 1, 16384);
                    method214(k + 1, i, 0x10000);
                }
                if (j == 2) {
                    method214(k, i, 20480);
                    method214(k + 1, i, 0x10000);
                    method214(k, i - 1, 1024);
                }
                if (j == 3) {
                    method214(k, i, 0x14000);
                    method214(k, i - 1, 1024);
                    method214(k - 1, i, 4096);
                }
            }
        }
    }

    public void method212(boolean flag, int j, int k, int regionX, int regionY, int j1) {
        int k1 = 256;
        if (flag) {
            k1 += 0x20000;
        }
        regionX -= mapRegionX;
        regionY -= mapRegionY;
        if (j1 == 1 || j1 == 3) {
            int l1 = j;
            j = k;
            k = l1;
        }
        for (int x = regionX; x < regionX + j; x++) {
            if (x >= 0 && x < mapRegionWidth) {
                for (int y = regionY; y < regionY + k; y++) {
                    if (y >= 0 && y < mapRegionHeight) {
                        method214(x, y, k1);
                    }
                }
            }
        }
    }

    public void method213(int i, int j, int k) {
        k -= mapRegionX;
        i -= mapRegionY;
        mapRegionArea[k][i] |= 0x200000;
    }

    private void method214(int i, int j, int k) {
        mapRegionArea[i][j] |= k;
    }

    public void method215(int i, int j, boolean flag, int k, int l) {
        k -= mapRegionX;
        l -= mapRegionY;
        if (j == 0) {
            if (i == 0) {
                method217(128, k, l);
                method217(8, k - 1, l);
            }
            if (i == 1) {
                method217(2, k, l);
                method217(32, k, l + 1);
            }
            if (i == 2) {
                method217(8, k, l);
                method217(128, k + 1, l);
            }
            if (i == 3) {
                method217(32, k, l);
                method217(2, k, l - 1);
            }
        }
        if (j == 1 || j == 3) {
            if (i == 0) {
                method217(1, k, l);
                method217(16, k - 1, l + 1);
            }
            if (i == 1) {
                method217(4, k, l);
                method217(64, k + 1, l + 1);
            }
            if (i == 2) {
                method217(16, k, l);
                method217(1, k + 1, l - 1);
            }
            if (i == 3) {
                method217(64, k, l);
                method217(4, k - 1, l - 1);
            }
        }
        if (j == 2) {
            if (i == 0) {
                method217(130, k, l);
                method217(8, k - 1, l);
                method217(32, k, l + 1);
            }
            if (i == 1) {
                method217(10, k, l);
                method217(32, k, l + 1);
                method217(128, k + 1, l);
            }
            if (i == 2) {
                method217(40, k, l);
                method217(128, k + 1, l);
                method217(2, k, l - 1);
            }
            if (i == 3) {
                method217(160, k, l);
                method217(2, k, l - 1);
                method217(8, k - 1, l);
            }
        }
        if (flag) {
            if (j == 0) {
                if (i == 0) {
                    method217(0x10000, k, l);
                    method217(4096, k - 1, l);
                }
                if (i == 1) {
                    method217(1024, k, l);
                    method217(16384, k, l + 1);
                }
                if (i == 2) {
                    method217(4096, k, l);
                    method217(0x10000, k + 1, l);
                }
                if (i == 3) {
                    method217(16384, k, l);
                    method217(1024, k, l - 1);
                }
            }
            if (j == 1 || j == 3) {
                if (i == 0) {
                    method217(512, k, l);
                    method217(8192, k - 1, l + 1);
                }
                if (i == 1) {
                    method217(2048, k, l);
                    method217(32768, k + 1, l + 1);
                }
                if (i == 2) {
                    method217(8192, k, l);
                    method217(512, k + 1, l - 1);
                }
                if (i == 3) {
                    method217(32768, k, l);
                    method217(2048, k - 1, l - 1);
                }
            }
            if (j == 2) {
                if (i == 0) {
                    method217(0x10400, k, l);
                    method217(4096, k - 1, l);
                    method217(16384, k, l + 1);
                }
                if (i == 1) {
                    method217(5120, k, l);
                    method217(16384, k, l + 1);
                    method217(0x10000, k + 1, l);
                }
                if (i == 2) {
                    method217(20480, k, l);
                    method217(0x10000, k + 1, l);
                    method217(1024, k, l - 1);
                }
                if (i == 3) {
                    method217(0x14000, k, l);
                    method217(1024, k, l - 1);
                    method217(4096, k - 1, l);
                }
            }
        }
    }

    public void method216(int i, int j, int k, int l, byte byte0, int i1, boolean flag) {
        int j1 = 256;
        if (flag) {
            j1 += 0x20000;
        }
        k -= mapRegionX;
        l -= mapRegionY;
        if (i == 1 || i == 3) {
            int k1 = j;
            j = i1;
            i1 = k1;
        }
        for (int l1 = k; l1 < k + j; l1++) {
            if (l1 >= 0 && l1 < mapRegionWidth) {
                for (int i2 = l; i2 < l + i1; i2++) {
                    if (i2 >= 0 && i2 < mapRegionHeight) {
                        method217(j1, l1, i2);
                    }
                }
            }
        }
    }

    private void method217(int i, int j, int k) {
        mapRegionArea[j][k] &= 0xffffff - i;
    }

    public void method218(int y, int x) {
        x -= mapRegionX;
        y -= mapRegionY;
        mapRegionArea[x][y] &= 0xdfffff;
    }

    public boolean atArea(int destX, int currX, int currY, int atTypeY, int atTypeX, int destY) {
        if (currX == destX && currY == destY) {
            return true;
        }
        currX -= mapRegionX;
        currY -= mapRegionY;
        destX -= mapRegionX;
        destY -= mapRegionY;
        if (atTypeX == 0) {
            if (atTypeY == 0) {
                if (currX == destX - 1 && currY == destY) {
                    return true;
                }
                if (currX == destX && currY == destY + 1 && (mapRegionArea[currX][currY] & 0x1280120) == 0) {
                    return true;
                }
                if (currX == destX && currY == destY - 1 && (mapRegionArea[currX][currY] & 0x1280102) == 0) {
                    return true;
                }
            } else if (atTypeY == 1) {
                if (currX == destX && currY == destY + 1) {
                    return true;
                }
                if (currX == destX - 1 && currY == destY && (mapRegionArea[currX][currY] & 0x1280108) == 0) {
                    return true;
                }
                if (currX == destX + 1 && currY == destY && (mapRegionArea[currX][currY] & 0x1280180) == 0) {
                    return true;
                }
            } else if (atTypeY == 2) {
                if (currX == destX + 1 && currY == destY) {
                    return true;
                }
                if (currX == destX && currY == destY + 1 && (mapRegionArea[currX][currY] & 0x1280120) == 0) {
                    return true;
                }
                if (currX == destX && currY == destY - 1 && (mapRegionArea[currX][currY] & 0x1280102) == 0) {
                    return true;
                }
            } else if (atTypeY == 3) {
                if (currX == destX && currY == destY - 1) {
                    return true;
                }
                if (currX == destX - 1 && currY == destY && (mapRegionArea[currX][currY] & 0x1280108) == 0) {
                    return true;
                }
                if (currX == destX + 1 && currY == destY && (mapRegionArea[currX][currY] & 0x1280180) == 0) {
                    return true;
                }
            }
        }
        if (atTypeX == 2) {
            if (atTypeY == 0) {
                if (currX == destX - 1 && currY == destY) {
                    return true;
                }
                if (currX == destX && currY == destY + 1) {
                    return true;
                }
                if (currX == destX + 1 && currY == destY && (mapRegionArea[currX][currY] & 0x1280180) == 0) {
                    return true;
                }
                if (currX == destX && currY == destY - 1 && (mapRegionArea[currX][currY] & 0x1280102) == 0) {
                    return true;
                }
            } else if (atTypeY == 1) {
                if (currX == destX - 1 && currY == destY && (mapRegionArea[currX][currY] & 0x1280108) == 0) {
                    return true;
                }
                if (currX == destX && currY == destY + 1) {
                    return true;
                }
                if (currX == destX + 1 && currY == destY) {
                    return true;
                }
                if (currX == destX && currY == destY - 1 && (mapRegionArea[currX][currY] & 0x1280102) == 0) {
                    return true;
                }
            } else if (atTypeY == 2) {
                if (currX == destX - 1 && currY == destY && (mapRegionArea[currX][currY] & 0x1280108) == 0) {
                    return true;
                }
                if (currX == destX && currY == destY + 1 && (mapRegionArea[currX][currY] & 0x1280120) == 0) {
                    return true;
                }
                if (currX == destX + 1 && currY == destY) {
                    return true;
                }
                if (currX == destX && currY == destY - 1) {
                    return true;
                }
            } else if (atTypeY == 3) {
                if (currX == destX - 1 && currY == destY) {
                    return true;
                }
                if (currX == destX && currY == destY + 1 && (mapRegionArea[currX][currY] & 0x1280120) == 0) {
                    return true;
                }
                if (currX == destX + 1 && currY == destY && (mapRegionArea[currX][currY] & 0x1280180) == 0) {
                    return true;
                }
                if (currX == destX && currY == destY - 1) {
                    return true;
                }
            }
        }
        if (atTypeX == 9) {
            if (currX == destX && currY == destY + 1 && (mapRegionArea[currX][currY] & 0x20) == 0) {
                return true;
            }
            if (currX == destX && currY == destY - 1 && (mapRegionArea[currX][currY] & 2) == 0) {
                return true;
            }
            if (currX == destX - 1 && currY == destY && (mapRegionArea[currX][currY] & 8) == 0) {
                return true;
            }
            if (currX == destX + 1 && currY == destY && (mapRegionArea[currX][currY] & 0x80) == 0) {
                return true;
            }
        }
        return false;
    }

    public boolean atAreaType2(int destX, int destY, int currY, int atTypeX, int atTypeY, int currX) {
        if (currX == destX && currY == destY) {
            return true;
        }
        currX -= mapRegionX;
        currY -= mapRegionY;
        destX -= mapRegionX;
        destY -= mapRegionY;
        if (atTypeX == 6 || atTypeX == 7) {
            if (atTypeX == 7) {
                atTypeY = atTypeY + 2 & 3;
            }
            if (atTypeY == 0) {
                if (currX == destX + 1 && currY == destY && (mapRegionArea[currX][currY] & 0x80) == 0) {
                    return true;
                }
                if (currX == destX && currY == destY - 1 && (mapRegionArea[currX][currY] & 2) == 0) {
                    return true;
                }
            } else if (atTypeY == 1) {
                if (currX == destX - 1 && currY == destY && (mapRegionArea[currX][currY] & 8) == 0) {
                    return true;
                }
                if (currX == destX && currY == destY - 1 && (mapRegionArea[currX][currY] & 2) == 0) {
                    return true;
                }
            } else if (atTypeY == 2) {
                if (currX == destX - 1 && currY == destY && (mapRegionArea[currX][currY] & 8) == 0) {
                    return true;
                }
                if (currX == destX && currY == destY + 1 && (mapRegionArea[currX][currY] & 0x20) == 0) {
                    return true;
                }
            } else if (atTypeY == 3) {
                if (currX == destX + 1 && currY == destY && (mapRegionArea[currX][currY] & 0x80) == 0) {
                    return true;
                }
                if (currX == destX && currY == destY + 1 && (mapRegionArea[currX][currY] & 0x20) == 0) {
                    return true;
                }
            }
        }
        if (atTypeX == 8) {
            if (currX == destX && currY == destY + 1 && (mapRegionArea[currX][currY] & 0x20) == 0) {
                return true;
            }
            if (currX == destX && currY == destY - 1 && (mapRegionArea[currX][currY] & 2) == 0) {
                return true;
            }
            if (currX == destX - 1 && currY == destY && (mapRegionArea[currX][currY] & 8) == 0) {
                return true;
            }
            if (currX == destX + 1 && currY == destY && (mapRegionArea[currX][currY] & 0x80) == 0) {
                return true;
            }
        }
        return false;
    }

    public boolean inArea(int minY, int minX, int x, int l, int i1, int j1, int y) {
        int maxX = (minX + j1) - 1;
        int maxY = (minY + l) - 1;
        if (x >= minX && x <= maxX && y >= minY && y <= maxY) {
            return true;
        }
        if (x == minX - 1 && y >= minY && y <= maxY && (mapRegionArea[x - mapRegionX][y - mapRegionY] & 8) == 0 && (i1 & 8) == 0) {
            return true;
        }
        if (x == maxX + 1 && y >= minY && y <= maxY && (mapRegionArea[x - mapRegionX][y - mapRegionY] & 0x80) == 0 && (i1 & 2) == 0) {
            return true;
        }
        if (y == minY - 1 && x >= minX && x <= maxX && (mapRegionArea[x - mapRegionX][y - mapRegionY] & 2) == 0 && (i1 & 4) == 0) {
            return true;
        }
        return y == maxY + 1 && x >= minX && x <= maxX && (mapRegionArea[x - mapRegionX][y - mapRegionY] & 0x20) == 0 && (i1 & 1) == 0;
    }
}
