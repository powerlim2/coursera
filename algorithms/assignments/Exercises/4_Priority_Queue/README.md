# Priority Queue

To specify an array or sequence of values in an answer, separate the values in
the sequence by whitespace. For example, if the question asks for the first
ten powers of two (starting at 1), then the following answer is acceptable:

     1 2 4 8 16 32 64 128 256 512

If you wish to discuss a particular question and answer in the forums, please
post the entire question and answer, including the seed (which can be used by
the course staff to uniquely identify the question) and the explanation (which
contains the correct answer).


## Question 1

Give the sequence of the 13 keys in the array that results after inserting the sequence of 3 keys

    18 62 43 

into the following maximum-oriented binary heap of size 10:

    86 73 51 66 28 33 40 29 55 21 


### Answer

    86 73 62 66 28 51 40 29 55 21 18 33 43 


        86 73 51 66 28 33 40 29 55 21 
    18: 86 73 51 66 28 33 40 29 55 21 18 
    62: 86 73 62 66 28 51 40 29 55 21 18 33 
    43: 86 73 62 66 28 51 40 29 55 21 18 33 43 

## Question 2

Give the sequence of the 7 keys in the array that results after performing 3 successive delete-the-max
operations on the following maximum-oriented binary heap of size 10:

    88 82 83 64 67 27 48 17 63 45 


### Answer

    67 64 48 17 63 27 45

    
                    88 82 83 64 67 27 48 17 63 45 
    [ 88 deleted ]  83 82 48 64 67 27 45 17 63 
    [ 83 deleted ]  82 67 48 64 63 27 45 17 
    [ 82 deleted ]  67 64 48 17 63 27 45 

## Question 3

Which of the following statements about priority queues are true? Check all that apply. Unless otherwise specified, assume that the binary heap implementation is the one from lecture (e.g., max-oriented and using 1-based indexing).

1. Given a binary heap with N distinct keys, the result (ignoring any array resizing) of deleting the maximum key and then inserting that key back into the heap yields the original binary heap.
2. It is easy to modify our implementation of a binary heap to support finding (but not deleting) the minimum key in logarithmic time.
3. The main reason that we can use an array to represent the heap-ordered tree in a binary heap is because the tree is heap-ordered.
4. Let a[] be a binary heap that contains the N distinct integers 1, 2, ..., N. Then, key N must be in a[1] and key N-1 must be in either a[2] or a[3].
5. The height of a complete binary tree with N > 0 nodes is exactly floor(lg N).


### Answer

    2, 4, 5

