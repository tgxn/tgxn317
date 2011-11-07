package client;


public final class NodeList {
    
    public Node head;
    private Node current;
    
    public NodeList() {
        head = new Node();
        head.prev = head;
        head.next = head;
    }

    public void insertHead(Node class30) {
        if (class30.next != null) {
            class30.unlink();
        }
        class30.next = head.next;
        class30.prev = head;
        class30.next.prev = class30;
        class30.prev.next = class30;
    }

    public void insertTail(Node class30) {
        if (class30.next != null) {
            class30.unlink();
        }
        class30.next = head;
        class30.prev = head.prev;
        class30.next.prev = class30;
        class30.prev.next = class30;
    }

    public Node popHead() {
        Node class30 = head.prev;
        if (class30 == head) {
            return null;
        } else {
            class30.unlink();
            return class30;
        }
    }

    public Node reverseGetFirst() {
        Node class30 = head.prev;
        if (class30 == head) {
            current = null;
            return null;
        } else {
            current = class30.prev;
            return class30;
        }
    }

    public Node getFirst() {

        Node class30 = head.next;
        if (class30 == head) {
            current = null;
            return null;
        } else {
            current = class30.next;
            return class30;
        }
    }

    public Node reverseGetNext() {
        Node class30 = current;
        if (class30 == head) {
            current = null;
            return null;
        } else {
            current = class30.prev;
            return class30;
        }
    }

    public Node getNext() {
        Node class30 = current;
        if (class30 == head) {
            current = null;
            return null;
        }
        current = class30.next;
        return class30;
    }

    public void RemoveAll() {
        if (head.prev == head) {
            return;
        }
        do {
            Node class30 = head.prev;
            if (class30 == head) {
                return;
            }
            class30.unlink();
        } while (true);
    }
}
