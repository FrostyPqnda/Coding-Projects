'''
Created on Aug 23, 2020

@author: brian
'''

"""
Method digit_checker loops through 
the user's input to check if a number
existed in it
"""
def digit_checker():
    is_digit = False
    text = input("Enter a text: ")
    for char in text:
        if char.isdigit():
            is_digit = True
            return "Does your text contain a number? " + str(is_digit)

    return "Does your text contain a number? " + str(is_digit)

print(digit_checker())

