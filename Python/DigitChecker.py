'''
Created on Aug 23, 2020

@author: brian
'''

"""
Method digit_checker loops through 
the user's input to check if a number
exists in it
"""
def digit_checker():
    is_digit = False
    text = input("Enter a text: ")
    for char in text:
        if char.isdigit():
            is_digit = True
            print("Your input contains a number")
            return is_digit

    print("Your input does not contain a number")
    return is_digit

print(digit_checker())

