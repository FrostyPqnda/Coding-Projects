# Imports the Pokemon.py file to use the Pokemon class
from Pokemon import Pokemon
import random 
import pickle

salamence = Pokemon("Salamence", "Dragon", "Flying", "Intimidate", "Moxie", True, True, False, random.randint(1, 714), random.randint(1, 100))
charizard = Pokemon("Charizard", "Fire", "Flying", "Blaze", "Solar Power", True, True, True, random.randint(1, 714), random.randint(1, 100))
lucario = Pokemon("Lucario", "Fighting", "Steel", "Inner Focus", "Justified", True, True, False, random.randint(1, 714), random.randint(1, 100))
blastoise = Pokemon("Blastoise", "Water", "N/A", "Torrent", "Rain Dish", True, True, True, random.randint(1, 714), random.randint(1, 100))
gengar = Pokemon("Gengar", "Ghost", "Poison", "Cursed Body", "Levitate", True, True, False, random.randint(1, 714), random.randint(1, 100))
infernape = Pokemon("Infernape", "Fire", "Fighting", "Blaze", "Iron Fist", True, False, False, random.randint(1, 714), random.randint(1, 100))

pkmnList = []

pkmnList.append(salamence)
pkmnList.append(charizard)
pkmnList.append(lucario)
pkmnList.append(blastoise)
pkmnList.append(gengar)
pkmnList.append(infernape)

# Creates the 'PokemonData.txt' file and
# will overwrite any existing data
data = open('PokemonData.txt', 'w')

# Writes all the Pokemon object to
# the 'PokemonData.txt' file
for pkmn in pkmnList:
    data.write((str)(pkmn) + "\n")
    data.write("\n")

# Closes the file after all objects have been added
data.close()
