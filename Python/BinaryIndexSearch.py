"""
Binary Search is a search algorithm that searches through
a sorted array by repeatedly dividing the search interval
in half. If the target is less than the item in the middle
of the array, it will search for the target in the lower 
half. Otherwise, it will search for it in the upper half.
"""
def binarySearch(arr, left, right, target):
    if left >= right: return -1

    mid = (left + right) // 2
    if arr[mid] > target: return binarySearch(arr, left, mid - 1, target)
    elif arr[mid] < target: return binarySearch(arr, mid + 1, right, target)
    else: return mid


# Prints all elements of list in one line
def printList(arr):
    for i in range(len(arr)):
        print(arr[i], end = " ")

arr = [1, 2, 3, 5, 5, 8, 10, 15, 19, 19]
printList(arr)
print(f'{5} was found at index {binarySearch(arr, 0, len(arr) - 1, 5)}')
