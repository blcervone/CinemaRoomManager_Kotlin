package cinema

var soldSeats = 0
var currentIncome = 0
var totalIncome = 0


fun printRoom(room: Array<CharArray>, numSeats: Int, numRows: Int) {
    print("\nCinema:\n  ")
    for (i in 1..numSeats) { print("$i ") }
    for (i in 1..numRows) { print("\n$i ${room[i - 1].joinToString(separator = " ")}") }
    println()
    printMenu()
}

fun seatSelector(room: Array<CharArray>, numSeats: Int, numRows: Int) {
    println("\nEnter a row number:")
    val rowNum = readln().toInt()
    println("Enter a seat number in that row:")
    val seatNum = readln().toInt()
    println()

    if (rowNum > numRows || seatNum > numSeats) {
        println("Wrong input!")
        seatSelector(room, numSeats, numRows)
    } else {
        if (room[rowNum - 1][seatNum - 1] == 'B') {
            println("That ticket has already been purchased!")
            seatSelector(room, numSeats, numRows)
        } else {
            room[rowNum - 1][seatNum - 1] = 'B'
            soldSeats++
        }

        if (numRows * numSeats <= 60) {
            println("Ticket price: $10")
            currentIncome += 10
        } else {
            if (numRows / 2 >= rowNum) {
                println("Ticket price: $10")
                currentIncome += 10
            } else {
                println("Ticket price: $8")
                currentIncome += 8
            }
        }
    }
    printMenu()
}

fun printMenu() {
    println()
    println("1. Show the seats")
    println("2. Buy a ticket")
    println("3. Statistics")
    println("0. Exit")
}

fun calcAndPrintStats(numSeats: Int, numRows: Int) {
    val totalSeats = numSeats * numRows
    val percentage: Double = (soldSeats * 1.0 / totalSeats) * 100.0
    val formatPercentage = "%.2f".format(percentage)

    if (totalSeats <= 60) {
        totalIncome = totalSeats * 10
    } else {
        totalIncome = ((numRows / 2) * numSeats) * 10 + (((numRows / 2) + 1) * numSeats) * 8
    }

    println("Number of purchased tickets: $soldSeats")
    println("Percentage: $formatPercentage%")
    println("Current income: $$currentIncome")
    println("Total income: $$totalIncome")
    printMenu()
}

fun main() {
    println("Enter the number of rows:")
    val numRows = readln().toInt()
    println("Enter the number of seats in each row:")
    val numSeats = readln().toInt()
    val room = Array(numRows) { CharArray(numSeats) { 'S' } }
    printMenu()
    while (true) {
        when (readln().toInt()) {
            1 -> printRoom(room, numSeats, numRows)
            2 -> seatSelector(room, numSeats, numRows)
            3 -> calcAndPrintStats(numSeats, numRows)
            0 -> break
        }
    }
}

