package com.jetapptech.halfwarenote.data.local.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jetapptech.halfwarenote.data.local.room.dao.Category_Dao
import com.jetapptech.halfwarenote.data.local.room.dao.CheckBox_Dao
import com.jetapptech.halfwarenote.data.local.room.dao.Media_Dao
import com.jetapptech.halfwarenote.data.local.room.dao.Note_Dao
import com.jetapptech.halfwarenote.data.local.room.dao.Paragraph_Dao
import com.jetapptech.halfwarenote.data.local.room.entities.Category_Room
import com.jetapptech.halfwarenote.data.local.room.entities.CheckBox_Room
import com.jetapptech.halfwarenote.data.local.room.entities.Media_Room
import com.jetapptech.halfwarenote.data.local.room.entities.Note_Room
import com.jetapptech.halfwarenote.data.local.room.entities.Paragraph_Room


@Database(entities = [CheckBox_Room::class , Media_Room::class , Paragraph_Room::class , Note_Room::class , Category_Room::class  ] , version = 1 , exportSchema = false)
//@TypeConverters(Converter_Room::class)
abstract class Database : RoomDatabase(){


    abstract val checkBoxDao  : CheckBox_Dao
    abstract val paragraphDao : Paragraph_Dao
    abstract val mediaDao     : Media_Dao
    abstract val categoryDao  : Category_Dao
    abstract val noteDao      : Note_Dao

}