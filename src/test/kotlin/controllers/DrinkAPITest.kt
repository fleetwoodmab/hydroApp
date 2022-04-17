package controllers

import models.Drink
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import persistence.XMLSerializer
import java.io.File
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

class DrinkAPITest {

    private var entry1: Drink? = null
    private var entry2: Drink? = null
    private var entry3: Drink? = null
    private var entry4: Drink? = null
    private var entry5: Drink? = null
    private var populatedEntries: DrinkAPI? = DrinkAPI(XMLSerializer(File("drinks.xml")))
    private var emptyEntries: DrinkAPI? = DrinkAPI(XMLSerializer(File("drinks.xml")))

    @BeforeEach
    fun setup() {
        entry1 = Drink(240, "water", "14:37", "12/04/2022")
        entry2 = Drink(350, "pepsi", "16:17", "12/04/2022")
        entry3 = Drink(240, "milk", "09:41", "13/04/2022")
        entry4 = Drink(240, "water", "10:54", "13/04/2022")
        entry5 = Drink(220, "tea", "12:28", "13/04/2022")

        // adding 5 drink entries to DrinkApi
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

    @Nested
    inner class ListEntries {
        @Test
        fun `listAllDrinks returns 'no entry' when the ArrayList is empty`() {
            assertEquals(0, emptyEntries!!.numberOfEntries())
            assertTrue(emptyEntries!!.listAllDrinks().contains("No entries created yet"))
        }

        @Test
        fun `listAllNotes returns all existing entries when the ArrayList is populated`() {
            assertEquals(5, populatedEntries!!.numberOfEntries())
            val entryString = populatedEntries!!.listAllDrinks()
            assertTrue(entryString.contains("water"))
            assertTrue(entryString.contains("pepsi"))
            assertTrue(entryString.contains("milk"))
            assertTrue(entryString.contains("water"))
            assertTrue(entryString.contains("tea"))
        }

        @Test
        fun `listPerDate returns 'no entry' when the ArrayList is empty`() {
            assertEquals(0, emptyEntries!!.numberOfEntries())
            assertTrue(
                emptyEntries!!.listPerDate("01/00/00").contains("No entries created yet")
            )
        }

        @Test
        fun `listPerDate returns 'no entry' when no entries with that date exist`() {
            assertEquals(5, populatedEntries!!.numberOfEntries())
            val dt = populatedEntries!!.listPerDate("00/00/00")
            assertTrue(dt.contains("No entries with date: 00/00/00"))
        }

        @Test
        fun `listPerDate returns all entries with said date when they exist`() {
            assertEquals(5, populatedEntries!!.numberOfEntries())
            val dt = populatedEntries!!.listPerDate("12/04/2022")
            assertTrue(dt.contains("water"))
            assertTrue(dt.contains("pepsi"))
            assertFalse(dt.contains("milk"))
            assertFalse(dt.contains("10:54"))
            assertFalse(dt.contains("tea"))
        }

        @Test
        fun `isGoalAchievedOnDay returns 'no entry' when the ArrayList is empty`() {
            assertEquals(0, emptyEntries!!.numberOfEntries())
            assertTrue(
                emptyEntries!!.isGoalAchievedOnDay("02/02/2022").contains("No entries created yet")
            )
        }

        @Test
        fun `isGoalAchievedOnDay returns 'no entry' when no entries with that date exist`() {
            assertEquals(5, populatedEntries!!.numberOfEntries())
            val dt = populatedEntries!!.isGoalAchievedOnDay("00/00/00")
            assertTrue(dt.contains("No entries with date: 00/00/00"))
        }

        @Test
        fun `isGoalAchievedOnDay returns where goal achieved with said date when it exists`() {
            val newDrink = Drink(505, "water", "14:37", "13/04/2022")
            assertTrue(populatedEntries!!.add(newDrink))
            val dt = populatedEntries!!.isGoalAchievedOnDay("13/04/2022")
            assertEquals(6, populatedEntries!!.numberOfEntries())
            assertTrue(dt.contains("You've achieved your goal on: 13/04/2022"))
        }

        @Test
        fun `listPerLiquid returns 'no entry' when the ArrayList is empty`() {
            assertEquals(0, emptyEntries!!.numberOfEntries())
            assertTrue(
                emptyEntries!!.listPerLiquid("oasis").contains("No entries created yet")
            )
        }

        @Test
        fun `listPerLiquid returns 'no entry' when no entries with that liquid exist`() {
            assertEquals(5, populatedEntries!!.numberOfEntries())
            val dt = populatedEntries!!.listPerLiquid("oasis")
            assertTrue(dt.contains("No entries with liquid type: oasis"))
        }

        @Test
        fun `listPerLiquid returns all entries with said liquid when they exist`() {
            assertEquals(5, populatedEntries!!.numberOfEntries())
            val dt = populatedEntries!!.listPerLiquid("water")
            assertTrue(dt.contains("14:37"))
            assertFalse(dt.contains("16:17"))
            assertFalse(dt.contains("09:41"))
            assertTrue(dt.contains("10:54"))
            assertFalse(dt.contains("12:28"))
        }




    }

    @Nested
    inner class UpdateEntries {
        @Test
        fun `updating a non-existing entry returns false`() {
            assertFalse(populatedEntries!!.updateDrink(127, Drink(250, "hot chocolate", "08:59", "11/04/2022")))
            assertFalse(populatedEntries!!.updateDrink(126, Drink(102, "woter", "00:58", "08/08/2008")))
            assertFalse(emptyEntries!!.updateDrink(125, Drink(850, "7up", "01:01", "01/07/2002")))
        }

        @Test
        fun `updating an existing entry returns true and updates it`() {
            // entry exists ?
            assertEquals(entry3, populatedEntries!!.findEntry(2))
            assertEquals("milk", populatedEntries!!.findEntry(2)!!.liquidType)
            assertEquals(240, populatedEntries!!.findEntry(2)!!.sizeGlassMl)

            // successful update ?
            assertTrue(populatedEntries!!.updateDrink(2, Drink(241, "molk", "09:41", "13/04/2022")))
            assertEquals(241, populatedEntries!!.findEntry(2)!!.sizeGlassMl)
            assertEquals("molk", populatedEntries!!.findEntry(2)!!.liquidType)
        }
    }

    @Nested
    inner class DeleteEntries {

        @Test
        fun `deleting a non-existing entry returns null`() {
            assertNull(emptyEntries!!.deleteDrink(158))
            assertNull(populatedEntries!!.deleteDrink(157))
            assertNull(populatedEntries!!.deleteDrink(156))
        }

        @Test
        fun `deleting an existing entry deletes it`() {
            assertEquals(5, populatedEntries!!.numberOfEntries())
            assertEquals(entry5, populatedEntries!!.deleteDrink(4))
            assertEquals(4, populatedEntries!!.numberOfEntries())
            assertEquals(entry4, populatedEntries!!.deleteDrink(3))
            assertEquals(3, populatedEntries!!.numberOfEntries())
        }
    }

    @Nested
    inner class PersistenceTests {

        @Test
        fun `saving and loading empty collection in XML`() {
            // Saving empty drinks.XML file.
            val savingEntries = DrinkAPI(XMLSerializer(File("drinks.xml")))
            savingEntries.store()

            // Loading empty file
            val loadingEntries = DrinkAPI(XMLSerializer(File("drinks.xml")))
            loadingEntries.load()

            // Comparing saved entries with loaded entries
            assertEquals(0, savingEntries.numberOfEntries())
            assertEquals(0, loadingEntries.numberOfEntries())
            assertEquals(savingEntries.numberOfEntries(), loadingEntries.numberOfEntries())
        }

        @Test
        fun `saving and loading a loaded collection in XML prevents loss of data`() {
            val storingEntries = DrinkAPI(XMLSerializer(File("drinks.xml")))
            storingEntries.add(entry1!!)
            storingEntries.add(entry2!!)
            storingEntries.add(entry3!!)
            storingEntries.store()

            // Loading file to another collection
            val loadingEntries = DrinkAPI(XMLSerializer(File("drinks.xml")))
            loadingEntries.load()

            // Comparing saved entries with loaded entries
            assertEquals(3, storingEntries.numberOfEntries())
            assertEquals(3, loadingEntries.numberOfEntries())
            assertEquals(storingEntries.numberOfEntries(), loadingEntries.numberOfEntries())
            assertEquals(storingEntries.findEntry(0), loadingEntries.findEntry(0))
            assertEquals(storingEntries.findEntry(1), loadingEntries.findEntry(1))
            assertEquals(storingEntries.findEntry(2), loadingEntries.findEntry(2))
        }
    }
}
