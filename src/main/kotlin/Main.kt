import controllers.DrinkAPI
import models.Drink
import persistence.XMLSerializer
import utils.ScannerInput
import utils.ScannerInput.readNextInt
import utils.ScannerInput.readNextLine
import java.io.File
import java.lang.System.exit

private val drinkAPI = DrinkAPI(XMLSerializer(File("drinks.xml")))

fun main() {
    runMenu()
}

fun mainMenu(): Int {
    return ScannerInput.readNextInt(
        """ 
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
         > ==>> """.trimMargin(">")
    )
}

fun runMenu() {
    do {
        val option = mainMenu()
        when (option) {
            1 -> addDrink()
            2 -> listAllDrinks()
            3 -> listDrinksPer()
            4 -> updateDrink()
            5 -> deleteDrink()
            10 -> save()
            11 -> load()
            0 -> exitApp()
            else -> System.out.println("Invalid option entered: $option")
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
    if (drinkAPI.numberOfEntries() > 0) {
        val option = readNextInt(
            """
                  > --------------------------------
                  > |   1) List entries per date   |
                  > |   2) List entries per liquid |
                  > --------------------------------
         > ==>> """.trimMargin(">")
        )

        when (option) {
            1 -> listPerDate()
            2 -> listPerLiquid()
            else -> println("Please enter a valid number")
        }
    } else {
        println("There are no entries")
    }
}

fun listPerDate() {
    val date = readNextLine("Enter date to search by: ")
    println(drinkAPI.listPerDate(date))
}

fun listPerLiquid() {
    val liquid = readNextLine("Enter liquid to search by: ")
    println(drinkAPI.listPerLiquid(liquid))
}

fun updateDrink() {
    listAllDrinks()
    if (drinkAPI.numberOfEntries() > 0) {
        val indexToUpdate = readNextInt("Enter index of entry to modify: ")
        if (drinkAPI.isValidIndex(indexToUpdate)) {
            val sizeGlassMl = readNextInt("Enter amount drank: ")
            val liquidType = readNextLine("Enter type of liquid: ")
            val timeTaken = readNextLine("Enter time glass drank: ")
            val date = readNextLine("Enter date glass drank: ")

            if (drinkAPI.updateDrink(indexToUpdate, Drink(sizeGlassMl, liquidType, timeTaken, date))) {
                println("Entry updated")
            } else {
                println("Entry update failed")
            }
        } else {
            println("No entry exists for this index number")
        }
    }
}

fun deleteDrink() {
    listAllDrinks()
    if (drinkAPI.numberOfEntries() > 0) {
        val indexToDelete = readNextInt("Enter index of entry to delete: ")
        val entryToDelete = drinkAPI.deleteDrink(indexToDelete)
        if (entryToDelete != null) {
            println("Entry deleted")
        } else {
            println("Entry --not-- deleted")
        }
    }
}

fun save() {
    try {
        drinkAPI.store()
    } catch (e: Exception) {
        System.err.println("Error writing to file: $e")
    }
}

fun load() {
    try {
        drinkAPI.load()
    } catch (e: Exception) {
        System.err.println("Error reading from file: $e")
    }
}

fun exitApp() {
    println("See you soon!")
    exit(0)
}
