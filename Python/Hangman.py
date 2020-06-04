'''
Created on Jun 4, 2020

@author: brian
'''
"""
Hangman game project from my CodeHS assignment
"""
import random
import requests

word_site = "http://svnweb.freebsd.org/csrg/share/dict/words?view=co&content-type=text/plain"
response = requests.get(word_site)
list_of_words = response.content.splitlines()
secret_word = random.choice(list_of_words)
dashes = "-" * len(secret_word)
guesses_left = len(secret_word)
wrong = []

def get_guess(word):
    global wrong
    global guesses_left
    
    while True:
        guess = raw_input("Guess: ")
        guess = guess.lower()
        
        if len(guess) != 1:
            print("Your guess must have exactly one character!")
        elif not guess.isalpha():
            print("Your guess must be a letter!")
        elif guess not in word:
            print("That letter is not in the secret word!")
            guesses_left -= 1
            
            break
        else:
            print("That letter is in the secret word!")
            return guess
            break
    
def update_dashes(word, dashes, guess):    
    for i in range(len(word)):
        if word[i] == guess:
            new = dashes[:i]+guess+dashes[i+1:]
            dashes = new
    return dashes
    
def play_hangman():
    # Print the word
    print("The word is %d letters long" % len(secret_word))
    dashes = ""
    dashes = dashes.ljust(len(secret_word),'-')
    
    end = False
    while not end:
        print(str(guesses_left) + " guesses left. \n")
        print(dashes + '        ')
        guess = get_guess(secret_word)
        dashes = update_dashes(secret_word, dashes, guess)
        if dashes == secret_word:
            print("CONGRATS! YOU HAVE GUESSED THE WORD!")
            print("The word was " + secret_word)
            end = True
        if guesses_left == 0:
            print("You lose. The secret word was " + secret_word)
            end = True
        
    
play_hangman()