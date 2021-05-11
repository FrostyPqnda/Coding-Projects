from math import pow

# Checks if the array is an arithmetic sequence
def isArithmetic(arr):
    isSequential = True
    for i in range(0, len(arr) - 2):
        if arr[i + 1] - arr[i] != arr[i + 2] - arr[i + 1]:
            isSequential = False
    return isSequential

"""
Calculates the nth term of an arithmetic sequence

a₁: First term of the seqeunce
d: Common difference in the sequence
n: Nth term of the sequence

Formula: aₙ = a₁ + d(n - 1)
"""
def arithmeticNthTerm(a1, d, n):
    nthTerm = a1 + d * (n - 1)
    return nthTerm

# Calculates the sum of a finite arithmetic series
def finiteArithmeticSum(start, end, a1, d):
    sum = 0
    for i in range(start, end + 1):
        sum += arithmeticNthTerm(a1, d, i)
    return sum

# Checks if the array is a geometric sequence
def isGeometric(arr):
    isSequential = True
    for i in range(0, len(arr) - 2):
        if arr[i + 1] / arr[i] != arr[i + 2] / arr[i + 1]:
            isSequential = False
    return isSequential

"""
Calculates the nth term of a geometric sequence

a₁: First term of the seqeunce
r: Common ratio in the sequence
n: Nth term of the sequence

Formula: aₙ = a₁ * r⁽ⁿ⁻¹⁾
"""
def geometricNthTerm(a1, r, n):
    nthTerm = a1 * pow(r, (n - 1))
    return nthTerm

# Calculates the sum of a finite geometric series
def finiteGeometricSum(start, end, a1, r):
    sum = 0
    for i in range(start, end + 1):
        sum += geometricNthTerm(a1, r, i)
    return sum

print(finiteGeometricSum(4, 6, 5, 4))

