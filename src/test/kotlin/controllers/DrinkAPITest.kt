package controllers

import models.Drink
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class DrinkAPITest {

    private var entry1: Drink? = null
    private var entry2: Drink? = null
    private var entry3: Drink? = null
    private var entry4: Drink? = null
    private var entry5: Drink? = null
    private var populatedEntries: DrinkAPI? = DrinkAPI()
    private var emptyEntries: DrinkAPI? = DrinkAPI()

    @BeforeEach
    fun setup() {
        entry1 = Drink(240, "water", "14:37", "12/04/2022")
        entry2 = Drink(350, "pepsi", "16:17", "12/04/2022")
        entry3 = Drink(240, "milk", "09:41", "13/04/2022")
        entry4 = Drink(240, "water", "10:54", "13/04/2022")
        entry5 = Drink(220, "tea", "12:28", "13/04/2022")

        //adding 5 drink entries to DrinkApi
        populatedEntries!!.add(entry1!!)
        populatedEntries!!.add(entry2!!)
        populatedEntries!!.add(entry3!!)
        populatedEntries!!.add(entry4!!)
        populatedEntries!!.add(entry5!!)
    }

        @AfterEach
        fun tearDown() {
            entry1 = null
            entry2 = null
            entry3 = null
            entry4 = null
            entry5 = null
            populatedEntries = null
            emptyEntries = null
        }

    @Nested
        inner class AddDrink {
        @Test
        fun `adding an entry to a populated list adds to the ArrayList`() {
            val newDrink = Drink(240, "water", "14:37", "12/04/2022")
            assertEquals(5, populatedEntries!!.numberOfEntries())
            assertTrue(populatedEntries!!.add(newDrink))
            assertEquals(6, populatedEntries!!.numberOfEntries())
            assertEquals(newDrink, populatedEntries!!.findEntry(populatedEntries!!.numberOfEntries() - 1))

        }

        @Test
        fun `adding an entry to an empty list adds to the ArrayList`() {
            val newDrink = Drink(240, "water", "14:37", "12/04/2022")
            assertEquals(0, emptyEntries!!.numberOfEntries())
            assertTrue(emptyEntries!!.add(newDrink))
            assertEquals(1, emptyEntries!!.numberOfEntries())
            assertEquals(newDrink, emptyEntries!!.findEntry(emptyEntries!!.numberOfEntries() - 1))

        }
    }
}