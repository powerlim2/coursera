# Analysis of Algorithms

To specify an array or sequence of values in an answer, separate the values in
the sequence by whitespace. For example, if the question asks for the first
ten powers of two (starting at 1), then the following answer is acceptable:

     1 2 4 8 16 32 64 128 256 512

If you wish to discuss a particular question and answer in the forums, please post
the entire question and answer, including the seed (which can be used by the course
staff to uniquely identify the question) and the explanation (which contains the
correct answer).


## Question 1

Suppose that you time a program as a function of N and produce
the following table.
    
            N   seconds
    -------------------
          128     0.000
          256     0.001
          512     0.004
         1024     0.026
         2048     0.148
         4096     0.822
         8192     4.873
        16384    28.811
        32768   165.896
        65536   949.946
       131072  5530.615


Estimate the order of growth of the running time as a function of N.
Assume that the running time obeys a power law T(N) ~ a N^b. For your
answer, enter the constant b. Your answer will be marked as correct
if it is within 1% of the target answer - we recommend using
two digits after the decimal separator, e.g., 2.34.


### Answer

    0.765074509


Approach (Use Google Spreadsheet for quick calculation): 

    N       seconds     Growth          Lg(Growth)
    ------------------------------------------------
    128	    0		
    256	    0.001		
    512	    0.004	    4	
    1024	0.026	    6.5	
    2048	0.148	    5.692307692	    2.509013647
    4096	0.822	    5.554054054	    2.473541218
    8192	4.873	    5.928223844	    2.567599924
    16384	28.811	    5.912374307	    2.563737608
    32768	165.896	    5.758078512	    2.52558746
    65536	949.946	    5.726153735	    2.517566404
    131072	5530.615	5.822030936	    2.541522506


## Question 2

What is the order of growth of the worst case running time of the following code fragment
as a function of N?

    int sum = 0;
    for (int i = 1; i <= N; i++)
        for (int j = 1; j <= i*i; j++)
            sum++;


### Answer

    N^3

Approach: 

    for (int i = 1; i <= N; i++)            // N
        for (int j = 1; j <= i*i; j++)      // N^2 @ worst case
            sum++;


## Question 3

Given the following definition of a MysteryBox object: 

    public class MysteryBox {
        private boolean x0;
        private double y0, y1, y2;
        private int z0, z1, z2, z3;
        private long[] a = new long[152];
    
        ...
    }

Using the 64-bit memory cost model from lecture, how many bytes does each object of type MysteryBox use?


### Answer

    1312

Approach:

```java

public class MysteryBox {                     //   16 (object overhead)
    private boolean x0;                       //    1 (1 boolean)
    private double y0, y1, y2;                //   24 (3 double)
    private int z0, z1, z2, z3;               //   16 (4 int)
    private long[] a = new long[152];         //    8 (reference to array)
                                              // 1240 (long array of size 152)
    ...                                             7 (padding to round up to a multiple of 8)
}                                                ----
                                                 1312
```

