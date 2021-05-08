"""
Python program that calculates the sum
given a list of Strings
"""

def getSum(arr):
    sum = 0
    for item in arr:
        if item.isdigit():
            sum += int(item)
    return sum

arr = ['1', '2', '3', '5', 'bird', '10', 'dog']
print('Sum: ' + str(getSum(arr)))