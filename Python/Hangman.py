'''
Created on Jun 4, 2020

@author: brian
'''
"""
Hangman game project from my CodeHS assignment
"""
# Retrieve word list from website url
import random
import requests




class Hangman:

    def __init__(self):
        self.word_site = "http://svnweb.freebsd.org/csrg/share/dict/words?view=co&content-type=text/plain"
        response = requests.get(self.word_site)
        self.list_of_words = response.content.splitlines()
        self.secret_word = random.choice(self.list_of_words)
        self.dashes = "-" * len(self.secret_word)
        self.guesses_left = len(self.secret_word)
        self.wrong = []

    def get_guess(self, word):
        while True:
            guess = input("Guess: ")
            guess = guess.lower()
            guess = guess.encode()
            
            if len(guess) != 1:
                print("Your guess must have exactly one character!")
            elif not guess.isalpha():
                print("Your guess must be a letter!")
            elif guess not in word:
                print("That letter is not in the secret word!")
                self.guesses_left -= 1
                break
            else:
                print("That letter is in the secret word!")
                return guess.decode()
                break
        
    def update_dashes(self, word, dashes, guess):    
        for i in range(len(word)):
            if word[i] == guess:
                new = dashes[:i]+guess+dashes[i+1:]
                dashes = new
        return dashes
        
    def play_hangman(self):
        # Print the word
        print("The word is %d letters long" % len(self.secret_word))
        dashes = ""
        dashes = dashes.ljust(len(self.secret_word),'-')
        
        end = False
        while not end:
            print(str(self.guesses_left) + " guesses left.")
            print(dashes + '        ')
            guess = self.get_guess(self.secret_word)
            dashes = self.update_dashes(self.secret_word.decode(), dashes, guess)
            if dashes == self.secret_word.decode():
                print("CONGRATS! YOU HAVE GUESSED THE WORD!")
                print("The word was " + self.secret_word.decode())
                end = True
            if self.guesses_left == 0:
                print("You lose. The secret word was " + self.secret_word.decode())
                end = True
        
    
hangman = Hangman()
hangman.play_hangman()