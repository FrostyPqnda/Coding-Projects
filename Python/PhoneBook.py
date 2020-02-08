'''
Created on Nov 23, 2019

@author: brian
'''
"""
Python program that asks for a name and phone number, and
adds it to the phone_book dictionary.
"""
phone_book = {}
name = ""
number = ""
while True:
    name = raw_input("Enter name: ")
    if name == "":
        break
    elif name in phone_book:
        print ("Phone number: " + str(phone_book[name]))
    else:
        number = raw_input("Enter phone number: ")
        phone_book[name] = number
print (phone_book)







