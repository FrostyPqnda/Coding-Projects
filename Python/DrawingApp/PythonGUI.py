"""
Created on Aug 31, 2020

@author: brian

Python project that uses the
tkinter module to create a GUI
app.
"""

# imports the tkinter module to
# set up the GUI
import tkinter as tk

# imports ImageGrab from
# the PIL module to save images
from PIL import ImageGrab

# Sets up the GUI to be displayed
root = tk.Tk()
root.configure(bg = '#283140')
root.title('GUI App')

# Instruction for the GUI app
msg = tk.Label(
    root, 
    font = ('Oswald', 40, 'bold'), 
    background = '#2d57a1', 
    foreground = '#cccccc', 
    text='Click and drag to draw')
msg.pack(anchor = 'center')

# Creates and adds a canvas for the GUI
canvas = tk.Canvas(root, height = 700, width = 700, bg = '#1d4d80')
canvas.pack(expand=True, fill='both')

# Draws on the canvas
def draw(event):
    color = 'white'
    x1, y1 = (event.x-1), (event.y-1)
    x2, y2 = (event.x+1), (event.y+1)
    canvas.create_line(x1, y1, x2, y2, fill=color, width = 5)

# Saves the image
def save(event):
    x = root.winfo_rootx()+canvas.winfo_x()
    y = root.winfo_rooty()+canvas.winfo_y()
    x1 = x + canvas.winfo_width()
    y1 = y + canvas.winfo_height()
    store = ImageGrab.grab((x, y, x1, y1))
    store.save("img.png")

canvas.bind('<B1-Motion>', draw) # Calls the draw function on click and drag
root.bind('<Control-s>', save) # Calls the save function on 'ctrl-s'

saveLbl = tk.Label(
    root, 
    font = ('Oswald', 30, 'bold'), 
    background = '#2d57a1', 
    foreground = '#cccccc', 
    text='Ctrl-S to save')
saveLbl.pack(anchor = 'center')

# Runs the GUI
root.mainloop()




