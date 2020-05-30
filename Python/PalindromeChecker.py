'''
Created on May 29, 2020

@author: brian
'''

"""
Python program that checks if a word
is a palindrome - a word/phrase that
will remain the same when read in 
reverse.
"""
def is_palindrome(word):
    palin = ''.join(reversed(word))
    
    if(word == palin):
        return True
    return False

inputText = raw_input("Enter a word: ");

palindrome = is_palindrome(inputText)

print(palindrome)

if(palindrome):
    print(inputText + ' is a palindrome :)')
else:
    print(inputText + ' is not a palindrome :(')