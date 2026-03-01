package main

import (
	"fmt"
	"strconv"
	"strings"
)

func main() {
	var input string
	fmt.Scanln(&input)
	values := strings.Split(input, "")

	r1, _ := strconv.Atoi(values[0])
	r2, _ := strconv.Atoi(values[1])
	i1, _ := strconv.Atoi(values[2])
	r3, _ := strconv.Atoi(values[3])
	i2, _ := strconv.Atoi(values[4])
	i3, _ := strconv.Atoi(values[5])
	i4, _ := strconv.Atoi(values[6])

	t := [][]interface{}{
		{"r1", r1},
		{"r2", r2},
		{"i1", i1},
		{"r3", r3},
		{"i2", i2},
		{"i3", i3},
		{"i4", i4},
	}
	
	s1 := r1 ^ i1 ^ i2 ^ i4
	s2 := r2 ^ i1 ^ i3 ^ i4
	s3 := r3 ^ i2 ^ i3 ^ i4

	s := fmt.Sprintf("%d%d%d", s3, s2, s1)
	S, _ := strconv.ParseInt(s, 2, 64)

	bit := t[S-1][0].(string)
	value := t[S-1][1].(int)

	fmt.Printf("Ошибка в %d бите %s = %d. Заменяем на %s = %d и получаем корректное сообщение.\n", S, bit, value, bit, abs(value-1))

	t[S-1][1] = abs(value - 1)

	for i := 0; i < 7; i++ {
		fmt.Print(t[i][1])
	}
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
