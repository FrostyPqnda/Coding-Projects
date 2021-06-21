# Extracts a sublist from the original list.
# Values of sublist will include all values
# of the original list [start, end).
# Precondition: 0 < start < end < len(arr)
def extractSublist(arr, start, end):
    subList = []
    for item in range(start, end):
        subList.append(arr[item])
    return subList

arr = [1, 2, 3, 4, 5, 6, 7, 8, 9]
print(extractSublist(arr, 5, 8))
