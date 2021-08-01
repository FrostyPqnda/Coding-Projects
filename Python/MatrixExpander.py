def expandMatrix(mat, rowFactor, colFactor):
    rowSize = len(mat) * rowFactor
    colSize = len(mat[0]) * colFactor

    expMat = [[None for _ in range(colSize)] for _ in range(rowSize)]

    for r in range(len(mat)):
        rowOffset = r * rowFactor
        rowLen = rowOffset + rowFactor

        for c in range(len(mat[0])):
            colOffset = c * colFactor
            colLen = colOffset + colFactor

            for row in range(rowOffset, rowLen):
                for col in range(colOffset, colLen):
                    expMat[row][col] = mat[r][c]

    return expMat
    
def printMatrix(mat):
    for r in range(len(mat)):
        for c in range(len(mat[0])):
            print(mat[r][c], end = " ")
        print()

mat = [
    [1, 2, 3],
    [4, 5, 6]
]

expMat = expandMatrix(mat, 2, 2)

printMatrix(expMat)

