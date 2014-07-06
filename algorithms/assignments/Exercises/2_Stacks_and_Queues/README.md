# Stacks and Queues

To specify an array or sequence of values in an answer, separate the values in
the sequence by whitespace. For example, if the question asks for the first
ten powers of two (starting at 1), then the following answer is acceptable:

     1 2 4 8 16 32 64 128 256 512

If you wish to discuss a particular question and answer in the forums, please
post the entire question and answer, including the seed (which can be used by
the course staff to uniquely identify the question) and the explanation (which
contains the correct answer).


## Question 1

Suppose that an **intermixed sequence of 10 push and 10 pop** operations are performed
on a **LIFO stack**. The pushes push the letters 0 through 9 in order; the pops
print out the return value. Which of the following output sequence(s) could occur?
Check all that apply.


### Answer
    
    6 5 4 3 2 1 0 7 8 9
     
    3 4 5 8 9 7 6 2 1 0
     
    0 3 7 6 5 4 2 9 8 1


## Question 2

Suppose that an **intermixed sequence of 10 enqueue and 10 dequeue** operations are performed
on a **FIFO queue**. The enqueues add the letters 0 through 9 in order; the dequeues
print out the return value. Which of the following output sequence(s) could occur?
Check all that apply.

### Answer

    0 1 2 3 4 5 6 8 7 9

By definition, it's first in first out.


## Question 3

Consider an object of type GenericMysteryBox<Integer> that stores N items of type Integer
in a generic doubly-linked list of N nodes, referenced by first.

    public class GenericMysteryBox<Item> {
        private Node first;

        private class Node {
            private Item item;
            private Node next;
            private Node prev;
        }

        ...
    }


Using the 64-bit memory cost model from the lecture, how many bytes does it use as a function of N?
Include all memory referenced by the object and use tilde notation to simplify your answer.
For example, enter ~ 4N if the number of bytes as a function of N is 4N + 32.

Hint: an object of the wrapper type Integer uses 24 bytes.

### Answer

    ~ 72N

```java

public class GenericMysteryBox<Item> {        //       16 (object overhead)
    private Node first;                       //        8 (reference)

    private class Node {                      //       16 (object overhead)
                                              //        8 (inner class overhead)
        private Item item;                    //        8 (reference to Integer)
                                              //       24 (Integer)
        private Node next;                    //        8 (reference)
        private Node prev;                    //        8 (reference)
    }                                             -------
                                                 24 + 72N
    ...
}
```

