package client;

import client.sign.Signlink;

public final class MemCache {

    private NodeSub emptyNodeSub;
    private int initialCount;
    private int spaceLeft;
    private HashTable hashTable;
    private Queue queue;

    public MemCache(int i) {
        emptyNodeSub = new NodeSub();
        queue = new Queue();
        initialCount = i;
        spaceLeft = i;
        hashTable = new HashTable(-877, 1024);
    }

    public NodeSub get(long l) {
        NodeSub nodeSub = (NodeSub) hashTable.findNodeByID(l);
        if (nodeSub != null) {
            queue.insertHead(nodeSub);
        }
        return nodeSub;
    }

    public void put(NodeSub nodeSub, long l) {
        try {
            if (spaceLeft == 0) {
                NodeSub class30_sub2_1 = queue.popTail();
                class30_sub2_1.unlink();
                class30_sub2_1.unlinkSub();
                if (class30_sub2_1 == emptyNodeSub) {
                    NodeSub class30_sub2_2 = queue.popTail();
                    class30_sub2_2.unlink();
                    class30_sub2_2.unlinkSub();
                }
            } else {
                spaceLeft--;
            }
            hashTable.removeFromCache(nodeSub, l, (byte) 7);
            queue.insertHead(nodeSub);
            return;
        } catch (RuntimeException runtimeexception) {
            Signlink.reportError("47547, " + nodeSub + ", " + l + ", " + runtimeexception.toString());
        }
        throw new RuntimeException();
    }

    public void unlinkAll() {
        do {
            NodeSub nodeSub = queue.popTail();
            if (nodeSub != null) {
                nodeSub.unlink();
                nodeSub.unlinkSub();
            } else {
                spaceLeft = initialCount;
                return;
            }
        } while (true);
    }
}
