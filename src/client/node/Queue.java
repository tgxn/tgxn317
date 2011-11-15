package client.node;

import client.node.NodeSub;


public final class Queue {
    
    public NodeSub head; //head
    private NodeSub current; //current
    
    public Queue() { //NodeSubList
        head = new NodeSub();
        head.prevNodeSub = head;
        head.nextNodeSub = head;
    }

    public void insertHead(NodeSub class30_sub2) {
        if (class30_sub2.nextNodeSub != null) {
            class30_sub2.unlinkSub();
        }
        class30_sub2.nextNodeSub = head.nextNodeSub;
        class30_sub2.prevNodeSub = head;
        class30_sub2.nextNodeSub.prevNodeSub = class30_sub2;
        class30_sub2.prevNodeSub.nextNodeSub = class30_sub2;
    }

    public NodeSub popTail() {
        NodeSub class30_sub2 = head.prevNodeSub;
        if (class30_sub2 == head) {
            return null;
        } else {
            class30_sub2.unlinkSub();
            return class30_sub2;
        }
    }

    public NodeSub reverseGetFirst() {
        NodeSub class30_sub2 = head.prevNodeSub;
        if (class30_sub2 == head) {
            current = null;
            return null;
        } else {
            current = class30_sub2.prevNodeSub;
            return class30_sub2;
        }
    }

    public NodeSub reverseGetNext() {
        NodeSub class30_sub2 = current;
        if (class30_sub2 == head) {
            current = null;
            return null;
        } else {
            current = class30_sub2.prevNodeSub;
            return class30_sub2;
        }
    }

    public int getNodeCount() {
        int i = 0;
        for (NodeSub class30_sub2 = head.prevNodeSub; class30_sub2 != head; class30_sub2 = class30_sub2.prevNodeSub) {
            i++;
        }
        return i;
    }
}
