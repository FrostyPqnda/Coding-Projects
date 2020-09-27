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
    # Order: Name, Type 1, Type 2, Ability, Hidden Ability, Shiny?, Mega Evolution?, Gigantamax Factor?, HP, Level
    def __init__(self, name = "MissingNo.", type1 = "N/A", type2 = "N/A", ability = "N/A", hiddenAbility = "N/A", shiny = False, canMegaEvolve = False, canGigantamax = False, hp = 0, level = 0):
        self.name = name
        self.type1 = type1
        self.type2 = type2
        self.ability = ability
        self.hiddenAbility = hiddenAbility
        self.shiny = shiny
        self.canMegaEvolve = canMegaEvolve
        self.canGigantamax = canGigantamax
        self.hp = hp
        self.set_hp(hp)
        self.level = level
        self.set_level(level)

    # Sets the HP of the Pokemon based on a random number between [1, 714]
    def set_hp(self, hp):
        if self.hp < 0 or self.hp > 714:
            self.hp = randint(1, 714)
        else:
            self.hp = hp

    # Sets the HP of the Pokemon based on a random number between [1, 100]
    def set_level(self, level):
        if self.level < 0 or self.level > 100:
            self.level = randint(1, 100)
        else:
            self.level = level

    # Compares two Pokemon objects
    def __eq__(self, value):
        return super().__eq__(value)
    
    # Returns the object as a string
    def __str__(self):
        pokemon = "Pokemon: " + self.name
        type_one = "\nType 1: " + self.type1
        type_two = "\nType 2: " + self.type2
        base_ability = "\nAbility: " + self.ability
        hidden_ability = "\nHidden Ability: " + self.hiddenAbility
        shiny_value = "\nShiny? " + str(self.shiny)
        mega_evolve = "\nMega Evolution? " + str(self.canMegaEvolve)
        gigantamx_factor = "\nGigantamax Factor? " + str(self.canGigantamax)
        base_hp = "\nHP: " + str(self.hp)
        base_level = "\nLevel: " + str(self.level)
        return pokemon + type_one + type_two + base_ability + hidden_ability + shiny_value + mega_evolve + gigantamx_factor + base_hp + base_level
        
        
