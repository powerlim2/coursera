#07.09.2013
#Author: Joon Lim

# insertion sort algorithm
A = [31, 41, 59, 26, 41, 58]

def insertion_sort(A):
	''' this is the insertion sort algorithm'''
	for j in range(1,len(A)):
		key = A[j]
		i = j - 1
		while ((i+1) > 0) & (A[i] > key):
			A[i+1] = A[i]
			i -= 1
		A[i+1] = key
	return A



# TIP : more practical way using numpy
A = np.array([31, 41, 59, 26, 41, 58])

A.sort() # quick sort - O(nlogn)
A.sort(kind='mergesort') # merge sort - O(nlogn)

