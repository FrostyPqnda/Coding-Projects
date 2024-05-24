import requests
import random
from os import system, name
import json
import sys

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
        
        self.__list_of_words = [word.decode().upper() for word in response.content.splitlines()]
        self.__load_state()
        
    # Load the game state from state.json
    def __load_state(self):
        with open('state.json', 'r') as f:
            data = json.loads(f.read())
            self.__secret_word = bytes.fromhex(data['state']['secret_word']).decode('UTF-8') if data['state']['secret_word'] else random.choice(self.__list_of_words)
            self.__valid_chars = data['state']['valid']
            self.__invalid_chars = data['state']['invalid']
            self.__board = data['state']['board']
            self.__current_score = data['state']['score']['current_score']
            self.__high_score = data['state']['score']['high_score']
            self.__current_streak = data['state']['streak']['current_streak']
            self.__highest_streak = data['state']['streak']['highest_streak']   

    # Get a 5-letter input from the user
    def __getWord(self):
        while True:
            s = input('Enter a 5-letter word (Enter -1 to quit): ')

            if s == '-1':
                self.__save_state()
                sys.exit()

            if len(s) == 5 and s.upper() in self.__list_of_words:
                return s

    """
    Update the user's guess with the following character:
    - A '+' means that a character from the user's input 
      exists in the secret word but it is not in the 
      correct position.

    - A '-' means that a character from the user's input
      does not exists in the secret word
    """
    def __guess(self, guess_word):
        for i in range(len(self.__secret_word)):
            if guess_word[i] in self.__secret_word and guess_word[i] != self.__secret_word[i]:
                if guess_word[i] not in self.__valid_chars:
                    self.__valid_chars.append(guess_word[i].upper())
                guess_word = guess_word[:i] + '+' + guess_word[i + 1:]
            elif guess_word[i] not in self.__secret_word:
                if guess_word[i] not in self.__invalid_chars:
                    self.__invalid_chars.append(guess_word[i].upper())
                guess_word = guess_word[:i] + '-' + guess_word[i + 1:]

        return guess_word

    # Clear the console
    def __clear_console(self):
        if name == 'nt':
            system('cls')
        else:
            system('clear')

    # Reset the game state
    def __clear_state(self):
        self.__clear_console()
        self.__valid_chars.clear()
        self.__invalid_chars.clear()
        self.__secret_word = random.choice(self.__list_of_words)
        self.__board = [['_' for _ in range(5)] for _ in range(6)]

    # Reset the game
    def __reset(self):
        while True:
            next = input('Would you like to continue? (Y/N)? ')
            
            if next.upper() in ['Y', 'N']:
                break
            
        if next.upper() == 'N':
            self.__clear_state()
            self.__save_state()
            sys.exit()

        self.__clear_state()
    
    # Save the game state
    def __save_state(self):
        state = {
            'state': {
                'secret_word': self.__secret_word.encode('UTF-8').hex(),
                'valid': self.__valid_chars,
                'invalid': self.__invalid_chars,
                'board': self.__board,
                'score': {
                    'current_score': self.__current_score,
                    'high_score': self.__high_score
                },
                'streak': {
                    'current_streak': self.__current_streak,
                    'highest_streak': self.__highest_streak
                }
            }
        }

        with open('state.json', 'w') as f:
            f.write(json.dumps(state, indent=4))

    # Play Wordle game
    def play(self):
        while True:
            board = self.__board
            correct = False
            score = self.__current_score
            print(f'Score: {score}')

            for line in board:
                    b = ' '.join(line).split(',')
                    print(b)

            for i, j in zip(range(len(board)), range(len(board), -1, -1)):
                word = self.__getWord().upper()
                guess = self.__guess(word)

                if word != '':
                    self.__clear_console()

                print(f'Score: {score}')
                board[i] = [*guess]

                for line in board:
                    b = ' '.join(line).split(',')
                    print(b)

                print(f'Previous Guess: {word}')
                print(f'Valid: {self.__valid_chars}')
                print(f'Invalid: {self.__invalid_chars}')

                if word == guess:
                    score += j
                    correct = True
                    break

            if not correct:
                print(f'Secret Word: {self.__secret_word}')
                self.__current_score = 0
                self.__current_streak = 0
            else:                
                self.__current_score = score
                self.__high_score = max(self.__current_score, self.__high_score)
                
                self.__current_streak += 1
                self.__highest_streak = max(self.__current_streak, self.__highest_streak)

            print(f'Current Score: {self.__current_score}')
            print(f'Highest Score: {self.__high_score}')
            print(f'Current Streak: {self.__current_streak}')
            print(f'Highest Streak: {self.__highest_streak}')

            self.__reset()
            self.__save_state()

if __name__ == '__main__':
    wordle = Wordle()
    wordle.play()
