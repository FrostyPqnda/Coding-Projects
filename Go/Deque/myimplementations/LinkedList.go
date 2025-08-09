package myimplementations

import (
	"fmt"
	"strings"
)

/*
Implementation of the LinkedList data
structure
*/
type LinkedList[T comparable] struct {
	head     *Node[T]
	tail     *Node[T]
	numItems int
	modCount int
}

// Instantiates an empty LinkedList data structure
func NewList[T comparable]() *LinkedList[T] {
	return &LinkedList[T]{
		head:     nil,
		tail:     nil,
		numItems: 0,
		modCount: 0,
	}
}

// Create a new node at the head of the list
func (ll *LinkedList[T]) linkHead(x T) {
	f := ll.head
	newNode := NewNode(nil, x, f)
	ll.head = newNode
	if f == nil {
		ll.tail = newNode
	} else {
		f.prev = newNode
	}
	ll.numItems++
	ll.modCount++
}

// Create a new node at thee tail of the list
func (ll *LinkedList[T]) linkTail(x T) {
	l := ll.tail
	newNode := NewNode(l, x, nil)
	ll.tail = newNode
	if l == nil {
		ll.head = newNode
	} else {
		l.next = newNode
	}
	ll.numItems++
	ll.modCount++
}

/*func (ll *LinkedList[T]) linkBefore(x T, succ *Node[T]) {
	pred := succ.prev
	newNode := NewNode(pred, x, succ)
	succ.prev = newNode
	if pred == nil {
		ll.head = newNode
	} else {
		pred.next = newNode
	}
	ll.numItems++
	ll.modCount++
}*/

// Disconnect the head node from the linked list and
// make the node after it the new head
func (ll *LinkedList[T]) unlinkHead(f *Node[T]) T {
	var zero T

	elem := f.data
	next := f.next

	f.data = zero
	f.next = nil
	ll.head = next

	if next == nil {
		ll.tail = nil
	} else {
		next.prev = nil
	}

	ll.numItems--
	ll.modCount++
	return elem
}

// Disconnect the tail node from the linked list and
// make the node before it the new tail
func (ll *LinkedList[T]) unlinkTail(l *Node[T]) T {
	var zero T

	elem := l.data
	prev := l.prev

	l.data = zero
	l.prev = nil
	ll.tail = prev

	if prev == nil {
		ll.head = nil
	} else {
		prev.next = nil
	}

	ll.numItems--
	ll.modCount++
	return elem
}

// Disconnect a node from the linked list
func (ll *LinkedList[T]) unlink(x *Node[T]) T {
	var zero T

	elem := x.data
	next := x.next
	prev := x.prev

	if prev == nil {
		ll.head = next
	} else {
		prev.next = next
		x.prev = nil
	}

	x.data = zero
	ll.numItems--
	ll.modCount++
	return elem
}

// Get the head node data
func (ll *LinkedList[T]) GetHead() T {
	f := ll.head
	if f == nil {
		panic("No such element found")
	}

	return f.data
}

// Get the tail node data
func (ll *LinkedList[T]) GetTail() T {
	l := ll.tail
	if l == nil {
		panic("No such element found")
	}

	return l.data
}

// Removes the head node
func (ll *LinkedList[T]) RemoveHead() T {
	f := ll.head
	if f == nil {
		panic("No such element found")
	}
	return ll.unlinkHead(f)
}

// Removes the tail node
func (ll *LinkedList[T]) RemoveTail() T {
	l := ll.tail
	if l == nil {
		panic("No such element found")
	}
	return ll.unlinkTail(l)
}

// Adds a node at the head
func (ll *LinkedList[T]) AddHead(x T) {
	ll.linkHead(x)
}

// Adds a node at the tail
func (ll *LinkedList[T]) AddTail(x T) {
	ll.linkTail(x)
}

// Checks if the linked list contains the specified item
func (ll *LinkedList[T]) Contains(x T) bool {
	if ll.IsEmpty() {
		return false
	}

	tmp := ll.head
	for tmp != nil {
		if tmp.data == x {
			return true
		}
		tmp = tmp.next
	}

	return false
}

// Get the size of the linked list
func (ll *LinkedList[T]) Size() int {
	return ll.numItems
}

// Add a new node with the specified value at the tail end of linked list
func (ll *LinkedList[T]) Add(x T) bool {
	ll.linkTail(x)
	return true
}

// Remove an occurence of an item from the linked list
func (ll *LinkedList[T]) Remove(o T) bool {
	for x := ll.head; x != nil; x = x.next {
		if o == x.data {
			ll.unlink(x)
			return true
		}
	}
	return false
}

// Reset the linked list
func (ll *LinkedList[T]) Clear() {
	var zero T

	for x := ll.head; x != nil; {
		next := x.next
		x.data = zero
		x.next = nil
		x.prev = nil
		x = next
	}

	ll.head = nil
	ll.tail = nil
	ll.numItems = 0
	ll.modCount++
}

