"""
Python program that will record keyboard input from the
user.

Purpose of the program: Collect the frequency of each letter
the user presses.

NOTE: Upper and lowercase letters will be treated the same 
for simplicity sake. 
"""

import pynput.keyboard as kb

# List to store the key input
keyArr = []

# Record the key input from the user
# and store it into a list.
def keyPress(key):
    try:
        print('Key {0} pressed'.format(key.char))
        if str(key.char).isalpha():
            keyArr.append(str(key.char).upper())
    except AttributeError:
        print ('Key {0} pressed'.format(key))

# End the recording 
def keyRelease(key):
    if key == kb.Key.esc:
        return False

# Count the frequency of each key input and store
# it into a bucket.
def bucketize(data, bucket):
    # Store bucket dictionary with letter, count pair
    for i in range(65, 91):
        bucket[chr(i)] = 0

    for item in data:
        if item in bucket:
            bucket[item] += 1
                                    
# Record key presses
def recordKey():
    print('Press [esc] to end recording')
    with kb.Listener(on_press=keyPress, on_release=keyRelease) as listener:
        listener.join()

# Write the data to an excel sheet
def writeData(bucket):
    file = 'KeyLog.xlsx'
    data = open(file, 'w')

    data.write('Key\tCount\n')
        
    for key, value in bucket.items():
        data.write(key + '\t' + str(value) + '\n')
    
    print('File successfully written to', file)