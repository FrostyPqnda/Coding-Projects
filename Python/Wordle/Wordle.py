import requests
import random
from os import system, name
import json

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
        self.__in__valid_chars = []
        self.__valid_chars = []
        self.__secret_word = random.choice(self.__list_of_words)

        with open('data.json', 'r') as f:
            data = json.loads(f.read())
            self.__current_score = data['game_data']['score']['current_score']
            self.__high_score = data['game_data']['score']['high_score']
            self.__current_streak = data['game_data']['streak']['current_streak']
            self.__highest_streak = data['game_data']['streak']['highest_streak']       

        print(self.__secret_word)

    # Get a 5-letter input from the user
    def __getWord(self):
        while True:
            s = input('Enter a 5-letter word: ')

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
    def __guess(self, __secret_word, guess_word):
        for i in range(len(__secret_word)):
            if guess_word[i] == __secret_word[i]:
                if guess_word[i] not in self.__valid_chars:
                    self.__valid_chars.append(guess_word[i].upper())
                    self.__valid_chars.sort()
            elif guess_word[i] in __secret_word and guess_word[i] != __secret_word[i]:
                if guess_word[i] not in self.__valid_chars:
                    self.__valid_chars.append(guess_word[i].upper())
                    self.__valid_chars.sort()
                guess_word = guess_word[:i] + '+' + guess_word[i + 1:]
            elif guess_word[i] not in __secret_word:
                if guess_word[i] not in self.__in__valid_chars:
                    self.__in__valid_chars.append(guess_word[i].upper())
                    self.__in__valid_chars.sort()
                guess_word = guess_word[:i] + '-' + guess_word[i + 1:]

        return guess_word
    
    # Update the JSON file and print the current scoreboard
    def __update_file(self):
        data = {
            'game_data': {
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

        with open('data.json', 'w') as f:
            json.dump(data, f, indent = 4)

        print(f'Current Score: {self.__current_score}')
        print(f'Highest Score: {self.__high_score}')
        print(f'Current Streak: {self.__current_streak}')
        print(f'Highest Streak: {self.__highest_streak}')

    # Play the Wordle game
    def play(self):
        board = [['_' for _ in range(5)] for _ in range(6)]
        score = 0

        for line in board:
                b = ' '.join(line).split(',')
                print(b)

        for i, j in zip(range(len(board)), range(len(board), -1, -1)):
            word = self.__getWord().upper()
            guess = self.__guess(self.__secret_word, word)

            if word != '':
                if name == 'nt':
                    system('cls')
                else:
                    system('clear')

            board[i] = [*guess]

            for line in board:
                b = ' '.join(line).split(',')
                print(b)

            print(f'Previous Guess: {word}')
            print(f'Valid: {self.__valid_chars}')
            print(f'Invalid: {self.__in__valid_chars}')

            if word == guess:
                score += j
                break

        if score == 0:
            print(f'Secret Word = {self.__secret_word}')
            self.__current_score = 0
            self.__current_streak = 0
        else:
            print(f'Score = {score}')
            
            self.__current_score += score
            self.__high_score = max(self.__current_score, self.__high_score)
            
            self.__current_streak += 1
            self.__highest_streak = max(self.__current_streak, self.__highest_streak)

        self.__update_file()


if __name__ == '__main__':
    wordle = Wordle()
    wordle.play()