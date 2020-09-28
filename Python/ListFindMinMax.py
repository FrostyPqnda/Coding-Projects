'''
Created on Sept 28, 2020

@author: brian
'''

"""
Python project that uses the simple Bubble Sort
algorithm to sort the array in order of least to
greatest or the reverse in order to find the
minimum or maximum value of a list
"""
# Finds the minimum value of a list
def findMin(arr):
    length = len(arr)
    if len(arr) == 1:
        return arr[0]
    """
    Sorts the list using the Bubble Sort algorithm
    Bubble Sort: simple sorting algorithm that swaps 
    the adjacent elements repeatedly until they are in
    the right order.

    Sorts the list from least to greatest
    """
    for x in range(length - 1):
        for y in range(length - x - 1):
            if arr[y] > arr[y + 1]:
                arr[y], arr[y + 1] = arr[y + 1], arr[y]
    # Removes the last elements of the array until
    # only the minimum value is left
    for i in range(length - 1 - 0):
        arr.pop()

    return arr
# Finds the maximum value of list
def findMax(arr):
    length = len(arr)
    if len(arr) == 1:
        return arr[0]
    """
    Sorts the list using the Bubble Sort algorithm
    Bubble Sort: simple sorting algorithm that swaps 
    the adjacent elements repeatedly until they are in
    the right order.

    Sorts the list from greatest to least
    """
    for x in range(length - 1):
        for y in range(length - x - 1):
            if arr[y] < arr[y + 1]:
                arr[y], arr[y + 1] = arr[y + 1], arr[y]
    # Removes the last elements of the array until
    # only the maximum value is left
    for i in range(length - 1 - 0):
        arr.pop()
    
    return arr

numbers = []

while True:
    try:
        num = int(input('Enter a number: '))
        numbers.append(num)
    except ValueError:
        break

print('Minimum:', findMin(numbers))