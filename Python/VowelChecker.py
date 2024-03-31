'''
Created on May 31, 2020

@author: brian
'''
"""
Python program that asks an input from the user
to check if there is a vowel.
"""

vowels = ['A', 'E', 'I', 'O', 'U']

vowel = input("Enter a string of letters: ")
def check_for_vowel(vowel):
    return any(letter.upper() in vowels for letter in vowel)

print(check_for_vowel(vowel))

