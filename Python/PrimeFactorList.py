class PrimeFactorList:

    def __init__(self, num):
        self.num = num
        self.factorList = []

        # Gets the prime factors of num and append
        # it to the list
        for i in range(2, num + 1):
            while num % i == 0:
                self.factorList.append(i)
                num /= i
    
    def __str__(self):
        return str(self.factorList)

primeFactor = PrimeFactorList(78)

print(primeFactor)