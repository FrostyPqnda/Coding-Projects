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
    # Order: Name, Type, Ability, Shiny?, Mega Evolution?, HP, Level
    def __init__(self, pkmnName = "MissingNo.", pkmnType = "N/A", pkmnAbility = "N/A", pkmnShiny = None, pkmnMegaEvolve = None, pkmnHP = 0, pkmnLevel = 0):
        self.pkmnName = pkmnName
        self.pkmnType = pkmnType
        self.pkmnAbility = pkmnAbility
        self.pkmnShiny = pkmnShiny
        self.pkmnMegaEvolve = pkmnMegaEvolve
        self.pkmnHP = pkmnHP
        self.set_hp(pkmnHP)
        self.pkmnLevel = pkmnLevel
        self.set_level(pkmnLevel)

    # Sets the HP of the Pokemon based on a random number between [1, 714]
    def set_hp(self, pkmnHP):
        if self.pkmnHP < 0 or self.pkmnHP > 714:
            self.pkmnHP = randint(1, 714)

    # Sets the HP of the Pokemon based on a random number between [1, 100]
    def set_level(self, pkmnHP):
        if self.pkmnHP < 0 or self.pkmnHP > 100:
            self.pkmnHP = randint(1, 100)
    
    # Returns the object as a string
    def __str__(self):
        pokemon = "Pokemon: " + self.pkmnName
        pkmnType = "\nType: " + self.pkmnType
        pkmnAbility = "\nAbility: " + self.pkmnAbility
        pkmnShiny = "\nShiny? " + str(self.pkmnShiny)
        pkmnMegaEvolve = "\nMega Evolution? " + str(self.pkmnMegaEvolve)
        pkmnHP = "\nHP: " + str(self.pkmnHP)
        pkmnLevel = "\nLevel: " + str(self.pkmnLevel)
        return pokemon + pkmnType + pkmnAbility + pkmnShiny + pkmnMegaEvolve + pkmnHP + pkmnLevel
        
        
