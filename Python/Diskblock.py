import sys
import math

def computeOverheadBlocks(diskblocks: int) -> int:
    max = 12 + 2048 + (2048*2048) + (2048*2048*2048)
    if diskblocks > max: return -1
    numDirect = int(math.ceil((diskblocks - 12) / 2048))
    indirectX = int(math.ceil((numDirect - 1) / 2048))
    indirectY = int(math.ceil((indirectX - 1) / 2048))
    return numDirect + indirectX + indirectY

if __name__ == '__main__':
    if len(sys.argv) != 2:
        print('Usage: python diskblock.py <long>')
        sys.exit()
    
    fSize = int(sys.argv[1])
    diskblocks = int(fSize / 8)
    if fSize % 8:
        diskblocks += 1

    print(f'{diskblocks} {computeOverheadBlocks(diskblocks)}')
