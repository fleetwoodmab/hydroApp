package controllers
import models.Drink

class DrinkAPI() {

    private var drinks = ArrayList<Drink>()

    fun add(drink: Drink): Boolean {
        return drinks.add(drink)
    }

    fun listAllDrinks(): String {
        return if (drinks.isEmpty()) {
            "No entries created yet"
        } else {
            var allEntries = ""
            for (i in drinks.indices) {
                allEntries += "${i}: ${drinks[i]} \n"
            }
            allEntries
        }
    }

// ----- test purposes -----
    fun numberOfEntries(): Int {
        return drinks.size
    }

    fun findEntry(index: Int): Drink? {
        return if (isValidListIndex(index, drinks)) {
            drinks[index]
        } else null
    }

    fun isValidListIndex(index: Int, list: List<Any>): Boolean {
        return (index >= 0 && index < list.size)
    }
}