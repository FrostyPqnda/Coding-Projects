from random import randint

"""
Creates a submatrix with values extracted from the original
matrx.

If values for the endRow and endCol are given, create a 
submatrix with a size of endRow - row, endCol - col.

Otherwise, submatrix will be created with values of the original
matrix from [0, row] and [0, col].
"""
def extractSubmatrix(mat, row, col, endRow = None, endCol = None):
    
    if endRow is not None and endCol is not None:
        rowSize = endRow - row
        colSize = endCol - col
        subMat = [[None for _ in range(colSize)] for _ in range(rowSize)]
        for r in range(row, endRow):
            for c in range(col, endCol):
                x = r - row
                y = c - col
                subMat[x][y] = mat[r][c] 
        return subMat
    else:
        subMat = [[None for _ in range(col + 1)] for _ in range(row + 1)]
        for r in range(row + 1):
            for c in range(col + 1):
                subMat[r][c] = mat[r][c]
        return subMat

# Creates a 2D list with values [100, 999] inclusive.
# 2D list will have a defined row size and column size.
def createMatrix(rowSize, colSize):
    mat = [[None for _ in range(colSize)] for _ in range(rowSize)]
    for r in range(rowSize):
        for c in range(colSize):
            mat[r][c] = randint(100, 999)
    return mat

# Display the matrix
def printMatrix(mat):
    for r in range(len(mat)):
        for c in range(len(mat[0])):
            print(str(mat[r][c]) + ' ', end = " ")
        print()

print('Original:\n')
mat = createMatrix(5, 5)
printMatrix(mat)

print('\nSubmatrix:\n')
subMat = extractSubmatrix(mat, 2, 3)
printMatrix(subMat)