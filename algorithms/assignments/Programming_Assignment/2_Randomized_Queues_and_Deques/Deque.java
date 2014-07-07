/*************************************************************************
 *  Compilation:  javac Deque.java
 *  Execution:    java Deque
 *  
 *  Dependency: StdOut.java
 *  
 *  A generic Deque, implemented using a linked list. Each Deque
 *  element is of type Item.
 *
 *  % more tobe.txt
 *  to be or not to - be - - that - - - is
 *
 *  % java Deque
 *  to be not that or be (2 left on stack)  // with addFirst, removeFirst (LIFO)
 *  to be or not to be (2 left on Deque)  // with AddLast, removeFirst (FIFO)
 *************************************************************************/

import java.util.Iterator;
import java.lang.NullPointerException;
import java.lang.UnsupportedOperationException;
import java.util.NoSuchElementException;

// for unit test only
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

/**
 *  The <tt>Deque</tt> class represents a double-ended queue of generic items (Stack + Queue).
 *  It supports the usual <em>removeFirst</em> and <em>removeLast</em> operations, along with methods
 *  for adding at the front item, adding at the bottom item, and iterating through
 *  the items from front to end.
 *  <p>
 *  This implementation uses a double-linked list with a non-static nested class for
 *  linked-list nodes. See {@link LinkedStack, LinkedQueue} for a version that uses a static nested class.
 *  The <em>addFirst</em>, <em>removeFirst</em>, <em>removeLast</em>, <em>removeLast</em>, 
 *  and <em>isEmpty</em> operations all take constant time in the worst case.
 *  <p> 
 *  @author Joon Lim
 */

public class Deque<Item> implements Iterable<Item> {
    private Node<Item> first;   // front pointer
    private Node<Item> last;    // end pointer
    private int N;              // Size of Deque

    /**
     * Initializes an empty Deque
     */
    public Deque() {
        first = null;
        last = null;
        N = 0;
        assert check();
    }

    // helper double linked list class
    private static class Node<Item> {
        private Item item;
        private Node<Item> previous;
        private Node<Item> next;
    }

    /**
     * @return a boolean value indicating whether the deque is empty
     */
    public boolean isEmpty() { return first == null; }

    /**
     * @return return the number of items on the deque (N)
     */
    public int size() { return N; }

    /**
     * Insert the item at the front
     * @param item a generic item
     * @throws java.lang.NullPointerException if item is null
     */
    public void addFirst(Item item) {
        if (item == null) { throw new NullPointerException(); }
        if (isEmpty()) {
            first = new Node<Item>();
            first.item = item;
            first.next = null;
            first.previous = null;
            last = first;
        } else {
            Node<Item> oldFirst = first;
            first = new Node<Item>();
            first.item = item;
            // pointer manipulation
            first.next = oldFirst;
            first.previous = null;
            oldFirst.previous = first;
        }
        N++;
//        assert check();
    }

    /**
     * Insert the item at the end
     * @param item a generic item
     * @throws java.lang.NullPointerException if item is null
     */
    public void addLast(Item item) {
        if (item == null) { throw new NullPointerException(); }
        if (isEmpty()) {
            last = new Node<Item>();
            last.item = item;
            last.previous = null;
            last.next = null;
            first = last;
        } else {
            Node<Item> oldLast = last;
            last = new Node<Item>();
            last.item = item;
            last.previous = oldLast;
            last.next = null;
            oldLast.next = last;
        }
        N++;
//        assert check();
    }

    /**
     * delete and return the item at the front
     * @return a deleted item in the front
     * @throws java.util.NoSuchElementException if this deque is empty
     */
    public Item removeFirst() {
        if (isEmpty()) { throw new NoSuchElementException(); }
        Item item = first.item;

        if (size() == 1) {
            first = null;
            last = null;
        } else {
            first = first.next;
            first.previous = null;
        }
        N--;
        if (isEmpty()) last = null;  // avoid loitering
//        assert check();
        return item;
    }

    /**
     * delete and return the item at the end
     * @return a deleted item in the end
     * @throws java.util.NoSuchElementException is this deque is empty
     */
    public Item removeLast() {
        if (isEmpty()) { throw new NoSuchElementException(); }
        Item item = last.item;

        if (size() == 1) {
            first = null;
            last = null;
        } else {
            last = last.previous;
            last.next = null;
        }
        N--;
        if (isEmpty()) last = null;  // avoid loitering (by definition, first is null)
//        assert check();
        return item;
    }

    /**
     * return an iterator over items in order from front to end: Use 'first' Node
     */
    public Iterator<Item> iterator() {
        return new DequeIterator<Item>(first);
    }

    // an iterator, doesn't implement remove() since it's optional
    private class DequeIterator<Item> implements Iterator<Item> {
        private Node<Item> current;

        public DequeIterator(Node<Item> first) {
            current = first;
        }

        public boolean hasNext() { return current != null; }
        public void remove() { throw new UnsupportedOperationException(); }
        
        public Item next() {
            if (current == null) { throw new NoSuchElementException(); }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    // check internal invariants
    private boolean check() {
        if (N == 0) {
            if (first != null) return false;
            if (last  != null) return false;
        }
        else if (N == 1) {
            if (first == null && last == null)           return false;
            if (first != last)                           return false;
            if (first.next != null && last.next != null) return false;
        }
        else {
            if (first.next == null && last.next == null) return false;

            // check internal consistency of instance variable N
            int numberOfNodes = 0;
            for (Node x = first; x != null; x = x.next) {
                numberOfNodes++;
            }
            for (Node x = last; x != null; x = x.next) {
                numberOfNodes++;
            }
            if (numberOfNodes != N) return false;
        }
        return true;
    }

    private static String readTobe() throws IOException {
        File tobe = new File("algorithms/data/tobe.txt");
        BufferedReader reader = new BufferedReader(new FileReader(tobe));
        return reader.readLine();
    }

    /**
     * Unit tests the <tt>Deque</tt> data type.
     */
    public static void main(String[] args) throws Exception {
        Deque<String> d = new Deque<String>();
        String[] input = readTobe().split(" ");

        for (String c: input) {
            // FIFO test (same as queue)
            String item = c;
            if (!item.equals("-")) d.addLast(item);  // change this to .addFirst to test LIFO
            else if (!d.isEmpty()) StdOut.print(d.removeFirst() + " ");
        }
        StdOut.println("(" + d.size() + " left on Deque)");
    }
}