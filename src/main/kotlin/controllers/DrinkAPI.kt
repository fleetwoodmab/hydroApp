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
}