from math import *
import sys
# Function to check if n is perfect square.
def isPerfectSquare(n):
    sqrtNum = sqrt(n)
    return (sqrtNum - floor(sqrtNum)) == 0

"""
Returns the root of a negative number.
"""
def getImaginaryNumber(n):
    if n >= 0:
        print('n must be less than 0!')
        sys.exit()

    imgNum = ''
    
    """
    If n is less than 0, return the root value
    of n but with an 'i' attached. 
    """
    num = sqrt(abs(n))
    if(isPerfectSquare(abs(n))):
            intNum = int(num)
            if(n == -1):
                imgNum += 'i'
            else:
                imgNum += str(intNum) + 'i'
    else:
        roundedNum = float(round(num * 100)) / 100 # Rounds the number to the nearest hundredth place.
        imgNum += str(roundedNum) + 'i'

    return '\u00B1 ' + imgNum

negNum = int(input('Enter a negative number: '))
print(f'The square root of {negNum} is {getImaginaryNumber(negNum)}')