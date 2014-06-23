# Union Find


To specify an array or sequence of values in an answer, separate the values in
the sequence by whitespace. For example, if the question asks for the first
ten powers of two (starting at 1), then the following answer is acceptable:

    1 2 4 8 16 32 64 128 256 512

If you wish to discuss a particular question and answer in the forums, please post
the entire question and answer, including the seed (which can be used by the course
staff to uniquely identify the question) and the explanation (which contains the
correct answer).


## Question 1

Give the `id[]` array that results from the following sequence of 6 union
operations on a set of 10 items using the quick-find algorithm.

    2-3 3-7 3-6 9-1 4-0 8-7 

Recall: our quick-find convention for the union operation p-q is to change `id[p]`
(and perhaps some other entries) but not `id[q]`.


### Answer

    0 1 6 6 0 5 6 6 6 1
 
Check out the question1.java


## Question 2


