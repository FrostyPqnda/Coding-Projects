package main

import (
	"fmt"
	"os"
	"regexp"
)

func checkCard(card string) bool {
	var nSum int = 0
	var isSecond bool = false

	for i := len(card) - 1; i >= 0; i-- {
		var d int = int(card[i] - '0')

		if isSecond {
			d *= 2
		}

		// Add the two digits to handle
		// cases where d > 9 on doubling
		nSum += (d / 10)
		nSum += (d % 10)

		isSecond = !isSecond
	}

	return nSum%10 == 0
}

func main() {
	var args []string = os.Args[1:]

	if len(args) != 1 {
		fmt.Fprintf(os.Stderr, "Expected: go run ValidateCard.go <credit card>\n")
		os.Exit(1)
	}

	var re = regexp.MustCompile(`^[0-9]+$`)
	if !re.MatchString(args[0]) {
		fmt.Fprintf(os.Stderr, "Expected: all digits for the credit\n")
		os.Exit(1)
	}

	fmt.Println(checkCard(args[0]))
}
