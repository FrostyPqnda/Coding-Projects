package myimplementations

import (
	"fmt"
	"strings"
)

type Table struct {
	status     string
	tableNum   int
	maxSeats   int
	numGuests  int
	order      Order
	isOccupied bool
	hasOrdered bool
}

func InitTable(tableNum, maxSeats int) *Table {
	return &Table{
		order:      *InitOrder(),
		tableNum:   tableNum,
		maxSeats:   maxSeats,
		status:     "",
		isOccupied: false,
		hasOrdered: false,
	}
}

func (t *Table) ProcesssOrder(input string) {
	if strings.EqualFold(input, "S") {
		if strings.EqualFold(t.status, "O") {
			fmt.Printf("Food served in Table %d\n", t.tableNum)
		} else {
			fmt.Printf("Order not yet placed at Table %d yet!\n", t.tableNum)
		}
	}

	if strings.EqualFold(input, "C") {
		if strings.EqualFold(t.status, "S") {
			fmt.Printf("Table %d is closed. Here is the bill.\n", t.tableNum)
			fmt.Printf("\nReceipt Table# %d Party %d", t.tableNum, t.numGuests)
			t.order.PrintReceipt()
			t.ResetTable()
		} else {
			fmt.Printf("Food not served for Table %d yet!\n", t.tableNum)
		}
	}
}

func (t *Table) ResetTable() {
	t.SetStatus("")
	t.SetOrderStatus(false)
}

func (t *Table) GetStatus() string {
	return t.status
}

func (t *Table) SetStatus(status string) {
	t.status = status
}

func (t *Table) GetGuestCount() int {
	return t.numGuests
}

func (t *Table) SetGuestCount(numGuests int) {
	t.numGuests = numGuests
}

func (t *Table) GetOrderStatus() bool {
	return t.hasOrdered
}

func (t *Table) SetOrderStatus(hasOrdered bool) {
	t.hasOrdered = hasOrdered
}

func (t *Table) CheckOccupied() bool {
	if t.numGuests >= 1 {
		fmt.Printf("Table %d already occupied!\n", t.tableNum)
	}
	return t.numGuests >= 1
}

func (t *Table) SetOccupied(isOccupied bool) {
	t.isOccupied = isOccupied
}

func (t *Table) GetTableNum() int {
	return t.tableNum
}

func (t *Table) GetMaxSeats() int {
	return t.maxSeats
}

func (t *Table) GetOrder() *Order {
	return &t.order
}
