package myimplementations

import (
	"strings"
)

type Menu struct {
	items    []MenuItem
	numItems int
	index    int
}

func InitMenu(numItems int) *Menu {
	return &Menu{
		items:    make([]MenuItem, numItems),
		numItems: numItems,
		index:    0,
	}
}

func (m *Menu) AddToMenu(item MenuItem) {
	m.items[m.index] = item
	m.index++
}

func (m *Menu) GetMenuList() []MenuItem {
	return m.items
}

func (m *Menu) GetItem(index int) MenuItem {
	return m.items[m.index]
}

func (m *Menu) FindMenuItem(code string) int {
	idx := -1

	for i := 0; i < len(m.items); i++ {
		if strings.EqualFold(m.items[i].GetCode(), code) {
			idx = i
			break
		}
	}

	return idx
}
