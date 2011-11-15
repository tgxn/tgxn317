package client.node;


public class Node {
    
    public long hash;
    public Node prev;
    public Node next;
    public static boolean aBoolean551;
    
    public Node() {
    }
    
    public void unlink() {
        if (next == null) {
            return;
        } else {
            next.prev = prev;
            prev.next = next;
            prev = null;
            next = null;
            return;
        }
    }
}
