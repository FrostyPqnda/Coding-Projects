# Import modules
from tkinter import *
import time

count = 0
cps = 0
running = False

def clicks():
    global count
    global running
    running = True
    if running == True:
        count += 1
        counterLabel.configure(text = f'Clicks: {count}')

def countdown(t):
    global running
    running = True
    if running == True:
        timerLabel.configure(text = 'Timer: ' + str(t))

        if t > 0:
            root.after(1000, countdown, t - 1)
        if t == 0:
            running == False

root = Tk()
root.configure(bg = '#283140')
root.title('Click Speed Tester')

title = Label(root, font = ('Oswald', 40, 'bold'), background = '#283140', foreground = '#cccccc', text='CLICK SPEED TEST')
title.pack()

canvas = Canvas(root, height = 700, width = 700, bg = '#1d4d80')
canvas.pack(expand=True, fill='both')

counterLabel = Label(canvas, text = 'Clicks: ', bg = '#222222', fg = '#cccccc', font = ('Oswald', 20, 'bold'))
counterLabel.pack(pady = 10)

timerLabel = Label(canvas, text = 'Timer: ', bg = '#222222', fg = '#cccccc', font = ('Oswald', 20, 'bold'))
timerLabel.pack(pady = 30)

countdown(10)

cpsLabel = Label(canvas, text = 'CPS: ' + str(cps), bg = '#222222', fg = '#cccccc', font = ('Oswald', 20, 'bold'))
cpsLabel.pack(pady = 40)

btn = Button(canvas, text = 'CLICK ME', width = 25, height = 5, bg = '#222222', font = ('Oswald', 12), fg = '#cccccc', command = clicks) 
btn.pack(padx = 350, pady = 80)

root.mainloop()