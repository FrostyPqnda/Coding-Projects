def search(phrase, word):
    textArr = phrase.split(' ')
    for i in range(0, len(textArr)):
        if(textArr[i] == word):
            return 'The word [' + word + '] exists in the sentence and is located at index ' + str(i) 
    return 'The word [' + word + '] does not exist in the sentence'

s = 'I am a cat'
print(s + '\n')
print(search(s, 'cat'))
