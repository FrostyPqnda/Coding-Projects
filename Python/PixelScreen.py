from random import randint

"""
Creates a pixel screen with data copied over from
a 1-Dimensional list.
"""
class PixelScreen:
    # Initializer for the PixelScreen class
    # Precondition: numRows >= 0, numCols >= 3; numCols % 3 == 0
    def __init__(self, numRows, numCols, scanned, *screen):
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
    def RGBToHex(self, r, g, b):
        if (r >= 0 and r <= 255) and (g >= 0 and g <= 255) and (b >= 0 and b <= 255):
            return ('#%02x%02x%02x' % (r, g, b)).upper()
        else:
            return -1
    
    # Display the PixelScreen list
    def displayScreen(self):
        for r in range(self.numRows):
            for c in range(self.numCols):
                print(self.screen[r][c], end = " ")
            print()

# Creates a randomized list with values [0, 255] inclusive
# Precondition: size % 3 == 0
def generatePixelScreen(size):
    pixelScreen = []
    for _ in range(size):
        pixelScreen.append(randint(0, 255))
    return pixelScreen

tv = generatePixelScreen(15)

pixelScreen = PixelScreen(5, 3, tv)
pixelScreen.displayScreen()

print('\n' + str(pixelScreen.screen[1]))

red = pixelScreen.screen[1][0]
green = pixelScreen.screen[1][1]
blue = pixelScreen.screen[1][2]

print(pixelScreen.RGBToHex(red, green, blue))