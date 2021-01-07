'''
Created on Jan 6, 2021

@author: brian
'''
from random import randint
"""
Python game that asks users to guess a number
between 1 and 100 inclusive.
"""
randNum = randint(1, 100) # Generated random number b/w 1 and 100 inclusive
count = 1 # Counting variable that counts how many tries it took

while True:
    num = int(input("Guess the number [1, 100]: "))

    if num == randNum:
        print("Correct! \nIt took you " + str(count) + " tries to guess the number.")
        break
    elif num > randNum:
        print("Too high!")
    elif num < randNum:
        print("Too low!")
    
    count += 1