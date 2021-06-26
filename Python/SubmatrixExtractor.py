from random import randint

# Creates a submatrix from the original matrix.
# Size of submatrix is endRow - startRow, endCol - startCol.
# Submatrix will contain all values of the original matrix
# from [startRow, endRow] and [startCol, endCol]
def extractSubmatrix(mat, startRow, endRow, startCol, endCol):
    rowSize = endRow - startRow
    colSize = endCol - startCol
    subMat = [[None for _ in range(colSize)] for _ in range(rowSize)]
    for row in range(startRow, endRow):
        for col in range(startCol, endCol):
            r = row - startRow
            c = col - startCol
            subMat[r][c] = mat[row][col] 
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
mat = createMatrix(3, 3)
printMatrix(mat)

print('\nSubmatrix:\n')
subMat = extractSubmatrix(mat, 1, 1)
printMatrix(subMat)