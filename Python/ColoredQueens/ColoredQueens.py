from os.path import exists, getsize

# Load the file into a board.
def loadFile(inFile: str) -> list[list[str]] | None:
    if not exists(inFile) or getsize(inFile) <= 0:
        return None                      
      
    with open(inFile, 'r') as file:
        content = file.readlines()
        content = [x.split() for x in content]
        size = len(content)

        board = [[content[row][col] for col in range(size)] for row in range(size)]

    return board

def isSafe(row: int, col: int) -> bool:
    if not colors:
        exit(-1)

    color = colors[row][col]

    # Only one queen per color
    if color in usedColors:
        return False
    
    # Check row and column
    for i in range(n):
        if colors[row][i] == 'Q' or colors[i][col] == 'Q':
            return False
    
    # Check adjacent cells
    for dr in [-1, 0, 1]:
        for dc in [-1, 0, 1]:
            if dr == 0 and dc == 0:
                continue

            nr = row + dr
            nc = col + dc

            if 0 <= nr < n and 0 <= nc < n:
                if colors[nr][nc] == 'Q':
                    return False
    
    return True


def placeQueens(row: int) -> None:
    if not colors:
        exit(-1)

    global numSolutions

    if row == n:
        numSolutions += 1
        printBoard()
        print()
        return
    
    for col in range(n):
        if isSafe(row, col):
            color = colors[row][col]
            colors[row][col] = 'Q'
            usedColors.add(color)

            placeQueens(row + 1)

            usedColors.remove(color)
            colors[row][col] = color

def printBoard() -> None:
    if not colors:
        return

    n = len(colors)
    for i in range(n):
        for j in range(n):
            print(colors[i][j], end = ' ')
        print()

if __name__ == '__main__':
    colors = loadFile('colors.txt')
    if not colors:
        print('Board could not be loaded due to file error.')
        exit(-1)

    n = len(colors)
    usedColors = set()  
    numSolutions = 0

    placeQueens(0)