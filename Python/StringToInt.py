'''
Created on May 31, 2020

@author: brian
'''

def safe_int(word):
    try:
        convert_to_int = int(word)
        return convert_to_int
    except ValueError:
        return 0
    
string_int = ['194234', '902', ' 332', 'A', 'B', 'C']

print ([safe_int(word) for word in string_int])