# Geometric Searching Applications of BSTs

To specify an array or sequence of values in an answer, separate the values in
the sequence by whitespace. For example, if the question asks for the first
ten powers of two (starting at 1), then the following answer is acceptable:

     1 2 4 8 16 32 64 128 256 512

If you wish to discuss a particular question and answer in the forums, please
post the entire question and answer, including the seed (which can be used by
the course staff to uniquely identify the question) and the explanation (which
contains the correct answer).

# Question 1

Suppose that you run the orthogonal line segment intersection algorithm from lecture
on the following set of segments:

    A ( 1, 12)  ->  (19, 12)  [ horizontal ]
    B (13, 14)  ->  (13, 16)  [ vertical   ]
    C (10,  6)  ->  (10, 17)  [ vertical   ]
    D (12,  2)  ->  (17,  2)  [ horizontal ]
    E (16,  1)  ->  (18,  1)  [ horizontal ]
    F ( 7,  9)  ->  ( 7, 14)  [ vertical   ]
    G ( 5,  0)  ->  (14,  0)  [ horizontal ]
    H ( 6,  3)  ->  (15,  3)  [ horizontal ]

Give the horizontal line segments in the BST (sorted in increasing order of y-coordinate) just before
the sweep-line algorithm processes the vertical line segment B.

Your answer should be a sequence of letters, separated by whitespace.

## Answer

    G D H A

Here are the sweep line events (sorted by x-coordinate):

    A G H F C D B G H E D E A 
    
    Here is a trace of the sweep-line algorithm after each event.
      *  Horizontal segment (left  endpoint): display BST (sorted by y-coordinate) after adding line segment.
      *  Horizontal segment (right endpoint): display BST (sorted by y-coordinate) after removing line segment.
      *  Vertical segment: display result of range search.
    
    A:  A 
    G:  G A 
    H:  G H A 
    F:  range search [ intersects A ]
    C:  range search [ intersects A ]
    D:  G D H A 
    B:  range search [ no intersections ]
    G:  D H A 
    H:  D A 
    E:  E D A 
    D:  E A 
    E:  A 
    A:  

# Question 2

What is the level-order traversal of the kd-tree that results after inserting
the following sequence of points into an initially empty tree?

    A (0.18, 0.63)
    B (0.96, 0.33)
    C (0.30, 0.65)
    D (0.26, 0.25)
    E (0.32, 0.92)
    F (0.16, 0.37)
    G (0.35, 0.69)
    H (0.08, 0.61)

Your answer should be a sequence of letters, starting with A and separated by single spaces.

Recall that our convention is to subdivide the region using the x-coordinate at even levels
(including the root) and using the y-coordinate at odd levels. Also, we use the left subtree
for points with smaller x- or y-coordinates.

## Answer

    A F B H D C E G

Here is the level-order traversal of the kd-tree after each insertion:

    A:  A 
    B:  A B 
    C:  A B C 
    D:  A B D C 
    E:  A B D C E 
    F:  A F B D C E 
    G:  A F B D C E G 
    H:  A F B H D C E G 

# Question 3

Consider an interval search tree containing the set of 8 intervals

    A [19, 30]
    B [33, 38]
    C [ 1,  4]
    D [26, 36]
    E [20, 39]
    F [27, 32]
    G [21, 40]
    H [28, 37]

and whose level-order traversal is:  A C B D E F G H.

Suppose that you use the search algorithm described in lecture to search for
any *one* interval that intersects [11, 16]. What is the sequence of
intervals in the tree that are checked for intersection with the query interval?

Your answer should be a sequence of letters, starting with A, separated by whitespace.

## Answer

    A B D E G

Here is a trace of the interval intersection query for [11, 16]:

```shell
    A: go right (because max endpoint in left subtree <  11)
    B: go left  (because max endpoint in left subtree >= 11)
    D: go left  (because max endpoint in left subtree >= 11)
    E: go right (becaue left subtree is empty)
    G: go right (becaue left subtree is empty)
    no intersection
```