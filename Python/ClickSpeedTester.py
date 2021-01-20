# Import modules
from tkinter import *

count = 0
cps = 0

def clicks():
    global count
    count += 1
    counterLabel.configure(text = f'Clicks: {count}')

def countdown(t):
    resetBtn.configure(state = 'disabled')
    timerLabel.configure(text = 'Timer: ' + str(t))
    if t > 0:
        root.after(1000, countdown, t - 1)
    if t == 0:
        clickBtn.configure(state = 'disabled')
        getCPS()
        resetBtn.configure(state = 'active')

def getCPS():
    cps = round((count / 10), 2)
    cpsLabel.configure(text = 'CPS: ' + str(cps))

def reset():
    global count 
    global cps
    count = 0
    cps = 0
    counterLabel.configure(text = 'Clicks: ' + str(count))
    cpsLabel.configure(text = 'CPS: ' + str(cps))
    clickBtn.configure(state = 'active')
    countdown(10)

root = Tk()
root.configure(bg = '#283140')
root.title('Click Speed Tester')

title = Label(root, font = ('Oswald', 40, 'bold'), background = '#283140', foreground = '#cccccc', text='CLICK SPEED TEST')
title.pack()

canvas = Canvas(root, height = 700, width = 700, bg = '#1d4d80')
canvas.pack(expand=True, fill='both')

counterLabel = Label(canvas, text = 'Clicks: 0', bg = '#222222', fg = '#cccccc', font = ('Oswald', 20, 'bold'))
counterLabel.pack(pady = 10)

timerLabel = Label(canvas, text = 'Timer: ', bg = '#222222', fg = '#cccccc', font = ('Oswald', 20, 'bold'))
timerLabel.pack(pady = 20)

cpsLabel = Label(canvas, text = 'CPS: ' + str(cps), bg = '#222222', fg = '#cccccc', font = ('Oswald', 20, 'bold'))
cpsLabel.pack(pady = 25)

clickBtn = Button(canvas, text = 'CLICK ME', width = 25, height = 5, bg = '#222222', font = ('Oswald', 12), fg = '#cccccc', command = clicks) 
clickBtn.pack(pady = 80)

resetBtn = Button(canvas, text = 'RESET', width = 25, height = 2, bg = '#222222', font = ('Oswald', 12), fg = '#cccccc', command = reset)
resetBtn.pack(pady = 85)

countdown(10)

root.mainloop()