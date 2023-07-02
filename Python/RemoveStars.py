"""
Solution to the 'Solution to the "Removing Stars From a String" problem from LeetCode.
Link: https://leetcode.com/problems/removing-stars-from-a-string/
"""
# Removes all stars and any nearby character 
# to its left in a string.
#
# Example: 'leet**cod*e' -> 'lecoe'
def removeStars(s: str) -> str:
    stack = []
    for item in s:
        stack.append(item) # Add character to the stack
        if item == '*':
            stack.pop() # '*' found, remove it from the stack
            stack.pop() # Remove the most recent item before the '*'
    return ''.join(stack) # Return as string

if __name__ == '__main__':
    print(removeStars('leet**cod*e'))