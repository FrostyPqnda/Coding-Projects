# Import modules
from tkinter import *
import pygame
from tkinter import filedialog
import time
from mutagen.mp3 import MP3
import tkinter.ttk as ttk

# Initialize Pygame Mixer
pygame.mixer.init()

def play_time():
    if stopped:
        return

    curr_time = pygame.mixer.music.get_pos() / 1000
    conv_curr_time = time.strftime('%M:%S', time.gmtime(curr_time))

    song = playlist.get(ACTIVE)
    song = f'D:/Coding-Projects/Python/AudioPlayerApp/Music/{song}.mp3'
    
    song_mut = MP3(song)
    #global song_length
    song_length = song_mut.info.length
    
    conv_song_length = time.strftime('%M:%S', time.gmtime(song_length))
    status_bar.config(text = f'Time Elapsed: {conv_curr_time} of {conv_song_length} ')
    
    curr_time += 1

    if(int(music_slider.get()) == int(song_length)):
        status_bar.config(text=f'Time Elapsed: {conv_song_length}  of  {conv_song_length}  ')
    elif paused:
        pass
    elif int(music_slider.get()) == int(curr_time):
        slider_pos = int(song_length)
        music_slider.config(to = slider_pos, value = int(curr_time))
    else:
        # Update Slider position
        slider_pos = int(song_length)
        music_slider.config(to=slider_pos, value=int(music_slider.get()))
    
        conv_curr_time = time.strftime('%M:%S', time.gmtime(int(music_slider.get())))
        status_bar.config(text=f'Time Elapsed: {conv_curr_time}  of  {conv_song_length}  ')
        
        next_time = int(music_slider.get()) + 1
        music_slider.config(value = next_time)

    # Update time
    status_bar.after(1000, play_time)
    
# Add one song
def add_song():
    song = filedialog.askopenfilename(initialdir='Music/', title = 'Choose A Song', filetypes = (('MP3 Files', '*.mp3'), ('WAV Files', '*.wav'), ('OGG Files', '*.ogg')))
    song = song.replace('D:/Coding-Projects/Python/AudioPlayerApp/Music/', '')
    
    if '.mp3' in song:
        song = song.replace('.mp3', '')

    if '.wav' in song:
        song = song.replace('.wav', '')

    if '.ogg' in song:
        song = song.replace('.ogg', '')

    playlist.insert(END, song)

# Add songs
def add_many_songs():
    songs = filedialog.askopenfilenames(initialdir='Music/', title = 'Choose A Song', filetypes = (('MP3 Files', '*.mp3'), ))
    for song in songs:
        song = song.replace('D:/Coding-Projects/Python/AudioPlayerApp/Music/', '')
        song = song.replace('.mp3', '')
        playlist.insert(END, song)

# Play song
def play():
    global stopped
    stopped = False
    
    song = playlist.get(ACTIVE)
    song = f'D:/Coding-Projects/Python/AudioPlayerApp/Music/{song}.mp3'
    
    pygame.mixer.music.load(song)
    pygame.mixer.music.play(loops = 0)
    
    play_time()

    curr_vol = pygame.mixer.music.get_volume()
    curr_vol = curr_vol * 100

#Stop song
global stopped
stopped = False
def stop():
    # Reset slider and status bar
    status_bar.config(text='')
    music_slider.config(value = 0)

    # Stop current song from playing
    pygame.mixer.music.stop()
    playlist.selection_clear(ACTIVE)
    
    # Clear status bar
    status_bar.config(text='')

    # Set stopped variable to True
    global stopped
    stopped = True

global paused
paused = False
# Pause song
def pause(is_paused):
    global paused
    paused = is_paused
    if paused:
        pygame.mixer.music.unpause()
        paused = False
    else:
        pygame.mixer.music.pause()
        paused = True

# Slider
def slide(x):
    song = playlist.get(ACTIVE)
    song = f'D:/Coding-Projects/Python/AudioPlayerApp/Music/{song}.mp3'
    
    pygame.mixer.music.load(song)
    pygame.mixer.music.play(loops = 0, start = int(music_slider.get()))

# Volume function
def volume(x):
    pygame.mixer.music.set_volume(volume_slider.get())

    # Get current volume
    curr_vol = pygame.mixer.music.get_volume()
    curr_vol = curr_vol * 100
    

# Go to previous song
def prev_song():
    # Reset slider and status bar
    status_bar.config(text='')
    music_slider.config(value = 0)

    # Get current song tuple number and goes back 1
    next_one = playlist.curselection()
    next_one = next_one[0]-1
    
    song = playlist.get(next_one)
    song = f'D:/Coding-Projects/Python/AudioPlayerApp/Music/{song}.mp3'
    
    pygame.mixer.music.load(song)
    pygame.mixer.music.play(loops = 0)
    
    playlist.selection_clear(0, END)
    playlist.activate(next_one)
    playlist.selection_set(next_one, last = None)

