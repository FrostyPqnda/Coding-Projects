def factorial(n):
    if n == 0:
        return 1
    return n * factorial(n - 1)

def findEuler(n):
    e = 0

    for i in range(n):
        fac = factorial(i)
        e += (1/fac)
    
    return e 

print(f'Estimated value of e = {findEuler(500)}')