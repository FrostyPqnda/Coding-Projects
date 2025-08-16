package main

import (
	"bufio"
	"fmt"
	"log"
	"os"
	r "restaurant/myimplementations"
	"strconv"
	"strings"
	"unicode"
)

type Test struct {
}

var tables []r.Table
var menu r.Menu
var list []r.MenuItem

func main() {
	file, err := os.Open("config.txt")
	if err != nil {
		log.Fatal(err)
	}
	defer file.Close()

	scanner := bufio.NewScanner(file)
	fillTable(scanner)
	scanner.Scan()
	fillMenu(scanner)

	//for isRunning := true; isRunning; isRunning ==
	//fmt.Print("input text: ")
	reader := bufio.NewReader(os.Stdin)
	var line string
	var inputErr error

	for {
		// Read input
		line, inputErr = reader.ReadString('\n')
		if inputErr != nil {
			log.Fatal(inputErr)
		}
		line = strings.TrimSpace(line)

		// Process input
		splitInput := strings.Fields(line)
		if len(splitInput) == 2 {
			if hasInt(splitInput[1]) {
				assignTable(splitInput)
			} else {
				num, err := convert2Int(splitInput[0])
				if err != nil {
					log.Fatal(err)
				}
				index := findTable(num)
				tables[index].ProcesssOrder(splitInput[1])
			}
		} else {
			orderFood(splitInput)
		}

		// Condition check at the end (simulate do-while)
		if strings.EqualFold(line, "C") {
			break
		}
	}

}

func assignTable(input []string) {
	tableNum, tableErr := convert2Int(input[0])
	if tableErr != nil {
		log.Fatal(tableErr)
	}
	index := findTable(tableNum)

	partyCount, partyErr := convert2Int(input[1])
	if partyErr != nil {
		log.Fatal(partyErr)
	}

	if !tables[index].CheckOccupied() && partyCount <= tables[index].GetMaxSeats() {
		fmt.Printf("Party of %d assigned to Table %d\n", partyCount, tableNum)
		tables[index].SetOccupied(true)
		tables[index].SetGuestCount(partyCount)
	} else {
		fmt.Printf("Sorry, max %d seats in Table %d!\n", tables[index].GetMaxSeats(), tableNum)
	}
}

func orderFood(input []string) {
	tableNum, err := convert2Int(input[0])
	if err != nil {
		log.Fatal(err)
	}

	index := findTable(tableNum)
	status := input[1]
	lineCode := getOrderLine(input)
	count := getOrderCount(lineCode)

	if tables[index].GetOrderStatus() {
		fmt.Printf("%d additional items ordered for Table %d\n", count, tableNum)
	} else {
		fmt.Printf("%d items ordered for Table %d\n", count, tableNum)
	}

	tables[index].SetStatus(status)
	tables[index].SetOrderStatus(true)
	addFoodToBill(tables[index], lineCode)
}

func addFoodToBill(t r.Table, itemCode string) {
	split := strings.Split(itemCode, " ")
	for i := 0; i < len(split); i++ {
		index := menu.FindMenuItem(split[i])
		if index != -1 {
			t.GetOrder().AddToOrder(list[index])
		}
	}
}

func getOrderLine(input []string) string {
	line := ""

	for i := 2; i < len(input); i++ {
		line += fmt.Sprintf("%s ", input[i])
	}

	return line
}

func getOrderCount(line string) int {
	arr := strings.Split(line, " ")
	n := 0

	for i := 0; i < len(arr); i++ {
		if menu.FindMenuItem(arr[i]) != -1 {
			n++
		} else {
			fmt.Printf("No item with code %s\n", arr[i])
		}
	}

	return n
}

func findTable(tableNum int) int {
	for i, table := range tables {
		if table.GetTableNum() == tableNum {
			return i
		}
	}
	return -1
}

func fillTable(scan *bufio.Scanner) {
	if !scan.Scan() {
		log.Fatal("expected table size")
	}

	tableSize, err := convert2Int(scan.Text())
	if err != nil {
		log.Fatal(err)
	}

	tables = make([]r.Table, tableSize)

	for i := 0; i < tableSize; i++ {
		if !scan.Scan() {
			log.Fatalf("expected data for table %d", i+1)
		}

		line := scan.Text()
		split := strings.Split(line, " ")

		num, num_err := convert2Int(split[0])
		if num_err != nil {
			log.Fatal(num_err)
		}

		seat, seat_err := convert2Int(split[1])
		if seat_err != nil {
			log.Fatal(seat_err)
		}

		tables[i] = *r.InitTable(num, seat)
	}
}

func fillMenu(scan *bufio.Scanner) {
	if !scan.Scan() {
		log.Fatal("expected menu size line")
	}

	menuSize, err := convert2Int(scan.Text())
	if err != nil {
		log.Fatal(err)
	}

	menu = *r.InitMenu(menuSize)

	for i := 0; i < menuSize; i++ {
		if !scan.Scan() {
			log.Fatalf("expected menu item %d", i+1)
		}
		line := scan.Text()
		split := strings.Fields(line)
		if len(split) < 3 {
			log.Fatalf("invalid menu item line %d: %s", i+1, line)
		}

		code := split[0]
		name := split[1]
		price, err := strconv.ParseFloat(split[2], 64)
		if err != nil {
			log.Fatal(err)
		}

		item := r.InitItem(code, name, price)
		menu.AddToMenu(*item)
	}
}

func hasInt(s string) bool {
	_, err := convert2Int(s)
	return err == nil
}

func convert2Int(s string) (int, error) {
	t := ""

	for i := 0; i < len(s); i++ {
		if unicode.IsDigit(rune(s[i])) {
			t += string(s[i])
		}
	}

	if t == "" {
		return 0, fmt.Errorf("no digits found in input")
	}

	val, err := strconv.Atoi(t)
	if err != nil {
		return 0, err
	}

	return val, nil
}
