# Imports all functions of the tkinter module
from tkinter import *

count = 0
cps = 0

# Displays the user's click count
def clicks():
    global count
    count += 1
    counterLabel.configure(text = f'Clicks: {count}')

# Countdown timer that disables the button once it hits 0
def countdown(t):
    resetBtn.configure(state = 'disabled')
    timerLabel.configure(text = 'Timer: ' + str(t))
    if t > 0:
        root.after(1000, countdown, t - 1)
    if t == 0:
        clickBtn.configure(state = 'disabled')
        getCPS()
        resetBtn.configure(state = 'active')

# Calculates the user's cps and displays it on screen
def getCPS():
    cps = round((count / 10), 2)
    cpsLabel.configure(text = 'CPS: ' + str(cps))

# Resets the click speed tester
def reset():
    global count 
    global cps
    count = 0
    cps = 0
    counterLabel.configure(text = 'Clicks: ' + str(count))
    cpsLabel.configure(text = 'CPS: ' + str(cps))
    clickBtn.configure(state = 'active')
    countdown(10)

"""
Sets up the GUI layout where the click speed tester
will be displayed.
"""
root = Tk()
root.configure(bg = '#283140')
width= root.winfo_screenwidth() 
height= root.winfo_screenheight()
root.geometry("%dx%d" % (width, height))
root.title('Click Speed Tester')

# Creates a title text and adds it to the app. 
title = Label(root, font = ('Oswald', 40, 'bold'), background = '#283140', foreground = '#cccccc', text='CLICK SPEED TEST')
title.pack()

# Creates a canvas board for the app where most of the elements such as the click button will go.
canvas = Canvas(root, height = 700, width = 700, bg = '#1d4d80')
canvas.pack(expand=True, fill='both')

# Creates a label that displays the user's click count
counterLabel = Label(canvas, text = 'Clicks: 0', bg = '#222222', fg = '#cccccc', font = ('Oswald', 20, 'bold'))
counterLabel.pack(pady = 10)

# Creates a label that displays the countdown timer
timerLabel = Label(canvas, text = 'Timer: ', bg = '#222222', fg = '#cccccc', font = ('Oswald', 20, 'bold'))
timerLabel.pack(pady = 20)

# Creates a label that displays the user's average clicks per second
cpsLabel = Label(canvas, text = 'CPS: ' + str(cps), bg = '#222222', fg = '#cccccc', font = ('Oswald', 20, 'bold'))
cpsLabel.pack(pady = 25)

# Creates a click button that users will repeatedly press to record their average clicks per second
clickBtn = Button(canvas, text = 'CLICK ME', width = 25, height = 5, bg = '#222222', font = ('Oswald', 12), fg = '#cccccc', command = clicks) 
clickBtn.pack(pady = 80)

# Creates a reset button that allow users to redo the click speed tester
resetBtn = Button(canvas, text = 'RESET', width = 25, height = 2, bg = '#222222', font = ('Oswald', 12), fg = '#cccccc', command = reset)
resetBtn.pack(pady = 85)

# Displays the GUI app
countdown(10)
root.mainloop()