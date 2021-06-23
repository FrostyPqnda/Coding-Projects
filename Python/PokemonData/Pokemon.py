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
    def __init__(self, pkmnName = None, pkmnType = None, pkmnAbility = None, pkmnShiny = None, pkmnHP = 0, pkmnLevel = 0):
        self.pkmnName = pkmnName
        self.pkmnType = pkmnType
        self.pkmnAbility = pkmnAbility
        self.pkmnShiny = pkmnShiny
        self.pkmnHP = pkmnHP
        self.pkmnLevel = pkmnLevel
    
    # Returns the object as a string
    def __str__(self):
        pkmnName = "Pokemon: " + str(self.pkmnName)
        pkmnType = "\nType: " + str(self.pkmnType)
        pkmnAbility = "\nAbility: " + str(self.pkmnAbility)
        pkmnShiny = "\nShiny: " + str(self.pkmnShiny)
        pkmnHP = "\nHP: " + str(self.pkmnHP)
        pkmnLevel = "\nLevel: " + str(self.pkmnLevel)
        return pkmnName + pkmnType + pkmnAbility + pkmnShiny + pkmnHP + pkmnLevel