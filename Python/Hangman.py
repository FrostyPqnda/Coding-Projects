'''
Created on Jun 4, 2020

@author: brian
'''
"""
Hangman game project from my CodeHS assignment
"""
import random 
import requests

class Hangman:
    def __init__(self):
        self.word_site = 'https://gist.githubusercontent.com/deekayen/4148741/raw/98d35708fa344717d8eee15d11987de6c8e26d7d/1-1000.txt'
        response = requests.get(self.word_site)
        self.list_of_words = response.content.splitlines()
        self.secret_word = random.choice(self.list_of_words)
        self.dashes = '-' * len(self.secret_word)
        self.guesses_left = len(self.secret_word)
        self.wrong = []

    def __getGuess(self, word):
        while True:
            guess = input('Guess: ')
            guess = guess.lower()
            guess = guess.encode()
            
            if len(guess) != 1:
                print('Your guess must have exactly one character!')
            elif not guess.isalpha():
                print('Your guess must be a letter!')
            elif guess not in word:
                print('That letter is not in the secret word!')
                self.guesses_left -= 1
                break
            else:
                print('That letter is in the secret word!')
                return guess.decode()
                break
        
    def __updateDashes(self, word, dashes, guess):    
        for i in range(len(word)):
            if word[i] == guess:
                new = dashes[:i] + guess + dashes[i+1:]
                dashes = new
        return dashes
        
    def play(self):
        # Print the word
        print('The word is %d letters long' % len(self.secret_word))
        dashes = ''
        dashes = dashes.ljust(len(self.secret_word),'-')
        
        gameLoop = True
        while gameLoop:
            print(str(self.guesses_left) + ' guesses left.')
            print(dashes + '        ')
            guess = self.__getGuess(self.secret_word)
            dashes = self.__updateDashes(self.secret_word.decode(), dashes, guess)
            if dashes == self.secret_word.decode():
                print('CONGRATS! YOU HAVE GUESSED THE WORD!')
                print('The word was ' + self.secret_word.decode())
                gameLoop = False
            if self.guesses_left == 0:
                print('You lose. The secret word was ' + self.secret_word.decode())
                gameLoop = False
        
hangman = Hangman()
hangman.play()