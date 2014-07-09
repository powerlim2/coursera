# *Merge Sort (Week3)*

Date: 07.08.2014

## Basic Plan

* Divide array into two halves
* Recursively sort each half.
* Merge two halves.


## Abstract in-place merge demo

### Goal

Given two sorted subarrays `a[lo]` to `a[mid]` and `a[mid+1] to `a[hi]`, replace with sorted subarray `a[lo]` to `a[hi]`.


## Merging: Java implementation

```java

private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
    assert isSorted(a, lo, mid);    // precondition: a[lo..mid] sorted
    assert isSorted(a, mid+1, hi);  // precondition: a[mid+1..hi] sorted
    
    for (int k = lo; k <= hi; k++)
        aux[k] = a[k];              // copy
    
    int i = lo, j = mid+1;          // pointers
    for (int k = lo; k <= hi; k++) {
        if      (i > mid)               a[k] = aux[j++];
        else if (j > hi)                a[k] = aux[i++];
        else if (less(aux[j], aux[i]))  a[k] = aux[j++];
        else                            a[k] = aux[i++];
    }
    
    assert isSorted(a, lo, hi);     // precondition: a[lo..hi] sorted
}
```

### Assertion

Statement to test assumptions about your program

* Helps detect bugs
* Documents code.

**Java Assert Statement**: throws exception unless boolean condition is true.

```java
assert isSorted(a, lo, hi);
```

Can enable to disable at runtime -> No cost in production code.

    java -ea Myprogram  // enable assertions
    java -da Myprogram  // disable assertions (default)

Best Practice: Use assertions to check internal invariants. Assume assertions will be disabled in production code.


## Sort: Java implementation

```java

public class Merge {

    private static void merge(...) {
        /* as before */
    }
    
    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid+1, hi);
        merge(a, aux, lo, mid, hi);
    }
    
    public static void sort(Comparable[] a) {   // actual sort
        aux = new Comparable[a.length];
        sort(a, aux, 0, a.length - 1);
    }
}
```

### Mergesort: Trace

![trace](misc/trace.png)


## Mergesort: empirical analysis

Running time estimates:

* Laptop executes 10^8 compares / second.
* Supercomputer executes 10^12 compares / second.

![ea](misc/ea.png)

**Bottom line**: Good algorithm are better than supercomputers.


### Number of compares and Array accesses

**Proposition**. Mergesort uses at most N*lg(N) compares and 6*N*lg(N) array accesses to sort any array of size N.

Proof Sketch. The number of compares C(N) and array accesses A(N) to mergesort an array of size N satisfy the recurrences:

    C(N) <= C([N/2])  +  C([N/2])  +  N for N > 1, with C(1) = 0.
                |           |         |
            left half   right half   merge
                |           |         |
    A(N) <= A([N/2])  +  A([N/2])  +  6N for N > 1, with A(1) = 0.


We solve the recurrence when N is a power of 2.  // result holds for all N

### Divide and Conquer recurrence

**Proposition**. If D(N) satisfies ProD(N) = 2D(N/2) + N for N > 1, with D(1) = 0, then D(N) = N*lg(N).

![pf](misc/pf.png)

*Proof 2*

    D(N) = 2*D(N/2) + N
    D(N) / N = 2*D(N/2) / N + 1
             = D(N/2) / (N/2) + 1
             = D(N/4) / (N/4) + 1 + 1
             = D(N/8) / (N/8) + 1 + 1 + 1
             ...
             = D(N/N) / (N/N) + 1 + 1 + ... + 1     // D(1) = 0
             = lg(N)


### Mergesort analysis: Memory

**Proposition**. Mergesort uses extra space proportional to N.

pf. The array aux[] needs to be of size N for the last merge.

Def. A sorting algorithm is in-place if it uses <= c*log(N) extra memory.
E.g. Insertion sort, selection sort, shellsort.


## Practical Improvements

**Use insertion sort for small subarrays.**
* Mergesort has too much overhead for tiny subarrays.
* Cutoff to insertion sort for ~ 7 items.

```java

private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
    if (hi <= lo + CUTOFF - 1) {
        Insertion.sort(a, lo, hi);
        return;
    }
    int mid = lo + (hi - lo) / 2;
    sort(a, aux, lo, mid);
    sort(a, aux, mid+1, hi);
    merge(a, aux, lo, mid, hi);
}
```

**Stop if already sorted.**
* Is biggest item in first half <= smallest item in second half?
* Helps for partially-ordered arrays.

```java

private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
    if (hi <= lo) return;
    int mid = lo + (hi - lo) / 2;
    sort(a, aux, lo, mid);
    sort(a, aux, mid+1, hi);
    if (!less(a[mid+1], a[mid])) return;    // check for order of array
    merge(a, aux, lo, mid, hi);
}
```

**Eliminate the copy to the auxiliary array.** 
Save time (but not space) by switching the role of the input and auxiliary array in each recursive call.

```java

private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) { 
    int i = lo, j = mid+1;          // pointers
    for (int k = lo; k <= hi; k++) {
        if      (i > mid)               aux[k] = a[j++];
        else if (j > hi)                aux[k] = a[i++];
        else if (less(aux[j], aux[i]))  aux[k] = a[j++];
        else                            aux[k] = a[i++];
    }    
}

private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
    if (hi <= lo) return;
    int mid = lo + (hi - lo) / 2;
    sort(aux, a, lo, mid);
    sort(aux, a, mid+1, hi);        // sort(a) initializes aux[] and sets
    merge(a, aux, lo, mid, hi);     // aux[1] = a[1] for each 1.
}
```
