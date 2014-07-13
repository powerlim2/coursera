# Mergesort

To specify an array or sequence of values in an answer, separate the values in
the sequence by whitespace. For example, if the question asks for the first
ten powers of two (starting at 1), then the following answer is acceptable:

     1 2 4 8 16 32 64 128 256 512

If you wish to discuss a particular question and answer in the forums, please
post the entire question and answer, including the seed (which can be used by
the course staff to uniquely identify the question) and the explanation (which
contains the correct answer).


## Question 1

Give the array that results immediately after the 7th call to merge()
when top-down mergesorting the following array of size 12:

    87 36 17 77 48 40 65 91 83 89 16 72 


### Answer

    17 36 40 48 77 87 65 83 91 89 16 72
    
    1. merge(0, 0, 1):  36 87 17 77 48 40 65 91 83 89 16 72 
    2. merge(0, 1, 2):  17 36 87 77 48 40 65 91 83 89 16 72 
    3. merge(3, 3, 4):  17 36 87 48 77 40 65 91 83 89 16 72 
    4. merge(3, 4, 5):  17 36 87 40 48 77 65 91 83 89 16 72 
    5. merge(0, 2, 5):  17 36 40 48 77 87 65 91 83 89 16 72 
    6. merge(6, 6, 7):  17 36 40 48 77 87 65 91 83 89 16 72 
    7. merge(6, 7, 8):  17 36 40 48 77 87 65 83 91 89 16 72 


## Question 2

The column on the left contains the original input of 12 strings to be sorted;
the column on the right contains the strings in sorted order; the other 4 columns contain the
contents at some intermediate step during one of the 2 mergesorting algorithms listed below.

    leaf   cost   cost   cost   cost   cost   
    fifo   fifo   fifo   fifo   fifo   fifo   
    load   leaf   leaf   leaf   leaf   find   
    cost   load   load   load   load   hash   
    null   null   hash   null   hash   java   
    swim   swim   java   swim   java   leaf   
    java   hash   null   hash   null   load   
    hash   java   swim   java   swim   loop   
    type   type   loop   type   find   null   
    loop   find   type   loop   loop   root   
    root   loop   find   root   root   swim   
    find   root   root   find   type   type   
    ----   ----   ----   ----   ----   ----   
     0      ?      ?      ?      ?      3     


Match up each column with the corresponding mergesorting algorithm from the given list:

    0. Original input
    1. Top-down Mergesort (standard recursive version)
    2. Bottom-up Mergesort (nonrecursive version)
    3. Sorted

You may use an algorithm more than once. Your answer should be a sequence of 6 integers between
0 and 3 (starting with 0 and ending with 3) and with each integer separated by whitespace.

Hint: think about algorithm invariants. Do not trace code.


### Answer

    0 1 2 1 2 3


## Question 3

Which of the following statements about mergesort are true? Check all that apply. 
Unless otherwise specified, assume that mergesort refers to the pure recursive (top-down) version of mergesort (with no optimizations), 
using the merging subroutine described in lecture.

1. It is possible to design a compare-based algorithm to merge two sorted arrays, each of size N, with no more than N compares.
2. The number of compares in mergesort depends only on the size of the array N (and not on the items in the array).
3. Any compare-based sorting algorithm requires at least ~ N lg N compares (in the worst case) to sort an array of N items.
4. When merging two subarrays, the main reason for taking equal keys from the left subarray before the right subarray is to ensure stability.
5. An embedded systems programmer might prefer bottom-up mergesort to (top-down) mergesort if they are working in an environment where recursion is expensive or unavailable.


### Answer

    2, 3, 4, 5