import controllers.DrinkAPI
import models.Drink
import mu.KotlinLogging
import utils.ScannerInput
import java.lang.System.exit

private val logger = KotlinLogging.logger {}
private val drinkAPI = DrinkAPI()

fun main() {
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
        val sizeGlassMl = ScannerInput.readNextInt("How much did you drink (in mL) ? ")
        val liquidType = ScannerInput.readNextLine("What did you drink ? ")
        val timeTaken = ScannerInput.readNextLine("At what time ? ")
        val date = ScannerInput.readNextLine("On what date ? (DD-MM-YYYY) ")
        val isAdded = drinkAPI.add(Drink(sizeGlassMl, liquidType, timeTaken, date))

        if (isAdded) {
            println("entry added")
        } else {
            println("entry failed")
        }
    }


fun listAllDrinks() {
    println(drinkAPI.listAllDrinks())
}

fun listDrinksPer() {
    println("listDrinksPer chosen")
    logger.info { "listDrinksPer() function invoked" }
}

fun updateDrink() {
    println("updateDrink chosen")
    logger.info { "updateDrink() function invoked" }
}

fun deleteDrink() {
    println("deleteDrink chosen")
    logger.info { "deleteDrink() function invoked" }
}

fun save() {
    println("save chosen")
    logger.info { "save() function invoked" }
}

fun load() {
    println("load chosen")
    logger.info { "load() function invoked" }
}

fun exitApp() {
    println("See you soon!")
    exit(0)
}
