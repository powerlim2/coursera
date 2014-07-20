# Binary search Trees

To specify an array or sequence of values in an answer, separate the values in
the sequence by whitespace. For example, if the question asks for the first
ten powers of two (starting at 1), then the following answer is acceptable:

     1 2 4 8 16 32 64 128 256 512

If you wish to discuss a particular question and answer in the forums, please
post the entire question and answer, including the seed (which can be used by
the course staff to uniquely identify the question) and the explanation (which
contains the correct answer).


## Question 1

Give the level-order traversal of the BST that results after inserting
the following sequence of keys into an initially empty BST:

    22 92 36 83 44 74 23 77 64 20 


### Answer

    22 20 92 36 23 83 44 74 64 77


Here is the level-order traversal of the BST after each insertion: 

    22:  22 
    92:  22 92 
    36:  22 92 36 
    83:  22 92 36 83 
    44:  22 92 36 83 44 
    74:  22 92 36 83 44 74 
    23:  22 92 36 23 83 44 74 
    77:  22 92 36 23 83 44 74 77 
    64:  22 92 36 23 83 44 74 64 77 
    20:  22 20 92 36 23 83 44 74 64 77 



## Question 2

Given a BST whose level-order traversal is:

    89 66 99 39 74 13 49 83 33 44 84 88 

What is the level-order traversal of the resulting BST after Hibbard deleting
the following three keys?

    99 49 66 

### Answer

    89 74 39 83 13 44 84 33 88


Here is the level-order traversal of the BST after each deletion:

    99:  89 66 39 74 13 49 83 33 44 84 88 
    49:  89 66 39 74 13 44 83 33 84 88 
    66:  89 74 39 83 13 44 84 33 88 

## Question 3

Which of the following statements about binary search and binary search trees are true? Check all that apply. 
Unless otherwise specified, assume that the binary search and binary search tree implementations are the one from lecture.

1. One reason for storing the subtree counts in each node is to efficiently support both the floor and the ceiling operations.
2. Given a BST, it is possible to obtain a sorted list of the keys in linear time.
3. Postorder traversal of a BST gives the keys in descending order.
4. Given the inorder traversal of a BST containing N distinct keys, it is possible to reconstruct the shape of the BST.
5. Preorder traversal of a BST gives the keys in ascending order.

### Answer

    2

