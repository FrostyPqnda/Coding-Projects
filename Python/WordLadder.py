'''
Created on Nov 23, 2019

@author: brian
'''
"""
Python program that asks user to input a word
and index to to replace the letter with another
letter.
"""
# This function gets the index of the word
def get_index(Inital_Word1):
    User_Input = int(input("Enter an index (-1 to quit): "))
    DexNav = len(Inital_Word1)
    while User_Input > DexNav  or User_Input < -1:
        print "Invalid index"
        User_Input = raw_input("Enter an index (-1 to quit):")
        DexNav = len(Inital_Word1)
    return User_Input
# This gets the letter of the word
def get_letter():
    User_Input = raw_input("Enter a letter: ")
    DexNav = len(User_Input)
    while DexNav > 1:
        print "Must be exactly one character!"
        User_Input = raw_input("Enter a letter: ")
        DexNav = len(User_Input)
    while User_Input.isupper():
        print "Character must be a lowercase letter!"
        User_Input = raw_input("Enter a letter: ")
    return User_Input
# This replaces a letter for another letter in an index
def replace_at_index(User_Input, num, replacement):
    return User_Input[:num] + replacement + User_Input[num + 1:]

for i in range(1):
    Inital_Word = raw_input("Enter a word: ")
    Index = get_index(Inital_Word)
    while Index != -1:
        Letter = get_letter()
        Inital_Word = replace_at_index(Inital_Word, Index, Letter)
        print (Inital_Word)
        Index = get_index(Inital_Word)
        
        
        