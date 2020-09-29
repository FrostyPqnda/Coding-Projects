'''
Created on Nov 23, 2019

@author: brian
'''
"""
Python program that asks for your name
and splits each letters into a list.
"""
name = input("What is your name? ")

num = 0

nameList = list(name)

print (nameList)

for letter in name:
    num += 1

print (num)


