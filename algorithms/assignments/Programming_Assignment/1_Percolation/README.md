# [Programming Assignment 1: Percolation_Without_Weighted_Quick_Union](http://coursera.cs.princeton.edu/algs4/assignments/percolation.html)

Write a program to estimate the value of the percolation threshold via Monte Carlo simulation.


## Problem Statement 

In a famous scientific problem, researchers are interested in the following question: if sites are independently set to be open with probability p (and therefore blocked with probability 1 − p), what is the probability that the system percolates? When p equals 0, the system does not percolate; when p equals 1, the system percolates. The plots below show the site vacancy probability p versus the percolation probability for 20-by-20 random grid (left) and 100-by-100 random grid (right).


## 1 What the problem suggested:

* Percolation.java
* PercolationStats.java 

### Assessment Summary

* Compilation:  PASSED
* Style:        FAILED
* Findbugs:     No potential bugs found.
* API:          PASSED
* Correctness:  22/22 tests passed
* Memory:       8/8 tests passed
* Timing:       9/9 tests passed

**Raw score: 100.00%** [Correctness: 65%, Memory: 10%, Timing: 25%, Style: 0%]


## 2 Re-implemented Percolation Data Structure
 
This is based on 2 dimensional array (for grid) and recursive calls (for checking the grid).
This is not a requirement for the assignment.

* PercolationWithoutWeightedQuickUnion.java
* [Case Study Note](http://introcs.cs.princeton.edu/java/lectures/24percolation-2x2.pdf)

