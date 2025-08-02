package main

import (
	"fmt"
	"math/bits"
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

	bit_len := bits.Len(uint(n))
	ans, curr := 0, 0

	for i := bit_len - 1; i >= 0; i-- {
		bit := (n >> i) & 1
		if bit == 1 {
			ans = max(ans, curr)
			curr = 1
		} else {
			curr += 1
		}
	}

	fmt.Printf("Largest gap between 1's for n = %d is %d\n", n, ans)
}
