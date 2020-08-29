# importing whole module 
import tkinter as tk
import tkinter.ttk as ttk
  
# importing strftime function to 
# retrieve system's time 
import datetime as dt

root = tk.Tk()
root.configure(bg = "#283140")
root.title("Python GUI App")

def time(): 
    string = dt.datetime.now()
    lbl.config(text = string.strftime('%I:%M:%S %p')) 
    lbl.after(1000, time) 

lbl = tk.Label(root, font = ('Oswald', 40, 'bold'), 
            background = '#2d57a1', 
            foreground = '#cccccc') 
  
# Placing clock at the centre 
# of the tkinter window 
lbl.pack(anchor = 'center') 
time() 


canvas = tk.Canvas(root, height = 700, width = 700, bg = "#283140")
canvas.pack()

#frame = tk.Frame(root, bg = "#3c74d6")
#frame.place(relwidth = 0.8, relheight = 0.8, relx = 0.1, rely = 0.1)


root.mainloop()




