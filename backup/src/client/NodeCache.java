package client;


import sign.signlink;

public final class NodeCache {
    
    private int size;
    private Node cache[];

    public NodeCache(int i, int j) {
        if (i >= 0) {
            throw new NullPointerException();
        }
        size = j;
        cache = new Node[j];
        for (int k = 0; k < j; k++) {
            Node class30 = cache[k] = new Node();
            class30.prev = class30;
            class30.next = class30;
        }
    }

    public Node findNodeByID(long l) {
        Node class30 = cache[(int) (l & (long) (size - 1))];
        for (Node class30_1 = class30.prev; class30_1 != class30; class30_1 = class30_1.prev) {
            if (class30_1.aLong548 == l) {
                return class30_1;
            }
        }
        return null;
    }

    public void removeFromCache(Node class30, long l, byte byte0) {
        try {
            if (class30.next != null) {
                class30.unlink();
            }
            Node class30_1 = cache[(int) (l & (long) (size - 1))];
            if (byte0 != 7) {
                return;
            } else {
                class30.next = class30_1.next;
                class30.prev = class30_1;
                class30.next.prev = class30;
                class30.prev.next = class30;
                class30.aLong548 = l;
                return;
            }
        } catch (RuntimeException runtimeexception) {
            signlink.reportError("91499, " + class30 + ", " + l + ", " + byte0 + ", " + runtimeexception.toString());
        }
        throw new RuntimeException();
    }
}