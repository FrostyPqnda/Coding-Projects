"""
Matrix is a magic square if:

1. All elements are unique
2. The sum of each row, column and diagonal are the same

Precondition: row size is equal to column size
"""
def magicSquare(square: list[list[int]]) -> bool:
    isMagic = True
    diagOne = getDiagonal(square)[0]
    diagTwo = getDiagonal(square)[1]

    for r in range(len(square)):
        for c in range(len(square) - 1):
            rowSum = getSum(square[r])
            colSum = getSum(getColumn(square, c))
            diagOneSum = getSum(diagOne)
            diagTwoSum = getSum(diagTwo)
            
            """
            If any of these conditions are true, then isMagic will be set to False.

            1. There is a duplicate in the square.
            2. the row sum is not equal to the column sum.
            3. the 1st diagonal sum is not equal to the 2nd diagonal sum.
            """
            if (square[r][c] == square[r][c + 1]) or (rowSum != colSum) or (diagOneSum != diagTwoSum):
                isMagic = False

    return isMagic

# Gets the sum of the array
def getSum(arr: list[int]) -> int:
    sum = 0
    for item in arr:
        sum += item
    return sum

# Gets the column array of the matrix
def getColumn(mat: list[list[int]], col: int) -> list[int]:
    colArr = [None for _ in range(len(mat))]
    for i in range(len(colArr)):
        colArr[i] = mat[i][col]
    return colArr

# Get the diagonals of the matrix
def getDiagonal(mat: list[list[int]]) -> tuple:
    # Gets the forward diagonal array of the matrix
    diagOneArr = [None for _ in range(len(mat))]
    index = 0
    for r in range(len(mat)):
        for c in range(len(mat[r])):
            if r == c:
                diagOneArr[index] = mat[r][c]
                index += 1

    # Gets the backward diagonal array of the matrix
    diagTwoArr = [None for _ in range(len(mat))]
    index = 0
    for r in range(len(mat)):   
        for c in range(len(mat[r])):
            if (r + c) == (len(mat) - 1):
                diagTwoArr[index] = mat[r][c]
                index += 1

    return (diagOneArr, diagTwoArr)

mat = [
    [7, 2, 3],
    [0, 4, 8],
    [5, 6, 1]
]

print(magicSquare(mat))
