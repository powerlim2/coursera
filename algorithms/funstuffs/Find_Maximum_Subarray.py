# 07. 13. 2013
# Author: Joon Lim
# Maximum valued Subarray Finder


# Using Brute-Force algorithm - O(n^2)
def BFMS(A):
    max_value = 0
    max_range = 0,0
    for i in range(len(A)):
        V = 0
        index = []
        for j in range(i,len(A)):
            V += A[j]
            if V > max_value:
                max_range = i,j
                max_value = V
    return max_range[0],max_range[1],max_value


# The Maximum Value Subarray for a list
A = [13,-3,-25,20,-3,-16,-23,18,20,-7,12,-5,-22,15,-4,7]

BFMS(A)
#(7, 10, 43)




# Using recursive algorithm — O(nlgn)
def MCS(A, middle, low, high):
    '''Find the Maximum Crossing Subarray of an Array'''
    left_sum = -sys.maxint
    sum = 0
    i = middle
    while i >= low:
        sum += A[i]
        if sum > left_sum:
            left_sum = sum
            max_left = i
        i -= 1
    right_sum = -sys.maxint
    sum = 0
    i = middle + 1
    while i <= high:
        sum += A[i]
        if sum > right_sum:
            right_sum = sum
            max_right = i
        i += 1
    return max_left,max_right,left_sum+right_sum


def MS(A, low, high):
    '''Find the Maxmium Subarray of a specified portion of an Array'''
    if low == high: # base case
        return low,high,A[low]
    else:
        middle = (high+low)/2
        left_low,left_high, left_value = MS(A,low,middle)
        right_low,right_high, right_value = MS(A,middle+1,high)
        cross_low, cross_high, cross_value = MCS(A, middle,low, high)
        if left_value >= right_value and left_value >= cross_value:
            return left_low,left_high,left_value
        elif right_value >= left_value and right_value >= cross_value:
            return right_low,right_high,right_value
        else:
            return cross_low,cross_high,cross_value


# The Maximum Value Subarray for a list

MS(A,0,len(A)-1)
# (7, 10, 43)


#In [3]: BFMS(A) == MS(A,0,len(A)-1)
#Out[3]: True

