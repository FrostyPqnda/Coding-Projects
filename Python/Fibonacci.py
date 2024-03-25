
# Calculate the nth Fibonacci number using dynamic programming
# Time complexity: O(n)
def Memoized_Fibonaccci(n: int) -> int:
    F = [None for i in range(n + 2)]
    if n <= 1: return n
    if F[n] is None:
        F[n] = Memoized_Fibonaccci(n - 1) + Memoized_Fibonaccci(n - 2)
    return F[n]

num = int(input("Enrter an integer value: "))
fib = Memoized_Fibonaccci(num)
print(f'The fibonacci of {num} is {fib}')
