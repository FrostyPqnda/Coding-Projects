"""
Python program that calculates the total possible number of 
combination that could be attained from a selection of set 
in a larger set.
"""

def factorial(num):
    if num == 1:
        return 1
    return num * factorial(num - 1)

def calculateCombination(a, b):
    return int(factorial(max(a, b)) / (factorial(min(a, b)) * factorial(max(a, b) - min(a, b))))

x = int(input("Enter an integer: "))
y = int(input("Enter an integer: "))

c = calculateCombination(x, y)

print(c)