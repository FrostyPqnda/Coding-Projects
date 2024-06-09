'''
Created on Sept 27, 2020

@author: brian
'''
"""
Writes a Pokemon's data
to a .txt file
"""
from Pokemon import Pokemon # Imports the Pokemon.py file to use the Pokemon class
from random import randint # Imports the randint function from the random module

salamence = Pokemon("Salamence", "Dragon, Flying", "Intimidate, Moxie", randint(202, 394), randint(50, 100))
charizard = Pokemon("Charizard", "Fire, Flying", "Blaze, Solar Power", randint(185, 360), randint(50, 100))
lucario = Pokemon("Lucario", "Fighting, Steel", "Inner Focus, Justified", randint(177, 344), randint(50, 100))
blastoise = Pokemon("Blastoise", "Water", "Torrent, Rain Dish", randint(186, 362), randint(50, 100))
gengar = Pokemon("Gengar", "Ghost, Poison", "Cursed Body", randint(167, 324), randint(50, 100))
infernape = Pokemon("Infernape", "Fire, Fighting", "Blaze, Iron Fist", randint(183, 356), randint(50, 100))
gyarados = Pokemon("Gyarados", "Water, Flying", "Intimidate, Moxie", randint(202, 394), randint(50, 100))
pk = Pokemon()

# Creates a list of Pokemon objects to store
pkmnList = []

# Adds the Pokemon objects to pkmnList
pkmnList.append(salamence)
pkmnList.append(charizard)
pkmnList.append(lucario)
pkmnList.append(blastoise)
pkmnList.append(gengar)
pkmnList.append(infernape)
pkmnList.append(gyarados)
pkmnList.append(pk)

# Creates the 'PokemonData.txt' file
# Will overwrite any pre-existing data
# Note: any pre-existing data about a Pokemon will be overwrittten
with open('PokemonData.txt', 'w') as pk:
    for pkmn in pkmnList:
        pk.write(str(pkmn))
        pk.write('\n=====================================\n')

print("Pokemon data successfully written to PokemonData.txt")