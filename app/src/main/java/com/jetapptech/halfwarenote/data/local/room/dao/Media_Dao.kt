package com.jetapptech.halfwarenote.data.local.room.dao
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.jetapptech.halfwarenote.data.local.room.entities.Media_Room
import kotlinx.coroutines.flow.Flow


@Dao
interface Media_Dao {

    @Upsert
    suspend fun insert(mediaRoom: Media_Room)

    @Delete
    suspend fun delete(mediaRoom: Media_Room)



//    @Query("select * from Media ")
//    fun getAllMedia(): Flow<List<Media_Room>>

}