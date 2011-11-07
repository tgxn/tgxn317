package client;


import sign.signlink;

public final class MemCache {

    private boolean aBoolean297;
    private int anInt298;
    private int anInt299;
    private NodeSub emptyNodeSub;
    private int initialCount;
    private int spaceLeft;
    private NodeCache nodeCache;
    private NodeSubList nodeSubList;

    public MemCache(int i) {
        aBoolean297 = false;
        emptyNodeSub = new NodeSub();
        nodeSubList = new NodeSubList();
        initialCount = i;
        spaceLeft = i;
        nodeCache = new NodeCache(-877, 1024);
    }

    public NodeSub insertFromCache(long l) {
        NodeSub class30_sub2 = (NodeSub) nodeCache.findNodeByID(l);
        if (class30_sub2 != null) {
            nodeSubList.insertHead(class30_sub2);
            anInt299++;
        } else {
            anInt298++;
        }
        return class30_sub2;
    }

    public void removeFromCache(NodeSub class30_sub2, long l) {
        try {
            if (spaceLeft == 0) {
                NodeSub class30_sub2_1 = nodeSubList.popTail();
                class30_sub2_1.unlink();
                class30_sub2_1.unlinkSub();
                if (class30_sub2_1 == emptyNodeSub) {
                    NodeSub class30_sub2_2 = nodeSubList.popTail();
                    class30_sub2_2.unlink();
                    class30_sub2_2.unlinkSub();
                }
            } else {
                spaceLeft--;
            }
            nodeCache.removeFromCache(class30_sub2, l, (byte) 7);
            nodeSubList.insertHead(class30_sub2);
            return;
        } catch (RuntimeException runtimeexception) {
            signlink.reportError("47547, " + class30_sub2 + ", " + l + ", " + runtimeexception.toString());
        }
        throw new RuntimeException();
    }

    public void unlinkAll() {
        do {
            NodeSub class30_sub2 = nodeSubList.popTail();
            if (class30_sub2 != null) {
                class30_sub2.unlink();
                class30_sub2.unlinkSub();
            } else {
                spaceLeft = initialCount;
                return;
            }
        } while (true);
    }
}
