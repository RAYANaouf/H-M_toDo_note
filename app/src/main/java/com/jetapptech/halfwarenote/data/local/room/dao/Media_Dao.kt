package com.jetapptech.halfwarenote.data.local.room.dao
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.jetapptech.halfwarenote.data.local.room.entities.Media_Room
import kotlinx.coroutines.flow.Flow
import java.io.File


@Dao
interface Media_Dao {

    @Upsert
    suspend fun insert(mediaRoom: Media_Room)

    @Query("DELETE FROM media WHERE id = :mediaId")
    suspend fun delete(mediaId: Int)


    @Transaction
    suspend fun deleteMediaFile(mediaId: Int , filePath : String){

        delete(mediaId)

        val file = File(filePath)
        if(file.exists()){
            file.delete()
        }

    }



//    @Query("select * from Media ")
//    fun getAllMedia(): Flow<List<Media_Room>>

}