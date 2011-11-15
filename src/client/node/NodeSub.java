package client.node;


public class NodeSub extends Node {

    public NodeSub prevNodeSub;
    public NodeSub nextNodeSub;
    public static int anInt1305;

    public NodeSub() {
    }

    public void unlinkSub() {
        if (nextNodeSub == null) {
            return;
        } else {
            nextNodeSub.prevNodeSub = prevNodeSub;
            prevNodeSub.nextNodeSub = nextNodeSub;
            prevNodeSub = null;
            nextNodeSub = null;
            return;
        }
    }
}
