# *Dynamic Connectivity (Week1: Union-Find)*

Date: 06.18.2014

## Steps to developing a usable algorithm 

1. Model the problem.
2. Find an algorithm to solve it.
3. Is it fast enough? Does it fit in memory? Efficiency.
4. If not, figure out why.
5. Find a way to address the problem.
6. Iterate steps 1 through 5 until satisfied.


## Definitions

* Union command: connect to objects
* Find/connected query: is there a path connecting two objects
* Assume "Is connect to" is equivalent: 
    * *P* is connected to *P* (Reflective)
    * If *P* is connected to *Q*, then *Q* is connected to *P*. (Symmetric)
    * If *P* <-> *Q* and *Q* <-> *R* => *P* <-> *R*. (Transitive) 
* Connected Components: Maximal set of objects that are mutually connected.
    * Find query: check if two objects are in the same component.
    * Union Command: Replace components containing two objects with their union.

## Union-Find data structure

    UF (int N)
    void union(int p, int q)
    boolean connected(int p, int q)


## Dynamic-Connectivity Client

### input.txt

```
10
4 3
3 8
6 5
9 4
2 1
8 9
5 0
7 2
6 1
1 0
6 7
```

### reader.java
```java
   public static void main(String[] args) {
        int N = StdIn.readInt();
        UF uf = new UF(N);
        
        while(!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            
            if(!uf.connected(p, q)) {
                uf.union(p, q);
                StdOut.println(p + ' ' + q);
            }
        }
    }
```



# Quick Find

## Data Structure

* integer array `id[]` of size N.
* interpretation: *p* and *q* are connected if and only if they have the same id.

#### Find: Check if p and q have the same id
#### Union: To merge components containing *p* and *q*, change all entries whose id equals `id[p]` to `id[q]`

## Quick-Find Java Implementation

```java
    public class QuickFindUF {
      private id[] id;
      
      public QuickFindUF(int N) {
         id = new int[N];
         for(int i=0; i<N; i++) {
          id[i] = i;
         }
      }
    
      public boolean connected(int p, int q) {
          return id[p] == id[q];
      }
    
      public void union(int p, int q) {
          int pid = id[p];
          int qid = id[q];
          for(int i=0; i<id.length; i++) {
            if(id[i] == pid) {
              id[i] = qid;
            }
          }
      }
    }
```

### Cost Model (Number of Array Accesses)

Order of growth of number of array accesses.

| Algorithm     | Initialize    | Union | Find  |
| ------------- |:-------------:|:-----:|:-----:|
| Quick-Find    |       N       |   N   |   1   |


* Quick-Find
    * Defect: Union too expensive O(N^2)
    * E.g. take N^2 array accesses to process sequence of N union commands on N objects.
    * Quadratic algorithm don't scale with technology: computer improves at 10X => you want to solve 10X larger problem.
    With quadratic algorithm, 10^2 = 100 / 10 = 10 X slower.

Maximum number of `id[]` array entries that can change during one call to union when using the quick-find data structure on
N elements is *N-1*. In the worst case, all of the entries except `id[q]` are changed from `id[p]` to `id[q]`.



# Quick-Union (Lazy approach)

## Data Structure (Forest)

* Integer array id[] of size N
* Interpretation: id[i] is parent of i
* Root of i is id[ id[ id[ id[...]]]]

#### Find: Check if *p* and *q* have the same root
#### Union: To merge components containing *p* and *q* set the id of *p*'s to the id of *q*'s root


## Quick-Find Java Implementation

```java
    public class QuickFindUF {
          private id[] id;
        
          public QuickFindUF(int N) {
             id = new int[N];
             
             for(int i=0; i<N; i++) {
              id[i] = i;
             }
          }
        
          private int root(int i) {
            while(i != id[i]) i = id[i];
            return i;
          }
        
          public boolean connected(int p, int q) {
            return root(p) == root(q);
          }
        
          public void union(int p, int q) {
            int i = root(p);
            int j = root(q);
            id[i] = j;
          }
    }
```

### Cost Model

* Defect: Trees can get tall. Find too expensive O(N)

| Algorithm     | Initialize    | Union | Find  |
| ------------- |:-------------:|:-----:|:-----:|
| Quick-Find    |       N       |   N   |   1   |
| Quick-Union   |       N       |   N   |   N   |



# Quick-Union Improvements

## 1. Weighted Quick-Union

* Modify quick-union to avoid tall trees
* Keep track of size of each tree (number of objects)
* Balance by linking root of smaller tree to root of larger tree
  (reasonable alternative: Union by height or "rank")
    * Always put the smaller tree lower

