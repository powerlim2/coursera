# Quicksort 

To specify an array or sequence of values in an answer, separate the values in
the sequence by whitespace. For example, if the question asks for the first
ten powers of two (starting at 1), then the following answer is acceptable:

     1 2 4 8 16 32 64 128 256 512

If you wish to discuss a particular question and answer in the forums, please
post the entire question and answer, including the seed (which can be used by
the course staff to uniquely identify the question) and the explanation (which
contains the correct answer).


## Question 1

Give the array that results after applying the standard 2-way partitioning
algorithm from lecture to the following array:

    51 69 27 66 38 19 79 62 11 37 34 63 

Recall, in the standard 2-way partitioning algorithm, the leftmost entry is the partitioning item.

### Answer

    11 34 27 37 38 19 51 62 79 66 69 63


* Process:

```shell
      i   j    0   1   2   3   4   5   6   7   8   9  10  11 
    --------------------------------------------------------
      0  12   51  69  27  66  38  19  79  62  11  37  34  63  
      1  10   51  69  27  66  38  19  79  62  11  37  34  63  
      1  10   51  34  27  66  38  19  79  62  11  37  69  63  
      3   9   51  34  27  66  38  19  79  62  11  37  69  63  
      3   9   51  34  27  37  38  19  79  62  11  66  69  63  
      6   8   51  34  27  37  38  19  79  62  11  66  69  63  
      6   8   51  34  27  37  38  19  11  62  79  66  69  63  
      7   6   11  34  27  37  38  19  51  62  79  66  69  63  
          6   11  34  27  37  38  19  51  62  79  66  69  63  
```

## Question 2

Give the array that results after applying Dijkstra's 3-way partitioning
algorithm from lecture to the following array:

    41 41 60 32 10 53 28 41 79 88 


### Answer

    32 10 28 41 41 41 53 79 88 60

* Process

```shell
     lt   i  gt    0   1   2   3   4   5   6   7   8   9 
    ----------------------------------------------------
      0   0   9   41  41  60  32  10  53  28  41  79  88  
      0   1   9   41  41  60  32  10  53  28  41  79  88  
      0   2   9   41  41  60  32  10  53  28  41  79  88  
      0   2   8   41  41  88  32  10  53  28  41  79  60  
      0   2   7   41  41  79  32  10  53  28  41  88  60  
      0   2   6   41  41  41  32  10  53  28  79  88  60  
      0   3   6   41  41  41  32  10  53  28  79  88  60  
      1   4   6   32  41  41  41  10  53  28  79  88  60  
      2   5   6   32  10  41  41  41  53  28  79  88  60  
      2   5   5   32  10  41  41  41  28  53  79  88  60  
      3   6   5   32  10  28  41  41  41  53  79  88  60  
      3       5   32  10  28  41  41  41  53  79  88  60  
```

## Question 3

Which of the following statements about quicksort are true? Check all that apply. Unless otherwise specified, assume that quicksort refers to the recursive, randomized version of quicksort (with no extra optimizations) and uses the 2-way partitioning algorithm described in lecture.

1. The number of compares to quicksort an array of N equal keys is ~ N lg N.
2. The number of partitioning steps to quicksort an array of N items is no larger than N.
3. The primary reason to use the first entry in the array as the partitioning item instead of the last entry is to guarantee performance (probabilistically).
4. The expected number of exchanges to quicksort a uniformly random array of N distinct keys is ~ 1/3 N ln N.
5. Suppose that quicksort is modified to use an explicit stack instead of recursion and to always recur on the subarray with fewer items before the subarray with more items. Then, the order of growth of the maximize size of the stack is log N in the worst case.


### Answer

    1, 2, 4, 5

