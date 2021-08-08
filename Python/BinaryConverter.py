def strToBinary(text):
    binArr = []

    for letter in text:
        ascVal = ord(letter)
        binVal = bin(ascVal)
        binArr.append(binVal[2:])

    return ' '.join(binArr)

def decToBinary(num):  
    return bin(num)[2:]

print(strToBinary('brian'))