"""
Python project that will record keyboard input from the
user.

The keyboard input that will be collected will be instances
of when the user inputs a lowercase alpha character.
"""

from pynput import keyboard as kb

# List to store the key input
keyArr = []

# Record the key input from the user
# and store it into a list.
def keyPress(key):
    try:
        print('Key {0} pressed'.format(key.char))
        if str(key.char).isalpha() and str(key.char).islower():
            keyArr.append(key.char)
    except AttributeError:
        if key != kb.Key.esc:
            print('Non-alphanumeric key pressed')

# End the recording 
def keyRelease(key):
    if key == kb.Key.esc:
        return False

# Sorts the array.
# Method of sorting is insertion sort.
def sort(arr):
    for i in range(1, len(arr)):
        currItem = arr[i]
        prev = i - 1

        while prev >= 0 and currItem < arr[prev]:
            arr[prev + 1] = arr[prev]
            prev -= 1
        
        arr[prev + 1] = currItem

# Count the frequency of each key input and store
# it into a dictionary
def bucketize(arr):
    sort(arr)
    bucket = {}

    for item in arr:
        if item in bucket:
            bucket[item] += 1
        else:
            bucket[item] = 1
    return bucket

# Write the data to an excel sheet
def recordData(bucket):
    file = 'KeyLog.xlsx'
    data = open(file, 'w')

    data.write('Key\tCount\n')

    for item in bucket:
        data.write(item + '\t' + str(bucket[item]) + '\n')
    
    print('SUCCESS!')

print('Press [esc] to end recording')
with kb.Listener(on_press=keyPress, on_release=keyRelease) as listener:
    listener.join()

bucket = bucketize(keyArr)

recordData(bucket)