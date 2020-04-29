'''
Created on Apr 28, 2020

@author: brian
'''

"""
FileGenerator is a simple Python program that
writes to a txt file known as "UserFile.txt"
"""

UserFile = open("UserFile.txt", "w")
write_to_file = raw_input("Type: ")
UserFile.write(write_to_file)
UserFile.close()
print("SUCCESS!")

    
    
    
    
