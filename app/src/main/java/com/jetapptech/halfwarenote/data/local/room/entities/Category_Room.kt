package com.jetapptech.halfwarenote.data.local.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category")
data class Category_Room (
    @PrimaryKey(autoGenerate = true)
    val id         : Int    = 0,
    val category   : String = "",
)