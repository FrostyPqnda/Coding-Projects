'''
Created on Nov 23, 2019

@author: brian
'''
"""
Python program that asks user to input a sentence
and a word to check if the word appears in the
sentence.
"""
word = input("Enter a sentence: ")

string = input("Enter a word: ")

def count_occurrences(word, string):
    for letter in string:
        if letter in word:
            print ("found " + string + "!")
        else:
            print ("Word not found.")
        return word
count_occurrences(word, string)



