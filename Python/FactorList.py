class FactorList:

    def __init__(self, number):
        self.factorList = []
        self.number = number

        if self.number > 0:
            for i in range(1, self.number + 1):
                if self.number % i == 0:
                    self.factorList.append(i)
        else:
            for i in range(self.number, abs(self.number) + 1):
                if i == 0:
                    continue
                else:
                    if(number % i == 0):
                        self.factorList.append(i)
    
    def __str__(self):
        return str(self.factorList)

num = int(input('Enter a number: '))

factors = FactorList(num)

print('\nPossible factors of', num, ':', factors)
