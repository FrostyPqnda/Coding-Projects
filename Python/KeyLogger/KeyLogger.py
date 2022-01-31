"""
Python program that will record keyboard input from the
user.

Purpose of the program: Collect the frequency of each letter
the user presses.
"""

from email import message
import pynput.keyboard as kb
import matplotlib.pyplot as plt
from tkinter import *
from tkinter import messagebox

# List to store the key input
keyArr = []

# Checks if the key logger is still running
isRunning = True

# Creates a GUI window
root = Tk()
root.configure(bg = '#283140')

# Destroys the GUI app on exit
def exit():
    global isRunning
    msgBox = messagebox.askquestion('Exit Application','Do you wish to end the recording?', icon = 'warning')

    if msgBox == 'yes':
        root.destroy()
        isRunning = False

# Creates a pop-up GUI app
def createPopUpWindow():
    canvas = Canvas(root, width = 300, height = 300, bg = '#1d4d80')
    canvas.pack()
    button = Button(root, text = 'End Recording', command = exit, bg = '#222222', fg = '#cccccc', width = 25, height = 5)
    canvas.create_window(150, 150, window = button)
    root.protocol("WM_DELETE_WINDOW", exit)
    root.mainloop()

# Record key presses and append them to a list
def keyPress(key):
    try:
        print('Key {0} pressed'.format(key.char))

        if (key.char).isalpha():
            keyArr.append((key.char).upper())

    except AttributeError:
        print('Key {0} pressed'.format(key))

# Count the frequency of each key input and store
# it into a bucket.
def bucketize(data, bucket):
    # Store bucket dictionary with letter, count pair
    for i in range(65, 91):
        bucket[chr(i)] = 0

    for item in data:
        if item in bucket:
            bucket[item] += 1

# Runs a key logging session
def runKeyLogger():
    print('KeyLogger recording in session [Close the pop-up window to end the recording.]:')
    with kb.Listener(on_press = keyPress) as listener:
        createPopUpWindow()
        
        if not isRunning:
            return False

        listener.join()

# Write the data to an excel sheet
def writeData(data):
    inFile = 'KeyLog.xlsx'

    with open(inFile, 'w') as file:
        file.write('Key\tCount\n')
            
        for key, value in data.items():
            file.write(key + '\t' + str(value) + '\n')
    
    print('File successfully written to', inFile)

runKeyLogger()
bucket = {}
bucketize(keyArr, bucket)
writeData(bucket)

plt.title('Key Frequency Bar Graph')
plt.bar(*zip(*bucket.items()))
plt.show()