package client.util;

// Fully Renamed - gamerx - 14.11.11

import client.node.Node;
import client.sign.Signlink;

public final class HashTable {
    
    private int tableSize;
    private Node hashTable[];

    /**
     * Generated a hash table of nodes.
     */
    public HashTable() {
        int tableLength = 1024;
        tableSize = tableLength;
        hashTable = new Node[tableLength];
        for (int pos = 0; pos < tableLength; pos++) {
            Node node = hashTable[pos] = new Node();
            node.prev = node;
            node.next = node;
        }
    }

    /**
     * Return a nodes hash using its ID.
     *
     * @param nodeHashID THe id to find the hash for.
     * @return THe node for the specified hash.
     */
    public Node findNodeByID(long nodeHashID) {
        Node node = hashTable[(int) (nodeHashID & (long) (tableSize - 1))];
        for (Node nodes = node.prev; nodes != node; nodes = nodes.prev) {
            if (nodes.hash == nodeHashID) {
                return nodes;
            }
        }
        return null;
    }

    /**
     * Remove node from the cash using its hash ID.
     *
     * @param nodeCache Cache to remove from.
     * @param nodeHashID Hash value to remove.
     */
    public void removeFromCache(Node nodeCache, long nodeHashID) {
        try {
            if (nodeCache.next != null) {
                nodeCache.unlink();
            }
            Node node = hashTable[(int) (nodeHashID & (long) (tableSize - 1))];
            nodeCache.next = node.next;
            nodeCache.prev = node;
            nodeCache.next.prev = nodeCache;
            nodeCache.prev.next = nodeCache;
            nodeCache.hash = nodeHashID;
        } catch (RuntimeException runtimeexception) {
            Signlink.reportError("91499, " + nodeCache + ", " + nodeHashID + ", " + runtimeexception.toString());
        }
    }
}
