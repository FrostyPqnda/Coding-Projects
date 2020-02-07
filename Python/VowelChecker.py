'''
Created on Nov 23, 2019

@author: brian
'''
"""
Python program that asks an input from the user
to check if there is a lowercase vowel.
"""
vowel = raw_input("Enter a string of lowercase letters: ")
def check_for_vowel(vowel):
    if "a" in vowel or "e" in vowel or "i" in vowel or "o" in vowel or "u" in vowel:
        return "Contains a lowercase vowel!"
    else:
        return "Doesn't contain a lowercase vowel."

print check_for_vowel(vowel);





