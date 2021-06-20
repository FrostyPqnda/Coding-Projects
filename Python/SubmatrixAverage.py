"""
Gets the average of a particular submatrix within the matrix.
Precondition: 0 <= startRow <= endRow < len(mat)
Precondition: 0 <= startCol <= endCol < len(mat[0])
"""
def getSubmatrixAverage(mat, startRow, endRow, startCol, endCol):
    sum = 0
    count = 0

    for r in range(startRow, endRow + 1):
        for c in range(startCol, endCol + 1):
            sum += mat[r][c]
            count += 1
    
    avg = float(round((sum / count) * 100)) / 100
    return avg

arr = [
    [12, 4, 5, 6,],
    [7, 8, 9, 9],
    [5, 6, 2, 4],
    [4, 2, 5, 7],
    [5, 6, 1, 3]
]

print('Average:', str(getSubmatrixAverage(arr, 1, 3, 1, 2)))