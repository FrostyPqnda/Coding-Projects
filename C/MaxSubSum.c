#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#define MAXLEN 10000
#define max(a, b) (((a) >= (b)) ? (a) : (b))

int nums[MAXLEN];

int solve(int nums[MAXLEN], int left, int right) {
    if(left == right)
        return (nums[left] > 0) ? nums[left] : 0;

    int center = (left + right) / 2;
    int leftMax = solve(nums, left, center);
    int rightMax = solve(nums, center + 1, right);

    int maxLeftBorder = 0, leftBorder = 0;
    for(int i = center; i >= left; i--) {
        leftBorder += nums[i];
        maxLeftBorder = max(maxLeftBorder, leftBorder);
    }

    int maxRightBorder = 0, rightBorder = 0;
    for(int i = center + 1; i <= right; i++) {
        rightBorder += nums[i];
        maxRightBorder = max(maxRightBorder, rightBorder);
    }

    return max(max(leftMax, rightMax), maxLeftBorder + maxRightBorder);
}

int main() {
    int a[MAXLEN] = {4, -3, 5, -2, -1, 2, 6, -2};
    int len = sizeof(a) / sizeof(a[0]);
    int result = solve(a, 0, len - 1);
    printf("Solution = %d", result);
    return 0;
}