    import controllers.DrinkAPI
    import models.Drink
    import mu.KotlinLogging
    import utils.ScannerInput
    import utils.ScannerInput.readNextInt
    import utils.ScannerInput.readNextLine
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
        listAllDrinks()
        if (drinkAPI.numberOfEntries() > 0) {
            val indexToUpdate = readNextInt("Enter index of entry to modify: ")
            if (drinkAPI.isValidIndex(indexToUpdate)) {
                val sizeGlassMl = readNextInt("Enter amount drank: ")
                val liquidType = readNextLine("Enter type of liquid: ")
                val timeTaken = readNextLine("Enter time glass drank: ")
                val date = readNextLine("Enter date glass drank: ")

                if (drinkAPI.updateDrink(indexToUpdate, Drink(sizeGlassMl, liquidType, timeTaken, date))){
                    println("Entry updated")
                } else {
                    println("Entry update failed")
                }
            } else {
                println("No entry exists for this index number")
            }
        }
    }

    fun deleteDrink(){
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
