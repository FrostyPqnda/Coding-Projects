'''
Created on Sept 28, 2020

@author: brian
'''

"""
Python program that uses a Divide and Conquer algorithm
to find the minimum and maximum of a list
"""

# Finds the minimum value of a list
def findMin(data: list, left: int, right: int) -> int:
    if left >= right:
        return data[left - 1]
    
    mid = (left + right) // 2
    leftMin = findMin(data, left, mid)
    rightMin = findMin(data, mid + 1, right)
    return min(leftMin, rightMin)

# Finds the maximum value of list
def findMax(data: list, left: int, right: int) -> int:
    if left >= right:
        return data[left - 1]
    
    mid = (left + right) // 2
    leftMin = findMax(data, left, mid)
    rightMin = findMax(data, mid + 1, right)
    return max(leftMin, rightMin)

if __name__ == '__main__':
    data = [6, 4, 3, 2, 7, 8, 5]
    
    min = findMin(data, 0, len(data))
    max = findMax(data, 0, len(data))
    print(f'Data: {data}\nMinimum: {min}\nMaximum: {max}')