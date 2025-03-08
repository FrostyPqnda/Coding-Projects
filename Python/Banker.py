# Subtract two matrices
def subMat(a: list[list[int]], b: list[list[int]]) -> list[list[int]]:
    if len(a) != len(b) or len(a[0]) != len(b[0]):
        raise Exception('Size do not match!')
    
    return [[a[i][j] - b[i][j] for j in range(len(a[0]))] for i in range(len(a))]

# Add two list
def addList(a: list[int], b: list[int]) -> list[int]:
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
def findSafe(n: list[list[int]], v: list[int], completed: list[bool]):
    for i in range(len(n)):
        # Check if n[i] is a safe vector and that it is not yet completed
        if safeVector(n[i], v) and not completed[i]:
            return i
    return -1

# Check if processes are safe from deadlock
def safeState(c: list[list[int]], a: list[list[int]], r: list[int], v: list[int]) -> bool:
    n = subMat(c, a)
    num_processes = len(c)
    completed = [False] * num_processes
    safe_sequence = []

    while len(safe_sequence) < num_processes:
        index = findSafe(n, v, completed)
        if index == -1:
            print("System is in a deadlock state!")
            return False, []
        
        print(f'Process P{index + 1} runs to completion')
        v = addList(v, a[index])
        completed[index] = True
        safe_sequence.append(index + 1)

    return True, safe_sequence

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
resource = [9, 3, 6]
available = [0, 1, 1]

_, safe = safeState(claim, allocation, resource, available)
print(safe)
