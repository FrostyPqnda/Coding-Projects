package MyImplementations;

import java.util.EmptyStackException;

public class Stack<E> {
    StackNode head; 
    int numItems;

    public Stack() {
        head = null;
        numItems = 0;
    }

    public E push(E item) {
        StackNode ptr = new StackNode(item);
        ptr.next = head;
        head = ptr;
        numItems++;
        return item;
    }

    public E pop() {
        if(empty())
            throw new EmptyStackException();
        
        E popped = head.data;
        head = head.next;
        numItems--;
        return popped;
    }

    public E peek() {
        if(empty())
            throw new EmptyStackException();
        
        return head.data;
    }

    public boolean empty() {
        return head == null | numItems == 0;
    }


    class StackNode {
        E data;
        StackNode next;

        public StackNode(E data) {
            this.data = data;
            next = null;
        }
    }
}
