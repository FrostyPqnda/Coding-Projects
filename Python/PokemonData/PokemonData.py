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

salamence = Pokemon("Salamence", "Dragon", "Flying", "Intimidate", "Moxie", True, True, False, randint(1, 714), randint(1, 100))
charizard = Pokemon("Charizard", "Fire", "Flying", "Blaze", "Solar Power", True, True, True, randint(1, 714), randint(1, 100))
lucario = Pokemon("Lucario", "Fighting", "Steel", "Inner Focus", "Justified", True, True, False, randint(1, 714), randint(1, 100))
blastoise = Pokemon("Blastoise", "Water", "N/A", "Torrent", "Rain Dish", True, True, True, randint(1, 714), randint(1, 100))
gengar = Pokemon("Gengar", "Ghost", "Poison", "Cursed Body", "Levitate", True, True, False, randint(1, 714), randint(1, 100))
infernape = Pokemon("Infernape", "Fire", "Fighting", "Blaze", "Iron Fist", True, False, False, randint(1, 714), randint(1, 100))
gyarados = Pokemon("Gyarados", "Water", "Flying", "Intimidate", "Moxie", True, True, False, randint(1, 714), randint(1, 100))
pkmnList = []

# Adds the Pokemons to pkmnList
pkmnList.append(salamence)
pkmnList.append(charizard)
pkmnList.append(lucario)
pkmnList.append(blastoise)
pkmnList.append(gengar)
pkmnList.append(infernape)
pkmnList.append(gyarados)

# Creates the 'PokemonData.txt' file and
# will overwrite any pre-existing data
data = open('PokemonData.txt', 'w')

# Writes all the Pokemon object to
# the 'PokemonData.txt' file
for pkmn in pkmnList:
    data.write((str)(pkmn) + "\n")
    data.write("\n")

# Closes the file after all objects have been added
data.close()
