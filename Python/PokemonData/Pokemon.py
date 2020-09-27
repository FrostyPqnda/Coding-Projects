'''
Created on Sept 27, 2020

@author: brian
'''
"""
Class Pokemon

Used to store the Pokemon data object
"""
import random

class Pokemon:

    def __init__(self, name = "None", type1 = "None", type2 = "None", ability = "None", hiddenAbility = "None", shiny = False, canMegaEvolve = False, canGigantamax = False, hp = 0, level = 0):
        self.name = name
        self.type1 = type1
        self.type2 = type2
        self.ability = ability
        self.hiddenAbility = hiddenAbility
        self.shiny = shiny
        self.canMegaEvolve = canMegaEvolve
        self.canGigantamax = canGigantamax
        self.hp = hp
        self.level = level

    def set_hp(self, hp):
        self.hp = hp
