package main

import (
	ll "github.com/Brian-Pham02/Go-Linked-List"
)

/*
Implementation of the Deque data structure
utilizing doubly linked list
*/
type Deque[T comparable] struct {
	list *ll.LinkedList[T] // pointer
}

// Instantiates a deque using a doubly linked list
func NewDeque[T comparable]() *Deque[T] {
	return &Deque[T]{
		list: ll.NewList[T](),
	}
}

// Insert at the front of the deque
func (d *Deque[T]) InsertFront(data T) {
	d.list.AddHead(data)
}

// Insert at the rear of the deque
func (d *Deque[T]) InsertRear(data T) {
	d.list.AddTail(data)
}

// Delete the front of the deque
func (d *Deque[T]) DeleteFront() {
	d.list.RemoveHead()
}

// Delete the front of the deque
func (d *Deque[T]) DeleteRear() {
	d.list.RemoveTail()
}

// Get the front of the deque
func (d *Deque[T]) GetFront() T {
	return d.list.GetHead()
}

// Get the tail of the deque
func (d *Deque[T]) GetTail() T {
	return d.list.GetTail()
}

// Check if the deque is empty
func (d *Deque[T]) IsEmpty() bool {
	return d.list.IsEmpty()
}

// Get the size of the deque
func (d *Deque[T]) Size() int {
	return d.list.Size()
}

// Get the string value of the deque
func (d *Deque[T]) String() string {
	return d.list.String()
}
