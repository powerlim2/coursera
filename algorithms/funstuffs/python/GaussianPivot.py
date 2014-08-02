# 08.03.2013
# Author : Joon
# Gaussian Elimination with Pivoting Algorithm

from numpy import *


def swapRows(v,i,j):
    ''' Swap Row of i and j in Matrix V.'''
    if len(v.shape) == 1:
        v[i],v[j] = v[j],v[i] # this only works for 1-d array.
    else:
        temp = v[i].copy()
        v[i] = v[j]
        v[j] = temp


def Gauss(A,b,tol = 1.0e-9):
    ''' Ax = b '''
    
    if A.shape[0] != A.shape[1]:
        raise Exception("Matrix is singualr!")
    
    n = len(b)
    y = b[:]
    # Set up scale factors
    s = zeros(n)
    for i in range(n):
        s[i] = max(abs(A[i,:])) # Maximum value for each row

    for k in range(0, n-1):
        # Row interchange, if needed
        p = int(argmax(abs(A[k:n,k])/s[k:n])) + k
        if abs(A[p,k]) < tol:
            raise Exception("Matrix is singular!")
        if p != k:
            swapRows(y,k,p)
            swapRows(s,k,p)
            swapRows(A,k,p)

    # Elimination
    for i in range(k+1,n):
        if A[i,k] != 0:
            lam = A[i,k] / A[k,k]
            A[i,k+1:n] = A[i,k+1:n] - lam * A[k,k+1:n]
            y[i] = y[i] - lam * y[k]

        if abs(A[n-1,n-1]) < tol:
            raise Exception("Matrix is singular")

    # Back Substitution
    for k in range(n-1,-1,-1):
        y[k] = (y[k] - dot(A[k,k+1:n],y[k+1:n]))/A[k,k]

    return y


# Check
A = identity(5) * 1/2
B = range(1,6)
x = Gauss(A,B) # Ax = B  <=>  x = A^(-1)B

from numpy.linalg import inv
print x == inv(A).dot(B)

#[ True  True  True  True  True]
