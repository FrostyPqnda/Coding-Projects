package main

import (
	"fmt"
	"os"
	"strconv"
)

func main() {
	args := os.Args[1:]
	if len(args) != 1 {
		fmt.Fprintf(os.Stderr, "Expected: go run Pascal.go <no. of rows>\n")
		os.Exit(1)
	}

	n, err := strconv.Atoi(args[0])
	if err != nil || n <= 0 {
		fmt.Fprintf(os.Stderr, "Expected an integer value\n")
		os.Exit(1)
	}

	var triangle = generate(n)
	for _, row := range triangle {
		for _, val := range row {
			fmt.Printf("%d ", val)
		}
		fmt.Println()
	}
}

func generate(numRows int) [][]int {
	var pascal = [][]int{}

	for i := 0; i < numRows; i++ {
		var row []int = []int{}
		for j := 0; j <= i; j++ {
			row = append(row, getCoefficient(i, j))
		}
		pascal = append(pascal, row)
	}

	return pascal
}

func getCoefficient(n, k int) int {
	var res int = 1
	if k > n-k {
		k = n - k
	}

	for i := 0; i < k; i++ {
		res *= (n - i)
		res /= (i + 1)
	}

	return res
}
