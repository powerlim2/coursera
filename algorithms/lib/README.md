Algorithms Standard Library
===========================

Our original goal for this book was to cover the 50 algorithms that every programmer should know. 
We use the word programmer to refer to anyone engaged in trying to accomplish something with the help of a computer, including scientists, engineers, and applications developers, not to mention college students in science, engineering, and computer science.


* [Website](http://introcs.cs.princeton.edu/java/stdlib/)


Java Matrix (JAMA) Library
==========================

## Background

JAMA is a basic linear algebra package for Java. It provides user-level classes for constructing and manipulating real, dense matrices. 
It is meant to provide sufficient functionality for routine problems, packaged in a way that is natural and understandable to non-experts. 
It is intended to serve as the standard matrix class for Java, and will be proposed as such to the Java Grande Forum and then to Sun. 
A straightforward public-domain reference implementation has been developed by the MathWorks and NIST as a strawman for such a class. 
We are releasing this version in order to obtain public comment. There is no guarantee that future versions of JAMA will be compatible with this one.

A sibling matrix package, Jampack, has also been developed at NIST and the University of Maryland. 
The two packages arose from the need to evaluate alternate designs for the implementation of matrices in Java. 
JAMA is based on a single matrix class within a strictly object-oriented framework. Jampack uses a more open approach that lends itself to extension by the user. 
As it turns out, for the casual user the packages differ principally in the syntax of the matrix operations. 
We hope you will take the time to look at Jampack along with JAMA. There is much to be learned from both packages.

## Capabilities

JAMA is comprised of six Java classes: Matrix, CholeskyDecomposition, LUDecomposition, QRDecomposition, SingularValueDecomposition and EigenvalueDecomposition.
The Matrix class provides the fundamental operations of numerical linear algebra. Various constructors create Matrices from two dimensional arrays of double precision floating point numbers. 
Various gets and sets provide access to submatrices and matrix elements. The basic arithmetic operations include matrix addition and multiplication, matrix norms and selected element-by-element array operations. 
A convenient matrix print method is also included.

Five fundamental matrix decompositions, which consist of pairs or triples of matrices, permutation vectors, and the like, produce results in five decomposition classes. 
These decompositions are accessed by the Matrix class to compute solutions of simultaneous linear equations, determinants, inverses and other matrix functions. The five decompositions are:

* Cholesky Decomposition of symmetric, positive definite matrices
* LU Decomposition (Gaussian elimination) of rectangular matrices
* QR Decomposition of rectangular matrices
* Eigenvalue Decomposition of both symmetric and nonsymmetric square matrices
* Singular Value Decomposition of rectangular matrices

The current JAMA deals only with real matrices. We expect that future versions will also address complex matrices. 
This has been deferred since crucial design decisions cannot be made until certain issues regarding the implementation of complex in the Java language are resolved.
The design of JAMA represents a compromise between the need for pure and elegant object-oriented design and the need to enable high performance implementations.

* [Website](http://math.nist.gov/javanumerics/jama)
