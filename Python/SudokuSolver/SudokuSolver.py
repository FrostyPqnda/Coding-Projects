"""
WORK IN PROGRESS

A sudoku solving program that reads in an unsolved
sudoku text file and solves it.
"""


from os.path import exists

SIZE = 9

def printBoard(board):
    for row in board:
        for item in row:
            print(item, end = ' ')
        print()

# Checks if the content of the file
# is empty
def isEmpty(file):    
    content = file.readlines()
    return len(content) == 0

# Checks if the content of the file
# contains the correct data
def isValid(file):
    content = file.readlines()
    content = [x.replace(' ', '') for x in content]
    valid = True

    """
    for r in range(len(content)):
        for c in range(len(content[r])):
            if not str(content[r][c]).isdigit():
                valid = False
    """
    return valid

# Load the board with contents
# from the file.
def loadFile(board, file):
    #isLoaded = False
    content = file.readlines()
    content = [x.replace(' ', '') for x in content]
    
    for r in range(SIZE):
        for c in range(SIZE):
            board[r][c] = content[r][c]

inFile = input('Enter the filename: ')
board = [[0 for _ in range(SIZE)] for _ in range(SIZE)]

if exists(inFile):
    file = open(inFile, 'r')
    loadFile()

else:
    print('Error reading file.')

