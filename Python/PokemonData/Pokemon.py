'''
Created on Sept 27, 2020

@author: brian
'''
"""
Class Pokemon

Used to store the Pokemon data object
"""
from random import randint # Imports the randint function from the random module

class Pokemon:
    # Constructor for the Pokemon class
    # Order: Name, Type, Ability, Shiny, HP, Level
    # Type: String, String, String, Boolean, Int, Int
    def __init__(self, pkmnName: str = 'MissingNo.', pkmnType: str = None, pkmnAbility: str = None, pkmnHP: int = 0, pkmnLevel: int= 0):
        self.pkmnName = pkmnName
        self.pkmnType = pkmnType
        self.pkmnAbility = pkmnAbility
        self.pkmnHP = pkmnHP
        self.pkmnLevel = pkmnLevel
    
    # Returns the object as a string
    def __str__(self):
        return f'Pokemon: {self.pkmnName}\nType: {self.pkmnType}\nAbility: {self.pkmnAbility}\nHP: {self.pkmnHP}\nLevel: {self.pkmnLevel}'
