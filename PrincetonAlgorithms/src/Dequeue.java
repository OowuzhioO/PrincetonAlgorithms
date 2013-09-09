import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: satkuppu
 * Date: 9/9/13
 * Time: 11:07 AM
 * To change this template use File | Settings | File Templates.
 */
public class DeQueue<Item> implements Iterable<Item> {
    private int n;         // number of elements on queue
    private Node first;    // beginning of queue
    private Node last;     // end of queue

    // helper linked list class
    private class Node {
        private Item item;
        private Node next;
        private Node previous;
    }

    /**
     * Create an empty queue.
     */
    public DeQueue() {
        first = null;
        last  = null;
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
     * @throws java.util.NoSuchElementException if queue is empty.
     */
    public void addFirst(Item item) {
        Node newFirst = new Node();
        newFirst.item = item;
        newFirst.next = first;
        if(!isEmpty()) {
            first.previous = newFirst;
        } else {
            last = newFirst;
        }

        first = newFirst;
        n++;
    }

    @Override
    public Iterator<Item> iterator() {
        return null;
    }

    public static void main(String[] args) {
        DeQueue<String> myDequeue = new DeQueue<String>();
        myDequeue.addFirst("Sathish");
        myDequeue.addFirst("Deepa");
        myDequeue.addFirst("Dyutisha");
    }
}
