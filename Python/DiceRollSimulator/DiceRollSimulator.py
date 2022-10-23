from random import randint
import matplotlib.pyplot as plt
import matplotlib.ticker as ticker
import tkinter as tk
from matplotlib.figure import Figure
from matplotlib.backends.backend_tkagg import (FigureCanvasTkAgg, 
NavigationToolbar2Tk)

app = tk.Tk()
title = tk.Label(app, font = ('Oswald', 40, 'bold'), background = '#283140', foreground = '#fff', text='Dice Roll Simulator')
canvas = tk.Canvas(app, width = 600, height = 600, bg = '#1d4d80')
entry = tk.Entry(canvas, width = 20, font=('Oswald 24'))
fig = Figure(figsize = (6, 5), dpi = 100)
plot_canvas = None
freq = tk.Label(canvas, font = ('Oswald', 15, 'bold'), width = 50, height = 5, background = '#283140', foreground = '#fff')

colors = [
    '#14262B', '#25575B', '#408873',
    '#88A986', '#DFDFAF', '#425768',
]

def drawApp():
    app.title('Dice Roll Simulator')
    title.pack(fill = tk.X)
    minWidth = int(app.winfo_screenwidth() * 0.40)
    minHeight = int(app.winfo_screenheight() * 0.75)
    app.minsize(minWidth, minHeight)
    app.geometry('%dx%d' % (app.winfo_screenwidth(), app.winfo_screenheight()))
    canvas.pack(expand=True, fill='both')
    app.unbind_all('<<PrevWindow>>')
    app.unbind_all('<<NextWindow>>')
    app.state('zoomed')
    entry.pack(pady = 10)
    btn = tk.Button(canvas, width = 10, height = 1, font=('Oswald 12'), text = 'Generate', command = generateGraph)
    btn.pack(pady = 20)
    app.mainloop()

def generateGraph():
    global plot_canvas
    
    if plot_canvas:
        plot_canvas.get_tk_widget().destroy()

    diceRolls = rollDie()
    bucket = bucketize(diceRolls)
    str = ''

    plot_canvas = FigureCanvasTkAgg(fig, canvas)
    ax = fig.add_subplot(111)
    barplot = ax.barh(*zip(*bucket.items()), color = colors, align = 'center', ec = 'black')
    ax.invert_yaxis()
    ax.set_ylabel('Dice #')
    ax.set_xlabel('Frequency')

    for axis in[ax.xaxis, ax.yaxis]:
        axis.set_major_locator(ticker.MaxNLocator(integer=True))

    ax.set_xlim(0)

    #plot_canvas.get_tk_widget().pack()
    plot_canvas.get_tk_widget().pack(side=tk.TOP, fill=tk.NONE, expand=0)
    #toolbar = NavigationToolbar2Tk(plot_canvas, app)
    #toolbar.update()
    #plot_canvas.get_tk_widget().pack()

    app.after(1000, None)
    """
    for key, value in bucket.items():
        str += 'Frequency of Dice #' + (f'{key}: {value}') + '\n'

    freq.config(text = str)
    freq.pack(pady = 10)
    """
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