'''
Created on Apr 28, 2020

@author: brian
'''

"""
FileGenerator is a simple Python program that
writes to a txt file that users can create
"""

# Names the file based on the user's input
file_name = input("Name your file: ")+".txt"
          

# This is where the user can type out the contents
# on their file
UserFile = open(file_name, "w")
write_to_file = input("Enter text: ")
UserFile.write(write_to_file)
UserFile.close()
print("SUCCESS! File written to " + file_name)   

    
