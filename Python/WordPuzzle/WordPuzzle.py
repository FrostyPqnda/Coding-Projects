dir = [
    [-1, -1],
    [-1, 0],
    [-1, 1],
    [0, -1],
    [0, 1],
    [1, -1],
    [1, 0],
    [1, 1]
]

"""
def scanBoard(word, row, column):
    for d in dir:
        k = 1
        rowDir = row + d[0]
        colDir = column + d[1]

        for k in range(len(word)):
            if rowDir 
"""

def readFile(inFile):
    with open(inFile, 'r') as file:
        content = file.readlines()
        content = [x.split for x in content]
    return len(content)

def loadFile(board, inFile):
    with open(inFile, 'r') as file:
        content = file.readlines()
        content = [x.split() for x in content]

        for row in range(size):
            for col in range(size):
                board[row][col] = content[row][col]

def printBoard(board):
    for row in range(size):
        for col in range(size):
            print(board[row][col], end = ' ')
        print()

size = readFile('minecraft.txt')
board = [['-' for _ in range(size)] for _ in range(size)]
loadFile(board, 'minecraft.txt')
printBoard(board)

