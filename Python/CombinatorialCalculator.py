"""
Python program that calculates the total possible number of 
combination that could be attained from a selection of set 
in a larger set.
"""

def factorial(num):
    # Base case
    if num == 0:
        return 1
    # Recursive call
    return num * factorial(num - 1)

def calculateCombination(a, b):
    return int(factorial(max(a, b)) / (factorial(min(a, b)) * factorial(max(a, b) - min(a, b))))

x = int(input("Enter an integer: "))
y = int(input("Enter an integer: "))

print(str(calculateCombination(x, y)) + " total combinations")