from random import randint # Imports the randint function from the random module

# Generate a list with a random set of numbers
def generateRandomList(length):
    arr = [randint(1, 100) for i in range(length)]
    return arr

"""
Sorts the list by splitting the lists into halves and then into
individiual elements. At that point, the list will merge and split 
until the elements are in the right order.
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

# Prints all elements of list in one line
def printList(arr):
    for i in range(len(arr)):
        print(arr[i], end = " ")
    print()

inputLength = int(input("Enter a length for the list: "))

arr = generateRandomList(inputLength)

print("Unsorted List:")
printList(arr)

mergeSort(arr)

print("\nSorted List")
printList(arr)