package com.jetapptech.halfwarenote.data.local.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.jetapptech.halfwarenote.data.local.room.entities.Category_Room
import com.jetapptech.halfwarenote.data.local.room.entities.CheckBox_Room
import kotlinx.coroutines.flow.Flow



@Dao
interface Category_Dao{

    @Upsert
    suspend fun insert(categoryDao: Category_Room)

    @Delete
    suspend fun delete(categoryDao: Category_Room)

    @Query("select * from category ")
    fun getCategories(): Flow<List<Category_Room>>


}