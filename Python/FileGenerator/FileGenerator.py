'''
Created on Apr 28, 2020

@author: brian
'''

"""
FileGenerator is a simple Python program that
writes to a txt file that an user can create
"""

# Names the file based on the user's input
name_file = raw_input("Name your file: ")

# Adds a .txt extension to the user's created file
file_name = name_file+".txt"

# This is where the user can type out what he/she wants
# on their file
UserFile = open(file_name, "w")
write_to_file = raw_input("Type: ")
UserFile.write(write_to_file)
UserFile.close()
print("SUCCESS!")



    
    
    
    