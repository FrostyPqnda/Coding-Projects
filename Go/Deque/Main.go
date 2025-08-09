package main

import (
	"fmt"
	"math/rand"
)

func main() {
	deque := NewDeque[int]()
	for i := 0; i < 10; i++ {
		deque.InsertFront(rand.Intn(100))
	}

	fmt.Println(deque)

	deque.DeleteRear()

	fmt.Println(deque)
}
