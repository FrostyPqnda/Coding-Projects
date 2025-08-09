package main

/*
Defines the node of the LinkedList structure
*/
type Node[T comparable] struct {
	data T
	prev *Node[T]
	next *Node[T]
}

/*
	Instantiates a Node object with a value and pointers
	to the previous Node object and the next Node object
*/
func NewNode[T comparable](prev *Node[T], elem T, next *Node[T]) *Node[T] {
	return &Node[T]{
		data: elem,
		prev: prev,
		next: next,
	}
}
