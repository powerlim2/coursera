# [Programming Assignment 1: PercolationWithoutWeightedQuickUnion](http://coursera.cs.princeton.edu/algs4/assignments/percolation.html)

Write a program to estimate the value of the percolation threshold via Monte Carlo simulation.


## Problem Statement 

In a famous scientific problem, researchers are interested in the following question: if sites are independently set to be open with probability p (and therefore blocked with probability 1 − p), what is the probability that the system percolates? When p equals 0, the system does not percolate; when p equals 1, the system percolates. The plots below show the site vacancy probability p versus the percolation probability for 20-by-20 random grid (left) and 100-by-100 random grid (right).


## Two Type of Programs

### 1 What the problem suggested:

* Percolation.java
* PercolationStats.java 

### Result Summary

*  memory usage

-                 N        bytes
*-------------------------------------------
=> passed       64        37040         
=> passed      256       590000         
=> passed      512      2359472         
=> passed     1024      9437360         


*  executing PercolationStats with reference Percolation

For each N, a percolation object is generated and sites are randomly opened
until the system percolates. If you do not pass the correctness tests, these
results may be meaningless.

-                 N   seconds       union()   2 * connected() + find()       constructor
*--------------------------------------------------------------------------------------------
=> passed        8     0.00           43                   382                   1         
=> passed       32     0.01          585                  4612                   1         
=> passed      128     0.03         9075                 70594                   1         
=> passed      512     0.11       148553               1156412                   1         
=> passed     1024     0.25       587689               4558796                   1         



### 2 Re-implemented Percolation Data Structure
 
This is based on 2 dimensional array (for grid) and recursive calls (for checking the grid).

* PercolationWithoutWeightedQuickUnion.java
* [Case Study Note](http://introcs.cs.princeton.edu/java/lectures/24percolation-2x2.pdf)