# Go to next song
def next_song():
    # Reset slider and status bar
    status_bar.config(text='')
    music_slider.config(value = 0)

    # Get current song tuple number and go up 1
    next_one = playlist.curselection()
    next_one = next_one[0]+1

    # Get song from playlist
    song = playlist.get(next_one)
    song = f'D:/Coding-Projects/Python/AudioPlayerApp/Music/{song}.mp3'
    
    pygame.mixer.music.load(song)
    pygame.mixer.music.play(loops = 0)
    
    playlist.selection_clear(0, END)
    playlist.activate(next_one)
    playlist.selection_set(next_one, last = None)

# Remove a song
def remove_song():
    stop()
    playlist.delete(ANCHOR)
    pygame.mixer.music.stop()

# Remove all songs
def remove_all():
    stop()
    playlist.delete(0, END)
    pygame.mixer.music.stop()

# Sets up the GUI to be displayed
root = Tk()
root.configure(bg = '#283140')
root.iconbitmap('Image/PortfolioLogo.ico')
root.title('Music Player')

# Master Frame
master_frame = Frame(root, bg = '#222222')
master_frame.pack(pady=20)

playlist = Listbox(master_frame, bg = '#1d4d80', fg = '#cccccc', width = 150, height = 30)
playlist.grid(row = 0, column = 0)

# Grabs the media control button images
back_btn_img = PhotoImage(file = 'Image/BackBtn.png')
forward_btn_img = PhotoImage(file = 'Image/ForwardBtn.png')
play_btn_img = PhotoImage(file = 'Image/PlayBtn.png')
pause_btn_img = PhotoImage(file = 'Image/PauseBtn.png')
stop_btn_img = PhotoImage(file = 'Image/StopBtn.png')

# Create a media control frame
media_frame = Frame(master_frame)
media_frame.grid(row = 1, column = 0)

# Create Volume Label Frame
duration_frame = LabelFrame(master_frame, text="Duration", font = ('Oswald', 15, 'bold'), background = '#2d57a1', foreground = '#cccccc')
duration_frame.grid(row=2, column=0, pady=20)

# Create Volume Label Frame
volume_frame = LabelFrame(master_frame, text="Volume", font = ('Oswald', 15, 'bold'), background = '#2d57a1', foreground = '#cccccc')
volume_frame.grid(row=3, column=0, pady=20)

# Control buttons
back_btn = Button(media_frame, image = back_btn_img, borderwidth = 0, command = prev_song)
forward_btn = Button(media_frame, image = forward_btn_img, borderwidth = 0, command = next_song)
play_btn = Button(media_frame, image = play_btn_img, borderwidth = 0, command = play)
pause_btn = Button(media_frame, image = pause_btn_img, borderwidth = 0, command = lambda: pause(paused))
stop_btn = Button(media_frame, image = stop_btn_img, borderwidth = 0, command = stop)

back_btn.grid(row = 0, column = 0, padx = 10)
forward_btn.grid(row = 0, column = 1, padx = 10)
play_btn.grid(row = 0, column = 2, padx = 10)
pause_btn.grid(row = 0, column = 3, padx = 10)
stop_btn.grid(row = 0, column = 4, padx = 10)

# Menu
my_menu = Menu(root)
root.config(menu = my_menu)

# Add one song to playlist
add_song_menu = Menu(my_menu)
my_menu.add_cascade(label = 'Add Songs', menu = add_song_menu)
add_song_menu.add_command(label = 'Add A Song to Playlist', command = add_song)
add_song_menu.add_command(label = 'Add Many Songs to Playlist', command = add_many_songs)

# Delete song
delete_song_menu = Menu(my_menu)
my_menu.add_cascade(label = 'Remove Songs', menu = delete_song_menu)
delete_song_menu.add_command(label = 'Remove A Song to Playlist', command = remove_song)
delete_song_menu.add_command(label = 'Remove All Songs to Playlist', command = remove_all)

# Status bar
status_bar = Label(root, text='', bd=1, relief=GROOVE, anchor=E)
status_bar.pack(fill=X, side=BOTTOM, ipady=2)

# Music position slider
music_slider = ttk.Scale(duration_frame, from_ = 0, to = 100, orient = HORIZONTAL, value = 0, command = slide, length = 360)
music_slider.grid(row = 2, column = 0, pady = 10)

# Volume control slider
volume_slider = ttk.Scale(volume_frame, from_ = 0, to = 1, orient = HORIZONTAL, value = 1, command = volume, length = 360)
volume_slider.grid(row = 3, column = 0, pady = 10)

# Runs the GUI
root.mainloop()
