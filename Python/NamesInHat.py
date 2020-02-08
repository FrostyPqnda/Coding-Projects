'''
Created on Feb 7, 2020

@author: brian
'''

import random

"""
Class NameHat returns a random name
from a list.
"""
class NameHat:
    def __init__(self):
        self.names = []
    def __repr__(self):
        return str(self.name)
    
    def insert_name(self, name):
        if name not in self.names:
            self.names.append(name)
        else:
            return
    def draw_name(self):
        nameList = random.choice(self.names)
        self.names.remove(nameList)
        return nameList
                
hat = NameHat()

# long list of random names
list_of_names = random.choice(["Liam", "Noah", "William", "James", "Logan", "Benjamin", "Mason",
"Elijah", "Oliver", "Jacob", "Lucas", "Michael", "Alexander", "Ethan", "Daniel", "Matthew", "Aiden",
"Henry", "Joseph", "Jackson", "Samuel", "Sebastian", "David", "Carter", "Wyatt", "Jayden", "John", "Owen",  
"Dylan", "Luke", "Gabriel", "Anthony", "Isaac", "Grayson", "Jack", "Julian", "Levi", "Christopher", "Joshua",
"Andrew", "Lincoln", "Mateo", "Ryan", "Jaxon", "Nathan", "Aaron", "Isaiah", "Thomas", "Charles", "Caleb", "Josiah",
"Christian", "Hunter", "Eli", "Jonathan", "Connor", "Landon", "Adrian", "Asher", "Cameron", "Leo", "Theodore", "Jeremiah",
])

hat.insert_name(list_of_names)

print (hat.draw_name() + " was pulled from the hat!")
