from math import *

# Function to check if n is perfect square.
def isPerfectSquare(n):
    sqrtNum = sqrt(n)
    return (sqrtNum - floor(sqrtNum)) == 0

"""
Returns the root of a negative number.

If n is greater than 0, the function will
just return the root value of n.
"""
def getImaginaryNumber(n):
    imgNum = ''
    
    """
    If n is less than 0, return the root value
    of n but with an 'i' attached. 

    Otherwise, return the root value of n.
    """
    num = sqrt(abs(n))
    if(n < 0):
        if(isPerfectSquare(abs(n))):
            intNum = int(num)
            if(n == -1):
                imgNum += 'i'
            else:
                imgNum += str(intNum) + 'i'
        else:
            roundedNum = float(round(num * 100)) / 100 # Rounds the number to the nearest hundredth place.
            imgNum += str(roundedNum) + 'i'
    else:
        num = sqrt(n)
        if(isPerfectSquare(n)):
            intNum = int(num)
            imgNum += str(intNum)
        else:
            roundedNum = float(round(num * 100)) / 100 # Rounds the number to the nearest hundredth place.
            imgNum += str(roundedNum)

    return imgNum
