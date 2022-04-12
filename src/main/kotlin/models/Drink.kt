package models

import java.util.Date

data class Drink(val sizeGlassMl: Int,
                val liquidType: String,
                val timeTaken: String,
                val date: Date,
                 val goalAchieved: Boolean,
){
}


