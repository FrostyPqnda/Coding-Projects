from random import randint
import matplotlib.pyplot as plt
import matplotlib.ticker as ticker
import tkinter as tk
from matplotlib.figure import Figure
from matplotlib.backends.backend_tkagg import (FigureCanvasTkAgg, 
NavigationToolbar2Tk)
import os
import base64
from icon import iconImg
from logo import logoImg
from PIL import Image, ImageTk

app = tk.Tk()
title = tk.Label(app, font = ('Oswald', 40, 'bold'), background = '#283140', foreground = '#fff', text='Dice Roll Simulator')
canvas = tk.Canvas(app, width = 600, height = 600, bg = '#1d4d80')
entry = tk.Entry(canvas, width = 10, font=('Oswald 24'), justify = tk.CENTER)
fig = Figure(figsize = (6, 5), dpi = 100)
plot_canvas = None
freq = tk.Label(canvas, font = ('Oswald', 15, 'bold'), width = 25, height = 7, background = '#283140', foreground = '#fff')
btn = tk.Button(canvas, width = 10, height = 1, font=('Oswald 12'), text = 'Generate')
colors = [
    '#14262B', '#25575B', '#408873',
    '#88A986', '#DFDFAF', '#425768',
]
personalLogo = tk.Label(canvas, bg = '#1d4d80')

# Creates the icon existing in the top left corner of the application
def createIcon():
    tmpIcon = open('tempIcon.ico','wb+')
    tmpIcon.write(base64.b64decode(iconImg))
    tmpIcon.close()

    app.iconbitmap('tempIcon.ico')
    os.remove('tempIcon.ico')

# Add my logo to the bottom left of my app.
def createLogo():
    tmpLogo = open('tempLogo.ico', 'wb+')
    tmpLogo.write(base64.b64decode(logoImg))
    tmpLogo.close()

    image = Image.open('tempLogo.ico')
    image = image.resize((30, 30))
    logo = ImageTk.PhotoImage(image)    

    personalLogo.config(image = logo)
    personalLogo.image = logo # Keep a reference to the logo.
    personalLogo.pack(anchor='w', side = tk.BOTTOM, padx = 7, pady = 7)

    os.remove('tempLogo.ico')

def drawApp():
    #createIcon()
    createLogo()

    app.title('Dice Roll Simulator')
    title.pack(fill = tk.X)
    minWidth = int(app.winfo_screenwidth() * 0.40)
    minHeight = int(app.winfo_screenheight() * 0.75)
    app.minsize(minWidth, minHeight)
    app.geometry('%dx%d' % (app.winfo_screenwidth(), app.winfo_screenheight()))
    canvas.pack(expand=True, fill='both')
    app.state('zoomed')
    entry.pack(pady = 10)
    btn.configure(command = generateGraph)
    btn.pack(pady = 20)
    app.mainloop()

def generateGraph():
    global plot_canvas
    global btn
    
    if plot_canvas:
        plot_canvas.get_tk_widget().destroy()

    diceRolls = rollDie()
    bucket = bucketize(diceRolls)
    str = ''

    plot_canvas = FigureCanvasTkAgg(fig, canvas)
    ax = fig.add_subplot(1,1,1)
    ax.set_title('Dice Roll Frequency\n# of Rolls: ' + entry.get())
    barplot = ax.barh(*zip(*bucket.items()), color = colors, align = 'center', ec = 'black')
    ax.invert_yaxis()
    ax.set_ylabel('Dice #')
    ax.set_xlabel('Frequency')

    for axis in[ax.xaxis, ax.yaxis]:
        axis.set_major_locator(ticker.MaxNLocator(integer=True))

    ax.set_xlim(0)

    plot_canvas.get_tk_widget().pack()
    plot_canvas.get_tk_widget().pack(side=tk.TOP, fill=tk.NONE, expand=0)
    #toolbar = NavigationToolbar2Tk(plot_canvas, app)
    #toolbar.update()
    #plot_canvas.get_tk_widget().pack()

    app.after(1000, None)
    
    for key, value in bucket.items():
        str += 'Frequency of ' + (f'{key}: {value}') + '\n'

    #freq.config(text = str)
    #freq.pack(side = tk.TOP, anchor = 'w', pady = 20)

# Return a list of dice rolls
def rollDie():
    return [randint(1, 6) for _ in range(int(entry.get()))]

# Return a dictionary containing the frequency of each roll
def bucketize(diceRolls):
    bucket = {
        1: 0,
        2: 0,
        3: 0,
        4: 0,
        5: 0,
        6: 0
    }

    for item in diceRolls:
        if item in bucket:
            bucket[item] += 1
        else:
            bucket[item] = 1

    return bucket

drawApp()