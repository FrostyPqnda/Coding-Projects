class FactorList:

    def __init__(self, number):
        self.factorList = []
        self.number = number

        if number > 0:
            for i in range(1, number + 1):
                if number % i == 0:
                    self.factorList.append(i)
        else:
            for i in range(number, abs(number) + 1):
                if i == 0:
                    continue
                else:
                    if(number % i == 0):
                        self.factorList.append(i)
    
    def checkType(self):
        if len(self.factorList) == 0 or len(self.factorList) == 1:
            return 'Neither prime or composite'
            
        if len(self.factorList) > 2:
            return 'Composite Number'
        else:
            return 'Prime Number'

    def __str__(self):
        return str(self.factorList) + '\n' + self.checkType()

num = int(input('Enter a number: '))

factors = FactorList(num)

print('Possible factors of', num, ':', factors)