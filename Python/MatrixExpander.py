class MatrixExpander:

    def __init__(self, mat, rowFactor, colFactor):
        
        rowSize = len(mat) * rowFactor
        colSize = len(mat[0]) * colFactor

        self.expMat = [[None for _ in range(colSize)] for _ in range(rowSize)]

        for r in range(len(mat)):
            rowOffset = r * rowFactor
            rowLen = rowOffset + rowFactor

            for c in range(len(mat[0])):
                colOffset = c * colFactor
                colLen = colOffset + colFactor

                for row in range(rowOffset, rowLen):
                    for col in range(colOffset, colLen):
                        self.expMat[row][col] = mat[r][c]

    def printMatrix(self):
        for r in range(len(self.expMat)):
            for c in range(len(self.expMat[0])):
                print(self.expMat[r][c], end = " ")
            print()

mat = [
    [1, 2, 3],
    [4, 5, 6]
]

expMat = MatrixExpander(mat, 2, 2)

expMat.printMatrix()

