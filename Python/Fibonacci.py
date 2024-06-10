# Calculate the nth Fibonacci number using dynamic programming
# Time complexity: O(n)
def Fibonacci(n: int) -> int:
    F = [0 for i in range(n + 2)]
    F[0] = 0
    F[1] = 1

    for i in range(2, n + 1): 
        F[i] = F[i - 1] + F[i - 2]

    return F[n]

num = int(input("Enter an integer value: "))
fib = Fibonacci(num)
print(f'The fibonacci of {num} is {fib}')
