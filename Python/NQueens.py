"""
Python program that solves the NQueens problem
via a Backtracking Algorithm.

Example: N = 4:
- Q - -  
- - - Q
Q - - -
- - Q -

- - Q -
Q - - -
- - - Q
- Q - -
"""

# Checks if the current row, column position
# is safe for a queen to be placed at
def isSafe(row, column):
    safe = True

    for i in range(size):
        if board[row][i] == 'Q' or board[i][column] == 'Q':
            safe = False
            break
    
    for i, j in zip(range(row, -1, -1), range(column, -1, -1)):
        if board[i][j] == 'Q':
            safe = False
            break
    
    for i, j in zip(range(row, -1, -1), range(column, size, 1)):
        if board[i][j] == 'Q':
            safe = False
            break

    return safe

# Places a queen onto a board via a backtracking
# algorithm.
def placeQueens(row):
    global numSolution # Access the numSolution variable outside the function
    if(row == size):
        numSolution += 1
        printBoard() # Print the solved NQueens board
        print()
        return
    
    for i in range(size):
        if isSafe(row, i):
            board[row][i] = 'Q' # Place queen
            placeQueens(row + 1) # Go to next position
            board[row][i] = '-' # Remove queen

def printBoard():
    for i in range(size):
        for j in range(size):
            print(board[i][j], end = ' ')
        print()

numSolution = 0
size = int(input('Enter a size: '))
board = [['-' for _ in range(size)] for _ in range(size)]
placeQueens(0)
print(f'# of solutions: {numSolution}')