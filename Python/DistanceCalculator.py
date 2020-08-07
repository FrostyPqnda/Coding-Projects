'''
Created on Nov 23, 2019
      
@author: brian
'''
import math
"""
Python program that asks users for 2 coordinate pairs
to calculate the distance between them.
"""
x1 = int(input("Input value of x1: "))
y1 = int(input("Input value of y1: "))
x2 = int(input("Input value of x2: "))
y2 = int(input("Input value of y2: "))

pos1 = [x1, y1]
pos2 = [x2, y2]

def distance(pos1, pos2):
    squareX = pow((pos2[0] - pos1[0]), 2)
    squareY = pow((pos2[1] - pos1[1]), 2)

    squareXY = squareX + squareY
    
    result = math.sqrt(squareXY)
    roundedResult = round(result, 2)
    
    print ("The distance from " + str(pos1) + " to " + str(pos2) + " is " + str(roundedResult) + " units.")

distance(pos1, pos2)