## Data Structure

Same as the Quick-Union, but maintain extra array `sz[i]` to count number of objects in tree rooted at i.

### Find: Identical to Quick-Union

    return root(p) == root(q);

### Union: Modify Quick-Union to:
* Link root of smaller tree to root of larger tree
* Update the `sz[]` array.


    int i = root(p);
    int j = root(q);
    if (i == j) return;
    if (sz[i] < sz[j]) {
        id[i] = j;
        sz[j] += sz[i];
    } else {
        id[j] = i;
        sz[i] += sz[j];
    }

### running time

* Find: takes time proportional to depth of *p* and *q*.
* Union: takes constant time, given roots.
* Proposition: Depth of any node *x* is at most log(N)


## Weighted Quick-Find Java Implementation

```java
    public class QuickFindUF {
      private id[] id;
      public sz[] id;
    
      public QuickFindUF(int N) {
         id = new int[N];
         for(int i=0; i<N; i++) {
          id[i] = i;
         }
      }
    
      private int root(int i) {
        while(i != id[i]) {
          i = id[i];
        return i;
      }
    
      public boolean connected(int p, int q)
      {
        return root(p) == root(q);
      }
    
      public void union(int p, int q)
      {
        int i = root(p);
        int j = root(q);
        if (sz[i] < sz[j])
        {
          id[i] = j;
          sz[j] += sz[i];
        }
        else
        {
          id[j] = i;
          sz[i] += sz[j];
        }
      }
    }
```


# Quick union with path compression

Quick Union with path compression. Just after computing the root of p, set the id of each examined node to point to that root.


1. Two-pass implementation: add second loop to `root()` to the `id[]` of each examined node to the root.
2. Simpler one pass variant: make every other node in path point to its grandparent (thereby having path length).


## Weighted Union-Fined with Path Compression Java Implementation

```java
    public class QuickFindUF {
      private id[] id;
      public sz[] id;
    
      public QuickFindUF(int N) {
         id = new int[N];
         for(int i=0; i<N; i++) {
          id[i] = i;
         }
      }
    
      private int root(int i) {
        while(i != id[i]) {
          id[i] = id[id[i]]; // Path Compression! 
          i = id[i];
        }
        return i;
      }
    
      public boolean connected(int p, int q) {
        return root(p) == root(q);
      }
    
      public void union(int p, int q) {
        int i = root(p);
        int j = root(q);
        if (sz[i] < sz[j]) {
          id[i] = j;
          sz[j] += sz[i];
        } else {
          id[j] = i;
          sz[i] += sz[j];
        }
      }
    }
```

In practice, keeps tree almost completely flat. 

## Amortized analysis.

Proposition. `[Hopecroft-Ulman, Tarjan]` Starting from an empty data structure, any sequence of *M* union-find operations on *N* objects makes less than equal to c(N + Mlg(N)) array accesses.

### Cost (Worst Case) 
* N+Mlg*N where lg* is the number of times you need to take the lg to get 1 (~5)



# Union-Find Applications

- PercolationWithoutWeightedQuickUnion
- Games (Go, Hex)
- Dynamic Connectivity
- Least common ancestor
- Equivalence of finite state automata
- Hoshen-Kpelman algorithm in physics
- Kruskal's minimum spanning tree algorithm
- Matlab's bwlabel() function in image processing
- etc


# PercolationWithoutWeightedQuickUnion

A model of many physical systems:
1.  N by N grid of sets.
2.  Each site is open with probability p (or blocked 1 - p).
3.  System percolates iff top and bottom are connected by open sites.


## PercolationWithoutWeightedQuickUnion phase transition 

When N is large, theory guarantees a sharp threshold p*
- p > p*: almost certainly percolates
- p < p*: almost certainly does not percolate


IMAGE HERE!



## Find p* by Monte Carlo Simulation

1. Initialize by N by N whole grid to be blocked.
2. Declare random sites open util top connected to bottom.
3. Vacancy percentage estimates p*.

## How to check if N by N system percolates?

- Create an object for each site and name them 0 to (N^2 - 1).
- Sites are in same component if connected by open sites.
- Percolates if and only if any site on bottom row is connected to site on top row.

### Clever Trick: 
- Introduce 2 virtual sites (and connections to top and bottom)
- Percolates iff virtual top site is connected to virtual bottom site

## How to open a new site?
- Connect newly opened site to all of its adjacent open sites.
