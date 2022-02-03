"""
Python program that will record keyboard input from the
user.

Purpose of the program: Collect the frequency of each letter
the user presses.
"""
import pynput.keyboard as kb

# List to store the key input
keyArr = []

# Record key presses and append them to a list
def keyPress(key):
    try:
        print('Key {0} pressed'.format(key.char))

        if (key.char).isalpha():
            keyArr.append((key.char).upper())

    except AttributeError:
        print('Key {0} pressed'.format(key))

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

# Records the key logging session
def recordKey():
    print('Press [esc] to end recording')
    with kb.Listener(on_press = keyPress, on_release = keyRelease) as listener:
        listener.join()

# Write the data to an excel sheet
def writeData(data):
    with open('KeyLog.xlsx', 'w') as file:
        file.write('Key\tCount\n')
            
        for key, value in data.items():
            file.write(key + '\t' + str(value) + '\n')
    print('Data written to KeyLog.xlsx')


recordKey()
bucket = {}
bucketize(keyArr, bucket)
writeData(bucket)
