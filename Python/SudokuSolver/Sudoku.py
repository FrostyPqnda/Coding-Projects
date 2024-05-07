"""
A sudoku solving program that reads in an unsolved
sudoku text file and solves it.
"""

from os.path import exists, getsize
from math import sqrt
import sys

# Print the board
def printBoard(board):
    for arr in board:
        for item in arr:
            print(item, end = ' ')
        print()

# Load the file into a board.
def loadFile(inFile) -> int: 
    if not exists(inFile) or getsize(inFile) <= 0:
        return None

    with open(inFile, 'r') as file:
        content = file.readlines()
        content = [x.split() for x in content]
        size = len(content)

        board = [[None for _ in range(size)] for _ in range(size)]

        for row in range(size):
            for col in range(size):
                board[row][col] = int(content[row][col])

    return board

# Checks if it is possible to assign
# a number to the given cell (row, col)
def isSafe(board, row, col, num):
    # Check if the row / column is safe
    for i in range(len(board)):
        if board[row][i] == num or board[i][col] == num:
            return False

    # Check if the sub-grid is safe
    subSize = int(sqrt(len(board)))
    startRow = row - row % subSize
    startCol = col - col % subSize

    for r in range(subSize):
        for c in range(subSize):
            if board[r + startRow][c + startCol] == num:
                return False

    return True 

# Finds an empty cell
# returns a tuple row, col if there is one.
# Otherwise return tuple None, None
def findEmpty(board):
    for row in range(len(board)):
        for col in range(len(board)):
            if board[row][col] == 0:
                return row, col
    return None, None

# Solves the sudoku puzzle using a backtracking algorithm.
# Process:
# 1. Recursively find an empty cell in the puzzle.
# 2. If there is an empty cell and safe to fill in,
#    set the cell to a value from [1, board size].
# 3. Repeat until board is solved or can't be solved.
def solve(board):
    # Get a cell position where there is an empty spot on the board
    row, col = findEmpty(board)
    
    # There are no more empty cell, so return True
    if row is None: return True

    # Fill the board with values from [1, board size]
    for num in range(1, len(board) + 1):
        # If there is a safe cell to be filled,
        # set that cell to a value from [1, board size]
        if isSafe(board, row, col, num):
            board[row][col] = num
            if solve(board): return True
        # Set the cell back to 0, if there are no safe cell spotted.
        board[row][col] = 0

    # Return False by default
    return False

if __name__ == '__main__':
    if len(sys.argv) != 2:
        print('usage: python Sudoku.py <text file>')
        sys.exit()
    
    inFile = sys.argv[1]

    board = loadFile(inFile)

    if board is None:
        print('Error loading file')
        sys.exit()

    printBoard(board)
    print()

    if not solve(board):
        print('Puzzle could not be solved.')
        sys.exit()

    printBoard(board)
    