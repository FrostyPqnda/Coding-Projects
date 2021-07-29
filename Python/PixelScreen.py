from random import randint

"""
Creates a pixel screen with data copied over from
a 1-Dimensional list.
"""
class PixelScreen:
    # Initializer for the PixelScreen class
    # Precondition: numRows >= 0, numCols >= 3
    def __init__(self, numRows, numCols, scanned):
        self.numRows = numRows
        self.numCols = numCols
        self.scanned = scanned
        self.screen = [[None for _ in range(numCols)] for _ in range(numRows)]
        
        index = 0

        for r in range(numRows):
            for c in range(numCols):
                self.screen[r][c] = scanned[index]
                index += 1

    # Code taken from [https://stackoverflow.com/questions/3380726/converting-a-rgb-color-tuple-to-a-six-digit-code]
    # Converts RGB values to hexadecimal
    def __RGBToHex(self, r, g, b):
        if (r >= 0 and r <= 255) and (g >= 0 and g <= 255) and (b >= 0 and b <= 255):
            return '#{:02x}{:02x}{:02x}'.format(r, g, b).upper()
        else:
            return -1
    
    # Gets the total possible consecutive hexadecimal values that can be made from a particular row and returns it as a list.
    def getPossibleHexesFromRow(self, row):
        rowScreen = self.screen[row]
        hexCodeList = []
        
        for i in range(len(rowScreen) - 2):
            hexVal = self.__RGBToHex(rowScreen[i], rowScreen[i+1], rowScreen[i+2])
            hexCodeList.append(hexVal)
            
        return hexCodeList

    # Display the PixelScreen list
    def displayScreen(self):
        for r in range(self.numRows):
            for c in range(self.numCols):
                print(self.screen[r][c], end = " ")
            print()

# Creates a randomized list with values [0, 255] inclusive
def generatePixelRow(size):
    pixelScreen = []
    for _ in range(size):
        pixelScreen.append(randint(0, 255))
    return pixelScreen

tv = generatePixelRow(56)

pixelScreen = PixelScreen(7, 8, tv)

print("Pixel Screen:\n")

pixelScreen.displayScreen()

rowInput = (int(input("\nEnter a row #: ")))

print('\nThe possible hexadecimals that could be made from row ' + str(rowInput) + ' are ' + str(pixelScreen.getPossibleHexesFromRow(rowInput)))