# Imports all functions of the tkinter module
from tkinter import *

count = 0
cps = 0

# Displays the user's click count
def clicks():
    global count
    count += 1
    clickCounterLabel.configure(text = f'Clicks: {count}')

# Countdown timer that disables the button once it hits 0
def countdown(t):
    countdownTimerLabel.configure(text = 'Timer: ' + str(t))
    if t > 0:
        root.after(1000, countdown, t - 1)
    if t == 0:
        clickBtn.configure(state = 'disabled')
        getCPS()
        resetBtn.configure(state = 'active')

# Calculates the user's cps and displays it on screen
def getCPS():
    cpsLabel.configure(text = 'CPS: ' + str(round((count / 10), 2)))

# Resets the click speed tester
def reset():
    global count 
    global cps
    count = 0
    cps = 0
    clickCounterLabel.configure(text = 'Clicks: ' + str(count))
    cpsLabel.configure(text = 'CPS: ' + str(cps))
    clickBtn.configure(state = 'active')
    countdown(10)

"""
Sets up the GUI layout where the click speed tester
will be displayed.
"""
root = Tk()
root.configure(bg = '#283140')
root.geometry("%dx%d" % (root.winfo_screenwidth(), root.winfo_screenheight()))
root.title('Click Speed Tester')

# Giant title text
title = Label(root, font = ('Oswald', 40, 'bold'), background = '#283140', foreground = '#cccccc', text='CLICK SPEED TEST')
title.pack()  

# A "board" where all of the buttons and labels will be displayed on
canvas = Canvas(root, height = 700, width = 700, bg = '#1d4d80')
canvas.pack(expand=True, fill='both')

# Creates a label that displays the number of clicks the user has made
clickCounterLabel = Label(canvas, text = 'Clicks: 0', bg = '#222222', fg = '#cccccc', font = ('Oswald', 20, 'bold'))
clickCounterLabel.pack(pady = 10)

# Creates a label that displays the countdown timer
countdownTimerLabel = Label(canvas, text = 'Timer: ', bg = '#222222', fg = '#cccccc', font = ('Oswald', 20, 'bold'))
countdownTimerLabel.pack(pady = 20)

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