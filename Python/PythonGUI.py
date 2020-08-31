# imports the tkinter module to
# set up the GUI
import tkinter as tk
import tkinter.ttk as ttk
  
# imports strftime function to 
# retrieve the system's time 
import datetime as dt

# Sets up the GUI to be displayed
root = tk.Tk()
root.configure(bg = '#283140')
root.title('Python GUI App')

# Displays the current time 
# onto to the GUI
def display_time(): 
    clock = dt.datetime.now()
    lbl.config(text = clock.strftime('%I:%M:%S %p')) 
    lbl.after(1000, display_time) 

# Creates a label where the time will be displayed
lbl = tk.Label(root, font = ('Oswald', 40, 'bold'), 
            background = '#2d57a1', 
            foreground = '#cccccc') 

# Places the clock at the center
# of the GUI app
lbl.pack(anchor = 'center') 
display_time() 

# Creates and adds a canvas for the GUI
canvas = tk.Canvas(root, height = 700, width = 700, bg = '#1d4d80')
canvas.pack()

# Runs the GUI
root.mainloop()




