# Subtract two matrices
def subMat(a: list[list[int]], b: list[list[int]]) -> list[list[int]]:
    if len(a) != len(b) or len(a[0]) != len(b[0]):
        raise Exception('Size do not match!')
    
    return [[a[i][j] - b[i][j] for j in range(len(a[0]))] for i in range(len(a))]

# Subtract two list
def subList(a: list[int], b: list[int]) -> list[int]:
    if len(a) != len(b):
        raise Exception('Size do not match!')
    
    return [a[i] + b[i] for i in range(len(a))]

# Check if need vector n <= available vector v
def safeVector(n: list[int], v: list[int]) -> bool:
    return all(n[i] <= v[i] for i in range(len(n)))

# Checks if the need matrix (C - A) has all zeroes 
def isSafe(need: list[list[int]]) -> bool:
    for i in range(len(need)):
        if any(n != 0 for n in need[i]):
            return False
    return True

# Find the index of the process that can run to completion
def findSafe(n: list[list[int]], v: list[int]):
    for i in range(len(n)):
        # Check if n[i] is a safe vector and that it is not yet completed
        if safeVector(n[i], v) and not all(p == 0 for p in n[i]):
            return i
    return -1

# Check if the resource is safe from deadlock
def safeState(c: list[list[int]], a: list[list[int]], v: list[int]) -> bool:
    n = subMat(c, a)
    if(isSafe(n)): 
        return True

    index = findSafe(n, v)
    if index == -1:
        return False

    v = subList(v, a[index])
    c[index] = [0 for i in range(len(c[0]))]
    a[index] = [0 for i in range(len(a[0]))]
    print(f'Process P{index + 1} runs to completion')
    return safeState(c, a, v)

claim = [
    [3, 2, 2],
    [6, 1, 3],
    [3, 1, 4],
    [4, 2, 2]
]
allocation = [
    [1, 0, 0],
    [6, 1, 2],
    [2, 1, 1],
    [0, 0, 2]
]
#resource = [9, 3, 6]
available = [0, 1, 1]

safe = safeState(claim, allocation, available)
print(safe)
