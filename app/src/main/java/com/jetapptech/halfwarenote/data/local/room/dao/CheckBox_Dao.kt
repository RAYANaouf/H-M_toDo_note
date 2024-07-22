package com.jetapptech.halfwarenote.data.local.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.jetapptech.halfwarenote.data.local.room.entities.CheckBox_Room
import kotlinx.coroutines.flow.Flow


@Dao
interface CheckBox_Dao{

    @Upsert
    suspend fun insert(checkboxDao: CheckBox_Room)

    @Delete
    suspend fun delete(checkboxDao: CheckBox_Room)

    @Query("select * from CheckBox ")
    fun getAllCheckBox(): Flow<List<CheckBox_Room>>


}