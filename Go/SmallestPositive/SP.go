package main

import "fmt"

func main() {
	nums := []int{3, 4, -1, 1}
	fmt.Println(nextSmallest(nums))
}

func nextSmallest(nums []int) int {
	n := len(nums)
	visited := make([]bool, n+1)

	for i := 0; i < n; i++ {
		if nums[i] > 0 && nums[i] <= n {
			visited[nums[i]-1] = true
		}
	}

	for i := 1; i <= n; i++ {
		if !visited[i-1] {
			return i
		}
	}

	return n + 1
}
