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
    return any(c.isdigit() for c in text)

print(digit_checker("h3ll0"))
print(digit_checker("hello"))

