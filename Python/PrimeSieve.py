"""
Returns a list of prime number up to n using
the Sieve of Eratosthenes algorithm.

Time Complexity: O(nlog(log(n)))
Learn more: [https://www.geeksforgeeks.org/sieve-of-eratosthenes/]
"""
def getPrime(n: int):
    if n < 2:
        return []

    marked = [True for _ in range(n + 1)]
    len = (int)(n ** 0.5)

    for i in range(2, len + 1):
        if marked[i]:
            for j in range(i * i, n + 1, i):
                marked[j] = False
    
    # Return a list of prime using list comprehension
    return [k for k in range(2, n + 1) if marked[k]]
    
if __name__ == '__main__':
    n = int(input('Enter a number: '))
    print(getPrime(n))
