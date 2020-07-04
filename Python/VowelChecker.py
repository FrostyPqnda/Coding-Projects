'''
Created on May 31, 2020

@author: brian
'''
"""
Python program that asks an input from the user
to check if there is a vowel.
"""
vowel = input("Enter a string of letters: ")
def check_for_vowel(vowel):
    if "a" in vowel or "e" in vowel or "i" in vowel or "o" in vowel or "u" in vowel: 
        return "Contains a vowel!"
    if "A" in vowel or "E" in vowel or "I" in vowel or "I" in vowel or "U" in vowel:
        return "Contains a vowel!"
    else:
        return "Does not contain a vowel."

print (check_for_vowel(vowel))

