package com.jetapptech.halfwarenote.data.local.room.dao
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.jetapptech.halfwarenote.data.local.room.entities.Paragraph_Room

@Dao
interface Paragraph_Dao{

    @Upsert
    suspend fun insert(paragraphRoom: Paragraph_Room)

    @Delete
    suspend fun delete(paragraphRoom: Paragraph_Room)

//    @Query("select * from Paragraph ")
//    fun getAllParagraph(): Flow<List<Paragraph_Room>>


}