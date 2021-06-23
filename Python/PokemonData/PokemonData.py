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
"""
Creates 6 objects that are an instance of the Pokemon class.
Each Pokemon object will have a randomized HP and level.
Each pokemon's HP will be determined by the highest possible HP 
they will have at level 50 and 100. The levels will be set between [50, 100].
"""
salamence = Pokemon("Salamence", "Dragon, Flying", "Intimidate, Moxie", True, randint(202, 394), randint(50, 100))
charizard = Pokemon("Charizard", "Fire, Flying", "Blaze, Solar Power", True, randint(185, 360), randint(50, 100))
lucario = Pokemon("Lucario", "Fighting, Steel", "Inner Focus, Justified", True, randint(177, 344), randint(50, 100))
blastoise = Pokemon("Blastoise", "Water", "Torrent, Rain Dish", True, randint(186, 362), randint(50, 100))
gengar = Pokemon("Gengar", "Ghost, Poison", "Cursed Body", True, randint(167, 324), randint(50, 100))
infernape = Pokemon("Infernape", "Fire, Fighting", "Blaze, Iron Fist", True, randint(183, 356), randint(50, 100))
gyarados = Pokemon("Gyarados", "Water, Flying", "Intimidate, Moxie", True, randint(202, 394), randint(50, 100))
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

# Creates the 'PokemonData.txt' file and
# will overwrite any pre-existing data
# Note: any pre-existing data about a Pokemon will be overwrittten
pkmn_data_file = 'PokemonData.txt'
data = open(pkmn_data_file, 'w')

# Writes all the Pokemon objects in the pkmnList to
# the 'PokemonData.txt' file
for pkmn in pkmnList:
    data.write((str)(pkmn) + "\n")
    data.write("=====================================")
    data.write("\n")

# Closes the file after all objects have been added
data.close()
print("Pokemon data successfully written to " + pkmn_data_file)