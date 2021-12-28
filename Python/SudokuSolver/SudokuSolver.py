"""
A sudoku solving program that reads in an unsolved
sudoku text file and solves it.
"""

from os.path import exists, getsize
from math import sqrt

# Checks if the number is perfect square.
def isPerfectSquare(num):
    return num > 1 and pow(sqrt(num), 2) == num

# Print the board
def printBoard(board):
    if len(board) <= 9:
        for arr in board:
            for item in arr:
                print(item, end = ' ')
            print()
    else:
        for arr in board:
            print(arr)

# Read the file and return the matrix size
def readFile(inFile):
    with open(inFile, 'r') as file:
        content = file.readlines()
        content = [x.split() for x in content]
        
        rowSize = len(content)
        colSize = 0

        for i in range(rowSize):
            colSize = len(content[i])

        if (isPerfectSquare(rowSize) or (rowSize % 2 == 0)) and rowSize == colSize:
            size = rowSize
        else:
            size = -1

    return size

# Load the board with values from
# the file.
def loadBoard(board, inFile):
    with open(inFile, 'r') as file:
        content = file.readlines()
        content = [x.split() for x in content]

        for row in range(size):
            for col in range(size):
                try:
                    board[row][col] = int(content[row][col])
                except ValueError:
                    board[row][col] = 0

# Checks if the board is valid.
#
# Board is considered valid if 
# it does not contain only 0's
# and contains no invalid values,
def isValidBoard(board):
    valid = True
    numZero = 0

    for row in range(size):
        for col in range(size):
            if board[row][col] == 0:
                numZero += 1
            if board[row][col] < 0 or board[row][col] > size:
                valid = False

    return valid and numZero != pow(size, 2)

# Finds an empty cell
# returns a tuple row, col if there is one.
# Otherwise return tuple None, None
def findEmpty(board):
    for row in range(size):
        for col in range(size):
            if board[row][col] == 0:
                return row, col
    return None, None

# Checks if it is possible to assign
# a number to the given cell (row, col)
def isSafe(board, row, col, num):
    safe = True

    # Check if the row / column is safe
    for i in range(size):
        if board[row][i] == num or board[i][col] == num:
            safe = False
    
    # Check if the sub-grid is safe
    if isPerfectSquare(size):
        subSize = int(sqrt(size))

        startRow = row - row % subSize
        startCol = col - col % subSize

        for r in range(subSize):
            for c in range(subSize):
                if board[r + startRow][c + startCol] == num:
                    safe = False
    else:
        rowSize = row // 2 * 2
        colOffset = int(size / 2)
        colSize = col // colOffset * colOffset

        for r in range(rowSize, rowSize + 2):
            for c in range(colSize, colSize + colOffset):
                if board[row][col] == num:
                    safe = False
    
    return safe 

# Solves the sudoku puzzle using a backtracking algorithm.
# Process:
# 1. Recursively find an empty cell in the puzzle.
# 2. If there is an empty cell and safe to fill in,
#    set the cell to a value from [1, size].
# 3. Repeat until board is solved or can't be solved.
def solve(board):
    # Get a cell position where there is an empty spot on the board
    row, col = findEmpty(board)
    
    # There are no more empty cell, so return True
    if row is None:
        return True

    # Fill the board with values from [1, size]
    for num in range(1, size + 1):
        # If there is a safe cell to be filled,
        # set that cell to a value from [1, size]
        if isSafe(board, row, col, num):
            board[row][col] = num

            # Recurse the function again to find the next empty cell
            if solve(board):
                return True
        
        # Set the cell back to 0, if there are no safe cell spotted.
        board[row][col] = 0

    # Return False by default
    return False

inFile = input('Enter the filename: ')

if exists(inFile) and getsize(inFile) > 0:
    size = readFile(inFile)

    if size != -1:
        board = [[None for _ in range(size)] for _ in range(size)]
        loadBoard(board, inFile)
        
        if isValidBoard(board):
            printBoard(board) 
            print()
            if solve(board):
                printBoard(board)
            else:
                print('Puzzle could not be solved.')
        else:
            print('Board is invalid.')
    else:
        print('Error reading size.')
else:
    print('Error reading file.')