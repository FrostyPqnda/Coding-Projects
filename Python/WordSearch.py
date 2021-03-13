"""
Searches for a specific word in a sentence.
If the word does exist in the sentence, it 
returns the index position it is first
located in the sentence.
"""
def search(phrase, word):
    if phrase[-1] == '.' or phrase[-1] == '?' or phrase[-1] == '!':
        phrase = phrase[:-1]
    textArr = phrase.split(' ')
    for i in range(0, len(textArr)):
        if(textArr[i] == word):
            return 'The word [' + word + '] exists in the sentence and is located at index ' + str(i) 
    return 'The word [' + word + '] does not exist in the sentence'

s = 'I am a cat.'
print(search(s, 'cat'))
