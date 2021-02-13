"""
Sorts the list by splitting the lists into halves and then into
individiual elements. At that point, the list will merge and split 
until the elements are in the right order and then merge them together.
"""
def mergeSort(arr):
    if len(arr) > 1:
        mid = int(len(arr) / 2) # Midpoint value of list
        leftList = arr[:mid] # Left side of list up to mid
        rightList = arr[mid:] # Right side of list starting from mid
        mergeSort(leftList) # Calls mergeSort to sort the left side of the list
        mergeSort(rightList) # Calls mergeSort to sort the right side of the list
        """
        Loop through both the left and right lists and check for the
        lowest index value in the two lists, which will be the lowest
        value. 
        """
        x = y = z = 0
        while x < len(leftList) and y < len(rightList):
            # Compares the left value to the right value and takes the lowest one
            # and add it the array. It will also increment the index of the list
            # for the list where it just took the previous value.
            if leftList[x] < rightList[y]:
                arr[z] = leftList[x]
                x += 1
            else: 
                arr[z] = rightList[y]
                y += 1
            z += 1
        # Places the remaining elements in the current list
        while x < len(leftList):
            arr[z] = leftList[x]
            x += 1
            z += 1
        while y < len(rightList):
            arr[z] = rightList[y]
            y += 1
            z += 1

"""
Binary Search is a search algorithm that searches through
a sorted array by repeatedly dividing the search interval
in half. If the target is less than the item in the middle
of the array, it will search for the target in the lower 
half. Otherwise, it will search for it in the upper half.
"""
def binarySearch(arr, left, right, target):
    # Binary search only works if the data is in order. mergeSort will sort the array as a premeasurement
    mergeSort(arr) 
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
    print()

arr = [1, 34, 45, 532, 6, 56, 678, 3, 6, 5467, 435, 36, 36, 436, 346, 324, 54, 14]

printList(arr)

print("\nThe index was found at position " + str(binarySearch(arr, 0, len(arr) - 1, 45)) + "\n")

mergeSort(arr)

printList(arr)