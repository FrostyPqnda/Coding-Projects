"""
A sudoku solving program that reads in an unsolved
sudoku text file and solves it.
"""

from os.path import exists, getsize

# Max size of the sudoku puzzle+
SIZE = 9

def printBoard(board):
    for row in board:
        for item in row:
            print(item, end = ' ')
        print()

# Load the board with contents
# from the file.
def loadBoard(board, file):
    content = file.readlines()
    content = [x.replace(' ', '') for x in content]
    
    for r in range(SIZE):
        for c in range(SIZE):
            board[r][c] = int(content[r][c])

# Checks if the board is empty.
# Board is considered empty if 
# it contains only 0's.
def isEmptyBoard(board):    
    numZero = 0

    for r in range(SIZE):
        for c in range(SIZE):
            if board[r][c] == 0:
                numZero += 1

    return numZero == pow(SIZE, 2)

# Finds an empty cell
# returns a tuple row, col if there is one.
# Otherwise return tuple None, None
def findEmpty(board):
    for row in range(SIZE):
        for col in range(SIZE):
            if board[row][col] == 0:
                return row, col
    return None, None

# Checks if it is possible to assign
# a number to the given cell (row, col)
def isSafe(board, row, col, num):
    safe = True

    for i in range(SIZE):
        if board[row][i] == num or board[i][col] == num:
            safe = False
        
    startRow = row - row % 3
    startCol = col - col % 3
    for r in range(3):
        for c in range(3):
            if board[r + startRow][c + startCol] == num:
                safe = False

    return safe 

# Solves the sudoku puzzle using a backtracking algorithm.
# Process:
# 1. Recursively find an empty cell in the puzzle.
# 2. If there is an empty cell and safe to fill in,
#    set the cell to a value from [1, 9].
# 3. Repeat until board is solved or can't be solved.
def solve(board):
    # Get a cell position where there is an empty spot on the board
    row, col = findEmpty(board)
    
    # There are no more empty cell, so return True
    if row is None:
        return True

    # Fill the board with values from [1, 9]
    for num in range(1, SIZE + 1):
        # If there is a safe cell to be filled,
        # set that cell to a value from [1, 9]
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
board = [[0 for _ in range(SIZE)] for _ in range(SIZE)]

if exists(inFile) and getsize(inFile) > 0:
    file = open(inFile, 'r')
    loadBoard(board, file)
    if not isEmptyBoard(board):
        printBoard(board)
        print()
        if solve(board):
            printBoard(board)
        else:
            print('Puzzle could not be solved.')
    else:
        print('Board contains no values.')
else:
    print('Error reading file.')