

class LinkedList {
    Node head;

    static class Node {
        Node next;
        int data;
        Node(int data) {
            this.data = data;
            next = null;
        }
        
    }

    LinkedList() {
        head = null;
    }

    // Insert a node into a linked list
    // Insertion will be appended to the end of the linked list
    void insert(int data) {
        Node newNode = new Node(data);
        newNode.next = null;

        if(head != null) {
            Node next = head;
            while(next.next != null) {
                next = next.next;
            }
            next.next = newNode;
        } else {
            head = newNode;
        }

        return;
    }

    int search(int item) {
        Node n = head;
        int it = 0;
        int idx = -1;

        while(n != null) {
            if(n.data == item) {
                idx = it;
                break;
            } else {
                it++;
            }
            n = n.next;
        }

        return idx;
    }

    // Reverses the linked list
    void reverse() {
        Node currNode = head;
        Node prevNode = null;
        Node nextNode = null;

        while(currNode != null) {
            nextNode = currNode.next;
            currNode.next = prevNode;
            
            prevNode = currNode;
            currNode = nextNode;
        }

        head = prevNode;
        return;
    }

    void print() {
        Node n = head;
        while(n != null) {
            System.out.print(n.data + " ");
            n = n.next;
        }
    }
}

class Main {
    
    public static void main(String[] args) {
        LinkedList li = new LinkedList();
        for(int i = 0; i < 10; i++) {
            li.insert(i + 1);
        }

        System.out.println("Linked List");
        li.print();

        System.out.println("\n\nReverse Linked List");
        li.reverse();
        li.print();
    }
    
}