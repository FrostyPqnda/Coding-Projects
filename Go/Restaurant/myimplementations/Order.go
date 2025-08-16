package myimplementations

import (
	"fmt"
	"math"
)

type Order struct {
	index   int
	ordered []MenuItem
}

func InitOrder() *Order {
	return &Order{
		ordered: make([]MenuItem, 100),
	}
}

func (o *Order) AddToOrder(item MenuItem) {
	o.ordered[o.index] = item
	o.index++
}

func (o *Order) GetOrdered() []MenuItem {
	return o.ordered
}

func (o *Order) GetTotal() float64 {
	sum := 0.0

	for _, item := range o.ordered {
		sum += item.GetPrice()
	}

	return math.Round(sum*100) / 100
}

func (o *Order) PrintReceipt() {
	for _, item := range o.ordered {
		item.PrintItem()
	}
	fmt.Printf("\t\tTotal %f\n", o.GetTotal())
}
