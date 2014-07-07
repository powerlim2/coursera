/*************************************************************************
 *  Compilation:  javac RandomizedQueue.java
 *  Execution:    java RandomizedQueue
 *
 *  Dependency: StdOut.java
 *
 *  A generic randomized queue, implemented using a resizing array. Each queue
 *  element is of type Item.
 *
 *  % more tobe.txt
 *  to be or not to - be - - that - - - is
 *
 *  % java RandomizedQueue
 *  to be not that or be (2 left on stack)  // the result varies because dequeue deletes at random
 *
 *************************************************************************/

import java.util.Iterator;
import java.lang.NullPointerException;
import java.lang.UnsupportedOperationException;
import java.util.NoSuchElementException;

// Unit Test Only
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 *  The <tt>RandomizedQueue</tt> class represents a first-in-first-out (FIFO) queue of
 *  generic items.
 *  It supports the usual <em>enqueue</em> and <em>dequeue</em> operations.
 *  <p>
 *  This implementation uses a resizing array. See {@link ResizingArrayQueue} for a version that uses a resizing array.
 *  The <em>enqueue</em>, <em>dequeue</em>, <em>size</em>, and <em>isEmpty</em>
 *  operations all take constant time in the average case.
 *  <p>
 *
 *  @author Joon Lim
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] q;           // array queue
    private int N;              // size of queue

    /**
     * Initializes an empty randomized queue
     */
    public RandomizedQueue() {
        N = 0;                       // initial size
        q = (Item[]) new Object[1];  // empty queue
    }

    /**
     * @return a boolean value indicating whether the queue is empty.
     */
    public boolean isEmpty() {
        return N == 0;
    }

    /**
     * @return the number of items on the queue (N)
     */
    public int size() {
        return N;
    }

    /**
     * add the item
     * @param item a generic item
     */
    public void enqueue(Item item) {
        if (item == null) throw new NullPointerException();
        if (N == q.length) resize(2 * q.length);
        q[N++] = item;
    }

    /**
     * delete and return a random item
     * @return the deleted item
     */
    public Item dequeue() {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        int randomNumber = StdRandom.uniform(N);
        exch(q, randomNumber, N-1);
        Item x = q[--N];
        q[N] = null;  // avoid loitering
        if (N <= q.length/4) resize(q.length/2);
        return x;
    }

    /**
     * @return (but not delete) a random item
     */
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException();
        int randomNumber = StdRandom.uniform(N);
        return q[randomNumber];

    }

    // helper for resizing the array
    private void resize(int capacity) {
        if (capacity <= 0) capacity = 1;
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++)
            copy[i] = q[i];
        q = copy;
    }

    // swap a[i] and a[j]
    private void exch(Item[] a, int i, int j) {
        Item temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    /**
     * @return an independent iterator over items in random order
     */
    public Iterator<Item> iterator() {
        return new RandomizedArrayIterator();
    }

    // an iterator, doesn't implement remove() since it's optional
    private class RandomizedArrayIterator implements Iterator<Item> {
        private int i = N;  // size
        private int[] idx;  // index (randomized index)

        public RandomizedArrayIterator() {
            idx = new int[N];
            for (int j = 0; j < i; j++)
                idx[j] = j;          // initial index assignment
            StdRandom.shuffle(idx);  // shuffle indexes using Standard Library: stdRandom.java
        }

        public boolean hasNext() {
            return i > 0;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return q[idx[--i]];
        }
    }

    private static String readTobe() throws IOException {
        File tobe = new File("algorithms/data/tobe.txt");
        BufferedReader reader = new BufferedReader(new FileReader(tobe));
        return reader.readLine();
    }

    /**
     * Unit tests the <tt>RandomizedQueue</tt> data type.
     */
    public static void main(String[] args) throws Exception {
        RandomizedQueue<String> q = new RandomizedQueue<String>();
        String[] input = readTobe().split(" ");

        for (String c: input) {
            // FIFO test (same as queue)
            String item = c;
            if (!item.equals("-")) q.enqueue(item);
            else if (!q.isEmpty()) StdOut.print(q.dequeue() + " ");
        }
        StdOut.println("(" + q.size() + " left on Queue)");
    }
}