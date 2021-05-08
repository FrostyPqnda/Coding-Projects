'''
Created on Aug 23, 2020

@author: brian
'''

"""
Method digit_checker loops through 
the user's input to check if a number
exists in it
"""
def digit_checker(text):
    isDigit = False
    for char in text:
        if char.isdigit():
            isDigit = True
    return isDigit

print(digit_checker("h3llo"))

