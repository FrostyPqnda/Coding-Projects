import sys

# Convert decimal to binary
def bin(value: int):
    if value < 0: sys.exit('Value must be at least 0')
    
    s = ''
    while value > 0:
        s += str(value % 2)
        value = int(value / 2)

    return format(('0' * 4) + s[::-1])

# Return the sign bit of binary number [Leftmost value]
# 0 -> Positive
# 1 -> Negative
def sign(b: str) -> str:
    return b[0]

# Format to make length of binary string divisible by 4 
# and add spacing every 4 digits
def format(a: str) -> str:
    i = 1
    while len(a) % 4 != 0:
            a = a.rjust(i, '0')
            i += 1
    a = ' '.join(a[i: i + 4] for i in range(0, len(a), 4))
    return a

# Inverts the binary number
# 0 -> 1
# 1 -> 0
def invert(bin: str):
    inv = ''
    for i in range(len(bin)):
        if bin[i] == '1':
            inv += '0'
        elif bin[i] == '0':
            inv += '1'
    return inv

# Returns two's complement of a binary number
def complement(bin: str):
    if bin in ['0000']: return bin # Two's complement of 0 is 0

    inv = invert(bin)
    a = '1'.zfill(len(inv))

    res = ''
    carry = 0

    for i in range(len(inv) - 1, -1, -1):
        r = carry
        r += 1 if a[i] == '1' else 0
        r += 1 if inv[i] == '1' else 0
        res = ('1' if r % 2 == 1 else '0') + res
        carry = 0 if r < 2 else 1
    
    if carry != 0: res = '1' + res
    return format(res)

if __name__ == '__main__':
    if len(sys.argv) != 2:
        file = __file__
        file = file[file.rfind('\\') + 1:]
        sys.exit(f'Usage: python {file} <decimal value>')

    try: value = bin(int(sys.argv[1]))
    except ValueError: sys.exit('Must be of type <int>')

    print(f'Binary value of {sys.argv[1]}: {value}')
    print(f'Two\'s Complement of {sys.argv[1]}: {complement(value)}')