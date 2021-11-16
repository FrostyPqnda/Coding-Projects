"""
Binary Search is a search algorithm that searches through
a sorted array by repeatedly dividing the search interval
in half. If the target is less than the item in the middle
of the array, it will search for the target in the lower 
half. Otherwise, it will search for it in the upper half.
"""
def binarySearch(arr, left, right, target):
    if(right >= left):
        mid = int(left + (right - left) / 2)
        # Element is at the middle itself
        if arr[mid] == target:
            return mid
        # Element is smaller than mid
        if arr[mid] > target:
            return binarySearch(arr, left, mid - 1, target)
        # Element is greater than mid
        return binarySearch(arr, mid + 1, right, target)
    return -1

# Prints all elements of list in one line
def printList(arr):
    for i in range(len(arr)):
        print(arr[i], end = " ")

arr = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

printList(arr)

print("\nThe index was found at position " + str(binarySearch(arr, 0, len(arr) - 1, 4)) + "\n")