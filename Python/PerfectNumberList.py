from math import *

class PerfectNumberList:

    """
    Initializes a list of 'perfect' numbers.
    All values will be within [0, numRange]

    A perfect number is a number in which an nth root value can
    be given. i.e. 1000 is a perfect number if the nthRoot is 3

    i.e. calling PerfectNumberList(9, 2) will return [0, 1, 4, 9]
    """
    def __init__(self, numRange, nthRoot):
        self.numRange = numRange
        self.nthRoot = nthRoot
        self.numList = []

        for i in range(numRange + 1):
            if self.__isPerfectNthRoot(i, self.nthRoot):
                self.numList.append(i)

    # Checks if the number is a perfect nth root 
    def __isPerfectNthRoot(self, num, root):
        num = abs(num)
        root = abs(root)

        powNum = self.__getNthRoot(num, root)
        return pow(round(powNum), root) == num

    # Gets the nth root value of a number
    def __getNthRoot(self, num, root):
        rootNum = pow(num, (1/root))
        return round(rootNum)

    # Return the class object as a string
    def __str__(self):
        return str(self.numList)

numRange = int(input('Enter a range: '))
nthRoot = int(input('Enter a root: '))

perfectNumbers = PerfectNumberList(numRange, nthRoot)

print('\n' + str(perfectNumbers))
    

