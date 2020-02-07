'''
Created on Nov 23, 2019

@author: brian
'''
"""
This is a Python program that
checks if there is number inside
of a list of strings, and converts it into 
an int. 
"""
def safe_int(word):
    # Checks if there is an integer in a string.
    try:
        convert_to_int = int(word)
        return convert_to_int
    # If there is no integer in a string, it will
    # return a 0.
    except ValueError:
        return 0
list_of_strings = ["merry christmas", "2", "7", "kansas", "120212"]

print [safe_int(word) for word in list_of_strings]






