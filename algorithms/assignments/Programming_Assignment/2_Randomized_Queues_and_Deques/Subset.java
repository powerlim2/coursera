/*************************************************************************
 *  Compilation:  javac Subset.java
 *  Execution:    java Subset < input.txt
 *  Dependency:   RandomizedQueue.java, StdIn.java, StdOut.java
 *
 *  % echo "A B C D E F G H I" | java Subset 3
 *  D
 *  H
 *  B
 *************************************************************************/

/**
 *  The <tt>Subset Client</tt> class represents a client program that takes a command-line integer k.
 *  Reads in a sequence of N strings from standard input using StdIn.readString() and prints out exactly k of them,
 *  uniformly at random. Each item from the sequence can be printed out at most once.
 *  Assume that k ≥ 0 and no greater than the number of string N on standard input.
 *
 *  @author Joon Lim
 */

public class Subset {
    public static void main(String[] args) {
        int N;                                  // size of queue
        int k = Integer.parseInt(args[0]);      // # of Strings to print out
        RandomizedQueue<String> q = new RandomizedQueue<String>();
        // read
        while (!StdIn.isEmpty()) {
            q.enqueue(StdIn.readString());
        }
        // write
        N = q.size();
        int i = 0;
        while (k <= N && k > 0 && i < k) {
            StdOut.println(q.dequeue());
            i++;
        }
    }
}