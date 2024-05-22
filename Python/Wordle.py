import requests
import random
from os import system, name

"""
Author: Brian Pham
Date: 22 May 2024

Python program that simulates the Wordle game
"""

class Wordle:
    # Initialize the Wordle class
    # Read a list of 5-letter words from an online text file
    def __init__(self):
        self.url = 'https://www-cs-faculty.stanford.edu/~knuth/sgb-words.txt'
        
        try:
            response = requests.get(self.url)
        except requests.exceptions.HTTPError as errh:
            print('HTTP Error')
            print(errh.args[0])
        
        self.list_of_words = response.content.splitlines()
        self.list_of_words = [word.decode().upper() for word in self.list_of_words]
        self.invalid_chars = []
        self.secret_word = random.choice(self.list_of_words)
        

    # Get a 5-letter input from the user
    def __getWord(self):
        while True:
            s = input('Enter a 5-letter word: ')

            if len(s) == 5 and s.upper() in self.list_of_words:
                return s

    """
    Update the user's guess with the following character:
    - A '+' means that a character from the user's input 
      exists in the secret word but it is not in the 
      correct position.

    - A '-' means that a character from the user's input
      does not exists in the secret word
    """
    def __guess(self, secret_word, guess_word):
        for i in range(len(secret_word)):
            if guess_word[i] in secret_word and guess_word[i] != secret_word[i]:
                guess_word = guess_word[:i] + '+' + guess_word[i + 1:]
            elif guess_word[i] not in secret_word:
                if guess_word[i] not in self.invalid_chars:
                    self.invalid_chars.append(guess_word[i].upper())
                    self.invalid_chars.sort()
                guess_word = guess_word[:i] + '-' + guess_word[i + 1:]

        return guess_word

    # Play the Wordle game
    def play(self):
        board = [['_' for _ in range(5)] for _ in range(6)]
        score = 0

        for line in board:
                b = ' '.join(line).split(',')
                print(b)

        for i, j in zip(range(len(board)), range(len(board), -1, -1)):
            word = self.__getWord()
            guess = self.__guess(self.secret_word, word)

            if word != '':
                if name == 'nt':
                    system('cls')
                else:
                    system('clear')

            board[i] = [*guess]

            for line in board:
                b = ' '.join(line).split(',')
                print(b)

            print(f'Invalid: {self.invalid_chars}')

            if word == guess:
                score += j
                break

        if score == 0:
            print(f'Secret Word = {self.secret_word}')
        else:
            print(f'Score = {score}')

if __name__ == '__main__':
    wordle = Wordle()
    wordle.play()