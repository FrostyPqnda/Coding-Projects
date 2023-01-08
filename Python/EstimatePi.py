"""
Python program that estimates the value of pi
using Monte Carlo.

Formula:
Let p = Area of circle = πr² -> Sum of points in circle
Let q = Area of square = (2r)² -> Sum of points in square

p / q = π / 4 = Sum of points in circle / Sum of points in square
π = 4 * (No. of points in circle / No. of points in square)

[https://www.geeksforgeeks.org/estimating-value-pi-using-monte-carlo/]
"""

from random import uniform

# Estimate the value of π given a sample size n
def estimatePi(n: int) -> float:
    numInCircle = 0 # Number of points in the circle
    numTotal = 0 # Number of points total 

    for i in range(n):
        # Random (x, y) point in the range [-1, 1]
        x, y = uniform(-1, 1), uniform(-1, 1) 
        
        # Distance from origin to the current (x, y) 
        dist = (x ** 2) + (y ** 2)

        # Increment number of points in the circle
        # if distance <= 1
        if dist <= 1:
            numInCircle += 1

        # Increment total points overall
        numTotal += 1

    return 4 * (numInCircle / numTotal)

if __name__ == '__main__':
    n = int(input('Enter sample size: '))
    print(f'Estimated value of π: {estimatePi(n)}')