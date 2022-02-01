"""
Python program that will record keyboard input from the
user.

Purpose of the program: Collect the frequency of each letter
the user presses.
"""

import pynput.keyboard as kb
import matplotlib.pyplot as plt
from tkinter import *
from tkinter import messagebox

# List to store the key input
keyArr = []

# Checks if the key logger is still running
isRunning = True

# Creates a GUI app
app = Tk()
title = Label(app, font = ('Oswald', 40, 'bold'), background = '#283140', foreground = '#cccccc', text='Key Logger')
canvas = Canvas(app, width = 600, height = 600, bg = '#1d4d80')
headerText = Label(canvas, font = ('Oswald', 20, 'bold'), text = 'Press any key to start the recording.\nClose the application to end the recording', bg = '#1d4d80', fg = '#fff')
keyPressText = Text(canvas, bg = '#222', fg = "#fff", font = ('Oswald', 20, 'bold'), height = 10, width = 50)

# Destroys the GUI app on exit
def closeApp():
    global isRunning
    msgBox = messagebox.askquestion('Exit Application','Do you wish to end the recording?')

    # End the recording if the user decides to close the app.
    if msgBox == 'yes':
        app.destroy()
        isRunning = False

# Creates a pop-up GUI app
def drawApp():
    app.title('KeyLogger')
    app.iconbitmap('Icon.ico')
    app.minsize(520, 710)
    app.geometry("%dx%d" % (app.winfo_screenwidth(), app.winfo_screenheight()))
    app.unbind_all('<<PrevWindow>>')
    app.unbind_all('<<NextWindow>>')
    app.protocol("WM_DELETE_WINDOW", closeApp)
    app.state('zoomed')

    title.pack(fill = X)
    canvas.pack(expand=True, fill='both')
    headerText.pack(side = TOP, pady = 20)
    keyPressText.pack(side = TOP, padx = 10)

    app.mainloop()

# Record key presses and append them to a list
def keyPress(key):
    try:
        keyPressText.insert(END, f' Key pressed: {key.char}\n')

        if (key.char).isalpha():
            keyArr.append((key.char).upper())

    except AttributeError:
        keyPressText.insert(END, f' Key pressed: {key}\n')
    
    # Scrolls the text box to tahe end
    keyPressText.see("end")

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
    with kb.Listener(on_press = keyPress) as listener:
        drawApp()
        
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

recordKey()
bucket = {}
bucketize(keyArr, bucket)
writeData(bucket)

plt.title('Key Frequency Bar Graph')
plt.bar(*zip(*bucket.items()))
plt.show()