package main

import (
	"fmt"
	"os"
	"strconv"
)

var board [][]byte        // NxN grid
var solutionCount int = 0 // No. of unique solutions

func safe(row, col int) bool {
	var safe bool = true

	for i := 0; i < len(board); i++ {
		if board[row][i] == 'Q' || board[i][col] == 'Q' {
			safe = false
			break
		}
	}

	for i, j := row, col; i >= 0 && j >= 0; i, j = i-1, j-1 {
		if board[i][j] == 'Q' {
			safe = false
			break
		}
	}

	for i, j := row, col; i >= 0 && j < len(board); i, j = i-1, j+1 {
		if board[i][j] == 'Q' {
			safe = false
			break
		}
	}

	return safe
}

func place(row int) {
	if row == len(board) {
		solutionCount++
		printBoard()
		fmt.Println()
		return
	}

	for i := 0; i < len(board); i++ {
		if safe(row, i) {
			board[row][i] = 'Q'
			place(row + 1)
			board[row][i] = '-'
		}
	}
}

func printBoard() {
	for _, row := range board {
		for _, col := range row {
			fmt.Printf("%c ", col)
		}
		fmt.Println()
	}
}

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

	board = make([][]byte, n)
	for i := range board {
		board[i] = make([]byte, n)
	}

	for i := 0; i < n; i++ {
		for j := 0; j < n; j++ {
			board[i][j] = '-'
		}
	}

	place(0)
	fmt.Printf("Possible N-Queens solution for an %dx%d board: %d\n", n, n, solutionCount)

}
