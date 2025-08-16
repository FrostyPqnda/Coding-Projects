package myimplementations

import "fmt"

type MenuItem struct {
	itemCode string
	name     string
	price    float64
}

func InitItem(itemCode string, name string, price float64) *MenuItem {
	return &MenuItem{
		itemCode: itemCode,
		name:     name,
		price:    price,
	}
}

func (mi *MenuItem) GetCode() string {
	return mi.itemCode
}

func (mi *MenuItem) GetName() string {
	return mi.name
}

func (mi *MenuItem) GetPrice() float64 {
	return mi.price
}

func (mi *MenuItem) PrintItem() {
	fmt.Printf("%s %18s %7f\n", mi.itemCode, mi.name, mi.price)
}
