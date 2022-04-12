import java.util.*
import java.lang.System.exit

val scanner = Scanner(System.`in`)

fun main(args: Array<String>) {
    runMenu()
}

fun mainMenu() : Int {
    return ScannerInput.readNextInt(""" 
         > ----------------------------------
         > |        Hydration App           |
         > ----------------------------------
         > | Menu                           |
         > |   1) Add a drink               |
         > |   2) List all entries          |
         > |   3) List entries per...       |
         > |   4) Update entry              |
         > |   5) Delete an entry           |
         > |               
         > |   10) Save drinks collection   |
         > |   11) Load drinks collection   |
         > ----------------------------------
         > |   0) Exit                      |
         > ----------------------------------
         > ==>> """.trimMargin(">"))
}

fun runMenu() {
    do {
        val option = mainMenu()
        when (option) {
            1  -> addDrink()
            2  -> listAllDrinks()
            3  -> listDrinksPer()
            4  -> updateDrink()
            5 -> deleteDrink()
            10 -> save()
            11 -> load()
            0  -> exitApp()
            else -> System.out.println("Invalid option entered: ${option}")
        }
    } while (true)
}

// ------------ Menu Functions ------------

fun addDrink() {
    println("addDrink chosen")
}

fun listAllDrinks() {
    println("listAllDrinks chosen")
}

fun listDrinksPer() {
    println("listDrinksPer chosen")
}

fun updateDrink() {
    println("updateDrink chosen")
}

fun deleteDrink() {
    println("deleteDrink chosen")
}

fun save() {
    println("save chosen")
}

fun load() {
    println("load chosen")
}

fun exitApp() {
    println("See you soon!")
    exit(0)
}
