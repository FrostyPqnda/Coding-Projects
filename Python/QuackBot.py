import praw # Python Reddit API Wrapper
from random import randint # randint function from random module
from time import sleep # sleep function from time module

reddit = praw.Reddit(
    client_id = 'e1LRroqJO6Ljiw', 
    client_secret = 'Ijoju8oaO35pl67lnhzvbpoViNyBMg', 
    username = 'Quack-Bot', 
    password = 'REDACTED', 
    user_agent = 'QuackBot by u/Frosty-Pqnda'
) # Instantiate Reddit bot

keyphrases = ['!QuackBot', 'Duck', 'Quack'] # Certain words that will activate QuackBot

subreddit = reddit.subreddit('dankmemes') # The subreddit the QuackBot will live in

bot_reply_list = [
    'QUACK!', 
    'I snorted 10000 pounds of quack.', 
    'I know your secret. You better pay me back your debt if you don\'t want anyone to find out.', 
    'Ducks are the superior creature. Otters can suck it.', 
    'I exist to remind you how insignificant your life is in the grand scheme of things.',
    'I am the eternal duck of the infinite multiverse. Bow down to your duck god, filthy redditors.', 
    'I am humbled to be in your presence and assist you in your endeavors, sir. Would you like me to reiterate why your wife left you for Steve?',
    'Can confirm as a representative for all ducks that we will kidnap your child and sell them on the black market to fuel our hentai addictions. But if you don\'t have any offsprings, we\'ll just have to kidnap you and sell your kidneys as an alternative.\n177013 is my favorite!',
    'You are looking very fine today. How can we aspire to be like you?', 
    'For only a dime, I\'ll kill your parents right in front of your eyes if you want to become Batman.',
    'For the last time, Rachel, I will not let you use my beak as a makeshift sex toy.',
    'I am a duck.'
] # List of replies QuackBot will give when summoned

for post in subreddit.hot(limit=10000):
    for comment in post.comments:
        if hasattr(comment,'body'):
            if keyphrases[0].lower() in comment.body or keyphrases[1].lower() in comment.body or keyphrases[2].lower() in comment.body:
                random_index = randint(0, len(bot_reply_list) - 1) # Get a random value from the bot reply list
                comment.reply(bot_reply_list[random_index]) # Replies to a redditor with a randomized reply from the list
                sleep(720) # Replies to another post after 12 minutes