// Get the node data at the specicied index
func (ll *LinkedList[T]) Get(index int) T {
	ll.checkElementIndex(index)
	return ll.node(index).data
}

// Set the Node at the specified index to a new value
func (ll *LinkedList[T]) Set(index int, x T) T {
	ll.checkElementIndex(index)
	t := ll.node(index)
	oldVal := t.data
	t.data = x
	return oldVal
}

// Check if the linked list is empty
func (ll *LinkedList[T]) IsEmpty() bool {
	return ll.head == nil || ll.numItems == 0
}

// Check if the index is within range
func (ll *LinkedList[T]) isElementIndex(index int) bool {
	return index >= 0 && index < ll.numItems
}

/*func (ll *LinkedList[T]) isPositionIndex(index int) bool {
	return index >= 0 && index <= ll.numItems
}*/

// Display an out-of-bounds error message
func (ll *LinkedList[T]) outOfBoundsMsg(index int) string {
	return fmt.Sprintf("Index: %d, Size: %d\n", index, ll.numItems)
}

// Throw an out of bound message if the given index is out of boundss
func (ll *LinkedList[T]) checkElementIndex(index int) {
	if !ll.isElementIndex(index) {
		panic(ll.outOfBoundsMsg(index))
	}
}

/*func (ll *LinkedList[T]) checkPositionIndex(index int) {
	if !ll.isPositionIndex(index) {
		panic(ll.outOfBoundsMsg(index))
	}
}*/

// Retrieve the Node object at the specified data
func (ll *LinkedList[T]) node(index int) *Node[T] {
	var x *Node[T]

	if index < (ll.numItems >> 1) {
		x = ll.head
		for i := 0; i < index; i++ {
			x = x.next
		}
	} else {
		x = ll.tail
		for i := ll.numItems - 1; i > index; i-- {
			x = x.prev
		}
	}

	return x
}

// Get the first index of a node data
func (ll *LinkedList[T]) IndexOf(o T) int {
	index := 0
	for x := ll.head; x != nil; x = x.next {
		if o == x.data {
			return index
		}
		index++
	}
	return -1
}

// Get the last index of a node data
func (ll *LinkedList[T]) LastIndex(o T) int {
	index := ll.numItems
	for x := ll.tail; x != nil; x = x.prev {
		index--
		if o == x.data {
			return index
		}
	}
	return -1
}

// Get the node data at the top of the linked list
func (ll *LinkedList[T]) Peek() T {
	f := ll.head
	if f == nil {
		var zero T
		return zero
	}
	return f.data
}

// Get the node data at the top of the linked list and remove it
func (ll *LinkedList[T]) Poll() T {
	f := ll.head
	if f == nil {
		var zero T
		return zero
	}
	return ll.unlinkHead(f)
}

// Add a new node data to the tail end of the linked list
func (ll *LinkedList[T]) Offer(x T) bool {
	return ll.Add(x)
}

// Add a new node data to the head of the linked list
func (ll *LinkedList[T]) OfferHead(x T) bool {
	ll.AddHead(x)
	return true
}

// Add a new node data to the tail end of the linked list
func (ll *LinkedList[T]) OfferTail(x T) bool {
	ll.AddTail(x)
	return true
}

// Get the node data at the top of the linked list
func (ll *LinkedList[T]) PeekHead() T {
	return ll.Peek()
}

// Get the node data at the end of the linked list
func (ll *LinkedList[T]) PeekTail() T {
	f := ll.tail
	if f == nil {
		var zero T
		return zero
	}
	return f.data
}

// Get the node data at the top of the linked list and remove it
func (ll *LinkedList[T]) PollHead() T {
	return ll.Poll()
}

// Get the node data at the end of the linked list and remove it
func (ll *LinkedList[T]) PollTail() T {
	f := ll.tail
	if f == nil {
		var zero T
		return zero
	}
	return ll.unlinkTail(f)
}

// Appends a new node data at the head of the linked list
func (ll *LinkedList[T]) Push(x T) {
	ll.AddHead(x)
}

// Get the node data at the top of the linked list and remove it
func (ll *LinkedList[T]) Pop() T {
	return ll.RemoveHead()
}

// Remove the first occurence of a node data
func (ll *LinkedList[T]) RemoveFirstOccurence(o T) bool {
	return ll.Remove(o)
}

// Remove the last occurence of a node data
func (ll *LinkedList[T]) RemoveLastOccurence(o T) bool {
	for x := ll.tail; x != nil; x = x.prev {
		if o == x.data {
			ll.unlink(x)
			return true
		}
	}
	return false
}

func (ll *LinkedList[T]) String() string {
	var sb strings.Builder
	sb.WriteString("[")

	curr := ll.head
	first := true

	for curr != nil {
		if !first {
			sb.WriteString(", ")
		}
		sb.WriteString(fmt.Sprintf(fmt.Sprintf("%v", curr.data)))
		first = false
		curr = curr.next
	}

	sb.WriteString("]")
	return sb.String()
}
