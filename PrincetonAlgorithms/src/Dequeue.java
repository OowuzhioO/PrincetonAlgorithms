import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created with IntelliJ IDEA. User: satkuppu Date: 9/9/13 Time: 11:07 AM To
 * change this template use File | Settings | File Templates.
 */
public class Dequeue<Item> implements Iterable<Item> {
    private int n; // number of elements on queue
    private Node first; // beginning of queue
    private Node last; // end of queue

    // helper linked list class
    private class Node {
        private Item item;
        private Node next;
        private Node previous;
    }

    /**
     * Create an empty queue.
     */
    public Dequeue() {
        first = null;
        last = null;
        n = 0;
    }

    /**
     * Is the queue empty?
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * Return the number of items in the queue.
     */
    public int size() {
        return n;
    }

    /**
     * Return the item least recently added to the queue.
     * 
     * @throws java.util.NoSuchElementException
     *             if queue is empty.
     */
    public void addFirst(Item item) {
        Node newFirst = new Node();
        newFirst.item = item;
        if (!isEmpty()) {
            newFirst.next = first;
            first.previous = newFirst;
        } else {
            last = newFirst;
        }

        first = newFirst;
        n++;
    }

    public Item removeFirst() {
        if (isEmpty())
            throw new NoSuchElementException("DeQueue underflow");
        Item item = first.item;
        first = first.next;
        first.previous = null;
        n--;
        return item;
    }

    /**
     * Return the item least recently added to the queue.
     * 
     * @throws java.util.NoSuchElementException
     *             if queue is empty.
     */
    public void addLast(Item item) {
        Node newLast = new Node();
        newLast.item = item;
        if (!isEmpty()) {
            last.next = newLast;
            newLast.previous = last;
        } else {
            first = newLast;
        }

        last = newLast;
        n++;
    }

    public Item removeLast() {
        if (isEmpty())
            throw new NoSuchElementException("DeQueue underflow");
        Item item = last.item;
        last = last.previous;
        last.next = null;
        n--;
        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    // an iterator, doesn't implement remove() since it's optional
    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {
        Dequeue<String> myDequeue = new Dequeue<String>();

        myDequeue.addFirst("Revathi");
        myDequeue.addLast("Sathish");
        myDequeue.addLast("Deepa");
        myDequeue.addLast("Dyutisha");
        System.out.println();
        myDequeue.removeFirst();
        System.out.println();
        myDequeue.removeLast();
        myDequeue.removeLast();
        myDequeue.addFirst("Revathi");

    }
}
