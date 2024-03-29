# Gets the list of possible factor of num
def getFactor(num):
    factor = []

    for i in range(1, num + 1):
        if num % i == 0:
            factor.append(i)
    
    return factor

# Checks if a number is perfect.
#
# A number is perfect if the sum 
# of all the possible factors 
# (excluding the last) is equal
# to the original number
def isPerfect(num):
    factorNum = getFactor(num)
    sum = 0

    for i in range(len(factorNum) - 1):
        sum += factorNum[i]

    return sum == factorNum[len(factorNum) - 1]

print(isPerfect(496))